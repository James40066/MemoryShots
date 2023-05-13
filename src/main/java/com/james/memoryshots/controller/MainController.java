package com.james.memoryshots.controller;

import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.AlbumQueryParams;
import com.james.memoryshots.dto.AlbumRequest;
import com.james.memoryshots.dto.Member;
import com.james.memoryshots.service.MainService;
import com.james.memoryshots.util.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    @ApiIgnore
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
    @ApiOperation("進行相簿新增")
    public ResponseEntity<?> creat_album(
            @ApiParam(required = true, value = "相簿資料") @RequestBody @Valid AlbumRequest albumRequest,
            @ApiParam(required = true, value = "會員ID") @PathVariable String memberId
    ) throws Exception {
        //http://localhost:8082/creat_album/6
        //{"albumName":"大阪自由行8","albumDesc":"2023/03/02"}

        boolean status = mainService.insert(albumRequest,memberId);

        if(status){
            return ResponseEntity.status(HttpStatus.CREATED).body("新增成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("新增失敗");
        }
    }

    @DeleteMapping("/del_album/{albumId}")
    @ApiOperation("進行相簿刪除")
    public ResponseEntity<?> del_album(
            @ApiParam(required = true, value = "相簿ID") @PathVariable int albumId
    ) throws Exception {
        //http://localhost:8082/del_album/6

        boolean status = mainService.delete(albumId);

        if(status){
            return ResponseEntity.status(HttpStatus.OK).body("刪除成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("刪除失敗");
        }
    }

    @PutMapping("/update_album/{albumId}")
    @ApiOperation("進行相簿更新")
    public ResponseEntity<?> update_album(
            @ApiParam(required = true, value = "相簿資料") @RequestBody @Valid AlbumRequest albumRequest,
            @ApiParam(required = true, value = "相簿ID") @PathVariable int albumId
    ) throws Exception {
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
    @ApiOperation("透過memberId查詢相簿")
    public ResponseEntity<?> find_album(
            @ApiParam(required = true, value = "會員ID") @RequestParam(required = true) String memberId,
            @ApiParam(value = "資料最大比數") @RequestParam(defaultValue = "10") @Max(10) @Min(0) int limit,
            @ApiParam(value = "offset") @RequestParam(defaultValue = "0") @Min(0) int offset,
            @ApiParam(value = "查詢關鍵字") @RequestParam(required = false) String search
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
