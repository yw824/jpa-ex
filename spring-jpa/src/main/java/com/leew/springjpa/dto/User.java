package com.leew.springjpa.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(
        name="user",
        indexes={@Index(columnList = "name")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @Column(insertable = false)
    private LocalDateTime updatedAt;
    /**
     * @OneToMany(fetch=FetchType.EAGER)
     * private List<Address> address;
     */

    @Transient
    private String testData;

    @Enumerated(value=EnumType.STRING)
    private Gender gender;
}
