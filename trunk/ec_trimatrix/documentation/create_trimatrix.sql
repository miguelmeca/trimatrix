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

/*Data for the table `attachments` */

insert  into `attachments`(`id`,`category_key`,`description`,`owner_id`,`mime_type`,`file_name`,`file_size`,`file_content`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('b8a954e4-4bca-11de-ab35-74df036e1e4f','certificat','Attest Test','0b0b7658-2ddb-11de-86ae-00301bb60f17','application/pdf','20080908.pdf',18806,'%PDF-1.4\n%����\n2 0 obj<</P 3 0 R/Type/Annot/F 132/T(Signature1)/V 1 0 R/Rect[0 0 0 0]/Subtype/Widget/FT/Sig>>\nendobj\n1 0 obj<</Filter/Adobe.PPKMS/Type/Sig/Contents <30820ebb06092a864886f70d010702a0820eac30820ea8020101310b300906052b0e03021a0500302306092a864886f70d010701a0160414a3939baa417b8f344e2be299336cd946bf5fcdeda0820d1d308204b33082039ba003020102020302f2f9300d06092a864886f70d010105050030819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033301e170d3037303232303135343535385a170d3132303232303135343535385a305b310b3009060355040613024154310e300c060355040a0c054241574147310b3009060355040b0c0249543118301606035504030c0f424157414720502e532e4b2e204147311530130603550405130c33343038393431313432383930819f300d06092a864886f70d010101050003818d00308189028181009f18e18acafeced47e87948327150690350f940b496d3d832b80568e1578be143898e395bd10d5863bab7400f0b3f7c41052e298169f96ae84f3931805bae5fb150b191a80cee84538a05cce009c4457f3b059e34f5d9668a7317ced1a8db2ea88079ce7685599fd678ebe8a41154bdb18ec239098f4ee063944d4142ccb585b0203010001a38201bd308201b930130603551d23040c300a80084191691cbfadd898305506082b0601050507010104493047304506082b060105050730028639687474703a2f2f7777772e612d74727573742e61742f63657274732f612d7369676e2d636f72706f726174652d6c696768742d30332e63727430580603551d200451304f304d06072a2800110107013042304006082b060105050702011634687474703a2f2f7777772e612d74727573742e61742f646f63732f63702f612d7369676e2d636f72706f726174652d6c6967687430819e0603551d1f048196308193308190a0818da0818a8681876c6461703a2f2f6c6461702e612d74727573742e61742f6f753d612d7369676e2d636f72706f726174652d6c696768742d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f7269747930110603551d0e040a0408487cf8373c3f8bb6300e0603551d0f0101ff0404030204b030220603551d11041b3019811769742d736963686572686569744062617761672e636f6d30090603551d1304023000300d06092a864886f70d01010505000382010100776e67497156b2bcb608d9de5dcf85efbaa18859128f1934e0ee4ecab2f94ccee454e8610e6be5b6c37eb49e4c8026363d107ce5b3e62bea1b4927cd9ed152d430a77976215fabbe75b27a8f452c3f1e8943bc28339dfc241c2a3fc66872919067c1420a7e010933947e324117d118bfa2cb732d845c80b3aecd37ee481323fe9166bdad16d619298ee502c5533254e8bdb1f90ab97c9a4c343deb368f3def13c4ba4d66fc4861f5af516cd6d94e1b5ebddf5e8eceb331a7fb2c9bfb0d210c0d8639f81ffd9d2cbf50e50ba200828415e8dabad043cbfd95355915ab7f3542ce80c6d90b5d1b72d1d03c9370e7c9f90909b76f870ebdf248b918da2e705259d73082048f30820377a003020102020301aaed300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035313131333233303030305a170d3135313131333233303030305a30819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100b7e7da22b5b1e490481d12b44f78176471844ad832ddc5f7279fe488ba6484633595b1c4537213ca4b36fb7f56ba1073db079599b0625a5035fd29f4eab0934ca3eedb0122a4de0b0b571359a98dadd86fb2f07bd111861e8c3388ce302470142bb7e9efb8152519854942312ec809f5f1fdaf1b463754dbd91462a73caf00572714324d14c36d516cadef5f9b5b6cf810fe471990de1cce2e551dea4cbdfea1030c0ba098fe28bf73c66f23c8f2bcf2a0812808cc342a592ac121c9a9f0d428d86d5987b38e50e56e8506da83712e88ab8982e93b543c6b424eea07eff24bba1129d3be68869f3bbf77d297378b2630699140cff54f13bfb9767ae0f4b755150203010001a381e33081e0300f0603551d130101ff040530030101ff30110603551d0e040a04084191691cbfadd89830130603551d23040c300a8008446a95675579114f300e0603551d0f0101ff0404030201063081940603551d1f04818c308189308186a08183a08180867e6c6461703a2f2f6c6461702e612d74727573742e61742f6f753d412d54727573742d6e5175616c2d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f72697479300d06092a864886f70d010105050003820101000d3448690b4fc283d2ebf4e9c8184ec38c001e4fd13388242d3efa5113d8dac5078a1c6acbef2a10494df9fe6523ad66c823f72054c295f95c1d5475402a0c834648e7f11c41323c0e0779890917b958e1df00a3f3b29ad93bbb7752be8bd549e0147fa61fea98f291ecbc7f3ed013c3fbd6a38936adbb79a354308b167b08fb7e045f7109a002e2e2f46c1d0cf4cde2f56d9e3e6a97e96201de33244fd1500b42a284f3dfc3fd7b29d74d8b8ac530c1224f22af253b53a839c80305964eab0bd46ee8c4c32d9e761a296310ea04223a2473098ede7dc022ff7630517e942d6e73c6a6a936cd6fa846d21ac40497578597aec70591d800f7681c6527645d0a69308203cf308202b7a0030201020203016c1e300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035303831373232303030305a170d3135303831373232303030305a30818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100ad3d616e03f3903bc0410b8480cdec2aa39d6bbb6ec24284f75114e1a0a82d51a351f2de23f03444ff94ebcc05239540b90778a525f60abd4586e8d9bdc0048e854461ef7fa7c9fac125cc852c633f0560734905e0607895104bdcf91159ce717f409b8aaa24df0b42e2db56bc4ad2a50c9bb7433edd83d3261002cfea23c4494ee5d3e9b488ab0cae6292d46587d96ad7f4859fe4332225a5e5c833bac3c741dc5fc66acc000e6d32a8b687360062779b1e1f34cb903c78887405eb79f5937165ca9dc76b182d3d5c4ee7d5f83f317d8f87ec0a222f23e9febb7dc9e0f4eceb7cc4b0c32d62b59a71d6b16ae8ecd9edd572ecbe5701ce05559fded1608810b30203010001a3363034300f0603551d130101ff040530030101ff30110603551d0e040a0408446a95675579114f300e0603551d0f0101ff040403020106300d06092a864886f70d0101050500038201010055d454d159485cb39385aabf632fe480ce34a334623ef6d8ee67883104036f0bd407fb4e750fd32ed3c017c7c628ec060d11240e0ea55dbf8cb2139671dcd4ce0e0d0a68326cb9413119abb1077b4d98d35cb0d1f0a742a0b5c48eaffef13ff4ef4f460076eb02fbf99dd24096c7883ab89f1179f38065a8bd1fd37881a0514c37b4a65d2570d166c968f92e111468f1549808ac26920fde899ed4fab3792bd2a379d4ec8bac875368424c5151741e1b272ee3f51f29744dedaff7e1929981e8be3ac71750f6b7c6fc9bb08a6bd68803918f06773a8502dd98d543783fc63015ac9b6bcb57b789518b3ae8c9840cdbb150200a1a4aba6a1abdec1bc8c5849acd3182014e3082014a0201013081a730819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033020302f2f9300906052b0e03021a0500300d06092a864886f70d01010105000481806452bd278af0c176438b49ad48756ef60aa7d0badffe78407ee18e1b9dbab2036362b5314ac3dfe6b2a29164063ae49d6346fd126c4f8784c363231d9987565076313d509b3321bf733910119405ebb8903601f87e721a425ebc2075284692f6706b7aecadf8f6a2f4f0c4cb9d17aaf87ce597e78e5b7aef4807be6de1d9fa3800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000>/M(D:20080923065445+02\'00\')/Name(BAWAG P.S.K. AG)/SubFilter/adbe.pkcs7.sha1/ByteRange [0 164 7844 10962 ]                                                             >>\nendobj\n4 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1530/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Y�R�H}�W�q����IH�bI8qվ	<�m�Յ��C��y��dK��\ZK�|NO_Nw˃�a�\np祾�\"$�+\r	��r�ڹ�}C�V!������\'痫���׃/��q�8FܠP��~�:�\n��$�o��#�u����k.�n\n&��	L���B��Y׆HT�]\r~���i�y��揯\r&�y�;�8��Q�K�hX��c$$7����	CJT�^1�˸(�F�x��a�{<��,\nE��B\n�r<�ӷ=�`6�~�$�~����N�֫7=�`E{,�ԥ9���!QT%����v�i�N��Jg��̅��(�����>������ad��/��6��x.t����t�{.�!��K�^9\'/�tv�7��7M?���\n/��h��W���\\(!���B���9��DE]hm���^�L�?�G�W��R�*�X�(��2�0�q:�����A�iD�A�ԑ��TN�oc�ph�\',L#�~FnP�Q�Mjs�dS<�:�{p����K��E���E�;a���D�����f��=p(� ��^�ar�}_fw��C�E����ڭ 4��ae��C2��<DR����6��,v��#˘�eN;�����p�KgE�t\0/}Ç�z�����C�\0X��2g�����zJ���g��?��*:��J����0�Eb��ӿ����,�m�u!k*��dn\'�j�(J�\\��C���&��d�,Iq9�3����E���L���m����<�z�Z�����k�VS�;1Bx����*���!�ƌ������K���Ow�(��m�tv(\Z�?tq�Um[BTZu�FG�,�{�Ǝb�ԅ��r��镨M��6�$�_�o�1$�+{�O��F�\r;����U�0��6�$�|��.;O�\\FQ����<\"{�uT��c��r��kX��������8�Y ����M�۰C)<�L0L!�>��9bvB�+<��q��g���efX��n@\\�6߷-��5�m��<�W_- f��7\'W��f=י=g\nG.��$M��a0]W�����z����%�b�\\fL��V������t�?f�P�8��BW�g	�)z�ΰ�Q+���ck��~�ݪ��Y>_�\\�\'өwT9H���(�����Oǟ^�����ȣvO4�0n�|I�h�|(ќ.ܞ���2��%�ֻC��t�x�M0A�2��i�-�+t�.��\r��G�ױWWr�H����B�:\0}��8-�>�42�����9�\n���,�|x��Zܶ\0�:E`@��<��ub3y�B(Ea���C�.챧-�m�O\Zsq}��]����m�_$M��]h\"%�����&�!̕P�ΥG�)�9B�C�ouv��iO��42�zм?\"]�Z!Y-aգ�o���\\��T�����D�����j�w�1����Ch��aJu�����?`��ʰ��z7��m`�18��E0����V\'LF�`�d�����/̱v�J��`v��t�<��cQ��C+_.����`�e\nendstream\nendobj\n10 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G�*��+r*�s��u�+Y\Z�(�rs\0K�\nendstream\nendobj\n11 0 obj<</Count 4/Type/Pages/Kids[3 0 R 12 0 R 13 0 R 14 0 R]>>\nendobj\n3 0 obj<</Type/Page/Contents 10 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf1 4 0 R>>>>/Annots[2 0 R]/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n15 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1664/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Z�R�8��)tܭ\"B��|4�a�26	P�����Y��N���{ؖ��D��qr	1�׭���Gv���>�8_��<��|����7/��}�V��\r�\Z+�i������;�N��z���	��AaLc��$�8���0۸|�]iw4�~*ե�\"4��Q°�h��]�yi���ޗ�/���yr9����W4���]l0�ȋc�t�8΢�\\[\\�MEQ%Beʱ��U^12��gy�}3ڒCY�Np�A\r݋�q CcM�2~,���D�2�)\"t\'��fB�ɺ�,^:݉���^��p��\0�H��bKRb*��Y������%�6K3��p��b,������fd�-�aM�<�P�|{��(.�&œg�^g���[lÛm��@AV1�����3��\Z*@#��M�`�e����J����l��4k��x����(v1�+�X2p�qO���$.Ԋ�6��r�\r�At]��!�-�\r�\n��\"��6��a��h���*h�m!�Z�g�>�<xQIs���J�QG���u�yQy���V�>#�L��8�B���-mأ�m���D�Y�̳�GU��f�%Y�Ɩ1:>$��끇�^\n��lx�\n�S6�r)E�a���>k9�=M�z�Mݜ�+/R�����`�N���t38��8+�j���5\nf2���<~�UA\'\"TWÑ\0]5�L�@��×#D���\"��78�AEg��a�Eo[�EoOIlMo^���m���C����Q�i�-/^��ǻ�`!�#���YȄ��v�B�X\rXX�װ��U\',�ZX�ے���,t`�����4��#ӈ1�}D��hp�Dt��VD�q�%x��wD�`G.mAD%m~+\"M���XE�g�}���>]d�h�����v�M`�����78�_vkb��5L�%��퀌�Q]Sp1�l4J���%S��{���G����ܖd,m\r��>�\Z�!�\ruR�t|\0IyT����Km��ʥղ3�Je����i�2��9��l~�v�)t���S,�Ug$�\Zj�>`�pͶ�l\"�;nR��=���^�r��nft���S�S�1�u�{�L&���l:N�̏��r����\n~O��lxv~}�M*��}`G����Nߑau����m�a�S��ۦ.����E\"�n�>��5iN.�N�xSTܟ�Z�*c�Ӊ�N&l��\n#D�b��g�����ȶմv�����eG��\rO�o�m�o� �����-��f��(�Ěa?�W_&��pۃ:�3~۠��Z�;H׶�&|3�aϴ���Xӛ���F5�Ao[��nϑO[v;v�j�A�;U�ׅ��q�(�����rz>Jv��0 GZ��~N���T�0���&\"b��Qa�� ��}m];¨h��Q�E[�E�Qc�nױKG|Q{�M�jӠ�V���!�\\�DTA���fh�8�~�Z�ibV���5�}ꪣ��Y �\Z\'W�\'��b�r���t�����.��A��P���1\Z\r��RB/����� ����l��m�0kJy���SE*�]\'��O�#sȂ���P�}Ks�:�E׏Y��y�ݕ?�^�i����p5E�e��>˞f�e����1�ߧ���������uI_\nendstream\nendobj\n16 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G�)��+r*�s��u�+Y\Z�(�rs\0t�\nendstream\nendobj\n12 0 obj<</Type/Page/Contents 16 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf2 15 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n17 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1634/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Y�r�F}�+�)�T�x�\Z=�6�)�e�������ر�\rW�ߗo��3򐖄�4\n\nU[`�t������Q���>��������)�}?g{���Q�n��8��ǎ�~���~<=t�h|m4N{\'1\nc\Z��x\'��J�����۷�ѭw�a��K�.MnB�s���D��iWX�ih�ɳ��Ͻ� ��o��/(�\r��b�vh�����Rs���]Ӝ�E*���6�X�Ć�^�m\\�O��Ӌц���p�Ձ��}���ƚ���X��]��i��ו�Ηې�*L����bV�J$g�?�/�2^�v����a?Y���ʌ;��z1��f����*_\n<3c�?�����=�;�E~�����s-Q�Y}n���Kv���=FO��ZO�O\'b��c��� ��y|��pjs^C(E� ��2}����H%ibrK4�!ZVP&^�L�X$���9�X��8�n�pq�m�l·\'c��Ѭ|=�\'��\n�n3�Ď�¢q��Ѝơ��A����{y������\'x��}��t��Y�e�J3�rXcy���gdg�޶��@�;-M؀�q��f0X�G����D\'���4�|r:��vt!��p�,(�ʹ��n�<Tŷ�����AJI--����A1$�\n�	?�\\�䣭�q��:���i/�=H�WD)EZ�\nHM_��ʫ�ڻ��I+�(Jw�*}\0>p�*���{��4�������0V얢N��ú<��RL���Lڃ�m�(���@9�C��(堐+L�=PB�\Z�G��b�d�1�qѴ0�i��4w(.9M��뜇�I�QE$��sfK���4A���E΂�\'�Y�(��nD�����rF�lܫO_ޡ5E�i�Z/v]Q�ۗgG�H�*�J����٬\ZbC������y����;��`\0:��k\rSD/�m����y/@��IJ�h�����1,_�	K���QOi\n\Z}w�o���Ǩӿ�A?���Y둰;G,c_Xzo*�v�d�6=^S.��0ڋ\'�s�R;�N�&�\r�S���+=ciw�nP?�&w��a.�`��ۮ�ߌ�h��\r��`��1 \05U���]��5�*��B��Jo��oJo\Z1���ib���Ԧ�&j�Jﺏ���cJO��Sz?Sz�[�*&%�fw���],	�G��bLػQ0Fװ\"�ܸ��q�x�ਮ>��*8�i���ק,Ļ�zפ\\&��\\��T�O/�[�*=M�&cu�ӄ=)�ՈB�1�ى�ڜ(&�m�=����s��T0eݥď�F[0~�!i�3��\rE8a�#ԧ5��wg@�ֺU�i^؈R5�MЦ�u�s�A?^��\"��R�6T�C�7t�h-W�y4����Ǘ(~����NR3R��mYTa�ޏ��Ο��9�Z�f����LE�5mzkK�����έ�P�Y�mZA+ffa��ĄLs�9�(L��aT�Q�w�z�����^I�oi�1)1�g�E�^��U0�ά_�[��s��w@�)�\'��~��vZn��Ɣd�����\0���{\"��GD_�hˢo/��X��*���L=erڴ>4�w����t����Y�`�=z]���	=�.׫Ǘ�s��_�u�\\Ư�^�j�F��z�c�|��V\n�֯w���.�_��Q�\nendstream\nendobj\n18 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G�+��+r*�s��u�+Y\Z�(�rs\0��\nendstream\nendobj\n13 0 obj<</Type/Page/Contents 18 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf3 17 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n19 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1504/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Y�R�8��)tܭ\"B��|t 0�dC�ڽe3d\'?��Cm���{ؖ�8r��9@p�[�_�;u(&��H���\"�2Y@q����b��n��*\nܭ,��k�ر��o��_^	�\\u~�tN{\'�1�I�#�I.q��Q��}��xכtί\nwi~��v(aXJ4�8�\n˼m�e������~2��x��M�0��O��3]k��`c�6�2BQPڀ��Z�r�enCW���|�e��-;����v��Z4C\Zk�o�}�lw%�L�X�+���ې@&�3��Z����EL]�j��p�ڑ\n�����Tnkg��V��b\'ǭ�A�R��[3��o�z��82d���a���G\'(Z�k7���s<��<8i6]~9^��~\0%��\n_����d�r^�P�� ��2=��B���C����\r�\'\r���g���|�!]V!�uA���Sg�����)e��8v��i�nD�A����TA7����R84����8��nP�Q�[j�նx����͉J��*��:�&��i�y��\Z�7��}Fv��!�=�R�Ӧ�ei��;\"�0�@\'�����qtw���㇛�O����5�M0Y/	R��>o�,S=Y/�R�B+�gRt�~��	�o�7�w��?�;ػ�s�L��$v��m�r������Qt�\0(l�*�F�!ߡ��w���8Ĩ@焞n<�^0�b���H\n���*,=�W�B�+���s��h\Z���%L���i��ذ��c�6��4N�d�LP�]`l��20�!��.ІBPmO�����Ϋ̢�6�Oë�]F�����6`�]�B��z���\Z�\03���뫂V�__M�x��a�cz���<q�q`n�RyÄd��I�0�B���F���9���m�hp4������Ȉ�+\"f\0i ׶�:Od�Rv��sztt�*��O��*�v��Z���jlr� 	�ܖ\0UxFOv\"M���V�a�Ʋ��\r;��w/��%�zc�~�4�Zs`�\n�w�fT3ۚ66��8���8`��s�R7\\A=��8�K�1m vP��\"�KA�a�(���檏z�1��A�S;�7|����؛�;tc�x���S�Ycc>�v|d.�nړ���ڔ�6�iM}S�BL�\'ʭ��>s	�B��3���1�QO&z�Q�˰a�8�]�a�5 ��{�/	�8k,�6<�m1�Fm��ީ���g(�Fwp�sA$�\"s�\Z\'��-����),\rYH��R���r��?[Χ_�⡍l��T8$���qj^���D�E&�������+�R��L\Z*h��p{�Y�2w��>{���.�;�,�rzr������́s�Z+q�h�-���=d�-��-�������#��G�{�ݦ��\\ì���b��R[��9�FE�z��7��_���s<��z�=ϒ,}y�����fq��D��j�F�4K�C~�i\Z��[6��_�4�ϋ���?:W��\nendstream\nendobj\n20 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G��(��+r*�s��u�+Y\Z�(�rs\0��\nendstream\nendobj\n14 0 obj<</Type/Page/Contents 20 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf4 19 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n7 0 obj[/PDF/Text/ImageB]\nendobj\n5 0 obj <</Type/XObject/Filter/FlateDecode/Length 80/Height 30/BitsPerComponent 1/Subtype/Image/Name/IMmpdf1/ImageMask true/Width 2232/Decode[1 0]>>stream\nx���1\00���/��-��d��)hь��hCg��]m�,R=zB�Y�z� ���H��AEg��у��\"գ!�E��\0h�Fq\nendstream\nendobj\n6 0 obj <</Type/XObject/Filter/FlateDecode/Length 700/Height 78/BitsPerComponent 1/Subtype/Image/Name/IMmpdf0/ImageMask true/Width 360>>stream\nx���;��0`G)B�[\n�Y�\r��2K��v�v��@�(ق�`D��\"��?v�c\"d�H���sl���z��˝G�o��:�C��n6ð��ax��aX��~vð���A| G?�r��	��꒣_j�qŔb�\n��^ -\nq�m\\��h��\Z]�uS�~���\0V��M�]�y\"�cC���ێ�M�X��.⠁Y�/�y�xՂ��[�u�{�	{�;�cSq0�x#�:F����%S�F�9��#�e\'ަ����\r]�J�d\'f8g�q�k����`#8.���u���b8qI�~u�s0���jq����2^cp�0:��p�D�c�g��Ur��`��\'%l������Mh�I������ۆ.d�I�PV�@i�|#H\r|�Z�*�%7$+��Z�J����\r��uþa��,�5��\'~��bt/���\"�8��E&&�%�mR�<u��eA����ib�\\ptI��4vqI-7d+s����N\r\'#�X�[�&�\r�G`ǔ�Y��b���;�\\i�\"���_�\'ļpY�+�K�]�h1-}��vi8��q��#��w,�$ q��R ��ꎽ���V7鹿cw���T�U$���/�8Y���Y���Z�1�IadOw�l\\?���mRV�91z���ogY�o���������oܒm��7O���������G��a���V����\nendstream\nendobj\n8 0 obj<</Type/Font/BaseFont/Helvetica-Bold/Subtype/Type1/Name/Fmpdf1/Encoding/WinAnsiEncoding>>\nendobj\n9 0 obj<</Type/Font/BaseFont/Helvetica/Subtype/Type1/Name/Fmpdf0/Encoding/WinAnsiEncoding>>\nendobj\n21 0 obj<</Type/Catalog/AcroForm<</SigFlags 3/Fields[2 0 R]>>/Pages 11 0 R>>\nendobj\n22 0 obj<</CreationDate(D:20080923065445+02\'00\')/Producer(iText 1.4.8 \\(by lowagie.com\\))/ModDate(D:20080923065445+02\'00\')>>\nendobj\nxref\n0 23\n0000000000 65535 f \n0000000117 00000 n \n0000000015 00000 n \n0000010019 00000 n \n0000008020 00000 n \n0000016736 00000 n \n0000016989 00000 n \n0000016703 00000 n \n0000017850 00000 n \n0000017954 00000 n \n0000009802 00000 n \n0000009947 00000 n \n0000012262 00000 n \n0000014463 00000 n \n0000016534 00000 n \n0000010200 00000 n \n0000012117 00000 n \n0000012431 00000 n \n0000014318 00000 n \n0000014632 00000 n \n0000016389 00000 n \n0000018053 00000 n \n0000018137 00000 n \ntrailer\n<</Root 21 0 R/Size 23/Info 22 0 R>>\nstartxref\n18269\n%%EOF\n','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-08 23:41:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0),('aa5fe149-d31e-4809-802c-15d8dc3419a2','certificat','Test für Mach','7522bc7f-42cf-415c-a050-da12518a4cd3','application/pdf','20080908.pdf',18806,'%PDF-1.4\n%����\n2 0 obj<</P 3 0 R/Type/Annot/F 132/T(Signature1)/V 1 0 R/Rect[0 0 0 0]/Subtype/Widget/FT/Sig>>\nendobj\n1 0 obj<</Filter/Adobe.PPKMS/Type/Sig/Contents <30820ebb06092a864886f70d010702a0820eac30820ea8020101310b300906052b0e03021a0500302306092a864886f70d010701a0160414a3939baa417b8f344e2be299336cd946bf5fcdeda0820d1d308204b33082039ba003020102020302f2f9300d06092a864886f70d010105050030819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033301e170d3037303232303135343535385a170d3132303232303135343535385a305b310b3009060355040613024154310e300c060355040a0c054241574147310b3009060355040b0c0249543118301606035504030c0f424157414720502e532e4b2e204147311530130603550405130c33343038393431313432383930819f300d06092a864886f70d010101050003818d00308189028181009f18e18acafeced47e87948327150690350f940b496d3d832b80568e1578be143898e395bd10d5863bab7400f0b3f7c41052e298169f96ae84f3931805bae5fb150b191a80cee84538a05cce009c4457f3b059e34f5d9668a7317ced1a8db2ea88079ce7685599fd678ebe8a41154bdb18ec239098f4ee063944d4142ccb585b0203010001a38201bd308201b930130603551d23040c300a80084191691cbfadd898305506082b0601050507010104493047304506082b060105050730028639687474703a2f2f7777772e612d74727573742e61742f63657274732f612d7369676e2d636f72706f726174652d6c696768742d30332e63727430580603551d200451304f304d06072a2800110107013042304006082b060105050702011634687474703a2f2f7777772e612d74727573742e61742f646f63732f63702f612d7369676e2d636f72706f726174652d6c6967687430819e0603551d1f048196308193308190a0818da0818a8681876c6461703a2f2f6c6461702e612d74727573742e61742f6f753d612d7369676e2d636f72706f726174652d6c696768742d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f7269747930110603551d0e040a0408487cf8373c3f8bb6300e0603551d0f0101ff0404030204b030220603551d11041b3019811769742d736963686572686569744062617761672e636f6d30090603551d1304023000300d06092a864886f70d01010505000382010100776e67497156b2bcb608d9de5dcf85efbaa18859128f1934e0ee4ecab2f94ccee454e8610e6be5b6c37eb49e4c8026363d107ce5b3e62bea1b4927cd9ed152d430a77976215fabbe75b27a8f452c3f1e8943bc28339dfc241c2a3fc66872919067c1420a7e010933947e324117d118bfa2cb732d845c80b3aecd37ee481323fe9166bdad16d619298ee502c5533254e8bdb1f90ab97c9a4c343deb368f3def13c4ba4d66fc4861f5af516cd6d94e1b5ebddf5e8eceb331a7fb2c9bfb0d210c0d8639f81ffd9d2cbf50e50ba200828415e8dabad043cbfd95355915ab7f3542ce80c6d90b5d1b72d1d03c9370e7c9f90909b76f870ebdf248b918da2e705259d73082048f30820377a003020102020301aaed300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035313131333233303030305a170d3135313131333233303030305a30819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100b7e7da22b5b1e490481d12b44f78176471844ad832ddc5f7279fe488ba6484633595b1c4537213ca4b36fb7f56ba1073db079599b0625a5035fd29f4eab0934ca3eedb0122a4de0b0b571359a98dadd86fb2f07bd111861e8c3388ce302470142bb7e9efb8152519854942312ec809f5f1fdaf1b463754dbd91462a73caf00572714324d14c36d516cadef5f9b5b6cf810fe471990de1cce2e551dea4cbdfea1030c0ba098fe28bf73c66f23c8f2bcf2a0812808cc342a592ac121c9a9f0d428d86d5987b38e50e56e8506da83712e88ab8982e93b543c6b424eea07eff24bba1129d3be68869f3bbf77d297378b2630699140cff54f13bfb9767ae0f4b755150203010001a381e33081e0300f0603551d130101ff040530030101ff30110603551d0e040a04084191691cbfadd89830130603551d23040c300a8008446a95675579114f300e0603551d0f0101ff0404030201063081940603551d1f04818c308189308186a08183a08180867e6c6461703a2f2f6c6461702e612d74727573742e61742f6f753d412d54727573742d6e5175616c2d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f72697479300d06092a864886f70d010105050003820101000d3448690b4fc283d2ebf4e9c8184ec38c001e4fd13388242d3efa5113d8dac5078a1c6acbef2a10494df9fe6523ad66c823f72054c295f95c1d5475402a0c834648e7f11c41323c0e0779890917b958e1df00a3f3b29ad93bbb7752be8bd549e0147fa61fea98f291ecbc7f3ed013c3fbd6a38936adbb79a354308b167b08fb7e045f7109a002e2e2f46c1d0cf4cde2f56d9e3e6a97e96201de33244fd1500b42a284f3dfc3fd7b29d74d8b8ac530c1224f22af253b53a839c80305964eab0bd46ee8c4c32d9e761a296310ea04223a2473098ede7dc022ff7630517e942d6e73c6a6a936cd6fa846d21ac40497578597aec70591d800f7681c6527645d0a69308203cf308202b7a0030201020203016c1e300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035303831373232303030305a170d3135303831373232303030305a30818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100ad3d616e03f3903bc0410b8480cdec2aa39d6bbb6ec24284f75114e1a0a82d51a351f2de23f03444ff94ebcc05239540b90778a525f60abd4586e8d9bdc0048e854461ef7fa7c9fac125cc852c633f0560734905e0607895104bdcf91159ce717f409b8aaa24df0b42e2db56bc4ad2a50c9bb7433edd83d3261002cfea23c4494ee5d3e9b488ab0cae6292d46587d96ad7f4859fe4332225a5e5c833bac3c741dc5fc66acc000e6d32a8b687360062779b1e1f34cb903c78887405eb79f5937165ca9dc76b182d3d5c4ee7d5f83f317d8f87ec0a222f23e9febb7dc9e0f4eceb7cc4b0c32d62b59a71d6b16ae8ecd9edd572ecbe5701ce05559fded1608810b30203010001a3363034300f0603551d130101ff040530030101ff30110603551d0e040a0408446a95675579114f300e0603551d0f0101ff040403020106300d06092a864886f70d0101050500038201010055d454d159485cb39385aabf632fe480ce34a334623ef6d8ee67883104036f0bd407fb4e750fd32ed3c017c7c628ec060d11240e0ea55dbf8cb2139671dcd4ce0e0d0a68326cb9413119abb1077b4d98d35cb0d1f0a742a0b5c48eaffef13ff4ef4f460076eb02fbf99dd24096c7883ab89f1179f38065a8bd1fd37881a0514c37b4a65d2570d166c968f92e111468f1549808ac26920fde899ed4fab3792bd2a379d4ec8bac875368424c5151741e1b272ee3f51f29744dedaff7e1929981e8be3ac71750f6b7c6fc9bb08a6bd68803918f06773a8502dd98d543783fc63015ac9b6bcb57b789518b3ae8c9840cdbb150200a1a4aba6a1abdec1bc8c5849acd3182014e3082014a0201013081a730819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033020302f2f9300906052b0e03021a0500300d06092a864886f70d01010105000481806452bd278af0c176438b49ad48756ef60aa7d0badffe78407ee18e1b9dbab2036362b5314ac3dfe6b2a29164063ae49d6346fd126c4f8784c363231d9987565076313d509b3321bf733910119405ebb8903601f87e721a425ebc2075284692f6706b7aecadf8f6a2f4f0c4cb9d17aaf87ce597e78e5b7aef4807be6de1d9fa3800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000>/M(D:20080923065445+02\'00\')/Name(BAWAG P.S.K. AG)/SubFilter/adbe.pkcs7.sha1/ByteRange [0 164 7844 10962 ]                                                             >>\nendobj\n4 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1530/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Y�R�H}�W�q����IH�bI8qվ	<�m�Յ��C��y��dK��\ZK�|NO_Nw˃�a�\np祾�\"$�+\r	��r�ڹ�}C�V!������\'痫���׃/��q�8FܠP��~�:�\n��$�o��#�u����k.�n\n&��	L���B��Y׆HT�]\r~���i�y��揯\r&�y�;�8��Q�K�hX��c$$7����	CJT�^1�˸(�F�x��a�{<��,\nE��B\n�r<�ӷ=�`6�~�$�~����N�֫7=�`E{,�ԥ9���!QT%����v�i�N��Jg��̅��(�����>������ad��/��6��x.t����t�{.�!��K�^9\'/�tv�7��7M?���\n/��h��W���\\(!���B���9��DE]hm���^�L�?�G�W��R�*�X�(��2�0�q:�����A�iD�A�ԑ��TN�oc�ph�\',L#�~FnP�Q�Mjs�dS<�:�{p����K��E���E�;a���D�����f��=p(� ��^�ar�}_fw��C�E����ڭ 4��ae��C2��<DR����6��,v��#˘�eN;�����p�KgE�t\0/}Ç�z�����C�\0X��2g�����zJ���g��?��*:��J����0�Eb��ӿ����,�m�u!k*��dn\'�j�(J�\\��C���&��d�,Iq9�3����E���L���m����<�z�Z�����k�VS�;1Bx����*���!�ƌ������K���Ow�(��m�tv(\Z�?tq�Um[BTZu�FG�,�{�Ǝb�ԅ��r��镨M��6�$�_�o�1$�+{�O��F�\r;����U�0��6�$�|��.;O�\\FQ����<\"{�uT��c��r��kX��������8�Y ����M�۰C)<�L0L!�>��9bvB�+<��q��g���efX��n@\\�6߷-��5�m��<�W_- f��7\'W��f=י=g\nG.��$M��a0]W�����z����%�b�\\fL��V������t�?f�P�8��BW�g	�)z�ΰ�Q+���ck��~�ݪ��Y>_�\\�\'өwT9H���(�����Oǟ^�����ȣvO4�0n�|I�h�|(ќ.ܞ���2��%�ֻC��t�x�M0A�2��i�-�+t�.��\r��G�ױWWr�H����B�:\0}��8-�>�42�����9�\n���,�|x��Zܶ\0�:E`@��<��ub3y�B(Ea���C�.챧-�m�O\Zsq}��]����m�_$M��]h\"%�����&�!̕P�ΥG�)�9B�C�ouv��iO��42�zм?\"]�Z!Y-aգ�o���\\��T�����D�����j�w�1����Ch��aJu�����?`��ʰ��z7��m`�18��E0����V\'LF�`�d�����/̱v�J��`v��t�<��cQ��C+_.����`�e\nendstream\nendobj\n10 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G�*��+r*�s��u�+Y\Z�(�rs\0K�\nendstream\nendobj\n11 0 obj<</Count 4/Type/Pages/Kids[3 0 R 12 0 R 13 0 R 14 0 R]>>\nendobj\n3 0 obj<</Type/Page/Contents 10 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf1 4 0 R>>>>/Annots[2 0 R]/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n15 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1664/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Z�R�8��)tܭ\"B��|4�a�26	P�����Y��N���{ؖ��D��qr	1�׭���Gv���>�8_��<��|����7/��}�V��\r�\Z+�i������;�N��z���	��AaLc��$�8���0۸|�]iw4�~*ե�\"4��Q°�h��]�yi���ޗ�/���yr9����W4���]l0�ȋc�t�8΢�\\[\\�MEQ%Beʱ��U^12��gy�}3ڒCY�Np�A\r݋�q CcM�2~,���D�2�)\"t\'��fB�ɺ�,^:݉���^��p��\0�H��bKRb*��Y������%�6K3��p��b,������fd�-�aM�<�P�|{��(.�&œg�^g���[lÛm��@AV1�����3��\Z*@#��M�`�e����J����l��4k��x����(v1�+�X2p�qO���$.Ԋ�6��r�\r�At]��!�-�\r�\n��\"��6��a��h���*h�m!�Z�g�>�<xQIs���J�QG���u�yQy���V�>#�L��8�B���-mأ�m���D�Y�̳�GU��f�%Y�Ɩ1:>$��끇�^\n��lx�\n�S6�r)E�a���>k9�=M�z�Mݜ�+/R�����`�N���t38��8+�j���5\nf2���<~�UA\'\"TWÑ\0]5�L�@��×#D���\"��78�AEg��a�Eo[�EoOIlMo^���m���C����Q�i�-/^��ǻ�`!�#���YȄ��v�B�X\rXX�װ��U\',�ZX�ے���,t`�����4��#ӈ1�}D��hp�Dt��VD�q�%x��wD�`G.mAD%m~+\"M���XE�g�}���>]d�h�����v�M`�����78�_vkb��5L�%��퀌�Q]Sp1�l4J���%S��{���G����ܖd,m\r��>�\Z�!�\ruR�t|\0IyT����Km��ʥղ3�Je����i�2��9��l~�v�)t���S,�Ug$�\Zj�>`�pͶ�l\"�;nR��=���^�r��nft���S�S�1�u�{�L&���l:N�̏��r����\n~O��lxv~}�M*��}`G����Nߑau����m�a�S��ۦ.����E\"�n�>��5iN.�N�xSTܟ�Z�*c�Ӊ�N&l��\n#D�b��g�����ȶմv�����eG��\rO�o�m�o� �����-��f��(�Ěa?�W_&��pۃ:�3~۠��Z�;H׶�&|3�aϴ���Xӛ���F5�Ao[��nϑO[v;v�j�A�;U�ׅ��q�(�����rz>Jv��0 GZ��~N���T�0���&\"b��Qa�� ��}m];¨h��Q�E[�E�Qc�nױKG|Q{�M�jӠ�V���!�\\�DTA���fh�8�~�Z�ibV���5�}ꪣ��Y �\Z\'W�\'��b�r���t�����.��A��P���1\Z\r��RB/����� ����l��m�0kJy���SE*�]\'��O�#sȂ���P�}Ks�:�E׏Y��y�ݕ?�^�i����p5E�e��>˞f�e����1�ߧ���������uI_\nendstream\nendobj\n16 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G�)��+r*�s��u�+Y\Z�(�rs\0t�\nendstream\nendobj\n12 0 obj<</Type/Page/Contents 16 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf2 15 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n17 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1634/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Y�r�F}�+�)�T�x�\Z=�6�)�e�������ر�\rW�ߗo��3򐖄�4\n\nU[`�t������Q���>��������)�}?g{���Q�n��8��ǎ�~���~<=t�h|m4N{\'1\nc\Z��x\'��J�����۷�ѭw�a��K�.MnB�s���D��iWX�ih�ɳ��Ͻ� ��o��/(�\r��b�vh�����Rs���]Ӝ�E*���6�X�Ć�^�m\\�O��Ӌц���p�Ձ��}���ƚ���X��]��i��ו�Ηې�*L����bV�J$g�?�/�2^�v����a?Y���ʌ;��z1��f����*_\n<3c�?�����=�;�E~�����s-Q�Y}n���Kv���=FO��ZO�O\'b��c��� ��y|��pjs^C(E� ��2}����H%ibrK4�!ZVP&^�L�X$���9�X��8�n�pq�m�l·\'c��Ѭ|=�\'��\n�n3�Ď�¢q��Ѝơ��A����{y������\'x��}��t��Y�e�J3�rXcy���gdg�޶��@�;-M؀�q��f0X�G����D\'���4�|r:��vt!��p�,(�ʹ��n�<Tŷ�����AJI--����A1$�\n�	?�\\�䣭�q��:���i/�=H�WD)EZ�\nHM_��ʫ�ڻ��I+�(Jw�*}\0>p�*���{��4�������0V얢N��ú<��RL���Lڃ�m�(���@9�C��(堐+L�=PB�\Z�G��b�d�1�qѴ0�i��4w(.9M��뜇�I�QE$��sfK���4A���E΂�\'�Y�(��nD�����rF�lܫO_ޡ5E�i�Z/v]Q�ۗgG�H�*�J����٬\ZbC������y����;��`\0:��k\rSD/�m����y/@��IJ�h�����1,_�	K���QOi\n\Z}w�o���Ǩӿ�A?���Y둰;G,c_Xzo*�v�d�6=^S.��0ڋ\'�s�R;�N�&�\r�S���+=ciw�nP?�&w��a.�`��ۮ�ߌ�h��\r��`��1 \05U���]��5�*��B��Jo��oJo\Z1���ib���Ԧ�&j�Jﺏ���cJO��Sz?Sz�[�*&%�fw���],	�G��bLػQ0Fװ\"�ܸ��q�x�ਮ>��*8�i���ק,Ļ�zפ\\&��\\��T�O/�[�*=M�&cu�ӄ=)�ՈB�1�ى�ڜ(&�m�=����s��T0eݥď�F[0~�!i�3��\rE8a�#ԧ5��wg@�ֺU�i^؈R5�MЦ�u�s�A?^��\"��R�6T�C�7t�h-W�y4����Ǘ(~����NR3R��mYTa�ޏ��Ο��9�Z�f����LE�5mzkK�����έ�P�Y�mZA+ffa��ĄLs�9�(L��aT�Q�w�z�����^I�oi�1)1�g�E�^��U0�ά_�[��s��w@�)�\'��~��vZn��Ɣd�����\0���{\"��GD_�hˢo/��X��*���L=erڴ>4�w����t����Y�`�=z]���	=�.׫Ǘ�s��_�u�\\Ư�^�j�F��z�c�|��V\n�֯w���.�_��Q�\nendstream\nendobj\n18 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G�+��+r*�s��u�+Y\Z�(�rs\0��\nendstream\nendobj\n13 0 obj<</Type/Page/Contents 18 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf3 17 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n19 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1504/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Y�R�8��)tܭ\"B��|t 0�dC�ڽe3d\'?��Cm���{ؖ�8r��9@p�[�_�;u(&��H���\"�2Y@q����b��n��*\nܭ,��k�ر��o��_^	�\\u~�tN{\'�1�I�#�I.q��Q��}��xכtί\nwi~��v(aXJ4�8�\n˼m�e������~2��x��M�0��O��3]k��`c�6�2BQPڀ��Z�r�enCW���|�e��-;����v��Z4C\Zk�o�}�lw%�L�X�+���ې@&�3��Z����EL]�j��p�ڑ\n�����Tnkg��V��b\'ǭ�A�R��[3��o�z��82d���a���G\'(Z�k7���s<��<8i6]~9^��~\0%��\n_����d�r^�P�� ��2=��B���C����\r�\'\r���g���|�!]V!�uA���Sg�����)e��8v��i�nD�A����TA7����R84����8��nP�Q�[j�նx����͉J��*��:�&��i�y��\Z�7��}Fv��!�=�R�Ӧ�ei��;\"�0�@\'�����qtw���㇛�O����5�M0Y/	R��>o�,S=Y/�R�B+�gRt�~��	�o�7�w��?�;ػ�s�L��$v��m�r������Qt�\0(l�*�F�!ߡ��w���8Ĩ@焞n<�^0�b���H\n���*,=�W�B�+���s��h\Z���%L���i��ذ��c�6��4N�d�LP�]`l��20�!��.ІBPmO�����Ϋ̢�6�Oë�]F�����6`�]�B��z���\Z�\03���뫂V�__M�x��a�cz���<q�q`n�RyÄd��I�0�B���F���9���m�hp4������Ȉ�+\"f\0i ׶�:Od�Rv��sztt�*��O��*�v��Z���jlr� 	�ܖ\0UxFOv\"M���V�a�Ʋ��\r;��w/��%�zc�~�4�Zs`�\n�w�fT3ۚ66��8���8`��s�R7\\A=��8�K�1m vP��\"�KA�a�(���檏z�1��A�S;�7|����؛�;tc�x���S�Ycc>�v|d.�nړ���ڔ�6�iM}S�BL�\'ʭ��>s	�B��3���1�QO&z�Q�˰a�8�]�a�5 ��{�/	�8k,�6<�m1�Fm��ީ���g(�Fwp�sA$�\"s�\Z\'��-����),\rYH��R���r��?[Χ_�⡍l��T8$���qj^���D�E&�������+�R��L\Z*h��p{�Y�2w��>{���.�;�,�rzr������́s�Z+q�h�-���=d�-��-�������#��G�{�ݦ��\\ì���b��R[��9�FE�z��7��_���s<��z�=ϒ,}y�����fq��D��j�F�4K�C~�i\Z��[6��_�4�ϋ���?:W��\nendstream\nendobj\n20 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G��(��+r*�s��u�+Y\Z�(�rs\0��\nendstream\nendobj\n14 0 obj<</Type/Page/Contents 20 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf4 19 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n7 0 obj[/PDF/Text/ImageB]\nendobj\n5 0 obj <</Type/XObject/Filter/FlateDecode/Length 80/Height 30/BitsPerComponent 1/Subtype/Image/Name/IMmpdf1/ImageMask true/Width 2232/Decode[1 0]>>stream\nx���1\00���/��-��d��)hь��hCg��]m�,R=zB�Y�z� ���H��AEg��у��\"գ!�E��\0h�Fq\nendstream\nendobj\n6 0 obj <</Type/XObject/Filter/FlateDecode/Length 700/Height 78/BitsPerComponent 1/Subtype/Image/Name/IMmpdf0/ImageMask true/Width 360>>stream\nx���;��0`G)B�[\n�Y�\r��2K��v�v��@�(ق�`D��\"��?v�c\"d�H���sl���z��˝G�o��:�C��n6ð��ax��aX��~vð���A| G?�r��	��꒣_j�qŔb�\n��^ -\nq�m\\��h��\Z]�uS�~���\0V��M�]�y\"�cC���ێ�M�X��.⠁Y�/�y�xՂ��[�u�{�	{�;�cSq0�x#�:F����%S�F�9��#�e\'ަ����\r]�J�d\'f8g�q�k����`#8.���u���b8qI�~u�s0���jq����2^cp�0:��p�D�c�g��Ur��`��\'%l������Mh�I������ۆ.d�I�PV�@i�|#H\r|�Z�*�%7$+��Z�J����\r��uþa��,�5��\'~��bt/���\"�8��E&&�%�mR�<u��eA����ib�\\ptI��4vqI-7d+s����N\r\'#�X�[�&�\r�G`ǔ�Y��b���;�\\i�\"���_�\'ļpY�+�K�]�h1-}��vi8��q��#��w,�$ q��R ��ꎽ���V7鹿cw���T�U$���/�8Y���Y���Z�1�IadOw�l\\?���mRV�91z���ogY�o���������oܒm��7O���������G��a���V����\nendstream\nendobj\n8 0 obj<</Type/Font/BaseFont/Helvetica-Bold/Subtype/Type1/Name/Fmpdf1/Encoding/WinAnsiEncoding>>\nendobj\n9 0 obj<</Type/Font/BaseFont/Helvetica/Subtype/Type1/Name/Fmpdf0/Encoding/WinAnsiEncoding>>\nendobj\n21 0 obj<</Type/Catalog/AcroForm<</SigFlags 3/Fields[2 0 R]>>/Pages 11 0 R>>\nendobj\n22 0 obj<</CreationDate(D:20080923065445+02\'00\')/Producer(iText 1.4.8 \\(by lowagie.com\\))/ModDate(D:20080923065445+02\'00\')>>\nendobj\nxref\n0 23\n0000000000 65535 f \n0000000117 00000 n \n0000000015 00000 n \n0000010019 00000 n \n0000008020 00000 n \n0000016736 00000 n \n0000016989 00000 n \n0000016703 00000 n \n0000017850 00000 n \n0000017954 00000 n \n0000009802 00000 n \n0000009947 00000 n \n0000012262 00000 n \n0000014463 00000 n \n0000016534 00000 n \n0000010200 00000 n \n0000012117 00000 n \n0000012431 00000 n \n0000014318 00000 n \n0000014632 00000 n \n0000016389 00000 n \n0000018053 00000 n \n0000018137 00000 n \ntrailer\n<</Root 21 0 R/Size 23/Info 22 0 R>>\nstartxref\n18269\n%%EOF\n','2009-06-12 23:03:28','e96bcbd2-676d-102c-ace2-9cc3fca64c89','2009-06-12 23:03:28','e96bcbd2-676d-102c-ace2-9cc3fca64c89',1,0),('18119e4f-07fe-4c15-bdce-927dfa69eed6','certificat','Test zum Löschen','0b0b7658-2ddb-11de-86ae-00301bb60f17','application/octet-stream','grid.csv',0,'','2009-06-19 16:36:28','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-19 16:36:28','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0),('0c05017b-0f9f-4bc3-baeb-c1f8eb734120','certificat','Test','0b0b7658-2ddb-11de-86ae-00301bb60f17','application/pdf','20080908.pdf',18806,'%PDF-1.4\n%����\n2 0 obj<</P 3 0 R/Type/Annot/F 132/T(Signature1)/V 1 0 R/Rect[0 0 0 0]/Subtype/Widget/FT/Sig>>\nendobj\n1 0 obj<</Filter/Adobe.PPKMS/Type/Sig/Contents <30820ebb06092a864886f70d010702a0820eac30820ea8020101310b300906052b0e03021a0500302306092a864886f70d010701a0160414a3939baa417b8f344e2be299336cd946bf5fcdeda0820d1d308204b33082039ba003020102020302f2f9300d06092a864886f70d010105050030819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033301e170d3037303232303135343535385a170d3132303232303135343535385a305b310b3009060355040613024154310e300c060355040a0c054241574147310b3009060355040b0c0249543118301606035504030c0f424157414720502e532e4b2e204147311530130603550405130c33343038393431313432383930819f300d06092a864886f70d010101050003818d00308189028181009f18e18acafeced47e87948327150690350f940b496d3d832b80568e1578be143898e395bd10d5863bab7400f0b3f7c41052e298169f96ae84f3931805bae5fb150b191a80cee84538a05cce009c4457f3b059e34f5d9668a7317ced1a8db2ea88079ce7685599fd678ebe8a41154bdb18ec239098f4ee063944d4142ccb585b0203010001a38201bd308201b930130603551d23040c300a80084191691cbfadd898305506082b0601050507010104493047304506082b060105050730028639687474703a2f2f7777772e612d74727573742e61742f63657274732f612d7369676e2d636f72706f726174652d6c696768742d30332e63727430580603551d200451304f304d06072a2800110107013042304006082b060105050702011634687474703a2f2f7777772e612d74727573742e61742f646f63732f63702f612d7369676e2d636f72706f726174652d6c6967687430819e0603551d1f048196308193308190a0818da0818a8681876c6461703a2f2f6c6461702e612d74727573742e61742f6f753d612d7369676e2d636f72706f726174652d6c696768742d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f7269747930110603551d0e040a0408487cf8373c3f8bb6300e0603551d0f0101ff0404030204b030220603551d11041b3019811769742d736963686572686569744062617761672e636f6d30090603551d1304023000300d06092a864886f70d01010505000382010100776e67497156b2bcb608d9de5dcf85efbaa18859128f1934e0ee4ecab2f94ccee454e8610e6be5b6c37eb49e4c8026363d107ce5b3e62bea1b4927cd9ed152d430a77976215fabbe75b27a8f452c3f1e8943bc28339dfc241c2a3fc66872919067c1420a7e010933947e324117d118bfa2cb732d845c80b3aecd37ee481323fe9166bdad16d619298ee502c5533254e8bdb1f90ab97c9a4c343deb368f3def13c4ba4d66fc4861f5af516cd6d94e1b5ebddf5e8eceb331a7fb2c9bfb0d210c0d8639f81ffd9d2cbf50e50ba200828415e8dabad043cbfd95355915ab7f3542ce80c6d90b5d1b72d1d03c9370e7c9f90909b76f870ebdf248b918da2e705259d73082048f30820377a003020102020301aaed300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035313131333233303030305a170d3135313131333233303030305a30819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100b7e7da22b5b1e490481d12b44f78176471844ad832ddc5f7279fe488ba6484633595b1c4537213ca4b36fb7f56ba1073db079599b0625a5035fd29f4eab0934ca3eedb0122a4de0b0b571359a98dadd86fb2f07bd111861e8c3388ce302470142bb7e9efb8152519854942312ec809f5f1fdaf1b463754dbd91462a73caf00572714324d14c36d516cadef5f9b5b6cf810fe471990de1cce2e551dea4cbdfea1030c0ba098fe28bf73c66f23c8f2bcf2a0812808cc342a592ac121c9a9f0d428d86d5987b38e50e56e8506da83712e88ab8982e93b543c6b424eea07eff24bba1129d3be68869f3bbf77d297378b2630699140cff54f13bfb9767ae0f4b755150203010001a381e33081e0300f0603551d130101ff040530030101ff30110603551d0e040a04084191691cbfadd89830130603551d23040c300a8008446a95675579114f300e0603551d0f0101ff0404030201063081940603551d1f04818c308189308186a08183a08180867e6c6461703a2f2f6c6461702e612d74727573742e61742f6f753d412d54727573742d6e5175616c2d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f72697479300d06092a864886f70d010105050003820101000d3448690b4fc283d2ebf4e9c8184ec38c001e4fd13388242d3efa5113d8dac5078a1c6acbef2a10494df9fe6523ad66c823f72054c295f95c1d5475402a0c834648e7f11c41323c0e0779890917b958e1df00a3f3b29ad93bbb7752be8bd549e0147fa61fea98f291ecbc7f3ed013c3fbd6a38936adbb79a354308b167b08fb7e045f7109a002e2e2f46c1d0cf4cde2f56d9e3e6a97e96201de33244fd1500b42a284f3dfc3fd7b29d74d8b8ac530c1224f22af253b53a839c80305964eab0bd46ee8c4c32d9e761a296310ea04223a2473098ede7dc022ff7630517e942d6e73c6a6a936cd6fa846d21ac40497578597aec70591d800f7681c6527645d0a69308203cf308202b7a0030201020203016c1e300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035303831373232303030305a170d3135303831373232303030305a30818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100ad3d616e03f3903bc0410b8480cdec2aa39d6bbb6ec24284f75114e1a0a82d51a351f2de23f03444ff94ebcc05239540b90778a525f60abd4586e8d9bdc0048e854461ef7fa7c9fac125cc852c633f0560734905e0607895104bdcf91159ce717f409b8aaa24df0b42e2db56bc4ad2a50c9bb7433edd83d3261002cfea23c4494ee5d3e9b488ab0cae6292d46587d96ad7f4859fe4332225a5e5c833bac3c741dc5fc66acc000e6d32a8b687360062779b1e1f34cb903c78887405eb79f5937165ca9dc76b182d3d5c4ee7d5f83f317d8f87ec0a222f23e9febb7dc9e0f4eceb7cc4b0c32d62b59a71d6b16ae8ecd9edd572ecbe5701ce05559fded1608810b30203010001a3363034300f0603551d130101ff040530030101ff30110603551d0e040a0408446a95675579114f300e0603551d0f0101ff040403020106300d06092a864886f70d0101050500038201010055d454d159485cb39385aabf632fe480ce34a334623ef6d8ee67883104036f0bd407fb4e750fd32ed3c017c7c628ec060d11240e0ea55dbf8cb2139671dcd4ce0e0d0a68326cb9413119abb1077b4d98d35cb0d1f0a742a0b5c48eaffef13ff4ef4f460076eb02fbf99dd24096c7883ab89f1179f38065a8bd1fd37881a0514c37b4a65d2570d166c968f92e111468f1549808ac26920fde899ed4fab3792bd2a379d4ec8bac875368424c5151741e1b272ee3f51f29744dedaff7e1929981e8be3ac71750f6b7c6fc9bb08a6bd68803918f06773a8502dd98d543783fc63015ac9b6bcb57b789518b3ae8c9840cdbb150200a1a4aba6a1abdec1bc8c5849acd3182014e3082014a0201013081a730819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033020302f2f9300906052b0e03021a0500300d06092a864886f70d01010105000481806452bd278af0c176438b49ad48756ef60aa7d0badffe78407ee18e1b9dbab2036362b5314ac3dfe6b2a29164063ae49d6346fd126c4f8784c363231d9987565076313d509b3321bf733910119405ebb8903601f87e721a425ebc2075284692f6706b7aecadf8f6a2f4f0c4cb9d17aaf87ce597e78e5b7aef4807be6de1d9fa3800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000>/M(D:20080923065445+02\'00\')/Name(BAWAG P.S.K. AG)/SubFilter/adbe.pkcs7.sha1/ByteRange [0 164 7844 10962 ]                                                             >>\nendobj\n4 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1530/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Y�R�H}�W�q����IH�bI8qվ	<�m�Յ��C��y��dK��\ZK�|NO_Nw˃�a�\np祾�\"$�+\r	��r�ڹ�}C�V!������\'痫���׃/��q�8FܠP��~�:�\n��$�o��#�u����k.�n\n&��	L���B��Y׆HT�]\r~���i�y��揯\r&�y�;�8��Q�K�hX��c$$7����	CJT�^1�˸(�F�x��a�{<��,\nE��B\n�r<�ӷ=�`6�~�$�~����N�֫7=�`E{,�ԥ9���!QT%����v�i�N��Jg��̅��(�����>������ad��/��6��x.t����t�{.�!��K�^9\'/�tv�7��7M?���\n/��h��W���\\(!���B���9��DE]hm���^�L�?�G�W��R�*�X�(��2�0�q:�����A�iD�A�ԑ��TN�oc�ph�\',L#�~FnP�Q�Mjs�dS<�:�{p����K��E���E�;a���D�����f��=p(� ��^�ar�}_fw��C�E����ڭ 4��ae��C2��<DR����6��,v��#˘�eN;�����p�KgE�t\0/}Ç�z�����C�\0X��2g�����zJ���g��?��*:��J����0�Eb��ӿ����,�m�u!k*��dn\'�j�(J�\\��C���&��d�,Iq9�3����E���L���m����<�z�Z�����k�VS�;1Bx����*���!�ƌ������K���Ow�(��m�tv(\Z�?tq�Um[BTZu�FG�,�{�Ǝb�ԅ��r��镨M��6�$�_�o�1$�+{�O��F�\r;����U�0��6�$�|��.;O�\\FQ����<\"{�uT��c��r��kX��������8�Y ����M�۰C)<�L0L!�>��9bvB�+<��q��g���efX��n@\\�6߷-��5�m��<�W_- f��7\'W��f=י=g\nG.��$M��a0]W�����z����%�b�\\fL��V������t�?f�P�8��BW�g	�)z�ΰ�Q+���ck��~�ݪ��Y>_�\\�\'өwT9H���(�����Oǟ^�����ȣvO4�0n�|I�h�|(ќ.ܞ���2��%�ֻC��t�x�M0A�2��i�-�+t�.��\r��G�ױWWr�H����B�:\0}��8-�>�42�����9�\n���,�|x��Zܶ\0�:E`@��<��ub3y�B(Ea���C�.챧-�m�O\Zsq}��]����m�_$M��]h\"%�����&�!̕P�ΥG�)�9B�C�ouv��iO��42�zм?\"]�Z!Y-aգ�o���\\��T�����D�����j�w�1����Ch��aJu�����?`��ʰ��z7��m`�18��E0����V\'LF�`�d�����/̱v�J��`v��t�<��cQ��C+_.����`�e\nendstream\nendobj\n10 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G�*��+r*�s��u�+Y\Z�(�rs\0K�\nendstream\nendobj\n11 0 obj<</Count 4/Type/Pages/Kids[3 0 R 12 0 R 13 0 R 14 0 R]>>\nendobj\n3 0 obj<</Type/Page/Contents 10 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf1 4 0 R>>>>/Annots[2 0 R]/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n15 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1664/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Z�R�8��)tܭ\"B��|4�a�26	P�����Y��N���{ؖ��D��qr	1�׭���Gv���>�8_��<��|����7/��}�V��\r�\Z+�i������;�N��z���	��AaLc��$�8���0۸|�]iw4�~*ե�\"4��Q°�h��]�yi���ޗ�/���yr9����W4���]l0�ȋc�t�8΢�\\[\\�MEQ%Beʱ��U^12��gy�}3ڒCY�Np�A\r݋�q CcM�2~,���D�2�)\"t\'��fB�ɺ�,^:݉���^��p��\0�H��bKRb*��Y������%�6K3��p��b,������fd�-�aM�<�P�|{��(.�&œg�^g���[lÛm��@AV1�����3��\Z*@#��M�`�e����J����l��4k��x����(v1�+�X2p�qO���$.Ԋ�6��r�\r�At]��!�-�\r�\n��\"��6��a��h���*h�m!�Z�g�>�<xQIs���J�QG���u�yQy���V�>#�L��8�B���-mأ�m���D�Y�̳�GU��f�%Y�Ɩ1:>$��끇�^\n��lx�\n�S6�r)E�a���>k9�=M�z�Mݜ�+/R�����`�N���t38��8+�j���5\nf2���<~�UA\'\"TWÑ\0]5�L�@��×#D���\"��78�AEg��a�Eo[�EoOIlMo^���m���C����Q�i�-/^��ǻ�`!�#���YȄ��v�B�X\rXX�װ��U\',�ZX�ے���,t`�����4��#ӈ1�}D��hp�Dt��VD�q�%x��wD�`G.mAD%m~+\"M���XE�g�}���>]d�h�����v�M`�����78�_vkb��5L�%��퀌�Q]Sp1�l4J���%S��{���G����ܖd,m\r��>�\Z�!�\ruR�t|\0IyT����Km��ʥղ3�Je����i�2��9��l~�v�)t���S,�Ug$�\Zj�>`�pͶ�l\"�;nR��=���^�r��nft���S�S�1�u�{�L&���l:N�̏��r����\n~O��lxv~}�M*��}`G����Nߑau����m�a�S��ۦ.����E\"�n�>��5iN.�N�xSTܟ�Z�*c�Ӊ�N&l��\n#D�b��g�����ȶմv�����eG��\rO�o�m�o� �����-��f��(�Ěa?�W_&��pۃ:�3~۠��Z�;H׶�&|3�aϴ���Xӛ���F5�Ao[��nϑO[v;v�j�A�;U�ׅ��q�(�����rz>Jv��0 GZ��~N���T�0���&\"b��Qa�� ��}m];¨h��Q�E[�E�Qc�nױKG|Q{�M�jӠ�V���!�\\�DTA���fh�8�~�Z�ibV���5�}ꪣ��Y �\Z\'W�\'��b�r���t�����.��A��P���1\Z\r��RB/����� ����l��m�0kJy���SE*�]\'��O�#sȂ���P�}Ks�:�E׏Y��y�ݕ?�^�i����p5E�e��>˞f�e����1�ߧ���������uI_\nendstream\nendobj\n16 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G�)��+r*�s��u�+Y\Z�(�rs\0t�\nendstream\nendobj\n12 0 obj<</Type/Page/Contents 16 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf2 15 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n17 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1634/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Y�r�F}�+�)�T�x�\Z=�6�)�e�������ر�\rW�ߗo��3򐖄�4\n\nU[`�t������Q���>��������)�}?g{���Q�n��8��ǎ�~���~<=t�h|m4N{\'1\nc\Z��x\'��J�����۷�ѭw�a��K�.MnB�s���D��iWX�ih�ɳ��Ͻ� ��o��/(�\r��b�vh�����Rs���]Ӝ�E*���6�X�Ć�^�m\\�O��Ӌц���p�Ձ��}���ƚ���X��]��i��ו�Ηې�*L����bV�J$g�?�/�2^�v����a?Y���ʌ;��z1��f����*_\n<3c�?�����=�;�E~�����s-Q�Y}n���Kv���=FO��ZO�O\'b��c��� ��y|��pjs^C(E� ��2}����H%ibrK4�!ZVP&^�L�X$���9�X��8�n�pq�m�l·\'c��Ѭ|=�\'��\n�n3�Ď�¢q��Ѝơ��A����{y������\'x��}��t��Y�e�J3�rXcy���gdg�޶��@�;-M؀�q��f0X�G����D\'���4�|r:��vt!��p�,(�ʹ��n�<Tŷ�����AJI--����A1$�\n�	?�\\�䣭�q��:���i/�=H�WD)EZ�\nHM_��ʫ�ڻ��I+�(Jw�*}\0>p�*���{��4�������0V얢N��ú<��RL���Lڃ�m�(���@9�C��(堐+L�=PB�\Z�G��b�d�1�qѴ0�i��4w(.9M��뜇�I�QE$��sfK���4A���E΂�\'�Y�(��nD�����rF�lܫO_ޡ5E�i�Z/v]Q�ۗgG�H�*�J����٬\ZbC������y����;��`\0:��k\rSD/�m����y/@��IJ�h�����1,_�	K���QOi\n\Z}w�o���Ǩӿ�A?���Y둰;G,c_Xzo*�v�d�6=^S.��0ڋ\'�s�R;�N�&�\r�S���+=ciw�nP?�&w��a.�`��ۮ�ߌ�h��\r��`��1 \05U���]��5�*��B��Jo��oJo\Z1���ib���Ԧ�&j�Jﺏ���cJO��Sz?Sz�[�*&%�fw���],	�G��bLػQ0Fװ\"�ܸ��q�x�ਮ>��*8�i���ק,Ļ�zפ\\&��\\��T�O/�[�*=M�&cu�ӄ=)�ՈB�1�ى�ڜ(&�m�=����s��T0eݥď�F[0~�!i�3��\rE8a�#ԧ5��wg@�ֺU�i^؈R5�MЦ�u�s�A?^��\"��R�6T�C�7t�h-W�y4����Ǘ(~����NR3R��mYTa�ޏ��Ο��9�Z�f����LE�5mzkK�����έ�P�Y�mZA+ffa��ĄLs�9�(L��aT�Q�w�z�����^I�oi�1)1�g�E�^��U0�ά_�[��s��w@�)�\'��~��vZn��Ɣd�����\0���{\"��GD_�hˢo/��X��*���L=erڴ>4�w����t����Y�`�=z]���	=�.׫Ǘ�s��_�u�\\Ư�^�j�F��z�c�|��V\n�֯w���.�_��Q�\nendstream\nendobj\n18 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G�+��+r*�s��u�+Y\Z�(�rs\0��\nendstream\nendobj\n13 0 obj<</Type/Page/Contents 18 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf3 17 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n19 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1504/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nx��Y�R�8��)tܭ\"B��|t 0�dC�ڽe3d\'?��Cm���{ؖ�8r��9@p�[�_�;u(&��H���\"�2Y@q����b��n��*\nܭ,��k�ر��o��_^	�\\u~�tN{\'�1�I�#�I.q��Q��}��xכtί\nwi~��v(aXJ4�8�\n˼m�e������~2��x��M�0��O��3]k��`c�6�2BQPڀ��Z�r�enCW���|�e��-;����v��Z4C\Zk�o�}�lw%�L�X�+���ې@&�3��Z����EL]�j��p�ڑ\n�����Tnkg��V��b\'ǭ�A�R��[3��o�z��82d���a���G\'(Z�k7���s<��<8i6]~9^��~\0%��\n_����d�r^�P�� ��2=��B���C����\r�\'\r���g���|�!]V!�uA���Sg�����)e��8v��i�nD�A����TA7����R84����8��nP�Q�[j�նx����͉J��*��:�&��i�y��\Z�7��}Fv��!�=�R�Ӧ�ei��;\"�0�@\'�����qtw���㇛�O����5�M0Y/	R��>o�,S=Y/�R�B+�gRt�~��	�o�7�w��?�;ػ�s�L��$v��m�r������Qt�\0(l�*�F�!ߡ��w���8Ĩ@焞n<�^0�b���H\n���*,=�W�B�+���s��h\Z���%L���i��ذ��c�6��4N�d�LP�]`l��20�!��.ІBPmO�����Ϋ̢�6�Oë�]F�����6`�]�B��z���\Z�\03���뫂V�__M�x��a�cz���<q�q`n�RyÄd��I�0�B���F���9���m�hp4������Ȉ�+\"f\0i ׶�:Od�Rv��sztt�*��O��*�v��Z���jlr� 	�ܖ\0UxFOv\"M���V�a�Ʋ��\r;��w/��%�zc�~�4�Zs`�\n�w�fT3ۚ66��8���8`��s�R7\\A=��8�K�1m vP��\"�KA�a�(���檏z�1��A�S;�7|����؛�;tc�x���S�Ycc>�v|d.�nړ���ڔ�6�iM}S�BL�\'ʭ��>s	�B��3���1�QO&z�Q�˰a�8�]�a�5 ��{�/	�8k,�6<�m1�Fm��ީ���g(�Fwp�sA$�\"s�\Z\'��-����),\rYH��R���r��?[Χ_�⡍l��T8$���qj^���D�E&�������+�R��L\Z*h��p{�Y�2w��>{���.�;�,�rzr������́s�Z+q�h�-���=d�-��-�������#��G�{�ݦ��\\ì���b��R[��9�FE�z��7��_���s<��z�=ϒ,}y�����fq��D��j�F�4K�C~�i\Z��[6��_�4�ϋ���?:W��\nendstream\nendobj\n20 0 obj <</Filter/FlateDecode/Length 78>>stream\nx�3P0T�5T0P0�4�ɹ\\�\n ��442�36U045г\0�(�G��(��+r*�s��u�+Y\Z�(�rs\0��\nendstream\nendobj\n14 0 obj<</Type/Page/Contents 20 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf4 19 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n7 0 obj[/PDF/Text/ImageB]\nendobj\n5 0 obj <</Type/XObject/Filter/FlateDecode/Length 80/Height 30/BitsPerComponent 1/Subtype/Image/Name/IMmpdf1/ImageMask true/Width 2232/Decode[1 0]>>stream\nx���1\00���/��-��d��)hь��hCg��]m�,R=zB�Y�z� ���H��AEg��у��\"գ!�E��\0h�Fq\nendstream\nendobj\n6 0 obj <</Type/XObject/Filter/FlateDecode/Length 700/Height 78/BitsPerComponent 1/Subtype/Image/Name/IMmpdf0/ImageMask true/Width 360>>stream\nx���;��0`G)B�[\n�Y�\r��2K��v�v��@�(ق�`D��\"��?v�c\"d�H���sl���z��˝G�o��:�C��n6ð��ax��aX��~vð���A| G?�r��	��꒣_j�qŔb�\n��^ -\nq�m\\��h��\Z]�uS�~���\0V��M�]�y\"�cC���ێ�M�X��.⠁Y�/�y�xՂ��[�u�{�	{�;�cSq0�x#�:F����%S�F�9��#�e\'ަ����\r]�J�d\'f8g�q�k����`#8.���u���b8qI�~u�s0���jq����2^cp�0:��p�D�c�g��Ur��`��\'%l������Mh�I������ۆ.d�I�PV�@i�|#H\r|�Z�*�%7$+��Z�J����\r��uþa��,�5��\'~��bt/���\"�8��E&&�%�mR�<u��eA����ib�\\ptI��4vqI-7d+s����N\r\'#�X�[�&�\r�G`ǔ�Y��b���;�\\i�\"���_�\'ļpY�+�K�]�h1-}��vi8��q��#��w,�$ q��R ��ꎽ���V7鹿cw���T�U$���/�8Y���Y���Z�1�IadOw�l\\?���mRV�91z���ogY�o���������oܒm��7O���������G��a���V����\nendstream\nendobj\n8 0 obj<</Type/Font/BaseFont/Helvetica-Bold/Subtype/Type1/Name/Fmpdf1/Encoding/WinAnsiEncoding>>\nendobj\n9 0 obj<</Type/Font/BaseFont/Helvetica/Subtype/Type1/Name/Fmpdf0/Encoding/WinAnsiEncoding>>\nendobj\n21 0 obj<</Type/Catalog/AcroForm<</SigFlags 3/Fields[2 0 R]>>/Pages 11 0 R>>\nendobj\n22 0 obj<</CreationDate(D:20080923065445+02\'00\')/Producer(iText 1.4.8 \\(by lowagie.com\\))/ModDate(D:20080923065445+02\'00\')>>\nendobj\nxref\n0 23\n0000000000 65535 f \n0000000117 00000 n \n0000000015 00000 n \n0000010019 00000 n \n0000008020 00000 n \n0000016736 00000 n \n0000016989 00000 n \n0000016703 00000 n \n0000017850 00000 n \n0000017954 00000 n \n0000009802 00000 n \n0000009947 00000 n \n0000012262 00000 n \n0000014463 00000 n \n0000016534 00000 n \n0000010200 00000 n \n0000012117 00000 n \n0000012431 00000 n \n0000014318 00000 n \n0000014632 00000 n \n0000016389 00000 n \n0000018053 00000 n \n0000018137 00000 n \ntrailer\n<</Root 21 0 R/Size 23/Info 22 0 R>>\nstartxref\n18269\n%%EOF\n','2009-06-03 11:40:04','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-08 22:13:51','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

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

/*Table structure for table `entities_have_labels` */

DROP TABLE IF EXISTS `entities_have_labels`;

CREATE TABLE `entities_have_labels` (
  `id` int(11) NOT NULL,
  `entity` varchar(36) NOT NULL,
  `label` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_entities_have_labels_uk` (`entity`,`label`),
  KEY `fk_entities_have_labels_labels` (`label`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `entities_have_labels` */

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

insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('masterdata',' ',' ',0,0,0),('users_all','ENTITYLIST','USER',1,1,1),('persons_all','ENTITYLIST','PERSON',1,1,1),('person_own','ENTITYDETAIL','PERSON',1,0,0),('coaches_own','ENTITYLIST','MYCOACHES',0,0,0),('athletes_own','ENTITYLIST','MYATHLETES',0,0,1),('relations','','',0,0,0),('relation_coach','RELATIONLIST','COACH',1,1,1),('relation_doctor','RELATIONLIST','DOCTOR',1,1,1),('doctors_all','ENTITYLIST','DOCTOR',1,1,1),('doctors_own','ENTITYLIST','MYDOCTORS',0,0,0),('attachments_all','ENTITYLIST','ATTACHMENT',1,1,1),('attachments_own','ENTITYLIST','MYATTACHMENTS',1,1,1),('relation_attachment','RELATIONLIST','ATTACHMENT',1,1,1);

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

/*Data for the table `labels` */

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

/*Data for the table `list_variants` */

insert  into `list_variants`(`list`,`entity`,`user_id`,`columns_sequence`,`columns_width`) values ('entitylist','ATTACHMENT','e96bcbd2-676d-102c-ace2-9cc3fca64c87','0;2;1;3;4;5;6','25;232;100;100;100;100;100');

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

insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('0b0b7658-2ddb-11de-86ae-00301bb60f17','mr','Markus','Reich','m','Dorfstraße','91','6393','St. Ulrich','Tirol','at','reich.markus@gmail.com','www.meex-rich.com','','0664/3453852',NULL,'1978-10-24 00:00:00',NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-05-26 21:01:18','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('10f52302-2ddb-11de-86ae-00301bb60f17','mrs','Daniela','Bucher','w','Moosbach','28/2','6392','St. Jakob','Tirol','at','dany.bucher@gmail.com','www.dany.at','05354/88462','0664/2844263','05354/88462-10','1983-05-17 00:00:00',NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-03 16:04:34','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('7522bc7f-42cf-415c-a050-da12518a4cd3','mr','Thomas','Mach','m','Dorf','2','6392','St. Jakob','Tirol','de','thomas.mach@egger.com',NULL,NULL,NULL,NULL,'1969-09-15 00:00:00','����\0JFIF\0\0H\0H\0\0��\0C\0��\0\0`\0`\0��\0\0\0\0\0\0\0\0\0\0\0\0	\n\0��\0H\0	\0\0\0!1\0AQq	\"a\n$%24��#35���&6BCD��ERber������\0\0\0?\0�yt�~�/!��l�c��H��PwÇ�<��^c��\0wQ����8�Q&��A�i\"H/�˂��><�!��������r��ߩI��^��m���V6�RGX3\0`1�1�}N�C��it��F��#(�*T�k����D3cs哀���?�#�\Z���Jt��STEJ}M��A�!d.���q�c���6���{�\'52ֿjh������JB��V�.ΪH����	<�|qr���+���3��QNIr�q���ජ�E��qvg�l������T�\0,@�[�s�m�s)^d�Qn��b7vϟ�8y���Ӳ�>���\0�y�l�?�����w�^�>O�heubn��q��\0�~3������_��н ���Յ\nq�U4��E�u�\0�;�x�۩ک�WtkWj��ζ���>�F����(_��� �b�N	˄j��I��J��]�*\"��O�Oz�ǿ8\0Ē0aÁ�MJL�S�d�$H/ۈ�\0��Ł�������LUTA����]P��N^\n��@pa����I�~5��O��)�\ni��T�U�=�K�C{mY�q�\"	Ky��^��1�\"�Y�8�-�1��r�$�|~ϗ/�� y�O[�;\0��>%���g�Q9!�\0�?�\\<n}?��e:Nҧ���;G�1ˈ���\\�5�6��\"��\"�Ĵ�ׁ5��)��S\0�#��[fǟn��F�t�Ի�\\uif�e-�q(�)��L1֨G_ՎY\"#Մ����,D\"�4����i�4s�����B��d�{�pq�-�7#ρZ�ЗGoi�)ة�ᾁYH�j���|5t�w�Ɓ\rJ�N�d-Sӊ��RUжBJ�7��9cbx�����R�%N��9F�|T�T�\"��w�c\'�9$Aw�:���+��@��F���H�M>EGT��#�e$׼M(���0 �_���7K��i���ɞ�9;�/Jw��ԶIV-@�$���?k�c���*0H�r�?�@�\r�<~����p���4�������\0�y\r�[�Q��_��\0���G�R\'D�?J�\'N�g[�vӵ�w?|�JXB��̱\0�5��v��n��)�D��D� �.��d���@sĵ�^���j���M�`	��q��Ny��r��`�8����%��\\r~�p<�\0p�]�t*�RF\"�r�@yc�q�p^��ett���ur�IL�Gx��M����NƶZ|A\ZZ���OA��bf���T`��9q?��?��d�&�X�����!UTH��C�tZ|�%%Uܾ~�����4dm�o�ۿ�?\r��[�~���o�_>n�c�ˇ����&��|����e��8�g����C�D��g_T���SI@�m?&c�P.I0\"��uF�ʗIT�`^2Qk����h�$�Ab	/�N���-v׽i$�#[$\'��K�\0�,0��6s����5��H�;qw�-{MQ.D U,�-\Z,��X��1$�������L���m��9ԿvE��k{�N���� ų�#*Ǌw�Ժ�	�d�$wz���\'l�ɘ�3 h\'���/N�z�E���[P����2|�K\0H�&;\0��n.����%��e�S:e$��+��ʞ\"(�\n����mx 9��1�v�%����w��6[ώ<:��\0\\=a�\08x;��_��\0<Q/���T\\���O��8�Z������AUM�RU�\0��	M���q�������s$�j��y�K�Y�L��ZT�8H�1*\\�#���&�8\"�	B~4�驳d^_6+���M=5EM7qU&;�uH|gs�v�.�N�\Z�rY�XIZ�9\nE92��Y��d�wn\\������D�	�nz���N_A��W�.5�{�塡T�WW�5�[\"��̎/ �&\0�[��h��r{��+�\n�pK�\'%�19�\'��=ښǬ���$e:��)�\nT���\0�U�H������\"����$�>�]��].Jl��m-�OM�/�4�U<Y(l�o�$����]�k\"�\"X;�@>�\0��\0�åI��_��O\nϺO���><B^�Vi��.��--)�Z�i�tH��\0U��a��D�T��ˏx��r�H�2�_M\"a>�����\'�\rjȖ���6�fu��C�uP4&�Ĝy�CG���sé��0���*r��d�\n_��-\\*���������b�Sl/�j2��|6�I�*�u1���	�\'�3hw��R�ug��Ab�]�}�uK��r&�:�V/Ͽ�i+d�)HD���P��戀3��J9\"fطE���:��F���{�V�[ezذ���~�Z�B�U�	g%�A-\0Nϋ���P�\0�p�6W����w�~���rw��ϧ��P-�[�ü�˵��j/ ���j�IpN��%V�U�a�6u]WrS�L��QU�6��)�C#�wa�d�:{>��JUѨV~��jWB�%�mܵտU��UG#���.��0��̻b\Z��Ԅ�����99��{�_���\\`�*�b��8{lQS梒��K�ԅMu\'�*�5i�Qˇ0a!��=�\r-2u�&vӧ�\n���Iߙ���t���:��w$��}��ZNb<T��BRJa,	eU��/cN��6�����$�J\nj\njt��aCCGA�𴱟C�a��82P���<ϧ2<�#;p���?��h�vdD66 P䏏���\\y��o�L3�g8]<�mO�ַ)^�4���k=Ez�mM;\n4����m\0s�e�_f��s��\n-Md�S w�j��+���%�b��l���\\����h��e>G*t�C�wa��m\r�i:��r��o�9�r��\0y9`I����tɷ-�]���=�T���$� ��?-�\"�IK���QنI�,$\n}�u[�Y_�S<�A��כTDD�l�����;���tl����~�Iϙ|q�9��Ǘ����p��V\'M�8��y����6�����X�ϐ\"\'9������.߿��g?~�.�2�n��KK&H�u�mj�uki>j)J�LJ�ŕ��H �7�jj���:��IS����R)���V�\r�w��C`m�aW^m��x~g ,�֊o�\nZn�B<��w99s����Q��%��T���3��ɣ�r`H��+Ņ2�uRW�\'��5%���8p���}��:xX�:SI�u&�jM���\nb��-/~���ԛ�Y\"�RVJ�(�ŕ>t8a��\ZȤ�gK���]�������齔1�\\Í���?/ǀ�,i�3&�*9���6#P��gX���u�H��G{�MPӻ\nI��M�y�H��o�W���Ȉ3��׏gƓ�֩�t��o�����1�;�n��\'�Y ��ȸn7;�o�hO����&ۺ%]����AgXh���֤�i*k\n�*��틃��\r�y72죱:Vif������$�׺uE-2�P\rX8�R�:6\"���	\"���OlE�ڪ*��S�M���5g ��/x����t��\n]��*�M;��	Ü���;8�`	\'1�X�S襜�>��Qm���Z��۪~7\\=�E\nR>̀�lqL�����k\r��6�\"�cmT �qZz��J~.Z&݀,�\0x��҃L:U�J6���B�&}5-Ԋ*�jȻ<7�[j�J�!\\;�s7�H�Rf)�k<=S�w\'������ʈ��$�}�?2���O�^�~���2l�ۤ6��)��n\\�1����L�V���̓���q݋��jʢ��hv�Q��d���A\0�rfEu\\rev�jV$��4A�-�C���ݰ	}m|ȋ	� �g$��s�Â9�41b?��\0L��xU۷�Y�\'SY���mJ�WZ�\'�T��J� �=�\"\"b��Z q�P����[��J��Q�p�W\Z��~�&#8$��pA�SKk�$E�<�?��߉�%@���� ��\0��9�g X�(�>t����\r�%�\n���==�h�\Z�d˥%`�5�W��>�#\r�h��@�z��5�Cl�����7�(��u�ۥ+���ֶ���]���]z\rk��KO��zkpO���\ncdW�����}n\\����','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c89',0,0);

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

insert  into `persons_have_attachments`(`id`,`person`,`attachment`,`reltyp_key`,`standard`) values ('7591d0e4-bdb7-414b-9acb-9fd8b938333a','10f52302-2ddb-11de-86ae-00301bb60f17','b8a954e4-4bca-11de-ab35-74df036e1e4f','attachment',0),('26b01e83-5d8e-497d-b5a5-8497be4f50da','7522bc7f-42cf-415c-a050-da12518a4cd3','0c05017b-0f9f-4bc3-baeb-c1f8eb734120','attachment',0),('dc30b3ef-f1a0-4c78-9ed5-24c545e2069d','10f52302-2ddb-11de-86ae-00301bb60f17','0c05017b-0f9f-4bc3-baeb-c1f8eb734120','attachment',0);

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

insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('e3572a08-8c2d-102c-a1cd-29e813a50118','10f52302-2ddb-11de-86ae-00301bb60f17','0b0b7658-2ddb-11de-86ae-00301bb60f17','coach',0),('57f2f725-e3ee-496a-9d83-cd1f861b7dcb','0b0b7658-2ddb-11de-86ae-00301bb60f17','7522bc7f-42cf-415c-a050-da12518a4cd3','coach',0),('541a619b-f363-45e7-9999-51cdfb3ee784','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','coach',0);

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

insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','masterdata',1,0,1),('admin','users_all',2,1,1),('admin','persons_all',3,1,2),('athlete','person_own',1,0,1),('athlete','coaches_own',2,0,2),('coach','person_own',1,0,1),('coach','athletes_own',2,0,2),('admin','relations',4,0,2),('admin','relation_coach',5,4,1),('admin','doctors_all',6,1,3),('admin','relation_doctor',7,4,2),('athlete','doctors_own',3,0,3),('coach','doctors_own',3,0,3),('admin','attachments_all',7,1,4),('athlete','attachments_own',4,0,4),('coach','attachments_own',4,0,4),('admin','relation_attachment',8,4,3);

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

insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('persons_all','de','Personen','Personen'),('persons_all','en','Persons','Persons'),('users_all','de','Benutzer','Benutzer'),('users_all','en','Users','Users'),('masterdata','de','Stammdaten','Stammdaten'),('masterdata','en','Masterdata','Masterdata'),('person_own','de','Eigene Person','Eigene Person'),('person_own','en','My person','My person'),('coaches_own','de','Meine Trainer','Meine Trainer'),('coaches_own','en','My Coaches','My Coaches'),('athletes_own','de','Meine Athleten','Meine Athleten'),('athletes_own','en','My Athletes','My Athletes'),('relations','de','Beziehungen','Beziehungen'),('relations','en','Relationships','Relationships'),('relation_coach','de','Trainer','Trainer'),('relation_coach','en','Coaches','Coaches'),('relation_doctor','de','Ärzte','Ärzte'),('relation_doctor','en','Doctors','Doctors'),('doctors_all','de','Ärzte','Ärzte'),('doctors_all','en','Doctors','Doctors'),('doctors_own','de','Meine Ärzte','Meine Ärzte'),('doctors_own','en','My Doctors','My Doctors'),('attachments_all','de','Anhänge','Anhänge'),('attachments_all','en','Attachments','Attachments'),('attachments_own','de','Meine Anhänge','Meine Anhänge'),('attachments_own','en','My Attachments','My Attachments'),('relation_attachment','de','Anhänge','Anhänge'),('relation_attachment','en','Attachments','Attachments');

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

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `speed` text,
  `heartrate` text,
  `lactate` text
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `test` */

insert  into `test`(`speed`,`heartrate`,`lactate`) values ('1 2','2 3','4 5');

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

insert  into `users`(`id`,`user_name`,`user_hash`,`language_key`,`currency_key`,`locked`,`initial`,`active`,`person_id`,`email`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c87','reich','test','de','eur',0,0,1,'0b0b7658-2ddb-11de-86ae-00301bb60f17','reich.markus@gmail.com','1900-01-01 00:00:00','','1900-01-01 00:00:00','',0,0),('e96bcbd2-676d-102c-ace2-9cc3fca64c88','bucher','test','en','usd',0,0,1,'10f52302-2ddb-11de-86ae-00301bb60f17','dany.bucher@gmail.com','1900-01-01 00:00:00','','2009-06-21 17:04:39','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('e96bcbd2-676d-102c-ace2-9cc3fca64c89','mach','test','de','eur',0,0,1,'7522bc7f-42cf-415c-a050-da12518a4cd3','mach.thomas@gmail.com','1900-01-01 00:00:00','','1900-01-01 00:00:00','',0,0);

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

/*View structure for view entities */

/*!50001 DROP TABLE IF EXISTS `entities` */;
/*!50001 DROP VIEW IF EXISTS `entities` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `entities` AS select `users`.`id` AS `id`,'Users' AS `entity`,`users`.`deleted` AS `deleted` from `users` union select `persons`.`id` AS `id`,'Persons' AS `entity`,`persons`.`deleted` AS `deleted` from `persons` union select `attachments`.`id` AS `id`,'Attachments' AS `entity`,`attachments`.`deleted` AS `deleted` from `attachments` union select `doctors`.`id` AS `id`,'Doctors' AS `entity`,`doctors`.`deleted` AS `deleted` from `doctors` */;

/*View structure for view relations */

/*!50001 DROP TABLE IF EXISTS `relations` */;
/*!50001 DROP VIEW IF EXISTS `relations` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `relations` AS select `persons_have_attachments`.`id` AS `id`,`persons_have_attachments`.`person` AS `partner1`,`persons_have_attachments`.`attachment` AS `partner2`,`persons_have_attachments`.`reltyp_key` AS `reltyp_key` from `persons_have_attachments` union select `persons_have_doctors`.`id` AS `id`,`persons_have_doctors`.`person` AS `partner1`,`persons_have_doctors`.`doctor` AS `partner2`,`persons_have_doctors`.`reltyp_key` AS `reltyp_key` from `persons_have_doctors` union select `persons_have_relations`.`id` AS `id`,`persons_have_relations`.`partner1` AS `partner1`,`persons_have_relations`.`partner2` AS `partner2`,`persons_have_relations`.`reltyp_key` AS `reltyp_key` from `persons_have_relations` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
