package BaiTapUDP;

import BaiTapUDP.UDP.Book;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class RMdJ1bo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String server = "203.162.10.109";
        int port = 2209;
        String message = ";B22DCCN604;4RMdJ1bo";

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
        Book book = (Book) ois.readObject();

        // xu ly
        book.setTitle(title(book.getTitle()));
        book.setAuthor(name(book.getAuthor()));
        book.setIsbn(isbn(book.getIsbn()));
        book.setPublishDate(publishDate(book.getPublishDate()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write(requestID.getBytes());
        result.write(baos.toByteArray());

        byte[] sendResult = result.toByteArray();
        DatagramPacket sendResultPacket = new DatagramPacket(sendResult, sendResult.length, inetAddress, port);
        socket.send(sendResultPacket);
    }

    public static String title(String s) {
        String[] temp = s.split(" ");
        ArrayList<String> list = new ArrayList<>();

        for (String str : temp) {
            list.add(str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
        }
        return String.join(" ", list);
    }

    public static String name(String s) {
        String[] temp = s.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String str : temp) {
            list.add(str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
        }
        String ans = list.get(0).toUpperCase() + ", ";
        for (int i = 1; i < list.size(); i++) {
            ans += list.get(i) + " ";
        }
        return ans.trim();
    }

    public static String isbn(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            ans += s.charAt(i);
            if (i == 2 || i == 3 || i == 5 || i == 11) {
                ans += "-";
            }
        }
        return ans;
    }

    public static String publishDate(String s) {
        String[] temp = s.split("-");
        return temp[1] + "/" + temp[0];
    }
}
