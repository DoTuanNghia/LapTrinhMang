package BaiTapTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCP01ZHreKi {
    public static void main(String[] args) throws IOException {
        String studentCode = "B22DCCN783";
        String qCode = "01ZHreKi";
        String host = "203.162.10.109";
        int port = 2207;
        String message = studentCode + ";" + qCode;

        Socket socket = new Socket(host, port);
        socket.setSoTimeout(5000);

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF(message);
        dos.flush();

        int n = dis.readInt();
        String ans = Integer.toBinaryString(n) + ";" + Integer.toHexString(n);

        dos.writeUTF(ans);
        dos.flush();

        dis.close();
        dos.close();
        socket.close();
    }
}
