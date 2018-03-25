
package be.unamur.info.workbook.algorithmic.greedy;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;

/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class PackagingCorrection extends Packaging {

    public PackagingCorrection(double[] weights, double capacity) {
        super(weights, capacity);
    }

    @Override
    protected void initMap() {
        map = new int[objectsCount()];
        int boxesCount = 0;
        double[] capacities = new double[objectsCount()]; // The remaining capacity of the boxes
        for(int i = 0; i < capacities.length; i++){
            capacities[i] = capacity;
        }
        // Main loop
        for(int i = 0; i < objectsCount() ; i++){
            boolean found = false;
            for(int j = 0 ; j < objectsCount() && !found ; j++){
                // If the object fits in the box, add it to the box
                if(weights[i] <= capacities[j]){
                    if(weights[i] == capacities[j]){
                        boxesCount = boxesCount + 1;
                    }
                    capacities[j] = capacities[j] - weights[i];
                    map[i] = j + 1;
                    found = true; // process the next object
                }
            }
        }
    }

    @Override
    public int packagesCount() {
        if(map == null){
            initMap();
        }
        return Ints.max(map);
    }

    @Override
    public int getPackageIndex(int object) {
        Preconditions.checkElementIndex(object, map.length);
        if(map == null){
            initMap();
        }
        return map[object];
    }
    
    

}
