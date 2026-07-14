package com.example.scalongDemo.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class OkxWebSocketClient implements WebSocket.Listener {

    private final WebSocketSession clientSession;

    private WebSocket okxSocket;

    public OkxWebSocketClient(WebSocketSession clientSession) {

        this.clientSession = clientSession;

        HttpClient.newHttpClient()
                .newWebSocketBuilder()
                .buildAsync(
                        URI.create("wss://ws.okx.com:8443/ws/v5/public"),
                        this)
                .thenAccept(ws -> okxSocket = ws);
    }

    public void subscribe(String symbol) {

        String request = """
            {
              "op":"subscribe",
              "args":[
                {
                  "channel":"books",
                  "instId":"%s"
                }
              ]
            }
            """.formatted(symbol);

        okxSocket.sendText(request, true);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket,
                                     CharSequence data,
                                     boolean last) {

        try {
            clientSession.sendMessage(
                    new TextMessage(data.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return WebSocket.Listener.super.onText(webSocket, data, last);
    }

    public void close() {

        if (okxSocket != null) {
            okxSocket.abort();
        }
    }
}
