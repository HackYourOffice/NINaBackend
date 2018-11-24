package de.dm.nina.nina.user.api.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionRestRequestDto {
    private int id;
    private String person;
}
