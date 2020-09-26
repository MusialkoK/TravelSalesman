package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randoms {

    private final int[] exportValues;
    private final List<Integer> numbers = new ArrayList<>();
    private final boolean zero;
    private final int range;
    private int iterator = 0;


    public Randoms(int range, int valuesToDraw, boolean zero) {
        exportValues = new int[valuesToDraw];
        this.range = range;
        this.zero = zero;
        setNumbers();
        setExportValues();
    }

    public int draw() {
        int result = exportValues[iterator++];
        if (iterator == exportValues.length) {
            iterator = 0;
            setExportValues();
        }
        return result;
    }

    private void setNumbers() {
        int startValue = zero ? 0 : 1;
        for (int i = startValue; i < range - startValue; i++) {
            numbers.add(i);
        }
    }

    private void setExportValues() {
        Random random = new Random();
        List<Integer> localNumbers = new ArrayList<>(numbers);
        for (int i = 0; i < exportValues.length; i++) {
            if (localNumbers.isEmpty()) break;
            int exportValue = localNumbers.get(random.nextInt(localNumbers.size()));
            exportValues[i] = exportValue;
            localNumbers.remove((Object) exportValue);
        }

    }
}
