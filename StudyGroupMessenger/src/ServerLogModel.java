/**
 *
 *
 *
 */
public class ServerLogModel {


    private String chatLog;

    public ServerLogModel(){

        chatLog = "";
    }

    public void addToChatLog(String message){

        chatLog = chatLog + message;

    }

    public String getChatLog() {
        return chatLog;
    }
}
