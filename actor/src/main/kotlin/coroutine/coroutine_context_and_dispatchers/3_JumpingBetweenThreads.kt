package coroutine.coroutine_context_and_dispatchers

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

fun main() {
    val log = LoggerFactory.getLogger(Thread.currentThread().name)

    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                log.info("Started in ctx1")
                withContext(ctx2) {
                    log.info("Working in ctx2")
                }
                log.info("Back to ctx1")
            }
        }
    }
}