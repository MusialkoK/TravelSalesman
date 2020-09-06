package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwoRandoms {
    @Getter
    private int firstRandom;
    @Getter
    private int secondRandom;
    private List<Integer> numbers = new ArrayList<>();
    @Getter
    private final boolean zero;
    private final int range;


    public TwoRandoms(int range, boolean zero) {
        this.range = range;
        this.zero = zero;
        initiate();
    }

    public void reset() {
        this.numbers = new ArrayList<>(range);
        initiate();
    }

    private void initiate() {
        int startValue = zero ? 0 : 1;
        for (int i = startValue; i < range-startValue; i++) {
            numbers.add(i);
        }
        firstRandom=draw();
        secondRandom=draw();
    }

    private int draw(){
        Random random = new Random();
        int result = numbers.get(random.nextInt(numbers.size()));
        numbers.remove((Integer) result);
        return result;
    }
}
