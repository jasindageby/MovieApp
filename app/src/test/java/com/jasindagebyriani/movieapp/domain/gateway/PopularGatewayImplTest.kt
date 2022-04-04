package com.jasindagebyriani.movieapp.domain.gateway

import com.jasindagebyriani.movieapp.domain.api.MovieApi
import com.jasindagebyriani.movieapp.domain.entity.Popular
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class PopularGatewayImplTest {
    @get:Rule
    val server = MockWebServer()

    private lateinit var popularGateway: PopularGateway

    @Before
    fun setup() {
        popularGateway = PopularGatewayImpl(createApi())
    }

    @Test
    fun `given response success when getMovie should return Popular entity`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody(popularResponses))

        val expected = listOf(
            Popular(
                634649,
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked",
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg",
                "2021-12-15",
                "en",
                8.2,
                10923,
                listOf(28, 12, 878)
            ),
            Popular(
                508947,
                "Turning Red",
                "Thirteen-year-old Mei",
                "/qsdjk9oAKSQMWs0Vt5Pyfh6O4GZ.jpg",
                "/fOy2Jurz9k6RnJnMUMRDAgBwru2.jpg",
                "2022-03-01",
                "en",
                7.5,
                1374,
                listOf(16, 10751, 35, 14)
            )
        )

        popularGateway.getPopularMovies()
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue(expected)
            .assertNoErrors()
    }

    @Test
    fun `given response success with null path when getMovie should return Popular entity with empty path`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody(popularResponseWithNullPath))

        val expected = listOf(
            Popular(
                634649,
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked",
                "",
                "",
                "2021-12-15",
                "en",
                8.2,
                10923,
                listOf(28, 12, 878)
            )
        )

        popularGateway.getPopularMovies()
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue(expected)
            .assertNoErrors()
    }

    private fun createApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    companion object {
        private val popularResponses = """
        {
            "page": 1,
            "results": [
            {
                "adult": false,
                "backdrop_path": "/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg",
                "genre_ids": [
                    28,
                    12,
                    878
                ],
                "id": 634649,
                "original_language": "en",
                "original_title": "Spider-Man: No Way Home",
                "overview": "Peter Parker is unmasked",
                "popularity": 8426.2,
                "poster_path": "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "release_date": "2021-12-15",
                "title": "Spider-Man: No Way Home",
                "video": false,
                "vote_average": 8.2,
                "vote_count": 10923
            },
             {
                "adult": false,
                "backdrop_path": "/fOy2Jurz9k6RnJnMUMRDAgBwru2.jpg",
                "genre_ids": [
                    16,
                    10751,
                    35,
                    14
                ],
                "id": 508947,
                "original_language": "en",
                "original_title": "Turning Red",
                "overview": "Thirteen-year-old Mei",
                "popularity": 8326.002,
                "poster_path": "/qsdjk9oAKSQMWs0Vt5Pyfh6O4GZ.jpg",
                "release_date": "2022-03-01",
                "title": "Turning Red",
                "video": false,
                "vote_average": 7.5,
                "vote_count": 1374
            }],
            "total_pages": 32988,
            "total_results": 659749
        }
        """.trimIndent()

        private val popularResponseWithNullPath = """
            {
            "page": 1,
            "results": [
            {
                "adult": false,
                "backdrop_path": null,
                "genre_ids": [
                    28,
                    12,
                    878
                ],
                "id": 634649,
                "original_language": "en",
                "original_title": "Spider-Man: No Way Home",
                "overview": "Peter Parker is unmasked",
                "popularity": 8426.2,
                "poster_path": null,
                "release_date": "2021-12-15",
                "title": "Spider-Man: No Way Home",
                "video": false,
                "vote_average": 8.2,
                "vote_count": 10923
            }],
            "total_pages": 32988,
            "total_results": 659749
        }
        """.trimIndent()
    }
}