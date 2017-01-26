package za.co.tangent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import za.co.tangent.security.RestUnauthorizedEntryPoint;
import za.co.tangent.service.RestAuthProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthProvider restAuthProvider;

    @Autowired
    private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private AuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(restAuthProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/login.jsp", "/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/failure").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler)
                .and().httpBasic().realmName("TEST_REALM")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .successHandler(restAuthenticationSuccessHandler)
                //.failureHandler(restAuthenticationFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll().and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .permitAll();

        /* http.authorizeRequests()
         .antMatchers("/failure").permitAll()
         .anyRequest().fullyAuthenticated()
         .and()
         .formLogin().loginPage("/login").failureUrl("/login?error")
         .usernameParameter("username").passwordParameter("password")
         .permitAll()
         .and()
         .logout().logoutSuccessUrl("/login?logout")
         .and()
         .csrf();*/

        /*
         http
         .headers().disable()
         .csrf().disable()
         .authorizeRequests()
         .antMatchers("/failure").permitAll()
         .anyRequest().fullyAuthenticated()
         .and()
         .exceptionHandling()
         .authenticationEntryPoint(restAuthenticationEntryPoint)
         .accessDeniedHandler(restAccessDeniedHandler)
         .and()
         .formLogin()
         .loginProcessingUrl("/authenticate")
         .successHandler(restAuthenticationSuccessHandler)
         .failureHandler(restAuthenticationFailureHandler)
         .usernameParameter("username")
         .passwordParameter("password")
         .permitAll()
         .and()
         .logout()
         .logoutUrl("/logout")
         .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
         .deleteCookies("JSESSIONID")
         .permitAll();
         */
    }
}
