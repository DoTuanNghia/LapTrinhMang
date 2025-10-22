package BaiTapTCP;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RBREc2kD {

    public static void main(String[] args) throws IOException {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN604";
        String qCode = "BaiTapTCP.RBREc2kD";

        Socket socket = new Socket(host, port);

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //a
        String sendData = studentCode + ";" + qCode;
        os.write(sendData.getBytes());
        os.flush();

        byte[] buffer = new byte[2024];
        int bytesRead = is.read(buffer);
        String received = new String(buffer, 0, bytesRead).trim();

        String[] temp = received.split(",");
        List<Integer> nums = new ArrayList<>();
        double average = 0;
        for(String strNum : temp){
            nums.add(Integer.parseInt(strNum));
        }
        for(Integer num : nums){
            average += num;
        }
        average /= nums.size();
        average *= 2;

        Collections.sort(nums);
        int num1 = 0, num2 = 0;
        int left = 0, right = nums.size() - 1;
        double minDiff = Double.MAX_VALUE;

        while (left < right){
            int sum = nums.get(left) + nums.get(right);
            double diff = Math.abs(sum - average);

            if (diff < minDiff) {
                minDiff = diff;
                num1 = nums.get(left);
                num2 = nums.get(right);
            }

            if (sum < average){
                left++;
            }
            else {
                right--;
            }
        }

        String result = String.valueOf(num1) + "," + String.valueOf(num2);
        os.write(result.getBytes());
        os.flush();
    }
}
