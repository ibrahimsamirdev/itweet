package mum.itweet.repository;

import mum.itweet.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Course, Long> {
}
