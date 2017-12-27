package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards() {
        //Given
        List<TrelloListDto> listA = new ArrayList<>();
        listA.add(new TrelloListDto("1", "one", true));
        listA.add(new TrelloListDto("2", "two", false));

        List<TrelloListDto> listB = new ArrayList<>();
        listB.add(new TrelloListDto("3", "three", true));
        listB.add(new TrelloListDto("4", "four", true));

        List<TrelloListDto> listC = new ArrayList<>();
        listC.add(new TrelloListDto("5", "five", false));

        List<TrelloBoardDto> boards = new ArrayList<>();
        boards.add(new TrelloBoardDto("1", "One", listA));
        boards.add(new TrelloBoardDto("2", "Two", listB));
        boards.add(new TrelloBoardDto("3", "Three", listC));

        //When
        List<TrelloBoard> mappedTrelloBoardsDto = trelloMapper.mapToBoards(boards);

        //Then
        Assert.assertNotNull(mappedTrelloBoardsDto);
        Assert.assertEquals(3, mappedTrelloBoardsDto.size());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloList> listA = new ArrayList<>();
        listA.add(new TrelloList("1", "one", true));
        listA.add(new TrelloList("2", "two", false));

        List<TrelloList> listB = new ArrayList<>();
        listB.add(new TrelloList("3", "three", true));
        listB.add(new TrelloList("4", "four", true));

        List<TrelloList> listC = new ArrayList<>();
        listC.add(new TrelloList("5", "five", false));

        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(new TrelloBoard("1", "One", listA));
        boards.add(new TrelloBoard("2", "Two", listB));
        boards.add(new TrelloBoard("3", "Three", listC));

        //When
        List<TrelloBoardDto> mappedTrelloBoards = trelloMapper.mapToBoardsDto(boards);

        //Then
        Assert.assertNotNull(mappedTrelloBoards);
        Assert.assertEquals(3, mappedTrelloBoards.size());
    }

    @Test
    public void shouldMapToList() {
        //Given
        List<TrelloListDto> list = new ArrayList<>();
        list.add(new TrelloListDto("1", "one", true));
        list.add(new TrelloListDto("2", "two", false));

        //When
        List<TrelloList> mappedTrelloListsDto = trelloMapper.mapToList(list);

        //Then
        Assert.assertNotNull(mappedTrelloListsDto);
        Assert.assertEquals(2, mappedTrelloListsDto.size());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("1", "one", true));
        list.add(new TrelloList("2", "two", false));

        //When
        List<TrelloListDto> mappedTrelloLists = trelloMapper.mapToListDto(list);

        //Then
        Assert.assertNotNull(mappedTrelloLists);
        Assert.assertEquals(2, mappedTrelloLists.size());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto card = new TrelloCardDto("1", "one", "top", "11");

        //When
        TrelloCard mappedTrelloCard = trelloMapper.mapToCard(card);

        //Then
        Assert.assertNotNull(mappedTrelloCard);
        Assert.assertEquals("1", mappedTrelloCard.getName());
        Assert.assertEquals("one", mappedTrelloCard.getDescription());
        Assert.assertEquals("top", mappedTrelloCard.getPos());
        Assert.assertEquals("11", mappedTrelloCard.getListId());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("1", "one", "top", "11");

        //When
        TrelloCardDto mappedTrelloCardDto = trelloMapper.mapToCardDto(card);

        //Then
        Assert.assertNotNull(mappedTrelloCardDto);
        Assert.assertEquals("1", mappedTrelloCardDto.getName());
        Assert.assertEquals("one", mappedTrelloCardDto.getDescription());
        Assert.assertEquals("top", mappedTrelloCardDto.getPos());
        Assert.assertEquals("11", mappedTrelloCardDto.getListId());
    }
}
