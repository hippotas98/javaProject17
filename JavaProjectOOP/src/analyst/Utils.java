package analyst;
import java.util.*;
import java.io.*;
import org.eclipse.jdt.core.dom.*;

public class Utils{
	static List<String> readContentFromFile(String path){ //create source file 
        String line;
        List<String> strs = new ArrayList<String>();
        List<String> files = new ArrayList<String>();
        files = readFileName(path, files);
        for(String file : files) {
        		String pack = getPackName(file);
        		try(BufferedReader in = new BufferedReader(new FileReader(file))) {
                while((line=in.readLine())!=null){
                		if(line.contains("class")) {
                			StringBuilder linewithPackage = new StringBuilder(")");
                			linewithPackage.append(pack);
                			linewithPackage.append(" ");
                			linewithPackage.append(line);
                			line = linewithPackage.toString();
                			//System.out.println(line);
                		}
                    strs.add(line);
                }
                strs = removeComment(strs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return strs;
    }
	static List<String> removeComment(List<String> lines){	
		List<String> list = new ArrayList<String>();
		int start = lines.size(),end = -1;
		for(int i = 0;i<lines.size();++i){
			String line = lines.get(i);
			String s = line.replaceAll(" ", "");
			if(s.indexOf("//")!=0) {
				if(s.indexOf("/*")==0){
					start = lines.indexOf(line);
					for(int indxComment = lines.indexOf(line);indxComment<lines.size();++indxComment) {
						if(lines.get(indxComment).contains("*/")) {
							end = indxComment;
							break;
						}
					}
				}
				if(i < start || i > end)
				{
					list.add(line);
				}
			}
		}
		return list;
	}
	public static List<String> readFileName(String path, List<String> ls)
    {
        File folder = new File(path);
        if(folder.isDirectory()) {
        		for(File file : folder.listFiles()){
                if(file.isDirectory()==false)
                {
                    StringBuilder temp = new StringBuilder();
                    temp.append(file.getParent());
                    temp.append("/");
                    if(file.getName().contains(".java") && file.getName().indexOf(".")!=0)
                    {
                    		temp.append(file.getName());
                    		ls.add(temp.toString());
                    }
                }
            }
            for(File file : folder.listFiles()){
                if(file.isDirectory())
                {
                    ls = readFileName(file.getPath(),ls);
                }
            }
        }
        return ls;
    }
	static final CompilationUnit generateAST(String source)
	{
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(source.toCharArray());
		return (CompilationUnit) parser.createAST(null);
	}
	static String getPackName(String filePath) {
		String folders [] = filePath.split("/");
		return folders[folders.length-2];
	}
}