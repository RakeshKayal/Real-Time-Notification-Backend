package Com.test.Service;

import Com.test.Model.GroupMessage;
import Com.test.Repo.GroupMessageRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMessageService {

    @Autowired
    GroupMessageRepo groupMessageRepo;


    @Transactional
    public void save(GroupMessage msg) {
        groupMessageRepo.save(msg);
    }


    public List<GroupMessage> getGroupMessages(String groupName) {
        return groupMessageRepo.findByGroup_GroupNameOrderBySentAtAsc(groupName);
    }
}
