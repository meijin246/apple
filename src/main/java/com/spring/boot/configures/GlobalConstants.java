package com.spring.boot.configures;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.cell")
public class GlobalConstants {
    private Integer sum;
    private String data;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
        System.out.println(sum);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        System.out.println(data);
    }
}
