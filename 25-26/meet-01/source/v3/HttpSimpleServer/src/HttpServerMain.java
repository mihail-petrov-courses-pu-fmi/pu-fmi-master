import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerMain {

    private static final String NEW_LINE = "\r\n";

    public static void main(String[] args) throws IOException {

        // Инициализираме си СОКЕТ който да чака включване от клиента
        ServerSocket serverSocket = new ServerSocket(1423);

        while (serverSocket.isBound()) {

            System.out.println("* Connected");

            // чакаме заявка от клиента
            Socket clientSocket = serverSocket.accept();

            // Взимаме си входно изходните потоци от данни
            InputStream request = clientSocket.getInputStream();
            OutputStream response = clientSocket.getOutputStream();

            // Трябва да прочетем първо ЗАЯВКАТА, която браузъра ни праща
            // и да преценим какво ни връща.
            // * Ще използваме механизъм който превръща ПОТОКА от данни в НИЗОВЕ
            BufferedReader httpRequestReader = new BufferedReader(
                    new InputStreamReader(request)
            );

            // постоянне цикъл в който четем входните данни от браузъра
            String currentRequestLine = "";
            String requestMethod    = "";
            String requestURI       = "";

            while((currentRequestLine = httpRequestReader.readLine()) != null) {

                String[] requestProperties =  currentRequestLine.split(" ");
                requestMethod   = requestProperties[0];
                requestURI      = requestProperties[1];
                break;
            }

            if(requestURI.equals("/test")) {

                // В момента в който получиш заявка от клиента
                // - върни ми HTTP съобщение - което да носи някакъв текст
                String serverMessage = buildHTTPResponse("Test Page");
                response.write(serverMessage.getBytes());
            }

            if(requestURI.equals("/")) {
                String serverMessage = buildHTTPResponse("Hello World");
                response.write(serverMessage.getBytes());
            }

            String serverMessage = buildHTTPResponse("Page Not found");
            response.write(serverMessage.getBytes());
        }
    }

    private static String buildHTTPResponse(String message) {
        return "HTTP/1.1 200 OK"    + NEW_LINE +
                "Content-Length: "  + message.getBytes().length + NEW_LINE +
                "Content-Type: application/json" + NEW_LINE + NEW_LINE +
                message + NEW_LINE + NEW_LINE;
    }
}
