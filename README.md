# WhatsApp Chatbot Backend — Spring Boot

A simple WhatsApp chatbot backend simulation built with **Java** and **Spring Boot**. Exposes a REST API endpoint (`/webhook`) that accepts simulated WhatsApp messages and responds with predefined replies.

---

## Features

- REST API endpoint (`POST /webhook`) to receive simulated WhatsApp messages
- Predefined reply logic (`Hi → Hello`, `Bye → Goodbye`, etc.)
- Request/response logging using SLF4J
- Clean layered architecture (Controller → Service → Model)
- GET `/webhook` endpoint for webhook verification simulation

---

## Tech Stack

| Layer       | Technology              |
|-------------|-------------------------|
| Language    | Java 17                 |
| Framework   | Spring Boot 3.2.0       |
| Build Tool  | Maven                   |
| Logging     | SLF4J (built-in)        |
| Deployment  | Localhost / Render      |

---

## Project Structure

```
whatsapp-chatbot/
├── src/main/java/com/chatbot/
│   ├── controller/
│   │   └── WebhookController.java      # REST endpoint
│   ├── service/
│   │   └── ChatbotService.java         # Reply logic
│   ├── model/
│   │   └── WhatsAppMessage.java        # Request/Response model
│   └── WhatsAppChatbotApplication.java # Main class
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+
- IntelliJ IDEA (or any IDE)

### Run Locally

```bash
# Clone the repository
git clone https://github.com/your-username/whatsapp-chatbot.git
cd whatsapp-chatbot

# Build the project
./mvnw clean package -DskipTests

# Run the application
./mvnw spring-boot:run
```

The server starts at `http://localhost:8080`.

---

## API Reference

### GET `/webhook`
Webhook verification endpoint.

**Response:**
```
Webhook is active
```

---

### POST `/webhook`
Receives a simulated WhatsApp message and returns a reply.

**Request Body:**
```json
{
  "from": "+91xxxxxxxxxx",
  "to": "+1xxxxxxxxxx",
  "message": "Hi"
}
```

**Response:**
```json
{
  "to": "+91xxxxxxxxxx",
  "from": "+1xxxxxxxxxx",
  "message": "Hello! How can I help you?",
  "status": "sent"
}
```

---

## Predefined Replies

| Input          | Response                                         |
|----------------|--------------------------------------------------|
| `Hi` / `Hello` | `Hello! How can I help you?`                    |
| `Bye` / `Goodbye` | `Goodbye! Have a great day!`               |
| `Help`         | `Available commands: Hi, Bye, Help`              |
| *(anything else)* | `Sorry, I didn't understand that. Type 'Help' to see commands.` |

---

## Testing

### Using curl

```bash
# Test "Hi"
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from": "+91xxxxxxxxxx", "to": "+1xxxxxxxxxx", "message": "Hi"}'

# Test "Bye"
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from": "+91xxxxxxxxxx", "to": "+1xxxxxxxxxx", "message": "Bye"}'

# Test "Help"
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from": "+91xxxxxxxxxx", "to": "+1xxxxxxxxxx", "message": "Help"}'
```

### Using Postman

1. Set method to `POST`
2. URL: `http://localhost:8080/webhook`
3. Headers: `Content-Type: application/json`
4. Body (raw JSON): paste any request body from above

---

## Deployment on Render

1. Push project to GitHub
2. Go to [render.com](https://render.com) → **New** → **Web Service**
3. Connect your GitHub repository
4. Configure:

| Field           | Value                                              |
|-----------------|----------------------------------------------------|
| Build Command   | `./mvnw clean package -DskipTests`                |
| Start Command   | `java -jar target/whatsapp-chatbot-0.0.1-SNAPSHOT.jar` |
| Environment     | Java                                               |

5. Click **Deploy** — your public URL will be: `https://your-app.onrender.com/webhook`

---

## Common Issues

| Error | Fix |
|-------|-----|
| `JDK isn't specified for module` | `File → Project Structure → Modules` → set SDK to JDK 17 |
| `ExceptionInInitializerError` (Lombok) | Use explicit Lombok version `1.18.30` or remove Lombok |
| Port 8080 already in use | Change `server.port` in `application.properties` |

---

## Author

**Avinandan_Bhandari**  
MCA Student — Asansol Engineering College  
Java Backend Developer  

---

## License

This project is open source and available under the [MIT License](LICENSE).
