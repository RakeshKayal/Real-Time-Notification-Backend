package Com.test.MessageModel_StructureModel;

import java.util.List;

public class FriendRequestCreation {
    private String senderName;
    private List<String > receiverName;


    public FriendRequestCreation() {
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public List<String> getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(List<String> receiverName) {
        this.receiverName = receiverName;
    }

    public FriendRequestCreation(String senderName, List<String> receiverName) {
        this.senderName = senderName;
        this.receiverName = receiverName;
    }
}
