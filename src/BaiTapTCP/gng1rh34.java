package BaiTapTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class gng1rh34 {
    public static void main(String[] args) throws IOException {
        String studentCode = "B22DCCN783";
        String qCode = "gng1rh34";
        String host = "203.162.10.109";
        int port = 2206;

        Socket socket = new Socket(host, port);
        socket.setSoTimeout(5000);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        String message = studentCode + ";" + qCode;
        os.write(message.getBytes());
        os.flush();

        byte[] buffer = new byte[1024];
        int len = is.read(buffer);
        String receive = new String(buffer, 0, len);

        int n = Integer.parseInt(receive);
        ArrayList<String> list = new ArrayList<>();
        list.add(String.valueOf(n));

        while (n > 1) {
            if (n % 2 == 0) {
                list.add(String.valueOf(n / 2));
                n = n / 2;
            } else {
                list.add(String.valueOf(n * 3 + 1));
                n = n * 3 + 1;
            }
        }
        String ans = String.join(" ", list) + "; " + list.size();

        os.write(ans.getBytes());
        os.flush();

        is.close();
        os.close();
        socket.close();
    }
}
