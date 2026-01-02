package BaiTapUDP;

import UDP.Employee;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class dkfPZCwY {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String message = ";B22DCCN604;dkfPZCwY";
        String server = "203.162.10.109";
        int port = 2209;

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        byte[] receive = receivePacket.getData();
        String requestId = new String(receive, 0, 8);

        ByteArrayInputStream bais = new ByteArrayInputStream(receive, 8, receive.length-8);
        ObjectInputStream ois = new ObjectInputStream(bais);

        Employee employee = (Employee) ois.readObject();
        // xu ly
        employee.setName(name(employee.getName()));
        employee.setSalary(employee.getSalary() + 1.0*x(employee.getHireDate())* employee.getSalary()/100);
        employee.setHireDate(hireDate(employee.getHireDate()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(employee);
        oos.flush();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write(requestId.getBytes());
        result.write(baos.toByteArray());

        byte[] sendData2 = result.toByteArray();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
    }
    public static String name(String s){
        String[] temp = s.toLowerCase().split(" ");
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < temp.length; i++){
            ans.add(temp[i].substring(0,1).toUpperCase() + temp[i].substring(1));
        }
        return String.join(" ",ans);
    }
    public static int x (String date){
        String[] temp = date.split("-");
        String s = temp[0];
        int sum = 0;
        for (int i = 0; i < s.length(); i++){
            sum += Integer.parseInt(String.valueOf(s.charAt(i)));
        }
        return sum;
    }
    public static String hireDate(String s){
        String[] temp = s.split("-");
        return temp[2] + "/" + temp[1] + "/" + temp[0];
    }
}
