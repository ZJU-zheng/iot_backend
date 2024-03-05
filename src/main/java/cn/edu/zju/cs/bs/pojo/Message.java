package cn.edu.zju.cs.bs.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private Long id;
    private Long deviceId;
    private String info;
    private Long value;
    private Long alert;
    private Double lng;
    private Double lat;
    private LocalDateTime timeStamp;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
