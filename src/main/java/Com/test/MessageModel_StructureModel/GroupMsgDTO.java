package Com.test.MessageModel_StructureModel;

public class GroupMsgDTO {
    private String groupName;
    private String senderName;
    private String content;

    // Getters and Setters

    public GroupMsgDTO() {}

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GroupMsgDTO(String groupName, String senderName, String content) {
        this.groupName = groupName;
        this.senderName = senderName;
        this.content = content;
    }
}
