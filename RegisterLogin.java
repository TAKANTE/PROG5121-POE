import java.util.Scanner;

public class RegisterLogin {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String firstName;
        String lastName;
        String username;
        String password;

        System.out.println("=== REGISTRATION ===");

        // First Name - letters only
        while (true) {
            System.out.print("Enter First Name: ");
            firstName = input.nextLine().trim();

            if (firstName.isEmpty()) {
                System.out.println("Error: First Name cannot be empty.");
            } else if (!firstName.matches("[a-zA-Z]+")) {
                System.out.println("Error: First Name must contain letters only.");
            } else {
                break;
            }
        }

        // Last Name - letters only
        while (true) {
            System.out.print("Enter Last Name: ");
            lastName = input.nextLine().trim();

            if (lastName.isEmpty()) {
                System.out.println("Error: Last Name cannot be empty.");
            } else if (!lastName.matches("[a-zA-Z]+")) {
                System.out.println("Error: Last Name must contain letters only.");
            } else {
                break;
            }
        }

        // Username - maximum 10 characters, no whitespace
        while (true) {
            System.out.print("Enter Username (max 10 characters, no spaces): ");
            username = input.nextLine();

            if (username.isEmpty()) {
                System.out.println("Error: Username cannot be empty.");
            } else if (username.length() > 10) {
                System.out.println("Error: Username must be a maximum of 10 characters.");
            } else if (username.matches(".*\\s.*")) {
                System.out.println("Error: Username must not contain spaces.");
            } else {
                break;
            }
        }

        // Password - minimum 6 characters, uppercase and lowercase
        while (true) {
            System.out.print("Enter Password (min 6 characters, upper & lower case): ");
            password = input.nextLine();

            if (password.length() < 6) {
                System.out.println("Error: Password must be at least 6 characters.");
            } else if (!password.matches(".*[A-Z].*")) {
                System.out.println("Error: Password must contain at least one uppercase letter.");
            } else if (!password.matches(".*[a-z].*")) {
                System.out.println("Error: Password must contain at least one lowercase letter.");
            } else {
                break;
            }
        }

        System.out.println("\nRegistration Successful!");
        System.out.println();

        // LOGIN
        System.out.println("=== LOGIN ===");

        while (true) {
            System.out.print("Enter Username: ");
            String loginUser = input.nextLine();

            System.out.print("Enter Password: ");
            String loginPass = input.nextLine();

            if (loginUser.equals(username) && loginPass.equals(password)) {
                System.out.println("\nWelcome " + firstName + " " + lastName + "!");
                break;
            } else {
                System.out.println("Invalid login, try again.\n");
            }
        }

        input.close();
    }
}
