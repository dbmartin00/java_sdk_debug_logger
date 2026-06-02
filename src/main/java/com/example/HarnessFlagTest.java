package com.example;

import io.split.client.SplitClient;
import io.split.client.SplitFactory;
import io.split.client.SplitClientConfig;
import io.split.client.SplitFactoryBuilder;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HarnessFlagTest {

    private static final Logger LOG =
            LoggerFactory.getLogger(HarnessFlagTest.class);

    public static void main(String[] args) {

        String sdkKey = System.getenv("HARNESS_API_KEY");

        if (sdkKey == null || sdkKey.isBlank()) {
            LOG.error("HARNESS_API_KEY environment variable is not set.");
            System.exit(1);
        }

        SplitFactory factory = null;

        try {
            LOG.info("Initializing Harness FME SDK...");

            SplitClientConfig config = SplitClientConfig.builder()
                    .setBlockUntilReadyTimeout(10000)
                    .build();

            factory = SplitFactoryBuilder.build(sdkKey, config);

            SplitClient client = factory.client();

            LOG.info("Waiting for SDK readiness...");

            client.blockUntilReady();

            LOG.info("SDK is ready.");

            String key = "demo-user";

            String treatment = client.getTreatment(key, "logging");

            LOG.info("Flag 'logging' treatment = {}", treatment);

            if ("on".equalsIgnoreCase(treatment)) {
                LOG.info("logging is ON. Exiting immediately.");
            } else {
                LOG.info("logging is OFF. Sleeping 600 seconds...");
                TimeUnit.SECONDS.sleep(600);
                LOG.info("Done sleeping.");
            }

        } catch (Exception e) {
            LOG.error("Application failed", e);
        } finally {
            if (factory != null) {
                LOG.info("Destroying SDK...");
                factory.destroy();
            }
        }

        LOG.info("Program exiting.");
    }
}
