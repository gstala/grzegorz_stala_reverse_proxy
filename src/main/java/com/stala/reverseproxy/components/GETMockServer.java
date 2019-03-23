package com.stala.reverseproxy.components;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Component
public class GETMockServer {

    private WireMockServer wireMockServer;
    private final int port = 8081;

    @PostConstruct
    public void startServer() {
        wireMockServer = new WireMockServer(wireMockConfig().port(port));
        wireMockServer.start();

        WireMock wireMock = new WireMock(port);
        wireMock.register(get(urlEqualTo("/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Test GET")));
    }

    @PreDestroy
    public void stopServer() {
        wireMockServer.stop();
    }
}
