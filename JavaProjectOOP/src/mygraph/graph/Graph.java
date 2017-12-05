package mygraph.graph;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import mygraph.graph.layout.CellLayer;

public class Graph {
	
	private Model model;
	private Group canvas;
	private ZoomableScrollPane scrollPane;
	MouseGestures mouseGestures;
	
	CellLayer cellLayer;
	
	public Graph() {
		model = new Model();
		canvas = new Group();
		cellLayer = new CellLayer();
		
		canvas.getChildren().add(cellLayer);
		mouseGestures = new MouseGestures(this);
		scrollPane = new ZoomableScrollPane(canvas);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
	}
	
	public ScrollPane getScrollPane() {
		return scrollPane;
	}
	
	public Pane getCellLayer() {
		return cellLayer;
	}
	
	public Model getModel() {
		return model;
	}
	
	public double getScale() {
		return scrollPane.getScaleValue();
	}
	
	public void beginUpdate() {}
	
	public void endUpdate() {
		getCellLayer().getChildren().addAll(model.getAddedEdges());
		getCellLayer().getChildren().addAll(model.getAddedCells());

		for (Cell cell : model.getAddedCells()) 
			mouseGestures.makeDraggable(cell);
		
		
		getModel().merge();
	}
	
	
}
