package in.nareshit.raghu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
	extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserDetailsService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	protected void configure(AuthenticationManagerBuilder auth) 
			throws Exception 
	{
		auth.userDetailsService(service)
		.passwordEncoder(encoder);
	}
	protected void configure(HttpSecurity http)
			throws Exception
	{
		
		http.authorizeRequests()
		.antMatchers("/user/register","/user/save","/home").permitAll()
		.antMatchers("/user/login").permitAll()
		.antMatchers("/emp").hasAuthority("EMPLOYEE")
		.antMatchers("/admin").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/user/login") //to show login page
		.loginProcessingUrl("/login") //todo login check
		.defaultSuccessUrl("/common",true) //login success
		.failureUrl("/user/login?error") //on login failed
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //URL for Logout
		.logoutSuccessUrl("/user/login?logout") // goto login page after logout
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied")
		
		;
		
	}
}
