package cc.azin.atools.config;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Value("${casdoor.certificate}")
  private String certificate;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth ->
                auth
                    // 公开端点
                    .requestMatchers("/v1/public/**")
                    .permitAll()
                    .requestMatchers("/swagger-ui/**")
                    .permitAll()
                    .requestMatchers("/v3/**")
                    .permitAll()
                    // 需要ROLE_USER或ROLE_ADMIN角色
                    .requestMatchers("/v1/**")
                    .hasAnyRole("USER", "ADMIN")
                    // 需要ROLE_ADMIN角色
                    .requestMatchers("/admin/**")
                    .hasRole("ADMIN")
                    .anyRequest()
                    .authenticated())
        .oauth2ResourceServer(
            oauth2 ->
                oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));

    return http.build();
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(customJwtGrantedAuthoritiesConverter());
    return converter;
  }

  @Bean
  public Converter<Jwt, Collection<GrantedAuthority>> customJwtGrantedAuthoritiesConverter() {
    return new Converter<Jwt, Collection<GrantedAuthority>>() {
      @Override
      public Collection<GrantedAuthority> convert(@NotNull Jwt jwt) {
        String authoritiesClaimName = "roles";
        Object rolesClaim = jwt.getClaim(authoritiesClaimName);
        if (rolesClaim == null) {
          return List.of();
        }
        if (!(rolesClaim instanceof List<?> rolesList)) {
          return List.of();
        }
        return rolesList.stream()
            .filter(role -> role instanceof Map) // 确保元素是 Map
            .map(role -> (Map<String, Object>) role)
            .map(roleMap -> (String) roleMap.get("name"))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
      }
    };
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    try {
      // 解析证书
      String publicKeyPEM =
          certificate
              .replace("-----BEGIN CERTIFICATE-----", "")
              .replace("-----END CERTIFICATE-----", "")
              .replaceAll("\\s+", "");

      byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);
      CertificateFactory fact = CertificateFactory.getInstance("X.509");
      X509Certificate cer =
          (X509Certificate) fact.generateCertificate(new ByteArrayInputStream(encoded));
      RSAPublicKey publicKey = (RSAPublicKey) cer.getPublicKey();

      return NimbusJwtDecoder.withPublicKey(publicKey).build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to create JWT decoder", e);
    }
  }
}
