package GUİ;

import CODE.Tree;
import CODE.TreeInterface;
import CODE.TreeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame  {
    public MainFrame(){
        // Set the JFrame title
        setTitle("Main Frame");

        // Set the size of the JFrame directly
        this.setSize(600, 700);

        // Ensure the application exits on close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // Set layout manager (optional, default is BorderLayout)
        this.setLayout(null);

        // Make the frame visible
        this.setVisible(true);

        JTextArea outputArea = new JTextArea();
        outputArea.setLineWrap(true); // Wrap text lines if they are too long
        outputArea.setWrapStyleWord(true); // Wrap at word boundaries for better readability

// Create a JScrollPane to make the JTextArea scrollable
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(200, 10, 370, 500); // Set position and size of the scroll pane

// Add the JScrollPane (not the JTextArea directly) to the frame
        this.add(scrollPane);

        // input
        JTextField input = new JTextField();
        input.setBounds(200 , 600 , 370 , 30);
        this.add(input);

        // buttons
        JButton SearchForSpeciesButton = new JButton();
        SearchForSpeciesButton.setText("search for species");
        SearchForSpeciesButton.setBounds(10, 10, 180, 100);
        this.add(SearchForSpeciesButton);

        SearchForSpeciesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = input.getText();
                TreeInterface tree =  Tree.GetInstance();
                TreeNode node = tree.GetById(id);
                String visualized = node.toStringInterpolated();
                outputArea.setText(visualized);
            }
        } );

        JButton TraversethetreeButton = new JButton();
        TraversethetreeButton.setText("Traverse the tree");
        TraversethetreeButton.setBounds(10, 120, 180, 100);
        this.add(TraversethetreeButton);

        TraversethetreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeInterface tree =  Tree.GetInstance();
                tree.LogTree();
            }
        } );


        JButton PrintthesubtreeofagivenspeciesinpreorderButton = new JButton();
        PrintthesubtreeofagivenspeciesinpreorderButton.setText("<html>Print the subtree<br> of a given species in <br> pre-order</html>");
        PrintthesubtreeofagivenspeciesinpreorderButton.setBounds(10, 230, 180, 100);
        this.add(PrintthesubtreeofagivenspeciesinpreorderButton);

        PrintthesubtreeofagivenspeciesinpreorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = input.getText();
                TreeInterface tree =  Tree.GetInstance();
                List<String> subTreeNodes = tree.getSubTreePreOrder(id);
                String content = tree.mergeListOfString(subTreeNodes);

                outputArea.setText(content);
            }
        } );


        JButton PrinttheancestorpathofthegivenspeciesButton = new JButton();
        PrinttheancestorpathofthegivenspeciesButton.setText("<html>Print the <br> ancestor path of<br> the given species.</html>");
        PrinttheancestorpathofthegivenspeciesButton.setBounds(10, 340, 180, 100);
        this.add(PrinttheancestorpathofthegivenspeciesButton);

        PrinttheancestorpathofthegivenspeciesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = input.getText();
                TreeInterface tree =  Tree.GetInstance();
                List<TreeNode> path = tree.getAncestors(id);
                String content = "";
                for(TreeNode cur : path){
                    content += cur.toStringInterpolated();
                    content += "\n";
                }
                outputArea.setText(content);
            }
        } );


        JButton findCommonAncestorButton = new JButton();
        findCommonAncestorButton.setText("<html>Find the most recent<br>common ancestor of<br>the given two species.</html>");
        findCommonAncestorButton.setBounds(10, 450, 180, 100); // Set position and size
        this.add(findCommonAncestorButton);
        findCommonAncestorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = input.getText().split(" ")[0];
                String id2 = input.getText().split(" ")[1];
                TreeInterface tree =  Tree.GetInstance();
                TreeNode commonAncestor = tree.mostCommonAncestor(id1 , id2);
                String content = commonAncestor.toStringInterpolated();
                outputArea.setText(content);
            }
        } );



        JButton getHeightButton = new JButton();
        getHeightButton.setText("<html>calc height</html>");
        getHeightButton.setBounds(10, 570, 180, 20); // Set position and size
        this.add(getHeightButton);

        getHeightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeInterface tree =  Tree.GetInstance();
                int height = tree.getHeight();
                String content = String.valueOf(height);
                outputArea.setText(content);
            }
        } );


        JButton getBreadthButton = new JButton();
        getBreadthButton.setText("<html>calc breadth</html>");
        getBreadthButton.setBounds(10, 600, 180, 20); // Set position and size
        this.add(getBreadthButton);

        getBreadthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeInterface tree =  Tree.GetInstance();
                int breadth = tree.getBreadth();
                String content = String.valueOf(breadth);
                outputArea.setText(content);
            }
        } );

        JButton getDegreeButton = new JButton();
        getDegreeButton.setText("<html>calc degree  of tree</html>");
        getDegreeButton.setBounds(10, 630, 180, 20); // Set position and size
        this.add(getDegreeButton);

        getDegreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeInterface tree =  Tree.GetInstance();
                int degree = tree.getMaxDegree();
                String content = String.valueOf(degree);
                outputArea.setText(content);
            }
        } );


    }

}
