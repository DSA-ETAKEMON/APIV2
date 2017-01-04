package logica;

import Entity.Etakemons;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
}
