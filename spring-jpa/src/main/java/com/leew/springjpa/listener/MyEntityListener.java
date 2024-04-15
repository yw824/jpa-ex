package com.leew.springjpa.listener;

import com.leew.springjpa.dto.Auditable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class MyEntityListener {

    /** 이 객체는 Entity의 시선 밖이기 때문에,
     Entity를 받기 위해 Object로 받아서 처리해야 한다.
     Object로 받는 이유 : Entity가 Book일 수도 있고, User일 수도 있다.
     그저 createdAt, updatedAt과 그 멤버변수의 Getter/Setter를 가지고 있는 Entity일 뿐
     */
    @PrePersist
    public void prePersist(Object o) {
        /** object로 받았기 때문에, 어떤 클래스의 인스턴스인지 확인하는 절차가 필요 */
        if(o instanceof Auditable) {
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
    @PreUpdate
    public void preUpdate(Object o) {
        /** object로 받았기 때문에, 어떤 클래스의 인스턴스인지 확인하는 절차가 필요 */
        if(o instanceof Auditable) {
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
