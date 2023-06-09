package ru.axkip.ectocontrol.helper;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.axkip.ectocontrol.DeviceDetails;

import java.util.List;

/**
 * Список устройств
 */
@NoArgsConstructor
@Data
public class DevicesDetails {

    private List<DeviceDetails> devicesInfo;

}
