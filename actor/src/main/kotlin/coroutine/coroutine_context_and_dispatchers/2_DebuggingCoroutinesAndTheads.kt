package coroutine.coroutine_context_and_dispatchers

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

fun main() = runBlocking<Unit> {
    val log = LoggerFactory.getLogger(this::class.java.name)

    val a = async {
        log.info("I'm computing a piece of the answer")
        6
    }

    val b = async {
        log.info("I'm computing another piece of the answer")
        7
    }
    log.info("The answer if ${a.await() * b.await()}")
}