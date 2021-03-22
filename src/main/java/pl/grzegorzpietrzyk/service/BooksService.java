package pl.grzegorzpietrzyk.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzegorzpietrzyk.model.BooksEntity;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor

public class BooksService {

    private static final Logger log = LoggerFactory.getLogger(BooksService.class);

     static Session session;
     static SessionFactory sessionFactory;

    private static SessionFactory buildSession() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        return cfg.buildSessionFactory();
    }

    public static void createBook(String data) {
        BooksEntity book = new BooksEntity();
        String[] dataSplit = data.split(";");

        try {
            session = buildSession().openSession();
            session.beginTransaction();

            book.setTitle(dataSplit[0]);
            book.setAuthor(dataSplit[1]);
            book.setPublishername(dataSplit[2]);

            session.save(book);

            session.getTransaction().commit();
            log.info("Successfully created books in the database!");
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                log.info("Transaction Is Being Rolled Back");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static List<BooksEntity> displayBooks() {
        List<BooksEntity> books = new ArrayList<>();

        try {
            session = buildSession().openSession();
            session.beginTransaction();

            books = session.createQuery("from BooksEntity").list();
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                log.info("Transaction Is Being Rolled Back");
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return books;
    }

    public static BooksEntity updateBook(int id, String data) {
        String[] splitData = data.split(";");
        BooksEntity book = new BooksEntity();

        try {
            session = buildSession().openSession();
            session.beginTransaction();

            book = session.get(BooksEntity.class, id);
            book.setTitle(splitData[0]);
            book.setAuthor(splitData[1]);
            book.setPublishername(splitData[2]);

           // session.update(book);

            session.getTransaction().commit();
            log.info("Student with id = " + id + " is successfully update");
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                log.info("Transaction Is Being Rolled Back");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session.getTransaction() != null) {
                session.close();
            }
        }
        return book;
    }

    public static void deleteBookByID(int id) {
        try {
            session = buildSession().openSession();
            session.beginTransaction();

            BooksEntity book = findBookByID(id);
            session.delete(book);
            session.getTransaction().commit();

            log.info("Book with id = " + id + " is successfully deleted");
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                log.info("Transaction Is Being Rolled Back");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session.getTransaction() != null) {
                session.close();
            }
        }
    }

    public static BooksEntity findBookByID(int id) {
        BooksEntity book = new BooksEntity();
        try {
            session = buildSession().openSession();
            session.beginTransaction();

            book = session.load(BooksEntity.class, id);
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                log.info("Transaction Is Being Rolled Back");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
        return book;
    }
}
