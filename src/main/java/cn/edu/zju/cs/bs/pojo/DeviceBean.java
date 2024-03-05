package cn.edu.zju.cs.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceBean<T> {
    private Integer totalNum;
    private List<T> items;
}
