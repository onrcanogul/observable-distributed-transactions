package com.starter.dtxspring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;

@ConfigurationProperties(prefix = "dtx")
public class DtxProperties {

    private boolean enabled = true;

    /** fallback: spring.application.name de okunabilir ama burada basit tutuyorum */
    private String serviceName = "unknown-service";

    private int inboundFilterOrder = Ordered.HIGHEST_PRECEDENCE + 10;

    private final Http http = new Http();
    private final Collector collector = new Collector();

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public int getInboundFilterOrder() { return inboundFilterOrder; }
    public void setInboundFilterOrder(int inboundFilterOrder) { this.inboundFilterOrder = inboundFilterOrder; }

    public Http getHttp() { return http; }
    public Collector getCollector() { return collector; }

    public static class Http {
        private boolean restTemplateEnabled = true;
        private boolean webClientEnabled = true;

        public boolean isRestTemplateEnabled() { return restTemplateEnabled; }
        public void setRestTemplateEnabled(boolean restTemplateEnabled) { this.restTemplateEnabled = restTemplateEnabled; }

        public boolean isWebClientEnabled() { return webClientEnabled; }
        public void setWebClientEnabled(boolean webClientEnabled) { this.webClientEnabled = webClientEnabled; }
    }

    public static class Collector {
        private boolean enabled = true;
        private String endpoint = "http://localhost:9090";

        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }

        public String getEndpoint() { return endpoint; }
        public void setEndpoint(String endpoint) { this.endpoint = endpoint; }
    }
}
