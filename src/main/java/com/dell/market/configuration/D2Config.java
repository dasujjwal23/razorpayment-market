package com.dell.market.configuration;

import java.util.ArrayList;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

//import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableMongoRepositories(
    basePackages = { "com.dell.market.repository.db2" },
    mongoTemplateRef = "secondaryMongoTemplate"
  )
public class D2Config {
	
	 @Autowired
     private Environment environment;
	
	@Primary
	@Bean(name = "secondaryProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.db2")
    public MongoProperties secondaryProperties() {
		MongoProperties prop=new MongoProperties();
		//prop.setHost(environment.getProperty("spring.data.mongodb.db2.host"));
		//prop.setPort(Integer.parseInt(environment.getProperty("spring.data.mongodb.db2.port")));
		//prop.setDatabase(environment.getProperty("spring.data.mongodb.db2.host"));
        return prop;
    }

    @Bean(name = "secondaryMongoClient")
    public MongoClient mongoClient(@Qualifier("secondaryProperties") MongoProperties mongoProperties) {

      // MongoCredential credential = MongoCredential
      //   .createCredential(mongoProperties.getUsername(), mongoProperties.getAuthenticationDatabase(), mongoProperties.getPassword());

        return MongoClients.create(MongoClientSettings.builder()
            .applyToClusterSettings(builder -> builder
            .hosts(Arrays.asList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
     //     .credential(credential)
          .build());
    }

    @Bean(name = "secondaryMongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(
       @Qualifier("secondaryMongoClient") MongoClient mongoClient, 
       @Qualifier("secondaryProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("secondaryMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }
}

