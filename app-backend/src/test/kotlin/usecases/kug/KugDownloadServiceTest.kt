package usecases.kug

import di.buildModule
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.AssertionFailureBuilder.assertionFailure
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class KugDownloadServiceTest {
    private val kugModule = buildModule<KugModule>()

    @AfterEach
    fun close() {
    }

    @Test
    fun `test getting yaml file with user groups`() = runTest {
        val yaml = kugModule.kugDownloadService.get.download()

        if (!yaml.contains("https://bkug.by/")) {
            assertionFailure()
                .message("yaml")
                .expected("Yaml with Kotlin User Groups")
                .actual(yaml)
                .buildAndThrow()
        }
    }

    @Test
    fun `test parsing yaml file with user groups`() = runTest {
        val sections = kugModule.kugDownloadService.get.pull()
        val bkug = sections.find { it.section == "Europe" }
            ?.groups
            ?.find { it.url == "https://bkug.by/" }

        assertEquals(
            KugDownloadService.Kug(
                name = "Belarus KUG",
                country = "Belarus",
                url = "https://bkug.by/",
                position = KugDownloadService.Position(
                    lat = 53.709807,
                    lng = 27.953389,
                ),
            ),
            bkug
        )
    }
}
