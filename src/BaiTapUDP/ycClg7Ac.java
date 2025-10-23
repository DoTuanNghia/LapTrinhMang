package BaiTapUDP;

import UDP.Book;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ycClg7Ac {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String server = "203.162.10.109";
        int port = 2209;
        String message = ";B22DCCN604;ycClg7Ac";

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
        Book book = (Book) ois.readObject();

        //xu ly
        book.setAuthor(author(book.getAuthor()));
        book.setIsbn(isbn(book.getIsbn()));
        book.setTitle(title(book.getTitle()));
        book.setPublishDate(publicDate(book.getPublishDate()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();

        ByteArrayOutputStream result = new ByteArrayOutputStream();;
        result.write(requestID.getBytes());
        result.write(baos.toByteArray());

        byte[] sendData2 = result.toByteArray();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);

        socket.close();
    }

    public static String title(String s){
        String[] temp = s.split(" ");
        ArrayList<String> ans = new ArrayList<>();
        for (String x : temp) {
            ans.add(x.substring(0,1).toUpperCase() + x.substring(1).toLowerCase());
        }
        return String.join(" ", ans);
    }

    public static String author(String s){
        String[] temp = s.split(" ");
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 1; i < temp.length; i++){
            ans.add(temp[i].substring(0,1).toUpperCase() + temp[i].substring(1).toLowerCase());
        }
        return temp[0].toUpperCase() + ", " + String.join(" ", ans);
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

    public static String publicDate(String s){
        String[] temp = s.split("-");
        return temp[1] + "/" + temp[0];
    }
}
