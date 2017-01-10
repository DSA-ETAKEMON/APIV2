package logica;

import Entity.Etakemons;
import Entity.UserEtakemons;
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
    @Path("/etakemonslist")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etakemons> list() {

        List<Etakemons> etkList = new ArrayList<Etakemons>();
        Gson gson = new Gson();
        Etakemons etk = new Etakemons();
        etkList = etk.selectAll(); ;//(gson.fromJson(etakemonsListjson, Etakemons.class));
            if(etkList.size()!=0) {
                System.out.println("Lista cargada  ------- Tamaño lista : " +etkList.size());
            }
            else
            {
                etkList= null;
                System.out.println("No hay etakemons que listar.");
            }
            return  etkList;
    }

    @GET
    @Path("/misestakemons/{iduser}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etakemons> MisEtakemonslist(@PathParam("iduser") int iduser) {
        UserEtakemons usrEtkemons = new UserEtakemons();
        Etakemons etk = new Etakemons();
        List<UserEtakemons> UsretkList = new ArrayList<UserEtakemons>();
        List<Etakemons> etkList = new ArrayList<Etakemons>();
        UsretkList = usrEtkemons.selectEtakemonByUser("iduser",iduser);
        if(UsretkList.size()!=0) {
            System.out.println("Lista cargada  ------- Tamaño lista : " +UsretkList.size());
            for(UserEtakemons data : UsretkList)
            {
                etkList.add(etk.selectEtakemon("id",data.getIdetakemon()));
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
        String res ="";
        try{
            usrEtkemons.setIduser(iduser);
            usrEtkemons.setIdetakemon(idetakemon);
            usrEtkemons.insert();
            res = " CAZADO !";
            System.out.println(res);
        }catch (Exception e){
            res = "error al setear datos a UserEtakemons, etakemon  no cazado : " +e.toString();
            System.out.println(res);
        }

        return  res;
    }



}
