package com.company.CompanyPC_IT.repository;

import com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees.Employee_IT_PC_Company;

import java.util.List;

public abstract class Repository {
    public abstract List<Employee_IT_PC_Company> getEmployees(String nameFilial);
    public abstract void editEmployee(int id, Employee_IT_PC_Company empl);
    public abstract void addEmployee(Employee_IT_PC_Company empl);
    public abstract void deleteEmployee(int id);



}
