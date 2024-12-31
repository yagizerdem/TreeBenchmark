package CODE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeNode  {
    private String node_id;
    private String node_name;
    private String child_nodes;
    private String leaf_node;
    private String tolorg_link;
    private String extinct;
    private String confidence;
    private String phylesis;

    public int depth  = 0;
    public List<TreeNode> ChildrenTreeNodes = new ArrayList<>();

    public TreeNode ParentTreeNode = null;

    // Method to return interpolated string
    public String toStringInterpolated() {
        return String.format(
                "Node ID: %s, Node Name: %s, Child Nodes: %s, Leaf Node: %s, TolOrg Link: %s, Extinct: %s, Confidence: %s, Phylesis: %s",
                node_id, node_name, child_nodes, leaf_node, tolorg_link, extinct, confidence, phylesis
        );
    }


    // Setter for node_id
    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    // Getter for node_id
    public String getNode_id() {
        return node_id;
    }

    // Setter for node_name
    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    // Getter for node_name
    public String getNode_name() {
        return node_name;
    }

    // Setter for child_nodes
    public void setChild_nodes(String child_nodes) {
        this.child_nodes = child_nodes;
    }

    // Getter for child_nodes
    public String getChild_nodes() {
        return child_nodes;
    }

    // Setter for leaf_node
    public void setLeaf_node(String leaf_node) {
        this.leaf_node = leaf_node;
    }

    // Getter for leaf_node
    public String getLeaf_node() {
        return leaf_node;
    }

    // Setter for tolorg_link
    public void setTolorg_link(String tolorg_link) {
        this.tolorg_link = tolorg_link;
    }

    // Getter for tolorg_link
    public String getTolorg_link() {
        return tolorg_link;
    }

    // Setter for extinct
    public void setExtinct(String extinct) {
        this.extinct = extinct;
    }

    // Getter for extinct
    public String getExtinct() {
        return extinct;
    }

    // Setter for confidence
    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    // Getter for confidence
    public String getConfidence() {
        return confidence;
    }

    // Setter for phylesis
    public void setPhylesis(String phylesis) {
        this.phylesis = phylesis;
    }

    // Getter for phylesis
    public String getPhylesis() {
        return phylesis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return Objects.equals(getNode_id(), treeNode.getNode_id());
    }
}
