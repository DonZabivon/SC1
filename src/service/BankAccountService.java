package service;

import dao.BankAccountDAO;
import dao.DAOError;
import model.BankAccount;

import java.util.List;

public class BankAccountService {
    private final BankAccountDAO bankAccountDAO;

    public BankAccountService(BankAccountDAO dao) {
        this.bankAccountDAO = dao;
    }

    public List<BankAccount> getAll() {
        return bankAccountDAO.getAll();
    }

    public BankAccount findById(long id) {
        return bankAccountDAO.findById(id);
    }

    public BankAccount create(BankAccount object) throws DAOError {
        return bankAccountDAO.create(object);
    }

    public void update(BankAccount object) throws DAOError {
        bankAccountDAO.update(object);
    }

    public void delete(long id) throws DAOError {
        bankAccountDAO.delete(id);
    }
}
