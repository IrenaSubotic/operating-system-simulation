package memory;
import kernel.Process;

public class Node {
private int size;
private Node left;
private Node right;
private boolean occupied;
private boolean exist;
private int usage;
private int id;
	public Node(int size) {
		this.size=size;
		left=null;
		right=null;
		occupied=false;
		exist=true;
		usage=0;
		id=-1;
	}
public int getSize() {
	return this.size;
}
public Node getLeftNode() {
	return this.left;
}
public Node getRightNode() {
	return this.right;
}
public void setLeftNode(Node left) {
	this.left=left;
}
public void setRightNode(Node right) {
this.right=right;
}
public void setUsage(int usage) {
	this.usage=usage;
}
public int getUsage() {
	return usage;
}
public void setOccupation(boolean occupied) {
	this.occupied=occupied;
}
public void setSize(int size) {
	// TODO Auto-generated method stub
	this.size=size;
}
public boolean getOccupation() {
	// TODO Auto-generated method stub
	return occupied;
}
public void setExistention(boolean exist) {
	// TODO Auto-generated method stub
	this.exist=exist;
}
public boolean getExistence() {
	// TODO Auto-generated method stub
	return this.exist;
}
public void setID(int id) {
	this.id=id;
}
public int getID() {
	return this.id;
}
}
