package asgn2GUIs;

import java.awt.event.ActionEvent;



import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a ‘dummy’ class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature – as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Yu Gen Yeap and Anderson Lee
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	
	private PizzaRestaurant restaurant;
	
	private File file;	
	private JFileChooser fileChooser;
	
	private JButton load; 
	private JButton btnDisplayCustomer;
	private JButton btnCalculateDistance;
	private JButton btnDisplayPizza;
	private JButton btnCalculateProfit;
	private JButton reset;
	
	private DefaultTableModel model;
	private DefaultTableModel model2;
	
	private JPanel panelStatus;
	private JPanel panelButtons; //for buttons 
	private JPanel panelCustomers; // for customer 
	private JPanel panelPizzas; //for pizza

	private JLabel lblTotalDistance;
	private JLabel lblProft;
	private JLabel lblStatus;
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	
	public PizzaGUI(String title) {
		restaurant = new PizzaRestaurant();
		//Properties
		super.setTitle(title);
	}
	
	@Override
	public void run() {
		createGUI();
	}
	
	private void createGUI () {
		//JFrame
		setSize(new Dimension(900,560));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(new FlowLayout());
		
		// initialise panels		
		panelPizzas = new JPanel();
		panelCustomers = new JPanel();
		panelButtons = new JPanel();
		panelStatus = new JPanel();
		
		//make six buttons 
		load = new JButton("Choose File");
		btnDisplayCustomer = new JButton("Display Customer Data");
		btnCalculateDistance = new JButton("Calculate Distance ");
		btnDisplayPizza = new JButton("Display Pizza Data");
		btnCalculateProfit = new JButton ("Calculate Profit");
		reset = new JButton("Reset Info");
		
		// action listeners 
		load.addActionListener(this);
		btnDisplayCustomer.addActionListener(this);
		btnCalculateDistance.addActionListener(this);
		btnDisplayPizza.addActionListener(this);
		btnCalculateProfit.addActionListener(this);
		reset.addActionListener(this);
		
		//Disable buttons
		btnDisplayCustomer.setEnabled(false);
		btnCalculateDistance.setEnabled(false);
		btnDisplayPizza.setEnabled(false);
		btnCalculateProfit.setEnabled(false);
		reset.setEnabled(false);
		
		// Customer JTable 
		JTable cusTable = new JTable();
		Object[] head = {"Name","Mobile","Type", "X", "Y", "Distance"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(head);
		cusTable.setModel(model);
		
		// Pizza JTable
		JTable pizTable = new JTable();
		Object[] head2 = {"Type","Quantity","Price", "Cost", "Profit"};
		model2 = new DefaultTableModel();
		model2.setColumnIdentifiers(head2);
		pizTable.setModel(model2);

		// Customer JScrollPane
        JScrollPane pane = new JScrollPane(cusTable);
        pane.setPreferredSize(new Dimension (600,150));
        
		// Pizza JScrollPane       
        JScrollPane pane2 = new JScrollPane(pizTable);
        pane2.setPreferredSize(new Dimension (600,150));
        
        /////// Holds customer table and some other labels
        // label for btnDisplayCustomer purpose
        JLabel head1 = new JLabel("Customer Data");
        lblTotalDistance = new JLabel ("Total Distance: ");
        panelCustomers.add(head1);
        panelCustomers.add(pane);
        panelCustomers.add(lblTotalDistance);
        // panel size
		panelCustomers.setPreferredSize(new Dimension (700,200));
		
		/////// Pizza Panel
		JLabel labelPizza = new JLabel("    Pizza Data    ");
		lblProft = new JLabel ("Total Profit: ");
		panelPizzas.add(labelPizza);
		panelPizzas.add(pane2);
		panelPizzas.add(lblProft);
        // panel size
		panelPizzas.setPreferredSize(new Dimension (700,200));

		// Button Panel
//		panelButtons.setBackground(Color.yellow);
		panelButtons.add(load);
		panelButtons.add(btnDisplayCustomer);
		panelButtons.add(btnDisplayPizza);
		panelButtons.add(btnCalculateDistance);
		panelButtons.add(btnCalculateProfit);
		panelButtons.add(reset);
		
		
		// label to show status =
		lblStatus = new JLabel ();
		lblStatus.setFont(new Font("Serif", Font.PLAIN, 14));
		lblStatus.setForeground(Color.blue);
		lblStatus.setText("No file selected");
		
		
		//File chooser Panel
		panelStatus.add(lblStatus);
		
		
		// add panels to JFRame
		add(panelStatus);
		add(panelButtons);
		add(panelCustomers);
		add(panelPizzas);
		
	} /// end CreateGUI()
	
	public void actionPerformed(ActionEvent e) {
		
		Object src = e.getSource();
		
		if ( src == load ) {
			// instantiate file-chooser 
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(".//logs/"));
			
			// open file-chooser dialog
			int result = fileChooser.showOpenDialog(panelButtons);
			
			// check if a file is selected
			if (result == JFileChooser.APPROVE_OPTION) {
				    
				file = fileChooser.getSelectedFile();
			
				// process restaurant
				try {
					restaurant.processLog(file.toString());
					
					//Enable other buttons if processLog successful
					btnDisplayCustomer.setEnabled(true);
					btnCalculateDistance.setEnabled(true);
					btnDisplayPizza.setEnabled(true);
					btnCalculateProfit.setEnabled(true);
					reset.setEnabled(true);
					
				} catch (CustomerException exception) {
					JOptionPane.showMessageDialog(this,"Error in one or more of the log inputs: \n" + exception.getMessage(),"Error message",JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				} catch (PizzaException exception) {	
					JOptionPane.showMessageDialog(this,"Error in one or more of the log inputs: \n" + exception.getMessage(),"Error message",JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}catch (LogHandlerException exception) {	
					JOptionPane.showMessageDialog(this,"Error in one or more of the log inputs: \n" + exception.getMessage(),"Error message",JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
				
				// clear data in table
				model.setRowCount(0);
				model2.setRowCount(0);
				lblProft.setText("Total Profit:");
				lblTotalDistance.setText("Total Distance:");
				
				// show selected file =
				lblStatus.setText(file.getName() + " is selected" );
				
				
			}
			
		} else if  ( src == btnDisplayCustomer )  {

				for (int i=0; i<= restaurant.getNumCustomerOrders() -1; i++){
					
					Object[] row = new Object[6];
					try {
					row[0] = restaurant.getCustomerByIndex(i).getName();
					row[1] = restaurant.getCustomerByIndex(i).getMobileNumber();
					row[2] = restaurant.getCustomerByIndex(i).getCustomerType();
					row[3] = restaurant.getCustomerByIndex(i).getLocationX();
					row[4] = restaurant.getCustomerByIndex(i).getLocationY();
					row[5] = Math.round(restaurant.getCustomerByIndex(i).getDeliveryDistance() * 100.00 ) /100.00 ;
					} catch (CustomerException exception) {
						JOptionPane.showMessageDialog(this,"Error:" + exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
					model.addRow(row);
				}
				
				btnDisplayCustomer.setEnabled(false);
				lblStatus.setText("Displaying Customer Info");
			
		} else if ( src == btnDisplayPizza) {
				
			
				Object[] row = new Object[5];

				for (int i=0; i<= restaurant.getNumPizzaOrders() -1; i++){
					try {
					row[0] = restaurant.getPizzaByIndex(i).getPizzaType();
					row[1] = restaurant.getPizzaByIndex(i).getQuantity();
					row[2] = "$" + Math.round(restaurant.getPizzaByIndex(i).getOrderPrice() * 100.00 ) /100.00 ;
					row[3] = "$" + Math.round(restaurant.getPizzaByIndex(i).getOrderCost()* 100.00 ) /100.00 ;
					row[4] = "$" + Math.round(restaurant.getPizzaByIndex(i).getOrderProfit()* 100.00 ) /100.00 ;
					} catch (PizzaException exception) {
						JOptionPane.showMessageDialog(this,"Error:" + exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);		
					}
					
					model2.addRow(row);
				}
				btnDisplayPizza.setEnabled(false);
				lblStatus.setText("Displaying Pizza Info");
			
		}
		else if (src == btnCalculateDistance){
			lblTotalDistance.setText("Total Distance: " + Math.round(restaurant.getTotalDeliveryDistance() * 100.00 ) /100.00  );
			btnCalculateDistance.setEnabled(false);
			lblStatus.setText("Displaying Total Distance");
			
		} else if (src == btnCalculateProfit){
			lblProft.setText("Total Profit: " + restaurant.getTotalProfit());
			btnCalculateProfit.setEnabled(false);
			lblStatus.setText("Displaying Total Profit");

		}
		else if (src == reset){
			restaurant.resetDetails();
			model.setRowCount(0);
			model2.setRowCount(0);
			lblProft.setText("Total Profit:");
			lblTotalDistance.setText("Total Distance:");
			lblStatus.setText("No file selected");
			
			//Disable all other buttons
			btnDisplayCustomer.setEnabled(false);
			btnCalculateDistance.setEnabled(false);
			btnDisplayPizza.setEnabled(false);
			btnCalculateProfit.setEnabled(false);
			reset.setEnabled(false);
		}
	
	} //end Action Performed ()
	
	
	
}
