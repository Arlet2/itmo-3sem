package database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="spiders")
public class Spider extends Point {
    private int legCount;

    public Spider() {

    }
}
