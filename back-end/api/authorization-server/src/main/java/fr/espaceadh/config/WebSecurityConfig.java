package fr.espaceadh.config;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private Environment env;
    
  //  @Autowired
  //  private DataSource dataSource;
    
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        logger.info(" --> ######################### globalUserDetails");

        StringBuilder queryAuth = new StringBuilder();
            queryAuth.append("  SELECT username, password, enabled ");
            queryAuth.append("   FROM users  ");
            queryAuth.append("   WHERE username = ?  ");
            
         StringBuilder queryAuthority = new StringBuilder();
            queryAuthority.append(" SELECT username, authority as role ");
            queryAuthority.append("   FROM authorities  ");
            queryAuthority.append("   WHERE username = ? ");           
            
        
        auth.jdbcAuthentication().dataSource(dataSource())
            .usersByUsernameQuery(queryAuth.toString())
                .authoritiesByUsernameQuery(queryAuthority.toString())
        .passwordEncoder(new BCryptPasswordEncoder());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          

// @formatter:off
	/** auth.inMemoryAuthentication()
	  .withUser("john").password(passwordEncoder.encode("123")).roles("USER").and()
	  .withUser("tom").password(passwordEncoder.encode("111")).roles("ADMIN").and()
	  .withUser("user1").password(passwordEncoder.encode("pass")).roles("USER").and()
	  .withUser("admin").password(passwordEncoder.encode("nimda")).roles("ADMIN");
          * 
          * **/
    }// @formatter:on

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
		http.authorizeRequests()
                /** .antMatchers("/login").permitAll()
		.antMatchers("/oauth/token/revokeById/**").permitAll()
		.antMatchers("/tokens/**").permitAll()
                .antMatchers("/authentification").permitAll()        **/
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().csrf().disable();
		// @formatter:on
    }
    
    
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
}

}
