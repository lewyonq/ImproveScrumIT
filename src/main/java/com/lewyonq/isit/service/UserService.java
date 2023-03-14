package com.lewyonq.isit.service;

import com.lewyonq.isit.enums.UserRole;
import com.lewyonq.isit.model.RegisterRequest;
import com.lewyonq.isit.model.User;
import com.lewyonq.isit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyService companyService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void addUser(RegisterRequest request, UserRole role, @AuthenticationPrincipal User owner) throws Exception {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .userRole(role)
                .company(companyService.findByID(owner.getCompany().getId()))
                .build();

        userRepository.save(user);
    }

    public void registerOwner(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .userRole(UserRole.OWNER)
                .build();

        userRepository.save(user);
    }

    public List<User> findByCompanyId(Long id) {
        return userRepository.findByCompanyId(id);
    }
}
