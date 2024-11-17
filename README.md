Flashcard Quiz System

I. Project Overview

The Flashcard Quiz System is a console-based Java application designed to aid users in learning and self-assessment by creating, managing, and taking quizzes with flashcards. The program prioritizes user-specific data privacy, enabling users to maintain their own flashcards and quiz results independently. It is structured with robust error handling and Object-Oriented Programming (OOP) principles for scalability and modularity.

Key Features

1. User Account Management:
-Register new accounts securely.
-Log in functionality with validation against stored credentials.
-Unique flashcard storage for each user, ensuring data privacy.

2. Flashcard Management:
-Add flashcards by selecting a type:
	Identification Flashcards: Open-ended questions with textual answers.
	True/False Flashcards: Binary questions with true/false answers.
-Remove, edit, and display stored flashcards with seamless file updates.

3. Quiz Functionality:
-Take quizzes on specific flashcard types or a randomized set.
-Features to skip, reveal, and revisit skipped questions during the quiz.
-Tracks and displays scores for each type of quiz after completion.

4. File Storage:
-User accounts and flashcards are stored in text files for simplicity and portability.

II. Application of OOP Principles

The project employs Object-Oriented Programming (OOP) principles to enhance design clarity, scalability, and maintainability:

1. Encapsulation:
-All class fields are declared as private to restrict direct access.
-Controlled access to data is provided via getter and setter methods in classes like Flashcard and UserAccount.

Protects data integrity by limiting how external classes interact with internal class states.

2. Polymorphism:
-The Flashcard class is an abstract parent class, with IdentificationFlashcard and TrueFalseFlashcard as its concrete implementations.
-Methods like displayFlashcard() and validateAnswer() are overridden in child classes to provide type-specific behavior.

Allows consistent handling of different flashcard types using a common interface while enabling unique functionalities.

3. Abstraction:
-The abstract Flashcard class defines the core structure and common behaviors (e.g., getQuestion()) for all flashcards.
-Internal implementation details, such as question validation, are hidden and customized in child classes.

Simplifies interaction with flashcards by focusing on high-level operations.

4. Inheritance:
-IdentificationFlashcard and TrueFalseFlashcard inherit from the abstract Flashcard class.
-Shared functionality is implemented in the parent class, reducing redundancy.

Encourages code reuse and simplifies the addition of new flashcard types in the future.

III. Sustainable Development Goal (SDG) Integration

SDG 4: Quality Education 

This Java console program directly supports SDG 4 by providing a digital platform to improve learning outcomes and promote self-directed education.

Integration of SDG into the Project:

1. Accessibility:
	 Flashcard Quiz System can be used by students, teachers, or lifelong learners to create tailored learning experiences. Operable on any device with Java, making it accessible to a wide audience without the need for advanced tools or infrastructure.

2. Personalized Learning:
	Users can design flashcards based on their unique needs, enabling focused learning and effective knowledge retention.

3. Engagement:
	Interactive quiz features, such as revisiting skipped questions and scoring, encourage active learning.

4. Future Scalability (Possible improvements:
	The modular design allows future integration of features like multimedia flashcards, progress tracking, and multi-language support.
