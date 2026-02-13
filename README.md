# UDP ClientServer

## How It Works

```
┌────────────┐          UDP Packet           ┌────────────┐
│            │  ──────────────────────────►   │            │
│  UDPClient │       "hello world"           │  UDPServer  │
│  (port: ?) │                               │  (port:9001)│
│            │   ◄──────────────────────────  │            │
└────────────┘       "HELLO WORLD"           └────────────┘
```

## Components

### UDPServer

1. Creates a `DatagramSocket` on port **9001** and waits for incoming packets.
2. When a packet arrives, it extracts the client's **IP address**, **port**, and **message**.
3. Converts the message to **uppercase** and sends it back to the client.
4. Repeats until it receives `"exit"`, then closes the socket.

### UDPClient

1. Creates a `DatagramSocket` and resolves `"localhost"` as the server address.
2. Prompts the user to type a message.
3. Sends the message as a UDP packet to the server on port **9001**.
4. Waits for the server's response and prints it.
5. Repeats until the user types `"exit"`, then closes the socket.

## Flow

```
User types "hello world"
        │
        ▼
 Client packages message into a DatagramPacket
        │
        ▼
 Client sends packet to Server (localhost:9001)
        │
        ▼
 Server receives packet, extracts message
        │
        ▼
 Server converts to uppercase → "HELLO WORLD"
        │
        ▼
 Server sends response back to Client's IP and port
        │
        ▼
 Client receives and prints: "FROM SERVER: HELLO WORLD"
        │
        ▼
 Loop continues until user types "exit"
```

## Running

Open the two projects in separate windows and run both projects:

