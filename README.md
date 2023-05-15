# Cabinet System

## Project Overview

This project is a Cabinet System designed for managing a collection of artefacts. Developed as part of a module CSC306: Writing Mobile Apps at Swansea University (2023).
By Gonzalo M. Flores (2026765)
## Key Features

### User Roles

The system recognises three distinct user role:

- **Curator**: Has complete control over the artefacts, with the ability to Create, Read, Update, and Delete (CRUD) artefacts.
- **User**: Can view existing artefacts and add new ones.
- **Guest**: Can only view artefacts.

### Authentication

The system includes a login function that ensures proper access control based on user roles.

### SQLite Database

A SQLite database is implemented to handle users' data and artefacts, ensuring efficient and secure data storage and retrieval.

## System Requirements

This application is developed for Android and requires an API version 28.

## Installation

1. Clone this repository: `git clone https://github.com/GonzaloMF/cabinet_system_android.git`
2. Open the project in your preferred IDE (Recommended: Android Studio).
3. Sync the Gradle files to download necessary dependencies.
4. Run the app on an emulator or an actual device.

## Usage

### Login / Registration

On launching the application, you will be directed to the login page. Enter your credentials and log in according to your user role. You can use the following test credentials:

- Curator: `admin@test.com` Password: `12345`
- User: `user@test.com` Password: `12345`

Also, you can create a curator or a user by adding the fields and pressing which user you like to.

### Curator

As a curator, you have the most control over the system. You can add, update, view, and delete artefacts.

### User

As a user, you can view all existing artefacts and add new ones.

### Guest

As a guest, you can only view artefacts.

