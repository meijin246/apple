package com.spring.boot.configures;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
//@Import(SpringDataSource.class)
@ConfigurationProperties(prefix = "spring.mybatis.config")
public class MybatisConfigs {
    private String basePackage="com.spring.boot.dao.mapper";
    private String aliasPackage="classpath:mybatis/*Mapper.xml";
    private String mapperLocations="com.spring.boot.models";
    private final String sessionBeanName = "oneSessionFactory";

    @Bean(name = sessionBeanName)
    @Qualifier(sessionBeanName)
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
    @DependsOn(sessionBeanName)
    public MapperScannerConfigurer mybatisMapperConfigure(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage(basePackage);
        configurer.setSqlSessionFactoryBeanName(sessionBeanName);
        return configurer;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getAliasPackage() {
        return aliasPackage;
    }

    public void setAliasPackage(String aliasPackage) {
        this.aliasPackage = aliasPackage;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }
}
