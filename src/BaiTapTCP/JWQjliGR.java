package BaiTapTCP;

import TCP.Laptop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class JWQjliGR {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String studentCode = "B22DCCN783";
        String qCode = "JWQjliGR";
        String host = "203.162.10.109";
        int port = 2209;
        String message = studentCode + ";" + qCode;

        Socket socket = new Socket(host, port);
        socket.setSoTimeout(5000);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        oos.writeObject(message);
        oos.flush();

        Laptop laptop = (Laptop) ois.readObject();

        // xu ly
        laptop.setName(name(laptop.getName()));
        laptop.setQuantity(quantity(laptop.getQuantity()));

        oos.writeObject(laptop);
        oos.flush();

        ois.close();
        oos.close();
        socket.close();
    }
    public static String name(String s){
        String[] temp = s.split(" ");
        ArrayList<String> res = new ArrayList<>();
        res.add(temp[temp.length-1]);
        for (int i = 1; i < temp.length-1; i++) {
            res.add(temp[i]);
        }
        res.add(temp[0]);
        return String.join(" ", res);
    }
    public static Integer quantity(Integer n){
        String s = String.valueOf(n);
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            ans = s.charAt(i) + ans;
        }
        return Integer.parseInt(ans);
    }
}
