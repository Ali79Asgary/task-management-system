//package com.example.task_management_system_ampada.security;
//
//import com.example.task_management_system_ampada.models.User;
//import com.example.task_management_system_ampada.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class MyUserDetails implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final User user = userRepository.findUserByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User '" + username + "' not found");
//        }
//
//        return org.springframework.security.core.userdetails.User//
//                .withUsername(username)//
//                .password(user.getPassword())//
//                .authorities(user.getAppUserRoles())//
//                .accountExpired(false)//
//                .accountLocked(false)//
//                .credentialsExpired(false)//
//                .disabled(false)//
//                .build();
//    }
//
//}