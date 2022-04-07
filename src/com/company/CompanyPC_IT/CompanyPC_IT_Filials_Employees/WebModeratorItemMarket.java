package com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees;

public class WebModeratorItemMarket extends Employee_IT_PC_Company {
    protected static int countOfWbers;
    protected webSiteWork webSite;



    private enum webSiteWork {
        AVITO,
        VK_BE_USE_GROUPS,
        ULA,
    }

    public WebModeratorItemMarket(String fioEmpl, byte experienceEmployee, int website){
        personalNumber = countForPersonalNumberEmployees;
        countForPersonalNumberEmployees++;

        fio = fioEmpl;

        position = positionEmployee.website_item_moderator;

        experience = experienceEmployee;

        switch (website){
            case 0:
                webSite = webSiteWork.AVITO;
                break;

            case 1:
                webSite = webSiteWork.VK_BE_USE_GROUPS;
                break;

            case 2:
                webSite = webSiteWork.ULA;
                break;
        }

        countOfWbers++;
    }

    @Override
    public String getSecondInfo(String info) {
        String result = "";
        switch (this.webSite){
            case AVITO:
                result = String.valueOf(0);
                break;

            case VK_BE_USE_GROUPS:
                result = String.valueOf(1);
                break;

            case ULA:
                result = String.valueOf(2);
                break;
                //
        }
        return result;
    }



    public int getCountOfWbers() {
        return countOfWbers;
    }


}
