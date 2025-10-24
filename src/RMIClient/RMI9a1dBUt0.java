package RMIClient;

import RMI.ByteService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI9a1dBUt0 {
    public static void main(String[] args) throws Exception{
        String studentCode = "B22DCCN604";
        String qCode = "9a1dBUt0";

        String host = "203.162.10.109";
        int port = 1099;

        Registry registry = LocateRegistry.getRegistry(host, port);

        ByteService service = (ByteService) registry.lookup("RMIByteService");

        byte[] data = service.requestData(studentCode, qCode);
        String key = "PTIT";
        byte[] keyBytes = key.getBytes("ASCII");
        byte[] encoder = new byte[data.length];

        for (int i = 0; i < data.length; i++) {
            encoder[i] = (byte) (data[i] ^ keyBytes[i % keyBytes.length]);
        }

        service.submitData(studentCode, qCode, encoder);
    }
}
