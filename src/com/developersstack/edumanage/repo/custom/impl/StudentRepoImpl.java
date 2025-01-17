package com.developersstack.edumanage.repo.custom.impl;

import com.developersstack.edumanage.entity.Student;
import com.developersstack.edumanage.repo.CrudUtil;
import com.developersstack.edumanage.repo.custom.StudentRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRepoImpl implements StudentRepo {

    @Override
    public String findStudentLastId () throws SQLException,ClassNotFoundException   {

        /*Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement stm = connection.prepareStatement(" SELECT student_id FROM student ORDER BY CAST(SUBSTRING(student_id, 3) AS UNSIGNED) DESC LIMIT 1 ");

        ResultSet rst = stm.executeQuery();

        if ( rst.next() ) {
            return rst.getString( 1 );
        }

        return "S-1";*/

        ResultSet rst = CrudUtil.execute( " SELECT student_id FROM student ORDER BY CAST(SUBSTRING(student_id, 3) AS UNSIGNED) DESC LIMIT 1 ");

        if ( rst.next() ) {
            return rst.getString( 1 );
        }

        return "S-1";

    }

    @Override
    public ArrayList<Student> findAllStudents (String searchText) throws SQLException,ClassNotFoundException   {

        searchText = "%" + searchText + "%";

        /*
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
        */

        ResultSet rst = CrudUtil.execute( " SELECT * FROM student WHERE full_name LIKE ? OR  address LIKE ? ", searchText, searchText);

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

    @Override
    public boolean save(Student student) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute( " INSERT INTO student VALUES (?, ?, ?, ?) ",
                student.getStudentId(),
                student.getFullName(),
                student.getDateOfBirth(),
                student.getAddress()
        );
    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute( "UPDATE student SET full_name=?, dob=?, address=? WHERE student_id=?",
                student.getFullName(),
                student.getDateOfBirth(),
                student.getAddress(),
                student.getStudentId()
        );
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute( "DELETE FROM student WHERE student_id=?", s );
    }

    @Override
    public Student find(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute( " SELECT * FROM student WHERE student_id=? ", s);

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

    @Override
    public ArrayList<Student> findAll() {
        return null;
    }
}
