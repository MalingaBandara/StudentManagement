package com.developersstack.edumanage.db;

import com.developersstack.edumanage.model.Student;
import com.developersstack.edumanage.model.Teacher;
import com.developersstack.edumanage.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseAccessCode {

        // User manage ===============>

                // save user
                public boolean saveUser(User user) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" INSERT INTO user VALUES (?, ?, ?, ?)");

                    stm.setString( 1, user.getEmail() );
                    stm.setString( 2, user.getFirstName() );
                    stm.setString( 3, user.getLastName() );
                    stm.setString( 4, user.getPassword() );

                    return stm.executeUpdate() > 0;

                }

                // load user
                public User loginUser( String email) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" SELECT * FROM user WHERE email=? ");

                    stm.setString( 1, email );

                    ResultSet rst = stm.executeQuery();

                    if ( rst.next() ) {
                        return new User(
                                rst.getString( 2 ), rst.getString( 3 ),
                                rst.getString( 1 ), rst.getString( 4 ) );
                    }

                    return null;

                }

        // User manage ===============>


        // Student manage ===============>

                // save student
                public boolean saveStudent(Student student) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" INSERT INTO student VALUES (?, ?, ?, ?)");

                    stm.setString( 1, student.getStudentId() );
                    stm.setString( 2, student.getFullName() );
                    stm.setObject( 3, student.getDateOfBirth() );
                    stm.setString( 4, student.getAddress() );

                    return stm.executeUpdate() > 0;

                }


                // find student last id
                public String findStudentLastId () throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" SELECT student_id FROM student ORDER BY CAST(SUBSTRING(student_id, 3) AS UNSIGNED) DESC LIMIT 1 ");

                    ResultSet rst = stm.executeQuery();

                    if ( rst.next() ) {
                        return rst.getString( 1 );
                    }

                    return "S-1";

                }


                // find student
                public Student findStudent (String student_id) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" SELECT * FROM student WHERE student_id=? ");

                    ResultSet rst = stm.executeQuery();

                    if ( rst.next() ) {
                        return new Student(
                                rst.getString(1),
                                rst.getString(2),
                                rst.getDate(3),
                                rst.getString(4)
                        );
                    }

                    return null;

                }


                // Update student
                public boolean updateStudent(Student student) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" UPDATE student SET full_name=?, dob=?, address=? WHERE student_id=? ");

                    stm.setString( 2, student.getFullName() );
                    stm.setObject( 3, student.getDateOfBirth() );
                    stm.setString( 4, student.getAddress() );
                    stm.setString( 1, student.getStudentId() );

                    return stm.executeUpdate() > 0;

                }


                // find all student
                public ArrayList<Student> findAllStudents (String searchText) throws SQLException,ClassNotFoundException   {

                    searchText = "%" + searchText + "%";

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" SELECT * FROM student WHERE full_name LIKE ? OR  address LIKE ?");
                    stm.setString( 1, searchText );
                    stm.setObject( 2, searchText );

                    ResultSet rst = stm.executeQuery();

                    ArrayList<Student> studentsList = new ArrayList<>(); // create array list for save students



                    while ( rst.next() ) {
                        studentsList.add( new Student (
                                                rst.getString(1),
                                                rst.getString(2),
                                                rst.getDate(3),
                                                rst.getString(4)
                        ));
                    }

                    return studentsList;

                }


                // Delete student
                public boolean deleteStudent(String studentId) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" DELETE FROM student WHERE student_id=? ");
                    stm.setString( 1, studentId );

                    return stm.executeUpdate() > 0;

                }


        // Student manage ===============>


        // Teacher manage ===============>

                // save teacher
                public boolean saveTeacher(Teacher teacher) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" INSERT INTO teacher VALUES (?, ?, ?, ?)");

                    stm.setString( 1, teacher.getCode() );
                    stm.setString( 2, teacher.getName() );
                    stm.setObject( 3, teacher.getAddress() );
                    stm.setString( 4, teacher.getContact() );

                    return stm.executeUpdate() > 0;

                }


                // find student last id
                public String findStudentLastId () throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" SELECT student_id FROM student ORDER BY CAST(SUBSTRING(student_id, 3) AS UNSIGNED) DESC LIMIT 1 ");

                    ResultSet rst = stm.executeQuery();

                    if ( rst.next() ) {
                        return rst.getString( 1 );
                    }

                    return "S-1";

                }


                // find student
                public Student findStudent (String student_id) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" SELECT * FROM student WHERE student_id=? ");

                    ResultSet rst = stm.executeQuery();

                    if ( rst.next() ) {
                        return new Student(
                                rst.getString(1),
                                rst.getString(2),
                                rst.getDate(3),
                                rst.getString(4)
                        );
                    }

                    return null;

                }


                // Update student
                public boolean updateStudent(Student student) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" UPDATE student SET full_name=?, dob=?, address=? WHERE student_id=? ");

                    stm.setString( 2, student.getFullName() );
                    stm.setObject( 3, student.getDateOfBirth() );
                    stm.setString( 4, student.getAddress() );
                    stm.setString( 1, student.getStudentId() );

                    return stm.executeUpdate() > 0;

                }


                // find all student
                public ArrayList<Student> findAllStudents (String searchText) throws SQLException,ClassNotFoundException   {

                    searchText = "%" + searchText + "%";

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" SELECT * FROM student WHERE full_name LIKE ? OR  address LIKE ?");
                    stm.setString( 1, searchText );
                    stm.setObject( 2, searchText );

                    ResultSet rst = stm.executeQuery();

                    ArrayList<Student> studentsList = new ArrayList<>(); // create array list for save students



                    while ( rst.next() ) {
                        studentsList.add( new Student (
                                rst.getString(1),
                                rst.getString(2),
                                rst.getDate(3),
                                rst.getString(4)
                        ));
                    }

                    return studentsList;

                }


                // Delete student
                public boolean deleteStudent(String studentId) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" DELETE FROM student WHERE student_id=? ");
                    stm.setString( 1, studentId );

                    return stm.executeUpdate() > 0;

                }


            // Teacher manage ===============>

}
