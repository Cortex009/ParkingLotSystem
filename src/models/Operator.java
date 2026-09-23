package models;

public class Operator {
    private long id;
    private String employeeId;
    private String name;
    public Operator(long id,String employeeId, String name){
        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }
}
