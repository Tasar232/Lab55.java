package com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import javafx.embed.swt.SWTFXUtils;

public abstract class Employee_IT_PC_Company {
    /*Три вида работников:
    1 - Тот кто ездит за комплектующими - componentPCcourier
    2 - Тот кто собирает компьютеры - builderPC
    3 - Тот кто выставляет компы на торговые площадки - WebModeratorItemMarket
     */
    protected static int countForPersonalNumberEmployees = 0;
    protected int personalNumber;
    protected String fio;
    protected positionEmployee position;
    protected byte experience;


    public Employee_IT_PC_Company(){
        personalNumber = countForPersonalNumberEmployees;
        countForPersonalNumberEmployees++;
    }

    protected enum positionEmployee {
        component_courier,
        builder_PC,
        website_item_moderator
    }

    public abstract String  getSecondInfo(String info);


    public String  getFioPositionExp(String info){
        String result = "";
        switch (info) {
            case "fio":
                result = this.fio;
                break;
            case "personalNumber":
                result = String.valueOf(this.personalNumber);
                break;
            case "exp":
                result = String.valueOf(this.experience);
                break;
            case "position":
                switch (this.position) {
                    case component_courier:
                        result = "0";
                        break;
                    case builder_PC:
                        result = "1";
                        break;
                    case website_item_moderator:
                        result = "2";
                        break;
                }
                break;
            case "movecourier":
                result = getSecondInfo("move");
                break;
            case "areacourier":
                result = getSecondInfo("area");
                break;
            case "levelbuilder":
                result = getSecondInfo("level");
                break;
            case "website":
                result = getSecondInfo("site");
                break;

        }
        return result;
    }


    //public static int getCountEmployees(){
    //    return countForPersonalNumberEmployees;
    //}

}