import java.util.Date;

public class Employee {

    private int employeeIdNumber;
    private String firstName;
    private String lastName;
    private String surName;
    private int department;
    private int annualSalary;


    public Employee(int id, String firstName, String lastName, String surName, int department, int salary) {
        this.employeeIdNumber = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.department = department;
        this.annualSalary = salary;

    }

    public int getId() {
        return employeeIdNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSurName() {
        return surName;
    }
    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return annualSalary;
    }

}


