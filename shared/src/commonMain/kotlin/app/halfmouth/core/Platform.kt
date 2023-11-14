package app.halfmouth.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform