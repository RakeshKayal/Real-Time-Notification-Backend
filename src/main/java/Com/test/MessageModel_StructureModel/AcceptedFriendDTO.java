package Com.test.MessageModel_StructureModel;

public class AcceptedFriendDTO {
    private String name;
    private String email;
    private String connectedAt;

    public AcceptedFriendDTO(String name, String email, String connectedAt) {
        this.name = name;
        this.email = email;
        this.connectedAt = connectedAt;


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

    public String getConnectedAt() {
        return connectedAt;
    }

    public void setConnectedAt(String connectedAt) {
        this.connectedAt = connectedAt;
    }

    public AcceptedFriendDTO() {
    }
}
