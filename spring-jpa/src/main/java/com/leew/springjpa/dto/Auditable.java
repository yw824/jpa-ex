package com.leew.springjpa.dto;

import java.time.LocalDateTime;

public interface Auditable {

    // 함수를 Lombok을 통해 구현한다. 직접 구현할 필요가 없다.
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(LocalDateTime updatedAt);

}
