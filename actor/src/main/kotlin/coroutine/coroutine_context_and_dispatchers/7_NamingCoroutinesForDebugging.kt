package coroutine.coroutine_context_and_dispatchers

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

fun main() = runBlocking {
    val log = LoggerFactory.getLogger(Thread.currentThread().name)

    log.info("Started main coroutine")
    // run two background value computations
    val v1 = async(CoroutineName("v1coroutine")) {
        delay(500)
        log.info("Computing v1")
        252
    }
    val v2 = async(CoroutineName("v2coroutine")) {
        delay(1000)
        log.info("Computing v2")
        6
    }
    log.info("The answer for v1 / v2 = ${v1.await() / v2.await()}")
}