select max(BOARD_NUM) from board;

insert into web_gradle_erp.board 
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ,BOARD_READCOUNT, BOARD_DATE) 
values(1,'김상건','1111','마칠시간','5시');

INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE)
VALUES(1, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0, 0, 0, 0, '2021-03-03');

select * from board;

INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF)
VALUES(1, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0);

-- list 페이징
/*
 *  [1][2][3]
 * 
 *  (page - 1) * 10 => 1page 0, 2page => 10, 3page => 20
 */

select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE
  from board
 order by BOARD_RE_REF desc, BOARD_RE_SEQ asc
 limit 0, 10;


select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE
  from board
 order by BOARD_RE_REF desc, BOARD_RE_SEQ asc
 limit 10, 10;
 
-- list 카운트
select count(*) from board;

-- readcount 1추가
update board
   set BOARD_READCOUNT = BOARD_READCOUNT + 1
 where BOARD_NUM = 1 ;

-- 글 삭제
delete
  from board
 where BOARD_NUM = 20;

select * from board b  where board_num = 25 and BOARD_PASS = '1234';
 
select * from board b2  where BOARD_NUM = 37;

-- 수정
select *
from board
order by BOARD_RE_REF desc, board_re_seq;

update board set BOARD_SUBJECT = 'aaa',Board_content='aaa' where board_num = 6;
