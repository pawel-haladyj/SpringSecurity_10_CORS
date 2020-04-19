package pl.haladyj.springsecurity10.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Value("${cors.configuration.allowed.origins}")
    private List<String> cCAllowedOrigins;

    @Value("${cors.configuration.allowed.methods}")
    private List<String> cCAllowedMethods;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable();
        http
                .authorizeRequests()
                .anyRequest()
                .permitAll();

        http.cors(c->{
            CorsConfigurationSource corsConfigurationSource = r->{
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(cCAllowedOrigins);
                corsConfiguration.setAllowedMethods(cCAllowedMethods);
                return corsConfiguration;
            };
        });
    }
}
