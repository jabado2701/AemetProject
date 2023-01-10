package sensor;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class APIKey {
    final static String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYWJhZG85NUBnbWFpbC5jb20iLCJqdGkiOiJlNzIwZGMwNC1hODRiLTRjMjctOGYxMi0xM2M5YjU1ODNhYjciLCJpc3MiOiJBRU1FVCIsImlhdCI6MTY3MDk0NjQ4NSwidXNlcklkIjoiZTcyMGRjMDQtYTg0Yi00YzI3LThmMTItMTNjOWI1NTgzYWI3Iiwicm9sZSI6IiJ9.PxqYb0jkQa5pT7PEkUHnewzqMFTKjpZM62eaF_oVoSU";

    static String request(String string) throws IOException {
        return SSLHelper.getConnection(string)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute()
                .body();
    }

    static class SSLHelper {
        static public Connection getConnection(String url) {
            return Jsoup.connect(url).sslSocketFactory(SSLHelper.socketFactory());
        }

        static private SSLSocketFactory socketFactory() {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};
            try {
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                return sslContext.getSocketFactory();
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                throw new RuntimeException("Failed to create a SSL socket factory", e);
            }
        }
    }
}