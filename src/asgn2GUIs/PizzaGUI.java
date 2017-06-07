package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


/**
 * This class is the graphical user interface for the rest of the system.
 * Currently it is a �dummy� class which extends JFrame and implements Runnable and ActionLister.
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to
 * interact with the rest of the system. You may choose to implement this class as you like, including changing
 * its class signature � as long as it  maintains its core responsibility of acting as a GUI for the rest of the system.
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 *
 *
 * @author Urvi Patel and Hieu Vuong
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {


	private PizzaRestaurant restaurant = new PizzaRestaurant();
	private String frTitle;
	private String title = "";
	private boolean logdir = false;
	private JFrame frame;
	private JLabel lbDistance;
	private JLabel lbProfit;
	private JTable list;
	private ListSelectionModel lsm;
	private DefaultTableModel data = new DefaultTableModel(new Object[] {"Row", "Name", "Phone N.o", "Type", "Location (x,y)", "Net Distance"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };
	private DefaultTableModel dataPizza = new DefaultTableModel(new Object[] {"Information", "Detail"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };

	/**
	 * Creates a new Pizza GUI with the specified title
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		// TO DO
		frTitle = title;
		SwingUtilities.invokeLater(new Runnable() {
		     public void run() { }
		 });
	}

	@Override
	public void run() {
		// TO DO
		initialise();
		createMenuBar();

		JTabbedPane tabbedPane = createTabbedPane();

		createPanelBusiness(tabbedPane);
		createPanelLogs(tabbedPane);
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialise() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PizzaGUI.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		frame.setTitle("Welcome to " + frTitle);
		frame.setFont(new Font("SansSerif", Font.PLAIN, 12));
		frame.setBounds(200, 200, 850, 470);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setVisible(true);
	}

	/**
	 * Initialise tabbed pane
	 * @return JTabbedPane variable
	 */
	private JTabbedPane createTabbedPane () {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.RED);
		tabbedPane.setBackground(new Color(230, 230, 250));
		frame.getContentPane().add(tabbedPane);

		return tabbedPane;
	}

	/**
	 * Create Panel Business
	 * @param tabbedPane
	 */
	private void createPanelBusiness(JTabbedPane tabbedPane) {
		JPanel panelBusiness = new JPanel();
		panelBusiness.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelBusiness.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("Business", new ImageIcon(PizzaGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")), panelBusiness, null);
		tabbedPane.setForegroundAt(0, new Color(139, 0, 0));
		panelBusiness.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.6);
		panelBusiness.add(splitPane_1, BorderLayout.CENTER);

		JFileChooser file = new JFileChooser();
		file.setFont(new Font("SansSerif", Font.PLAIN, 12));
		file.setApproveButtonText("Load");
		file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//"Load" button action handler
				try {
					logdir = restaurant.processLog(file.getSelectedFile().toString());
				} catch (CustomerException | PizzaException | LogHandlerException e1) {
					if (logdir == false)
						warningSign(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		splitPane_1.setLeftComponent(file);

		JPanel mainPanel = new JPanel();
		splitPane_1.setRightComponent(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout(0,10));
		JLabel titleLabel = new JLabel("REPORT", SwingConstants.CENTER);
		titlePanel.add(titleLabel, BorderLayout.NORTH);

		JPanel subPanel_1 = new JPanel();
		subPanel_1.setLayout(new BorderLayout(0,0));
		JLabel lbProfitTitle = new JLabel("Net Profit: ", SwingConstants.LEFT);
		subPanel_1.add(lbProfitTitle, BorderLayout.NORTH);
		lbProfit = new JLabel("0", SwingConstants.CENTER);
		subPanel_1.add(lbProfit, BorderLayout.CENTER);

		JPanel subPanel_2 = new JPanel();
		subPanel_2.setLayout(new BorderLayout(0,150));
		JLabel lbDistanceTitle = new JLabel("Distance Travalled: ", SwingConstants.LEFT);
		subPanel_2.add(lbDistanceTitle, BorderLayout.NORTH);
		lbDistance = new JLabel("0", SwingConstants.CENTER);
		subPanel_2.add(lbDistance, BorderLayout.CENTER);

		mainPanel.add(titlePanel, BorderLayout.NORTH);
		mainPanel.add(subPanel_1, BorderLayout.CENTER);
		mainPanel.add(subPanel_2, BorderLayout.SOUTH);
		tabbedPane.setEnabledAt(0, true);
	}

	/**
	 * Create Panel Logs
	 * @param tabbedPane
	 * @throws CustomerException
	 */
	private void createPanelLogs(JTabbedPane tabbedPane) {
		JPanel panelLog = new JPanel();
		panelLog.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelLog.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("Logs", new ImageIcon(PizzaGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")), panelLog, null);
		tabbedPane.setForegroundAt(1, new Color(139, 0, 0));
		tabbedPane.setEnabledAt(1, true);
		panelLog.setLayout(new BorderLayout(0, 0));
		JButton btnRefresh = new JButton("Refresh");
		panelLog.add(btnRefresh, BorderLayout.SOUTH);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshView();
			}
		});

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.7);
		panelLog.add(splitPane, BorderLayout.CENTER);

		createLeftTable(splitPane);
		createRightTable(splitPane);
		tabbedPane.setBackgroundAt(1, Color.LIGHT_GRAY);

	}

	/**
	 * Create left table (customer information table) in Logs panel
	 * @param splitPane This is where the table belongs
	 */
	private void createLeftTable(JSplitPane splitPane) {
		list = new JTable(data);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(list); //add scroll-able feature into JTable
		splitPane.setLeftComponent(scrollPane);

		ListSelectionModel rowSM = list.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent e) {
	        	try {
					rowSelection(e);
				} catch (PizzaException e1) {
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
	        }
	        });
	}

	/**
	 * Create right table (pizza information table) in Logs panel
	 * @param splitPane This is where the table belongs
	 */
	private void createRightTable(JSplitPane splitPane) {
		JTable table = new JTable(dataPizza);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table); //add scroll-able feature into JTable
		splitPane.setRightComponent(scrollPane);

	}

	/**
	 * Create Menu Bar
	 */
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 255, 127));
		menuBar.setBackground(new Color(0, 100, 0));
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JButton btnCalc = new JButton("Generate");
		btnCalc.setForeground(new Color(105, 105, 105));
		btnCalc.setBackground(new Color(230, 230, 250));
		menuBar.add(btnCalc);
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Generate net profit and total distance traveled within a day through JFileChooser data
				try {
					generateInfo();
				} catch (CustomerException | PizzaException e1) {
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});

		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(new Color(105, 105, 105));
		btnReset.setBackground(new Color(230, 230, 250));
		menuBar.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clear all contents
				resetInfo();
			}
		});

		JButton btnInfo = new JButton("Authors");
		btnInfo.setForeground(new Color(105, 105, 105));
		btnInfo.setBackground(new Color(230, 230, 250));
		menuBar.add(btnInfo);
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Retrieve info
				authorInfo();
			}
		});
	}

	/**
	 * Generates net profit and total distance traveled within a day through JChooserfile data
	 * @throws CustomerException
	 * @throws PizzaException
	 */
	private void generateInfo() throws CustomerException, PizzaException {
		//TO DO generate net profit and total distance traveled within a day through JChooserfile data
		DecimalFormat df = new DecimalFormat("#.00");
		df.setRoundingMode(RoundingMode.CEILING);
		if (logdir == true) {
			lbDistance.setText(df.format(restaurant.getTotalDeliveryDistance()));
			lbProfit.setText(Double.toString(restaurant.getTotalProfit()));
			for (int i = 0; i < restaurant.getNumCustomerOrders(); i++) {
				//customer info table
				data.addRow(new Object[] {Integer.toString(i+1),
						restaurant.getCustomerByIndex(i+1).getName(),
						restaurant.getCustomerByIndex(i+1).getMobileNumber(),
						restaurant.getCustomerByIndex(i+1).getCustomerType(),
						Integer.toString(restaurant.getCustomerByIndex(i+1).getLocationX()) + "," +
								  Integer.toString(restaurant.getCustomerByIndex(i+1).getLocationY()),
						Double.toString(restaurant.getCustomerByIndex(i+1).getDeliveryDistance())});
			}
		} else if (logdir == false)
			warningSign("Please choose an appropriate txt file to start!");
	}

	/**
	 * Clear all contents
	 */
	private void resetInfo() {
		//TO DO clear all contents
		lbDistance.setText("0");
		lbProfit.setText("0");
		if (data.getRowCount() > 0) { //delete contents in customer info table
			for (int row = data.getRowCount() - 1; row > -1; row--) {
				data.removeRow(row);
			}
		}
		if (dataPizza.getRowCount() > 0) { //delete contents in pizza info table
		    for (int row = dataPizza.getRowCount() - 1; row > -1; row--) {
		        dataPizza.removeRow(row);
		    }
		}
	}

	/**
	 * "Author information" pop-up frame
	 */
	private void authorInfo() {
		JFrame authorFrame = new JFrame();
		authorFrame.setTitle("Authors Information");
		authorFrame.setBounds(300, 300, 300, 100);
		authorFrame.setResizable(false);
		authorFrame.setLayout(new BorderLayout());

		JLabel labelAuthor = new JLabel("AUTHORS", SwingConstants.CENTER);
		JLabel author1 = new JLabel("Hieu Vuong - n9065954", SwingConstants.CENTER);
		JLabel author2 = new JLabel("Urvi Patel - n9193367", SwingConstants.CENTER);

		labelAuthor.setFont(new Font("SansSerif", Font.BOLD, 15));
		authorFrame.getContentPane().add(labelAuthor, BorderLayout.NORTH);
		authorFrame.getContentPane().add(author1, BorderLayout.CENTER);
		authorFrame.getContentPane().add(author2, BorderLayout.AFTER_LAST_LINE);

		authorFrame.setVisible(true);
	}

	/**
	 * Display error messages
	 * @param msg Error message in string type
	 */
	private void warningSign(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Once a row is selected and double-clicked, it triggers displayPizzaInfo()
	 * method which displays the pizza information on the pizza information table
	 * @param e ListSelectionEvent variable
	 * @throws PizzaException
	 */
	private void rowSelection(ListSelectionEvent e) throws PizzaException {
        // Ignore extra messages.
        if (e.getValueIsAdjusting())
          return;

        lsm = (ListSelectionModel) e.getSource();
        if (lsm.isSelectionEmpty() == false) {
          	displayPizzaInfo(lsm.getMinSelectionIndex());
        }
	}

	/**
	 * Display pizza information on pizza info table
	 * @throws PizzaException
	 */
	private void displayPizzaInfo(int selectedRow) throws PizzaException {
//		String type = ;
//		String quantity = ;
//		String price = ;
//		String cost = ;
//		String profit = ;
//
		if (dataPizza.getRowCount() > 0) { //delete old contents to display new contents
		    for (int row = dataPizza.getRowCount() - 1; row > -1; row--) {
		        dataPizza.removeRow(row);
		    }
		}
		restaurant.getPizzaByIndex(selectedRow+1).calculateCostPerPizza();
		//new contents
		dataPizza.addRow(new Object[] {"Type: ", restaurant.getPizzaByIndex(selectedRow+1).getPizzaType()});
		dataPizza.addRow(new Object[] {"Quantity: ", Integer.toString(restaurant.getPizzaByIndex(selectedRow+1).getQuantity())});
		dataPizza.addRow(new Object[] {"Order price: ", Double.toString(restaurant.getPizzaByIndex(selectedRow+1).getOrderPrice())});
		dataPizza.addRow(new Object[] {"Order cost: ", Double.toString(restaurant.getPizzaByIndex(selectedRow+1).getOrderCost())});
		dataPizza.addRow(new Object[] {"Order profit: ", Double.toString(restaurant.getPizzaByIndex(selectedRow+1).getOrderProfit())});

		System.out.println(Double.toString(restaurant.getPizzaByIndex(selectedRow+1).getOrderCost()));
	}

	/**
	 * Clear current rowSelection(ListSelectionEvent e) and remove all contents
	 * in pizza info table
	 */
	private void refreshView() {
		if (lsm.isSelectionEmpty() == false) {
			lsm.clearSelection();
			if (dataPizza.getRowCount() > 0) { //delete contents in pizza info table
			    for (int row = dataPizza.getRowCount() - 1; row > -1; row--) {
			        dataPizza.removeRow(row);
			    }
			}

		}
	}


}
