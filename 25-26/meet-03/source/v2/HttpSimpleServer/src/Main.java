import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final String NEW_LINE = "\r\n";

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        ApplicationLoader.run("com");
        serve();
    }

    public static void serve() throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

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

            if(requestURI.isEmpty()) {
                continue;
            }

            // На базата на URL адреса искам да ми заредиш правилния контролен
            Class processableController = ApplicationLoader.loadController(requestURI);

            if(processableController == null) {

                String serverMessage = buildHTTPResponse("Page not found");
                response.write(serverMessage.getBytes());
                continue;
            }


            var controllerInstance = processableController.newInstance();
            // Ще ползваме инстанция като материал, за да извикваме определени методи върху класа
            // Да вземем бланката в чист вит - която се казва process и да я изпълним върху някаква конкретика
            Method processMethod =  processableController.getMethod("process");

            // ... инстанзцията на класа
            String message = (String) processMethod.invoke(controllerInstance);

            String serverMessage = buildHTTPResponse(message);
            response.write(serverMessage.getBytes());

            clientSocket.close();
        }

        serverSocket.close();
    }

    private static String buildHTTPResponse(String message) {
        return "HTTP/1.1 200 OK"    + NEW_LINE +
                "Content-Length: "  + message.getBytes().length + NEW_LINE +
                "Content-Type: application/json" + NEW_LINE + NEW_LINE +
                message + NEW_LINE + NEW_LINE;
    }
}
