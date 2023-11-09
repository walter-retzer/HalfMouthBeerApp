package app.halfmouth

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform