package BaiTapTCP;

import java.io.*;

public class draft {
    public static void main(String[] args) throws IOException, RuntimeException, ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2206;
        String sendData = "B22DCCN604;craft";

        //Byte Stream
        /*
        Socket socket = new Socket(host, port);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        os.write(sendData.getBytes());
        os.flush();

        byte[] buffer = new byte[1024];
        int bytesRead = is.read(buffer);
        String receive = new String(buffer, 0, bytesRead);
        String[] temp = receive.split(";");

        String ans = "";
        os.write(ans.getBytes());
        os.flush();
        */

        // Data Stream
        /*
        Socket socket = new Socket(host, port);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF(sendData);
        dos.flush();

        String receive = dis.readUTF();
        String[] temp = receive.split(";");

        int ans = 0;
        dos.writeInt(ans);
        dos.flush();
         */

        //Character Stream
        /*
        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write(sendData);
        out.newLine();
        out.flush();

        String receive = in.readLine();
        String[] temp = receive.split("\\s+");

        String ans = "";

        out.write(ans);
        out.newLine();
        out.flush();
         */

        //Object Stream
        /*
        Socket socket = new Socket(host, port);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        oos.writeObject(sendData);
        oos.flush();

        Address address = (Address) ois.readObject();
        //xu ly

        oos.writeObject(address);
        oos.flush();
        */
        int n = 5;
        String ans = Integer.toHexString(n);
        System.out.println(ans);
    }
}
