package BaiTapUDP;//import java.io.IOException;
//import java.net.*;
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class BaiTapUDP.yFZmsMwG {
//    public static void main(String[] args) throws IOException {
//        String server = "203.162.10.109";
//        int port = 2207;
//        String studentCode = "B22DCCN604";
//        String qCode = "BaiTapUDP.yFZmsMwG";
//
//        InetAddress inetAddress = InetAddress.getByName(server);
//        DatagramSocket socket = new DatagramSocket();
//        String msg = ";" + studentCode + ";" + qCode ;
//        byte[] sendData = msg.getBytes() ;
//        DatagramPacket sendMessage = new DatagramPacket(sendData, sendData.length, inetAddress , port) ;
//        socket.send(sendMessage);
//
//        byte[] receivedData = new byte[4096] ;
//        DatagramPacket receivedPacket = new DatagramPacket(receivedData , receivedData.length);
//        socket.receive(receivedPacket);
//
//        String receivedString = new String(receivedPacket.getData(),  0 , receivedPacket.getLength()  , "UTF-8") ;
//        String[] temp = receivedString.trim().split(";");
//        String requestId = temp[0].trim() ;
//        String[] intString = temp[1].split(",") ;
//        ArrayList<Integer> nums = new ArrayList<>();
//        for (int i = 0; i < intString.length; i++) {
//            nums.add(Integer.parseInt(intString[i]));
//        }
//        Collections.sort(nums);
//        int max = nums.get(nums.size() - 1);
//        int min = nums.get(0);
//
//        String res = requestId + ";" + max + ";" + min ;
//        byte[] sendRes = res.getBytes();
//        DatagramPacket sendResult =  new DatagramPacket(sendRes, sendRes.length, inetAddress, port);
//        socket.send(sendResult);
//    }
//}

import java.io.*;
import java.net.*;

public class yFZmsMwG {
    public static void main(String[] args) throws IOException {
        String server = "203.162.10.109";
        int port = 2207;
        String message = ";B22DCCN604;BaiTapUDP.yFZmsMwG";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);

        String receive = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
        String[] temp =  receive.split(";");
        String requestID = temp[0];
        String[] nums = temp[1].split(",");


        byte[] sendData2 = requestID.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
    }
}
