package Com.test.Service;

import Com.test.JWTConfig.generateToken;
import Com.test.Model.user;
import Com.test.Repo.userRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class userRegistrationService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private userRegistrationRepo  repo;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private generateToken  token;

    public user save(user u) {
        System.out.println("Before encoding: " + u.getPassword());
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        System.out.println("After encoding: " + u.getPassword());

        u.setDate(LocalDateTime.now());
        return repo.save(u);
    }

    public String  verify(user u) {

        try {
            Authentication auth = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(u.getName(), u.getPassword()));

            if (auth.isAuthenticated()) {
                return token.generate(u);
            } else {
                return "fail";
            }
        } catch (Exception e) {
            // This will catch BadCredentialsException, etc.
            return "fail";
        }
    }

    public List<user> getAll() {
        return  repo.findAll();
    }

    public user findById(Long creatorUserId) {

        return  repo.findById(creatorUserId);
    }
    public user findById(int creatorUserId) {

        return  repo.findById(creatorUserId);
    }

    public user findByName(String senderName) {
        return  repo.findByName(senderName);
    }
}
