import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginApp = new Login();

        String registeredUsername = "";
        String registeredPassword = "";
        String firstName = "";
        String cellNumber = "";
        String lastName = "";

        System.out.println("=== USER REGISTRATION ===");
        System.out.println("Enter First Name: ");
        firstName = scanner.nextLine();

        System.out.println("Enter your Surname:");
        lastName = scanner.nextLine();

        while (true) {
            System.out.print("Enter Username: ");
            registeredUsername = scanner.nextLine();
            if (loginApp.checkUserName(registeredUsername)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

            while (true) {
                System.out.print("Enter Password: ");
                registeredPassword = scanner.nextLine();
                if (loginApp.checkPasswordComplexity(registeredPassword)) {
                    System.out.println("Password successfully captured.");
                    break;
                } else {
                    System.out.println("Password is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
                }
            }
            while (true) {
                System.out.print("Enter Cellphone Number (e.g., +27634346348): ");
                cellNumber = scanner.nextLine();
                if (loginApp.checkCellPhoneNumber(cellNumber)) {
                    System.out.println("Cellphone number successfully added.");
                    break;
                } else {
                    System.out.println("Cellphone number incorrectly formatted or does not contain international code.");
                }
            }
            System.out.println("\n--- Registration Complete! ---\n");


            System.out.println("=== USER LOGIN ===");
            System.out.println("Enter Username: ");
            String loginUsername = scanner.nextLine();

            System.out.println("Enter Password: ");
            String loginPassword = scanner.nextLine();

            boolean isAuthenticated = loginApp.loginUser(loginUsername, loginPassword, registeredUsername, registeredPassword);
            String loginStatus = loginApp.returnLoginStatus(isAuthenticated, firstName, lastName);
            System.out.println(loginStatus);

            scanner.close();
        }
    }



