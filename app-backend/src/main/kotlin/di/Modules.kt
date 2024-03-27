package di

import utils.logger
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.typeOf
import kotlin.time.measureTimedValue

interface EntryPoint : AutoCloseable {
    suspend fun run()
}

@PublishedApi
internal val log = logger {}

suspend inline fun <reified T : EntryPoint> runApplication(
    vararg builder: KFunction<*>,
) {
    val beans = mutableMapOf<KType, Lazy<Any>>()

    builder.forEach { module ->
        beans[module.returnType] =
            lazy(
                mode = LazyThreadSafetyMode.NONE
            ) {
                val args = module.parameters
                    .map { param ->
                        val paramType = param.type

                        val result = measureTimedValue {
                            beans[paramType]?.value
                                ?: error("Type $paramType not found")
                        }

                        log.info("Initializing bean {} took {}", paramType, result.duration)

                        result.value
                    }
                    .toTypedArray()

                module.call(*args) as Any
            }
    }

    val entryPointType = typeOf<T>()

    val result = measureTimedValue {
        beans[entryPointType]?.value
            ?: error("Type $entryPointType not found")
    }

    log.debug("Constructing {} taken {}", entryPointType, result.duration)

    (result.value as EntryPoint).run()
}
