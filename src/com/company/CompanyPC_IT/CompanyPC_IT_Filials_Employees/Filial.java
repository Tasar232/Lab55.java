package com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees;

import java.util.ArrayList;

public class Filial {
    protected String address;
    protected String nameFilial;
    protected ArrayList<Employee_IT_PC_Company> listOfEmployees;

    public Filial(String addressfilial, String name){
        address = addressfilial;
        nameFilial = name;
        listOfEmployees = new ArrayList<Employee_IT_PC_Company>();
    }

    public Filial(Filial filial){
        address = filial.address;
        nameFilial = filial.nameFilial;
        listOfEmployees = new ArrayList<Employee_IT_PC_Company>(filial.listOfEmployees);
    }

    public void showInfoFilial(){
        StringBuilder strBuilder = new StringBuilder("");
        strBuilder.append("Назвние филиала: " + this.nameFilial + "\n");
        strBuilder.append("Адрес: " + this.address);

        System.out.println(strBuilder.toString());
    }

    public void addEmployee(Employee_IT_PC_Company employee){
        this.listOfEmployees.add(employee);
    }

    public void deleteEmployee(int index){
        this.listOfEmployees.remove(index);
    }

    public int getSize() {
        return this.listOfEmployees.size();
    }

    public Employee_IT_PC_Company getEmployee(int index){
        return this.listOfEmployees.get(index);
    }

    public String getAddressOrNameFilial(String suchfield){
        switch (suchfield){
            case "name":
                return this.nameFilial;
            case  "address":
                return this.address;
            default:
                return suchfield;
        }
    }

    public void editEmployee(int index, Employee_IT_PC_Company Empl){
        this.listOfEmployees.set(index, Empl);
    }


   public void deleteAllEmpl(){
        listOfEmployees.clear();
   }
}
