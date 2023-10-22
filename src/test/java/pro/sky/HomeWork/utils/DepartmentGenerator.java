package pro.sky.HomeWork.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepartmentGenerator {

    private final Set<Integer> departments = new HashSet<>(List.of(1, 2, 3, 4, 5));

    public Set<Integer> getDepartments() {
        return departments;
    }
}
