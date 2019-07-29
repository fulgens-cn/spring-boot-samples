package com.example.simple.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@Validated
@Component
@ConfigurationProperties(prefix = "com.example.acme")
public class AcmeProperties {

    @NotNull
    private boolean enabled;

    @NotNull
    private InetAddress remoteAddress;

    @NotNull
    private final Security security = new Security();

    @Setter
    @Getter
    public static class Security {

        @NotEmpty
        private String username;

        @NotEmpty
        private String password;

        @NotNull
        private List<String> roles = new ArrayList<String>(Collections.singleton("USER"));

    }
}
