package com.company.CompanyPC_IT.view;

import com.company.CompanyPC_IT.Controllers.ControllerWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ExitDialogWindow extends JFrame {
    private JButton no = new JButton("Нет");
    private JButton yes = new JButton("Да");
    private JButton cancel = new JButton("Отмена");
    private JLabel labelWarning = new JLabel("Вы хотите сохранить изменения?");

    private JPanel contentPane;
    private SpringLayout layout;

    public ExitDialogWindow(ControllerWin controllerWin) {

        setTitle("Внимание!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        layout = new SpringLayout();

        yes.addActionListener(controllerWin.myActionListenerSaveOrOpenFile);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        contentPane.add(labelWarning);
        layout.putConstraint(SpringLayout.WEST , labelWarning, 44,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelWarning, 5,
                SpringLayout.NORTH, contentPane);

        contentPane.add(yes);
        layout.putConstraint(SpringLayout.WEST , yes, 28,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, yes, 35,
                SpringLayout.NORTH, contentPane);
        contentPane.add(no);
        layout.putConstraint(SpringLayout.WEST , no, 98,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, no, 35,
                SpringLayout.NORTH, contentPane);
        contentPane.add(cancel);
        layout.putConstraint(SpringLayout.WEST , cancel, 173,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, cancel, 35,
                SpringLayout.NORTH, contentPane);


        contentPane.setLayout(layout);
        add(contentPane);
        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(300, 130);

    }


}
