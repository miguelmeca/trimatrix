/*
SQLyog Enterprise - MySQL GUI v8.05 
MySQL - 5.1.33-community : Database - trimatrix
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`trimatrix` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `trimatrix`;

/*Table structure for table `attachments` */

DROP TABLE IF EXISTS `attachments`;

CREATE TABLE `attachments` (
  `id` varchar(36) NOT NULL,
  `category_key` varchar(10) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `owner_id` varchar(36) DEFAULT NULL,
  `mime_type` varchar(100) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_size` int(11) DEFAULT NULL,
  `file_content` blob,
  `created_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz erstellt am',
  `created_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz erstellt von',
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geändert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geändert von',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelöscht',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_attachments_k_categories` (`category_key`),
  KEY `fk_attachments_persons` (`owner_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `attachments` */

insert  into `attachments`(`id`,`category_key`,`description`,`owner_id`,`mime_type`,`file_name`,`file_size`,`file_content`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('b8a954e4-4bca-11de-ab35-74df036e1e4f','certificat','Attest Test','0b0b7658-2ddb-11de-86ae-00301bb60f17','PDF','Attest.pdf',1500,NULL,'1900-01-01 00:00:00',NULL,'1900-01-01 00:00:00',NULL,0,0);

/*Table structure for table `doctors` */

DROP TABLE IF EXISTS `doctors`;

CREATE TABLE `doctors` (
  `id` varchar(36) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `housenumber` varchar(45) DEFAULT NULL,
  `postcode` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `country_key` varchar(2) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `homepage` varchar(255) DEFAULT NULL,
  `telephone` varchar(25) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `fax` varchar(25) DEFAULT NULL,
  `created_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz erstellt am',
  `created_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz erstellt von',
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geändert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geändert von',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelöscht',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_doctors_k_countries` (`country_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `doctors` */

insert  into `doctors`(`id`,`name`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('c94e3cff-495d-11de-921e-1178275b5596','Medizinalrat Dr. Helmut Schwitzer','Kirchweg','2','6391','Fieberbrunn','Tirol','at','office@drschwitzer.at','http://www.drschwitzer.at','05354 / 56535',NULL,'05354 / 56535 - 75','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-05-27 23:05:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('d0782a5c-495d-11de-921e-1178275b5596','Dr. Michael Plattner','Dorf','39','6373','Jochberg','Tirol','at','michael.plattner@gmail.com',NULL,'05355 / 20071',NULL,NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-05-27 23:05:40','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

/*Table structure for table `k_authorizations` */

DROP TABLE IF EXISTS `k_authorizations`;

CREATE TABLE `k_authorizations` (
  `key` varchar(36) NOT NULL,
  `entity_key` varchar(36) DEFAULT NULL,
  `entity_field` varchar(36) DEFAULT NULL,
  `value_low` varchar(100) DEFAULT NULL,
  `exclusive` tinyint(1) DEFAULT NULL,
  `value_high` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Roles for authorization';

/*Data for the table `k_authorizations` */

/*Table structure for table `k_categories` */

DROP TABLE IF EXISTS `k_categories`;

CREATE TABLE `k_categories` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `k_categories` */

insert  into `k_categories`(`key`) values ('certificat');

/*Table structure for table `k_countries` */

DROP TABLE IF EXISTS `k_countries`;

CREATE TABLE `k_countries` (
  `key` varchar(2) NOT NULL,
  `currency_key` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_countries` */

insert  into `k_countries`(`key`,`currency_key`) values ('at','eur'),('de','eur');

/*Table structure for table `k_currencies` */

DROP TABLE IF EXISTS `k_currencies`;

CREATE TABLE `k_currencies` (
  `key` varchar(3) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_currencies` */

insert  into `k_currencies`(`key`) values ('eur'),('usd');

/*Table structure for table `k_functionnodes` */

DROP TABLE IF EXISTS `k_functionnodes`;

CREATE TABLE `k_functionnodes` (
  `key` varchar(36) NOT NULL,
  `page` varchar(36) DEFAULT NULL COMMENT 'Defined in Dictionary Class Enum Page',
  `entity` varchar(36) DEFAULT NULL COMMENT 'Defined in Dictionary Class Enum Entity',
  `edit` tinyint(1) DEFAULT '0',
  `create` tinyint(1) DEFAULT '0',
  `delete` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_functionnodes` */

insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('masterdata',' ',' ',0,0,0),('users_all','ENTITYLIST','USER',1,1,1),('persons_all','ENTITYLIST','PERSON',1,1,1),('person_own','ENTITYDETAIL','PERSON',1,0,0),('coaches_own','ENTITYLIST','MYCOACHES',0,0,0),('athletes_own','ENTITYLIST','MYATHLETES',0,0,0),('relations','','',0,0,0),('relation_coach','RELATIONLIST','COACH',1,1,1),('relation_doctor','RELATIONLIST','DOCTOR',1,1,1),('doctors_all','ENTITYLIST','DOCTOR',1,1,1),('doctors_own','ENTITYLIST','MYDOCTORS',0,0,0),('attachments_all','ENTITYLIST','ATTACHMENT',1,1,1),('attachments_own','ENTITYLIST','MYATTACHMENTS',0,0,0);

/*Table structure for table `k_languages` */

DROP TABLE IF EXISTS `k_languages`;

CREATE TABLE `k_languages` (
  `key` varchar(2) NOT NULL,
  `logon` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Language relevant for logon',
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='\n';

/*Data for the table `k_languages` */

insert  into `k_languages`(`key`,`logon`) values ('de',1),('en',1),('fr',0);

/*Table structure for table `k_reltyps` */

DROP TABLE IF EXISTS `k_reltyps`;

CREATE TABLE `k_reltyps` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_reltyps` */

insert  into `k_reltyps`(`key`) values ('attachment'),('coach'),('doctor');

/*Table structure for table `k_roles` */

DROP TABLE IF EXISTS `k_roles`;

CREATE TABLE `k_roles` (
  `key` varchar(36) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Roles for authorization';

/*Data for the table `k_roles` */

insert  into `k_roles`(`key`) values ('admin'),('athlete'),('coach');

/*Table structure for table `k_salutation` */

DROP TABLE IF EXISTS `k_salutation`;

CREATE TABLE `k_salutation` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_salutation` */

insert  into `k_salutation`(`key`) values ('mr'),('mrs');

/*Table structure for table `k_sex` */

DROP TABLE IF EXISTS `k_sex`;

CREATE TABLE `k_sex` (
  `key` varchar(1) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_sex` */

insert  into `k_sex`(`key`) values ('m'),('w');

/*Table structure for table `persons` */

DROP TABLE IF EXISTS `persons`;

CREATE TABLE `persons` (
  `id` varchar(36) NOT NULL,
  `salutation_key` varchar(10) DEFAULT NULL,
  `name_first` varchar(45) DEFAULT NULL,
  `name_last` varchar(45) DEFAULT NULL,
  `sex_key` varchar(1) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `housenumber` varchar(45) DEFAULT NULL,
  `postcode` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `country_key` varchar(2) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `homepage` varchar(255) DEFAULT NULL,
  `telephone` varchar(25) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `fax` varchar(25) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `picture` blob,
  `created_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz erstellt am',
  `created_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz erstellt von',
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geändert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geändert von',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelöscht',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_persons_k_countries` (`country_key`),
  KEY `fk_persons_k_sex` (`sex_key`),
  KEY `fk_persons_k_salutation` (`salutation_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `persons` */

insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('0b0b7658-2ddb-11de-86ae-00301bb60f17','mr','Markus','Reich','m','Dorfstraße','91','6393','St. Ulrich','Tirol','at','reich.markus@gmail.com','www.meex-rich.com','','0664/3453852',NULL,'1978-10-24 00:00:00',NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-05-26 21:01:18','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('10f52302-2ddb-11de-86ae-00301bb60f17','mrs','Daniela','Bucher','w','Moosbach','28/2','6392','St. Jakob','Tirol','at','dany.bucher@gmail.com','www.dany.at','05354/88462','0664/2844263','05354/88462-10','1983-05-17 00:00:00',NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-05-27 22:48:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('7522bc7f-42cf-415c-a050-da12518a4cd3','mr','Thomas','Mach','m','Dorf','2','6391','Fieberbrunn','Tirol','de','thomas.mach@egger.com',NULL,NULL,NULL,NULL,'1969-09-15 00:00:00',NULL,'1900-01-01 00:00:00',NULL,'1900-01-01 00:00:00',NULL,0,0);

/*Table structure for table `persons_have_attachments` */

DROP TABLE IF EXISTS `persons_have_attachments`;

CREATE TABLE `persons_have_attachments` (
  `id` varchar(36) NOT NULL,
  `person` varchar(36) DEFAULT NULL COMMENT 'Partner 1 der Beziehung',
  `attachment` varchar(36) DEFAULT NULL COMMENT 'Partner 2 der Beziehung',
  `reltyp_key` varchar(10) DEFAULT NULL COMMENT 'Beziehungstyp',
  `standard` tinyint(1) DEFAULT '0' COMMENT 'Default Beziehung falls mehrere existieren',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_persons_have_relations_uk` (`person`,`attachment`,`reltyp_key`),
  KEY `fk_persons_have_relations_persons_1` (`person`),
  KEY `fk_persons_have_relations_persons_2` (`attachment`),
  KEY `fk_persons_have_relations_k_reltyps` (`reltyp_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `persons_have_attachments` */

insert  into `persons_have_attachments`(`id`,`person`,`attachment`,`reltyp_key`,`standard`) values ('2c32df42-4bcc-11de-ab35-74df036e1e4f','10f52302-2ddb-11de-86ae-00301bb60f17','b8a954e4-4bca-11de-ab35-74df036e1e4f','attachment',0),('2c32df42-4bcc-11de-ab35-74df036e1e4g','7522bc7f-42cf-415c-a050-da12518a4cd3','b8a954e4-4bca-11de-ab35-74df036e1e4f','attachment',0);

/*Table structure for table `persons_have_doctors` */

DROP TABLE IF EXISTS `persons_have_doctors`;

CREATE TABLE `persons_have_doctors` (
  `id` varchar(36) NOT NULL,
  `person` varchar(36) DEFAULT NULL COMMENT 'Partner 1 der Beziehung',
  `doctor` varchar(36) DEFAULT NULL COMMENT 'Partner 2 der Beziehung',
  `reltyp_key` varchar(10) DEFAULT NULL COMMENT 'Beziehungstyp',
  `standard` tinyint(1) DEFAULT '0' COMMENT 'Default Beziehung falls mehrere existieren',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_persons_have_relations_uk` (`person`,`doctor`,`reltyp_key`),
  KEY `fk_persons_have_relations_persons_1` (`person`),
  KEY `fk_persons_have_relations_persons_2` (`doctor`),
  KEY `fk_persons_have_relations_k_reltyps` (`reltyp_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `persons_have_doctors` */

insert  into `persons_have_doctors`(`id`,`person`,`doctor`,`reltyp_key`,`standard`) values ('f12d0178-496f-11de-921e-1178275b5596','10f52302-2ddb-11de-86ae-00301bb60f17','c94e3cff-495d-11de-921e-1178275b5596','doctor',0),('f73f73b4-496f-11de-921e-1178275b5596','0b0b7658-2ddb-11de-86ae-00301bb60f17','c94e3cff-495d-11de-921e-1178275b5596','doctor',0);

/*Table structure for table `persons_have_relations` */

DROP TABLE IF EXISTS `persons_have_relations`;

CREATE TABLE `persons_have_relations` (
  `id` varchar(36) NOT NULL,
  `partner1` varchar(36) DEFAULT NULL COMMENT 'Partner 1 der Beziehung',
  `partner2` varchar(36) DEFAULT NULL COMMENT 'Partner 2 der Beziehung',
  `reltyp_key` varchar(10) DEFAULT NULL COMMENT 'Beziehungstyp',
  `standard` tinyint(1) DEFAULT '0' COMMENT 'Default Beziehung falls mehrere existieren',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_persons_have_relations_uk` (`partner1`,`partner2`,`reltyp_key`),
  KEY `fk_persons_have_relations_persons_1` (`partner1`),
  KEY `fk_persons_have_relations_persons_2` (`partner2`),
  KEY `fk_persons_have_relations_k_reltyps` (`reltyp_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `persons_have_relations` */

insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('e3572a08-8c2d-102c-a1cd-29e813a50118','10f52302-2ddb-11de-86ae-00301bb60f17','0b0b7658-2ddb-11de-86ae-00301bb60f17','coach',0),('57f2f725-e3ee-496a-9d83-cd1f861b7dcb','0b0b7658-2ddb-11de-86ae-00301bb60f17','7522bc7f-42cf-415c-a050-da12518a4cd3','coach',0);

/*Table structure for table `roles_have_functionnodes` */

DROP TABLE IF EXISTS `roles_have_functionnodes`;

CREATE TABLE `roles_have_functionnodes` (
  `role_key` varchar(36) CHARACTER SET latin1 NOT NULL,
  `functionnode_key` varchar(36) CHARACTER SET latin1 NOT NULL,
  `node` int(11) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_key`,`functionnode_key`),
  KEY `fk_roles_have_functionnodes_k_roles` (`role_key`),
  KEY `fk_roles_have_functionnodes_k_functionnodes` (`functionnode_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `roles_have_functionnodes` */

insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','masterdata',1,0,1),('admin','users_all',2,1,1),('admin','persons_all',3,1,2),('athlete','person_own',1,0,1),('athlete','coaches_own',2,0,2),('coach','person_own',1,0,1),('coach','athletes_own',2,0,2),('admin','relations',4,0,2),('admin','relation_coach',5,4,1),('admin','doctors_all',6,1,3),('admin','relation_doctor',7,4,2),('athlete','doctors_own',3,0,3),('coach','doctors_own',3,0,3),('admin','attachments_all',7,1,4),('athlete','attachments_own',4,0,4),('coach','attachments_own',4,0,4);

/*Table structure for table `t_authorizations` */

DROP TABLE IF EXISTS `t_authorizations`;

CREATE TABLE `t_authorizations` (
  `key` varchar(36) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_authorization_k_languages` (`language_key`),
  KEY `fk_t_authorization_k_authorization` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `t_authorizations` */

/*Table structure for table `t_categories` */

DROP TABLE IF EXISTS `t_categories`;

CREATE TABLE `t_categories` (
  `key` varchar(10) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_categories_k_languages` (`language_key`),
  KEY `fk_t_categories_k_categories` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_categories` */

insert  into `t_categories`(`key`,`language_key`,`description`,`description_long`) values ('certificat','de','Attest','Attest'),('certificat','en','Certificate','Certificate');

/*Table structure for table `t_countries` */

DROP TABLE IF EXISTS `t_countries`;

CREATE TABLE `t_countries` (
  `key` varchar(2) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_countries_k_languages` (`language_key`),
  KEY `fk_t_countries_k_countries` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `t_countries` */

insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('at','de','Österreich','Österreich'),('de','de','Deutschland','Deutschland'),('at','en','Austria','Austria'),('de','en','Germany','Germany');

/*Table structure for table `t_currencies` */

DROP TABLE IF EXISTS `t_currencies`;

CREATE TABLE `t_currencies` (
  `key` varchar(3) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_currencies_k_languages` (`language_key`),
  KEY `fk_t_currencies_k_currencies` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `t_currencies` */

insert  into `t_currencies`(`key`,`language_key`,`description`,`description_long`) values ('eur','de','Euro','Euro'),('usd','de','Dollar','Dollar');

/*Table structure for table `t_functionnodes` */

DROP TABLE IF EXISTS `t_functionnodes`;

CREATE TABLE `t_functionnodes` (
  `key` varchar(36) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_functions_k_languages` (`language_key`),
  KEY `fk_t_functionnodes_k_functionnodes` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `t_functionnodes` */

insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('persons_all','de','Personen','Personen'),('persons_all','en','Persons','Persons'),('users_all','de','Benutzer','Benutzer'),('users_all','en','Users','Users'),('masterdata','de','Stammdaten','Stammdaten'),('masterdata','en','Masterdata','Masterdata'),('person_own','de','Eigene Person','Eigene Person'),('person_own','en','My person','My person'),('coaches_own','de','Meine Trainer','Meine Trainer'),('coaches_own','en','My Coaches','My Coaches'),('athletes_own','de','Meine Athleten','Meine Athleten'),('athletes_own','en','My Athletes','My Athletes'),('relations','de','Beziehungen','Beziehungen'),('relations','en','Relationships','Relationships'),('relation_coach','de','Trainer','Trainer'),('relation_coach','en','Coaches','Coaches'),('relation_doctor','de','Ärzte','Ärzte'),('relation_doctor','en','Doctors','Doctors'),('doctors_all','de','Ärzte','Ärzte'),('doctors_all','en','Doctors','Doctors'),('doctors_own','de','Meine Ärzte','Meine Ärzte'),('doctors_own','en','My Doctors','My Doctors'),('attachments_all','de','Anhänge','Anhänge'),('attachments_all','en','Attachments','Attachments'),('attachments_own','de','Meine Anhänge','Meine Anhänge'),('attachments_own','en','My Attachments','My Attachments');

/*Table structure for table `t_languages` */

DROP TABLE IF EXISTS `t_languages`;

CREATE TABLE `t_languages` (
  `key` varchar(2) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_languages_k_languages` (`language_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `t_languages` */

insert  into `t_languages`(`key`,`language_key`,`description`,`description_long`) values ('de','de','Deutsch','Deutsch'),('de','en','German','German'),('en','de','Englisch','Englisch'),('en','en','English','English');

/*Table structure for table `t_reltyps` */

DROP TABLE IF EXISTS `t_reltyps`;

CREATE TABLE `t_reltyps` (
  `key` varchar(10) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  `relation_description` varchar(20) DEFAULT NULL,
  `relation_description_inverse` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_reltyps_k_languages` (`language_key`),
  KEY `fk_t_reltyps_k_reltyps` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `t_reltyps` */

insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('coach','de','Trainer','Trainer','hat den Trainer','ist Trainer von'),('coach','en','Coach','Coach','has the coach','is the coach of'),('doctor','de','Arzt','Arzt','hat den Arzt','ist Arzt von'),('doctor','en','Doctor','Doctor','has the doctor','is the doctor of'),('attachment','de','Anhang','Anhang','hat den Anhang','der Anhang gehört'),('attachment','en','Attachment','Attachment','has the attachment','the attachment belon');

/*Table structure for table `t_roles` */

DROP TABLE IF EXISTS `t_roles`;

CREATE TABLE `t_roles` (
  `key` varchar(36) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_roles_k_languages` (`language_key`),
  KEY `fk_t_roles_k_roles` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `t_roles` */

insert  into `t_roles`(`key`,`language_key`,`description`,`description_long`) values ('admin','de','Administrator','Administrator'),('admin','en','Admin','Admin'),('coach','de','Trainer','Trainer'),('coach','en','Coach','Coach'),('athlete','de','Athlet','Athlet'),('athlete','en','Athlete','Athlete');

/*Table structure for table `t_salutation` */

DROP TABLE IF EXISTS `t_salutation`;

CREATE TABLE `t_salutation` (
  `key` varchar(10) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_salutation_k_languages` (`language_key`),
  KEY `fk_t_salutation_k_title` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `t_salutation` */

insert  into `t_salutation`(`key`,`language_key`,`description`,`description_long`) values ('mr','de','Herr','Herr'),('mr','en','Mr.','Mr.'),('mrs','de','Frau','Frau'),('mrs','en','Mrs.','Mrs.');

/*Table structure for table `t_sex` */

DROP TABLE IF EXISTS `t_sex`;

CREATE TABLE `t_sex` (
  `key` varchar(1) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_sex_k_languages` (`language_key`),
  KEY `fk_t_sex_k_sex` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `t_sex` */

insert  into `t_sex`(`key`,`language_key`,`description`,`description_long`) values ('m','de','männlich','männlich'),('w','de','weiblich','weiblich'),('m','en','male','male'),('w','en','female','female');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` varchar(36) NOT NULL,
  `user_name` varchar(16) DEFAULT NULL COMMENT 'Username',
  `user_hash` varchar(24) DEFAULT NULL COMMENT 'Hashed password',
  `language_key` varchar(2) DEFAULT NULL,
  `currency_key` varchar(3) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0' COMMENT 'locked through wrong logon attempts',
  `initial` tinyint(1) DEFAULT '1' COMMENT 'initial password, user has to change the password at first logon',
  `active` tinyint(1) DEFAULT '1' COMMENT 'user is active',
  `person_id` varchar(36) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT '1900-01-01 00:00:00',
  `created_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz erstellt von',
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geändert von',
  `deleted` tinyint(1) DEFAULT '0',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_uk` (`user_name`),
  KEY `fk_users_k_currencies` (`currency_key`),
  KEY `fk_users_k_languages` (`language_key`),
  KEY `fk_users_persons` (`person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='User data';

/*Data for the table `users` */

insert  into `users`(`id`,`user_name`,`user_hash`,`language_key`,`currency_key`,`locked`,`initial`,`active`,`person_id`,`email`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c87','reich','test','de','eur',0,0,1,'0b0b7658-2ddb-11de-86ae-00301bb60f17','reich.markus@gmail.com','1900-01-01 00:00:00','','1900-01-01 00:00:00','',0,0),('e96bcbd2-676d-102c-ace2-9cc3fca64c88','bucher','test','en','usd',0,0,1,'10f52302-2ddb-11de-86ae-00301bb60f17','dany.bucher@gmail.com','1900-01-01 00:00:00','','2009-05-10 17:53:59','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('e96bcbd2-676d-102c-ace2-9cc3fca64c89','mach','test','de','eur',0,0,1,'7522bc7f-42cf-415c-a050-da12518a4cd3','mach.thomas@gmail.com','1900-01-01 00:00:00','','1900-01-01 00:00:00','',0,0);

/*Table structure for table `users_have_authorizations` */

DROP TABLE IF EXISTS `users_have_authorizations`;

CREATE TABLE `users_have_authorizations` (
  `user_id` varchar(36) NOT NULL,
  `authorization_key` varchar(36) NOT NULL,
  PRIMARY KEY (`user_id`,`authorization_key`),
  KEY `fk_users_have_authorizations_k_authorizations` (`authorization_key`),
  KEY `fk_users_have_authorizations_users` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `users_have_authorizations` */

/*Table structure for table `users_have_roles` */

DROP TABLE IF EXISTS `users_have_roles`;

CREATE TABLE `users_have_roles` (
  `user_id` varchar(36) NOT NULL,
  `role_key` varchar(36) NOT NULL,
  PRIMARY KEY (`user_id`,`role_key`),
  KEY `fk_users_has_roles_users` (`user_id`),
  KEY `fk_users_has_roles_roles` (`role_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `users_have_roles` */

insert  into `users_have_roles`(`user_id`,`role_key`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c87','admin'),('e96bcbd2-676d-102c-ace2-9cc3fca64c87','athlete'),('e96bcbd2-676d-102c-ace2-9cc3fca64c87','coach'),('e96bcbd2-676d-102c-ace2-9cc3fca64c88','coach'),('e96bcbd2-676d-102c-ace2-9cc3fca64c89','athlete');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
