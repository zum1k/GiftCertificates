package com.epam.esm.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {
  private static final String PERMIT_ALL = "permitAll()";
  private static final String IS_AUTHENTICATED = "isAuthenticated()";

  @Value("${spring.security.oauth2.client.id}")
  private String clientId;

  @Value("${spring.security.oauth2.client.secret}")
  private String clientSecret;

  @Value("${spring.security.oauth2.grant-types}")
  private String[] grantTypes;

  @Value("${spring.security.oauth2.scopes}")
  private String[] scopes;

  @Value("${spring.security.oauth2.authorities}")
  private String[] authorities;

  @Value("${spring.security.oauth2.access-token-validity-seconds}")
  private Integer accessTokenValidity;

  @Value("${spring.security.oauth2.signing-key}")
  private String signingKey;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  @Qualifier("UserDetailsServiceImpl")
  private UserDetailsService userService;

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private JwtAccessTokenConverter accessTokenConverter;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey(signingKey);
    return converter;
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
        .inMemory()
        .withClient(clientId)
        .secret(passwordEncoder.encode(clientSecret))
        .authorizedGrantTypes(grantTypes)
        .authorities(authorities)
        .scopes(scopes)
        .autoApprove(true)
        .accessTokenValiditySeconds(accessTokenValidity);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints
        .authenticationManager(authenticationManager)
        .accessTokenConverter(accessTokenConverter)
        .tokenStore(tokenStore)
        .userDetailsService(userService);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) {
    security
        .passwordEncoder(passwordEncoder)
        .tokenKeyAccess(PERMIT_ALL)
        .checkTokenAccess(IS_AUTHENTICATED);
  }
}
