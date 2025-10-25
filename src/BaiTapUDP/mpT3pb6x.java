package BaiTapUDP;

import UDP.Book;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class mpT3pb6x {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String message = ";B22DCCN783;mpT3pb6x";
        String host =  "203.162.10.109";
        int port = 2209;

        InetAddress inetAddress = InetAddress.getByName(host);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);

        byte[] receive = receivePacket.getData();
        String requestID = new String(receive, 0 , 8);

        ByteArrayInputStream bais = new ByteArrayInputStream(receive, 8, receive.length-8);
        ObjectInputStream ois = new ObjectInputStream(bais);

        Book book = (Book) ois.readObject();

        //xu ly
        book.setTitle(title(book.getTitle()));
        book.setAuthor(author(book.getAuthor()));
        book.setPublishDate(date(book.getPublishDate()));
        book.setIsbn(isbn(book.getIsbn()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos =  new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write(requestID.getBytes());
        result.write(baos.toByteArray());

        byte[] sendData2 = result.toByteArray();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);
    }
    public static String title(String s){
        String[] temp = s.split(" ");
        List<String> list = new ArrayList<>();
        for (String str : temp) {
            list.add(str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
        }
        return String.join(" ", list);
    }

    public static String author(String s){
        String[] temp = s.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 1; i < temp.length; i++){
            list.add(temp[i].substring(0,1).toUpperCase() + temp[i].substring(1).toLowerCase());
        }
        return temp[0].toUpperCase() + ", " + String.join(" ", list);
    }

    public static String isbn(String s){
        String ans = "";
        for (int i = 0; i < s.length(); i++){
            ans += s.charAt(i);
            if (i == 2 || i == 3 || i == 5 || i == 11){
                ans += "-";
            }
        }
        return ans;
    }

    public static String date(String s){
        String[] temp = s.split("-");
        return temp[1] + "/" + temp[0];
    }
}
