import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class Messagetest {

    private Message message;

    @BeforeEach
    public void setUp() {
        message = new Message();
    }


    @Test
    public void testCheckRecipientCell_Success() {
        String validNumber = "+27718693002";
        String expected = "Cell phone number successfully captured.";
        assertEquals(expected, message.checkRecipientCell(validNumber));
    }

    @Test
    public void testCheckRecipientCell_Failure_InvalidFormat() {
        String invalidNumber = "08579575889";
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals(expected, message.checkRecipientCell(invalidNumber));
    }

    @Test
    public void testCheckMessageLength_Success() {
        String shortMessage = "Hi Keegan, did you receive the payment?";
        String expected = "Message ready to send.";
        assertEquals(expected, message.checkMessageLength(shortMessage));
    }

    @Test
    public void testCheckMessageLength_Failure_Overflow() {
        // Create a string that is 255 characters long (5 character overflow)
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 255; i++) {
            longMessage.append("A");
        }
        String expected = "Message exceeds 250 characters by 5 [enter number here]; please reduce the size.";
        assertEquals(expected, message.checkMessageLength(longMessage.toString()));
    }


    @Test
    public void testSentMessage_Case1_Send() {
        String expected = "Message successfully sent.";
        assertEquals(expected, message.SentMessage(1));
    }

    @Test
    public void testSentMessage_Case2_DeleteConfirmation() {
        String expected = "Press 0 to delete the message.";
        assertEquals(expected, message.SentMessage(2));
    }

    @Test
    public void testSentMessage_Case3_Store() {
        String expected = "Message successfully stored.";
        assertEquals(expected, message.SentMessage(3));
    }

    @Test
    public void testPrintMessages_IDCreation() {
        message.SentMessage(1);
        String expected = "Message ID generated: 1";
        assertEquals(expected, message.printMessages());
    }

    @Test
    public void testCreateMessageHash_TestCase1() {
        int totalMessagesSent = 0;
        String textbody = "HITONIGHT";

        String expectedHash = "00:0:HITONIGHT";
        String actualHash = message.createMessageHash(totalMessagesSent, textbody);

        assertEquals(expectedHash, actualHash);
    }
}
