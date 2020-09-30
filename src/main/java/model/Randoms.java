package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randoms {

    private final int[] exportValues;
    private final List<Integer> numbers = new ArrayList<>();
    @Getter
    private final int startValue;
    @Getter
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

    public int[] getArray() {
        int[] result;
        result = exportValues;
        setExportValues();
        return result;
    }

    private void setNumbers() {
        for (int i = 0; i < range; i++) {
            numbers.add(i+startValue);
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
