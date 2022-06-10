package com.dev.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.Objects;

@Deprecated
public class DatabaseConfig {

    // For research purpose only
    public MongoTemplate mongoTemplate( @Value("${mongo.db.uri}") String dbUri,
                                        @Value("${mongo.db.username-key}") String dbUsernameKey,
                                        @Value("${mongo.db.password-key}") String dbPasswordKey) {
        ConnectionString conn = new ConnectionString(dbUri);
        MongoClientSettings.Builder settings = MongoClientSettings.builder().applyConnectionString(conn);
        char[] dbPassword = dbPasswordKey.toCharArray();
        MongoCredential credential = MongoCredential.createCredential(dbUsernameKey, Objects.requireNonNull(conn.getDatabase()), dbPassword);
        settings.credential(credential);

        MongoClient mongoClient = MongoClients.create(settings.build());
        MongoDatabaseFactory mongoDbFactory = new SimpleMongoClientDatabaseFactory(mongoClient, conn.getDatabase());
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), new MongoMappingContext());
        converter.afterPropertiesSet();
        return new MongoTemplate(mongoDbFactory, converter);
    }

}
