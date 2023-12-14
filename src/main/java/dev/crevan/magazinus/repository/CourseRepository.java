package dev.crevan.magazinus.repository;

import dev.crevan.magazinus.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
}
