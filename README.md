# Research-Oriented University Information System

Welcome to the **Research-Oriented University Information System**, a comprehensive, console-based Java application designed to manage university operations with a special emphasis on scientific research tracking. The system manages courses, marks, attendance, and student requests while offering full-featured research profile management for students, teachers, and dedicated research associates.

---

## 🏛️ System Architecture & Package Structure

The project is structured into logical packages to separate concerns and modularize core system capabilities:

```
src/
├── Main.java                # Main entry point (Login and Main loop)
├── core/                    # Fundamental system abstractions
│   ├── Database.java        # Serialized data storage & persistence
│   ├── User.java            # Abstract base class for all users
│   ├── Employee.java        # Abstract base class for university employees
│   └── IdGenerator.java     # Utility for unique identifiers
├── users/                   # Concrete system actors & creations
│   ├── Admin.java           # Users manager, request approvals, and log viewer
│   ├── Manager.java         # Course controller, registrator, and news creator
│   ├── Student.java         # Registrations, transcripts, and researcher
│   ├── Teacher.java         # Mark entry, attendance supervisor, and researcher
│   ├── ResearchAssociate.java # Full-time dedicated researcher class
│   └── UserFactory.java     # Factory class for instantiating users
├── academic/                # Academic curriculum & grades structure
│   ├── Course.java          # Representation of study courses
│   ├── Lesson.java          # Individual lectures or practice sessions
│   ├── Mark.java            # Numerical grades and retake eligibility
│   └── Transcript.java      # Consolidated record of all marks for a student
├── research/                # Scientific publications & projects tracker
│   ├── Researcher.java      # Contract interface for scientific actors
│   ├── ResearchPaper.java   # Research paper definitions & citations
│   └── ResearchProject.java # Collaborative scientific projects
├── communication/           # Observer notifications & requesting system
│   ├── Observer.java        # Observer interface for event tracking
│   ├── Subject.java         # Subject interface for publishing events
│   ├── Message.java         # Direct communications
│   └── Request.java         # Formal requests (such as supervisor request)
├── services/                # Business services
│   ├── AuthService.java     # Singleton authenticating user credentials
│   └── ResearchService.java  # Singleton aggregating and analyzing research metrics
├── enums/                   # Enums for role, gender, lesson type, request type, etc.
└── exceptions/              # Specialized system exceptions
```

---

## 👥 System Actors & Capabilities

### 🛡️ Admin
* **User Management**: Add or remove users (Students, Teachers, Managers, Research Associates).
* **Supervision Approvals**: View, approve, or reject supervisor requests from senior students.
* **Logs & Audits**: Access system-wide logs for audit trails.
* **Course Registry**: Create and remove academic courses.

### 💼 Manager
* **Curriculum Control**: Assign teachers to courses.
* **Course Registration**: Open or close courses for registration.
* **Student/Teacher Directory**: View lists of students and teachers.
* **Public Announcements**: Publish university news (broadcasts to all subscribed observers).
* **Reports**: Create general system performance and activity reports.

### 🎓 Student
* **Academic Enrolment**: Register for courses (credit limit validation: max 21 credits).
* **Grades & Progress**: View marks, check the comprehensive transcript, and monitor current GPA.
* **Research Supervision**: Request academic supervisors (available for 4th-year students).
* **Research Hub**: Publish papers, participate in research projects, and check citations.
* **Notifications**: Receive public university updates and news.

### 👩‍🏫 Teacher
* **Course Management**: View assigned courses and active student rolls.
* **Mark Entry**: Put grades (First Attestation, Second Attestation, Final Exam) for enrolled students.
* **Attendance Management**: Create lessons (Lectures or Practice) and mark attendance.
* **Research Hub**: Publish papers, track h-index, and manage co-authors.

### 🔬 Research Associate
* **Dedicated Research**: Full-time researchers who participate in projects and publish papers.

---

## ⚡ Design Patterns Implemented

The system adheres strictly to classical Object-Oriented Design Patterns:

1. **Singleton Pattern**:
   * [Database.java](file:///Users/zagvozdkinaaa/IdeaProjects/oop-project/src/core/Database.java): Manages single-point access to the serialized data storage (`database.ser`) to ensure all user sessions read/write to the same data context.
   * [AuthService.java](file:///Users/zagvozdkinaaa/IdeaProjects/oop-project/src/services/AuthService.java): Coordinates authentication state throughout the application lifecycle.
   * [ResearchService.java](file:///Users/zagvozdkinaaa/IdeaProjects/oop-project/src/services/ResearchService.java): Centralizes global academic research statistics and leaderboard calculations.

2. **Factory Method Pattern**:
   * [UserFactory.java](file:///Users/zagvozdkinaaa/IdeaProjects/oop-project/src/users/UserFactory.java): Streamlines instantiation of concrete system actors (Admin, Manager, Student, Teacher, ResearchAssociate) using generic property maps.

3. **Observer Pattern**:
   * [communication/Observer.java](file:///Users/zagvozdkinaaa/IdeaProjects/oop-project/src/communication/Observer.java) & [Subject.java](file:///Users/zagvozdkinaaa/IdeaProjects/oop-project/src/communication/Subject.java): Used for news delivery. The Manager updates the news database, which acts as a subject, automatically broadcasting notifications to registered students (observers).

4. **Dynamic Interface/Role Pattern**:
   * [research/Researcher.java](file:///Users/zagvozdkinaaa/IdeaProjects/oop-project/src/research/Researcher.java): Rather than creating complex, deep inheritance hierarchies for "researching students" or "researching teachers", the system defines `Researcher` as an interface.
   * A boolean field `isResearcher` in [Student](file:///Users/zagvozdkinaaa/IdeaProjects/oop-project/src/users/Student.java) and [Teacher](file:///Users/zagvozdkinaaa/IdeaProjects/oop-project/src/users/Teacher.java) is used to track whether they are actively conducting research, showing how static Java interfaces can be combined with dynamic runtime states.

---

## ⚙️ Core Business Rules & Flows

### 📘 Course Enrolment & Registration
* Students register for courses through the student menu.
* **Credit Limit**: A student cannot register for more than **21 credits** per semester. If a limit is exceeded, a `RuntimeException` is thrown.
* Registration is only allowed if the Manager has officially opened the course for enrollment.

### 📝 Attendance & Retakes
* Teachers can manage attendance by selecting a course, creating lessons (either Lectures or Practice sessions), and marking each student as Present (`y`) or Absent (`n`).
* **Retake Threshold**: If a student's attendance on a course falls **below 70.0%**, the system triggers an automatic retake check via [Mark.java](file:///Users/zagvozdkinaaa/IdeaProjects/oop-project/src/academic/Mark.java#L46). The student is flagged as requiring a retake, and warnings are printed in real-time.

### 🧪 Supervisor Selection
* **Eligibility**: Only **4th-year students** can request a research supervisor.
* **H-Index Constraint**: The supervisor (a Teacher or ResearchAssociate) must have an active **h-index of at least 3** to be approved. If their h-index is lower, the system throws a `LowHIndexException`.
* **Flow**: The student submits a request. The request is queued in the system database. The Admin reviews the queue to approve or reject the assignment.

### 📈 Research Statistics & Citations
* The [ResearchService.java](file:///Users/zagvozdkinaaa/IdeaProjects/oop-project/src/services/ResearchService.java) keeps track of all active researchers.
* Calculates metrics such as **h-index**, average h-index of the university, total citations, and allows managers to view top researchers sorted by year, school, or citations.

---

## 🚀 Getting Started

### 📋 Prerequisites
* **Java Development Kit (JDK)**: Version 17 or higher.

### 🛠️ Compilation and Execution
To compile the project and run it via terminal, use the following commands from the root directory:

```bash
# Create target build directory
mkdir -p bin

# Compile all source files
find src -name "*.java" | xargs javac -d bin

# Run the application
java -cp bin Main
```

### 🔑 Default Credentials
Upon the first startup, the system automatically creates a default administrator if the database is empty:
* **Email**: `admin@uni.kz`
* **Password**: `admin123`

You can log in with this account to start creating courses, managers, teachers, and students.
