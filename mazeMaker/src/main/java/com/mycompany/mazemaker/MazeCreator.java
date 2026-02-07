/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mazemaker;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author mcdevitt_894713
 */
public class MazeCreator extends javax.swing.JFrame {

    public static final String DEFAULT_FILE = 
            "0 0 0 0 0 0 \n" + 
            "0 0 0 0 0 0 \n" +
            "0 0 0 0 0 0 \n" +
            "0 0 0 0 0 0 \n" +
            "0 0 0 0 0 0 \n" +
            "0 0 0 0 0 0 \n" +
            "0 0 0 0 0 0 ";
    private int screenNum;
    private ArrayList<String[][]> screens;
    private String[] colNames;
    private int paleteNum;
    private String currentDir;
    private String packName;

    /**
     * Creates new form MazeCreator
     */
    public MazeCreator() {
        initComponents();
        jTable1.getModel().addTableModelListener(new TableModelListener(){
            public void tableChanged(TableModelEvent e){
        	if(e.getType() != TableModelEvent.UPDATE || jTable1.getCellEditor() == null){
                    return;
        	}
                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        	int value = (int)model.getValueAt(e.getFirstRow(), e.getColumn());
        	if(value > 12 || value < 0){
                    model.setValueAt(0, e.getFirstRow(), e.getColumn());
        	}
                if(((int)model.getValueAt(e.getFirstRow(), e.getColumn()) == 3) || ((int)model.getValueAt(e.getFirstRow(), e.getColumn()) == 5) || ((int)model.getValueAt(e.getFirstRow(), e.getColumn()) == 7) || ((int)model.getValueAt(e.getFirstRow(), e.getColumn()) == 9)){
                    for (int x = 0; x < model.getRowCount(); x++){
                        for (int y = 1; y < model.getColumnCount(); y++){
                            if((x == e.getFirstRow()) && ((y - 1) == e.getColumn())){
                                continue;
                            }
                            if(((int)model.getValueAt(x, y - 1) == 3) || ((int)model.getValueAt(x, y - 1) == 5) || ((int)model.getValueAt(x, y - 1) == 7) || ((int)model.getValueAt(x, y - 1) == 9)){
                                model.setValueAt(0, x, y - 1);
                            }
                        }
                    }
                }
            }
        });
        jTable1.setRowHeight(jTable1.getHeight() / jTable1.getRowCount());
        jTable1.setBackground(new Color(214, 217, 223));
        jTable1.setDefaultRenderer(Integer.class, new ColorCellRenderer());
        paleteNum = -1;
        packName = "defaultLevel";
        currentDir = "levels\\defaultLevel";
        this.setTitle("Maze Creator - " + packName);

        buildScreens();

        //colNames = new String[7];
        colNames = new String[]{"", "1", "2", "3", "4", "5", "6"};
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);

        updateModel();
    }
    
    private void buildScreens(){
        Scanner s = null;
        screens = new ArrayList<String[][]>();
        for (int i = 0; i < 9; i++) {
            screens.add(new String[7][7]);
        }
        int scrNum = 1;
        for (String[][] str : screens) {
            try {
                s = new Scanner(new File(currentDir + "\\Screen" + scrNum));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
            String currentSquare = "";
            int rowCount = 1;
            for (int i = 0; i < str.length; i++) {
                str[i][0] = "" + rowCount;
                rowCount++;
                for (int j = 1; j < str[i].length; j++) {
                    currentSquare = s.next();
                    str[i][j] = currentSquare;
                    if(currentSquare.equals("2")){
                        screenNum = scrNum;
                    }
                }
            }
            System.out.println("screen " + scrNum + " built");
            scrNum++;
        }
        switch(screenNum){
            case 1:
                screen1Button.setSelected(true);
                break;
            case 2:
                screen2Button.setSelected(true);
                break;
            case 3:
                screen3Button.setSelected(true);
                break;
            case 4:
                screen4Button.setSelected(true);
                break;
            case 5:
                screen5Button.setSelected(true);
                break;
            case 6:
                screen6Button.setSelected(true);
                break;
            case 7:
                screen7Button.setSelected(true);
                break;
            case 8:
                screen8Button.setSelected(true);
                break;
            case 9:
                screen9Button.setSelected(true);
                break;
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

        screenButtonGroup = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        paleteButtonGroup = new javax.swing.ButtonGroup();
        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel4 = new javax.swing.JLabel();
        jFileChooser1 = new javax.swing.JFileChooser();
        buttonPanel = new javax.swing.JPanel();
        screen1Button = new javax.swing.JRadioButton();
        screen2Button = new javax.swing.JRadioButton();
        screen3Button = new javax.swing.JRadioButton();
        screen4Button = new javax.swing.JRadioButton();
        screen5Button = new javax.swing.JRadioButton();
        screen6Button = new javax.swing.JRadioButton();
        screen7Button = new javax.swing.JRadioButton();
        screen8Button = new javax.swing.JRadioButton();
        screen9Button = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton13 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jRadioButton15 = new javax.swing.JRadioButton();
        createButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        chooseFileButton = new javax.swing.JMenuItem();
        newFileButton = new javax.swing.JMenuItem();

        jRadioButton1.setText("jRadioButton1");

        jScrollPane3.setViewportView(jEditorPane1);

        jLabel4.setText("jLabel4");

        jFileChooser1.setApproveButtonText("Open");
        jFileChooser1.setDialogTitle("Choose A Level Pack");
        jFileChooser1.setFileHidingEnabled(true);
        jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        screenButtonGroup.add(screen1Button);
        screen1Button.setSelected(true);
        screen1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screen1ButtonActionPerformed(evt);
            }
        });

        screenButtonGroup.add(screen2Button);
        screen2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screen2ButtonActionPerformed(evt);
            }
        });

        screenButtonGroup.add(screen3Button);
        screen3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screen3ButtonActionPerformed(evt);
            }
        });

        screenButtonGroup.add(screen4Button);
        screen4Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screen4ButtonActionPerformed(evt);
            }
        });

        screenButtonGroup.add(screen5Button);
        screen5Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screen5ButtonActionPerformed(evt);
            }
        });

        screenButtonGroup.add(screen6Button);
        screen6Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screen6ButtonActionPerformed(evt);
            }
        });

        screenButtonGroup.add(screen7Button);
        screen7Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screen7ButtonActionPerformed(evt);
            }
        });

        screenButtonGroup.add(screen8Button);
        screen8Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screen8ButtonActionPerformed(evt);
            }
        });

        screenButtonGroup.add(screen9Button);
        screen9Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screen9ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addComponent(screen1Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(screen2Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(screen3Button))
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addComponent(screen4Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(screen5Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(screen6Button))
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addComponent(screen7Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(screen8Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(screen9Button)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(screen3Button)
                    .addComponent(screen2Button)
                    .addComponent(screen1Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(screen4Button)
                    .addComponent(screen5Button)
                    .addComponent(screen6Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(screen7Button)
                    .addComponent(screen8Button)
                    .addComponent(screen9Button))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Current Screen");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText(" 0 - nothing\n 1 - normal wall\n 2 - player start\n 3 - red key\n 4 - red wall\n 5 - green key\n 6 - green wall\n 7 - blue key\n 8 - blue wall\n 9 - pink key\n 10 - pink wall\n 11 - finish\n 12 - portal");
        jTextArea1.setFocusable(false);
        jScrollPane2.setViewportView(jTextArea1);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(6, 259));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "", "1", "2", "3", "4", "5", "6"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable1.setAutoscrolls(false);
        jTable1.setFillsViewportHeight(true);
        jTable1.setInheritsPopupMenu(true);
        jTable1.setMaximumSize(new java.awt.Dimension(2147483647, 270));
        jTable1.setMinimumSize(new java.awt.Dimension(105, 270));
        jTable1.setPreferredSize(new java.awt.Dimension(525, 160));
        jTable1.setRowSelectionAllowed(false);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel2.setText("Palete");

        paleteButtonGroup.add(jRadioButton2);
        jRadioButton2.setText("Empty");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton3);
        jRadioButton3.setText("Wall");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton4);
        jRadioButton4.setText("Player Start");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton5);
        jRadioButton5.setText("Red Key");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton6);
        jRadioButton6.setText("Red Wall");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton7);
        jRadioButton7.setText("Green Key");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton8);
        jRadioButton8.setText("Green Wall");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton9);
        jRadioButton9.setText("Blue Key");
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton10);
        jRadioButton10.setText("Blue Wall");
        jRadioButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton10ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton11);
        jRadioButton11.setText("Finish");
        jRadioButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton11ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton12);
        jRadioButton12.setSelected(true);
        jRadioButton12.setText("Disable Palete Selection");
        jRadioButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton12ActionPerformed(evt);
            }
        });

        jLabel3.setText("If Palete selection is enabled, right click will place empty");

        paleteButtonGroup.add(jRadioButton13);
        jRadioButton13.setText("Portal");
        jRadioButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton13ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton14);
        jRadioButton14.setText("Pink Key");
        jRadioButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton14ActionPerformed(evt);
            }
        });

        paleteButtonGroup.add(jRadioButton15);
        jRadioButton15.setText("Pink Wall");
        jRadioButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jRadioButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton13)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButton12)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton9)
                    .addComponent(jRadioButton10)
                    .addComponent(jRadioButton11)
                    .addComponent(jRadioButton13)
                    .addComponent(jRadioButton14)
                    .addComponent(jRadioButton15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        createButton.setText("Create Files");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("ONLY ONE KEY AND PORTAL PER SCREEN");

        jLabel6.setText("ONLY TWO PORTALS TOTAL");

        jMenu1.setText("File");

        chooseFileButton.setText("Choose Level Pack");
        chooseFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFileButtonActionPerformed(evt);
            }
        });
        jMenu1.add(chooseFileButton);

        newFileButton.setText("New Level Pack");
        newFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileButtonActionPerformed(evt);
            }
        });
        jMenu1.add(newFileButton);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(14, 14, 14))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(47, 47, 47)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void screen1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screen1ButtonActionPerformed
        // TODO add your handling code here:
        saveChangeTable();
        screenNum = 1;
        updateModel();
    }//GEN-LAST:event_screen1ButtonActionPerformed

    private void screen2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screen2ButtonActionPerformed
        // TODO add your handling code here:
        saveChangeTable();
        screenNum = 2;
        updateModel();
    }//GEN-LAST:event_screen2ButtonActionPerformed

    private void screen3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screen3ButtonActionPerformed
        // TODO add your handling code here:
        saveChangeTable();
        screenNum = 3;
        updateModel();
    }//GEN-LAST:event_screen3ButtonActionPerformed

    private void screen4ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screen4ButtonActionPerformed
        // TODO add your handling code here:
        saveChangeTable();
        screenNum = 4;
        updateModel();
    }//GEN-LAST:event_screen4ButtonActionPerformed

    private void screen5ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screen5ButtonActionPerformed
        // TODO add your handling code here:
        saveChangeTable();
        screenNum = 5;
        updateModel();
    }//GEN-LAST:event_screen5ButtonActionPerformed

    private void screen6ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screen6ButtonActionPerformed
        // TODO add your handling code here:
        saveChangeTable();
        screenNum = 6;
        updateModel();
    }//GEN-LAST:event_screen6ButtonActionPerformed

    private void screen7ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screen7ButtonActionPerformed
        // TODO add your handling code here:
        saveChangeTable();
        screenNum = 7;
        updateModel();
    }//GEN-LAST:event_screen7ButtonActionPerformed

    private void screen8ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screen8ButtonActionPerformed
        // TODO add your handling code here:
        saveChangeTable();
        screenNum = 8;
        updateModel();
    }//GEN-LAST:event_screen8ButtonActionPerformed

    private void screen9ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screen9ButtonActionPerformed
        // TODO add your handling code here:
        saveChangeTable();
        screenNum = 9;
        updateModel();
    }//GEN-LAST:event_screen9ButtonActionPerformed

    private void saveChangeTable(){
        if(jTable1.getCellEditor() != null){
            boolean check = jTable1.getCellEditor().stopCellEditing();
            if(!check){
                jTable1.getCellEditor().cancelCellEditing();
            }
        }
        System.out.println("table Updated");
        String[][] tempScreen = screens.get(screenNum - 1);
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        for(int i = 0; i < model.getRowCount(); i++){
            for(int x = 0; x < model.getColumnCount(); x++){
                if(model.getValueAt(i, x) == null){
                    tempScreen[i][x] = "0";
                    continue;
                }
                tempScreen[i][x] = "" + model.getValueAt(i, x);
            }
        }
    }
    
    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        // TODO add your handling code here:
        saveChangeTable();
        for (int i = 0; i < screens.size(); i++){
            String[][] curScreen = screens.get(i);
            File currentFile = new File(currentDir + "\\Screen" + (i + 1));
            currentFile.delete();
            currentFile = new File(currentDir + "\\Screen" + (i + 1));
            try {
                currentFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
            FileWriter fw = null;
            try {
                fw = new FileWriter(currentFile);
            } catch (IOException ex) {
                Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int x = 0; x < curScreen.length; x++){
                for (int y = 1; y < curScreen[x].length; y++){
                    try {
                        fw.write(curScreen[x][y] + " ");
                    } catch (IOException ex) {
                        Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    fw.write("\n");
                } catch (IOException ex) {
                    Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("screen " + (i + 1) + " updated");
        }
        File highScore = new File(currentDir + "\\highScore");
        highScore.delete();
        highScore = new File(currentDir + "\\highScore");
        try {
            highScore.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(highScore);
            fw.write("0.0");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_createButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        int row = jTable1.rowAtPoint(evt.getPoint());
        int col = jTable1.columnAtPoint(evt.getPoint());
        
        if (row >= 0 && col >= 1){
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            if(evt.getButton() == java.awt.event.MouseEvent.BUTTON3){
                model.setValueAt(0, row, col);
            }
            if (paleteNum == -1) {
                return;
            }
            if(evt.getButton() == java.awt.event.MouseEvent.BUTTON1){
                model.setValueAt(paleteNum, row, col);
            }
        }
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int value = (int)model.getValueAt(row, col);
        if(value > 12 || value < 0){
            model.setValueAt(0, row, col);
        }
        if(((int)model.getValueAt(row, col) == 3) || ((int)model.getValueAt(row, col) == 5) || ((int)model.getValueAt(row, col) == 7) || ((int)model.getValueAt(row, col) == 9)){
            for (int x = 0; x < model.getRowCount(); x++){
                for (int y = 1; y < model.getColumnCount(); y++){
                    if((x == row) && ((y - 1) == col)){
                        continue;
                    }
                    if(((int)model.getValueAt(x, y - 1) == 3) || ((int)model.getValueAt(x, y - 1) == 5) || ((int)model.getValueAt(x, y - 1) == 7) || ((int)model.getValueAt(x, y - 1) == 9)){
                        model.setValueAt(0, x, y - 1);
                    }
                }
            }
        }
        if(((int)model.getValueAt(row, col) == 12)){
            for (int x = 0; x < model.getRowCount(); x++){
                for (int y = 1; y < model.getColumnCount(); y++){
                    if((x == row) && ((y - 1) == col)){
                        continue;
                    }
                    if(((int)model.getValueAt(x, y - 1) == 12)){
                        model.setValueAt(0, x, y - 1);
                    }
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        paleteNum = 0;
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        paleteNum = 1;
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        paleteNum = 2;
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
        paleteNum = 3;
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
        paleteNum = 4;
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
        paleteNum = 5;
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
        paleteNum = 6;
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
        // TODO add your handling code here:
        paleteNum = 7;
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jRadioButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton10ActionPerformed
        // TODO add your handling code here:
        paleteNum = 8;
    }//GEN-LAST:event_jRadioButton10ActionPerformed

    private void jRadioButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton11ActionPerformed
        // TODO add your handling code here:
        paleteNum = 11;
    }//GEN-LAST:event_jRadioButton11ActionPerformed

    private void jRadioButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton12ActionPerformed
        // TODO add your handling code here:
        paleteNum = -1;
    }//GEN-LAST:event_jRadioButton12ActionPerformed

    private void jRadioButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton13ActionPerformed
        // TODO add your handling code here:
        paleteNum = 12;
    }//GEN-LAST:event_jRadioButton13ActionPerformed

    private void switchLevelPack(File file){
        try {
            currentDir = file.getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        packName = file.getName();
        this.setTitle("Maze Creator - " + packName);
        buildScreens();
        updateModel();
    }
    
    private void chooseFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileButtonActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(null, "Would you like to save your changes to " + currentDir + "?", "Save?", JOptionPane.YES_NO_CANCEL_OPTION);
        switch (choice) {
            case JOptionPane.YES_OPTION -> createButtonActionPerformed(evt);
            case JOptionPane.NO_OPTION -> {
            }
            default -> {
                return;
            }
        }
        System.out.println("filechooser");
        jFileChooser1.setCurrentDirectory(new File("levels"));
        int option = jFileChooser1.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();
            try {
                System.out.println(file.getCanonicalPath());
                switchLevelPack(file);
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_chooseFileButtonActionPerformed

    private void newFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane optionPane = new JOptionPane();
        String option = optionPane.showInputDialog(null, "Please enter a name for the level pack", "Enter a name", JOptionPane.QUESTION_MESSAGE);
        System.out.println(option);
        //System.out.println(optionPane.getValue());
        if(option == null || option.equals("")){
            System.out.println("canceled");
            return;
        }
        System.out.println("attempting to create directory");
        File file = new File("levels/" + option);
        if(file.mkdir()){
            System.out.println("sucessfully created folder");
            for (int i = 1; i < 10; i++){
                try {
                    File currentScreen = new File(file.getCanonicalPath() + "/Screen" + i);
                    currentScreen.createNewFile();
                    try {
                        FileWriter fw = new FileWriter(currentScreen); 
                        fw.write(DEFAULT_FILE);
                        fw.close();
                    } catch (Exception e){
                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MazeCreator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                FileWriter fw = new FileWriter(new File(file.getCanonicalPath() + "/highScore"));
                fw.write("0.0");
                fw.close();
            } catch (Exception e){
                
            }
            switchLevelPack(file);
        } else {
            JOptionPane.showMessageDialog(null, "This folder already exists, please try a different name.", "Oops", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_newFileButtonActionPerformed

    private void jRadioButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton14ActionPerformed
        // TODO add your handling code here:
        paleteNum = 9;
    }//GEN-LAST:event_jRadioButton14ActionPerformed

    private void jRadioButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton15ActionPerformed
        // TODO add your handling code here:
        paleteNum = 10;
    }//GEN-LAST:event_jRadioButton15ActionPerformed

    private void updateModel(){
        String[][] i = screens.get(screenNum - 1);
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        for (int y = 0; y < i.length; y++){
            for (int x = 0; x < i[y].length; x++){
                model.setValueAt(Integer.parseInt(i[y][x]), y, x);
            }
        }
        System.out.println("model updated");
        jTable1.setModel(model);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MazeCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MazeCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MazeCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MazeCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MazeCreator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JMenuItem chooseFileButton;
    private javax.swing.JButton createButton;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton15;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem newFileButton;
    private javax.swing.ButtonGroup paleteButtonGroup;
    private javax.swing.JRadioButton screen1Button;
    private javax.swing.JRadioButton screen2Button;
    private javax.swing.JRadioButton screen3Button;
    private javax.swing.JRadioButton screen4Button;
    private javax.swing.JRadioButton screen5Button;
    private javax.swing.JRadioButton screen6Button;
    private javax.swing.JRadioButton screen7Button;
    private javax.swing.JRadioButton screen8Button;
    private javax.swing.JRadioButton screen9Button;
    private javax.swing.ButtonGroup screenButtonGroup;
    // End of variables declaration//GEN-END:variables
}
