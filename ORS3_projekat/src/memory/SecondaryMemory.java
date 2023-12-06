package memory;

import java.util.ArrayList;

public class SecondaryMemory {

	private final int SIZE=2048;
	private static Block[] blocks;
	private int numberOfBlocks;
	private static ArrayList<FileInMemory> listOfFiles=new ArrayList<>();
	
	public SecondaryMemory() {
		numberOfBlocks=SIZE / Block.getSize();
		blocks=new Block[numberOfBlocks];
		for(int i=0;i<numberOfBlocks;i++) {
			Block block=new Block(i);
			blocks[i]=block;
		}
	}
	public void save(FileInMemory file) {
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
	public void delete(FileInMemory file) {
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
			int index=listOfFiles.indexOf(file);
			listOfFiles.remove(index);
		}
	}
	public String read(FileInMemory file) {
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
	public static void print() {
		String spacing="**********************************************************************************";
		System.out.println(spacing);
		System.out.println("Name\t\t\t\t\tStart\t\t\t\t\tLength");
		System.out.println(spacing);
		for(FileInMemory file: listOfFiles) {
			String print = "";
			if (file.getName().length() <8)
				print += file.getName() + "\t\t\t\t\t";
			else if (file.getName().length() <12)
				print += file.getName() + "\t\t\t";
			else if (file.getName().length() < 16)
				print += file.getName() + "\t\t\t";
			else
				print += file.getName() + "\t\t";
			print += file.getStart().block.getAddress() + "\t\t\t\t";
			print += file.getLength();
			System.out.println(print);
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
	public boolean isContaining(String name) {
		for(FileInMemory f: listOfFiles) 
			if(f.getName().equals(name))
				return true;
			return false;
	}
	public FileInMemory getFile(String name) {
		for(FileInMemory f : listOfFiles)
			if(f.getName().equals(name))
				return f;
		return null;
	}
}
