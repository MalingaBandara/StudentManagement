package com.developersstack.edumanage.repo.custom;

import com.developersstack.edumanage.entity.Student;
import com.developersstack.edumanage.repo.CrudRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentRepo extends CrudRepo<Student, String> {

    // find student last id
    public String findStudentLastId () throws SQLException, ClassNotFoundException; // unique

    // find all student
    public ArrayList<Student> findAllStudents (String searchText) throws SQLException, ClassNotFoundException; // unique



}
