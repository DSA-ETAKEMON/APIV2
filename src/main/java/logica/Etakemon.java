package logica;

import Entity.Etakemons;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hixam on 29/12/16.
 */
@Path("etakemon")

public class Etakemon {

    @POST
        @Path("/insertetakemon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Etakemons registerar(String etakemonjson) {
        if(etakemonjson.length()==0)
            throw new BadRequestException("Json recibido vacio");

        Etakemons etk = new Etakemons();
        Gson gson = new Gson();
        etk = gson.fromJson(etakemonjson, Etakemons.class);
        String res ="";

        if(etk.getName()!=null || etk.getTipo()!=null) {
            try {
                etk.insert();
                res = "Etakemon insertado";
            } catch (Exception e) {
                System.out.print(e.toString());
                res = "error al insertar etkemon ------- "+e.toString();
            }
        }
        else
        {
            System.out.print("Etakemon que intenta insertar esta vacio.");

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
        List<String> etkListJson = new ArrayList<String>();
        etkList = etk.selectAll(); ;//(gson.fromJson(etakemonsListjson, Etakemons.class));

        if(etkList.size()!=0) {
            try {
                for(Etakemons e : etkList)
                {
                    etkListJson.add(gson.toJson(e));
                    System.out.print("----- Añadiendo "+e.getName() + " a la lista.");
                }
            } catch (Exception e) {
                System.out.print("error al insertar etkemon ------- "+e.toString());
            }
        }
        else
        {
            System.out.print("No hay etakemons que listar.");

        }
        return  etkList;
    }
}
