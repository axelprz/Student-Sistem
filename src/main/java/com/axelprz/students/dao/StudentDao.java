package com.axelprz.students.dao;

import com.axelprz.students.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static com.axelprz.students.connection.ConnectionBD.getConnection;

public class StudentDao {
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<Student>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM student ORDER BY id_student";

        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                Student s = new Student();
                s.setIdStudent(rs.getInt("id_student"));
                s.setFirstName(rs.getString("first_name"));
                s.setLastName(rs.getString("last_name"));
                s.setPhone(rs.getString("phone"));
                s.setEmail(rs.getString("email"));
                students.add(s);
            }
        }catch( Exception e ){
            System.out.println("Error: " + e);
        }finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error: " + e);
            }
        }
        return students;
    }

    public boolean FindStudentById(Student student) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM student WHERE id_student = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getIdStudent());
            rs = ps.executeQuery();
            if(rs.next()) {
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                return true;
            }
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error: " + e);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var student = new Student(2);
        var find = new StudentDao().FindStudentById(student);
        if(find){
            System.out.println("Student found: " + student);
        }else{
            System.out.println("Student not found");
        }
    }
}


