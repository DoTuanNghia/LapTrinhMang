package BaiTapTCP;

import TCP.Customer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class iHdsyVD0 {
    public static void main(String[] args) throws IOException, RuntimeException, ClassNotFoundException {
        Socket socket = new Socket("203.162.10.109", 2209);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        oos.writeObject("B22DCCN604;iHdsyVD0");
        oos.flush();

        Customer customer = (Customer) ois.readObject();

        System.out.println(customer.getUsername());
        System.out.println(customer.getDayOfBirth());
        System.out.println(customer.getName());
        System.out.println();

//        String tempName = customer.getName();
        customer.setUsername(chuanHoaUsername(customer.getName()));
        customer.setDayOfBirth(chuanHoaNgay(customer.getDayOfBirth()));
        customer.setName(chuanHoaTen(customer.getName()));

        System.out.println(customer.getUsername());
        System.out.println(customer.getDayOfBirth());
        System.out.println(customer.getName());

        oos.writeObject(customer);
        oos.flush();

        ois.close();
        oos.close();
        socket.close();
    }

    public static String chuanHoaTen(String s){
        String[] temp = s.toLowerCase().split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i<temp.length - 1;i++){
            list.add(temp[i].substring(0, 1).toUpperCase() + temp[i].substring(1));
        }
        list.add(temp[temp.length - 1].toUpperCase());
        String result = list.get(list.size()-1) + ", ";
        for (int i=0;i<list.size()-1;i++){
            result += list.get(i) + " ";
        }
        return result.trim();
    }

    public static String chuanHoaNgay(String s){
        String[] temp = s.split("-");
        return temp[1] + "/" + temp[0] + "/" + temp[2];
    }

    public static String chuanHoaUsername(String s){
        String[] temp = s.toLowerCase().split(" ");
        String result = "";
        for (int i=0;i<temp.length-1;i++){
            result += temp[i].substring(0, 1);
        }
        result += temp[temp.length-1];
        return result;
    }
}
