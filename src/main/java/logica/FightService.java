package logica;

import Entity.Etakemons;
import Entity.Fight;
import Entity.User;
import Entity.UserEtakemons;
import Objects.FightObject;
import Objects.UserObject;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            if(pelea.getEstado1().equals("TRUE") && !pelea.getEstado2().equals("IDLE")) {

                pelea.update("id", String.valueOf(pelea.getId()));//getUserByNick(us.getNick());
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
    @Path("/misretosenviadosidle")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<FightObject> retosEnviadosPendientes(String fight) {
        if(fight.length() == 0)
            throw new BadRequestException("Reto incorrecto, json recibido vacio.");
        Fight pelea = new Fight();
        Gson gson = new Gson();
        pelea = gson.fromJson(fight, Fight.class);
        List<FightObject> fl = new ArrayList<>();
        try {
            for (Fight f : pelea.selectFightOrderBy("contrincanteuno", pelea.getContrincanteuno(),"estado2","IDLE"))
            {
                fl.add(new FightObject(f));
            }
        }catch (Exception e)
        {
            System.out.print(e.toString());
            // e.toString();
            fl=null;
        }
        return fl;
    }

    @POST
    @Path("/misretosenviadosaccept")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<FightObject> retosEnviadosAceptados(String fight) {
        if(fight.length() == 0)
            throw new BadRequestException("Reto incorrecto, json recibido vacio.");
        Fight pelea = new Fight();
        Gson gson = new Gson();
        pelea = gson.fromJson(fight, Fight.class);
        List<FightObject> fl = new ArrayList<>();
        try {
            for (Fight f : pelea.selectFightOrderBy("contrincanteuno", pelea.getContrincanteuno(),"estado2","TRUE"))
            {
                fl.add(new FightObject(f));
            }
        }catch (Exception e)
        {
            System.out.print(e.toString());
            // e.toString();
            fl = null;
        }
        return fl;
    }

    @POST
    @Path("/misretosajugar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<FightObject> retosRecibidosAJugar(String fight) {
        if(fight.length() == 0)
            throw new BadRequestException("Reto incorrecto, json recibido vacio.");
        Fight pelea = new Fight();
        Gson gson = new Gson();
        pelea = gson.fromJson(fight, Fight.class);
        List<FightObject> fl = new ArrayList<>();
        try {
            for (Fight f : pelea.selectFightOrderBy("contrincantedos", pelea.getContrincantedos(),"estado2","TRUE"))
            {
                if(f.getJuego1()!=null )
                fl.add(new FightObject(f));
            }
        }catch (Exception e)
        {
            System.out.print(e.toString());
            // e.toString();
            fl=null;
        }
        return fl;
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
        if(pelea.getJuego1().length()!=0 || pelea.getJuego1().length()!=0) {
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
            if(!pelea.getJuego1().equals("NULL") || pelea.getJuego2().equals("NULL"))
                pelea.update(""+pelea.getId());
            System.out.println("Tu oponente aun no ha jugado **************-----------***************");
            return "Tu oponente aun no ha jugado";
        }
        User usr = new User();
        ganador = res1>res2 ? ""+ pelea.getContrincanteuno() : ""+ pelea.getContrincantedos();
        if(res1 == res2)
            ganador= "empate";
        pelea.setGanador(ganador);

        if(pelea.getGanador().length()!=0)
        {
            gestionJugada(pelea); // update de la partida y puntos user
        }
        else
        {
            System.out.println("ganador calculado incorrectamente es igual a '' ");
        }

        System.out.println("ganador de la partida es " + ganador);
      return ganador;
    }
    public Fight gestionJugada(Fight f)
    {
       // update de la pelea -- set ganador
        if(!f.getGanador().equals("empate") || !f.getGanador().equals(""))
        {
            User usGanador = new User();
            User usPerdedor = new User();
            f.update(""+(f.getId()));
            usGanador = usGanador.selectBy("id",f.getGanador());

            if(Integer.valueOf(f.getGanador()) != f.getContrincanteuno())
            {
                // perdio el jugador 1
                usPerdedor = usPerdedor.selectBy( "id" , String.valueOf(f.getContrincanteuno()) );
                f.setPuntoscontrincanteuno(usPerdedor.getPuntuacionTotal());
            }
            else
            {
                // perdio el jugador 2
                usPerdedor = usPerdedor.selectBy( "id" , String.valueOf(f.getContrincantedos()) );
                f.setPuntoscontrincantedos(usPerdedor.getPuntuacionTotal());
            }

            int p1 = getRamdomEtakemon(usGanador);
            int p2 = getRamdomEtakemon(usPerdedor);
            System.out.println("ganador Etakemon : "+p1+" perdedor etakemon :" + p2);

            int g1 = Gestion.Ganar(p1,usGanador.getPuntuacionTotal(),usPerdedor.getPuntuacionTotal());
            int g2 = Gestion.Perder(p2,usPerdedor.getPuntuacionTotal());
            System.out.println("ganador puntos : "+g1+" perdedor puntos :" + g2);

            f.update(""+f.getId());
            usGanador.setPuntuacionTotal(g1);
            usPerdedor.setPuntuacionTotal(g2);

            // us2 = us1.getId() == f.getGanador() ?
            // update puntos jugadores
            usGanador.update(""+usGanador.getId());
            usPerdedor.update(""+usPerdedor.getId()); //Integer.parseInt(f.getGanador())==(f.getContrincanteuno()) ? ""+f.getPuntoscontrincanteuno() : ""+f.getPuntoscontrincantedos());

        }
        return f;
    }
public int getRamdomEtakemon(User us)
            {
             List<UserEtakemons> miList = new ArrayList<>();
                UserEtakemons etk = new UserEtakemons();
                miList= etk.selectEtakemonByUser("iduser",us.getId());
                // falta crear caso id=0, cuando lista =0 , hacer que id = 0 devuelve los mismos puntos
                int[] num = new int[miList.size()];
                int i=0;
                for(UserEtakemons data: miList)
                {
                    num[i] = data.getIdetakemon();
                    i++;
                }
                int rnd = new Random().nextInt(num.length);

                return num[rnd];
            }
}
