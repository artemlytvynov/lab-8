/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sumdu.edu.ua.laba3.Repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sumdu.edu.ua.laba3.model.Content;
import sumdu.edu.ua.laba3.model.Student;

/**
 *
 * @author lytvy
 */
@Repository
public interface Repo_Content extends JpaRepository<Student, Integer>{
    @Query("from disciplines where student_id = id")
    List<Content> getMarksByStId(@Param("id") int id);
}
