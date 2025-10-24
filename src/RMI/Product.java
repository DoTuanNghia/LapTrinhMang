package RMI;

import java.io.Serializable;

public class Product implements Serializable {
    //    Lớp Product gồm các thông tin: id String, code String, importPrice double, exportPrice double.
//    Trường dữ liệu: private static final long serialVersionUID = 20151107L;
//    02 hàm khởi dựng
//    public Product()
//    public Product(id String, String code,double ImportPrice, double ExportPrice)
    private static final long serialVersionUID = 20151107L;
    String id;
    String code;
    double importPrice;
    double exportPrice;

    public Product() {
    }

    public Product(String id, String code, double importPrice, double exportPrice) {
        this.id = id;
        this.code = code;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }
}
