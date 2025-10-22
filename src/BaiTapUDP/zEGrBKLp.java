package BaiTapUDP;

import BaiTapUDP.UDP.Student;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class zEGrBKLp {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String server = "203.162.10.109";
        int port = 2209;
        String message = ";B22DCCN604;BaiTapUDP.zEGrBKLp";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] buffer = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);

        byte[] receiveData = receivePacket.getData();
        String requestID = new String(receiveData, 0, 8);

        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData, 8, receiveData.length - 8 );
        ObjectInputStream ois = new ObjectInputStream(bais);
        Student student = (Student) ois.readObject();

        //xu ly
        student.setName(name(student.getName()));
        student.setEmail(email(student.getName()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(student);
        oos.flush();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write(requestID.getBytes());
        result.write(baos.toByteArray());

        DatagramPacket resultPacket = new DatagramPacket(result.toByteArray(), result.size(), inetAddress, port);
        socket.send(resultPacket);
    }

    public static String name(String s){
        String[] temp =  s.split(" ");
        ArrayList<String>list = new ArrayList<>();
        for (String str : temp) {
            list.add(str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
        }
        return String.join(" ", list);
    }

    public static String email(String s){
        String[] temp =  s.toLowerCase().split(" ");
        ArrayList<String>list = new ArrayList<>();
        for (String str : temp) {
            list.add(str);
        }
        String ans = list.get(list.size()-1);
        for (int i = 0; i < list.size() - 1; i++) {
            ans += list.get(i).substring(0,1);
        }
        return ans + "@ptit.edu.vn";
    }
}
