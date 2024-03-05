package cn.edu.zju.cs.bs.service.impl;

import cn.edu.zju.cs.bs.mapper.DeviceMapper;
import cn.edu.zju.cs.bs.pojo.Device;
import cn.edu.zju.cs.bs.pojo.DeviceBean;
import cn.edu.zju.cs.bs.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Override
    public void insertDevice(Integer userID, String deviceName, String deviceType) {
        deviceMapper.insertDevice(userID, deviceName, deviceType);
    }

    @Override
    public void updateDevice(Integer deviceID, String deviceName, String deviceType) {
        deviceMapper.updateDevice(deviceID, deviceName, deviceType);
    }

    @Override
    public Device selectByUserIDAndDeviceID(Integer userID, Integer deviceID) {
        Device device = deviceMapper.selectByUserIDAndDeviceID(userID, deviceID);
        return device;
    }

    @Override
    public DeviceBean<Device> getAllDevice(Integer userID) {
        DeviceBean<Device> deviceBean = new DeviceBean<>();
        List<Device> devices = deviceMapper.getAllDevice(userID);
        deviceBean.setTotalNum(devices.size());
        deviceBean.setItems(devices);
        return deviceBean;
    }

    @Override
    public Device getDevice(Integer deviceID, Integer userID) {
        Device device = deviceMapper.getDevice(deviceID, userID);
        return device;
    }

    @Override
    public Integer getDeviceNum(Integer userID) {
        return deviceMapper.getDeviceNum(userID);
    }

    @Override
    public Integer getDeviceOnlineNum(Integer userID) {
        return deviceMapper.getDeviceOnlineNum(userID);
    }
}
