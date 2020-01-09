package coroutine.asyncronous_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

private fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i First")
    delay(500) // wait 500 ms
    emit("$i Second")
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapMerge { requestFlow(it) }
        .collect {
            println("$it at ${System.currentTimeMillis() - startTime} ms from start")
        }
}