**Flashcard Quiz System**

**I. Project Overview**

The Flashcard Quiz System is a console-based Java application designed to aid users in learning and self-assessment by creating, managing, and taking quizzes with flashcards. The program prioritizes user-specific data privacy, enabling users to maintain their own flashcards and quiz results independently. It is structured with robust error handling and Object-Oriented Programming (OOP) principles for scalability and modularity.

**Key Features**

1. **User Account Management:**
	- Register new accounts securely.
	- Log in functionality with validation against stored credentials.
	- Unique flashcard storage for each user, ensuring data privacy.

2. **Flashcard Management:**
	- Add flashcards by selecting a type:
		- Identification Flashcards: Open-ended questions with textual answers.
		- True/False Flashcards: Binary questions with true/false answers.
	- Remove, edit, and display stored flashcards with seamless file updates.

3. **Quiz Functionality:**
	- Take quizzes on specific flashcard types or a randomized set.
	- Features to skip, reveal, and revisit skipped questions during the quiz.
	- Tracks and displays scores for each type of quiz after completion.

4. **File Storage:**
	- User accounts and flashcards are stored in text files for simplicity and portability.

**II. Application of OOP Principles**

The project employs Object-Oriented Programming (OOP) principles to enhance design clarity, scalability, and maintainability:

1. **Encapsulation:** Protects data integrity by limiting how external classes interact with internal class states.
	- All class fields are declared as private to restrict direct access.
	- Controlled access to data is provided via getter and setter methods in classes like Flashcard and UserAccount.

2. **Polymorphism:** Allows consistent handling of different flashcard types using a common interface while enabling unique functionalities.
	- The Flashcard class is an abstract parent class, with IdentificationFlashcard and TrueFalseFlashcard as its concrete implementations.
	- Methods like displayFlashcard() and validateAnswer() are overridden in child classes to provide type-specific behavior.

3. **Abstraction:** Simplifies interaction with flashcards by focusing on high-level operations
	- The abstract Flashcard class defines the core structure and common behaviors (e.g., getQuestion()) for all flashcards.
	- Internal implementation details, such as question validation, are hidden and customized in child classes.

4. **Inheritance:** Encourages code reuse and simplifies the addition of new flashcard types in the future.
	- IdentificationFlashcard and TrueFalseFlashcard inherit from the abstract Flashcard class.
	- Shared functionality is implemented in the parent class, reducing redundancy.

**III. Chosen Sustainable Development Goal (SDG)**

**SDG 4: Quality Education**
	- This Java console program directly supports SDG 4 by providing a digital platform to improve learning outcomes and promote self-directed education.

**Integration of SDG into the Project:**

1. **Accessibility:**
	 - Flashcard Quiz System can be used by students, teachers, or lifelong learners to create tailored learning experiences. 
  	 - Operable on any device with Java, making it accessible to a wide audience without the need for advanced tools or infrastructure.
2. **Personalized Learning:**
	- Users can design flashcards based on their unique needs, enabling focused learning and effective knowledge retention.
3. **Engagement:**
	- Interactive quiz features, such as revisiting skipped questions and scoring, encourage active learning.
4. **Future Scalability (Possible improvements:**
	- The modular design allows future integration of features like multimedia flashcards, progress tracking, and multi-language support.

**IV. Instructions for Running the Program**

**Prerequisites:**
1. Code Editor or IDE: e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code.
   
2. Java Development Kit (JDK): Ensure you have JDK 8 or later installed

3. File Structure Setup: Organize the files as described in the File Structure section.

**Steps to Run:**

1. **Download and Place Files**
	- Save all the .java files into a single folder named flashcard-system.

**📁 Folder Structure:**
```
flashcard-system/
├── Main.java
├── UserAccount.java
├── FlashcardManager.java
├── QuizManager.java
├── Flashcard.java
├── TrueFalseFlashcard.java
└── IdentificationFlashcard.java
```

2. **Create and Place Supporting Files:**
- accounts.txt:
	- This file stores user credentials (username,password).
	- Place it in the same folder (flashcard-system).
	- If it doesn't exist, the program will create it automatically on account registration.

3. **Compile the Program:**

	- Open a terminal or command prompt.
	- Navigate to the flashcard-system folder.

4. **Run the Program:**

After successful compilation, run the program by executing:
```
java Main
```
**📁 File Structure**
```
flashcard-system/
├── Main.java
├── UserAccount.java
├── FlashcardManager.java
├── QuizManager.java
├── Flashcard.java
├── TrueFalseFlashcard.java
├── IdentificationFlashcard.java
├── accounts.txt (optional, created automatically if not present)
```
