package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * @Author Stefan Razvan
 * In aceasta clasa sunt create cele 3 JFrame-uri care permit interactiunea cu tabelele Produs,Client, respectiv Comanda.
 */
public class View {
    private JFrame clientF;  ////////
    private JPanel clientPanel,clientPanelMain;
    private JButton addClientB,editClientB,deleteClientB,viewClientsB;
    private JLabel labelCl;
    private JComboBox selectAtrC;
    private JTextField clientField,editCl,delCl, idFieldC;
    private JScrollPane viewCPane;

    private JFrame produsF;  ///////
    private JPanel produsPanel, produsPanelMain;
    private JButton addPrB,editPrB,deletePrB,viewPrB;
    private JComboBox selectAtrP;
    private JTextField addPrF,editPrF,delPrF,idFieldP;
    private JScrollPane viewPPane;


    private JFrame comandaF; ///////
    private JPanel comandaPanel, comandaPanelMain;
    private JComboBox prCJ, clCJ;
    private JButton orderB,viewOrdersB,loadClPr;
    private JTextField quantityFld;
    private JTextField produsFld;
    private JScrollPane viewOPane;
    private JTextArea mesajOrder;
    // private J

    Controller controller = new Controller(this);

    public View(String n){
        String[] atributeC = {"Select the field to update:", "name", "address","email"};
        String[] atributeP = {"Select the field to update:", "name", "price", "quantity"};
        clientF = new JFrame("Client Operations");
        clientF.setSize(400,700);
        clientF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        clientPanelMain = new JPanel(new GridLayout(2,1));
        clientPanel = new JPanel(new GridLayout(10,2));
        addClientB = new JButton("Add Client");
        editClientB = new JButton("Edit Client with ID:");
        deleteClientB=new JButton("Delete Client with ID:");
        viewClientsB = new JButton("View Clients");
        clientField = new JTextField();
        editCl = new JTextField();
        delCl=new JTextField();
        labelCl = new JLabel("Name Address Email");
        selectAtrC = new JComboBox(atributeC);
        idFieldC = new JTextField();
        viewCPane = new JScrollPane();
        clientPanel.add(new JLabel("  "));
        clientPanel.add(labelCl);
        clientPanel.add(addClientB);
        clientPanel.add(clientField);
        clientPanel.add(new JLabel("  "));
        clientPanel.add(new JLabel("  "));

        clientPanel.add(editClientB);
        clientPanel.add(idFieldC);
        clientPanel.add(selectAtrC);
        clientPanel.add(editCl);
        clientPanel.add(new JLabel("  "));
        clientPanel.add(new JLabel("  "));

        clientPanel.add(deleteClientB);
        clientPanel.add(delCl);
        clientPanel.add(new JLabel("  "));
        clientPanel.add(new JLabel("  "));

        clientPanel.add(viewClientsB);
        clientPanel.add(new JLabel("  "));
        clientPanel.add(new JLabel("  "));
        clientPanel.add(new JLabel("  "));

        clientPanelMain.add(clientPanel);
        //clientPanelMain.add(viewCPane);
        clientF.add(clientPanelMain);
        clientF.setVisible(true);


        produsF = new JFrame("Product Operations");
        produsF.setSize(400,700);
        produsF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        produsPanelMain = new JPanel(new GridLayout(2,1));
        produsPanel = new JPanel(new GridLayout(10,2));
        addPrB = new JButton("Add Product");
        editPrB = new JButton("Edit Product with ID:");
        deletePrB = new JButton("Delete Product with ID:");
        viewPrB = new JButton("View products");
        addPrF = new JTextField();
        editPrF = new JTextField();
        delPrF = new JTextField();
        selectAtrP = new JComboBox(atributeP);
        idFieldP = new JTextField();
        viewPPane = new JScrollPane();
        quantityFld = new JTextField();
        produsPanel.add(new JLabel("  "));
        produsPanel.add(new JLabel("Name Price Quantity"));
        produsPanel.add(addPrB);
        produsPanel.add(addPrF);
        produsPanel.add(new JLabel("  "));
        produsPanel.add(new JLabel("  "));

        produsPanel.add(editPrB);
        produsPanel.add(idFieldP);
        produsPanel.add(selectAtrP);
        produsPanel.add(editPrF);
        produsPanel.add(new JLabel("  "));
        produsPanel.add(new JLabel("  "));

        produsPanel.add(deletePrB);
        produsPanel.add(delPrF);
        produsPanel.add(new JLabel("  "));
        produsPanel.add(new JLabel("  "));

        produsPanel.add(viewPrB);
        produsPanel.add(new JLabel("  "));
        produsPanel.add(new JLabel("  "));
        produsPanel.add(new JLabel("  "));

        produsPanelMain.add(produsPanel);
        //  produsPanelMain.add(viewPPane);
        produsF.add(produsPanelMain);
        produsF.setVisible(true);


        comandaF = new JFrame("Order Operations");
        comandaF.setSize(600,700);
        comandaF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        comandaPanel = new JPanel(new GridLayout(5,2));
        orderB = new JButton(" Place Order");
        prCJ = new JComboBox();
        clCJ = new JComboBox();
        viewOrdersB = new JButton("View Orders");
        quantityFld = new JTextField();
        viewOPane = new JScrollPane();
        comandaPanelMain = new JPanel(new GridLayout(2,1));
        loadClPr = new JButton("Load Clients and Products");
        mesajOrder = new JTextArea();
        comandaPanel.add(new JLabel("Client: \n id name address email"));
        comandaPanel.add(new JLabel("Produs: \n id name price quantity"));
        comandaPanel.add(clCJ);
        comandaPanel.add(prCJ);
        comandaPanel.add(new JLabel("Quantity:"));
        comandaPanel.add(quantityFld);
        comandaPanel.add(loadClPr);
        comandaPanel.add(viewOrdersB);
        comandaPanel.add(orderB);
       // comandaPanel.add(new JLabel("  "));
        comandaPanel.add(mesajOrder);
        comandaPanelMain.add(comandaPanel);
     //   comandaPanelMain.add(viewOPane);
        comandaF.add(comandaPanelMain);
        comandaF.setVisible(true);


        addClientB.setActionCommand("addClient");
        addClientB.addActionListener( controller);
        editClientB.setActionCommand("editClient");
        editClientB.addActionListener( controller);
        deleteClientB.setActionCommand("deleteClient");
        deleteClientB.addActionListener( controller);
        viewClientsB.setActionCommand("viewClients");
        viewClientsB.addActionListener( controller);

        //addPrB,editPrB,deletePrB,viewPrB;

        addPrB.setActionCommand("addProdus");
        addPrB.addActionListener( controller);
        editPrB.setActionCommand("editProdus");
        editPrB.addActionListener( controller);
        deletePrB.setActionCommand("deleteProdus");
        deletePrB.addActionListener( controller);
        viewPrB.setActionCommand("viewProducts");
        viewPrB.addActionListener( controller);

        orderB.setActionCommand("orderB");
        orderB.addActionListener( controller);
        viewOrdersB.setActionCommand("viewOrders");
        viewOrdersB.addActionListener( controller);
        loadClPr.setActionCommand("loadClPr");
        loadClPr.addActionListener(controller);

    }


    public JPanel getClientPanel() {
        return clientPanel;
    }

    public JPanel getClientPanelMain() {
        return clientPanelMain;
    }

    public JComboBox getSelectAtrC() {
        return selectAtrC;
    }

    public JTextField getClientField() {
        return clientField;
    }

    public JTextField getEditCl() {
        return editCl;
    }

    public JTextField getDelCl() {
        return delCl;
    }

    public JTextField getIdFieldC() {
        return idFieldC;
    }

    public JScrollPane getViewCPane() {
        return viewCPane;
    }

    public void setViewCPane(JScrollPane viewCPane) {
        this.viewCPane = viewCPane;
    }

    public JPanel getProdusPanel() {
        return produsPanel;
    }

    public JPanel getProdusPanelMain() {
        return produsPanelMain;
    }

    public JComboBox getSelectAtrP() {
        return selectAtrP;
    }

    public JTextField getAddPrF() {
        return addPrF;
    }

    public JTextField getEditPrF() {
        return editPrF;
    }

    public JTextField getDelPrF() {
        return delPrF;
    }

    public JTextField getIdFieldP() {
        return idFieldP;
    }

    public JScrollPane getViewPPane() {
        return viewPPane;
    }

    public void setViewPPane(JScrollPane viewPPane) {
        this.viewPPane = viewPPane;
    }

    public JPanel getComandaPanel() {
        return comandaPanel;
    }

    public JPanel getComandaPanelMain() {
        return comandaPanelMain;
    }

    public JComboBox getPrCJ() {
        return prCJ;
    }

    public void setPrCJ(JComboBox prCJ) {
        this.prCJ = prCJ;
    }

    public JComboBox getClCJ() {
        return clCJ;
    }

    public void setClCJ(JComboBox clCJ) {
        this.clCJ = clCJ;
    }

    public JButton getOrderB() {
        return orderB;
    }

    public JButton getViewOrdersB() {
        return viewOrdersB;
    }

    public JButton getLoadClPr() {
        return loadClPr;
    }

    public JTextField getQuantityFld() {
        return quantityFld;
    }

    public JScrollPane getViewOPane() {
        return viewOPane;
    }

    public void setViewOPane(JScrollPane viewOPane) {
        this.viewOPane = viewOPane;
    }

    public JTextArea getMesajOrder() {
        return mesajOrder;
    }

}
