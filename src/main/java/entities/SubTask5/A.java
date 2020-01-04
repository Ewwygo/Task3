package entities.SubTask5;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class A implements Stat{

    int i;
    String s;

    @Override
    public int sum(int i, String s) {
        return i+s.length();
    }
}
