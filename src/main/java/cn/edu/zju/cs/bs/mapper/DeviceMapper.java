package cn.edu.zju.cs.bs.mapper;

import cn.edu.zju.cs.bs.pojo.Device;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DeviceMapper {
    @Insert("insert into device(user_id, device_name, device_type, create_time, update_time) " +
            "values(#{userID}, #{deviceName}, #{deviceType},now(),now())")
    void insertDevice(Integer userID, String deviceName, String deviceType);
    @Update("update device set device_name=#{deviceName},device_type=#{deviceType},update_time=now() where id=#{deviceID}")
    void updateDevice(Integer deviceID, String deviceName, String deviceType);
    @Select("select * from device where id=#{deviceID} and user_id=#{userID}")
    Device selectByUserIDAndDeviceID(Integer userID, Integer deviceID);

    @Select("select * from device where user_id=#{userID}")
    List<Device> getAllDevice(Integer userID);
    @Select("select * from device where id=#{deviceID} and user_id=#{userID}")
    Device getDevice(Integer deviceID, Integer userID);

    @Select("select count(*) from device where user_id=#{userID}")
    Integer getDeviceNum(Integer userID);

    @Select("select count(*) from device where user_id=#{userID} and id in(1,2,3,4,5)")
    Integer getDeviceOnlineNum(Integer userID);

}
