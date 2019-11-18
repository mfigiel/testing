package com.testing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "person")
public class PersonDto {

    @Id
    @SequenceGenerator(name="person_id_seq",
            sequenceName="person_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="person_id_seq")
    private Long id;
    private Date creationDate = new Date();
    private String name;
    private String surname;
    @OneToOne
    @JoinColumn(name = "personaddressdto_id")
    private PersonAddressDto address;

    @Override
    public String toString() {
        return "PersonDto{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + address +
                '}';
    }
}
