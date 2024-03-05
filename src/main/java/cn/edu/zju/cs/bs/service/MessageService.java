package cn.edu.zju.cs.bs.service;

import cn.edu.zju.cs.bs.pojo.Message;
import cn.edu.zju.cs.bs.pojo.MessageBean;

public interface MessageService {
    MessageBean<Message> getMessageByDeviceID_(Integer deviceID);
    MessageBean<Message> getMessageByDeviceID(Integer deviceID);

    Integer getMessageNumByDeviceID(Long deviceID);

    Integer getAlertMessageNumByDeviceID(Long deviceID);
}
