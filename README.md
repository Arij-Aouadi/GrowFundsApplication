
# GrowFunds

## Introduction

Economic inequalities are starkly evident worldwide. The richest 10% capture 52% of global income, while the poorest half receive only 8%. The disparity in wealth is even greater: the top 10% hold 76% of global wealth, whereas the bottom half possess just 2%.

Microfinance has emerged as a solution to bridge these gaps. By providing basic financial services—such as loans, savings accounts, and insurance—to those excluded from traditional financial systems, microfinance aims to include the most disadvantaged. It offers essential financial resources to individuals who lack collateral, enabling economic self-sufficiency and contributing to the reduction of inequalities. Through specialized institutions and innovative financial products, microfinance provides a pathway to financial security and prosperity for vulnerable populations.

## Project Overview

GrowFunds is a comprehensive application designed for financial management, including user management, account management, transaction processing, credit management, and partnership management. The system supports different user roles, each with specific functionalities, and emphasizes security, usability, and performance.


## Project Structure

The project is structured into several key modules:

- **User Management**: Admins, Agents, and Clients.
- **Account Management**: Creation, visualization, and notifications.
- **Transaction Management**: Transactions and payments.
- **Credit Management**: Credit approval, advice, and insurance.
- **Partnership Management**: Partner and pack management.

## Requirements

### Functional Requirements

1. **User Management**
   - **Admin**: Manage investors, agents, clients, and view loan procedures.
   - **Agent**: Analyze data, manage claims.

2. **Account Management**
   - **Client**: Create accounts, view account details, check historical activities, file complaints, and receive notifications.
   - **Agent**: Validate client accounts, check profiles, and receive payment notifications.

3. **Transaction Management**
   - **Client**: Initiate transactions.
   - **Agent**: Manage and validate transactions.

4. **Credit Management**
   - **Admin**: Approve credits, offer advice, manage client blacklists, and check remaining credit.
   - **Agent**: Review credit requests and obtain credit insurance.
   - **Client**: Access short-term loans and customized loan packages.

5. **Partnership and Packs Management**
   - **Admin**: Manage partners.
   - **Agent**: Manage partner packs.

### Non-Functional Requirements

1. **Security**
   - **Authentication**: JWT with Spring Security.
   - **Authorization**: Role-based access control with Spring Security annotations.
   - **Encryption**: Jasypt for sensitive data encryption.

2. **Usability**
   - Intuitive UI with a user guide.
   - Color scheme to encourage action and communication.

3. **Performance**
   - Fast and reliable performance.
   - Single-page application for efficiency.

## Technologies

- **Backend**: Spring Boot, Spring Security, Jasypt
- **Frontend**: Angular
- **Database**: MySQL
- **Development Tools**: IntelliJ IDEA, Visual Studio Code, Swagger, XAMPP

## Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/growfunds.git
   cd growfunds
   ```

2. **Backend Setup**

   - Ensure you have JDK 11 or later installed.
   - Navigate to the backend directory and run:

     ```bash
     ./mvnw spring-boot:run
     ```

3. **Frontend Setup**

   - Ensure Node.js and npm are installed.
   - Navigate to the frontend directory and run:

     ```bash
     npm install
     npm start
     ```

4. **Database Setup**

   - Ensure MySQL is installed and running.
   - Import the provided schema into your MySQL database.

## Usage

### User Interfaces

- **Client Interfaces**
  - **Homepage**: Overview and navigation.
  - **Registration**: Create new accounts.
  - **Login**: Secure access.
  - **Dashboard**: Manage accounts and view information.
  - **Packs**: View and select financial packs.
  - **Transactions**: Add and manage transactions.
  - **Retirement Simulator**: Calculate retirement plans.
  - **Complaints**: Submit and manage complaints.
  - **Notifications**: Receive alerts.
  - **Projects**: View and manage project details.

- **Admin Interfaces**
  - **User Management**: View and manage all user data.
  - **Transactions**: Monitor and adjust transactions.
  - **Complaints**: Handle customer complaints.
  - **Notifications**: Manage and send notifications.
  - **Projects**: Oversee project details.
  - **Credits**: Manage credit data and approvals.
  - **Packs**: Manage financial packs and products.
