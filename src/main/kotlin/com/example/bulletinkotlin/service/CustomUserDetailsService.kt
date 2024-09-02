package com.example.bulletinkotlin.service

import com.example.bulletinkotlin.entity.User
import com.example.bulletinkotlin.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository  // 생성자 주입을 통해 UserRepository를 주입
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        // 사용자 이름으로 사용자를 찾습니다.
        val user: User = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")

        // 기존에 Spring으로 구현된 User(implements UserDetails) 객체를 custom User 정보를 참고하여 반환
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            user.roles.map { SimpleGrantedAuthority(it.name) }  // 역할을 GrantedAuthority로 변환
        )
    }
}