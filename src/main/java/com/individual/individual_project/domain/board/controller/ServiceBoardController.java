package com.individual.individual_project.domain.board.controller;

import com.individual.individual_project.SessionConst;
import com.individual.individual_project.domain.board.Category;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.dto.ServiceBoardResponseDto;
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

        log.info("categoryList: {}", categoryList);

        return ApiResponse.success(categoryList ,ResponseCode.CATEGORY_READ_SUCCESS);
    }

    // 모집 상태
    @GetMapping("/status/recruitment")
    public ApiResponse<List<Status>> getStatusRecruitment() {

        List<Status> statService = statusService.findByStatusTypeId(1L);

        log.info("statService: {}", statService);

        return ApiResponse.success(statService ,ResponseCode.STATUS_READ_SUCCESS);
    }

    // 서비스 상태
    @GetMapping("/status/service")
    public ApiResponse<List<Status>> getStatusService() {

        List<Status> statService = statusService.findByStatusTypeId(2L);

        log.info("statService: {}", statService);

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

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);


        MultipartFile thumbnailToSave = (thumbnail != null && !thumbnail.isEmpty()) ? thumbnail : null;

        ServiceBoard saveBoard = serviceBoardService.createServiceBoard(title, category, content, recruitCount, serviceTime, deadline, serviceDate, thumbnailToSave, user.getId());

        log.info("saveBoard : {}" ,saveBoard);

        return ApiResponse.success(saveBoard ,ResponseCode.BORD_WRITE_SUCCESS);
    }

    // 게시글 조회
    @GetMapping("/service/boards")
    public ApiResponse<List<ServiceBoardResponseDto>> getServiceBoard(
            @RequestParam(required = false) String serviceStatId,
            @RequestParam(required = false) String recruitStatId,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String serviceBoardSearchName
    ) {

        log.info("serviceStatId : {}", serviceStatId);
        log.info("recruitStatId : {}", recruitStatId);
        log.info("categoryId : {}", categoryId);
        log.info("serviceBoardSearchName : {}", serviceBoardSearchName);

        List<ServiceBoardResponseDto> all = serviceBoardService.findAll(serviceStatId, recruitStatId, categoryId, serviceBoardSearchName);
        return ApiResponse.success(all, ResponseCode.BORD_READ_SUCCESS);
    }


}
