package BaiTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class YbCLPrcK {
    public static void main(String[] args) throws IOException {
        String message = ";B22DCCN604;YbCLPrcK";
        String server = "203.162.10.109";
        int port = 2208;

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength());
        String[] temp = receiveString.split(";");
        String requestId = temp[0];
        String[] data = temp[1].split(" ");

        List<String> res = new ArrayList<>();

        for (int i = 0; i < data.length; i++){
            res.add(data[i].substring(0, 1).toUpperCase() + data[i].substring(1).toLowerCase());
        }

        String ans = requestId + ";" + String.join(" ", res);

        byte[] sendData2 = ans.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
    }
}
