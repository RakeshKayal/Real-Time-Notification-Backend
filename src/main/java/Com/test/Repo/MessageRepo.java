package Com.test.Repo;

import Com.test.Model.Massege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepo extends JpaRepository<Massege,Integer> {
    List<Massege> findBySenderIdAndReceiverId(Long senderId, Long receiverId);



    @Query("SELECT m FROM Massege m WHERE ((m.sender.name = :user1 AND m.receiver.name = :user2) OR (m.sender.name = :user2 AND m.receiver.name = :user1)) ORDER BY m.sentAt ASC")
    List<Massege> findMessagesBetweenUsers(@Param("user1") String user1, @Param("user2") String user2);

}
