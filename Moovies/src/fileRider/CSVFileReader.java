package fileRider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
* It reads a CSV File and returns an object from each line read. It is up to the subclasses to
* define how that object gets created.
*/
public abstract class CSVFileReader<T> {
	
	private String filePath;
	private BufferedReader bufferedReader;
	/**
	 * @param filePath the absolute path of the file to be read.
	 * */
	public CSVFileReader(String filePath){
		this.filePath = filePath;
	}
	/**
	 * Reads a CSV file and returns a list of T
	 * */
	public List<T> readFile(){
		Stream<String> lines = this.readLines();
		return lines.map(line->this.parseLine(this.splitLine(line))).collect(Collectors.toList());
	}
	/**
	 * Reads the lines from the file and returns them as a Stream.
	 * If the file doesn't exist it returns an empty Stream.
	 * */
	private Stream<String> readLines(){
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(this.filePath);
		} 
		catch (FileNotFoundException e) {
			System.out.println(this.filePath + " NO EXISTE. \nFlashaste.");
			e.printStackTrace();
			return new ArrayList<String>().stream();
		}
		bufferedReader = new BufferedReader(fileReader);
		return bufferedReader.lines();
	}
	/**
	 * A valid regex string (remember escaping especial characters in regex)
	 * */
	protected String getSeparator(){
		return "//|";
	}
	/**
	 * Applies the separator to a given line
	 * */
	private String[] splitLine(String line){
		return line.split(this.getSeparator());
	}
	/**
	 * Returns an Object of type T. Must be redefined by subclasses.
	 * */
	protected abstract T parseLine(String[] line);
}

