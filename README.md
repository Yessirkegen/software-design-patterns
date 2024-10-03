# software-design-pattern

# Cinema Management System

This project is a simple implementation of a **Cinema Management System** in Java using the **five major creational design patterns**: **Singleton**, **Factory Method**, **Abstract Factory**, **Builder**, and **Prototype**. The project serves as an example of how to use each pattern in a practical context.

## Project Overview

The Cinema Management System manages various operations such as:
- Movies and their types (e.g., Regular, IMAX)
- User Interface (UI) elements based on themes
- Complex ticket booking
- Movie schedules

Each design pattern is used to address a specific aspect of the system:

1. **Singleton Pattern**: Manages global system configuration.
2. **Factory Method Pattern**: Handles the creation of different types of movies.
3. **Abstract Factory Pattern**: Creates user interface components based on different themes.
4. **Builder Pattern**: Constructs complex ticket bookings step-by-step.
5. **Prototype Pattern**: Manages and clones movie schedules.

## Design Patterns Used

### 1. Singleton Pattern: System Configuration
- The `CinemaConfig` class ensures that only one instance of global configuration settings (e.g., cinema name, number of screens) exists throughout the application.
  
### 2. Factory Method Pattern: Movie Creation
- The `MovieFactory` interface and its concrete implementations (`RegularMovieFactory`, `IMAXMovieFactory`) handle the creation of different types of movies, such as regular movies and IMAX movies.

### 3. Abstract Factory Pattern: User Interface Generation
- The `UIFactory` interface is used to generate UI components for different themes (`DarkThemeFactory`, `LightThemeFactory`), ensuring consistent design for buttons, text fields, etc.

### 4. Builder Pattern: Ticket Booking
- The `TicketBookingBuilder` class builds a `TicketBooking` object by configuring options like movie title, seat number, and snack combos.

### 5. Prototype Pattern: Movie Schedule
- The `MovieSchedule` interface and its implementation (`StandardSchedule`) provide a cloning mechanism to copy and modify movie schedules without affecting the original.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or above
- A Java IDE (e.g., IntelliJ, Eclipse) or a text editor

### Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/USERNAME/software-design-patterns.git
   ```

2. **Navigate to the Project Directory**:

   ```bash
   cd software-design-patterns
   ```

3. **Compile and Run**:

   - Open the project in your preferred Java IDE.
   - Compile and run the `CinemaManagementApp` class.

### Running the Application

- The main file is `CinemaManagementApp.java`, which demonstrates the use of each design pattern.
- You can run the application using the `main` method provided in `CinemaManagementApp.java`.

### Example Output

The sample output for the project would look like this:

```
Cinema Name: Starlight Cinemas
Number of Screens: 5
Movie: Inception, Type: Regular Movie
Rendering a button in Dark Theme
Booking: Movie: Inception, Seat: A1, Snack Combo: Popcorn and Soda
Schedule: Movie - Inception, Time - 18:00
Cinema Management System initialized.
```

## Project Structure

The project consists of the following core classes:

- **CinemaManagementApp**: Main class to run the application.
- **Singleton Pattern**: `CinemaConfig` class.
- **Factory Method Pattern**: `Movie`, `RegularMovie`, `IMAXMovie`, and `MovieFactory` classes.
- **Abstract Factory Pattern**: `UIFactory`, `Button`, `DarkThemeFactory`, `LightThemeFactory` classes.
- **Builder Pattern**: `TicketBooking` and `TicketBookingBuilder` classes.
- **Prototype Pattern**: `MovieSchedule` and `StandardSchedule` classes.

## Future Enhancements
Some ideas for future improvements:
1. Implement additional movie types (e.g., `4DMovie`).
2. Expand the UI generation with more themes and UI components.
3. Add more complex configurations for `TicketBooking` like loyalty discounts or membership points.
4. Implement a `Facade` pattern to simplify interactions between subsystems.

## Contributing
If you'd like to contribute, feel free to fork the repository and submit a pull request. Please ensure that your code follows the project structure and is well-documented.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

---

This `README` provides an overview, explains the design patterns used, and includes instructions to run the project. You can further customize it based on your project's specific requirements.
