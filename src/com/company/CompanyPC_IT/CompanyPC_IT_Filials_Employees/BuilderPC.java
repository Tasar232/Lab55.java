package com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees;

public class BuilderPC extends Employee_IT_PC_Company {
    protected static int countOfbuilders;
    protected byte levelOfSkill;

    public BuilderPC(String fioEmpl, byte experienceEmployee, int levelskill) {
        personalNumber = countForPersonalNumberEmployees;
        countForPersonalNumberEmployees++;

        fio = fioEmpl;

        position = positionEmployee.builder_PC;

        experience = experienceEmployee;

        switch (levelskill){
            case 0:
                levelOfSkill = 0;
                break;

            case 1:
                levelOfSkill = 1;
                break;

            case 2:
                levelOfSkill = 2;
                break;
        }

        countOfbuilders++;
    }




    public int getCountOfbuilders() {
        return countOfbuilders;
    }

    @Override
    public String getSecondInfo(String info) {
        return String.valueOf(this.levelOfSkill);
    }
}
