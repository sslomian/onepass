package pl.sscode.onepass.repository.api.config;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by sscode on 2017-06-15.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan
@EnableJpaRepositories(basePackages = "pl.sscode.onepass.repository")
@PropertySource("classpath:application.properties")
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("pl.sscode.onepass.repository");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(getJpaProperties());

        return factoryBean;
    }

    @Bean
    public Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.put(Environment.HBM2DDL_AUTO, "create");
        properties.put("hibernate.archive.autodetection", "class,hbm");
        properties.put("hibernate.show_sql", "false");
        properties.put(Environment.DIALECT, org.hibernate.dialect.H2Dialect.class.getCanonicalName());
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).setName("integration-loopData")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        EntityManagerFactory entityManagerFactoryBean = entityManagerFactory().getObject();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean);
        transactionManager.setJpaDialect(new HibernateJpaDialect());
        return transactionManager;
    }
}
