# Parking Lot Management System

A robust, object-oriented backend application designed to manage the end-to-end operations of a parking lot. Built with Core Java, this system demonstrates advanced domain modeling, clear separation of concerns, and the practical application of standard software design patterns.

## 🚀 Overview

This system handles the core workflows of a commercial parking lot, including vehicle entry, dynamic spot allocation, ticket generation, and fee calculation upon exit. It is designed with modularity in mind, allowing for easy extension of pricing models and parking strategies.

## 🏗️ Architecture

The codebase follows a strict layered architecture to ensure separation of concerns:

*   **Controllers (`/controllers`)**: Acts as the entry point, handling incoming requests for ticketing and parking workflows.
*   **Services (`/services`)**: Contains the core business logic, orchestrating models and strategies to execute operations like `TicketService`.
*   **Models (`/models`)**: Rich domain entities representing the physical and logical components of the system (e.g., `ParkingLot`, `ParkingFloor`, `ParkingSpot`, `Gate`, `Vehicle`, `Ticket`).
*   **Enums (`/models/enums`)**: Type safety and state management for entities (e.g., `VehicleType`, `GateStatus`, `PaymentMode`).
*   **Exceptions (`/exceptions`)**: Custom error handling for domain-specific edge cases (e.g., `NoAvailableSpotException`).

## 🧠 Design Patterns Implemented

To keep the system highly cohesive and loosely coupled, the following design patterns are actively utilized:

*   **Strategy Pattern**: 
    *   **Spot Allotment**: Pluggable algorithms for assigning spots (`NearestFirstSpotAllotmentsStrategy`, `RandomSpotAllotmentStrategy`).
    *   **Fee Calculation**: Flexible pricing models allowing the system to easily switch between flat rates and hourly rates (`FlatFeeCalculationStrategy`, `HourlyFeeCalculationStrategy`).
*   **Builder Pattern**: Utilized within the `ParkingLot` model (`ParkingLot$Builder`) to safely and cleanly construct complex configurations without constructor pollution.

## 🛠️ Technology Stack

*   **Language**: Java (Core)
*   **Version Control**: Git

## 🔮 Future Enhancements

*   [ ] Migrate to **Spring Boot** to expose the system via RESTful APIs.
*   [ ] Integrate a relational database (e.g., **PostgreSQL**) using an ORM like Hibernate for data persistence.
*   [ ] Implement comprehensive unit testing using **JUnit** and **Mockito**.