package atm.simulation.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    JButton login, clear, signup;
    JTextField cardTextField;
    JPasswordField pinTextField;
    
    Login(){
        setTitle("AUTOMATED TELLER MACHINE");
        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70,10,100,100);
        add(label);
        
        
        JLabel text = new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Osward", Font.BOLD,38));
        text.setBounds(230, 40 ,400, 40);
        add(text);
        
        JLabel cardno = new JLabel("Card No.");
        cardno.setFont(new Font("Railway", Font.BOLD,25));
        cardno.setBounds(160, 150 ,150, 30);
        add(cardno);
        
        cardTextField = new JTextField();
        cardTextField.setBounds(300,150,240,30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);
        
        JLabel pin = new JLabel("PIN");
        pin.setFont(new Font("Railway", Font.BOLD,25));
        pin.setBounds(160, 230 ,400, 30);
        add(pin);
        
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300,230,240,30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);
        
        login = new JButton ("SIGN IN");
        login.setBounds(300, 300,100,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);
        
        clear = new JButton ("CLEAR");
        clear.setBounds(440, 300,100,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);
        
        signup = new JButton ("SIGN UP");
        signup.setBounds(300, 360,240,30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);
        
        getContentPane().setBackground(Color.white);
        
        setSize(800,480);//with the help of this we create frame
        setVisible(true);
        setLocation(300,130);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == clear){
            cardTextField.setText("");
            pinTextField.setText("");
        }else if(ae.getSource() == login){
            Conn conn  = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pinnumber+"'";
            try{
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()){
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or Pin");
                }
            }catch (Exception e){
                System.out.println(e);
            }

        }else if(ae.getSource() == signup){
        setVisible(false);
        new SignupOne().setVisible(true);
    }
    }
    public static void main(String [] args){
        new Login();
    }
}
