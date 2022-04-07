package com.company.CompanyPC_IT.repository;

import com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees.Employee_IT_PC_Company;
import com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees.Filial;

import java.io.File;
import java.util.List;

public class FileReaderRepository extends Repository{
    Filial filial;

    public FileReaderRepository(File receivedFile){

    }

    @Override
    public List<Employee_IT_PC_Company> getEmployees(String nameFilial) {
        return null;
    }

    @Override
    public void editEmployee(int id, Employee_IT_PC_Company empl) {

    }

    @Override
    public void addEmployee(Employee_IT_PC_Company empl) {

    }

    @Override
    public void deleteEmployee(int id) {

    }
}
