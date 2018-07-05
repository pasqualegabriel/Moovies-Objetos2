package fileRider;

public class AmistadFileReader extends CSVFileReader<AmistadIdUsuarios> {
	
	/**
	* @param filePath the absolute path of the file to be read.
	*/
	public AmistadFileReader(String filePath) {
		super(filePath);
	}
	
	@Override
	protected AmistadIdUsuarios parseLine(String[] line) {
		
		return new AmistadIdUsuarios(Integer.parseInt(line[0]),Integer.parseInt(line[1]));
	}
	
	@Override
	protected String getSeparator() {
		return "\\|";
	}

}