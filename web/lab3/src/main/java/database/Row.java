package database;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "points")
public class Row implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private int x;
    private float y;
    private float r;
    private boolean isHit;
    private String scriptTime;

    public Row() {

    }
}
