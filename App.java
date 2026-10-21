import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(
            new InetSocketAddress("0.0.0.0", 8080), 0
        );

        server.createContext("/", exchange -> {
            String html = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Faculty CI/CD</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background: #f0f4f8;
                            text-align: center;
                            padding: 60px;
                        }
                        .card {
                            background: white;
                            padding: 30px;
                            margin: auto;
                            max-width: 600px;
                            border-radius: 12px;
                            box-shadow: 0 4px 12px #cccccc;
                        }
                        h1 { color: #174ea6; }
                    </style>
                </head>
                <body>
                    <div class="card">
                        <h1>College Management System</h1>
                        <h2>Faculty Module</h2>
                        <p>Application is running successfully!</p>
                        <p>Deployed using Jenkins, Docker and Kubernetes.</p>
                    </div>
                </body>
                </html>
                """;

            byte[] response = html.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set(
                "Content-Type", "text/html; charset=UTF-8"
            );
            exchange.sendResponseHeaders(200, response.length);

            try (OutputStream out = exchange.getResponseBody()) {
                out.write(response);
            }
        });

        server.start();
        System.out.println("Faculty web application started on port 8080");
    }
}