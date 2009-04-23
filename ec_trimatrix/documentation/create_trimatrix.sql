SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `trimatrix` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `trimatrix`;

-- -----------------------------------------------------
-- Table `trimatrix`.`k_currencies`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`k_currencies` (
  `key` VARCHAR(3) NOT NULL ,
  PRIMARY KEY (`key`) )
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`k_currencies`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `k_currencies` (`key`) VALUES ('eur');
INSERT INTO `k_currencies` (`key`) VALUES ('usd');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`k_languages`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`k_languages` (
  `key` VARCHAR(2) NOT NULL ,
  `logon` BOOLEAN NOT NULL DEFAULT 0 COMMENT 'Language relevant for logon' ,
  PRIMARY KEY (`key`) )
ENGINE = MyISAM
COMMENT = '\n';


-- -----------------------------------------------------
-- Data for table `trimatrix`.`k_languages`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `k_languages` (`key`, `logon`) VALUES ('de', 1);
INSERT INTO `k_languages` (`key`, `logon`) VALUES ('en', 1);
INSERT INTO `k_languages` (`key`, `logon`) VALUES ('fr', 0);

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`k_countries`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`k_countries` (
  `key` VARCHAR(2) NOT NULL ,
  `currency_key` VARCHAR(3) NULL ,
  PRIMARY KEY (`key`) )
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`k_countries`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `k_countries` (`key`, `currency_key`) VALUES ('at', 'eur');
INSERT INTO `k_countries` (`key`, `currency_key`) VALUES ('de', 'eur');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`k_sex`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`k_sex` (
  `key` VARCHAR(1) NOT NULL ,
  PRIMARY KEY (`key`) )
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`k_sex`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `k_sex` (`key`) VALUES ('m');
INSERT INTO `k_sex` (`key`) VALUES ('w');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`k_salutation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`k_salutation` (
  `key` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`key`) )
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`k_salutation`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `k_salutation` (`key`) VALUES ('mr');
INSERT INTO `k_salutation` (`key`) VALUES ('ms');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`persons`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`persons` (
  `id` VARCHAR(36) NOT NULL ,
  `salutation_key` VARCHAR(10) NULL ,
  `name_first` VARCHAR(45) NULL ,
  `name_last` VARCHAR(45) NULL ,
  `sex_key` VARCHAR(1) NULL ,
  `street` VARCHAR(45) NULL ,
  `housenumber` VARCHAR(45) NULL ,
  `postcode` VARCHAR(45) NULL ,
  `city` VARCHAR(45) NULL ,
  `state` VARCHAR(45) NULL ,
  `country_key` VARCHAR(2) NULL ,
  `email` VARCHAR(255) NULL ,
  `homepage` VARCHAR(255) NULL ,
  `telephone` VARCHAR(25) NULL ,
  `mobile` VARCHAR(25) NULL ,
  `fax` VARCHAR(25) NULL ,
  `birthdate` DATETIME NULL ,
  `picture` BLOB NULL ,
  `created_at` DATETIME NULL DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz erstellt am' ,
  `created_by` VARCHAR(36) NULL COMMENT 'Datensatz erstellt von' ,
  `modified_at` DATETIME NULL DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geändert am' ,
  `modified_by` VARCHAR(36) NULL COMMENT 'Datensatz geändert von' ,
  `deleted` BOOLEAN NULL DEFAULT 0 COMMENT 'Datensatz gelöscht' ,
  `test` BOOLEAN NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX fk_persons_k_countries (`country_key` ASC) ,
  INDEX fk_persons_k_sex (`sex_key` ASC) ,
  INDEX fk_persons_k_salutation (`salutation_key` ASC) ,
  CONSTRAINT `fk_persons_k_countries`
    FOREIGN KEY (`country_key` )
    REFERENCES `trimatrix`.`k_countries` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_persons_k_sex`
    FOREIGN KEY (`sex_key` )
    REFERENCES `trimatrix`.`k_sex` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_persons_k_salutation`
    FOREIGN KEY (`salutation_key` )
    REFERENCES `trimatrix`.`k_salutation` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Table `trimatrix`.`users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`users` (
  `id` VARCHAR(36) NOT NULL ,
  `user_name` VARCHAR(16) NULL COMMENT 'Username' ,
  `user_hash` VARCHAR(24) NULL COMMENT 'Hashed password' ,
  `language_key` VARCHAR(2) NULL ,
  `currency_key` VARCHAR(3) NULL ,
  `locked` BOOLEAN NULL DEFAULT 0 COMMENT 'locked through wrong logon attempts' ,
  `initial` BOOLEAN NULL DEFAULT 1 COMMENT 'initial password, user has to change the password at first logon' ,
  `active` BOOLEAN NULL DEFAULT 1 COMMENT 'user is active' ,
  `person_id` VARCHAR(36) NULL ,
  `email` VARCHAR(255) NULL ,
  `created_at` DATETIME NULL DEFAULT '1900-01-01 00:00:00' ,
  `created_by` VARCHAR(36) NULL COMMENT 'Datensatz erstellt von' ,
  `modified_at` DATETIME NULL DEFAULT '1900-01-01 00:00:00' ,
  `modified_by` VARCHAR(36) NULL COMMENT 'Datensatz geändert von' ,
  `deleted` BOOLEAN NULL DEFAULT 0 ,
  `test` BOOLEAN NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX fk_users_k_currencies (`currency_key` ASC) ,
  INDEX fk_users_k_languages (`language_key` ASC) ,
  INDEX fk_users_persons (`person_id` ASC) ,
  UNIQUE INDEX idx_users_uk (`user_name` ASC) ,
  CONSTRAINT `fk_users_k_currencies`
    FOREIGN KEY (`currency_key` )
    REFERENCES `trimatrix`.`k_currencies` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_k_languages`
    FOREIGN KEY (`language_key` )
    REFERENCES `trimatrix`.`k_languages` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_persons`
    FOREIGN KEY (`person_id` )
    REFERENCES `trimatrix`.`persons` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM
COMMENT = 'User data';


-- -----------------------------------------------------
-- Data for table `trimatrix`.`users`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `users` (`id`, `user_name`, `user_hash`, `language_key`, `currency_key`, `locked`, `initial`, `active`, `person_id`, `email`, `created_at`, `created_by`, `modified_at`, `modified_by`, `deleted`, `test`) VALUES ('e96bcbd2-676d-102c-ace2-9cc3fca64c87', 'reich', 'test', 'de', 'eur', 0, 1, 1, '', 'reich.markus@gmail.com', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00', '', 0, 0);
INSERT INTO `users` (`id`, `user_name`, `user_hash`, `language_key`, `currency_key`, `locked`, `initial`, `active`, `person_id`, `email`, `created_at`, `created_by`, `modified_at`, `modified_by`, `deleted`, `test`) VALUES ('e96bcbd2-676d-102c-ace2-9cc3fca64c88', 'bucher', 'test', 'en', 'usd', 0, 1, 1, '', 'dany.bucher@gmail.com', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00', '', 0, 0);

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`k_roles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`k_roles` (
  `key` VARCHAR(36) NOT NULL ,
  PRIMARY KEY (`key`) )
ENGINE = MyISAM
COMMENT = 'Roles for authorization';


-- -----------------------------------------------------
-- Data for table `trimatrix`.`k_roles`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `k_roles` (`key`) VALUES ('admin');
INSERT INTO `k_roles` (`key`) VALUES ('coach');
INSERT INTO `k_roles` (`key`) VALUES ('athlete');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`users_have_roles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`users_have_roles` (
  `user_id` VARCHAR(36) NOT NULL ,
  `role_key` VARCHAR(36) NOT NULL ,
  INDEX fk_users_has_roles_users (`user_id` ASC) ,
  INDEX fk_users_has_roles_roles (`role_key` ASC) ,
  PRIMARY KEY (`user_id`, `role_key`) ,
  CONSTRAINT `fk_users_has_roles_users`
    FOREIGN KEY (`user_id` )
    REFERENCES `trimatrix`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_roles_roles`
    FOREIGN KEY (`role_key` )
    REFERENCES `trimatrix`.`k_roles` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`users_have_roles`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `users_have_roles` (`user_id`, `role_key`) VALUES ('e96bcbd2-676d-102c-ace2-9cc3fca64c87', 'admin');
INSERT INTO `users_have_roles` (`user_id`, `role_key`) VALUES ('e96bcbd2-676d-102c-ace2-9cc3fca64c87', 'coach');
INSERT INTO `users_have_roles` (`user_id`, `role_key`) VALUES ('e96bcbd2-676d-102c-ace2-9cc3fca64c87', 'athlete');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`k_functionnodes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`k_functionnodes` (
  `key` VARCHAR(36) NOT NULL ,
  `page` VARCHAR(36) NULL COMMENT 'Defined in Dictionary Class Enum Page' ,
  `entity` VARCHAR(36) NULL COMMENT 'Defined in Dictionary Class Enum Entity' ,
  `edit` BOOLEAN NULL DEFAULT 0 ,
  `create` BOOLEAN NULL DEFAULT 0 ,
  `delete` BOOLEAN NULL DEFAULT 0 ,
  PRIMARY KEY (`key`) )
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`k_functionnodes`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `k_functionnodes` (`key`, `page`, `entity`, `edit`, `create`, `delete`) VALUES ('masterdata', '', '', 0, 0, 0);
INSERT INTO `k_functionnodes` (`key`, `page`, `entity`, `edit`, `create`, `delete`) VALUES ('users_all', 'ENTITYLIST', 'USER', 1, 1, 1);
INSERT INTO `k_functionnodes` (`key`, `page`, `entity`, `edit`, `create`, `delete`) VALUES ('persons_all', 'ENTITYLIST', 'PERSON', 1, 1, 1);

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`t_functionnodes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`t_functionnodes` (
  `key` VARCHAR(36) NOT NULL ,
  `language_key` VARCHAR(2) NOT NULL ,
  `description` VARCHAR(20) NULL ,
  `description_long` VARCHAR(50) NULL ,
  PRIMARY KEY (`key`, `language_key`) ,
  INDEX fk_t_functions_k_languages (`language_key` ASC) ,
  INDEX fk_t_functionnodes_k_functionnodes (`key` ASC) ,
  CONSTRAINT `fk_t_functions_k_languages`
    FOREIGN KEY (`language_key` )
    REFERENCES `trimatrix`.`k_languages` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_functionnodes_k_functionnodes`
    FOREIGN KEY (`key` )
    REFERENCES `trimatrix`.`k_functionnodes` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`t_functionnodes`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `t_functionnodes` (`key`, `language_key`, `description`, `description_long`) VALUES ('persons_all', 'de', 'Personen', 'Personen');
INSERT INTO `t_functionnodes` (`key`, `language_key`, `description`, `description_long`) VALUES ('persons_all', 'en', 'Persons', 'Persons');
INSERT INTO `t_functionnodes` (`key`, `language_key`, `description`, `description_long`) VALUES ('users_all', 'de', 'Benutzer', 'Benutzer');
INSERT INTO `t_functionnodes` (`key`, `language_key`, `description`, `description_long`) VALUES ('users_all', 'en', 'Users', 'Users');
INSERT INTO `t_functionnodes` (`key`, `language_key`, `description`, `description_long`) VALUES ('masterdata', 'de', 'Stammdaten', 'Stammdaten');
INSERT INTO `t_functionnodes` (`key`, `language_key`, `description`, `description_long`) VALUES ('masterdata', 'en', 'Masterdata', 'Masterdata');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`t_roles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`t_roles` (
  `key` VARCHAR(36) NOT NULL ,
  `language_key` VARCHAR(2) NOT NULL ,
  `description` VARCHAR(20) NULL ,
  `description_long` VARCHAR(50) NULL ,
  PRIMARY KEY (`key`, `language_key`) ,
  INDEX fk_t_roles_k_languages (`language_key` ASC) ,
  INDEX fk_t_roles_k_roles (`key` ASC) ,
  CONSTRAINT `fk_t_roles_k_languages`
    FOREIGN KEY (`language_key` )
    REFERENCES `trimatrix`.`k_languages` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_roles_k_roles`
    FOREIGN KEY (`key` )
    REFERENCES `trimatrix`.`k_roles` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`t_roles`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `t_roles` (`key`, `language_key`, `description`, `description_long`) VALUES ('admin', 'de', 'Administrator', 'Administrator');
INSERT INTO `t_roles` (`key`, `language_key`, `description`, `description_long`) VALUES ('admin', 'en', 'Admin', 'Admin');
INSERT INTO `t_roles` (`key`, `language_key`, `description`, `description_long`) VALUES ('coach', 'de', 'Trainer', 'Trainer');
INSERT INTO `t_roles` (`key`, `language_key`, `description`, `description_long`) VALUES ('coach', 'en', 'Coach', 'Coach');
INSERT INTO `t_roles` (`key`, `language_key`, `description`, `description_long`) VALUES ('athlete', 'de', 'Athlet', 'Athlet');
INSERT INTO `t_roles` (`key`, `language_key`, `description`, `description_long`) VALUES ('athlete', 'en', 'Athlete', 'Athlete');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`t_languages`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`t_languages` (
  `key` VARCHAR(2) NOT NULL ,
  `language_key` VARCHAR(2) NOT NULL ,
  `description` VARCHAR(20) NULL ,
  `description_long` VARCHAR(50) NULL ,
  PRIMARY KEY (`key`, `language_key`) ,
  INDEX fk_t_languages_k_languages (`language_key` ASC) ,
  CONSTRAINT `fk_t_languages_k_languages`
    FOREIGN KEY (`language_key` )
    REFERENCES `trimatrix`.`k_languages` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`t_languages`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `t_languages` (`key`, `language_key`, `description`, `description_long`) VALUES ('de', 'de', 'Deutsch', 'Deutsch');
INSERT INTO `t_languages` (`key`, `language_key`, `description`, `description_long`) VALUES ('de', 'en', 'German', 'German');
INSERT INTO `t_languages` (`key`, `language_key`, `description`, `description_long`) VALUES ('en', 'de', 'Englisch', 'Englisch');
INSERT INTO `t_languages` (`key`, `language_key`, `description`, `description_long`) VALUES ('en', 'en', 'English', 'English');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`t_currencies`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`t_currencies` (
  `key` VARCHAR(3) NOT NULL ,
  `language_key` VARCHAR(2) NOT NULL ,
  `description` VARCHAR(20) NULL ,
  `description_long` VARCHAR(50) NULL ,
  PRIMARY KEY (`key`, `language_key`) ,
  INDEX fk_t_currencies_k_languages (`language_key` ASC) ,
  INDEX fk_t_currencies_k_currencies (`key` ASC) ,
  CONSTRAINT `fk_t_currencies_k_languages`
    FOREIGN KEY (`language_key` )
    REFERENCES `trimatrix`.`k_languages` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_currencies_k_currencies`
    FOREIGN KEY (`key` )
    REFERENCES `trimatrix`.`k_currencies` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`t_currencies`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `t_currencies` (`key`, `language_key`, `description`, `description_long`) VALUES ('eur', 'de', 'Euro', 'Euro');
INSERT INTO `t_currencies` (`key`, `language_key`, `description`, `description_long`) VALUES ('usd', 'de', 'Dollar', 'Dollar');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`t_countries`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`t_countries` (
  `key` VARCHAR(2) NOT NULL ,
  `language_key` VARCHAR(2) NOT NULL ,
  `description` VARCHAR(20) NULL ,
  `description_long` VARCHAR(50) NULL ,
  PRIMARY KEY (`key`, `language_key`) ,
  INDEX fk_t_countries_k_languages (`language_key` ASC) ,
  INDEX fk_t_countries_k_countries (`key` ASC) ,
  CONSTRAINT `fk_t_countries_k_languages`
    FOREIGN KEY (`language_key` )
    REFERENCES `trimatrix`.`k_languages` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_countries_k_countries`
    FOREIGN KEY (`key` )
    REFERENCES `trimatrix`.`k_countries` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`t_countries`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `t_countries` (`key`, `language_key`, `description`, `description_long`) VALUES ('at', 'de', 'Österreich', 'Österreich');
INSERT INTO `t_countries` (`key`, `language_key`, `description`, `description_long`) VALUES ('de', 'de', 'Deutschland', 'Deutschland');
INSERT INTO `t_countries` (`key`, `language_key`, `description`, `description_long`) VALUES ('at', 'en', 'Austria', 'Austria');
INSERT INTO `t_countries` (`key`, `language_key`, `description`, `description_long`) VALUES ('de', 'en', 'Germany', 'Germany');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`k_authorizations`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`k_authorizations` (
  `key` VARCHAR(36) NOT NULL ,
  `entity_key` VARCHAR(36) NULL ,
  `entity_field` VARCHAR(36) NULL ,
  `value_low` VARCHAR(100) NULL ,
  `exclusive` BOOLEAN NULL ,
  `value_high` VARCHAR(100) NULL ,
  PRIMARY KEY (`key`) )
ENGINE = MyISAM
COMMENT = 'Roles for authorization';


-- -----------------------------------------------------
-- Table `trimatrix`.`t_authorizations`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`t_authorizations` (
  `key` VARCHAR(36) NOT NULL ,
  `language_key` VARCHAR(2) NOT NULL ,
  `description` VARCHAR(20) NULL ,
  `description_long` VARCHAR(50) NULL ,
  PRIMARY KEY (`key`, `language_key`) ,
  INDEX fk_t_authorization_k_languages (`language_key` ASC) ,
  INDEX fk_t_authorization_k_authorization (`key` ASC) ,
  CONSTRAINT `fk_t_authorization_k_languages`
    FOREIGN KEY (`language_key` )
    REFERENCES `trimatrix`.`k_languages` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_authorization_k_authorization`
    FOREIGN KEY (`key` )
    REFERENCES `trimatrix`.`k_authorizations` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Table `trimatrix`.`users_have_authorizations`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`users_have_authorizations` (
  `user_id` VARCHAR(36) NOT NULL ,
  `authorization_key` VARCHAR(36) NOT NULL ,
  PRIMARY KEY (`user_id`, `authorization_key`) ,
  INDEX fk_users_have_authorizations_k_authorizations (`authorization_key` ASC) ,
  INDEX fk_users_have_authorizations_users (`user_id` ASC) ,
  CONSTRAINT `fk_users_have_authorizations_k_authorizations`
    FOREIGN KEY (`authorization_key` )
    REFERENCES `trimatrix`.`k_authorizations` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_have_authorizations_users`
    FOREIGN KEY (`user_id` )
    REFERENCES `trimatrix`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Table `trimatrix`.`t_sex`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`t_sex` (
  `key` VARCHAR(1) NOT NULL ,
  `language_key` VARCHAR(2) NOT NULL ,
  `description` VARCHAR(20) NULL ,
  `description_long` VARCHAR(50) NULL ,
  PRIMARY KEY (`key`, `language_key`) ,
  INDEX fk_t_sex_k_languages (`language_key` ASC) ,
  INDEX fk_t_sex_k_sex (`key` ASC) ,
  CONSTRAINT `fk_t_sex_k_languages`
    FOREIGN KEY (`language_key` )
    REFERENCES `trimatrix`.`k_languages` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_sex_k_sex`
    FOREIGN KEY (`key` )
    REFERENCES `trimatrix`.`k_sex` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`t_sex`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `t_sex` (`key`, `language_key`, `description`, `description_long`) VALUES ('m', 'de', 'männlich', 'männlich');
INSERT INTO `t_sex` (`key`, `language_key`, `description`, `description_long`) VALUES ('w', 'de', 'weiblich', 'weiblich');
INSERT INTO `t_sex` (`key`, `language_key`, `description`, `description_long`) VALUES ('m', 'en', 'male', 'male');
INSERT INTO `t_sex` (`key`, `language_key`, `description`, `description_long`) VALUES ('w', 'en', 'female', 'female');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`t_salutation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`t_salutation` (
  `key` VARCHAR(10) NOT NULL ,
  `language_key` VARCHAR(2) NOT NULL ,
  `description` VARCHAR(20) NULL ,
  `description_long` VARCHAR(50) NULL ,
  PRIMARY KEY (`key`, `language_key`) ,
  INDEX fk_t_salutation_k_languages (`language_key` ASC) ,
  INDEX fk_t_salutation_k_title (`key` ASC) ,
  CONSTRAINT `fk_t_salutation_k_languages`
    FOREIGN KEY (`language_key` )
    REFERENCES `trimatrix`.`k_languages` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_salutation_k_title`
    FOREIGN KEY (`key` )
    REFERENCES `trimatrix`.`k_salutation` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`t_salutation`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `t_salutation` (`key`, `language_key`, `description`, `description_long`) VALUES ('mr', 'de', 'Herr', 'Herr');
INSERT INTO `t_salutation` (`key`, `language_key`, `description`, `description_long`) VALUES ('mr', 'en', 'Mr.', 'Mr.');
INSERT INTO `t_salutation` (`key`, `language_key`, `description`, `description_long`) VALUES ('ms', 'de', 'Frau', 'Frau');
INSERT INTO `t_salutation` (`key`, `language_key`, `description`, `description_long`) VALUES ('ms', 'en', 'Ms.', 'Ms.');

COMMIT;

-- -----------------------------------------------------
-- Table `trimatrix`.`roles_have_functionnodes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `trimatrix`.`roles_have_functionnodes` (
  `role_key` VARCHAR(36) NOT NULL ,
  `functionnode_key` VARCHAR(36) NOT NULL ,
  `node` INT NULL ,
  `parent` INT NULL ,
  `order` INT NULL ,
  PRIMARY KEY (`role_key`, `functionnode_key`) ,
  INDEX fk_roles_have_functionnodes_k_roles (`role_key` ASC) ,
  INDEX fk_roles_have_functionnodes_k_functionnodes (`functionnode_key` ASC) ,
  CONSTRAINT `fk_roles_have_functionnodes_k_roles`
    FOREIGN KEY (`role_key` )
    REFERENCES `trimatrix`.`k_roles` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_roles_have_functionnodes_k_functionnodes`
    FOREIGN KEY (`functionnode_key` )
    REFERENCES `trimatrix`.`k_functionnodes` (`key` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM;


-- -----------------------------------------------------
-- Data for table `trimatrix`.`roles_have_functionnodes`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
INSERT INTO `roles_have_functionnodes` (`role_key`, `functionnode_key`, `node`, `parent`, `order`) VALUES ('admin', 'masterdata', 1, 0, 1);
INSERT INTO `roles_have_functionnodes` (`role_key`, `functionnode_key`, `node`, `parent`, `order`) VALUES ('admin', 'users_all', 2, 1, 1);
INSERT INTO `roles_have_functionnodes` (`role_key`, `functionnode_key`, `node`, `parent`, `order`) VALUES ('admin', 'persons_all', 3, 1, 2);

COMMIT;
CREATE SCHEMA IF NOT EXISTS `default` ;
USE `default`;

-- -----------------------------------------------------
-- Table `default`.`default_table`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `default`.`default_table` (
  `created_at` DATETIME NULL DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz erstellt am' ,
  `created_by` VARCHAR(36) NULL COMMENT 'Datensatz erstellt von' ,
  `modified_at` DATETIME NULL DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geändert am' ,
  `modified_by` VARCHAR(36) NULL COMMENT 'Datensatz geändert von' ,
  `test` BOOLEAN NULL DEFAULT 0 ,
  `deleted` BOOLEAN NULL DEFAULT 0 COMMENT 'Datensatz gelöscht' )
ENGINE = MyISAM;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
