package com.base.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by smalldeng on  july
 */
@Configuration
public class ShiroConfiguration {
    public final Logger logger= LoggerFactory.getLogger(getClass());

    @Bean
    UserRealm userRealm(){
        return new UserRealm();
    }
    @Bean
    DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm());
        manager.setSessionManager(sessionManager());
        return manager;
    }

    /**
     *
     * @param securityManager
     * @return
     */
    @Bean(name="shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //默认跳转到登陆页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登陆成功后的页面
        shiroFilterFactoryBean.setSuccessUrl("/#/main");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //自定义过滤器
        Map<String,Filter> filterMap=new LinkedHashMap<>();
        MyPassThruAuthenticationFilter authFilter = new MyPassThruAuthenticationFilter();
        filterMap.put("authc", authFilter);
        shiroFilterFactoryBean.setFilters(filterMap);
        //权限控制map
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        //filterChainDefinitionMap.put("/logout", "logout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }
    /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
}