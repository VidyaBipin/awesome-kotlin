package usecases.homepage

import kotlinx.html.BODY
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.section
import kotlinx.html.span
import kotlinx.html.title

fun BODY.NavigationBar(
    currentPage: String,
    links: List<NavigationLink>,
) {
    section(classes = "navigation_bar") {
        div(classes = "navigation_bar_wrapper") {
            links.forEachIndexed { idx, link ->
                a(href = link.href, classes = "link ${if (currentPage == link.href) "active" else ""}") {
                    title = link.title ?: link.name
                    +link.name
                }
                if (idx != links.size - 1) {
                    span(classes = "separator") {
                        +"|"
                    }
                }
            }
        }
    }
}

data class NavigationLink(
    val name: String,
    val href: String,
    val title: String? = null,
)
