// SortByExpo class for sort ArrayList of PolynomComponent by exponent
import java.util.ArrayList;

public class SortByExpo {
    // sort ArrayList of Polynom Component by exponent
    protected static ArrayList<PolynomComponent> SortByExpoARR(ArrayList<PolynomComponent> pArr)
    {
        return mergeSortARR(pArr);
    }

    // mergeSort method for SmallPolynom ArrayList
    private static ArrayList<PolynomComponent> mergeSortARR(ArrayList<PolynomComponent> input)
    {
        ArrayList<PolynomComponent> left = new ArrayList<PolynomComponent>();
        ArrayList<PolynomComponent> right = new ArrayList<PolynomComponent>();
        int center;

        if (input.size() == 1) {
            return input;
        } else {
            center = input.size()/2;
            // copy the left half of whole into the left.
            for (int i=0; i<center; i++) {
                left.add(input.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for (int i=center; i<input.size(); i++) {
                right.add(input.get(i));
            }

            // Sort the left and right halves of the arraylist.
            left  = mergeSortARR(left);
            right = mergeSortARR(right);

            // Merge the results back together.
            mergeARR(left, right, input);
        }
        return input;
    }

    // merge sub-method for mergeSort
    private static void mergeARR(ArrayList<PolynomComponent> left, ArrayList<PolynomComponent> right,
                          ArrayList<PolynomComponent> whole)
    {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( (left.get(leftIndex).compareByExpo(right.get(rightIndex))) < 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }

        ArrayList<PolynomComponent> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }

        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }
}
