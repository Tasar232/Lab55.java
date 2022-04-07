package com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees;

public class ComponentPCcourier extends Employee_IT_PC_Company {
    protected static int countOfCuriers = 0;
    protected courierMove howCourierMove;
    protected cityAreaIrkutsk cityArea;




    private enum courierMove {
        CAR,
        KICKSCOOTER,
        AFOOT
    }

    private enum cityAreaIrkutsk {
        SWERDLOWSKY,
        OCKTYBRSKY,
        PRAVOBEREZNY,
        LENENSKY
    }
    //public ComponentPCcourier(String s){

    // }

    public ComponentPCcourier(String fioEmpl, byte experienceEmployee, int HowMove, int cityAreaEm) {


        fio = fioEmpl;

        position = positionEmployee.component_courier;

        experience = experienceEmployee;

        switch (HowMove) {
            case 0:
                howCourierMove = courierMove.CAR;
                break;

            case 1:
                howCourierMove = courierMove.KICKSCOOTER;
                break;

            case 2:
                howCourierMove = courierMove.AFOOT;
                break;
        }

        switch (cityAreaEm) {
            case 0:
                cityArea = cityAreaIrkutsk.SWERDLOWSKY;
                break;

            case 1:
                cityArea = cityAreaIrkutsk.OCKTYBRSKY;
                break;

            case 2:
                cityArea = cityAreaIrkutsk.PRAVOBEREZNY;
                break;

            case 3:
                cityArea = cityAreaIrkutsk.LENENSKY;
                break;
        }

        countOfCuriers++;
    }


    @Override
    public String getSecondInfo(String info) {
        String result = "";
        switch (info){
            case "move":
                switch (howCourierMove) {
                    case CAR:
                        result = String.valueOf(0);
                        break;

                    case KICKSCOOTER:
                        result = String.valueOf(1);
                        break;

                    case AFOOT:
                        result = String.valueOf(2);
                        break;
                }
                break;
            case "area":
                switch (cityArea) {
                    case SWERDLOWSKY:
                        result = String.valueOf(0);
                        break;

                    case OCKTYBRSKY:
                        result = String.valueOf(1);
                        break;

                    case PRAVOBEREZNY:
                        result = String.valueOf(2);
                        break;

                    case LENENSKY:
                        result = String.valueOf(3);
                        break;
                }
                break;
        }
        return result;
    }


    public int getCountOfCuriers() {
        return countOfCuriers;
    }



}
