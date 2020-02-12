// PhoneBookGUI class

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class PhoneBookGUI extends JPanel implements ActionListener  {
    // for errMsg method
    private final int _errMsgNoErr = -1;
    private final int _errMsgEmptyFields = 0;
    private final int _errMsgEmptyPhoneBook = 1;
    private final int _errMsgNotValidName = 2;
    private final int _errMsgNotValidPhone = 3;
    private final int _errMsgNotSelectedContact = 4;

    private JFrame frame;
    private JPanel fullPanel;   // full app panel

    // panel for add and update contact
    private JTextField fullNameFields;
    private JTextField phoneFields;
    private JLabel fullNameLbl;
    private JLabel phoneNameLbl;
    private final int _updateMode = 1;  // use in updateContact() represent update mode
    private final int _addMode = 0; // use in addingContact() represent add mode

    private JPanel tablePanel;
    private JTable table;
    private DefaultTableModel defaultTableModel;
    private final int columnNum = 2;    // number of column in the table
    private String[] columnNames = { "Full Name", "#Telephone" };
    private TableRowSorter sorter;  // sort the table
    private JTextField jtf; // the search text field
    private final JLabel searchLbl = new JLabel("Search: ");;   // search label


    // all the menu bar items
    private  JMenuItem menuItemImport = new JMenuItem("Import file");
    private  JMenuItem menuItemExport = new JMenuItem("Export file");
    private  JMenuItem menuItemExit = new JMenuItem("Exit()");
    private  JMenuItem menuItemAdding = new JMenuItem("Add contact");
    private  JMenuItem menuItemUpdate = new JMenuItem("Update contact");
    private  JMenuItem menuItemRemove = new JMenuItem("Delete contact");
    private  JMenuItem menuItemRemoveAll = new JMenuItem("Delete All contact");
    private  JMenuItem menuItemAbout = new JMenuItem("About");

    // empty constructor
    public PhoneBookGUI()
    {
        String[][] emptyData = new String[0][0];
        defaultTableModel = new DefaultTableModel(emptyData, columnNames);
        runPhoneBookApp();
    }

    // constructor with map
    public PhoneBookGUI(TreeMap map)
    {
        String data[][] = convertWithIteration(map);
        defaultTableModel = new DefaultTableModel(data, columnNames);
        runPhoneBookApp();
    }

    // this method run the program
    private void runPhoneBookApp()
    {
        //Create and set up the window.
        frame = createAndShowGUI();

        // set the menu bar
        JMenuBar menuBar = myMenuBar();
        frame.setJMenuBar(menuBar);

        // setting the JPanel and buttons
        settingJPanel();

        // frame final set up
        frame.add(fullPanel);
        frame.setVisible(true);
    }

    // setting the JPanel
    private void settingJPanel()
    {
        // JPanel set up
        fullPanel = new JPanel(new BorderLayout());   // The full Panel.
        tablePanel = new JPanel();   // the table Panel
        JPanel searchPanel = new JPanel();   // the search Panel

        // the table set up and data
        tablePanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "my PhoneBook", TitledBorder.CENTER, TitledBorder.TOP));
        table = new JTable(defaultTableModel);
        tablePanel.add(new JScrollPane(table));

        // sortable the table
        sorter = new TableRowSorter<>(defaultTableModel);
        table.setRowSorter(sorter);
        sorter.toggleSortOrder(0);  // 0 mean the first column

        // the search option
        jtf = searchField();
        searchPanel.add(searchLbl);
        searchPanel.add(jtf);

        // add to the full Panel
        fullPanel.add(tablePanel, BorderLayout.CENTER);
        fullPanel.add(searchPanel, BorderLayout.SOUTH);
    }

    // search field with DocumentListener
    private JTextField searchField()
    {
        jtf = new JTextField(15);

        jtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(jtf.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(jtf.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(jtf.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });

        return jtf;
    }

    // set up JFrame
    private JFrame createAndShowGUI()
    {
        frame = new JFrame("PhoneBook_GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 550);
        frame.setLocationRelativeTo(null);  // put in the center of the screen

        return frame;
    }

    // adding item to menu bar
    private JMenuBar myMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        menu1.add(menuItemImport);
        menu1.add(menuItemExport);
        menu1.add(menuItemExit);

        menuItemImport.addActionListener(this);
        menuItemExport.addActionListener(this);
        menuItemExit.addActionListener(this);

        JMenu menu2 = new JMenu("Edit");
        menu2.add(menuItemAdding);
        menu2.add(menuItemUpdate);
        menu2.add(menuItemRemove);
        menu2.add(menuItemRemoveAll);
        menuItemAdding.addActionListener(this);
        menuItemUpdate.addActionListener(this);
        menuItemRemove.addActionListener(this);
        menuItemRemoveAll.addActionListener(this);

        JMenu menu3 = new JMenu("Help");
        menu3.add(menuItemAbout);
        menuItemAbout.addActionListener(this);

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);

        return menuBar;
    }

    // convert TreeMap to String[][]
    private String[][] convertWithIteration(TreeMap<?,?> map) {
        String[][] arr = new String[map.size()][columnNum];
        Set entries = map.entrySet();
        Iterator entriesIterator = entries.iterator();

        int i = 0;
        while(entriesIterator.hasNext()){
            Map.Entry mapping = (Map.Entry) entriesIterator.next();

            arr[i][0] = mapping.getKey().toString();
            arr[i][1] = mapping.getValue().toString();

            i++;
        }

        /* arr look like this
            String[][] arr = {
                { "aaa a", "0500000005" },
                { "bbb b", "0500000006" },
                { "ccc c", "0500000007" },
        }; */

        return arr;
    }

    // action button listener
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // File menu items
        if(e.getSource() == menuItemImport)
        {
            importPhoneBook();
        }
        else if(e.getSource() == menuItemExport)
        {
            exportPhoneBook();
        }
        else if(e.getSource() == menuItemExit)
        {
            System.exit(0);
        }

        // Edit menu items
        else if(e.getSource() == menuItemAdding)
        {
            addingContact();
        }
        else if(e.getSource() == menuItemUpdate)
        {
            updateContact();
        }
        else if(e.getSource() == menuItemRemove)
        {
            deleteContact();
        }
        else if(e.getSource() == menuItemRemoveAll)
        {
            deleteAllContact();
        }

        // Help menu items
        else if(e.getSource() == menuItemAbout)
        {
            JOptionPane.showMessageDialog(null, "Thank for check this button!\nPhoneBook version 0.1", "Java - MMN 14b", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            ;

        defaultTableModel.fireTableDataChanged(); // refresh the table!

    }

    // return JPanel for add and update contact
    private JPanel addAndUpdate(int mode)   // 0 = add, 1 = update
    {
        JPanel myPanel = new JPanel(new GridLayout(4,0));

        fullNameFields = new JTextField(15);
        phoneFields = new JTextField(15);
        fullNameLbl = new JLabel("Full Name: ");
        phoneNameLbl = new JLabel("Telephone: ");

        // if this update -> check
        if (mode == _updateMode)
            checkForUpdate();

        myPanel.add(fullNameLbl);
        myPanel.add(fullNameFields);
        myPanel.add(phoneNameLbl);
        myPanel.add(phoneFields);

        return myPanel;
    }

    // help method for update contact
    private void checkForUpdate()
    {
        if(table.getSelectedRow() != -1) {
            int row = table.getSelectedRow();   // the selected row
            table.convertRowIndexToModel( row ); // convert to the original row
            defaultTableModel = (DefaultTableModel)table.getModel();
            String fullNameStr = (String)table.getValueAt(row, 0);
            String PhoneNumberStr = (String)table.getValueAt(row, 1);
            fullNameFields = new JTextField(fullNameStr);
            phoneFields = new JTextField(PhoneNumberStr);
        } else if ((table.getRowCount() > 0) && (table.getSelectedRow() == -1)) {   // not selected contact
            errMsg(_errMsgNotSelectedContact );
            throw new RuntimeException("Not select contact to updated!");
        } else {    // empty phone book
            errMsg(_errMsgEmptyPhoneBook );
            throw new RuntimeException("Empty phone book can't be update contact!");
        }
    }

    // check for valid contact fields
    private boolean validContactFields()
    {
        int errNum = _errMsgNoErr;
        boolean ans = true;

        if (!validName(fullNameFields.getText())) {
            errNum = _errMsgNotValidName ;
            ans = false;
        } else if (!validPhone(phoneFields.getText())) {
            errNum = _errMsgNotValidPhone ;
            ans = false;
        } else
            ;
        errMsg(errNum); // show err msg

        return ans;
    }

    // adding new contact to the phone book
    private void addingContact()
    {
        JPanel addPanel;
        addPanel  = addAndUpdate(_addMode);

        int result = JOptionPane.showConfirmDialog(null, addPanel,
                "Add a new contact", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Vector row = new Vector();
            if (checkForEmpty() && validContactFields()) { // if empty field and valid
                row.add(fullNameFields.getText());
                row.add(phoneFields.getText());
                defaultTableModel.addRow(row);
            } else{
                addingContact();    // try again
            }
        }
    }

    // update contact on the phone book
    private void updateContact()
    {
        JPanel updatePanel;
        try {
            updatePanel  = addAndUpdate(_updateMode);
        }
        catch(Exception e) {    // catch not select contact or empty phone book
            return;
        }

        int result = JOptionPane.showConfirmDialog(null, updatePanel,
                "Update contact", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (checkForEmpty() && validContactFields()) { // if empty field and valid
                int modelRow = getSelectRow();
                defaultTableModel = (DefaultTableModel) table.getModel();
                defaultTableModel.setValueAt(phoneFields.getText(), modelRow, 1);   // 1 = name
                defaultTableModel.setValueAt(fullNameFields.getText(), modelRow, 0);   // 0 = telephone
            } else{
                updateContact();    // try again
            }
        }
    }

    // remove contact from the phone book
    private void deleteContact()
    {
        // check for selected row first
        if(table.getSelectedRow() != -1) {
            int modelRow = getSelectRow();
            defaultTableModel = (DefaultTableModel)table.getModel();
            defaultTableModel.removeRow( modelRow );    // remove selected row from the model

            // output Message
            JOptionPane.showMessageDialog(null, "Selected contact deleted successfully!", "Delete Contact", JOptionPane.WARNING_MESSAGE);
        } else if (table.getRowCount() > 0 && table.getSelectedRow() == -1) {   // not selected contact
            JOptionPane.showMessageDialog(null, "Please select contact to deleted!", "Delete Contact", JOptionPane.ERROR_MESSAGE);
        } else {    // empty phone book
            errMsg(_errMsgEmptyPhoneBook);
        }
    }

    // remove all the contact from phone book
    private void deleteAllContact()
    {
        if(table.getRowCount() > 0) {
            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            dtm.setRowCount(0);
        } else
            errMsg(_errMsgEmptyPhoneBook);
    }

    // return selected row
    private int getSelectRow()
    {
        int row = table.getSelectedRow();   // the selected row
        return table.convertRowIndexToModel( row ); // convert to the original row
    }

    // check if enter empty field in add or update contact
    private boolean checkForEmpty()
    {
        if (fullNameFields.getText().isEmpty() || phoneFields.getText().isEmpty()) {
            errMsg(_errMsgEmptyFields);
            return false;
        }

        return true;
    }

    // import phone book from file
    private void importPhoneBook()
    {
        JFileChooser file = new JFileChooser(".");  // the chooser
        file.showOpenDialog(null);
            if (file.getSelectedFile() != null) {   // if file selected
                String path = file.getSelectedFile().getAbsolutePath(); // get the full path
                String data = importDataFromFile(path); // copy data from file to String
                convertFileToTableRow(data);    // convert String data to row
        }
    }

    // import data contact from file
    private String importDataFromFile(String path)
    {
        String content = "";
        try {
            // NOTE FOR ME: this method is for Java 7, newer version of java may use other method
            content = new String(Files.readAllBytes(Paths.get(path)));
            //System.out.println(path);    // for testing
            //System.out.println(content);    // for testing
            return content;
        } catch (IOException e) {
            e.printStackTrace();    // print err info
        }
        return content;
    }

    // import data from file to row
    private void convertFileToTableRow(String data)
    {
        String fullName = "";
        String telephone = "";
        ArrayList<String> arr = new ArrayList();

        data = data.replaceAll("[\\n\\t ]", "");    // remove white space
        data = data.replaceAll(";", ",");   // fix export file separator

        for (String s : data.split(","))
            arr.add(s);

        int c1 = 0, c2 = 1; // represent the columns
        for(String name:arr) {

            if (c1 < arr.size() && c2 < arr.size()) {
                fullName = arr.get(c1);
                telephone = arr.get(c2);
                // System.out.println("fullname = " + fullName + ", tel = " + telephone); // for testing
                addRowToTable(fullName, telephone);
                c1 += columnNum;
                c2 += columnNum;
            }
        }
    }

    // adding new row from file to table
    private void addRowToTable(String fullName, String telephone)
    {
        if (!fullName.isEmpty() && !telephone.isEmpty())   // if not empty
            defaultTableModel.addRow(new Object[]{fullName, telephone});
    }

    // export the phone book to file
    private void exportPhoneBook()
    {
        if (table.getRowCount() == 0) {  // if empty
            errMsg(_errMsgEmptyPhoneBook);
            return;
        }

        String path = exportWithGUI();

        if (!path.isEmpty()) {
            //System.out.println("Save as file: " + path);
            if (exportToFile(table,path)) // "myPhoneBookData.txt"
                JOptionPane.showMessageDialog(null, "Export contacts successfully!",
                        "Export Contacts", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // file GUI chooser export return String path
    private String exportWithGUI()
    {
        String path = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            return path + ("" + fileToSave.getAbsolutePath());
        }

        return path;
    }

    // export the table to file
    private boolean exportToFile(JTable tableToExport, String pathToExportTo) {
        try {
            TableModel model = tableToExport.getModel();
            FileWriter fName = new FileWriter(new File(pathToExportTo));

            // fName.write(exportColumns(model)); // export the columns
            fName.write(exportRow(model));  // export the rows
            fName.close();  // close the file
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // export table column to String
    private String exportColumns(TableModel model)
    {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < model.getColumnCount(); i++) {
            buffer.append(model.getColumnName(i)).append("|");
        }
        buffer = new StringBuilder(removeLastChar(buffer.toString()));
        buffer.append("\n");

        return buffer.toString();
    }

    // export table row to String
    private String exportRow(TableModel model)
    {
        String buffer = "";
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                buffer += "" + model.getValueAt(i, j).toString() + ",";
            }
            buffer = removeLastChar(buffer);
            buffer += ";\n";
        }

        return buffer;
    }

    // remove the last Char from String
    private static String removeLastChar(String str)
    {
        return str.substring(0, str.length() - 1);
    }

    // validate name
    private boolean validName( String name )
    {
        return name.matches( "[A-Za-z\\s]*" );  // only letters and space
    }

    // validate phone
    private boolean validPhone( String phone )
    {
        return phone.matches("[-\\d\\+*\\+\\++]*");    // only numeric
    }

    // show err msg on GUI
    private void errMsg(int errNum)
    {
        switch(errNum) {
            case _errMsgEmptyFields : // empty fields
                JOptionPane.showMessageDialog(null, "Empty text field!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case _errMsgEmptyPhoneBook: // empty phone book
                JOptionPane.showMessageDialog(null, "Empty Phone book!", "Contact", JOptionPane.ERROR_MESSAGE);
                break;
            case _errMsgNotValidName: // not valid name
                JOptionPane.showMessageDialog(null, "Not valid name!", "Contact", JOptionPane.ERROR_MESSAGE);
                break;
            case _errMsgNotValidPhone: // not valid phone
                JOptionPane.showMessageDialog(null, "Not valid telephone number!", "Contact", JOptionPane.ERROR_MESSAGE);
                break;
            case _errMsgNotSelectedContact: // not selected contact
                JOptionPane.showMessageDialog(null, "Please Select contact!", "Contact", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                // code block
        }
    }
}