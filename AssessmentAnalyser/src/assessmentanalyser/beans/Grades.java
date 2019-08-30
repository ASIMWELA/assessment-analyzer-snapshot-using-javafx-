

package assessmentanalyser.beans;

/**
 *
 * @author AUGUSTINE
 */
public class Grades
{
    private String studentName, studentClass,subject;
    private double grade;
    int id;
    
    public Grades()
    {}
    
    public Grades(String studentName)
    {
        this.studentName = studentName;
    }
    public Grades(int id, String studentName, String studentClass, String subject, double grade)
    {
        this.id = id;
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.grade = grade;
        this.subject = subject;
    }
    
    public Grades(int id, String studentClass, String subject, double grade)
    {
        this.id = id;
        this.studentClass = studentClass;
        this.grade = grade;
        this.subject = subject;
    }
    
    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }
    
    public void setId(int id)
    {
        this.id= id;
    }
    
    public void setStudentClass(String studentClass)
    {
        this.studentClass = studentClass;
    }
    
    public void setGrade(double grade)
    {
        this.grade = grade;
    }
    
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    
    public double getGrade()
    {
        return this.grade;
    }
    
    public int getId()
    {
        return this.id;
    }
    public String getStudentName()
    {
        return this.studentName;
    }
    public String getStudentClass()
    {
        return this.studentClass;
    }
    public String getSubject()
    {
        return this.subject;
    }
}
