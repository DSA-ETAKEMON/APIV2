package logica;

import Entity.Etakemons;
import Entity.Fight;
import Exception.FormatException;
import com.google.gson.Gson;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:9091/etakemon";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in logica package
        final ResourceConfig rc = new EtakemonResourceConfig();//ResourceConfig().packages("logica");//EtakemonResourceConfig();

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, FormatException {
      /*  double[] a = new double[2];
        a[0] = 100;
        a[1] = 200;
        double ab = 3600;
        double dis = Gestion.MediaKm(a,ab);*/

     /*  Etakemons usr = new Etakemons();
        usr.setName("hicham");
        usr.setTipo("azouagh");
        usr.setPuntos(100);


        usr.insert();*/

     /*   String res="";
        Login lg = new Login();
        try {
            usr = lg.login(usr);
            res = "ok";
        }catch (NullPointerException e)
        {
        System.out.println(e.toString());
         }
        System.out.print("usuarrio name es :" + usr.getEmail() );
*/
        /*Etakemon et = new Etakemon();
        Etakemons e = new Etakemons();
        List<String> list = new ArrayList<String>();
        list = et.list();*/

        FightService fs = new FightService();
        Fight f = new Fight();
        Gson gson = new Gson();
        f.setContrincanteuno(1);
        f.setContrincantedos(2);
        f.setEstado1("TRUE");
        f.setEstado2("TRUE");
        f.setJuego1("111011100");
        f.setJuego2("111010010");
        String jsonInString = gson.toJson(f);
        double a = 132.1;
        int b = (int)a;
        fs.play(jsonInString);

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        if(server!=null)
        server.shutdown();
        else
            System.out.println("el servidor esta ya parado !!");
    }
}

