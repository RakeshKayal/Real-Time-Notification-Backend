package Com.test.MessageModel_StructureModel;

import java.time.LocalDateTime;

public class persosonalChatHistory {



        private Long id;
        private SimpleUserDTO sender;
        private SimpleUserDTO receiver;
        private String content;
        private LocalDateTime sentAt;



        // Getters and setters
        // (You can use Lombok @Data if preferred)


    public persosonalChatHistory(Long id, SimpleUserDTO sender, SimpleUserDTO receiver, String content, LocalDateTime sentAt) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.sentAt = sentAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimpleUserDTO getSender() {
        return sender;
    }

    public void setSender(SimpleUserDTO sender) {
        this.sender = sender;
    }

    public SimpleUserDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(SimpleUserDTO receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public persosonalChatHistory() {
    }
}
