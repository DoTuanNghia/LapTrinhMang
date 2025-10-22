package BaiTapUDP;

import BaiTapUDP.UDP.Employee;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class s77rQjex {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String server = "203.162.10.109";
        int port = 2209;
        String message = ";B22DCCN604;BaiTapUDP.s77rQjex";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] buffer =  new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);

        byte[] receiveData = receivePacket.getData();
        String requestID = new String(receiveData, 0, 8);

        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData, 8, receiveData.length - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Employee employee = (Employee) ois.readObject();

        // xu ly
        employee.setName(name(employee.getName()));
        employee.setSalary(employee.getSalary() + 1.0*employee.getSalary()*salary(employee.getHireDate())/100);
        employee.setHireDate(hireDate(employee.getHireDate()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(employee);
        oos.flush();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write(requestID.getBytes());
        result.write(baos.toByteArray());

        byte[] sendResult = result.toByteArray();
        DatagramPacket sendResultPacket = new DatagramPacket(sendResult, sendResult.length, inetAddress, port);
        socket.send(sendResultPacket);
    }

    public static String name(String s){
        String[] temp =  s.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String str : temp) {
            list.add(str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
        }
        return String.join(" ", list);
    }

    public static int salary(String s){
        String[] temp =  s.split("-");
        int sum = 0;
        String num = temp[0];
        for (int i = 0; i < num.length(); i++) {
            sum += Integer.parseInt(String.valueOf(num.charAt(i)));
        }
        return sum;
    }

    public static String hireDate(String s){
        String[] temp =  s.split("-");
        return temp[2] + "/" + temp[1] + "/" + temp[0];
    }
}
