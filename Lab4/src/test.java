
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.*;

/**
 * @author Jesse Francis
 */
public class test extends JFrame {

    public JTextField textField;
    public JTextArea textArea;

    public test() {
        super("A Nice Bookstore");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        /* 
            TextField's produces an ActionEvent is triggered when focus is in 
            the text field and enter/return is pressed (not when letters are 
            added).
         */
        textField = new JTextField(25);
        textField.addActionListener(new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent ae) {
                /*
                    The majority of your code should be written here.
                    Note that this scope has access to the fields textField
                    and textArea.
                */  
                textArea.setText(textField.getText());
            }
            
        });


        // TextArea should be used to store output
        textArea = new JTextArea(10, 10);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setText("A main menu would look great here.");
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        // Button
        JButton b1 = new JButton("Click Me");
        add(b1);
        b1.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		    textArea.setText("hey you.");
        		  } 
        		} );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        // Add the created components to the Frame
        add(textField);
        add(scrollPane);
    }
    
    public static void main(String[] args) {
        // GUI Example
        test bg = new test();
        bg.setVisible(true);

        // HashMap example
        HashMap<Integer, String> map = new HashMap();

        map.put(10, "A really good value.");
        System.out.println(map.get(10));

        map.put(10, "HashMaps can only map one value to each key.");
        System.out.println(map.get(10));

        System.out.println("-----------------------------------");

        System.out.println(map.putIfAbsent(10, "Was there a value already?"));
        System.out.println(map.putIfAbsent(11, "How about here?"));

        System.out.println("-----------------------------------");

        Set<Integer> keys = map.keySet();
        Iterator<Integer> iter = keys.iterator();
        while (iter.hasNext()) {
            Integer key = iter.next();
            String content = map.get(key);
            System.out.println("Key " + key + " is mapped to value: '" + content + "'.");
        }

    }
}