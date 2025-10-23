package BaiTapTCP;

import TCP.Laptop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NNO2oOF3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2209;
        String message = "B22DCCN196;NNO2oOF3";

        Socket socket = new Socket(host, port);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        oos.writeObject(message);
        oos.flush();

        Laptop laptop = (Laptop) ois.readObject();

        laptop.setName(name(laptop.getName()));
        laptop.setQuantity(quantity(String.valueOf(laptop.getQuantity())));

        oos.writeObject(laptop);
        oos.flush();
    }
    public static String name(String s){
        String[] temp = s.split(" ");
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 1; i < temp.length-1; i++) {
            arrayList.add(temp[i]);
        }
        return temp[temp.length-1] + " " + String.join(" ", arrayList) + " " + temp[0];
    }
    public static int quantity(String s){
        String temp = "";
        for (int i = s.length()-1; i >= 0; i--) {
            temp =  temp + s.charAt(i);
        }
        return Integer.parseInt(temp);
    }
}
