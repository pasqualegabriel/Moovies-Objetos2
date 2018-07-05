package fileRider;

import java.util.ArrayList;
import java.util.List;

import generos.Genero;
import generos.GeneroGeneral;
import moovies.Pelicula;

public class PeliculaFileReader extends CSVFileReader<PeliculaIdFileReader> {
	/**
	* @param filePath the absolute path of the file to be read.
	*/
	public PeliculaFileReader(String filePath) {
		super(filePath);
	}
	@Override
	protected  PeliculaIdFileReader parseLine (String[] line) {

		List<Genero> lsGenero=new ArrayList<Genero>();	
		List<Genero> keys= new ArrayList<Genero>();
		keys.add(new GeneroGeneral("Unknow"));
		keys.add(new GeneroGeneral("Action"));		
		keys.add(new GeneroGeneral("Adventure"));
		keys.add(new GeneroGeneral("Animation"));
		keys.add(new GeneroGeneral("Childrens"));
		keys.add(new GeneroGeneral("Comedy"));
		keys.add(new GeneroGeneral("Crime"));
		keys.add(new GeneroGeneral("Documentary"));
		keys.add(new GeneroGeneral("Drama"));
		keys.add(new GeneroGeneral("Fantasy"));
		keys.add(new GeneroGeneral("Film-Noir"));
		keys.add(new GeneroGeneral("Horror"));
		keys.add(new GeneroGeneral("Musical"));
		keys.add(new GeneroGeneral("Mystery"));
		keys.add(new GeneroGeneral("Romance"));
		keys.add(new GeneroGeneral("Sci-Fi"));
		keys.add(new GeneroGeneral("Thriller"));
		keys.add(new GeneroGeneral("War"));
		keys.add(new GeneroGeneral("Western"));

		for (int i = 5; i < 23; i++) {
			if(line[i]=="1") {
				lsGenero.add(keys.get(i-5));
			}
		}
		
		Pelicula pelicula = new Pelicula(line[1],line[2],line[3],lsGenero);
		
		return new PeliculaIdFileReader(Integer.parseInt(line[0]), pelicula);
	}
	
	@Override
	protected String getSeparator() {
		return "\\|";
	}
	
}

