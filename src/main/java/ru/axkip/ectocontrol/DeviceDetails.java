package ru.axkip.ectocontrol;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.axkip.ectocontrol.utils.JsonCodec;
import ru.axkip.ectocontrol.utils.StringUtils;

import java.util.Optional;

/**
 * Детали устройства
 */
@NoArgsConstructor
@Data
public class DeviceDetails {

    /**
     * Идентификатор устройства
     */
    private int id;

    /**
     * Название устройства
     */
    private String type;

    /**
     * Название устройства
     */
    private String name;

    /**
     * Состояние устройства
     */
    private String state;

    /**
     * Значение устройства
     */
    private Double value;

    /**
     * Уровень сигнала
     */
    private Integer signalLevel;

    /**
     * Версия прошивки
     */
    private String systemFirmwareVersion;

    /**
     * Соединение
     */
    private Boolean connection;

    public DeviceDetails setName(String name) {
        this.name = StringUtils.clearString(name);
        return this;
    }

    public DeviceDetails setState(String state) {
        this.state = StringUtils.clearString(state);
        return this;
    }

    public DeviceDetails setSystemFirmwareVersion(String systemFirmwareVersion) {
        this.systemFirmwareVersion = StringUtils.clearString(systemFirmwareVersion);
        return this;
    }

    /**
     * Получить тип устройства (может отсутствовать)
     */
    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    /**
     * Получить название устройства (может отсутствовать)
     */
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    /**
     * Получить состояние устройства (может отсутствовать)
     */
    public Optional<String> getState() {
        return Optional.ofNullable(state);
    }

    /**
     * Получить значение устройства (может отсутствовать)
     */
    public Optional<Double> getValue() {
        return Optional.ofNullable(value);
    }

    /**
     * Получить уровень сигнала (может отсутствовать)
     */
    public Optional<Integer> getSignalLevel() {
        return Optional.ofNullable(signalLevel);
    }

    /**
     * Получить номер прошивки (может отсутствовать)
     */
    public Optional<String> getSystemFirmwareVersion() {
        return Optional.ofNullable(systemFirmwareVersion);
    }

    /**
     * Получить состояние подключения (может отсутствовать)
     */
    public Optional<Boolean> getConnection() {
        return Optional.ofNullable(connection);
    }

    @Override
    public String toString() {
        return JsonCodec.toPrettyJson(this);
    }
}
