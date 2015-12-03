package net.bondar.web.configuration;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Created by Azeral on 24.11.2015.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"net.bondar.web.configuration"})
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("net.bondar.web.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl("jdbc:h2:mem:dataSource");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

//    @Bean
//    public DataSource dataSource() {
//        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        EmbeddedDatabase db = builder
//                .setType(EmbeddedDatabaseType.H2) //.H2 or .DERBY
//                .build();
//        return db;
//    }

    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.max_fetch_depth", environment.getRequiredProperty("hibernate.max_fetch_depth"));
//        properties.put("hibernate.jdbc.fetch_size", environment.getRequiredProperty("hibernate.jdbc.fetch_size"));
//        properties.put("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager htxManager = new HibernateTransactionManager();
        htxManager.setSessionFactory(sessionFactory);
        return htxManager;
    }
}
