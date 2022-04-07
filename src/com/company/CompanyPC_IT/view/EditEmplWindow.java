package com.company.CompanyPC_IT.view;

import com.company.CompanyPC_IT.Controllers.ControllerWin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;

public class EditEmplWindow extends JFrame {
    private JTextField experienceField = new JTextField(5);
    private JTextField fioField = new JTextField(20);
    private JTextField levelSkillField = new JTextField(5);

    private JLabel fioLabel = new JLabel("ФИО");
    private JLabel experienceLabel = new JLabel("Стаж");
    private JLabel moveCourierLabel = new JLabel("Средство передвижения");
    private JLabel areaCourierLabel = new JLabel("Район города");
    private JLabel levelSkillLabel = new JLabel("Уровень мастерства");
    private JLabel webSiteLabel = new JLabel("Вебсайт");
    private JLabel cbEditLabel = new JLabel("Редактировать");

    private JButton buttonOkey = new JButton("Окей");
    private JButton buttonSave = new JButton("Сохранить изменения");

    private JCheckBox cbEdit = new JCheckBox();


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

    private MyEditWindowActionListener myEditWindowActionListener = new MyEditWindowActionListener();

    private int position;
    private int indexEmpl;

    public EditEmplWindow(ControllerWin controllerWin, MyTableModel tableModel){

        setTitle("Информация работника");
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(myEditWindowActionListener);

        buttonSave.addActionListener(controllerWin.myActionListenerSaveOrOpenFile);
        buttonOkey.addActionListener(controllerWin.myActionListenerSaveOrOpenFile);

        contentPane = new JPanel();
        layout = new SpringLayout();
        contentPane.setLayout(layout);
        comboBoxEmplPosition.setSelectedIndex(position);
        setLocationElements();

        //lookUpFieldInfo(tableModel, position, indexEmpl, false);

        comboBoxEmplPosition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookUpFieldInfo(tableModel, position, indexEmpl, true);
            }
        });


        cbEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbEdit.isSelected()) {
                    fioField.setEnabled(true);
                    experienceField.setEnabled(true);
                    comboBoxMoveCourier.setEnabled(true);
                    comboBoxAreaCourier.setEnabled(true);
                    levelSkillField.setEnabled(true);
                    comboBoxWebsiteModer.setEnabled(true);
                    comboBoxEmplPosition.setEnabled(true);

                    remove(buttonOkey);
                    add(buttonSave, BorderLayout.SOUTH);

                    revalidate();
                    repaint();
                }
                else {
                    fioField.setEnabled(false);
                    experienceField.setEnabled(false);
                    comboBoxMoveCourier.setEnabled(false);
                    comboBoxAreaCourier.setEnabled(false);
                    levelSkillField.setEnabled(false);
                    comboBoxWebsiteModer.setEnabled(false);
                    comboBoxEmplPosition.setEnabled(false);

                    remove(buttonSave);
                    add(buttonOkey, BorderLayout.SOUTH);

                    revalidate();
                    repaint();
                }
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(fioField.getText(), "") || Objects.equals(experienceField.getText(), "")){
                    JOptionPane.showMessageDialog(null, "Заполните все поля!");
                }
                switch (comboBoxEmplPosition.getSelectedIndex()){
                    case 0:
                        tableModel.editInfo(fioField.getText(),
                                Byte.parseByte(experienceField.getText()),
                                comboBoxMoveCourier.getSelectedIndex(),
                                comboBoxAreaCourier.getSelectedIndex(),
                                -1,
                                -1,
                                0,
                                indexEmpl);
                        break;
                    case 1:
                        tableModel.editInfo(fioField.getText(),
                                Byte.parseByte(experienceField.getText()),
                                -1,
                                -1,
                                Integer.parseInt(levelSkillField.getText()),
                                -1,
                                1,
                                indexEmpl);
                        break;
                    case 2:
                        int i = comboBoxWebsiteModer.getSelectedIndex();
                        tableModel.editInfo(fioField.getText(),
                                Byte.parseByte(experienceField.getText()),
                                -1,
                                -1,
                                -1,
                                comboBoxWebsiteModer.getSelectedIndex(),
                                2,
                                indexEmpl);
                        break;
                }
                tableModel.fireTableStructureChanged();
                //ControllerWin.mainMenu.repaintTable();
            }
        });

        buttonOkey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        add(contentPane);
        add(buttonOkey, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(false);
    }


    public void lookUpFieldInfo(MyTableModel tableModelLocal, int positionLocal, int indexEmplLocal, boolean wantEdit){
        //tableModel = tableModelLocal;
        position = positionLocal;
        indexEmpl = indexEmplLocal;

        setUnVisibleElements();
        if (!wantEdit) {
            comboBoxEmplPosition.setSelectedIndex(positionLocal);
        }
        fioField.setText(tableModelLocal.getInfo("fio", indexEmpl));
        experienceField.setText(tableModelLocal.getInfo("exp", indexEmpl));


        switch (comboBoxEmplPosition.getSelectedIndex()){
            case 0:
                moveCourierLabel.setVisible(true);
                areaCourierLabel.setVisible(true);
                comboBoxMoveCourier.setVisible(true);
                comboBoxAreaCourier.setVisible(true);
                getSecondInfoInField(tableModelLocal, position, indexEmpl, wantEdit);
                break;
            case 1:
                levelSkillLabel.setVisible(true);
                levelSkillField.setVisible(true);
                getSecondInfoInField(tableModelLocal, position, indexEmpl, wantEdit);
                break;
            case 2:
                webSiteLabel.setVisible(true);
                comboBoxWebsiteModer.setVisible(true);
                getSecondInfoInField(tableModelLocal, position, indexEmpl, wantEdit);
                break;
        }
        setMinimumSize(new Dimension(400,400));
        revalidate();
        repaint();

    }

    private void getSecondInfoInField(MyTableModel tableModel, int position, int indexEmpl, boolean wantEdit){
        if (!wantEdit) {
            comboBoxEmplPosition.setEnabled(false);
            fioField.setEnabled(false);
            experienceField.setEnabled(false);
            switch (position) {
                case 0:
                    comboBoxMoveCourier.setSelectedIndex(Integer.parseInt(tableModel.getInfo("movecourier", indexEmpl)));
                    comboBoxAreaCourier.setSelectedIndex(Integer.parseInt(tableModel.getInfo("areacourier", indexEmpl)));
                    comboBoxMoveCourier.setEnabled(false);
                    comboBoxAreaCourier.setEnabled(false);
                    break;
                case 1:
                    levelSkillField.setText(tableModel.getInfo("levelbuilder", indexEmpl));
                    levelSkillField.setEnabled(false);
                    break;
                case 2:
                    int i = Integer.parseInt(tableModel.getInfo("website", indexEmpl));
                    comboBoxWebsiteModer.setSelectedIndex(i);

                    comboBoxWebsiteModer.setEnabled(false);
                    break;
            }
        }
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

        contentPane.add(cbEdit);
        contentPane.add(cbEditLabel);
        cbEdit.setEnabled(true);
        layout.putConstraint(SpringLayout.WEST , cbEditLabel, 45,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, cbEditLabel, 155,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, cbEdit, 155,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , cbEdit, 20,
                SpringLayout.EAST , cbEditLabel);
    }

    private class MyEditWindowActionListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            cbEdit.setSelected(false);
            remove(buttonSave);
            add(buttonOkey, BorderLayout.SOUTH);
            setVisible(false);
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}
