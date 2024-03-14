package boyarina.trainy.streamApi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreamCollectMapAdvancedExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

//        System.out.println(students
//                .parallelStream()
//                .map(Student::getGrades)
//                .flatMap(Collectors.groupingBy())
//        );
    }

    @AllArgsConstructor
    @Data
    public static class Student {
        private String name;
        private Map<String, Integer> grades;
    }
}
