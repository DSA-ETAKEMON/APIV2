package logica;

import Entity.User;
import Exception.FormatException;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by hixam on 26/12/16.
 */
@Path("user")

public class Login {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User login(String miUsr) {
        if(miUsr == null)
            throw new BadRequestException("all parameters are mandatory");

        User us = new User();
        Gson gson = new Gson();
        us = gson.fromJson(miUsr, User.class);
        boolean isOK=false;
        String pass ="";
        User mius = new User();
        String res ="";
        try {
            mius = us.getUserByNick(us.getNick());
            isOK =true;
        }catch (Exception e)
            {
            System.out.print(e.toString());
            // e.toString();
            }
        if(mius!=null && isOK && us.getPassword() != null) {
            pass = mius.getPassword();
            if (us.getPassword().equals(pass))
                System.out.print("login correcto");
            else
                System.out.print("Contraseña incorrecta");
        }
        return  mius;
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User register(String miUsr) {
        if(miUsr == null)
            throw new BadRequestException("all parameters are mandatory");

        User us = new User();
        Gson gson = new Gson();
        us = gson.fromJson(miUsr, User.class);
        boolean UserExists=false,insertOK=false;
        String res ="";

        try {
            User usr = new User();
            usr = us.getUserByNick(us.getNick());
            if(usr.getNick()!=null)
            UserExists =true;
        }catch (Exception e)
        {
            System.out.print(e.toString());
            return null;
        }

        if(!UserExists) {
            try {
                us.setTotalEtakemons(0);
                us.setPuntuacionTotal(0);
            } catch (NullPointerException e) {
                res = "los campos no pueden ser nulos";
                return null;
            }

            try {
                if(us.getNick().length()!=0 && us.getName().length()!=0 && us.getEmail().length()!=0
                        && us.getSurname().length()!=0 && us.getPassword().length()!=0) {
                    us.insert();
                    res = "Usuario registrado";
                    insertOK = true;
                }else
                {
                return null;}
            } catch (Exception e) {
                System.out.print(e.toString());
                return null;
            }
        }
        else
        {
            System.out.print("el nick ya existe");
            return null;
        }
        return  us;
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(String miUsr) {
        if(miUsr == null)
            throw new BadRequestException("all parameters are mandatory");

        User us = new User();
        User usrToUpdate = new User();
        Gson gson = new Gson();
        us = gson.fromJson(miUsr, User.class);
        boolean UserExists=false,insertOK=false;

        String res ="";

        try {
            usrToUpdate = us.getUserByNick(us.getNick());
            if(usrToUpdate.getNick()!=null)
                UserExists =true;
        }catch (Exception e)
        {
            System.out.print(e.toString());
            return null;
        }

        if(UserExists) {
            try {
                try {
                    if(us.getName()!=null)
                    usrToUpdate.setName(us.getName());
                    if(us.getEmail()!=null)
                        usrToUpdate.setEmail(us.getEmail());
                    if(us.getPassword()!=null)
                        usrToUpdate.setPassword(us.getPassword());
                    if(us.getSurname()!=null)
                        usrToUpdate.setSurname(us.getSurname());
                } catch (FormatException e) {
                    System.out.print(e.toString());
                    return null;
                }

            } catch (NullPointerException e) {    // TODO parece que sobra, repasar.
                res = "los campos no pueden ser nulos";
            }

            try {
                us.update("nick",us.getNick());
                System.out.print("Usuario actualizado correctamente.");
            } catch (Exception e) {
                System.out.print("Error al actualilzar usuario : "+e.toString());
                return null;
            }
        }
        else
        {
            System.out.println("El usuario que intenta actualizar no existe");
        }
        return  usrToUpdate;
    }


}
