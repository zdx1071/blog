package com.gordon.blog.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * /* @Description shiro配置类
 * 1.LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类
 * ，负责org.apache
 * .shiro.util.Initializable类型bean的生命周期的，初始化和销毁。主要是AuthorizingRealm类的子类
 * ，以及EhCacheManager类。
 * 2.HashedCredentialsMatcher，这个类是为了对密码进行编码的，防止密码在数据库里明码保存，当然在登陆认证的生活
 * ，这个类也负责对form里输入的密码进行编码。
 * 3.ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
 * 4.EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一
 * 次 数 据 库 。 5.SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
 * 6.ShiroFilterFactoryBean
 * ，是个factorybean，为了生成ShiroFilter。它主要保持了三项数据，securityManager
 * ，filters，filterChainDefinitionManager。
 * 7.DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
 * 8.AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
 * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
 */
/**
 * Created by gordon.zhang on 2018/2/10.
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置登录的url和登录成功的url
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/userspace/u");
        //用户访问未对其授权的资源时,所显示的连接
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //配置访问权限
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/userspace/*", "user");
        filterChainDefinitionMap.put("/admin", "user");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/blogs", "anon");


        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/admin*", "authc");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * thymeleaf中使用shiro标签
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    //配置自定义的权限登录器
    @Bean(name = "myShiroRealm")
    public MyShiroRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(matcher);
        return myShiroRealm;
    }


    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm) {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myShiroRealm);
        //注入记住我管理器;
        manager.setRememberMeManager(rememberMeManager());
        return manager;
    }

    //配置自定义的密码比较器
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * cookie对象;
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }



}