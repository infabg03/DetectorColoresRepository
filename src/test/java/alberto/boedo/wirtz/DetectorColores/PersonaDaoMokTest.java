package alberto.boedo.wirtz.DetectorColores;

import java.util.ArrayList;
import java.util.List;

import modelo.Persona;
import modelo.PersonaDAO;

public class PersonaDaoMokTest {
	
	public static void main(String[] args) {
		
		PersonaDAO personaDAO = new PersonaDAO();
		
		List<String> rutas = new ArrayList<String>();
		rutas.add("src/jjaj.png");
		rutas.add("src/jpa.png");
	
		Persona p = new Persona();
		p.setNombre("Alberto");
		p.setApellidos("Boedo Galan");
		p.setEmail("a@b.com");
		p.setPassword("ijsioa");
		p.setFotos(rutas);
		p.setTelefono("981124073");
		
		personaDAO.addPersona(p);
		
	}
	


}
