package org.viodo.hadoop.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.viodo.hadoop.client.HdfsTemplate;

@Configuration
@Slf4j
public class HdfsConfig {

    @Value("${hdfs.defaultFS}")
    private String defaultHdfsUri;

    @Bean
    public HdfsTemplate getHbaseService() {
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        conf.set("fs.defaultFS", defaultHdfsUri);
        return new HdfsTemplate(conf, defaultHdfsUri);
    }
}
