package cn.cloudwalk.service;

import cn.cloudwalk.model.User;

import java.util.List;
 
/**
 * 处理用户的Service
 * @author liuyazhuang
 *
 */
public interface UserService {  
      
    public boolean insert(User u);
      
    public List<User> findAll();  
      
    public List<User> findByUserIds(List<Integer> ids);  
      
    public void transactionTestSucess();  
      
    public void transactionTestFailure() throws IllegalAccessException;  
 
}  
