package usecases.homepage

import java.time.LocalDate
import java.time.Month

val logos: List<HeadLogo>
    get() {
        val activeLogos = _logos.filter { it.show() }
        val exclusiveLogo = activeLogos.find { it.exclusive }

        return if (exclusiveLogo != null) {
            listOf(exclusiveLogo)
        } else {
            activeLogos
        }
    }

private val _logos = listOf(
    HeadLogo(
        src = "/logos/kotlin-force.svg",
        alt = { "Star Wars Day" },
        show = {
            val localDate = LocalDate.now()
            localDate.dayOfMonth == 4 && localDate.month == Month.MAY
        },
        exclusive = true,
    ),
    HeadLogo(
        src = "/logos/kotlin-0.svg",
        alt = { "Kotlin Language Logo" },
        show = { true },
        exclusive = false,
    ),
    HeadLogo(
        src = "/logos/kotlin-1.svg",
        alt = { "Kotlin Language Logo" },
        show = { true },
        exclusive = false,
    ),
    HeadLogo(
        src = "/logos/kotlin-2.png",
        alt = { "Kotlin Language Logo" },
        show = { true },
        exclusive = false,
    ),
    HeadLogo(
        src = "/logos/kotlin-3.svg",
        alt = { "Kotlin Language Logo" },
        show = { true },
        exclusive = false,
    ),
)
