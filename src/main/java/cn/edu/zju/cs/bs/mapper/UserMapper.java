package cn.edu.zju.cs.bs.mapper;

import cn.edu.zju.cs.bs.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);

    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);
    @Insert("insert into user(username, password, email, create_time, update_time) " +
            "values(#{username},#{password},#{email},now(),now())")
    void insertUser(String username, String email, String password);
    @Update("update user set email=#{email},update_time=now() where username=#{username}")
    void updateEmail(String username, String email);

    @Update("update user set password=#{newPassword},update_time=now() where username=#{username}")
    void updatePassword(String username, String oldPassword, String newPassword);
}
