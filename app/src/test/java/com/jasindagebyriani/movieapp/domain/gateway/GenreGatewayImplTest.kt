package com.jasindagebyriani.movieapp.domain.gateway

import com.jasindagebyriani.movieapp.domain.api.GenreApi
import com.jasindagebyriani.movieapp.domain.entity.Genre
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class GenreGatewayImplTest {
    @get:Rule
    val server = MockWebServer()

    private lateinit var genreGateway: GenreGateway

    @Before
    fun setup() {
        genreGateway = GenreGatewayImpl(createApi())
    }

    @Test
    fun `given respond success when getGenre should return list of Genre`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody(genreResponses))

        val expected = listOf(
            Genre(28, "Action"),
            Genre(12, "Adventure"),
            Genre(16, "Animation")
        )

        genreGateway.getGenre().test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue(expected)
            .assertNoErrors()
    }

    private fun createApi(): GenreApi {
        return Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GenreApi::class.java)
    }

    companion object {
        private val genreResponses = """
            {
                "genres": [
                    {
                        "id": 28,
                        "name": "Action"
                    },
                    {
                        "id": 12,
                        "name": "Adventure"
                    },
                    {
                        "id": 16,
                        "name": "Animation"
                    }
                ]
            }
        """.trimIndent()
    }
}