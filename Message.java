import java.util.concurrent.ThreadLocalRandom;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;

    private static int totalMessagesSent =0;

    public Message() {
        long randomNum = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);
        this.messageID = String.valueOf(randomNum);
    }

    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 12;
    }

    public String checkRecipientCell(String cellInput) {
        if (cellInput != null && cellInput.length() <= 12 && cellInput.startsWith("+")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String checkMessageLength(String messageText) {
        if (messageText == null) {
            return "Message ready to send.";
        }

        int textLength = messageText.length();
        if (textLength > 250) {
            int overflow = textLength - 250;
            return "Message exceeds 250 characters by " + overflow + " characters; please reduce the size.";
        }

        return "Message ready to send.";
    }

    public void setRecipientCell(String recipientCell) {
        this.recipientCell = recipientCell;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String createMessageHash(int totalMessagesSent, String text) {
        String idPart = "00";
        if (totalMessagesSent > 0 && this.messageID != null && this.messageID.length() >= 2){
            idPart = this.messageID.substring(0, 2);
        }

        String combinedWords = "";

        if (text != null && !text.trim().isEmpty()) {
            String[] words = text.trim().split("\\s+");
            if (words.length == 1) {
                combinedWords = words[0];
            }else if (words.length > 1){
                combinedWords = words[0] + words[words.length - 1];
            }

        }

        this.messageHash = (idPart + ":" + totalMessagesSent + ":" + combinedWords).toUpperCase();
        return this.messageHash;
    }

    public String SentMessage(int handlingChoice) {
        if (handlingChoice == 1) {
            totalMessagesSent ++;
        }
        switch (handlingChoice) {

            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid handling action.";
        }
    }

    public int returnTotalMessagesSent() {
        return totalMessagesSent;
    }

    public String printMessages() {
        return "Message ID generated: " + totalMessagesSent;

    }

    public void displayMessageDetails() {
        System.out.println("\n--- Message Details ---");
        System.out.println("Message ID: " + this.messageID);
        System.out.println("Message Hash: " + this.messageHash);
        System.out.println("Recipient: " + this.recipientCell);
        System.out.println("Message: " + this.messageText);
        System.out.println("--------------------\n");
    }

    public String storeMessage() {
        String jsonString = "{\n"+
                "  \"messageID\": \"" + this.messageID + "\",\n" +
                "  \"recipientCell\": \"" + this.recipientCell + "\",\n" +
                "  \"messageText\": \"" + this.messageText + "\",\n" +
                "  \"messageHash\": \"" + this.messageHash + "\",\n" +
                "}";
        try (FileWriter file = new FileWriter("message_" + totalMessagesSent + ".json")) {
            file.write(jsonString);
            System.out.println("System Confirmation: Message successfully stored as JSON File.");
        } catch (IOException e) {
            System.out.println("Error saving message JSON file context bounds: " + e.getMessage());
        }
        return jsonString;


    }

}