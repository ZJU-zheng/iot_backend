package cn.edu.zju.cs.bs.mapper;

import cn.edu.zju.cs.bs.pojo.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("select * from message where device_id=#{deviceID} limit 10")
    List<Message> getMessageByDeviceID_(Integer deviceID);
    @Select("select * from message where device_id=#{deviceID}")
    List<Message> getMessageByDeviceID(Integer deviceID);

    @Insert("insert into message(device_id, info, value, alert, lng, lat, time_stamp, create_time, update_time) " +
            "values(#{deviceId},#{info},#{value},#{alert},#{lng},#{lat},#{timeStamp},now(),now())")
    void insertMessage(Message message);

    @Select("select count(*) from message where device_id=#{deviceID} and alert=0")
    Integer getMessageNumByDeviceID(Long deviceID);

    @Select("select count(*) from message where device_id=#{deviceID} and alert=1")
    Integer getAlertMessageNumByDeviceID(Long deviceID);
}
