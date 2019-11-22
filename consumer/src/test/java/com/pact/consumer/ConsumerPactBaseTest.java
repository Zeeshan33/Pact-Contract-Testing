package com.pact.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pact.consumer.model.Interaction;
import com.pact.consumer.model.PactJsonObject;
import com.pact.consumer.model.ProviderStates;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class ConsumerPactBaseTest {

    @Autowired
    ObjectMapper objectMapper;

    public PactJsonObject getPactJson(String path) throws IOException {
        PactJsonObject baseObject = objectMapper.readValue(this.getClass().getClassLoader()
                .getResourceAsStream(path), PactJsonObject.class);

        return baseObject;
    }

    public Interaction getInteractionByProviderState(List<Interaction> interactions, String providerState){
        for(Interaction interaction: interactions)
        {
            for(ProviderStates pactProviderState: interaction.getProviderStates())
            {
                if(pactProviderState.getName().equals(providerState))
                {
                    return interaction;
                }
            }
        }

        return null;
    }
}
