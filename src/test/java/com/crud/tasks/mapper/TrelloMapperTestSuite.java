package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TrelloMapperTestSuite {

    @Test
    public void testMapToBoards(){
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        TrelloBoardDto testTrelloBoardDto = new TrelloBoardDto("test1","id:1", trelloListDtoList);
        TrelloListDto testTrelloListDto = new TrelloListDto("1", "testTrelloList", false);
        trelloBoardDtoList.add(testTrelloBoardDto);
        trelloListDtoList.add(testTrelloListDto);
        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(1, trelloBoardList.size());
        assertEquals("test1", trelloBoardList.get(0).getName());
        assertEquals("id:1", trelloBoardList.get(0).getId());
        assertEquals("1", trelloBoardList.get(0).getLists().get(0).getId());
    }

    @Test
    public void testMapToBoardsDto(){
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        List<TrelloList> trelloListList = new ArrayList<>();
        TrelloBoard testTrelloBoard = new TrelloBoard("test1","id:1", trelloListList);
        TrelloList testTrelloList = new TrelloList("1", "testTrelloList", false);
        trelloBoardList.add(testTrelloBoard);
        trelloListList.add(testTrelloList);
        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertEquals(1, trelloBoardDtoList.size());
        assertEquals("test1", trelloBoardDtoList.get(0).getName());
        assertEquals("id:1", trelloBoardDtoList.get(0).getId());
        assertEquals("1", trelloBoardDtoList.get(0).getLists().get(0).getId());

    }

    @Test
    public void testMapToList(){
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        TrelloListDto testTrelloListDto = new TrelloListDto("1", "testTrelloList", false);
        trelloListDtoList.add(testTrelloListDto);
        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);
        //Then
        assertEquals(1, trelloLists.size());
        assertEquals("testTrelloList", trelloLists.get(0).getName());
        assertEquals("1", trelloLists.get(0).getId());
        assertFalse(trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToListDto(){
        //Given
        List<TrelloList> trelloListList = new ArrayList<>();
        TrelloList testTrelloList = new TrelloList("1", "testTrelloList", false);
        trelloListList.add(testTrelloList);
        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloListList);
        //Then
        assertEquals(1, trelloListDtoList.size());
        assertEquals("testTrelloList", trelloListDtoList.get(0).getName());
        assertEquals("1", trelloListDtoList.get(0).getId());
        assertFalse(trelloListDtoList.get(0).isClosed());

    }

    @Test
    public void testMapToCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("trello card name", "trello card description", "pos", "trello card list id");
        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("trello card list id", trelloCard.getListId());
        assertEquals("trello card name", trelloCard.getName());
        assertEquals("trello card description", trelloCard.getDescription());
        assertEquals("pos", trelloCard.getPos());

    }

    @Test
    public void testMapToCardDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard("trello card name", "trello card description", "pos", "trello card list id");
        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("trello card list id", trelloCardDto.getListId());
        assertEquals("trello card name", trelloCardDto.getName());
        assertEquals("trello card description", trelloCardDto.getDescription());
        assertEquals("pos", trelloCardDto.getPos());

    }
}
