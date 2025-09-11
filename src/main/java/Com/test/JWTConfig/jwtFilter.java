package Com.test.JWTConfig;

import Com.test.Config.CustomPrincipal;
import Com.test.Config.customUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class jwtFilter extends OncePerRequestFilter {


    @Autowired
    private generateToken service;
    @Autowired
    private customUserDetailsService  customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header= request.getHeader("authorization");
        String token= null;
        String  name= null;


        if(header!=null && header.startsWith("Bearer ")){

            token= header.substring(7);
            name= service.extractName(token);

        }

        if(name!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {

            CustomPrincipal user= (CustomPrincipal) customUserDetailsService.loadUserByUsername(name);



            if(service.valid(token,user)){

                System.out.println("token is valid");

                UsernamePasswordAuthenticationToken t=
                        new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());

                t.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(t);

            }


        }
        filterChain.doFilter(request, response);

    }
}
