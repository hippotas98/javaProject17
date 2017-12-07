package mygraph.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import analyst.ClassADT;
import analyst.InterfaceADT;
import mygraph.graph.Cell;


public class Model {
	
	Cell graphParent;
	
	List<Cell> allCells;
	List<Cell> addedCells;
	List<Cell> removedCells;
	
	List<Edge> allEdges;
	List<Edge> addedEdges;
	List<Edge> removedEdges;

	Map<String, Cell> cellMap;
	
	public void clear() {
		allCells = new ArrayList<>();
		addedCells = new ArrayList<>();
		removedCells = new ArrayList<>();
		
		allEdges = new ArrayList<>();
		addedEdges = new ArrayList<>();
		removedEdges = new ArrayList<>();
		
		cellMap = new HashMap<>(); 
		
	}
	
	public void clearAddedLists() {
		addedCells.clear();
		addedEdges.clear();
	}
	
	public Model() {
		graphParent = new Cell("ROOT");
		clear();
	}
	
	public List<Cell> getAddedCells() {
		return addedCells;
	}
	
	public List<Cell> getRemovedCells() {
		return removedCells;
	}
	
	public List<Cell> getAllCells() {
		return allCells;
	}
	
	public List<Edge> getAddedEdges() {
		return addedEdges;
	}
	
	public List<Edge> getRemovedEdges() {
		return removedEdges;
	}
	
	public List<Edge> getAllEdges() {
		return allEdges;
	}
	
	private void addCell(Cell cell) {
		addedCells.add(cell);
		cellMap.put(cell.getCellID(), cell);
	}
	
	public void addCell(String id, ClassADT clas, CellType type) {
		
		switch (type) {
		
		case RECTANGLE:
			int width = 0;
			int height = 0;
			int max = 0;
			int length = 0;
			List<String> content = clas.getInformation();
			for(String line : content) {
				if(line.length() > max) max = line.length();
				length++;
			}
			width = max*8;
			height = length*15;
			RectangleCell rectangleCell = new RectangleCell(id,clas,width,height);
			addCell(rectangleCell);
			break;		
		
			default:
				throw new UnsupportedOperationException("Unsupported type " + type);
		}
	}
	public void addCell(String id, InterfaceADT itf, CellType type) {
		
		switch (type) {
		
		case RECTANGLE:
			int width = 0;
			int height = 0;
			int max = 0;
			int length = 0;
			List<String> content = itf.getInformation();
			for(String line : content) {
				if(line.length() > max) max = line.length();
				length++;
			}
			width = max*8;
			height = length*15;
			RectangleCell rectangleCell = new RectangleCell(id,itf,width,height);
			addCell(rectangleCell);
			break;		
		
			default:
				throw new UnsupportedOperationException("Unsupported type " + type);
		}
	}
	
	
	public void addEdge(String sourceID, String targetID) {
		Cell sourceCell = cellMap.get(sourceID);
		Cell targetCell = cellMap.get(targetID);
		
		Edge edge = new Edge(sourceCell, targetCell);
		
		addedEdges.add(edge);
		
	}

	public void merge() {
		allCells.addAll(addedCells);		
		addedCells.clear();
		allEdges.addAll(addedEdges);	
		addedEdges.clear();
		removedEdges.clear();
	}
}
