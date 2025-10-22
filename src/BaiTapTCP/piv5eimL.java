package BaiTapTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class piv5eimL {
    public static void main(String[] args) throws IOException, RuntimeException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN604";
        String qCode = "BaiTapTCP.piv5eimL";

        Socket socket = new Socket(host, port);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF(studentCode+";"+qCode);
        dos.flush();

        int k = dis.readInt();
        String received = dis.readUTF();
        String[] temp = received.split(",");
        ArrayList<String> nums = new ArrayList<>();
        for(String s : temp){
            nums.add(s.trim());
        }

        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < nums.size(); i += k){
            if (i+k-1 > nums.size()-1){
                for (int j = nums.size()-1; j >= i; j--){
                    ans.add(nums.get(j));
                }
            }else {
                for (int j = i+k-1; j >= i; j--){
                    ans.add(nums.get(j));
                }
            }
        }

        String result = String.join(",", ans);
        dos.writeUTF(result);
        dos.flush();
    }
}
