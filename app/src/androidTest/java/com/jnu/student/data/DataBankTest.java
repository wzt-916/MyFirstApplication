package com.jnu.student.data;

import androidx.test.platform.app.InstrumentationRegistry;

import com.jnu.student.R;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
public class DataBankTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveAndLoadBooks() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("离散", R.drawable.book_1));
        books.add(new Book("安卓", R.drawable.book_2));
        books.add(new Book("涛哥", R.drawable.book_no_name));
        DataBank dataBank = new DataBank();
        dataBank.SaveBooks(InstrumentationRegistry.getInstrumentation().getTargetContext(),books);

        ArrayList<Book> booksLoad = dataBank.LoadBook(InstrumentationRegistry.getInstrumentation().getTargetContext());
        Assert.assertNotSame(books,booksLoad);
        Assert.assertEquals(books.size(),booksLoad.size());
        for(int i = 0; i < books.size(); ++i)
        {
            Assert.assertNotSame(books.get(i),booksLoad.get(i));
            Assert.assertEquals(books.get(i).getTitle(),booksLoad.get(i).getTitle());
            Assert.assertEquals(books.get(i).getImageResourceId(),booksLoad.get(i).getImageResourceId());
        }
    }

}