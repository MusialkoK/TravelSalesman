package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randoms {

    private final int[] exportValues;
    private final List<Integer> numbers = new ArrayList<>();
    private final int startValue;
    private final int range;
    private int iterator = 0;


    public Randoms(int range, int valuesToDraw) {
        exportValues = new int[valuesToDraw];
        this.range = range;
        this.startValue = 0;
        setNumbers();
        setExportValues();
    }

    public Randoms(int range, int valuesToDraw, int startValue) {
        exportValues = new int[valuesToDraw];
        this.range = range;
        this.startValue = startValue;
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
