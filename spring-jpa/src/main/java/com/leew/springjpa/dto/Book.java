package com.leew.springjpa.dto;

import com.leew.springjpa.listener.MyEntityListener;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name="book")
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
// @EntityListeners(value= AuditingEntityListener.class)
public class Book extends BaseEntity implements Auditable {
    /**
     * BaseEntity에서 AuditingEntityListener 설정하므로 여기서는 생략
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

//    private LocalDateTime createdAt;
//
//    private LocalDateTime updatedAt;

    // 이전에 썼던 방식 - 이번에는 다르게 해볼 예정
//    @PrePersist
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
}
