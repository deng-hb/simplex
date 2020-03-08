package com.denghb.simplex.common.config;

import com.denghb.eorm.Eorm;
import com.denghb.eorm.impl.EormMySQLImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class EormConfig {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Bean
    public Eorm db() {
        return new EormMySQLImpl(jdbcTemplate);
    }
}
