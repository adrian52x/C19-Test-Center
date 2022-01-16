package Covid19project.springSecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {




   /*@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/
   @Autowired
   private DataSource dataSource;

    @Bean
    public PasswordEncoder  passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

  /*  @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        return  authProvider;
    }*/

    /*@Bean
    public UserDetailsService userDetailsService(){
        return new UserPrincipalDetailsService();
    }
*/



    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
              .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // URLs matching for access rights
                .antMatchers("/").permitAll()
                .antMatchers("/admin").hasAnyAuthority("ADMIN", "SECRETARY")
                .antMatchers("/admin/manageUser").hasAnyAuthority("ADMIN")
                .antMatchers("/admin/manageAppointment").hasAnyAuthority("ADMIN", "SECRETARY")
                .antMatchers("/admin/manageAddress").hasAnyAuthority("ADMIN", "SECRETARY")
                .antMatchers("/admin/manageTestCenter").hasAnyAuthority("ADMIN", "SECRETARY")
                .antMatchers("/showNewUserForm").hasAnyAuthority("ADMIN", "SECRETARY")
                .antMatchers("/booking").hasAnyAuthority("ADMIN", "SECRETARY", "USER")
                .antMatchers("/login").permitAll()
                /*.antMatchers("/register").permitAll()*/
                .antMatchers("/error").permitAll()
                .antMatchers("/home/**").hasAnyAuthority("ADMIN", "SECRETARY", "USER")
                .anyRequest().authenticated()
                .and()
                // form login
                .csrf().disable().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/", true)
                .usernameParameter("cpr")
                .passwordParameter("password")
                .and()
                // logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and()
                .exceptionHandling()
                .accessDeniedPage("/error");
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
