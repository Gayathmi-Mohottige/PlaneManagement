# ✈️ Plane Management System

A Java-based console application for managing airplane seat bookings. This system allows users to buy and cancel tickets, search for ticket information, and view available seating in a simple, text-based interface. It also includes file I/O functionality to store ticket details in `.txt` files for future reference.

---

## 📌 Table of Contents

- [Features](#-features)
- [Project Structure](#-project-structure)
- [Seat Layout](#-seat-layout)
- [Seat Pricing](#-seat-pricing)
- [How It Works](#-how-it-works)
- [How to Run](#-how-to-run)
- [Sample Output](#-sample-output)
- [Sample Ticket File](#-sample-ticket-file)
- [Technologies Used](#-technologies-used)
- [Author](#-author)
- [License](#-license)

---

## 💡 Features

- ✅ Book a seat (with name, surname, email)
- ❌ Cancel a booked seat
- 🔍 Search for a ticket
- 🔎 Find the first available seat
- 📊 Display the current seating plan
- 🧾 Print ticket information and total sales
- 💾 Save ticket details to `.txt` file
- 📧 Validate email format
- 🎯 Dynamic seat pricing based on seat number

---

## 📁 Project Structure

```plaintext
├── PlaneManagement.java  // Main class: menu, seat logic, user input
├── Ticket.java           // Handles ticket attributes, printing, and saving to file
├── Person.java           // Represents customer info: name, surname, email
