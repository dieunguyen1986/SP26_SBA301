package edu.lms.service;

import edu.lms.dto.CustomUserDetails;
import edu.lms.entity.Role;
import edu.lms.entity.User;
import edu.lms.repository.RoleRepository;
import edu.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsManager {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        // Happy case - login success
        // Get list role
        List<Role> roles = roleRepository.findRole_UsersById(user.getId());

        // Get Role and Permissions
        List<GrantedAuthority> grantedAuthorities = roles.stream()
                .map((role)-> {
                    return new SimpleGrantedAuthority(role.getName());
                }).collect(Collectors.toList());

        return new CustomUserDetails(user.getEmail(), user.getPassword(), user.getFullName(), grantedAuthorities);
    }
}
