import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/resources/**").permitAll() // Allow access to login and static resources
                .anyRequest().authenticated() // Protect other routes
                .and()
            .formLogin()
                .loginPage("/login") // Specify the custom login page
                .permitAll(); // Allow everyone to see the login page
    }
}
