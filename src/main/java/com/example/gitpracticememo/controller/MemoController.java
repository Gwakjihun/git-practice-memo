package com.example.gitpracticememo.controller;

import com.example.gitpracticememo.dto.MemoRequestDto;
import com.example.gitpracticememo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController // @Controller + @ResponseBody
@RequestMapping("/memos") // Prefix
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping
    public ResponseEntity<Memo> createMemo(@RequestBody MemoRequestDto requestDto) {
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;
        Memo memo = new Memo(memoId, requestDto);

        return new ResponseEntity<>(memo , HttpStatus.OK);
    }
}
