package com.ptyt.haiguan.kits.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptyt.haiguan.kits.handler.LoginSuccessHandler;
import com.ptyt.haiguan.kits.service.KitsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author yq
 * @date 2019/9/9
 * @description:SpringSecurity配置
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private KitsUserService kitsUserService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Lazy
    @Autowired
    private AuthorizationServerTokenServices defaultAuthorizationServerTokenServices;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(kitsUserService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin()
                .loginPage("/kits/login")
                .loginProcessingUrl("/token/form")
                .and()
                .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
    }

    /**
     * 不拦截静态资源
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**","/js/**");
    }

    /**
     * 需要配置这个支持password模式
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationSuccessHandler mobileLoginSuccessHandler() {
        return LoginSuccessHandler.builder()
                .objectMapper(objectMapper)
                .clientDetailsService(clientDetailsService)
                .passwordEncoder(passwordEncoder())
                .defaultAuthorizationServerTokenServices(defaultAuthorizationServerTokenServices).build();
    }

    /**
     * springboot2.0需要添加密码加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        };
    }
}
