package com.laughingather.gulimall.admin.config;

import com.laughingather.gulimall.admin.filter.UserinfoFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security自定义配置
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 09:58:48
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf()
                .disable()
                // 基于token，所以不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET, "/swagger-ui/**")
                .permitAll()
                // 对登录注册要允许匿名访问
                .antMatchers("/openapi/admin/**")
                .permitAll()
                // 跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                // 测试时全部放开访问
//                .antMatchers("/**")
//                .permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest()
                .authenticated();

        // 添加 filter
        httpSecurity.addFilterBefore(userinfoFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public UserinfoFilter userinfoFilter() {
        return new UserinfoFilter();
    }

}
