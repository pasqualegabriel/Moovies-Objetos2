package fileRider;

import moovies.UsuarioRegular;

public class UsuarioFileReader extends CSVFileReader<UsuarioIdFileReader> {
	
	/**
	* @param filePath the absolute path of the file to be read.
	*/
	public UsuarioFileReader(String filePath) {
		super(filePath);
	}
	
	@Override
	protected UsuarioIdFileReader parseLine(String[] line) {
	
		UsuarioRegular usuarioRegular = new UsuarioRegular(
				           line[0]/* nombre de usuario */, line[5]/* contrasenia */, 
				           line[5]/* nombre */, line[6]/* apellido */, 
				           line[2]/* genero */, Integer.parseInt(line[1])/* edad */, 
				           line[3]/* ocupacion */, Integer.parseInt(line[4])/* codigo postal */);
		
		return new UsuarioIdFileReader(Integer.parseInt(line[0]),usuarioRegular);
	}
	
	@Override
	protected String getSeparator() {
		return "\\|";
	}

}