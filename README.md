# :blue_book: 프로젝트 내용
다같이는 나눔을 실천하고, 함께 성장하며, 더 나은 세상을 만들어가는 사람들을 위한 공간입니다. 봉사활동을 원하는 누구나 손쉽게 활동에 참여하고, 활동 완료 후에는 공인된 봉사시간을 간편하게 확인할 수 있습니다.
*****

### :books: 목차
1. 기술 스택
2. 페이지 url 정보
3. API url 정보 
4. ERD 설계
5. 아키텍처

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
|users/:id|회원정보|

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

|제목|내용|설명|
|------|---|---|
|테스트1|테스트2|테스트3|
|테스트1|테스트2|테스트3|
|테스트1|테스트2|테스트3|





