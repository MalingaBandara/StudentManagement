package com.developersstack.edumanage.db;

import com.developersstack.edumanage.model.Student;
import com.developersstack.edumanage.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                public String findStudentLastId (Student student) throws SQLException,ClassNotFoundException   {

                    Connection connection = DbConnection.getInstance().getConnection();

                    PreparedStatement stm = connection.prepareStatement(" SELECT student_id FROM student ORDER BY CAST(SUBSTRING(student_id, 3) AS UNSIGNED) DESC LIMIT 1 ");

                    ResultSet rst = stm.executeQuery();

                    if ( rst.next() ) {
                        return rst.getString( 1 );
                    }

                    return "S-1";

                }

        // Student manage ===============>

}