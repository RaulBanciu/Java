package Controller;

import Model.MyException;
import Model.Statements.IStmt;
import Model.Utils.*;
import Repository.MyIRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MyController implements MyIController {
    MyIRepository repo;
    boolean printFlag = true;
    ExecutorService executor;

    public MyController(MyIRepository repoP) {
        this.repo = repoP;
    }

    public MyIRepository getRepo() {
        return this.repo;
    }

    public void setPrintFlag() {
        this.printFlag = true;
    }

    public void unsetPrintFlag() {
        this.printFlag = false;
    }

    public boolean getPrintFlag() {
        return this.printFlag;
    }


    public List<Pair<Integer,Integer>> getHeapTablePairList(){
        MyIHeap<Integer,Integer> heapTable = repo.getPrgList().get(0).getHeap();
        return heapTable.keySet().stream().map(p -> new Pair<>(p,heapTable.getVal(p))).collect(Collectors.toList());
    }

    public List<Pair<String, Integer>> getSymTablePairList(int index) {
        MyIDictionary<String, Integer> symTable = repo.getPrgList().get(index).getSymTable();
        return symTable.keySet().stream().map(p -> new Pair<>(p, symTable.get(p))).collect(Collectors.toList());
    }

    public void executeOneStep()
    {
        executor = Executors.newFixedThreadPool(8);
        removeCompletedPrg(repo.getPrgList());
        List<PrgState> programStates = repo.getPrgList();
        if(programStates.size() > 0)
        {
            try {
                oneStepForAllPrg(repo.getPrgList());
            } catch (InterruptedException e) {
                System.out.println();
            }
            programStates.forEach(e -> {
                    repo.logPrgStateExec(e);
            });
            removeCompletedPrg(repo.getPrgList());
            executor.shutdownNow();
        }
    }


    public List<Pair<Integer,String>> getFileTablePairList() {
        MyIFileTable fileTable = repo.getPrgList().get(0).getFileTable();
        return fileTable.keySet().stream().map(p -> new Pair<>(p,fileTable.get(p).getLeft())).collect(Collectors.toList());
    }

    void fileClose(MyIFileTable fileTable) {
        fileTable.getContent().getValues().stream()
                .forEach(entry -> {try{
                    entry.getRight().close();} catch(IOException e) {}});
    }

    Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                      Map<Integer,Integer> heap){
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}

    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException{

        //prgList.forEach(prg ->repo.logPrgStateExec(prg));

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());


        List<PrgState> newPrgList;
        newPrgList= executor.invokeAll(callList).stream()
                    . map(future -> { try { return future.get();}
                    catch(Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                    }}).filter(p->p!=null).collect(Collectors.toList());

        prgList.addAll(newPrgList);
        prgList.forEach(prg->repo.logPrgStateExec(prg));
        repo.setPrgList(prgList);

    }

    public void allStep() {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());

        while(prgList.size() > 0){try{

                oneStepForAllPrg(prgList);}
                catch(InterruptedException e){System.out.println(e.getMessage());}

            prgList = removeCompletedPrg(repo.getPrgList());
        }

        executor.shutdownNow();
        repo.setPrgList(prgList);
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }
}
