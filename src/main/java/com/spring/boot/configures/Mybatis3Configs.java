package com.spring.boot.configures;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class Mybatis3Configs {
    private String basePackage="com.spring.boot.dao.mapper3";
    private String aliasPackage="com.spring.boot.models3";
    private String mapperLocations="classpath:mybatis3/*Mapper.xml";

    @Bean("twoSessionFactory")
    @Order(2)
    public SqlSessionFactoryBean mybatisSessionFactory(@Qualifier("mybatisDataSource3")DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage(aliasPackage);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        return factoryBean;
    }

    @Bean("myabtis3MapperScanner")
    @Order(3)
    public MapperScannerConfigurer mybatisMapperConfigure(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage(basePackage);
        configurer.setSqlSessionFactoryBeanName("twoSessionFactory");
        return configurer;
    }
}
