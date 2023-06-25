package space.bumtiger.auth.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import space.bumtiger.auth.users.User;
import space.bumtiger.auth.users.UserRepository;

@Configuration
public class ApplicationConfig {

	private void createUserIfNotExists(UserRepository repository,
			PasswordEncoder encoder, String username, String roles) {
		if (null == repository.findByUsername(username)) {
			repository.save(new User(username, encoder.encode("1234"), roles));
		}
	}

	@Bean
	ApplicationRunner dataLoader(UserRepository repository,
			PasswordEncoder encoder) {
		return args -> {
			createUserIfNotExists(repository, encoder, "soap",
					"ROLE_ADMIN,ROLE_USER");
			createUserIfNotExists(repository, encoder, "bum", "ROLE_USER");
		};
	}
	
}
