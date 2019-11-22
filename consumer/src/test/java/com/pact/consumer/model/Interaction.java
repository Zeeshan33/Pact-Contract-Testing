package com.pact.consumer.model;

import javax.print.attribute.standard.RequestingUserName;
import java.util.List;

public class Interaction {
    private String description;
    private Request request;
    private Response response;
    private List<ProviderStates> providerStates;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        request = request;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        response = response;
    }

    public List<ProviderStates> getProviderStates() {
        return providerStates;
    }

    public void setProviderStates(List<ProviderStates> providerStates) {
        this.providerStates = providerStates;
    }
}
