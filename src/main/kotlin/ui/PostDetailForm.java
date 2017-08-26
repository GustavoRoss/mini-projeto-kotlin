package ui;

import business.PostBusiness;
import entitiy.PostEntity;

import javax.swing.*;

public class PostDetailForm extends JFrame{
    private JPanel rootPanel;
    private JLabel lblTitle;
    private JLabel lblDescription;
    private PostBusiness mPostBusiness = new PostBusiness();

    public PostDetailForm(int postId){
        setContentPane(rootPanel);
        getSinglePost(postId);
        setSize(500, 250);
        setVisible(true);

    }

    public void getSinglePost(int postId){
        PostEntity postEntity = mPostBusiness.getSinglePost(postId);
        lblDescription.setText(postEntity.getBody());
        lblTitle.setText(postEntity.getTitle());
    }
}
