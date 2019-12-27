package coroutine_vs_thread

fun main() {
    val threads = (0..10000).map {
        Thread {
            Thread.sleep(5000L)
            println(".")
            Thread.sleep(10000L)
        }
    }
    threads.forEach { it.start() }
    threads.forEach { it.join() }
    println(" Done.")
}