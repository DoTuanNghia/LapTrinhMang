package BaiTapTCP;

import BaiTapTCP.TCP.Product;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Yamh3hN4 {
    public static void main(String[] args) throws IOException, RuntimeException, ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN604";
        String qCode = "BaiTapTCP.Yamh3hN4";

        Socket socket = new Socket(host, port);
        socket.setSoTimeout(5000);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        oos.writeObject(studentCode+";"+qCode);
        oos.flush();

//        BaiTapTCP.TCP.Product product = (BaiTapTCP.TCP.Product) ois.readObject();
        Product product = (Product) ois.readObject();

        int intPrice = (int) product.getPrice();
        int sum = 0;
        while(intPrice != 0){
            sum += intPrice % 10;
            intPrice /= 10;
        }

        product.setDiscount(sum);

        oos.writeObject(product);
        oos.flush();
    }
}
