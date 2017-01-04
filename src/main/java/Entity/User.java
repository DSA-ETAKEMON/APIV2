package Entity;

import Exception.FormatException;

/**
 * Created by hicham.az on 09/12/2016.
 */
public class User extends DAO.DAO {


    int totalEtakemons, puntuacionTotal;
    String name;
    String surname;
    String nick;
    String email;
    String password;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws FormatException {
        if(surname!=null)
        this.surname = surname;
        else
            throw new FormatException("El apellido no debe ser nulo");
    }

    public int getTotalEtakemons() {
        return totalEtakemons;
    }

    public void setTotalEtakemons(int totalEtakemons) {
        this.totalEtakemons = totalEtakemons;
    }

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(int puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public User() {
    }
/*
    public User(String name,String apellidos, String nick, String email, String password,int totalPokemons, int punctuacionTotal) {
        this.totalEtakemons = totalPokemons;
        this.puntuacionTotal = punctuacionTotal;
        this.name = name;
        this.surname = apellidos;
        nick = nick;
        email = email;
        password = password;
    }
*/
    public String getName() {
        return name;
    }


    public void setName(String name) throws FormatException{
        if(name!=null)
        this.name = name;
        else
            throw new FormatException("El nombre no debe ser nulo");
    }



    public String getNick() {
        return nick;
    }

    public void setNick(String nick) throws FormatException {
        if(nick!=null)
        this.nick = nick;
        else
            throw new FormatException("El nick no debe ser nulo");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws FormatException {
        if(email!=null)
        this.email = email;
        else throw new FormatException("El email no debe ser nulo");
    }

   // public int getId(){return this.id;}
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws FormatException {
        if(password!=null)
        this.password = password;
        else throw new FormatException("la contraseña no debe ser nulo");
    }



    public User getUserByNick(String nick)
    {

        User usr = new User();
        usr = usr.selectByNick(nick);
        return usr;
    }
}
