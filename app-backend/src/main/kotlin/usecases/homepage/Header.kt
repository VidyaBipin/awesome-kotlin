package usecases.homepage

import kotlinx.html.BODY
import kotlinx.html.DIV
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.id
import kotlinx.html.img
import kotlinx.html.section
import kotlinx.html.title

data class HeadLogo(
    val src: String,
    val alt: () -> String,
    val show: () -> Boolean,
    val exclusive: Boolean,
    val link: String? = null,
)

fun BODY.Header(
    index: Int,
    logos: List<HeadLogo>,
) {
    val nextLogoIndex = (index + 1) % logos.size

    section(classes = "head") {
        id = "head"
        div(classes = "head_wrapper") {
            attributes["hx-get"] = "/head_logo?index=$nextLogoIndex"
            attributes["hx-trigger"] = "click"
            attributes["hx-target"] = "#head"

            HeadLogoComponent(
                logos[index]
            )
        }
    }
}

fun DIV.HeadLogoComponent(logo: HeadLogo) {
    if (logo.link != null) {
        a(href = logo.link) {
            target = "_blank"
            rel = "noopener noreferrer"
            img(classes = "head_logo") {
                src = logo.src
                alt = logo.alt()
                title = logo.alt()
            }
        }
    } else {
        img(classes = "head_logo") {
            src = logo.src
            alt = logo.alt()
            title = logo.alt()
        }
    }
}
