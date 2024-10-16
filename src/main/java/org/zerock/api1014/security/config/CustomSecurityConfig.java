package org.zerock.api1014.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zerock.api1014.security.filter.JWTCheckFilter;
import org.zerock.api1014.security.util.JWTUtil;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) //preAuthorize 사용하려고
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final JWTUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(
//                (auth) -> auth.requestMatchers("/api/v1/product/list").permitAll()
//        );
        http.formLogin(config -> config.disable()); //로그인화면 안 만들거야

        //내부적으로 세션도 안만들거야 => 인증방법이 토큰 밖에 없음
        http.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.NEVER));

        http.csrf(config -> config.disable());

        http.addFilterBefore(new JWTCheckFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
