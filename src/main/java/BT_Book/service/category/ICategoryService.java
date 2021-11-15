package BT_Book.service.category;

import BT_Book.model.Category;
import BT_Book.service.IService;

import java.util.List;

public interface ICategoryService extends IService<Category> {
    List<Category> checkCategory(int id);
}
