package genericCheckpointing.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {
	private String fileName;
	private FileReader fr;
	private FileWriter fw;
	private BufferedReader br;
	private BufferedWriter bw;
	private File file;
	
	/**
	 * Take fileName as argument
	 * @param fileNameIn
	 */
	public FileProcessor(String fileNameIn) {
		fileName = fileNameIn;
		file = new File(fileNameIn);
	}

	public void createWriter() {

		try {
			fw = new FileWriter(file);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		bw = new BufferedWriter(fw);
	}
	
	public void createReader(){
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.err.println(fileName + " File Not found");
			e.printStackTrace();
			System.exit(0);
		}
		br = new BufferedReader(fr);
	}
	
	public void storeLine(String line){
		try {
			bw.write(line);
		} catch (IOException e) {
			System.err.println(line + " can not be stored in the file");
			e.printStackTrace();
		}
	}
	
	public String readLineFromFile() {
		try {
			//System.out.println(br.readLine());
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeWriter(){
		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.err.println("Problem in closing the writer connection");
			e.printStackTrace();
		}
		
	}
	
	public void closeReader(){
		try {
			br.close();
			fr.close();
		} catch (IOException e) {
			System.err.println("Problem in closing the reading connection");
			e.printStackTrace();
		}
	}
	
}
