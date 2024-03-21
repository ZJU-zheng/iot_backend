package cn.edu.zju.cs.bs.controller;

import cn.edu.zju.cs.bs.pojo.Result;
import cn.edu.zju.cs.bs.pojo.User;
import cn.edu.zju.cs.bs.service.UserService;
import cn.edu.zju.cs.bs.utils.JwtUtil;
import cn.edu.zju.cs.bs.utils.ThreadLoaclUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static cn.edu.zju.cs.bs.utils.ThreadLoaclUtil.get;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public Result<User> getUserInfo(/*@RequestHeader(name = "Authorization") String token*/) {
//        Map<String, Object> userInfos = JwtUtil.parseToken(token);
//        String username = (String)userInfos.get("username");
        Map<String, Object> map = ThreadLoaclUtil.get();
        String username = (String) map.get("username");
        User user = userService.selectByUsername(username);
        return new Result(1, "用户信息查询成功", user);
    }

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{7,20}$") String username, @Email String email, @Pattern(regexp = "^\\S{7,20}$") String password) {
        User user_1 = userService.selectByUsername(username);
        User user_2 = userService.selectByEmail(email);
        if((user_1 == null) && (user_2 == null)){
            userService.register(username,email,password);
            return new Result(1, "注册成功", null);
        }else if(user_1 == null){
            return new Result(0, "邮箱已注册", null);
        }else{
            return new Result(0, "用户名已注册", null);
        }
    }

    @PostMapping("/login")
    public Result login(@Email String email, @Pattern(regexp = "^\\S{7,20}$") String password) {
        User user = userService.selectByEmail(email);
        if(user == null) {
            return new Result(0, "邮箱不存在", null);
        }
        if(user.getPassword().equals(password)){
            //用户登录成功,生成token令牌,通过响应数据传给前端(
            Map<String, Object> userInfos = new HashMap<>();
            userInfos.put("id", user.getId());
            userInfos.put("username", user.getUsername());
            String token = JwtUtil.genToken(userInfos);
            return new Result(1, "登录成功", token);
        }else{
            return new Result(0, "密码错误", null);
        }
    }

    @PatchMapping("/updateEmail")
    public Result updateEmail(@Email String newEmail, @RequestHeader(name = "Authorization") String token) {
        Map<String, Object> userInfos = JwtUtil.parseToken(token);
        String username = (String)userInfos.get("username");
        User user = userService.selectByEmail(newEmail);
        if(user != null) {
            return new Result(0, "邮箱已存在", null);
        }
        userService.updateEmail(username, newEmail);
        return new Result(1, "修改邮箱成功", null);
    }

    @PatchMapping("/updatePassword")
    public Result updatePassword(@Pattern(regexp = "^\\S{7,20}$") String oldPassword, @Pattern(regexp = "^\\S{7,20}$") String newPassword, @RequestHeader(name = "Authorization") String token) {
        Map<String, Object> userInfos = JwtUtil.parseToken(token);
        String username = (String)userInfos.get("username");
        User user = userService.selectByUsername(username);
        if(user == null) {
            return new Result(0, "token失效", null);
        }
        if(!user.getPassword().equals(oldPassword)) {
            return new Result(0, "输入的旧密码错误", null);
        }
        userService.updatePassword(username, oldPassword, newPassword);
        return new Result(1, "密码修改成功", null);
    }
}
