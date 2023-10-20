package memory;

import java.util.ArrayList;

public class SecondaryMemory {

	private final int SIZE=2048;
	private static Block[] blocks;
	private int numberOfBlocks;
	private ArrayList<File> listOfFiles=new ArrayList<>();
	
	public SecondaryMemory() {
		numberOfBlocks=SIZE / Block.getSize();
		blocks=new Block[numberOfBlocks];
		for(int i=0;i<numberOfBlocks;i++) {
			Block block=new Block(i);
			blocks[i]=block;
		}
	}
	public void save(File file) {
		int numberOfFileBlocks;
		Pointer left=null;
		int reminder=file.getSize() % Block.getSize();
		if(reminder == 0)
			numberOfFileBlocks=file.getSize() / Block.getSize();
		else
			numberOfFileBlocks=file.getSize() / Block.getSize() +1;
			//System.out.println(numberOfFileBlocks);
		if(numberOfFileBlocks>numberOfFreeBlocks()) {
			System.out.println(numberOfFreeBlocks());
			System.out.println("NOT ENOUGH SPACE FOR CREATING FILE!");
		}
		else {
			int counter=0;
			for(int i=0;i<numberOfBlocks;i++) {
				
				if(!blocks[i].isOccupied()) {
					blocks[i].setOccupied(true);
					blocks[i].setContent(file.certainPart(counter));
					if(counter==0) {
						left=new Pointer(blocks[i]);
						file.setStart(left);
						counter++;
					}
					else {
						Pointer right=new Pointer(blocks[i]);
						left.next=right;
						left=right;
						counter++;
					}
					if(counter==numberOfFileBlocks) {
						file.setLength(counter);
						listOfFiles.add(file);
						break;
					}
				}
			}
		}
			
	}
	public void delete(File file) {
		if(!listOfFiles.contains(file))
			System.out.println("THIS FILE ISN'T IN SECONDARY MEMORY!");
		else {
			Pointer p=file.getStart();
			file.setStart(null);
			p.block.setOccupied(false);
			p.block.setContent(null);
			while(p.next!=null) {
				Pointer temp=p;
				p=p.next;
				temp.next=null;
				p.block.setOccupied(false);
			}
		}
	}
	public String read(File file) {
		String result="";
		Pointer p=file.getStart();
		byte[] content=p.block.getContent();
		for(byte b:content)
			result += (char) b;
		while(p.next!=null) {
			p=p.next;
			content=p.block.getContent();
			for(byte b: content)
				result += (char) b;
		}
		return result;
	}
	public void print() {
		String spacing="**********************************************************************************";
		System.out.println(spacing);
		System.out.println("Name\t\tStart\t\tLength");
		System.out.println(spacing);
		for(File file: listOfFiles) {
			System.out.println(file.getName()+"\t\t"+file.getStart().block.getAddress()+"\t\t"+file.getLength());
		}
	}
	public int numberOfFreeBlocks() {
		int counter=0;
		for(int i=0;i<numberOfBlocks;i++) {
			if(!blocks[i].isOccupied())
				counter++;
		}
		return counter;
	}
	public static void main(String[] args) {
		SecondaryMemory sm=new SecondaryMemory();
		byte[] content1= {80,65,78,75,9,9,9,9,9,9,9,9,9,9};
		File fim1=new File("first",content1);
		sm.save(fim1);
		byte[] content2= {81,65,78,25,5,7,73,2,22,33,9,9,9,9,9,9};
		File fim2=new File("second",content2);
		sm.save(fim2);
		byte[] content3= {81,65};
		File fim3=new File("third",content3);
		sm.save(fim3);
		byte[] content4= {81,65,20};
		File fim4=new File("fourth",content4);
		sm.save(fim4);
		sm.print();
		System.out.println(sm.read(fim3));
	}
}
