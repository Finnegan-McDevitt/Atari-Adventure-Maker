/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mazemaker;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mcdevitt_894713
 */
public class ColorCellRenderer extends JLabel implements TableCellRenderer{

    ImageIcon key;
    ImageIcon lock;
    Color bgColor;
    public ColorCellRenderer(){
        super.setOpaque(true);
        key = new ImageIcon("requiredImages/makerKey.png");
        lock = new ImageIcon("requiredImages/makerLock.png");
        bgColor = new Color(214, 217, 223);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int curValue = (Integer)value;
        super.setBackground(bgColor);
        super.setIcon(null);
        if (curValue == 1){
            super.setBackground(Color.YELLOW);
        }
        if (curValue == 2 || curValue == 11){
            super.setBackground(Color.LIGHT_GRAY);
        }
        if (curValue == 3 || curValue == 4){
            super.setBackground(Color.RED);
        }
        if (curValue == 5 || curValue == 6){
            super.setBackground(Color.GREEN);
        }
        if (curValue == 7 || curValue == 8){
            super.setBackground(Color.BLUE);
        }
        if (curValue == 9 || curValue == 10){
            super.setBackground(Color.PINK);
        }
        if (curValue == 12){
            super.setBackground(Color.CYAN);
        }
        if(column == 0){
            super.setBackground(bgColor);
        } 
        else {
            if(curValue == 3 || curValue == 5 || curValue == 7 || curValue == 9){
                super.setIcon(key);
            }
            if(curValue == 4 || curValue == 6 || curValue == 8 || curValue == 10){
                super.setIcon(lock);
            }
        }
        
        super.setText("" + value);
        return this;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
