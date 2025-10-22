package BaiTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;

public class CLqkFDTs {
    public static void main(String[] args) throws IOException {
        String server = "203.162.10.109";
        int port = 2208;
        String message = ";B22DCCN604;BaiTapUDP.CLqkFDTs";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receive = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
        socket.receive(receivePacket);

        String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
        String[] temp = receiveString.split(";");
        String requestID =  temp[0];
        String data = temp[1];
        String[] words = data.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String word : words) {
            list.add(word.trim());
        }
        Collections.sort(list, ((o1, o2) ->  o2.compareTo(o1)));

        String res = String.join(",", list);
        String ans = requestID + ";" + res;
        System.out.println(ans);
        byte[] sendData2 = ans.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
        socket.close();
    }
}
