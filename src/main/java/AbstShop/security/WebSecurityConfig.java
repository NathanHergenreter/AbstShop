package AbstShop.security;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    private ModUserDetailsService userDetailsService;

    @Autowired
    private WebApplicationContext applicationContext;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void completeSetup() {
        userDetailsService = applicationContext.getBean(ModUserDetailsService.class);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(encoder())
            .and()
            .authenticationProvider(authenticationProvider())
            .jdbcAuthentication()
            .dataSource(dataSource);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/home", "/signup", "/successSignup")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .authorizeRequests()
            	.antMatchers("/resources/**")
            	.permitAll()
            	.anyRequest()
            	.permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .failureUrl("/login.html?error=true")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
    
}