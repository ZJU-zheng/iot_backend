package cn.edu.zju.cs.bs.config.impl;

import cn.edu.zju.cs.bs.mapper.MessageMapper;
import cn.edu.zju.cs.bs.pojo.Message;
import cn.edu.zju.cs.bs.pojo.MessageFromMqtt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class MqttConsumerCallBack implements MqttCallback {

    private final MessageMapper messageMapper;

    public MqttConsumerCallBack(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("drop in connectionLost()");
    }
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String content = mqttMessage.toString();
        System.out.println(content);//
        ObjectMapper objectMapper = new ObjectMapper();
        MessageFromMqtt messageFromMqtt = objectMapper.readValue(content, MessageFromMqtt.class);
        System.out.println(messageFromMqtt);//
        Message message = new Message();
        message.setDeviceId(Long.valueOf(messageFromMqtt.getClientId().substring(8)));
        message.setInfo(messageFromMqtt.getInfo());
        message.setValue(Long.valueOf(messageFromMqtt.getValue()));
        message.setAlert(Long.valueOf(messageFromMqtt.getAlert()));
        message.setLat(messageFromMqtt.getLat());
        message.setLng(messageFromMqtt.getLng());
        message.setTimeStamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(messageFromMqtt.getTimestamp()), ZoneId.systemDefault()));
        System.out.println(message);//
        messageMapper.insertMessage(message);
    }
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println(String.format("drop in deliveryComplete()"));
    }
}
