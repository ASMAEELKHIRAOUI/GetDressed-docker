package getdressed.domain;

import getdressed.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private String zipcode;

    private String address;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

}
