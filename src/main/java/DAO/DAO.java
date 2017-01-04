package DAO;

import Entity.Etakemons;
import Entity.User;
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

    public void select(int primaryKey) {
        String query = getSelectQuery();
        System.out.println(query);
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            int position = 1;
            addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                setFieldsFromResultSet(resultSet, resultSetMetaData, this);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public Etakemons selectUnEtakemon(int primaryKey) {
        String query = getSelectQuery();
        System.out.println(query);
        Etakemons etk = new Etakemons();
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            int position = 1;
            addPrimaryKeyParameter(preparedStatement, position, primaryKey);
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



    public List<Etakemons> selectEtakemonByidUser(String nick) {
        String query = getSelectQueryByIDUser();
        System.out.println(query);
        Etakemons etk = new Etakemons();
        List<Etakemons> miLista = new ArrayList<Etakemons>();
        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            int position = 1;
            // addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            while (rs.next()) {
                    etk.setTipo(rs.getString("tipo"));
                    etk.setName(rs.getString("name"));
                    etk.setPuntos(rs.getInt("puntos"));
                    miLista.add(etk);

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
