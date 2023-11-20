package memory;
import java.util.ArrayList;

import kernel.Process;


public class Tree {
	
public static Node root=new Node(1024);
private static int res=-1;
private int c=0;
static ArrayList<Node> list=new ArrayList<>();
static ArrayList<Integer> occupationList=new ArrayList<>();
static ArrayList<Integer> nodesList =new ArrayList<>();
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
public static boolean ifExistInsert(Node node,int id,int value) {
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
public static Node findNodeInTree(Node node,int value) {
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
public static Node findSmallestSizeNode(int value) {
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
public static void insertNode(Process process) {
	int id=process.getPId();
	int value=process.getInstructions().size();
	//NEED CODE FOR:if not exist certain size of partition
	if(ifExistInsert(root, id, value)){

	}
	else {
		Node nd=findSmallestSizeNode(value);
		int s=nd.getSize();
		while(s>suitablePartition(value)) {
			nd.setExistention(false);
			Node left=new Node(s/2);
			Node right=new Node(s/2);
			nd.setLeftNode(left);
			nd.setRightNode(right);
			Node help=right;
			help.setExistention(true);
			nd=left;
			s=s/2;
		}
		nd.setOccupation(true);
		nd.setUsage(value);
		nd.setID(id);
		nd.setExistention(true);
	}
}
public static Node findGivenNodeByName(Node node,Process process) {
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
public int height(Node node) {
if(root==null)
	return 0;
else {
	int leftHeight=0;
	int rightHeight=0; 
	if(node.getLeftNode()!=null)
		leftHeight=height(node.getLeftNode());
	if(node.getRightNode()!=null)
		rightHeight=height(node.getRightNode());
	int max= (leftHeight>rightHeight) ? leftHeight : rightHeight;
	return (max+1);
}
}
public void deleteNode(Process process) {
	int id=process.getPId();
	Node n=findGivenNodeByName(root,process);
	n.setID(-1);
	n.setOccupation(false);
	n.setUsage(0);
	int br=height(root);
	while(c<br) {
		System.out.println(split(root));
		c++;
	}
}
public static void fillLists(Node node){
	if(node!=null) {
		if(node.getExistence()==true) {
		list.add(node);
		nodesList.add(node.getID());
		if(node.getOccupation()==true)
		occupationList.add(node.getUsage());
		else
			occupationList.add(0);
		}
		fillLists(node.getLeftNode());
		fillLists(node.getRightNode());	
	}
}
public static int position(Process process) {
	for(int i=0;i<nodesList.size();i++) {
		if(nodesList.get(i)==process.getPId()) {
			res=i;
			break;
		}
		}
	return res;
}
public static void loadInRAM(Process process) {
   int sum=0;
	int pos=position(process);
	System.out.println(pos);
	int size=list.get(pos).getSize();
	int usage=occupationList.get(pos);
	for(int i=0;i<pos;i++){
	   sum+=list.get(i).getSize();
	}
	System.out.println(sum);
	Partition partition=new Partition(process);
	RAM.set(sum,partition.getData());
	
}
}
