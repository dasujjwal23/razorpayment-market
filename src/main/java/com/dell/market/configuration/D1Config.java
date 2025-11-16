package com.dell.market.configuration;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@EnableJpaRepositories(
  basePackages = {"com.dell.market.repository.db1"},
  entityManagerFactoryRef = "studentEntityManagerFactory",
  transactionManagerRef = "studentTransactionManager"
)
@EnableTransactionManagement
@Configuration
public class D1Config {
	
	  @Autowired
      private Environment environment;

	  @Primary
	  @Bean
	//  @ConfigurationProperties(prefix = "db1.datasource")
	  public DataSource studentDataSource() {
		    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setUrl(environment.getProperty("db1.datasource.url"));
	        dataSource.setDriverClassName(environment.getProperty("db1.datasource.driver-class-name"));
	       // dataSource.setUsername(environment.getProperty("second.datasource.username"));
	        //dataSource.setPassword(environment.getProperty("second.datasource.password"));

	        return dataSource;
	    //return DataSourceBuilder.create().build();
	  }
	  
	  @Primary
	  @Bean
	  public LocalContainerEntityManagerFactoryBean studentEntityManagerFactory(
	      EntityManagerFactoryBuilder builder,
	      @Qualifier("studentDataSource") DataSource dataSource) {
		  
	      LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
          bean.setDataSource(studentDataSource());
          bean.setPackagesToScan("com.dell.market.entity.db1");

          HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
          bean.setJpaVendorAdapter(adapter);
		  Map<String,Object> map=new HashMap<>();
		  map.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		  map.put("hibernate.show_sql","true");
		  map.put("hibernate.hbm2ddl.auto","update");
		 // map.put("hibernate.h2.console.enabled",true);
		 // map.put("hibernate.h2.console.path","/h2-console");
		  
		  bean.setJpaPropertyMap(map);
		  
		  bean.setPersistenceUnitName("db1");
		  return bean;
/*		  
	    return builder
	        .dataSource(dataSource)
	        .properties(map)
	        .packages("com.dell.market.entity.db1")
	        .persistenceUnit("db1")
	        .build();*/
	  }

	  @Primary
	  @Bean
	  public PlatformTransactionManager studentTransactionManager(
	      @Qualifier("studentEntityManagerFactory") EntityManagerFactory localEntityManagerFactory) {
	    return new JpaTransactionManager(localEntityManagerFactory);
	  }

}
