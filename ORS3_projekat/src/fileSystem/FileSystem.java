package fileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.control.TreeItem;
import shell.Shell;

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
	
	public TreeItem<File> getTreeItem() {
		treeItem=new TreeItem<>(currentFolder);
		createTree(treeItem);
		return treeItem;
		
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
					
					/************************/
					
				}
				
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		}
		else {
			for(TreeItem<File> file: Shell.tree.getTreeItem().getChildren()) {
				if(file.getValue().getName().equals(directory) && file.getValue().isDirectory())
					currentFolder=file.getValue();
			}
		}
		
	}
	
	public static void makeDirectory(String directory) {
		File folder=new File(currentFolder.getAbsolutePath() + "\\" + directory);
		if(!folder.exists()) {
			folder.mkdir();
	    }
	}
	
	public static void  deleteDirectory(String directory) {
		for(TreeItem<File> file: Shell.tree.getTreeItem().getChildren()) {
			if(file.getValue().getName().equals(directory) &&  file.getValue().isDirectory()) {
				file.getValue().delete();
			}
			
		}
	}
	
    public static void renameDirectory(String oldName, String newName) {
    	for(TreeItem<File> file: Shell.tree.getTreeItem().getChildren()) {
    		if(file.getValue().getName().equals(oldName) && file.getValue().isDirectory()) {
    			file.getValue().renameTo(new File(currentFolder.getAbsolutePath() +"\\" +newName ));
    			System.out.println("Directory is renamed");
    		}
    	}
    }
	
	public File getCurrentFolder() {
		return currentFolder;
	}
	
	

}
