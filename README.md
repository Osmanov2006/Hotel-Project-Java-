# Hotel-Project-Java-
Hotel Management System Project 


Author
Magamedkhan Osmanov




Version
09.01.2026 v9.0 Last Updated




Description
A JavaFxxbased hotel management system that manages rooms, employees, customers, and reservations. Features include login authentication, room availability visualization, and comprehensive management panels.




Requirements
-Java JDK 17-21
-JavaFX SDK (if not included in your JDK)




Project Structure

HotelManagementSystem.java  - Main application entry point
ILoginable.java             - Custom interface for login functionality
Person.java                 - Base class for Employee and Customer
Employee.java               - Employee class implementing ILoginable
Customer.java               - Customer class implementing Comparable
Room.java                   - Room entity class
Reservation.java            - Reservation entity class
DataManager.java            - Singleton class for data persistence
LoginScreen.java            - Login screen with analog clock
MainScreen.java             - Main screen with room grid and date selection
ManagementScreen.java       - Management panel for data entry



How to Compile

Option 1: Using Command Line (if JavaFX is in JDK)
bash javac *.java

Option 2: Using Command Line (with external JavaFX)
bash javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls *.java

Option 3: Using an IDE (Recommended IntelliJ)
1. Import thsi project into your IDE (Eclipse, IntelliJ IDEA, NetBeans)
2. Add JavaFX library to your project build path
3. Build the project





How to Run
Option 1: Using Command Line (if JavaFX is in JDK)
java HotelManagementSystem

Option 2: Using Command Line (with external JavaFX)
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls HotelManagementSystem

Option 3: Using an IDE
 1. Open in IntelliJ IDEA
 2. Wait for Maven to load dependencies (pom.xml is included in project)
 3.OPpen Maven at the right side of the IDE -> open Execute Maven Goal -> run this command: mvn clean javafx:run


Default Login Credentials
Username: admin
Password:admin





Features
Login Screen
- Custom analog clock created with JavaFX Canvas
- Login validation using ILoginable interface
- Default admin account

Main Screen
- 25 rooms displayed in a 5x5 grid
- Date range selection for checking availability
- Room color coding:
  - Green: Completely free
  - Orange: Partially occupied
  - Red: Fully occupied
- Click rooms to view customer reservations
- Customer list sorted by name (Comparable implementation)

Management Panel
- Room Management: Add new rooms with type and pricing
- Employee Management: Add new employees with login credentials
- Reservation Management: Create reservations for customers





Data Persistence
The system uses text file storage with three files:
- rooms.txt - Stores room information
- employees.txt - Stores employee information
-reservations.txt - Stores reservation and customer data
Files are automatically created on first run with default data.





Object-Oriented Features
- Custom Interface: ILoginable (implemented by Employee)
- Built-in Interface: Comparable (implemented by Customer)
- Inheritance: Person -> Employee, Person -> Customer
- Encapsulation: Private fields with getters/setters
- Singleton Pattern: DataManager class




Error Handling
- Input validation for all forms
- Date range validation
- Empty field checks
- Invalid login detection
- User-friendly error messages



Notes
- The system automatically saves data when changes are made
- All 25 rooms are initialized on first run
- Customer sorting is by name, then by check-in date if names match
- Reservations can overlap (overbooking is allowed in this version)



If you have any questions contact through email - Magosm.06@yandex.com
