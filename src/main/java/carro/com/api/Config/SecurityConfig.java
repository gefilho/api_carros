package carro.com.api.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/criar").permitAll() // Libera o endpoint de criação de admin
                .requestMatchers("/usuario/criar").permitAll() // Libera criação de usuários
                .requestMatchers("/carro/listar").permitAll() // Libera listar carros
                .requestMatchers("/carro/criar").hasRole("ADMIN") // Apenas ADMIN pode criar carro
                .requestMatchers("/carro/editar/**").hasRole("ADMIN") // Apenas ADMIN pode editar carro
                .requestMatchers("/carro/deletar/**").hasRole("ADMIN") // Apenas ADMIN pode deletar carro
                .anyRequest().authenticated() // Outros endpoints precisam de autenticação
            )
            .httpBasic(); // Usando autenticação HTTP básica

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
