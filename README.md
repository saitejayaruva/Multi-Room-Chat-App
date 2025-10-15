# 💬 Real-Time Chat Application (Spring Boot + WebSocket)

A real-time chat web application built using **Spring Boot**, **WebSocket (STOMP)**, and **HTML/CSS** — enabling users to create or join chat rooms and exchange live messages instantly.

---

## 🚀 Live Demo  
🔗 [Click here to try it on Render](https://multi-room-chat-app.onrender.com)

---

## 🧠 Tech Stack
- **Backend:** Spring Boot, WebSocket (STOMP)
- **Frontend:** HTML5, CSS3 (Single Page Template)
- **Build Tool:** Maven
- **Deployment:** Render Cloud (Dockerized)

---

## ⚙️ Features
- 🔹 Create and join unique chat rooms  
- 🔹 Real-time messaging powered by WebSockets  
- 🔹 Auto-display of online users  
- 🔹 Simple, responsive one-page UI  
- 🔹 Deployed on Render with auto-restart support  

---

## 🗂️ Project Structure
```
app/
 ├── src/
 │   ├── main/
 │   │   ├── java/com/chat/app/
 │   │   │   ├── controller/ChatController.java
 │   │   │   ├── model/Message.java
 │   │   │   └── AppApplication.java
 │   │   └── resources/
 │   │       ├── templates/chat.html
 │   │       └── application.properties
 ├── pom.xml
 └── Dockerfile
```

---

## ⚡ Local Setup
### 1️⃣ Clone the repository
```bash
git clone https://github.com/saitejayaruva/Multi-Room-Chat-App.git
cd <Multi-Room-Chat-App>
```

### 2️⃣ Build the app
```bash
mvn clean package -DskipTests
```

### 3️⃣ Run locally
```bash
java -jar target/app-0.0.1-SNAPSHOT.jar
```
Visit 👉 **http://localhost:8080**

---

## 🐳 Deploy via Docker
```bash
docker build -t mychatapp .
docker run -p 8080:8080 mychatapp
```

---

## 🌍 Auto Deployment (Render)
This project is connected to **Render GitHub Auto Deploy**, so every push to `main` automatically rebuilds and redeploys the live app.

---

## 📎 Author
👤 **Saiteja Yaruva**  
📫 GitHub: [github.com/saitejayaruvA](https://github.com/saitejayaruvA)

---

⭐ *If you like this project, give it a star on GitHub!*
