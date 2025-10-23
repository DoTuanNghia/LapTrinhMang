package BaiTapTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class QqwcjSvo {
    public static void main(String[] args) throws IOException, RuntimeException {
        String host = "203.162.10.109";
        int port = 2206;
        String message = "B22DCCN196;QqwcjSvo";

        Socket socket = new Socket(host, port);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        os.write(message.getBytes());
        os.flush();

        byte[] buffer = new byte[1024];
        int readBytes = is.read(buffer);
        String receive = new  String(buffer, 0, readBytes, "UTF-8");
        String[] temp = receive.split("\\|");

        int tong = 0;
        for (int i = 0; i < temp.length; i++) {
            tong += Integer.parseInt(temp[i]);
        }

        String ans = tong + "";
        byte[] sendData = ans.getBytes();
        os.write(sendData);
        os.flush();
    }
}
