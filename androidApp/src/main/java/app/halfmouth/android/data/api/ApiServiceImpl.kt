package app.halfmouth.android.data.api

import app.halfmouth.android.data.remote.ThingSpeakResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url


class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun getThingSpeakValues(results: String): ThingSpeakResponse {
        return try {
            client.get {
                url(HttpRoutes.REQUEST_URL)
                parameter("api_key", "ZL0IH5O2QK5U4NNS")
                parameter("results", results)
            }
        } catch (e: RedirectResponseException) {
            // 3xx - response
            println("Error: ${e.response.status.description}")
            ThingSpeakResponse(null, emptyList())
        } catch (e: ClientRequestException) {
            // 4xx - response
            println("Error: ${e.response.status.description}")
            ThingSpeakResponse(null, emptyList())
        } catch (e: ServerResponseException) {
            // 5xx - response
            println("Error: ${e.response.status.description}")
            ThingSpeakResponse(null, emptyList())
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ThingSpeakResponse(null, emptyList())
        }
    }
}
