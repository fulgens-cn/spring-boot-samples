package com.example.web.xml.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户实体类
 *
 * @author fulgens
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "user")
public class User {

    @JacksonXmlProperty(localName = "id")
    private String id;

    @JacksonXmlProperty(localName = "username")
    private String username;

    @JacksonXmlProperty(localName = "age")
    private Integer age;

    @JacksonXmlElementWrapper(localName = "addressList")
    @JacksonXmlProperty(localName = "address")
    private List<Address> addressList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JacksonXmlRootElement(localName = "address")
    public static class Address {

        @JacksonXmlProperty(localName = "country")
        private String country;

        @JacksonXmlProperty(localName = "province")
        private String province;

        @JacksonXmlProperty(localName = "city")
        private String city;
    }
}
