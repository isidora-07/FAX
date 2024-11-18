package primer_2;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.cert.CRL;

public class HttpClientRequest extends Thread {
    static String CRLF = "\r\n";
    Socket socket;
    String encoding = "UTF-16";

    public HttpClientRequest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());

            String requestLine = br.readLine();

            System.out.println("Request: " + requestLine);
            String[] deloviZahteva = requestLine.split(" ");
            String method = deloviZahteva[0];
            String path = deloviZahteva[1];

            String statusLine = null;
            String contentTypeLine = null;
            String contentLengthLine = null;
            String contentEncoding = null;

            String body = "";

            if (method.equals("GET") && path.equals("/hello")) {
                statusLine = "HTTP/1.1 200 OK" + CRLF;
                contentTypeLine = "Content-Type: text/txt" + CRLF;
                body = "Zdravo svima";
            } else {
                statusLine = "HTTP/1.1 404 Not Found" + CRLF;
                contentTypeLine = "Content-Type: text/html" + CRLF;
                body = "<html>" +
                        "<head></head>" +
                        "<body>Fajl nije pronadjen</body>" +
                        "</html>";
            }

            contentLengthLine = "Content-Length:  " + body.getBytes() + CRLF;
            contentEncoding = "Content-Encoding: " + encoding + CRLF;

            os.writeBytes(statusLine);
            os.writeBytes(contentTypeLine);
            os.writeBytes(contentLengthLine);
            os.writeBytes(contentEncoding);
            os.writeBytes(CRLF);

            byte[] bytes = body.getBytes(encoding);
            for (int i = 0; i < bytes.length; i++) {
                os.write(bytes[i]);
            }
            os.flush();
            os.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
