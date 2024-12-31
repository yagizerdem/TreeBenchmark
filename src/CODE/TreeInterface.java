package CODE;

import java.util.List;

public interface TreeInterface {
    public TreeNode GetById(String id);
    public void LogTree();
    public List<String> getSubTreePreOrder(String id);
    public List<TreeNode> getAncestors(String id);
    public TreeNode mostCommonAncestor(String id1 , String id2);
    public List<List<TreeNode>> getLongestEvalutionaryPath();
    public int getHeight();
    public  int getBreadth();
    public int getMaxDegree();
    public String mergeListOfString(List<String> list);
}
