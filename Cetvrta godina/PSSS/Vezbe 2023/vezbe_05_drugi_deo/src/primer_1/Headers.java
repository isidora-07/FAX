package primer_1;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Headers {
    public static void main(String[] args) {
        String urlString = "https://google.rs";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            System.out.println("Response code: " + responseCode);

            if (responseCode == 200) {
                System.out.println("Content-Length: " + connection.getContentLength());
                System.out.println("Content-Type: " + connection.getContentType());
                System.out.println("Date: " + new Date(connection.getDate()));

                for (int i = 1; ; i++) {
                    String header = connection.getHeaderField(i);
                    if (header == null)
                        break;
                    System.out.println(connection.getHeaderFieldKey(i) + ": " + header);
                }
            } else {
                System.out.println("File ne postoji");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
