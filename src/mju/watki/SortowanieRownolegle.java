/**
 * Zbiór wejściowy dzielimy na 4 części
 * Każdą część sortujemy w oddzielnym wątku
 * Scalamy uzyskane wyniki: wybieramy pierwszy element z dowolnej części, porównujemy z pozostałymi pierwszymi
 * elementami w pozostałych częściach, po znalezieniu najmniejszego/największego usuwamy go ze zbioru
 * częściowego i przepisujemy do zbioru wynikowego.
 */

package mju.watki;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class SortowanieRownolegle {

    public static void main(String[] args) throws InterruptedException {

        int numberOfElements = 1000000;
        int numberOfPartitions = 6;
        int numberOfThreads = 3;
        List<Integer> startingList = createElements(numberOfElements);
        List<List<Integer>> partitionedList = splitListToPartitions(startingList,numberOfPartitions);
        List<List<Integer>> sortedPartitionedList = new ArrayList<>();
        List<Integer> finalResult = new ArrayList<>();

        System.out.println("Ilość jedoczesnych wątków i równoczesnych sortowań = " + partitionedList.size());


        //TODO to trzeba przerobić na uruchamianie submitów w pętli
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        AtomicLong parallelSortingStart = new AtomicLong();
        List<Future<List<Integer>>> sortedPartitionsFutures = new ArrayList<>();
        for (List<Integer> partition : partitionedList) {
            Future<List<Integer>> wynikSubmit = executorService.submit(() -> {
                if (parallelSortingStart.get() ==0)  {
                    final long currentTime = (new Date()).getTime();
                    parallelSortingStart.set(currentTime);
                }
                Collections.sort(partition);
                return partition;
            });
            sortedPartitionsFutures.add(wynikSubmit);
        }

        try{
            for(Future<List<Integer>> future : sortedPartitionsFutures){
                sortedPartitionedList.add(future.get());
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        long parallelSortingFinish = (new Date()).getTime();
        finalResult=mergeSortedLists(sortedPartitionedList);
        executorService.shutdown();

        long nonParallelSortingStart = (new Date()).getTime();
        Collections.sort(startingList);
        long nonParallelSortingFinish = (new Date()).getTime();

        System.out.println("Time required for parallel sorting of " + numberOfElements + " using " + numberOfThreads + " watkow = " + (parallelSortingFinish-parallelSortingStart.get()));
        System.out.println("Time required for non parallel sorting of " + numberOfElements + " = " + (nonParallelSortingFinish-nonParallelSortingStart));
        System.out.println("Is the merged array equals to sorted starting array - " + (finalResult.equals(startingList)));
    }

    private static List<Integer> createElements(int numberOfElements) {
        List<Integer> localList = new ArrayList<>();

        System.out.println("Generuję elementy losowe. Proszę czekać");

        Random random = new Random();
        for (int i=0;i<numberOfElements;i++) {
            localList.add(random.nextInt(10000));
            if (i%100000==0) {
                System.out.print(".");
            }
        }

        System.out.println("");
        return localList;
    }

    private static List<List<Integer>> splitListToPartitions(List<Integer> startingList, int numberOfPartitions) {

        List<List<Integer>> listOfLists = new ArrayList<>();
        int lastNotAssignedIndex = 0;

        for (int i=0;i<numberOfPartitions;i++) {
            listOfLists.add(new ArrayList<Integer>());
        }

        for (int i=0;i<startingList.size();i+=numberOfPartitions) {
            if (startingList.size()-i>=numberOfPartitions) {
                for (int j=0;j<numberOfPartitions;j++) {
                    listOfLists.get(j).add(startingList.get(i+j));
                }
            } else {
                lastNotAssignedIndex=i;
                break;
            }
        }

        for (int i = lastNotAssignedIndex;i<startingList.size();i++) {
            listOfLists.get(i-lastNotAssignedIndex).add(startingList.get(i));
        }

        return listOfLists;
    }

    //TODO napisac mergowanie
    private static List<Integer> mergeSortedLists(List<List<Integer>> sortedPartitions) {

        List<Integer> result = new ArrayList<>();
        int minimum = Integer.MAX_VALUE;
        int indexOfListWithMinimum = -1;

        System.out.println("Lącze posortowane tablice w jedną całość");

        while(!allListsAreEmpty(sortedPartitions)) {
            for (int i=0;i<sortedPartitions.size();i++) {
                if (!sortedPartitions.get(i).isEmpty()) {
                    if (minimum>sortedPartitions.get(i).get(0)) {
                        minimum=sortedPartitions.get(i).get(0);
                        indexOfListWithMinimum=i;
                    }
                }
            }
            result.add(minimum);
            sortedPartitions.get(indexOfListWithMinimum).remove(0);
            minimum = Integer.MAX_VALUE;
        }

        return result;
    }

    private static boolean allListsAreEmpty(List<List<Integer>> sortedPartitions) {
        boolean allListsAreEmpty = true;

        for (List<Integer> list : sortedPartitions) {
            if (!list.isEmpty()) {
                allListsAreEmpty=false;
            }
        }

        return allListsAreEmpty;
    }

}

