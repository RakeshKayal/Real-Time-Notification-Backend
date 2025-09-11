package Com.test.Repo;

import Com.test.Model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepo extends JpaRepository<FriendRequest, Integer> {
    FriendRequest findById(Long requestId);



    Optional<Object> findBySenderIdAndReceiverId(int senderId, int receiverId);

    @Query("SELECT fr FROM FriendRequest fr WHERE ((fr.sender.id = :user1 AND fr.receiver.id = :user2) OR (fr.sender.id = :user2 AND fr.receiver.id = :user1)) AND fr.status = 'ACCEPTED'")
    Optional<FriendRequest> findAcceptedFriendship(int user1, int user2);

    @Query("SELECT f FROM FriendRequest f WHERE ((f.sender.id = :senderId AND f.receiver.id = :receiverId) OR (f.sender.id = :receiverId AND f.receiver.id = :senderId)) AND f.status = 'ACCEPTED'")
    Optional<FriendRequest> findAcceptedRequestBetweenUsers(@Param("senderId") int senderId, @Param("receiverId") int receiverId);


    @Query("SELECT fr FROM FriendRequest fr WHERE (fr.sender.id = :userId OR fr.receiver.id = :userId) AND fr.status = 'ACCEPTED'")
    List<FriendRequest> findAllAccepted(@Param("userId") int userId);


    List<FriendRequest> findByReceiverId(int id);


    @Query("SELECT fr FROM FriendRequest fr WHERE " +
            "((fr.sender.name = :user1 AND fr.receiver.name = :user2) " +
            "OR (fr.sender.name = :user2 AND fr.receiver.name = :user1)) " +
            "AND fr.status = 'ACCEPTED'")
    Optional<Object> checkFriendship(String user1, String user2);
}
