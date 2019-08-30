
package assessmentanalyser.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection
{
    private static  String DBNAME = null;
    private static Connection conn = null;
    private static String stuTable = "STUDENTS", gradeTable = "GRADES";
   
    
    public static Connection createConnection()
    {   
        //register and load the driver
        try
        {     
                DBNAME = "Assessment";
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

                //establish connection

                conn = DriverManager.getConnection("jdbc:derby:"+ DBNAME + ";create=true"); 
                System.out.println("Creadted");
              
            
       
        }
        catch(ClassNotFoundException | SQLException  ex)
        {
        }
        
        
        return conn;   
    }
    
    //checking if the tabke already exists
    public static boolean checkTable(String table, Connection conn)
    {   
        boolean exists = false;
        
        try
        {
            ResultSet rs = conn.getMetaData().getTables(null, null, table, null);
            
            while(rs.next())
            {
                String tName = rs.getString("TABLE_NAME");
                
                if(tName != null && tName.equals(table))
                    exists = true;
                break;
            }
          
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }
    
    
    //creating the table student
    public static String createStudentTable(Connection conn )
    {
       
        PreparedStatement pstm;
        
        
        //querry for table student
        String querry = "CREATE TABLE " + stuTable + " (id INT , fname VARCHAR(80), lname VARCHAR(80), class VARCHAR(50), primary key(id))";
        try
        {  
            
           if(checkTable(stuTable, conn))
           {   
                System.out.println(stuTable +" TABLE ALREADY EXISTS");
            
            }
            else
            {
            
                 pstm = conn.prepareStatement(querry);
                 pstm.execute();
                 
            }  
        } 
        catch(SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return stuTable;
    }
    
    public static String createGradeTable(Connection conn)       
    {   
        
        PreparedStatement pstm;
        
        
        //querry for table student
        String querry = "CREATE TABLE " + gradeTable 
                + " (subject VARCHAR(90), grade DOUBLE, student_id INT,primary key(subject, grade, student_id),"
               + " FOREIGN KEY(student_id) REFERENCES " +stuTable+"(id))";
        
        try
        {  
            
            if(checkTable(gradeTable, conn))
            {   
                System.out.println(gradeTable + " TABLE ALREADY EXISTS");
            
            }
            else
            {
                 pstm = conn.prepareStatement(querry);
                 pstm.execute();
                 
                 
            }  
        } 
        catch(SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return gradeTable;
    }
    
    public static void dropTable(Connection conn, String table) 
    {
        
        try
        {   Statement stm = conn.createStatement();
            stm.executeUpdate("DROP TABLE " + table);
            System.out.println("droped");
            stm.close();
        }
        catch(SQLException ex)
        {
        }
        
        
    }
}
