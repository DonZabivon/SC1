package service;

import dao.DAOError;
import dao.OperationDAO;
import model.Operation;

import java.util.List;

public class OperationService {
    private final OperationDAO operationDAO;


    public OperationService(OperationDAO operationDAO) {
        this.operationDAO = operationDAO;
    }

    public List<Operation> getAll() {
        return operationDAO.getAll();
    }

    public Operation findById(long id) {
        return operationDAO.findById(id);
    }

    public Operation create(Operation object) throws DAOError {
        return operationDAO.create(object);
    }

    public void update(Operation object) throws DAOError {
        operationDAO.update(object);
    }

    public void delete(long id) throws DAOError {
        operationDAO.delete(id);
    }
}
