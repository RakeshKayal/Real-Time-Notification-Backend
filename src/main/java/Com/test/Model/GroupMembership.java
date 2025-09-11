package Com.test.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_memberships")
public class GroupMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private user user;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupChat group;

    private LocalDateTime joinedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Com.test.Model.user getUser() {
        return user;
    }

    public void setUser(Com.test.Model.user user) {
        this.user = user;
    }

    public GroupChat getGroup() {
        return group;
    }

    public void setGroup(GroupChat group) {
        this.group = group;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public GroupMembership(Long id, Com.test.Model.user user, GroupChat group, LocalDateTime joinedAt) {
        this.id = id;
        this.user = user;
        this.group = group;
        this.joinedAt = joinedAt;
    }

    public GroupMembership() {
    }
}

