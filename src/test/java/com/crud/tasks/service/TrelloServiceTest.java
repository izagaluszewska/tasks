package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloService.fetchTrelloBoards();
        //Then
        Assert.assertNotNull(fetchedTrelloBoards);
        Assert.assertEquals(1, fetchedTrelloBoards.size());
    }

    @Test
    public void testFetchEmptyTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloService.fetchTrelloBoards();
        //Then
        Assert.assertNotNull(fetchedTrelloBoards);
        Assert.assertEquals(0, fetchedTrelloBoards.size());
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("1", "my_task", "top", "11");
        CreatedTrelloCardDto createdCardDto = new CreatedTrelloCardDto("1", "1", "test");
        when(trelloService.createTrelloCard(cardDto)).thenReturn(createdCardDto);
        //When
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(cardDto);
        //Then
        Assert.assertEquals(createdCardDto, newCard);
        Assert.assertNotNull(newCard);
        Assert.assertEquals("1", newCard.getId());
        Assert.assertEquals("1", newCard.getName());
        Assert.assertEquals("test", newCard.getShortUrl());
    }
}
