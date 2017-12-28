package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {
    @Test
    public void testValidateTrelloBoardsWithTestInName() {
        //Given
        TrelloValidator trelloValidator = new TrelloValidator();
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test_list", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));
        //When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        Assert.assertNotNull(filteredBoards);
        Assert.assertEquals(0, filteredBoards.size());
    }

    @Test
    public void testValidateTrelloBoards() {
        //Given
        TrelloValidator trelloValidator = new TrelloValidator();
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "true_list", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "true", trelloLists));
        //When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        Assert.assertNotNull(filteredBoards);
        Assert.assertEquals(1, filteredBoards.size());
    }
}
