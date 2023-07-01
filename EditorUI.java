import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.*;

public class EditorUI extends JFrame{
    public static int default_size = 14;
    public static int default_style = Font.PLAIN;
    public static String default_type = "Arial";
    public static int cnt=0; 
    private JTabbedPane tabp;
    JMenu filem,editm,fontm,textStylem,fonttypem,fontsizem,fontcolm;
    JMenuItem newm,openm,closem,savem,saveasm,cutm,copym,pastem;
    JRadioButtonMenuItem boldBm,itlBm,plnBm;
    String []size = {"8","9","10","11","12","14","16","18","20","24","28","36","50"};
    String type[] = {"Arial","Felix Titling","Franklin Gothic Book","Georgia","Gloucester MT","Impact","Latin","Algerian"};
    String [] style = {"BOLD","ITALIC","PLAIN"};

    JComboBox<String> combobox1,combobox2;
    JList<String> jl1 ;
    JList<String> jl2 ;
    JList<String> jl3 ;
    public EditorUI()
    {
        //Jlist
        jl2 = new JList(type);
        jl2.setVisibleRowCount(4);
        jl1 = new JList(size);
        jl1.setVisibleRowCount(4);
        jl3 = new JList(style);
        jl3.setVisibleRowCount(3);
        JMenuBar menuB = new JMenuBar();
        menuB.setBackground(new Color(123,50,250));
        setJMenuBar(menuB);

        //File menu & its menu items.
        filem = new JMenu("File");
        menuB.add(filem);
        newm = new JMenuItem("New");
        filem.add(newm);
        openm = new JMenuItem("Open");
        filem.add(openm);
        closem = new JMenuItem("Close");
        filem.add(closem);
        filem.addSeparator();
        savem = new JMenuItem("Save");
        filem.add(savem);
        saveasm = new JMenuItem("Save As");
        filem.add(saveasm);

        //Edit menu
        editm = new JMenu("Edit");
        menuB.add(editm);
        cutm = new JMenuItem("Cut");
        editm.add(cutm);
        copym = new JMenuItem("Copy");
        editm.add(copym);
        pastem = new JMenuItem("Paste");
        editm.add(pastem);

        //Font Menu
        fontm = new JMenu("Font");
        menuB.add(fontm);
        fontsizem = new JMenu("Font Size");
        fontsizem.add(new JScrollPane(jl1));
        fontm.add(fontsizem);
        fonttypem = new JMenu("Font Type");
        fonttypem.add(new JScrollPane(jl2));
        fontm.add(fonttypem);

        //font style menu
        textStylem = new JMenu("Font Style");
        textStylem.add(new JScrollPane(jl3));
        fontm.add(textStylem);

        tabp = new JTabbedPane();
        add(tabp,BorderLayout.CENTER);

        addActList();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
        setTitle("Notepad");
        getContentPane().setBackground(Color.gray);
        
        setVisible(true);
    }

    //Adding action listener for remaining menuItems
    public void addActList()
    {
        newm.addActionListener(new TextEventListner(this));
        openm.addActionListener(new TextEventListner(this));
        closem.addActionListener(new TextEventListner(this));
        savem.addActionListener(new TextEventListner(this));
        saveasm.addActionListener(new TextEventListner(this));
        cutm.addActionListener(new TextEventListner(this));
        copym.addActionListener(new TextEventListner(this));
        pastem.addActionListener(new TextEventListner(this));

        jl1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                String item = (String)jl1.getSelectedValue();
                JTextArea t1 = this.getSelectedTextArea();
                Font font = t1.getFont();
                t1.setFont(font.deriveFont(Float.parseFloat(item)));
                default_size = Integer.parseInt(item);
            }

            private JTextArea getSelectedTextArea() {
                JScrollPane scroll = (JScrollPane)tabp.getSelectedComponent();
                JTextArea t = (JTextArea)((scroll.getViewport()).getView());
                return t;
            }

        });
        jl2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                String item = (String)jl2.getSelectedValue();
                JTextArea t1 = this.getSelectedTextArea();
                Font font = new Font(item,default_style,default_size);
                default_type = item;
                t1.setFont(font);
            }

            private JTextArea getSelectedTextArea() {
                JScrollPane scroll = (JScrollPane)tabp.getSelectedComponent();
                JTextArea t = (JTextArea)((scroll.getViewport()).getView());
                return t;
            }
        });
        jl3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                String item = (String)jl3.getSelectedValue();
                JTextArea t1 = this.getSelectedTextArea();
                Font font ;
                switch(item)
                {
                    case "PLAIN":
                    font = new Font(default_type,Font.PLAIN,default_size);
                    default_style = Font.PLAIN;
                    t1.setFont(font);
                    break;
                    case "BOLD":
                    font = new Font(default_type,Font.BOLD,default_size);
                    t1.setFont(font);
                    default_style = Font.BOLD;
                    break;
                    case "ITALIC":
                    font = new Font(default_type,Font.ITALIC,default_size);
                    t1.setFont(font);
                    default_style = Font.ITALIC;
                    break;
                }
            }

            private JTextArea getSelectedTextArea() {
                JScrollPane scroll = (JScrollPane)tabp.getSelectedComponent();
                JTextArea t = (JTextArea)((scroll.getViewport()).getView());
                return t;
            }
        });
    }

    public void addTextTab(String title,String s)
    {
        JTextArea text = new JTextArea(s);
        tabp.add(title,new JScrollPane(text));
        tabp.setSelectedIndex(cnt);
        cnt++;
    }

    public void remSelectedTxttab()
    {
        int selectedTab = tabp.getSelectedIndex();
        tabp.removeTabAt(selectedTab);
        cnt--;
    }

    public void setActiveTabTitle(String title)
    {
        tabp.setTitleAt(tabp.getSelectedIndex(),title);
    }

    public JTextArea getSelectedTextArea()
    {
        JScrollPane scroll = (JScrollPane)tabp.getSelectedComponent();
        JTextArea t = (JTextArea)((scroll.getViewport()).getView());
        return t;
    }
   
}
