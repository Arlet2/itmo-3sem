package database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ants")
public class Ant extends Point {
    private float mustacheLength;

    public Ant() {

    }
}
