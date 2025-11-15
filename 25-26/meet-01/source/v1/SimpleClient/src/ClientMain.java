import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("localhost", 4521);

        // получаваме данни - от СЪРВЪРА
        InputStream response = clientSocket.getInputStream();

        // изпращаме данни - към сървъра
        OutputStream request = clientSocket.getOutputStream();

        // докатож имам връзка със сървъра искам постоянно да слушам
        // за нови съобщения и да му изпращам нещо
        while(clientSocket.isConnected()) {

            // Същинска заявка по мрежата
            request.write(1);

            // очакваме потенциално някакъв резултат
            int responseCode = response.read();
            System.out.println("Client reseaved: " + responseCode);
        }
    }
}
