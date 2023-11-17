package app.halfmouth.android.data.api

import app.halfmouth.android.data.remote.PostRequest
import app.halfmouth.android.data.remote.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostServiceImpl(
    private val client: HttpClient
) : PostService {
    override suspend fun getPost(): List<PostResponse> {
        return try {
            client.get { url(HttpRoutes.POST_URL) }
        } catch (e: RedirectResponseException) {
            // 3xx - response
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - response
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - response
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse> {
                url(HttpRoutes.POST_URL)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e: RedirectResponseException) {
            // 3xx - response
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - response
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - response
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }


}