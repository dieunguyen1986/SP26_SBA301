package edu.lms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cloudinary")
@Setter
@Getter
public class CloudinaryProperties {
    private String cloudName;
    private String apiKey;
    private String apiSecret;
}
