import models.Student;
import repositories.StudentsRepository;
import repositories.StudentsRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/java_lab_intro";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qwerty007";


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        StudentsRepository studentsRepository = new StudentsRepositoryJdbcImpl(connection);
        System.out.println(studentsRepository.findById(2L));
        //findAll
        System.out.println(studentsRepository.findAll());
        //save
        Student newStudent = new Student(null, "Роман", "Леонтьев", 18, 905);
        studentsRepository.save(newStudent);
        //update
        Student student = studentsRepository.findById(2L);
        student.setFirstName("Григорий");
        studentsRepository.update(student);
        connection.close();
    }

}
