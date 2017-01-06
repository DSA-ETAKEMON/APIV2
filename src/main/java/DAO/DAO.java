package DAO;

import Entity.Etakemons;
import Entity.User;
import Entity.UserEtakemons;
import Exception.FormatException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by hixam on 21/12/16.
 */
public class DAO extends DAOConnection{
   public void insert() {
        String query = getInsertQuery();
        System.out.println(query);
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            addClassFieldsParameters(preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                setIntField(generatedKeys.getInt(1), "id", this);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public Etakemons select(String where,int someThing) {
        String query = getSelectQuery(where,someThing);
        System.out.println(query);
        Etakemons etk  = new Etakemons();
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            //int position = 1;
           // addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                etk.setId(rs.getInt("id"));
                etk.setName(rs.getString("name"));
                etk.setTipo(rs.getString("tipo"));
                etk.setPuntos(rs.getInt("puntos"));
                // setFieldsFromResultSet(resultSet, resultSetMetaData, this);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return etk;
    }

    public Etakemons selectUnEtakemon(String where,String someThing) {
        String query = getSelectQuery(where,someThing);
        System.out.println(query);
        Etakemons etk = new Etakemons();
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            int position = 1;
            //addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                etk.setTipo(resultSet.getString("tipo"));
                etk.setPuntos(resultSet.getInt("puntos"));
                // setFieldsFromResultSet(resultSet, resultSetMetaData, this);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return etk;
    }



    public List<UserEtakemons> selectEtakemonByUser(String where,int iduser) {
        String query = getSelectQueryByIDUser( where ,iduser);
        System.out.println(query);
        List<UserEtakemons> miLista = new ArrayList<UserEtakemons>();
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
           // int position = 1;
            // addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            while (rs.next()) {
                UserEtakemons userEtks = new UserEtakemons();
                userEtks.setId(rs.getInt("id"));
                userEtks.setIduser(rs.getInt("iduser"));
                userEtks.setIdetakemon(rs.getInt("idetakemon"));
                miLista.add(userEtks);
                // setFieldsFromResultSet(resultSet, resultSetMetaData, this);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return miLista;
    }


    public User selectByNick(String nick) {
        String query = getSelectQueryByNick();
        query += "'" +nick+"'";
        System.out.println(query);
        User user = new User();
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            int position = 1;
            int primaryKey  = getPrimaryKeyParameter();
          //  addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            while (rs.next()) {

                try {
                    user.setName(rs.getString("name"));
                    user.setNick(rs.getString("nick"));
                    user.setSurname(rs.getString("surname"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setPuntuacionTotal(rs.getInt("puntuacionTotal"));
                    user.setTotalEtakemons(rs.getInt("totalEtakemons"));
                } catch (FormatException e) {
                    System.out.print(e.toString());
                }
               // setFieldsFromResultSet(resultSet, resultSetMetaData, this);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return user;
    }

    public void update( String where, String update) {
        String query = getUpdateQuery(where,update);
        System.out.println(query);
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            addClassFieldsParameters(preparedStatement);
            int primaryKey = getPrimaryKeyParameter();
            int position = (this.getClass().getDeclaredFields().length + 1);
            addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void delete() {
        String query = getDeleteQuery();
        System.out.println(query);
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            int position = 1;
            int primaryKey = getPrimaryKeyParameter();
            addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

     public List selectAll() {
        List<Object> objects = new ArrayList<>();
        String query = getSelectAllQuery();
        System.out.println(query);
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Class classToLoad = this.getClass();
                Object newObject = classToLoad.newInstance();
                setFieldsFromResultSet(resultSet, resultSetMetaData, newObject);
                objects.add(newObject);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            System.out.println(e.toString());
        }
        return objects;
    }
}
