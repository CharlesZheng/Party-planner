package ui;

import model.DecidingGuest;
import model.Guest;
import model.GuestList;
import model.Item;
import persistence.Loader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.ListView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainView extends JFrame implements ActionListener {
    private static final int BUTTON_POSITION = 100;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 20;
    private GuestListView listView;
    private ItemListView itemListView;
    private final GuestList guestList = new GuestList();
    private final GuestList decidingGuestList = new GuestList();
    private final GuestList itemList = guestList;
    private static final String VIEW_COMING_GUEST_LIST_ACTION = "VIEW_COMING_GUEST_LIST_ACTION";
    private static final String VIEW_ITEM_LIST_ACTION = "VIEW_ITEM_LIST_ACTION";
    private static final String EMPTY_LIST_ACTION = "EMPTY_LIST_ACTION";
    private static final String EMPTY_ITEM_LIST_ACTION = "EMPTY_ITEM_LIST_ACTION";
    private static final String LOAD_LISTS = "LOAD_LISTS";
    private static final String QUIT_APP_ACTION = "QUIT_APP_ACTION";


    public MainView() {
        super("Party Planner Application");
        this.setWindow();
        this.setBackgroundImage();
        this.setUpLabelsAndButtons();
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    private void setWindow() {
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

    private void setBackgroundImage() {
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("src/main/ui/image/party1.jpeg"));
            setContentPane(new BackgroundImage(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpLabelsAndButtons() {
        JLabel selectOptionLabel = new JLabel("Please select an option: ", JLabel.CENTER);
        selectOptionLabel.setBounds(26, 10, 300, 20);
        add(selectOptionLabel);
        selectOptionLabel.setForeground(Color.black);

        JButton viewListButton = new JButton("View Coming Guest list");
        viewListButton.setBounds(BUTTON_POSITION, 40, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(viewListButton);
        viewListButton.setActionCommand(VIEW_COMING_GUEST_LIST_ACTION);
        viewListButton.addActionListener(this);
        viewListButton.setForeground(Color.black);

        JButton loadButton = new JButton("Load Lists");
        loadButton.setBounds(BUTTON_POSITION, 80, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(loadButton);
        loadButton.setActionCommand(LOAD_LISTS);
        loadButton.addActionListener(this);
        loadButton.setForeground(Color.black);

        JButton emptyListButton = new JButton("Empty list");
        emptyListButton.setBounds(BUTTON_POSITION, 120, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(emptyListButton);
        emptyListButton.setActionCommand(EMPTY_LIST_ACTION);
        emptyListButton.addActionListener(this);
        emptyListButton.setForeground(Color.black);

        setUpLabelsAndButtons1();
    }

    private void setUpLabelsAndButtons1() {
        JButton viewListButton = new JButton("View Item list");
        viewListButton.setBounds(BUTTON_POSITION, 160, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(viewListButton);
        viewListButton.setActionCommand(VIEW_ITEM_LIST_ACTION);
        viewListButton.addActionListener(this);
        viewListButton.setForeground(Color.black);

        JButton clearItemListButton = new JButton("Empty Item list");
        clearItemListButton.setBounds(BUTTON_POSITION, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(clearItemListButton);
        clearItemListButton.setActionCommand(EMPTY_ITEM_LIST_ACTION);
        clearItemListButton.addActionListener(this);
        clearItemListButton.setForeground(Color.black);

        JButton quitAppButton = new JButton("Quit Party Planner Application and Save All The Information");
        quitAppButton.setBounds(55, 240, 380, BUTTON_HEIGHT);
        add(quitAppButton);
        quitAppButton.setActionCommand(QUIT_APP_ACTION);
        quitAppButton.addActionListener(this);
        quitAppButton.setForeground(Color.black);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(VIEW_COMING_GUEST_LIST_ACTION)) {
            listView = new GuestListView(guestList);
        } else if  (action.equals(VIEW_ITEM_LIST_ACTION)) {
            itemListView = new ItemListView(itemList);
        } else if  (action.equals(LOAD_LISTS)) {
            loadLists();
        } else if (action.equals(EMPTY_LIST_ACTION)) {
            guestList.clearGuestList();
            listView.dispose();
            listView = new GuestListView(guestList);
            JOptionPane.showMessageDialog(null,"Your GuestList has been emptied");
        } else if (action.equals(EMPTY_ITEM_LIST_ACTION)) {
            itemList.clearItemList();
            itemListView.dispose();
            itemListView = new ItemListView(itemList);
            JOptionPane.showMessageDialog(null,"Your Item List has been emptied");
        } else if (action.equals(QUIT_APP_ACTION)) {
            quitAction();
        }
    }

    public void quitAction() {
        if (listView != null) {
            listView.dispose();
            itemListView.dispose();
        }
        dispose();
        guestList.printLog();
    }

    public void loadLists() {
        try {
            Loader.loadGuests(guestList);
            Loader.loadDecidingGuests(decidingGuestList);
            Loader.loadItem(itemList);
        } catch (IOException e) {
            System.out.println("Encountered IOException while loading guest list.");
        }
        JOptionPane.showMessageDialog(null,"Your Lists has been loaded");
    }
}