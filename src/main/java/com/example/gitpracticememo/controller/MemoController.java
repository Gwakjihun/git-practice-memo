package com.example.gitpracticememo.controller;

import com.example.gitpracticememo.dto.MemoRequestDto;
import com.example.gitpracticememo.dto.MemoResponseDto;
import com.example.gitpracticememo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // @Controller + @ResponseBody
@RequestMapping("/memos") // Prefix
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto requestDto) {
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;
        Memo memo = new Memo(memoId, requestDto);
        memoList.put(memoId, memo);
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
        return new ResponseEntity<>(memoResponseDto , HttpStatus.OK);
    }


    //READ All
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


    //DELETE 단건삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemoById(@PathVariable Long id) {

        if(memoList.containsKey(id)) {
            memoList.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //DELETE 전체삭제
    @DeleteMapping()
    public ResponseEntity<Void> resetAllMemo() {

        if(memoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        memoList.clear();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
