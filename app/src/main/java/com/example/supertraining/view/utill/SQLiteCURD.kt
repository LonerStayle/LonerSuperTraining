package com.example.supertraining.view.utill


//***CREATE***
//INSERT INTO topic (title,description,created,author,profile)
//VALUES('MySQL','MySQL is...',NOW(),'egoing','developer');
//(열 1칸 )
//
//INSERT INTO topic (title,description,created,author,profile)
//VALUES('ORACLE','ORACLE is...',NOW(),'egoing','developer');
//(열 2칸)
//열이 늘어남.
//
//***READING***
//SELECT * FROM topic;
//테이블 전체로 가져옴
//SELECT id,title,created,author FROM topic;
//테이블 로우 필요한 부분만 가져옴
//SELECT "egoing";
//SELECT "egoing",1+1;
//SELECT id,title,created,author FROM topic WHERE author = 'egoing';
//왼쪽 row중 포함된 egoing 값들을 모두 가져옴
//ELECT id,title,created,author FROM topic WHERE author = 'egoing'ORDER BY id DESC;
//왼쪽 row중 포함된 egoing 값들을 내림차순 기준으로 가져옴
//SELECT id,title,created,author FROM topic WHERE author = 'egoing'ORDER BY id DESC LIMIT 2;
//왼쪽 row중 포함된 egoing 값들을 내림차순 기준으로 첫칸부터 최대 2칸만 가져옴
//
//***UPDATE***
//UPDATE topic SET description = 'Oracle is...',title='Oracle'WHERE id =2 ;
//row에 속해 있는 description 과 title의 값을 변경함 프라이머리 키 인 id = 2 번것만. (안그럼 컬럼 다 변경됨)
//
//***DELETE***
//DELETE FROM topic WHERE id = 5;
//프라이머리 id = 5 의 한줄을 삭제함 .

