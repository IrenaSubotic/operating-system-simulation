package fileSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import asembler.Operations;
import javafx.scene.control.TreeItem;
import memory.FileInMemory;
import shell.Shell;
import kernel.Process;

public class FileSystem {
	
	private static File rootFolder;
	private static File currentFolder;
	private TreeItem<File> treeItem;
	
	public FileSystem(File path) {
		rootFolder=path;
		currentFolder=rootFolder;
		treeItem=new TreeItem<>(rootFolder);
		createTree(treeItem);
	}
	
	
	public static void createTree(TreeItem<File> rootItem) {
		try (DirectoryStream<Path> directoryStream=Files.newDirectoryStream(Paths.get(rootItem.getValue().getAbsolutePath()))){
			for(Path path : directoryStream) {
				TreeItem<File> newItem=new TreeItem<>(path.toFile());
				newItem.setExpanded(false);
				rootItem.getChildren().add(newItem);
				if(Files.isDirectory(path))
					createTree(newItem);
				else {
					//ucitava fajlove u sekundarnu memoriju
					byte [] content=Files.readAllBytes(newItem.getValue().toPath());
					  FileInMemory newFile=new FileInMemory(newItem.getValue().getName(), content);
					  if(!Shell.memory.isContaining(newItem.getValue().getName()))
					     Shell.memory.save(newFile);
								
				}			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public TreeItem<File> getTreeItem() {
		treeItem=new TreeItem<>(currentFolder);
		createTree(treeItem);
		return treeItem;
		
	}
	
	public static void listFiles() {
		System.out.println("Current folder "+ currentFolder.getName()+" contains:");
		System.out.println("Type\t\tName");
		for(TreeItem<File> file : Shell.tree.getTreeItem().getChildren()) {
			  if(file.getValue().isDirectory())
				  System.out.println("Folder\t\t"+file.getValue().getName());
			  else 
				  System.out.println("File\t\t"+ file.getValue().getName());
		}
	}
	
	public static void changeDirectory(String directory) {
		if(directory.equals("..") && !currentFolder.equals(rootFolder)) {
			currentFolder=currentFolder.getParentFile();
			System.out.println("Directory is changed.");
		}
		else {
			for(TreeItem<File> file: Shell.tree.getTreeItem().getChildren()) {
				if(file.getValue().getName().equals(directory) && file.getValue().isDirectory())
					currentFolder=file.getValue();
				    System.out.println("Directory is changed.");
			}
		}
		
	}
	
	
	public static void makeDirectory(String directory) {
		File folder=new File(currentFolder.getAbsolutePath() + "\\" + directory);
		if(!folder.exists()) {
			folder.mkdir();
			System.out.println("Directory is made.");
	    }
	}
	
	public static void  deleteDirectory(String directory) {
		for(TreeItem<File> file: Shell.tree.getTreeItem().getChildren()) {
			if(file.getValue().getName().equals(directory) &&  file.getValue().isDirectory()) {
				file.getValue().delete();
				System.out.println("Directory is deleted.");
			}
			
		}
	}
	
    public static void renameDirectory(String oldName, String newName) {
    	for(TreeItem<File> file: Shell.tree.getTreeItem().getChildren()) {
    		if(file.getValue().getName().equals(oldName) && file.getValue().isDirectory()) {
    			file.getValue().renameTo(new File(currentFolder.getAbsolutePath() +"\\" +newName ));
    			System.out.println("Directory is renamed.");
    		}
    	}
    }
    
     public static void createFile(Process p){ 
         String name = p.getName().substring(0, p.getName().indexOf('.')) + "_output";
         File newFile=new File(p.getPath().getParent() + "\\" + name + ".txt");
         try{
              newFile.createNewFile();
              FileWriter fw=new FileWriter(newFile);
              fw.write("Rezultat izvrsavanja: " + Operations.R4.value);
              fw.close();
         }catch(IOException e){
         System.out.println("Error while creating file!");
         }
     }
     
     
      public static void deleteFile(String name){
      for(TreeItem<File> file: Shell.tree.getTreeItem().getChildren()) {
    	  if(file.getValue().getName().equals(name) && !file.getValue().isDirectory()) 
    		  file.getValue().delete();
    		    if(Shell.memory.isContaining(name)){
    		        Shell.memory.delete(Shell.memory.getFile(name));
    		  
    	  }
      }
      }
     
	public File getCurrentFolder() {
		return currentFolder;
	}
	
	

}
