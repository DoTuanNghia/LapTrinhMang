package BaiTapTCP;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class iehTyQ2E {
    public static void main(String[] args) throws IOException, RuntimeException {
        String host = "203.162.10.109";
        int port = 2208;
        String message = "B22DCCN196;iehTyQ2E";

        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write(message);
        out.newLine();
        out.flush();

        String receive =  in.readLine();
        String[] temp =  receive.split(",");

        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains(".edu")){
                ans.add(temp[i]);
            }
        }

        out.write(String.join(", ", ans));
        out.newLine();
        out.flush();
    }
}
