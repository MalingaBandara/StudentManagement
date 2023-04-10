package com.developersstack.edumanage.repo.custom;

import com.developersstack.edumanage.entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherRepo {

    // save teacher
    public boolean saveTeacher(Teacher teacher) throws SQLException, ClassNotFoundException;

    // find teacher last id
    public String findTeacherLastId () throws SQLException, ClassNotFoundException;

    // find teacher
    public Teacher findTeacher (String teacherId) throws SQLException, ClassNotFoundException;

    // Update teacher
    public boolean updateTeachr(Teacher teacher) throws SQLException, ClassNotFoundException;

    // find all teachers
    public ArrayList<Teacher> findAllTeachers (String searchText) throws SQLException, ClassNotFoundException;

    // Delete teacher
    public boolean deleteTeacher (String teacherId) throws SQLException, ClassNotFoundException;



}
