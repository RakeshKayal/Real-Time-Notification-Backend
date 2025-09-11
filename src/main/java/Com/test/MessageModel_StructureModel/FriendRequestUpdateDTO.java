package Com.test.MessageModel_StructureModel;

public class FriendRequestUpdateDTO {
    private String requesterName;
    private String  responderName;
    private String status; // ACCEPTED or REJECTED

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getResponderName() {
        return responderName;
    }

    public void setResponderName(String responderName) {
        this.responderName = responderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FriendRequestUpdateDTO(String requesterName, String responderName, String status) {
        this.requesterName = requesterName;
        this.responderName = responderName;
        this.status = status;
    }

    public FriendRequestUpdateDTO() {
    }
}
