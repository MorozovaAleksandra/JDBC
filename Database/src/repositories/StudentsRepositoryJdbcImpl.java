package repositories;

import models.Mentor;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentsRepositoryJdbcImpl implements StudentsRepository {
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from student where id = ";

    private Connection connection;

    public StudentsRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Student> findAllByAge(int age) {
        return null;
    }

    // Необходимо вытащить список всех студентов, при этом у каждого студента должен быть проставлен список менторов
    // у менторов в свою очередь ничего проставлять (кроме имени, фамилии, id не надо)
    // student1(id, firstName, ..., mentors = [{id, firstName, lastName, null}, {}, ), student2, student3
    // все сделать одним запросом
    //TODO
    public List<Student> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
        Student student = new Student();
        List<Student> students = new ArrayList<>();

        ResultSet resultSetMentor = statement.executeQuery("SELECT * FROM mentor");
        List<Mentor> listMentor = new ArrayList<>();

        if (resultSetMentor.next()) {
            listMentor.add(new Mentor(
                    resultSetMentor.getLong("id"),
                    resultSetMentor.getString("first_name"),
                    resultSetMentor.getString("last_name")
            ));
        }

        if (resultSet.next()) {
            student = new Student(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age"),
                    resultSet.getInt("group_number")
            );
        }
        students.add(student);
        return students;
    }

    @Override
    public Student findById(Long id) {
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(SQL_SELECT_BY_ID + id);
            if (result.next()) {
                return new Student(
                        result.getLong("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getInt("age"),
                        result.getInt("group_number")
                );
            } else return null;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
        }
    }

    // просто вызывается insert для сущности
    // student = Student(null, 'Марсель', 'Сидиков', 26, 915)
    // studentsRepository.save(student);
    // // student = Student(3, 'Марсель', 'Сидиков', 26, 915)
    @Override
    public void save(Student entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(
                "INSERT INTO student(first_name, last_name, age, group_number) VALUES ('%s','%s', '%d', '%d')");
    }

    // для сущности, у которой задан id выполнить обновление всех полей

    // student = Student(3, 'Марсель', 'Сидиков', 26, 915)
    // student.setFirstName("Игорь")
    // student.setLastName(null);
    // studentsRepository.update(student);
    // (3, 'Игорь', null, 26, 915)

    @Override
    public void update(Student entity) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
//            statement.execute("UPDATE student SET (first_name, last_name, age, group_number) VALUES ('%s','%s', '%d', '%d')");
            statement.execute("Update student set" + " first_name = " + entity.getFirstName() +
                    ", last_name = " + entity.getLastName() + ", age = " + entity.getAge() +
                    ", group_number = " + entity.getGroupNumber() +
                    " WHERE id = " + entity.getId() + "");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
    }

}
