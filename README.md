# 🎤 AI Voice Assistant (Java)

A lightweight AI-powered desktop voice assistant built with Java.

This application captures audio from the microphone, converts speech to text, sends the request to an AI model, and displays the response in a chat-style interface.

---

## 🚀 Features

* 🎤 Voice recording from microphone
* 🧠 Speech-to-text processing
* 🤖 AI-generated responses using OpenAI API
* 💬 Chat-style response display
* ⚡ Lightweight Java desktop application
* 🌐 HTTP communication with external APIs

---

## 🧱 Architecture

```text
User Voice
   ↓
Audio Recording (Java)
   ↓
Speech → Text
   ↓
AI Request (OpenAI API)
   ↓
AI Response
   ↓
Display in Chat Window
```

---

## 🛠 Tech Stack

* **Java 17**
* **Maven**
* **Java Swing (UI)**
* **OpenAI API**
* **Java HTTP Client**

---

## 📂 Project Structure

```text
AI-Assistant-Java-Prototype
│
├── src/main/java
│   └── assistant
│       ├── MainApp.java
│       ├── AudioRecorder.java
│       ├── SpeechToText.java
│       ├── OpenAIClient.java
│       └── ChatWindow.java
│
├── README.md
├── LICENSE
└── pom.xml
```

---

## ▶️ How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/soheilnajafi/AI-Assistant-Java-Prototype.git
   ```

2. Open the project in IntelliJ IDEA

3. Add your OpenAI API key where required

4. Build using Maven:

   ```bash
   mvn clean install
   ```

5. Run:

   ```text
   MainApp.java
   ```

---

## 💡 Future Improvements

* Real-time streaming voice input
* Improved UI/UX (modern chat interface)
* Better error handling
* Spring Boot backend integration
* Docker support

---

## 👨‍💻 Author

**Suhill (Sammy) Najafi**
