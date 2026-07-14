Market Data Service
Overview    <AI TOOL USED --  CHATGPT>

This project is a Spring Boot based Market Data Service that acts as a proxy between client applications and the OKX public Market Data APIs.

The backend exposes REST APIs for market overview data and a WebSocket endpoint intended to stream live order book updates. Clients never communicate with OKX directly.

Technology Stack
Java 21
Spring Boot 3.x
Spring Web
Spring WebSocket
Spring WebFlux (WebClient)
Maven
Lombok
Project Structure
src/main/java

config/
    WebClientConfig
    WebSocketConfig

client/
    OkxRestClient
    OkxWebSocketClient

controller/
    MarketController

service/
    MarketService

dto/
    okx/
    response/

websocket/
    MarketWebSocketHandler
Features Implemented
--->>>    Market Overview
Fetches Spot Market data from OKX Public API
Retrieves all Spot trading pairs
Sorts pairs based on 24-hour trading volume
Returns Top 20 trading pairs
-->> Backend Proxy

The application acts as a proxy between clients and OKX.

Client
    │
    ▼
Spring Boot
    │
    ▼
OKX Public API

Clients never directly communicate with OKX.

--> REST Endpoint
GET /market/top20

Returns the Top 20 Spot trading pairs.

API Documentation
Get Top 20 Market Pairs
Request
GET http://localhost:8080/market/top20
Sample Response
[
  {
    "symbol": "BTC-USDT",
    "lastPrice": 118560.52,
    "change24h": 1.82,
    "volume24h": 2458745632.12
  },
  {
    "symbol": "ETH-USDT",
    "lastPrice": 3684.15,
    "change24h": -0.45,
    "volume24h": 1356489754.25
  }
]
External API Used

REST API

GET https://openapi.okx.com/api/v5/market/tickers?instType=SPOT

No authentication or API key is required.

WebSocket (Current Status)

A basic WebSocket integration has been added to demonstrate connectivity with the OKX public WebSocket endpoint.

wss://ws.okx.com:8443/ws/v5/public

The current implementation demonstrates:

Backend WebSocket endpoint
OKX WebSocket connectivity
Subscription request handling
WebSocket Scope Remaining

The WebSocket portion has intentionally been kept minimal for demonstration purposes.

A production-ready implementation would additionally include:

Shared upstream OKX WebSocket connection (instead of one per client)
Subscription management for multiple trading pairs
Broadcasting updates to multiple connected clients
Automatic unsubscribe when no clients remain
Reconnection with exponential backoff
Heartbeat/Ping-Pong handling
Session cleanup on disconnect
Resource leak prevention
Order Book DTO parsing instead of forwarding raw payloads
Thread-safe subscription management

These enhancements were intentionally left out to keep the implementation concise for the assignment.

Assumptions
Uses only OKX public APIs
No database required
No persistence
No authentication provider (OAuth/JWT)
Demo-oriented implementation
Market data is fetched on demand
How to Run

Clone the repository

git clone <repository-url>

Run

mvn spring-boot:run

Application starts on

http://localhost:8080
Testing
REST
GET

http://localhost:8080/market/top20

Can be tested using:

Postman
Browser
Curl
WebSocket
ws://localhost:8080/ws/orderbook

Can be tested using:

Postman WebSocket Client
WebSocket King
Browser WebSocket API

Example subscription message:

BTC-USDT

(or JSON if the handler is implemented to accept a JSON request).

Known Limitations
No frontend included.
No authentication/login module (if omitted from your implementation).
Market data is not cached.
WebSocket implementation is simplified and not production-ready.
No scheduler for periodic refresh.
No shared subscription management for multiple clients.
Error handling and reconnection strategies are basic.

Future Enhancements

React-based client UI
Scheduled market cache refresh
Single upstream WebSocket connection
Live order book aggregation
Multiple client subscription management
Session-based authentication
Docker support
Unit and integration tests
Notes

This implementation focuses on demonstrating the backend architecture, integration with the OKX public APIs, and a clean separation of concerns. The WebSocket portion is intentionally simplified to illustrate the core integration pattern while acknowledging that a full production-grade streaming solution would require more comprehensive connection management and subscription handling.
