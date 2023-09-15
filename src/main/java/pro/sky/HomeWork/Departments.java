package pro.sky.HomeWork;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Создал отдельно список отделов, чтобы нельзя было добавить человека с некорректным отделом
public class Departments {
    private final Set<Integer> departments = new HashSet<>(List.of(1, 2, 3, 4, 5));

    public Set<Integer> getDepartments() {
        return departments;
    }
}
