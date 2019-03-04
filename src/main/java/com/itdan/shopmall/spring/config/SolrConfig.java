package com.itdan.shopmall.spring.config;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置solr
 */
@Configuration
public class SolrConfig {

    @Value("${spring.data.solr.host}")
    private  String host;

    @Bean
    public HttpSolrServer solrServer(){
        return  new HttpSolrServer(host);
    }

}
