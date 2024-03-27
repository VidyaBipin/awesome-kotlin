@file:JvmName("Application")

import config.ConfigModule
import di.EntryPoint
import di.bean
import di.runApplication
import ktor.KtorFeaturesModule
import lifecycle.LifecycleModule
import metrics.MetricsModule
import usecases.github.GithubModule
import usecases.healthcheck.HealthcheckModule
import usecases.kug.HomePageModule
import usecases.kug.KugModule
import usecases.links.LinksModule
import usecases.rss.RssModule
import usecases.signup.JwtModule
import usecases.signup.LoginModule
import usecases.signup.RegisterModule
import usecases.stars_job.StarsJobModule
import usecases.version.KotlinVersionModule
import utils.close
import utils.logger
import java.lang.management.ManagementFactory
import java.lang.management.RuntimeMXBean
import kotlin.time.Duration.Companion.milliseconds

suspend fun main() {
    runApplication<ApplicationModule>(
        ::HttpClientModule,
        ::YamlModule,
        ::XmlModule,
        ::ConfigModule,
        ::JdbcModule,
        ::LifecycleModule,
        ::StarsJobModule,
        ::JwtModule,
        ::JooqModule,
        ::FlywayModule,
        ::LoginModule,
        ::RegisterModule,
        ::KugModule,
        ::GithubModule,
        ::HealthcheckModule,
        ::LinksModule,
        ::MetricsModule,
        ::RssModule,
        ::KotlinVersionModule,
        ::KtorFeaturesModule,
        ::ServerModule,
        ::HomePageModule,
        ::ApplicationModule,
    )
}

class ApplicationModule(
    private val jdbcModule: JdbcModule,
    private val httpClientModule: HttpClientModule,
    private val lifecycleModule: LifecycleModule,
    private val starsJobModule: StarsJobModule,
    private val flywayModule: FlywayModule,
    private val serverModule: ServerModule,
) : EntryPoint {
    override suspend fun run() {
        val gracefulShutdown = lifecycleModule.gracefulShutdown.get
        starsJobModule.starsJobScheduler.get.start()
        lifecycleModule.shutdownHandler.get.registerHook()
        flywayModule.flyway.get.migrate()
        serverModule.ktorServer.get.start(wait = false)
        log.info("Server started in {}", runtimeMXBean.get.uptime.milliseconds)
        gracefulShutdown.waitForShutdown()
    }

    override fun close() {
        jdbcModule.close {}
        httpClientModule.close {}
    }

    val runtimeMXBean by bean<RuntimeMXBean> {
        ManagementFactory.getRuntimeMXBean()
    }

    private companion object {
        private val log = logger<ApplicationModule>()
    }
}
