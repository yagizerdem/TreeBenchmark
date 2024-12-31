package CODE;

import DATA.Database;

import javax.print.attribute.standard.PresentationDirection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.ref.WeakReference;
import java.util.*;

public class Tree  implements  TreeInterface{

    private static Tree instace = null;
    public TreeNode root = null;
    private Tree(){
        this.initlize();
    }

    public static Tree GetInstance(){
        if(Tree.instace == null){
            Tree.instace = new Tree();
        }
        return  Tree.instace;
    }

    private void initlize(){
        Database db = Database.GetInstance();

        List<String[]> links = db.Links;
        Hashtable<String , TreeNode> treeNodes = db.TreeNodes;

        for(int i = 0; i < links.size() ; i++){
            String[] from_to = links.get(i);
            String from = from_to[0];
            String to = from_to[1];

            TreeNode parent = treeNodes.get(from);
            TreeNode child =  treeNodes.get(to);

            parent.ChildrenTreeNodes.add(child);
            child.ParentTreeNode = parent;
        }



        // set root
        this.root = treeNodes.get("1");
    }


    public TreeNode GetById(String id){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this.root);
        while (queue.size() > 0){
            TreeNode cur = queue.remove();
            if(cur.getNode_id().equals(id)){
                return  cur;
            }
            for(TreeNode treeNode : cur.ChildrenTreeNodes){
                queue.add(treeNode);
            }
        }

        return  null;
    }


    private  class PreOrderIterator<T> implements Iterator<TreeNode> {
        private Stack<TreeNode> stack;

        public PreOrderIterator(TreeNode root) {
            stack = new Stack<>();
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public TreeNode next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements");
            }

            TreeNode current = stack.pop();

            // Push right first so left is processed next
            for(TreeNode child : current.ChildrenTreeNodes){
                stack.push(child);
            }
            return  current;
        }
    }

    // perOrder
    public void LogTree(){
        try{
            PreOrderIterator<TreeNode> iterator = new PreOrderIterator<>(root);
            String filePath = "C:\\Users\\yagiz\\Desktop\\evolutionary tree\\src\\DATA\\preOrderLog.txt";

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));

            while (iterator.hasNext()){
                TreeNode cur = iterator.next();
                String record = cur.toStringInterpolated();

                writer.write(record);
                writer.newLine();
            }
            writer.flush();
            writer.close();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
            System.exit(1);
        }
    }


    public List<String> getSubTreePreOrder(String id){
       TreeNode root =  Database.GetInstance().TreeNodes.get(id);
        PreOrderIterator<TreeNode> iterator = new PreOrderIterator<>(root);
        List<String> lines = new ArrayList<>();

        while (iterator.hasNext()){
            String line = iterator.next().toStringInterpolated();
            lines.add(line);
        }
        return  lines;
    }


    public List<TreeNode> getAncestors(String id){
        TreeNode root =  Database.GetInstance().TreeNodes.get(id);
        List<TreeNode> list = new ArrayList<>();
        while (root != null){
            list.add(root);
            root = root.ParentTreeNode;
        }
        return  list;
    }

    public TreeNode mostCommonAncestor(String id1 , String id2){
        TreeNode node1 =  Database.GetInstance().TreeNodes.get(id1);
        TreeNode node2 =  Database.GetInstance().TreeNodes.get(id2);

        List<TreeNode> path1 = this.getAncestors(node1.getNode_id());
        List<TreeNode> path2 = this.getAncestors(node2.getNode_id());

        for(int i = 0 ; i < path1.size() ; i++){
            for(int j = 0  ; j < path2.size() ; j++){
                if(path1.get(i).equals(path2.get(j))){
                    return  path1.get(i);
                }
            }
        }

        return  null;
    }


    public List<List<TreeNode>> getLongestEvalutionaryPath(){
        List<List<TreeNode>> paths = new LinkedList<>();
        int maxDepth = Integer.MIN_VALUE;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(this.root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            for(TreeNode child : cur.ChildrenTreeNodes){
                child.depth = cur.depth +1 ;
                stack.push(child);
            }
            if(cur.ChildrenTreeNodes.size() == 0){
                if(cur.depth > maxDepth){
                    maxDepth = cur.depth;
                    List<TreeNode> reconstructedPath = this.getAncestors(cur.getNode_id());
                    paths.clear();
                    paths.add(reconstructedPath);
                }
                else if(cur.depth == maxDepth){
                    List<TreeNode> reconstructedPath = this.getAncestors(cur.getNode_id());
                    paths.add(reconstructedPath);
                }
            }
        }
        return  paths;
    }


    public int getHeight(){
        return  getHeightHelper(this.root);
    }
    private  int getHeightHelper(TreeNode root){
        if(root.ChildrenTreeNodes.size() == 0) return  1; // leaf node
        int maxHeight = Integer.MIN_VALUE;
        for(TreeNode child : root.ChildrenTreeNodes){
            int height = this.getHeightHelper(child) + 1 ;
            if(height > maxHeight) maxHeight = height;
        }
        return  maxHeight;
    }

    public  int getBreadth(){
        int counter = 0;
        PreOrderIterator<TreeNode> iterator = new PreOrderIterator<>(root);
        while (iterator.hasNext()){
            TreeNode cur = iterator.next();
            if(cur.ChildrenTreeNodes.size() == 0){
                counter++;
            }
        }
        return  counter;
    }

    public int getMaxDegree(){
        int maxDegree = Integer.MIN_VALUE;
        PreOrderIterator<TreeNode> iterator = new PreOrderIterator<>(root);
        while (iterator.hasNext()){
            TreeNode cur = iterator.next();
            int curDegree = cur.ChildrenTreeNodes.size();
            maxDegree = Math.max(curDegree , maxDegree);
        }
        return  maxDegree;
    }

    public String mergeListOfString(List<String> list){
        String content = "";
        for(int i = 0 ; i < list.size() ; i++){
            String line = list.get(i);
            content += line;
            content += "\n";
        }
        return  content;
    }
}
