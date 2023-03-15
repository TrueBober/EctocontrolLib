package ru.kip.ectocontrol.helper;

import lombok.*;

import java.util.List;

@Value(staticConstructor = "of")
public class DevicesIdsList {

    List<Integer> ids;

}
