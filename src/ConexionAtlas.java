
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class ConexionAtlas extends MongoClient{
    
    public ConexionAtlas (MongoClientURI direccionConexion){
        super(direccionConexion);
    }
    
    /*
    MongoClientURI uri = new MongoClientURI(
        "mongodb+srv://MainFrame:WTCT@wintercontingency.hdczx.mongodb.net/ejemplo1?retryWrites=true&w=majority");

    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("test");*/

}
