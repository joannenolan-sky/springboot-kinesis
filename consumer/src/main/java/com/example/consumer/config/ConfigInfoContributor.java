package com.example.consumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigInfoContributor implements InfoContributor {

    BuildProperties buildProperties;

    @Value("${DEPLOYMENT_ENVIRONMENT:local}")
    private String deploymentEnvironment;

    public ConfigInfoContributor(@Autowired(required = false) BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("environment", deploymentEnvironment);
    }
}
