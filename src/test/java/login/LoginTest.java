package login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LoginTest.java
 *
 * Unit tests built directly from the "Test (assertEquals)" and
 * "Test (assertTrue/False)" tables in the POE brief.
 *
 * NOTE: A couple of the scanned pages you shared were slightly hard to
 * read / had minor wording differences between the "Conditions and
 * Messages" spec table and the "Unit Test" table (e.g. "Cell phone
 * number successfully added." vs "Cell number successfully captured.").
 * I've gone with the wording from the Conditions/Messages spec table
 * for the actual returned messages, since that's the functional
 * requirement — double check this against your own copy of the brief
 * and adjust the expected strings below if your version differs.
 */
public class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        Login.resetRegisteredUser();
        login = new Login();
    }

    // ---- checkUserName() ---------------------------------------------

    @Test
    void testUserName_correctlyFormatted() {
        // Test Data: "kyl_1" -> contains underscore, <= 5 characters
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    void testUserName_incorrectlyFormatted() {
        // Test Data: "kyle!!!!!!" -> no underscore, > 5 characters
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    // ---- checkPasswordComplexity() -------------------------------------

    @Test
    void testPassword_meetsComplexityRequirements() {
        // Test Data: "Ch&&sec@ke99!"
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void testPassword_doesNotMeetComplexityRequirements() {
        // Test Data: "password"
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // ---- checkCellPhoneNumber() -----------------------------------------

    @Test
    void testCellPhone_correctlyFormatted() {
        // Test Data: "+27838968976"
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void testCellPhone_incorrectlyFormatted() {
        // Test Data: "08966553" -> no international code
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // ---- registerUser() ---------------------------------------------------

    @Test
    void testRegisterUser_usernameIncorrectlyFormatted() {
        Login newUser = new Login("Kyle", "Smith", "kyle!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");
        assertEquals(
                "Username is not correctly formatted; please ensure that "
                        + "your username contains an underscore and is no "
                        + "more than five characters in length.",
                newUser.registerUser());
    }

    @Test
    void testRegisterUser_passwordDoesNotMeetComplexity() {
        Login newUser = new Login("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");
        assertEquals(
                "Password is not correctly formatted; please ensure that "
                        + "the password contains at least eight characters, "
                        + "a capital letter, a number, and a special "
                        + "character.",
                newUser.registerUser());
    }

    @Test
    void testRegisterUser_allConditionsMet() {
        Login newUser = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        String result = newUser.registerUser();
        assertTrue(result.contains("successfully"));
    }

    // ---- loginUser() / returnLoginStatus() -------------------------------

    @Test
    void testLoginUser_successful() {
        Login newUser = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        newUser.registerUser();

        boolean result = newUser.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(result);
        assertEquals("Welcome Kyle, Smith, it is great to see you again.",
                newUser.returnLoginStatus(result));
    }

    @Test
    void testLoginUser_failed() {
        Login newUser = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        newUser.registerUser();

        boolean result = newUser.loginUser("kyl_1", "WrongPassword1!");
        assertFalse(result);
        assertEquals("Username or password incorrect, please try again.",
                newUser.returnLoginStatus(result));
    }
}
