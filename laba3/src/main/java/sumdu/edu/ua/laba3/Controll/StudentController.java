/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sumdu.edu.ua.laba3.Controll;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sumdu.edu.ua.laba3.model.Student;
/**
 *
 * @author lytvy
 */
@Controller
public class StudentController {
    List<Student> students;
    ApplicationContext factory;
    Student student;
    @RequestMapping(value = "/")
    public String home() {
	return "student";
    }
        
    @RequestMapping("StudentAdd")
    public String addStudent(HttpServletRequest request,HttpServletResponse response,Model m) throws IOException, SQLException{
        ApplicationContext factory = new ClassPathXmlApplicationContext("/spring.xml");
        List<Student> students;
        PrintWriter pw=null;
        try{
            pw=response.getWriter();
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace(pw);
            pw.print(ex.getMessage());
        }

        Connection conn=null;
        conn= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3311/university?useUnicode=true&characterEncoding=UTF-8","root","root");

        if(request.getParameter("name")!=null && request.getParameter("surname")!=null){
            PreparedStatement ps= (PreparedStatement) conn.prepareStatement("Insert into student (name, surname, age, email, group_, faculty) "+
                    "Values (?, ?, ?, ?, ?, ?)");
            ps.setString(1,request.getParameter("name"));
            ps.setString(2,request.getParameter("surname"));
            ps.setInt(3,Integer.parseInt(request.getParameter("age")));
            ps.setString(4,request.getParameter("email"));
            ps.setString(5,request.getParameter("group"));
            ps.setString(6,request.getParameter("faculty"));
            ps.executeUpdate();
        }


        Statement s= (Statement) conn.createStatement();
        ResultSet rs=s.executeQuery("SELECT * FROM student;");
        students =new LinkedList<Student>();
        while(rs.next()){
            Student student = (Student)factory.getBean("Student");
            student.setId(Integer.parseInt(rs.getString(1))); 
            student.setName(rs.getString(2));
            student.setSurname(rs.getString(3));
            student.setAge(Integer.parseInt(rs.getString(4)));
            student.setEmail(rs.getString(5));
            student.setFaculty(rs.getString(6));
            student.setGroup(rs.getString(7));
            students.add(student);
        }
        for(Student st:students){
            System.out.println(st.getName());
        }
        m.addAttribute("students", students);

        return "student";
    }       
}
