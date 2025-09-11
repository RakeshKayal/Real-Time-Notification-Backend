package Com.test.MessageModel_StructureModel;

import java.util.List;

public class GroupCreationRequest {
    private String groupName;
    private String creatorName;
    private List<String > membersName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }



    public GroupCreationRequest() {
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public List<String> getMembersName() {
        return membersName;
    }

    public void setMembersName(List<String> membersName) {
        this.membersName = membersName;
    }

    public GroupCreationRequest(String groupName, String creatorName, List<String> membersName) {
        this.groupName = groupName;
        this.creatorName = creatorName;
        this.membersName = membersName;
    }
// Getters and setters
}
