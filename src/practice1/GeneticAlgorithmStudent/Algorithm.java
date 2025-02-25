package practice1.GeneticAlgorithmStudent;//import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Algorithm {

    public static int counter = 0;

    /* Calculates fitness for individual
     * (+1 for each threat from column for the currently viewed queen, +1 for all threats together from diagonals fot the currently viewed queen)
     * @param one board state
     * @return calculated fitness value (best is 0)
     */
    protected static int getFitness(Individual iv){
        int clashes = 0;
    	for (int i = 7; i>0; i--) {
            for (int j = 7; j>0; j--) {
                if (i != j) {
                    if (iv.list.get(i).equals(iv.list.get(j))) {
                        clashes +=1;
                    }
                }
            }
            if (checkDiagonals(iv,iv.list.get(i), i)) clashes += 1;
        }
        return clashes;
    }

    /* Checks for diagonal clashes for each queen in the board state
     * @param board state; queen to be checked here; index of the queen to be checked here
     * @return boolean if the board state passed the test (true = passed test, no threats; false = didn't pass, a threat was found)
     */
    protected static Boolean checkDiagonals(Individual iv, int current, int indexOfCurrent){
        for (int i = 7; i >=0; i--){
            if (i != indexOfCurrent){
                int viewableElem = iv.list.get(i);
                int diff = Math.abs(indexOfCurrent-i);
                if (Math.abs(viewableElem-current)==diff) return false;
            }
        }
        return true;
    }

    /* Sorts current individuals by fitness (0 is best) in ascending order and mates the best half of individuals with each other and adds a mutation to each new individual
     * @param a population of individuals
     * @return a new population based on the current one
     */
    public static Population nextGeneration(Population pop){
        int popSize = pop.individuals.size();

        pop.individuals.sort(Comparator.comparingInt(Algorithm::getFitness));

        Population halfPop = new Population();
        for (int i = popSize/2-1; i >=0; i--){
            halfPop.individuals.add(pop.individuals.get(i));
        }
        Population newPop = new Population();
        for (int i = popSize/2-1; i >=0; i--){
            if (i > 0) {
                Individual nextGenIv = mateIv(halfPop.individuals.get(i), halfPop.individuals.get(i - 1));
                mutateIv(nextGenIv);
                newPop.individuals.add(nextGenIv);
                Individual nextGenIv2 = mateIv(halfPop.individuals.get(i-1), halfPop.individuals.get(i));
                mutateIv(nextGenIv2);
                halfPop.individuals.add(nextGenIv2);
            }
            else {
                Individual nextGenIv = mateIv(halfPop.individuals.get(i), halfPop.individuals.get(popSize/2-1));
                mutateIv(nextGenIv);
                newPop.individuals.add(nextGenIv);
                Individual nextGenIv2 = mateIv(halfPop.individuals.get(popSize/2-1), halfPop.individuals.get(i));
                mutateIv(nextGenIv2);
                newPop.individuals.add(nextGenIv2);
            }
        }
        return newPop;
    }

    /* Takes 4 random values from one list and remaining 4 from the other and combines them into a new individual
     * @param 2 different board states
     * @return a combined board state
     */
    protected static Individual mateIv(Individual iv1, Individual iv2){
        final List<Integer> indices = new Random().ints(0, 8).distinct().limit(4).boxed().collect(Collectors.toList());
        Individual newIndividual = new Individual();
        for (int i = 0; i <= 7; i++){
            if (indices.contains(i)){
                newIndividual.list.set(i, iv2.list.get(i));
            }
            else{
                newIndividual.list.set(i, iv1.list.get(i));
            }
        }
        return newIndividual;
    }

    /* changes one queen location on the board
     * @param a board state
     * @return the same board state with one element changed
     */
    protected static Individual mutateIv(Individual iv){
        int index = (int)(Math.random()*8);
        int value = (int)(Math.random()*8);
        iv.list.set(index,value);
        return iv;
    }

    /* runs the full genetic algorithm and prints out results
     * @param a population of board states
     */
    public static void runAlgorithm(Population population) throws Exception{
        final List<Integer> solutionList = generation(population).list;
        System.out.println("Found suitable board state on generation " + (counter+1) + ": " + solutionList);
        System.out.println("Here is the found solution as a board where . marks an empty spot and, X marks a queen");
        printBoard(solutionList);
    }

    /* prints the boards
     * @param board state list
     */
    protected static void printBoard(List<Integer> state) {
        for (Integer queen:state) {
            for (int i = 0; i <8; i++){
                if (i==queen){
                    System.out.print("X ");
                }
                else System.out.print(". ");
            }
            System.out.println();
        }
    }

    /* generates new populations until a solution is found or 1000 generations is reached
     * @param the starting population
     * @return the solution board state
     */
    protected static Individual generation(Population population) throws Exception{
        if (counter >= 1000) {
            throw new Exception("Didn't find solution in 1000 generations");
        }
        for (Individual iv:population.individuals) {
            if (getFitness(iv) == 0){
                return iv;
            }
        }
        counter += 1;
        System.out.print("Generation: " + counter + "   Current highest fitness: ");
        printBestFitness(population);
        return generation(nextGeneration(population));
    }

    public static void printPopFitnesses(Population pop) {
        for (Individual individual : pop.individuals) {
            System.out.println(getFitness(individual));
        }
    }

    public static void printFitness(Individual iv){
        System.out.println(getFitness(iv));
    }

    protected static void printBestFitness(Population pop){
        int fitness = 200;
        for (Individual iv:pop.individuals) {
            int current = getFitness(iv);
            if (current <= fitness) {
                fitness = current;
            }
        }
        System.out.println(fitness);
    }
}
