import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll


inline fun <T> Array<out T>.withEach(action: T.() -> Unit) {
    for (element in this) action(element)
}

suspend fun <T> Sequence<Deferred<T>>.awaitAll(): List<T> = toList().awaitAll()


