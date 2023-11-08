package gui;


import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static user.UserFile.findUser;

public class LoginGui extends JFrame {
    private static JFrame frame;
    private JPanel primary;
    private JPanel pnId;
    private JPanel pnPw;
    private JPanel pnBtn;
    private JLabel lblId;
    private JLabel lblPw;
    private JTextField tfId;
    private JPasswordField tfPw;
    private JButton btnLogin;
    private JButton btnSignup;
    private final Dimension lblSize = new Dimension(80,30);
    private final Dimension btnSize = new Dimension(100,25);
    private final Dimension pnSize = new Dimension(200,75);

    public static void setLoginGuiVisible(Boolean value){
        frame.setVisible(value);
    }
    public void init(){
        frame = new JFrame("Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        primary = new JPanel(new GridLayout(0,1));
        primary.setBackground(Color.lightGray);

        pnId = new JPanel();
        pnId.setBackground(Color.WHITE);
        pnId.setPreferredSize(pnSize);

        pnPw = new JPanel();
        pnPw.setBackground(Color.WHITE);
        pnPw.setPreferredSize(pnSize);

        pnBtn = new JPanel();
        pnBtn.setBackground(Color.WHITE);
        pnBtn.setPreferredSize(pnSize);

        lblId = new JLabel("ID:");
        lblId.setPreferredSize(lblSize);
        lblPw = new JLabel("Password:");
        lblPw.setPreferredSize(lblSize);

        tfId = new JTextField(10);
        tfPw = new JPasswordField(10);

        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(btnSize);
        btnSignup = new JButton("Join in");
        btnSignup.setPreferredSize(btnSize);
    }

    public void display() {
        pnId.add(lblId);
        pnId.add(tfId);

        pnPw.add(lblPw);
        pnPw.add(tfPw);

        pnBtn.add(btnLogin);
        pnBtn.add(btnSignup);

        primary.add(pnId);
        primary.add(pnPw);
        primary.add(pnBtn);

        frame.getContentPane().add(primary);
        frame.pack();
        frame.setVisible(true);
    }



    public void addListener(){
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 유저 확인
                if(findUser(new User(tfId.getText(), tfPw.getText()))!=null){
                    try {
                        new MainPage();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "ID or Password is wrong!");
                }
            }
        });

        btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateUserGui();
                frame.setVisible(false);
            }
        });
    }
}
