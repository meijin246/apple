package com.spring.boot.configures;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ConfigurationProperties(prefix = "spring.mybatis.config")
public class MybatisConfigs {
    private String basePackage="com.spring.boot.dao.mapper";
    private String aliasPackage="com.spring.boot.models";
    private String mapperLocations="classpath:mybatis/*Mapper.xml";
    private final String sessionBeanName = "oneSessionFactory";

    @Bean(name = sessionBeanName)
//    @Qualifier(sessionBeanName)
    @ConditionalOnMissingBean
    public SqlSessionFactoryBean mybatisSessionFactory(@Qualifier("springDataSource")DataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage(aliasPackage);
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            factoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        } catch (IOException e) {
        }
        return factoryBean;
    }

    @Bean
    @Qualifier("myabtisMapperScanner")
    public MapperScannerConfigurer mybatisMapperConfigure(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage(basePackage);
        configurer.setSqlSessionFactoryBeanName(sessionBeanName);
        return configurer;
    }
}
