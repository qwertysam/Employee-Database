/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data.EmployeeInfo;
import data.Gender;
import data.Location;
import data.OpenHashTable;
import data.PartTimeEmployee;
import io.Database;
import java.awt.CardLayout;
import java.awt.Color;

/**
 *
 */
public class NewJFrame extends javax.swing.JFrame {

	private OpenHashTable table;

	/**
	 * Creates new form NewJFrame
	 */
	public NewJFrame() {
		table = new OpenHashTable(2);
		Database.instance().load(table);

		initComponents();
		
		setEnabledEmployeeInfoPanel(false);
		
		searchTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				action();
			}

			public void removeUpdate(DocumentEvent e) {
				action();
			}

			public void insertUpdate(DocumentEvent e) {
				action();
			}

			private void action() {
				updateDisplayTable();
			}
		});
	}

	public void updateDisplayTable() {
		ListModel<EmployeeInfo> listModel = employeeList.getModel();

		//Check to see if the list is an instance of DefaultListModel, for type safety
		if (listModel instanceof DefaultListModel<?>) {
			DefaultListModel<EmployeeInfo> entries = (DefaultListModel<EmployeeInfo>) listModel;

			// Clear the list
			entries.removeAllElements();

			String search = searchTextField.getText().toLowerCase();
			//doSearch indicate if there are non-space characters
			boolean doSearch = !search.replace(" ", "").equals("");

			// If there's a search, filter things out
			if (doSearch) {
				// The parameters to search for (Each word that the user has typed in)
				String[] params = search.split(" ");

				// For every EmployeeInfo in the hash table
				for (ArrayList<EmployeeInfo> bucket : table.getBuckets()) {
					for (EmployeeInfo e : bucket) {

						// Keep track if each parameter matched a field
						boolean[] addResult = new boolean[params.length];
						for (int i = 0; i < addResult.length; i++) {
							addResult[i] = false;
						}

						// Test each parameter for a match
						for (int i = 0; i < params.length; i++) {

							String param = params[i];

							// Test first name
							if (e.getFirstName().toLowerCase().contains(param)) {
								addResult[i] = true;
							}

							// Test last name
							if (e.getLastName().toLowerCase().contains(param)) {
								addResult[i] = true;
							}

							// Test ID number
							if (("" + e.getEmployeeNumber()).contains(param)) {
								addResult[i] = true;
							}
						}

						// Tests if each parameter found a match in this EmployeeInfo object
						boolean trulyAdd = true;
						for (int j = 0; j < addResult.length; j++) {
							if (addResult[j] == false)
								trulyAdd = false;
						}

						// If the above code found it to be true, add the element
						if (trulyAdd) {
							entries.addElement(e);
						}
					}
				}
			} else {// nothing is being searched, add everything
				// For every EmployeeInfo in the hash table
				for (ArrayList<EmployeeInfo> bucket : table.getBuckets()) {
					for (EmployeeInfo e : bucket) {
						// Add it
						entries.addElement(e);
					}
				}
			}
		} else {
			System.err.println("Display table's ListModel isn't an instance of DefaultListModel");
		}
	}

	private void saveTable() {
		Database.instance().save(table);
	}

        private void addBlankEmployee(){
                employeeList.setEnabled(false);
                enableEmployeeInfo();
                setEnabledEmployeeInfoPanel(true);
                //PartTimeEmployee employee;
                
                //table.addEmployee(employee);
        }
        
        private void removeEmployee(int id){
            table.removeEmployee(id);
        }
        
        private void editEmployee(){
            
        }
        
        private void updateHashTable(){
            
        }
        
        private void enableEmployeeInfo(){
            
        }
        
        private void setEnabledEmployeeInfoPanel(boolean enabled){
        	employeeInfoPanel.setEnabled(enabled);
        	for(int i = 0; i < employeeInfoPanel.getComponentCount(); i++){
        		employeeInfoPanel.getComponent(i).setEnabled(enabled);
        	}
        	for(int i = 0; i < partTimeWagePanel.getComponentCount(); i++){
        		partTimeWagePanel.getComponent(i).setEnabled(enabled);
        	}
        	for(int i = 0; i < fullTimeWagePanel.getComponentCount(); i++){
        		fullTimeWagePanel.getComponent(i).setEnabled(enabled);
        	}
        }
        
        public void selectWagePanel(){
                if(fullTimeRadioButton.isSelected()){
                    ((CardLayout)wagePanel.getLayout()).show(wagePanel,"fullTimeWageCard");
                } else {
                    if(partTimeRadioButton.isSelected()){
                        ((CardLayout)wagePanel.getLayout()).show(wagePanel,"partTimeWageCard");
                    }
                }
        }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        fullPartTimeButtonGroup = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeList = new gui.EmployeeList();
        jPanel1 = new javax.swing.JPanel();
        labelEmployeeNumber = new javax.swing.JLabel();
        labelLastName = new javax.swing.JLabel();
        labelFirstName = new javax.swing.JLabel();
        searchTextField = new IconTextField("Search", IconType.SEARCH);
        buttonPanel = new javax.swing.JPanel();
        addButton = new IconButton(IconType.ADD);
        removeButton = new IconButton(IconType.REMOVE);
        editButton = new IconButton(IconType.EDIT);
        doneButton = new IconButton(IconType.DONE);
        employeeInfoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        empInfoFirstName = new javax.swing.JTextField();
        lastNameEmpInfoLabel = new javax.swing.JLabel();
        empInfoLastName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        positionLabel = new javax.swing.JTextField();
        portraitPanel = new ImagePanel(IconType.USA);
        empnumLabel = new javax.swing.JLabel();
        empInfoEmpnum = new javax.swing.JTextField();
        fullTimeRadioButton = new javax.swing.JRadioButton();
        partTimeRadioButton = new javax.swing.JRadioButton();
        genderLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        genderLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        wagePanel = new javax.swing.JPanel();
        fullTimeWagePanel = new javax.swing.JPanel();
        annualSalaryLabel = new javax.swing.JLabel();
        salaryTextField = new javax.swing.JTextField();
        fullTimeWageLabel = new javax.swing.JLabel();
        fullTimeWageTextField = new javax.swing.JTextField();
        partTimeWagePanel = new javax.swing.JPanel();
        hourlyWageLabel = new javax.swing.JLabel();
        hoursWorkedLabel = new javax.swing.JLabel();
        hourlyWageTextField = new javax.swing.JTextField();
        hoursWorkedTextField = new javax.swing.JTextField();
        deductionRateLabel = new javax.swing.JLabel();
        deductionRateTextField = new javax.swing.JTextField();
        partTimeWageLabel = new javax.swing.JLabel();
        partTimeWageTextField = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        employeeList.setMaximumSize(new java.awt.Dimension(0, 0));
        employeeList.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setViewportView(employeeList);

        labelEmployeeNumber.setText("<html>Employee<br>&nbsp;Number</p></html>");

        labelLastName.setText("Last Name");

        labelFirstName.setText("First Name");

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFirstName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLastName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEmployeeNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelLastName)
                            .addComponent(labelFirstName))
                        .addContainerGap())))
        );

        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        removeButton.setText("iconButton1");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        editButton.setText("iconButton1");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 78, Short.MAX_VALUE))
        );

        doneButton.setText("iconButton1");
        doneButton.setEnabled(false);
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        employeeInfoPanel.setBackground(new java.awt.Color(204, 204, 204));
        employeeInfoPanel.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel1.setText("First Name");

        lastNameEmpInfoLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lastNameEmpInfoLabel.setText("Last Name");

        empInfoLastName.setSize(new java.awt.Dimension(10, 26));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("Deduction Rate");

        portraitPanel.setBackground(new java.awt.Color(204, 204, 204));
        portraitPanel.setPreferredSize(new java.awt.Dimension(160, 200));

        javax.swing.GroupLayout portraitPanelLayout = new javax.swing.GroupLayout(portraitPanel);
        portraitPanel.setLayout(portraitPanelLayout);
        portraitPanelLayout.setHorizontalGroup(
            portraitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );
        portraitPanelLayout.setVerticalGroup(
            portraitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        empnumLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        empnumLabel.setText("Employee Number");

        fullPartTimeButtonGroup.add(fullTimeRadioButton);
        fullTimeRadioButton.setText("Full Time");
        fullTimeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullTimeRadioButtonActionPerformed(evt);
            }
        });

        fullPartTimeButtonGroup.add(partTimeRadioButton);
        partTimeRadioButton.setText("Part Time");
        partTimeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partTimeRadioButtonActionPerformed(evt);
            }
        });

        genderLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        genderLabel.setText("Gender\n");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        jComboBox1.setSelectedIndex(-1);
        jComboBox1.setSelectedItem(null);

        genderLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        genderLabel1.setText("Location");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mississauga", "Ottawa", "Chicago" }));
        jComboBox2.setSelectedIndex(-1);
        jComboBox2.setSelectedItem(null);

        wagePanel.setLayout(new java.awt.CardLayout());

        fullTimeWagePanel.setPreferredSize(new java.awt.Dimension(492, 70));

        annualSalaryLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        annualSalaryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        annualSalaryLabel.setText("<html>Annual<br>Salary</p></html>");

        salaryTextField.setText("jTextField1");

        fullTimeWageLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        fullTimeWageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fullTimeWageLabel.setText("<html>Annual<br>Income</p></html>");

        fullTimeWageTextField.setText("jTextField2");

        javax.swing.GroupLayout fullTimeWagePanelLayout = new javax.swing.GroupLayout(fullTimeWagePanel);
        fullTimeWagePanel.setLayout(fullTimeWagePanelLayout);
        fullTimeWagePanelLayout.setHorizontalGroup(
            fullTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fullTimeWagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fullTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(salaryTextField)
                    .addComponent(annualSalaryLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                .addGroup(fullTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fullTimeWageTextField)
                    .addComponent(fullTimeWageLabel))
                .addContainerGap())
        );
        fullTimeWagePanelLayout.setVerticalGroup(
            fullTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fullTimeWagePanelLayout.createSequentialGroup()
                .addGroup(fullTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(annualSalaryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fullTimeWageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fullTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fullTimeWageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        wagePanel.add(fullTimeWagePanel, "fullTimeWageCard");

        partTimeWagePanel.setPreferredSize(new java.awt.Dimension(492, 70));

        hourlyWageLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        hourlyWageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hourlyWageLabel.setText("<html>Hourly<br>&nbsp;Wage</p></html>");

        hoursWorkedLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        hoursWorkedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hoursWorkedLabel.setText("<html>&nbsp;&nbsp;Hours<br>per Week</p></html>");

        hourlyWageTextField.setText("jTextField1");

        hoursWorkedTextField.setText("jTextField2");

        deductionRateLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        deductionRateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deductionRateLabel.setText("<html>&nbsp;&nbsp;Weeks<br>per Year</p></html>");

        deductionRateTextField.setText("jTextField2");

        partTimeWageLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        partTimeWageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        partTimeWageLabel.setText("<html>Annual<br>Income</p></html>");

        partTimeWageTextField.setText("jTextField2");

        javax.swing.GroupLayout partTimeWagePanelLayout = new javax.swing.GroupLayout(partTimeWagePanel);
        partTimeWagePanel.setLayout(partTimeWagePanelLayout);
        partTimeWagePanelLayout.setHorizontalGroup(
            partTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(partTimeWagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(partTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hourlyWageTextField)
                    .addComponent(hourlyWageLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 49, Short.MAX_VALUE)
                .addGroup(partTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hoursWorkedTextField)
                    .addComponent(hoursWorkedLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 51, Short.MAX_VALUE)
                .addGroup(partTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deductionRateTextField)
                    .addComponent(deductionRateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 51, Short.MAX_VALUE)
                .addGroup(partTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(partTimeWageTextField)
                    .addComponent(partTimeWageLabel))
                .addContainerGap())
        );
        partTimeWagePanelLayout.setVerticalGroup(
            partTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, partTimeWagePanelLayout.createSequentialGroup()
                .addGroup(partTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hourlyWageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hoursWorkedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deductionRateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(partTimeWageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(partTimeWagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hourlyWageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hoursWorkedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deductionRateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(partTimeWageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        wagePanel.add(partTimeWagePanel, "partTimeWageCard");

        javax.swing.GroupLayout employeeInfoPanelLayout = new javax.swing.GroupLayout(employeeInfoPanel);
        employeeInfoPanel.setLayout(employeeInfoPanelLayout);
        employeeInfoPanelLayout.setHorizontalGroup(
            employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeeInfoPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employeeInfoPanelLayout.createSequentialGroup()
                        .addComponent(wagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(employeeInfoPanelLayout.createSequentialGroup()
                        .addComponent(portraitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(positionLabel)
                                .addGroup(employeeInfoPanelLayout.createSequentialGroup()
                                    .addComponent(fullTimeRadioButton)
                                    .addGap(18, 49, Short.MAX_VALUE)
                                    .addComponent(partTimeRadioButton)
                                    .addContainerGap(60, Short.MAX_VALUE))
                                .addComponent(jLabel2))
                            .addGroup(employeeInfoPanelLayout.createSequentialGroup()
                                .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(empnumLabel)
                                    .addComponent(empInfoEmpnum, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(employeeInfoPanelLayout.createSequentialGroup()
                                .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(employeeInfoPanelLayout.createSequentialGroup()
                                        .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(empInfoFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(employeeInfoPanelLayout.createSequentialGroup()
                                        .addComponent(genderLabel)
                                        .addGap(98, 98, 98)))
                                .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(empInfoLastName)
                                    .addGroup(employeeInfoPanelLayout.createSequentialGroup()
                                        .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(genderLabel1)
                                            .addComponent(lastNameEmpInfoLabel)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 9, Short.MAX_VALUE))))))))
        );
        employeeInfoPanelLayout.setVerticalGroup(
            employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeeInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empnumLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(employeeInfoPanelLayout.createSequentialGroup()
                        .addComponent(empInfoEmpnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lastNameEmpInfoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(empInfoFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(empInfoLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genderLabel)
                            .addComponent(genderLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(positionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(portraitPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(partTimeRadioButton)
                    .addComponent(fullTimeRadioButton))
                .addGap(18, 18, 18)
                .addComponent(wagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(27, 27, 27))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(employeeInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(employeeInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)))
                        .addGap(25, 25, 25))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addBlankEmployee();
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doneButtonActionPerformed

    private void fullTimeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullTimeRadioButtonActionPerformed
        //TODO catch the event
        selectWagePanel();
    }//GEN-LAST:event_fullTimeRadioButtonActionPerformed

    private void partTimeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partTimeRadioButtonActionPerformed
        //TODO catch the event
        selectWagePanel();
    }//GEN-LAST:event_partTimeRadioButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the default look and feel */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.IconButton addButton;
    private javax.swing.JLabel annualSalaryLabel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JLabel deductionRateLabel;
    private javax.swing.JTextField deductionRateTextField;
    private gui.IconButton doneButton;
    private gui.IconButton editButton;
    private javax.swing.JTextField empInfoEmpnum;
    private javax.swing.JTextField empInfoFirstName;
    private javax.swing.JTextField empInfoLastName;
    private javax.swing.JPanel employeeInfoPanel;
    private gui.EmployeeList employeeList;
    private javax.swing.JLabel empnumLabel;
    private javax.swing.ButtonGroup fullPartTimeButtonGroup;
    private javax.swing.JRadioButton fullTimeRadioButton;
    private javax.swing.JLabel fullTimeWageLabel;
    private javax.swing.JPanel fullTimeWagePanel;
    private javax.swing.JTextField fullTimeWageTextField;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JLabel genderLabel1;
    private javax.swing.JLabel hourlyWageLabel;
    private javax.swing.JTextField hourlyWageTextField;
    private javax.swing.JLabel hoursWorkedLabel;
    private javax.swing.JTextField hoursWorkedTextField;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelEmployeeNumber;
    private javax.swing.JLabel labelFirstName;
    private javax.swing.JLabel labelLastName;
    private javax.swing.JLabel lastNameEmpInfoLabel;
    private javax.swing.JRadioButton partTimeRadioButton;
    private javax.swing.JLabel partTimeWageLabel;
    private javax.swing.JPanel partTimeWagePanel;
    private javax.swing.JTextField partTimeWageTextField;
    private javax.swing.JPanel portraitPanel;
    private javax.swing.JTextField positionLabel;
    private gui.IconButton removeButton;
    private javax.swing.JTextField salaryTextField;
    private gui.IconTextField searchTextField;
    private javax.swing.JPanel wagePanel;
    // End of variables declaration//GEN-END:variables
}
