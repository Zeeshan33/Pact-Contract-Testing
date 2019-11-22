package com.pact.provider.controller;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.pact.provider.ProviderApplication;
import com.pact.provider.ProviderApplicationTests;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@Provider("GetOrderProvider")
@RunWith(PactRunner.class)
@PactBroker(host = "localhost", port = "8081")
public class PocControllerTest extends ProviderApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(PocControllerTest.class);
    private static final String ORDER_ID = "123";
    private static final String STATE = "200-OK";
    private static final String STATE2 = "201-Created";

    private static ConfigurableWebApplicationContext application;

    @BeforeClass
    public static void start() {
        application = (ConfigurableWebApplicationContext)
                SpringApplication.run(ProviderApplication.class);
    }

    @TestTarget
    public final Target target = new HttpTarget("http", "localhost", 8110, "/");

    @State(STATE)
    public void testGetOrders() {
        LOGGER.info(ORDER_ID);
    }


    @State(STATE2)
    public void testCreateOrders() {
        LOGGER.info(ORDER_ID);
    }
}
