package BaiTapTCP;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

// Character Stream
public class kUbO71r {
    public static String reverse(String s){
        String temp = "";
        for (int i = s.length()-1; i >= 0; i--){
            temp = temp + s.charAt(i);
        }
        return temp;
    }

    public static String rle(String s){
        String temp = "";
        int cnt = 1;
        for (int i = 1; i <= s.length(); i++){
            if (i < s.length() && s.charAt(i) == s.charAt(i-1)){
                cnt++;
            }else{
                temp = temp + s.charAt(i-1);
                if (cnt > 1){
                    temp = temp + String.valueOf(cnt);
                }
                cnt = 1;
            }
        }
        return temp;
    }

    public static void main(String[] args) throws IOException, RuntimeException {
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN604";
        String qCode = "6kUbO71r";

        Socket socket = new Socket(host, port);
        socket.setSoTimeout(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write(studentCode + ";" + qCode);
        out.newLine();
        out.flush();

        String received = in.readLine();
        String[] temp = received.split("\\s+");
        ArrayList<String> words = new ArrayList<>();
        for(String s : temp){
            words.add(rle(reverse(s.trim())));
            System.out.println(rle(reverse(s.trim())));
        }

        String result = String.join(" ", words);

        out.write(result);
        out.newLine();
        out.flush();
    }
}
