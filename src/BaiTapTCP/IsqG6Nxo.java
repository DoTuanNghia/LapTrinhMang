package BaiTapTCP;

import TCP.Laptop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IsqG6Nxo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String message = "B22DCCN604;IsqG6Nxo";

        Socket socket = new Socket("203.162.10.109", 2209);
        socket.setSoTimeout(5000);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        oos.writeObject(message);
        oos.flush();

        Laptop laptop = (Laptop) ois.readObject();
        laptop.setName(name(laptop.getName()));
        laptop.setQuantity(quantity(laptop.getQuantity()));

        oos.writeObject(laptop);
        oos.flush();
    }
    public static String name(String s){
        String[] temp = s.split(" ");
        String res = "";
        res += temp[temp.length - 1];
        for (int i = 1; i < temp.length - 1; i++) {
            res += " " + temp[i];
        }
        res += " " + temp[0];
        return res;
    }
    public static int quantity(int n){
        String temp = String.valueOf(n);
        String res = "";
        for (int i = temp.length() - 1; i >= 0; i--) {
            res += temp.charAt(i);
        }
        return Integer.parseInt(res);
    }
}
