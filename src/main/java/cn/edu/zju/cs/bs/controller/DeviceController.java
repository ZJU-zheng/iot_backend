package cn.edu.zju.cs.bs.controller;

import cn.edu.zju.cs.bs.pojo.Device;
import cn.edu.zju.cs.bs.pojo.DeviceBean;
import cn.edu.zju.cs.bs.pojo.Result;
import cn.edu.zju.cs.bs.service.DeviceService;
import cn.edu.zju.cs.bs.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/num")
    public Result<Integer> getDeviceNum(@RequestHeader(name = "Authorization") String token) {
        Map<String, Object> userInfos = JwtUtil.parseToken(token);
        Integer userID = (Integer) userInfos.get("id");
        Integer num = deviceService.getDeviceNum(userID);
        return new Result(1, "获得数据成功", num);
    }

    @GetMapping("/onlineNum")
    public Result<Integer> getDeviceOnlineNum(@RequestHeader(name = "Authorization") String token) {
        Map<String, Object> userInfos = JwtUtil.parseToken(token);
        Integer userID = (Integer) userInfos.get("id");
        Integer num = deviceService.getDeviceOnlineNum(userID);
        return new Result(1, "获得数据成功", num);
    }

    @GetMapping("/all")
    public Result<DeviceBean<Device>> getAllDevice(@RequestHeader(name = "Authorization") String token) {
        Map<String, Object> userInfos = JwtUtil.parseToken(token);
        Integer userID = (Integer) userInfos.get("id");
        DeviceBean<Device> deviceBean = deviceService.getAllDevice(userID);
        return new Result(1, "获得数据成功", deviceBean);
    }

    @GetMapping("/")
    public Result<Device> getAllDevice(Integer deviceID, @RequestHeader(name = "Authorization") String token) {
        Map<String, Object> userInfos = JwtUtil.parseToken(token);
        Integer userID = (Integer) userInfos.get("id");
        Device device = deviceService.getDevice(deviceID, userID);
        if(device == null){
            return new Result(0, "您没有该设备", null);
        }else{
            return new Result(1, "获得数据成功", device);
        }
    }

    @PostMapping("/insertDevice")
    public Result insertDevice(String deviceName, String deviceType, @RequestHeader(name = "Authorization") String token) {
        Map<String, Object> userInfos = JwtUtil.parseToken(token);
        Integer userID = (Integer) userInfos.get("id");
        deviceService.insertDevice(userID, deviceName, deviceType);
        return new Result(1, "新增设备成功", null);
    }

    @PatchMapping("/updateDevice")
    public Result updateDevice(Integer deviceID, String deviceName, String deviceType, @RequestHeader(name = "Authorization") String token) {
        Map<String, Object> userInfos = JwtUtil.parseToken(token);
        Integer userID = (Integer)userInfos.get("id");
        Device device = deviceService.selectByUserIDAndDeviceID(userID, deviceID);
        if(device == null){
            return new Result(0, "您没有该设备", null);
        }
        deviceService.updateDevice(deviceID, deviceName, deviceType);
        return new Result(1, "修改设备成功", null);
    }
}
