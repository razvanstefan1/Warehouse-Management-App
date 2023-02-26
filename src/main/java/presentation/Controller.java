package presentation;

import businesslogic.ClientBLL;
import businesslogic.ComandaBLL;
import businesslogic.ProdusBLL;
import dataaccess.ClientDAO;
import dataaccess.ComandaDAO;
import dataaccess.ProdusDAO;
import model.AuxString;
import model.Client;
import model.Comanda;
import model.Produs;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * In aceasta clasa se face traducerea in cod (si executarea acestuia)
 * a actiunilor utilizatorului care foloseste interfata grafica.
 */
public class Controller implements ActionListener {
    private View view;
    public Controller(View v){
        this.view = v;

    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        ClientDAO cln = new ClientDAO();
        ProdusDAO prs = new ProdusDAO();
        ComandaDAO cmn = new ComandaDAO();
        ClientBLL c = new ClientBLL();
        ProdusBLL p = new ProdusBLL();
        ComandaBLL o = new ComandaBLL();
        if(command == "viewClients"){
            JScrollPane t = cln.viewC();
            view.setViewCPane(t);

            view.getClientPanelMain().removeAll();   //sterg tot inainte sa reincarc panel ul (ca sa pot apasa de mai multe ori)
            view.getClientPanelMain().revalidate();
            view.getClientPanelMain().repaint();

            view.getClientPanelMain().add(view.getClientPanel());
            view.getClientPanelMain().add(view.getViewCPane());
            view.getClientPanelMain().revalidate();
        }
        if(command == "viewProducts"){

            JScrollPane t = prs.viewP();
            view.setViewPPane(t);

            view.getProdusPanelMain().removeAll();
            view.getProdusPanelMain().revalidate();
            view.getProdusPanelMain().repaint();

            view.getProdusPanelMain().add(view.getProdusPanel());
            view.getProdusPanelMain().add(view.getViewPPane());
            view.getProdusPanelMain().revalidate();

        }
        if(command == "addClient"){
            String s = view.getClientField().getText();
            Client cl = stringToClient(s);
            c.insertClientBLL(cl);
        }
        if(command == "addProdus"){
            String s = view.getAddPrF().getText();
            Produs pr = stringToProdus(s);
            p.insertProdusBLL(pr);
        }
        if(command == "deleteClient"){
            String s = view.getDelCl().getText();
            int idDel = Integer.parseInt(s);
            c.deletetClientBLL(idDel);
        }
        if(command == "deleteProdus"){
            String s = view.getDelPrF().getText();
            int idDel = Integer.parseInt(s);
            p.deleteProdusBLL(idDel);
        }
        if(command == "editClient"){
            String auxId = view.getIdFieldC().getText();
            int idEd = Integer.parseInt(auxId);
            String atr = String.valueOf(view.getSelectAtrC().getSelectedItem());
            String newVal = view.getEditCl().getText();
            c.editClientBLL(atr,newVal,idEd);
        }
        if(command == "editProdus"){
            String auxId = view.getIdFieldP().getText();
            int idEd = Integer.parseInt(auxId);
            String atr = String.valueOf(view.getSelectAtrP().getSelectedItem());
            String newVal = view.getEditPrF().getText();
            p.editProdusBLL(atr,newVal,idEd);
        }
        if(command == "viewOrders"){
            JScrollPane t = cmn.viewC1();
            view.setViewOPane(t);

            view.getComandaPanelMain().removeAll();
            view.getComandaPanelMain().revalidate();
            view.getComandaPanelMain().repaint();

            view.getComandaPanelMain().add(view.getComandaPanel());
            view.getComandaPanelMain().add(view.getViewOPane());
            view.getComandaPanelMain().revalidate();
        }

        if(command == "loadClPr"){
            AuxString pr = new AuxString(null);
            AuxString cl = new AuxString(null);
            cln.viewCl1(cl);
            String[] cls = cl.getStrings(4);  //ce punem in combo box
            prs.viewPr2(pr);
            String[] prss = pr.getStrings(4);
            JComboBox c1 = new JComboBox(cls);
            JComboBox c2 = new JComboBox(prss);
            view.setClCJ(c1);
            view.setPrCJ(c2);
            view.getComandaPanel().removeAll();
            view.getComandaPanel().add(new JLabel("Client: \n id name address email"));
            view.getComandaPanel().add(new JLabel("Produs: \n id name price quantity"));
            view.getComandaPanel().add(view.getClCJ());
            view.getComandaPanel().add(view.getPrCJ());
            view.getComandaPanel().add(new JLabel("Quantity:"));
            view.getComandaPanel().add(view.getQuantityFld());
            view.getComandaPanel().add(view.getLoadClPr());
            view.getComandaPanel().add(view.getViewOrdersB());
            view.getComandaPanel().add(view.getOrderB());
            view.getComandaPanel().add(view.getMesajOrder());
           // view.getComandaPanel().add(new JLabel("  "));
            view.getComandaPanelMain().revalidate();
            view.getComandaPanelMain().repaint();
        }
        if(command == "orderB"){
            int qty = Integer.parseInt(view.getQuantityFld().getText());
            //scot din combobox uri
            String clData = String.valueOf(view.getClCJ().getSelectedItem());
            String prData = String.valueOf(view.getPrCJ().getSelectedItem());

            String[] auxC = clData.split(" ");
            String[] auxP = prData.split(" ");

            //obtinerea datei curente
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String data = df.format(new Date());
            //
            Comanda cm = new Comanda(123,Integer.parseInt(auxP[0]),
                    Integer.parseInt(auxC[0]),qty,data);
            Produs prod = p.findProdusBLL(Integer.parseInt(auxP[0])," ");  // produsul pe care vreau sa l comand

            if(prod.getQuantity()>=qty) {
                o.insertOrderBLL(cm);
                view.getMesajOrder().setText(" ");
                view.getMesajOrder().append("Comanda realizata cu succes \n");
                prod.setQuantity(-1*qty); // inserez un produs cu cantitatea comandata negata pt a scadea stocul
                p.insertProdusBLL(prod);
                if(prod.getQuantity()-qty==0)  //daca e chiar egala cantitatea, facem in plus o stergere
                {p.deleteProdusBLL(prod.getId());
                }

                //creare fisier
                String saux = new String("id Comanda, id Produs, id Client, cantitate, data:\n");
                saux += cmn.getAttributeValues(cm);
                SimpleDateFormat formatter = new SimpleDateFormat("HH_mm_ss");
                Date date = new Date();
                String sss = formatter.format(date);
                        PrintWriter out = null;
                try {
                    out = new PrintWriter("order_" + cm.getIdClient() + "_at_" + sss + ".txt");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                out.println(saux);
                out.close();
            }else
            {   view.getMesajOrder().setText(" ");
                view.getMesajOrder().append("Stoc insuficient. Comanda nu a fost realizata");}
        }
    }
     public static Client stringToClient(String s){
        String[] ss = s.split(" ");
        Client cl = new Client(123,ss[0],ss[1],ss[2]);
        return cl;
     }
    public static Produs stringToProdus(String s){
        String[] ss = s.split(" ");
        Produs pr = new Produs(123,ss[0],Double.parseDouble(ss[1]),Integer.parseInt(ss[2]));
        return pr;
    }
}
