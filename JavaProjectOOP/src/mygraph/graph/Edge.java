package mygraph.graph;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge extends Group {
	
	Cell source;
	Cell target;
	
	Line line;
	
	public Edge(Cell _source, Cell _target) {
		source = _source;
		target = _target;
		
		Cell joint1 = new Cell("joint1");
		Cell joint2 = new Cell("joint2");
		
		source.addCellChild(target);
		target.addCellParent(source);
		
		line = new Line();
		
		Line line1, line2;
		line1 = new Line(); 
		line2 = new Line();
		
		line.startXProperty().bind(source.layoutXProperty().add(source.getBoundsInParent().getWidth()/2.0));
		line.startYProperty().bind(source.layoutYProperty().add(source.getBoundsInParent().getHeight()/2.0));
		
		line.endXProperty().bind(target.layoutXProperty().add(target.getBoundsInParent().getWidth()/2.0));
		line.endYProperty().bind(target.layoutYProperty().add(target.getBoundsInParent().getHeight()/2.0));
		
		/*line.startXProperty().bind(joint1.layoutXProperty().add(joint1.getBoundsInParent().getWidth()/2.0));
		line.startYProperty().bind(joint1.layoutYProperty().add(joint1.getBoundsInParent().getHeight()/2.0));
		
		//line1.startXProperty().bind(observable);
		
		line.startXProperty().bind(source.layoutXProperty().add(source.getBoundsInParent().getWidth()/2.0));
		line.startYProperty().bind(source.layoutYProperty().add(source.getBoundsInParent().getHeight()/2.0));
		
		line.endXProperty().bind(target.layoutXProperty().add(target.getBoundsInParent().getWidth()/2.0));
		line.endYProperty().bind(target.layoutYProperty().add(target.getBoundsInParent().getHeight()/2.0));*/
		
		
		getChildren().add(line);
		//getChildren().add(line2);
	}
	
	public Cell getSource() {
		return source;
	}
	
	public Cell getTarget() {
		return target;
	}
}
