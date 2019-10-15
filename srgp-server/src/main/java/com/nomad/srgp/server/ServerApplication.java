package com.nomad.srgp.server;

import com.nomad.srgp.utility.SrgpUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;

/**
 * @author Shariful Islam
 */

@SpringBootApplication
@ComponentScan("com.nomad.srgp")
public class ServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    @Value("${info.version}")
    private String versionNo;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
//        System.setProperty("java.rmi.server.hostname","localhost");
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> logger.warn("Uncaught error in thread {}", t.getName(), e));
        SrgpUtils.logMemoryStatus("Starting dataacess-server application");
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean(name = "datasource")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("local.driver"));
        dataSource.setJdbcUrl(environment.getRequiredProperty("local.url"));
        dataSource.setUsername(environment.getRequiredProperty("local.user"));
        dataSource.setPassword(environment.getRequiredProperty("local.password"));
        dataSource.setAutoCommit(true);
        dataSource.setMaximumPoolSize(environment.getRequiredProperty("local.poolsize.max", Integer.class) * 2);
        return dataSource;
    }

    @Bean(name = "jdbc")
    public JdbcTemplate jdbcTemplate(@Qualifier("datasource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setFetchSize(environment.getRequiredProperty("local.fetchSize", Integer.class));
        return jdbcTemplate;
    }

    @Bean
    public LobHandler lobHandler() {
        return new DefaultLobHandler();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
