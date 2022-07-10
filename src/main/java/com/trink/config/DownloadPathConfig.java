package com.trink.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class DownloadPathConfig {

    @Value("${spring.download.path}")
    private String path;

}
