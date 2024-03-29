package cn.edu.zju.cs.bs.pojo;

import lombok.Data;

import java.time.LocalDateTime;
//成员变量顺序也要和传过来的json格式数据一样
@Data
public class MessageFromMqtt {
    //设备ID
    private String clientId;
    //上报信息
    private String info;
    //设备数据
    private int value;
    //是否告警，0-正常，1-告警
    private int alert;
    //设备位置，经度
    private double lng;
    //设备位置，纬度
    private double lat;
    //上报时间，ms
    private long timestamp;
}