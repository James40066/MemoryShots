package com.james.memoryshots.controller;

import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.AlbumQueryParams;
import com.james.memoryshots.dto.AlbumRequest;
import com.james.memoryshots.dto.Member;
import com.james.memoryshots.service.MainService;
import com.james.memoryshots.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@RequestMapping("/MemoryShots_main")
public class MainController {
    @Autowired
    MainService mainService;

    @GetMapping("/")
    public String index(HttpSession session, Model model){
        Member member = (Member)session.getAttribute("member");
        if(member == null){
            return "signin";//重新登入
        }else{
            //首頁
            model.addAttribute("member_id", member.getMemberId());
            model.addAttribute("member_Name", member.getName());
            return "album";//相簿主頁
        }
    }

    @PostMapping("/creat_album/{memberId}")
    public ResponseEntity<?> creat_album(@RequestBody @Valid AlbumRequest albumRequest,@PathVariable String memberId) throws Exception {
        //http://localhost:8082/creat_album/6
        //{"albumName":"大阪自由行8","albumDesc":"2023/03/02"}

        boolean status = mainService.insert(albumRequest,memberId);

        if(status){
            return ResponseEntity.status(HttpStatus.CREATED).body("新增成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("新增失敗");
        }
    }

    @GetMapping("/del_album/{albumId}")
    public ResponseEntity<?> del_album(@PathVariable int albumId) throws Exception {
        //http://localhost:8082/del_album/6

        boolean status = mainService.delete(albumId);

        if(status){
            return ResponseEntity.status(HttpStatus.CREATED).body("刪除成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("刪除失敗");
        }
    }

    @PutMapping("/update_album/{albumId}")
    public ResponseEntity<?> update_album(@RequestBody @Valid AlbumRequest albumRequest,@PathVariable int albumId) throws Exception {
        //http://localhost:8082/update_album/6
        //{"albumName":"大阪自由行8","albumDesc":"2023/03/02"}

        boolean status = mainService.update(albumRequest,albumId);

        if(status){
            return ResponseEntity.status(HttpStatus.OK).body("更新成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("更新失敗");
        }
    }

    @GetMapping("/find_album")
    public ResponseEntity<?> find_album(@RequestParam(required = true) String memberId,
                                        @RequestParam(defaultValue = "10") @Max(10) @Min(0) int limit,
                                        @RequestParam(defaultValue = "0") @Min(0) int offset,
                                        @RequestParam(required = false) String search
                                                                    ) throws Exception {
        //http://localhost:8082/MemoryShots_main/find_album?memberId=6&offset=10
        //http://localhost:8082/MemoryShots_main/find_album?memberId=6&offset=10&search=05/11
        AlbumQueryParams albumQueryParams = new AlbumQueryParams();
        albumQueryParams.setMemberId(memberId);
        albumQueryParams.setLimit(limit);
        albumQueryParams.setOffset(offset);
        albumQueryParams.setSearch(search);
        List<Album> albumList = mainService.findByMemberId(albumQueryParams);

        Integer total = mainService.countAlbumList(albumQueryParams);

        Page<Album> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(albumList);

        if(albumList.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(page);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("查無資料");
        }
    }

}
