package login;

import java.util.regex.Pattern;

/**
 * Login.java
 *
 * Implements account registration and login for the PROG5121 POE.
 *
 * Validation rules (per the task brief):
 *  - Username: must contain an underscore (_) and be no more than
 *    five characters long.
 *  - Password: at least eight characters, containing at least one
 *    capital letter, one number, and one special character.
 *  - Cell phone number: must contain the South African international
 *    country code (+27) followed by the subscriber number
 *    (nine digits, matching the local 0XX XXX XXXX format with the
 *    leading 0 replaced by +27).
 *
 * Regex reference / attribution:
 *  - Password complexity pattern adapted from the OWASP "Password
 *    Strength Regular Expression" cheat sheet:
 *    https://owasp.org/www-community/OWASP_Validation_Regex_Repository
 *  - South African mobile number pattern adapted from the regex
 *    library discussion on validating SA cell numbers:
 *    https://regexlib.com/REDetails.aspx?regexp_id=2019
 *  (Adjust/replace these references with the actual sources you used,
 *  as your brief requires you to attribute this code in your own submission.)
 */
public class Login {

    // ---- Regex patterns -------------------------------------------------

    // At least 1 uppercase letter, at least 1 digit, at least 1 special
    // character, minimum length 8.
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$");

    // South African cell number: +27 followed by exactly 9 digits
    // (e.g. +27838968976). Adjust here if your brief defines the
    // format differently.
    private static final Pattern CELL_PATTERN =
            Pattern.compile("^\\+27\\d{9}$");

    // ---- Fields -----------------------------------------------------------

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;

    // Stores the currently registered account so loginUser() has
    // something to check against.
    private static Login registeredUser;

    public Login() {
    }

    public Login(String firstName, String lastName, String username,
                 String password, String cellPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // ---- Validation methods -------------------------------------------

    /**
     * Checks that the username contains an underscore and is no more
     * than five characters long.
     */
    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;
    }

    /**
     * Checks that the password is at least eight characters long and
     * contains a capital letter, a number, and a special character.
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * Checks that the cell phone number contains the South African
     * international country code followed by the subscriber number.
     */
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber == null) {
            return false;
        }
        return CELL_PATTERN.matcher(cellPhoneNumber).matches();
    }

    // ---- Registration ---------------------------------------------------

    /**
     * Validates the username, password, and cell phone number supplied
     * to this Login instance's constructor and returns the appropriate
     * registration message. On success, the account is stored so that
     * loginUser() can later authenticate against it.
     */
    public String registerUser() {
        if (!checkUserName(this.username)) {
            return "Username is not correctly formatted; please ensure that "
                    + "your username contains an underscore and is no more "
                    + "than five characters in length.";
        }

        if (!checkPasswordComplexity(this.password)) {
            return "Password is not correctly formatted; please ensure that "
                    + "the password contains at least eight characters, a "
                    + "capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(this.cellPhoneNumber)) {
            return "Cell phone number incorrectly formatted or does not "
                    + "contain international code.";
        }

        registeredUser = this;
        return "Username successfully captured. Password successfully "
                + "captured. Cell phone number successfully added.";
    }

    // ---- Login ------------------------------------------------------------

    /**
     * Verifies that the supplied username and password match the
     * currently registered user.
     */
    public boolean loginUser(String username, String password) {
        if (registeredUser == null) {
            return false;
        }
        return registeredUser.username.equals(username)
                && registeredUser.password.equals(password);
    }

    /**
     * Returns the login outcome message. Call loginUser() first to
     * determine the outcome, then call this to get the message to
     * display to the user.
     */
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful && registeredUser != null) {
            return "Welcome " + registeredUser.firstName + ", "
                    + registeredUser.lastName + ", it is great to see you "
                    + "again.";
        }
        return "Username or password incorrect, please try again.";
    }

    // ---- Getters (useful for tests / other classes) ----------------------

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    /** Resets stored registration state - handy between unit tests. */
    static void resetRegisteredUser() {
        registeredUser = null;
    }
}
