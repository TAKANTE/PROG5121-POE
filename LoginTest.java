import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {


    private final Login loginApp = new Login();


    @Test
    public void testUsernameCorrectlyFormatted() {
        // Test Data: "kyl_1" -> satisfies having an underscore and length <= 5
        boolean isValid = loginApp.checkUserName("kyl_1");
        assertTrue(isValid);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        // Test Data: "kyle!!!!!!" -> fails because it is longer than 5 characters
        boolean isValid = loginApp.checkUserName("kyle!!!!!!");
        assertFalse(isValid);
    }


    @Test
    public void testPasswordMeetsComplexityRequirements() {
        // Test Data: "Ch&&sec@ke99l" -> satisfies capital, number, special, and length >= 8
        boolean isValid = loginApp.checkPasswordComplexity("Ch&&sec@ke99l");
        assertTrue(isValid);
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        // Test Data: "password" -> fails because it lacks capital, digit, and special characters
        boolean isValid = loginApp.checkPasswordComplexity("password");
        assertFalse(isValid);
    }


    @Test
    public void testCellPhoneCorrectlyFormatted() {
        // Test Data: "+27838968976" -> matches regex pattern ^\+\d{1,3}\d{4,10}$
        boolean isValid = loginApp.checkCellPhoneNumber("+27838968976");
        assertTrue(isValid);
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        // Test Data: "08966553" -> fails because it lacks the required '+' international prefix
        boolean isValid = loginApp.checkCellPhoneNumber("08966553");
        assertFalse(isValid);
    }


    @Test
    public void testRegisterUser_Successful() {
        String expected = "Username, password, and cell phone number successfully captured.";
        String actual = loginApp.registeredUser("kyl_1", "Ch&&sec@ke99l", "+27838968976");
        assertEquals(expected, actual);
    }

    @Test
    public void testRegisterUser_InvalidUsername() {
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = loginApp.registeredUser("kyle!!!!!!", "Ch&&sec@ke99l", "+27838968976");
        assertEquals(expected, actual);
    }

    @Test
    public void testRegisterUser_InvalidPassword() {
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = loginApp.registeredUser("kyl_1", "password", "+27838968976");
        assertEquals(expected, actual);
    }


    @Test
    public void testLoginUser_SuccessfulMatch() {
        // Tests matching stored credentials against incoming user input values
        boolean loginResult = loginApp.loginUser("kyl_1", "Ch&&sec@ke99l", "kyl_1", "Ch&&sec@ke99l");
        assertTrue(loginResult);
    }

    @Test
    public void testLoginUser_FailedMatch() {
        // Tests failing login logic when credentials mismatched
        boolean loginResult = loginApp.loginUser("kyl_1", "wrongpassword", "kyl_1", "Ch&&sec@ke99l");
        assertFalse(loginResult);
    }

    @Test
    public void testReturnLoginStatus_WelcomeMessage() {
        String expected = "Welcome Kyle ,Smith it is great to see you.";
        String actual = loginApp.returnLoginStatus(true, "Kyle", "Smith");
        assertEquals(expected, actual);
    }

    @Test
    public void testReturnLoginStatus_ErrorMessage() {
        String expected = "Username or password incorrect, please try again.";
        String actual = loginApp.returnLoginStatus(false, "Kyle", "Smith");
        assertEquals(expected, actual);
    }
}
