package org.example.Presentation;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.lang.reflect.Field;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.example.BusinessLogic.ClientBll;
import org.example.BusinessLogic.ExceptiePentruStock;
import org.example.BusinessLogic.OrdersBll;
import org.example.BusinessLogic.ProductBll;
import org.example.Model.Client;
import org.example.Model.Orders;
import org.example.Model.Product;

public final class View extends JFrame implements ActionListener {

    @Serial
    private static final long serialVersionUID = 1L;
    public static final String RESULT_QUERY = "Rezutatul interogarii";
    public static final String UPDATE_PRODUCT = "Update la produs";
    public static final String DELETE = "Stergere";
    public static final String CONDITION_VALUE = "Seteaza Valoarea:          ";
    public static final String PLASEAZA_O_COMANDA = "Plaseaza o comanda";
    GridBagConstraints gridConstraints = new GridBagConstraints();
    private final JButton button1 = new JButton("Adauga");
    private final JButton button2 = new JButton("Editeaza");
    private final JButton button3 = new JButton("Sterge");
    private final JButton button4 = new JButton("Vezi tot");
    private final JButton button5 = new JButton("Vezi unul singur");
    private final JButton button6 = new JButton(PLASEAZA_O_COMANDA);

    String[] optionTable = {"Client","Produs"};
    JList<String> optionList =new JList<>(optionTable);

    private void Stergere(JDialog dial, JLabel labelSterge1, JLabel labelSterge2, JTextField text1, JList<String> optionSet, JButton updateButton) {
        sterge(dial, labelSterge1, labelSterge2, text1, optionSet);
        gridConstraints.gridy = 4;
        dial.add(updateButton, gridConstraints);
    }
    private void sterge(JDialog dial, JLabel labelSterge1, JLabel labelSterge2, JTextField text1, JList<String> optionSet) {
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        dial.add(labelSterge1, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        dial.add(optionSet, gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        dial.add(labelSterge2, gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        dial.add(text1, gridConstraints);
        gridConstraints.gridx = 0;
    }
    private void Upgrade(JDialog dial, JLabel labelUpgrade1, JLabel labelUpgrade2, JTextField text1, JTextField text2, JList<String> optionSet, JList<String> optionWhere, JButton updateButton) {
        sterge(dial, labelUpgrade1, labelUpgrade2, text1, optionSet);
        gridConstraints.gridy = 2;
        dial.add(new JLabel("Conditie coloana"), gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        dial.add(optionWhere, gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        dial.add(new JLabel("Conditie valoare"), gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 3;
        dial.add(text2, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 4;
        dial.add(updateButton, gridConstraints);
    }
    private void Resultat(JPanel paneRezultat1, JPanel paneRezultat2, JDialog dial) {
        dial.setLayout(new GridBagLayout());
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        dial.add(paneRezultat1, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        dial.add(paneRezultat2, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
    }
    private void Inserare(JPanel paneInserare1, JPanel paneInserare2, JLabel labelInserare1, JLabel labelInserare2, JLabel labelInserare3, JLabel labelInserare4, JLabel labelInserare5, JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5) {
        paneInserare1.add(labelInserare1);
        paneInserare1.add(labelInserare2);
        paneInserare1.add(labelInserare3);
        paneInserare1.add(labelInserare4);
        paneInserare1.add(labelInserare5);

        paneInserare2.add(text1);
        paneInserare2.add(text2);
        paneInserare2.add(text3);
        paneInserare2.add(text4);
        paneInserare2.add(text5);
    }
    public static String[] getNumeColoana(Object object) {
        String []numeColoana=new String[20];
        int i=0;
        for(Field field:object.getClass().getDeclaredFields()) {
            numeColoana[i]=field.getName();
            i++;
        }
        return numeColoana;
    }

    public <T> void getProprietati(List<T> lista) {
        DefaultTableModel model = new DefaultTableModel();
        int i=0;
        int ok=1;
        for(Object object:lista) {
            String[] proprietati =new String[100];
            for(Field field:object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value=field.get(object);
                    if(ok==1)
                        model.addColumn(field.getName());
                    proprietati[i]=value.toString();
                    i++;
                }
                catch(IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(proprietati);
            i=0;
            ok=0;
        }
        JTable j=new JTable(model);
        JScrollPane sp = new JScrollPane(j);

        JDialog d = new JDialog(this, RESULT_QUERY);
        d.add(sp);
        d.setSize(700,500);
        d.setVisible(true);
    }

    public View(String name) {
        super(name);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        JLabel label1 = new JLabel("Selectati un tabel pentru operatii");
        JPanel pane2 = new JPanel(new GridBagLayout());
        pane2.add(label1, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        optionList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pane2.add(optionList, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        button1.addActionListener(this);
        JPanel pane1 = new JPanel(new GridBagLayout());
        pane1.add(button1, gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        button2.addActionListener(this);
        pane1.add(button2, gridConstraints);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        button3.addActionListener(this);
        pane1.add(button3, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        button4.addActionListener(this);
        pane1.add(button4, gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        button5.addActionListener(this);
        pane1.add(button5, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        JPanel pane3 = new JPanel(new GridBagLayout());
        pane3.add(pane2, gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        pane3.add(pane1, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        JPanel mainPane = new JPanel(new GridBagLayout());
        mainPane.add(pane3, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        button6.addActionListener(this);
        pane3.add(button6, gridConstraints);
        this.add(mainPane);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        Object source = arg0.getSource();
        if(source == button1){

            int optiune= optionList.getSelectedIndex();
            System.out.println(optiune);
            JPanel pane1 = new JPanel(new FlowLayout());
            JPanel pane2 = new JPanel(new FlowLayout());
            JLabel labelId= new JLabel("ID    ");
            JLabel labelNume = new JLabel("Nume  ");
            JLabel labelAresa = new JLabel("Adresa    ");
            JLabel labelEmail = new JLabel("Email ");
            JLabel labelVarsta = new JLabel("Varsta    ");
            JLabel labelStoc = new JLabel("Stoc  ");

            JTextField text1 = new JTextField(3);
            JTextField text2 = new JTextField(8);
            JTextField text3 = new JTextField(8);
            JTextField text4 = new JTextField(8);
            JTextField text5 = new JTextField(8);
            JTextField text6 = new JTextField(4);
            JButton insereazaClientul = new JButton("Insereaza Client");
            JButton insereazaProdusul = new JButton("Insereaza Produs");

            if(optiune==0) {
                Inserare(pane1, pane2,labelId , labelNume, labelAresa, labelEmail, labelVarsta, text1, text2, text3, text4, text5);
            }
            if(optiune==1) {
                pane1.add(labelId);
                pane1.add(labelNume);
                pane1.add(labelStoc);
                pane2.add(text1);
                pane2.add(text2);
                pane2.add(text6);
            }
            JDialog dial = new JDialog(this, RESULT_QUERY);
            Resultat(pane1, pane2, dial);
            if(optiune==0) {
                dial.add(insereazaClientul, gridConstraints);
            }
            if(optiune==1) {
                dial.add(insereazaProdusul, gridConstraints);
            }
            insereazaClientul.addActionListener(e -> {
                try {
                    (new ClientBll()).insertClient(new Client(Integer.parseInt(text1.getText()),text2.getText(),text3.getText(),text4.getText(),Integer.parseInt(text5.getText())));
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            });
            insereazaProdusul.addActionListener(e -> {
                try {
                    (new ProductBll()).insertProduct(new Product(Integer.parseInt(text1.getText()),text2.getText(),Integer.parseInt(text6.getText())));
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            });
            dial.setSize(600,400);
            dial.setVisible(true);
        }

        if(source == button2){
            int optiune= optionList.getSelectedIndex();
            JDialog dial = new JDialog(this, "Update");
            dial.setLayout(new GridBagLayout());
            JLabel labelCl1 = new JLabel("Update coloana    ");
            JLabel labelCl2 = new JLabel("Alege Valoarea:          ");
            JTextField text1 = new JTextField(8);
            JTextField text2 = new JTextField(8);

            if(optiune==0) {
                String[] optionTableSet = getNumeColoana(new Client());
                JList<String> optSet=new JList<>(optionTableSet);
                String[] optionTableWhere = getNumeColoana(new Client());
                JList<String> optWhere=new JList<>(optionTableWhere);
                optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                optWhere.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JButton updateButton = new JButton("Update Client");
                Upgrade(dial, labelCl1, labelCl2, text1, text2, optSet, optWhere, updateButton);
                updateButton.addActionListener(e -> {
                    String optiuneSet= optSet.getSelectedValue();
                    String optiuneWhere= optWhere.getSelectedValue();
                    (new ClientBll()).updateClient(optiuneSet,text1.getText(),optiuneWhere,text2.getText());
                });
            }
            if(optiune==1) {
                String[] optionTableSet = getNumeColoana(new Product());
                JList<String> optSet=new JList<>(optionTableSet);
                String[] optionTableWhere = getNumeColoana(new Product());
                JList<String> optWhere=new JList<>(optionTableWhere);
                optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                optWhere.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JButton updateButton = new JButton(UPDATE_PRODUCT);
                Upgrade(dial, labelCl1, labelCl2, text1, text2, optSet, optWhere, updateButton);
                updateButton.addActionListener(e -> {
                    String optiuneSet= optSet.getSelectedValue();
                    String optiuneWhere= optWhere.getSelectedValue();
                    (new ProductBll()).updateProduct(optiuneSet,text1.getText(),optiuneWhere,text2.getText());
                });
            }
            dial.setSize(600,400);
            dial.setVisible(true);
        }

        if(source == button3){
            int optiune= optionList.getSelectedIndex();
            JDialog dial = new JDialog(this, DELETE);
            dial.setLayout(new GridBagLayout());
            JLabel labelCl1 = new JLabel("Conditia pentru stergere:     ");
            JLabel labelCl2 = new JLabel(CONDITION_VALUE);
            JTextField text1 = new JTextField(8);

            if(optiune==0) {
                String[] optionTableSet = getNumeColoana(new Client());
                JList<String> optSet=new JList<>(optionTableSet);
                optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JButton updateButton = new JButton(DELETE);
                Stergere(dial, labelCl1, labelCl2, text1, optSet, updateButton);
                updateButton.addActionListener(e -> {
                    String optiuneSet= optSet.getSelectedValue();
                    (new ClientBll()).deleteClient(optiuneSet,text1.getText());
                });
            }
            if(optiune==1) {
                String[] optionTableSet = getNumeColoana(new Product());
                JList<String> optSet=new JList<>(optionTableSet);
                optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JButton updateButton = new JButton(UPDATE_PRODUCT);

                Stergere(dial, labelCl1, labelCl2, text1, optSet, updateButton);

                updateButton.addActionListener(e -> {
                    String optiuneSet= optSet.getSelectedValue();
                    (new ProductBll()).deleteProduct(optiuneSet,text1.getText());
                });
            }
            dial.setSize(600,400);
            dial.setVisible(true);
        }


        if(source == button4){

            int optiune= optionList.getSelectedIndex();
            if(optiune==0) {
                List<Client> lista=(new ClientBll()).findAll();
                this.getProprietati(lista);
            }
            if(optiune==1) {
                List<Product> lista=(new ProductBll()).findAll();
                this.getProprietati(lista);
            }
        }

        if(source == button5){
            int optiune= optionList.getSelectedIndex();
            JDialog dial = new JDialog(this, "Cautare");
            dial.setLayout(new GridBagLayout());
            JLabel labelCl1 = new JLabel("Conditia pentru cautare   ");
            JLabel labelCl2 = new JLabel(CONDITION_VALUE);
            JTextField text1 = new JTextField(8);  //setVal

            if(optiune==0) {
                String[] optionTableSet = getNumeColoana(new Client());
                JList<String> optSet=new JList<>(optionTableSet);
                optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JButton findButton = new JButton("Find");
                Stergere(dial, labelCl1, labelCl2, text1, optSet, findButton);
                findButton.addActionListener(e -> {
                    String optiuneSet= optSet.getSelectedValue();
                    List<Client> lista=(new ClientBll()).findClient(optiuneSet,text1.getText());
                    getProprietati(lista);
                });
            }
            if(optiune==1) {
                String[] optionTableSet = getNumeColoana(new Product());
                JList<String> optSet=new JList<>(optionTableSet);
                optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JButton findButton = new JButton("Find");
                Stergere(dial, labelCl1, labelCl2, text1, optSet, findButton);
                findButton.addActionListener(e -> {
                    String optiuneSet= optSet.getSelectedValue();
                    List<Product> lista=(new ProductBll()).findProduct(optiuneSet,text1.getText());
                    getProprietati(lista);
                });
            }
            dial.setSize(600,400);
            dial.setVisible(true);
        }

        if(source== button6) {
            JPanel pane3 = new JPanel(new FlowLayout());
            JPanel pane4 = new JPanel(new FlowLayout());
            JLabel labelIdComanda = new JLabel("Id Comanda    ");
            JLabel labelIdClient = new JLabel("Id Client     ");
            JLabel labelNumeClient = new JLabel("Nume Client   ");
            JLabel labelIdProdus = new JLabel("Id Produs     ");
            JLabel labelNumeProdus = new JLabel("Nume Produs   ");
            JLabel labelCantitate = new JLabel("Cantitate     ");

            JTextField text1 = new JTextField(8);
            JTextField text2 = new JTextField(8);
            JTextField text3 = new JTextField(8);
            JTextField text4 = new JTextField(8);
            JTextField text5 = new JTextField(8);
            JTextField text6 = new JTextField(8);

            JButton orderButton = new JButton(PLASEAZA_O_COMANDA);

            pane3.add(labelIdComanda);
            Inserare(pane3, pane4, labelIdClient, labelNumeClient, labelIdProdus, labelNumeProdus, labelCantitate, text1, text2, text3, text4, text5);
            pane4.add(text6);
            JDialog dial = new JDialog(this, PLASEAZA_O_COMANDA);
            Resultat(pane3, pane4, dial);
            dial.add(orderButton, gridConstraints);
            orderButton.addActionListener(e -> {
                try {
                    (new OrdersBll()).insertOrder(new Orders(Integer.parseInt(text1.getText()),Integer.parseInt(text2.getText()),text3.getText(),Integer.parseInt(text4.getText()),text5.getText(),Integer.parseInt(text6.getText())));
                } catch (ExceptiePentruStock e1) {
                    JDialog dial2 = new JDialog(dial,"Eroare");
                    dial2.setLayout(new GridBagLayout());

                    JLabel errorMessage = new JLabel();
                    errorMessage.setText(e1.getMessage());
                    gridConstraints.gridx = 0;
                    gridConstraints.gridy = 0;
                    dial2.add(errorMessage, gridConstraints);
                    dial2.setSize(500,200);
                    dial2.setVisible(true);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            dial.setSize(700,300);
            dial.setVisible(true);
        }
    }
}