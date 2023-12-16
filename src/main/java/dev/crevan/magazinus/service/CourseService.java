package dev.crevan.magazinus.service;

import dev.crevan.magazinus.model.Course;
import dev.crevan.magazinus.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course save(final Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }
}
