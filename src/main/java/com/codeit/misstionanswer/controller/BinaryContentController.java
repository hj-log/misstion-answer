package com.codeit.misstionanswer.controller;

import com.codeit.misstionanswer.controller.api.*;
import com.codeit.misstionanswer.entity.*;
import com.codeit.misstionanswer.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/api/binaryContent")
@Controller
@ResponseBody
public class BinaryContentController implements BinaryContentApi {
    private final BinaryContentService binaryContentService;

    @RequestMapping(path = "find")
    public ResponseEntity<BinaryContent> find(@RequestParam("binaryContentId") UUID binaryContentId) {
        BinaryContent binaryContent = binaryContentService.find(binaryContentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(binaryContent);
    }

    @RequestMapping(path = "findAllByIdIn")
    public ResponseEntity<List<BinaryContent>> findAllByIdIn (@RequestParam("binaryContentIds")
                                                                  List<UUID> binaryContentIds) {
        List<BinaryContent> binaryContents = binaryContentService.findAllByIdIn(binaryContentIds);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(binaryContents);
    }
}
