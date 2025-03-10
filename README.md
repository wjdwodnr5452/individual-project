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
- /      : 메인 페이지
- /login : 로그인 페이지
- /signup : 회원가입 페이지
- /boards/write : 게시판 작성페이지
- /boards/:id : 게시판 상세 페이지
- /boards/:id/edit : 게시판 상세 수정 페이지
- /users/:id : 회원정보 페이지

*****


### :three: API url 정보
- **POST** &nbsp;&nbsp; /api/auth/login :  로그인
- **POST** &nbsp;&nbsp; /api/auth/logout : 로그아웃
- **GET**  &nbsp;&nbsp; /api/auth/status : 상태 체크
- **POST** &nbsp;&nbsp; /api/users : 회원 가입
- **GET**  &nbsp;&nbsp; /api/users/{id} : 유저 상세보기
- **GET**  &nbsp;&nbsp; /api/service-boards : 봉사 게시글 목록 조회
- **POST** &nbsp;&nbsp; /api/service-boards : 봉사 게시글 작성
- **GET**  &nbsp;&nbsp; /api/service-boards/{id} : 봉사 게시글 상세 조회
- **PUT**  &nbsp;&nbsp; /api/service-boards/{id} : 봉사 게시글 상세 수정
- **GET** &nbsp;&nbsp; /api/status/service : 봉사 상태 조회
- **GET** &nbsp;&nbsp; /api/status/recruitment : 봉사 모집 상태 조회
- **GET** &nbsp;&nbsp; /api/categorys : 카테고리 조회

*****

### :four: ERD 설계
- url : https://www.erdcloud.com/d/8Tba5NQqfBJtvEhuD

![image](https://github.com/user-attachments/assets/28795093-6408-42a4-9ea4-40dd9f5eb66a)

*****

### :five: 아키텍처
![아키텍처 drawio](https://github.com/user-attachments/assets/dda46cb0-ef95-4894-9be8-8e0b5c7d2f48)

*****
### :six: 결과
https://web.together-communication.com/







