
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;



class SaveContact
{
    private String name;
    private String phone;
    private boolean dataValid;


public SaveContact (String name, String phone)
 {
    dataValid = false;

 if (name.length() == 0)
 {
    JOptionPane.showMessageDialog(null, "Name cannot be blank.",
        "Name Error:", JOptionPane.ERROR_MESSAGE);

 
 }
 else if (phone.length() == 0)
 {
    JOptionPane.showMessageDialog(null, "Phone number cannot be blank.",
        "Phone Error:", JOptionPane.ERROR_MESSAGE);
 }
 else
 {
 
 dataValid = true;
    this.name = name;
     this.phone = phone;
 }
 }

    SaveContact() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 public String getName ()
 {
 return name;
 }

 public String getPhone ()
 {
 return phone;
 }

 public boolean isDataValid ()
 {
 return dataValid;
 }
}

    public class Javaphonebook extends JFrame{
        public static final String data_file_name= "phonebook.txt";
        
            JTextArea textArea = new JTextArea();
            JButton displayButton = new JButton ("Show Save Contact");
            JButton clearButton = new JButton ("Delete Contact");
            JButton saveButton= new JButton("Save Contact");
            JButton addButton = new JButton("Add Contact");
            JButton editButton = new JButton("Edit");
            
            
                private ArrayList<SaveContact> staffArrayList = new ArrayList<SaveContact> ();
                    
     public Javaphonebook() {
                    
                setTitle ("Phone Book");
                
                    JPanel flowPanel = new JPanel (new FlowLayout (FlowLayout.CENTER));
                 
                    add (textArea, BorderLayout.CENTER);
                    
                    flowPanel.add (displayButton);
                    flowPanel.add (addButton);
                    flowPanel.add (editButton);
                    flowPanel.add   (clearButton);
                    flowPanel.add (saveButton);
                    
                    add (flowPanel, BorderLayout.SOUTH);
                    
                    displayButton.addActionListener(event -> readFile(data_file_name));
                    clearButton.addActionListener (event -> clearData());
                    saveButton.addActionListener (event-> writeFile(data_file_name));
                    addButton.addActionListener (event -> addData());
                    editButton.addActionListener (event -> editData());
                     
                    readFile (data_file_name);
                        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
                        
                        addWindowListener (new WindowAdapter()
                        {
                            public void windowClosing(WindowEvent e)
                            {
                                
                            
                        exitApplication();
                            }
                        });
    }
     
     private void addTextData()
     {
         textArea.setText ("Hello World"+ "\n\n"+ "The 'Display' button is working !");
     
     }
     private void addData(){
         String name = JOptionPane.showInputDialog("Enter Contact Name:");
         String phone = JOptionPane.showInputDialog("Enter Contact PhoneNumber");
         
         SaveContact newSaveContact = new SaveContact (name, phone);
            if (newSaveContact.isDataValid()== true);
            {
            staffArrayList.add (newSaveContact);
            
            System.out.println("New Contact Added, now:"+ staffArrayList.size()+ "records.");
            
            refreshTextArea ();
            }
     } 
    
     private void editData (){
     
         String searchName= JOptionPane.showInputDialog("Enter the contact name to edit:");
         boolean matchfound = false;
         int location = -1;
         
         for (int k = 0; k <staffArrayList.size(); k ++){
             if (searchName.compareTo(staffArrayList.get (k) .getName()) == 0){
                 matchfound = true;
                 location = k;
             }
         }
     
        if (matchfound == false)
 {
    JOptionPane.showMessageDialog(null,
        "Error: contact '" + searchName + "' could not be found.",
            "Phone Error:", JOptionPane.ERROR_MESSAGE);
         }
        else{
            String name = JOptionPane.showInputDialog("Enter new Name:");
            String phone = JOptionPane.showInputDialog("Enter new phone numeber:");
            
            SaveContact newSaveContact = new SaveContact (name, phone);
            
            if(newSaveContact.isDataValid()== true){
                staffArrayList.set (location, newSaveContact);
                
             System.out.println ("Contact Updated!");
             
             refreshTextArea ();
            
            }
            
         }
     
     
     }
     private void readFile (String fileName)
 {
     String fileDataStr = "";

     staffArrayList.clear ();

     try
    {
    FileReader fileReader = new FileReader (fileName);
         Scanner inFile = new Scanner (fileReader);

     while (inFile.hasNext() == true)
        {
    String[] parts = inFile.nextLine().split (", ");
        if (parts.length == 2)
        {
        SaveContact newSaveContact = new SaveContact (parts [0], parts [1]);

    staffArrayList.add (newSaveContact);

 }
 else
 {
 throw new IOException ();
 }
 }

 inFile.close();
 fileReader.close();

    refreshTextArea ();

 System.out.println (staffArrayList.size() + " records read.");

 }
 catch (IOException err)
 {
 textArea.setText ("Error: file could not be read.");

 }
 }     
     
    private void refreshTextArea ()
    {
    textArea.setText ("");
    for (int k = 0; k < staffArrayList.size(); k++)
     {
    textArea.append (staffArrayList.get (k).getName ()
        + "\t" + staffArrayList.get (k).getPhone ()
    + "\n");
    }

    textArea.append ("\n\n" + staffArrayList.size() + " records read." + "\n");
    }


 private void writeFile (String fileName)
 {
 try
 {
 Formatter outFile = new Formatter (fileName);

 for (int k = 0; k < staffArrayList.size(); k++)
 {
 outFile.format ("%s, %s\n", staffArrayList.get(k).getName(),
 staffArrayList.get(k).getPhone());
 }

 outFile.close();

 System.out.println (staffArrayList.size() + " records written.");
 }
 catch (IOException err)
 {
 textArea.setText ("Error: file could not be written.");
 
 }
 }

 private void clearData ()
 {
 staffArrayList.clear ();
 textArea.setText ("");
 }

 private void exitApplication ()
 {
 writeFile (data_file_name);

 System.exit (0);
 }

    public static void main (String[] args)
    {
       Javaphonebook app = new Javaphonebook ();
        app.setVisible (true);
            app.setSize (700,700);
                app.setLocation (200, 200);

 }
}
