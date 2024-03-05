package cn.edu.zju.cs.bs.service;

import cn.edu.zju.cs.bs.pojo.Device;
import cn.edu.zju.cs.bs.pojo.DeviceBean;

public interface DeviceService {
    void insertDevice(Integer userID, String deviceName, String deviceType);

    void updateDevice(Integer deviceID, String deviceName, String deviceType);

    Device selectByUserIDAndDeviceID(Integer userID, Integer deviceID);

    DeviceBean<Device> getAllDevice(Integer userID);

    Device getDevice(Integer deviceID, Integer userID);

    Integer getDeviceNum(Integer userID);

    Integer getDeviceOnlineNum(Integer userID);
}
