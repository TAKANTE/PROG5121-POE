import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {

    public boolean checkUserName(String username) {
        return username != null && username.contains ("_") && username.length() <=5;

    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() <8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) return false;
        String phoneRegex = "^\\+\\d{1,3}\\d{4,10}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(cellNumber);
        return matcher.matches();
    }
    public String registeredUser(String username,String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "Username, password, and cell phone number successfully captured.";
    }
    public boolean loginUser(String enteredUsername, String enteredPassword, String storedUsername, String storedPassword) {
        return enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword);
    }
    public String returnLoginStatus(boolean isLoggedIn, String firstName, String lastName) {
        if (isLoggedIn) {
            return "Welcome " + firstName + " ," + lastName +" it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

}
