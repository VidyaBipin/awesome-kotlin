package usecases.kug

import io.ktor.http.ContentType
import io.ktor.server.application.*
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import ktor.KtorRoute
import usecases.homepage.barCss
import usecases.homepage.bodyCss
import usecases.homepage.categoryCss
import usecases.homepage.commonCss
import usecases.homepage.headerCss
import usecases.homepage.kotlinVersionCss
import usecases.homepage.listCss
import usecases.homepage.listItemCss
import usecases.homepage.navigationCss
import usecases.homepage.searchCss

class CssRoute() : KtorRoute {
    override fun Routing.install() {
        get("/styles.css") {
            call.respondText(
                contentType = ContentType.Text.CSS,
                text = buildString {
                    append(commonCss)
                    append(bodyCss)
                    append(headerCss)
                    append(searchCss)
                    append(listItemCss)
                    append(listCss)
                    append(categoryCss)
                    append(kotlinVersionCss)
                    append(barCss)
                    append(navigationCss)
                }
            )
        }
    }
}
