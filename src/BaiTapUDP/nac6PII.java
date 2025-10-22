package BaiTapUDP;

import BaiTapUDP.UDP.Customer;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class nac6PII {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String server = "203.162.10.109";
        int port = 2209;
        String message = ";B22DCCN604;0nac6PII";

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

        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData, 8, receiveData.length - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Customer customer = (Customer) ois.readObject();

        //xu ly
        customer.setUserName(userName(customer.getName()));
        customer.setDayOfBirth(dayOfBirth(customer.getDayOfBirth()));
        customer.setName(name(customer.getName()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(customer);
        oos.flush();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write(requestID.getBytes());
        result.write(baos.toByteArray());

        byte[] sendResult = result.toByteArray();
        DatagramPacket resultPacket = new DatagramPacket(sendResult, sendResult.length, inetAddress, port);
        socket.send(resultPacket);
    }

    public static String name(String s){
        String[] temp = s.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String str : temp) {
            list.add(str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
        }
        list.set(list.size()-1, list.get(list.size()-1).toUpperCase());
        String ans = list.get(list.size()-1) + ", ";
        for (int i = 0; i < list.size()-1; i++){
            ans += list.get(i) + " ";
        }
        return ans.trim();
    }

    public static String dayOfBirth(String s){
        String[] temp = s.split("-");
        return temp[1] + "/" + temp[0] + "/" + temp[2];
    }

    public static String userName(String s){
        String[] temp = s.toLowerCase().split(" ");
        String ans = "";
        for (int i = 0; i < temp.length-1; i++){
            ans += temp[i];
        }
        return ans + temp[temp.length-1];
    }
}
