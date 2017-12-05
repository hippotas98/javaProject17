package mygraph.graph;

import java.awt.List;
import java.util.*;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.shape.Line;
import analyst.ClassADT;
public class RectangleCell extends Cell {
	
	public RectangleCell(String _id, ClassADT clas ,int width, int height) {
		super(_id);
		Text name = new Text(getName(clas));
		name.setTranslateY(6);
		name.setTranslateX(width/2-getName(clas).length()*4);
		Rectangle rname = new Rectangle(width,28);
		rname.setStroke(Color.BLACK);
		rname.setFill(Color.WHITE);
		StackPane sname = new StackPane();
		//sname.setAlignment(name, Pos.CENTER);
		sname.getChildren().addAll(rname, name);
		setView(rname);
		setView(sname);
		String var = getVariable(clas);
		Text tVar = new Text(var);
		Rectangle rVar = new Rectangle(rname.getLayoutX(),rname.getLayoutY()+rname.getHeight(),width,count(var,"\n")*16);
		drawRec(tVar,rVar);
		String method = getMethod(clas);
		Text tMethod = new Text(method);
		Rectangle rMethod = new Rectangle(rname.getLayoutX(),rVar.getY()+rVar.getHeight(),width,count(method,"\n")*16);
		drawRec(tMethod,rMethod);
	}
	private void drawRec(Text text, Rectangle r) {
		r.setStroke(Color.BLACK);
		r.setFill(Color.WHITE);
		text.setTranslateY(r.getY());
		text.setTranslateX(5);
		StackPane sp = new StackPane();
		sp.getChildren().addAll(r,text);
		setView(r);
		setView(sp);
	}
	String getName(ClassADT clas) {
		return clas.getName();
	}
	String getVariable(ClassADT clas) {
		StringBuilder sb = new StringBuilder();
		for(String i : clas.getVariables()) {
			sb.append(i);
			sb.append("\n");
		}
		return sb.toString();
	}
	String getMethod(ClassADT clas) {
		StringBuilder sb = new StringBuilder();
		for(String i : clas.getMethod()) {
			sb.append(i);
			sb.append("\n");
		}
		return sb.toString();
	}
	int count (String s, String regex ) {
		String strs [] = s.split(regex);
		return strs.length;
	}
}
