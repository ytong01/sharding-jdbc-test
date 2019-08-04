package cn.cloudwalk;

import cn.cloudwalk.model.Student;
import cn.cloudwalk.model.User;
import cn.cloudwalk.service.StudentService;
import cn.cloudwalk.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
 
/**
 * 测试分库分表规则
 * @author liuyazhuang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath*:spring-datasource.xml" })
public class ShardingJdbcMybatisTest {  
 
    @Resource  
    public UserService userService;
      
    @Resource  
    public StudentService studentService;
 
    @Test  
    public void testUserInsert() {  
        User u = new User();
        u.setUserId(10);
        u.setAge(23);
        u.setName("github");  
        Assert.assertEquals(userService.insert(u), true);  
    }  
      
    @Test  
    public void testStudentInsert() {  
        Student student = new Student();
        student.setStudentId(24);
        student.setAge(21);  
        student.setName("hehe");
        student.setUserId(31);

        Assert.assertEquals(studentService.insert(student), true);  
    }  
 
    @Test  
    public void testFindAll(){  
        List<User> users = userService.findAll();  
        if(null != users && !users.isEmpty()){  
            for(User u :users){  
                System.out.println(u);  
            }  
        }  
    }

    @Test
    public void testFindAllStudents(){
        List<Student> users = studentService.findAll();
        if(null != users && !users.isEmpty()){
            for(Student u :users){
                System.out.println(u);
            }
        }
    }

    @Test  
    public void testSQLIN(){  
        List<User> users = userService.findByUserIds(Arrays.asList(10, 11));
        if(null != users && !users.isEmpty()){  
            for(User u :users){  
                System.out.println(u);  
            }  
        }  
    }

    /**
     * shardingjdbc 支持异库事务
     */
    @Test  
    public void testTransactionTestSucess(){  
        userService.transactionTestSucess();  
    }  
      
    @Test(expected = IllegalAccessException.class)  
    public void testTransactionTestFailure() throws IllegalAccessException{  
        userService.transactionTestFailure();  
    }  
}  
