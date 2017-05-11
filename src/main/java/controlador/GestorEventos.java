package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Persona;
import modelo.PersonaDAO;

public class GestorEventos {

	
	public static void insertaUsuario(List<String> campos){
		PersonaDAO personaDAO = new PersonaDAO();
		List<String> rutas = new ArrayList<String>();
		
		Persona p = new Persona();
		p.setNombre(campos.get(0));
		p.setApellidos(campos.get(1));
		p.setEmail(campos.get(2));
		p.setPassword(campos.get(3));
		p.setFotos(rutas);
		p.setTelefono(campos.get(4));
		
		personaDAO.addPersona(p);
	}
	
	public static void modificoUsuario(List<String> valores, String mail){
		PersonaDAO personaDAO = new PersonaDAO();
		personaDAO.deletePersona(mail);
		insertaUsuario(valores);
	}
	
	public static boolean correctLogin(String login, String passwd) {
		PersonaDAO personaDAO = new PersonaDAO();
		try{
		return login.matches(personaDAO.getPersona(login).getEmail()) && passwd.matches(personaDAO.getPersona(login).getPassword());
		}catch (NullPointerException e){
			System.out.println("Lista vacia");
			return false;
		}
		
	}
	
}
