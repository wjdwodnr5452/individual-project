# :blue_book: 프로젝트 내용
다같이는 나눔을 실천하고, 함께 성장하며, 더 나은 세상을 만들어가는 사람들을 위한 공간입니다. 봉사활동을 원하는 누구나 손쉽게 활동에 참여하고, 활동 완료 후에는 공인된 봉사시간을 간편하게 확인할 수 있습니다.
*****

### :books: 목차
1. 기술 스택
2. 페이지 url 정보
3. API url 정보 
4. ERD 설계
5. 아키텍처
6. 프로젝트 주요기능

*****
### :one: 기술 스택
- Java 17
- Spring Boot
- mysql
- AWS
- Git Action
- JPA
- SpringData JPA
- Querydsl
- React
- Kafka
- Redis

*****

### :two: 페이지 url 정보
|url |설명|
|------|---|
|/|메인|
|/login|로그인 |
|/signup|회원가입|
|/boards/write|게시판 작성|
|/boards/:id|게시판 상세|
|/boards/:id/edit|게시판 상세 수정|
|/users/:id|회원정보|

*****


### :three: API url 정보

|메소드|url|설명|
|------|---|---|
|POST|/api/auth/login|로그인|
|POST|/api/auth/logout|로그아웃|
|GET|/api/auth/status|상태 체크|
|POST|/api/users|회원 가입|
|GET|/api/users/{userId}|유저 상세보기|
|GET|/api/service-boards|봉사 게시글 목록 조회|
|POST|/api/service-boards|봉사 게시글 작성|
|GET|/api/service-boards/{serviceBoardId}|봉사 게시글 상세 조회|
|PUT|/api/service-boards/{serviceBoardId}|봉사 게시글 상세 수정|
|POST|/api/service-boards/{serviceBoardId}/assign-time-and-complete|봉사 시간 부여|
|GET|/api/users/{userId}/service-boards|유저가 작성한 봉사게시물 리스트|
|GET|/api/status/service|봉사 상태 조회|
|GET|/api/status/recruitment|봉사 모집 상태 조회|
|GET|/api/categorys|카테고리 조회|
|POST|/api/service-boards/{serviceBoardId}/applicants|봉사 신청|
|GET|/api/service-boards/{serviceBoardId}/applicants|봉사 신청 리스트 조회|
|PATCH|/api/applicants/{id}/status|봉사 신청 상태 변경 (신청, 취소)|
|GET|/api/applicants/{userId}/{serviceBoardId}|봉사 신청 조회|
|GET|/api/users/{userId}/applicants|유저가 봉사신청 한 리스트|
*****

### :four: ERD 설계
- url : https://www.erdcloud.com/d/8Tba5NQqfBJtvEhuD

![image](https://github.com/user-attachments/assets/28795093-6408-42a4-9ea4-40dd9f5eb66a)

*****

### :five: 아키텍처
![아키텍처 drawio](https://github.com/user-attachments/assets/dda46cb0-ef95-4894-9be8-8e0b5c7d2f48)

*****
### :six: 프로젝트 기능

|제목|설명|비고|
|------|------|---|
|회원가입|사용자 회원가입|이메일 중복 확인, 비밀번호 검증 확인|
|로그인|사용자 로그인|회원가입 된 이메일 체크, 비밀번호 체크|
|메인페이지|사용들이 작성한 봉사게시물 리스트 조회|검색 필터 및 페이징|
|게시물작성|사용자 봉사 게시물 작성|봉사게시물에 필요한 제목, 카테고리, 모집인원 등 필수 체크|
|게시물상세|봉사 게시물 상세 내용 조회 하는 페이지 이다. <br>  작성자는 게시물 수정 버튼 표출, 봉사 종료 버튼, 봉사 신청자 조회, 봉사 신청자 봉사 시간 부여가 가능하다. <br> 작성자 이외에 일반 사용자는 봉사 신청/취소가 가능하다.|작성자 : 게시물 수정, 신청자 목록 조회 <br> 일반사용자: 봉사 신청/취소|
|게시물수정|봉사 게시물 수정|봉사게시물에 필요한 제목, 카테고리, 모집인원 등 필수 체크|
|회원정보|회원 정보 조회하는 페이지이다. 회원이 부여 받은 봉사시간과 봉사 신청 리스트를 조회 할 수 있으며 작성한 게시물 목록도 조회 가능하다.||
|스케줄러|봉사 마감일이 지나면 모집 상태 값이 마감으로 변경, 봉사 진행일이 지나면 진행 상태가 진행중으로 변경|30분 마다 스케줄러 작동해서 모집 상태와 진행 상태 값을 변경|


### :seven: 공통 기능
- API 응답 표준화 및 일관된 응답 구조 유지를 위해 ApiResponse<T> 클래스를 설계하여 재사용성을 높임
- ResponseCode를 Enum으로 만들어서 API 응답 코드와 메시지를 일관되게 관리
- interceptor 통한 세션 체크
- 개인 민감한 정보 암호화 (비밀번호는 단방향)
- Spring의 @RestControllerAdvice를 활용하여 전역 예외 처리(GlobalExceptionHandler)를 구현하여 API 응답의 일관성을 유지
