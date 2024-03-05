package cn.edu.zju.cs.bs.service.impl;

import cn.edu.zju.cs.bs.mapper.MessageMapper;
import cn.edu.zju.cs.bs.pojo.Message;
import cn.edu.zju.cs.bs.pojo.MessageBean;
import cn.edu.zju.cs.bs.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public MessageBean<Message> getMessageByDeviceID_(Integer deviceID) {
        MessageBean<Message> messageBean = new MessageBean<>();
        List<Message> messages = messageMapper.getMessageByDeviceID_(deviceID);
        messageBean.setTotalNum(messages.size());
        messageBean.setItems(messages);
        return messageBean;
    }

    @Override
    public MessageBean<Message> getMessageByDeviceID(Integer deviceID) {
        MessageBean<Message> messageBean = new MessageBean<>();
        List<Message> messages = messageMapper.getMessageByDeviceID(deviceID);
        messageBean.setTotalNum(messages.size());
        messageBean.setItems(messages);
        return messageBean;
    }

    @Override
    public Integer getMessageNumByDeviceID(Long deviceID) {
        return messageMapper.getMessageNumByDeviceID(deviceID);
    }

    @Override
    public Integer getAlertMessageNumByDeviceID(Long deviceID) {
        return messageMapper.getAlertMessageNumByDeviceID(deviceID);
    }
}
