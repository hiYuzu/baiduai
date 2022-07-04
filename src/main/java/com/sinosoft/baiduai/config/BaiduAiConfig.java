package com.sinosoft.baiduai.config;

import com.baidu.aip.ocr.AipOcr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2022/5/16 17:46
 */
@Configuration
public class BaiduAiConfig {
    @Value("${baidu.appId}")
    public String appId;
    @Value("${baidu.apiKey}")
    public String apiKey;
    @Value("${baidu.secretKey}")
    public String secretKey;


    @Bean
    public AipOcr getAipOcr() {
        AipOcr client = new AipOcr(appId, apiKey, secretKey);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }
}
