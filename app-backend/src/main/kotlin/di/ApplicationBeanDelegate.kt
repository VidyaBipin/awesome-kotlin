package di

import utils.logger
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.time.measureTimedValue

/**
 * This class is not thread-safe, make sure bean initialization happens in a single thread.
 */
class MutableBean<V>(
    private val initializer: () -> V,
    var name: String,
) {
    private var _value: V? = null

    val get: V
        get() {
            val currentValue = _value
            return if (currentValue == null) {
                val newValue = measureTimedValue(initializer).let { timedValue ->
                    log.info("Initializing bean $name took ${timedValue.duration}")
                    timedValue.value
                }
                _value = newValue
                newValue
            } else {
                currentValue
            }
        }

    val isInitialized: Boolean
        get() = _value != null

    /**
     * Mock the bean with a custom function.
     * This is useful for testing purposes.
     */
    fun mock(
        mockFn: () -> V,
    ) {
        log.debug("Mocking bean $name")
        _value = mockFn()
    }

    /**
     * Set the value of the bean directly.
     * This is useful for testing purposes.
     */
    fun setBeanValue(value: V) {
        log.debug("Setting new value to bean $name")
        _value = value
    }

    private companion object {
        private val log = logger {}
    }
}

class ApplicationBeanDelegate<V>(
    private val initializer: () -> V,
) : ReadOnlyProperty<Any, MutableBean<V>> {
    private var _value: MutableBean<V>? = null

    /**
     * Returns the initializer wrapped in [MutableBean].
     * If the value is not initialized, it will be initialized by calling the initializer.
     * If the value is already initialized, the current value will be returned.
     *
     * Not thread-safe, expected to be called in an implicit chain from the single (usually main) thread.
     */
    override fun getValue(thisRef: Any, property: KProperty<*>): MutableBean<V> {
        val currentValue = _value
        return if (currentValue == null) {
            val newValue = MutableBean(
                initializer = initializer,
                name = "${thisRef::class.qualifiedName}.${property.name}"
            )
            _value = newValue
            newValue
        } else {
            currentValue
        }
    }
}

/**
 * Create a library with a name: to-been-injected
 */
fun <V> bean(initializer: () -> V) = ApplicationBeanDelegate(
    initializer = initializer,
)
