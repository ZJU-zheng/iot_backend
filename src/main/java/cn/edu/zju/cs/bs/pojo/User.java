package cn.edu.zju.cs.bs.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
