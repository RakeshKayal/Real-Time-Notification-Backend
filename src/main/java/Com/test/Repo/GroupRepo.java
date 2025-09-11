package Com.test.Repo;

import Com.test.Model.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository<GroupChat, Integer> {
   // GroupChat findById(Long groupId);

    GroupChat findByGroupName(String groupName);

}
