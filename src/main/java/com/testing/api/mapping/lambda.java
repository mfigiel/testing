package com.testing.api.mapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class lambda {

    List<String> names = Arrays.asList("Kasia", "Ania", "Zosia", "Bartek");
    public void sort(){
        Collections.sort(names, (s1, s2) -> s1.compareToIgnoreCase(s2));
        names.forEach(arg -> System.out.println(arg));
    }

}
