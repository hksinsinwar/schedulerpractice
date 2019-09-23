package com.example.batchprocessing.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;

@Configuration
public class MongoDBConfig extends AbstractMongoConfiguration {
    private static Logger logger = LoggerFactory.getLogger(MongoDBConfig.class);
    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.host}")
    private String host;
    @Override
    protected String getDatabaseName() {
        return database;
    }
    @Override
    public MongoClient mongo() {
        MongoClientOptions.Builder mongoClientOptionsBuilder = MongoClientOptions.builder()
                .writeConcern(WriteConcern.ACKNOWLEDGED);
        return new MongoClient(new MongoClientURI(host, mongoClientOptionsBuilder));
    }
    
    
}