package usecases.kug

import io.ktor.http.ContentType
import io.ktor.server.application.*
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import ktor.KtorRoute
import usecases.homepage.renderHtmlPage

class ResourcesPageRoute() : KtorRoute {
    override fun Routing.install() {
        get("/resources") {
            val query = call.request.queryParameters["q"] ?: ""

            call.respondText(
                text = renderHtmlPage {
                    HomePage(
                        currentPage = "/resources",
                        query = query,
                        versions = listOf(),
                        categories = listOf(),
                    )
                },
                contentType = ContentType.Text.Html,
            )
        }
    }
}
