package Com.test.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_messages")
public class GroupMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private user sender;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupChat group;

    private String content;
    private LocalDateTime sentAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public user getSender() {
        return sender;
    }

    public void setSender(user sender) {
        this.sender = sender;
    }

    public GroupChat getGroup() {
        return group;
    }

    public void setGroup(GroupChat group) {
        this.group = group;
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

    public GroupMessage(Long id, user sender, GroupChat group, String content, LocalDateTime sentAt) {
        this.id = id;
        this.sender = sender;
        this.group = group;
        this.content = content;
        this.sentAt = sentAt;
    }

    public GroupMessage() {
    }
}


