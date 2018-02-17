package Repository;

import Model.Utils.MyIList;
import Model.Utils.PrgState;

import java.io.IOException;
import java.util.List;

public interface MyIRepository {
    //PrgState getCrtPrg();

    void addPrgState(PrgState var1);
    List<PrgState> getPrgList();
    void setPrgList(List prgList);

    public PrgState getProgramStateWithId(int currentId);

    void setLogFilePath(String var1);

    void logPrgStateExec(PrgState prog);
}