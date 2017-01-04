package logica;

import Entity.Etakemons;
import Entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@FormParam("nick") String nick, @FormParam("password") String password) {
        if(nick == null || password == null)
            throw new BadRequestException("all parameters are mandatory");
       User us = new User();
        boolean isOK=false;

        String res ="";
     try {
      us = us.getUserByNick(nick);
         isOK =true;
     }catch (Exception e)
     {
         System.out.print(e.toString());
     }
        if(us!=null && isOK)
           res="Login ok";
        else
            res ="login incorrecto";

        return  res;
    }

    @GET
    @Path("/list/{nick}")
    @Produces(MediaType.TEXT_PLAIN)
    public String listar(@PathParam("nick") String nick) {
        if(nick == null)
            throw new BadRequestException("all parameters are mandatory");
        Etakemons etk = new Etakemons();
        List<Etakemons> lst = new ArrayList<Etakemons>();
        try {
            lst = etk.getEtakemons(nick);
        }catch(NullPointerException e)
        {
            System.out.print(e.toString());
        }
        Gson gson = new Gson();
        String Json = "vacio";
        try {
            Json = gson.toJson(lst);
        }catch (Exception e)
        {
            Json =(e.toString());
        }
        return Json;
    }

    @GET
    @Path("/getpokemon/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getpokemon(@PathParam("id") int id) {

        Etakemons etk = new Etakemons();

        try {
            etk = etk.getEtakemon(id);
        }catch(NullPointerException e)
        {
            System.out.print(e.toString());
        }

        Gson gson = new Gson();
        String Json = "vacio";
        try {
            Json = gson.toJson(etk);
        }catch (Exception e)
        {
            System.out.print(e.toString());
        }
        return Json;
    }
}
