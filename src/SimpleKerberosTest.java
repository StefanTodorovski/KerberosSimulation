import Actors.KDCServer;
import Actors.User;
import Tools.KDCResponse;
import Tools.KDCRequest;
import Tools.YABMessage;
import java.util.ArrayList;
import java.util.HexFormat;

public class SimpleKerberosTest {
    public static void main(String[] args) {
        // Define the encryption keys for Alice and Bobby
        String keyA = "Alice123";
        String keyB = "Bobby321";

        // Create user objects for Alice and Bobby
        User Alice = new User("Alice", "AliceID", keyA);
        User Bobby = new User("Bobby", "BobbyID", keyB);

        // Set the other user's ID for Alice
        Alice.setOtherId(Bobby.getId());

        // Create a KDC (Key Distribution Center) server with the encryption keys
        KDCServer kdcServer = new KDCServer(keyA, keyB);

        // Create a KDC request from Alice to Bobby
        KDCRequest request = new KDCRequest(Alice.getId(), Bobby.getId(), Alice.getNonce());

        // Generate the KDC responses containing encrypted session keys and other information
        ArrayList<KDCResponse> responses = kdcServer.ResponseGenerator(request);
        KDCResponse yA = responses.get(0); // Response for Alice
        KDCResponse yB = responses.get(1); // Response for Bobby

        try {
            // Verify and process the KDC responses for Alice and Bobby
            YABMessage yAB = Alice.aliceMessageVerify(yA);
            Bobby.bobbyMessageVerify(yAB, yB);

            // Simulate message exchange between Alice and Bobby
            System.out.println("START OF SIMULATION");

            // Alice sends a message to Bobby
            String messageAlice = "Hello Bobby, it's Alice";
            byte[] messageAliceBytes = Alice.encMessage(messageAlice);
            System.out.println(HexFormat.of().formatHex(messageAliceBytes));

            // Bobby decrypts and reads Alice's message
            String s = Bobby.decMessage(messageAliceBytes);
            System.out.println(s);

            // Bobby sends a message to Alice
            String messageBobby = "Hi Alice, how are you";
            byte[] messageBobbyBytes = Bobby.encMessage(messageBobby);
            System.out.println(HexFormat.of().formatHex(messageBobbyBytes));

            // Alice decrypts and reads Bobby's message
            System.out.println(Alice.decMessage(messageBobbyBytes));
            System.out.println("END OF SIMULATION");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
