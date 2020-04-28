package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "Test List 1", false);
        TrelloList trelloList2 = new TrelloList("2", "Test List 2", true);
        TrelloList trelloList3 = new TrelloList("3", "Test List 3", false);
        TrelloList trelloList4 = new TrelloList("4", "Test List 4", true);
        TrelloList trelloList5 = new TrelloList("5", "Test List 5", false);
        TrelloList trelloList6 = new TrelloList("6", "Test List 6", false);
        List<TrelloList> list1 = Arrays.asList(trelloList1, trelloList2, trelloList3);
        List<TrelloList> list2 = Arrays.asList(trelloList4, trelloList5, trelloList6);
        TrelloBoard trelloBoard1 = new TrelloBoard("TestBoard1", "BoardId1", list1);
        TrelloBoard trelloBoard2 = new TrelloBoard("TestBoard2", "BoardId2", list2);

        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard1, trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(2, trelloBoardDtoList.size());
        assertEquals("BoardId1", trelloBoardDtoList.get(0).getId());
        assertEquals("BoardId2", trelloBoardDtoList.get(1).getId());
        assertEquals("1", trelloBoardDtoList.get(0).getLists().get(0).getId());
        assertEquals("2", trelloBoardDtoList.get(0).getLists().get(1).getId());
        assertEquals("3", trelloBoardDtoList.get(0).getLists().get(2).getId());
        assertEquals("4", trelloBoardDtoList.get(1).getLists().get(0).getId());
        assertEquals("5", trelloBoardDtoList.get(1).getLists().get(1).getId());
        assertEquals("6", trelloBoardDtoList.get(1).getLists().get(2).getId());
        assertEquals("Test List 1", trelloBoardDtoList.get(0).getLists().get(0).getName());
        assertEquals("Test List 2", trelloBoardDtoList.get(0).getLists().get(1).getName());
        assertEquals("Test List 3", trelloBoardDtoList.get(0).getLists().get(2).getName());
        assertEquals("Test List 4", trelloBoardDtoList.get(1).getLists().get(0).getName());
        assertEquals("Test List 5", trelloBoardDtoList.get(1).getLists().get(1).getName());
        assertEquals("Test List 6", trelloBoardDtoList.get(1).getLists().get(2).getName());
        assertFalse(trelloBoardDtoList.get(0).getLists().get(0).isClosed());
        assertTrue(trelloBoardDtoList.get(0).getLists().get(1).isClosed());
        assertFalse(trelloBoardDtoList.get(0).getLists().get(2).isClosed());
        assertTrue(trelloBoardDtoList.get(1).getLists().get(0).isClosed());
        assertFalse(trelloBoardDtoList.get(1).getLists().get(1).isClosed());
        assertFalse(trelloBoardDtoList.get(1).getLists().get(2).isClosed());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "Test List 1", false);
        TrelloList trelloList2 = new TrelloList("2", "Test List 2", true);
        TrelloList trelloList3 = new TrelloList("3", "Test List 3", false);
        TrelloList trelloList4 = new TrelloList("4", "Test List 4", true);
        List<TrelloList> list = Arrays.asList(trelloList1, trelloList2, trelloList3, trelloList4);
        //When
        List<TrelloListDto> TrelloListDtos = trelloMapper.mapToListDto(list);
        //Then

        assertEquals("1", TrelloListDtos.get(0).getId());
        assertEquals("2", TrelloListDtos.get(1).getId());
        assertEquals("3", TrelloListDtos.get(2).getId());
        assertEquals("4", TrelloListDtos.get(3).getId());
        assertEquals("Test List 1", TrelloListDtos.get(0).getName());
        assertEquals("Test List 2", TrelloListDtos.get(1).getName());
        assertEquals("Test List 3", TrelloListDtos.get(2).getName());
        assertEquals("Test List 4", TrelloListDtos.get(3).getName());
        assertFalse(TrelloListDtos.get(0).isClosed());
        assertTrue(TrelloListDtos.get(1).isClosed());
        assertFalse(TrelloListDtos.get(2).isClosed());
        assertTrue(TrelloListDtos.get(3).isClosed());
    }

    @Test
    public void mapToBoardsTest() {
        //Given
        TrelloListDto trelloList1 = new TrelloListDto("1", "Test List 1", false);
        TrelloListDto trelloList2 = new TrelloListDto("2", "Test List 2", true);
        TrelloListDto trelloList3 = new TrelloListDto("3", "Test List 3", false);
        TrelloListDto trelloList4 = new TrelloListDto("4", "Test List 4", true);
        TrelloListDto trelloList5 = new TrelloListDto("5", "Test List 5", false);
        TrelloListDto trelloList6 = new TrelloListDto("6", "Test List 6", false);
        List<TrelloListDto> list1 = Arrays.asList(trelloList1, trelloList2, trelloList3);
        List<TrelloListDto> list2 = Arrays.asList(trelloList4, trelloList5, trelloList6);
        TrelloBoardDto trelloBoard1 = new TrelloBoardDto("TestBoard1", "BoardId1", list1);
        TrelloBoardDto trelloBoard2 = new TrelloBoardDto("TestBoard2", "BoardId2", list2);

        List<TrelloBoardDto> trelloBoardDtoList = Arrays.asList(trelloBoard1, trelloBoard2);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(2, trelloBoardList.size());
        assertEquals("BoardId1", trelloBoardList.get(0).getId());
        assertEquals("BoardId2", trelloBoardList.get(1).getId());
        assertEquals("1", trelloBoardList.get(0).getLists().get(0).getId());
        assertEquals("2", trelloBoardList.get(0).getLists().get(1).getId());
        assertEquals("3", trelloBoardList.get(0).getLists().get(2).getId());
        assertEquals("4", trelloBoardList.get(1).getLists().get(0).getId());
        assertEquals("5", trelloBoardList.get(1).getLists().get(1).getId());
        assertEquals("6", trelloBoardList.get(1).getLists().get(2).getId());
        assertEquals("Test List 1", trelloBoardList.get(0).getLists().get(0).getName());
        assertEquals("Test List 2", trelloBoardList.get(0).getLists().get(1).getName());
        assertEquals("Test List 3", trelloBoardList.get(0).getLists().get(2).getName());
        assertEquals("Test List 4", trelloBoardList.get(1).getLists().get(0).getName());
        assertEquals("Test List 5", trelloBoardList.get(1).getLists().get(1).getName());
        assertEquals("Test List 6", trelloBoardList.get(1).getLists().get(2).getName());
        assertFalse(trelloBoardList.get(0).getLists().get(0).isClosed());
        assertTrue(trelloBoardList.get(0).getLists().get(1).isClosed());
        assertFalse(trelloBoardList.get(0).getLists().get(2).isClosed());
        assertTrue(trelloBoardList.get(1).getLists().get(0).isClosed());
        assertFalse(trelloBoardList.get(1).getLists().get(1).isClosed());
        assertFalse(trelloBoardList.get(1).getLists().get(2).isClosed());
    }

    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto trelloList1 = new TrelloListDto("1", "Test List 1", false);
        TrelloListDto trelloList2 = new TrelloListDto("2", "Test List 2", true);
        TrelloListDto trelloList3 = new TrelloListDto("3", "Test List 3", false);
        TrelloListDto trelloList4 = new TrelloListDto("4", "Test List 4", true);
        List<TrelloListDto> list = Arrays.asList(trelloList1, trelloList2, trelloList3, trelloList4);
        //When
        List<TrelloList> TrelloLists = trelloMapper.mapToList(list);

        //Then
        assertEquals("1", TrelloLists.get(0).getId());
        assertEquals("2", TrelloLists.get(1).getId());
        assertEquals("3", TrelloLists.get(2).getId());
        assertEquals("4", TrelloLists.get(3).getId());
        assertEquals("Test List 1", TrelloLists.get(0).getName());
        assertEquals("Test List 2", TrelloLists.get(1).getName());
        assertEquals("Test List 3", TrelloLists.get(2).getName());
        assertEquals("Test List 4", TrelloLists.get(3).getName());
        assertFalse(TrelloLists.get(0).isClosed());
        assertTrue(TrelloLists.get(1).isClosed());
        assertFalse(TrelloLists.get(2).isClosed());
        assertTrue(TrelloLists.get(3).isClosed());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard1 = new TrelloCard("Card1", "Card1 Test", "1", "1");
        TrelloCard trelloCard2 = new TrelloCard("Card2", "Card2 Test", "2", "1");
        //When
        TrelloCardDto trelloCardDto1 = trelloMapper.mapToCardDto(trelloCard1);
        TrelloCardDto trelloCardDto2 = trelloMapper.mapToCardDto(trelloCard2);
        //Then
        assertEquals("Card1", trelloCardDto1.getName());
        assertEquals("Card1 Test", trelloCardDto1.getDescription());
        assertEquals("1", trelloCardDto1.getPos());
        assertEquals("1", trelloCardDto1.getListId());
        assertEquals("Card2", trelloCardDto2.getName());
        assertEquals("Card2 Test", trelloCardDto2.getDescription());
        assertEquals("2", trelloCardDto2.getPos());
        assertEquals("1", trelloCardDto2.getListId());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto1 = new TrelloCardDto("Card1", "Card1 Test", "1", "1");
        TrelloCardDto trelloCardDto2 = new TrelloCardDto("Card2", "Card2 Test", "2", "1");
        //When
        TrelloCard trelloCard1 = trelloMapper.mapToCard(trelloCardDto1);
        TrelloCard trelloCard2 = trelloMapper.mapToCard(trelloCardDto2);
        //Then
        assertEquals("Card1", trelloCard1.getName());
        assertEquals("Card1 Test", trelloCard1.getDescription());
        assertEquals("1", trelloCard1.getPos());
        assertEquals("1", trelloCard1.getListId());
        assertEquals("Card2", trelloCard2.getName());
        assertEquals("Card2 Test", trelloCard2.getDescription());
        assertEquals("2", trelloCard2.getPos());
        assertEquals("1", trelloCard2.getListId());
    }
}
