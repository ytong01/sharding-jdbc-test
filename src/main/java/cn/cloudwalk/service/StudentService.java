package cn.cloudwalk.service;

import cn.cloudwalk.model.Student;

import java.util.List;

/**
 * 处理学生的service
 * @author liuyazhuang
 *
 */
public interface StudentService {  
 
    boolean insert(Student student);

    List<Student> findAll();
}  
