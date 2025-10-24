package RMIClient;

import RMI.DataService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class I6wDpKi5 {
    public static void main(String[] args) throws Exception{
        String studentCode = "B22DCCN604";
        String qCode = "I6wDpKi5";

        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);

        DataService dataService = (DataService) registry.lookup("RMIDataService");

        Object receive = dataService.requestData(studentCode, qCode);
        int amount = (int)receive;

        int[] price = {10, 5, 2, 1};
        int cnt = 0;
        ArrayList<String> res = new ArrayList<>();
        for (Integer p : price) {
            while (amount >= p){
                amount -= p;
                cnt++;
                res.add(String.valueOf(p));
            }
        }

        String ans = cnt + "; " + String.join(",", res);
        dataService.submitData(studentCode, qCode, ans);
    }
}
