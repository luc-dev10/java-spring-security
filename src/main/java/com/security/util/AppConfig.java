package com.security.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.security")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig {

    // setup logging
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private Helper helper;

    // set variable to hold properties
    @Autowired
    private Environment env;

    // define bean for security datasource
    @Bean
    public DataSource securityDataSource() {

        // create connection pool
        ComboPooledDataSource securityDatasource = new ComboPooledDataSource();

        // set the jdbc driver getClass()
        try {
            securityDatasource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        // log connection props
        logger.info(String.format(">>> jdbc.url= %s", env.getProperty("jdbc.url")));
        logger.info(String.format(">>> jdbc.user= %s", env.getProperty("jdbc.user")));

        // set database connection props
        securityDatasource.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDatasource.setUser(env.getProperty("jdbc.user"));
        securityDatasource.setPassword(env.getProperty("jdbc.password"));

        // set connection pool
        String propsPoolSize = env.getProperty("connection.pool.initialPoolSize");
        String minPoolSize = env.getProperty("connection.pool.minPoolSize");
        String maxPoolSize = env.getProperty("connection.pool.maxPoolSize");
        String maxIdleTime = env.getProperty("connection.pool.maxIdleTime");

        securityDatasource.setInitialPoolSize(helper.getPropertyInInteger(propsPoolSize));
        securityDatasource.setMinPoolSize(helper.getPropertyInInteger(minPoolSize));
        securityDatasource.setMaxPoolSize(helper.getPropertyInInteger(maxPoolSize));
        securityDatasource.setMaxIdleTime(helper.getPropertyInInteger(maxIdleTime));

        return securityDatasource;

    }

    // define bean for view resolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
