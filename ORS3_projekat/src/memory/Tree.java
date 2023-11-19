package memory;
import kernel.Process;


public class Tree {
	
public Node root=new Node(1024);
private int counter=0;
private int c=0;
//private int count=0;
	public Tree(Node root) {
		this.root=root;}
	int s=0;
	boolean flag=false;
	Node n=null;
	public static int suitablePartition(int value) {
		int suitableSize=0;
		for(int i=0;i<10;i++) {
			if(Math.pow(2,i)< value && Math.pow(2,i+1)>=value){
				suitableSize=(int) Math.pow(2,i+1);
			}
		}
		return suitableSize;
	}
public boolean ifExistInsert(Node node,int id,int value) {
	if(node==null)
		return false;
	if(node.getSize()==suitablePartition(value) && node.getOccupation()==false && node.getExistence()==true) {
		node.setUsage(value);
		node.setOccupation(true);
		node.setID(id);
		return true;
	}
	boolean res1=ifExistInsert(node.getLeftNode(),id,value);
	if (res1) return true;
	boolean res2=ifExistInsert(node.getRightNode(),id,value);
	return res2;
}
public Node findNodeInTree(Node node,int value) {
	if(node!=null) {
		if(node.getSize()==suitablePartition(value) && node.getOccupation()==false && node.getExistence()==true)
			return node;
		else {
			Node fNode=findNodeInTree(node.getLeftNode(),value);
			if(fNode==null) {
				fNode=findNodeInTree(node.getRightNode(),value);
			}
			return fNode;
	}
}else {
	return null;
}
	
}
public Node findSmallestSizeNode(int value) {
	Node n1=null;
	int r=suitablePartition(value);
	while(r<=1024) {
	if(findNodeInTree(root,r)!=null) {
		n1=findNodeInTree(root,r);
		break;
	}
	r=r*2;
	}
	return n1;
}
public void insertNode(Process process) {
	int id=process.getPId();
	int value=process.getInstructions().size();
	if(ifExistInsert(root, id, value)){

	}
	else {
		Node nd=findSmallestSizeNode(value);
		int s=nd.getSize();
		//System.out.println(s);
		//System.out.println("Hello");
		while(s>suitablePartition(value)) {
			//System.out.println("HI");
			nd.setExistention(false);
			Node left=new Node(s/2);
			Node right=new Node(s/2);
			nd.setLeftNode(left);
			nd.setRightNode(right);
			nd=left;
			s=s/2;
		}
		nd.setOccupation(true);
		nd.setUsage(value);
		nd.setID(id);
	}
}
public Node findGivenNodeByName(Node node,Process process) {
	int id=process.getPId();
	if(node!=null) {
		if(node.getID()==id)
			return node;
		else {
			Node fNode=findGivenNodeByName(node.getLeftNode(),process);
			if(fNode==null) {
				fNode=findGivenNodeByName(node.getRightNode(),process);
			}
			return fNode;
	}
}else {
	return null;
}
	
}
public boolean split(Node node) {
	if(node!=null) {
		if(node.getLeftNode()!=null && node.getRightNode()!=null && node.getLeftNode().getLeftNode()==null && node.getLeftNode().getRightNode()==null
				&& node.getRightNode().getLeftNode()==null && node.getRightNode().getRightNode()==null
				&& node.getLeftNode().getOccupation()==false && node.getLeftNode().getID()==-1 && node.getLeftNode().getUsage()==0
				&& node.getRightNode().getOccupation()==false && node.getRightNode().getID()==-1 && node.getRightNode().getUsage()==0) {
			node.setLeftNode(null);
			node.setRightNode(null);
			node.setExistention(true);
			return true;
		}
		else {
			boolean fNode=split(node.getLeftNode());
			if(fNode==false) {
				fNode=split(node.getRightNode());
			}
			return fNode;
	}
}else {
	return false;
}
}
public int depth(Node node) {
	while(node.getLeftNode()!=null) {
		node=node.getLeftNode();
		counter++;
	}
	return counter;
}
public void restart() {
	counter=0;
	c=0;
}
public void deleteNode(Process process) {
	int id=process.getPId();
	Node n=findGivenNodeByName(root,process);
	n.setID(-1);
	n.setOccupation(false);
	n.setUsage(0);
	int br=depth(root);
	while(c<br) {
		System.out.println(split(root));
		c++;
	}
	restart();
}
public static void main(String[] args) {
	/*Node root=new Node(1024);
	Tree tree=new Tree(root);
	Node pr1=new Node(512);
	Node pr2=new Node(512);
	root.setLeftNode(pr1);
	root.setRightNode(pr2);*/
	/*Node pr3=new Node(256);
	Node pr4=new Node(256);
	Node pr5=new Node(256);
	Node pr6=new Node(256);
	
	pr1.setLeftNode(pr3);
	pr1.setRightNode(pr4);
	pr2.setLeftNode(pr5);
	pr2.setRightNode(pr6);
	int val=129;
	System.out.println(tree.searchTree(val));
	System.out.println(pr3.getUsage());
	System.out.println(pr4.getUsage());*/
	/*System.out.println(tree.searchTree2(127).getSize());
	tree.insertNode("name",127);
	System.out.println(pr1.getLeftNode().getSize());
	System.out.println(pr1.getRightNode().getSize());
	System.out.println(pr1.getLeftNode().getUsage());
	System.out.println(pr1.getRightNode().getUsage());
	System.out.println(pr1.getLeftNode().getLeftNode().getSize());
	System.out.println(pr1.getLeftNode().getRightNode().getSize());
	System.out.println(pr1.getLeftNode().getLeftNode().getUsage());
	System.out.println(pr1.getLeftNode().getRightNode().getUsage());
	System.out.println(tree.searchTree(root,"name1",126));
	System.out.println(pr1.getLeftNode().getRightNode().getUsage());
	System.out.println(pr1.getLeftNode().getLeftNode().getUsage());
	System.out.println("--------------");
	System.out.println(tree.searchTree(root,"name2",511));
	System.out.println(pr1.getUsage());
	System.out.println(pr2.getUsage());
	System.out.println(tree.searchTreeByName(root,"name2").getSize());*/
	/*Node root=new Node(0);
	Tree tree=new Tree(root);
	Node n1=new Node(1);
	Node n2=new Node(2);
	Node n3=new Node(3);
	Node n4=new Node(4);
	Node n5=new Node(5);
	Node n6=new Node(6);
	Node n7=new Node(7);
	Node n8=new Node(8);
	Node n9=new Node(9);
	root.setLeftNode(n1);
	root.getLeftNode().setLeftNode(n3);
	root.getLeftNode().getLeftNode().setLeftNode(n7);
	root.getLeftNode().setRightNode(n4);
	root.getLeftNode().getRightNode().setLeftNode(n8);
	root.getLeftNode().getRightNode().setRightNode(n9);
	root.setRightNode(n2);
	root.getRightNode().setLeftNode(n5);
	root.getRightNode().setRightNode(n6);
	System.out.println(tree.searchTree(root,10));*/
	
	
	/*Node root1=new Node(1024);
	Tree tree1=new Tree(root1);
	Node p1=new Node(512);
	Node p2=new Node(512);
	Node p3=new Node(256);
	Node p4=new Node(256);
	root1.setLeftNode(p1);
	root1.setRightNode(p2);
	p1.setLeftNode(p3);
	p1.setRightNode(p4);
	tree1.insertNode("prvi",130);
	System.out.println(root1.getUsage()+", "+p1.getUsage()+", "+p2.getUsage()+", "+p3.getUsage()+", "+p4.getUsage());
	tree1.deleteNode("prvi");*/
	//System.out.println(root1.getUsage()+", "+p1.getUsage()+", "+p2.getUsage()+", "+p3.getUsage()+", "+p4.getUsage());
    //tree1.split(root1);
	//System.out.println(root1.getSize()+", "+p1.getSize()+", "+p2.getSize()+", "+p3.getSize()+", "+p4.getSize());
//System.out.println(root1.getSize());
//System.out.println(root1.getLeftNode().getSize());
//System.out.println(root1.getRightNode().getSize());
//System.out.println(root1.getLeftNode().getLeftNode().getSize());
//System.out.println(root1.getLeftNode().getRightNode().getSize());
//tree1.split(root1);
//System.out.println(root1.getSize());
//System.out.println(root1.getLeftNode().getSize());
//System.out.println(root1.getRightNode().getSize());
//System.out.println(root1.getLeftNode().getLeftNode().getSize());
//System.out.println(root1.getLeftNode().getRightNode().getSize());
//Node p5=new Node(512);
//root1.setLeftNode(p5);
	Node root=new Node(1024);
	Tree tree=new Tree(root);
	Process process=new Process("stepen");
	tree.insertNode(process);
	//-------tree.insertNode(44, 127);
	System.out.println(root.getLeftNode().getLeftNode().getSize());
	System.out.println(root.getLeftNode().getRightNode().getSize());
	System.out.println(root.getLeftNode().getLeftNode().getLeftNode().getUsage());
	System.out.println(root.getRightNode().getSize());
	//-------tree.deleteNode(34);
	System.out.println(root.getSize());
	System.out.println(root.getLeftNode().getSize());	
	System.out.println(root.getRightNode().getUsage());
	//System.out.println(root.getLeftNode().getLeftNode().getSize());
	System.out.println(root.getRightNode().getRightNode().getSize());
	System.out.println(root.getRightNode().getRightNode().getUsage());
	System.out.println(root.getRightNode().getRightNode().getID());


}
}
