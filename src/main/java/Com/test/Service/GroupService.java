package Com.test.Service;

import Com.test.Model.GroupChat;
import Com.test.Model.user;
import Com.test.Repo.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GroupService {
    @Autowired
    GroupRepo repo;

    public void create(user u, GroupChat groupDetails) {
        groupDetails.setCreatedBy(u);
        groupDetails.setCreatedAt(java.time.LocalDateTime.now());
        repo.save(groupDetails);
    }
}
