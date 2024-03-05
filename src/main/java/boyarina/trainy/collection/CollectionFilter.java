package boyarina.trainy.collection;


import java.util.Arrays;

public class CollectionFilter<T> {

    public T[] filter(T[] array, Filter filter) {
        return (T[]) Arrays.stream(array).map(filter::apply).toArray();
    }

    interface Filter {
        Object apply(Object o);
    }
}