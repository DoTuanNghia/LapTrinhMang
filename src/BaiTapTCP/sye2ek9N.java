package BaiTapTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class sye2ek9N {
    public static void main(String[] args) throws IOException {
        String message = "B22DCCN604;sye2ek9N";

        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF(message);
        dos.flush();

        int n = dis.readInt();

        dos.writeUTF(Integer.toBinaryString(n)+";"+Integer.toHexString(n).toUpperCase());
        dos.flush();
    }
}
