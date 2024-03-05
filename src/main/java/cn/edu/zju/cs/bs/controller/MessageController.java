package cn.edu.zju.cs.bs.controller;

import cn.edu.zju.cs.bs.pojo.*;
import cn.edu.zju.cs.bs.service.DeviceService;
import cn.edu.zju.cs.bs.service.MessageService;
import cn.edu.zju.cs.bs.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/getMessages")
    public Result<MessageBean> getMessageByDeviceID_(Integer deviceID) {
        MessageBean messageBean = messageService.getMessageByDeviceID_(deviceID);
        return new Result(1, "获得消息成功", messageBean);
    }

    @GetMapping("/getMessage")
    public Result<MessageBean> getMessageByDeviceID(Integer deviceID) {
        MessageBean messageBean = messageService.getMessageByDeviceID(deviceID);
        return new Result(1, "获得消息成功", messageBean);
    }

    @GetMapping("/getMessageNum")
    public Result<Long> getMessageNum(@RequestHeader(name = "Authorization") String token) {
        Map<String, Object> userInfos = JwtUtil.parseToken(token);
        Integer userID = (Integer) userInfos.get("id");
        DeviceBean<Device> devices = deviceService.getAllDevice(userID);
        long num = 0;
        for(int i=0; i<devices.getTotalNum(); i++){
            num += messageService.getMessageNumByDeviceID(devices.getItems().get(i).getId());
        }
        return new Result(1, "获得消息成功", num);
    }

    @GetMapping("/getAlertMessageNum")
    public Result<Long> getAlertMessageNum(@RequestHeader(name = "Authorization") String token) {
        Map<String, Object> userInfos = JwtUtil.parseToken(token);
        Integer userID = (Integer) userInfos.get("id");
        DeviceBean<Device> devices = deviceService.getAllDevice(userID);
        long num = 0;
        for(int i=0; i<devices.getTotalNum(); i++){
            num += messageService.getAlertMessageNumByDeviceID(devices.getItems().get(i).getId());
        }
        return new Result(1, "获得消息成功", num);
    }
}
