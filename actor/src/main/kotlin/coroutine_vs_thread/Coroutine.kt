package coroutine_vs_thread

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    (0..10000).map {
        launch {
            delay(5000L)
            println(".")
            delay(10000L)
        }
    }.forEach {
        it.join()
    }
    println(" Done.")
}