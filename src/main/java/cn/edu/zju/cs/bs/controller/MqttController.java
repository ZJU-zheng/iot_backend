package cn.edu.zju.cs.bs.controller;

import cn.edu.zju.cs.bs.config.MqttConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MqttController {
    @Autowired
    private MqttConsumerConfig client;

    @RequestMapping("/connect")
    @ResponseBody
    public String connect(){
        client.connect();
        return "mqtt服务器连接";
    }

    @RequestMapping("/disConnect")
    @ResponseBody
    public String disConnect(){
        client.disConnect();
        return "mqtt服务器断开连接";
    }
}
