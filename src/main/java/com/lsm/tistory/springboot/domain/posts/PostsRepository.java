package com.lsm.tistory.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 보통 ibatis나 Mybatis 등에서 Dao 라고 불리는 DB Layer 접근자
 * JPA 에서는 Repository 라고 부르며 인터페이스로 생성
 * 단순히 인터페이스 생성후 JpaRepository<Entity 클래스 , PK 타입> 을 상속하면 기본적인 CRUD 메소드가 자동으로 생성됨
 * Entity 클래스는 기본 Repository 없이는 제대로 역할을 할수 가 없다.
 */
public interface PostsRepository extends JpaRepository<Posts,Long> {
    // p.146
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
