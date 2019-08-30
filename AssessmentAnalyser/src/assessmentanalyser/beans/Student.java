
package assessmentanalyser.beans;

/**
 *
 * @author AUGUSTINE
 */
public class Student
{
    private String firstName;
    private String lastName;
    private int id;
    private String className;
    
    
    public Student()
    {}
    
    public Student(int id, String firstName, String lastName, String className)
    {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.className = className;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setClass(String className)
    {
        this.className = className;
    }
    
    public String getFirstName()
    {
        return this.firstName;
    }
    
    public String getClassName()
    {
        return this.className;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    
    public int getId()
    {
        return this.id;
    }
    
}
