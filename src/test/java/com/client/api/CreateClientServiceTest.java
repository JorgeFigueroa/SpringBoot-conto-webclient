package com.client.api;

import static org.assertj.core.api.Assertions.assertThat;
import com.conto.services.ClientFabrickService;
import com.conto.services.ClientFabrickServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringRunner.class)
@RestClientTest(ClientFabrickServiceImpl.class)
public class CreateClientServiceTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private RestTemplate template;

    @Autowired
    private ClientFabrickService client;

    @Test
    public void testServiceCall() throws Exception {

        String mesaggeexpect = "{ \"status\" : \"KO\", \"errors\" : [{\"code\":\"REQ004\",\"description\":\"Invalid account identifier\",\"params\":\"\"}], \"payload\": {} }";

        this.server.expect(requestTo("http://localhost:9091/api/lettura_saldo/1453778"))
                .andRespond(withSuccess(mesaggeexpect, MediaType.APPLICATION_JSON));

    }




}
