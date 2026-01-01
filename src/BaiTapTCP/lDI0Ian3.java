package BaiTapTCP;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class lDI0Ian3 {
    public static void main(String[] args) throws IOException{
        String message = "B22DCCN604;lDI0Ian3";

        Socket socket = new Socket("203.162.10.109", 2208);
        socket.setSoTimeout(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write(message);
        out.newLine();
        out.flush();

        String s = in.readLine();
        System.out.println(s);
        String[] temp = s.split(",");
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            String res = temp[i].trim();
            if (res.endsWith(".edu")){
                ans.add(res);
            }
        }
        System.out.println(ans);

        String res = String.join(", ", ans);
        out.write(res);
        out.newLine();
        out.flush();
    }
}
