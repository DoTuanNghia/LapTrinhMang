package BaiTapTCP;

import BaiTapTCP.TCP.Address;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// Object Stream
public class IEifU82H {
    public static String normalizePostalCode(String input) {
        // xóa mọi kí tự khác ngoại trừ số và -
        input = input.replaceAll("[^0-9-]", "");
        return input;
    }

    public static String normalizeAddressLine(String input) {
        // xóa mọi kí tự khác trừ chữ thường, chữ hoa, số, khoảng cách
        input = input.replaceAll("[^a-zA-Z0-9\\s]", " ");

        //Đảm bảo tách biệt chữ và số
        input = input.replaceAll("([a-zA-Z])([0-9])", "$1 $2");
        input = input.replaceAll("([0-9])([a-zA-Z])", "$1 $2");

        StringBuilder result = new StringBuilder();
        String[] words = input.trim().split("\\s+");
        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0)));
            result.append(word.substring(1).toLowerCase());
            result.append(" ");
        }

        return result.toString().trim();
    }

    public static void main(String[] args) throws RuntimeException, ClassNotFoundException, IOException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN604";
        String qCode = "BaiTapTCP.IEifU82H";

        Socket socket = new Socket(host, port);
        socket.setSoTimeout(5000);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        oos.writeObject(studentCode+";"+qCode);
        oos.flush();

        Address address = (Address) ois.readObject();

        address.setPostalCode(normalizePostalCode(address.getPostalCode()));
        address.setAddressLine(normalizeAddressLine(address.getAddressLine()));


        oos.writeObject(address);
        oos.flush();
    }
}
