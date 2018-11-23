package de.dm.nina.nina.user.api.rest;

import lombok.Data;

import java.util.Date;

@Data
public class UserPositionRestDto {
    private String name;
    private int id;
    private Date timtestamp;
}