package com.example.jetty.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHome() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/", String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Hello World");
    }

    @Test
    public void testCompression() throws IOException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept-Encoding", "gzip");
        HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange("/", HttpMethod.GET, requestEntity, byte[].class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        try (GZIPInputStream is = new GZIPInputStream(new ByteArrayInputStream(responseEntity.getBody()))) {
            assertThat(StreamUtils.copyToString(is, StandardCharsets.UTF_8)).isEqualTo("Hello World");
        }
    }
}