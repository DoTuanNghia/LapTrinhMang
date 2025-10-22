package BaiTapTCP;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class W42hLtIp {
    public static void main(String[] args) throws IOException, RuntimeException {
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN604";
        String qCode = "BaiTapTCP.W42hLtIp";

        Socket socket = new Socket(host, port);
        socket.setSoTimeout(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write(studentCode + ";" + qCode);
        out.newLine();
        out.flush();

        String received = in.readLine();
        String[] temp = received.split("\\s+");
        List<String> words = new ArrayList<>();
        for (String s : temp){
            words.add(s.trim());
        }

        Collections.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));

        String result = String.join(", ", words);

        out.write(result);
        out.newLine();
        out.flush();
    }
}
