package analyst;
import java.util.*;
import org.eclipse.jdt.core.dom.*;
public class InterfaceADT {
	String name;
	int indx;
	String pack;
	List<String> interfaces = new ArrayList<String>();
	List<String> methods = new ArrayList<String>();
	public static List<String> lsInterface = new ArrayList<String>();
	public static List<String> lsInterface_name = new ArrayList<String>();
	static List<String> getInterfaces(CompilationUnit cu,String name){
		List<String> rs = new ArrayList<String>();
			cu.accept(new ASTVisitor() {
				@SuppressWarnings("unchecked")
				public boolean visit(TypeDeclaration node){
					if(node!=null){
						while(node.getParent() instanceof TypeDeclaration) {
							TypeDeclaration nodeP = (TypeDeclaration) node.getParent();
							if(nodeP.getName().toString().equals(name)) {
								List<Type> ls = nodeP.superInterfaceTypes();
								for(Type sc : ls) {
									rs.add(sc.toString());
								}
								break;
							}
							else {
							    node = (TypeDeclaration) node.getParent();
							}
						}
						if(!(node.getParent() instanceof TypeDeclaration)){
							List<Type> ls = node.superInterfaceTypes();
							for(Type sc : ls) {
								rs.add(sc.toString());
							}
						}
					}
					else rs.add("null");
					return true;
			}});
		return rs;
	}
	public static void getInterfacesName(){
		for(String Interface : lsInterface){
			String [] _1stline = Interface.substring(0, Interface.indexOf("{")).split(" ");
			for(int i = 0;i<_1stline.length;++i){
				if(_1stline[i].equals("interface"))
					lsInterface_name.add(_1stline[i+1].replaceAll("\n", ""));
			}
		}
    }
	public static void getInterfacesContent(String fileName){
		List<String> temp = Utils.readContentFromFile(fileName);
		for(int i = 0;i<temp.size();){
            int index = 0;
            int counter = 0;
            if(temp.get(i).contains("interface")){
            		if(temp.get(i).contains("{")) {
            			counter+=1;
            		}
                StringBuilder tmp = new StringBuilder();
                for(int j = i + 1;j<temp.size();++j){
                		  if(temp.get(j).contains("{")) {
                			  counter+=1;
                		  }
                		  if(temp.get(j).contains("}")) {
                			  counter-=1;
                		  }
                		  if(counter==0) {
                			  index = j+1;
                			  break;
                		  }
                }
                List<String> strs = temp.subList(i,index);
                for(String str : strs){
                    tmp.append(str);
                    tmp.append("\n");
                }
                lsInterface.add(tmp.toString());
                i = index;
            }
            else i++;
        }    
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getPack() {
		return this.pack;
	}
	public List<String> getMethods(){
		return this.methods;
	}
	public List<String> getInterfaces(){
		return this.interfaces;
	}
	String getModifiers(int flags){
		String modifier = new String();
		if(Modifier.isDefault(flags)) {
			modifier = "~ ";
		}
		if(Modifier.isPublic(flags)) {
			modifier = "+ ";
		}
		else if(Modifier.isPrivate(flags)) {
			modifier = "- ";
		}
		else if(Modifier.isProtected(flags)) {
			modifier = "# ";
		}
		else modifier = "~ ";
		return modifier;
	}
	void setMethods(CompilationUnit cu){
		cu.accept(new ASTVisitor() {
			public boolean visit(TypeDeclaration node) {
				if(node.isInterface()) {
					MethodDeclaration[] lsMethods = node.getMethods();
					for(MethodDeclaration method : lsMethods) {
						if(!method.isConstructor()) {
							String name = method.getName().toString();
							String type = method.getReturnType2().toString();
							if(type!=null) {
								List<SingleVariableDeclaration> parameter = method.parameters();
								String para_name = "(";
								String str="";
								for(SingleVariableDeclaration i : parameter){
									str += i.getType().toString() + " " + i.getName();
									if(parameter.listIterator().hasNext()==false)
										str += ",";
								}
								para_name = para_name +  str + ")";
								String modifier = getModifiers(method.getModifiers());
								String infor = modifier + type + " " + name + para_name;
								methods.add(infor);
							}
						}
					}
				}
				return true;
			}
		});
	}
	
	void setInterfaces(CompilationUnit cu){
		cu.accept(new ASTVisitor() {
			public boolean visit(TypeDeclaration node) {
					if(node!=null){
						while(node.getParent() instanceof TypeDeclaration) {
							TypeDeclaration nodeP = (TypeDeclaration) node.getParent();
							if(nodeP.getName().toString().equals(name)) {
								@SuppressWarnings("unchecked")
								List<Type> ls = nodeP.superInterfaceTypes();
								for(Type sc : ls) {
									interfaces.add(sc.toString());
								}
								break;
							}
							else {
							    node = (TypeDeclaration) node.getParent();
							}
						}
						if(!(node.getParent() instanceof TypeDeclaration)){
							List<Type> ls = node.superInterfaceTypes();
							for(Type sc : ls) {
								interfaces.add(sc.toString());
							}
						}
					}
					//else interfaces.add("null");
					return true;
				}
		});
	}
	public static InterfaceADT createInterface(String content, String name,int indx){
		List<String> interfaces = new ArrayList<String>();
		List<String> methods = new ArrayList<String>();
		CompilationUnit cu = Utils.generateAST(content);
		InterfaceADT ex = new InterfaceADT(name,interfaces,methods);
		ex.setInterfaces(cu);
		ex.setMethods(cu);
		ex.indx = indx;
		ex.setPackageName();
		return ex;
	}
	public InterfaceADT(String name, List<String> interfaces, List<String> methods){
		this.name = name;
		this.interfaces = interfaces;
		this.methods = methods;
	}
	public List<String> getInformation(){
		List<String> infor = new ArrayList<String>();
		String name = this.name;
		infor.add(name);
		infor.add("");
		for(String method : methods) {
			infor.add(method);
		}
		infor.add("");
		return infor;
	}
	void setPackageName() {
		String firstLine = lsInterface.get(indx).substring(0,lsInterface.get(indx).indexOf("{"));
		firstLine = firstLine.split(" ")[0];
		pack = firstLine.substring(1);
	}
	public static List<InterfaceADT> checkName(List<InterfaceADT> interfaces) {
		for(int i = 0;i<interfaces.size()-1;++i) {
			for(int j = i+1;j<interfaces.size();++j) {
				if(interfaces.get(i).getName().equals(interfaces.get(j).getName())) {
					interfaces.get(i).setName(interfaces.get(i).getName()+" ("+interfaces.get(i).getPack()+")");
					interfaces.get(j).setName(interfaces.get(j).getName()+" ("+interfaces.get(j).getPack()+")");
				}
			}
		}
		return interfaces;
	}
}
