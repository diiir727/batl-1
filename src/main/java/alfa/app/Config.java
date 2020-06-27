package alfa.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public void ssl() {
        System.setProperty("javax.net.ssl.keyStore","/home/diiir/asas/keystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword","123456]");
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");    }

}
