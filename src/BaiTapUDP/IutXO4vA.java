package BaiTapUDP;

import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class IutXO4vA {
    public static void main(String[] args) throws IOException, NumberFormatException {
        String server = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN604";
        String qCode = "BaiTapUDP.IutXO4vA";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        String message = ";" + studentCode + ";" + qCode;
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receiveData = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        String receive = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
        String[] temp = receive.split(";");
        String requestID = temp[0];
//        Long a =  Long.parseLong(nums[0]);
//        Long b = Long.parseLong(nums[1]);
//        Long sum = a+b;
//        Long difference = a-b;
        BigInteger a = new BigInteger(temp[1]);
        BigInteger b = new BigInteger(temp[2]);
        BigInteger sum = a.add(b);
        BigInteger difference = a.subtract(b);
        String result = requestID + ";" + sum + "," + difference.abs();
        System.out.println(result);
        byte[] sendData2 = result.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
    }
}
