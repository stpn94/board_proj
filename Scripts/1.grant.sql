use web_gradle_erp;

drop table if exists web_gradle_erp.board;

create table if not exists web_gradle_erp.board (
	BOARD_NUM INT,
	BOARD_NAME VARCHAR(20) not null,
	BOARD_PASS VARCHAR(15) not null,
	BOARD_SUBJECT VARCHAR(50) not null,
	BOARD_CONTENT VARCHAR(2000) not null,
	BOARD_FILE VARCHAR(50) not null, 
	
	BOARD_RE_REF INT not null,
	
	BOARD_RE_LEV INT default 0,
	BOARD_RE_SEQ INT default 0,
	BOARD_READCOUNT INT default 0,
	BOARD_DATE DATETIME default current_timestamp,
	primary KEY(BOARD_NUM)
);


grant all
on web_gradle_erp.*
to'user_gradle_erp'@'localhost' identified by 'rootroot';

select *from board;