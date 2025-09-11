package Com.test.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user-Registration")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;
    private String email;
    private String password;

    @Column(name = "created_date")
    private LocalDateTime date;

    // Bidirectional Mappings
    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<FriendRequest> sentFriendRequests;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<FriendRequest> receivedFriendRequests;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Massege> sentMessages;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<Massege> receivedMessages;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<GroupMessage> groupMessages;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<GroupMembership> groupMemberships;

    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    private List<GroupChat> createdGroups;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // Constructors
    public user() {
    }

    public user(int id, String name, String email, String password, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.date = date;
    }

    // Relationships Getters (Optional)
    public List<FriendRequest> getSentFriendRequests() {
        return sentFriendRequests;
    }

    public void setSentFriendRequests(List<FriendRequest> sentFriendRequests) {
        this.sentFriendRequests = sentFriendRequests;
    }

    public List<FriendRequest> getReceivedFriendRequests() {
        return receivedFriendRequests;
    }

    public void setReceivedFriendRequests(List<FriendRequest> receivedFriendRequests) {
        this.receivedFriendRequests = receivedFriendRequests;
    }

    public List<Massege> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Massege> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Massege> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Massege> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<GroupMessage> getGroupMessages() {
        return groupMessages;
    }

    public void setGroupMessages(List<GroupMessage> groupMessages) {
        this.groupMessages = groupMessages;
    }

    public List<GroupMembership> getGroupMemberships() {
        return groupMemberships;
    }

    public void setGroupMemberships(List<GroupMembership> groupMemberships) {
        this.groupMemberships = groupMemberships;
    }

    public List<GroupChat> getCreatedGroups() {
        return createdGroups;
    }

    public void setCreatedGroups(List<GroupChat> createdGroups) {
        this.createdGroups = createdGroups;
    }
}
