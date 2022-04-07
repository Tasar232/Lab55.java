package com.company.CompanyPC_IT.view;

import com.company.CompanyPC_IT.Controllers.ControllerWin;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddEmplWindow extends JFrame {

    private JTextField experienceField = new JTextField(5);
    private JTextField fioField = new JTextField(15);
    private JTextField levelSkillField = new JTextField(5);

    private JLabel fioLabel = new JLabel("ФИО");
    private JLabel experienceLabel = new JLabel("Стаж");
    private JLabel moveCourierLabel = new JLabel("Средство передвижения");
    private JLabel areaCourierLabel = new JLabel("Район города");
    private JLabel levelSkillLabel = new JLabel("Уровень мастерства");
    private JLabel webSiteLabel = new JLabel("Вебсайт");

    private JButton buttonAdd = new JButton("Добавить");


    private JComboBox comboBoxMoveCourier = new JComboBox(new String[] {
            "Машина",
            "Самокат",
            "Пешком"
    });
    private JComboBox comboBoxAreaCourier = new JComboBox(new String[]  {
            "Свердловский",
            "Октябрьский",
            "Правобережный",
            "Ленинский"
    });
    private JComboBox comboBoxWebsiteModer = new JComboBox(new String[] {
            "Авито",
            "ВК обьявления",
            "Юла"
    });
    private JComboBox comboBoxEmplPosition = new JComboBox(new String[] {
            "Курьер комплектующих",
            "Сборщик пк",
            "Веб модератор обьявлений"
    });

    private JPanel contentPane;
    private SpringLayout layout;


    public AddEmplWindow(ControllerWin controllerWin, MyTableModel tableModel){
        setTitle("Добавить работника" );
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        buttonAdd.addActionListener(controllerWin.myActionListenerSaveOrOpenFile);

        contentPane = new JPanel();
        layout = new SpringLayout();
        contentPane.setLayout(layout);
        comboBoxEmplPosition.setEditable(false);
        comboBoxEmplPosition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookUpAddParameters();
            }
        });

        setLocationElements();



        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Objects.equals(fioField.getText(), "") || Objects.equals(experienceField.getText(), "")) {
                        JOptionPane.showMessageDialog(null, "Заполните все поля!");
                    }
                    if (

                            (Byte.parseByte(experienceField.getText()) < 0 || Byte.parseByte(experienceField.getText()) > 100)
                    ) {
                        JOptionPane.showMessageDialog(null, "Проверьте правильность ввода полей!");
                    } else {
                        switch (comboBoxEmplPosition.getSelectedIndex()) {
                            case 0:
                                tableModel.addInfo(fioField.getText(),
                                        Byte.parseByte(experienceField.getText()),
                                        comboBoxMoveCourier.getSelectedIndex(),
                                        comboBoxAreaCourier.getSelectedIndex(),
                                        -1,
                                        -1,
                                        0);
                                break;
                            case 1:
                                tableModel.addInfo(fioField.getText(),
                                        Byte.parseByte(experienceField.getText()),
                                        -1,
                                        -1,
                                        Integer.parseInt(levelSkillField.getText()),
                                        -1,
                                        1);
                                break;
                            case 2:
                                tableModel.addInfo(fioField.getText(),
                                        Byte.parseByte(experienceField.getText()),
                                        -1,
                                        -1,
                                        -1,
                                        comboBoxWebsiteModer.getSelectedIndex(),
                                        2);
                                break;
                        }

                        tableModel.fireTableStructureChanged();
                        setVisible(false);
                    }
                    //ControllerWin.mainMenu.repaintTable();
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Проверьте правильность ввода!");
                }
            }

        });



        //pack();
        add(contentPane);
        add(buttonAdd, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    private void lookUpAddParameters(){

        setUnVisibleElements();

        switch (comboBoxEmplPosition.getSelectedIndex()){
            case 0:
                moveCourierLabel.setVisible(true);
                areaCourierLabel.setVisible(true);
                comboBoxMoveCourier.setVisible(true);
                comboBoxAreaCourier.setVisible(true);
                break;
            case 1:
                levelSkillLabel.setVisible(true);
                levelSkillField.setVisible(true);
                break;
            case 2:
                webSiteLabel.setVisible(true);
                comboBoxWebsiteModer.setVisible(true);
                break;
        }
        setMinimumSize(new Dimension(400,400));
        revalidate();
        repaint();
    }

    private void setUnVisibleElements(){
        experienceField.setVisible(true);
        fioField.setVisible(true);
        levelSkillField.setVisible(false);

        fioLabel.setVisible(true);
        experienceLabel.setVisible(true);
        moveCourierLabel.setVisible(false);
        areaCourierLabel.setVisible(false);
        levelSkillLabel.setVisible(false);
        webSiteLabel.setVisible(false);

        comboBoxMoveCourier.setVisible(false);
        comboBoxAreaCourier.setVisible(false);
        comboBoxWebsiteModer.setVisible(false);
    }


    private void setLocationElements(){
        setUnVisibleElements();

        contentPane.add(comboBoxEmplPosition);
        layout.putConstraint(SpringLayout.WEST , comboBoxEmplPosition, 35,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, comboBoxEmplPosition, 5,
                SpringLayout.NORTH, contentPane);

        contentPane.add(fioLabel);
        contentPane.add(fioField);
        layout.putConstraint(SpringLayout.WEST , fioLabel, 45,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, fioLabel, 35,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, fioField, 35,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , fioField, 20,
                SpringLayout.EAST , fioLabel);

        contentPane.add(experienceField);
        contentPane.add(experienceLabel);
        layout.putConstraint(SpringLayout.WEST , experienceLabel, 45,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, experienceLabel, 65,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, experienceField, 65,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , experienceField, 20,
                SpringLayout.EAST , fioLabel);

        comboBoxMoveCourier.setEditable(true);
        comboBoxAreaCourier.setEditable(true);
        contentPane.add(moveCourierLabel);
        contentPane.add(areaCourierLabel);
        contentPane.add(comboBoxMoveCourier);
        contentPane.add(comboBoxAreaCourier);

        layout.putConstraint(SpringLayout.WEST , moveCourierLabel, 45,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, moveCourierLabel, 95,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, comboBoxMoveCourier, 95,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , comboBoxMoveCourier, 20,
                SpringLayout.EAST , moveCourierLabel);

        layout.putConstraint(SpringLayout.WEST , areaCourierLabel, 45,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, areaCourierLabel, 125,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, comboBoxAreaCourier, 125,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , comboBoxAreaCourier, 20,
                SpringLayout.EAST , areaCourierLabel);

        contentPane.add(levelSkillLabel);
        contentPane.add(levelSkillField);
        layout.putConstraint(SpringLayout.WEST , levelSkillLabel, 45,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, levelSkillLabel, 95,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, levelSkillField, 95,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , levelSkillField, 20,
                SpringLayout.EAST , levelSkillLabel);

        comboBoxWebsiteModer.setEditable(true);
        contentPane.add(comboBoxWebsiteModer);
        contentPane.add(webSiteLabel);

        layout.putConstraint(SpringLayout.WEST , webSiteLabel, 45,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, webSiteLabel, 95,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, comboBoxWebsiteModer, 95,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , comboBoxWebsiteModer, 20,
                SpringLayout.EAST , webSiteLabel);
    }
}
