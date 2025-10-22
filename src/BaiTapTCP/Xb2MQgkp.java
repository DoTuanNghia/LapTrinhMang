package BaiTapTCP;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Xb2MQgkp {
    public static void main(String[] args) throws IOException, RuntimeException {
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN604";
        String qCode = "BaiTapTCP.Xb2MQgkp";

        Socket socket = new Socket(host, port);
        socket.setSoTimeout(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String sendData = studentCode + ";" + qCode;
        out.write(sendData);
        out.newLine();
        out.flush();

        String received = in.readLine();
        String[] words = received.split(", ");
        List<String> eduDomains = new ArrayList<>();
        for (String word : words){
            if (word.endsWith(".edu")){
                eduDomains.add(word.trim());
            }
        }

        String result = String.join(", ", eduDomains);
        out.write(result);
        out.newLine();
        out.flush();
    }
}
