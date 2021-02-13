package com.butko.springcourse.botapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfig {

    @Value("${bot.token}")
    private String token;

    public String getToken() {
        return token;
    }
}
