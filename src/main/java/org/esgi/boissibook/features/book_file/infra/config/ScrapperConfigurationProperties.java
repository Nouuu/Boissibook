package org.esgi.boissibook.features.book_file.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "scrapper")
public class ScrapperConfigurationProperties {
    private String scrapperApiUrl;

    public String getScrapperApiUrl() {
        return scrapperApiUrl;
    }

    public void setScrapperApiUrl(String scrapperApiUrl) {
        this.scrapperApiUrl = scrapperApiUrl;
    }
}
