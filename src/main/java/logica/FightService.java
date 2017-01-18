package logica;

import Entity.Fight;
import Entity.User;
import Objects.FightObject;
import Objects.UserObject;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hixam on 6/01/17.
 */
@Path("fight")
public class FightService {

    // envia {'estado1':'TRUE','estado2':'IDLE','contrincanteuno':'1','contrincantedos':'2'}
    @POST
    @Path("/retar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FightObject retar(String fight) {
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
        return  new FightObject(pelea);
    }

    @POST
    @Path("/misretos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<FightObject> consultarRetos(String fight) {
        if(fight.length() == 0)
            throw new BadRequestException("Reto incorrecto, json recibido vacio.");
        Fight pelea = new Fight();
        Gson gson = new Gson();
        pelea = gson.fromJson(fight, Fight.class);
        List<FightObject> fl = new ArrayList<>();
        try {
            for (Fight f : pelea.selectFightOrderBy("contrincantedos", pelea.getContrincantedos(),"estado2","IDLE"))
            {
                fl.add(new FightObject(f));
            }
        }catch (Exception e)
        {
            System.out.print(e.toString());
            // e.toString();
        }
        return fl;
    }

    @POST
    @Path("/returnreto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FightObject ReturnReto(String fight) {
        if(fight.length() == 0)
            throw new BadRequestException("Reto incorrecto, json recibido vacio.");
        Fight pelea = new Fight();
        Gson gson = new Gson();
        pelea = gson.fromJson(fight, Fight.class);
        boolean insertOK=false;
        try {
            if(pelea.getEstado1().equals("TRUE") && pelea.getEstado2().equals("TRUE")) {
                pelea.update("estado2", pelea.getEstado2(),"id", pelea.getId());//getUserByNick(us.getNick());
                insertOK = true;
            } else
            System.out.println("Estado 1: " + pelea.getEstado1() + " ***** Estado 2: " + pelea.getEstado2());

        }catch (Exception e)
        {
            System.out.print(e.toString());
        }
        return new FightObject(pelea.selectFight("id", String.valueOf(pelea.getId())));
    }

    @POST
    @Path("/GetTopUsers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserObject> GetTopUsers() {
        List<User> miLista = new ArrayList<User>();
        List<UserObject> l = new ArrayList<UserObject>();
        User us = new User();
       // Gson gson = new Gson();
       // pelea = gson.fromJson(fight, Fight.class);
       // boolean insertOK=false;
        try {
                miLista =  us.selectAllOrderBy("puntuacionTotal");//("contrincantedos", pelea.getContrincantedos());//getUserByNick(us.getNick());

        }catch (Exception e)
        {
            System.out.print(e.toString());
        }
        if(miLista.size()!=0)
        {
            for(User f: miLista) {
                l.add(new UserObject(f));
            }
        }
        return  l;
    }
}
