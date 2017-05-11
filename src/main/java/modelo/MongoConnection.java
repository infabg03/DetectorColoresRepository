package modelo;

import com.mongodb.MongoClient;

public class MongoConnection {


	public static MongoClient getMongoClient() {
		return new MongoClient();
	}
	
	

}
