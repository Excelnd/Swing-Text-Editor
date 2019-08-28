import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


class textEditor extends JFrame implements ActionListener {
    JTextArea tArea;

    JFrame frm;

    textEditor() {
        frm = new JFrame("Text Editor v1.2");

        try {
            // to set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {

        }
        // Textarea component
        tArea = new JTextArea();
        // a menubar
        JMenuBar menuBar = new JMenuBar();

        // a menu for new window menu
        JMenu iMenu = new JMenu("File");

        // Menu items
        JMenuItem menuNew = new JMenuItem("New");
        JMenuItem menuOpen = new JMenuItem("Open");
        JMenuItem menuSave = new JMenuItem("Save");
        JMenuItem menuPrint = new JMenuItem("Print");

        // Adding action Listener
        menuNew.addActionListener(this);
        menuOpen.addActionListener(this);
        menuSave.addActionListener(this);
        menuPrint.addActionListener(this);

        iMenu.add(menuNew);
        iMenu.add(menuOpen);
        iMenu.add(menuSave);
        iMenu.add(menuPrint);

        // Create a menu for menu again
        JMenu iMenu_2 = new JMenu("Edit");

        JMenuItem menuCut = new JMenuItem("Cut");
        JMenuItem menuCopy = new JMenuItem("Copy");
        JMenuItem menuPaste = new JMenuItem("Paste");

        // Add action Listener
        menuCut.addActionListener(this);
        menuCopy.addActionListener(this);
        menuPaste.addActionListener(this);

        iMenu_2.add(menuCut);
        iMenu_2.add(menuCopy);
        iMenu_2.add(menuPaste);

        JMenuItem iMenu_3 = new JMenuItem("Close");

        iMenu_3.addActionListener(this);

        menuBar.add(iMenu);
        menuBar.add(iMenu_2);
        menuBar.add(iMenu_3);

        frm.setJMenuBar(menuBar);
        frm.add(tArea);
        frm.setSize(800, 800);
        frm.show();

    }

    // if btn pressed
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();

        if (act.equals("Cut")) {
            tArea.cut();
        } else if (act.equals("Copy")) {
            tArea.copy();
        } else if (act.equals("Paste")) {
            tArea.paste();
        } else if (act.equals("Save")) {
            // object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");
            FileNameExtensionFilter filters = new FileNameExtensionFilter("TEXT FILES", ".txt", "text");
            j.setFileFilter(filters);

            // invoke the showSaveDialog function
            int r = j.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {

                // set the label to the path of the selection Dir
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    FileWriter wr = new FileWriter(fi, false);
                    BufferedWriter w = new BufferedWriter(wr);

                    w.write(tArea.getText());
                    w.flush();
                    w.close();
                } catch (Exception event) {
                    JOptionPane.showMessageDialog(frm, event.getMessage());
                }
            }
            // if the user aborted the operation
            else
                JOptionPane.showMessageDialog(frm, "The user aborted the operation");
        } else if (act.equals("Print")) {
            try {
                // to print file
                tArea.print();
            } catch (Exception event) {
                JOptionPane.showMessageDialog(frm, event.getMessage());
            }
        } else if (act.equals("Open")) {
            JFileChooser j = new JFileChooser("f:");

            // open showsOpenDialog
            int r = j.showOpenDialog(null);


            // If user selects the file
            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    String sOne = "", sLine = "";

                    // File reader
                    FileReader fr = new FileReader(fi);

                    BufferedReader br = new BufferedReader(fr);

                    sLine = br.readLine();

                    // Take input from file
                    while ((sOne = br.readLine()) != null) {
                        sLine = sLine + "\n" + sOne;
                    }
                    // set the text
                    tArea.setText(sLine);
                } catch (Exception event) {
                    JOptionPane.showMessageDialog(frm, event.getMessage());
                }
            } else
                JOptionPane.showMessageDialog(frm, "The user aborted the operation");
        } else if (act.equals("New")) {
            tArea.setText("");
        } else if (act.equals("Close")) {
            frm.setVisible(false);
        }
    }

        // Main
        public static void main(String[] args) {
            textEditor te = new textEditor();
        }

}
