package com.company.CompanyPC_IT.Controllers;

import com.company.CompanyPC_IT.view.MainWindow;
import com.company.CompanyPC_IT.Util.MyReaderFile;
import com.company.CompanyPC_IT.view.MyTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class ControllerWin {
    protected static MainWindow mainMenu;
    private MyTableModel tableModel;

    public MyActionListenerSavedOrOpen myActionListenerSaveOrOpenFile = new MyActionListenerSavedOrOpen();

    //Что касается открытого файла
    private String pathFileIsOpen;
    public  boolean isFileOpen(){
        boolean result;
        if(pathFileIsOpen == null){
            result = false;
        }
        else {
            result = true;
        }
        return result;
    }
    private boolean isFileSaved = true;
    public boolean isFileSaved(){return isFileSaved;}

    public  void startingWindow() {
        tableModel = new MyTableModel();
        mainMenu = new MainWindow(tableModel, this);
    }

    private void saveOrOpen (String command){
        JFileChooser jC;
        switch (command) {
            case "open":
                if (isFileSaved) {
                    jC = new JFileChooser(new File(System.getProperty("user.dir")));
                    if (jC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        tableModel.deleteAll();
                        String path = jC.getSelectedFile().getAbsolutePath();
                        pathFileIsOpen = path;
                        File file = new File(path);
                        tableModel.setFilial(MyReaderFile.openFile(file));
                        tableModel.fireTableStructureChanged();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Сохраните открытый файл!");
                }
                break;
            case "save":
                if (pathFileIsOpen != null){
                    File file = new File(pathFileIsOpen);
                    MyReaderFile.saveFile(file, tableModel.getFilial());
                    isFileSaved = true;
                    JOptionPane.showMessageDialog(null, "Файл сохранен!");
                }
                break;
            case "saveAs":
                jC = new JFileChooser(new File(System.getProperty("user.dir")));
                if (jC.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String path = jC.getSelectedFile().getAbsolutePath();
                    File file = new File(path + ".txt");
                    MyReaderFile.saveFile(file, tableModel.getFilial());
                    isFileSaved = true;
                }
                break;
            case "new":
                jC = new JFileChooser(new File(System.getProperty("user.dir")));
                if (jC.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String path = jC.getSelectedFile().getAbsolutePath();
                    File file = new File(path + ".txt");
                    isFileSaved = false;
                    pathFileIsOpen = path;
                }
        }
    }

    private class MyActionListenerSavedOrOpen implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent el) {
            String flag = el.getActionCommand();
            switch (flag) {
                case "Открыть":
                    saveOrOpen("open");
                    break;
                case "Сохранить":
                    saveOrOpen("save");
                    break;
                case "Сохранить как":
                    saveOrOpen("saveAs");
                    break;
                case "Добавить":
                    isFileSaved = false;
                    break;
                case "Сохранить изменения":
                    isFileSaved = false;
                    break;
                case "Да":
                    saveOrOpen("save");
                    break;
                case "Новый":
                    saveOrOpen("new");
                    break;
                case "Сохранить и закрыть":
                    saveOrOpen("save");
                    tableModel.deleteAll();
                    tableModel.fireTableStructureChanged();
                    break;
            }
        }

    }



}
