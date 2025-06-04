package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("students", studentService.getAllStudents(PageRequest.of(page, 5)));
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "form";
        }
        studentService.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, @RequestParam String type, Model model) {
        List<Student> students = "class".equalsIgnoreCase(type)
                ? studentService.searchByClass(keyword)
                : studentService.searchByName(keyword);
        model.addAttribute("students", students);
        return "search";
    }
}
