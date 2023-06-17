package space.bumtiger.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import space.bumtiger.auth.users.UserRepository;

@EnableWebSecurity
public class SecurityConfig {

	// @formatter:off
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(
			HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(
						requests -> requests.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults()).build();
	}
	// @formatter:on
	
	@Bean
	UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> userRepo.findByUsername(username);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
