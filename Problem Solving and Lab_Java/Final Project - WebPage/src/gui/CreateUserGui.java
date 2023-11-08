package gui;

import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static user.UserFile.addUser;

public class CreateUserGui extends JFrame {
    private JPanel primary;
    private JPanel pnId;
    private JPanel pnPw;
    private JPanel pnBtn;
    private JLabel lblId;
    private JLabel lblPw;
    private JButton btnSub;
    private JTextField tfId;
    private JPasswordField tfPw;

    private final Dimension lblSize = new Dimension(80,30);
    private final Dimension btnSize = new Dimension(100,25);
    private final Dimension pnSize = new Dimension(200,75);

    CreateUserGui() {
        super("Create Your Account");
        init();
        display();
        addListener();

        setSize(200 , 300);
        add(primary);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {
        primary = new JPanel();
        primary.setBackground(Color.white);

        pnId = new JPanel();
        pnId.setBackground(Color.white);
        pnId.setPreferredSize(pnSize);

        pnPw = new JPanel();
        pnPw.setBackground(Color.white);
        pnPw.setPreferredSize(pnSize);

        pnBtn = new JPanel();
        pnBtn.setBackground(Color.white);
        pnBtn.setPreferredSize(pnSize);

        lblId = new JLabel("ID:");
        lblId.setPreferredSize(lblSize);

        lblPw = new JLabel("Password");
        lblPw.setPreferredSize(lblSize);

        btnSub = new JButton("Submit");
        btnSub.setPreferredSize(btnSize);

        tfId = new JTextField(10);
        tfPw = new JPasswordField(10);
    }

    private void display() {
        pnId.add(lblId);
        pnId.add(tfId);

        pnPw.add(lblPw);
        pnPw.add(tfPw);

        pnBtn.add(btnSub);

        primary.add(pnId);
        primary.add(pnPw);
        primary.add(pnBtn);
    }

    private void addListener(){
        btnSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfId.getText()!=null && tfPw.getText()!=null){
                    addUser(new User(tfId.getText(), tfPw.getText()));
                    setVisible(false);
                    LoginGui.setLoginGuiVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Enter the Value!");
                }
            }
        });
    }
}
