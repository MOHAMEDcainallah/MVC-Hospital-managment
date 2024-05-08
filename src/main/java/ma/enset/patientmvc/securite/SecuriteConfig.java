package ma.enset.patientmvc.securite;
import ma.enset.patientmvc.securite.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecuriteConfig {
    @Autowired           
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public SecuriteConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

     auth.userDetailsService(userDetailsService);

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/user/**").hasAnyAuthority("USER")
                        .requestMatchers("/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling((exceptions) -> exceptions
                        .accessDeniedPage("/403")
                );

        return http.build();
    }

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource, PasswordEncoder passwordEncoder) {
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        userDetailsManager.setUsersByUsernameQuery("SELECT username as principal, password as credentials, active FROM users WHERE username = ?");
//        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT username as principal, role as role FROM users_roles WHERE username = ?");
//        userDetailsManager.setRolePrefix("ROLE_"); // Add role prefix
//
//        // Set the password encoder
//
//        return userDetailsManager;
//    }




    // Example user details for demonstration purposes
//    @Bean
//    public UserDetails user1(PasswordEncoder passwordEncoder) {
//        return User.builder()
//                .username("user1")
//                .password(passwordEncoder.encode("1111")) // Use the passed encoder
//                .roles("USER")
//                .build();
//    }
//
//    @Bean
//    public UserDetails user2(PasswordEncoder passwordEncoder) {
//        return User.builder()
//                .username("user2")
//                .password(passwordEncoder.encode("2222")) // Use the passed encoder
//                .roles("USER")
//                .build();
//    }
//
//    @Bean
//    public UserDetails admin(PasswordEncoder passwordEncoder) {
//        return User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("12345")) // Use the passed encoder
//                .roles("USER", "ADMIN")
//                .build();
//    }
}














