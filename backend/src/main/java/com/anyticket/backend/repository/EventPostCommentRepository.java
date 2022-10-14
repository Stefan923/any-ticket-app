package com.anyticket.backend.repository;

import com.anyticket.backend.domain.EventPostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPostCommentRepository extends JpaRepository<EventPostComment, Long> {

    Page<EventPostComment> findByEventPostId(long id, Pageable pageable);

}
