# 🏨 Hotel Management System

## 👤 Author

**Magamedkhan Osmanov**

## 📦 Version

**v9.0 (Last updated: 09.01.2026)**

---

## 📖 Description

A JavaFX-based hotel management system for managing rooms, employees, customers, and reservations.
The application includes authentication, room availability visualization, and management panels for handling data.

---

## ⚙️ Requirements

* Java JDK 17–21
* JavaFX SDK (if not included in your JDK)

---

## 🛠️ Technologies

* Java
* JavaFX
* Object-Oriented Programming (OOP)

---

## 🚀 How to Run

### ▶️ Option 1: Using IntelliJ IDEA (Recommended)

1. Open the project in IntelliJ IDEA
2. Add JavaFX SDK to the project
3. Run the main class: `HotelManagementSystem`

---

### ▶️ Option 2: Command Line (JavaFX included in JDK)

```bash
javac *.java
java HotelManagementSystem
```

---

### ▶️ Option 3: Command Line (External JavaFX)

```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls *.java
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls HotelManagementSystem
```

---

## 🔐 Default Login

* **Username:** admin
* **Password:** admin
  ## Screenshots
![Main Screen](https://github.com/Osmanov2006/Hotel-Project-Java-/blob/main/2026-03-22%20(7).png)

---

## 📁 Project Structure

* `HotelManagementSystem.java` — Main application entry point
* `ILoginable.java` — Interface for login functionality
* `Person.java` — Base class for Employee and Customer
* `Employee.java` — Employee class (implements ILoginable)
* `Customer.java` — Customer class (implements Comparable)
* `Room.java` — Room entity
* `Reservation.java` — Reservation entity
* `DataManager.java` — Singleton class for data storage
* `LoginScreen.java` — Login UI with analog clock
* `MainScreen.java` — Main UI with room grid
* `ManagementScreen.java` — Management panel

---

## ⭐ Features

### 🔑 Login System

* Custom analog clock (JavaFX Canvas)
* Login validation via interface
* Default admin account

---

### 🏠 Main Screen

* 25 rooms (5×5 grid)
* Date selection for availability
* Color indicators:

  * 🟢 Green — free
  * 🟠 Orange — partially occupied
  * 🔴 Red — fully occupied
* Click room → view reservations
* Customers sorted by name

---

### 🛠️ Management Panel

* Add rooms (type & price)
* Add employees (with login credentials)
* Create reservations

---

## 💾 Data Storage

The system uses text files:

* `rooms.txt`
* `employees.txt`
* `reservations.txt`

✔ Files are created automatically on first run

---

## 🧠 OOP Concepts Used

* Interface: `ILoginable`
* Comparable: `Customer`
* Inheritance: `Person → Employee / Customer`
* Encapsulation: getters & setters
* Singleton: `DataManager`

---

## ⚠️ Error Handling

* Input validation
* Date validation
* Empty field checks
* Invalid login detection
* User-friendly messages

---

## 📌 Notes

* Data is saved automatically
* 25 rooms are generated on first run
* Customers are sorted by name
* Reservation overlapping is allowed

---

## 📧 Contact

Email: **[Magosm.06@yandex.com](mailto:Magosm.06@yandex.com)**
