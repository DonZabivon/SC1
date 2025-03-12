package service;

import dao.CategoryDAO;
import dao.DAOError;
import model.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO dao) {
        this.categoryDAO = dao;
    }

    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    public Category findById(long id) {
        return categoryDAO.findById(id);
    }

    public Category create(Category object) throws DAOError {
        return categoryDAO.create(object);
    }

    public void update(Category object) throws DAOError {
        categoryDAO.update(object);
    }

    public void delete(long id) throws DAOError {
        categoryDAO.delete(id);
    }
}
