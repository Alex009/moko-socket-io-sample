package ru.alex009.moko.socketio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform