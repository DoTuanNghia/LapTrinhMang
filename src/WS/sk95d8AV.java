/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS;

import java.util.*;
import vn.medianews.*;
/**
 *
 * @author Tuan Nghia
 */
public class sk95d8AV {
    public static void main(String[] args) {
        String studentCode = "B22DCCN604";
        String qCode = "sk95d8AV";
        
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        
        List<Integer> list = port.getData(studentCode, qCode);
        
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            ans.add(binary(list.get(i)));
        }
        
        port.submitDataStringArray(studentCode, qCode, ans);
    }
    public static String binary(int n){
        String s = "";
        while (n != 0){
            if (n % 2 == 0){
                s = "0" + s;
            }else{
                s = "1" + s;
            }
            n /= 2;
        }
        return s;
    }
}
