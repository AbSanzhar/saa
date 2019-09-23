package kz.saa.prototype.security;

import kz.saa.prototype.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jackson2.SimpleGrantedAuthorityMixin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.url-param}")
    private String jwtParam;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getParameter(jwtParam) == null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String jwtToken = httpServletRequest.getParameter(jwtParam);
        if (this.authenticationService.validateToken(jwtToken) == "OK") {
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            String role = this.authenticationService.getRoleByToken(jwtToken);
            authorities.add(new SimpleGrantedAuthority(role));
            Authentication authentication = new UsernamePasswordAuthenticationToken(jwtToken, null,
                    authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}