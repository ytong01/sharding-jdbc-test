package cn.cloudwalk.service.impl;

import cn.cloudwalk.mapper.StudentMapper;
import cn.cloudwalk.model.Student;
import cn.cloudwalk.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
  
@Service  
public class StudentServiceImpl implements StudentService {
      
    @Resource  
    public StudentMapper studentMapper;
  
    public boolean insert(Student student) {
        return studentMapper.insert(student) > 0 ? true : false;  
    }  
  
}  
