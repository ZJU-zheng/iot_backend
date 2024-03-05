package cn.edu.zju.cs.bs.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Device {
    private Long id;
    private Long userId;
    private String deviceName;
    private String deviceType;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
