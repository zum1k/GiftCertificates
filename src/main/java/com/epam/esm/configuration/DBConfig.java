package com.epam.esm.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.sql.DataSource;
import java.util.Locale;


@EnableWebMvc
@Configuration
public class DBConfig {
    @Bean //TODO in properties.file
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/gift_certificates");
        dataSource.setUsername("root");
        dataSource.setPassword("2584170");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean(name = "messageSource")
    public MessageSource getMessageResource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

        // Read i18n/messages_xxx.properties file.
        // For example: i18n/messages_en.properties

        messageResource.setBasename("classpath:resources/Resource Bundle'exception_messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        resolver.setCookieName("language");
        // 60 minutes

        resolver.setCookieMaxAge(60 * 60);
        return resolver;
    }

    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");
        interceptorRegistry.addInterceptor(localeInterceptor).addPathPatterns("/*");
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource source) {
        return new NamedParameterJdbcTemplate(source);
    }
}
