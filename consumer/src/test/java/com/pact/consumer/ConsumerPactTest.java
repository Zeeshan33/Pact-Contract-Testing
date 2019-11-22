package com.pact.consumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerPactTest {  //extends ConsumerPactBaseTest {

    @Autowired
    RestTemplate restTemplate;

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("GetOrderProvider",this);

    @Pact(provider = "GetOrderProvider", consumer="OrderController")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("content-Type", "application/json");
        DslPart results = new PactDslJsonBody()
                .stringType("orderId","123")
                .asBody();

        return builder
                .given("200-OK")
                .uponReceiving("Get Orders")
                .path("/pact")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(results)
                .toPact();
    }

    @Pact(provider = "GetOrderProvider", consumer="OrderController")
    public RequestResponsePact getPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("content-Type", "application/json");
        DslPart results = new PactDslJsonBody()
                .stringType("orderId","123")
                .asBody();

        return builder
                .given("201-Created")
                .uponReceiving("Get Orders")
                .path("/pact/create")
                .method("GET")
                .willRespondWith()
                .status(201)
                .toPact();
    }

    @Test
    @PactVerification(fragment = "createPact")
    public void doTest() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>("content-Type", headers);

//      System.setProperty("pact.rootDir","../json");
        ResponseEntity<?> response = restTemplate.getForEntity(mockProvider.getUrl()+"/pact", Map.class, entity);
        Map order = (Map) response.getBody();
        Assert.assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
        Assert.assertEquals(order.get("orderId"), "123");
    }

    @Test
    @PactVerification(fragment = "getPact")
    public void doTest2() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>("content-Type", headers);

//      System.setProperty("pact.rootDir","../json");
        ResponseEntity<?> response = restTemplate.getForEntity(mockProvider.getUrl()+"/pact/create", Map.class, entity);

        Assert.assertEquals(response.getStatusCodeValue(), HttpStatus.CREATED.value());
    }

//    @Test
//    public void verifyJson() throws IOException {
//        PactJsonObject pactJsonObject = getPactJson("requests/request.json");
//        Interaction interaction = getInteractionByProviderState(pactJsonObject.getInteractions(), "201-Created");
//
//        Assert.assertEquals(HttpStatus.CREATED.value(), HttpStatus.CREATED.value());
//    }
}
