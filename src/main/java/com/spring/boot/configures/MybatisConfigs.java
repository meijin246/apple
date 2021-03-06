package com.spring.boot.configures;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class MybatisConfigs {
    private String basePackage="com.spring.boot.dao.mapper";
    private String aliasPackage="com.spring.boot.models";
    private String mapperLocations="classpath:mybatis/*Mapper.xml";

    @Bean("oneSessionFactory")
    @Order(2)
    public SqlSessionFactoryBean mybatisSessionFactory(@Qualifier("springDataSource")DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage(aliasPackage);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        return factoryBean;
    }

    @Bean("myabtisMapperScanner")
    @Order(3)
    public MapperScannerConfigurer mybatisMapperConfigure(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage(basePackage);
        configurer.setSqlSessionFactoryBeanName("oneSessionFactory");
        return configurer;
    }
}
