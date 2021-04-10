package com.github.natalianacamura.cadastro.configuration;
import java.util.Objects;

import org.testcontainers.containers.PostgreSQLContainer;

public class AppPostgresqlContainer extends PostgreSQLContainer<AppPostgresqlContainer> {

    private static final String IMAGE_VERSION = "postgres:13.0";

    private static AppPostgresqlContainer container = null;


    private AppPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static AppPostgresqlContainer getInstance() {
        if (Objects.isNull(container)) {
            container = new AppPostgresqlContainer();
            container
                .withDatabaseName("cadastro")
                .withUsername("cadastro")
                .withPassword("cadastro")
                .start();
        }
        return container;
    }

    @Override
    public void stop() {}
}