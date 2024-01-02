/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sumdu.edu.ua.laba3.Repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sumdu.edu.ua.laba3.model.Student;

/**
 *
 * @author lytvy
 */
@Repository("DAO_Student")
public interface Repo_Student extends JpaRepository<Student, Integer>{
    
    Student addStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Integer studentId);

    Student updateStudent(Student student);

    void deleteStudent(Integer studentId);

    public Student getOne(int parseInt);
}
