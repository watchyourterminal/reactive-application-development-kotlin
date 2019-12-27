package coroutine.basics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main() {
    // GlobalScope.launch + delay(...)는 "thread { ... } + Thread.sleep(..)"과 같음
    // 다만 delay는 thread를 blocking하지 않고 Thread.sleep은 thread를 blocking하게됨.
//    GlobalScope.launch { // launch a new coroutine in background and continue
//        delay(1000L)
//        println("World!")
//    }
    thread {
        Thread.sleep(1000L)
        println("World!")
    }
    println("Hello,") // main thread continues here immediately
//    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
    runBlocking { // but this expression blocks the main thread
        delay(2000L) // ... while we delay for 2 seconds to keep JVM alive
    }
}