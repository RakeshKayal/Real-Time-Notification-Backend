package Com.test.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_chats")
public class GroupChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    private user createdBy;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public user getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(user createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public GroupChat(Long id, String groupName, user createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.groupName = groupName;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public GroupChat() {
    }
}

