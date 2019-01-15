create database shorturl;
use shorturl;

CREATE TABLE `url` (
  `url_id` int(11) NOT NULL AUTO_INCREMENT,
  `url_short` varchar(10) NOT NULL,
  `url_long` varchar(254) NOT NULL,
  PRIMARY KEY (`url_id`),
  UNIQUE KEY `url_id_UNIQUE` (`url_id`),
  UNIQUE KEY `short_UNIQUE` (`url_short`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;


