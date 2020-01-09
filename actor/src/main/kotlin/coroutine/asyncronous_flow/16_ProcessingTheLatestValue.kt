package coroutine.asyncronous_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private fun foo(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // pretend we are asynchronously waiting 100ms
        emit(i) // emit next value
    }
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        foo()
            .collectLatest { // cancel & restart on the latest value
                println("Collecting $it")
                delay(300) // pretend we are processing it for 300ms
                println("Done $it")
            }
    }
    println("Collected in $time ms")
}