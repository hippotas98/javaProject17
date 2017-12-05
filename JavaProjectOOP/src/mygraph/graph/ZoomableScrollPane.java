package mygraph.graph;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Scale;

public class ZoomableScrollPane extends ScrollPane {
	
	Group zoomGroup;
	Scale scaleTransform;
	Node content;
	private double scaleValue = 1.0;
	private double delta = 0.1;
	
	public double getScaleValue() {
		return scaleValue;
	}
	
	public void zoomTo(double _scaleValue) {
		scaleValue = _scaleValue;
		
		scaleTransform.setX(scaleValue);
		scaleTransform.setY(scaleValue);
	}
	
	public void zoomActual() {
		scaleValue = 1;
		zoomTo(scaleValue);
	}
	
	public void zoomOut() {
		scaleValue -= delta;
		
		if (Double.compare(scaleValue, 0.1) < 0) 
			scaleValue = 0.1;
		zoomTo(scaleValue);
	}
	
	public void zoomIn() {
		scaleValue += delta;
		
		if (Double.compare(scaleValue, 10) > 0) 
			scaleValue = 10;
		
		zoomTo(scaleValue);
	}
	
	public void zoomToFit(boolean minimizeOnly) {
		double scaleX = getViewportBounds().getWidth()/getContent().getBoundsInLocal().getWidth();
		double scaleY = getViewportBounds().getHeight()/getContent().getBoundsInLocal().getHeight();
		
		scaleX *= scaleValue;
		scaleY *= scaleValue;
		
		double scale = Math.min(scaleX, scaleY);
		
		if (minimizeOnly) {
			if (Double.compare(scaleY,  1) > 0) 
				scale = 1;
		}
		
		zoomTo(scale);
	}
	
	public void zoomToActual() {
		zoomTo(1.0);
	}
	
	private class ZoomHandler implements EventHandler<ScrollEvent> {
		
		@Override
		public void handle(ScrollEvent scrollEvent) {
			if (scrollEvent.getDeltaY() < 0) 
				scaleValue -= delta;
			else scaleValue += delta;
			
			zoomTo(scaleValue);
			
			scrollEvent.consume();
		}
	}
	
	public ZoomableScrollPane(Node _content) {
		content = _content;
		Group contentGroup = new Group();
		zoomGroup = new Group();
		contentGroup.getChildren().add(zoomGroup);
		zoomGroup.getChildren().add(content);
		setContent(contentGroup);
		scaleTransform = new Scale(scaleValue, scaleValue, 0, 0);
		zoomGroup.getTransforms().add(scaleTransform);
		zoomGroup.setOnScroll(new ZoomHandler());
	}
	
}
