package Com.test.Repo;

import Com.test.Model.GroupMessage;
import Com.test.Model.Massege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMessageRepo extends JpaRepository<GroupMessage, Integer> {
    List<GroupMessage> findByGroup_GroupNameOrderBySentAtAsc(String groupName);


}
