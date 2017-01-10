package logica;

import Entity.Fight;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by hixam on 6/01/17.
 */
@Path("fight")
public class FightService {

    @POST
    @Path("/retar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Fight retar(String fight) {
        if(fight.length() == 0)
            throw new BadRequestException("Reto incorrecto, json recibido vacio.");
        Fight pelea = new Fight();
        Gson gson = new Gson();
        pelea = gson.fromJson(fight, Fight.class);
        boolean insertOK=false;
        try {
            pelea.insert();//getUserByNick(us.getNick());
            insertOK =true;
        }catch (Exception e)
        {
            System.out.print(e.toString());
            // e.toString();
        }
        return  pelea;
    }

    @POST
    @Path("/misretos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Fight consultarRetos(String fight) {
        if(fight.length() == 0)
            throw new BadRequestException("Reto incorrecto, json recibido vacio.");
        Fight pelea = new Fight();
        Gson gson = new Gson();
        pelea = gson.fromJson(fight, Fight.class);
        boolean insertOK=false;
        try {
            pelea = pelea.selectFight("contrincantedos", pelea.getContrincantedos());//getUserByNick(us.getNick());
            insertOK =true;
        }catch (Exception e)
        {
            System.out.print(e.toString());
            // e.toString();
        }
        return  pelea;
    }

}
