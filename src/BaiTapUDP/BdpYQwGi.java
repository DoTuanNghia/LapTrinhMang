package BaiTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class BdpYQwGi {
    public static void main(String[] args) throws IOException,  ClassNotFoundException, RuntimeException {
        String server = "203.162.10.109";
        int port = 2207;
        String message = ";B22DCCN604;BdpYQwGi";

        InetAddress inetAddress = InetAddress.getByName(server);
        DatagramSocket socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(sendPacket);

        byte[] recceive = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(recceive, recceive.length);
        socket.receive(receivePacket);
        System.out.println(receivePacket.getData());

        String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength());
        String[] temp =  receiveString.split(";");
        String requestID = temp[0];
        int n = Integer.parseInt(temp[1]);
        int k = Integer.parseInt(temp[2]);
        String[] tempNums = temp[3].split(",");
        ArrayList<Integer> nums = new ArrayList<>();
        for (String s : tempNums) {
            nums.add(Integer.parseInt(s));
        }
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < n-k+1; i ++){
            int maxm = nums.get(i);
            for (int j = i; j < i+k; j++){
                if (nums.get(j)>maxm){
                    maxm = nums.get(j);
                }
            }
            ans.add(String.valueOf(maxm));
        }

        String res = requestID + ";" + String.join(",", ans);
        byte[] sendData2 = res.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, inetAddress, port);
        socket.send(sendPacket2);

        socket.close();
    }
}
