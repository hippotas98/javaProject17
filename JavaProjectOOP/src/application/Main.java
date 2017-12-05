package application;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


public class Main extends Application {
	List<List<String>> classInfor = new ArrayList<List<String>>();
	List<ClassADT> classes = new ArrayList<ClassADT>();
	Graph graph = new Graph();
	@Override
	public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        graph = new Graph();
        
        root.setCenter(graph.getScrollPane());

        Scene scene = new Scene(root, 1024, 768);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.show();
        String folderPath = "/Users/apple/Documents/Git/JavaProject/JavaProjectOOP/example project/abc";
        
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
	
	private void addGraphComponents() {
		Model model = graph.getModel();
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
			//System.out.println(pName + "   " + cls.getName());
			if(!pName.equals("null")) {
				String name =  cls.getPack()+"."+cls.getName();
				if(name.contains(" ("))
					name = name.substring(0,name.indexOf(" ("));
				model.addEdge(pName,name);
			}
		}
		graph.endUpdate();
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
			//ImageUtils.createImagefromString(clas.getInformation(), Cname);
			List<String> ls = clas.getInformation();
			classInfor.add(clas.getInformation());
			//System.out.println(clas.getPack()+" " + clas.getName());
			//index++;
		}
		classes = ClassADT.checkName(classes);
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
