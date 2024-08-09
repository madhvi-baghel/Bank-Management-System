package atm.simulation.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin,repin;
    JButton change,back;
    String pinnumber;
    PinChange(String pinnumber){
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,700);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.white);
        text.setBounds(260,200,500,25);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);

        JLabel pintext = new JLabel("New PIN");
        pintext.setForeground(Color.white);
        pintext.setBounds(160,240,180,25);
        pintext.setFont(new Font("System",Font.BOLD,16));
        image.add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("Railway",Font.BOLD,25));
        pin.setBounds(330, 240, 180,25);
        image.add(pin);

        JLabel repintext = new JLabel("Re-Enter New PIN");
        repintext.setForeground(Color.white);
        repintext.setBounds(160,280,180,25);
        repintext.setFont(new Font("System",Font.BOLD,16));
        image.add(repintext);

        repin = new JPasswordField();
        repin.setFont(new Font("Railway",Font.BOLD,25));
        repin.setBounds(330, 280, 180,25);
        image.add(repin);

        change = new JButton("CHANGE");
        change.setBounds(315,340,180,30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("BACK");
        back.setBounds(315,380,180,30);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == back){
            try {
               String npin = pin.getText();
               String rpin = repin.getText();

               if (!npin.equals(rpin)) {
                   JOptionPane.showMessageDialog(null, "Entered PIN  does not match");
                   return;
               }

               if (npin.equals("")){
                   JOptionPane.showMessageDialog(null, "Please Enter PIN");
                   return;
               }
               if (rpin.equals("")){
                   JOptionPane.showMessageDialog(null, "Plese re-enter new PIN");
                   return;
               }

               Conn conn =new Conn();
               String query1 = "update bank set pin = '"+pin+"' where pin='"+pinnumber+"'";
               String query2 = "update login set pin = '"+pin+"' where pin='"+pinnumber+"'";
               String query3 = "update signupthree set pin = '"+pin+"' where pin='"+pinnumber+"'";

               conn.s.executeUpdate(query1);
               conn.s.executeUpdate(query2);
               conn.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN change successfully");

                setVisible(false);
                new Transactions(rpin).setVisible(true);

            }catch (Exception e){
               System.out.println(e);
            }
        }else {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}
