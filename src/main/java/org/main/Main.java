package org.main;

import org.christophides.CombineMstMinWtPerfMatch;
import org.christophides.HamiltonianCircuit;
import org.christophides.MSTGenerator;
import org.christophides.MinWtPerfMatch;
import org.strategicopt.StrategicAntColonyOpt;
import org.strategicopt.StrategicSimulatedAnnealing;
import org.structs.Edge;
import org.structs.Node;
import org.tacticalopt.TacticalRandomSwap;
import org.tacticalopt.TacticalTwoOpt;
import org.util.DistanceCalculator;
import org.util.ParseCSV;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Commencing TSP...");

        ParseCSV pc = new ParseCSV();
        List<Node> nodeList = pc.ParseCSV();

        DistanceCalculator dc = new DistanceCalculator();
        double[][] distanceMatrix= dc.calculateDistance(nodeList);

        MSTGenerator mstGenerator = new MSTGenerator();
        List<Edge> mst=mstGenerator.calculateMST(distanceMatrix);

        System.out.println("\nGenerating Minimum Weight Perfect Matching...");
        MinWtPerfMatch pm = new MinWtPerfMatch();
        List<Edge> minWtPerfMatch=pm.calculateMST(distanceMatrix, mst);

        //System.out.println("\nCombining MST with Minimum Weight Perfect Matching...");
        CombineMstMinWtPerfMatch combine=new CombineMstMinWtPerfMatch();
        List<Edge> combinedGraph=combine.combineMSTAndPerfectMatching(mst,minWtPerfMatch );

        System.out.println("\nGenerating Hamiltonian Graph...");
        HamiltonianCircuit hc=new HamiltonianCircuit();
        List<Integer> hamiltonianPath= hc.findHamiltonianCircuit(combinedGraph,distanceMatrix);

        System.out.println("\nPerforming 2 opt Tactical optimization...");
        TacticalTwoOpt to=new TacticalTwoOpt();

        List<Integer> twoOptList= to.twoOpt(hamiltonianPath, distanceMatrix );


        /*System.out.println("\nPerforming 3 opt Tactical optimization...");
        TacticalThreeOpt threeOpt=new TacticalThreeOpt();
        List<Integer> threeOptList= threeOpt.optimizeThreeOpt(hamiltonianPath,distanceMatrix );*/

        System.out.println("\nPerforming Random Swap Tactical optimization...");
        TacticalRandomSwap trs=new TacticalRandomSwap();
        List<Integer> randomSwapList= trs.optimizeRandomSwap(hamiltonianPath,distanceMatrix, 100000 );

        //System.out.println("check eqaulity"+twoOptList.equals(randomSwapList));

        System.out.println("\nPerforming Simulated Annealing for Strategic Optimization   ...");
        StrategicSimulatedAnnealing ssa=new StrategicSimulatedAnnealing();
        List<Integer> SimulatedAnnealingList= ssa.optimizeSimulatedAnnealing(hamiltonianPath,distanceMatrix );

        System.out.println("\nPerforming Ant colony Strategic Optimization...");
        StrategicAntColonyOpt ac=new StrategicAntColonyOpt();
        List<Integer> AntColonyList= ac.optimizeAntColonyOptimization(hamiltonianPath,distanceMatrix );

        System.out.println("\nThe end...");


    }
}