package Com.test.MessageModel_StructureModel;

public class PersonalMsgDTO {
    private String  senderName;
    private String receiverName;
    private String content;

    // Getters & Setters


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public PersonalMsgDTO() {
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public PersonalMsgDTO(String senderName, String receiverName, String content) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.content = content;
    }
}

