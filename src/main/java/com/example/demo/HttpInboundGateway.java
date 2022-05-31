package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.expression.ValueExpression;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class HttpInboundGateway {

    @Bean
    public IntegrationFlow inGate(MessageChannel sampleChannel) {
        return IntegrationFlows
                .from(Http.inboundGateway("/check/{var1}")
                                .replyChannel("sampleChannel")
                                .headerExpression("var", "#pathVariables.var1")
                )
                .headerFilter("accept-encoding", false)
                .log(LoggingHandler.Level.INFO, "Http.inboundGateway pathVariables-parsing", "headers.var")
                .handle(Http.outboundGateway("https://api.chucknorris.io/jokes/{random}")
                                .uriVariable("random", "headers.var")
                                .httpMethod(HttpMethod.GET)
                                .expectedResponseType(Joke.class)
                )
                .get();
    }
}