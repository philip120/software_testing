package practice1.GeneticAlgorithmStudent;

import java.util.ArrayList;
import java.util.List;

public class TestGA extends Algorithm {
	public static List<Integer> generationCounts = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        int def_pop_size = 100;
        Population pop = new Population(def_pop_size);
        runAlgorithm(pop);
    }

    /*
     * HINT!
     * These methods help you run Algorithm.java methods separately to make it easier to debug them one-by-one.
     * You are strongly recommended to use these while debugging, especially for issue 3
     * You may want to change the randomised input there with your own hard-coded input (maybe a list).
     */
    public static void runCheckDiagonals() {
        Individual iv = new Individual();
        System.out.println(iv.list);
        printBoard(iv.list);
        for (int i = 0; i<iv.list.size();i++) {
            System.out.println(checkDiagonals(iv, iv.list.get(i), i));
        }
        System.out.println();
    }

    public static void runGetFitness() {
        Individual iv = new Individual();
        System.out.println(iv.list);
        printBoard(iv.list);
        printFitness(iv);
        System.out.println();
    }

    public static void runMateIv() {
        Individual iv1 = new Individual();
        System.out.println(iv1.list);
        Individual iv2 = new Individual();
        System.out.println(iv2.list);
        System.out.println(mateIv(iv1, iv2).list);
        System.out.println();
    }

    public static void runMutateIv() {
        Individual iv = new Individual();
        System.out.println(iv.list);
        System.out.println(mutateIv(iv).list);
        System.out.println();
    }

    public static void runNextGeneration() {
        Population pop = new Population(10);
        for (Individual iv : pop.individuals) {
            System.out.println(iv.list);
        }

        Population nextPop = nextGeneration(pop);
        System.out.println();
        for (Individual iv : nextPop.individuals) {
            System.out.println(iv.list);
        }
        System.out.println();
    }

}
