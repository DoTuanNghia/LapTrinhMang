package RMIClient;

import RMI.ObjectService;
import RMI.Product;
import RMI.ProductX;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class YsVbQWbI {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN244";
        String qCode = "YsVbQWbI";

        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);

        ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService");

        Product product = (Product) objectService.requestObject(studentCode, qCode);

        product.setCode(chuanHoa(product.getCode()));
        product.setExportPrice(price(product.getImportPrice()));

        objectService.submitObject(studentCode, qCode, product);
    }
    public static String chuanHoa(String s){
        return s.toUpperCase();
    }

    public static double price(double importPrice) {
        return importPrice + 1.0*importPrice*20/100;
    }
}
