public class Employee1 {
    // private field's
    private String FIO;
    private int Department;
    private float Salary;
    private static int Counter;
    private int Id;

    // Getter's
    public int getId() { return this.Id; }
    public String getFIO() { return this.FIO; }
    public int getDepartment() { return this.Department; }
    public float getSalary() { return this.Salary; }

    // Setter's
    public void SetFIO(String fio) { this.FIO = fio; }
    public void SetDepartment(int dept) { this.Department = dept; }
    public void SetSalary(float sal) { this.Salary = sal; }

    // Constructor
    public Employee1(String fio, int dept, float salary) {
        FIO = fio;
        Department = dept;
        Salary = salary;
        Id = ++Counter;
    }

    @Override
    public String toString() {
        return "Id: " + Id + " Fio: " + FIO + " Dept: " + Department + " Salary: " + Salary;
    }

    public static void main(String[] args) {
        Employee1[] empl = new Employee1[3];
        empl[0] = new Employee1("Fam1 Name1 SName1", 1, 25500f);
        empl[1] = new Employee1("Fam2 Name2 SName2", 2, 31450f);
        empl[2] = new Employee1("Fam3 Name3 SName3", 3, 40200f);

        for (Employee1 e: empl) {
            System.out.println(e);
        }
    }
}
