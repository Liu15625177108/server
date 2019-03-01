package com.example.server.security.core.server;

import com.example.server.common.filter.CorsFilterConfig;
import com.example.server.security.MyUserDetailsService;
import com.example.server.security.core.handle.MyAuthenticationSuccessHandle;
import com.example.server.security.core.smslogin.SmsAuthenticationFilterConfig;
import com.example.server.security.core.validatorcode.smscode.filter.SmsCodeFilterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsUtils;

/**
 * @ClassName ResourceServerConfig
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server
 * @Date 2019/2/21 22:53
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig  extends ResourceServerConfigurerAdapter {



    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private CorsFilterConfig corsFilterConfig;
    @Autowired
    private MyAuthenticationSuccessHandle myAuthenticationSuccessHandle;
    @Autowired
    private SmsCodeFilterConfig smsCodeFilterConfig;

    @Autowired
    private SmsAuthenticationFilterConfig smsAuthenticationFilterConfig;
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.apply(corsFilterConfig)
                .and()
                .apply(smsCodeFilterConfig)
                .and()
                .apply(smsAuthenticationFilterConfig)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .formLogin().permitAll()
                .loginPage("/server.html")
                .loginProcessingUrl("/authentication/form")
                .successHandler(myAuthenticationSuccessHandle)
                .and()
                .authorizeRequests()
                .antMatchers("/error","/user/signup","/social/user","/login.html","/authentication/form","/hello","/css/**","/js/**").permitAll()
                .antMatchers("/authentication/require","/code/image","/code/sms","/signout","/session/invalid","/oauth/**","/oauth/token").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable();

//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =http.authorizeRequests();
//        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();



    }
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(asList("*"));
//        configuration.setAllowedMethods(asList("HEAD",
//                "GET", "POST", "PUT", "DELETE", "PATCH"));
//        // setAllowCredentials(true) is important, otherwise:
//        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//        configuration.setAllowCredentials(true);
//        // setAllowedHeaders is important! Without it, OPTIONS preflight request
//        // will fail with 403 Invalid CORS request
//        configuration.setAllowedHeaders(asList("Authorization", "Cache-Control", "Content-Type"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
         DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
         daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
          daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
         return daoAuthenticationProvider;
    }
}
