import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    static ArrayList<Message> GlobalMessageHistory = new ArrayList<>();
    static int totalMessagesSent =  0;
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
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
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

        System.out.println("Welcome to quick chat.");

        int mainMenu = 0;

        while (mainMenu != 3) {
            System.out.println("Quick Chat Application Menu");
            System.out.println("1) Send Message");
            System.out.println("2) Show Recently Sent Message");
            System.out.println("3) Quit");
            System.out.print("Please Select An Option: ");

            if (scanner.hasNextInt()) {
                mainMenu = scanner.nextInt();
                scanner.nextLine();

            switch (mainMenu){
                case 1:
                    sendMessage(scanner);
                    break;
                case 2:
                    System.out.println("Coming Soon");
                    break;
                case 3:
                    System.out.println("Exiting Application loop. Goodbye!");
                default:
                    System.out.println("Invalid option context bounds.");
            }
            }else{
                System.out.println("Invalid choice input. Please select digit option");
                scanner.nextLine();
                continue;
            }
        }

        scanner.close();
    }
    private static void sendMessage(Scanner scanner){
        System.out.print("How many messages do you want to enter?");

        int totalBatchCount = 0;

        if (scanner.hasNextInt()){
            totalBatchCount =scanner.nextInt();
            scanner.nextLine();

        }else {
            System.out.println("Invalid input count detected.");
            scanner.nextLine();
            return;
        }

        for (int i =0; i < totalBatchCount; i++){
            System.out.println("Processing Messages" + (i +1) + " of " + totalBatchCount);
            Message messageInstance = new Message();

            if (!messageInstance.checkMessageID()) {
                System.out.println("Critical Error occurred: Invalid ID structure encountered.");
                continue;
            }

            while (true) {
                System.out.print("Enter Recipient Cell Number (e.g., +27821234567: ");
                String cellInput = scanner.nextLine().trim();
                if ("Cell phone number successfully captured.".equals(messageInstance.checkRecipientCell(cellInput))) {
                    messageInstance.setRecipientCell(cellInput);
                    break;
                } else {
                    System.out.println("Incorrect format configuration. Must contain international valid prefix code.");
                }
            }

            while (true) {
                System.out.print("Enter message body content text: ");
                String textInput = scanner.nextLine();

                messageInstance.setMessageText(textInput);
                if (textInput.length() <= 250) {
                    System.out.println("Message sent");
                    break;
                } else {
                    System.out.println("Please enter a message of less than 250 characters.");
                }
            }

            messageInstance.createMessageHash(totalMessagesSent, messageInstance.getMessageText());

            System.out.println("1 - Send Message");
            System.out.println("2 - Disregard Message");
            System.out.println("3 - Store Message");
            System.out.print("Action choice selection input: ");

            int handlingChoice = 0;
            if (scanner.hasNextInt()) {
                handlingChoice = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();

            }

            System.out.println(messageInstance.SentMessage(handlingChoice));

            if (handlingChoice == 2) {
                System.out.println("Message discarded by manual input rule triggers. ");
                continue;
            }

            GlobalMessageHistory.add(messageInstance);
            totalMessagesSent++;

            messageInstance.displayMessageDetails();

        }
        System.out.println("Total number of messages accumulated and sent:" + totalMessagesSent);

        returnTotalMessages();
    }

    public static int returnTotalMessages(){
        System.out.println("Cumulative program messages processed history: " + totalMessagesSent);
        return totalMessagesSent;
    }
}


