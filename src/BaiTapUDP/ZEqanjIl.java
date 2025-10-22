package BaiTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;

public class ZEqanjIl {
    public static void main(String[] args) throws IOException {
        String server = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN604";
        String qCode = "BaiTapUDP.ZEqanjIl";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        String message = ";" + studentCode + ";" + qCode;
        byte[] sendMessage = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendMessage, sendMessage.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receiveMessage = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(receiveMessage, receiveMessage.length);
        socket.receive(receivePacket);

        String received = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
        String[] temp = received.trim().split(";");
        String requestID =  temp[0].trim();
        String[] intString = temp[1].split(",");
        ArrayList<Integer> nums = new ArrayList<>();
        for (String s : intString) {
            nums.add(Integer.parseInt(s));
        }
        Collections.sort(nums);
        int max2 = nums.get(nums.size() - 2);
        int min2 = nums.get(1);

        String res = requestID + ";" + max2 + "," + min2;
        System.out.println(res);
        byte[] sendMessage2 = res.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendMessage2, sendMessage2.length, inetAddress, port);
        socket.send(sendPacket2);
    }
}
