package br.com.brn.feedrapi.adapters.configurations;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class HibernateConfiguration {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUser;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       MultiTenantConnectionProvider multiTenantConnectionProvider,
                                                                       CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        ;
        em.setPackagesToScan("br.com");
        em.setJpaVendorAdapter(this.jpaVendorAdapter());
        Map<String, Object> jpaPropsMap = new HashMap<>();
        jpaPropsMap.put(Environment.JPA_JDBC_URL, datasourceUrl);
        jpaPropsMap.put(Environment.JPA_JDBC_USER, datasourceUser);
        jpaPropsMap.put(Environment.JPA_JDBC_PASSWORD, datasourcePassword);
        jpaPropsMap.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        jpaPropsMap.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        jpaPropsMap.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
        jpaPropsMap.put(Environment.CONNECTION_HANDLING, "DELAYED_ACQUISITION_AND_RELEASE_AFTER_STATEMENT");
        jpaPropsMap.put(Environment.POOL_SIZE, 50);
        em.setJpaPropertyMap(jpaPropsMap);
        return em;
    }

}
