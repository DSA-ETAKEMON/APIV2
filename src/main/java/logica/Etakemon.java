package logica;

import Entity.Etakemons;
import Entity.EtakemonsDescription;
import Entity.User;
import Entity.UserEtakemons;
import Objects.EtakemonObject;
import Objects.EtakemonsDescriptionObject;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by hixam on 29/12/16.
 */
@Path("etakemon")

public class Etakemon {

    @POST
    @Path("/insertetakemon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Etakemons registrar(String etakemonjson) {
        if(etakemonjson.length()==0)
            throw new BadRequestException("Json recibido vacio");
        Etakemons etk = new Etakemons();
        Gson gson = new Gson();
        etk = gson.fromJson(etakemonjson, Etakemons.class);
        String res ="";

        if(etk.getName()!=null || etk.getTipo()!=null) {
            try {
                etk.insert();
                //res = "Etakemon insertado";
                System.out.println("Etakemon insertado");

            } catch (Exception e) {
                System.out.println("error al insertar etkemon ------- "+e.toString());
               // res = "error al insertar etkemon ------- "+e.toString();
            }
        }
        else
        {
            System.out.println("Etakemon que intenta insertar esta vacio.");

        }
        return  etk;
    }

    @GET
    @Path("/getdescription/{idetk}")
    @Produces(MediaType.APPLICATION_JSON)
    public EtakemonsDescriptionObject getDescription(@PathParam("idetk") int idetk) {
        if(idetk == 0)
            throw new BadRequestException("id etakemon recibido vacio");
        EtakemonsDescription etk = new EtakemonsDescription();
        Gson gson = new Gson();
        String res ="";

            try {
               etk = etk.selectByEtkDescId("idetakemon", ""+idetk);
                //res = "Etakemon insertado";
                System.out.println("Etakemon Description : " + etk.getPoder());

            } catch (Exception e) {
                System.out.println("error al recuperar description etkemon ------- "+e.toString());
                // res = "error al insertar etkemon ------- "+e.toString();
                etk = null;
            }


        return  new EtakemonsDescriptionObject(etk);
    }

    @GET
    @Path("/etakemonslist")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EtakemonObject> list() {

        List<Etakemons> etkList = new ArrayList<Etakemons>();
        List<EtakemonObject> miLista = new ArrayList<>();
        Gson gson = new Gson();
        Etakemons etk = new Etakemons();
        etkList = etk.selectAll(); ;//(gson.fromJson(etakemonsListjson, Etakemons.class));
            if(etkList.size()!=0) {
                System.out.println("Lista cargada  ------- Tamaño lista : " +etkList.size());
                for(Etakemons et: etkList)
                {
                    miLista.add(new EtakemonObject(et));
                }
            }
            else
            {
                miLista= null;
                System.out.println("No hay etakemons que listar.");
            }
            return  miLista;
    }

    @GET
    @Path("/misestakemons/{iduser}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EtakemonObject> MisEtakemonslist(@PathParam("iduser") int iduser) {
        UserEtakemons usrEtkemons = new UserEtakemons();
        Etakemons etk = new Etakemons();
        List<UserEtakemons> UsretkList = new ArrayList<UserEtakemons>();
        List<EtakemonObject> etkList = new ArrayList<EtakemonObject>();
        UsretkList = usrEtkemons.selectEtakemonByUser("iduser",iduser);
        if(UsretkList.size()!=0) {
            System.out.println("Lista cargada  ------- Tamaño lista : " +UsretkList.size());
            for(UserEtakemons data : UsretkList)
            {
                etkList.add(new EtakemonObject(etk.selectEtakemon("id",data.getIdetakemon())));
            }
        }
        else
        {
            etkList= null;
            System.out.println("No hay etakemons que listar.");
        }
        return  etkList;
    }

    @GET
    @Path("/cazar/{iduser}/{idetakemon}")
    public String cazar(@PathParam("iduser") int iduser,@PathParam("idetakemon") int idetakemon) {
        UserEtakemons usrEtkemons = new UserEtakemons();
        Etakemons etk = new Etakemons();
        User usr = new User();

        String res ="";
        try {

            if (idetakemon<= 25 || idetakemon<=0){
                res = " CAZADO !";
                etk = etk.selectEtakemon("id", idetakemon);
                usr = usr.selectBy("id", "" + iduser);
                usr.setPuntuacionTotal(usr.getPuntuacionTotal() + etk.getPuntos());
                usrEtkemons.setIduser(iduser);
                usrEtkemons.setIdetakemon(idetakemon);
                usrEtkemons.insert();
            usr.setTotalEtakemons(usr.getTotalEtakemons() + 1);
                usr.update("" + usr.getId());

            }
            else
            res= "etakemon no existe";

            System.out.println(res);
        }catch (Exception e){
            res = "error al setear datos a UserEtakemons, etakemon  no cazado : " +e.toString();
            System.out.println(res);
        }

        return  res;
    }



}
