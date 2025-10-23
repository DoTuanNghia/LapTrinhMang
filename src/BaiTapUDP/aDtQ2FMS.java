package BaiTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class aDtQ2FMS {
    public static void main(String[] args) throws IOException {
        String server = "203.162.10.109";
        int port = 2207;
        String message = ";B22DCCN196;aDtQ2FMS";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receive = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
        socket.receive(receivePacket);

        String receiveData = new String(receivePacket.getData(), 0, receivePacket.getLength());
        String[] temp =  receiveData.split(";");
        String requestID = temp[0];
        String[] string =  temp[1].split(",");
        TreeMap<Integer, String> map = new TreeMap<>();
        for (int i = 0; i < string.length; i++) {
            String[] parse =  string[i].split(":");
            int n = Integer.parseInt(parse[1]);
            String s = parse[0];
            map.put(n, s);
        }
        ArrayList<String> ans = new ArrayList<>();
        for (Integer key : map.keySet()) {
            ans.add(map.get(key));
        }
        String res = requestID + ";" + String.join(",", ans);
        System.out.println(res);

        byte[] sendData2 = res.getBytes();
        DatagramPacket sendPacket2 =  new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
    }
}
