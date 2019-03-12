package com.mtgpeasant.configuration;

import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class OkHttpConfiguration {

    @Value("${proxy.host:}")
    private String proxyHost;

    @Value("${proxy.port:}")
    private Integer proxyPort;

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        if (proxyHost != null && !proxyHost.isEmpty() && proxyPort != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(proxyHost, proxyPort));
            okHttpClient.setProxy(proxy);
        }
        return okHttpClient;
    }
}
