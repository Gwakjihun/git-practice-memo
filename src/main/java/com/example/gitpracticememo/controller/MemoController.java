package com.example.gitpracticememo.controller;

import com.example.gitpracticememo.dto.MemoRequestDto;
import com.example.gitpracticememo.dto.MemoResponseDto;
import com.example.gitpracticememo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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


    //READ
    @GetMapping
    public List<MemoResponseDto> readAllMemos() {
        List<MemoResponseDto> responseList=new ArrayList<>();

        //HashMap<Memo>->List<MemoResponseDto>
        for(Memo memo:memoList.values()){
            MemoResponseDto responseDto=new MemoResponseDto(memo);
            responseList.add(responseDto);
        }
        return responseList;
    }

    //READ 단건조회
    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {
        Memo memo=  memoList.get(id);

        if(memo==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }
}
