package com.neokod.postman.configuration;

import com.neokod.postman.export.PostmanFileNamingHelper;
import com.neokod.postman.export.item.PostmanItemWriter;
import com.neokod.postman.export.item.SeparatedItemWriter;
import com.neokod.postman.properties.PostmanExportProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostmanExportConfiguration {

    @Bean
    public PostmanItemWriter postmanItemWriter(PostmanExportProperties exportProperties ,
                                               PostmanFileNamingHelper postmanFileNamingHelper) {
        return new SeparatedItemWriter(postmanFileNamingHelper, exportProperties);
    }
}
