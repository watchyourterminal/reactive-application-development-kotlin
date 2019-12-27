package chapter2

object Tourist {
    data class Guidance(val code: String, val description: String)
    data class Start(val codes: Sequence<String>)
}

