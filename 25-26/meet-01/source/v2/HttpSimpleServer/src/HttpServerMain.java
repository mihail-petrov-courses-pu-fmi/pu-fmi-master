import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

            // В момента в който получиш заявка от клиента
            // - върни ми HTTP съобщение - което да носи някакъв текст
            String serverMessage = buildHTTPResponse("Hello World");
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
