INSERT INTO categorys (category_name) VALUES
                                                               ('환경보호'),
                                                               ('사회복지'),
                                                               ('교육'),
                                                               ('문화및예술'),
                                                               ('동물보호');


INSERT INTO status_type (status_type_name) VALUES
                                                                    ('모집상태'),
                                                                    ('서비스상태'),
                                                                    ('지원상태');


INSERT INTO status (status_type_id,status_name) VALUES
                                                                         (1,'모집중'),
                                                                         (1,'모집종료'),
                                                                         (2,'시작전'),
                                                                         (2,'진행중'),
                                                                         (2,'종료'),
                                                                         (3,'신청'),
                                                                         (3,'승인'),
                                                                         (3,'취소');


