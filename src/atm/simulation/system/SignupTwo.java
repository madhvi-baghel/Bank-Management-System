package atm.simulation.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

//    long random;
    JTextField pan, aadhar;
    JButton next;
    JRadioButton syes, sno,eyes,eno;
    JComboBox religion, category,income,education,occupation;
    String formno;

    SignupTwo(String formno) {
        this.formno = formno;
        setLayout(null);

        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
        

        JLabel additionalDetails = new JLabel("Page 2 : Additional Details");
        additionalDetails.setFont(new Font("Railway", Font.BOLD, 22));
        additionalDetails.setBounds(280, 50, 400, 30);
        add(additionalDetails);

        JLabel religionL = new JLabel("Religion:");
        religionL.setFont(new Font("Railway", Font.BOLD, 20));
        religionL.setBounds(100, 100, 200, 30);
        add(religionL);

        String valReligion[] = {"Hindu","Muslim","Sikh","Christan","Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300, 100, 400, 30);
        religion.setBackground(Color.white);
        add(religion);


        JLabel categoryL = new JLabel("Category:");
        categoryL.setFont(new Font("Railway", Font.BOLD, 20));
        categoryL.setBounds(100, 160, 200, 30);
        add(categoryL);

        String valcategory[] = {"General","OBC","SC","ST","Other"};
        category = new JComboBox(valcategory);
        category.setBounds(300, 160, 400, 30);
        category.setBackground(Color.white);
        add(category);

        JLabel incomeL = new JLabel("Income:");
        incomeL.setFont(new Font("Railway", Font.BOLD, 20));
        incomeL.setBounds(100, 220, 200, 30);
        add(incomeL);

        String incomecategory[] = {"Null","< 1,50,000","< 2,50,000","< 5,00,000","Upto 10,00,000"};
        income = new JComboBox(incomecategory);
        income.setBounds(300, 220, 400, 30);
        income.setBackground(Color.white);
        add(income);

        JLabel educationL = new JLabel("Educational:");
        educationL.setFont(new Font("Railway", Font.BOLD, 20));
        educationL.setBounds(100, 270, 200, 30);
        add(educationL);

        JLabel qualificationL = new JLabel("Qualification:");
        qualificationL.setFont(new Font("Railway", Font.BOLD, 20));
        qualificationL.setBounds(100, 300, 200, 30);
        add(qualificationL);

        String educationValues[] = {"Non-Graduate","Graduate","Post-Graduation","Doctrate","Others"};
        education = new JComboBox(educationValues);
        education.setBounds(300, 280, 400, 30);
        education.setBackground(Color.white);
        add(education);

        JLabel occupationL = new JLabel("Occupation:");
        occupationL.setFont(new Font("Railway", Font.BOLD, 20));
        occupationL.setBounds(100, 350, 200, 30);
        add(occupationL);

        String occupationValues[] = {"Salaried","Self-Employed","Bussiness","Student","Retired","Others"};
        occupation = new JComboBox(occupationValues);
        occupation.setBounds(300, 350, 400, 30);
        occupation.setBackground(Color.white);
        add(occupation);

        JLabel panN = new JLabel("Pan Number:");
        panN.setFont(new Font("Railway", Font.BOLD, 20));
        panN.setBounds(100, 410, 200, 30);
        add(panN);

        pan = new JTextField();
        pan.setFont(new Font("Railway", Font.BOLD, 14));
        pan.setBounds(300, 410, 400, 30);
        add(pan);

        JLabel aadharN = new JLabel("Aadhar Number:");
        aadharN.setFont(new Font("Railway", Font.BOLD, 20));
        aadharN.setBounds(100, 460, 200, 30);
        add(aadharN);

        aadhar = new JTextField();
        aadhar.setFont(new Font("Railway", Font.BOLD, 14));
        aadhar.setBounds(300, 460, 400, 30);
        add(aadhar);

        JLabel state = new JLabel("Senior Citizen:");
        state.setFont(new Font("Railway", Font.BOLD, 20));
        state.setBounds(100, 510, 200, 30);
        add(state);

        syes = new JRadioButton("Yes");
        syes.setBounds(300, 510, 100, 30);
        syes.setBackground(Color.white);
        add(syes);

        sno = new JRadioButton("No");
        sno.setBounds(450, 510, 100, 30);
        sno.setBackground(Color.white);
        add(sno);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(syes);
        seniorGroup.add(sno);


        JLabel eaccount = new JLabel("Existing Account:");
        eaccount .setFont(new Font("Railway", Font.BOLD, 20));
        eaccount .setBounds(100, 560, 200, 30);
        add(eaccount );

        eyes = new JRadioButton("Yes");
        eyes.setBounds(300, 560, 100, 30);
        eyes.setBackground(Color.white);
        add(eyes);

        eno = new JRadioButton("No");
        eno.setBounds(450, 560, 100, 30);
        eno.setBackground(Color.white);
        add(eno);

        ButtonGroup eaccountGroup = new ButtonGroup();
        eaccountGroup.add(eyes);
        eaccountGroup.add(eno);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.setFont(new Font("Railway", Font.BOLD, 14));
        next.setBounds(620, 630, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
//        String formno = "" + random;//long
        String sreligion = (String)religion.getSelectedItem();
        String scategory = (String)category.getSelectedItem();
        String sincome = (String)income.getSelectedItem();
        String seducation = (String)education.getSelectedItem();
        String soccupation = (String)occupation.getSelectedItem();
        String seniorcitizen = null;
        if (syes.isSelected()) {
            seniorcitizen = "Yes";
        } else if (sno.isSelected()) {
            seniorcitizen = "No";
        }

        String existingaccount = null;
        if (eyes.isSelected()) {
            existingaccount = "Yes";
        } else if (eno.isSelected()) {
            existingaccount = "No";
        }
        String span = pan.getText();
        String saadhar = aadhar.getText();
        try {
            Conn c = new Conn();
            String query = "insert into signuptwo values('" + formno + "','" +sreligion+ "', '" + scategory + "','" + sincome + "','" + seducation + "','" + soccupation + "','" + span + "','" + saadhar + "','" + seniorcitizen + "','" + existingaccount + "')";//"String'"+variable+"'String"
            c.s.executeUpdate(query);

            //Signup3 object
            setVisible(false);
            new SignupThree(formno).setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupTwo("");
    }

}