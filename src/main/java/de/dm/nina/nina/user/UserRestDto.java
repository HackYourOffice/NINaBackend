package de.dm.nina.nina.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserRestDto {
    private String name;
    private int id;
    private Date timtestamp;
}