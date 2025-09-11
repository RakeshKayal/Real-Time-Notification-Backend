package Com.test.Service;

import Com.test.Model.GroupMembership;
import Com.test.Repo.GroupMemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberService {
    @Autowired
    GroupMemberRepo repo;



    public GroupMembership save(GroupMembership membership) {

        return  repo.save( membership);
    }

    public List<GroupMembership> findAllByUserId(int userId) {
        return repo.findByUserId(userId);
    }

    public List<GroupMembership> findAllByGroupId(Long groupId) {
        return repo.findByGroupId(groupId);
    }

}
