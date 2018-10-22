package com.zkt.common.web.jwt;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.jwt
 *
 * @Author: Evangoe
 * @Description:
 * @Date:31/05/17
 * @Modified:
 */
@Component
@ConfigurationProperties(prefix = "audience")
public class Audience {
    private String clientId;
    private String base64Secret;
    private String name;
    private long expiresSecond;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExpiresSecond() {
        return expiresSecond;
    }

    public void setExpiresSecond(long expiresSecond) {
        this.expiresSecond = expiresSecond;
    }
}
