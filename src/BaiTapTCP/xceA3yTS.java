package BaiTapTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class xceA3yTS {
    public static void main(String[] args) throws IOException {
        String host = "203.162.10.109";
        int port = 2207;
        String message = "B22DCCN196;xceA3yTS";

        Socket socket = new Socket(host, port);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF(message);
        dos.flush();

        int n = dis.readInt();
        System.out.println(n);
        String ans = nhiPhan(n);
        System.out.println(ans);

        dos.writeUTF(ans);
        dos.flush();
    }

    public static String nhiPhan(int n) {
        String s = "";
        ArrayList<String> temp = new ArrayList<>();
        while (n != 0){
            if (n % 2 == 0){
                s = "0" + s;
            } else {
                s = "1" + s;
            }
            n /= 2;
        }
//        for (int i = temp.size()-1; i > 0; i--) {
//            s = s + temp.get(i);
//        }
        return s;
    }
}
