package BaiTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class yLNZdxID {
    public static void main(String[] args) throws IOException, RuntimeException {
        String server = "203.162.10.109";
        int port = 2208;
        String message = ";B22DCCN604;yLNZdxID";

        InetAddress address = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        socket.send(sendPacket);

        byte[] receive = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
        socket.receive(receivePacket);

        String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength());
        String[] temp = receiveString.split(";");
        String requestID = temp[0];;
        String data = temp[1];

        String ans = requestID + ";" + chuanHoa(data);
        byte[] sendData2 = ans.getBytes();
        DatagramPacket  sendPacket2 = new DatagramPacket(sendData2, sendData2.length, address, port);
        socket.send(sendPacket2);

        socket.close();
    }
    public static String chuanHoa(String s){
        String[] temp = s.split(" ");
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            ans.add(temp[i].substring(0,1).toUpperCase() + temp[i].substring(1).toLowerCase());
        }
        return String.join(" ", ans);
    }
}
