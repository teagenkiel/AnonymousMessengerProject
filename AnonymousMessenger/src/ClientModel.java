import java.io.IOException;

/**
 * Holds the client for each instance of a StudyGroup app so it can be used by each scene's controller efficiently.
 *
 *
 */
public class ClientModel {


    private Client client;
    private String clientHostAddress;
    private int clientPortNumber;

    public ClientModel(String serverHostAddress, int serverPortNumber) throws IOException {

        this.client = new Client(serverHostAddress, serverPortNumber);
        this.clientHostAddress = serverHostAddress;
        this.clientPortNumber = serverPortNumber;

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
