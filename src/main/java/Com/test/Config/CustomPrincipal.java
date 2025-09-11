package Com.test.Config;


import Com.test.Model.user;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomPrincipal implements UserDetails {

    user u;

    public CustomPrincipal( user u) {
        this.u = u;
        System.out.println("com there");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return empty list for now. You can integrate roles here later.
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        System.out.println("DEBUG: getPassword() called => " + u.getPassword());
        return u.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println("DEBUG: getPassword() called => " + u.getName());
        return u.getName(); // Usually email is used as username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assume always true
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assume always true
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assume always true
    }

    @Override
    public boolean isEnabled() {
        return true; // Assume always true
    }

    public user getUser() {
     return  this.u;
    }
}
