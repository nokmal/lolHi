DROP DATABASE IF EXISTS lolHi;
CREATE DATABASE lolHi;
USE lolHi;

CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    `updateDate` DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,    
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(30) NOT NULL,
    loginId CHAR(30) NOT NULL,
    loginPw CHAR(80) NOT NULL
);

INSERT INTO `member`
SET  regDate = NOW(),
updateDate = NOW(),
`name` ='guest',
loginId='guest',
loginPw='guest';


INSERT INTO article
SET regDate=NOW(),
`updateDate`=NOW(),
title='제목1',
`body`='내용1';

INSERT INTO article
SET regDate=NOW(),
`updateDate`=NOW(),
title='제목2',
`body`='내용2';

INSERT INTO article
SET regDate=NOW(),
`updateDate`=NOW(),
title='제목3',
`body`='내용3';

