package sample.admin;

import sample.TypUzytkownika;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminConnection implements AdminConnectionInterface {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL;
    String USER;
    String PASS;
    Connection conn = null;
    //Wez dane z usera i wrzuc je do tabeli, pamietaj, aby zahashowac haslo, algorytm MD5
    //https://www.mkyong.com/java/java-md5-hashing-example/

    public AdminConnection() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            //STEP 3: Open a connection
            connectToDatabase("","","");// skąd mam wziąć usera, passworda i to trzecie do nawiązania połączenia? :/
    }
    /**
     * Olga, ja też nie wiem :( :( :(
     *
     * */

        boolean connectToDatabase (String DB_URL, String USER, String PASS){
            this.DB_URL=DB_URL;
            this.USER=USER;
            this.PASS=PASS;
            Statement stmt = null;
            try {
                System.out.println("Connecting to a selected database...");
                this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected database successfully...");
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        // ej nie wiem, czy wszystko zrobiłam dobrze xD
        //obsluga pracownikow
        //dawaj mi wszystkich
        //dodaj do bazy danych
        //sprawdz po peselu i zmien zmiany
        //wez pesel i skasuj odpowiedni rekord
        //informacje o finansach na rozne lata


        @Override
        public ArrayList<UserView> getUsers () {
            ArrayList<UserView> uzytkownicy = new ArrayList<>();
            Statement stmt = null;
            try {
                stmt = this.conn.createStatement();
                String sql;
                sql = "SELECT PESEL, TypUzytkownika FROM uzytkownicy";
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    //Retrieve by column name
                    String PESEL = rs.getString("PESEL");
                    TypUzytkownika typ = rs.getObject("TypUzytkownika"); //zrobić fiza tutaj, bo nie wiem jak zrobić getTypUzytkownika xD

                    uzytkownicy.add(new UserView(PESEL, typ));
                    //Display values
                    //System.out.print("ID: " + id);
                    //System.out.print(", Age: " + age);
                    //System.out.print(", First: " + first);
                    //System.out.println(", Last: " + last);
                }
                rs.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }finally{
                //finally block used to close resources         Ale czy istotnie potrzebne xD?
                try{
                    if(stmt!=null)
                        conn.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
            return uzytkownicy;
        }

        //bedzie bez hashowania, jeszcze
        @Override
        public boolean addUser (UserView user){
            Statement stmt = null;
            try {
                stmt = this.conn.createStatement();
                String sql;
                sql = "INSERT INTO uzytkownicy VALUES ('"+ user.getPESEL() +"', '"+ user.getPasswd() +"', '" + user.getTyp()+"')";
                ResultSet rs = stmt.executeQuery(sql); //po addzie jakiś resultSet? :o
                return true;
            } catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }finally{
                //finally block used to close resources         Ale czy istotnie potrzebne xD?
                try{
                    if(stmt!=null)
                        conn.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
        }

        //
        @Override
        public boolean changePasswd (UserView userWithNewPasswd){ // a nowe haslo?
            Statement stmt = null;
            try {
                stmt = this.conn.createStatement();
                String sql;
                sql = "UPDATE uzytkownicy SET haslo = '"+ /*no nowe hasło xD*/ +"' WHERE login = '"+ userWithNewPasswd.getPESEL() +"'";
                ResultSet rs = stmt.executeQuery(sql); //po delete jakiś resultSet? :o
                return true;
            } catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }finally{
                //finally block used to close resources         Ale czy istotnie potrzebne xD?
                try{
                    if(stmt!=null)
                        conn.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
        }

        //
        @Override
        public boolean deleteUser (UserView user){
            Statement stmt = null;
            try {
                stmt = this.conn.createStatement();
                String sql;
                sql = "DELETE FROM users WHERE PESEL = '"+ user.getPESEL() +"'";
                ResultSet rs = stmt.executeQuery(sql); //po delete jakiś resultSet? :o
                return true;
            } catch(SQLException se){
                //Handle errors for JDBC
                 se.printStackTrace();
                 return false;
            }finally{
        //finally block used to close resources         Ale czy istotnie potrzebne xD?
                try{
                    if(stmt!=null)
                    conn.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(conn!=null)
                    conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
        }

        @Override
        public ArrayList<EmployeeView> getEmployees () {
            ArrayList<EmployeeView> pracownicy = new ArrayList<>();
            Statement stmt = null;
            try {
                stmt = this.conn.createStatement();
                String sql;
                sql = "SELECT * FROM pracownicy";
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    //Retrieve by column name
                    String PESEL = rs.getString("PESEL");
                    String name = rs.getString("name"); //name czy imie? poprawić ewentualnie
                    String secondName = rs.getString("secondName");
                    String surname = rs.getString("surname");
                    String position = rs.getString("position");
                    String specialization = rs.getString("specialization");
                    String street = rs.getString("street");
                    String city = rs.getString("city");
                    String postcode = rs.getString("postcode");
                    String telephone = rs.getString("telephone");
                    int wage = rs.getInt("wage");
                    Date dateOfEmployee = rs.getDate("dateOfEmployee");

                    pracownicy.add(new EmployeeView(PESEL, name, secondName, surname, position, specialization, street,
                            city, postcode, telephone, wage, dateOfEmployee));
                    //Display values
                    //System.out.print("ID: " + id);
                    //System.out.print(", Age: " + age);
                    //System.out.print(", First: " + first);
                    //System.out.println(", Last: " + last);
                }
                rs.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }finally{
                //finally block used to close resources         Ale czy istotnie potrzebne xD?
                try{
                    if(stmt!=null)
                        conn.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
            return pracownicy;
        }

        @Override
        public boolean addEmployee (EmployeeView Employee){
            Statement stmt = null;
            try {
                stmt = this.conn.createStatement();
                String sql;
                sql = "INSERT INTO pracownicy VALUES ('"+ Employee.getPESEL() +"', '"+ Employee.getName() +"', '"
                        + Employee.getSecondName() +"', '" +Employee.getSurname() +"', '" +Employee.getPosition()
                        + "', '" + Employee.getSpecialization() +"', '" + Employee.getStreet() +"', '" + Employee.getCity()
                        +"', '" + Employee.getPostcode() +"', '" + Employee.getTelephone() +"', '" + Employee.getWage()
                        +"', '" + Employee.getDateOfEmployee() +"')";
                ResultSet rs = stmt.executeQuery(sql); //po addzie jakiś resultSet? :o
                return true;
            } catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }finally{
                //finally block used to close resources         Ale czy istotnie potrzebne xD?
                try{
                    if(stmt!=null)
                        conn.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
        }
            // tego nie czaję :/
        @Override
        public boolean changeEmployeeName (EmployeeView Employee){
            return false;
        }

        @Override
        public boolean deleteEmployee (EmployeeView employee){
            Statement stmt = null;
            try {
                stmt = this.conn.createStatement();
                String sql;
                sql = "DELETE FROM pracownicy WHERE PESEL = '"+ employee.getPESEL() +"'";
                ResultSet rs = stmt.executeQuery(sql); //po delete jakiś resultSet? :o
                return true;
            } catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
                return false;
            }finally{
                //finally block used to close resources         Ale czy istotnie potrzebne xD?
                try{
                    if(stmt!=null)
                        conn.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
        }
            // jest 5:30 i teraz też tego nie potrafię napisać :(
        @Override
        public FinanceView getFinances () {
            return null;
        }
    }