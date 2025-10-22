package BaiTapTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ufKcEGMe {
    public static void main(String[] args) throws IOException, RuntimeException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN604";
        String qCode = "BaiTapTCP.ufKcEGMe";

        Socket socket = new Socket(host, port);

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF(studentCode+";"+qCode);
        dos.flush();

        int a = dis.readInt(), b = dis.readInt();

        int tong = a + b;
        int tich = a*b;

        dos.writeInt(tong);
        dos.writeInt(tich);
        dos.flush();
    }
}
