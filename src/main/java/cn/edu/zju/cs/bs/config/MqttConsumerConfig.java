package cn.edu.zju.cs.bs.config;

import cn.edu.zju.cs.bs.config.impl.MqttConsumerCallBack;
import cn.edu.zju.cs.bs.mapper.MessageMapper;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConsumerConfig {

    @Autowired
    private MessageMapper messageMapper;

    @Value("${mqtt.url}")
    private String url;

    @Value("${mqtt.client.id}")
    private String clientId;

    @Value("${mqtt.topic}")
    private String topic;
    private MqttClient client;

    @PostConstruct
    public void init() {
        connect();
    }

    public void connect(){
        try {
            client = new MqttClient(url,clientId,new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setConnectionTimeout(100);
            options.setKeepAliveInterval(20);
            client.setCallback(new MqttConsumerCallBack(messageMapper));
            client.connect(options);
            client.subscribe(topic, 2);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void disConnect(){
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
