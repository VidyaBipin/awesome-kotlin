package usecases.kug

import di.bean
import usecases.homepage.HeadLogoRoute
import usecases.links.LinksModule
import usecases.version.KotlinVersionModule

open class HomePageModule(
    private val kotlinVersionModule: KotlinVersionModule,
    private val linksModule: LinksModule,
) {
    val homePageRoute by bean {
        LinksPageRoute(
            kotlinVersionFetcher = kotlinVersionModule.kotlinVersionFetcher.get,
            links = linksModule.linkSource.get,
            path = "/",
            type = LinksPageType.AWESOME,
        )
    }

    val resourcesPageRoute by bean {
        LinksPageRoute(
            kotlinVersionFetcher = kotlinVersionModule.kotlinVersionFetcher.get,
            links = linksModule.linkSource.get,
            path = "/resources",
            type = LinksPageType.EVERYTHING,
        )
    }

    val kugsPageRoute by bean {
        LinksPageRoute(
            kotlinVersionFetcher = kotlinVersionModule.kotlinVersionFetcher.get,
            links = linksModule.linkSource.get,
            path = "/kugs",
            type = LinksPageType.KUGS,
        )
    }

    val stylesRoute by bean {
        CssRoute()
    }

    val headLogoRoute by bean {
        HeadLogoRoute()
    }
}

