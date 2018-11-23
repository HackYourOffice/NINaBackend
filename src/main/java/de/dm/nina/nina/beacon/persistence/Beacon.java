package de.dm.nina.nina.beacon.persistence;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "beacons")
public class Beacon {
    @Id
    private Integer id;

    @NotNull
    private Integer x;
    @NotNull
    private Integer y;
    @NotNull
    private Integer z;

    @NotNull
    @Column(length = 50)
    private String description;
}
