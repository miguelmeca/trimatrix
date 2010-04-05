/*
SQLyog Enterprise - MySQL GUI v8.05 
MySQL - 5.1.33-community : Database - trimatrix
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `attachments` */

DROP TABLE IF EXISTS `attachments`;

CREATE TABLE `attachments` (
  `id` varchar(36) NOT NULL,
  `category_key` varchar(10) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `owner_id` varchar(36) DEFAULT NULL,
  `intern` tinyint(1) DEFAULT '0',
  `mime_type` varchar(100) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_size` int(11) DEFAULT NULL,
  `file_content` mediumblob,
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

/*Table structure for table `competitions` */

DROP TABLE IF EXISTS `competitions`;

CREATE TABLE `competitions` (
  `id` varchar(36) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `subtype` varchar(10) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `country_key` varchar(2) DEFAULT NULL,
  `results_template` varchar(50) DEFAULT NULL,
  `results_id` varchar(36) DEFAULT NULL,
  `created_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz erstellt am',
  `created_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz erstellt von',
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geändert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geändert von',
  `test` tinyint(1) DEFAULT '0',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelöscht',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `competitions_scouts` */

DROP TABLE IF EXISTS `competitions_scouts`;

CREATE TABLE `competitions_scouts` (
  `competition_id` varchar(36) NOT NULL,
  `scout_id` varchar(36) NOT NULL,
  `limits` text COMMENT 'multiple factors, normally one per category. format [{category:factor},{...},...]',
  PRIMARY KEY (`competition_id`,`scout_id`),
  KEY `fk_competition_specification_competition` (`competition_id`),
  KEY `fk_competition_specification_persons` (`scout_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `day_infos` */

DROP TABLE IF EXISTS `day_infos`;

CREATE TABLE `day_infos` (
  `date` date NOT NULL,
  `person_id` varchar(36) NOT NULL,
  `resting_hr` int(11) DEFAULT NULL,
  `weight_morning` decimal(5,1) DEFAULT NULL,
  `weight_evening` decimal(5,1) DEFAULT NULL,
  `sleeping_quality` int(11) DEFAULT NULL,
  `temperature` decimal(5,1) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `altitude` int(11) DEFAULT NULL,
  `dinners_lunch` text,
  `dinners_evening` text,
  `fluids_intake` decimal(5,1) DEFAULT NULL,
  `sleeping_hours` decimal(5,1) DEFAULT NULL,
  `tiredness` int(11) DEFAULT NULL,
  `feeling` int(11) DEFAULT NULL,
  `training_intensity` int(11) DEFAULT NULL,
  `training_valuation` int(11) DEFAULT NULL,
  `restday` tinyint(1) DEFAULT NULL,
  `travelday` tinyint(1) DEFAULT NULL,
  `camp` tinyint(1) DEFAULT NULL,
  `illness` tinyint(1) DEFAULT NULL,
  `illness_text` varchar(100) DEFAULT NULL,
  `massage` tinyint(1) DEFAULT NULL,
  `massage_text` varchar(100) DEFAULT NULL,
  `therapy` tinyint(1) DEFAULT NULL,
  `therapy_text` varchar(100) DEFAULT NULL,
  `comment` text,
  `comment_coach` text,
  `ck_morning` int(11) DEFAULT NULL,
  `ck_morning_time` time DEFAULT NULL,
  `ck_lunch` int(11) DEFAULT NULL,
  `ck_lunch_time` time DEFAULT NULL,
  `ck_evening` int(11) DEFAULT NULL,
  `ck_evening_time` time DEFAULT NULL,
  `urea_morning` int(11) DEFAULT NULL,
  `urea_morning_time` time DEFAULT NULL,
  `urea_lunch` int(11) DEFAULT NULL,
  `urea_lunch_time` time DEFAULT NULL,
  `urea_evening` int(11) DEFAULT NULL,
  `urea_evening_time` time DEFAULT NULL,
  `glucose_morning` int(11) DEFAULT NULL,
  `glucose_morning_time` time DEFAULT NULL,
  `glucose_lunch` int(11) DEFAULT NULL,
  `glucose_lunch_time` time DEFAULT NULL,
  `glucose_evening` int(11) DEFAULT NULL,
  `glucose_evening_time` time DEFAULT NULL,
  `hb` int(11) DEFAULT NULL,
  `hb_time` time DEFAULT NULL,
  `hkt` int(11) DEFAULT NULL,
  `hkt_time` time DEFAULT NULL,
  `weather` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`date`,`person_id`),
  KEY `fk_day_infos_persons` (`person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
  `longitude` decimal(13,10) DEFAULT NULL,
  `latitude` decimal(13,10) DEFAULT NULL,
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

/*Table structure for table `entities_have_labels` */

DROP TABLE IF EXISTS `entities_have_labels`;

CREATE TABLE `entities_have_labels` (
  `entity` varchar(36) NOT NULL,
  `label` varchar(36) NOT NULL,
  `person_id` varchar(36) NOT NULL,
  PRIMARY KEY (`entity`,`label`,`person_id`),
  KEY `fk_entities_have_labels_labels` (`label`),
  KEY `fk_entities_have_labels_persons` (`person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `import_templates` */

DROP TABLE IF EXISTS `import_templates`;

CREATE TABLE `import_templates` (
  `entity` varchar(25) NOT NULL,
  `person_id` varchar(36) NOT NULL,
  `description` varchar(50) NOT NULL,
  `starting_row` int(11) DEFAULT NULL,
  `mapping` text,
  PRIMARY KEY (`entity`,`person_id`,`description`),
  KEY `fk_import_templates_pesons` (`person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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

/*Table structure for table `k_categories` */

DROP TABLE IF EXISTS `k_categories`;

CREATE TABLE `k_categories` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `k_compsubtypes` */

DROP TABLE IF EXISTS `k_compsubtypes`;

CREATE TABLE `k_compsubtypes` (
  `key` varchar(10) NOT NULL,
  `key_parent` varchar(10) NOT NULL,
  `ordinal` int(11) DEFAULT NULL,
  PRIMARY KEY (`key`,`key_parent`),
  KEY `fk_compsubtypes_comptypes` (`key_parent`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Keys for type of competition';

/*Table structure for table `k_comptypes` */

DROP TABLE IF EXISTS `k_comptypes`;

CREATE TABLE `k_comptypes` (
  `key` varchar(10) NOT NULL,
  `ordinal` int(11) DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Keys for type of competition';

/*Table structure for table `k_countries` */

DROP TABLE IF EXISTS `k_countries`;

CREATE TABLE `k_countries` (
  `key` varchar(2) NOT NULL,
  `currency_key` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `k_currencies` */

DROP TABLE IF EXISTS `k_currencies`;

CREATE TABLE `k_currencies` (
  `key` varchar(3) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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

/*Table structure for table `k_languages` */

DROP TABLE IF EXISTS `k_languages`;

CREATE TABLE `k_languages` (
  `key` varchar(2) NOT NULL,
  `logon` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Language relevant for logon',
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='\n';

/*Table structure for table `k_reltyps` */

DROP TABLE IF EXISTS `k_reltyps`;

CREATE TABLE `k_reltyps` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `k_roles` */

DROP TABLE IF EXISTS `k_roles`;

CREATE TABLE `k_roles` (
  `key` varchar(36) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Roles for authorization';

/*Table structure for table `k_salutation` */

DROP TABLE IF EXISTS `k_salutation`;

CREATE TABLE `k_salutation` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `k_scheduletypes` */

DROP TABLE IF EXISTS `k_scheduletypes`;

CREATE TABLE `k_scheduletypes` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `k_sex` */

DROP TABLE IF EXISTS `k_sex`;

CREATE TABLE `k_sex` (
  `key` varchar(1) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `k_testtypes` */

DROP TABLE IF EXISTS `k_testtypes`;

CREATE TABLE `k_testtypes` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `labels` */

DROP TABLE IF EXISTS `labels`;

CREATE TABLE `labels` (
  `id` varchar(36) NOT NULL,
  `person_id` varchar(36) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_labels_persons` (`person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `list_variants` */

DROP TABLE IF EXISTS `list_variants`;

CREATE TABLE `list_variants` (
  `list` varchar(25) NOT NULL,
  `entity` varchar(25) NOT NULL,
  `user_id` varchar(36) NOT NULL,
  `columns_sequence` text,
  `columns_width` text,
  PRIMARY KEY (`list`,`entity`,`user_id`),
  KEY `fk_list_variants_users` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Saved state of entity lists';

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

/*Table structure for table `persons_athlete` */

DROP TABLE IF EXISTS `persons_athlete`;

CREATE TABLE `persons_athlete` (
  `id` varchar(36) NOT NULL,
  `height` decimal(10,2) DEFAULT NULL,
  `height_unit` varchar(5) DEFAULT NULL,
  `weight` decimal(10,2) DEFAULT NULL,
  `weight_unit` varchar(5) DEFAULT NULL,
  `max_hr` int(11) DEFAULT NULL,
  `resting_hr` int(11) DEFAULT NULL,
  `vo2_max` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_persons_athlete_persons` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Athlete profile for persons';

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

/*Table structure for table `persons_have_competitions` */

DROP TABLE IF EXISTS `persons_have_competitions`;

CREATE TABLE `persons_have_competitions` (
  `id` varchar(36) NOT NULL,
  `person` varchar(36) DEFAULT NULL COMMENT 'Partner 1 der Beziehung',
  `competition` varchar(36) DEFAULT NULL COMMENT 'Partner 2 der Beziehung',
  `reltyp_key` varchar(10) DEFAULT NULL COMMENT 'Beziehungstyp',
  `standard` tinyint(1) DEFAULT '0' COMMENT 'Default Beziehung falls mehrere existieren',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_persons_have_competitions_uk` (`person`,`competition`,`reltyp_key`),
  KEY `fk_persons_have_competitions_competitions` (`competition`),
  KEY `fk_persons_have_competitions_k_reltyps` (`reltyp_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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

/*Table structure for table `results` */

DROP TABLE IF EXISTS `results`;

CREATE TABLE `results` (
  `id` varchar(36) NOT NULL,
  `competition_id` varchar(36) NOT NULL,
  `scout_id` varchar(36) NOT NULL,
  `athlete_id` varchar(36) NOT NULL,
  `final_position` varchar(5) DEFAULT NULL,
  `time` varchar(8) DEFAULT NULL,
  `comment` text,
  `created_at` datetime DEFAULT '1900-01-01 00:00:00',
  `created_by` varchar(36) DEFAULT NULL,
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00',
  `modified_by` varchar(36) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_results_competition` (`competition_id`),
  KEY `fk_results_scout` (`scout_id`),
  KEY `fk_results_athlet` (`athlete_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Result of a competition by an athlete';

/*Table structure for table `results_tria` */

DROP TABLE IF EXISTS `results_tria`;

CREATE TABLE `results_tria` (
  `id` varchar(36) NOT NULL,
  `category` varchar(10) DEFAULT NULL,
  `swim_split` varchar(8) DEFAULT NULL,
  `run_split` varchar(8) DEFAULT NULL,
  `bike_split` varchar(8) DEFAULT NULL,
  `swim_position` varchar(5) DEFAULT NULL,
  `run_position` varchar(5) DEFAULT NULL,
  `bike_position` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Triathlon specific result data';

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

/*Table structure for table `schedules` */

DROP TABLE IF EXISTS `schedules`;

CREATE TABLE `schedules` (
  `id` varchar(36) NOT NULL,
  `person_id` varchar(36) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `description` text,
  `start` datetime DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL COMMENT 'Duration  in minutes',
  `color` varchar(10) DEFAULT NULL,
  `template` tinyint(1) DEFAULT '0',
  `template_name` varchar(100) DEFAULT NULL,
  `done` tinyint(1) DEFAULT '0',
  `details` text,
  `created_at` datetime DEFAULT '1900-01-01 00:00:00',
  `created_by` varchar(36) DEFAULT NULL,
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00',
  `modified_by` varchar(36) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_schedules_persons` (`person_id`),
  KEY `fk_schedules_scheduletypes` (`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Table for superclass of schedule items';

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

/*Table structure for table `t_compsubtypes` */

DROP TABLE IF EXISTS `t_compsubtypes`;

CREATE TABLE `t_compsubtypes` (
  `key` varchar(10) NOT NULL,
  `key_parent` varchar(10) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`key_parent`,`language_key`),
  KEY `fk_t_compsubtypes_k_languages` (`language_key`),
  KEY `fk_t_compsubtypes_k_compsubtypes` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `t_comptypes` */

DROP TABLE IF EXISTS `t_comptypes`;

CREATE TABLE `t_comptypes` (
  `key` varchar(10) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_comptypes_k_languages` (`language_key`),
  KEY `fk_t_comptypes_k_comptypes` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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

/*Table structure for table `t_reltyps` */

DROP TABLE IF EXISTS `t_reltyps`;

CREATE TABLE `t_reltyps` (
  `key` varchar(10) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  `relation_description` varchar(50) DEFAULT NULL,
  `relation_description_inverse` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_reltyps_k_languages` (`language_key`),
  KEY `fk_t_reltyps_k_reltyps` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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

/*Table structure for table `t_scheduletypes` */

DROP TABLE IF EXISTS `t_scheduletypes`;

CREATE TABLE `t_scheduletypes` (
  `key` varchar(10) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_scheduletypes_k_languages` (`language_key`),
  KEY `fk_t_scheduletypes_k_scheduletypes` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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

/*Table structure for table `t_testtypes` */

DROP TABLE IF EXISTS `t_testtypes`;

CREATE TABLE `t_testtypes` (
  `key` varchar(10) NOT NULL,
  `language_key` varchar(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `description_long` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_testtypes_k_languages` (`language_key`),
  KEY `fk_t_testtypes_k_testtypes` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `speed` text,
  `heartrate` text,
  `lactate` text
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `tests` */

DROP TABLE IF EXISTS `tests`;

CREATE TABLE `tests` (
  `id` varchar(36) NOT NULL,
  `person_id` varchar(36) DEFAULT NULL,
  `doctor_id` varchar(36) DEFAULT NULL,
  `coach_id` varchar(36) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `description` text,
  `created_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz erstellt am',
  `created_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz erstellt von',
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geändert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geändert von',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelöscht',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_tests_persons` (`person_id`),
  KEY `fk_tests_doctors` (`doctor_id`),
  KEY `fk_tests_coaches` (`coach_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Table contains tests definition';

/*Table structure for table `tests_analysis` */

DROP TABLE IF EXISTS `tests_analysis`;

CREATE TABLE `tests_analysis` (
  `id` varchar(36) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tests_analysis_tests` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Analysis of testprotocol';

/*Table structure for table `tests_ergo` */

DROP TABLE IF EXISTS `tests_ergo`;

CREATE TABLE `tests_ergo` (
  `id` varchar(36) NOT NULL,
  `power_init` int(11) DEFAULT NULL,
  `power_step` int(11) DEFAULT NULL,
  `cadence_low` int(11) DEFAULT NULL,
  `cadence_high` int(11) DEFAULT NULL,
  `step_time` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tests_ergo_tests` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Tests specific for ergometers';

/*Table structure for table `tests_protocol` */

DROP TABLE IF EXISTS `tests_protocol`;

CREATE TABLE `tests_protocol` (
  `id` varchar(36) NOT NULL,
  `description` text,
  `model` varchar(50) DEFAULT NULL,
  `model_lactate` varchar(50) DEFAULT NULL,
  `model_spiro` varchar(50) DEFAULT NULL,
  `count_steps` int(11) DEFAULT NULL,
  `time_last_step` varchar(5) DEFAULT NULL,
  `performance_max` varchar(5) DEFAULT NULL,
  `lactate` text,
  `hr` text,
  `o2_absorption` text,
  `co2_emission` text,
  `rq` text,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tests_treadmill_tests` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Testprotocol for specific test';

/*Table structure for table `tests_swim` */

DROP TABLE IF EXISTS `tests_swim`;

CREATE TABLE `tests_swim` (
  `id` varchar(36) NOT NULL,
  `date2` datetime DEFAULT NULL,
  `assistant_name` varchar(50) DEFAULT NULL,
  `baths` varchar(50) DEFAULT NULL,
  `pool` varchar(50) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL,
  `splits` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tests_swim_tests` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Tests specific for swim tests';

/*Table structure for table `tests_swim_protocol` */

DROP TABLE IF EXISTS `tests_swim_protocol`;

CREATE TABLE `tests_swim_protocol` (
  `id` varchar(36) NOT NULL,
  `step` int(11) NOT NULL,
  `attempt` int(11) NOT NULL,
  `intensity` int(11) DEFAULT NULL,
  `time` varchar(5) DEFAULT NULL,
  `valid` tinyint(1) DEFAULT NULL,
  `lactate` text,
  `hr` text COMMENT 'Datensatz erstellt am',
  `splits` text,
  `comment` text,
  PRIMARY KEY (`step`,`attempt`,`id`),
  KEY `fk_tests_swim_protocol_tests` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Testprotocol for swim test';

/*Table structure for table `tests_treadmill` */

DROP TABLE IF EXISTS `tests_treadmill`;

CREATE TABLE `tests_treadmill` (
  `id` varchar(36) NOT NULL,
  `speed_variable` tinyint(1) DEFAULT '0',
  `incline_variable` tinyint(1) DEFAULT '0',
  `speed_init` decimal(3,1) DEFAULT NULL,
  `speed_step` decimal(3,1) DEFAULT NULL,
  `incline_init` int(11) DEFAULT NULL,
  `incline_step` int(11) DEFAULT NULL,
  `step_time` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tests_treadmill_tests` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Tests specific for treadmills';

/*Table structure for table `user_defaults` */

DROP TABLE IF EXISTS `user_defaults`;

CREATE TABLE `user_defaults` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) NOT NULL,
  `field` varchar(20) NOT NULL,
  `dependency` varchar(20) DEFAULT NULL,
  `value` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_preferences` (`user_id`,`field`,`dependency`),
  KEY `fk_user_preferences_users` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Table to store default values for certain fields';

/*Table structure for table `user_preferences` */

DROP TABLE IF EXISTS `user_preferences`;

CREATE TABLE `user_preferences` (
  `id` varchar(36) NOT NULL,
  `sbvisibleamount` int(11) DEFAULT NULL,
  `competition_categories` text,
  PRIMARY KEY (`id`),
  KEY `fk_user_preferences_users` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` varchar(36) NOT NULL,
  `user_name` varchar(16) DEFAULT NULL COMMENT 'Username',
  `user_hash` varchar(36) DEFAULT NULL COMMENT 'Hashed password',
  `language_key` varchar(2) DEFAULT NULL,
  `currency_key` varchar(3) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0' COMMENT 'locked through wrong logon attempts',
  `initial` tinyint(1) DEFAULT '1' COMMENT 'initial password, user has to change the password at first logon',
  `active` tinyint(1) DEFAULT '1' COMMENT 'user is active',
  `person_id` varchar(36) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `last_login_ip` varchar(20) DEFAULT NULL,
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

/*Table structure for table `users_have_authorizations` */

DROP TABLE IF EXISTS `users_have_authorizations`;

CREATE TABLE `users_have_authorizations` (
  `user_id` varchar(36) NOT NULL,
  `authorization_key` varchar(36) NOT NULL,
  PRIMARY KEY (`user_id`,`authorization_key`),
  KEY `fk_users_have_authorizations_k_authorizations` (`authorization_key`),
  KEY `fk_users_have_authorizations_users` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `users_have_roles` */

DROP TABLE IF EXISTS `users_have_roles`;

CREATE TABLE `users_have_roles` (
  `user_id` varchar(36) NOT NULL,
  `role_key` varchar(36) NOT NULL,
  PRIMARY KEY (`user_id`,`role_key`),
  KEY `fk_users_has_roles_users` (`user_id`),
  KEY `fk_users_has_roles_roles` (`role_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `zones` */

DROP TABLE IF EXISTS `zones`;

CREATE TABLE `zones` (
  `id` varchar(36) NOT NULL,
  `athlete_id` varchar(36) NOT NULL,
  `zones_definition_id` varchar(36) NOT NULL,
  `hr_low` int(11) DEFAULT NULL,
  `hr_high` int(11) DEFAULT NULL,
  `speed_low` decimal(10,2) DEFAULT NULL,
  `speed_high` decimal(10,2) DEFAULT NULL,
  `auto_hr` tinyint(1) DEFAULT NULL,
  `auto_speed` tinyint(1) DEFAULT NULL,
  `test_id_hr` varchar(36) DEFAULT NULL,
  `test_id_speed` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_zones_persons` (`athlete_id`),
  KEY `fk_zones_tests_hr` (`test_id_hr`),
  KEY `fk_zones_zones_definition` (`zones_definition_id`),
  KEY `fk_zones_tests_speed` (`test_id_speed`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Exercise zones for athletes';

/*Table structure for table `zones_definition` */

DROP TABLE IF EXISTS `zones_definition`;

CREATE TABLE `zones_definition` (
  `id` varchar(36) NOT NULL,
  `coach_id` varchar(36) NOT NULL,
  `sequence` int(11) NOT NULL,
  `shortcut` varchar(20) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `lactate_low` decimal(10,2) DEFAULT NULL,
  `lactate_high` decimal(10,2) DEFAULT NULL,
  `hr_low` int(11) DEFAULT NULL,
  `hr_high` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_zones_definition_persons` (`coach_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Definition of exercise zones';

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

/*!50001 DROP VIEW IF EXISTS `categories` */;
/*!50001 DROP TABLE IF EXISTS `categories` */;

/*!50001 CREATE TABLE `categories` (
  `scout_id` varchar(36) CHARACTER SET latin1 NOT NULL,
  `category` varchar(10) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 */;

/*Table structure for table `entities` */

DROP TABLE IF EXISTS `entities`;

/*!50001 DROP VIEW IF EXISTS `entities` */;
/*!50001 DROP TABLE IF EXISTS `entities` */;

/*!50001 CREATE TABLE `entities` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `entity` varchar(11) NOT NULL DEFAULT '',
  `deleted` tinyint(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 */;

/*Table structure for table `relations` */

DROP TABLE IF EXISTS `relations`;

/*!50001 DROP VIEW IF EXISTS `relations` */;
/*!50001 DROP TABLE IF EXISTS `relations` */;

/*!50001 CREATE TABLE `relations` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `partner1` varchar(36) DEFAULT NULL,
  `partner2` varchar(36) DEFAULT NULL,
  `reltyp_key` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 */;

/*View structure for view categories */

/*!50001 DROP TABLE IF EXISTS `categories` */;
/*!50001 DROP VIEW IF EXISTS `categories` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `categories` AS select distinct `results`.`scout_id` AS `scout_id`,`results_tria`.`category` AS `category` from (`results_tria` join `results` on((`results`.`id` = `results_tria`.`id`))) where (`results`.`deleted` = 0) */;

/*View structure for view entities */

/*!50001 DROP TABLE IF EXISTS `entities` */;
/*!50001 DROP VIEW IF EXISTS `entities` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `entities` AS select `users`.`id` AS `id`,_utf8'User' AS `entity`,`users`.`deleted` AS `deleted` from `users` union select `persons`.`id` AS `id`,_utf8'Person' AS `entity`,`persons`.`deleted` AS `deleted` from `persons` union select `attachments`.`id` AS `id`,_utf8'Attachment' AS `entity`,`attachments`.`deleted` AS `deleted` from `attachments` union select `doctors`.`id` AS `id`,_utf8'Doctor' AS `entity`,`doctors`.`deleted` AS `deleted` from `doctors` union select `tests`.`id` AS `id`,_utf8'Test' AS `entity`,`tests`.`deleted` AS `deleted` from `tests` union select `competitions`.`id` AS `id`,_utf8'Competition' AS `entity`,`competitions`.`deleted` AS `deleted` from `competitions` union select `results`.`id` AS `id`,_utf8'Result' AS `entity`,`results`.`deleted` AS `deleted` from `results` */;

/*View structure for view relations */

/*!50001 DROP TABLE IF EXISTS `relations` */;
/*!50001 DROP VIEW IF EXISTS `relations` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `relations` AS select `persons_have_attachments`.`id` AS `id`,`persons_have_attachments`.`person` AS `partner1`,`persons_have_attachments`.`attachment` AS `partner2`,`persons_have_attachments`.`reltyp_key` AS `reltyp_key` from `persons_have_attachments` union select `persons_have_doctors`.`id` AS `id`,`persons_have_doctors`.`person` AS `partner1`,`persons_have_doctors`.`doctor` AS `partner2`,`persons_have_doctors`.`reltyp_key` AS `reltyp_key` from `persons_have_doctors` union select `persons_have_relations`.`id` AS `id`,`persons_have_relations`.`partner1` AS `partner1`,`persons_have_relations`.`partner2` AS `partner2`,`persons_have_relations`.`reltyp_key` AS `reltyp_key` from `persons_have_relations` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
