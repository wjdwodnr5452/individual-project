package com.individual.individual_project.domain.board.controller;

import com.individual.individual_project.SessionConst;
import com.individual.individual_project.domain.board.Category;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.service.CategoryService;
import com.individual.individual_project.domain.board.service.ServiceBoardService;
import com.individual.individual_project.domain.board.service.StatusService;
import com.individual.individual_project.domain.response.ApiResponse;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServiceBoardController {

    private final CategoryService categoryService;
    private final StatusService statusService;
    private final ServiceBoardService serviceBoardService;

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


    // 글등록
/*    @PostMapping("/service/boards")
    public ApiResponse<ServiceBoard> createServiceBoard(@Validated @RequestBody ServiceBoard serviceBoard) {

        log.info("serviceBoard: {}", serviceBoard);

        return null;
    }*/

    // 글등록
    @PostMapping("/service/boards")
    public ApiResponse<ServiceBoard> createServiceBoard(
            @RequestParam String title,
            @RequestParam String category,
            @RequestParam String content,
            @RequestParam String recruitCount,
            @RequestParam String serviceTime,
            @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestParam String deadline,
            @RequestParam String serviceDate,
            HttpServletRequest request) {

        log.info("title: {}", title);
        log.info("category: {}", category);
        log.info("content: {}", content);
        log.info("recruitCount: {}", recruitCount);
        log.info("serviceTime: {}", serviceTime);
        log.info("deadline: {}", deadline);
        log.info("serviceDate: {}", serviceDate);
        log.info("thumbnail: {}", thumbnail);

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);

        serviceBoardService.createServiceBoard(title, category, content, recruitCount, serviceTime, deadline, serviceDate, thumbnail, user.getId());

        return null;
    }


}
