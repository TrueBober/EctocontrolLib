package ru.axkip.ectocontrol.helper;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class DevicesState {

    List<Integer> on;

    List<Integer> off;

}
