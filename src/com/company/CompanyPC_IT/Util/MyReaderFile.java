package com.company.CompanyPC_IT.Util;

import com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees.BuilderPC;
import com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees.ComponentPCcourier;
import com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees.Filial;
import com.company.CompanyPC_IT.CompanyPC_IT_Filials_Employees.WebModeratorItemMarket;
import com.company.CompanyPC_IT.view.MyTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Objects;

public class MyReaderFile{

    public static void saveFile(File file, Filial filial){
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(filial.getAddressOrNameFilial("name") + "#" + filial.getAddressOrNameFilial("address") + "\n");
            for(int i = 0; i < filial.getSize(); i++){
                String position = filial.getEmployee(i).getFioPositionExp("position");
                writer.write(position + "#");
                String result = "";
                switch (position){
                    case "0":
                        result = filial.getEmployee(i).getFioPositionExp("fio") + "#" +
                                filial.getEmployee(i).getFioPositionExp("exp") + "#" +
                                filial.getEmployee(i).getFioPositionExp("movecourier") + "#" +
                                filial.getEmployee(i).getFioPositionExp("areacourier") + "\n";
                        writer.write(result);
                        break;
                    case "1":
                        result = filial.getEmployee(i).getFioPositionExp("fio") + "#" +
                                filial.getEmployee(i).getFioPositionExp("exp") + "#" +
                                filial.getEmployee(i).getFioPositionExp("levelbuilder") + "\n";
                        writer.write(result);
                        break;
                    case "2":
                        result = filial.getEmployee(i).getFioPositionExp("fio") + "#" +
                                filial.getEmployee(i).getFioPositionExp("exp") + "#" +
                                filial.getEmployee(i).getFioPositionExp("website") + "\n";
                        writer.write(result);
                        break;
                }
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Filial openFile(File file){
        Filial filial = null;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String name = "";
            String address = "";
            String line = reader.readLine();
            int jIndex = 0;
            for (int i = 0; i < line.length(); i++){
                if (line.charAt(i) != '#'){
                    switch (jIndex){
                        case 0:
                            name += line.charAt(i);
                            break;
                        case 1:
                            address += line.charAt(i);
                            break;
                    }
                }
                else {
                    jIndex++;
                }
            }

            filial = new Filial(name, address);


            line = reader.readLine();
            while (line != null) {
                String position = String.valueOf(line.charAt(0));
                //String result = " ";
                byte exp = 0;
                int j;
                String fio = "";
                switch (position) {
                    case "0":
                        int move = 0;
                        int area = 0;
                        j = 0;
                        for (int i = 2; i < line.length(); i++) {
                            if (line.charAt(i) != '#'){
                                switch (j){
                                    case 0:
                                        fio += line.charAt(i);
                                        break;
                                    case 1:
                                        exp += Integer.parseInt(String.valueOf(line.charAt(i)));
                                        break;
                                    case 2:
                                        move += Integer.parseInt(String.valueOf(line.charAt(i)));
                                        break;
                                    case 3:
                                        area += Integer.parseInt(String.valueOf(line.charAt(i)));
                                        break;
                                }
                            }
                            else {
                                j++;
                            }
                        }
                        filial.addEmployee(new ComponentPCcourier(fio, exp, move, area));
                        break;
                    case "1":
                        int level = 0;
                        j = 0;
                        for (int i = 2; i < line.length(); i++) {
                            if (line.charAt(i) != '#'){
                                switch (j){
                                    case 0:
                                        fio += line.charAt(i);
                                        break;
                                    case 1:
                                        exp += Integer.parseInt(String.valueOf(line.charAt(i)));
                                        break;
                                    case 2:
                                        level += Integer.parseInt(String.valueOf(line.charAt(i)));
                                        break;
                                }
                            }
                            else {
                                j++;
                            }
                        }
                        filial.addEmployee(new BuilderPC(fio, exp, level));
                        break;
                    case "2":
                        int site = 0;
                        j = 0;
                        for (int i = 2; i < line.length(); i++) {
                            if (line.charAt(i) != '#'){
                                switch (j){
                                    case 0:
                                        fio += line.charAt(i);
                                        break;
                                    case 1:
                                        exp += Integer.parseInt(String.valueOf(line.charAt(i)));
                                        break;
                                    case 2:
                                        site += Integer.parseInt(String.valueOf(line.charAt(i)));
                                        break;
                                }
                            }
                            else {
                                j++;
                            }
                        }
                        filial.addEmployee(new WebModeratorItemMarket(fio, exp, site));
                        break;
                }

                line = reader.readLine();
            }
            fileReader.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }


        return filial;
    }

}
