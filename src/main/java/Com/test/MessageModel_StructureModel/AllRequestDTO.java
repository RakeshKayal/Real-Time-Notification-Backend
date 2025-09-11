package Com.test.MessageModel_StructureModel;

import java.time.LocalDateTime;

public class AllRequestDTO {


    public  String  senderName;
    private String  senderEmail;
    private String  sentAt;
    private  String  status;

    public AllRequestDTO(String senderName, String senderEmail, String sentAt, String status) {
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.sentAt = sentAt;
        this.status = status;
    }

    public AllRequestDTO(String name, String email, LocalDateTime sentAt) {
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }

    public AllRequestDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
