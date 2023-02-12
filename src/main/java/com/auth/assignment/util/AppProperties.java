package com.auth.assignment.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "config")
@Component
public class AppProperties {

    private Authentication authentication = new Authentication();

    public Authentication getAuthentication() {
        return this.authentication;
    }

    public static class Authentication {
        private String secret = "U3ByaW5nIEJvb3QgaXMgYnVpbHQgb24gdGhlIHRvcCBvZiB0aGUgc3ByaW5nIGFuZCBjb250YWlucyBhbGwgdGhlIGZlYXR1cmVzIG9mIHNwcmluZy4gQW5kIGlzIGJlY29taW5nIGEgZmF2b3JpdGUgb2YgZGV2ZWxvcGVycyB0aGVzZSBkYXlzIGJlY2F1c2Ugb2YgaXRzIHJhcGlkIHByb2R1Y3Rpb24tcmVhZHkgZW52aXJvbm1lbnQgd2hpY2ggZW5hYmxlcyB0aGUgZGV2ZWxvcGVycyB0byBkaXJlY3RseSBmb2N1cyBvbiB0aGUgbG9naWMgaW5zdGVhZCBvZiBzdHJ1Z2dsaW5nIHdpdGggdGhlIGNvbmZpZ3VyYXRpb24gYW5kIHNldHVwLiBTcHJpbmcgQm9vdCBpcyBhIG1pY3Jvc2VydmljZS1iYXNlZCBmcmFtZXdvcmsgYW5kIG1ha2luZyBhIHByb2R1Y3Rpb24tcmVhZHkgYXBwbGljYXRpb24=";
        private String claimKey = "role";
        private long tokenValidityInSeconds = 3600;

        private String issuer = "Assignment";

        public String getSecret() {
            return secret;
        }

        public String getIssuer() {
            return issuer;
        }

        public String getClaimKey() {
            return claimKey;
        }

        public long getTokenValidityInSeconds() {
            return tokenValidityInSeconds;
        }
    }
}
