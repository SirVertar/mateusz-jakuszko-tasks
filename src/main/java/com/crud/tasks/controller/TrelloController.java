package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        final List<TrelloBoardDto> trelloBoardsOptional = trelloBoards.stream()
                .map(Optional::ofNullable)
                .map(e -> e.orElse(new TrelloBoardDto("empty", "empty")))
                .collect(Collectors.toList());

        trelloBoards.stream()
                .filter(e -> e.getId() != null)
                .filter(e -> e.getName() != null)
                .filter(e ->e.getName().contains("Kodilla"))
                .forEach(e -> System.out.println(e.getId() + " " + e.getName()));

        //trelloBoards.forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));
        return trelloBoardsOptional;
    }
}
