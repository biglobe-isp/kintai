package jp.co.esumit.kintai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("app.csv")
public class AppProperties {
    private String kintaiDataCsv;
}
