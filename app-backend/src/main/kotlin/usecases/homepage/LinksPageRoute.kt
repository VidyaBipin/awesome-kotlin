package usecases.kug

import io.ktor.http.ContentType
import io.ktor.server.application.*
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import kotlinx.html.BODY
import kotlinx.html.DIV
import kotlinx.html.HTML
import kotlinx.html.LI
import kotlinx.html.UL
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.h2
import kotlinx.html.h3
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.img
import kotlinx.html.input
import kotlinx.html.lang
import kotlinx.html.li
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.p
import kotlinx.html.script
import kotlinx.html.section
import kotlinx.html.span
import kotlinx.html.style
import kotlinx.html.title
import kotlinx.html.ul
import kotlinx.html.unsafe
import ktor.KtorRoute
import usecases.homepage.Header
import usecases.homepage.NavigationBar
import usecases.homepage.NavigationLink
import usecases.homepage.logos
import usecases.homepage.renderHtmlComponent
import usecases.homepage.renderHtmlPage
import usecases.links.CategoryV1
import usecases.links.LinkStateV1
import usecases.links.LinkV1
import usecases.links.SubcategoryV1
import usecases.version.KotlinVersionFetcher

enum class LinksPageType {
    AWESOME,
    EVERYTHING,
    KUGS,
}
class LinksPageRoute(
    private val kotlinVersionFetcher: KotlinVersionFetcher,
    private val links: List<CategoryV1>,
    private val path: String,
    private val type: LinksPageType,
) : KtorRoute {
    override fun Routing.install() {
        get(path) {
            val versions = kotlinVersionFetcher.getLatestVersions(listOf("1.8", "1.9"))
            val isHtmxRequest = call.request.headers["HX-Request"] == "true"
            val query = call.request.queryParameters["q"] ?: ""

            val sortedLinks = links.map { category -> category.sortLinks() }
            val filteredLinks = when (type) {
                LinksPageType.AWESOME -> if (query.isEmpty()) {
                    sortedLinks.filter { it.name != "Kotlin User Groups" }
                        .map { category ->
                            category.awesome()
                        }
                        .filter { category ->
                            category.subcategories.isNotEmpty()
                        }
                } else {
                    sortedLinks.filter { it.name != "Kotlin User Groups" }
                        .query(query)
                }
                LinksPageType.EVERYTHING -> if (query.isEmpty()) {
                    sortedLinks.filter { it.name != "Kotlin User Groups" }
                } else {
                    sortedLinks.filter { it.name != "Kotlin User Groups" }
                        .query(query)
                }
                LinksPageType.KUGS -> sortedLinks.filter { it.name == "Kotlin User Groups" }
            }

            call.respondText(
                text = if (isHtmxRequest) {
                    renderHtmlComponent {
                        HomePage(
                            currentPage = path,
                            query = query,
                            versions = versions,
                            categories = filteredLinks,
                        )
                    }
                } else {
                    renderHtmlPage {
                        HomePage(
                            currentPage = path,
                            query = query,
                            versions = versions,
                            categories = filteredLinks,
                        )
                    }
                },
                contentType = ContentType.Text.Html,
            )
        }
    }
}

fun CategoryV1.sortLinks(): CategoryV1 {
    val subcategories = subcategories.sortedByDescending { subcategory -> subcategory.links.size }

    return CategoryV1(
        name = name,
        subcategories = subcategories,
    )
}

fun linkMatches(link: LinkV1, searchTerm: String): Boolean {
    val lowercasedSearchTerm = searchTerm.lowercase()

    return link.name.lowercase().contains(lowercasedSearchTerm) ||
        link.desc?.lowercase()?.contains(lowercasedSearchTerm) == true ||
        link.tags.any { tag -> tag.lowercase().contains(lowercasedSearchTerm) }
}

fun SubcategoryV1.query(
    query: String,
): SubcategoryV1 {
    val searchTerm = query.lowercase()

    val linksFiltered = links.fold(mutableListOf<LinkV1>()) { acc, link ->
        if (linkMatches(link, searchTerm)) {
            acc.add(link)
        }

        acc
    }

    return SubcategoryV1(
        name = name,
        links = linksFiltered,
    )
}

fun CategoryV1.awesome(): CategoryV1 {
    val subcategoriesFiltered = subcategories.fold(mutableListOf<SubcategoryV1>()) { acc, subcategory ->
        val linksFiltered = subcategory.links.filter { link -> link.state === LinkStateV1.AWESOME }

        if (linksFiltered.isNotEmpty()) {
            acc.add(
                SubcategoryV1(
                    name = subcategory.name,
                    links = linksFiltered,
                )
            )
        }

        acc
    }

    return CategoryV1(
        name = name,
        subcategories = subcategoriesFiltered,
    )
}

fun List<CategoryV1>.query(
    query: String,
): List<CategoryV1> {
    val searchTerm = query.lowercase()

    return fold(mutableListOf()) { acc, category ->
        val subcategories = category.subcategories.fold(mutableListOf<SubcategoryV1>()) { acc, subcategory ->
            val subcategoryFiltered = subcategory.query(searchTerm)

            if (subcategoryFiltered.links.isNotEmpty()) {
                acc.add(subcategoryFiltered)
            }

            acc
        }

        if (subcategories.isNotEmpty()) {
            acc.add(
                CategoryV1(
                    name = category.name,
                    subcategories = subcategories,
                )
            )
        }

        acc
    }

}

fun HTML.HomePage(
    currentPage: String,
    query: String,
    versions: List<String>,
    categories: List<CategoryV1>,
) {
    lang = "en"
    CommonHead("Kotlin is Awesome!")
    LinksBody(
        currentPage = currentPage,
        query = query,
        versions = versions,
        categories = categories,
    )
}

val links = listOf(
    NavigationLink(
        name = "Essential",
        href = "/",
    ),
    NavigationLink(
        name = "Everything",
        href = "/resources",
    ),
    NavigationLink(
        name = "Kotlin User Groups",
        href = "/kugs",
    ),
    NavigationLink(
        name = "Github",
        href = "https://github.com/KotlinBy/awesome-kotlin",
        title = "Fork me!"
    ),
)

fun BODY.Search(
    query: String,
    currentPage: String,
) {
    section(classes = "search") {
        form(classes = "search_wrapper") {
            input(classes = "search_field") {
                attributes["hx-push-url"] = "true"
                attributes["hx-get"] = currentPage
                attributes["hx-trigger"] = "keyup changed delay:500ms"
                attributes["hx-target"] = "#root"

                name = "q"
                value = query
                placeholder = "Type to Filter"
            }
        }
    }
}

fun BODY.Category(
    categoryV1: CategoryV1,
) {
    section(classes = "category") {
        div(classes = "category_wrapper") {
            h2(classes = "category_title") {
                +categoryV1.name
            }
            div(classes = "category_wrapper_lists") {
                categoryV1.subcategories.forEach { subcategory ->
                    Subcategory(
                        prefix = categoryV1.name,
                        subcategoryV1 = subcategory,
                    )
                }
            }
        }
    }
}

fun getAnchor(prefix: String, suffix: String): String {
    val normalizedPrefix = prefix.replace(" ", "_").lowercase()
    val normalizedSuffix = suffix.replace(" ", "_").lowercase()

    return "${normalizedPrefix}-${normalizedSuffix}"
}

fun DIV.Subcategory(
    prefix: String,
    subcategoryV1: SubcategoryV1,
) {
    section(classes = "list") {
        h3(classes = "list_title") {
            id = getAnchor(
                prefix = prefix,
                suffix = subcategoryV1.name,
            )
            a(href = "#${getAnchor(prefix, subcategoryV1.name)}") {
                +subcategoryV1.name
            }
        }
        ul(classes = "list_list") {
            subcategoryV1.links
                .filter { link -> link.state === LinkStateV1.AWESOME }
                .forEach { link ->
                    Listitem(link)
                }
            subcategoryV1.links
                .filter { link -> link.state === LinkStateV1.DEFAULT }
                .forEach { link ->
                    Listitem(link)
                }
            subcategoryV1.links
                .filter { link -> link.state === LinkStateV1.ARCHIVED }
                .forEach { link ->
                    Listitem(link)
                }
            subcategoryV1.links
                .filter { link -> link.state === LinkStateV1.UNSUPPORTED }
                .forEach { link ->
                    Listitem(link)
                }
        }
    }
}


fun UL.Listitem(
    link: LinkV1,
) {
    val classes = buildList {
        add("listitem")
        when (link.state) {
            LinkStateV1.ARCHIVED -> add("listitem_archived")
            LinkStateV1.UNSUPPORTED -> add("listitem_unsupported")
            LinkStateV1.AWESOME,
            LinkStateV1.DEFAULT -> Unit
        }
    }
    li(classes = classes.joinToString(separator = " ")) {
        Stars(link)

        a(
            href = link.href,
            classes = "listitem_link",
        ) {
            target = "_blank"
            rel = "nofollow noopener"
            title = getTitle(link)
            +split(link.name)
        }

        LastUpdated(link)

        span(classes = "listitem_description") {
            unsafe {
                +link.desc.orEmpty()
            }
        }
    }
}

fun getTitle(link: LinkV1): String {
    return when(link.state) {
        LinkStateV1.AWESOME -> "[awesome] ${link.name}"
        LinkStateV1.UNSUPPORTED -> "[unsupported] ${link.name}"
        LinkStateV1.ARCHIVED -> "[archived] ${link.name}"
        LinkStateV1.DEFAULT -> link.name
    }
}

fun LI.LastUpdated(
    link: LinkV1,
) {
    if (link.update != null) {
        p(classes = "listitem_description") {
            +"Last update: ${link.update}"
        }
    }
}

fun split(name: String): String {
    // replace "/" with "/ and https://en.wikipedia.org/wiki/Zero-width_space"
    return name.replace("/", "/​")
}

fun LI.Stars(
    link: LinkV1,
) {
    if (link.star != null) {
        span(classes = "listitem_star") {
            span(classes = "listitem_star_count") {
                +link.star.toString()
            }
            img(classes = "listitem_star_icon") {
                alt = ""
                src = "/star/star.svg"
            }
        }
    }
}

fun BODY.VersionBar(
    versions: List<String>,
) {
    section(classes = "bar") {
        div(classes = "bar_wrapper") {
            versions.forEach { version ->
                span(classes = "kotlin_version") {
                    +version
                }
            }
        }
    }
}

fun HTML.LinksBody(
    currentPage: String,
    query: String,
    versions: List<String>,
    categories: List<CategoryV1>,
) {
    body {
        id = "root"
        NavigationBar(
            currentPage = currentPage,
            links = links,
        )
        Header(
            index = 0,
            logos = logos,
        )
        Search(
            query = query,
            currentPage = currentPage,
        )
        VersionBar(
            versions = versions,
        )
        categories.forEach { category ->
            Category(
                categoryV1 = category,
            )
        }
    }
}

fun HTML.CommonHead(
    pageTitle: String,
) {
    head {
        meta(charset = "UTF-8")
        title(pageTitle)
        meta(
            name = "viewport",
            content = "width=device-width,initial-scale=1",
        )
        link {
            rel = "stylesheet"
            href = "/styles.css"
        }
        style {

        }
        script(
            src = "https://unpkg.com/htmx.org@1.9.11/dist/htmx.min.js",
        ) {
            integrity = "sha384-0gxUXCCR8yv9FM2b+U3FDbsKthCI66oH5IA9fHppQq9DDMHuMauqq1ZHBpJxQ0J0"
            attributes["crossorigin"] = "anonymous"
        }
        link(
            rel = "alternate",
            type = "application/atom+xml",
            href = "/rss.xml"
        ) {
            title = "Kotlin.Link - 20 latest"
        }
        link(
            rel = "alternate",
            type = "application/atom+xml",
            href = "/rss-full.xml",
        ) {
            title = "Kotlin.Link - full archive"
        }
        meta(
            name = "msapplication-TileColor",
            content = "#FFFFFF",
        )
        meta(
            name = "msapplication-TileImage",
            content = "/favicon-144.png",
        )
        link(
            rel = "icon",
            href = "/favicon-32.png",
        ) {
            sizes = "32x32"
        }
        link(
            rel = "icon",
            href = "/favicon-16.png",
        ) {
            sizes = "16x16"
        }
        link(
            rel = "shortcut icon",
            href = "/favicon.ico",
        )
        link(
            rel = "shortcut icon",
            href = "/favicon-196.png",
        ) {
            sizes = "196x196"
        }
        link(
            rel = "mask-icon",
            href = "/mask-favicon.svg",
        ) {
            attributes["color"] = "#009DD9"
        }
        link(
            rel = "apple-touch-icon-precomposed",
            href = "/favicon-180.png",
        ) {
            sizes = "180x180"
        }
        link(
            rel = "apple-touch-icon-precomposed",
            href = "/favicon-152.png",
        )
        link(
            rel = "apple-touch-icon-precomposed",
            href = "/favicon-152.png",
        ) {
            sizes = "152x152"
        }
        link(
            rel = "apple-touch-icon-precomposed",
            href = "/favicon-144.png",
        ) {
            sizes = "144x144"
        }
        link(
            rel = "apple-touch-icon-precomposed",
            href = "/favicon-120.png",
        ) {
            sizes = "120x120"
        }
        link(
            rel = "apple-touch-icon-precomposed",
            href = "/favicon-114.png",
        ) {
            sizes = "114x114"
        }
        link(
            rel = "apple-touch-icon-precomposed",
            href = "/favicon-76.png",
        ) {
            sizes = "76x76"
        }
        link(
            rel = "apple-touch-icon-precomposed",
            href = "/favicon-57.png",
        ) {
            sizes = "57x57"
        }
    }
}
