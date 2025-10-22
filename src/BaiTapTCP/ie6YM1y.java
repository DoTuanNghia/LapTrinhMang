package BaiTapTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

// Data Stream
public class ie6YM1y {
    public static void main(String[] args) throws IOException, RuntimeException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN604";
        String qCode = "0ie6YM1y";

        Socket socket = new Socket(host, port);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF(studentCode+";"+qCode);
        dos.flush();

        String received = dis.readUTF();
        String[] temp = received.split(",");
        ArrayList<Integer> nums = new ArrayList<>();
        for (String s : temp){
            nums.add(Integer.parseInt(s.trim()));
        }

        int doiChieu = 0, doBienThien = 0;

        for (int i = 1; i < nums.size()-1; i++){
            if (nums.get(i) > nums.get(i-1)){
                if (nums.get(i) > nums.get(i+1)){
                    doiChieu += 1;
                }
            }
            else if (nums.get(i) < nums.get(i-1)){
                if(nums.get(i) < nums.get(i+1)){
                    doiChieu += 1;
                }
            }
            else {
                if (nums.get(i) != nums.get(i+1)){
                    doiChieu += 1;
                }
            }
        }

        for (int i = 1; i < nums.size(); i++){
            doBienThien += Math.abs(nums.get(i) - nums.get(i-1));
        }

        dos.writeInt(doiChieu);
        dos.writeInt(doBienThien);
        dos.flush();
    }
}
