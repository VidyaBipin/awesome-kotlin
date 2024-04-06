package di

import utils.logger
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.typeOf
import kotlin.time.measureTimedValue

inline fun <reified T : Any> buildModule(): T {
    val log = logger {}

    val moduleType = typeOf<T>()

    val beanTreeResult = measureTimedValue {
        val beans = mutableMapOf<KType, Lazy<Any>>()

        fun traverseModuleTree(
            type: KType,
        ) {
            if (beans[type] == null) {
                val clazz = type.classifier as KClass<*>
                val constructor = clazz.primaryConstructor
                    ?: error("Primary constructor not found for $clazz")
                val parameters = constructor.parameters

                val createFunction = lazy(
                    mode = LazyThreadSafetyMode.NONE,
                ) {
                    val args = parameters
                        .map { param ->
                            val paramType = param.type

                            beans[paramType]?.value
                        }
                        .toTypedArray()

                    constructor.call(*args)
                }

                beans[type] = createFunction

                parameters.forEach { param ->
                    traverseModuleTree(param.type)
                }
            }
        }

        traverseModuleTree(moduleType)

        beans as Map<KType, Lazy<Any>>
    }

    log.info("Constructing module tree for module {} taken {}", moduleType, beanTreeResult.duration)

    val result = measureTimedValue {
        beanTreeResult.value[moduleType]?.value
            ?: error("Type $moduleType not found")
    }

    log.info("Constructing module {} taken {}", moduleType, result.duration)

    return result.value as T
}
