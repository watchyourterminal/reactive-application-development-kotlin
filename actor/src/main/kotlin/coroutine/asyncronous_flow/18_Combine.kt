package coroutine.asyncronous_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300ms
    val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400ms
    val zipStartTime = System.currentTimeMillis() // remember the start time
    nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string
        .collect { // collect and print
            println("$it at ${System.currentTimeMillis() - zipStartTime} ms from start")
        }
    val combineStartTime = System.currentTimeMillis() // remember the start time
    nums.combine(strs) { a, b -> "$a -> $b" } // compose a single string with "combine
        .collect { // collect and print
            println("$it at ${System.currentTimeMillis() - combineStartTime} ms from start")
        }
}