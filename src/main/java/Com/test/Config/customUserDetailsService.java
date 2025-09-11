package Com.test.Config;

import Com.test.Model.user;
import Com.test.Repo.userRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class customUserDetailsService implements UserDetailsService {


    @Autowired
    private  userRegistrationRepo  repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("DEBUG: Looking for user with name: " + username);
        user  u= repo.findByName(username);

        if (u== null){
            System.out.println("DEBUG: Looking for user not found ");
            throw  new RuntimeException("user not found");

        }
        System.out.println("DEBUG: found  with name: " + username);
        return new CustomPrincipal(u);
    }
}
