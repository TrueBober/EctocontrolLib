package ru.axkip.ectocontrol;

import java.util.List;

/**
 * Библиотека для доступа к сервисам экеоконтрол
 */
public interface Ectocontrol {

    /**
     * Запросить список всех устройств
     *
     * @return список устройств, полученных от сервера
     */
    List<Device> getAllDevices();

    /**
     * Запросить подробную информацию об устройствах
     *
     * @param devicesIds список идентификаторов, для которых нужно получить детальную информацию
     * @return подробная информация об устройствах из списка
     */
    List<DeviceDetails> getDeviceDetails(List<Integer> devicesIds);

    /**
     * Включить реле для устройств, с указанными идентификаторами
     *
     * @param devicesIds список идентификаторов реле, которые нужно включить
     * @return список реле, операция для которых была завершена успешно
     */
    List<Integer> enableRelay(List<Integer> devicesIds);

    /**
     * Выключить реле с указанными идентификаторами
     *
     * @param devicesIds список идентификаторов реле, которые нужно выключить
     * @return список реле, операция для которых была завершена успешно
     */
    List<Integer> disableRelay(List<Integer> devicesIds);
}
