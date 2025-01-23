package com.individual.individual_project.domain.board.controller;

import com.individual.individual_project.domain.board.Category;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.service.BoardService;
import com.individual.individual_project.domain.board.service.CategoryService;
import com.individual.individual_project.domain.board.service.StatusService;
import com.individual.individual_project.domain.response.ApiResponse;
import com.individual.individual_project.domain.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

   // private final BoardService boardService;
    private final CategoryService categoryService;
    private final StatusService statusService;

    // 카테고리
    @GetMapping("/categorys")
    public ApiResponse<List<Category>> getCategory() {
        List<Category> categoryList = categoryService.findAll();

        return ApiResponse.success(categoryList ,ResponseCode.CATEGORY_READ_SUCCESS);
    }

    // 모집 상태
    @GetMapping("/status/recruitment")
    public ApiResponse<List<Status>> getStatusRecruitment() {

        List<Status> statService = statusService.findByStatusTypeId(1L);

        return ApiResponse.success(statService ,ResponseCode.STATUS_READ_SUCCESS);
    }

    // 서비스 상태
    @GetMapping("/status/service")
    public ApiResponse<List<Status>> getStatusService() {

        List<Status> statService = statusService.findByStatusTypeId(2L);

        return ApiResponse.success(statService ,ResponseCode.STATUS_READ_SUCCESS);
    }



}
