package org.deban.cpvault

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform