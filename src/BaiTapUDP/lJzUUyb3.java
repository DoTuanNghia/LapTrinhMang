package BaiTapUDP;

import UDP.Employee;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class lJzUUyb3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String server = "203.162.10.109";
        int port = 2209;
        String message = ";B22DCCN196;lJzUUyb3";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receive = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
        socket.receive(receivePacket);

        byte[] receiveData = receivePacket.getData();
        String requestID = new String(receiveData, 0, 8);

        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData, 8, receiveData.length - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Employee employee = (Employee) ois.readObject();

        //xu ly
        employee.setName(name(employee.getName()));
        employee.setSalary(salary(employee.getSalary(), employee.getHireDate()));
        employee.setHireDate(hireDate(employee.getHireDate()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(employee);
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
        ArrayList<String> list = new ArrayList<>();
        for (String str : temp) {
            list.add(str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase());
        }
        return String.join(" ", list);
    }

    public static double salary(double n, String s){
        String[] temp = s.split("-");
        String year = temp[0];
        int sum = 0;
        for (int i = 0; i < year.length(); i++) {
            sum += Integer.parseInt(String.valueOf(year.charAt(i)));
        }
        return n + 1.0*n*sum/100;
    }

    public static String hireDate(String s){
        String[] temp = s.split("-");
        return temp[2] + "/" + temp[1] + "/" + temp[0];
    }

}
