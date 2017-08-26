package ui;

import business.PostBusiness;
import entitiy.PostEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PostListForm extends JFrame implements ListSelectionListener {
    private JTable tablePost;
    private JPanel rootPanel;
    private PostBusiness mPostBusiness = new PostBusiness();

    public PostListForm() {
        super();
        getAllPosts();
        setContentPane(rootPanel);
        setSize(250, 250);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setEvents();

    }

    private void setEvents() {
        tablePost.getSelectionModel().addListSelectionListener(this);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int postId;
        if(e.getValueIsAdjusting()) {
            postId = Integer.parseInt(tablePost.getValueAt(tablePost.getSelectedRow(), 0).toString());
            new PostDetailForm(postId);
        }
    }

    private void getAllPosts() {
        String[] columnNames = {"Id", "Título"};
        List<PostEntity> postEntities = mPostBusiness.getAllPosts();
        DefaultTableModel tableModel = new DefaultTableModel(new Object[0][0], columnNames);

        int i = 0;
        for(PostEntity p: postEntities) {
            Object[] o = new Object[2];
            o[0] = p.getId();
            o[1] = p.getTitle();

            tableModel.addRow(o);

            i++;
            if(i >= 10) break;

        }
        tablePost.setModel(tableModel);
    }
}
