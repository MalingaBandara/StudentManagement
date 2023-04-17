package com.developersstack.edumanage.repo.custom.impl;

import com.developersstack.edumanage.entity.Teacher;
import com.developersstack.edumanage.repo.CrudUtil;
import com.developersstack.edumanage.repo.custom.TeacherRepo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherRepoImpl implements TeacherRepo {


    @Override
    public boolean save(Teacher teacher) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute( " INSERT INTO teacher VALUES (?, ?, ?, ?)",
                teacher.getCode(),
                teacher.getName(),
                teacher.getAddress(),
                teacher.getContact()
        );
    }

    @Override
    public boolean update(Teacher teacher) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute( " UPDATE teacher SET name=?, address=?, contact=? WHERE teacher_code=? ",
                teacher.getName(),
                teacher.getAddress(),
                teacher.getContact(),
                teacher.getCode()
        );

    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute( "DELETE FROM teacher WHERE teacher_code=?", s );

    }

    @Override
    public Teacher find(String s) throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.execute( " SELECT * FROM teacher WHERE teacher_code=? ", s );

        if ( rst.next() ) {
            return new Teacher(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }

        return null;

    }

    @Override
    public ArrayList<Teacher> findAll() {
        return null;
    }


    @Override
    public String findTeacherLastId () throws SQLException,ClassNotFoundException   {

        /*Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement stm = connection.prepareStatement(" SELECT teacher_code FROM teacher ORDER BY CAST(SUBSTRING(teacher_code, 3) AS UNSIGNED) DESC LIMIT 1 ");

        ResultSet rst = stm.executeQuery();

        if ( rst.next() ) {
            return rst.getString( 1 );
        }

        return "T-1";*/

        ResultSet rst = CrudUtil.execute( " SELECT teacher_code FROM teacher ORDER BY CAST(SUBSTRING(teacher_code, 3) AS UNSIGNED) DESC LIMIT 1 " );

        if ( rst.next() ) {
            return rst.getString( 1 );
        }

        return "T-1";

    }


    @Override
    public ArrayList<Teacher> findAllTeachers (String searchText) throws SQLException,ClassNotFoundException   {

        searchText = "%" + searchText + "%";

        /*Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement stm = connection.prepareStatement(" SELECT * FROM teacher WHERE name LIKE ? OR  address LIKE ?");
        stm.setString( 1, searchText );
        stm.setObject( 2, searchText );

        ResultSet rst = stm.executeQuery();

        ArrayList<Teacher> teacherList = new ArrayList<>(); // create array list for save teacher



        while ( rst.next() ) {
            teacherList.add( new Teacher (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }

        return teacherList;*/


        ResultSet rst = CrudUtil.execute( "SELECT * FROM teacher WHERE name LIKE ? OR  address LIKE ?", searchText, searchText );

        ArrayList<Teacher> teacherList = new ArrayList<>(); // create array list for save teacher



        while ( rst.next() ) {
            teacherList.add( new Teacher (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }

        return teacherList;

    }
}
