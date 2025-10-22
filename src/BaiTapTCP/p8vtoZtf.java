package BaiTapTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class p8vtoZtf {
    public static boolean isPrime(int n){
        for (int i = 2; i <= Math.sqrt(n); i++){
            if (n%i == 0) return false;
        }
        return n>1;
    }
    public static void main(String[] args) throws IOException {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN604";
        String qCode = "BaiTapTCP.p8vtoZtf";

        Socket socket = new Socket(host, port);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        String sendData = studentCode + ";" + qCode;
        os.write(sendData.getBytes());
        os.flush();

        byte[] buffer = new byte[1024];
        int bytesRead = is.read(buffer);

        String received = new String(buffer, 0, bytesRead).trim();
        String[] temp = received.split(",");
        ArrayList<Integer> nums = new ArrayList<>();
        for (String s : temp){
            Integer n = Integer.parseInt(s);
            if (isPrime(n)) nums.add(n);
        }

        int sum = 0;
        for (Integer num : nums){
            sum += num;
        }

        String result = String.valueOf(sum);
        os.write(result.getBytes());
        os.flush();
    }
}
