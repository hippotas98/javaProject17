package analyst;
import java.util.*;
import org.eclipse.jdt.core.dom.*;
public class ClassADT {
	String name;
	String pack;
	int indexofClass;
	List<String> methods = new ArrayList<String>();
	List<String> interfaces = new ArrayList<String>();
	List<String> variables = new ArrayList<String>();
	List<String> innerclass = new ArrayList<String>();
	String superclass = "null";
	public static List<String> lsClass = new ArrayList<String>(); // list class
	public static List<String> lsClass_name = new ArrayList<String>(); // class name
	boolean abs = false;
	List<String> hasaClass = new ArrayList<String>();
	public String getName(){
		return this.name;
	}
	public int getIndexOfClass() {
		return this.indexofClass;
	}
	public void setIndexOfClass(int index) {
		this.indexofClass = index;
	}
	public String getPack() {
		return this.pack;
	}
	void setName(String name){
		this.name = name;
	}
	public List<String> getMethod(){
		return this.methods;
	}
	public List<String> getVariables(){
		return this.variables;
	}
	public String getSuperClass(){
		return this.superclass;
	}
	public List<String> getInterfaces(){
		return this.interfaces;
	}
	public ClassADT(String name, List<String> method, List<String>variables, List<String> interfaces, 
			List<String> hasaClass, List<String> innerclass, boolean abs, String superclass, String pack){
		setName(name);
		this.methods = method;
		this.variables = variables;
		this.interfaces = interfaces;
		this.superclass = superclass;
		this.hasaClass = hasaClass;
		this.innerclass = innerclass;
		this.abs = abs;
		this.pack = pack;
	}
	public static ClassADT createClassADT(String content, String name,int index){
		CompilationUnit cu = Utils.generateAST(content);
		List<String> method = new ArrayList<String>();
		List<String> variables = new ArrayList<String>();
		//List<String> lsInterface = InterfaceADT.lsInterface_name;
		List<String> interfaces = new ArrayList<String>();
		List<String> hasaClass = new ArrayList<String>();
		List<String> innerclass = new ArrayList<String>();
		boolean abs = false;
		String spn = new String();
		String pack = new String();
		ClassADT clas =  new ClassADT(name,method,variables,interfaces,hasaClass,innerclass,abs,spn,pack);
		clas.setIndexOfClass(index);
		clas.setSuperClass(cu);
		clas.IsAbstract();
		clas.setVariable(cu);
		clas.setMethod(cu);
		clas.setInnerclass(cu);
		clas.setPackageName();
		clas.hasaFind();
		clas.interfaces = InterfaceADT.getInterfaces(cu,clas.name);
		return clas;
	}
	void setVariable(CompilationUnit cu){
		cu.accept( new ASTVisitor() {
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName();
				String type = new String();
				if(node.getParent() instanceof FieldDeclaration){
					FieldDeclaration fd = (FieldDeclaration) node.getParent();
					type = fd.getType().toString();
					String temp = getModifiers(fd.getModifiers()) + name.toString() +": " + type;
					variables.add(temp);
				}
				return true; 
			}});
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
	void setSuperClass (CompilationUnit cu){
		int indx = indexofClass;
		String firstLine = lsClass.get(indx).substring(lsClass.get(indx).indexOf("class"),lsClass.get(indx).indexOf("{"));
		if(firstLine.contains("extends")) {
			cu.accept(new ASTVisitor() {
				public boolean visit(TypeDeclaration node){
					if(node!=null){
						while(node.getParent() instanceof TypeDeclaration) {
							TypeDeclaration nodeP = (TypeDeclaration) node.getParent();
							if(nodeP.getName().toString().equals(name)) {
								Type sc = nodeP.getSuperclassType();
								superclass = sc.toString();
								break;
							}
							else {
							    node = (TypeDeclaration) node.getParent();
							}
						}
						if(!(node.getParent() instanceof TypeDeclaration)){
							Type sc = node.getSuperclassType();
							superclass = sc.toString();
						}
					}
					else superclass = "null";
					return true;
				}});
		}
		else superclass = "null";
	}
	void setMethod (CompilationUnit cu){
		cu.accept(new ASTVisitor() {
			String parentname = getName();
			public boolean visit(MethodDeclaration node){
				if(!node.isConstructor()) {
					String name = node.getName().toString();
					String type = node.getReturnType2().toString();
					if(type!=null) {
						List<SingleVariableDeclaration> parameter = node.parameters();
						String para_name = "(";
						String str="";
						for(SingleVariableDeclaration i : parameter){
							str += i.getType().toString() + " " + i.getName();
							if(parameter.listIterator().hasNext()==false)
								str += ",";
						}
						para_name = para_name +  str + ")";
						String method ="";
						String modifier = getModifiers(node.getModifiers());
						if(abs==false) {
							method += modifier + type + " " + name + para_name;
						}
						else
						{
							if(node.getBody()==null)
							{
								method = "abstract ";
							}
							method += modifier + type + " " + name + para_name;
						}
						if(node.getParent() instanceof TypeDeclaration){
							TypeDeclaration parent = (TypeDeclaration) node.getParent();
							if(parent.getName().toString().equals(parentname))
								methods.add(method);
						}
					}
				}
				return true;
			}});
	}
	public static void getClassContent(String fileName){
        List<String> temp = Utils.readContentFromFile(fileName);
        for(int i = 0;i<temp.size();){
            int index = 0;
            int counter = 0;
            if(temp.get(i).contains("class")){
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
                lsClass.add(tmp.toString());
                i = index;
            }
            else i++;
        }    
    }
	public static void getClassName(){
		for(String classcontent : lsClass){
			String [] _1stline = classcontent.substring(0, classcontent.indexOf("{")).split(" ");
			for(int i = 0;i<_1stline.length;++i){
				if(_1stline[i].equals("class"))
					lsClass_name.add(_1stline[i+1].replaceAll("\n", ""));
			}
		}
	}
	void IsAbstract(){
		String line = lsClass.get(indexofClass);
		line = line.substring(0,line.indexOf("{")-1);
		if(line.contains("abstract")){
			this.abs = true;
		}
	}
	public void hasaFind(){
		for(String variable : variables){
			String type = variable.split(" ")[0];
			for(String clas : lsClass_name){
				if(clas.equals(type)){
					hasaClass.add(clas);
				}
			}
			for(String cls : this.innerclass) {
				if(!cls.equals("null")) {
					if(cls.equals(type)) {
						hasaClass.add(cls);
					}
				}
			}
		}
		if(hasaClass.size()==0){
			hasaClass.add("null");
		}
	}
	void setInnerclass(CompilationUnit cu)
	{
		cu.accept(new ASTVisitor() {
			public boolean visit(TypeDeclaration node){
				if(!node.isPackageMemberTypeDeclaration()){
					innerclass.add(node.getName().toString());
				}
				return true;
			}
			public boolean visit(AnonymousClassDeclaration node){
				String name = "Anonymous ";
				name = name + node.toString();
				innerclass.add(name);
				return true;
			}
		});
		if(innerclass.size()==0){
			innerclass.add("null");
		}
	}
	public List<String> getInformation(){
		List<String> infor = new ArrayList<String>();
		String abs = this.abs == true ? "abstract " : "";
		String name = abs + this.name;
		infor.add(name);
		infor.add("");
		for(String variable : variables){
			infor.add(variable);
		}
		infor.add("");
		for(String method : methods) {
			infor.add(method);
		}
		infor.add("");
		return infor;
	}
	void setPackageName() {
		int indx = indexofClass;
		String firstLine = lsClass.get(indx).substring(0,lsClass.get(indx).indexOf("{"));
		firstLine = firstLine.split(" ")[0];
		pack = firstLine.substring(1);
	}
	public static List<ClassADT> checkName(List<ClassADT> classes) {
		for(int i = 0;i<classes.size()-1;++i) {
			for(int j = i+1;j<classes.size();++j) {
				if(classes.get(i).getName().equals(classes.get(j).getName())) {
					classes.get(i).setName(classes.get(i).getName()+" ("+classes.get(i).getPack()+")");
					classes.get(j).setName(classes.get(j).getName()+" ("+classes.get(j).getPack()+")");
				}
			}
		}
		return classes;
	}
}
