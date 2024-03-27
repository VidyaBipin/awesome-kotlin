package usecases.homepage

import io.ktor.http.ContentType
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import kotlinx.html.BODY
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.consumers.filter
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import kotlinx.html.stream.createHTML
import ktor.KtorRoute

class HeadLogoRoute : KtorRoute {
    override fun Routing.install() {
        get("/head_logo") {
            val index = call.request.queryParameters["index"]?.toInt() ?: 0

            call.respondText(
                contentType = ContentType.Text.Html,
                text = renderBodyComponent {
                    Header(
                        index = index,
                        logos = logos,
                    )
                },
            )
        }
    }
}

fun renderBodyComponent(
    component: BODY.() -> Unit,
): String {
    return createHTML(prettyPrint = false)
        .filter {
            when (it.tagName) {
                "html", "body" -> SKIP
                else -> PASS
            }
        }
        .body {
            component()
        }
}

fun renderHtmlComponent(
    component: HTML.() -> Unit,
): String {
    return createHTML(prettyPrint = false)
        .filter {
            when (it.tagName) {
                "html", "body" -> SKIP
                "head" -> DROP
                else -> PASS
            }
        }
        .html {
            component()
        }
}

fun renderHtmlPage(
    component: HTML.() -> Unit,
): String {
    return buildString {
        append("<!DOCTYPE html>\n")
        appendHTML(prettyPrint = false)
            .html(block = component)
    }
}
