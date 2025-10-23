package BaiTapTCP;

import java.io.*;
import java.net.Socket;
import java.util.LinkedHashMap;

public class e8ufOcmB {
    public static void main(String[] args) throws IOException, RuntimeException, ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2208;
        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write("B22DCCN604;e8ufOcmB");
        out.newLine();
        out.flush();

        String receive = in.readLine();
        String[] temp =  receive.split("\\s+");

        String s = "";
        for (int i = 0; i < temp.length; i++) {
            s += temp[i];
        }

        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        String ans = "";
        for (Character key : map.keySet()) {
            if (map.get(key) > 1) {
                ans = ans + key + ":" +  map.get(key) + ",";
            }
        }

        out.write(ans);
        out.newLine();
        out.flush();
    }
}
