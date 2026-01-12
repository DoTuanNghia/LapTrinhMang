package BaiTapTCP;

import TCP.Customer;

import java.io.*;
import java.net.Socket;

public class ontap {
    public static void main(String[] args) throws Exception{
        String studentCode = "";
        String qCode = "";
        String message = "";
        String server = "203.162.10.109";
        int port = 2207;

//        // byte stream
//        Socket socket = new Socket(server, port);
//        InputStream is = socket.getInputStream();
//        OutputStream os = socket.getOutputStream();
//
//        String message = ";";
//        os.write(message.getBytes());
//        os.flush();
//
//        byte[] buffer = new byte[1024];
//        int len = is.read(buffer);
//        String s =  new String(buffer, 0, len);
//
//        // xu ly
//
//        os.write(s.getBytes());
//        os.flush();

//        // char stream
//        Socket socket = new Socket(server, port);
//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//
//        out.write(message);
//        out.newLine();
//        out.flush();
//
//        String s = in.readLine();
//        String[] temp = s.split(";");
//
//        int n = 0;
//        out.write(n);
//        out.newLine();
//        out.flush();

//        // DATA stream
//        Socket socket = new Socket(server, port);
//        DataInputStream dis = new DataInputStream(socket.getInputStream());
//        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//
//        dos.writeUTF(message);
//        dos.flush();
//
//        String s = dis.readUTF();
//        int n = dis.readInt();
//
//        dos.writeUTF(s);
//        dos.flush();
//        dos.writeInt(n);
//        dos.flush();

//        // object stream
//        Socket socket = new Socket(server, port);
//        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//
//        oos.writeObject(message);
//        oos.flush();
//
//        Customer customer = (Customer) ois.readObject();
//
//        // xu ly
//
//        oos.writeObject(customer);
//        oos.flush();
    }
}
