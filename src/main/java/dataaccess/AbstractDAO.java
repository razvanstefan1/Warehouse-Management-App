package dataaccess;
import model.AuxString;
import model.Client;
import model.Produs;
import connection.ConnectionFactory;
import javax.swing.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *      Aceasta clasa este folosita pentru efectuarea operatiilor pe baza de date.
 *      In atributul type se obtine subclasa care apeleaza metodele din aceasta clasa.
 *
 */
public class AbstractDAO<T> {
            protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

            private final Class<T> type;

            @SuppressWarnings("unchecked")
            public AbstractDAO(){
                this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            }

          private String createSelectQuery(String field){
              String sb = "SELECT " +
                      " * " +
                      " FROM " +
                      type.getSimpleName() +
                      " WHERE " + field + " =?";
                return sb;
          }

    public static String getID(Object obj){
                String ss;
                if(obj instanceof Produs){
                    Produs aux = (Produs) obj;
                   return String.valueOf(aux.getId());
                }else{  //daca e Client
                    Client auxx = (Client) obj;
                    return String.valueOf(auxx.getId());
                }
    }

    /**
     * Aceasta metoda obtine un String cu numele atributelor obiectului dat ca parametru.
     * @param object
     * @param comma acest parametru decide daca intre numele atributelor se pune spatiu sau virgula.
     * @return
     */
    public static String getAttributes(Object object, boolean comma) { //obtine un string cu numele atributelor
                String s = new String();
                int k=0;
        for (Field field : object.getClass().getDeclaredFields()) { //parcurge atributele
            field.setAccessible(true); // set modifier to public
                if(k!=0) {  //sa nu ia primul atribut, pt ca am dat auto increment
                    s += field.getName();
                    if(comma==false)
                    s += " ";
                    else s+=",";
                }
                k=1;
        }
        s= s.substring(0, s.length() - 1);
        return s;
    }
    //returneaza chestia de dupe VALUES gen: (atr1, atr2,blabla)

    /**
     * Aceasta metoda returneaza un String cu valorile atributelor obiectului dat ca parametru.
     * @param object
     * @return
     */
    public static String getAttributeValues(Object object) { //obtine un string cu valorile ce trebuie introduse (valorile lui object)
        String s = new String("(");
        int k=0;
        for (Field field : object.getClass().getDeclaredFields()) { //parcurge atributele
            field.setAccessible(true); // set modifier to public
            Object value;
            try{
                value = field.get(object);
                if(k!=0){
                    if(value instanceof String) {
                        s += "'";
                        s += value;
                        s += "',";
                    }else if(value instanceof Client || value instanceof Produs){   //daca e pt comanda, trb apelat getname
                        s+=getID(value);
                        s += ",";
                    }else {  //daca e int nu mai pun ' '
                        s+=value;
                        s+=",";
                    }
                }
            }catch(IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace(); }
            k=1;
        }
        s= s.substring(0, s.length() - 1);   //sterg ultima virgula sa nu fie (123,'Vasile',)
        s+=")";
        return s;
    }
    public static String getVal(Object object) { //obtine un string cu valorile obiectului separate cu spatiu
        String s = new String();
        for (Field field : object.getClass().getDeclaredFields()) { //parcurge atributele
            field.setAccessible(true); // set modifier to public
            Object value;
            try{
                value = field.get(object);
                    if(value instanceof String) {
                        s += value;
                    }else if(value instanceof Client || value instanceof Produs){   //daca e pt comanda, trb apelat getname
                        s+=getID(value);
                    }else {  //daca e int nu mai pun ' '
                        s+=value;
                    }
                    s+=" ";
            }catch(IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace(); }
        }
        s= s.substring(0, s.length() - 1);   //sterg ultimul spatiu
        return s;
    }


          public String createInsertQuery(Object object) {
              String sb = "INSERT INTO " + type.getSimpleName() + "( " + getAttributes(object,true) + ")"
                      + " VALUES" + getAttributeValues(object) + ";";
              return sb;
            }

          public String createUpdateQuery(String field, String newValue,int id){
              String sb = "UPDATE " + type.getSimpleName() + " SET " + field + "=" + "'" + newValue + "'" + " WHERE id=" + id + ";";
            return sb;
          }

          public String createDeleteQuery(int id){
              String sb = "DELETE FROM " + type.getSimpleName() + " WHERE id = " + id + ";";
              return sb;
          }

          public String createSelectQuery(){
              String sb = "SELECT * FROM " + type.getSimpleName() + ";";
              return sb;
          }

    /**
     * Se insereaza obiectul dat ca parametru in tabelul corespunzator din baza de date.
     * In cazul unui obiect de tip produs, se apeleaza o procedura din baza de date pentru a se actualiza stocul disponibil.
     * @param object
     */
    public void insert(Object object){
              Connection dbConnection = ConnectionFactory.getConnection();
              String query = createInsertQuery(object);
              if(object instanceof Produs) query = "CALL update_prod " + getAttributeValues(object);   // pt a actualiza stocul. daca se sterge nu se mai actualizeaza
              PreparedStatement insertStatement = null;
              try {
                  insertStatement = dbConnection.prepareStatement(query);
                  insertStatement.executeUpdate();
              } catch (SQLException e) {
                  LOGGER.log(Level.WARNING, "AbstractDAO:insert " + e.getMessage());
              } finally {
                  ConnectionFactory.close(insertStatement);
                  ConnectionFactory.close(dbConnection);
              }
          }

    /**
     * Se editeaza in baza de date coloana cu numele field a tuplei care are id-ul id. Se inlocuieste vechea valoare cu newValue
     * @param field campul care se va edita
     * @param newValue noua valoare
     * @param id id-ul obiectului care se va edita
     */
          public void edit(String field, String newValue, int id){
              Connection dbConnection = ConnectionFactory.getConnection();
              String query = createUpdateQuery(field, newValue, id);
              PreparedStatement updateStatement = null;
              try {
                  updateStatement = dbConnection.prepareStatement(query);
                  updateStatement.executeUpdate();
              } catch (SQLException e) {
                  LOGGER.log(Level.WARNING, "AbstractDAO:edit " + e.getMessage());
              } finally {
                  ConnectionFactory.close(updateStatement);
                  ConnectionFactory.close(dbConnection);
              }
          }

    /**
     * Se sterge din baza de date tupla care are id-ul dat ca parametru.
     * @param id
     */
          public void delete(int id){
              Connection dbConnection = ConnectionFactory.getConnection();
              String query = createDeleteQuery(id);
              PreparedStatement deleteStatement = null;
              try {
                  deleteStatement = dbConnection.prepareStatement(query);
                  deleteStatement.executeUpdate();
              } catch (SQLException e) {
                  LOGGER.log(Level.WARNING, "AbstractDAO:delete " + e.getMessage());
              } finally {
                  ConnectionFactory.close(deleteStatement);
                  ConnectionFactory.close(dbConnection);
              }
          }

    /**
     * Aceasta metoda creeaza un JTable cu valorile unui tabel din baza de date
     * @param obj Acest parametru este folosit pentru apelarea metodei getAttributes
     * @param tab Daca acest parametru este true, se populeaza matricea de String-uri continuta de parametrul au cu valorile din tabel.
     * @param au
     * @return
     */
    public JScrollPane view(Object obj, boolean tab, AuxString au){  //dau un obiect de tipul tabelului pe care vreau sa l vizualizez ( ca sa pot folosi getAttributes)
              String s = getAttributes(obj,false);
              String[] aux = s.split(" ");
              String[] columnNames = new String[aux.length + 1];
              columnNames[0] = "id";
              System.arraycopy(aux,0,columnNames,1,aux.length);
              int k=0;
              String[][] tabel;
              Connection connection = null;
              PreparedStatement statement = null;
              ResultSet resultSet = null;
              String query = createSelectQuery();
              try{
                  connection = ConnectionFactory.getConnection();
                  statement = connection.prepareStatement(query);
                  resultSet = statement.executeQuery();
                  ArrayList<T>  data = (ArrayList<T>) createObjects(resultSet);  //aici am o lista cu coloanele (ca obiecte)
                  tabel = new String[data.size()][columnNames.length +1];        // am pus +1 pt ca aveam index out of bounds
                  for(T i : data){  // pt fiecare obiect obtin string cu atr si il fac vector de string cu split si adaug in matrice
                     String auxs = getVal(i);
                     String[] auxss = auxs.split(" ");
                     for(int j=0;j<auxss.length;j++)
                         tabel[k][j]=auxss[j];
                      k++;
                  }
                  if(tab==true) au.setM(tabel);
                  JTable tabelJ = new JTable(tabel,columnNames);
                  JScrollPane p = new JScrollPane(tabelJ);
                  return p;
              } catch (SQLException e ){
                  LOGGER.log(Level.WARNING, type.getName() + "DAO:view" + e.getMessage());
              }finally{
                  ConnectionFactory.close(resultSet);
                  ConnectionFactory.close(statement);
                  ConnectionFactory.close(connection);
              }
                return null;
          }

    /**
     * Aceasta metoda primeste un resultSet, adica rezultatele unei interogari pe unul dintre tabelele bazei de date si
     * creaza o lista cu obiecte de tipul acelui tabel.
     * @param resultSet
     * @return
     */
          private List<T> createObjects(ResultSet resultSet){
                List<T> list = new ArrayList<T>();
                try{
                    while(resultSet.next()){
                        T instance =  type.newInstance();
                        for(Field field: type.getDeclaredFields()){
                            Object value = resultSet.getObject(field.getName());
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),type);
                            Method method = propertyDescriptor.getWriteMethod();
                            method.invoke(instance,value); ///////////////////////////
                        }
                     //   ReflectionExample.retrieveProperties(instance);
                        list.add(instance);
                    }
                    return list;
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
              return null;
          }

    /**
     * Se returneaza un obiect care contine valorile tuplei din baza de date cu id-ul dat ca parametru.
     * @param id
     * @return
     */
          public T findById(int id, String str){  //daca str e diferit de  spatiu, se face find by name
                Connection connection = null;
                PreparedStatement statement = null;
                ResultSet resultSet = null;
                String query = createSelectQuery("id");
                if(str!=" ") query = createSelectQuery("name");
                try{
                    connection = ConnectionFactory.getConnection();
                    statement = connection.prepareStatement(query);
                    if(str == " ")
                    statement.setInt(1,id);
                    else
                        statement.setString(1,str);
                    resultSet = statement.executeQuery();
                    return createObjects(resultSet).get(0);  // returnam primul obiect din result set-ul query ului
                } catch (SQLException e ){
                    LOGGER.log(Level.WARNING, type.getName() + "DAO:findById" + e.getMessage());
                }finally{
                    ConnectionFactory.close(resultSet);
                    ConnectionFactory.close(statement);
                    ConnectionFactory.close(connection);
                }
                return null;
          }



}
