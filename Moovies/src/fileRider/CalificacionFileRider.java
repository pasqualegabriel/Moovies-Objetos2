package fileRider;

public class CalificacionFileRider extends CSVFileReader<CalificacionIdPeliculaYUsuario> {
	
	/**
	* @param filePath the absolute path of the file to be read.
	*/
	public CalificacionFileRider(String filePath) {
		super(filePath);
	}
	
	@Override
	protected CalificacionIdPeliculaYUsuario parseLine(String[] line) {
		
		return new CalificacionIdPeliculaYUsuario(Integer.parseInt(line[0]),Integer.parseInt(line[2]),Integer.parseInt(line[3]));
	}
	
	@Override
	protected String getSeparator() {
		return "\\|";
	}

}