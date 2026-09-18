# PROG5121 POE — Registration/Login + Chat App
# PROG5121 POE - Part 1

**Student Information:** Katleho Mphuthi 
**Student Number:** ST10530534
**Module:** PROG5121 Programming 1A

## Project layout
```
POE/
├── pom.xml
├── README.md
└── src
    ├── main/java
    │   ├── login/
    │   │   ├── Login.java     — registration & login logic
    │   │   └── Main.java      — console runner for registration/login
    │   
    └── test/java
        ├── login/LoginTest.java
        
```

## Part 1 — Login 
`Login.java` implements the six methods from the method table
(`checkUserName`, `checkPasswordComplexity`, `checkCellPhoneNumber`,
`registerUser`, `loginUser`, `returnLoginStatus`), with messages and
validation rules taken from the "Conditions and Messages" tables in
your screenshots. `LoginTest.java` uses the exact test data shown
(e.g. `"kyl_1"`, `"Ch&&sec@ke99!"`, `"+27838968976"`).


## My Logic and Flow
- checkUserName: Checks if username contains underscore "_" and length <=5 using if-statement and String.contains().
- checkPasswordComplexity: Checks length >=8, has capital letter, number, and special character using regex.
- registerUser: Returns success/failure messages based on validation.
- loginUser: Compares entered credentials with stored credentials.


## Running the tests
This sandbox has no internet access to Maven Central, so I couldn't
run `mvn test` here. I opened the project in NetBeans with
internet access and run:
```
mvn test
```
Both `LoginTest` and `MessageTest` passed.

## More of what is done for Part 1
- Push to GitHub with a minimum of six commits per part.
- No GUIs / no JOptionPane — `Main.java` is a
  plain console app.


## How to Run
1. Open project in NetBeans.
2. Right-click project > Run.
3. To run tests: Click Test or run `mvn test` (All tests should show "BUILD SUCCESS").

## GitHub Commits
This repository has 6+ commits showing Part 1 development process.
- Commits include: initial code, validation methods, tests, .gitignore fix, README update.

## Video Presentation
YouTube Link: https://youtu.be/A7G5dlRZiNM

## References
- No AI voice used, I used my own voice for presentation.
