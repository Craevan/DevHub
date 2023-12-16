package dev.crevan.magazinus.controller.web;

import dev.crevan.magazinus.model.Course;
import dev.crevan.magazinus.repository.CourseRepository;
import dev.crevan.magazinus.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping(CourseController.ROUTE)
@RequiredArgsConstructor
public class CourseController {

    static final String ROUTE = "/course";

    private final CourseService courseService;

    @GetMapping
    public String indexAction(final Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "course/index";
    }

    @GetMapping("/add")
    public String getCreateForm(final Model model) {
        return "course/add";
    }

    @PostMapping("/add")
    public String addCourse(@RequestBody final MultiValueMap<String, String> formData) {
        Course course = new Course();
        course.setName(formData.getFirst("name"));
        course.setDescription(formData.getFirst("description"));
        course.setPrice(Integer.parseInt(Objects.requireNonNull(formData.getFirst("price"))));
        courseService.save(course);
        return "redirect:" + ROUTE;
    }
}
