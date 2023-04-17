package com.developersstack.edumanage.repo.custom;

import com.developersstack.edumanage.entity.Teacher;
import com.developersstack.edumanage.repo.CrudRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherRepo extends CrudRepo<Teacher, String> {

    // find teacher last id
    public String findTeacherLastId () throws SQLException, ClassNotFoundException;

    // find all teachers
    public ArrayList<Teacher> findAllTeachers (String searchText) throws SQLException, ClassNotFoundException;


}
