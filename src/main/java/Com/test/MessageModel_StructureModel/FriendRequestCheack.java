package Com.test.MessageModel_StructureModel;

public class FriendRequestCheack {

    public  long senderId;
    public  long receiverId;
    public  String  status;

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FriendRequestCheack(long senderId, long receiverId, String status) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }

    public FriendRequestCheack() {
    }
}
