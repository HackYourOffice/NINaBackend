package de.dm.nina.nina.user.persistence;

import de.dm.nina.nina.beacon.persistence.Beacon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userposition")
public class UserPosition {
    @Id
    @NotNull
    @Column(length = 255)
    private String name;

    @ManyToOne
    private Beacon beacon;

    @NotNull
    private Date timestamp;

    @NotNull
    private String color;
}
