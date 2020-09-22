package model;

import lombok.Setter;

import java.util.*;

public class Randoms {
    private Queue<Integer> randomsQueue = new ArrayDeque<>();
    private List<Integer> numbers = new ArrayList<>();
    private final boolean zero;
    private final int range;
    @Setter
    private int limit=2;


    public Randoms(int range, boolean zero) {
        this.range = range;
        this.zero = zero;
        initiate();
        queueDraw();
    }

    public void reset() {
        this.numbers = new ArrayList<>(range);
        initiate();
        queueDraw();
    }

    public Integer getRandom(){
        return randomsQueue.remove();
    }

    public Integer getRandomAndReset(){
        Integer result = getRandom();
        reset();
        return result;
    }

    private void initiate() {
        int startValue = zero ? 0 : 1;
        for (int i = startValue; i < range-startValue; i++) {
            numbers.add(i);
        }
    }

    private void queueDraw(){
        Random random = new Random();
        for (int i = 0; i < limit; i++) {
            if(numbers.isEmpty()) break;
            int size=numbers.size();
            randomsQueue.add(numbers.get(random.nextInt(size)));
            numbers.remove(randomsQueue.peek());
        }

    }
}
