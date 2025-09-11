package Com.test.Service;

import Com.test.MessageModel_StructureModel.AcceptedFriendDTO;
import Com.test.Model.FriendRequest;
import Com.test.Model.user;
import Com.test.Repo.FriendRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendService {
    @Autowired
    FriendRequestRepo repo;

    public void save(FriendRequest request) {
        repo.save(request);
    }
    public FriendRequest updateStatus(user sender, user receiver, String status) {
        // Fetch friend request from DB
        FriendRequest request = (FriendRequest) repo.findBySenderIdAndReceiverId(sender.getId(), receiver.getId())
                .orElseThrow(() -> new RuntimeException("Friend request not found between sender and receiver."));

        // Only the receiver is allowed to respond
        if (request.getReceiver().getId()!=(receiver.getId())) {
            throw new RuntimeException("Only the receiver can respond to the friend request.");
        }

        // Validate status
        if (!status.equalsIgnoreCase("ACCEPTED") && !status.equalsIgnoreCase("REJECTED")) {
            throw new RuntimeException("Status must be 'ACCEPTED' or 'REJECTED'");
        }

        request.setStatus(status.toUpperCase());
        return repo.save(request);
    }

    public boolean isAcceptedFriend(int user1Id, int user2Id) {
        return repo.findAcceptedFriendship(user1Id, user2Id).isPresent();
    }

  public List<FriendRequest> findAllRequest(int id){

        return  repo.findByReceiverId(id);
  }

    public List<user> getAcceptedFriends(int id) {

        List<FriendRequest> requests = repo.findAllAccepted(id); // Custom query needed
        List<user> friends = new ArrayList<>();

        for (FriendRequest req : requests) {
            if (req.getSender().getId()==id) {
                friends.add(req.getReceiver());
            } else {
                friends.add(req.getSender());
            }
        }
        return friends;
    }

    public boolean areFriends(String user1, String user2) {
        return repo.checkFriendship(user1, user2).isPresent();
    }
}
