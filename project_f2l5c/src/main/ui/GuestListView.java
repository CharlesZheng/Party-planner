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
// This class is for setting gust list view

public class GuestListView extends JFrame implements ActionListener {
    private DefaultTableModel tableModel;
    private JTable table;
    private GuestList guestList;
    private static final String ADD_NEW_GUEST_ACTION = "ADD_NEW_GUEST_ACTION";
    private static final String CHANGE_STATUS_OF_A_GUEST = "CHANGE_STATUS_OF_A_GUEST";

    public GuestListView(GuestList guestList) {
        this.guestList = guestList;
        this.setBackgroundImage();
        final String[] columnLabels = new String[] {
                "Index",
                "Name",
                "Status"
        };
        tableModel = new DefaultTableModel(null, columnLabels) {};
        table = new JTable(tableModel);
        this.populateTableRows();

        add(new JScrollPane(table));
        this.setButtons();
        setTitle("my guest list");
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

    //EFFECT; Get all guests from guestList
    private void populateTableRows() {
        for (int i = 0; i < guestList.getSizeOfGuestList(); i++) {
            Guest guest = guestList.getSpecificGuest(i);
            Object[] tableRow = new Object[] {
                    i, // index column
                    guest.getName(), // name column
                    guest.getStatus() // status column
            };
            tableModel.addRow(tableRow);
        }
    }

    //EFFECT; set buttons
    private void setButtons() {
        JButton addItemButton = new JButton(("Add a new Guest"));
        add(addItemButton);
        addItemButton.setActionCommand(ADD_NEW_GUEST_ACTION);
        addItemButton.addActionListener(this);
        addItemButton.setForeground(Color.darkGray);

        JButton changeGuestStatusButton = new JButton(("CHANGE_STATUS_OF_A_GUEST"));
        add(changeGuestStatusButton);
        changeGuestStatusButton.setActionCommand(CHANGE_STATUS_OF_A_GUEST);
        changeGuestStatusButton.addActionListener(this);
        changeGuestStatusButton.setForeground(Color.darkGray);
    }

    // EFFECTS: mark one Guest in the list to change its status as COMING
    public boolean markGuestAsComing(int itemIndex) {
        if (itemIndex >= 0 && itemIndex < guestList.getSizeOfGuestList()) {
            Guest guest = guestList.getSpecificGuest(itemIndex);
            guest.setStatus(Guest.GuestStatus.COMING);
            return true;
        } else {
            System.out.println("Invalid Index");
            return false;
        }
    }

    //EFFECT; save coming Guest;
    public void saver() {
        try {
            Saver.saveComingGuests(guestList);
        } catch (IOException ee) {
            System.out.println("Encountered IOException while loading todo list.");
        }
    }

    //EFFECT; Add functionalities for each button
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(ADD_NEW_GUEST_ACTION)) {
            new AddGuestView(this, guestList);
        }
        saver();
        if (action.equals(CHANGE_STATUS_OF_A_GUEST)) {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(null,"Please select a guest to mark as done.");
                return;
            }
            markGuestAsComing(selectedRowIndex);
            table.setValueAt((Object) guestList.getSpecificGuest(selectedRowIndex).getStatus().name(),
                    selectedRowIndex, 2);
            try {
                Saver.saveComingGuests(guestList);
            } catch (IOException ee) {
                System.out.println("Encountered IOException while loading todo list.");
            }
        }
    }
}
