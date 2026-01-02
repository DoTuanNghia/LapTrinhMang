package BaiTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class PAgeICKT {
    public static void main(String[] args) throws IOException {
        String message = ";B22DCCN604;PAgeICKT";
        String server = "203.162.10.109";
        int port = 2207;

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receive = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
        socket.receive(receivePacket);

        String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength());
        String[] temp = receiveString.split(";");
        String requestId = temp[0];
        int n = Integer.valueOf(temp[1]);
        int k = Integer.valueOf(temp[2]);
        List<Integer> res = new ArrayList<>();
        String[] nums = temp[3].split(",");
        for (int i = 0; i < nums.length; i++){
            res.add(Integer.valueOf(nums[i]));
        }
        System.out.println(res);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n-k+1; i++){
            int max = res.get(i);
            for (int j = 1; j < k; j++){
                if (res.get(i+j) > max){
                    max = res.get(i+j);
                }
            }
            ans.add(String.valueOf(max));
        }

        String answer = requestId + ";" + String.join(",", ans);
        byte[] sendData2 = answer.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
    }
}
