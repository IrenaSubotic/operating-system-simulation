package memory;

public class FileForString {

	private String name;
	private int size;
	private byte[] content;
	private Pointer start;
	private int length;
	
	public FileForString(String name, byte[] content) {
		this.name=name;
		this.content=content;
		this.size=content.length;
	}
	
	public int getSize() {
		return size;
	}
	public void setStart(Pointer start) {
		this.start=start;
	}
	public Pointer getStart() {
		return start;
	}
	public byte[] getContent() {
		return content;
	}
	public byte[] certainPart(int number) {
		int counter=0;
		byte[] part=new byte[Block.getSize()];
		for(int i=number*Block.getSize();i<this.size;i++) {
			part[counter]=this.content[i];
			if(counter==Block.getSize()-1)
				break;
			counter++;
		}
		while(counter<Block.getSize()-1) {
			byte[] blank=" ".getBytes();
			part[counter]=blank[0];
			counter++;
		}
		return part;
	}

	public void setLength(int length) {
		// TODO Auto-generated method stub
		this.length=length;
	}
	public String getName() {
		return this.name;
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return this.length;
	}
	
}
