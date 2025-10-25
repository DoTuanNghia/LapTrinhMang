package BaiTapTCP;

import java.io.*;
import java.net.Socket;
import java.util.LinkedHashSet;

public class cONXIj2W {
    public static void main(String[] args) throws IOException {
        String studentCode = "B22DCCN783";
        String qCode = "cONXIj2W";
        String host = "203.162.10.109";
        int port = 2208;

        Socket socket = new Socket(host, port);
        socket.setSoTimeout(5000);

        BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String message = studentCode + ";" + qCode;
        out.write(message);
        out.newLine();
        out.flush();

        String receive = in.readLine();
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for (int i = 0; i < receive.length(); i++){
            set.add(receive.charAt(i));
        }

        String ans = "";
        for (Character c : set){
            if (Character.isLetter(c)){
                ans += String.valueOf(c);
            }
        }
        System.out.println(ans);

        out.write(ans);
        out.newLine();
        out.flush();
    }
}
