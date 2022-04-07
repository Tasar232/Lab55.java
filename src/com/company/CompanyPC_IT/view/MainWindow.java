package com.company.CompanyPC_IT.view;

import com.company.CompanyPC_IT.Controllers.ControllerWin;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.*;


public class MainWindow extends JFrame {
    private JTable table;
    private JMenuBar menuBar;
    private JScrollPane jScrollPane;
    private JButton buttonAddEmployee;
    private JPopupMenu popupMenu;

    private ActionListener actionListenerSaveOrOpenFile;
    private MyMainWindowActionListener myMainWindowActionListener = new MyMainWindowActionListener();

    private ControllerWin controllerWinLocal;

    private AddEmplWindow addEmplWindow;
    private EditEmplWindow editEmplWindow;
    private ExitDialogWindow exitDialogWindow;

    public MainWindow(MyTableModel tableModel, ControllerWin controllerWin){
        controllerWinLocal = controllerWin;
        actionListenerSaveOrOpenFile = controllerWin.myActionListenerSaveOrOpenFile;
        setTitle("PC_IT");
        setSize(805, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        addEmplWindow = new AddEmplWindow(controllerWin, tableModel);
        editEmplWindow = new EditEmplWindow(controllerWin, tableModel);

        exitDialogWindow = new ExitDialogWindow(controllerWin);

        menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createAboutProgramMenu());
        setJMenuBar(menuBar);
        table = new JTable();
        table.setModel(tableModel);

        jScrollPane = new JScrollPane(table);
        add(jScrollPane, BorderLayout.CENTER);

        popupMenu = new JPopupMenu();

        JMenuItem editEmpl = new JMenuItem("Полная информация (редактирование)");
        editEmpl.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                int position = Integer.parseInt(tableModel.getInfo("position", i));
                editEmplWindow.lookUpFieldInfo(tableModel, position, i, false);
                editEmplWindow.setVisible(true);
            }
        });

        JMenuItem deleteEmpl = new JMenuItem("Удалить");
        deleteEmpl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                tableModel.deleteInfo(i);
                tableModel.fireTableStructureChanged();
            }
        });

        popupMenu.add(editEmpl);
        popupMenu.add(deleteEmpl);
        table.setComponentPopupMenu(popupMenu);

        popupMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), table));
                        if (rowAtPoint > -1) {
                            table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }
        });

        addWindowListener(myMainWindowActionListener);

        buttonAddEmployee = new JButton("Добавить работника");
        buttonAddEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controllerWin.isFileOpen()) {
                    addEmplWindow.setVisible(true);
                    tableModel.fireTableStructureChanged();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Создайте новый или откройте файл!");
                }
            }
        });
        add(buttonAddEmployee, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);


    }


    private JMenu createFileMenu()
    {
        JMenu file = new JMenu("Файл");
        JMenuItem newFile = new JMenuItem("Новый");
        newFile.addActionListener(actionListenerSaveOrOpenFile);
        JMenuItem openFile = new JMenuItem("Открыть");
        openFile.addActionListener(actionListenerSaveOrOpenFile);
        JMenuItem saveFile = new JMenuItem("Сохранить");
        saveFile.addActionListener(actionListenerSaveOrOpenFile);
        JMenuItem saveAsFile = new JMenuItem("Сохранить как");
        saveAsFile.addActionListener(actionListenerSaveOrOpenFile);
        JMenuItem closeFile = new JMenuItem("Сохранить и закрыть");
        closeFile.addActionListener(actionListenerSaveOrOpenFile);

        file.add(openFile);
        file.add(newFile);
        file.add(saveFile);
        file.add(saveAsFile);
        file.add(closeFile);
        return file;
    }

    private JMenu createAboutProgramMenu()
    {
        // Создание выпадающего меню
        JMenu aboutProg = new JMenu("Справка");
        // Пункт меню "Открыть"
        JMenuItem about = new JMenuItem("О программе");
        // Добавим в меню пункта open
        aboutProg.add(about);
        // Добавление разделителя между полями
        //aboutProg.addSeparator();

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Ну и шо ты сюда залез?" +
                        " Эта прога пятая лаба по ООП, мы тут знакомимся с технологией \nобьектного программирования," +
                        " а не готовый продукт создаем. \n" +
                        "Хочешь знать версию? - да я сам хз, считай 1.хз. \n" +
                        "Ты еще тут? Обнавления не будет!");
            }
        });
        return aboutProg;
    }

    public class MyMainWindowActionListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            if (!controllerWinLocal.isFileSaved()) {
                exitDialogWindow.setVisible(true);
            }
            else {
                System.exit(0);
            }
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