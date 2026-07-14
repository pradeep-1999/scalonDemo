package com.example.scalongDemo.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.scalongDemo.client.OkxWebSocketClient;

@Component
public class MarketWebSocketHandler extends TextWebSocketHandler {

    private OkxWebSocketClient okxClient;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        okxClient = new OkxWebSocketClient(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) {

        // Browser sends: BTC-USDT
        okxClient.subscribe(message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) {

        okxClient.close();
    }
}