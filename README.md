
### :newspaper: 개인 프로젝트 목적
회사 다니면서 레거시 프로젝트를 많이 접했기 때문에 요즘 사용하고 있는 ORM 기술 JPA 기술을 배우고 싶었습니다.
회사 끝나고 퇴근 하면 인프런 강의를 들어 JPA에 대한 기술을 접할 수 있었고 JPA 기술을 활용한 간단한 개인 프로젝트를 만들게 되었습니다.

### :blue_book: 프로젝트 내용
요즘 환경을 신경 쓰지 않는 사람들이 많은 것 같습니다. 그래서 사람들이 환경을 위해 노력하고 있는 커뮤니티를 만들어 한 사람 만이 아닌 여러 사람들이 같이 노력하고 있는 환경 커뮤니티를 만들고 싶었습니다.

### :heavy_check_mark: 기술 스택
- Java 17
- Spring Boot
- mysql
- redis
- AWS EC2
- JPA
- SpringData JPA
- Querydsl


### :memo: 페이지 url 정보
- /      : 메인 페이지
- /login : 로그인 페이지
- /signup : 회원가입 페이지
- /boards/write : 게시판 작성페이지
- /boards/:id : 게시판 상세 페이지
- /boards/:id/edit : 게시판 상세 수정 페이지
- /users/:id : 회원정보 페이지 


### :memo: API url 정보
- **POST** &nbsp;&nbsp; /api/login : 유저 로그인
- **POST** &nbsp;&nbsp; /api/logout : 유저 로그아웃
- **POST** &nbsp;&nbsp; /api/users : 회원 가입
- **GET**  &nbsp;&nbsp; /api/users/{id} : 유저 상세보기
- **GET**  &nbsp;&nbsp; /api/boards/environment : 환경 게시글 목록 조회
- **POST** &nbsp;&nbsp; /api/boards/environment : 환경 게시글 작성
- **GET**  &nbsp;&nbsp; /api/boards/environment/{id} : 환경 게시글 상세 조회
- **PUT**  &nbsp;&nbsp; /api/boards/environment/{id} : 환경 게시글 상세 수정


### 🔧 ERD 설계
![image](https://github.com/user-attachments/assets/2a3bc8b2-ebe3-4f77-8c4a-5e156553090b)


