package com.developersstack.edumanage.repo.custom;

import com.developersstack.edumanage.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentRepo {

    // save student
    public boolean saveStudent(Student student) throws SQLException, ClassNotFoundException;

    // find student last id
    public String findStudentLastId () throws SQLException, ClassNotFoundException;

    // find student
    public Student findStudent (String student_id) throws SQLException, ClassNotFoundException;

    // Update student
    public boolean updateStudent(Student student) throws SQLException, ClassNotFoundException;

    // find all student
    public ArrayList<Student> findAllStudents (String searchText) throws SQLException, ClassNotFoundException;

    // Delete student
    public boolean deleteStudent(String studentId) throws SQLException, ClassNotFoundException;

}
