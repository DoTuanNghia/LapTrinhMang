package BaiTapUDP;//import BaiTapUDP.UDP.Student;
//
//import java.io.*;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//public class BaiTapUDP.BTObject {
//    private static final String server = "203.162.10.109" ;
//    private static final int port = 2209 ;
//    public static void main(String[] args) {
//        DatagramSocket socket = null ;
//        String studentCode = "B22DCCN352" ;
//        String qCode = "FtZfXZ5l" ;
//        try{
//            socket = new DatagramSocket() ;
//            InetAddress inetAddress = InetAddress.getByName(server) ;
//            String message = ";" + studentCode + ";" + qCode ;
//            DatagramPacket loginPacket = new DatagramPacket(message.getBytes() , message.length() , inetAddress , port) ;
//            socket.send(loginPacket) ;
//
//            byte[] response = new byte[4096] ;
//            DatagramPacket responsePacket = new DatagramPacket(response , response.length) ;
//            socket.receive(responsePacket);
//            byte[] requestId = new byte[8] ;
//            System.arraycopy(response, 0 , requestId , 0 , 8);
//            byte[] object = new byte[responsePacket.getLength() - 8] ;
//            System.arraycopy(response , 8 , object , 0 , object.length) ;
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(object) ;
//            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream) ;
//            Student student = (Student) objectInputStream.readObject() ;
//            System.out.println(student);
//            student.setEmail(email(student.getName()));
//            student.setName(name(student.getName())) ;
//            System.out.println(student.getEmail());
//            System.out.println(student.getName());
//
//            byte[] result = new byte[4096] ;
//            System.arraycopy(requestId , 0 , result , 0 , 8) ;
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream() ;
//            ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream) ;
//            oos.writeObject(student);
//            oos.flush();
//            System.arraycopy(byteArrayOutputStream.toByteArray() , 0 , result , 8 , byteArrayOutputStream.toByteArray().length);
//            DatagramPacket resultPacket = new DatagramPacket(result , result.length , inetAddress , port) ;
//            socket.send(resultPacket);
//        } catch (UnknownHostException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static String name(String input){
//        StringBuilder sb = new StringBuilder() ;
//        String[] arr = input.toLowerCase().trim().split("\\s+") ;
//        for(String str : arr){
//            StringBuilder temp = new StringBuilder(str) ;
//            temp.setCharAt(0 , Character.toUpperCase(temp.charAt(0))) ;
//            sb.append(temp).append(" ") ;
//        }
//        return sb.toString().trim() ;
//    }
//    public static String email(String input){
//        String[] arr = input.toLowerCase().trim().split("\\s+") ;
//        StringBuilder sb = new StringBuilder() ;
//        sb.append(arr[arr.length - 1]) ;
//        for(int i = 0  ; i < arr.length - 1 ; i ++){
//            sb.append(arr[i].charAt(0)) ;
//        }
//
//        sb.append("@ptit.edu.vn") ;
//        return sb.toString() ;
//    }
//}

import BaiTapUDP.UDP.Student;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BTObject {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2209;
        String msg = ";B22DCCN244;bfD55neC";

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);

            byte[] send = msg.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(send, send.length, InetAddress.getByName(host), port);
            socket.send(sendPacket);

            byte[] buffer = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(receivePacket);

            byte[] receiveData = receivePacket.getData();
            String requestId = new String(receiveData, 0, 8);

            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData, 8, receiveData.length - 8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Student student = (Student) ois.readObject();

            student.setName(name(student.getName()));
            student.setEmail(email(student.getName()));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(student);
            oos.flush();

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            result.write(requestId.getBytes());
            result.write(baos.toByteArray());

            byte[] resultData = result.toByteArray();
            DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, InetAddress.getByName(host), port);
            socket.send(resultPacket);
        }
    }

    public static String name(String name) {
        StringBuilder sb = new StringBuilder();
        String[] words = name.split("\\s+");
        for (String word : words) {
            sb.append(word.substring(0, 1).toUpperCase());
            sb.append(word.substring(1).toLowerCase());
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    public static String email(String name) {
        StringBuilder sb = new StringBuilder();
        String[] words = name.split("\\s+");

        sb.append(words[words.length - 1].toLowerCase());
        for (int i = 0; i < words.length - 1; i++){
            sb.append(words[i].substring(0, 1).toLowerCase());
        }

        sb.append("@ptit.edu.vn");
        return sb.toString();
    }
}