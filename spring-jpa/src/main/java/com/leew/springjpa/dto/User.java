package com.leew.springjpa.dto;

import com.leew.springjpa.listener.MyEntityListener;
import com.leew.springjpa.listener.UserEntityListener;
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
@EntityListeners(value={MyEntityListener.class, UserEntityListener.class})
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

    // @Enumerated(value=EnumType.STRING)
    // private Gender gender;

    // Entity-Listener
    // PrePersist , PostPersist
    // PreUpdate , PostUpdate
    // PreRemove , PostRemove
    // PostLoad
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        System.out.println("[Pre-Persist] >>>");
    }

    /*@PostPersist
    public void postPersist() {
        System.out.println("[Post-Persist] >>> ");
    }*/

    @PreUpdate
    public void PreUpdate() {
        this.updatedAt = LocalDateTime.now();
        System.out.println("[Pre-Update] >>> ");
    }

    /*@PostUpdate
    public void PostUpdate() {
        System.out.println("[PostUpdate] >>> ");
    }*/

//    @PreRemove
//    public void PreRemove() {
//        System.out.println("[PreRemove] >>> ");
//    }
//
//    @PostRemove
//    public void PostRemove() {
//        System.out.println("[PostRemove] >>> ");
//    }
//
//    @PostLoad
//    public void postLoad() {
//        System.out.println("[Post-Load] >>>");
//    }
}