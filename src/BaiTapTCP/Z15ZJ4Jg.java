package BaiTapTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Z15ZJ4Jg {
    public static void main(String[] args) throws IOException, RuntimeException {
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF("B22DCCN604;Z15ZJ4Jg");
        dos.flush();

        int a = dis.readInt();
        int b = dis.readInt();

        int tong = a+b;
        int tich = a*b;

        dos.writeInt(tong);
        dos.flush();
        dos.writeInt(tich);
        dos.flush();

        dis.close();
        dos.close();
        socket.close();
    }
}
