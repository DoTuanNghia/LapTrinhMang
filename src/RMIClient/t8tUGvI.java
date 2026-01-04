package RMIClient;

import RMI.DataService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class t8tUGvI {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        String studentCode = "B22DCCN604";
        String qCode = "9t8tUGvI";
        String host = "203.162.10.109";
        int port = 1099;

        Registry registry = LocateRegistry.getRegistry(host, port);

        DataService dataService = (DataService) registry.lookup("RMIDataService");

        Object receive = dataService.requestData(studentCode, qCode);
        int n =  (int)receive;
        System.out.println(n);
        int[] price = {10, 5, 2, 1};
        int cnt = 0;
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < price.length; i++){
            while (n >= price[i]){
                cnt++;
                n -= price[i];
                temp.add(String.valueOf(price[i]));
                System.out.println(n);
            }
        }
        String ans = cnt + "; " + String.join(",", temp);
        System.out.println(ans);
        dataService.submitData(studentCode, qCode, ans);
    }
}
