import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class TextEventListner implements ActionListener
{
    EditorUI ui;
    String fname,faddr;
    public TextEventListner(EditorUI ui)
    {
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea text ;
        Object source = e.getSource();
        if(source instanceof JMenuItem)
        {
            switch(e.getActionCommand())
            {
                case "New":
                    ui.addTextTab("Untitled", null);
                    break;
                case "Open":
                    open();
                    break;
                case "Close":
                    ui.remSelectedTxttab();
                    break;
                case "Save":
                    save();
                    break;
                case "Save As":
                    saveas();
                    break;
                case "Cut":
                text = ui.getSelectedTextArea();
                text.cut();
                    break;
                case "Copy":
                text = ui.getSelectedTextArea();
                text.copy();
                    break;
                case "Paste":
                text = ui.getSelectedTextArea();
                text.paste();
                    break;
            }
        }
        
    }
    public void open()
    {
        BufferedReader br;
        ui.addTextTab("Untitled", null);
        JTextArea text = ui.getSelectedTextArea();
        FileDialog fd = new FileDialog(ui,"Open",FileDialog.LOAD);
        fd.setVisible(true);
        if(fd.getFile()!=null)
        {
            fname = fd.getFile();
            faddr = fd.getDirectory();
            ui.setActiveTabTitle(fname);
            ui.setTitle(fname);
            try{
                br = new BufferedReader(new FileReader(faddr+fname));
                String s=null;
                while((s=br.readLine())!=null)
                {
                    text.append(s+"\n");
                }
                br.close();

            }catch(Exception e){System.out.println("File cannot be opened..");}
    }   
}   
public void saveas()
{
    FileDialog fd = new FileDialog(ui,"Save",FileDialog.SAVE);
    fd.setVisible(true);
    JTextArea text = ui.getSelectedTextArea();

    if(fd.getFile()!=null)
    {
        fname = fd.getFile();
        faddr = fd.getDirectory();
        ui.setActiveTabTitle(fname);
        ui.setTitle(fname); 
    }
    try
    {
        PrintWriter pw = new PrintWriter(faddr+fname);
        pw.write(text.getText());
        pw.close();
        fname=null;
        faddr=null;
    }
    catch(Exception e)
    {
        System.out.println("Something is wrong..");
    }
}
public void save()
{
    JTextArea text = ui.getSelectedTextArea();
    if(fname == null)
    {
        saveas();
    }
    else
    {
        try{
            if(!fname.contains(".txt"))
                fname+=".txt";
            PrintWriter pw = new PrintWriter(faddr+fname);
            ui.setActiveTabTitle(fname);
            pw.write(text.getText());
            pw.close();
            fname=null;
            faddr=null;
        }catch(Exception e)
        {
            System.out.println("Something is wrong..");
        }
    }
}
    
}

