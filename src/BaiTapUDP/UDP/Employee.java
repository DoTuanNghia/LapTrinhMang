package BaiTapUDP.UDP;

import java.io.Serializable;

public class Employee implements Serializable {
//    Tên đầy đủ lớp: BaiTapUDP.UDP.Employee
//    Các thuộc tính: id (String), name (String), salary (double), hireDate (String)
//    Hàm khởi tạo:
//    public Employee(String id, String name, double salary, String hireDate)
//    Trường dữ liệu: private static final long serialVersionUID = 20261107L
    private static final long serialVersionUID = 20261107L;
    String id;
    String name;
    double salary;
    String hireDate;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public Employee(String id, String name, double salary, String hireDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
    }
}
