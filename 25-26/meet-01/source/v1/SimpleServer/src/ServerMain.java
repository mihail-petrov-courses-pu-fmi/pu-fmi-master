import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerMain {
    public static void main(String[] args) throws IOException {

        // Искам да създам МЯСТО на което да слушам за
        // мрежови заявки
        ServerSocket serverSocket = new ServerSocket(4521);
        System.out.println("Server started");

        while(true) {

            try {
                // Чакаме да дойдат клиентски заявки към ТОЗИ сокет
                // * Резултата от слушателя е евентуален потребителски
                // сокет, който се е закачил към нас.
                Socket connectionSocket = serverSocket.accept();
                System.out.println("Connection established");

                // Искам да знам, че получавам СЪОБЩЕНИЯ от клиент,
                // който е свързан с мен

                // Получавам данни - от клиента
                InputStream request = connectionSocket.getInputStream();

                // Изпращам данни - към клиента
                OutputStream response = connectionSocket.getOutputStream();

                // ТУК си правя постоянни операции докато КЛИЕНТА има връзка със СЪРВЪРА
                while(connectionSocket.isConnected()) {

                    // Същинската заявка получена от КЛИЕНТА на нашия сървър
                    int code = request.read();
                    System.out.println("Server output: " + code);
                    response.write(200);
                }
            }
            catch (SocketException e) {
                System.out.println("Server connection reset");
            }
        }
    }
}
