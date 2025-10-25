package RMIClient;

import RMI.ByteService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class yVdTOWm3 {
    public static void main(String[] args) throws Exception{
        String studentCode = "B22DCCN244";
        String qCode = "yVdTOWm3";

        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService byteService = (ByteService) registry.lookup("RMIByteService");

        byte[] receive = byteService.requestData(studentCode, qCode);
        String ans = "";
        for (int i = 0; i < receive.length; i++){
            ans += Integer.toHexString(receive[i]);
        }

        byteService.submitData(studentCode, qCode, ans.getBytes());
    }
}
