package alfa.infrastracture;

import alfa.domain.Terminal;
import alfa.domain.TerminalDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.time.Duration;
import java.util.Optional;

@Repository
public class TerminalDaoImpl implements TerminalDao {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final HttpClient httpClient = getRestTemplate();

    private ObjectMapper mapper = new ObjectMapper();
    private String alfaApiUrl;
    private String key;

    public TerminalDaoImpl(@Value("${alfa.api.url}") String alfaApiUrl, @Value("${alfa.api.secret}") String key) throws Exception {
        this.alfaApiUrl = alfaApiUrl;
        this.key = key;
    }

    @Override
    public Optional<Terminal> getTerminal(Integer id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(alfaApiUrl+"?id="+id))
                    .header("accept", "application/json")
                    .setHeader("X-IBM-Client-Id", "af9b6f1f-8280-4369-8ec7-805bd75ed698")
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                var resp = mapper.readValue(response.body(), MainResp.class);
                return resp.getData().getAtms().parallelStream()
                        .filter(v -> v.getDeviceId().equals(id))
                        .findFirst()
                        .map(v-> new Terminal(
                                v.getAddress().getCity(),
                                v.getDeviceId(),
                                v.getCoordinates().getLatitude(),
                                v.getAddress().getLocation(),
                                v.getCoordinates().getLongitude(),
                                !v.getAvailablePaymentSystems().isEmpty()
                        ));
                /*return ;*/
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("api get atm error", e);
            return Optional.empty();
        }
    }

    @Override
    public Terminal getTerminal(double latitude, double longitude, boolean payments) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(alfaApiUrl+"?id="))
                    .header("accept", "application/json")
                    .setHeader("X-IBM-Client-Id", "af9b6f1f-8280-4369-8ec7-805bd75ed698")
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                var resp = mapper.readValue(response.body(), MainResp.class);
                var result = resp.getData().getAtms().get(0);
                var length = 99999999999.0;

                for (var term : resp.getData().getAtms()) {
                    try {
                        double lat2 = Double.valueOf(term.getCoordinates().getLatitude());
                        double lon2 = Double.valueOf(term.getCoordinates().getLongitude());

                        var tmpLength = Math.sqrt(
                                Math.pow(lat2-latitude, 2) + Math.pow(lon2-longitude, 2)
                        );
                        if (tmpLength < length) {
                            length = tmpLength;
                            result = term;
                        }
                    } catch (Exception e) {

                    }

                }

                return new Terminal(
                                result.getAddress().getCity(),
                                result.getDeviceId(),
                                result.getCoordinates().getLatitude(),
                                result.getAddress().getLocation(),
                                result.getCoordinates().getLongitude(),
                                !result.getAvailablePaymentSystems().isEmpty()
                        );
            } else {
                throw new IllegalStateException();
            }
        } catch (Exception e) {
            logger.error("api get atm error", e);
            return null;
        }
    }

    private static HttpClient getRestTemplate() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] password = "password".toCharArray();
        InputStream jksInputStream = new FileInputStream("alfabattle.jks");
        keyStore.load(jksInputStream, password);

        SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, password)
                .build();
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .sslContext(sslContext)
                .build();

    }

}
