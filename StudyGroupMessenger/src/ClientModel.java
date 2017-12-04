/**
 * Holds the client for each instance of a StudyGroup app so it can be used by each scene's controller efficiently.
 *
 *
 */
public class ClientModel {


    private Client client;
    private String clientHostAddress;
    private int clientPortNumber;

    public ClientModel(String clientHostAddress, int clientPortNumber){

        this.client = new Client(clientHostAddress, clientPortNumber);
        this.clientHostAddress = clientHostAddress;
        this.clientPortNumber = clientPortNumber;

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
