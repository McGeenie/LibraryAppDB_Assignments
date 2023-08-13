package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class US04_StepDefs {

    BookPage bookPage = new BookPage();
    String bookName;
    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String BookTitle) {
        bookName = BookTitle;
        bookPage.bookSearch(BookTitle);
    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        bookPage.editBook(bookName).click();
    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        String query = "select * from books\n" +
                "where name = '"+bookName+"'";
        DB_Util.runQuery(query);

        Map<String, String> mapDBData = DB_Util.getRowMap(1);
        System.out.println(mapDBData);

        String name_DB = mapDBData.get("name");
        String name_UI = bookPage.getBookInfo("name");
        Assert.assertEquals(name_DB, name_UI);
        
        String isbn_DB = mapDBData.get("isbn");
        String isbn_UI = bookPage.getBookInfo("isbn");
        Assert.assertEquals(isbn_DB, isbn_UI);

        String year_DB = mapDBData.get("year");
        String year_UI = bookPage.getBookInfo("year");
        Assert.assertEquals(year_DB, year_UI);

        String author_DB = mapDBData.get("author");
        String author_UI = bookPage.getBookInfo("author");
        Assert.assertEquals(author_DB, author_UI);


    }

}
