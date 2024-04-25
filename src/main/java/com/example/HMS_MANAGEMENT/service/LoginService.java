//package com.example.HMS_MANAGEMENT.service;
//
//import com.example.HMS_MANAGEMENT.entity.LoginEntity;
//import com.example.HMS_MANAGEMENT.repository.LoginRepo;
//import lombok.RequiredArgsConstructor;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
////import org.springframework.security.core.userdetails.UserDetailsService;
//
//@Service
//public class LoginService {
//
//    @Autowired
//    private LoginRepo loginRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder; // 비밀번호 암호화를 위한 PasswordEncoder
//
//    // 사용자 인증 메서드
//    public boolean authenticate(String userId, String password) {
//        // 입력받은 아이디로 사용자를 찾습니다.
//        LoginEntity user = loginRepo.findByUserId(userId);
//        // 사용자가 존재하고, 입력받은 비밀번호가 암호화된 비밀번호와 일치하면 true를 반환합니다.
//        return user != null && passwordEncoder.matches(password, user.getPassword());
//    }
//
//    // 아이디로 사용자 정보를 찾는 메서드
//    public LoginEntity findByUserId(String userId) {
//        return loginRepo.findByUserId(userId);
//    }
//}
