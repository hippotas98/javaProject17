package w5;
import javax.swing.*;
import java.io.*;
import java.nio.*;
import java.util.*;
class Utils
{
    static List<String> readFileJava(String fileName)
    {
        List<String> file = new ArrayList<String>();
        try (BufferedReader fin = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while((line=fin.readLine())!=null) 
            {
                file.add(line);
            }   
        } 
        catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return file;
    }
    public static List<String> readClassfromFile(String fileName)
    {
        List<String> classes = new ArrayList<String>();
        List<String> temp = readFileJava(fileName);
        System.out.println(temp.size());
        for(int i = 0;i<temp.size();)
        {
            int index = 0;
            if(temp.get(i).contains("class"))
            {
                
                StringBuilder tmp = new StringBuilder();
                for(int j = i + 1;j<temp.size();++j)
                {
                    if(temp.get(j).contains("class"))
                    {
                        index = j; 
                        break;
                    } 
                    else if (j==temp.size()-1)
                    {
                        index = j;
                        break;
                    }
                }
                List<String> strs = temp.subList(i,index);
                for(String str : strs)
                {
                    tmp.append(str);
                    tmp.append("\n");
                }
                classes.add(tmp.toString());
                i = index;
            }
            else i++;
        }
        return classes;
    }
    public static List<String> readClassNamefromFile (String fileName)
    {
        List<String> classes = readFileJava(fileName);
        List<String> result = new ArrayList<String>();
        for(String str : classes)
        {
            if(str.contains("class"))
            {
                String [] strstmp = str.split(" ");
                for(int i = 0;i<strstmp.length;++i)
                {
                    if(strstmp[i].equals("class"))
                    {
                        result.add(strstmp[i+1]);
                        break;
                    }
                }
            }
        }
        return result;
    }
}
class JTableExample
{
    JFrame f;
    JTableExample()
    {
        String fileName = "cau3.java";
        List<String> classes = Utils.readClassNamefromFile(fileName);
        f = new JFrame();
        f.setSize(300,400);
        f.setLayout(null);
        f.setVisible(true);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Test hehe");
        String column [] = {"Class"};
        String data[][] = {{"Hello"},{"test"}};
        // String [][] data = new String [classes.size()][1];
        // int idx =0;
        // for(String str: classes)
        // {
        //     data[idx][0] = str;
        //     idx++;
        // }
        JTable jt = new JTable(data, column);
        jt.setBounds(30,40,200,300); 
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);
        //f.add(jt);
        // for(String str : classes)
        //     System.out.println(str);
    }
}
public class problem2{
    
    public static void main (String [] args)
    {
        new JTableExample();
        //System.out.println(classes.get(0));
    }
}