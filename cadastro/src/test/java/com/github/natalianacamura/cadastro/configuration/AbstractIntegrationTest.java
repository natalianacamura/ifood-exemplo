package com.github.natalianacamura.cadastro.configuration;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static AppPostgresqlContainer postgres = AppPostgresqlContainer.getInstance();

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            configurePostgresSystemProperties(applicationContext);
        }

        private void configurePostgresSystemProperties(ConfigurableApplicationContext applicationContext) {
        }
    }
}
