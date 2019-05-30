package com.liang.rent.auth;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;


@Configuration
public class ShiroConfiguration {
	
	/*
		<bean id="userRealm" class="org.gec.smart.auth.UserRealm"/>
	*/
	@Bean
	public RentManagerRealm RentManagerRealm() {
		return new RentManagerRealm();
	}
	
	/*
		<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
			<property name="securityManager" ref="securityManager"/>
		</bean>
	*/
	@Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //指定认证失败后跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/rent-manager-login.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/rent-manager-rbm.html");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/error.html");
        //配置资源的访问规则
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断，authc:所有url都必须认证通过才可以访问， anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/rent-manager-login.html", "anon");
        filterChainDefinitionMap.put("/RentManagerCon/RentManagerLogin.do", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
//        filterChainDefinitionMap.put("/authority.html", "perms[add]");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

	/*
		<bean id="securityManager" class="org.apache.shiro.mgt.SecurityManager">
			<property name="realm" value="userRealm">
		</bean>
	*/
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(RentManagerRealm());
        return securityManager;
    } 

}
