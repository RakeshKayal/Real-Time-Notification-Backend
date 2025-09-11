package Com.test.Repo;

import Com.test.Model.GroupMembership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMemberRepo extends JpaRepository<GroupMembership, Integer> {
    List<GroupMembership> findByUserId(int id);



    List<GroupMembership> findByGroupId(Long groupId);
}
