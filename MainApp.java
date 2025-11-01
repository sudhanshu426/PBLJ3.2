package com.example.app;

import com.example.app.config.AppConfig;
import com.example.app.dao.StudentDAO;
import com.example.app.di.Course;
import com.example.app.di.StudentDI;
import com.example.app.entity.Student;
import com.example.app.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {

        // --- Part A: Spring DI ---
        System.out.println("=== Spring Dependency Injection ===");
        Course course = new Course("Spring Framework");
        StudentDI studentDI = new StudentDI(course);
        studentDI.showDetails();

        // --- Part B & C: Spring + Hibernate ---
        System.out.println("\n=== Hibernate CRUD & Transaction Management ===");
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentDAO studentDAO = context.getBean(StudentDAO.class);
        studentDAO.saveStudent(new Student("Alice", "alice@example.com"));
        System.out.println("Student saved!");

        AccountService service = context.getBean(AccountService.class);
        try {
            service.transferMoney(1, 2, 1000);
        } catch (Exception e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }

        context.close();
    }
}
