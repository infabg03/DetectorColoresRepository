package modelo;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

public class PersonaDAO {

	MongoClient conexion = MongoConnection.getMongoClient();
	Morphia morphia = new Morphia();
	Datastore ds;

	public void mapeaDataStore() {
		morphia.map(Persona.class);
		ds = morphia.createDatastore(conexion, morphia.getMapper(), "Usuarios");
	}

	public void addPersona(Persona p) {
		mapeaDataStore();
		ds.save(p);
	}

	public Persona getPersona(String mail) {
		mapeaDataStore();
		Query<Persona> query = ds.createQuery(Persona.class);
		query.field("_id").equal(mail);
		List<Persona> miLista = query.asList();
		if (miLista.size() > 0)
			return miLista.get(0);
		else
			return null;
	}

	public void deletePersona(String mail) {

		mapeaDataStore();
		Query<Persona> query = ds.createQuery(Persona.class);
		query.field("_id").equal(mail);

		ds.findAndDelete(query);

	}
}
