package com.untucapital.usuite.utg.untu_capital.configs;//package com.untucapital.usuite.utg.untu_capital.configs;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Component
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "untuCapitalEntityManagerFactory",
//        basePackages 	 = {"com.untucapital.usuite.utg.untu_capital.repository"},
//        transactionManagerRef = "untuCapitalTransactionManager"
//)
//public class UntuCapitalDAO {
//
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Value("${spring.datasource.driverClassName}")
//    private String driverClass;
//
//
//    @Primary
//    @Bean(name= "untuCapitalDataSource")
//    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setUrl(url);
//        ds.setUsername(username);
//        ds.setPassword(password);
//        ds.setDriverClassName(driverClass);
//        return ds;
//    }
//
//
//    @Primary
//    @Bean(name= "untuCapitalEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManager() {
//        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
//        bean.setDataSource(dataSource());
//        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        bean.setJpaVendorAdapter(adapter);
//        HashMap<String,Object> properties = new HashMap<String, Object>();
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//        bean.setJpaPropertyMap(properties);
//        bean.setPackagesToScan("com.untucapital.usuite.utg.untu_capital.model");
//        return bean;
//
//    }
//
//    @Primary
//    @Bean("untuCapitalTransactionManager")
//    public PlatformTransactionManager transactionManager(@Qualifier("untuCapitalEntityManagerFactory") EntityManagerFactory entityManagerFactory ) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//
//}
