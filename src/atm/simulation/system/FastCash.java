package atm.simulation.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton hundred,two,thousand,twothousand,fivethousand,tenthousand,exit;
    String pinnumber;
    FastCash(String pinnumber){
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,700);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setBounds(220,200,700,35);
        text.setForeground(Color.white);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);

        hundred = new JButton("Rs 100");
        hundred.setBounds(160,316,150,30);
        hundred.addActionListener(this);
        image.add(hundred);

        two = new JButton("Rs 500");
        two.setBounds(358,316,150,30);
        two.addActionListener(this);
        image.add(two);

        thousand = new JButton("Rs 1000");
        thousand.setBounds(160,350,150,30);
        thousand.addActionListener(this);
        image.add(thousand);

        twothousand = new JButton("Rs 2000");
        twothousand.setBounds(358,350,150,30);
        twothousand.addActionListener(this);
        image.add(twothousand);

        fivethousand = new JButton("Rs 5000");
        fivethousand.setBounds(160,385,150,30);
        fivethousand.addActionListener(this);
        image.add(fivethousand);

        tenthousand = new JButton("Rs 10000");
        tenthousand.setBounds(358,385,150,30);
        tenthousand.addActionListener(this);
        image.add(tenthousand);

        exit = new JButton("Back");
        exit.setBounds(358,420,150,30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900, 900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == exit){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }else {
            String amount = ((JButton)ae.getSource()).getText().substring(3);
            Conn conn = new Conn();
            try {
                ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
                int balance = 0;
                while(rs.next()){
                    if (rs.getString("type").equals("Deposit")){
                        balance += Integer.parseInt(rs.getString("amount"));
                    }else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if (ae.getSource()!= exit && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                Date date = new Date();
                String query = "insert into bank value ('"+pinnumber+"','"+date+"','withdrawl','"+amount+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs "+ amount + " Debited successfully");
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
