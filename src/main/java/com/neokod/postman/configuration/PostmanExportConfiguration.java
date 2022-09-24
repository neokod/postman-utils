package com.neokod.postman.configuration;

import com.neokod.postman.export.PostmanFileNamingManager;
import com.neokod.postman.export.PostmanItemWriter;
import com.neokod.postman.export.SeparatedWriter;
import com.neokod.postman.properties.PostmanExportProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostmanExportConfiguration {

    @Bean
    public PostmanItemWriter postmanItemWriter(PostmanExportProperties exportProperties ,
                                               PostmanFileNamingManager postmanFileNamingManager) {
        return new SeparatedWriter(postmanFileNamingManager, exportProperties);
    }
}
