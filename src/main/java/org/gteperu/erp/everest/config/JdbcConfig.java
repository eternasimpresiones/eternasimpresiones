package org.gteperu.erp.everest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Value("classpath:schema.sql")
    private Resource schemaScript;

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
       initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    @Value("${spring.datasource.driverClassName}")
    private String DB_DRIVER;
    @Value("${spring.datasource.username}")
    private String DB_USERNAME;
    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;
    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = null;
        try {
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(DB_DRIVER);
            dataSource.setUrl(DB_URL);
            dataSource.setUsername(DB_USERNAME);
            dataSource.setPassword(DB_PASSWORD);
        } catch (Exception e) {
            e.getMessage();
        }
        return dataSource;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
       populator.addScript(schemaScript);
        return populator;
    }
    
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
    

}
