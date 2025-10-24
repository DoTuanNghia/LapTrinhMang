package RMIClient;

import RMI.ByteService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class kUwPiVvH {
    public static void main(String[] args) throws Exception{
        String host = "203.162.10.109";
        int port = 1099;
        String studentCode = "B22DCCN196";
        String qCode = "kUwPiVvH";

        Registry registry = LocateRegistry.getRegistry(host, port);

        ByteService byteService = (ByteService) registry.lookup("RMIByteService");

        byte[] data = byteService.requestData(studentCode, qCode);
        byte[] ans = new byte[data.length];
        for (int i = 0; i < data.length; i++){
            ans[i] = (byte) (data[i] + data.length);
        }

        byteService.submitData(studentCode, qCode, ans);
    }
}
