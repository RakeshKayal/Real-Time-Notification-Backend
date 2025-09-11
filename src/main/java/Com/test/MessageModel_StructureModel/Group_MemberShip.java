package Com.test.MessageModel_StructureModel;

import java.util.List;

public class Group_MemberShip {


    public  String  groupName;

    public  String creatorName;

    public List<String > members;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Group_MemberShip(String groupName, String creatorName, List<String> members) {
        this.groupName = groupName;
        this.creatorName = creatorName;
        this.members = members;
    }

    public Group_MemberShip() {
    }
}
