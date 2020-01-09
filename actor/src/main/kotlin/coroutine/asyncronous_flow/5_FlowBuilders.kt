package coroutine.asyncronous_flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    (1..3).asFlow().collect { println(it) }
}