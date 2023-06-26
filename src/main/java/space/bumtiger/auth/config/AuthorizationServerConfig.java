package space.bumtiger.auth.config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
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
	
	@Bean
	RegisteredClientRepository registeredClientRepository(
	        PasswordEncoder passwordEncoder) {
		// @formatter:off
	  RegisteredClient registeredClient = 
	    RegisteredClient.withId(UUID.randomUUID().toString())
	      .clientId("burger-admin-client")
	      .clientSecret(passwordEncoder.encode("1234"))
	      .clientAuthenticationMethod(
	              ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
	      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
	      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
	      .redirectUri(
	          "http://127.0.0.1:9090/login/oauth2/code/burger-admin-client")
	      .scope("writeIngredients")
	      .scope("deleteIngredients")
	      .scope(OidcScopes.OPENID)
	      .clientSettings(
	      		ClientSettings.builder().requireAuthorizationConsent(true).build())
	      .build();
	  return new InMemoryRegisteredClientRepository(registeredClient);
		// @formatter:on
	}	
}
