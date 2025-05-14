package com.example.notificationservice.config;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvLoader {
    static {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }
}