package com.lsm.tistory.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
/**
 * BaseTimeEntity 클래스는 모든 Entity의 상위 클래스가 되어 Entity 들의
 * createdDate , modifiedDate를 자동으로 관리하는 역할
 * @MappedSuperclass : JPA Entity 클래스들이 BaseTimeEntity을 상속할경우 필드들도 칼럼으로 인식하도록 함
 * @EntityListeners(AuditingEntityListener.class) : Auditing 기능을 포함시킨다
 */
public abstract class BaseTimeEntity {
    @CreatedDate
    /**
     * @CreatedDate : Entity가 생성되어 저장될때 시간이 자동 저장
     */
    private LocalDateTime createdDate;

    @LastModifiedDate
    /**
     * @LastModifiedDate : 조회한 Entity의 값을 변경할 때 시간이 자동 저장
     */
    private LocalDateTime modifiedDate;
}
