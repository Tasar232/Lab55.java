package com.company.CompanyPC_IT.repository;

import com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees.Employee_IT_PC_Company;
import com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees.Filial;
import com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees.WebModeratorItemMarket;

import java.io.File;
import java.util.List;

public class TestRepository {
    File file;
    private Filial filial = new Filial("Test", "test");

    public TestRepository(File inputfile){
        file = inputfile;

    }
    //@Override
    public List<Employee_IT_PC_Company> getEmployees(String nameFilial) {

        return null;
    }

    //@Override
    public void editEmployee(int id, Employee_IT_PC_Company empl) {

    }
}
