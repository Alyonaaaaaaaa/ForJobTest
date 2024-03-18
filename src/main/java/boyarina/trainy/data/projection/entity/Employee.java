package boyarina.trainy.data.projection.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}