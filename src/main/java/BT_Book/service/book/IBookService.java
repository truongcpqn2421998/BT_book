package BT_Book.service.book;

import BT_Book.model.Book;
import BT_Book.service.IService;

public interface IBookService extends IService<Book> {
    void save(Book book ,int[] categories);
}
