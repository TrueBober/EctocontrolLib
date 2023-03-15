package ru.kip.ectocontrol;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.kip.ectocontrol.utils.JsonCodec;
import ru.kip.ectocontrol.utils.StringUtils;

import java.util.Optional;

/**
 * Устройство
 */
@NoArgsConstructor
@Data
public class Device {

    /**
     * Идентификатор устройства
     */
    private int id;

    /**
     * Идентификатор системы
     */
    private int systemObjectId;

    /**
     * Название устройства
     */
    private String name;

    /**
     * Тип устройства
     */
    @NonNull
    private String type;

    /**
     * Указать имя устройства (в API ошибка - смя устройства возвращалось в кавычках, поэтому здесь эти кавычки
     * исключаются)
     */
    public void setName(String name) {
        this.name = StringUtils.clearString(name);
    }

    /**
     * Получить название устройства (может отсутствовать)
     */
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @Override
    public String toString() {
        return JsonCodec.toPrettyJson(this);
    }
}
