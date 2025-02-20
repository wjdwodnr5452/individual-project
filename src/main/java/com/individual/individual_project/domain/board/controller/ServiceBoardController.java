package com.individual.individual_project.domain.board.controller;

import com.individual.individual_project.SessionConst;
import com.individual.individual_project.comm.file.FileUploadService;
import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBoardsDto;
import com.individual.individual_project.domain.board.dto.*;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServiceBoardController {

    private final CategoryService categoryService;
    private final StatusService statusService;
    private final ServiceBoardService serviceBoardService;
    private final FileUploadService fileUploadService;

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
    @PostMapping("/service-boards")
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

        return ApiResponse.success(saveBoard ,ResponseCode.BORD_CREATE_SUCCESS);
    }

    // 게시글 조회
    @GetMapping("/service-boards")
    public ApiResponse<Page<ServiceBoardsDto>> getServiceBoard(
            @RequestParam(required = false) String serviceStatId,
            @RequestParam(required = false) String recruitStatId,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String serviceBoardSearchName,
            Pageable pageable
    ) {

        log.info("serviceStatId : {}", serviceStatId);
        log.info("recruitStatId : {}", recruitStatId);
        log.info("categoryId : {}", categoryId);
        log.info("serviceBoardSearchName : {}", serviceBoardSearchName);
        log.info("pageable : {}", pageable);

        Page<ServiceBoardsDto> all = serviceBoardService.findAll(serviceStatId, recruitStatId, categoryId, serviceBoardSearchName, pageable);
        return ApiResponse.success(all, ResponseCode.BORD_READ_SUCCESS);
    }


    // 이미지 조회
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileUploadService.getFullPath(filename)); // 실제 경로 이미지를 가져옴
    }


    // 글 상세 조회
    @GetMapping("/service-boards/{id}")
    public ApiResponse<ServiceBoardDetailDto> findServiceBoard(@PathVariable String id, HttpServletRequest request) {

        ServiceBoardDetailDto serviceBoardById = serviceBoardService.findServiceBoardById(id, request);

        log.info("serviceBoardById : {} ", serviceBoardById);

        return ApiResponse.success(serviceBoardById, ResponseCode.BORD_READ_SUCCESS);
    }

    // 글 상세 수정 조회
    @GetMapping("/service-boards/{id}/edit")
    public ApiResponse<ServiceBoardDetailEditDto> findServiceBoardEdit(@PathVariable Long id, HttpServletRequest request) {

        ServiceBoardDetailEditDto serviceBoardById = serviceBoardService.findServiceBoardEditById(id, request);

        log.info("serviceBoardById : {} ", serviceBoardById);

        return ApiResponse.success(serviceBoardById, ResponseCode.BORD_READ_SUCCESS);

    }

    // 글 상세 수정
    @PutMapping("/service-boards/{id}/edit")
    public ApiResponse<ServiceBoardDetailEditDto> serviceBoardEdit(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String category,
            @RequestParam String content,
            @RequestParam String recruitCount,
            @RequestParam String serviceTime,
            @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestParam String deadline,
            @RequestParam String serviceDate,
            HttpServletRequest request
    ) {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);

        MultipartFile thumbnailToSave = (thumbnail != null && !thumbnail.isEmpty()) ? thumbnail : null;

        ServiceBoardDetailEditDto serviceBoardDetailEditDto = serviceBoardService.updateServiceBoardEdit(id,title, category, content, recruitCount, serviceTime, deadline, serviceDate, thumbnailToSave, user.getId());

        return ApiResponse.success(serviceBoardDetailEditDto, ResponseCode.BORD_CREATE_SUCCESS);
    }

    // 봉사 시간 부여
    @PostMapping("/service-boards/{id}/assign-time-and-complete")
    public ApiResponse<Status> assigntimeAndComplete(@PathVariable Long id, @RequestBody List<SaveApplicantServiceTimeDto> saveApplicantServiceTimeDtos) {

        Status status = serviceBoardService.saveServiceTimeAndComplete(id, saveApplicantServiceTimeDtos);

        return  ApiResponse.success(status, ResponseCode.BORD_STAT_UPDATE_SUCCESS);
    }


    @GetMapping("/users/{userId}/service-boards")
    public ApiResponse<List<UserWriteServiceBoardDto>> findUserServiceBoards(@PathVariable Long userId, HttpServletRequest request) {

        List<UserWriteServiceBoardDto> serviceBoardByUserId = serviceBoardService.findServiceBoardByUserId(userId, request);


        return ApiResponse.success(serviceBoardByUserId, ResponseCode.BORD_READ_SUCCESS);

    }


}
