package com.example.bulletinkotlin.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtTokenProvider {
    final val jwtSecretKey: String = "ashfsuaidfhsadhfuiwehfhiuwehfehfisadhfiashfuh"
    final val secretKeyObj: SecretKey = Keys.hmacShaKeyFor(jwtSecretKey.toByteArray())
    fun generateToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserDetails
        val jwtExpirationDate = Date(Date().time + 60 * 60 * 1000)

        // return JWT Token
        return Jwts.builder()
            .setSubject(userPrincipal.username) // 터큰 주체
            .setIssuedAt(Date()) // 발급시간
            .setExpiration(jwtExpirationDate)
            .signWith(secretKeyObj, SignatureAlgorithm.HS512) // 최신 버전에서 Key객체를 전달
            .compact()
    }

    fun getUsernameFromToken(token: String): String? {
        val claims = Jwts.parserBuilder().setSigningKey(secretKeyObj).build().parseClaimsJws(token).body
        return claims.subject
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(secretKeyObj).build().parseClaimsJws(token)
            return true
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return false
    }
}