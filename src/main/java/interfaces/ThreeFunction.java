package interfaces;

@FunctionalInterface
public interface ThreeFunction<T,R> {

    R run (T t1,T t2,T t3);
}
