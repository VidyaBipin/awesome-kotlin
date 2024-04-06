@file:JvmName("Application")

import di.bean
import db.FlywayModule
import db.JdbcModule
import di.buildModule
import kotlinx.coroutines.runBlocking
import lifecycle.LifecycleModule
import usecases.stars_job.StarsJobModule
import utils.close
import utils.logger
import java.lang.management.ManagementFactory
import java.lang.management.RuntimeMXBean
import kotlin.time.Duration.Companion.milliseconds

suspend fun main() {
    buildModule<ApplicationModule>()
        .use(ApplicationModule::run)
}

class ApplicationModule(
    private val jdbcModule: JdbcModule,
    private val httpClientModule: HttpClientModule,
    private val lifecycleModule: LifecycleModule,
    private val starsJobModule: StarsJobModule,
    private val flywayModule: FlywayModule,
    private val serverModule: ServerModule,
) : AutoCloseable {
    fun run() = runBlocking {
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
