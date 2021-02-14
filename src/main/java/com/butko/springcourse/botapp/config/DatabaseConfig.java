//package com.butko.springcourse.botapp.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "primaryEntityManagerFactory",
///       transactionManagerRef = "primaryTransactionManager",
//        basePackages = {"main.java.com.butko.springcourse.botapp.repository"})
//public class DatabaseConfig {
//
//    @Primary
//    @Bean(name = "primaryDataSourceProperties")
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean(name = "primaryHikariConfig")
//    @ConfigurationProperties(prefix = "spring.datasource.hikari")
//    public HikariConfig hikariConfig() {
//        return new HikariConfig();
//    }
//
//    @Primary
//    @Bean(name = "primaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.configuration")
//    public DataSource primaryDataSource(
//            @Qualifier("primaryDataSourceProperties") DataSourceProperties dataSourceProperties,
//            @Qualifier("primaryHikariConfig") HikariConfig hikari) {
//        hikari.setDriverClassName(dataSourceProperties.getDriverClassName());
//        hikari.setJdbcUrl(dataSourceProperties.getUrl());
//        hikari.setUsername(dataSourceProperties.getUsername());
//        hikari.setPassword(dataSourceProperties.getPassword());
//        return new HikariDataSource(hikari);
//    }
//
//    @Primary
//    @Bean(name = "primaryEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean
//    entityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("primaryDataSource") DataSource dataSource) {
//
//        return builder
//                .dataSource(dataSource)
//                .packages("botapp.retail.repository", "botapp.retail.staffimport",
//                        "botapp.retail.printed")
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "primaryTransactionManager")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory managerFactory) {
//        return new JpaTransactionManager(managerFactory);
//    }
//}
