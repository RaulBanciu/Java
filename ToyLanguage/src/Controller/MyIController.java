package Controller;

import Model.MyException;
import Model.Utils.PrgState;
import Repository.MyIRepository;

import java.util.List;

public interface MyIController {
    void setPrintFlag();

    void unsetPrintFlag();

    boolean getPrintFlag();

    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException ;

    void allStep();


    MyIRepository getRepo();
}