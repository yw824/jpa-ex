package com.leew.springjpa.dto;

import com.leew.springjpa.listener.MyEntityListener;
import com.leew.springjpa.listener.UserEntityListener;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(
        name="user",
        indexes={@Index(columnList = "name")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
)
@EntityListeners(value={UserEntityListener.class})
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class User extends BaseEntity {
    /**
     * BaseEntity에서 AuditingEntityListener 처리하기 때문에 여기서는 해당 listener 제외
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;

//    @Column(updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @Column(insertable = false)
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
    /**
     * @OneToMany(fetch=FetchType.EAGER)
     * private List<Address> address;
     */

    @Transient
    private String testData;
}