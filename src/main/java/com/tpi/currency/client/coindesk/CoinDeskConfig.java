package com.tpi.currency.client.coindesk;

import com.tpi.currency.client.base.ClientConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "clients.coindesk", ignoreInvalidFields = true)
public class CoinDeskConfig extends ClientConfig {}
