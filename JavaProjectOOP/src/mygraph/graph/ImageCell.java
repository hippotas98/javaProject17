package mygraph.graph;

import javafx.scene.image.ImageView;

public class ImageCell extends Cell {
	public ImageCell(String _id) {
		super(_id);
		ImageView view = new ImageView("https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/what-is-a-class-diagram-in-UML/UML_class_diagram_example2-750x539.PNG");
		view.setFitHeight(100);
		view.setFitWidth(100);
		setView(view);
	}
}
