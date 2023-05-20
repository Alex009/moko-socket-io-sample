package ru.alex009.moko.socketio

import dev.icerock.moko.socket.Socket
import dev.icerock.moko.socket.SocketEvent
import dev.icerock.moko.socket.SocketOptions

class Greeting {
    val socket = Socket(
        endpoint = "https://socketio-chat-h9jt.herokuapp.com",
        config = SocketOptions(
            queryParams = mapOf("param1" to "1", "param2" to "2"),
            transport = SocketOptions.Transport.WEBSOCKET
        )
    ) {
        on(SocketEvent.Connect) {
            println("connect")
        }

        on(SocketEvent.Connecting) {
            println("connecting")
        }

        on(SocketEvent.Disconnect) {
            println("disconnect")
        }

        on(SocketEvent.Error) {
            println("error $it")
        }

        on(SocketEvent.Reconnect) {
            println("reconnect")
        }

        on(SocketEvent.ReconnectAttempt) {
            println("reconnect attempt $it")
        }

        on(SocketEvent.Ping) {
            println("ping")
        }

        on(SocketEvent.Pong) {
            println("pong")
        }

        listOf(
            "input",
            "login",
            "new message",
            "user joined",
            "user left",
            "typing",
            "stop typing"
        ).forEach { eventName ->
            on(eventName) { data ->
                println("$eventName $data")
            }
        }
    }

    fun connect() {
        socket.connect()
    }

    fun hello() {
        socket.emit("add user", "mokoSocketIo")
    }

    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}