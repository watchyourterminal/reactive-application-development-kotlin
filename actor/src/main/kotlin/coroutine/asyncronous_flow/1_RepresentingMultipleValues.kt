package coroutine.asyncronous_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

//fun foo(): List<Int> = listOf(1, 2, 3)

// Sequences
//fun foo(): Sequence<Int> = sequence { // sequence builder
//    for (i in 1..3) {
//        Thread.sleep(100) // pretend we are computing it
//        yield(i) // yield next value
//    }
//}

// Suspending functions
private suspend fun foo(): List<Int> {
    delay(1000) // pretend we are doing something asynchronous here
    return listOf(1, 2, 3)
}

fun main() = runBlocking {
    foo().forEach { value -> println(value) }
}