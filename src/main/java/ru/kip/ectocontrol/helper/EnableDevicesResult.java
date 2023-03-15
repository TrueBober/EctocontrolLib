package ru.kip.ectocontrol.helper;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class EnableDevicesResult {

    private List<Integer> success;

    private List<Integer> fail;

}
