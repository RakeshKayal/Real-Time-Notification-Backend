package Com.test.Controller;


import Com.test.MessageModel_StructureModel.*;
import Com.test.JWTConfig.generateToken;
import Com.test.Model.*;
import Com.test.Respons.tokenResponse;
import Com.test.Respons.userResponse;
import Com.test.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/public")
@CrossOrigin(origins = "http://localhost:5173")
public class homeController {
    @Autowired
    private userRegistrationService  service;
    @Autowired
    private generateToken  tokens;

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private GroupService groupService;
    @Autowired
    private FriendService friendService;
   // private GroupMemberService groupMemberService1;

    @Autowired
    private MessageService messageService;

    @Autowired
    private  GroupMessageService groupMessageService;





    @GetMapping("/home")
    public  String home(){
        return "this is home";
    }


    @PostMapping("/register")
    public ResponseEntity<?> Registration(@RequestBody user u) {

      try {
          user savedUser = service.save(u);
          String token= tokens.generate(u);
          System.out.println(token);
          tokenResponse response = new tokenResponse("200", token,"null", "user Registration sucessfull");
          return ResponseEntity.ok(response);
      }
      catch ( Exception e){

          userResponse fail= new userResponse("403", u,"not reachable");
          return  ResponseEntity.status(403).body(fail);
      }
    }


    @PostMapping("/login")
    public ResponseEntity<tokenResponse>  login(@RequestBody user u){

        String verify = service.verify(u);

        tokenResponse fail=new tokenResponse("400","no token ", "null","authentication fail");
        tokenResponse sucess= new tokenResponse("200", verify,u.getName(),"authentication Sucessfull");

        if(verify.equals("fail")){
            return  ResponseEntity.badRequest().body(fail);

        }
        else {
            return  ResponseEntity.ok(sucess);
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        List<user> u= service.getAll();

        return  ResponseEntity.ok(u);


    }

    @PostMapping("/GroupCreate")
    public ResponseEntity<?> createGroup(@RequestBody GroupCreationRequest request) {
        try {
            // 1. Get the group creator
            user creator = service.findByName(request.getCreatorName());
            if(creator==null){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new userResponse("300",null,"sorry not found"));
            }


            GroupChat group = new GroupChat();
            group.setGroupName(request.getGroupName());
            group.setCreatedBy(creator);
            group.setCreatedAt(LocalDateTime.now());
            groupService.create(creator, group);

            GroupMembership creatorMembership = new GroupMembership();
            creatorMembership.setUser(creator);
            creatorMembership.setGroup(group);
            creatorMembership.setJoinedAt(LocalDateTime.now());
            groupMemberService.save(creatorMembership);


          //  FriendRequest request1= new FriendRequest();


            for (String membersName : request.getMembersName()) {
                user member = service.findByName(membersName);

                if(member == null){
                    return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new userResponse("300",null,"notfound"));
                }

                if (friendService.isAcceptedFriend(creator.getId(),  member.getId())) {
                    GroupMembership membership = new GroupMembership();
                    membership.setUser(member);
                    membership.setGroup(group);
                    membership.setJoinedAt(LocalDateTime.now());
                    groupMemberService.save(membership);
                } else {
                    System.out.println("Skipped adding user " + member.getId() + " — not an accepted friend");
                }
            }

            return ResponseEntity.ok("Group created with members successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Group creation failed: " + e.getMessage());
        }
    }


    @PostMapping("/friendRequest")
    public  ResponseEntity<?> frindRequest(@RequestBody FriendRequestCreation requestCreation){

        try {
            user sender = service.findByName(requestCreation.getSenderName());
            if(sender== null ){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new userResponse("300",null,"notfound"));
            }

            for (String receiverName : requestCreation.getReceiverName()) {
                user receiver = service.findByName(receiverName);
                if(receiver== null){
                    return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new userResponse("300",null,"notfound"));
                }
                FriendRequest request = new FriendRequest();
                request.setSender(sender);
                request.setReceiver(receiver);
                request.setStatus("PENDING");
                request.setSentAt(LocalDateTime.now());
                friendService.save(request);
            }

            return ResponseEntity.ok("Friend requests sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send friend requests: " + e.getMessage());
        }
    }

    @PostMapping("/friendRequest/respond")
    public ResponseEntity<?> respondToFriendRequest(@RequestBody FriendRequestUpdateDTO updateDTO) {
        try {
            System.out.println("Incoming request: " + updateDTO); // debug log

            user requesterId = service.findByName(updateDTO.getRequesterName());
            user responderId = service.findByName(updateDTO.getResponderName());

            if (requesterId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Requester not found: " + updateDTO.getRequesterName());
            }
            if (responderId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Responder not found: " + updateDTO.getResponderName());
            }

            friendService.updateStatus(requesterId, responderId, updateDTO.getStatus());
            return ResponseEntity.ok("Friend request " + updateDTO.getStatus().toLowerCase());

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return ResponseEntity.status(500)
                    .body("Failed to update friend request: " + e.getMessage());
        }
    }






    @GetMapping("/allFriendRequest/{userName}")
    public ResponseEntity<List<AllRequestDTO>> getAll(@PathVariable String userName) {
        user find = service.findByName(userName);

        if (find == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    List.of(new AllRequestDTO("User not found", null, null, null))
            );
        }

        List<FriendRequest> allRequest = friendService.findAllRequest(find.getId());

        if (allRequest.isEmpty()) {

            return (ResponseEntity<List<AllRequestDTO>>) List.of(new AllRequestDTO(null, null, null, null));
        }

        List<AllRequestDTO> dtoList = allRequest.stream()
                .map(req -> new AllRequestDTO(
                        req.getSender().getName(),
                        req.getSender().getEmail(),
                        req.getSentAt().toString(),  // Convert LocalDateTime to String
                        req.getStatus()
                ))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

//    @GetMapping("/acceptedFriends/{userName}")
//    public ResponseEntity<List<AcceptedFriendDTO>> getAcceptedFriends(@PathVariable String userName) {
//        user find = service.findByName(userName);
//
//        if (find == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    List.of(new AcceptedFriendDTO("User not found", null, null))
//            );
//        }
//
//        List<AcceptedFriendDTO> dtoList = friendService.getAcceptedFriends(userName);
//
//        if (dtoList.isEmpty()) {
//            return ResponseEntity.ok(List.of()); // return empty list if no friends
//        }
//
//        return ResponseEntity.ok(dtoList);
//    }
//
//





    @GetMapping("/allPendingrequest/{userName}")
    public ResponseEntity<List<AllRequestDTO>> pendingRequest(@PathVariable String userName) {
        user find = service.findByName(userName);

        if (find == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    List.of(new AllRequestDTO("User not found", null, null, null))
            );
        }

        List<FriendRequest> allRequest = friendService.findAllRequest(find.getId());

        List<FriendRequest> pendingRequests = allRequest.stream()
                .filter(req -> "pending".equalsIgnoreCase(req.getStatus()))
                .toList();

        if (pendingRequests.isEmpty()) {
            return ResponseEntity.ok(
                    List.of(new AllRequestDTO("No pending requests", null, null, null))
            );
        }

        List<AllRequestDTO> dtoList = pendingRequests.stream()
                .map(req -> new AllRequestDTO(
                        req.getSender().getName(),
                        req.getSender().getEmail(),
                        req.getSentAt().toString(),
                        req.getStatus()
                ))
                .toList();

        return ResponseEntity.ok(dtoList);
    }


    @GetMapping("/getgroup/{userName}")
    public ResponseEntity<Object> memberships(@PathVariable String userName) {
        user get = service.findByName(userName);

        if (get == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new userResponse("300", null, "sorry not found"));
        }

        List<GroupMembership> memberships = groupMemberService.findAllByUserId(get.getId());

        if ( memberships.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new userResponse("300", null, "sorry not found"));
        }

        List<Group_MemberShip> result = memberships.stream().map(membership -> {
            GroupChat group = membership.getGroup();

            List<String> memberNames = groupMemberService.findAllByGroupId(group.getId())
                    .stream()
                    .map(m -> m.getUser().getName())
                    .toList();

            return new Group_MemberShip(
                    group.getGroupName(),
                    group.getCreatedBy().getName(),
                    memberNames
            );
        }).toList();

        return ResponseEntity.ok(result);
    }




    @GetMapping("/getFriends/{userName}")
    public ResponseEntity<?> getFriends(@PathVariable String userName) {
        user currentUser = service.findByName(userName);

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new userResponse("404", null, "User not found"));
        }

        List<user> friends = friendService.getAcceptedFriends(currentUser.getId());

        if (friends.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new userResponse("404", null, "No accepted friends found"));
        }

        return ResponseEntity.ok(friends);
    }




    @GetMapping("/checkFriendship/{user1}/{user2}")
    public ResponseEntity<String> checkFriendship(@PathVariable String user1, @PathVariable String user2) {
        boolean isFriend = friendService.areFriends(user1, user2);

        if (isFriend) {
            return ResponseEntity.ok("Users are friends");
        } else {
            return ResponseEntity.ok("Users are not friends");
        }
    }

    @GetMapping("/privateChatHistory/message/{user1}/{user2}")
    public ResponseEntity<List<persosonalChatHistory>> getPrivateMessages(
            @PathVariable String user1,
            @PathVariable String user2
    ) {

        System.out.println("comming here");

        List<Massege> messages = messageService.findMessagesBetweenUsers(user1, user2);

        List<persosonalChatHistory> response = messages.stream().map(m -> new persosonalChatHistory(
                m.getId(),
                new SimpleUserDTO((long) m.getSender().getId(), m.getSender().getName(), m.getSender().getEmail()),
                new SimpleUserDTO((long) m.getReceiver().getId(), m.getReceiver().getName(), m.getReceiver().getEmail()),
                m.getContent(),
                m.getSentAt()
        )).toList();

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{groupName}/messages")
    public List<GroupMsgHistory> getGroupMessages(@PathVariable String groupName) {
        List<GroupMessage> messages = groupMessageService.getGroupMessages(groupName);

        return messages.stream()
                .map(m -> new GroupMsgHistory(
                        m.getSender().getName(),  // extract sender name from user entity
                        m.getContent(),
                        m.getSentAt()
                ))
                .collect(Collectors.toList());
    }


}




