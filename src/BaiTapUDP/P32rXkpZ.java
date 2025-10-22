package BaiTapUDP;

import BaiTapUDP.UDP.Product;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class P32rXkpZ {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        String server = "203.162.10.109";
//        int port = 2209;
//        String message = ";B22DCCN604;BaiTapUDP.P32rXkpZ";
//
//        InetAddress inetAddress = InetAddress.getByName(server);
//        DatagramSocket socket = new DatagramSocket();
//        socket.setSoTimeout(5000);
//
//        byte[] sendData = message.getBytes();
//        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
//        socket.send(sendPacket);
//
//        byte[] buffer = new byte[4096];
//        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
//        socket.receive(receivePacket);
//
//        byte[] receiveData = receivePacket.getData();
//        String requestId = new String(receiveData, 0, 8);
//
//        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData, 8, receiveData.length - 8 );
//        ObjectInputStream ois = new ObjectInputStream(bais);
//        Product product = (Product) ois.readObject();
//
//        //xu ly
//        product.setName(name(product.getName()));
//        product.setQuantity(quantity(product.getQuantity()));
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(product);
//        oos.flush();
//
//        ByteArrayOutputStream result = new ByteArrayOutputStream();
//        result.write(requestId.getBytes());
//        result.write(baos.toByteArray());
//
//        byte[] resultData = result.toByteArray();
//        DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, inetAddress, port);
//        socket.send(resultPacket);
//
        String server = "203.162.10.109";
        int port = 2209;
        String message = ";B22DCCN604;BaiTapUDP.P32rXkpZ";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);

        byte[] receiveData = receivePacket.getData();
        String requestID = new String(receiveData, 0, 8);

        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData, 8, receiveData.length-8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Product product = (Product) ois.readObject();

        // xu ly
        product.setName(name(product.getName()));
        product.setQuantity(quantity(product.getQuantity()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(product);
        oos.flush();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write(requestID.getBytes());
        result.write(baos.toByteArray());

        byte[] sendData2 = result.toByteArray();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
    }

    public static String name(String s){
        String[] temp = s.split(" ");
        String ans = "";
        ans = temp[temp.length-1] + " ";
        for (int i = 1; i < temp.length-1; i++){
            ans = ans + temp[i] + " ";
        }
        ans = ans + temp[0];
        return ans.trim();
    }

    public static int quantity(int n){
        String s = String.valueOf(n);
        String ans = "";
        for (int i = s.length()-1; i >= 0; i--){
            ans = ans + s.charAt(i);
        }
        return Integer.parseInt(ans);
    }
}
