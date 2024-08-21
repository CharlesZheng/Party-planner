package ui;

import model.Guest;
import model.GuestList;
import model.Item;
import persistence.Saver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
//This class is for setting add item window

public class AddItemView extends JFrame implements ActionListener {
    JTextField itemNameField;
    JTextField itemPriceField;
    ItemListView itemListView;
    GuestList guestList;
    private static final String FINISH_ACTION = "FINISH_ACTION";

    public AddItemView(ItemListView listView, GuestList todoList) {
        super("Add an Item");
        this.itemListView = listView;
        this.guestList = todoList;
        this.setWindow();

        this.setLabelsFieldsButtons();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //EFFECT; set the background window
    private void setWindow() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

    //EFFECT; set buttons
    private void setLabelsFieldsButtons() {
        JLabel itemNameLabel = new JLabel("Enter Item name: ");
        itemNameLabel.setBounds(48, 40, 400, 20);
        add(itemNameLabel);
        itemNameLabel.setForeground(Color.darkGray);

        itemNameField = new JTextField(30);
        itemNameField.setBounds(50, 70, 300, 20);
        add(itemNameField);

        JLabel itemStatusLabel = new JLabel("Enter Item's Price");
        itemStatusLabel.setBounds(50, 100, 600, 20);
        add(itemStatusLabel);
        itemStatusLabel.setForeground(Color.darkGray);

        itemPriceField = new JTextField(30);
        itemPriceField.setBounds(50, 130,300,20);
        add(itemPriceField);

        JButton finishButton = new JButton("Finish");
        finishButton.setBounds(310,210,100,20);
        add(finishButton);
        finishButton.setActionCommand(FINISH_ACTION);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.darkGray);
    }

    //EFFECT; Add functionalities for each button
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(FINISH_ACTION)) {
            String name = itemNameField.getText();
            int price = Integer.parseInt(itemPriceField.getText());
            guestList.addItem(new Item(name, price));
            try {
                Saver.saveItem(guestList);
            } catch (IOException ee) {
                System.out.println("Encountered IOException while loading Item list.");
            }
            itemListView.dispose();
            new ItemListView(guestList);
            dispose();
        }
    }
}
