/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS;

import vn.medianews.*;
import java.util.*;
/**
 *
 * @author Tuan Nghia
 */
public class GyDUdUb0 {
    public static void main(String[] args) {
        String studentCode = "B22DCCN604";
        String qCode = "GyDUdUb0";
        
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        
        List<EmployeeY> employeey = port.requestListEmployeeY(studentCode, qCode);
        
        Collections.sort(employeey, (o1, o2) -> {
            return o1.getStartDate().compare(o2.getStartDate());
        });
        
        port.submitListEmployeeY(studentCode, qCode, employeey);
    }
}
