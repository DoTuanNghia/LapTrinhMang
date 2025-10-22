package BaiTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class FWeOLLl {
    public static void main(String[] args) throws IOException {
        String server = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN604";
        String qCode = "2FWeOLLl";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        String message = ";" + studentCode + ";" + qCode;
        byte[] bytes = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(bytes, bytes.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receive = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
        socket.receive(receivePacket);

        String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
        String[] temp = receiveString.split(";");
        String requestID = temp[0];
        String data = temp[1];
        String[] words = data.split(" ");
        ArrayList<String> list = new ArrayList<>();

        for (String word : words) {
            list.add(word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase());
        }

        String res = String.join(" ", list);
        String ans = requestID + ";" + data;
        System.out.println(ans);
        byte[] sendData = ans.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket2);
    }
}
