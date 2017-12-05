package cde;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;
import java.io.*;

public class Cau1
{
    public static void main(String [] args) {
        String path = "./Utils.java";
        List<String> contents = findAllFunction(path);
        // for(String i : contents)
        // {
        //     System.out.println(i);
        // }
        //System.out.println(contents.size());
        String direc = findFunctionByName("readContentFromFile(String)",path);
        System.out.println(direc);
    }
    public static List<String> findAllFunction(String path)
    {
        Path filePath = Paths.get(path);
        List<String> lines = new ArrayList<String>();
        List<String> result = new ArrayList<String>();
        try (BufferedReader reader = Files.newBufferedReader(filePath,
        StandardCharsets.UTF_8)){
                int start = 0,end = 0;
                lines = Files.readAllLines(filePath,StandardCharsets.UTF_8);
                for(int i = 0;i<lines.size();)
                {
                    if(lines.get(i).contains("static"))
                    {
                        StringBuilder str = new StringBuilder();
                        start = i;
                        for(int j = i+1;j<lines.size();j++)
                        {
                            if(lines.get(j).contains("static"))
                            {
                                end = j;
                                break;
                            }
                            else if (j == lines.size()-1)
                            {
                                end = j;
                                break;
                            }
                        }
                        //System.out.println(start + " " + end);
                        List<String>temp = lines.subList(start,end);
                        for(String tmp : temp)
                        {
                            str.append(tmp);
                            str.append("\n");
                        }
                        result.add(str.toString());
                        i = end;
                    }
                    else i++;  
                }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }
    public static String findFunctionByName(String name,String path)
    {
        List<String> contents = findAllFunction(path);
        List<String> result = new ArrayList<String>();
        for(String i : contents)
        {
            int endLine = i.indexOf("\n");
            String tmp = i.substring(0,endLine);
            int idx1 = name.indexOf("(");
            String str1 = name.substring(0,idx1-1);
            int idx2 = name.indexOf(")");
            String str2 = name.substring(idx1+1,idx2-1);
            String [] list = str2.split(",");
            if(tmp.contains(str1))
            {
                tmp.replaceFirst("str1","");
                for(String j : list)
                {
                    if(!tmp.contains(j))
                    {
                        tmp.replaceFirst(j,"");
                        break;
                    }
                        
                }
                return i;
            }
            
        }
        return "Not Found";
    }
    
}
class cau2 
{
    int a;
    int b;
}