package com.spring.boot.configures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class GlobalCongfigs {

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("springDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
/*
    @Bean
    @Qualifier("jdbcTemplate")
    public JdbcTemplate dataTemplate(@Qualifier("springDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
*/
}
