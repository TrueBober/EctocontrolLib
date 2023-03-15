package ru.kip.ectocontrol.helper;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kip.ectocontrol.Device;

import java.util.List;

/**
 * Список устройств
 */
@NoArgsConstructor
@Data
public class DevicesList {

    private List<Device> devices;

}
