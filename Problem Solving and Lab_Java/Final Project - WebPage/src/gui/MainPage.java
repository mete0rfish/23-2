package gui;

import post.Post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import static post.PostFile.addPost;
import static post.PostFile.readFileByLine;

public class MainPage extends JFrame {
    private JPanel primary;
    private JPanel pnPostList;
    private JPanel pnBtn;
    private JPanel[] pnPostArr;
    private JLabel[] lblTitleArr;
    private JLabel[] lblDesArr;
    private JButton btnCreate;
    private JButton btnRefresh;

    private ArrayList<Post> postList;
    private int idx = 0;

    MainPage() throws FileNotFoundException {
        super("Main");
        primary = new JPanel(new GridLayout(0,1));
        primary.setBackground(Color.WHITE);

        setSize(1000 , 1000);
        init();

        primary.add(pnPostList);
        primary.add(pnBtn);
        add(primary);

        addListener();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void makePostPanel(Post post, JPanel pnPost, JLabel lblTitle, JLabel lblDes) {
        pnPost = new JPanel();
        lblTitle = new JLabel(post.getTitle());
        lblDes = new JLabel(post.getDescription());
        pnPost.add(lblTitle);
        pnPost.add(lblDes);

        pnPostList.add(pnPost);
    }

    private void readAllPosts() throws FileNotFoundException {
        postList = readFileByLine();
    }
    private void init() throws FileNotFoundException {
        pnPostList = new JPanel(new GridLayout(0,1));
        btnCreate = new JButton("Create New Post");
        btnRefresh = new JButton("Refresh");
        pnBtn = new JPanel();


        btnCreate.setPreferredSize(new Dimension(150,50));
        pnBtn.add(btnCreate);
        btnRefresh.setPreferredSize(new Dimension(150,50));
        pnBtn.add(btnRefresh);


        readAllPosts();
        pnPostArr = new JPanel[10];
        lblTitleArr = new JLabel[10];
        lblDesArr = new JLabel[10];
        Iterator<Post> iter = postList.iterator();

        while(iter.hasNext()) {
            makePostPanel(iter.next(), pnPostArr[idx], lblTitleArr[idx], lblDesArr[idx]);
            idx++;
        }

    }

    private void addListener(){
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                primary.revalidate();
                primary.repaint();
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Make a Post GUI
                JTextField title = new JTextField(5);
                JTextField description= new JTextField(10);

                JPanel pnCreatePostPrimary = new JPanel(new GridLayout(0,1));
                pnCreatePostPrimary.add(new JLabel("Title:"));
                pnCreatePostPrimary.add(title);
                pnCreatePostPrimary.add(Box.createHorizontalStrut(15));
                pnCreatePostPrimary.add(new JLabel("Description:"));
                pnCreatePostPrimary.add(description);

                int result = JOptionPane.showConfirmDialog(null, pnCreatePostPrimary,
                        "Enter Title and Description", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    System.out.println("Post Creation Success");
                }

                // 2. save to TXT file
                Post newPost = new Post(title.getText(), description.getText());
                addPost(newPost);


                // 3. update a list
                makePostPanel(newPost, pnPostArr[idx], lblTitleArr[idx], lblDesArr[idx]);
                idx++;

                // 4. repaint
                primary.revalidate();
                primary.repaint();
            }
        });
    }

}
