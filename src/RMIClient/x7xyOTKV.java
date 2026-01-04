package RMIClient;

import RMI.ByteService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class x7xyOTKV {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        String studentCode = "B22DCCN604";
        String qCode = "x7xyOTKV";
        String host = "203.162.10.109";
        int port = 1099;

        Registry registry = LocateRegistry.getRegistry(host, port);

        ByteService byteService = (ByteService) registry.lookup("RMIByteService");

        byte[] data = byteService.requestData(studentCode, qCode);
        List<Integer> receive = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            receive.add(Integer.valueOf(data[i]));
        }
        System.out.println(receive);
        List<Integer> chan = new ArrayList<>();
        List<Integer> le = new ArrayList<>();
        byte[] ans = new byte[data.length];
        for (int i = 0; i < receive.size(); i++) {
            if (receive.get(i) % 2 == 0){
                chan.add(receive.get(i));
            }else {
                le.add(receive.get(i));
            }
        }
        for (int i = 0; i < chan.size(); i++) {
            ans[i] = Byte.valueOf(String.valueOf(chan.get(i)));
        }
        for (int i = 0; i < le.size(); i++) {
            ans[i+chan.size()] = Byte.valueOf(String.valueOf(le.get(i)));
        }
        System.out.println(chan);
        System.out.println(le);
        byteService.submitData(studentCode, qCode, ans);
    }
}
