package RMIClient;

import RMI.ObjectService;
import RMI.ProductX;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class OJSTIoOv {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN604";
        String qCode = "OJSTIoOv";

        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);

        ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService");

        ProductX productX = (ProductX) objectService.requestObject(studentCode, qCode);
        String discountCode =  productX.getDiscountCode();
        int sum = 0;
        for (int i = 0; i < discountCode.length(); i++) {
            if(Character.isDigit(discountCode.charAt(i))){
                sum += Integer.parseInt(String.valueOf(discountCode.charAt(i)));
            }
        }
        productX.setDiscount(sum);

        objectService.submitObject(studentCode, qCode, productX);
    }
}
