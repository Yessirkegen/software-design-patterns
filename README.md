Design Patterns Implementation in Java

This project contains Java implementations of seven common design patterns: Adapter, Bridge, Composite, Decorator, Facade, Flyweight, and Proxy. Each pattern is demonstrated through a specific scenario and organized within a single Java file for ease of use.
Table of Contents

    Introduction
    Design Patterns
        1. Adapter Pattern
        2. Bridge Pattern
        3. Composite Pattern
        4. Decorator Pattern
        5. Facade Pattern
        6. Flyweight Pattern
        7. Proxy Pattern
    Setup and Usage
    Contributing
    License

Introduction

Design patterns are reusable solutions to common problems in software design. This project aims to provide a clear understanding of these patterns through practical Java implementations. Each section includes a brief overview and example usage.
Design Patterns
1. Adapter Pattern

The Adapter pattern allows an existing class to work with incompatible interfaces. In this implementation, an audio player that plays MP3 files is adapted to support WAV and AAC files using an adapter class.
2. Bridge Pattern

The Bridge pattern separates an abstraction from its implementation, allowing them to vary independently. In this case, a universal remote control system can control different devices (TV, DVD, Sound System) from various manufacturers.
3. Composite Pattern

The Composite pattern lets you compose objects into tree structures to represent part-whole hierarchies. Here, a restaurant menu can contain menu items or other sub-menus, allowing for a flexible structure.
4. Decorator Pattern

The Decorator pattern allows behavior to be added to individual objects, either statically or dynamically, without affecting the behavior of other objects from the same class. This implementation allows customers to customize pizzas with various toppings.
5. Facade Pattern

The Facade pattern provides a simplified interface to a complex subsystem. In this example, a smart home system allows users to control various devices (lights, thermostat, security system) through a single interface.
6. Flyweight Pattern

The Flyweight pattern minimizes memory usage by sharing objects. In this case, a text editor efficiently renders characters by reusing shared character objects for similar properties.
7. Proxy Pattern

The Proxy pattern provides a surrogate or placeholder for another object to control access to it. This implementation allows video lectures to be loaded only when they are played for the first time, enhancing performance.
Setup and Usage

To run the implementations:

    Clone the repository:

    bash

git clone <repository-url>
cd <repository-directory>

Compile the code:

bash

javac DesignPatterns.java

Run the application:

bash

    java DesignPatterns

Each design pattern is demonstrated in the main method of its respective implementation. You can modify or expand the examples as needed.
Contributing

Contributions are welcome! If you have suggestions for improvements or additional patterns, please open an issue or submit a pull request.
License

This project is licensed under the MIT License. See the LICENSE file for details.