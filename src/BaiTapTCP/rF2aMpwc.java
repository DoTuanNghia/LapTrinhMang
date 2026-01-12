package BaiTapTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class rF2aMpwc {
    public static void main(String[] args) throws IOException {
        String message = "B22DCCN604;rF2aMpwc";
        String host = "203.162.10.109";
        int port = 2206;

        Socket socket = new Socket(host, port);
        socket.setSoTimeout(5000);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        os.write(message.getBytes());
        os.flush();

        byte[] buffer = new byte[1024];
        int len = is.read(buffer);
        String s = new String(buffer, 0, len);
//        String s = "5,10,20,25,50,40,30,35";
        String[] line = s.trim().split(",");

        int n = line.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(line[i]);

        int[] f = new int[n];
        int[] trace = new int[n];
        int max = 0;
        int id = 0;
        for (int i = 0; i < n; i++) {
            f[i] = 1;
            trace[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i] && f[j] + 1 > f[i]) {
                    f[i] = f[j] + 1;
                    trace[i] = j;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (f[i] > max) {
                max = f[i];
                id = i;
            }
        }
        List<Integer> lis = new ArrayList<>();
        while (id != -1) {
            lis.add(a[id]); // thêm vào đầu để đảo đúng thứ tự
            id = trace[id];
        }
        String res = "";
        for (int i = lis.size() - 1; i >= 0; i--) {
            res += lis.get(i) + ",";
        }

        res = res.substring(0, res.length() - 1);
        res += ";" + max;
        System.out.println(res);
        os.write(res.getBytes());
        os.flush();
    }
}
