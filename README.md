# 🔐 Java Password Manager

A secure and user-friendly **Password Manager** application built with **Java**, **JavaFX**, and **SQLite**. This project allows users to store, retrieve, and manage passwords for different accounts securely using encryption.

---

## 🚀 Features

- ✅ Graphical User Interface (GUI) using JavaFX
- 🔐 Master Password-based login
- 🔐 AES encryption for password storage
- 🗃️ SQLite database integration
- 🧠 Password retrieval for accounts
- 📦 Export/Import encrypted password vaults
- ☁️ (Coming Soon) Cloud sync using Firebase
- 👥 Multi-user login system (Coming Soon)

---

## 🛠️ Technologies Used

- Java 17+
- JavaFX 21+ (UI)
- SQLite (Local DB)
- JDBC (Database connectivity)
- AES Encryption
- File I/O for import/export

---

## 📸 GUI Preview

*(You can add screenshots here if you'd like)*

---

## 📂 How to Run

### Prerequisites

- Java 17+
- JavaFX SDK
- `sqlite-jdbc` driver (add to classpath)

### Compile

```bash
javac --module-path "PATH_TO_JAVAFX_LIB" --add-modules javafx.controls,javafx.fxml -cp ".;sqlite-jdbc-3.43.0.0.jar" *.java
