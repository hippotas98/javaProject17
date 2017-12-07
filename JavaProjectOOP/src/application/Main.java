package application;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import mygraph.graph.Arrow;
import mygraph.graph.Cell;
import mygraph.graph.CellType;
import mygraph.graph.Graph;
import mygraph.graph.Model;
import mygraph.graph.layout.base.Layout;
import mygraph.graph.layout.random.RandomLayout;
import analyst.ImageUtils;
import analyst.Utils;
import analyst.ClassADT;
import analyst.InterfaceADT;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class Main extends Application {
	List<List<String>> classInfor = new ArrayList<List<String>>();
	List<ClassADT> classes = new ArrayList<ClassADT>();
	List<List<String>> interfaceInfor = new ArrayList<List<String>>();
	List<InterfaceADT> interfaces = new ArrayList<InterfaceADT>();
	Graph graph = new Graph();
	@Override
	public void start(Stage primaryStage) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
	       configuringDirectoryChooser(directoryChooser);
	       TextArea textArea = new TextArea();
	       textArea.setMinHeight(70);
	       Button button = new Button("Open DirectoryChooser and select a directory");
	       VBox root1 = new VBox();
	       root1.setPadding(new Insets(10));
	       root1.setSpacing(5);
	       button.setOnAction(new EventHandler<ActionEvent>() {
	           @Override
	           public void handle(ActionEvent event) {
	               File dir = directoryChooser.showDialog(primaryStage);
	               if (dir != null) {
	            	   	   String folderPath = dir.getAbsolutePath(); /*"/Users/apple/Documents/Git/javaProject17/JavaProjectOOP/example project/abc"*/;
	            	   	   List<String> lsFile = new ArrayList<String>();
	            	   	   if( (lsFile = Utils.readFileName(folderPath, lsFile)).size()==0)
	            	   	   {
	            	   		   Text error = new Text("No java file!!");
	            	   		   VBox verror = new VBox();
	            	   		   verror.setSpacing(5);
	            	   		   verror.getChildren().addAll(error);
	            	   		   Scene serror = new Scene(verror,200,50);
	            	   		   primaryStage.setScene(serror);
	            	   		   primaryStage.show();
	            	   	   }
	            	   	   else {
	            	   		   BorderPane root = new BorderPane();
		            	   	   graph = new Graph();
		           	    	   Scene scene = new Scene(root, 1024, 768);
		           	    	   root.setCenter(graph.getScrollPane());
		           	    	   primaryStage.setScene(scene);
		           	    	   primaryStage.show();
		           	    	   getInterfaceInformation(folderPath);
		           	    	   getClassInformation(folderPath);
		           	    	   addGraphComponents();
		           	    	   Layout layout = new RandomLayout(graph);
		           	    	   List<Cell> cells = graph.getModel().getAllCells();
		           	    	   Random rnd = new Random();
		           	    	   for (Cell cell : cells) {
		           	    		   double x = rnd.nextDouble() * 500;
		           	    		   double y = rnd.nextDouble() * 500;
		           	    		   cell.relocate(x,  y);
		           	    	   }  
	            	   	   } 
	               }
	           	   else {
	                   textArea.setText(null);
	               }
	           }
	       });
	       Text program = new Text("Phan tich ma nguon");
	       root1.getChildren().addAll(program, button);
	       Scene scene1 = new Scene(root1, 400, 200);
	       primaryStage.setTitle("Select Directory");
	       primaryStage.setScene(scene1);
	    	   primaryStage.show();
	}
	
	private void addGraphComponents() {
		Model model = graph.getModel();
		//add class
		for(ClassADT cls : classes) {
			String name = cls.getPack()+"."+cls.getName();
			if(name.contains(" ("))
				name = name.substring(0, name.indexOf(" ("));
			//List<String> content = cls.getInformation();
			model.addCell(name, cls, CellType.RECTANGLE);
		}
		for(ClassADT cls : classes) {
			String pName;
			if(!cls.getSuperClass().contains(".")&&!cls.getSuperClass().equals("null")){
				pName = cls.getPack()+ "."+ cls.getSuperClass();
			}
			else {
				pName = cls.getSuperClass();
			}
			if(!pName.equals("null")) {
				String name =  cls.getPack()+"."+cls.getName();
				if(name.contains(" ("))
					name = name.substring(0,name.indexOf(" ("));
				model.addEdge(name, pName);
			}
		}
		//add interface
		for(InterfaceADT intface : interfaces) {
			String name = intface.getPack()+"."+intface.getName();
			if(name.contains(" ("))
				name = name.substring(0, name.indexOf(" ("));
			model.addCell(name, intface, CellType.RECTANGLE);
		}
		for(ClassADT cls : classes) {
			List<String> lsInterface = cls.getInterfaces();
			String name = cls.getPack()+"."+cls.getName();
			if(name.contains(" ("))
				name = name.substring(0, name.indexOf(" ("));
			if(lsInterface.size()!=0) {
				for(String i : lsInterface) {
					if(i.contains(".")) {
						model.addEdge(name, i);
					}
					else {
						model.addEdge(name, cls.getPack()+"."+i);
					}
				}
			}
		}
		for(InterfaceADT intface : interfaces) {
			List<String> lsinterfaces = intface.getInterfaces();
			String name = intface.getPack()+"."+intface.getName();
			if(name.contains(" ("))
				name = name.substring(0, name.indexOf(" ("));
			if(lsinterfaces.size()!=0) {
				for(String i : lsinterfaces) {
					if(i.contains(".")) {
						model.addEdge(name, i);
					}
					else {
						model.addEdge(name, intface.getPack()+"."+i);
					}
				}
			}
		}
		graph.endUpdate();
	}
	private void getInterfaceInformation(String filePath) {
		InterfaceADT.getInterfacesContent(filePath);
		InterfaceADT.getInterfacesName();
		for(String content : InterfaceADT.lsInterface) {
			int indx = InterfaceADT.lsInterface.indexOf(content);
			String name = InterfaceADT.lsInterface_name.get(indx);
			InterfaceADT itface = InterfaceADT.createInterface(content, name, indx);
			interfaceInfor.add(itface.getInformation());
			interfaces.add(itface);
		}
		interfaces = InterfaceADT.checkName(interfaces);
	}
	private void getClassInformation(String filePath) {
		String path = filePath;
		//int index = 0;
		ClassADT.getClassContent(path);
		ClassADT.getClassName();
		for(String content : ClassADT.lsClass){
			int indx = ClassADT.lsClass.indexOf(content);
			String Cname = ClassADT.lsClass_name.get(indx);
			ClassADT clas = ClassADT.createClassADT(content, Cname, indx);
			classes.add(clas);
			classInfor.add(clas.getInformation());
		}
		classes = ClassADT.checkName(classes);
	}
	
	private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
	       // Set tiêu đề cho DirectoryChooser
	       directoryChooser.setTitle("Select Some Directories");
	       // Sét thư mục bắt đầu nhìn thấy khi mở DirectoryChooser
	       directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
