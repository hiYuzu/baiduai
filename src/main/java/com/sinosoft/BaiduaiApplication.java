package com.sinosoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2022/5/16 16:05
 */
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class BaiduaiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaiduaiApplication.class);
    }
}
