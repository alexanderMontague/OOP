/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Jesse
 */
public class Lab5Example extends JFrame{

    private String myVariable;
    private JTextField title;
    private JTextField author;
    private JTextField year;
    private JTextField ISBN;
    private JTextField price;
    private JTextField subject;
    private JTextField workbookISBN;
    private JTextField numProblems;
    
    public Lab5Example(){
        this.setSize(new Dimension(800,600));
        this.setLayout(new GridLayout(2, 2, 1, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        JButton button1 = new JButton("List Books");
        JButton button2 = new JButton("Unique Authors");
        JButton button3 = new JButton("Average Price");
        JButton button4 = new JButton("Textbook-Workbook Pairs");
        JButton button5 = new JButton("Add Book");
        JButton button6 = new JButton("Add Textbook");
        JButton button7 = new JButton("Add Workbook");
        this.title = new JTextField(15);
        this.author = new JTextField(15);
        this.year = new JTextField(15);
        this.ISBN = new JTextField(15);
        this.price = new JTextField(15);
        this.subject = new JTextField(15);
        this.workbookISBN = new JTextField(15);
        this.numProblems = new JTextField(15);
        
        JTextArea area = new JTextArea(20,40);
        area.setEditable(false);
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (title.getText().isEmpty()){
                    area.setText("You entered nothing!");
                } else {
                    area.setText("You entered '" + title.getText() + "'.");
                }
                if (validateField(title.getText())){
                    area.append("\n A nice ampersand!");
                }
                title.setText("");
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        
        this.add(button1);
        this.add(title);
        this.add(area);

    }
    
    private boolean validateField(String field){
        return (field.equals("&"));
    }
    
    public static void main(String[] args) {
        Lab5Example l = new Lab5Example();
        l.setVisible(true);
    }
    
}
