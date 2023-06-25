package space.bumtiger.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.web.SecurityFilterChain;


@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {
	// @formatter:off
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	SecurityFilterChain authServerSecFilterChain(HttpSecurity http)
			throws Exception {
		OAuth2AuthorizationServerConfiguration
				.applyDefaultSecurity(http);
		return http
				.formLogin(Customizer.withDefaults())
				.build();

	}
	// @formatter:on
}
