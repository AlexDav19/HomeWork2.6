package pro.sky.HomeWork;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Создал отдельно список отделов, чтобы нельзя было добавить человека с некорректным отделом
@Service
public class Departments {
    private final Set<Integer> departments = new HashSet<>(List.of(1, 2, 3, 4, 5));

    public Set<Integer> getDepartments() {
        return departments;
    }
}
