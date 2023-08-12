package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US03_StepDefs {

    BookPage bookPage = new BookPage();
    List<String> actualAllDropdownOptions;

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String module) {
        bookPage.navigateModule(module);
    }

    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        actualAllDropdownOptions = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        System.out.println(actualAllDropdownOptions);

        actualAllDropdownOptions.remove(0);
        System.out.println(actualAllDropdownOptions);
    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        String query = "select name from book_categories";

        DB_Util.runQuery(query);
        List<String> expectedDropdownOptions = DB_Util.getColumnDataAsList(1);

        Assert.assertEquals(expectedDropdownOptions, actualAllDropdownOptions);
    }

}
