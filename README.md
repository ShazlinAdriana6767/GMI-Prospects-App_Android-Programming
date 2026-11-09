# GMI Prospect

**GMI Prospect** is an Android mobile application developed to help prospective students of the **German-Malaysian Institute (GMI)** explore available programmes, check their admission eligibility, and access important information about the institution.

The application was developed using **Java and XML in Android Studio** as part of an Android Programming mini project.

## Features

### 1. Welcome Page
- Displays the GMI Prospect welcome screen.
- Provides a Get Started button to access the application.

### 2. Home Page
- Main navigation menu.
- Quick access to Courses, Eligibility Checker, Contact GMI, and About GMI.

### 3. Courses
- Displays 12 diploma programmes.
- Includes the German A-Level Preparatory Programme (GAPP).
- Includes the German University Foundation Programme (GUFP).
- Allows users to select programmes and view further details.

### 4. Course Details
- Displays selected programme information.
- Shows programme duration.
- Provides access to the official GMI website for further information.

### 5. Eligibility Checker
- Supports SPM and IGCSE/O-Level examination results.
- Allows students to select grades for relevant subjects.
- Supports adding and removing additional subjects.
- Evaluates preliminary eligibility based on the implemented admission requirements.

### 6. Eligibility Results
- Displays programmes students may qualify for.
- Indicates whether the entered grades meet the implemented requirements.
- Provides links to relevant GMI or MARA websites.
- Allows students to check their eligibility again.

### 7. Contact GMI
- Displays GMI's address, phone number, and email.
- Provides buttons to open Google Maps, call GMI, send an email, and visit the official website.

### 8. About GMI
- Introduces German-Malaysian Institute.
- Explains its educational focus and programme pathways.
- Provides information about the GMI Prospect application.

## Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application logic and functionality |
| XML | User interface layouts |
| Android Studio | Development environment |
| Android SDK | Android application development |
| Gradle | Project build and dependency management |
| Git & GitHub | Version control and project sharing |

## Project Structure

```text
app/
└── src/
    └── main/
        ├── java/com/week18/myapplication/
        │   ├── WelcomeActivity.java
        │   ├── MainActivity.java
        │   ├── CoursesActivity.java
        │   ├── CourseDetailActivity.java
        │   ├── EligibilityActivity.java
        │   ├── ResultActivity.java
        │   ├── ContactActivity.java
        │   └── AboutActivity.java
        ├── res/
        │   ├── drawable/
        │   ├── layout/
        │   └── values/
        └── AndroidManifest.xml
```

## Installation and Setup

1. Download this repository by clicking **Code → Download ZIP** on GitHub.
2. Extract the downloaded ZIP file.
3. Open **Android Studio**.
4. Select **Open** and choose the extracted project folder.
5. Wait for Gradle Sync to finish.
6. Select an Android emulator or connect an Android device.
7. Click **Run** to launch the application.

**Requirements:**
- Android Studio
- Android SDK
- JDK compatible with the project's Gradle configuration
- Android device or emulator running Android 12 (API 31) or later

## How the Eligibility Checker Works

1. The student selects their examination type (SPM or IGCSE/O-Level).
2. The application displays the relevant subjects.
3. The student enters their grades.
4. Additional subjects can be added when needed.
5. The application evaluates the grades using the implemented eligibility rules.
6. Results are displayed with the relevant programme information.

**Note:** Eligibility results are preliminary and intended for reference only. Final admission decisions depend on GMI or MARA's official requirements and verification.

## Project Objectives

- Provide an accessible mobile platform for prospective GMI students.
- Help students explore available academic programmes.
- Simplify preliminary admission eligibility checking.
- Centralize important GMI contact and programme information.
- Demonstrate Android application development using Java and XML.

## Testing

The application has been manually tested for navigation, course information, eligibility checking, dynamic subject inputs, results display, and external links.

## Disclaimer

GMI Prospect is a student-developed educational project and is **not an official GMI application**. All programme details and admission requirements should be verified through the official German-Malaysian Institute website.

## Official Website

[German-Malaysian Institute (GMI)](https://www.gmi.edu.my/)

---

**Project:** GMI Prospect  
**Institution:** German-Malaysian Institute (GMI)  
**Project Type:** Android Programming Mini Project  
**Development Platform:** Android Studio  
**Programming Language:** Java  
**UI Development:** XML
