package com.company.CompanyPC_IT.view;

import com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees.*;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

    private int columCount=5;
    private int rowCount;

    private Filial filial ;

    public MyTableModel(){
        filial = new Filial("Сергеева д3", "PC IT");

        //filial.addEmployee(new BuilderPC("Преловский Тимофей Дмитриевич", (byte) 1, 0));
        //filial.addEmployee(new ComponentPCcourier("Леонтьев Александр Александрович", (byte) 0, 1, 1));
        //filial.addEmployee(new BuilderPC("Осадчий Вячеслав Юрьевич", (byte) 0, 1));
        //filial.addEmployee(new BuilderPC("Пилипенко Михаил Михайлович", (byte) 12, 2));
        //filial.addEmployee(new ComponentPCcourier("Бобров Валерий Михайлович", (byte) 5, 2, 3));
        //filial.addEmployee(new WebModeratorItemMarket("Дереза Алексадр Владимирович", (byte) 10, 1));
        //filial.addEmployee(new WebModeratorItemMarket("Потапов Арсений Олегович", (byte) 3, 2));

    }

    @Override
    public int getRowCount() {
        rowCount = filial.getSize();
        //fireTableDataChanged();
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String result = "";
        Employee_IT_PC_Company empl = filial.getEmployee(rowIndex);

        switch (columnIndex){
            case 0:
                result = String.valueOf(rowIndex+1);
                break;
            case 1:
                result = empl.getFioPositionExp("personalNumber");
                break;
            case 2:
                result = empl.getFioPositionExp("fio");
                break;
            case 3:
                switch (Integer.parseInt(empl.getFioPositionExp("position"))){
                    case 0:
                        result = "Курьер комплектующих";
                        break;
                    case 1:
                        result = "Сборщик пк";
                        break;
                    case 2:
                        result = "Веб модератор обьявлений";
                        break;
                }
                break;
            case 4:
                result = empl.getFioPositionExp("exp");
                break;
        }

        return result;
    }

    @Override
    public String getColumnName(int column) {
        String result = "";
        switch (column){
            case 0:
                result = "№";
                break;
            case 1:
                result = "Персональный номер";
                break;
            case 2:
                result = "ФИО";
                break;
            case 3:
                result = "Должность";
                break;
            case 4:
                result = "Стаж";
        }
        return result;
    }

    public String getInfo(String wantInfo, int indexEmpl){
        String result = "";
        switch (wantInfo){
            case "fio":
                result = filial.getEmployee(indexEmpl).getFioPositionExp("fio");
                break;
            case "position":
                result = filial.getEmployee(indexEmpl).getFioPositionExp("position");
                break;
            case "exp":
                result = filial.getEmployee(indexEmpl).getFioPositionExp("exp");
                break;
            case "movecourier":
                result = filial.getEmployee(indexEmpl).getFioPositionExp("movecourier");
                break;
            case "areacourier":
                result = filial.getEmployee(indexEmpl).getFioPositionExp("areacourier");
                break;
            case "levelbuilder":
                result = filial.getEmployee(indexEmpl).getFioPositionExp("levelbuilder");
                break;
            case "website":
                result = filial.getEmployee(indexEmpl).getFioPositionExp("website");
                break;
        }
        return result;
    }

    public void deleteInfo(int indexEmpl){
        filial.deleteEmployee(indexEmpl);
    }

    public void addInfo(String fio, byte exp, int move, int area, int level, int website, int position){
        switch (position){
            case 0:
                filial.addEmployee(new ComponentPCcourier(fio, exp, move, area));
                break;
            case 1:
                filial.addEmployee(new BuilderPC(fio, exp, level));
                break;
            case 2:
                filial.addEmployee(new WebModeratorItemMarket(fio, exp, website));
                break;
        }
    }

    public void editInfo(String fio, byte exp, int move, int area, int level, int website, int position, int indexEmpl){
        switch (position){
            case 0:
                filial.editEmployee(indexEmpl, new ComponentPCcourier(fio, exp, move, area));
                break;
            case 1:
                filial.editEmployee(indexEmpl, new BuilderPC(fio, exp, level));
                break;
            case 2:
                filial.editEmployee(indexEmpl, new WebModeratorItemMarket(fio, exp, website));
                break;
        }
    }

    public int getSizeFilial(){
        return filial.getSize();
    }

    public Filial getFilial(){return this.filial;}

    public void setFilial(Filial newFilial){this.filial = new Filial(newFilial);}

    public void deleteAll(){
        filial.deleteAllEmpl();
    }

}

