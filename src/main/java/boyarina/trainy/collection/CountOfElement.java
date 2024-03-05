package boyarina.trainy.collection;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountOfElement<K, V extends Number> {
    public Map<K, Long> countForValueMap(K[] array) {
        return Arrays.stream(array).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}