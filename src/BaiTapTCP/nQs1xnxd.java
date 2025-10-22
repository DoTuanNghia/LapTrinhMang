package BaiTapTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

// Byte Stream
public class nQs1xnxd {
    public static void main(String[] args) throws IOException {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN604";
        String qCode = "BaiTapTCP.nQs1xnxd";

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
            nums.add(Integer.parseInt(s));
        }

        int n = nums.size();
        ArrayList<Integer> window = new ArrayList<>();
        window.add(0);
        for (int i = 1; i <= n; i++){
            window.add(window.get(i - 1) + nums.get(i - 1));
        }


        int finalIndex = 0, finalSumLeft = 0, finalSumRight = 0, devitation = 0;
        Integer min = Integer.MAX_VALUE;
        int sum = window.get(n);
        for (int i = 0; i < n; i++){
            if (Math.abs(window.get(i) - (sum - window.get(i+1))) < min){
                min = Math.abs(window.get(i) - (sum - window.get(i+1)));
                finalIndex = i;
            }
        }
        finalSumLeft = window.get(finalIndex);
        finalSumRight = sum - window.get(finalIndex + 1);
        devitation = Math.abs(finalSumLeft - finalSumRight);

        String result = finalIndex + "," + finalSumLeft + "," + finalSumRight + "," + devitation;
        os.write(result.getBytes());
        os.flush();
    }
}
