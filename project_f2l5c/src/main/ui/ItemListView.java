package ui;

import model.Guest;
import model.GuestList;
import model.Item;
import persistence.Saver;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ItemListView extends JFrame implements ActionListener {
    private DefaultTableModel tableModel;
    private JTable table;
    private GuestList itemList;
    private int number;
    private static final String ADD_ITEM_LIST_ACTION = "ADD_ITEM_LIST_ACTION";
    private static final String SPLIT_THE_BILL = "SPLIT_THE_BILL";

    public ItemListView(GuestList guestList) {
        this.itemList = guestList;
        this.setBackgroundImage();
        final String[] columnLabels = new String[] {
                "Index",
                "Name",
                "Price"
        };
        tableModel = new DefaultTableModel(null, columnLabels) {};
        table = new JTable(tableModel);
        this.populateTableRows();

        add(new JScrollPane(table));
        this.setButtons();
        setTitle("my item list");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    //EFFECT; set the background window
    private void setBackgroundImage() {
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("src/main/ui/image/bg2.JPG"));
            setContentPane(new BackgroundImage(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECT; Get all items from guestList
    private void populateTableRows() {
        for (int i = 0; i < itemList.getSizeOfItemList(); i++) {
            Item item = itemList.getSpecificItem(i);
            Object[] tableRow = new Object[] {
                    i, // index column
                    item.getName(), // name column
                    item.getPrice() // status column
            };
            tableModel.addRow(tableRow);
        }
    }

    //EFFECT; set buttons
    private void setButtons() {
        JButton addItemButton = new JButton(("Add a new Item"));
        add(addItemButton);
        addItemButton.setActionCommand(ADD_ITEM_LIST_ACTION);
        addItemButton.addActionListener(this);
        addItemButton.setForeground(Color.darkGray);

        JButton average = new JButton(("split the bill"));
        add(average);
        average.setActionCommand(SPLIT_THE_BILL);
        average.addActionListener(this);
        average.setForeground(Color.darkGray);
    }


    //EFFECT; Add functionalities for each button
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(ADD_ITEM_LIST_ACTION)) {
            new AddItemView(this, itemList);
        }
        try {
            Saver.saveItem(itemList);
        } catch (IOException ee) {
            System.out.println("Encountered IOException while loading todo list.");
        }
        if (action.equals(SPLIT_THE_BILL)) {
            int a = 0;
            for (Item b : itemList.getItems()) {
                a += b.getPrice();
            }
            number = 0;
            number = a / (itemList.getSizeOfGuestList());
            String s = Integer.toString(number);
            JOptionPane.showMessageDialog(null,"Each Guest needs to pay" + s);
        }
    }
}
