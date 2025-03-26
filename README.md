Bus Ticket Booking System

Introduction:
This is a Java-based Bus Ticket Booking System that allows users to book, manage, and view bus tickets efficiently. The system is integrated with a MySQL database for data storage and retrieval.

Features:
-> User Registration & Login
-> Bus Schedule Management
-> Seat Selection & Booking
-> Booking History & Cancellation
-> Admin Dashboard for Managing Routes & Bookings

Technologies Used:
-> Java (JDK 8+)
-> MySQL
-> JDBC for Database Connectivity
-> Swing for GUI

Installation & Setup:

1. Prerequisites:
-> Install Java Development Kit (JDK 8 or later)
-> Install MySQL Server
-> Install a Java IDE (Eclipse/IntelliJ IDEA/NetBeans)

2. Database Setup:
• Open MySQL and create a database:
-> CREATE DATABASE bus_ticket_booking;

Import the provided SQL file (database.sql) to set up the required tables.

• Update the DatabaseConnection.java file with your MySQL credentials:
String url = "jdbc:mysql://localhost:3306/bus_ticket_booking";
String user = "your_username";
String password = "your_password";

3. Running the Project:
-> Clone the repository or download the source code.
-> git clone https://github.com/Antony-Ouseppachan/redbus-java.git
-> Open the project in your Java IDE.
-> Compile and run the redbus.java file.

Usage:
-> User: Can register, log in, book a ticket, view booking history, and cancel tickets (Note: Default credentials for login are username:Antony password:antony123).
-> Admin: Can manage bus schedules, update routes, and oversee ticket bookings.


Future Enhancements:
-> Add payment gateway integration
-> Implement email notifications for ticket confirmation
-> Develop a mobile-friendly web interface

License:
-> This project is open-source under the MIT License.

Contact:
-> For issues and contributions, reach out via GitHub or email at antony230710@gmail.com.
