package com.composemates.composecode.websocket
import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import io.socket.engineio.client.transports.WebSocket



class SocketManager {
    private lateinit var socket: Socket

    init {
        try {
            val opts = IO.Options()
            opts.transports = arrayOf(WebSocket.NAME) // Use WebSocket explicitly

            socket = IO.socket("http://192.168.1.2:5000", opts)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun connect() {
        socket.connect()

        socket.on(Socket.EVENT_CONNECT) {
            Log.d("SocketManager", "Socket connected")
            socket.emit("check_station_csp_status", "G01 F01 L02 S01")
        }

        socket.on("update_csp_notification_status", onUpdateNotificationStatus)
    }

    private val onUpdateNotificationStatus = Emitter.Listener { args ->
        val data = args[0] as String
        Log.d("SocketManager", "Received update: $data")
        // Handle the incoming message here
        handleMessage(data)
    }

    private fun handleMessage(message: String) {
        Log.d("SocketManager", "Received message: $message")
        // Process the received message here
    }

    fun disconnect() {
        socket.disconnect()
    }
}


/*
import okhttp3.*
import okio.ByteString

class SocketManager {
    private var webSocket: WebSocket? = null

    fun connect() {
        val client = OkHttpClient()
        val request = Request.Builder().url("ws://192.168.1.2:5000").build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                webSocket.send("check_station_csp_status==>G01 F01 L02 S01")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                println("Received message: $text")
                // Handle incoming messages here
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
                println("Received bytes: $bytes")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                t.printStackTrace()
            }
        })
    }

    fun disconnect() {
        webSocket?.cancel()
    }
}
*/
