package br.senai.sp.informatica.senaipatrimonio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.senai.sp.informatica.senaipatrimonio.filter.JwtFilter;
import br.senai.sp.informatica.senaipatrimonio.utils.JwtUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtFilter jwtFilter;
	
	/**
	 * Configurando a autenticação do webservice
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)	//configura a não utilização sessão
			.and()
			
			.authorizeRequests()
				.antMatchers("/rest/auth/**").permitAll() //permite o livre acesso ao endpoint de geração do token
				.antMatchers("/rest/**").authenticated() //permite o acesso a todos os outros endpoints aos usuarios atenticados (que possuem o token)
			
			.and()
		
				//faz a alterção dos modulos de seguranção do SpringSecuity
				.csrf().disable()
				.cors()
			;
	
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
