package BaiTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class DTtAfA8V {
    public static void main(String[] args) throws IOException, RuntimeException {
        String studentCode = "B22DCCN783";
        String qCode = "DTtAfA8V";
        String host = "203.162.10.109";
        int port = 2207;
        String message = ";" + studentCode + ";" + qCode;

        InetAddress inetAddress = InetAddress.getByName(host);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);
//        System.out.println(message);

        byte[] buffer = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);

        String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
        String[] temp =  receiveString.split(";");
        String requestID = temp[0];
        int n = Integer.parseInt(temp[1]);
        int k = Integer.parseInt(temp[2]);
        String[] z = temp[3].split(",");

        List<Integer> list = new ArrayList<>();
        for (String s : z){
            list.add(Integer.parseInt(s));
        }

        List<String> ans = new ArrayList<>();

        for (int i = 0; i < n-k+1; i++){
            int max = list.get(i);
            for (int j = i; j < i+k; j++){
                if (list.get(j) > max){
                    max = list.get(j);
                }
            }
            ans.add(max+"");
        }

        String res = requestID + ";" + String.join(",", ans);

        byte[] sendData2 = res.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);

        socket.close();
    }
}
