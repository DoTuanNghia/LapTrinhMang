package BaiTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class AFX8VDtz {
    public static void main(String[] args) throws IOException {
        String server = "203.162.10.109";
        int port = 2208;
        String message = ";B22DCCN196;AFX8VDtz";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);

        String receiveData = new String(receivePacket.getData(), 0, receivePacket.getLength());
        String[] temp =  receiveData.split(";");

        String requestID = temp[0];
        String data = temp[1];

        String ans = requestID+ ";" +chuanHoa(data);
        byte[] sendData2 = ans.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
    }
    public static String chuanHoa(String s){
        String[] temp = s.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {

            list.add(temp[i].substring(0,1).toUpperCase() + temp[i].substring(1).toLowerCase());
        }
        return String.join(" ", list);
    }
}
