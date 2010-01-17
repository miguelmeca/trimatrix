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
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geÃ¤ndert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geÃ¤ndert von',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelÃ¶scht',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_attachments_k_categories` (`category_key`),
  KEY `fk_attachments_persons` (`owner_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `attachments` */

insert  into `attachments`(`id`,`category_key`,`description`,`owner_id`,`mime_type`,`file_name`,`file_size`,`file_content`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('b8a954e4-4bca-11de-ab35-74df036e1e4f','certificat','Attest Test','0b0b7658-2ddb-11de-86ae-00301bb60f17','application/pdf','20080908.pdf',18806,'%PDF-1.4\n%âãÏÓ\n2 0 obj<</P 3 0 R/Type/Annot/F 132/T(Signature1)/V 1 0 R/Rect[0 0 0 0]/Subtype/Widget/FT/Sig>>\nendobj\n1 0 obj<</Filter/Adobe.PPKMS/Type/Sig/Contents <30820ebb06092a864886f70d010702a0820eac30820ea8020101310b300906052b0e03021a0500302306092a864886f70d010701a0160414a3939baa417b8f344e2be299336cd946bf5fcdeda0820d1d308204b33082039ba003020102020302f2f9300d06092a864886f70d010105050030819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033301e170d3037303232303135343535385a170d3132303232303135343535385a305b310b3009060355040613024154310e300c060355040a0c054241574147310b3009060355040b0c0249543118301606035504030c0f424157414720502e532e4b2e204147311530130603550405130c33343038393431313432383930819f300d06092a864886f70d010101050003818d00308189028181009f18e18acafeced47e87948327150690350f940b496d3d832b80568e1578be143898e395bd10d5863bab7400f0b3f7c41052e298169f96ae84f3931805bae5fb150b191a80cee84538a05cce009c4457f3b059e34f5d9668a7317ced1a8db2ea88079ce7685599fd678ebe8a41154bdb18ec239098f4ee063944d4142ccb585b0203010001a38201bd308201b930130603551d23040c300a80084191691cbfadd898305506082b0601050507010104493047304506082b060105050730028639687474703a2f2f7777772e612d74727573742e61742f63657274732f612d7369676e2d636f72706f726174652d6c696768742d30332e63727430580603551d200451304f304d06072a2800110107013042304006082b060105050702011634687474703a2f2f7777772e612d74727573742e61742f646f63732f63702f612d7369676e2d636f72706f726174652d6c6967687430819e0603551d1f048196308193308190a0818da0818a8681876c6461703a2f2f6c6461702e612d74727573742e61742f6f753d612d7369676e2d636f72706f726174652d6c696768742d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f7269747930110603551d0e040a0408487cf8373c3f8bb6300e0603551d0f0101ff0404030204b030220603551d11041b3019811769742d736963686572686569744062617761672e636f6d30090603551d1304023000300d06092a864886f70d01010505000382010100776e67497156b2bcb608d9de5dcf85efbaa18859128f1934e0ee4ecab2f94ccee454e8610e6be5b6c37eb49e4c8026363d107ce5b3e62bea1b4927cd9ed152d430a77976215fabbe75b27a8f452c3f1e8943bc28339dfc241c2a3fc66872919067c1420a7e010933947e324117d118bfa2cb732d845c80b3aecd37ee481323fe9166bdad16d619298ee502c5533254e8bdb1f90ab97c9a4c343deb368f3def13c4ba4d66fc4861f5af516cd6d94e1b5ebddf5e8eceb331a7fb2c9bfb0d210c0d8639f81ffd9d2cbf50e50ba200828415e8dabad043cbfd95355915ab7f3542ce80c6d90b5d1b72d1d03c9370e7c9f90909b76f870ebdf248b918da2e705259d73082048f30820377a003020102020301aaed300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035313131333233303030305a170d3135313131333233303030305a30819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100b7e7da22b5b1e490481d12b44f78176471844ad832ddc5f7279fe488ba6484633595b1c4537213ca4b36fb7f56ba1073db079599b0625a5035fd29f4eab0934ca3eedb0122a4de0b0b571359a98dadd86fb2f07bd111861e8c3388ce302470142bb7e9efb8152519854942312ec809f5f1fdaf1b463754dbd91462a73caf00572714324d14c36d516cadef5f9b5b6cf810fe471990de1cce2e551dea4cbdfea1030c0ba098fe28bf73c66f23c8f2bcf2a0812808cc342a592ac121c9a9f0d428d86d5987b38e50e56e8506da83712e88ab8982e93b543c6b424eea07eff24bba1129d3be68869f3bbf77d297378b2630699140cff54f13bfb9767ae0f4b755150203010001a381e33081e0300f0603551d130101ff040530030101ff30110603551d0e040a04084191691cbfadd89830130603551d23040c300a8008446a95675579114f300e0603551d0f0101ff0404030201063081940603551d1f04818c308189308186a08183a08180867e6c6461703a2f2f6c6461702e612d74727573742e61742f6f753d412d54727573742d6e5175616c2d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f72697479300d06092a864886f70d010105050003820101000d3448690b4fc283d2ebf4e9c8184ec38c001e4fd13388242d3efa5113d8dac5078a1c6acbef2a10494df9fe6523ad66c823f72054c295f95c1d5475402a0c834648e7f11c41323c0e0779890917b958e1df00a3f3b29ad93bbb7752be8bd549e0147fa61fea98f291ecbc7f3ed013c3fbd6a38936adbb79a354308b167b08fb7e045f7109a002e2e2f46c1d0cf4cde2f56d9e3e6a97e96201de33244fd1500b42a284f3dfc3fd7b29d74d8b8ac530c1224f22af253b53a839c80305964eab0bd46ee8c4c32d9e761a296310ea04223a2473098ede7dc022ff7630517e942d6e73c6a6a936cd6fa846d21ac40497578597aec70591d800f7681c6527645d0a69308203cf308202b7a0030201020203016c1e300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035303831373232303030305a170d3135303831373232303030305a30818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100ad3d616e03f3903bc0410b8480cdec2aa39d6bbb6ec24284f75114e1a0a82d51a351f2de23f03444ff94ebcc05239540b90778a525f60abd4586e8d9bdc0048e854461ef7fa7c9fac125cc852c633f0560734905e0607895104bdcf91159ce717f409b8aaa24df0b42e2db56bc4ad2a50c9bb7433edd83d3261002cfea23c4494ee5d3e9b488ab0cae6292d46587d96ad7f4859fe4332225a5e5c833bac3c741dc5fc66acc000e6d32a8b687360062779b1e1f34cb903c78887405eb79f5937165ca9dc76b182d3d5c4ee7d5f83f317d8f87ec0a222f23e9febb7dc9e0f4eceb7cc4b0c32d62b59a71d6b16ae8ecd9edd572ecbe5701ce05559fded1608810b30203010001a3363034300f0603551d130101ff040530030101ff30110603551d0e040a0408446a95675579114f300e0603551d0f0101ff040403020106300d06092a864886f70d0101050500038201010055d454d159485cb39385aabf632fe480ce34a334623ef6d8ee67883104036f0bd407fb4e750fd32ed3c017c7c628ec060d11240e0ea55dbf8cb2139671dcd4ce0e0d0a68326cb9413119abb1077b4d98d35cb0d1f0a742a0b5c48eaffef13ff4ef4f460076eb02fbf99dd24096c7883ab89f1179f38065a8bd1fd37881a0514c37b4a65d2570d166c968f92e111468f1549808ac26920fde899ed4fab3792bd2a379d4ec8bac875368424c5151741e1b272ee3f51f29744dedaff7e1929981e8be3ac71750f6b7c6fc9bb08a6bd68803918f06773a8502dd98d543783fc63015ac9b6bcb57b789518b3ae8c9840cdbb150200a1a4aba6a1abdec1bc8c5849acd3182014e3082014a0201013081a730819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033020302f2f9300906052b0e03021a0500300d06092a864886f70d01010105000481806452bd278af0c176438b49ad48756ef60aa7d0badffe78407ee18e1b9dbab2036362b5314ac3dfe6b2a29164063ae49d6346fd126c4f8784c363231d9987565076313d509b3321bf733910119405ebb8903601f87e721a425ebc2075284692f6706b7aecadf8f6a2f4f0c4cb9d17aaf87ce597e78e5b7aef4807be6de1d9fa3800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000>/M(D:20080923065445+02\'00\')/Name(BAWAG P.S.K. AG)/SubFilter/adbe.pkcs7.sha1/ByteRange [0 164 7844 10962 ]                                                             >>\nendobj\n4 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1530/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛRÛH}÷Wèq·Êæªí›IHŠbI8qÕ¾	<­m™Õ…İâC÷òyØdK¶Ç\ZK„|NO_NwËƒ¿aø\npç¥¾È\"$Í+\r	Šür·Ú¹Ô}CîV!âà’ôĞí\'ç—«ÇÙï×ƒ/ƒÁqë8FÜ Pª~Æ:Á\nÕŞ$¢o¯­#µu§“ÁÉk.©n\n&óÁ	L›B™Y×†HTï]\r~¹øüiòyüõæ¯\r&˜y—;›8ø¢QÕKÇhXó¶é¢c$$7àÒËÁ	CJTÒ^1§Ë¸(Fµxíça…{<Ü÷,\nE‘‡B\nïr<­Ó·=‰`6Ó~î$ù~¥­ÀNäŸÖ«7=‰`E{,ØÔ¥9¡òó¨!QT%€ˆˆ¦vÖi±NËÕJg‡ÙÌ…°ÿ(Œ³†¦…>ış•éıadˆã/¨6ğïºx.tŒËü¹tã{.è!ÃÇKƒ^9\'/âtvÄ7¬ß7M?€¤›\n/ïĞh»ŒW½È\\(!ƒÓóBÿã‚9âÒDE]hmø©Î^ L¬?”GˆW§€R›*ÀXÒ(ğˆ2â0÷q:¼öõÚê§A¤iD›AÔ‘ÊõTNêŒoc¥phœ\',L#û~FnPîQöMjsdS<×:¹{p¢âşÄK¥ØE½Œ³E™;aÃşöDÃÈ„ÜfœÁ=p(” öÈ^…arÙ}_fwùİCšEÇËÙÚ­ 4ôÈaeµ»C2ÿá²<DRöƒ†Ø6è,vûØ#Ë˜İeN;±‡Š pòKgE¼t\0/}Ã‡¼z«‹ìû½Câ\0XôË2gÜöõğ£«ÅzJäÀ¾gû†?ä*:€ıJøÁ0»Ebˆ±Ó¿²¿¶…,¸m€u!k*÷«dn\'äj¼(J½\\ºCĞÔş&ÔÊdù,Iq9Ï3ëìÉEà•ÉLâÚÕm‚ã™ì×<£zêZşÚæÉëºkƒVSƒ;1Bx‹‡¹Ä*„…Ö!ùÆŒ·¸û·ãõK§÷¦OwÈ(¢ümútv(\Zº?tqŸUm[BTZu­FG,ã{ıÆbŒÔ…ÕærúĞé•¨MäÛ6ò°$ü_‘o“1$¨+{¥OÛØF¾\r;ŒèÈ×U0ıÕ6ô$´|†æ“.;Oğ\\FQ»»™¸<\"{­uT›êc™ÂrâökX˜Ö·ºôê8·Y ™éëüMœÛ°C)<ÃL0L!ı>ÚÄ9bvB­+<¸şq·˜g¦ÎefX‡ßn@\\à6ß·-Îê5µmÈü<W_- f½êŸ7\'Wá†ğf=×™=g\nG.‚Û$Máàa0]W˜é•Íÿ¸z¨Àø%óbé\\fLØ¤V†ÛãÿÌt–?fÚPƒ8ÕÙBWög	Ì)zÕÎ°¡Q+üçï¼ck¦·~Ğİª—·Y>_İ\\Œ\'Ó©wT9H§‡˜(»¯´©ÎOÇŸ^­ÏÉÌÈ£vO40nÙ|IÊhè|(Ñœ.Üù¨2ê%‡Ö»C®ñtìx¦M0A¢2”¼ió-Ø+tƒ.ĞĞ\rì±ùGÍ×±WWrâH•¸ıõBˆ:\0}ıî’8-Æ>í•42ÔÁàÀ9¯\n¼¿Ò,Ò|xš¸ZÜ¶\0Â:E`@Âı<›ub3yØB(EaæÁÚCÄ.ì±§-¦mO\Zsq}¡Â]¯óÛØm±_$MŠŠ]h\"%é‰ÚØ»ğ&ˆ!Ì•P˜Î¥Gı)û9BôC¢ouv›•iO‚´42¬zĞ¼?\"]ÖZ!Y-aÕ£¨o¡È\\ÔTÖ„¥ Dº•ğ–j›wï1¿¼¼ÀCh¯¾aJu…ÕÉùî?`šŸÊ°úz7ßèm`å18ÓE0ÿ‘ãìV\'LF³`šdÌñÜşù/Ì±v‚Jàª®`vÎ¡tâ<‡½cQ€ÜC+_.í›öğ`‹e\nendstream\nendobj\n10 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤*¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0K°\nendstream\nendobj\n11 0 obj<</Count 4/Type/Pages/Kids[3 0 R 12 0 R 13 0 R 14 0 R]>>\nendobj\n3 0 obj<</Type/Page/Contents 10 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf1 4 0 R>>>>/Annots[2 0 R]/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n15 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1664/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµZÍRã8¾ç)tÜ­\"B¿–|4a¦26	Pµ·˜Ÿ’YÇÌNñ ûû{Ø–ÉD±¼qr	1ò×­îşúGvïÏÅ>ˆ8_«‹<ÆÊ|³ˆâ˜Ã7/µõ}÷Vë‹\rğ\Z+¶iùá—ÑË÷Û;‚N½ßz½íÚ	‚…AaLcÿí$—8Òş£0Û¸|¥]iw4í~*Õ¥Å\"4½ëQÂ°”hú¶]’yiÚËâŞ—Ş/Ãó³éyr9ùıòôW4ıÀÌ]l0õÈ‹cÌt£8Î¢•\\[\\¼MEQ%BeÊ±–…U^12gy¾}3Ú’CY³NpôA\rİ‹Æq CcMê2~,æİîDò2Ò)\"t\'àùfB…Éºç,^:İ‰äÇâ‹^šêpçğ\0îH…ãbKRb*×ÜYÌóÅüõå%Í6K3¢æ­pÁ×b,ôë²×ùıfdğ-øaMó·<ÍPòº|{õã(.Ù&Å“gƒ^g™Ïæ·[lÃ›m³®@AV1üõæ÷§3Ÿò\Z*@#²ÊMÈ`ôeşô¤J©‰ÉÑløë4k‘™x³˜ˆ±(v1¤+¢X2pŸqO–‹€$.ÔŠÊ6üàrì\rËAt]ˆ!é-Ì\rÌ\n‚®\"ŞÆ6™Â“ãa¡ùh‡ƒØ*h¿m!±Z“gœ>Ş<xQIsà¦JÖQG³ìéuÙyQy”Ã›V¥>#ï‚Lşõ8BÂ¨é-mØ£ômöğÄD£YºÌ³…GU°‰fÍ%Y‘Æ–1:>$Åüë‡å²^\n¶êlxá‰\n©S6§r)E™aĞÆû>k9ñ=M…zšMİœ¶+/RúûùüÓ`ŒN’³át38ö„8+Îjèíä5\nf2ªÎ<~ÕUA\'\"TWÃ‘\0]5ôŠL×@‡Ã—#D”À\"£¢78¼AEgô¶a÷Eo[†EoOIlMo^²èmƒÒ½C½ÈÀ‹Q°iÌ-/^ ˆÇ»·`!©#Á£YÈ„®ÁvÀB¥X\rXXØ×°öÌU\',¤ZXşÛ’›á,t`ÇéÍÃÜ4¿ó#Óˆ1É}DŒ±hpÖDtÄVDôq¦%xŞĞwDÄ`G.mAD%m~+\"M‰Š¢XEègù}‘åè>]d÷hù×âÛâvÖM`Âô´ˆŞ78°_vkb‚µ5Lä%¿‰í€Œ§Q]Sp1äl4J“éø%S¨Ä{±š›G¼ˆ¯ÂÜ–d,m\r‡>’\Zµ!©\ruR‘t|\0IyTÓÔíÔKmÔîÊ¥Õ²3–JeåÁÙüiñ2ËÍ9Ìól~‹vóª)t¤øãœS,šUg$Â”Š\Zjç>`ÈpÍ¶®l\"ô;nRòå=¸»é½^ƒr¦önft˜Œ§SàSá1©u{œL&Éñàl:N¾ÌÁørœ ãä\n~Oèòlxv~}ÖM*çÖ}`G¾»§Nß‘auú¾™»mƒaÃSŸ›Û¦.îò²«ÔêE\"°n®>•™5iN.’NæxSTÜŸ±Zò*cÌÓ‰·N&lƒ¼\n#Dá¸b÷gÁ¶ô¦ÖÈ¶Õ´vô¦ûä½eG­‰\rO¥o‚mËoõ ºâ·ãÆ-ü†fœ·(ÃÄša?ÆW_&°é±pÛƒ:½3~Û »ëZñ;H×¶ü&|3¿aÏ´›ƒºXÓ›‡ÓÛF5÷Ao[†ÅnÏ‘O[v;véjî°A»;U¹×…±Äqø(­­“ärz>Jv¬Ú0 GZ¸À~NÃøĞTÃ0ÂÀå&\"b¨ì¬Qa¢ ‚÷}m];Â¨h„±Q÷E[†EßQcËn×±KG|Q{ÓM·jÓ ÷VÖŒì!š\\ÃDTA°Æê€fhÏ8°~¬Z½ibVˆ‚¶5¹}êª£Ë×Y éµ\Z\'W\'îæ‚bá rõ»ètˆ’Œú‚.”ãA°ÒPÇÖÚ1\Z\r¡–RB/®ˆˆ”¾ úğ‚öl¥ßm¥0kJyù®”SE*—]\'§èOğ#sÈ‚îşÍP’}Ksô:¿E×Y¾¼y˜İ•?ÿ^æi–™Çîp5E‹e¾ü>ËfËeŠ’§ü1ß§Ëôù¹¼éãş„uI_\nendstream\nendobj\n16 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤)¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0t±\nendstream\nendobj\n12 0 obj<</Type/Page/Contents 16 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf2 15 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n17 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1634/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛrâF}ç+æ)•TÁxî\Z=Ê6‹)Ìe±°«öµåØ±\rWÊß—oÈä3ò–„Ä4\n\nU[`­tº§§ÏéîQã÷Å>ˆ¾¶¹½ø›)Š}?g{÷·ŠQàn­°8¯±Çİ~ÑíÏ~<=t½h|m4N{\'1\nc\Zÿ¿x\'¹ÄJü‡ÙÑÛ·ŞÑ­w—aãâKê.MnBásƒ†¥DáçiWXæih–É³³ÆÏ½á “»o“Î/(ü\rÀâ§b°vh±çû˜éRsœ©­]ÓœÊE*·¡¥6åXËÄ†—^‰m\\¾O×ëÓ‹Ñ†ÊÊíp‚Õæº}ßÁ†ÆšìÛøXÌë]‰äi¦¬×•ÀÎ—Û*LîïüÇbVëJ$gØ?È/ó2^¡vßîÀéa?Y’”˜ÊŒ;‹ùz1ßÌfÑò¸µø‚*_\n<3c ?üı²ÜÌ=;ÁE~˜À·Ñús-Q°Y}nìøKvÌñà=FO‚³ZOçO\'bÃËc“Õ  Ë¾y|Á­pjs^C(Eö „ò2}µş°H%ibrK4ş!ZVP&^¾LøX$«€Ò9ñX¸Å8µn°pqám©lÂ·\'c¨›Ñ¬|=Ò\'˜ë¨\n‚n3ŞÄ•Â¢q°ĞÆ¡éÛA…í³Ô{yÆÑëã‹•”\'xêÉ}Ôştù¶Y•eJ3rXcyÁÊİgdg²Ş¶”@•;-MØ€¢qûêf0XºGŸ©ÎËD\'úÂæ4ƒ|r:£vt!‹³p“,(êÊ¹Ø…nï<TÅ·½Œ‰ªç°AJI--ğÔÃÒA1$Ù\n’	?ì\\ä£­ÙqšÕ:íé i/™=H–WD)EZ¨\nHM_·ÎÊ«Ú»’¢I+Ô(Jw°*}\0>p‰*æ£şğ²{Ûö4òá¸œµ–œ×0Vì–¢NğÚÃº<òùRLØîêLÚƒğ¦m©(ü£@9ÛC·ò(å +Lª=PB©\Z¡GÉîbdå1ÙqÑ´0éƒiÊ4w(.9Mèûëœ‡–IÌQE$üĞsfK‘ªä4A›„ÔEÎ‚¯\'ÈY(ÔçnD‰…—çrF”lÜ«O_Ş¡5Eıi´Z/v]QåÛ—gGÁHÿ*ËJÎË®ÒÙ¬\ZbC­˜ÔúÒÃy©Óœï£;ØË`\0:„ˆk\rSD/‡m’Øy/@íêIJÏhÿ˜²éò1,_Œ	K´ÚşQOi\n\Z}wÃo¨İé´Ç¨Ó¿¼A?¡«áYë‘°;G,c_Xzo*„vÉdâ6=^S.™¨0Ú‹\'Ñs¿R;ØNû&¸\ríSª®€+=ciw¯nP?÷&w¶¤a.½`íÛ®äßŒÛhÔß\rÁí`œô1 \05U»‚©]µ£5‘*şáBªŠJoÂşoJo\Z1”ş¼ib§ô…ĞÔ¦ô&jJïº•“cJO´¿Sz?SzÈ[*&%£fwŠ­],	åGùbLØ»Q0F×°\"‹Ü¸¶éq¶xûà¨®>½€*8ÕiŸî××§,Ä»èz×¤\\&´¡\\¶‚T±O/À[å°*=MĞ&cuÑÓ„=)³ÕˆBÍ1Ù‰âÚœ(&ìmô=²¼±‰sÍáT0eİ¥ÄÏF[0~û!iÕ3 ‚\rE8aÌ#Ô§5àwg@ÔÖºUÍi^ØˆR5§MĞ¦–uå´sòA?^¡µ\"…‹Rš6Tğ“Cÿ7t¿h-W‹y4ÿ€ï×Ç—(~­„‚óNR3RùÂmYTaåŞš¨ÎŸ£9ºZÌfı©íÅLEÁ5mzkKıŠı‰Î­íPÅYÅmZA+ffaù‰Ä„Lsµ9ÿ(LâËaT¡Q·w‹z“±¥È^Iäoi“1)1¾g®E‹^èôU0ì‚Î¬_ß[Îãs•Ûw@é)¼\'µ•~¸vZnÀÆ”dŞÑï÷ˆ\0Á§£{\"”§GD_ŒhË¢o/ãóXæğ*„òìL=erÚ´>4Âw¸‡Ñåtş†ÿY¢`ù=z]£Íü	=¼.×«Ç—ésúç_«u´\\Æ¯¬^áj„F‹Õzõcº|›®V\nŞÖ¯w½¿§.á_¼›Q\nendstream\nendobj\n18 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤+¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0²\nendstream\nendobj\n13 0 obj<</Type/Page/Contents 18 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf3 17 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n19 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1504/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœ­YÍRã8¾ç)tÜ­\"B¿–|t 0„dC€Ú½e3d\'?³¶Cmñ ûû{Ø–8rÅòŒ9@pì¯[İ_ê–;u(&ğƒHåÏæ\"±2Y@qÈáÃËbïşnõ*\nÜ­,Àk¬Ø±ÛÏo‹ï_^	º\\u~ëtN{\'…1Iø#ŞI.q ¾Q˜½}ãİx×›tÎ¯\nwi~š¼v(aXJ4ù8í\nË¼m€eşì¢óËíğ~2Œşx¼şMş0ó”ëOöÂ3]k³`c×62BQPÚ€€ĞZ‚r¬enCWŒŞ|še§£-;”ÕÛáv¸ïZ4C\Zk²oã}µlw%’L«X¾+Ì×Û@&÷3ÿ¾Z´ºÉøEL]šjÿäpÚ‘\n‡ù’¤ÄTnkgµÌVËõb\'Ç­™AıR¸à[3úó¿oÉzùõ82d‚‹õaßÅÙG\'(Z§k7¾‡ã’s<šô<8i6]~9^›í~\0%ÈÊ\n_¿¼áîdêr^ÃP‹¬ „ò2=Íâ¿B‰ÀÂCš˜Üš\rÿ\'\r”‰×g€‰‹|À!]V!ŠuA»ŒSg‚…‡ˆµ)e¾ÿ8v€úiİnDŸAô‰ÊõTA7Œ·±R84Îº‘Ã8œ…nPáQö[j‰Õ¶xÆñìåÍ‰Jê‰*¹:˜&ßÖió‚‚y”Ã\Zë7¬Ò}Fv† !=RáÓ¦”eiÃò¢;\"Ô0›@\'ƒ®û÷ıqtwƒúã‡›‹Oıñãı5ŠM0Y/	R°Í>o,S=Y/ÂRŠB+ gRt°~áß	Œoü7¬w·Œ?¥;Ø»øs¼Lßã$vÆîm‰rê’Ò¸»ÇQt¢\0(lù*ğF§!ß¡†w˜Òà8Ä¨@ç„n<Ã^0¦bŒˆ€H\n©”*,=ôW’BŞ+ğÃës’ÿh\Zü‡¶%Lµğ¤óiËËØ°½øcú6æ Á4N³dåLP÷]`lƒ‹20ª!¨À.Ğ†BPmO¼³ßéúÎ«Ì¢²6’OÃ«ş]F÷·“ãà6`]ŠB€¨z¡ŸÊ\Z…\03ìÎîë«‚VŒ__MxøªaÜczô–£<qøq`nÙRyÃ„d…ÃIŒ0ÀBùóÂF½ì»Ê9ôé–ÍmÁhp4ˆú“ñ†Èˆ¶+\"f\0i ×¶:OdšRvë˜észtt¥*ÙğO—¥*¹vƒÓZ¤‹­jlr¤ 	‚Ü–\0UxFOv\"MèÃ÷Vºa™Æ²¶ä¯\r;Âøw/¢ñ%Šzcè~Ì4ÒZs`›\nÌw¡fT3Ûš66¼Õ8ãä·ò 8`©ÀsÕR7\\A=ƒ¶8èKª1m vP„Õ\"óKAÌaî(úıîæªz°1¢ëAïS;Ä7|Ö™¢şØ›å;tc‹x™æSÇYcc>Úv|d.ínÚ“ÙğÊÚ”6èiM}SÈBLı\'Ê­™ú>s	ÌB¿3ñ¥š1‘QO&zQ”Ë°a³8‹]ÑaĞ5  {Õ/	è8k,ˆ6<“m1ĞFm‘Ş©ƒüg(µFwpªsA$¦\"s\Z\'èâ-™¥ÙÌ),\rYH¸ßR„×Ñr¹¶?[Î§_Ûâ¡lñıT8$¤öĞqj^‚´ÄD·E&ú¦¯™†ÖÔ+¸RÅÎL\Z*hÙë¥p{şY¾2wˆœ>{»´êˆ.Ş;,èrzr›ŠùƒÊÍsZ+qhÀ-ıô=dë-ƒÁ-Œ¶”ĞÑÒ#¢ÏG´{òİ¦õ–\\Ã¬”òb†R[ë½è9ºFEçzÓå7ôú_‚¢äs<ËĞzù=Ï’,}y›¾ÿş“fq’˜DÎàjŒF«4K¿C~§i\Z£è[6‹—_ã4Ï‹‡—ğ?:WŸ°\nendstream\nendobj\n20 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤™(¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0Æ³\nendstream\nendobj\n14 0 obj<</Type/Page/Contents 20 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf4 19 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n7 0 obj[/PDF/Text/ImageB]\nendobj\n5 0 obj <</Type/XObject/Filter/FlateDecode/Length 80/Height 30/BitsPerComponent 1/Subtype/Image/Name/IMmpdf1/ImageMask true/Width 2232/Decode[1 0]>>stream\nxœíÖ1\00ÃÀĞ/ƒÂ-İdßé)hÑŒôëhCg‘ê]mè,R=zBÑY¤zô „¢³HõèAEg‘êÑƒŠÎ\"Õ£!Eª÷\0hœFq\nendstream\nendobj\n6 0 obj <</Type/XObject/Filter/FlateDecode/Length 700/Height 78/BitsPerComponent 1/Subtype/Image/Name/IMmpdf0/ImageMask true/Width 360>>stream\nxœÍ×;Õ0`G)B…[\n„Y‚\r·2K ¼vÇvØ@¨(Ù‚—`D‘\"›ÿ?vîc\"d‰HÉÍã‹slŸÑÔzûşËG¶oÿş:§CØÃn6Ã°†—ax†§aXÃ~vÃ°†äèA| G?ÃrÔş	ç«ä±ê’£_jıqÅ”bˆ\n­±^ -\nqÆm\\Ö÷hÇ×\Z]ôuSÄ~•òÀ\0V‚ÙM”]Çy\"†cCÀ¸®ÛçMÇXõÄ.â YÎ/ìyéxÕ‚ÍÚ[ÆuÍ{Ì	{;®cSq0·x#:Fçùß%S¸FÇ9ã#êe\'Ş¦Öû˜\r]ğJìd\'f8gŒqæk´´“`#8.·˜×u½ÂÑb8qIŒ~uìs0ˆõójqŠ¨ˆ¹2^cp®0:°púD¿cçg–úUrã…Œ`•Ù\'%lˆè©òÀ§©Mh”Iñéù˜£ğÛ†.dŸIÁPV†@iÂ|#H\r|§ZŠ*—%7$+Ë«ZæJ—Èìñ\rûÂ†€uÃ¾a€Ó,Ø5ÌÑ\'~¹Übt/ø¬³\"æ8÷ÄE&&Å%®mR<uçâeAµš²Íib®\\ptIúÃ4vqI-7d+sš‹ôØN\r\'#§Xç[Œ&Ü\rG`Ç”üY°ábÀ¤Ì;æ\\iœ\"ùÎÉ_ë\'Ä¼pY™+ñ’™K‚]Ãh1-}Á®vi8¸ĞqŒ–#Úêw,$ qÔ÷R ‹Óê½ƒö‘V7ï¥€cwÁ…ÏT¿U$Á±—/ö8YÓğ´ñY‘Z×1ŸIadOw¼l\\?áÄã³mRVÌ91zœ­ëogY’o˜ŠÄõ¿‚ïoÜ’m³ş7Oşû÷ö®¯à×GğÇaøĞÿVàŸ‘\nendstream\nendobj\n8 0 obj<</Type/Font/BaseFont/Helvetica-Bold/Subtype/Type1/Name/Fmpdf1/Encoding/WinAnsiEncoding>>\nendobj\n9 0 obj<</Type/Font/BaseFont/Helvetica/Subtype/Type1/Name/Fmpdf0/Encoding/WinAnsiEncoding>>\nendobj\n21 0 obj<</Type/Catalog/AcroForm<</SigFlags 3/Fields[2 0 R]>>/Pages 11 0 R>>\nendobj\n22 0 obj<</CreationDate(D:20080923065445+02\'00\')/Producer(iText 1.4.8 \\(by lowagie.com\\))/ModDate(D:20080923065445+02\'00\')>>\nendobj\nxref\n0 23\n0000000000 65535 f \n0000000117 00000 n \n0000000015 00000 n \n0000010019 00000 n \n0000008020 00000 n \n0000016736 00000 n \n0000016989 00000 n \n0000016703 00000 n \n0000017850 00000 n \n0000017954 00000 n \n0000009802 00000 n \n0000009947 00000 n \n0000012262 00000 n \n0000014463 00000 n \n0000016534 00000 n \n0000010200 00000 n \n0000012117 00000 n \n0000012431 00000 n \n0000014318 00000 n \n0000014632 00000 n \n0000016389 00000 n \n0000018053 00000 n \n0000018137 00000 n \ntrailer\n<</Root 21 0 R/Size 23/Info 22 0 R>>\nstartxref\n18269\n%%EOF\n','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-08 23:41:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('aa5fe149-d31e-4809-802c-15d8dc3419a2','certificat','Test fÃ¼r Mach','7522bc7f-42cf-415c-a050-da12518a4cd3','application/pdf','20080908.pdf',18806,'%PDF-1.4\n%âãÏÓ\n2 0 obj<</P 3 0 R/Type/Annot/F 132/T(Signature1)/V 1 0 R/Rect[0 0 0 0]/Subtype/Widget/FT/Sig>>\nendobj\n1 0 obj<</Filter/Adobe.PPKMS/Type/Sig/Contents <30820ebb06092a864886f70d010702a0820eac30820ea8020101310b300906052b0e03021a0500302306092a864886f70d010701a0160414a3939baa417b8f344e2be299336cd946bf5fcdeda0820d1d308204b33082039ba003020102020302f2f9300d06092a864886f70d010105050030819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033301e170d3037303232303135343535385a170d3132303232303135343535385a305b310b3009060355040613024154310e300c060355040a0c054241574147310b3009060355040b0c0249543118301606035504030c0f424157414720502e532e4b2e204147311530130603550405130c33343038393431313432383930819f300d06092a864886f70d010101050003818d00308189028181009f18e18acafeced47e87948327150690350f940b496d3d832b80568e1578be143898e395bd10d5863bab7400f0b3f7c41052e298169f96ae84f3931805bae5fb150b191a80cee84538a05cce009c4457f3b059e34f5d9668a7317ced1a8db2ea88079ce7685599fd678ebe8a41154bdb18ec239098f4ee063944d4142ccb585b0203010001a38201bd308201b930130603551d23040c300a80084191691cbfadd898305506082b0601050507010104493047304506082b060105050730028639687474703a2f2f7777772e612d74727573742e61742f63657274732f612d7369676e2d636f72706f726174652d6c696768742d30332e63727430580603551d200451304f304d06072a2800110107013042304006082b060105050702011634687474703a2f2f7777772e612d74727573742e61742f646f63732f63702f612d7369676e2d636f72706f726174652d6c6967687430819e0603551d1f048196308193308190a0818da0818a8681876c6461703a2f2f6c6461702e612d74727573742e61742f6f753d612d7369676e2d636f72706f726174652d6c696768742d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f7269747930110603551d0e040a0408487cf8373c3f8bb6300e0603551d0f0101ff0404030204b030220603551d11041b3019811769742d736963686572686569744062617761672e636f6d30090603551d1304023000300d06092a864886f70d01010505000382010100776e67497156b2bcb608d9de5dcf85efbaa18859128f1934e0ee4ecab2f94ccee454e8610e6be5b6c37eb49e4c8026363d107ce5b3e62bea1b4927cd9ed152d430a77976215fabbe75b27a8f452c3f1e8943bc28339dfc241c2a3fc66872919067c1420a7e010933947e324117d118bfa2cb732d845c80b3aecd37ee481323fe9166bdad16d619298ee502c5533254e8bdb1f90ab97c9a4c343deb368f3def13c4ba4d66fc4861f5af516cd6d94e1b5ebddf5e8eceb331a7fb2c9bfb0d210c0d8639f81ffd9d2cbf50e50ba200828415e8dabad043cbfd95355915ab7f3542ce80c6d90b5d1b72d1d03c9370e7c9f90909b76f870ebdf248b918da2e705259d73082048f30820377a003020102020301aaed300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035313131333233303030305a170d3135313131333233303030305a30819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100b7e7da22b5b1e490481d12b44f78176471844ad832ddc5f7279fe488ba6484633595b1c4537213ca4b36fb7f56ba1073db079599b0625a5035fd29f4eab0934ca3eedb0122a4de0b0b571359a98dadd86fb2f07bd111861e8c3388ce302470142bb7e9efb8152519854942312ec809f5f1fdaf1b463754dbd91462a73caf00572714324d14c36d516cadef5f9b5b6cf810fe471990de1cce2e551dea4cbdfea1030c0ba098fe28bf73c66f23c8f2bcf2a0812808cc342a592ac121c9a9f0d428d86d5987b38e50e56e8506da83712e88ab8982e93b543c6b424eea07eff24bba1129d3be68869f3bbf77d297378b2630699140cff54f13bfb9767ae0f4b755150203010001a381e33081e0300f0603551d130101ff040530030101ff30110603551d0e040a04084191691cbfadd89830130603551d23040c300a8008446a95675579114f300e0603551d0f0101ff0404030201063081940603551d1f04818c308189308186a08183a08180867e6c6461703a2f2f6c6461702e612d74727573742e61742f6f753d412d54727573742d6e5175616c2d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f72697479300d06092a864886f70d010105050003820101000d3448690b4fc283d2ebf4e9c8184ec38c001e4fd13388242d3efa5113d8dac5078a1c6acbef2a10494df9fe6523ad66c823f72054c295f95c1d5475402a0c834648e7f11c41323c0e0779890917b958e1df00a3f3b29ad93bbb7752be8bd549e0147fa61fea98f291ecbc7f3ed013c3fbd6a38936adbb79a354308b167b08fb7e045f7109a002e2e2f46c1d0cf4cde2f56d9e3e6a97e96201de33244fd1500b42a284f3dfc3fd7b29d74d8b8ac530c1224f22af253b53a839c80305964eab0bd46ee8c4c32d9e761a296310ea04223a2473098ede7dc022ff7630517e942d6e73c6a6a936cd6fa846d21ac40497578597aec70591d800f7681c6527645d0a69308203cf308202b7a0030201020203016c1e300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035303831373232303030305a170d3135303831373232303030305a30818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100ad3d616e03f3903bc0410b8480cdec2aa39d6bbb6ec24284f75114e1a0a82d51a351f2de23f03444ff94ebcc05239540b90778a525f60abd4586e8d9bdc0048e854461ef7fa7c9fac125cc852c633f0560734905e0607895104bdcf91159ce717f409b8aaa24df0b42e2db56bc4ad2a50c9bb7433edd83d3261002cfea23c4494ee5d3e9b488ab0cae6292d46587d96ad7f4859fe4332225a5e5c833bac3c741dc5fc66acc000e6d32a8b687360062779b1e1f34cb903c78887405eb79f5937165ca9dc76b182d3d5c4ee7d5f83f317d8f87ec0a222f23e9febb7dc9e0f4eceb7cc4b0c32d62b59a71d6b16ae8ecd9edd572ecbe5701ce05559fded1608810b30203010001a3363034300f0603551d130101ff040530030101ff30110603551d0e040a0408446a95675579114f300e0603551d0f0101ff040403020106300d06092a864886f70d0101050500038201010055d454d159485cb39385aabf632fe480ce34a334623ef6d8ee67883104036f0bd407fb4e750fd32ed3c017c7c628ec060d11240e0ea55dbf8cb2139671dcd4ce0e0d0a68326cb9413119abb1077b4d98d35cb0d1f0a742a0b5c48eaffef13ff4ef4f460076eb02fbf99dd24096c7883ab89f1179f38065a8bd1fd37881a0514c37b4a65d2570d166c968f92e111468f1549808ac26920fde899ed4fab3792bd2a379d4ec8bac875368424c5151741e1b272ee3f51f29744dedaff7e1929981e8be3ac71750f6b7c6fc9bb08a6bd68803918f06773a8502dd98d543783fc63015ac9b6bcb57b789518b3ae8c9840cdbb150200a1a4aba6a1abdec1bc8c5849acd3182014e3082014a0201013081a730819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033020302f2f9300906052b0e03021a0500300d06092a864886f70d01010105000481806452bd278af0c176438b49ad48756ef60aa7d0badffe78407ee18e1b9dbab2036362b5314ac3dfe6b2a29164063ae49d6346fd126c4f8784c363231d9987565076313d509b3321bf733910119405ebb8903601f87e721a425ebc2075284692f6706b7aecadf8f6a2f4f0c4cb9d17aaf87ce597e78e5b7aef4807be6de1d9fa3800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000>/M(D:20080923065445+02\'00\')/Name(BAWAG P.S.K. AG)/SubFilter/adbe.pkcs7.sha1/ByteRange [0 164 7844 10962 ]                                                             >>\nendobj\n4 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1530/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛRÛH}÷Wèq·Êæªí›IHŠbI8qÕ¾	<­m™Õ…İâC÷òyØdK¶Ç\ZK„|NO_NwËƒ¿aø\npç¥¾È\"$Í+\r	Šür·Ú¹Ô}CîV!âà’ôĞí\'ç—«ÇÙï×ƒ/ƒÁqë8FÜ Pª~Æ:Á\nÕŞ$¢o¯­#µu§“ÁÉk.©n\n&óÁ	L›B™Y×†HTï]\r~¹øüiòyüõæ¯\r&˜y—;›8ø¢QÕKÇhXó¶é¢c$$7àÒËÁ	CJTÒ^1§Ë¸(Fµxíça…{<Ü÷,\nE‘‡B\nïr<­Ó·=‰`6Ó~î$ù~¥­ÀNäŸÖ«7=‰`E{,ØÔ¥9¡òó¨!QT%€ˆˆ¦vÖi±NËÕJg‡ÙÌ…°ÿ(Œ³†¦…>ış•éıadˆã/¨6ğïºx.tŒËü¹tã{.è!ÃÇKƒ^9\'/âtvÄ7¬ß7M?€¤›\n/ïĞh»ŒW½È\\(!ƒÓóBÿã‚9âÒDE]hmø©Î^ L¬?”GˆW§€R›*ÀXÒ(ğˆ2â0÷q:¼öõÚê§A¤iD›AÔ‘ÊõTNêŒoc¥phœ\',L#û~FnPîQöMjsdS<×:¹{p¢âşÄK¥ØE½Œ³E™;aÃşöDÃÈ„ÜfœÁ=p(” öÈ^…arÙ}_fwùİCšEÇËÙÚ­ 4ôÈaeµ»C2ÿá²<DRöƒ†Ø6è,vûØ#Ë˜İeN;±‡Š pòKgE¼t\0/}Ã‡¼z«‹ìû½Câ\0XôË2gÜöõğ£«ÅzJäÀ¾gû†?ä*:€ıJøÁ0»Ebˆ±Ó¿²¿¶…,¸m€u!k*÷«dn\'äj¼(J½\\ºCĞÔş&ÔÊdù,Iq9Ï3ëìÉEà•ÉLâÚÕm‚ã™ì×<£zêZşÚæÉëºkƒVSƒ;1Bx‹‡¹Ä*„…Ö!ùÆŒ·¸û·ãõK§÷¦OwÈ(¢ümútv(\Zº?tqŸUm[BTZu­FG,ã{ıÆbŒÔ…ÕærúĞé•¨MäÛ6ò°$ü_‘o“1$¨+{¥OÛØF¾\r;ŒèÈ×U0ıÕ6ô$´|†æ“.;Oğ\\FQ»»™¸<\"{­uT›êc™ÂrâökX˜Ö·ºôê8·Y ™éëüMœÛ°C)<ÃL0L!ı>ÚÄ9bvB­+<¸şq·˜g¦ÎefX‡ßn@\\à6ß·-Îê5µmÈü<W_- f½êŸ7\'Wá†ğf=×™=g\nG.‚Û$Máàa0]W˜é•Íÿ¸z¨Àø%óbé\\fLØ¤V†ÛãÿÌt–?fÚPƒ8ÕÙBWög	Ì)zÕÎ°¡Q+üçï¼ck¦·~Ğİª—·Y>_İ\\Œ\'Ó©wT9H§‡˜(»¯´©ÎOÇŸ^­ÏÉÌÈ£vO40nÙ|IÊhè|(Ñœ.Üù¨2ê%‡Ö»C®ñtìx¦M0A¢2”¼ió-Ø+tƒ.ĞĞ\rì±ùGÍ×±WWrâH•¸ıõBˆ:\0}ıî’8-Æ>í•42ÔÁàÀ9¯\n¼¿Ò,Ò|xš¸ZÜ¶\0Â:E`@Âı<›ub3yØB(EaæÁÚCÄ.ì±§-¦mO\Zsq}¡Â]¯óÛØm±_$MŠŠ]h\"%é‰ÚØ»ğ&ˆ!Ì•P˜Î¥Gı)û9BôC¢ouv›•iO‚´42¬zĞ¼?\"]ÖZ!Y-aÕ£¨o¡È\\ÔTÖ„¥ Dº•ğ–j›wï1¿¼¼ÀCh¯¾aJu…ÕÉùî?`šŸÊ°úz7ßèm`å18ÓE0ÿ‘ãìV\'LF³`šdÌñÜşù/Ì±v‚Jàª®`vÎ¡tâ<‡½cQ€ÜC+_.í›öğ`‹e\nendstream\nendobj\n10 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤*¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0K°\nendstream\nendobj\n11 0 obj<</Count 4/Type/Pages/Kids[3 0 R 12 0 R 13 0 R 14 0 R]>>\nendobj\n3 0 obj<</Type/Page/Contents 10 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf1 4 0 R>>>>/Annots[2 0 R]/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n15 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1664/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµZÍRã8¾ç)tÜ­\"B¿–|4a¦26	Pµ·˜Ÿ’YÇÌNñ ûû{Ø–ÉD±¼qr	1ò×­îşúGvïÏÅ>ˆ8_«‹<ÆÊ|³ˆâ˜Ã7/µõ}÷Vë‹\rğ\Z+¶iùá—ÑË÷Û;‚N½ßz½íÚ	‚…AaLcÿí$—8Òş£0Û¸|¥]iw4í~*Õ¥Å\"4½ëQÂ°”hú¶]’yiÚËâŞ—Ş/Ãó³éyr9ùıòôW4ıÀÌ]l0õÈ‹cÌt£8Î¢•\\[\\¼MEQ%BeÊ±–…U^12gy¾}3Ú’CY³NpôA\rİ‹Æq CcMê2~,æİîDò2Ò)\"t\'àùfB…Éºç,^:İ‰äÇâ‹^šêpçğ\0îH…ãbKRb*×ÜYÌóÅüõå%Í6K3¢æ­pÁ×b,ôë²×ùıfdğ-øaMó·<ÍPòº|{õã(.Ù&Å“gƒ^g™Ïæ·[lÃ›m³®@AV1üõæ÷§3Ÿò\Z*@#²ÊMÈ`ôeşô¤J©‰ÉÑløë4k‘™x³˜ˆ±(v1¤+¢X2pŸqO–‹€$.ÔŠÊ6üàrì\rËAt]ˆ!é-Ì\rÌ\n‚®\"ŞÆ6™Â“ãa¡ùh‡ƒØ*h¿m!±Z“gœ>Ş<xQIsà¦JÖQG³ìéuÙyQy”Ã›V¥>#ï‚Lşõ8BÂ¨é-mØ£ômöğÄD£YºÌ³…GU°‰fÍ%Y‘Æ–1:>$Åüë‡å²^\n¶êlxá‰\n©S6§r)E™aĞÆû>k9ñ=M…zšMİœ¶+/RúûùüÓ`ŒN’³át38ö„8+Îjèíä5\nf2ªÎ<~ÕUA\'\"TWÃ‘\0]5ôŠL×@‡Ã—#D”À\"£¢78¼AEgô¶a÷Eo[†EoOIlMo^²èmƒÒ½C½ÈÀ‹Q°iÌ-/^ ˆÇ»·`!©#Á£YÈ„®ÁvÀB¥X\rXXØ×°öÌU\',¤ZXşÛ’›á,t`ÇéÍÃÜ4¿ó#Óˆ1É}DŒ±hpÖDtÄVDôq¦%xŞĞwDÄ`G.mAD%m~+\"M‰Š¢XEègù}‘åè>]d÷hù×âÛâvÖM`Âô´ˆŞ78°_vkb‚µ5Lä%¿‰í€Œ§Q]Sp1äl4J“éø%S¨Ä{±š›G¼ˆ¯ÂÜ–d,m\r‡>’\Zµ!©\ruR‘t|\0IyTÓÔíÔKmÔîÊ¥Õ²3–JeåÁÙüiñ2ËÍ9Ìól~‹vóª)t¤øãœS,šUg$Â”Š\Zjç>`ÈpÍ¶®l\"ô;nRòå=¸»é½^ƒr¦önft˜Œ§SàSá1©u{œL&Éñàl:N¾ÌÁørœ ãä\n~Oèòlxv~}ÖM*çÖ}`G¾»§Nß‘auú¾™»mƒaÃSŸ›Û¦.îò²«ÔêE\"°n®>•™5iN.’NæxSTÜŸ±Zò*cÌÓ‰·N&lƒ¼\n#Dá¸b÷gÁ¶ô¦ÖÈ¶Õ´vô¦ûä½eG­‰\rO¥o‚mËoõ ºâ·ãÆ-ü†fœ·(ÃÄša?ÆW_&°é±pÛƒ:½3~Û »ëZñ;H×¶ü&|3¿aÏ´›ƒºXÓ›‡ÓÛF5÷Ao[†ÅnÏ‘O[v;véjî°A»;U¹×…±Äqø(­­“ärz>Jv¬Ú0 GZ¸À~NÃøĞTÃ0ÂÀå&\"b¨ì¬Qa¢ ‚÷}m];Â¨h„±Q÷E[†EßQcËn×±KG|Q{ÓM·jÓ ÷VÖŒì!š\\ÃDTA°Æê€fhÏ8°~¬Z½ibVˆ‚¶5¹}êª£Ë×Y éµ\Z\'W\'îæ‚bá rõ»ètˆ’Œú‚.”ãA°ÒPÇÖÚ1\Z\r¡–RB/®ˆˆ”¾ úğ‚öl¥ßm¥0kJyù®”SE*—]\'§èOğ#sÈ‚îşÍP’}Ksô:¿E×Y¾¼y˜İ•?ÿ^æi–™Çîp5E‹e¾ü>ËfËeŠ’§ü1ß§Ëôù¹¼éãş„uI_\nendstream\nendobj\n16 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤)¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0t±\nendstream\nendobj\n12 0 obj<</Type/Page/Contents 16 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf2 15 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n17 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1634/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛrâF}ç+æ)•TÁxî\Z=Ê6‹)Ìe±°«öµåØ±\rWÊß—oÈä3ò–„Ä4\n\nU[`­tº§§ÏéîQã÷Å>ˆ¾¶¹½ø›)Š}?g{÷·ŠQàn­°8¯±Çİ~ÑíÏ~<=t½h|m4N{\'1\nc\Zÿ¿x\'¹ÄJü‡ÙÑÛ·ŞÑ­w—aãâKê.MnBásƒ†¥DáçiWXæih–É³³ÆÏ½á “»o“Î/(ü\rÀâ§b°vh±çû˜éRsœ©­]ÓœÊE*·¡¥6åXËÄ†—^‰m\\¾O×ëÓ‹Ñ†ÊÊíp‚Õæº}ßÁ†ÆšìÛøXÌë]‰äi¦¬×•ÀÎ—Û*LîïüÇbVëJ$gØ?È/ó2^¡vßîÀéa?Y’”˜ÊŒ;‹ùz1ßÌfÑò¸µø‚*_\n<3c ?üı²ÜÌ=;ÁE~˜À·Ñús-Q°Y}nìøKvÌñà=FO‚³ZOçO\'bÃËc“Õ  Ë¾y|Á­pjs^C(Eö „ò2}µş°H%ibrK4ş!ZVP&^¾LøX$«€Ò9ñX¸Å8µn°pqám©lÂ·\'c¨›Ñ¬|=Ò\'˜ë¨\n‚n3ŞÄ•Â¢q°ĞÆ¡éÛA…í³Ô{yÆÑëã‹•”\'xêÉ}Ôştù¶Y•eJ3rXcyÁÊİgdg²Ş¶”@•;-MØ€¢qûêf0XºGŸ©ÎËD\'úÂæ4ƒ|r:£vt!‹³p“,(êÊ¹Ø…nï<TÅ·½Œ‰ªç°AJI--ğÔÃÒA1$Ù\n’	?ì\\ä£­ÙqšÕ:íé i/™=H–WD)EZ¨\nHM_·ÎÊ«Ú»’¢I+Ô(Jw°*}\0>p‰*æ£şğ²{Ûö4òá¸œµ–œ×0Vì–¢NğÚÃº<òùRLØîêLÚƒğ¦m©(ü£@9ÛC·ò(å +Lª=PB©\Z¡GÉîbdå1ÙqÑ´0éƒiÊ4w(.9Mèûëœ‡–IÌQE$üĞsfK‘ªä4A›„ÔEÎ‚¯\'ÈY(ÔçnD‰…—çrF”lÜ«O_Ş¡5Eıi´Z/v]QåÛ—gGÁHÿ*ËJÎË®ÒÙ¬\ZbC­˜ÔúÒÃy©Óœï£;ØË`\0:„ˆk\rSD/‡m’Øy/@íêIJÏhÿ˜²éò1,_Œ	K´ÚşQOi\n\Z}wÃo¨İé´Ç¨Ó¿¼A?¡«áYë‘°;G,c_Xzo*„vÉdâ6=^S.™¨0Ú‹\'Ñs¿R;ØNû&¸\ríSª®€+=ciw¯nP?÷&w¶¤a.½`íÛ®äßŒÛhÔß\rÁí`œô1 \05U»‚©]µ£5‘*şáBªŠJoÂşoJo\Z1”ş¼ib§ô…ĞÔ¦ô&jJïº•“cJO´¿Sz?SzÈ[*&%£fwŠ­],	åGùbLØ»Q0F×°\"‹Ü¸¶éq¶xûà¨®>½€*8ÕiŸî××§,Ä»èz×¤\\&´¡\\¶‚T±O/À[å°*=MĞ&cuÑÓ„=)³ÕˆBÍ1Ù‰âÚœ(&ìmô=²¼±‰sÍáT0eİ¥ÄÏF[0~û!iÕ3 ‚\rE8aÌ#Ô§5àwg@ÔÖºUÍi^ØˆR5§MĞ¦–uå´sòA?^¡µ\"…‹Rš6Tğ“Cÿ7t¿h-W‹y4ÿ€ï×Ç—(~­„‚óNR3RùÂmYTaåŞš¨ÎŸ£9ºZÌfı©íÅLEÁ5mzkKıŠı‰Î­íPÅYÅmZA+ffaù‰Ä„Lsµ9ÿ(LâËaT¡Q·w‹z“±¥È^Iäoi“1)1¾g®E‹^èôU0ì‚Î¬_ß[Îãs•Ûw@é)¼\'µ•~¸vZnÀÆ”dŞÑï÷ˆ\0Á§£{\"”§GD_ŒhË¢o/ãóXæğ*„òìL=erÚ´>4Âw¸‡Ñåtş†ÿY¢`ù=z]£Íü	=¼.×«Ç—ésúç_«u´\\Æ¯¬^áj„F‹Õzõcº|›®V\nŞÖ¯w½¿§.á_¼›Q\nendstream\nendobj\n18 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤+¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0²\nendstream\nendobj\n13 0 obj<</Type/Page/Contents 18 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf3 17 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n19 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1504/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœ­YÍRã8¾ç)tÜ­\"B¿–|t 0„dC€Ú½e3d\'?³¶Cmñ ûû{Ø–8rÅòŒ9@pì¯[İ_ê–;u(&ğƒHåÏæ\"±2Y@qÈáÃËbïşnõ*\nÜ­,Àk¬Ø±ÛÏo‹ï_^	º\\u~ëtN{\'…1Iø#ŞI.q ¾Q˜½}ãİx×›tÎ¯\nwi~š¼v(aXJ4ù8í\nË¼m€eşì¢óËíğ~2Œşx¼şMş0ó”ëOöÂ3]k³`c×62BQPÚ€€ĞZ‚r¬enCWŒŞ|še§£-;”ÕÛáv¸ïZ4C\Zk²oã}µlw%’L«X¾+Ì×Û@&÷3ÿ¾Z´ºÉøEL]šjÿäpÚ‘\n‡ù’¤ÄTnkgµÌVËõb\'Ç­™AıR¸à[3úó¿oÉzùõ82d‚‹õaßÅÙG\'(Z§k7¾‡ã’s<šô<8i6]~9^›í~\0%ÈÊ\n_¿¼áîdêr^ÃP‹¬ „ò2=Íâ¿B‰ÀÂCš˜Üš\rÿ\'\r”‰×g€‰‹|À!]V!ŠuA»ŒSg‚…‡ˆµ)e¾ÿ8v€úiİnDŸAô‰ÊõTA7Œ·±R84Îº‘Ã8œ…nPáQö[j‰Õ¶xÆñìåÍ‰Jê‰*¹:˜&ßÖió‚‚y”Ã\Zë7¬Ò}Fv† !=RáÓ¦”eiÃò¢;\"Ô0›@\'ƒ®û÷ıqtwƒúã‡›‹Oıñãı5ŠM0Y/	R°Í>o,S=Y/ÂRŠB+ gRt°~áß	Œoü7¬w·Œ?¥;Ø»øs¼Lßã$vÆîm‰rê’Ò¸»ÇQt¢\0(lù*ğF§!ß¡†w˜Òà8Ä¨@ç„n<Ã^0¦bŒˆ€H\n©”*,=ôW’BŞ+ğÃës’ÿh\Zü‡¶%Lµğ¤óiËËØ°½øcú6æ Á4N³dåLP÷]`lƒ‹20ª!¨À.Ğ†BPmO¼³ßéúÎ«Ì¢²6’OÃ«ş]F÷·“ãà6`]ŠB€¨z¡ŸÊ\Z…\03ìÎîë«‚VŒ__MxøªaÜczô–£<qøq`nÙRyÃ„d…ÃIŒ0ÀBùóÂF½ì»Ê9ôé–ÍmÁhp4ˆú“ñ†Èˆ¶+\"f\0i ×¶:OdšRvë˜észtt¥*ÙğO—¥*¹vƒÓZ¤‹­jlr¤ 	‚Ü–\0UxFOv\"MèÃ÷Vºa™Æ²¶ä¯\r;Âøw/¢ñ%Šzcè~Ì4ÒZs`›\nÌw¡fT3Ûš66¼Õ8ãä·ò 8`©ÀsÕR7\\A=ƒ¶8èKª1m vP„Õ\"óKAÌaî(úıîæªz°1¢ëAïS;Ä7|Ö™¢şØ›å;tc‹x™æSÇYcc>Úv|d.ínÚ“ÙğÊÚ”6èiM}SÈBLı\'Ê­™ú>s	ÌB¿3ñ¥š1‘QO&zQ”Ë°a³8‹]ÑaĞ5  {Õ/	è8k,ˆ6<“m1ĞFm‘Ş©ƒüg(µFwpªsA$¦\"s\Z\'èâ-™¥ÙÌ),\rYH¸ßR„×Ñr¹¶?[Î§_Ûâ¡lñıT8$¤öĞqj^‚´ÄD·E&ú¦¯™†ÖÔ+¸RÅÎL\Z*hÙë¥p{şY¾2wˆœ>{»´êˆ.Ş;,èrzr›ŠùƒÊÍsZ+qhÀ-ıô=dë-ƒÁ-Œ¶”ĞÑÒ#¢ÏG´{òİ¦õ–\\Ã¬”òb†R[ë½è9ºFEçzÓå7ôú_‚¢äs<ËĞzù=Ï’,}y›¾ÿş“fq’˜DÎàjŒF«4K¿C~§i\Z£è[6‹—_ã4Ï‹‡—ğ?:WŸ°\nendstream\nendobj\n20 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤™(¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0Æ³\nendstream\nendobj\n14 0 obj<</Type/Page/Contents 20 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf4 19 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n7 0 obj[/PDF/Text/ImageB]\nendobj\n5 0 obj <</Type/XObject/Filter/FlateDecode/Length 80/Height 30/BitsPerComponent 1/Subtype/Image/Name/IMmpdf1/ImageMask true/Width 2232/Decode[1 0]>>stream\nxœíÖ1\00ÃÀĞ/ƒÂ-İdßé)hÑŒôëhCg‘ê]mè,R=zBÑY¤zô „¢³HõèAEg‘êÑƒŠÎ\"Õ£!Eª÷\0hœFq\nendstream\nendobj\n6 0 obj <</Type/XObject/Filter/FlateDecode/Length 700/Height 78/BitsPerComponent 1/Subtype/Image/Name/IMmpdf0/ImageMask true/Width 360>>stream\nxœÍ×;Õ0`G)B…[\n„Y‚\r·2K ¼vÇvØ@¨(Ù‚—`D‘\"›ÿ?vîc\"d‰HÉÍã‹slŸÑÔzûşËG¶oÿş:§CØÃn6Ã°†—ax†§aXÃ~vÃ°†äèA| G?ÃrÔş	ç«ä±ê’£_jıqÅ”bˆ\n­±^ -\nqÆm\\Ö÷hÇ×\Z]ôuSÄ~•òÀ\0V‚ÙM”]Çy\"†cCÀ¸®ÛçMÇXõÄ.â YÎ/ìyéxÕ‚ÍÚ[ÆuÍ{Ì	{;®cSq0·x#:Fçùß%S¸FÇ9ã#êe\'Ş¦Öû˜\r]ğJìd\'f8gŒqæk´´“`#8.·˜×u½ÂÑb8qIŒ~uìs0ˆõójqŠ¨ˆ¹2^cp®0:°púD¿cçg–úUrã…Œ`•Ù\'%lˆè©òÀ§©Mh”Iñéù˜£ğÛ†.dŸIÁPV†@iÂ|#H\r|§ZŠ*—%7$+Ë«ZæJ—Èìñ\rûÂ†€uÃ¾a€Ó,Ø5ÌÑ\'~¹Übt/ø¬³\"æ8÷ÄE&&Å%®mR<uçâeAµš²Íib®\\ptIúÃ4vqI-7d+sš‹ôØN\r\'#§Xç[Œ&Ü\rG`Ç”üY°ábÀ¤Ì;æ\\iœ\"ùÎÉ_ë\'Ä¼pY™+ñ’™K‚]Ãh1-}Á®vi8¸ĞqŒ–#Úêw,$ qÔ÷R ‹Óê½ƒö‘V7ï¥€cwÁ…ÏT¿U$Á±—/ö8YÓğ´ñY‘Z×1ŸIadOw¼l\\?áÄã³mRVÌ91zœ­ëogY’o˜ŠÄõ¿‚ïoÜ’m³ş7Oşû÷ö®¯à×GğÇaøĞÿVàŸ‘\nendstream\nendobj\n8 0 obj<</Type/Font/BaseFont/Helvetica-Bold/Subtype/Type1/Name/Fmpdf1/Encoding/WinAnsiEncoding>>\nendobj\n9 0 obj<</Type/Font/BaseFont/Helvetica/Subtype/Type1/Name/Fmpdf0/Encoding/WinAnsiEncoding>>\nendobj\n21 0 obj<</Type/Catalog/AcroForm<</SigFlags 3/Fields[2 0 R]>>/Pages 11 0 R>>\nendobj\n22 0 obj<</CreationDate(D:20080923065445+02\'00\')/Producer(iText 1.4.8 \\(by lowagie.com\\))/ModDate(D:20080923065445+02\'00\')>>\nendobj\nxref\n0 23\n0000000000 65535 f \n0000000117 00000 n \n0000000015 00000 n \n0000010019 00000 n \n0000008020 00000 n \n0000016736 00000 n \n0000016989 00000 n \n0000016703 00000 n \n0000017850 00000 n \n0000017954 00000 n \n0000009802 00000 n \n0000009947 00000 n \n0000012262 00000 n \n0000014463 00000 n \n0000016534 00000 n \n0000010200 00000 n \n0000012117 00000 n \n0000012431 00000 n \n0000014318 00000 n \n0000014632 00000 n \n0000016389 00000 n \n0000018053 00000 n \n0000018137 00000 n \ntrailer\n<</Root 21 0 R/Size 23/Info 22 0 R>>\nstartxref\n18269\n%%EOF\n','2009-06-12 23:03:28','e96bcbd2-676d-102c-ace2-9cc3fca64c89','2009-06-12 23:03:28','e96bcbd2-676d-102c-ace2-9cc3fca64c89',1,0),('18119e4f-07fe-4c15-bdce-927dfa69eed6','certificat','Test zum LÃ¶schen','0b0b7658-2ddb-11de-86ae-00301bb60f17','application/octet-stream','grid.csv',0,'','2009-06-19 16:36:28','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-19 16:36:28','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0),('0c05017b-0f9f-4bc3-baeb-c1f8eb734120','certificat','Test','0b0b7658-2ddb-11de-86ae-00301bb60f17','application/pdf','20080908.pdf',18806,'%PDF-1.4\n%âãÏÓ\n2 0 obj<</P 3 0 R/Type/Annot/F 132/T(Signature1)/V 1 0 R/Rect[0 0 0 0]/Subtype/Widget/FT/Sig>>\nendobj\n1 0 obj<</Filter/Adobe.PPKMS/Type/Sig/Contents <30820ebb06092a864886f70d010702a0820eac30820ea8020101310b300906052b0e03021a0500302306092a864886f70d010701a0160414a3939baa417b8f344e2be299336cd946bf5fcdeda0820d1d308204b33082039ba003020102020302f2f9300d06092a864886f70d010105050030819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033301e170d3037303232303135343535385a170d3132303232303135343535385a305b310b3009060355040613024154310e300c060355040a0c054241574147310b3009060355040b0c0249543118301606035504030c0f424157414720502e532e4b2e204147311530130603550405130c33343038393431313432383930819f300d06092a864886f70d010101050003818d00308189028181009f18e18acafeced47e87948327150690350f940b496d3d832b80568e1578be143898e395bd10d5863bab7400f0b3f7c41052e298169f96ae84f3931805bae5fb150b191a80cee84538a05cce009c4457f3b059e34f5d9668a7317ced1a8db2ea88079ce7685599fd678ebe8a41154bdb18ec239098f4ee063944d4142ccb585b0203010001a38201bd308201b930130603551d23040c300a80084191691cbfadd898305506082b0601050507010104493047304506082b060105050730028639687474703a2f2f7777772e612d74727573742e61742f63657274732f612d7369676e2d636f72706f726174652d6c696768742d30332e63727430580603551d200451304f304d06072a2800110107013042304006082b060105050702011634687474703a2f2f7777772e612d74727573742e61742f646f63732f63702f612d7369676e2d636f72706f726174652d6c6967687430819e0603551d1f048196308193308190a0818da0818a8681876c6461703a2f2f6c6461702e612d74727573742e61742f6f753d612d7369676e2d636f72706f726174652d6c696768742d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f7269747930110603551d0e040a0408487cf8373c3f8bb6300e0603551d0f0101ff0404030204b030220603551d11041b3019811769742d736963686572686569744062617761672e636f6d30090603551d1304023000300d06092a864886f70d01010505000382010100776e67497156b2bcb608d9de5dcf85efbaa18859128f1934e0ee4ecab2f94ccee454e8610e6be5b6c37eb49e4c8026363d107ce5b3e62bea1b4927cd9ed152d430a77976215fabbe75b27a8f452c3f1e8943bc28339dfc241c2a3fc66872919067c1420a7e010933947e324117d118bfa2cb732d845c80b3aecd37ee481323fe9166bdad16d619298ee502c5533254e8bdb1f90ab97c9a4c343deb368f3def13c4ba4d66fc4861f5af516cd6d94e1b5ebddf5e8eceb331a7fb2c9bfb0d210c0d8639f81ffd9d2cbf50e50ba200828415e8dabad043cbfd95355915ab7f3542ce80c6d90b5d1b72d1d03c9370e7c9f90909b76f870ebdf248b918da2e705259d73082048f30820377a003020102020301aaed300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035313131333233303030305a170d3135313131333233303030305a30819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100b7e7da22b5b1e490481d12b44f78176471844ad832ddc5f7279fe488ba6484633595b1c4537213ca4b36fb7f56ba1073db079599b0625a5035fd29f4eab0934ca3eedb0122a4de0b0b571359a98dadd86fb2f07bd111861e8c3388ce302470142bb7e9efb8152519854942312ec809f5f1fdaf1b463754dbd91462a73caf00572714324d14c36d516cadef5f9b5b6cf810fe471990de1cce2e551dea4cbdfea1030c0ba098fe28bf73c66f23c8f2bcf2a0812808cc342a592ac121c9a9f0d428d86d5987b38e50e56e8506da83712e88ab8982e93b543c6b424eea07eff24bba1129d3be68869f3bbf77d297378b2630699140cff54f13bfb9767ae0f4b755150203010001a381e33081e0300f0603551d130101ff040530030101ff30110603551d0e040a04084191691cbfadd89830130603551d23040c300a8008446a95675579114f300e0603551d0f0101ff0404030201063081940603551d1f04818c308189308186a08183a08180867e6c6461703a2f2f6c6461702e612d74727573742e61742f6f753d412d54727573742d6e5175616c2d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f72697479300d06092a864886f70d010105050003820101000d3448690b4fc283d2ebf4e9c8184ec38c001e4fd13388242d3efa5113d8dac5078a1c6acbef2a10494df9fe6523ad66c823f72054c295f95c1d5475402a0c834648e7f11c41323c0e0779890917b958e1df00a3f3b29ad93bbb7752be8bd549e0147fa61fea98f291ecbc7f3ed013c3fbd6a38936adbb79a354308b167b08fb7e045f7109a002e2e2f46c1d0cf4cde2f56d9e3e6a97e96201de33244fd1500b42a284f3dfc3fd7b29d74d8b8ac530c1224f22af253b53a839c80305964eab0bd46ee8c4c32d9e761a296310ea04223a2473098ede7dc022ff7630517e942d6e73c6a6a936cd6fa846d21ac40497578597aec70591d800f7681c6527645d0a69308203cf308202b7a0030201020203016c1e300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035303831373232303030305a170d3135303831373232303030305a30818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100ad3d616e03f3903bc0410b8480cdec2aa39d6bbb6ec24284f75114e1a0a82d51a351f2de23f03444ff94ebcc05239540b90778a525f60abd4586e8d9bdc0048e854461ef7fa7c9fac125cc852c633f0560734905e0607895104bdcf91159ce717f409b8aaa24df0b42e2db56bc4ad2a50c9bb7433edd83d3261002cfea23c4494ee5d3e9b488ab0cae6292d46587d96ad7f4859fe4332225a5e5c833bac3c741dc5fc66acc000e6d32a8b687360062779b1e1f34cb903c78887405eb79f5937165ca9dc76b182d3d5c4ee7d5f83f317d8f87ec0a222f23e9febb7dc9e0f4eceb7cc4b0c32d62b59a71d6b16ae8ecd9edd572ecbe5701ce05559fded1608810b30203010001a3363034300f0603551d130101ff040530030101ff30110603551d0e040a0408446a95675579114f300e0603551d0f0101ff040403020106300d06092a864886f70d0101050500038201010055d454d159485cb39385aabf632fe480ce34a334623ef6d8ee67883104036f0bd407fb4e750fd32ed3c017c7c628ec060d11240e0ea55dbf8cb2139671dcd4ce0e0d0a68326cb9413119abb1077b4d98d35cb0d1f0a742a0b5c48eaffef13ff4ef4f460076eb02fbf99dd24096c7883ab89f1179f38065a8bd1fd37881a0514c37b4a65d2570d166c968f92e111468f1549808ac26920fde899ed4fab3792bd2a379d4ec8bac875368424c5151741e1b272ee3f51f29744dedaff7e1929981e8be3ac71750f6b7c6fc9bb08a6bd68803918f06773a8502dd98d543783fc63015ac9b6bcb57b789518b3ae8c9840cdbb150200a1a4aba6a1abdec1bc8c5849acd3182014e3082014a0201013081a730819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033020302f2f9300906052b0e03021a0500300d06092a864886f70d01010105000481806452bd278af0c176438b49ad48756ef60aa7d0badffe78407ee18e1b9dbab2036362b5314ac3dfe6b2a29164063ae49d6346fd126c4f8784c363231d9987565076313d509b3321bf733910119405ebb8903601f87e721a425ebc2075284692f6706b7aecadf8f6a2f4f0c4cb9d17aaf87ce597e78e5b7aef4807be6de1d9fa3800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000>/M(D:20080923065445+02\'00\')/Name(BAWAG P.S.K. AG)/SubFilter/adbe.pkcs7.sha1/ByteRange [0 164 7844 10962 ]                                                             >>\nendobj\n4 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1530/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛRÛH}÷Wèq·Êæªí›IHŠbI8qÕ¾	<­m™Õ…İâC÷òyØdK¶Ç\ZK„|NO_NwËƒ¿aø\npç¥¾È\"$Í+\r	Šür·Ú¹Ô}CîV!âà’ôĞí\'ç—«ÇÙï×ƒ/ƒÁqë8FÜ Pª~Æ:Á\nÕŞ$¢o¯­#µu§“ÁÉk.©n\n&óÁ	L›B™Y×†HTï]\r~¹øüiòyüõæ¯\r&˜y—;›8ø¢QÕKÇhXó¶é¢c$$7àÒËÁ	CJTÒ^1§Ë¸(Fµxíça…{<Ü÷,\nE‘‡B\nïr<­Ó·=‰`6Ó~î$ù~¥­ÀNäŸÖ«7=‰`E{,ØÔ¥9¡òó¨!QT%€ˆˆ¦vÖi±NËÕJg‡ÙÌ…°ÿ(Œ³†¦…>ış•éıadˆã/¨6ğïºx.tŒËü¹tã{.è!ÃÇKƒ^9\'/âtvÄ7¬ß7M?€¤›\n/ïĞh»ŒW½È\\(!ƒÓóBÿã‚9âÒDE]hmø©Î^ L¬?”GˆW§€R›*ÀXÒ(ğˆ2â0÷q:¼öõÚê§A¤iD›AÔ‘ÊõTNêŒoc¥phœ\',L#û~FnPîQöMjsdS<×:¹{p¢âşÄK¥ØE½Œ³E™;aÃşöDÃÈ„ÜfœÁ=p(” öÈ^…arÙ}_fwùİCšEÇËÙÚ­ 4ôÈaeµ»C2ÿá²<DRöƒ†Ø6è,vûØ#Ë˜İeN;±‡Š pòKgE¼t\0/}Ã‡¼z«‹ìû½Câ\0XôË2gÜöõğ£«ÅzJäÀ¾gû†?ä*:€ıJøÁ0»Ebˆ±Ó¿²¿¶…,¸m€u!k*÷«dn\'äj¼(J½\\ºCĞÔş&ÔÊdù,Iq9Ï3ëìÉEà•ÉLâÚÕm‚ã™ì×<£zêZşÚæÉëºkƒVSƒ;1Bx‹‡¹Ä*„…Ö!ùÆŒ·¸û·ãõK§÷¦OwÈ(¢ümútv(\Zº?tqŸUm[BTZu­FG,ã{ıÆbŒÔ…ÕærúĞé•¨MäÛ6ò°$ü_‘o“1$¨+{¥OÛØF¾\r;ŒèÈ×U0ıÕ6ô$´|†æ“.;Oğ\\FQ»»™¸<\"{­uT›êc™ÂrâökX˜Ö·ºôê8·Y ™éëüMœÛ°C)<ÃL0L!ı>ÚÄ9bvB­+<¸şq·˜g¦ÎefX‡ßn@\\à6ß·-Îê5µmÈü<W_- f½êŸ7\'Wá†ğf=×™=g\nG.‚Û$Máàa0]W˜é•Íÿ¸z¨Àø%óbé\\fLØ¤V†ÛãÿÌt–?fÚPƒ8ÕÙBWög	Ì)zÕÎ°¡Q+üçï¼ck¦·~Ğİª—·Y>_İ\\Œ\'Ó©wT9H§‡˜(»¯´©ÎOÇŸ^­ÏÉÌÈ£vO40nÙ|IÊhè|(Ñœ.Üù¨2ê%‡Ö»C®ñtìx¦M0A¢2”¼ió-Ø+tƒ.ĞĞ\rì±ùGÍ×±WWrâH•¸ıõBˆ:\0}ıî’8-Æ>í•42ÔÁàÀ9¯\n¼¿Ò,Ò|xš¸ZÜ¶\0Â:E`@Âı<›ub3yØB(EaæÁÚCÄ.ì±§-¦mO\Zsq}¡Â]¯óÛØm±_$MŠŠ]h\"%é‰ÚØ»ğ&ˆ!Ì•P˜Î¥Gı)û9BôC¢ouv›•iO‚´42¬zĞ¼?\"]ÖZ!Y-aÕ£¨o¡È\\ÔTÖ„¥ Dº•ğ–j›wï1¿¼¼ÀCh¯¾aJu…ÕÉùî?`šŸÊ°úz7ßèm`å18ÓE0ÿ‘ãìV\'LF³`šdÌñÜşù/Ì±v‚Jàª®`vÎ¡tâ<‡½cQ€ÜC+_.í›öğ`‹e\nendstream\nendobj\n10 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤*¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0K°\nendstream\nendobj\n11 0 obj<</Count 4/Type/Pages/Kids[3 0 R 12 0 R 13 0 R 14 0 R]>>\nendobj\n3 0 obj<</Type/Page/Contents 10 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf1 4 0 R>>>>/Annots[2 0 R]/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n15 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1664/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµZÍRã8¾ç)tÜ­\"B¿–|4a¦26	Pµ·˜Ÿ’YÇÌNñ ûû{Ø–ÉD±¼qr	1ò×­îşúGvïÏÅ>ˆ8_«‹<ÆÊ|³ˆâ˜Ã7/µõ}÷Vë‹\rğ\Z+¶iùá—ÑË÷Û;‚N½ßz½íÚ	‚…AaLcÿí$—8Òş£0Û¸|¥]iw4í~*Õ¥Å\"4½ëQÂ°”hú¶]’yiÚËâŞ—Ş/Ãó³éyr9ùıòôW4ıÀÌ]l0õÈ‹cÌt£8Î¢•\\[\\¼MEQ%BeÊ±–…U^12gy¾}3Ú’CY³NpôA\rİ‹Æq CcMê2~,æİîDò2Ò)\"t\'àùfB…Éºç,^:İ‰äÇâ‹^šêpçğ\0îH…ãbKRb*×ÜYÌóÅüõå%Í6K3¢æ­pÁ×b,ôë²×ùıfdğ-øaMó·<ÍPòº|{õã(.Ù&Å“gƒ^g™Ïæ·[lÃ›m³®@AV1üõæ÷§3Ÿò\Z*@#²ÊMÈ`ôeşô¤J©‰ÉÑløë4k‘™x³˜ˆ±(v1¤+¢X2pŸqO–‹€$.ÔŠÊ6üàrì\rËAt]ˆ!é-Ì\rÌ\n‚®\"ŞÆ6™Â“ãa¡ùh‡ƒØ*h¿m!±Z“gœ>Ş<xQIsà¦JÖQG³ìéuÙyQy”Ã›V¥>#ï‚Lşõ8BÂ¨é-mØ£ômöğÄD£YºÌ³…GU°‰fÍ%Y‘Æ–1:>$Åüë‡å²^\n¶êlxá‰\n©S6§r)E™aĞÆû>k9ñ=M…zšMİœ¶+/RúûùüÓ`ŒN’³át38ö„8+Îjèíä5\nf2ªÎ<~ÕUA\'\"TWÃ‘\0]5ôŠL×@‡Ã—#D”À\"£¢78¼AEgô¶a÷Eo[†EoOIlMo^²èmƒÒ½C½ÈÀ‹Q°iÌ-/^ ˆÇ»·`!©#Á£YÈ„®ÁvÀB¥X\rXXØ×°öÌU\',¤ZXşÛ’›á,t`ÇéÍÃÜ4¿ó#Óˆ1É}DŒ±hpÖDtÄVDôq¦%xŞĞwDÄ`G.mAD%m~+\"M‰Š¢XEègù}‘åè>]d÷hù×âÛâvÖM`Âô´ˆŞ78°_vkb‚µ5Lä%¿‰í€Œ§Q]Sp1äl4J“éø%S¨Ä{±š›G¼ˆ¯ÂÜ–d,m\r‡>’\Zµ!©\ruR‘t|\0IyTÓÔíÔKmÔîÊ¥Õ²3–JeåÁÙüiñ2ËÍ9Ìól~‹vóª)t¤øãœS,šUg$Â”Š\Zjç>`ÈpÍ¶®l\"ô;nRòå=¸»é½^ƒr¦önft˜Œ§SàSá1©u{œL&Éñàl:N¾ÌÁørœ ãä\n~Oèòlxv~}ÖM*çÖ}`G¾»§Nß‘auú¾™»mƒaÃSŸ›Û¦.îò²«ÔêE\"°n®>•™5iN.’NæxSTÜŸ±Zò*cÌÓ‰·N&lƒ¼\n#Dá¸b÷gÁ¶ô¦ÖÈ¶Õ´vô¦ûä½eG­‰\rO¥o‚mËoõ ºâ·ãÆ-ü†fœ·(ÃÄša?ÆW_&°é±pÛƒ:½3~Û »ëZñ;H×¶ü&|3¿aÏ´›ƒºXÓ›‡ÓÛF5÷Ao[†ÅnÏ‘O[v;véjî°A»;U¹×…±Äqø(­­“ärz>Jv¬Ú0 GZ¸À~NÃøĞTÃ0ÂÀå&\"b¨ì¬Qa¢ ‚÷}m];Â¨h„±Q÷E[†EßQcËn×±KG|Q{ÓM·jÓ ÷VÖŒì!š\\ÃDTA°Æê€fhÏ8°~¬Z½ibVˆ‚¶5¹}êª£Ë×Y éµ\Z\'W\'îæ‚bá rõ»ètˆ’Œú‚.”ãA°ÒPÇÖÚ1\Z\r¡–RB/®ˆˆ”¾ úğ‚öl¥ßm¥0kJyù®”SE*—]\'§èOğ#sÈ‚îşÍP’}Ksô:¿E×Y¾¼y˜İ•?ÿ^æi–™Çîp5E‹e¾ü>ËfËeŠ’§ü1ß§Ëôù¹¼éãş„uI_\nendstream\nendobj\n16 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤)¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0t±\nendstream\nendobj\n12 0 obj<</Type/Page/Contents 16 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf2 15 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n17 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1634/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛrâF}ç+æ)•TÁxî\Z=Ê6‹)Ìe±°«öµåØ±\rWÊß—oÈä3ò–„Ä4\n\nU[`­tº§§ÏéîQã÷Å>ˆ¾¶¹½ø›)Š}?g{÷·ŠQàn­°8¯±Çİ~ÑíÏ~<=t½h|m4N{\'1\nc\Zÿ¿x\'¹ÄJü‡ÙÑÛ·ŞÑ­w—aãâKê.MnBásƒ†¥DáçiWXæih–É³³ÆÏ½á “»o“Î/(ü\rÀâ§b°vh±çû˜éRsœ©­]ÓœÊE*·¡¥6åXËÄ†—^‰m\\¾O×ëÓ‹Ñ†ÊÊíp‚Õæº}ßÁ†ÆšìÛøXÌë]‰äi¦¬×•ÀÎ—Û*LîïüÇbVëJ$gØ?È/ó2^¡vßîÀéa?Y’”˜ÊŒ;‹ùz1ßÌfÑò¸µø‚*_\n<3c ?üı²ÜÌ=;ÁE~˜À·Ñús-Q°Y}nìøKvÌñà=FO‚³ZOçO\'bÃËc“Õ  Ë¾y|Á­pjs^C(Eö „ò2}µş°H%ibrK4ş!ZVP&^¾LøX$«€Ò9ñX¸Å8µn°pqám©lÂ·\'c¨›Ñ¬|=Ò\'˜ë¨\n‚n3ŞÄ•Â¢q°ĞÆ¡éÛA…í³Ô{yÆÑëã‹•”\'xêÉ}Ôştù¶Y•eJ3rXcyÁÊİgdg²Ş¶”@•;-MØ€¢qûêf0XºGŸ©ÎËD\'úÂæ4ƒ|r:£vt!‹³p“,(êÊ¹Ø…nï<TÅ·½Œ‰ªç°AJI--ğÔÃÒA1$Ù\n’	?ì\\ä£­ÙqšÕ:íé i/™=H–WD)EZ¨\nHM_·ÎÊ«Ú»’¢I+Ô(Jw°*}\0>p‰*æ£şğ²{Ûö4òá¸œµ–œ×0Vì–¢NğÚÃº<òùRLØîêLÚƒğ¦m©(ü£@9ÛC·ò(å +Lª=PB©\Z¡GÉîbdå1ÙqÑ´0éƒiÊ4w(.9Mèûëœ‡–IÌQE$üĞsfK‘ªä4A›„ÔEÎ‚¯\'ÈY(ÔçnD‰…—çrF”lÜ«O_Ş¡5Eıi´Z/v]QåÛ—gGÁHÿ*ËJÎË®ÒÙ¬\ZbC­˜ÔúÒÃy©Óœï£;ØË`\0:„ˆk\rSD/‡m’Øy/@íêIJÏhÿ˜²éò1,_Œ	K´ÚşQOi\n\Z}wÃo¨İé´Ç¨Ó¿¼A?¡«áYë‘°;G,c_Xzo*„vÉdâ6=^S.™¨0Ú‹\'Ñs¿R;ØNû&¸\ríSª®€+=ciw¯nP?÷&w¶¤a.½`íÛ®äßŒÛhÔß\rÁí`œô1 \05U»‚©]µ£5‘*şáBªŠJoÂşoJo\Z1”ş¼ib§ô…ĞÔ¦ô&jJïº•“cJO´¿Sz?SzÈ[*&%£fwŠ­],	åGùbLØ»Q0F×°\"‹Ü¸¶éq¶xûà¨®>½€*8ÕiŸî××§,Ä»èz×¤\\&´¡\\¶‚T±O/À[å°*=MĞ&cuÑÓ„=)³ÕˆBÍ1Ù‰âÚœ(&ìmô=²¼±‰sÍáT0eİ¥ÄÏF[0~û!iÕ3 ‚\rE8aÌ#Ô§5àwg@ÔÖºUÍi^ØˆR5§MĞ¦–uå´sòA?^¡µ\"…‹Rš6Tğ“Cÿ7t¿h-W‹y4ÿ€ï×Ç—(~­„‚óNR3RùÂmYTaåŞš¨ÎŸ£9ºZÌfı©íÅLEÁ5mzkKıŠı‰Î­íPÅYÅmZA+ffaù‰Ä„Lsµ9ÿ(LâËaT¡Q·w‹z“±¥È^Iäoi“1)1¾g®E‹^èôU0ì‚Î¬_ß[Îãs•Ûw@é)¼\'µ•~¸vZnÀÆ”dŞÑï÷ˆ\0Á§£{\"”§GD_ŒhË¢o/ãóXæğ*„òìL=erÚ´>4Âw¸‡Ñåtş†ÿY¢`ù=z]£Íü	=¼.×«Ç—ésúç_«u´\\Æ¯¬^áj„F‹Õzõcº|›®V\nŞÖ¯w½¿§.á_¼›Q\nendstream\nendobj\n18 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤+¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0²\nendstream\nendobj\n13 0 obj<</Type/Page/Contents 18 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf3 17 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n19 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1504/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœ­YÍRã8¾ç)tÜ­\"B¿–|t 0„dC€Ú½e3d\'?³¶Cmñ ûû{Ø–8rÅòŒ9@pì¯[İ_ê–;u(&ğƒHåÏæ\"±2Y@qÈáÃËbïşnõ*\nÜ­,Àk¬Ø±ÛÏo‹ï_^	º\\u~ëtN{\'…1Iø#ŞI.q ¾Q˜½}ãİx×›tÎ¯\nwi~š¼v(aXJ4ù8í\nË¼m€eşì¢óËíğ~2Œşx¼şMş0ó”ëOöÂ3]k³`c×62BQPÚ€€ĞZ‚r¬enCWŒŞ|še§£-;”ÕÛáv¸ïZ4C\Zk²oã}µlw%’L«X¾+Ì×Û@&÷3ÿ¾Z´ºÉøEL]šjÿäpÚ‘\n‡ù’¤ÄTnkgµÌVËõb\'Ç­™AıR¸à[3úó¿oÉzùõ82d‚‹õaßÅÙG\'(Z§k7¾‡ã’s<šô<8i6]~9^›í~\0%ÈÊ\n_¿¼áîdêr^ÃP‹¬ „ò2=Íâ¿B‰ÀÂCš˜Üš\rÿ\'\r”‰×g€‰‹|À!]V!ŠuA»ŒSg‚…‡ˆµ)e¾ÿ8v€úiİnDŸAô‰ÊõTA7Œ·±R84Îº‘Ã8œ…nPáQö[j‰Õ¶xÆñìåÍ‰Jê‰*¹:˜&ßÖió‚‚y”Ã\Zë7¬Ò}Fv† !=RáÓ¦”eiÃò¢;\"Ô0›@\'ƒ®û÷ıqtwƒúã‡›‹Oıñãı5ŠM0Y/	R°Í>o,S=Y/ÂRŠB+ gRt°~áß	Œoü7¬w·Œ?¥;Ø»øs¼Lßã$vÆîm‰rê’Ò¸»ÇQt¢\0(lù*ğF§!ß¡†w˜Òà8Ä¨@ç„n<Ã^0¦bŒˆ€H\n©”*,=ôW’BŞ+ğÃës’ÿh\Zü‡¶%Lµğ¤óiËËØ°½øcú6æ Á4N³dåLP÷]`lƒ‹20ª!¨À.Ğ†BPmO¼³ßéúÎ«Ì¢²6’OÃ«ş]F÷·“ãà6`]ŠB€¨z¡ŸÊ\Z…\03ìÎîë«‚VŒ__MxøªaÜczô–£<qøq`nÙRyÃ„d…ÃIŒ0ÀBùóÂF½ì»Ê9ôé–ÍmÁhp4ˆú“ñ†Èˆ¶+\"f\0i ×¶:OdšRvë˜észtt¥*ÙğO—¥*¹vƒÓZ¤‹­jlr¤ 	‚Ü–\0UxFOv\"MèÃ÷Vºa™Æ²¶ä¯\r;Âøw/¢ñ%Šzcè~Ì4ÒZs`›\nÌw¡fT3Ûš66¼Õ8ãä·ò 8`©ÀsÕR7\\A=ƒ¶8èKª1m vP„Õ\"óKAÌaî(úıîæªz°1¢ëAïS;Ä7|Ö™¢şØ›å;tc‹x™æSÇYcc>Úv|d.ínÚ“ÙğÊÚ”6èiM}SÈBLı\'Ê­™ú>s	ÌB¿3ñ¥š1‘QO&zQ”Ë°a³8‹]ÑaĞ5  {Õ/	è8k,ˆ6<“m1ĞFm‘Ş©ƒüg(µFwpªsA$¦\"s\Z\'èâ-™¥ÙÌ),\rYH¸ßR„×Ñr¹¶?[Î§_Ûâ¡lñıT8$¤öĞqj^‚´ÄD·E&ú¦¯™†ÖÔ+¸RÅÎL\Z*hÙë¥p{şY¾2wˆœ>{»´êˆ.Ş;,èrzr›ŠùƒÊÍsZ+qhÀ-ıô=dë-ƒÁ-Œ¶”ĞÑÒ#¢ÏG´{òİ¦õ–\\Ã¬”òb†R[ë½è9ºFEçzÓå7ôú_‚¢äs<ËĞzù=Ï’,}y›¾ÿş“fq’˜DÎàjŒF«4K¿C~§i\Z£è[6‹—_ã4Ï‹‡—ğ?:WŸ°\nendstream\nendobj\n20 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤™(¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0Æ³\nendstream\nendobj\n14 0 obj<</Type/Page/Contents 20 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf4 19 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n7 0 obj[/PDF/Text/ImageB]\nendobj\n5 0 obj <</Type/XObject/Filter/FlateDecode/Length 80/Height 30/BitsPerComponent 1/Subtype/Image/Name/IMmpdf1/ImageMask true/Width 2232/Decode[1 0]>>stream\nxœíÖ1\00ÃÀĞ/ƒÂ-İdßé)hÑŒôëhCg‘ê]mè,R=zBÑY¤zô „¢³HõèAEg‘êÑƒŠÎ\"Õ£!Eª÷\0hœFq\nendstream\nendobj\n6 0 obj <</Type/XObject/Filter/FlateDecode/Length 700/Height 78/BitsPerComponent 1/Subtype/Image/Name/IMmpdf0/ImageMask true/Width 360>>stream\nxœÍ×;Õ0`G)B…[\n„Y‚\r·2K ¼vÇvØ@¨(Ù‚—`D‘\"›ÿ?vîc\"d‰HÉÍã‹slŸÑÔzûşËG¶oÿş:§CØÃn6Ã°†—ax†§aXÃ~vÃ°†äèA| G?ÃrÔş	ç«ä±ê’£_jıqÅ”bˆ\n­±^ -\nqÆm\\Ö÷hÇ×\Z]ôuSÄ~•òÀ\0V‚ÙM”]Çy\"†cCÀ¸®ÛçMÇXõÄ.â YÎ/ìyéxÕ‚ÍÚ[ÆuÍ{Ì	{;®cSq0·x#:Fçùß%S¸FÇ9ã#êe\'Ş¦Öû˜\r]ğJìd\'f8gŒqæk´´“`#8.·˜×u½ÂÑb8qIŒ~uìs0ˆõójqŠ¨ˆ¹2^cp®0:°púD¿cçg–úUrã…Œ`•Ù\'%lˆè©òÀ§©Mh”Iñéù˜£ğÛ†.dŸIÁPV†@iÂ|#H\r|§ZŠ*—%7$+Ë«ZæJ—Èìñ\rûÂ†€uÃ¾a€Ó,Ø5ÌÑ\'~¹Übt/ø¬³\"æ8÷ÄE&&Å%®mR<uçâeAµš²Íib®\\ptIúÃ4vqI-7d+sš‹ôØN\r\'#§Xç[Œ&Ü\rG`Ç”üY°ábÀ¤Ì;æ\\iœ\"ùÎÉ_ë\'Ä¼pY™+ñ’™K‚]Ãh1-}Á®vi8¸ĞqŒ–#Úêw,$ qÔ÷R ‹Óê½ƒö‘V7ï¥€cwÁ…ÏT¿U$Á±—/ö8YÓğ´ñY‘Z×1ŸIadOw¼l\\?áÄã³mRVÌ91zœ­ëogY’o˜ŠÄõ¿‚ïoÜ’m³ş7Oşû÷ö®¯à×GğÇaøĞÿVàŸ‘\nendstream\nendobj\n8 0 obj<</Type/Font/BaseFont/Helvetica-Bold/Subtype/Type1/Name/Fmpdf1/Encoding/WinAnsiEncoding>>\nendobj\n9 0 obj<</Type/Font/BaseFont/Helvetica/Subtype/Type1/Name/Fmpdf0/Encoding/WinAnsiEncoding>>\nendobj\n21 0 obj<</Type/Catalog/AcroForm<</SigFlags 3/Fields[2 0 R]>>/Pages 11 0 R>>\nendobj\n22 0 obj<</CreationDate(D:20080923065445+02\'00\')/Producer(iText 1.4.8 \\(by lowagie.com\\))/ModDate(D:20080923065445+02\'00\')>>\nendobj\nxref\n0 23\n0000000000 65535 f \n0000000117 00000 n \n0000000015 00000 n \n0000010019 00000 n \n0000008020 00000 n \n0000016736 00000 n \n0000016989 00000 n \n0000016703 00000 n \n0000017850 00000 n \n0000017954 00000 n \n0000009802 00000 n \n0000009947 00000 n \n0000012262 00000 n \n0000014463 00000 n \n0000016534 00000 n \n0000010200 00000 n \n0000012117 00000 n \n0000012431 00000 n \n0000014318 00000 n \n0000014632 00000 n \n0000016389 00000 n \n0000018053 00000 n \n0000018137 00000 n \ntrailer\n<</Root 21 0 R/Size 23/Info 22 0 R>>\nstartxref\n18269\n%%EOF\n','2009-06-03 11:40:04','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-08 22:13:51','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('92ba9435-0fd9-4011-a9e0-75c94ded2e74','certificat','Test','0b0b7658-2ddb-11de-86ae-00301bb60f17','image/png','Button.PNG',4578,'‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\Z\0\0\0;\0\0\0+VÒÊ\0\0\0sRGB\0®Îé\0\0\0	pHYs\0\0Ã\0\0ÃÇo¨d\0\0\0tIMEÙ0-ÄÂß_\0\0tIDATxÚíYl\\×y€¿sî2+9ÜÄ7q‘¬…²VËŠ,oŠ-9‰[;µc7i\n´H‘¢@ƒ¼µE_úĞ·¢E‹¢H‘\"éC“6¨;‰\'ñ\Zk±[b´K\\ÄU\\†CÎz—sOHÊ²#Sô7Ï÷Æ¹3s‡?îwÿÿüçÜ{EE€ƒÁğQÑZÒÂ`ø¤VEQ†¡RJkm2•Á°ÂŒ$„°mÛ¶íkì…\raçr9­µeY&RÃmQJY–µfÍšl6»`}cÃìììèèhE¶m›H·%Çq\\×mnn^xÅ¾9sEK˜H·%Š¢…±Ò\rŞÕIJiÛ¶ÉNÃÊ±m[JyŞÓ ¸¹¡5 ¥Œ\\7²,Àt*wrƒaA€(’A ÂP,äŸßêÏ½//-§Ó­SJŞ$˜ÁpG\"äÑú#ç ÓB÷Ü²´mkÛÖR:ssuçÎ%&&„Ö‘©\rwrzÒ2U,Vèé)vu©XLPJ†¡Xh+|ğLÒ²b¡-K¹®ŠÅ’££­/¿œ}ã\rEa\"abn¸c³“Öv©ä54~éK…0™tJ%±²Luk´e¡u,—K\\¿.Ã0ŒÇkêÎåó€;?o‚n¸³qJ¥†3gÊ­­a*e—Ë~&SÉfÃdRDÑ2jİZ\'åºB©šşşu/¼\Z		Ë÷&Ê†ÕCÃ;ï$GGe¢õÌîİ×|Ò¯¯—¾/ÂğCè$@Û6Rºssõ}}n¡ğ¦‡éìîì¡ÓÒAÏåâ¹ÜÂ‹A&cU«‘e-¿Ï¾E[\"ÇÑ–&‘ëšø\Z‘”¬`-ëûuÒBh!œBÁ.—c³³ZÊß–Í`¸“[·ÔÉuõGĞ)ŠÅPªáôé5ÇÕŸ=ëÜTé†£“‘ã TæâÅuÏ?oW«f¼d0¬yËq˜‚—ÃG×éF+\"L&Mt†QìİTõéeGf†ÿœõ=4·ÑİD&bìm†ÏSmÄŞL6Kg\rÑC\'›d*&^¿;:~‰×Ñı({á©lğyıyñ¹v’Ègîã‰.ü¼ğ÷¼>É£“ÑéÓ«p¸Ô×ÓVK}a¡Á’HĞšPQÍS¾N.Ïx™jø	íÔ!½–LÍ2’ê4¹rEráÊV.[.©,Ù\rlê º×Ğ*Izí½lN¡6r*E-8æÀ6:}zUS#\r°çİÆ=m‡bD’J…©3ş‚c§x¡ŸÑOhªÀMÑñ ;ph+ÛL¼ÊÑÿáØEÌQP+ú¡<¼2% ¢â@ *T‹TSx%<Eærj£Ó§ší´lgïƒl\\X_fÎ£õõ$fF*PåíÉOL\'-IµÑº“İ÷²f§™}‰+Ö‡;öuDh\"ŸPÖD\n@¢4‘ÑÉèô©¢ÑŠ(Di€à}ÇùéoÃ¾ùÊçéXCÛ~6Í³ñ4#ãLƒ÷±÷éU)U(•©(°¨–)((¬ø\Zµİ°M !0¶ÑéÓ³I£CT€B‰©s\\z…Ÿçˆf6Áşı´×\"ÚYÓFmœØP¥H¥p,,½ÔîT ğ=\n‚ì	—¸uÓDèS™\'“¥­†&ËG\'PqÒ´çÙ2Í@ª¯%À‘XzQ{\"tH©@1$TDúvÿ—Â‚ZA©Ç\"®ñKx%\"’8.q¡Ê!ÊA¸¸1!–š¸\Z4BTJT”\0—xŒtÛºé”@VªxŠ@V1B€&ò	Ê\0%pá¸* ŠLF\\ƒ…fÄİ‡¸ïI64Ğ¢HtˆE\rÓ÷şóUóĞNË.êåšjÛÁÜ¿‹ŞØ¼‰@ÃNö“­#<{—ßäß~È4ì|šıÑ“d\"&PbšÊ¯ç»¯Q,R]6ïh…_%!Øş8û°y-k+\\ú	\'Ì\\3ÎAº6³7KbšK/18ÈÌd/½kÙQOM\ZbHö Š(3ÚÏ+?åø%&°‰»ùÊ=´­!’\r>*DYÌÍ0ük®¼Åo&¹T¹ğF§;:MEàb×’^KÛ<5­$,P0J~œÑÊ¢KµMì=ÄÓ_c+4k,Q‚!v¼Â©K–HmaãçùÂ~şxëÒi^\0ä&èËP)²¥›ÎÆÅ]\'²tf3p\nüè^œıOğìÓÜH\"y¸Bsœ7Ïs6B.­[¹%QDèKÓz?›ş”Ckè‚_Oâ¿ÈX=ñ‡Ùû(_í¢nŒWÇ8^epò_èå‰–÷ü`\0Ü[ç¿ÄTrû~¯?FÊYLƒˆÅ÷ª\noÃCäç¸RYåÃ6{U»¤|¨¥a7w×ñõ<Ÿ“lÚÊÆjœ\\çÏ0:Ğ¸‡‡òÈ½ôLâå_0â0Ûºørİ{xl7ñ\Z’ØøÛ×ÀCçùÁ[LÎ‘ˆQ)01„›fG’µll¥YPäÚiF/3|‹s´?Æ¶\r<º…®*—_áù£Œ6àmàánvr÷gxêİóX5„ŠP-Wï!°]b	š,	l‰íâ&H‚•$îàZØVœšz\0ÕÏÉŸ“O ÷ÑÒÍÖ\rt­eGÙû©}’v’rPWxıÆBt/k7ò`±µ	\\‰%Ì”ÿjÖI p7°a=ë±¸èjn†kÃŒ‘ì{†CÏ²o\rÙ}Ÿïü+G\\ŠŸåÀèídûzŞF[\Zµ‡ô&²³ĞÇ«ÏñOÿÅõü»»K7s¶Á,ÏÔÓœ$ßÇñoñâQ^š!±‹ÇÿŠƒØ—\"ÖÇ©oóp²uá/²½‡µ›ùı]tLrº– \"ˆ–+_uDP¡<O!MVS¬â¯*æ³ˆyŠÕ¿ŠUÂóÑqÎü’ÿı;†\Z°¿Á¶CÔôÒ’¤¡‰-]ì¾öGÙéÀ8Ç~Ä·ş™3<ÍÎÏ‘9È½O%$ŒĞF§Õ<p@4Áø0C×™(a·é âw³¥ÂÁ\"S)vm¦½‡X%zvsğ«tº¶rÏ.Ú!$[OaŠÑ€|™JzØñ _V\\›Æ±)N0ú6Fxä=*\n@U)çÈÍ0*¤©ÎVÒà6Ğû \'ØÜˆÚÆ­4Å¡µuŒ—‰Û¨ß°J/[Şhv,4ıó3Ì†8%Š!»D––fzÖ9fÆ97ÎY¨›£§J Ìœ±ÑiI\'+UfNğÎÏùŞ¯ùÕ8õ÷sßñÈ.}¶õÔ†Œq\Zp¡MÍìx†€’h‡„ƒ\r”(yäË\\¼L!Cóz:×³s=ÿğ¡&’¿Å‘áÂ\0“ibÖâ,]Ü4©v‰º$µ..TÀíâá?gïŸ¡,p©±\0ÂóUÊ>a„\\ê |‚Y\Z°c$R¤j±cÄ%V„?EP‡´‰ƒ[†O>\rnŠ´ƒcVt\Z–|BJPx9f¸tšqÍD?}‚\Z)ÒëèiÁ&C-ŒÒ«äÇ\Z¥T!OãEäG¸~œÑ.LSÄ_G®ƒL²†uİlj ½µ‹dŒw\ZğäRY!x!¡Fƒ”˜aä:UŸ\0|M%`æ\"ã\'˜äzÈJ,¹ÜL­ØÍBO[>¾‡\\¶!)bñ¾h(\"å ]¤ÀöA¢‚ÈÌŞWğ„DÚX64dhÏÒT‡|B…’/a•.LpâûüÇw¹p|ŠêBŸ=DU*Zœ¶ùŸ~ÑÎÁÇùÆ×Øgí&<›kÍ\\_J,Zú!@©J®À<8!a?¿ü6ßÁ…_ÄŸ\'swí¤ÃÂ‘Ë¥\ZÖ\r4*$€ÀÃR‹ÒŠ\\& B!ga’R‘\ZÖ®¡-d2BxjqÜ°ªuZ5IKB‚Ì:Úw³/ š¢çötÑic{Lrn”s#L&Y¦«—•Âu‰ÅˆÅÑK }üG’®ÅN#Cd„Œ‹kcYèjü’‰Å\n\"CÃ:ºö²×FQYÃõ3œŠÓ±‘æ:IÌ%âÅœ«JÌÆ¶‘6¶DºØÖbá\'-,\\×&Â\Za[’¶-Ü}?ÙÜ.6ÔQÒFJ¬…ÚØl)i!plâ¢ÌÄ$ş4™&\ZšèÜÎgÑâö²1K]­‰@HÌSöVkv’;FÜAú!îßH÷3ü‰GMÍÍÔøp–şÃ<wœ×.Px“ömÔ}“†\'Øúüí~¼*mc9®1ø\"3S¨$÷ÑjÑZEÚ¨­t×PãÜ«¼ú&¿ÚBEp¨ÖR÷¶sßù+¼~šÿ!WPó7<¶‡şš=_¤\ZRµ.B0{–¡—WŒ7A‹pÇÂIO;Mm¯Â©>Îa[»Úéı2ÙTÈêãÄ8®MÌÂq±$\0©8.8;N,E\Z¨!“Æœ ü™]lkaÓÓüå6f=¼\Zu¬K\"¤…0†]:Eş4¹A.ÖS¿aá;¤ZÉH´À›bî*³Ç8z”Ã—ÊÌNÒ½—ìv4Ñ±‰” ÔHËe6†<ERtÜBKw…˜&Œ‚¹OpôG¯r>„F.´3œ¤±‘bšÚ6\Z|Îö1~Š~‹hÜÂÖ\ZZ·â”ÆŠ!S>NáÃ3L\\æ|\Z;b`Š˜+\\cäºğ¯0á1W¯ p²ûï§¡ºˆG0G©HÑaâ<ıÓL”ÉM!è¯§=Ã¥Q¦\"ò>ö9-9f-qù“)ZJlİM{=2ËZ—x’˜ğÀ÷ğK„¾ÑiUêÎ0{˜“W)üŒÔ ,´Ä±°Zú³T&™`|ni,qêÇÌÓœ¢Nã´^»{yò—)‰Æ±NÆ¢6ÄÖDšTÄÌ0ƒÃŒB4‡yi„ë?!‘$„¸3Oÿ\0“U€£ßcêM.µzñÇH 2ÅÜUr%®ŸC¿Æh0ö6Ãšêö3ú\Z\'kˆ&\Zfôuô›ül˜ñ“H¢$\"‚\0­Q’ÒSçÉç(V—Ÿ¹ä.2P¥¤‘?áÚÛ\\ª#Yeú\"×G§ĞÏs¯pº‘¸D:îfÏÙ±‡í6nœpü5Êæ¦W«S\'U¤x‘âE®~˜O\rŸføô²ï8{»¯(Ãeú.Ó÷Û0päö¿äÂÍÌÀ›ÀÑ›_,ÂÎ\\áÌò_4\rç¸ü3\rœ„“¼ó¾wN1ĞÏ @)€î	Ä.j·Ğ™!•cz’‘Yò˜i\\sqûÊZ«ù‘VÎÖnbw7÷´²¨ú4­gËÃ´§°¯2t„w®p\nFÌuVF§•°š]’.énZáÑøÚÒ±w7y3¿Á;/q¢Ÿsæ(1:n*‰ğ\'˜9ÅÉ©Ô\'ñT„\n)Œ1q‚«g¹0mâdt2¬D§ò¯2fó¼ï^8¸°$?\\¸\nÓp+o¡†–ç½çªÕkT@BÙÄâv¥ñ­ÇŞQ$•2Ñ1>–N•[Z¦wíòêê\"Ë&5¥ØÓZzRÎìŞ]nmÍ>Üşâ‹±ÙY&ƒá#f\'¡u©­múŞ{ç6m2O4†?>;İø°ÖD‘ô}«T2¡4¬jUDÑmWÍß¢³§APJKé55¹Å¢rİÈ6-uÃªU*Z¿¾>²mEbÙ9ı[K\"ƒ\0­‹CO=åæóÚ²Ş÷\\ƒaµ”yaˆ¥ ¦FzŞòUßë¥ööR{»	¨Á°Xòù>Ë.ô}W§(ŠÂ0Œ¢ˆ›A¨\\W[–Ğæ\ZfÃ*eññ¶ah-¥¦2A „ˆnÊWïê$„Rr©®³”²Ìr\r––AKÉMÓ°RJñŞæÄ¢N–eÕ××k­µÖ–e™è·E)eYV&“¹!•ĞZß¨ô”RZkaî¡a0¬(ci!„mÛ¶m/X³Xù…†©´Ö`0|şšã~Ş;óÚ\r\0\0\0\0IEND®B`‚','2009-08-24 23:28:10','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('11d5aa5f-3f00-438d-bf00-09fe4a9c3968','certificat','Attest SJO','0b0b7658-2ddb-11de-86ae-00301bb60f17','application/msword','Test.xls',28672,'ĞÏà¡±\Zá\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0>\0\0şÿ	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÿÿÿ\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿıÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\n\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\Z\0\0\0\0\0\0\0\0\0\0\0\0şÿÿÿ&\0\0\02\0\0\0!\0\0\0\"\0\0\0#\0\0\0$\0\0\0%\0\0\0\'\0\0\01\0\0\0(\0\0\0)\0\0\0*\0\0\0+\0\0\0,\0\0\0-\0\0\0.\0\0\0/\0\0\00\0\0\03\0\0\05\0\0\0şÿÿÿ4\0\0\06\0\0\0şÿÿÿşÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿR\0o\0o\0t\0 \0E\0n\0t\0r\0y\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0 \0\0\0\0\0À\0\0\0\0\0\0F\0\0\0\0¹9ñ@Ÿ=Êà,zà¢=Ê \0\0\0À%\0\0\0\0\0\0W\0o\0r\0k\0b\0o\0o\0k\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0m7\0\0\0\0\0\0_\0V\0B\0A\0_\0P\0R\0O\0J\0E\0C\0T\0_\0C\0U\0R\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\"\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\ZLÙ¢=ÊàUÙ¢=Ê\0\0\0\0\0\0\0\0\0\0\0\0V\0B\0A\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ğcNÙ¢=ÊğÔPÙ¢=Ê\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0ªÍÉ\0\0\0\0á\0\0°Á\0\0\0\0â\0\0\0\\\0p\0\0\0reich                                                                                                        B\0\0°a\0\0\0À\0\0=\0\0Ó\0\0\0º\0\0\0DieseArbeitsmappeœ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0¯\0\0\0¼\0\0\0=\0\0à _v/8\0\0\0\0\0\0X@\0\0\0\0\0\0\0\0\"\0\0\0\0\0\0\0·\0\0\0Ú\0\0\0\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\0h\08\0¼\0\0\0\0âC\0a\0m\0b\0r\0i\0a\01\0\Z\0,\08\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0\08\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0Ü\0\08\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0<\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0>\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0?\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\04\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\04\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0	\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\n\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0	\0\0\0\0\0âA\0r\0i\0a\0l\03\0\0\0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0;\0\\\0-\0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0=\0\0\0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0;\0[\0R\0e\0d\0]\0\\\0-\0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0?\0\0\0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0;\0\\\0-\0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0I\0\0\"\0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0;\0[\0R\0e\0d\0]\0\\\0-\0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0q\0*\06\0_\0-\0*\0 \0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0_\0-\0;\0\\\0-\0*\0 \0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0_\0-\0;\0_\0-\0*\0 \0\"\0-\0\"\0\\\0 \0\"\0¬ \"\0_\0-\0;\0_\0-\0@\0_\0-\0k\0)\03\0_\0-\0*\0 \0#\0,\0#\0#\00\0\\\0 \0_\0¬ _\0-\0;\0\\\0-\0*\0 \0#\0,\0#\0#\00\0\\\0 \0_\0¬ _\0-\0;\0_\0-\0*\0 \0\"\0-\0\"\0\\\0 \0_\0¬ _\0-\0;\0_\0-\0@\0_\0-\0\0,\0>\0_\0-\0*\0 \0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0_\0-\0;\0\\\0-\0*\0 \0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0_\0-\0;\0_\0-\0*\0 \0\"\0-\0\"\0?\0?\0\\\0 \0\"\0¬ \"\0_\0-\0;\0_\0-\0@\0_\0-\0{\0+\0;\0_\0-\0*\0 \0#\0,\0#\0#\00\0.\00\00\0\\\0 \0_\0¬ _\0-\0;\0\\\0-\0*\0 \0#\0,\0#\0#\00\0.\00\00\0\\\0 \0_\0¬ _\0-\0;\0_\0-\0*\0 \0\"\0-\0\"\0?\0?\0\\\0 \0_\0¬ _\0-\0;\0_\0-\0@\0_\0-\0à\0\0\0\0\0\0õÿ \0\0\0\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0\0 \0\0\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0Ÿ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0­ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0ª à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0® à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0› à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¯ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¬ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0 à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0‹ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0® à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¬ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0³ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0 à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0 à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0‹ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¤ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0± à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0´ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¾ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0Š à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¹ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¤ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0± à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0µ à\0\0\0\0\0õÿ \0\0”¿¿\0– à\0\0\0\0\0õÿ \0\0”——\0– à\0\0\0+\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0)\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\r\0\0\0õÿ \0\0”——\0¯ à\0\0\0\0\0õÿ \0\0Ô\0a\0\0>\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\n\0\0\0õÿ \0\0´\0\0\0\0\0\0\0ª à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0« à\0\0\0\0\0õÿ \0\0œ\0š à\0\0\0	\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0­ à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0Ô\0P\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0Ô\0P\0\0\0\0\0À à\0\0	\0\0\0õÿ \0\0Ô\0 \0\0\0\0\0À à\0\0	\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0Ô\0`\0\0\0\Z\0\0À à\0\0\0,\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0*\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0”ff¿¿\0· à\0\0\0\01\0\0 \0\0\0\0\0\0\0\0\0À |\0|\0\0\0\0\0\0\0\0\0\0\0\0?\0//}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\n\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef	\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\Z\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL	\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0 \0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0!\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023	\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\"\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0#\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0$\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0%\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0&\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\'\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0	\0\0\0\0,\0#\0#\00}Œ\0}\0\0\0\0\0\0\0\0\0\0\0\0(\0\0\0\0\r\0\0\0\0\0???ÿ\0_\0¬ _\0-\0\0\0\0\0òòòÿ\0,\0#\0#\00\0\0\0\0\0???ÿ\0¬ _\0-\0;\0\0\0\0\0???ÿ\0\"\0?\0?\0\\	\0\0\0\0\0???ÿ\0_\0-\0@\0_\n\0\0\0\0\0???ÿ\0\0\0\0\0\0\0\0}Œ\0}\0\0\0\0\0\0\0\0\0\0\0\0)\0\0\0\0\r\0\0\0\0\0ú}\0ÿ\0_\0¬ _\0-\0\0\0\0\0òòòÿ\0,\0#\0#\00\0\0\0\0\0ÿ\0¬ _\0-\0;\0\0\0\0\0ÿ\0\"\0?\0?\0\\	\0\0\0\0\0ÿ\0_\0-\0@\0_\n\0\0\0\0\0ÿ\0\0\0\0\0\0\0\0}(\0}\0\0\0\0\0\0\0\0\0\0\0\0*\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0+\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}Œ\0}\0\0\0\0\0\0\0\0\0\0\0\0,\0\0\0\0\r\0\0\0\0\0??vÿ\0_\0¬ _\0-\0\0\0\0\0ÿÌ™ÿ\0,\0#\0#\00\0\0\0\0\0ÿ\0¬ _\0-\0;\0\0\0\0\0ÿ\0\"\0?\0?\0\\	\0\0\0\0\0ÿ\0_\0-\0@\0_\n\0\0\0\0\0ÿ\0\0\0\0\0\0\0\0}P\0}\0\0\0\0\0\0\0\0\0\0\0\0-\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00\0\0\0\0\0\0\0\0\0¬ _\0-\0;}(\0}\0\0\0\0\0\0\0\0\0\0\0\0.\0\0\0\0\r\0\0\0\0\0ÿ\0_\0¬ _\0-}<\0}\0\0\0\0\0\0\0\0\0\0\0\0/\0\0\0\0\r\0\0\0\0\0\0a\0ÿ\0_\0¬ _\0-\0\0\0\0\0ÆïÎÿ\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\00\0\0\0\0\r\0\0\0\0\0œe\0ÿ\0_\0¬ _\0-\0\0\0\0\0ÿëœÿ\0,\0#\0#\00}Œ\0}\0\0\0\0\0\0\0\0\0\0\0\01\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0ÿÿÌÿ\0,\0#\0#\00\0\0\0\0\0²²²ÿ\0¬ _\0-\0;\0\0\0\0\0²²²ÿ\0\"\0?\0?\0\\	\0\0\0\0\0²²²ÿ\0_\0-\0@\0_\n\0\0\0\0\0²²²ÿ\0\0\0\0\0\0\0\0}(\0}\0\0\0\0\0\0\0\0\0\0\0\02\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}<\0}\0\0\0\0\0\0\0\0\0\0\0\03\0\0\0\0\r\0\0\0\0\0œ\0ÿ\0_\0¬ _\0-\0\0\0\0\0ÿÇÎÿ\0,\0#\0#\00}-\0}\0\0\0\0\0\0\0\0\0\0\0\04\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0}<\0}\0\0\0\0\0\0\0\0\0\0\0\05\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\06\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÿ?\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\07\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}(\0}\0\0\0\0\0\0\0\0\0\0\0\08\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}<\0}\0\0\0\0\0\0\0\0\0\0\0\09\0\0\0\0\r\0\0\0\0\0ú}\0ÿ\0_\0¬ _\0-\0\0\0\0\0ÿ€ÿ\0,\0#\0#\00}(\0}\0\0\0\0\0\0\0\0\0\0\0\0:\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0;\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0<\0\0\0\0\r\0\0\0\0\0ÿ\0\0ÿ\0_\0¬ _\0-}Œ\0}\0\0\0\0\0\0\0\0\0\0\0\0=\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0¥¥¥ÿ\0,\0#\0#\00\0\0\0\0\0???ÿ\0¬ _\0-\0;\0\0\0\0\0???ÿ\0\"\0?\0?\0\\	\0\0\0\0\0???ÿ\0_\0-\0@\0_\n\0\0\0\0\0???ÿ\0\0\0\0\0\0\0\0}(\0}\0\0\0\0\0\0\0\0\0\0\0\0>\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-“\0\0\r\0\020% - Akzent1’H\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\01\0\0\0\0\0\0efÛåñÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\020% - Akzent2’H\0’\0\0\0\0\0\0\0\0\0\0\"ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\02\0\0\0\0\0\0efòİÜÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\020% - Akzent3’H\0’\0\0\0\0\0\0\0\0\0\0&ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\03\0\0\0\0\0\0efêñİÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\020% - Akzent4’H\0’\0\0\0\0\0\0\0\0\0\0*ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\04\0\0\0\0\0\0efåàìÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\020% - Akzent5’H\0’\0\0\0\0\0\0\0\0\0\0.ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\05\0\0\0\0\0\0efÛîóÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\020% - Akzent6’H\0’\0\0\0\0\0\0\0\0\0\02ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\06\0\0\0\0\0\0	efıéÙÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\040% - Akzent1’H\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\01\0\0\0\0\0\0ÌL¸Ìäÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\040% - Akzent2’H\0’\0\0\0\0\0\0\0\0\0\0#ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\02\0\0\0\0\0\0ÌLæ¹¸ÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\040% - Akzent3’H\0’\0\0\0\0\0\0\0\0\0\0\'ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\03\0\0\0\0\0\0ÌL×ä¼ÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\040% - Akzent4’H\0’\0\0\0\0\0\0\0\0\0\0+ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\04\0\0\0\0\0\0ÌLÌÀÚÿ\0\0\0\0\0\0\0ÿ“\0\Z\0\r\0\040% - Akzent5’H\0’\0\0\0\0\0\0\0\0\0\0/ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\05\0\0\0\0\0\0ÌL¶İèÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\040% - Akzent6’H\0’\0\0\0\0\0\0\0\0\0\03ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\06\0\0\0\0\0\0	ÌLüÕ´ÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\060% - Akzent1’H\0’\0\0\0\0\0\0\0\0\0\0 ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\01\0\0\0\0\0\023•³×ÿ\0\0\0\0\0ÿÿÿÿ“\0\0\r\0\060% - Akzent2’H\0’\0\0\0\0\0\0\0\0\0\0$ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\02\0\0\0\0\0\023Ù—•ÿ\0\0\0\0\0ÿÿÿÿ“\0\0\r\0\060% - Akzent3’H\0’\0\0\0\0\0\0\0\0\0\0(ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\03\0\0\0\0\0\023ÂÖšÿ\0\0\0\0\0ÿÿÿÿ“\0\0\r\0\060% - Akzent4’H\0’\0\0\0\0\0\0\0\0\0\0,ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\04\0\0\0\0\0\023²¡Çÿ\0\0\0\0\0ÿÿÿÿ“\0 \0\r\0\060% - Akzent5’H\0’\0\0\0\0\0\0\0\0\0\00ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\05\0\0\0\0\0\023“Íİÿ\0\0\0\0\0ÿÿÿÿ“\0!\0\r\0\060% - Akzent6’H\0’\0\0\0\0\0\0\0\0\0\04ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\06\0\0\0\0\0\0	23úÀÿ\0\0\0\0\0ÿÿÿÿ“\0\"\0\0\0Akzent1’<\0’\0\0\0\0\0\0\0\0\0\0ÿ\0A\0k\0z\0e\0n\0t\01\0\0\0\0\0\0\0\0O½ÿ\0\0\0\0\0ÿÿÿÿ“\0#\0\0\0Akzent2’<\0’\0\0\0\0\0\0\0\0\0\0!ÿ\0A\0k\0z\0e\0n\0t\02\0\0\0\0\0\0\0\0ÀPMÿ\0\0\0\0\0ÿÿÿÿ“\0$\0\0\0Akzent3’<\0’\0\0\0\0\0\0\0\0\0\0%ÿ\0A\0k\0z\0e\0n\0t\03\0\0\0\0\0\0\0\0›»Yÿ\0\0\0\0\0ÿÿÿÿ“\0%\0\0\0Akzent4’<\0’\0\0\0\0\0\0\0\0\0\0)ÿ\0A\0k\0z\0e\0n\0t\04\0\0\0\0\0\0\0\0€d¢ÿ\0\0\0\0\0ÿÿÿÿ“\0&\0\0\0Akzent5’<\0’\0\0\0\0\0\0\0\0\0\0-ÿ\0A\0k\0z\0e\0n\0t\05\0\0\0\0\0\0\0\0K¬Æÿ\0\0\0\0\0ÿÿÿÿ“\0\'\0\0\0Akzent6’<\0’\0\0\0\0\0\0\0\0\0\01ÿ\0A\0k\0z\0e\0n\0t\06\0\0\0\0\0\0	\0\0÷–Fÿ\0\0\0\0\0ÿÿÿÿ“\0(\0\0\0Ausgabe’t\0’\0\0\0\0\0\0\0\0\0\0ÿ\0A\0u\0s\0g\0a\0b\0e\0\0\0\0\0\0ÿ\0\0òòòÿ\0\0ÿ\0\0???ÿ\0\0ÿ\0\0???ÿ\0\0\0ÿ\0\0???ÿ\0\0\0ÿ\0\0???ÿ\0	\0\0ÿ\0\0???ÿ\0“\0)\0\n\0\0Berechnung’z\0’\0\0\0\0\0\0\0\0\0\0ÿ\n\0B\0e\0r\0e\0c\0h\0n\0u\0n\0g\0\0\0\0\0\0ÿ\0\0òòòÿ\0\0ÿ\0\0ú}\0ÿ\0\0ÿ\0\0ÿ\0\0\0ÿ\0\0ÿ\0\0\0ÿ\0\0ÿ\0	\0\0ÿ\0\0ÿ\0“\0*€ÿ’$\0’\0\0\0\0\0\0\0\0\0\0ÿ\0D\0e\0z\0i\0m\0a\0l\0\0\0\0\0“\0+€ÿ’,\0’\0\0\0\0\0\0\0\0\0\0ÿ\0D\0e\0z\0i\0m\0a\0l\0 \0[\00\0]\0\0\0\0\0“\0,\0\0\0Eingabe’t\0’\0\0\0\0\0\0\0\0\0\0ÿ\0E\0i\0n\0g\0a\0b\0e\0\0\0\0\0\0ÿ\0\0ÿÌ™ÿ\0\0ÿ\0\0??vÿ\0\0ÿ\0\0ÿ\0\0\0ÿ\0\0ÿ\0\0\0ÿ\0\0ÿ\0	\0\0ÿ\0\0ÿ\0“\r\0-\0\0\0Ergebnis’N\0’\0\0\0\0\0\0\0\0\0\0ÿ\0E\0r\0g\0e\0b\0n\0i\0s\0\0\0\0\0\0\0\0\0\0\0ÿ\0\0\0\0O½ÿ\0\0\0\0\0O½ÿ\0“\0.\0\0\0Erklärender Text’B\0’\0\0\0\0\0\0\0\0\0\05ÿ\0E\0r\0k\0l\0ä\0r\0e\0n\0d\0e\0r\0 \0T\0e\0x\0t\0\0\0\0\0\0ÿ\0\0ÿ“\0/\0\0\0Gut’4\0’\0\0\0\0\0\0\0\0\0\0\Zÿ\0G\0u\0t\0\0\0\0\0\0ÿ\0\0ÆïÎÿ\0\0ÿ\0\0\0a\0ÿ“\00\0\0\0Neutral’<\0’\0\0\0\0\0\0\0\0\0\0ÿ\0N\0e\0u\0t\0r\0a\0l\0\0\0\0\0\0ÿ\0\0ÿëœÿ\0\0ÿ\0\0œe\0ÿ“\n\01\0\0\0Notiz’d\0’\0\0\0\0\0\0\0\0\0\0\nÿ\0N\0o\0t\0i\0z\0\0\0\0\0\0ÿ\0\0ÿÿÌÿ\0\0ÿ\0\0²²²ÿ\0\0\0ÿ\0\0²²²ÿ\0\0\0ÿ\0\0²²²ÿ\0	\0\0ÿ\0\0²²²ÿ\0“\02€ÿ’$\0’\0\0\0\0\0\0\0\0\0\0ÿ\0P\0r\0o\0z\0e\0n\0t\0\0\0\0\0“\r\03\0\0\0Schlecht’>\0’\0\0\0\0\0\0\0\0\0\0ÿ\0S\0c\0h\0l\0e\0c\0h\0t\0\0\0\0\0\0ÿ\0\0ÿÇÎÿ\0\0ÿ\0\0œ\0ÿ“\0\0€\0ÿ’2\0’\0\0\0\0\0\0\0\0\0\0\0ÿ\0S\0t\0a\0n\0d\0a\0r\0d\0\0\0\0\0\0\0\0\0\0\0ÿ“\04\0\0\0Überschrift’=\0’\0\0\0\0\0\0\0\0\0\0ÿ\0Ü\0b\0e\0r\0s\0c\0h\0r\0i\0f\0t\0\0\0\0\0\0\0\0I}ÿ%\0\0“\05\0\r\0\0Überschrift 1’J\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\0Ü\0b\0e\0r\0s\0c\0h\0r\0i\0f\0t\0 \01\0\0\0\0\0\0\0\0I}ÿ\0\0\0\0O½ÿ\0“\06\0\r\0\0Überschrift 2’J\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\0Ü\0b\0e\0r\0s\0c\0h\0r\0i\0f\0t\0 \02\0\0\0\0\0\0\0\0I}ÿ\0\0ÿ?¨ÀŞÿ\0“\07\0\r\0\0Überschrift 3’J\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\0Ü\0b\0e\0r\0s\0c\0h\0r\0i\0f\0t\0 \03\0\0\0\0\0\0\0\0I}ÿ\0\023•³×ÿ\0“\08\0\r\0\0Überschrift 4’<\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\0Ü\0b\0e\0r\0s\0c\0h\0r\0i\0f\0t\0 \04\0\0\0\0\0\0\0\0I}ÿ“\09\0\0\0Verknüpfte Zelle’P\0’\0\0\0\0\0\0\0\0\0\0ÿ\0V\0e\0r\0k\0n\0ü\0p\0f\0t\0e\0 \0Z\0e\0l\0l\0e\0\0\0\0\0\0ÿ\0\0ú}\0ÿ\0\0ÿ\0\0ÿ€ÿ\0“\0:€ÿ’$\0’\0\0\0\0\0\0\0\0\0\0ÿ\0W\0ä\0h\0r\0u\0n\0g\0\0\0\0\0“\0;€ÿ’,\0’\0\0\0\0\0\0\0\0\0\0ÿ\0W\0ä\0h\0r\0u\0n\0g\0 \0[\00\0]\0\0\0\0\0“\0<\0\0\0Warnender Text’>\0’\0\0\0\0\0\0\0\0\0\0ÿ\0W\0a\0r\0n\0e\0n\0d\0e\0r\0 \0T\0e\0x\0t\0\0\0\0\0\0ÿ\0\0ÿ\0\0ÿ“\0=\0\0\0Zelle überprüfen’†\0’\0\0\0\0\0\0\0\0\0\0ÿ\0Z\0e\0l\0l\0e\0 \0ü\0b\0e\0r\0p\0r\0ü\0f\0e\0n\0\0\0\0\0\0ÿ\0\0¥¥¥ÿ\0\0\0\0\0ÿÿÿÿ\0\0ÿ\0\0???ÿ\0\0\0ÿ\0\0???ÿ\0\0\0ÿ\0\0???ÿ\0	\0\0ÿ\0\0???ÿ\0X\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0T\0a\0b\0l\0e\0S\0t\0y\0l\0e\0M\0e\0d\0i\0u\0m\09\0P\0i\0v\0o\0t\0S\0t\0y\0l\0e\0L\0i\0g\0h\0t\01\06\0`\0\0\0…\0\0^.\0\0\0\0\0Tabelle1š\0š\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0£\0£\0\0\0\0\0\0\0\0\0\0\0\0\0\0Œ\0\01\01\0®\0\0\0\0\0\0\0\0\0\0\0Á\0Á\0\0ë\0ü\0\0\0\0\0\0\0\0\0\0Testÿ\0\n\0\0Ï-\0\0\0\0\0c\0c\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0–\0–\0\0\0\0\0\0\0\0\0\0Bå\0›\0›\0\0\0\0\0\0\0\0\0\0\0\0\0Œ\0Œ\0\0\0\0\0\0\0\0\0\0\0\0\0\0—\0—\0\0\0\0\0\0\0\0\0\04cş·¢Á\rå½=_MA»Âã\n\0\0\0	\0\0\0ªÍÉ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0f6\0\0ô6\0\0\r\0\0\0\0\0d\0\0\0\0\0\0\0\0\0\0ü©ñÒMbP?_\0\0\0*\0\0\0\0+\0\0\0\0‚\0\0\0€\0\0\0\0\0\0\0\0\0\0%\0\0\0ÿ\0\0\0Á\0\0\0\0\0\0ƒ\0\0\0\0„\0\0\0\0&\0\0ffffffæ?\'\0\0ffffffæ?(\0\0[4®Éd2é?)\0\0[4®Éd2é?M\0î\0\0\\\0\\\0p\0r\0i\0n\0t\0s\0r\0v\0\\\0s\0f\02\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Ü\0Sÿ€\0	\0š4d\0\0\0X\0\0X\0\0A\04\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0PRIVâ0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\'\'\'\0\0\'\0\0\0\0\0\0\0\0ğÄ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0L\0\0P4\0(ˆ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ïL„!\0\0\0\0\0\0\0\0ÿ\0\0\0ÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ğ\0\0SMTJ\0\0\0\0\0àR\0I\0C\0O\0H\0 \0A\0f\0i\0c\0i\0o\0 \0M\0P\0 \0C\02\05\00\00\0 \0P\0S\0\0\0Resolution\0600dpi\0RIPaperPolicy\0PromptUser\0PageSize\0A4\0PageRegion\0\0InputSlot\0*UseFormTrayTable\0LeadingEdge\0\0Duplex\0None\0RICollate\0False\0RIPrintMode\00rhit\0Rimagesm\0Off\0ColorModel\0Gray\0RPSBitsPerPixel\02BitsPerPixel\0RPSRGBcorrect\0DetailBright\0RPSColorRendDict\0Auto\0RPSDitherType\0Auto\0RPSBlackMode\0gray\0RPSBlackOverPrint\0False\0RPSColorSep\0None\0Rcmyksimulation\0Off\0MediaType\0Auto\0OutputBin\0Default\0StapleLocation\0None\0RIPunch\0None\0RIOrientOvr\0Off\0RIWatermark\0Off\0RIWMText\0Confidential\0RIwmFont\0HelveticaB\0RIwmSize\036\0RIwmAngle\045Deg\0RIwmTextStyle\0Gray\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0L\0\0\0EBDAEBDA\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0¡\0\"\0	\0d\0\0\0\0\0XX333333Ó?333333Ó?\0œ&\0œ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0<3\0\0\0\0\0\0\0\0U\0\0\n\0}\0\0\0\0\0\0m>\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\0\0\0\0\0\0\0\0\0\0\0\0ÿ\0\0\0\0\0\0\0\0\0\0\0\0ÿ\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0>\0\0\0\0\0~\n\0\0\0\0>\0\0À^@~\n\0\0\0\0>\0\0@m@×\0\n\0f\0\0\0(\0\0\0>\0¶\0\0\0\0@\0\0\0\0\0\0\0\0\0\0\0‹\0‹\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0º\0\0\0Tabelle1g\0g\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿD\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0M\0o\0d\0u\0l\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\n\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0â\0\0\0\0\0\0_\0_\0S\0R\0P\0_\02\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0T\0\0\0\0\0\0\0_\0_\0S\0R\0P\0_\03\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\Z\0\0\0k\0\0\0\0\0\0\0D\0i\0e\0s\0e\0A\0r\0b\0e\0i\0t\0s\0m\0a\0p\0p\0e\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$\0	\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ß\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\n\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÿÿÿ\0\0\0şÿÿÿ\0\0\0şÿÿÿ\0\0\0\0\0\0\0\0\0 \0\0\0!\0\0\0\"\0\0\0#\0\0\0$\0\0\0%\0\0\0&\0\0\0\'\0\0\0(\0\0\0)\0\0\0*\0\0\0+\0\0\0şÿÿÿ-\0\0\0.\0\0\0/\0\0\00\0\0\01\0\0\02\0\0\03\0\0\04\0\0\05\0\0\06\0\0\07\0\0\08\0\0\09\0\0\0:\0\0\0;\0\0\0şÿÿÿ=\0\0\0>\0\0\0?\0\0\0@\0\0\0A\0\0\0B\0\0\0C\0\0\0D\0\0\0E\0\0\0F\0\0\0G\0\0\0H\0\0\0I\0\0\0J\0\0\0K\0\0\0L\0\0\0M\0\0\0N\0\0\0O\0\0\0P\0\0\0Q\0\0\0R\0\0\0S\0\0\0T\0\0\0U\0\0\0V\0\0\0W\0\0\0X\0\0\0Y\0\0\0Z\0\0\0[\0\0\0\\\0\0\0]\0\0\0^\0\0\0_\0\0\0`\0\0\0a\0\0\0b\0\0\0c\0\0\0d\0\0\0şÿÿÿf\0\0\0g\0\0\0h\0\0\0i\0\0\0j\0\0\0k\0\0\0l\0\0\0m\0\0\0şÿÿÿo\0\0\0p\0\0\0q\0\0\0r\0\0\0s\0\0\0t\0\0\0u\0\0\0v\0\0\0w\0\0\0x\0\0\0y\0\0\0z\0\0\0{\0\0\0|\0\0\0}\0\0\0~\0\0\0\0\0\0şÿÿÿ\0ğ\0\0\0,\0\0Ô\0\0\0¸\0\0ÿÿÿÿ]\0\0µ\0\0\0\0\0\0\0\0\0áÛÀ—\0\0ÿÿ\0\0\0\0\0\0\0¶\0ÿÿ\0\0\0\0ÿÿÿÿ\0\0\0\0ÿÿ\0ÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0ÿÿÿÿx\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿ\0\0\0\0ME\0\0ÿÿÿÿÿÿ\0\0\0\0ÿÿ\0\0\0\0ÿÿ\0\0\0\0ß\0ÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿ\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ(\0\0\0\0<ÿÿ\0\0\0\0\0\0<\0\0\Z\0\0\0\0<\0\0\0\0\06\nÿÿÿÿ\0\0ÿÿ\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿè\0\0\0ÿÿÿÿ\0\0\0`\0\0\0\0ÿÿÿÿ@\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿ\0\0	\0	\0\0\0„\0\0\0\0\0\0\0 \n14 ¤\0ÿÿÿÿÿÿÿÿ`\0\0\0\0\0\0\0\\\0O\0\0\0\0%\0\0\0 „\Zÿÿÿÿÿÿÿÿ€\0\0\0\0\0\0\0L\0\0\0\0\0%\0\0\0 „ÿÿÿÿÿÿÿÿ \0\0\0\0\0\0\0\0\0\0\0\0\0%\0\0\0ÿÿÿÿ\0\0\0\0ƒÿÿÿÿ\0ÿÿØ\0\0\0\0\0ÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0%\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ°\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0¨\0\0\0àïGL\0$\0*\0\\\0R\0f\0f\0f\0f\0*\00\0@\04\0c\04\07\0f\06\00\01\0\0\0\0ß\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÊ\0	\0\"\0\0\0\0\0\0\0\0\0€\0\0\0\0\0\0\0€\0\0\0\0\0\0\0€\0\0\0\0\0\0\0*\0\0\0 \0\0\0\0\0\0\0ˆ\0\0\0\0\0\0\0P\0\0\0\0\n\0\0\0p\0\0\0\0\0\0\0€\0\0\0ÿÿÿÿ \0\0\0–\0\0\0\0\0\0]\0òH\0\0\0]\0òh\0\0\0]\0òˆ\0\0\0í\0¶\0\0c:\\temp\\test2.xls\0 \0\0!\0%\0\0.\0\Z\0ÿÿÿÿ¶\0\0@\0¶\0\0A:A\0 \0%\0\"\0(\0$x\0\0\0· \0\0(\0&Ñ§ô\0`o\0ÿÿ`\0\0\0í\0 \0\Z%\0 \0\0.\0ÿÿÿÿ`\0\0\0ÿÿÿÿ\0\0#±\0Attribut\0e VB_Nam\0e = \"Mod\0ul1\"\r\nSu\0b Makro1°()\r\n¬L.\0È\0ProcDataInvoke_FuncŠ \\n14\0ˆ   Di\0m excel @As New.\0Applicat ion\r\n&woÀrkbook)Á\nsheet­S\0;=`WL\0s.Open(\"€c:\\temp\0\0st2.xls\"O\0Ö\0<ƒ-= $.{ \ns€€.\0Columns(\0\"A:A\").N\0umberForhmat~@ƒ|‚z.@Visibl­T\0rue\r\nEnd €­\r\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0rU€\0\0\0€\0\0\0€\0\0\0€\0\0\0\0\0~|\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0k\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0rU€\0\0\0\0\0\0\0€\0\0\0€\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0$\0\0\0\0\0\0\0\0\0\0`\0\0ıÿÿÿÿÿÿÿa\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ğ\0\0\0Ä\0\0Ô\0\0\0\0\0\0ÿÿÿÿË\0\0\0\0\0\0\0\0\0\0\0áÛ>/\0\0ÿÿ#\0\0\0ˆ\0\0\0¶\0ÿÿ\0\0\0\0ÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0ÿÿÿÿx\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿ\0\0\0\0ME\0\0ÿÿÿÿÿÿ\0\0\0\0ÿÿ\0\0\0\0ÿÿ\0\0\0\0ß\0ÿÿ\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ(\0\0\0\0SLÿÿÿÿ\0\0\0Sÿÿÿÿ\0\0\0S”ÿÿÿÿ\0\0\0\0<ÿÿÿÿ\0\0ÿÿ\0\0\0\0\0N\00\0{\00\00\00\02\00\08\01\09\0-\00\00\00\00\0-\00\00\00\00\0-\0C\00\00\00\0-\00\00\00\00\00\00\00\00\00\00\04\06\0}\0\0\0\0\0ÿÿÿÿ8\0\0\0€şÿÿÿÿÿ\0ÿÿ(\0\0\0ÿÿ\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0%\0\0\0ÿÿÿÿH\0\0\0\0\0ÿÿ\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0ß\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÊ\0\0\0ÿÿÿÿ\0\0\0ÿÿÿÿx\0\0\0ÿÿÿÿ\0\0¶°\0Attribut\0e VB_Nam\0e = \"Die\0seArbeit\0smappe\"\r\"\n\n Bas 0{\000020819ê-\00C\0$ 0046}\r|GlobalÚSpa‚cIFalsed\0CreatablPredecla\0Id\0¶Tru\rBExpose\0Template`Deriv’BuÀstomizD2\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ğ\0\0\0Ä\0\0Ô\0\0\0\0\0\0ÿÿÿÿË\0\0\0\0\0\0\0\0\0\0\0áÛ‡§\0\0ÿÿ#\0\0\0ˆ\0\0\0¶\0ÿÿ\0\0\0\0ÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0ÿÿÿÿx\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿ\0\0\0\0ME\0\0ÿÿÿÿÿÿ\0\0\0\0ÿÿ\0\0\0\0ÿÿ\0\0\0\0ß\0ÿÿ\0\0\0\0\0ÿÿÿÿÿÿT\0a\0b\0e\0l\0l\0e\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0,\0\0\0Ó\0\0\0\0\0\0_\0V\0B\0A\0_\0P\0R\0O\0J\0E\0C\0T\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\Z\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0<\0\0\0 \n\0\0\0\0\0\0d\0i\0r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0e\0\0\0<\0\0\0\0\0\0_\0_\0S\0R\0P\0_\00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0n\0\0\0N\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ(\0\0\0\0SLÿÿÿÿ\0\0\0Sÿÿÿÿ\0\0\0S”ÿÿÿÿ\0\0\0\0<ÿÿÿÿ\0\0ÿÿ\0\0\0\0\0N\00\0{\00\00\00\02\00\08\02\00\0-\00\00\00\00\0-\00\00\00\00\0-\0C\00\00\00\0-\00\00\00\00\00\00\00\00\00\00\04\06\0}\0\0\0\0\0ÿÿÿÿ8\0\0\0€şÿÿÿÿÿ\0ÿÿ(\0\0\0ÿÿ\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0%\0\0\0ÿÿÿÿH\0\0\0\0\0ÿÿ\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0ß\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÊ\0\0\0ÿÿÿÿ\0\0\0ÿÿÿÿx\0\0\0ÿÿÿÿ\0\0ª°\0Attribut\0e VB_Nam\0e = \"Tab\0elle1\"\r\n\nøBas|0{0\00020820-;\0 C\0$0046}\r|Glob„alÈSpac’ FalsedCr@eatablP€redecla\0DId\0­Tru\rBE xposeTe\0mplateDeriv$’Bust0omizD2\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Ìaˆ\0\0\0ÿ\0\0	\0\0ä\0\0\0\0\0\0\0\0\0\0\0\0ú\0*\0\\\0G\0{\00\00\00\02\00\04\0E\0F\0-\00\00\00\00\0-\00\00\00\00\0-\0C\00\00\00\0-\00\00\00\00\00\00\00\00\00\00\04\06\0}\0#\04\0.\00\0#\09\0#\0C\0:\0\\\0P\0R\0O\0G\0R\0A\0~\01\0\\\0C\0O\0M\0M\0O\0N\0~\01\0\\\0M\0I\0C\0R\0O\0S\0~\01\0\\\0V\0B\0A\0\\\0V\0B\0A\06\0\\\0V\0B\0E\06\0.\0D\0L\0L\0#\0V\0i\0s\0u\0a\0l\0 \0B\0a\0s\0i\0c\0 \0F\0o\0r\0 \0A\0p\0p\0l\0i\0c\0a\0t\0i\0o\0n\0s\0\0\0\0\0\0\0\0\0\0\0\0\0*\0\\\0G\0{\00\00\00\02\00\08\01\03\0-\00\00\00\00\0-\00\00\00\00\0-\0C\00\00\00\0-\00\00\00\00\00\00\00\00\00\00\04\06\0}\0#\01\0.\06\0#\00\0#\0C\0:\0\\\0P\0r\0o\0g\0r\0a\0m\0 \0F\0i\0l\0e\0s\0\\\0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \0O\0f\0f\0i\0c\0e\0\\\0O\0f\0f\0i\0c\0e\01\02\0\\\0E\0X\0C\0E\0L\0.\0E\0X\0E\0#\0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \0E\0x\0c\0e\0l\0 \01\02\0.\00\0 \0O\0b\0j\0e\0c\0t\0 \0L\0i\0b\0r\0a\0r\0y\0\0\0\0\0\0\0\0\0\0\0\0\0¸\0*\0\\\0G\0{\00\00\00\02\00\04\03\00\0-\00\00\00\00\0-\00\00\00\00\0-\0C\00\00\00\0-\00\00\00\00\00\00\00\00\00\00\04\06\0}\0#\02\0.\00\0#\00\0#\0C\0:\0\\\0W\0I\0N\0N\0T\0\\\0s\0y\0s\0t\0e\0m\03\02\0\\\0s\0t\0d\0o\0l\0e\02\0.\0t\0l\0b\0#\0O\0L\0E\0 \0A\0u\0t\0o\0m\0a\0t\0i\0o\0n\0\0\0\0\0\0\0\0\0\0\0\0\0(*\0\\\0G\0{\02\0D\0F\08\0D\00\04\0C\0-\05\0B\0F\0A\0-\01\00\01\0B\0-\0B\0D\0E\05\0-\00\00\0A\0A\00\00\04\04\0D\0E\05\02\0}\0#\02\0.\04\0#\00\0#\0C\0:\0\\\0P\0r\0o\0g\0r\0a\0m\0 \0F\0i\0l\0e\0s\0\\\0C\0o\0m\0m\0o\0n\0 \0F\0i\0l\0e\0s\0\\\0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \0S\0h\0a\0r\0e\0d\0\\\0O\0F\0F\0I\0C\0E\01\02\0\\\0M\0S\0O\0.\0D\0L\0L\0#\0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \0O\0f\0f\0i\0c\0e\0 \01\02\0.\00\0 \0O\0b\0j\0e\0c\0t\0 \0L\0i\0b\0r\0a\0r\0y\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\n\0ÿÿÿÿÿÿ\0\0\0\0ÿÿ\0\0àïGL\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0ÿÿ\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0áÛ\0\0M\0o\0d\0u\0l\01\0\00\0@\04\0c\04\07\0f\06\00\01\0ÿÿ\0M\0o\0d\0u\0l\01\0ÿÿÀ—\0\0\0\0\0\0\0\0\0\0»\0\0ÿÿ\"\0D\0i\0e\0s\0e\0A\0r\0b\0e\0i\0t\0s\0m\0a\0p\0p\0e\0\00\0>\04\0c\04\07\0f\06\00\01\0ÿÿ)\"\0D\0i\0e\0s\0e\0A\0r\0b\0e\0i\0t\0s\0m\0a\0p\0p\0e\0ÿÿ>/\0\0\0\0\0\0\0\0\0%\0\0ÿÿ\0T\0a\0b\0e\0l\0l\0e\01\0\00\0?\04\0c\04\07\0f\06\00\01\0ÿÿ+\0T\0a\0b\0e\0l\0l\0e\01\0ÿÿ‡§\0\0\0\0\0\00\0\0\0%\0\0ÿÿÿÿÿÿP\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿˆ&µMI£}SÏY¿ÿÿÿÿ\0\0\0×m:ÃÈìaA¯®u÷z/·Ûÿÿÿÿ\0\0\0y”è ùÅ©L‘àŠéš“\"ÿÿÿÿ\0\0\0ÿÿÿÿ0\0\0\0€\0\0\0\0\0\0ÿ\0\'\0\0excel€+\0VBA÷â\0Win16Á~\0Win32\0Mac³²\0VBA6­#\0\nVBAProject¾¿\0\0stdole“`\0\0Officeu\0Modul1Í\0	\0_EvaluateÙ\0Makro1C|\0\0Application¥*\0workbookk\0	worksheetÁş\0	\0WorkbooksBì\0\n\0Worksheets¼ø\0\0Columnsp9\0\0NumberFormatÕR\0\0Visible¶Ó\0DieseArbeitsmappe¯\0Tabelle1RŠ\0ÿÿ`\0\0\0\0\0ÿÿ\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿ)\0\0+\0	\0ÿÿÿÿ\0ÿÿ\0ÿÿ\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\06\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\08²€\0\0\0\0\00*	\0pH\0‚\0dä\0\n\0\0VBAProjeˆct\04\0\0@j\n=\nr	àïGL”\0J<\n\0r€stdole>\0s\0t\0d\0o\0€l\0e\0\r\0f\0%\\\0*\\G{00€020430-\0C\0\n004\06}#2.0#0\0#C:\\WINN\0T\\system32\\c2.tl\0b#OLE Au\0tomation\0^\0ƒDOffic„DO€>f\0i\0c5‚D€”€D2D\0F8D04C-5\0BFA-101B -BDE5€DAA5€B4€2ˆD€—gr\0am Files€\\Common\0Microsof\0t Shared\0\\OFFICE1\02\\MSO.DLLL#‡ƒM 1€p ObÀ LibrXary\0K\0‚ÓD\0‚áÛ‚­M@odul1G‚­M€«d\0u€­1\0\ZUˆ2N‚HB1µÂn»À„B,Â(À—!B+B\0ÀDieseA\0rbeitsmaÀppeG\0\"\0Re\0s€RA\0r\0\nbÀi\0zs\0m\0ha\0p@\0e\0$’2C¤O,%\0\0M,>R/#\0\0E,€&T@abelleÀHwA\0$\'lB ÁIÉ2f\0Ñã‡§ÉÁ\0\0\0\0\0\0\0“K*ˆ\0\0\0\0ÿÿ\0\0\0\0\0\0ÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0rU\0\0\0€\0\0\0€\0\0\0€\0\0\0\0\0~\0\0~\0\0~\0\0~\0\0~\0\0~o\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0É\0\0\0\0\0\0\0G*OÀK´³E·ğv\0	\0\0\0\0ä\0\0\0\0\0\0\0ÿÿÿÿ\0ˆ \0	\0\0\0\0\0\0™\0\0\0\0\0\0ÿÿÿÿp\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0ÿÿ\0\0é\0\0\0\0\0\0\0\n\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\n\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\01\0\0\0\0\0\0\0ñ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0)\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\n\0\0\0VBAProject\0\0\0\0\0Modul1\0\0\0\0\0DieseArbeitsmappe\0\0\0\0\0Tabelle1\0\0ï\0\0\0\0\0À\0\0\0\0\0\0F\0\0/\0\0\0C:\\PROGRA~1\\COMMON~1\\MICROS~1\\VBA\\VBA6\\VBE6.DLL\0\0\0\0\0VBA\n\0\0\nQ\0\0\0\0\0\0ÿÿÿÿ\0\0\0	\0\0\0y\0\0\0\0\0\0á\0\0\0\0\0\00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0À\0\0\0\0\0\0F\0\04\0\0\0C:\\Program Files\\Microsoft Office\\Office12\\EXCEL.EXE\0\0\0\0\0Excel\n\0\0\nI\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0q\0\0\0\0\0\0é\0\0\0\0\0\0@\0\0\0\0\0\0\0\0\0\0\0\0\0\0\00\0\0\0\0\0À\0\0\0\0\0\0F\0\0\0\0\0C:\\WINNT\\system32\\stdole2.tlb\0\0\0\0\0stdole\0\0\n\0\0\nY\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0É\0\0\0\0\0\0P\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0LĞø-ú[½å\0ª\0DŞR\0\0?\0\0\0C:\\Program Files\\Common Files\\Microsoft Shared\\OFFICE12\\MSO.DLL\0\0\0\0\0Office\n\0\0\na\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0‰\0\0\0\0\0\0\0\0\0\0\0\0`\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Makro1\0\0\r\0\0\0\0\0\0\0\0\0\0\0I\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0_\0_\0S\0R\0P\0_\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0€\0\0\0V\0\0\0\0\0\0\0P\0R\0O\0J\0E\0C\0T\0w\0m\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0‚\0\0\0h\0\0\0\0\0\0\0P\0R\0O\0J\0E\0C\0T\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0„\0\0\0ß\0\0\0\0\0\0\0S\0u\0m\0m\0a\0r\0y\0I\0n\0f\0o\0r\0m\0a\0t\0i\0o\0n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0(\0ÿÿÿÿ\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Œ\0\0\0Ğ\0\0\0\0\0\0\0\0\0\0şÿÿÿƒ\0\0\0şÿÿÿ…\0\0\0†\0\0\0‡\0\0\0ˆ\0\0\0‰\0\0\0Š\0\0\0‹\0\0\0şÿÿÿ\0\0\0\0\0\0\0\0\0şÿÿÿ‘\0\0\0’\0\0\0“\0\0\0”\0\0\0şÿÿÿ–\0\0\0şÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿrU€\0\0\0\0\0\0\0€\0\0\0€\0\0\0\0\0\0\0\n\0\0\0	\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0 \n14q\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Modul1\0M\0o\0d\0u\0l\01\0\0\0DieseArbeitsmappe\0D\0i\0e\0s\0e\0A\0r\0b\0e\0i\0t\0s\0m\0a\0p\0p\0e\0\0\0Tabelle1\0T\0a\0b\0e\0l\0l\0e\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ID=\"{FDB9242B-AC6C-495B-BDDA-380C088A74A8}\"\r\nModule=Modul1\r\nDocument=DieseArbeitsmappe/&H00000000\r\nDocument=Tabelle1/&H00000000\r\nName=\"VBAProject\"\r\nHelpContextID=\"0\"\r\nVersionCompatible32=\"393222000\"\r\nCMG=\"595B437147714771477147\"\r\nDPB=\"DBD9C1714272427242\"\r\nGC=\"5D5F47FB4B7C4C7C4C83\"\r\n\r\n[Host Extender Info]\r\n&H00000001={3832D640-CF90-11CF-8E43-00A0C911005A};VBE;&H00000000\r\n\r\n[Workspace]\r\nModul1=44, 44, 1218, 675, \r\nDieseArbeitsmappe=0, 0, 0, 0, C\r\nTabelle1=22, 22, 1196, 653, \r\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0à…ŸòùOh«‘\0+\'³Ù0\0\0\0 \0\0\0\0\0\0\0\0\0@\0\0\0\0\0\0H\0\0\0\0\0\0X\0\0\0\0\0\0h\0\0\0\0\0\0€\0\0\0\r\0\0\0Œ\0\0\0\0\0\0˜\0\0\0\0\0\0ä\0\0\0\0\0\0\0\0reich\0\0\0\0\0\0\0\0\0reich\0\0\0\0\0\0\0\0\0Microsoft Excel\0@\0\0\0\0xÄŸ=Ê@\0\0\0€~Ù¢=Ê\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0D\0o\0c\0u\0m\0e\0n\0t\0S\0u\0m\0m\0a\0r\0y\0I\0n\0f\0o\0r\0m\0a\0t\0i\0o\0n\0\0\0\0\0\0\0\0\0\0\08\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0C\0o\0m\0p\0O\0b\0j\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0•\0\0\0s\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÕÍÕœ.“—\0+,ù®0\0\0\0Ü\0\0\0	\0\0\0\0\0\0P\0\0\0\0\0\0X\0\0\0\0\0\0x\0\0\0\0\0\0€\0\0\0\0\0\0ˆ\0\0\0\0\0\0\0\0\0\0\0\0˜\0\0\0\r\0\0\0 \0\0\0\0\0\0µ\0\0\0\0\0\0ä\0\0\0\0\0\0\0\0Fritz EGGER GmbH & Co.\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0Tabelle1\0\0\0\0\0\0\0\0\0\0\0\0Arbeitsblätter\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÿ\n\0\0ÿÿÿÿ \0\0\0\0\0À\0\0\0\0\0\0F\'\0\0\0Microsoft Office Excel 2003-Arbeitsbl.\0\0\0\0Biff8\0\0\0\0Excel.Sheet.8\0ô9²q\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','2009-11-16 13:22:19','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

/*Table structure for table `competitions` */

DROP TABLE IF EXISTS `competitions`;

CREATE TABLE `competitions` (
  `id` varchar(36) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `country_key` varchar(2) DEFAULT NULL,
  `created_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz erstellt am',
  `created_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz erstellt von',
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geÃ¤ndert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geÃ¤ndert von',
  `test` tinyint(1) DEFAULT '0',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelÃ¶scht',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `competitions` */

insert  into `competitions`(`id`,`date`,`description`,`type`,`address`,`country_key`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('x96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-08-31','Podersdorf','tria','Moosbach 28\n6392 St. Jakob','at','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('63df5d72-3d22-411f-affa-3c974318e790','2009-11-12','Ironman KÃ¤rnten','tria','Klagenfurt','at','2009-11-11 22:29:53','e96bcbd2-676d-102c-ace2-9cc3fca64c87','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('528e52ef-15ad-469c-bde6-6bd593c74984','2009-11-11','Test','tria','Test','de','2009-11-11 22:45:16','e96bcbd2-676d-102c-ace2-9cc3fca64c87','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('0d1edae8-a48a-4aa2-a9db-fdebd1acc15e','2009-11-13','Test 2','tria',NULL,NULL,'2009-11-11 22:51:25','e96bcbd2-676d-102c-ace2-9cc3fca64c87','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('ba76369e-5c2b-4814-a1ca-6229a2a2fa1a','2009-11-21','Test 3','tria',NULL,NULL,'2009-11-11 22:51:46','e96bcbd2-676d-102c-ace2-9cc3fca64c87','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('30c5931c-cabb-42cf-9e55-5bfe663cefa0','2010-07-10','Weltcup KitzbÃ¼hel','tria','6300 KitzbÃ¼hel','at','2009-11-28 12:40:38','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 12:40:38','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('bb6116f8-fd51-4d05-8bfd-e0d86e228278','2009-11-29','Ironman South Africa','tria',NULL,'at','2009-11-28 17:16:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 17:16:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('5337cdba-b2ac-42f6-bcd9-bd7825db4284','2009-11-27','Buachlauf','tria',NULL,'at','2009-11-28 23:14:14','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 23:14:14','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('2e122f41-2121-4d16-94d6-f5dc328d84dc','2009-11-18','Sylvesterlauf','tria','Test','at','2009-11-28 23:26:32','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 23:26:32','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

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

/*Data for the table `competitions_scouts` */

insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('x96bcbd2-676d-102c-ace2-9cc3fca64c87','0b0b7658-2ddb-11de-86ae-00301bb60f17','[{\"category\":\"M20\",\"limits\":[5.0,15.0],\"run\":[\"Markus Reich\",\"01:01:55\"],\"swim\":[\"Thomas Mach\",\"00:32:17\"]},{\"category\":\"W20\",\"limits\":[9.0,20.0],\"run\":[\"Daniela Bucher\",\"00:25:00\"],\"swim\":[\"Kate Allen\",\"00:26:17\"]}]'),('63df5d72-3d22-411f-affa-3c974318e790','0b0b7658-2ddb-11de-86ae-00301bb60f17',''),('ba76369e-5c2b-4814-a1ca-6229a2a2fa1a','0b0b7658-2ddb-11de-86ae-00301bb60f17',''),('0d1edae8-a48a-4aa2-a9db-fdebd1acc15e','0b0b7658-2ddb-11de-86ae-00301bb60f17',''),('30c5931c-cabb-42cf-9e55-5bfe663cefa0','0b0b7658-2ddb-11de-86ae-00301bb60f17',''),('bb6116f8-fd51-4d05-8bfd-e0d86e228278','0b0b7658-2ddb-11de-86ae-00301bb60f17',''),('5337cdba-b2ac-42f6-bcd9-bd7825db4284','0b0b7658-2ddb-11de-86ae-00301bb60f17',''),('2e122f41-2121-4d16-94d6-f5dc328d84dc','0b0b7658-2ddb-11de-86ae-00301bb60f17','');

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
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geÃ¤ndert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geÃ¤ndert von',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelÃ¶scht',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_doctors_k_countries` (`country_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `doctors` */

insert  into `doctors`(`id`,`name`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`longitude`,`latitude`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('c94e3cff-495d-11de-921e-1178275b5596','Medizinalrat Dr. Helmut Schwitzer','Kirchweg','2','6391','Fieberbrunn','Tirol','at',NULL,NULL,'office@drschwitzer.at','http://www.drschwitzer.at','05354 / 56535',NULL,'05354 / 56535 - 75','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-05-27 23:05:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('d0782a5c-495d-11de-921e-1178275b5596','Dr. Michael Plattner','Dorf','39','6373','Jochberg','Tirol','at','12.4180161953','47.3754969916','michael.plattner@gmail.com',NULL,'05355 / 20071',NULL,NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-12-10 23:22:13','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('e941cc54-cceb-4a38-984c-f55b20c41e6a','Dr. Hannes Lechner',NULL,NULL,'6391','Fieberbrunn',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-09-10 18:58:28','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-05-27 23:05:40','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('2650b3dc-a509-49d1-94e6-56ce7d63e727','Dr. Mittermayer Paul',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-11-27 23:28:44','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

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

/*Data for the table `entities_have_labels` */

insert  into `entities_have_labels`(`entity`,`label`,`person_id`) values ('0c05017b-0f9f-4bc3-baeb-c1f8eb734120','d89401a3-d6cc-41dd-b7df-32823172d893','0b0b7658-2ddb-11de-86ae-00301bb60f17'),('42473dc1-e4f2-4408-910f-10b4e64a04c1','e22d73e4-83c3-4a21-8f5d-9d76cefb6761','0b0b7658-2ddb-11de-86ae-00301bb60f17'),('52ec1e3f-ec3e-4676-960e-f8a547b734aa','55620350-6d49-11de-a69b-604b59d93787','0b0b7658-2ddb-11de-86ae-00301bb60f17'),('b8a954e4-4bca-11de-ab35-74df036e1e4f','3418c962-818c-43ee-ad7a-5964fdd2eb6c','10f52302-2ddb-11de-86ae-00301bb60f17'),('bb6116f8-fd51-4d05-8bfd-e0d86e228278','d89401a3-d6cc-41dd-b7df-32823172d893','0b0b7658-2ddb-11de-86ae-00301bb60f17'),('x96bcbd2-676d-102c-ace2-9cc3fca64c87','e22d73e4-83c3-4a21-8f5d-9d76cefb6761','0b0b7658-2ddb-11de-86ae-00301bb60f17');

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

/*Table structure for table `k_comptypes` */

DROP TABLE IF EXISTS `k_comptypes`;

CREATE TABLE `k_comptypes` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Keys for type of competition';

/*Data for the table `k_comptypes` */

insert  into `k_comptypes`(`key`) values ('tria');

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

insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('masterdata',' ',' ',0,0,0),('users_all','ENTITYLIST','USER',1,1,1),('persons_all','ENTITYLIST','PERSON',1,1,1),('person_own','ENTITYDETAIL','PERSON',1,0,0),('coaches_own','ENTITYLIST','MYCOACHES',0,0,0),('athletes_own','ENTITYLIST','MYATHLETES',1,1,1),('relations','','',0,0,0),('relation_coach','RELATIONLIST','COACH',1,1,1),('relation_doctor','RELATIONLIST','DOCTOR',1,1,1),('doctors_all','ENTITYLIST','DOCTOR',1,1,1),('doctors_own','ENTITYLIST','MYDOCTORS',0,0,0),('attachments_all','ENTITYLIST','ATTACHMENT',1,1,1),('attachments_own','ENTITYLIST','MYATTACHMENTS',1,1,1),('relation_attachment','RELATIONLIST','ATTACHMENT',1,1,1),('tests_all','ENTITYLIST','TEST',1,1,1),('tests_own','ENTITYLIST','MYTESTS',0,0,0),('tests_coach','ENTITYLIST','COACHTESTS',1,1,1),('zones_coach','ZONESDEFINITION',' ',1,1,1),('zones_athlete','ZONESDETAIL',' ',0,0,0),('competitions_all','ENTITYLIST','COMPETITION',1,1,1),('competitions_own','ENTITYLIST','SCOUTCOMPETITIONS',1,1,1),('scouted_own','ENTITYLIST','MYSCOUTEDATHLETES',1,1,1),('results_scout','ENTITYLIST','SCOUTRESULTS',1,1,1),('relation_scout','RELATIONLIST','SCOUT',1,1,1),('results_all','ENTITYLIST','RESULT',1,1,1);

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

insert  into `k_reltyps`(`key`) values ('attachment'),('coach'),('comp'),('doctor'),('scout');

/*Table structure for table `k_roles` */

DROP TABLE IF EXISTS `k_roles`;

CREATE TABLE `k_roles` (
  `key` varchar(36) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Roles for authorization';

/*Data for the table `k_roles` */

insert  into `k_roles`(`key`) values ('admin'),('athlete'),('coach'),('scouter');

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

/*Table structure for table `k_testtypes` */

DROP TABLE IF EXISTS `k_testtypes`;

CREATE TABLE `k_testtypes` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_testtypes` */

insert  into `k_testtypes`(`key`) values ('ergo'),('swim'),('treadmill');

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

insert  into `labels`(`id`,`person_id`,`description`,`color`) values ('55620350-6d49-11de-a69b-604b59d93787','0b0b7658-2ddb-11de-86ae-00301bb60f17','Bestellung','#ff0000'),('e22d73e4-83c3-4a21-8f5d-9d76cefb6761','0b0b7658-2ddb-11de-86ae-00301bb60f17','Super Test','#cc99ff'),('3418c962-818c-43ee-ad7a-5964fdd2eb6c','10f52302-2ddb-11de-86ae-00301bb60f17','World','#FFFFFF'),('d89401a3-d6cc-41dd-b7df-32823172d893','0b0b7658-2ddb-11de-86ae-00301bb60f17','Klasse A','#009933');

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

insert  into `list_variants`(`list`,`entity`,`user_id`,`columns_sequence`,`columns_width`) values ('entitylist','ATTACHMENT','e96bcbd2-676d-102c-ace2-9cc3fca64c87','0;2;1;3;4;5;6','25;232;100;100;100;100;100'),('entitylist','SCOUTCOMPETITIONS','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'100;100;100;242;100'),('entitylist','COMPETITION','e96bcbd2-676d-102c-ace2-9cc3fca64c87','0;1;2;4;3;5','100;100;100;291;100'),('entitylist','MYRESULTS','e96bcbd2-676d-102c-ace2-9cc3fca64c87','1;0;2;3;4;5;7;6;8;9;10;11;12;13;14;15;16;17','100;193;100;100;100;100;100;100;100;100;100;100;100;100;100;100;100;100');

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
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geÃ¤ndert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geÃ¤ndert von',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelÃ¶scht',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_persons_k_countries` (`country_key`),
  KEY `fk_persons_k_sex` (`sex_key`),
  KEY `fk_persons_k_salutation` (`salutation_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `persons` */

insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('0b0b7658-2ddb-11de-86ae-00301bb60f17','mr','Markus','Reich','m','Moosbach','28/2','6393','St. Ulrich','Tirol','at','reich.markus@gmail.com','www.meex-rich.com','','0664/3453852',NULL,'1978-10-24 00:00:00',NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-09-14 20:45:41','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('10f52302-2ddb-11de-86ae-00301bb60f17','mrs','Daniela','Bucher','w','Moosbach','28/21','6392','St. Jakob','Tirol','at','dany.bucher@gmail.com','www.dany.at','05354/88462','0664/2844263','05354/88462-10','1983-05-16 00:00:00',NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-27 23:48:50','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('7522bc7f-42cf-415c-a050-da12518a4cd3','mr','Thomas','Mach','m','Dorf','2','6392','St. Jakob','Tirol','de','thomas.mach@egger.com',NULL,NULL,NULL,NULL,'1969-09-15 00:00:00','ÿØÿà\0JFIF\0\0H\0H\0\0ÿÛ\0C\0ÿÀ\0\0`\0`\0ÿÄ\0\0\0\0\0\0\0\0\0\0\0\0	\n\0ÿÄ\0H\0	\0\0\0!1\0AQq	\"a\n$%24‘#35¡Áğ&6BCD±ÑERber‚’ÂáÿÚ\0\0\0?\0»yt³~É/!öç†lòc—–Hã´ÊPwÃ‡æ<±^cøÿ\0wQåş£ı8ãQ&’Ai\"H/ÕË‚ßñ><ó·«!¾¼ôçèíÑır¢ß©Iµ¾^¨›mÓø·V6ÊRGX3\0`1¶1}NùC¹–itÎÂF’™#(µ*TÊk• íâD3cså“€ÀÆê?”#Ò\Z‚éïJt¨şSTEJ}M§ôAñ!d.¹øq‡cÅàô6öÏô{é\'52Ö¿jhôºò¨î”ÕJB²ÉV¯.ÎªHùŸæÂ	<â|qrƒ²™+µŒù3şíQNIräqçëğà¶¢Eæá¿qvgõlİÎÜTÀ\0,@¾[×sùm·s)^d™QnáŸñb7vÏŸæ8yû¤ŞÓ²Ë>íÉÿ\0œyòlğ?»Ñùïğwû^¿>OÇheubn¶²q’ÿ\0É~3¯í•ö«¦ô_¡¬Ğ½ ­£¬Õ…\nqãU4Õ‹EËu\0å¹;îxÆÛ©Ú©¯WtkWjúíÎ¶º¢ó>­F¾²²º(_­Öò ‚b÷N	Ë„jˆ¬I«­J¨]è*\"§ˆOOz…Ç¿8\0Ä’0aÃÁMJLÙSªdö$H/Ûˆˆ\0à€Å„åú¤ƒèüLUTA®”¬—]P£N^\nŠ¶@pa‹ªı IÙ~5ÓìOö§)ß\niõÙT×UÎ=×Kîª©ñC{mYŠqˆ\"	Ky‚Û^­±1ˆ\"ŠY‚8µ-£1‰ƒrÛ$ç|~Ï—/³¢ y’O[˜;\0Ãà>%ù—÷gQ9!ÿ\0æ?ş\\<n}?ˆã¿e:NÒ§ğÉß;Gæ1Ëˆ·Òç\\ú5è6¥ë\"ÜÑ\"’Ä´ë×5«¯)‡ÂS\0Á#í·[fÇŸn–èF¢tâÔ»Ë\\uifºe-×q(ª)ÖÂL1Ö¨G_ÕY\"#Õ„Œ…ıã,D\"â4·¡¦Œiä4s’ìÔÙêBœıd¤{ípq¸-7#ÏZ‰Ğ—Goi¥)Ø©¸á¾YHŞj›ÈÁ|5tëw³Æ\rJ¯Nê”d-SÓŠ ‹RUĞ¶BJ«7À‚9cbx¥û†‚½R±%N–©9F‚|TÕT•\"€Âwˆc\'İ9$Awı:¼–¬+½ê@¯ŸF®¥H£M>EGTõ¡#¬e$×¼M(ÄíŒ0 é_Ğ¤½7KŠúi«°ÔÉ¿9;À/JwÇÎÔ¶IV-@œ$¥®¶?kŒc‰ƒ¡*0HırÃ?¦@×\r¹<~şŸùêpëÓÒ4Ççû¹åœùÿ\0øy\r[Qûğ_øÿ\0§ÑùGıR\'DË?JÓ\'N…g[õvÓµÏw?|¡JXBïÌ±\0±5­¡v’n¡Û)D„D— Ó.–ªdí±ò@sÄµ ^¦”ój©²ïMô`	…òqéêNy…ŠrüÈ`Ÿ8§¼È%³Œ\\r~©p<\0pÙ]µt*éRF\"ÈrŞ@yc°q€p^Ÿúett¶¯ËuríIL“Gx¢ÑM¯ ›NÆ¶Z|A\ZZ±Ø¬OAú±bfŠÁT`‡†9q?Æ¡?æãd¿&—X‚­³®ú!UTH ªCÔtZ|ò%%UÜ¾~©æìñªå4dm–oÛ¿?\rùğ[İ~‘ÖÃoÏ_>nûc«Ë‡‹²é&Íö|¼¼¹ùeˆÎ8ÍgÊµÅÁC¦D„g_T©©§SI@“m?&cõP.I0\"´‘uF–Ê—IT»`^2Qkˆ­øhä$à¶Ab	/’NĞÌû-v×½i$ª#[$\'š’K³\0ç,0Åğ6sîõº¼5÷ŸH¥;qwÀ-{MQ.D U,­-\Z,ŸúXø–1$ˆÉŒëÎìLùÏİmºº9Ô¿vE¸Ók{®NêÈìß Å³#*ÇŠwêÔºº	ÒdÎ$wzÀæ\'lÃÉ˜ä3 h\'¤ƒ/NµzéE¦ı[PË¸ªÅ2|°K\0H˜&;\0€Ãn.äçŞ%¦Íe¹S:e$µí+½çÊ\"(á•\n¥Ã„·mx 9‹¬1ãv“%ûû·wÜÜ6[Ï<:òÿ\0\\=aÿ\08x;‡õ_—ÿ\0<Q/¶’ÃT\\²´ìO¦Ÿ8ÚZğ…‰¦À¤AUM»RU\0û§	Mœ¶îq‹¯¢½£¨s$©jµùy×K§Y‰L›¢ZT‚8H‰1*\\Ø#–’&„8\"Ç	B~4²é©³d^_6+ÔêÑM=5EM7qU&;ÄuH|gsv•.‘N™\Z–rY£XIZñ9\nE92º…Y¶ñd•wn\\’ì²ÅöŸDı	´nz‹æÔN_A½ÔWÔ.5åš{å¡¡T«WW™5‚[\"‹ÚÌ/ æ&\0á[«µh´Ôr{¬²+‹\n†pK¿\'%À19Á\'â¼ú=ÚšÇ¬’•î$e:¹È)”\nTôÉõ\0ÑU×Hª€¬ğ»\"³‰½$Ù>Ñ]Ôû].Jlë³çm-ÇOMş/ú4®U<Y(l±o­$€ÌÂ€]€k\" \"X;ˆ@>¢\0îÿ\0áÃ¥IúÙ_ÏöO\nÏºOåËâ><B^ŸVi»ú.êí--)ŸZ“iÅtHÜÿ\0U‡‹aˆÈD…Tû¹ËxñšÛrÚHğ2©_M\"a>ÄçöŸÉ\'ç†\rjÈ–„²™6¡fuÍâCöuP4&¿Äœy¶CG»ÖòsÃ©£¾0”¼§*r”ôdÅ\n_£§-\\*ëÃìàù›˜‰bŸSl/Ûj2ª©|6àIú*Šu1úÀ¶	Î\'Ë3hw©ÔRÂug÷ÌAbí]}‹uK¶är&ú:¦V/Ï¿êi+d¦)HD¡¥§P¨¦ï¦3¹ÃJ9\"fØ·EìôÒ:…ÍF¹µÉ{¾V£[ezØ°””‡~ñZõB÷UÍ	g%ÉA-\0NÏ‹‡­ıPÿ\0¸p‡6WŞ¬üğwÚ~ŒùùrwçûÏ§çÂP-º[¶Ã¼­ËµùÉj/ œø¢j²IpNï—ˆà±ã%VêUÃaÜ6u]WrS‘L •QUş6½)ÜC#’waÄdÑ:{>®‘JUÑ¨V~œÜjWBŠ%ÇmÜµÕ¿UõUG#úÀÙ.³³0÷Ì»b\ZÂÒÔ„‹Æàï•99Á¹{_ìŸ÷¨\\`ä*çbåò8{lQSæ¢’õÇKÜÔ…Mu\'è*5iìQË‡0a!ƒâ=ë\r-2u&vÓ§§\nî…ÁıIß™áÕöté½«:Ãáw$š»}ºûZNb<T¥øBRJa,	eU‰Ã/cNèé6õ„´¤ú$ÚJ\nj\njtôÚaCCGA–ğ´±ŸC€aÎì82P›ÚÆ<Ï§2<¾#;pãµş?åÇh¦vdD66 Pä¢§ö\\yå›ÈoL3»g8]<ômO£Ö·)^è4½†k=Ez¢mM;\n4«¬¬«m\0sÕeë_fñŒósˆ•\n-MdÚS wêˆj¾ì+›İÀ%‰bùßlóü£\\·«’èh»¿e>G*tãC¸waóÈm\r™i:î¡”›áræÄoÕ9¥räü\0y9`IâêµİÛtÉ·-Ù]´éõ=ÔT¿Üö$ã ß?-Ù\"“IK©ºİQÙ†I³,$\n}ˆu[‘Y_æS<„AÜä‹×›TDD‘lóŸºó;ñÖtl®ÙË~òIÏ™|qÆ9ñÇ—ğşüùp¿“V\'Mˆ8“ˆyïøùä6ùåÀÉÓX‰Ï\"\'9ÛÅßÈñõ.ß¿çøg?~Ù.’2©n½è£KK&H»u‰mj¤uki>j)JÖLJÏÅ•¼‰H »7Åjjâœ¯:ØÔIS“”¤¥R)½ş…V€\rÎwÁÆC`m—aW^mšêx~g ,¬ÖŠo£\nZnãB<İöw99s³ğßÍQ¾—%ÕÍT¯ğÙ3€ïÉ£Ğr`H€Ë+Å…2µuRWÓ\'°§5%À Ë8pØÆÄ}Öİ:xX½:SI¶u&¥jM›¬ê\nb’Š-/~®·®Ô›™Y\"ÕRVJÀ(ãÅ•>t8aêí\ZÈ¤gKí²î]Ááƒìíû€ã´é½”1Ä\\Ã½ú·?/Ç€ü,iÄ3&“*9ÓÎî6#PÆàgXğƒ¾u¯HôÂG{ÔMPÓ»\nIûÍMéyÛHØo®WŠòçÈˆ3ªŞ×gÆ“¢Ö©¨t‹³oŠÉ©íİ1ñ;İnª¿\'ÃY øÈ¸n7;oŒhO…ş–ı&Ûº%]€š‚‹AgXhäÖÖ¤Úi*k\nä*ª·í‹ƒÅÕ\rÒy72ì£±:Vif¬¦Ûèúœ$Ù×ºuE-2P\rX8ÂR«:6\"ê¹ê¶ç¬	\"ßô·OlEäÚª*³¼S©M¨ïÔ5g /x˜ùétÙé\n]•*ŠM;ıæ	Ãœ˜¢Ø;8Á`	\'1£XúSè¥œ™>’¯Qm™ÓèZ˜§Ûª~7\\=ìE\nR>Í€ólqLš¥¬–î¡k\r¿¨6å\"ŸcmT øqZz÷áJ~.Z&İ€,Ü\0xôèŸÒƒL:UéJ6§é…ÑB±&}5-ÔŠ*™jÈ»<7ë[jêJİ!\\;s7ôHRf)¦k<=Sùw\'àÁ¶øñõÊˆ¾Ì$ü}À?2şñçO¬^Ó~›šİ2lÛÛ¤6¢š)À‰n\\Ÿ1ĞèÇÕLéV—ÍÒÌ“€Øqİ‹‹ÕjÊ¢šhv©Q¨ïd³õA\0ÀrfEu\\rev¸jV$ÄÀ4A€-³C“ÕÈİ°	}m|È‹	§ ¹g$³îsÁÃ‚9Í41b?öÿ\0LxUÛ·î¤Yó\'SY—åámJ´WZš\'öTÂJÌ Ä=÷\"\"bZ q…P»¯™Ÿ[İ×J”ãQô€¡pªW\Zé~ì&#8$¸ÂpAëSKk´$Eâ<ñ—?ƒ¾ß‰…%@·‡é ’¸\0€ì9óg Xñ(º>tµÖî‹·©Ô\rÔ%û\näş£==«h®\ZÖdË¥%`„5„WÏô>©#\rñh—Ê@Ôz¨¨5ÏClëúîÕ7(©ÙuÛ¥+‹‰›Ö¶üÆü]ƒû]z\rk¸‘KO«ÔzkpOÚİÖ\ncdWïâ¾õ°}n\\·«ÿÙ','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c89',0,0),('514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4','mr','Wolfgang','Baumgartner',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-11-16 13:19:11','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('aa37127b-0622-483d-89c8-fe378fe63a0d','mr','Marcel','Diechtler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-11-19 22:50:37','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('5b7265da-114c-467a-86fd-a8f2e5618f53','mr','Mario','Seibl',NULL,NULL,NULL,'6392',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1978-11-12 00:00:00',NULL,'2009-11-27 23:49:49','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-27 23:58:45','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('9f17b17c-594c-414e-bc6b-bd59e50c3cc0','mrs','Sabrina','von der Weide',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-11-28 18:06:24','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 18:06:24','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

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

/*Data for the table `persons_athlete` */

insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('0b0b7658-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,NULL,NULL,42,NULL),('10f52302-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,NULL,200,NULL,NULL),('7522bc7f-42cf-415c-a050-da12518a4cd3',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('9f17b17c-594c-414e-bc6b-bd59e50c3cc0',NULL,NULL,NULL,NULL,220,NULL,NULL);

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

insert  into `persons_have_attachments`(`id`,`person`,`attachment`,`reltyp_key`,`standard`) values ('26b01e83-5d8e-497d-b5a5-8497be4f50da','7522bc7f-42cf-415c-a050-da12518a4cd3','0c05017b-0f9f-4bc3-baeb-c1f8eb734120','attachment',0),('2dc325bb-1b17-4596-b733-d359398eceaa','10f52302-2ddb-11de-86ae-00301bb60f17','0c05017b-0f9f-4bc3-baeb-c1f8eb734120','attachment',0),('dbed9fd4-9ef3-4f3c-a8e1-dbd3ea3cc716','9f17b17c-594c-414e-bc6b-bd59e50c3cc0','0c05017b-0f9f-4bc3-baeb-c1f8eb734120','attachment',0);

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

/*Data for the table `persons_have_competitions` */

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

insert  into `persons_have_doctors`(`id`,`person`,`doctor`,`reltyp_key`,`standard`) values ('f12d0178-496f-11de-921e-1178275b5596','10f52302-2ddb-11de-86ae-00301bb60f17','c94e3cff-495d-11de-921e-1178275b5596','doctor',0),('f73f73b4-496f-11de-921e-1178275b5596','0b0b7658-2ddb-11de-86ae-00301bb60f17','c94e3cff-495d-11de-921e-1178275b5596','doctor',0),('8e505f79-8d0e-4617-bdd9-8cba09a2ab5f','10f52302-2ddb-11de-86ae-00301bb60f17','d0782a5c-495d-11de-921e-1178275b5596','doctor',1);

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

insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('e3572a08-8c2d-102c-a1cd-29e813a50118','10f52302-2ddb-11de-86ae-00301bb60f17','0b0b7658-2ddb-11de-86ae-00301bb60f17','coach',0),('57f2f725-e3ee-496a-9d83-cd1f861b7dcb','0b0b7658-2ddb-11de-86ae-00301bb60f17','7522bc7f-42cf-415c-a050-da12518a4cd3','coach',0),('541a619b-f363-45e7-9999-51cdfb3ee784','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','coach',1),('287f5224-708b-4788-8b70-27c4b2268021','10f52302-2ddb-11de-86ae-00301bb60f17','0b0b7658-2ddb-11de-86ae-00301bb60f17','scout',0),('c3ee4964-02f0-4860-9fe0-78462929f733','514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4','0b0b7658-2ddb-11de-86ae-00301bb60f17','coach',0);

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
  UNIQUE KEY `uk_competition_scout_athlete` (`competition_id`,`scout_id`,`athlete_id`),
  KEY `fk_results_competition` (`competition_id`),
  KEY `fk_results_scout` (`scout_id`),
  KEY `fk_results_athlet` (`athlete_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Result of a competition by an athlete';

/*Data for the table `results` */

insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('42473dc1-e4f2-4408-910f-10b4e64a04c1','x96bcbd2-676d-102c-ace2-9cc3fca64c87','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','DSQ','01:01:25','TEst','2009-11-10 12:18:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-10 12:18:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('7fbe2262-8b66-465b-8070-8a59bb3c2d50','63df5d72-3d22-411f-affa-3c974318e790','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,'2009-11-11 22:33:23','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-10 12:18:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0),('89c8328f-7a18-4b35-9c5f-8275ccf4f5f4','0d1edae8-a48a-4aa2-a9db-fdebd1acc15e','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,'2009-11-11 23:00:54','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-10 12:18:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0),('e0729fb3-ba68-46ca-badc-21a24187953c','ba76369e-5c2b-4814-a1ca-6229a2a2fa1a','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','5','01:01:30','FÃ¼nfter Platz','2009-11-15 22:45:25','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-10 12:18:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('14fcb1c9-b175-4026-a3ce-cbb84f431560','30c5931c-cabb-42cf-9e55-5bfe663cefa0','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','2','01:02:50',NULL,'2009-11-28 12:42:24','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 12:43:33','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('1d72ad99-b7cd-41fd-917c-99de4c408be8','bb6116f8-fd51-4d05-8bfd-e0d86e228278','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','3','01:00:00','Gutes Rennen','2009-11-28 17:19:20','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 17:21:19','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('43d43177-77a8-4a5a-ab27-8469b42be1a5','5337cdba-b2ac-42f6-bcd9-bd7825db4284','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,'2009-11-28 23:14:40','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 23:14:40','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('8d261ab5-113c-4050-bf0d-cc29a3e6dd8d','2e122f41-2121-4d16-94d6-f5dc328d84dc','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,NULL,'Test','2009-11-28 23:26:50','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 23:27:21','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

/*Table structure for table `results_tria` */

DROP TABLE IF EXISTS `results_tria`;

CREATE TABLE `results_tria` (
  `id` varchar(36) NOT NULL,
  `category` varchar(10) DEFAULT NULL,
  `swim_split` varchar(8) DEFAULT NULL,
  `run_split` varchar(8) DEFAULT NULL,
  `swim_position` varchar(5) DEFAULT NULL,
  `run_position` varchar(5) DEFAULT NULL,
  `swimsuit` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Triathlon specific result data';

/*Data for the table `results_tria` */

insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`swim_position`,`run_position`,`swimsuit`) values ('42473dc1-e4f2-4408-910f-10b4e64a04c1','W20','00:35:00','00:35:00','20','3',1),('7fbe2262-8b66-465b-8070-8a59bb3c2d50','W20','00:30:00','00:30:00',NULL,NULL,0),('89c8328f-7a18-4b35-9c5f-8275ccf4f5f4','W40','01:00:00','00:45:00',NULL,NULL,0),('e0729fb3-ba68-46ca-badc-21a24187953c','W20','00:30:00','00:29:00',NULL,NULL,1),('14fcb1c9-b175-4026-a3ce-cbb84f431560','W20','00:30:15','00:32:12','2','5',1),('1d72ad99-b7cd-41fd-917c-99de4c408be8','Pro W','00:32:00','00:25:00','5','2',0),('43d43177-77a8-4a5a-ab27-8469b42be1a5','W20','01:00:00','01:00:00',NULL,NULL,0),('8d261ab5-113c-4050-bf0d-cc29a3e6dd8d','Pro W','01:00:00','00:30:00',NULL,NULL,0);

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

insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','masterdata',1,0,1),('admin','users_all',2,1,1),('admin','persons_all',3,1,2),('athlete','person_own',1,0,1),('athlete','coaches_own',2,0,2),('coach','person_own',1,0,1),('coach','athletes_own',2,0,2),('admin','relations',4,0,2),('admin','relation_coach',5,4,1),('admin','doctors_all',6,1,3),('admin','relation_doctor',7,4,2),('athlete','doctors_own',3,0,3),('coach','doctors_own',3,0,3),('admin','attachments_all',7,1,4),('athlete','attachments_own',4,0,4),('coach','attachments_own',4,0,4),('admin','relation_attachment',8,4,3),('admin','tests_all',9,1,5),('athlete','tests_own',5,0,5),('coach','tests_coach',5,0,5),('coach','zones_coach',6,0,6),('athlete','zones_athlete',6,0,6),('admin','competitions_all',10,1,6),('scouter','competitions_all',1,0,1),('scouter','competitions_own',2,0,2),('scouter','scouted_own',3,0,3),('scouter','results_scout',4,0,4),('admin','relation_scout',11,4,4),('admin','results_all',12,1,7);

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

/*Data for the table `t_comptypes` */

insert  into `t_comptypes`(`key`,`language_key`,`description`,`description_long`) values ('tria','de','Triathlon','Triathlon'),('tria','en','Triatlon','Triathlon');

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

insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('at','de','Ã–sterreich','Ã–sterreich'),('de','de','Deutschland','Deutschland'),('at','en','Austria','Austria'),('de','en','Germany','Germany');

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

insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('persons_all','de','Personen','Personen'),('persons_all','en','Persons','Persons'),('users_all','de','Benutzer','Benutzer'),('users_all','en','Users','Users'),('masterdata','de','Stammdaten','Stammdaten'),('masterdata','en','Masterdata','Masterdata'),('person_own','de','Eigene Person','Eigene Person'),('person_own','en','My person','My person'),('coaches_own','de','Meine Trainer','Meine Trainer'),('coaches_own','en','My Coaches','My Coaches'),('athletes_own','de','Meine Athleten','Meine Athleten'),('athletes_own','en','My Athletes','My Athletes'),('relations','de','Beziehungen','Beziehungen'),('relations','en','Relationships','Relationships'),('relation_coach','de','Trainer','Trainer'),('relation_coach','en','Coaches','Coaches'),('relation_doctor','de','Ã„rzte','Ã„rzte'),('relation_doctor','en','Doctors','Doctors'),('doctors_all','de','Ã„rzte','Ã„rzte'),('doctors_all','en','Doctors','Doctors'),('doctors_own','de','Meine Ã„rzte','Meine Ã„rzte'),('doctors_own','en','My Doctors','My Doctors'),('attachments_all','de','AnhÃ¤nge','AnhÃ¤nge'),('attachments_all','en','Attachments','Attachments'),('attachments_own','de','Meine AnhÃ¤nge','Meine AnhÃ¤nge'),('attachments_own','en','My Attachments','My Attachments'),('relation_attachment','de','AnhÃ¤nge','AnhÃ¤nge'),('relation_attachment','en','Attachments','Attachments'),('tests_all','de','Tests','Tests'),('tests_own','de','Meine Tests','Meine Tests'),('tests_coach','de','Meine Tests','Meine Tests'),('zones_coach','de','Trainingsbereiche','Trainingsbereiche'),('zones_coach','en','Excercise Zones','Exercise Zones'),('zones_athlete','de','Trainingsbereiche','Trainingsbereiche'),('zones_athlete','en','My Zones','My Zones'),('competitions_all','de','WettkÃ¤mpfe','WettkÃ¤mpfe'),('competitions_all','en','Competitions','Competitions'),('competitions_own','de','Meine WettkÃ¤mpfe','Meine WettkÃ¤mpfe'),('competitions_own','en','My Competitions','My Competitions'),('scouted_own','de','Meine Athleten','Meine Athleten'),('scouted_own','en','My Athletes','My Athletes'),('results_scout','de','Ergebnisse','Ergebnisse'),('results_scout','en','Results','Results'),('relation_scout','de','Scouter','Scouter'),('relation_scout','en','Scout','Scout'),('results_all','de','Ergebnisse','Ergebnisse'),('results_all','en','Results','Results'),('adminpanel','de','Admin Konsole','Admin Konsole');

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
  `relation_description` varchar(50) DEFAULT NULL,
  `relation_description_inverse` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`key`,`language_key`),
  KEY `fk_t_reltyps_k_languages` (`language_key`),
  KEY `fk_t_reltyps_k_reltyps` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `t_reltyps` */

insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('coach','de','Trainer','Trainer','hat den Trainer','ist Trainer von'),('coach','en','Coach','Coach','has the coach','is the coach of'),('doctor','de','Arzt','Arzt','hat den Arzt','ist Arzt von'),('doctor','en','Doctor','Doctor','has the doctor','is the doctor of'),('attachment','de','Anhang','Anhang','hat den Anhang','der Anhang gehÃ¶rt'),('attachment','en','Attachment','Attachment','has the attachment','the att. belongs to'),('scout','de','Scouter','Scouter','hat den Scouter','ist Scouter von'),('scout','en','Scouter','Scouter','has the scouter','is the scouter of'),('comp','de','Wettkampf','Wettkampf','hat den Wettkampf','definiert Wettkampf'),('comp','en','Competition','Competition','has the competition','defines competition');

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

insert  into `t_roles`(`key`,`language_key`,`description`,`description_long`) values ('admin','de','Administrator','Administrator'),('admin','en','Admin','Admin'),('coach','de','Trainer','Trainer'),('coach','en','Coach','Coach'),('athlete','de','Athlet','Athlet'),('athlete','en','Athlete','Athlete'),('scouter','de','Scouter','Scouter'),('scouter','en','Scoutmaster','Scoutmaster');

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

insert  into `t_sex`(`key`,`language_key`,`description`,`description_long`) values ('m','de','mÃ¤nnlich','mÃ¤nnlich'),('w','de','weiblich','weiblich'),('m','en','male','male'),('w','en','female','female');

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

/*Data for the table `t_testtypes` */

insert  into `t_testtypes`(`key`,`language_key`,`description`,`description_long`) values ('treadmill','de','Laufband','Laufband'),('ergo','de','Ergometer','Ergometer'),('swim','de','Schwimmen','Schwimmen');

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `speed` text,
  `heartrate` text,
  `lactate` text
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `test` */

insert  into `test`(`speed`,`heartrate`,`lactate`) values ('10,12','125,130','2.1,2.3');

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
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geÃ¤ndert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geÃ¤ndert von',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelÃ¶scht',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_tests_persons` (`person_id`),
  KEY `fk_tests_doctors` (`doctor_id`),
  KEY `fk_tests_coaches` (`coach_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Table contains tests definition';

/*Data for the table `tests` */

insert  into `tests`(`id`,`person_id`,`doctor_id`,`coach_id`,`type`,`date`,`description`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('a5119e53-025e-4648-892c-adf2612eea04','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-01 00:00:00','Das ist ein Test','2009-11-01 18:34:13','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-01 18:34:33','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0),('cfbc410c-cdd8-4f76-9d5a-65ccd301b738','7522bc7f-42cf-415c-a050-da12518a4cd3','d0782a5c-495d-11de-921e-1178275b5596','0b0b7658-2ddb-11de-86ae-00301bb60f17','ergo','2009-11-01 00:00:00','Test\nTest2','2009-11-01 18:44:24','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-01 18:46:05','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('52ec1e3f-ec3e-4676-960e-f8a547b734aa','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','swim','2009-11-01 00:00:00','Test Schwimmen','2009-11-01 19:06:15','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-01 19:07:46','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('492f1a63-b6d7-40a0-8948-4b1e9d2f879c','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-12 00:00:00','Test','2009-11-27 23:30:18','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('1cac8765-c4cc-495d-a1b9-bd90a359bea7','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-26 00:00:00',NULL,'2009-11-27 23:25:05','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('66d6e0ad-dd1a-4854-a4a7-f10a3fb5c3b8','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','ergo','2009-11-13 00:00:00','Test','2009-11-27 23:35:08','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('55dd2633-7422-4dd2-bc85-70227f931e0c','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-04 00:00:00',NULL,'2009-11-27 23:39:46','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('75f5b154-63ab-49b5-9a5d-1719a3fd3c86','10f52302-2ddb-11de-86ae-00301bb60f17','d0782a5c-495d-11de-921e-1178275b5596','0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-20 00:00:00','TEST','2009-11-27 23:50:33','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-30 19:31:20','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('71ecbf87-f779-40a3-9dc3-bb9383f14bfe','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-20 00:00:00','Test','2009-11-27 23:50:33','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-29 00:40:39','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('4178104d-71ba-41a9-a653-bd89e797d1be','9f17b17c-594c-414e-bc6b-bd59e50c3cc0',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-29 00:00:00','Test','2009-11-28 18:09:04','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-29 00:37:19','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

/*Table structure for table `tests_analysis` */

DROP TABLE IF EXISTS `tests_analysis`;

CREATE TABLE `tests_analysis` (
  `id` varchar(36) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tests_analysis_tests` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Analysis of testprotocol';

/*Data for the table `tests_analysis` */

insert  into `tests_analysis`(`id`,`created_at`,`created_by`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa','2009-11-02 20:49:08','e96bcbd2-676d-102c-ace2-9cc3fca64c87'),('cfbc410c-cdd8-4f76-9d5a-65ccd301b738','2009-11-02 21:51:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87'),('75f5b154-63ab-49b5-9a5d-1719a3fd3c86','2009-11-27 23:51:40','e96bcbd2-676d-102c-ace2-9cc3fca64c87'),('4178104d-71ba-41a9-a653-bd89e797d1be','2009-11-29 00:36:36','e96bcbd2-676d-102c-ace2-9cc3fca64c87');

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

/*Data for the table `tests_ergo` */

insert  into `tests_ergo`(`id`,`power_init`,`power_step`,`cadence_low`,`cadence_high`,`step_time`) values ('cfbc410c-cdd8-4f76-9d5a-65ccd301b738',80,20,80,90,'01:00'),('66d6e0ad-dd1a-4854-a4a7-f10a3fb5c3b8',400,20,80,90,'01:30');

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

/*Data for the table `tests_protocol` */

insert  into `tests_protocol`(`id`,`description`,`model`,`model_lactate`,`model_spiro`,`count_steps`,`time_last_step`,`performance_max`,`lactate`,`hr`,`o2_absorption`,`co2_emission`,`rq`,`created_at`,`created_by`) values ('cfbc410c-cdd8-4f76-9d5a-65ccd301b738','Alles Roger','Daum','A1','A2',5,'01:00',NULL,'{\"list\":[1.0,2.0,3.0,4.0,5.0]}','{\"list\":[100,120,145,175,200]}','{\"list\":[2,1,null,2,8]}','','','2009-11-01 18:44:43','e96bcbd2-676d-102c-ace2-9cc3fca64c87'),('52ec1e3f-ec3e-4676-960e-f8a547b734aa','Test',NULL,'A1',NULL,4,'00:00','03:30','','','','','','2009-11-01 19:06:51','e96bcbd2-676d-102c-ace2-9cc3fca64c87'),('6630713b-3a20-4d20-b738-de5c456b98d7',NULL,'Daum','Lactate','keine Spiro',0,'00:00',NULL,'','','','','','2009-11-27 23:19:30','e96bcbd2-676d-102c-ace2-9cc3fca64c87'),('75f5b154-63ab-49b5-9a5d-1719a3fd3c86','Test','Daum',NULL,NULL,5,'00:00',NULL,'{\"list\":[1.0,2.0,4.5,5.0,8.0]}','{\"list\":[100,110,120,140,180]}','{\"list\":[null,2,null,null,null]}','{\"list\":[1,null,3,null,null]}','{\"list\":[null,1.1,null,7.7,10.5]}','2009-11-27 23:50:53','e96bcbd2-676d-102c-ace2-9cc3fca64c87'),('4178104d-71ba-41a9-a653-bd89e797d1be',NULL,NULL,NULL,NULL,6,'00:00',NULL,'','','','','','2009-11-28 18:09:07','e96bcbd2-676d-102c-ace2-9cc3fca64c87'),('71ecbf87-f779-40a3-9dc3-bb9383f14bfe','Test',NULL,NULL,NULL,0,'00:00',NULL,'','','','','','2009-11-29 00:40:44','e96bcbd2-676d-102c-ace2-9cc3fca64c87');

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

/*Data for the table `tests_swim` */

insert  into `tests_swim`(`id`,`date2`,`assistant_name`,`baths`,`pool`,`distance`,`splits`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa','2009-11-02 00:00:00','Tester',NULL,NULL,200,4);

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

/*Data for the table `tests_swim_protocol` */

insert  into `tests_swim_protocol`(`id`,`step`,`attempt`,`intensity`,`time`,`valid`,`lactate`,`hr`,`splits`,`comment`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa',1,3,75,'06:00',0,'4','90','[{00:00;0},{00:00;0},{00:00;0},{00:00;0}]',NULL),('52ec1e3f-ec3e-4676-960e-f8a547b734aa',1,1,75,'05:20',1,'1@2 1,1@5',NULL,'[{01:30;20},{00:45;20},{00:00;0},{00:00;0}]','Super Intervall'),('52ec1e3f-ec3e-4676-960e-f8a547b734aa',1,2,75,'05:00',0,'2','100','[{00:00;0},{00:00;0},{00:00;0},{00:00;0}]',NULL),('52ec1e3f-ec3e-4676-960e-f8a547b734aa',2,1,70,'04:35',1,'2@5','100','[{00:00;0},{00:00;0},{00:00;0},{00:00;0}]',NULL),('52ec1e3f-ec3e-4676-960e-f8a547b734aa',3,1,80,'04:10',1,'2,8',NULL,'[{00:00;0},{00:00;0},{00:00;0},{00:00;0}]',NULL),('52ec1e3f-ec3e-4676-960e-f8a547b734aa',4,1,90,'03:50',1,'4,5',NULL,'[{00:00;0},{00:00;0},{00:00;0},{00:00;0}]',NULL);

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

/*Data for the table `tests_treadmill` */

insert  into `tests_treadmill`(`id`,`speed_variable`,`incline_variable`,`speed_init`,`speed_step`,`incline_init`,`incline_step`,`step_time`) values ('a5119e53-025e-4648-892c-adf2612eea04',0,0,NULL,NULL,NULL,NULL,NULL),('1cac8765-c4cc-495d-a1b9-bd90a359bea7',1,0,'10.0','2.0',0,NULL,'01:30'),('492f1a63-b6d7-40a0-8948-4b1e9d2f879c',1,0,'10.0','2.0',0,NULL,'01:30'),('55dd2633-7422-4dd2-bc85-70227f931e0c',1,0,'10.0','2.0',0,NULL,'01:30'),('75f5b154-63ab-49b5-9a5d-1719a3fd3c86',1,0,'10.0','2.0',0,NULL,'01:30'),('71ecbf87-f779-40a3-9dc3-bb9383f14bfe',1,0,'10.0','2.0',0,NULL,'01:30'),('4178104d-71ba-41a9-a653-bd89e797d1be',1,0,'10.0','2.0',0,NULL,'01:00');

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
  `last_login` datetime DEFAULT NULL,
  `last_login_ip` varchar(20) DEFAULT NULL,
  `created_at` datetime DEFAULT '1900-01-01 00:00:00',
  `created_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz erstellt von',
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geÃ¤ndert von',
  `deleted` tinyint(1) DEFAULT '0',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_uk` (`user_name`),
  KEY `fk_users_k_currencies` (`currency_key`),
  KEY `fk_users_k_languages` (`language_key`),
  KEY `fk_users_persons` (`person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='User data';

/*Data for the table `users` */

insert  into `users`(`id`,`user_name`,`user_hash`,`language_key`,`currency_key`,`locked`,`initial`,`active`,`person_id`,`email`,`last_login`,`last_login_ip`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c87','reich','test','de','eur',0,0,1,'0b0b7658-2ddb-11de-86ae-00301bb60f17','reich.markus@gmail.com','2010-01-13 21:29:33','10.17.7.247','1900-01-01 00:00:00','','2010-01-13 21:29:34','',0,0),('e96bcbd2-676d-102c-ace2-9cc3fca64c88','bucher','test','en','usd',0,0,1,'10f52302-2ddb-11de-86ae-00301bb60f17','dany.bucher@gmail.com',NULL,NULL,'1900-01-01 00:00:00','','2009-06-21 17:04:39','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('e96bcbd2-676d-102c-ace2-9cc3fca64c89','mach','test','de','eur',0,0,1,'7522bc7f-42cf-415c-a050-da12518a4cd3','mach.thomas@gmail.com','2009-11-28 17:36:47','169.254.1.10','1900-01-01 00:00:00','','2009-11-28 17:36:47','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','diechtler','f5d8476efe68','de',NULL,0,1,0,'aa37127b-0622-483d-89c8-fe378fe63a0d','marcel.diechtler@gmail.com',NULL,NULL,'2009-11-19 23:00:43','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 17:28:15','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0),('c841dff0-94ea-4415-b145-9fe4f6fec5fa','baumgartner',NULL,'de',NULL,0,0,0,'514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4','wolfgang@gmx.de',NULL,NULL,'2009-11-16 13:19:46','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);

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

insert  into `users_have_roles`(`user_id`,`role_key`) values ('1c203729-ac8a-424a-960a-85ed6dae03d7','admin'),('1c203729-ac8a-424a-960a-85ed6dae03d7','athlete'),('1c203729-ac8a-424a-960a-85ed6dae03d7','coach'),('c841dff0-94ea-4415-b145-9fe4f6fec5fa','athlete'),('e96bcbd2-676d-102c-ace2-9cc3fca64c87','admin'),('e96bcbd2-676d-102c-ace2-9cc3fca64c87','athlete'),('e96bcbd2-676d-102c-ace2-9cc3fca64c87','coach'),('e96bcbd2-676d-102c-ace2-9cc3fca64c87','scouter'),('e96bcbd2-676d-102c-ace2-9cc3fca64c88','coach'),('e96bcbd2-676d-102c-ace2-9cc3fca64c89','athlete'),('f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','coach'),('f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','scouter');

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

/*Data for the table `zones` */

insert  into `zones`(`id`,`athlete_id`,`zones_definition_id`,`hr_low`,`hr_high`,`speed_low`,`speed_high`,`auto_hr`,`auto_speed`,`test_id_hr`,`test_id_speed`) values ('85c32e06-0361-4a57-97ad-1a81e03a2752','10f52302-2ddb-11de-86ae-00301bb60f17','e154a52c-1e5e-43b1-bc6c-5ec89d0ea915',97,110,'1.39','1.39',1,1,NULL,'75f5b154-63ab-49b5-9a5d-1719a3fd3c86'),('85c32e06-0361-4a57-97ad-1a81e03a2753','10f52302-2ddb-11de-86ae-00301bb60f17','41ac38c4-4609-4e18-98a0-ec3b20f001de',110,123,'1.20','1.20',1,1,NULL,'75f5b154-63ab-49b5-9a5d-1719a3fd3c86'),('85c32e06-0361-4a57-97ad-1a81e03a2754','10f52302-2ddb-11de-86ae-00301bb60f17','396456df-eaa5-4e6a-a83c-9b9ac7659eb9',123,128,'1.16','1.16',1,1,NULL,'75f5b154-63ab-49b5-9a5d-1719a3fd3c86'),('a3a2e797-c542-49c5-9d6d-8ffb7ff2abc6','514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4','e154a52c-1e5e-43b1-bc6c-5ec89d0ea915',80,100,NULL,NULL,0,NULL,NULL,NULL),('0dc35223-ea4f-4760-851a-d1cbd6d5bf4d','10f52302-2ddb-11de-86ae-00301bb60f17','1e678437-441a-470e-b058-cca95cfea404',128,148,'1.03','1.03',1,1,NULL,'75f5b154-63ab-49b5-9a5d-1719a3fd3c86'),('41913cc4-595b-4ff9-9f0d-a14fcc95de63','9f17b17c-594c-414e-bc6b-bd59e50c3cc0','e154a52c-1e5e-43b1-bc6c-5ec89d0ea915',107,153,NULL,NULL,1,NULL,NULL,'4178104d-71ba-41a9-a653-bd89e797d1be'),('9b926319-1948-495d-bb44-53dff5f61a9b','9f17b17c-594c-414e-bc6b-bd59e50c3cc0','41ac38c4-4609-4e18-98a0-ec3b20f001de',153,190,NULL,NULL,1,NULL,NULL,'4178104d-71ba-41a9-a653-bd89e797d1be'),('cb192319-6f7a-437a-a236-826016d5785f','9f17b17c-594c-414e-bc6b-bd59e50c3cc0','396456df-eaa5-4e6a-a83c-9b9ac7659eb9',190,199,NULL,NULL,1,NULL,NULL,'4178104d-71ba-41a9-a653-bd89e797d1be'),('38338c2a-927b-4e02-a710-d07609d57a27','9f17b17c-594c-414e-bc6b-bd59e50c3cc0','1e678437-441a-470e-b058-cca95cfea404',199,226,NULL,NULL,1,NULL,NULL,'4178104d-71ba-41a9-a653-bd89e797d1be');

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

/*Data for the table `zones_definition` */

insert  into `zones_definition`(`id`,`coach_id`,`sequence`,`shortcut`,`description`,`color`,`lactate_low`,`lactate_high`,`hr_low`,`hr_high`) values ('e154a52c-1e5e-43b1-bc6c-5ec89d0ea915','0b0b7658-2ddb-11de-86ae-00301bb60f17',0,'GA 1','Grundlagen Ausdauer 1','#00cc66','1.00','2.00',0,50),('41ac38c4-4609-4e18-98a0-ec3b20f001de','0b0b7658-2ddb-11de-86ae-00301bb60f17',1,'GA 2','Grundlagen Ausdauer 2','#0000ff','2.00','3.50',51,70),('396456df-eaa5-4e6a-a83c-9b9ac7659eb9','0b0b7658-2ddb-11de-86ae-00301bb60f17',2,'EXT','Extensiver Bereich','#990099','3.50','4.00',71,90),('1e678437-441a-470e-b058-cca95cfea404','0b0b7658-2ddb-11de-86ae-00301bb60f17',3,'INT','Intensiver Bereich','#cc0000','4.00','6.00',91,100),('1679d3da-1705-43f2-8bc5-c4144a7a24b9','123456ABCDEFG',1,NULL,'Test',NULL,'10.00','12.10',NULL,NULL);

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

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `entities` AS select `users`.`id` AS `id`,'User' AS `entity`,`users`.`deleted` AS `deleted` from `users` union select `persons`.`id` AS `id`,'Person' AS `entity`,`persons`.`deleted` AS `deleted` from `persons` union select `attachments`.`id` AS `id`,'Attachment' AS `entity`,`attachments`.`deleted` AS `deleted` from `attachments` union select `doctors`.`id` AS `id`,'Doctor' AS `entity`,`doctors`.`deleted` AS `deleted` from `doctors` union select `tests`.`id` AS `id`,'Test' AS `entity`,`tests`.`deleted` AS `deleted` from `tests` union select `competitions`.`id` AS `id`,'Competition' AS `entity`,`competitions`.`deleted` AS `deleted` from `competitions` union select `results`.`id` AS `id`,'Result' AS `entity`,`results`.`deleted` AS `deleted` from `results` */;

/*View structure for view relations */

/*!50001 DROP TABLE IF EXISTS `relations` */;
/*!50001 DROP VIEW IF EXISTS `relations` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `relations` AS select `persons_have_attachments`.`id` AS `id`,`persons_have_attachments`.`person` AS `partner1`,`persons_have_attachments`.`attachment` AS `partner2`,`persons_have_attachments`.`reltyp_key` AS `reltyp_key` from `persons_have_attachments` union select `persons_have_doctors`.`id` AS `id`,`persons_have_doctors`.`person` AS `partner1`,`persons_have_doctors`.`doctor` AS `partner2`,`persons_have_doctors`.`reltyp_key` AS `reltyp_key` from `persons_have_doctors` union select `persons_have_relations`.`id` AS `id`,`persons_have_relations`.`partner1` AS `partner1`,`persons_have_relations`.`partner2` AS `partner2`,`persons_have_relations`.`reltyp_key` AS `reltyp_key` from `persons_have_relations` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
