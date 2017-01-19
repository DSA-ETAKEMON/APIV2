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
                pelea.update("estado2", pelea.getEstado2(),"id", String.valueOf(pelea.getId()));//getUserByNick(us.getNick());
                insertOK = true;
            } else
            System.out.println("Estado 1: " + pelea.getEstado1() + " ***** Estado 2: " + pelea.getEstado2());

        }catch (Exception e)
        {
            System.out.print(e.toString());
        }
        return new FightObject(pelea.selectFight("id", String.valueOf(pelea.getId())));
    }

    @GET
    @Path("/GetTopUsers")
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

    @POST
    @Path("/jugar")
   // @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String play(String fight) {
        int res1=0, res2=0;
        String ganador="";
        Fight pelea = new Fight();
         Gson gson = new Gson();
         pelea = gson.fromJson(fight, Fight.class);
        if(pelea.getEstado1().equals("TRUE") && pelea.getEstado2().equals("TRUE")) {
            try {
                  String jugada1= pelea.getJuego1();
                  String jugada2= pelea.getJuego2();
                int i = 0;
                for (char ch: jugada1.toCharArray()) {
                    char j1 = jugada1.charAt(i);
                    char j2 = jugada2.charAt(i);
                    if(j1>j2)
                    {
                       res1++;
                    }
                    if(j1==j2)
                    {
                        if(j1 != '0' )
                        {
                            res2++;
                        }
                    }
                    i++;
                }
            } catch (Exception e) {
                System.out.print(e.toString());
            }
        }
        else
        // no se acepto el reto
       // if(miLista.size()!=0)
        {
        }
        User usr = new User();
        ganador = res1>res2 ? "Ganador es: "+ pelea.getContrincanteuno() : "Ganador es: "+ pelea.getContrincantedos();
        if(pelea.getContrincanteuno() == pelea.getContrincantedos())
            ganador= "empate";
        pelea.setGanador(ganador);
        if(pelea.getGanador().length()!=0)
        {
            gestionJugada(pelea); // update de la partida y puntos user
        }
        System.out.println("ganador de la partida es " + ganador);
      return ganador;
    }
    public Fight gestionJugada(Fight f)
    {
       // update de la pelea -- set ganador
        if(!f.getGanador().equals("empate") || !f.getGanador().equals(""))
        {
            User us = new User();
            f.update("ganador",f.getGanador(),"juego2",f.getJuego2());
            us = us.selectBy("id",f.getGanador());
            // update puntos jugadores
            us.update("puntuacionTotal",Integer.parseInt(f.getGanador())==(f.getContrincanteuno()) ? ""+f.getPuntoscontrincanteuno() : ""+f.getPuntoscontrincantedos());
        }
        return f;
    }

}
