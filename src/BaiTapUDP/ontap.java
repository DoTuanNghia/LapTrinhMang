package BaiTapUDP;

import UDP.Book;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ontap {
    public static void main(String[] args) throws Exception{
        String message = ";B22DCCN604;craft";
        String server = "203.162.10.109";
        int port = 2208;

//        // String + Data Type
//        DatagramSocket socket = new DatagramSocket();
//        InetAddress inetAddress = InetAddress.getByName(server);
//
//        byte[] sendData = message.getBytes();
//        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
//        socket.send(sendPacket);
//
//        byte[] receiveData = new byte[1024];
//        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//        socket.receive(receivePacket);
//
//        String s = new String(receivePacket.getData(), 0, receivePacket.getLength());
//
//        byte[] sendData2 = s.getBytes();
//        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
//        socket.send(sendPacket2);

        // Object
        DatagramSocket socket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName(server);

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receive = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
        socket.receive(receivePacket);

        byte[] receiveData = receivePacket.getData();
        String requestId = new String(receiveData, 0, 8);

        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData, 8, receiveData.length-8);
        ObjectInputStream ois = new ObjectInputStream(bais);

        Book book = (Book) ois.readObject();

        //xu ly

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write(requestId.getBytes());
        result.write(baos.toByteArray());

        byte[] sendData2 = result.toByteArray();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
    }
}
