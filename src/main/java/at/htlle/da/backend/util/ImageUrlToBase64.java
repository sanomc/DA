package at.htlle.da.backend.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

// Mit ChatGPT erstellt: https://chatgpt.com/share/675d5576-b7c8-8001-b67d-66ae78943a1e
public class ImageUrlToBase64 {
    private static final ImageUrlToBase64 INSTANCE = new ImageUrlToBase64();
    private final HttpClient httpClient;

    // Privater Konstruktor, um Instanziierung von außen zu verhindern
    private ImageUrlToBase64() {
        this.httpClient = HttpClient.newHttpClient();
    }

    // Öffentliche Methode, um die Singleton-Instanz zu erhalten
    public static ImageUrlToBase64 getInstance() {
        return INSTANCE;
    }

    public byte[] convert(String imageUrl) {
        try {
            // Erstellen der Anfrage
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(imageUrl))
                    .GET()
                    .build();

            // Senden der Anfrage und Empfangen der Antwort als Byte-Array
            HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());

            if (response.statusCode() == 200) {
                // Konvertieren des Byte-Arrays in einen Base64-String
                return response.body();
            } else {
                System.err.println("Fehler beim Abrufen des Bildes. HTTP-Statuscode: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            System.err.println("Ein Fehler ist aufgetreten: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
