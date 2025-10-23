package BaiTapUDP;

import UDP.Book;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class draft {
    public static void main(String[] args) throws IOException, ClassNotFoundException, RuntimeException {
        String server = "203.162.10.109";
        int port = 2207;

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        //Data Type + String
        /*
        String message = ";B22DCCN604;draft";
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receive = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
        socket.receive(receivePacket);

        String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
        String[] temp = receive.split(";");

        // xu ly

        String ans = "";
        byte[] sendData2 = ans.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
         */

        // Object
        /*
        String message = ";B22DCCN604;draft";
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receive = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
        socket.receive(receivePacket);

        byte[] receiveData = receivePacket.getData();
        String requestID = new String(receiveData, 0, 8);

        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData, 8, receiveData.length - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Book book = (Book) ois.readObject();

        //xu ly

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write(requestID.getBytes());
        result.write(baos.toByteArray());

        byte[] sendData2 = result.toByteArray();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
        */
    }
}
