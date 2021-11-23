package com.meowu.nonotfound.portal.service.commons.config;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ConnectionPoolSettings;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
@RefreshScope
public class MongoConfig{

    @Value("${mongo.urls}")
    private String urls;

    @Value("${mongo.database}")
    private String database;

    @Value("${mongo.pool.minSize}")
    private Integer minSize;

    @Value("${mongo.pool.maxSize}")
    private Integer maxSize;

    @Value("${mongo.pool.maxWaitTime}")
    private Integer maxWaitTime;

    @Value("${mongo.pool.maxConnectionIdleTime}")
    private Integer maxConnectionIdleTime;

    @Value("${mongo.pool.maxConnectionLifeTime}")
    private Integer maxConnectionLifeTime;

    @RefreshScope
    @Bean
    public MongoClientSettings mongoClientSettings(){
        return MongoClientSettings.builder()
                                  .applyToConnectionPoolSettings(new Block<ConnectionPoolSettings.Builder>(){
                                      @Override
                                      public void apply(ConnectionPoolSettings.Builder builder){
                                          builder.applyConnectionString(new ConnectionString(urls))
                                                 .minSize(minSize)
                                                 .maxSize(maxSize)
                                                 .maxWaitTime(maxWaitTime, TimeUnit.SECONDS)
                                                 .maxConnectionIdleTime(maxConnectionIdleTime, TimeUnit.SECONDS)
                                                 .maxConnectionLifeTime(maxConnectionLifeTime, TimeUnit.SECONDS);
                                      }
                                  })
                                  .build();
    }

    @RefreshScope
    @Bean("myMongoClient")
    public MongoClient mongoClient(MongoClientSettings settings){
        return MongoClients.create(settings);
    }

    @RefreshScope
    @Bean
    public MongoTemplate mongoTemplate(@Qualifier("myMongoClient") MongoClient client){
        return new MongoTemplate(client, database);
    }
}