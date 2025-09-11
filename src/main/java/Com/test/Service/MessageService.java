package Com.test.Service;

import Com.test.Model.Massege;
import Com.test.Repo.MessageRepo;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepo repo;







    public void save(Massege msg) {
        repo.save(msg);
    }

    public List<Massege> findMessagesBetweenUsers(String user1, String user2) {

        return  repo.findMessagesBetweenUsers(user1,user2);
    }
}
