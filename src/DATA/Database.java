package DATA;
import CODE.TreeNode;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class Database {
    private static Database instance = null;
    public Hashtable<String, TreeNode> TreeNodes = new Hashtable<>();
    public List<String[]> Links = new ArrayList<>();
    private Database(){
        this.readData();
    }
    public static Database GetInstance(){
        if(Database.instance ==  null){
            Database.instance = new Database();
        }
        return  Database.instance;
    }

    public void readData(){

        // get nodes
        try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader("C:\\Users\\yagiz\\Desktop\\evolutionary tree\\src\\DATA\\treeoflife_nodes.csv"),
                CsvPreference.STANDARD_PREFERENCE)){
            String[] header = {"node_id","node_name","child_nodes","leaf_node","tolorg_link","extinct",
                    "confidence","phylesis"};
            beanReader.read(TreeNode.class,header); // skip header


            TreeNode entry;
            // Read data row by row, mapping it to MyDataBean
            while ((entry = beanReader.read(TreeNode.class, header)) != null) {
                // Print each record
                TreeNode newNode = entry;
                this.TreeNodes.put(newNode.getNode_id() , newNode);
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
            System.exit(1);
        }

        // get links
        try {
            File myObj = new File("C:\\Users\\yagiz\\Desktop\\evolutionary tree\\src\\DATA\\treeoflife_links.csv");
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine(); // skip header
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] ids = data.split(",");
                String[] from_to = new String[]{ids[0], ids[1]};
                this.Links.add(from_to);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
