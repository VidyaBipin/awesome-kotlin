package usecases.homepage

import io.ktor.http.ContentType
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import kotlinx.html.DIV
import kotlinx.html.HTML
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.lang
import ktor.KtorRoute
import usecases.kug.CommonHead

class LoginRoute : KtorRoute {
    override fun Routing.install() {
        get("/login") {
            call.respondText(
                contentType = ContentType.Text.Html,
                text = renderHtmlPage {
                    LoginPage(
                        currentPage = "/login",
                    )
                },
            )
        }
    }
}

fun HTML.LoginPage(
    currentPage: String,
) {
    lang = "en"
    CommonHead("Login")
    body(classes = "login") {
        div(classes = "login_wrapper") {
            LoginForm()
        }
    }
}

fun DIV.LoginForm() {
    div(classes = "login_form") {
        div(classes = "login_form_wrapper") {
            a(classes = "login_form_button") {
                href = "/auth/github/redirect"
                +"Login with GitHub"
            }
        }
    }
}
