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
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geÃ¤ndert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geÃ¤ndert von',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelÃ¶scht',
  `test` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_attachments_k_categories` (`category_key`),
  KEY `fk_attachments_persons` (`owner_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `attachments` */

insert  into `attachments`(`id`,`category_key`,`description`,`owner_id`,`intern`,`mime_type`,`file_name`,`file_size`,`file_content`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('b8a954e4-4bca-11de-ab35-74df036e1e4f','certificat','Attest Test','0b0b7658-2ddb-11de-86ae-00301bb60f17',0,'application/pdf','20080908.pdf',18806,'%PDF-1.4\n%âãÏÓ\n2 0 obj<</P 3 0 R/Type/Annot/F 132/T(Signature1)/V 1 0 R/Rect[0 0 0 0]/Subtype/Widget/FT/Sig>>\nendobj\n1 0 obj<</Filter/Adobe.PPKMS/Type/Sig/Contents <30820ebb06092a864886f70d010702a0820eac30820ea8020101310b300906052b0e03021a0500302306092a864886f70d010701a0160414a3939baa417b8f344e2be299336cd946bf5fcdeda0820d1d308204b33082039ba003020102020302f2f9300d06092a864886f70d010105050030819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033301e170d3037303232303135343535385a170d3132303232303135343535385a305b310b3009060355040613024154310e300c060355040a0c054241574147310b3009060355040b0c0249543118301606035504030c0f424157414720502e532e4b2e204147311530130603550405130c33343038393431313432383930819f300d06092a864886f70d010101050003818d00308189028181009f18e18acafeced47e87948327150690350f940b496d3d832b80568e1578be143898e395bd10d5863bab7400f0b3f7c41052e298169f96ae84f3931805bae5fb150b191a80cee84538a05cce009c4457f3b059e34f5d9668a7317ced1a8db2ea88079ce7685599fd678ebe8a41154bdb18ec239098f4ee063944d4142ccb585b0203010001a38201bd308201b930130603551d23040c300a80084191691cbfadd898305506082b0601050507010104493047304506082b060105050730028639687474703a2f2f7777772e612d74727573742e61742f63657274732f612d7369676e2d636f72706f726174652d6c696768742d30332e63727430580603551d200451304f304d06072a2800110107013042304006082b060105050702011634687474703a2f2f7777772e612d74727573742e61742f646f63732f63702f612d7369676e2d636f72706f726174652d6c6967687430819e0603551d1f048196308193308190a0818da0818a8681876c6461703a2f2f6c6461702e612d74727573742e61742f6f753d612d7369676e2d636f72706f726174652d6c696768742d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f7269747930110603551d0e040a0408487cf8373c3f8bb6300e0603551d0f0101ff0404030204b030220603551d11041b3019811769742d736963686572686569744062617761672e636f6d30090603551d1304023000300d06092a864886f70d01010505000382010100776e67497156b2bcb608d9de5dcf85efbaa18859128f1934e0ee4ecab2f94ccee454e8610e6be5b6c37eb49e4c8026363d107ce5b3e62bea1b4927cd9ed152d430a77976215fabbe75b27a8f452c3f1e8943bc28339dfc241c2a3fc66872919067c1420a7e010933947e324117d118bfa2cb732d845c80b3aecd37ee481323fe9166bdad16d619298ee502c5533254e8bdb1f90ab97c9a4c343deb368f3def13c4ba4d66fc4861f5af516cd6d94e1b5ebddf5e8eceb331a7fb2c9bfb0d210c0d8639f81ffd9d2cbf50e50ba200828415e8dabad043cbfd95355915ab7f3542ce80c6d90b5d1b72d1d03c9370e7c9f90909b76f870ebdf248b918da2e705259d73082048f30820377a003020102020301aaed300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035313131333233303030305a170d3135313131333233303030305a30819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100b7e7da22b5b1e490481d12b44f78176471844ad832ddc5f7279fe488ba6484633595b1c4537213ca4b36fb7f56ba1073db079599b0625a5035fd29f4eab0934ca3eedb0122a4de0b0b571359a98dadd86fb2f07bd111861e8c3388ce302470142bb7e9efb8152519854942312ec809f5f1fdaf1b463754dbd91462a73caf00572714324d14c36d516cadef5f9b5b6cf810fe471990de1cce2e551dea4cbdfea1030c0ba098fe28bf73c66f23c8f2bcf2a0812808cc342a592ac121c9a9f0d428d86d5987b38e50e56e8506da83712e88ab8982e93b543c6b424eea07eff24bba1129d3be68869f3bbf77d297378b2630699140cff54f13bfb9767ae0f4b755150203010001a381e33081e0300f0603551d130101ff040530030101ff30110603551d0e040a04084191691cbfadd89830130603551d23040c300a8008446a95675579114f300e0603551d0f0101ff0404030201063081940603551d1f04818c308189308186a08183a08180867e6c6461703a2f2f6c6461702e612d74727573742e61742f6f753d412d54727573742d6e5175616c2d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f72697479300d06092a864886f70d010105050003820101000d3448690b4fc283d2ebf4e9c8184ec38c001e4fd13388242d3efa5113d8dac5078a1c6acbef2a10494df9fe6523ad66c823f72054c295f95c1d5475402a0c834648e7f11c41323c0e0779890917b958e1df00a3f3b29ad93bbb7752be8bd549e0147fa61fea98f291ecbc7f3ed013c3fbd6a38936adbb79a354308b167b08fb7e045f7109a002e2e2f46c1d0cf4cde2f56d9e3e6a97e96201de33244fd1500b42a284f3dfc3fd7b29d74d8b8ac530c1224f22af253b53a839c80305964eab0bd46ee8c4c32d9e761a296310ea04223a2473098ede7dc022ff7630517e942d6e73c6a6a936cd6fa846d21ac40497578597aec70591d800f7681c6527645d0a69308203cf308202b7a0030201020203016c1e300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035303831373232303030305a170d3135303831373232303030305a30818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100ad3d616e03f3903bc0410b8480cdec2aa39d6bbb6ec24284f75114e1a0a82d51a351f2de23f03444ff94ebcc05239540b90778a525f60abd4586e8d9bdc0048e854461ef7fa7c9fac125cc852c633f0560734905e0607895104bdcf91159ce717f409b8aaa24df0b42e2db56bc4ad2a50c9bb7433edd83d3261002cfea23c4494ee5d3e9b488ab0cae6292d46587d96ad7f4859fe4332225a5e5c833bac3c741dc5fc66acc000e6d32a8b687360062779b1e1f34cb903c78887405eb79f5937165ca9dc76b182d3d5c4ee7d5f83f317d8f87ec0a222f23e9febb7dc9e0f4eceb7cc4b0c32d62b59a71d6b16ae8ecd9edd572ecbe5701ce05559fded1608810b30203010001a3363034300f0603551d130101ff040530030101ff30110603551d0e040a0408446a95675579114f300e0603551d0f0101ff040403020106300d06092a864886f70d0101050500038201010055d454d159485cb39385aabf632fe480ce34a334623ef6d8ee67883104036f0bd407fb4e750fd32ed3c017c7c628ec060d11240e0ea55dbf8cb2139671dcd4ce0e0d0a68326cb9413119abb1077b4d98d35cb0d1f0a742a0b5c48eaffef13ff4ef4f460076eb02fbf99dd24096c7883ab89f1179f38065a8bd1fd37881a0514c37b4a65d2570d166c968f92e111468f1549808ac26920fde899ed4fab3792bd2a379d4ec8bac875368424c5151741e1b272ee3f51f29744dedaff7e1929981e8be3ac71750f6b7c6fc9bb08a6bd68803918f06773a8502dd98d543783fc63015ac9b6bcb57b789518b3ae8c9840cdbb150200a1a4aba6a1abdec1bc8c5849acd3182014e3082014a0201013081a730819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033020302f2f9300906052b0e03021a0500300d06092a864886f70d01010105000481806452bd278af0c176438b49ad48756ef60aa7d0badffe78407ee18e1b9dbab2036362b5314ac3dfe6b2a29164063ae49d6346fd126c4f8784c363231d9987565076313d509b3321bf733910119405ebb8903601f87e721a425ebc2075284692f6706b7aecadf8f6a2f4f0c4cb9d17aaf87ce597e78e5b7aef4807be6de1d9fa3800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000>/M(D:20080923065445+02\'00\')/Name(BAWAG P.S.K. AG)/SubFilter/adbe.pkcs7.sha1/ByteRange [0 164 7844 10962 ]                                                             >>\nendobj\n4 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1530/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛRÛH}÷Wèq·Êæªí›IHŠbI8qÕ¾	<­m™Õ…İâC÷òyØdK¶Ç\ZK„|NO_NwËƒ¿aø\npç¥¾È\"$Í+\r	Šür·Ú¹Ô}CîV!âà’ôĞí\'ç—«ÇÙï×ƒ/ƒÁqë8FÜ Pª~Æ:Á\nÕŞ$¢o¯­#µu§“ÁÉk.©n\n&óÁ	L›B™Y×†HTï]\r~¹øüiòyüõæ¯\r&˜y—;›8ø¢QÕKÇhXó¶é¢c$$7àÒËÁ	CJTÒ^1§Ë¸(Fµxíça…{<Ü÷,\nE‘‡B\nïr<­Ó·=‰`6Ó~î$ù~¥­ÀNäŸÖ«7=‰`E{,ØÔ¥9¡òó¨!QT%€ˆˆ¦vÖi±NËÕJg‡ÙÌ…°ÿ(Œ³†¦…>ış•éıadˆã/¨6ğïºx.tŒËü¹tã{.è!ÃÇKƒ^9\'/âtvÄ7¬ß7M?€¤›\n/ïĞh»ŒW½È\\(!ƒÓóBÿã‚9âÒDE]hmø©Î^ L¬?”GˆW§€R›*ÀXÒ(ğˆ2â0÷q:¼öõÚê§A¤iD›AÔ‘ÊõTNêŒoc¥phœ\',L#û~FnPîQöMjsdS<×:¹{p¢âşÄK¥ØE½Œ³E™;aÃşöDÃÈ„ÜfœÁ=p(” öÈ^…arÙ}_fwùİCšEÇËÙÚ­ 4ôÈaeµ»C2ÿá²<DRöƒ†Ø6è,vûØ#Ë˜İeN;±‡Š pòKgE¼t\0/}Ã‡¼z«‹ìû½Câ\0XôË2gÜöõğ£«ÅzJäÀ¾gû†?ä*:€ıJøÁ0»Ebˆ±Ó¿²¿¶…,¸m€u!k*÷«dn\'äj¼(J½\\ºCĞÔş&ÔÊdù,Iq9Ï3ëìÉEà•ÉLâÚÕm‚ã™ì×<£zêZşÚæÉëºkƒVSƒ;1Bx‹‡¹Ä*„…Ö!ùÆŒ·¸û·ãõK§÷¦OwÈ(¢ümútv(\Zº?tqŸUm[BTZu­FG,ã{ıÆbŒÔ…ÕærúĞé•¨MäÛ6ò°$ü_‘o“1$¨+{¥OÛØF¾\r;ŒèÈ×U0ıÕ6ô$´|†æ“.;Oğ\\FQ»»™¸<\"{­uT›êc™ÂrâökX˜Ö·ºôê8·Y ™éëüMœÛ°C)<ÃL0L!ı>ÚÄ9bvB­+<¸şq·˜g¦ÎefX‡ßn@\\à6ß·-Îê5µmÈü<W_- f½êŸ7\'Wá†ğf=×™=g\nG.‚Û$Máàa0]W˜é•Íÿ¸z¨Àø%óbé\\fLØ¤V†ÛãÿÌt–?fÚPƒ8ÕÙBWög	Ì)zÕÎ°¡Q+üçï¼ck¦·~Ğİª—·Y>_İ\\Œ\'Ó©wT9H§‡˜(»¯´©ÎOÇŸ^­ÏÉÌÈ£vO40nÙ|IÊhè|(Ñœ.Üù¨2ê%‡Ö»C®ñtìx¦M0A¢2”¼ió-Ø+tƒ.ĞĞ\rì±ùGÍ×±WWrâH•¸ıõBˆ:\0}ıî’8-Æ>í•42ÔÁàÀ9¯\n¼¿Ò,Ò|xš¸ZÜ¶\0Â:E`@Âı<›ub3yØB(EaæÁÚCÄ.ì±§-¦mO\Zsq}¡Â]¯óÛØm±_$MŠŠ]h\"%é‰ÚØ»ğ&ˆ!Ì•P˜Î¥Gı)û9BôC¢ouv›•iO‚´42¬zĞ¼?\"]ÖZ!Y-aÕ£¨o¡È\\ÔTÖ„¥ Dº•ğ–j›wï1¿¼¼ÀCh¯¾aJu…ÕÉùî?`šŸÊ°úz7ßèm`å18ÓE0ÿ‘ãìV\'LF³`šdÌñÜşù/Ì±v‚Jàª®`vÎ¡tâ<‡½cQ€ÜC+_.í›öğ`‹e\nendstream\nendobj\n10 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤*¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0K°\nendstream\nendobj\n11 0 obj<</Count 4/Type/Pages/Kids[3 0 R 12 0 R 13 0 R 14 0 R]>>\nendobj\n3 0 obj<</Type/Page/Contents 10 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf1 4 0 R>>>>/Annots[2 0 R]/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n15 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1664/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµZÍRã8¾ç)tÜ­\"B¿–|4a¦26	Pµ·˜Ÿ’YÇÌNñ ûû{Ø–ÉD±¼qr	1ò×­îşúGvïÏÅ>ˆ8_«‹<ÆÊ|³ˆâ˜Ã7/µõ}÷Vë‹\rğ\Z+¶iùá—ÑË÷Û;‚N½ßz½íÚ	‚…AaLcÿí$—8Òş£0Û¸|¥]iw4í~*Õ¥Å\"4½ëQÂ°”hú¶]’yiÚËâŞ—Ş/Ãó³éyr9ùıòôW4ıÀÌ]l0õÈ‹cÌt£8Î¢•\\[\\¼MEQ%BeÊ±–…U^12gy¾}3Ú’CY³NpôA\rİ‹Æq CcMê2~,æİîDò2Ò)\"t\'àùfB…Éºç,^:İ‰äÇâ‹^šêpçğ\0îH…ãbKRb*×ÜYÌóÅüõå%Í6K3¢æ­pÁ×b,ôë²×ùıfdğ-øaMó·<ÍPòº|{õã(.Ù&Å“gƒ^g™Ïæ·[lÃ›m³®@AV1üõæ÷§3Ÿò\Z*@#²ÊMÈ`ôeşô¤J©‰ÉÑløë4k‘™x³˜ˆ±(v1¤+¢X2pŸqO–‹€$.ÔŠÊ6üàrì\rËAt]ˆ!é-Ì\rÌ\n‚®\"ŞÆ6™Â“ãa¡ùh‡ƒØ*h¿m!±Z“gœ>Ş<xQIsà¦JÖQG³ìéuÙyQy”Ã›V¥>#ï‚Lşõ8BÂ¨é-mØ£ômöğÄD£YºÌ³…GU°‰fÍ%Y‘Æ–1:>$Åüë‡å²^\n¶êlxá‰\n©S6§r)E™aĞÆû>k9ñ=M…zšMİœ¶+/RúûùüÓ`ŒN’³át38ö„8+Îjèíä5\nf2ªÎ<~ÕUA\'\"TWÃ‘\0]5ôŠL×@‡Ã—#D”À\"£¢78¼AEgô¶a÷Eo[†EoOIlMo^²èmƒÒ½C½ÈÀ‹Q°iÌ-/^ ˆÇ»·`!©#Á£YÈ„®ÁvÀB¥X\rXXØ×°öÌU\',¤ZXşÛ’›á,t`ÇéÍÃÜ4¿ó#Óˆ1É}DŒ±hpÖDtÄVDôq¦%xŞĞwDÄ`G.mAD%m~+\"M‰Š¢XEègù}‘åè>]d÷hù×âÛâvÖM`Âô´ˆŞ78°_vkb‚µ5Lä%¿‰í€Œ§Q]Sp1äl4J“éø%S¨Ä{±š›G¼ˆ¯ÂÜ–d,m\r‡>’\Zµ!©\ruR‘t|\0IyTÓÔíÔKmÔîÊ¥Õ²3–JeåÁÙüiñ2ËÍ9Ìól~‹vóª)t¤øãœS,šUg$Â”Š\Zjç>`ÈpÍ¶®l\"ô;nRòå=¸»é½^ƒr¦önft˜Œ§SàSá1©u{œL&Éñàl:N¾ÌÁørœ ãä\n~Oèòlxv~}ÖM*çÖ}`G¾»§Nß‘auú¾™»mƒaÃSŸ›Û¦.îò²«ÔêE\"°n®>•™5iN.’NæxSTÜŸ±Zò*cÌÓ‰·N&lƒ¼\n#Dá¸b÷gÁ¶ô¦ÖÈ¶Õ´vô¦ûä½eG­‰\rO¥o‚mËoõ ºâ·ãÆ-ü†fœ·(ÃÄša?ÆW_&°é±pÛƒ:½3~Û »ëZñ;H×¶ü&|3¿aÏ´›ƒºXÓ›‡ÓÛF5÷Ao[†ÅnÏ‘O[v;véjî°A»;U¹×…±Äqø(­­“ärz>Jv¬Ú0 GZ¸À~NÃøĞTÃ0ÂÀå&\"b¨ì¬Qa¢ ‚÷}m];Â¨h„±Q÷E[†EßQcËn×±KG|Q{ÓM·jÓ ÷VÖŒì!š\\ÃDTA°Æê€fhÏ8°~¬Z½ibVˆ‚¶5¹}êª£Ë×Y éµ\Z\'W\'îæ‚bá rõ»ètˆ’Œú‚.”ãA°ÒPÇÖÚ1\Z\r¡–RB/®ˆˆ”¾ úğ‚öl¥ßm¥0kJyù®”SE*—]\'§èOğ#sÈ‚îşÍP’}Ksô:¿E×Y¾¼y˜İ•?ÿ^æi–™Çîp5E‹e¾ü>ËfËeŠ’§ü1ß§Ëôù¹¼éãş„uI_\nendstream\nendobj\n16 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤)¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0t±\nendstream\nendobj\n12 0 obj<</Type/Page/Contents 16 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf2 15 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n17 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1634/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛrâF}ç+æ)•TÁxî\Z=Ê6‹)Ìe±°«öµåØ±\rWÊß—oÈä3ò–„Ä4\n\nU[`­tº§§ÏéîQã÷Å>ˆ¾¶¹½ø›)Š}?g{÷·ŠQàn­°8¯±Çİ~ÑíÏ~<=t½h|m4N{\'1\nc\Zÿ¿x\'¹ÄJü‡ÙÑÛ·ŞÑ­w—aãâKê.MnBásƒ†¥DáçiWXæih–É³³ÆÏ½á “»o“Î/(ü\rÀâ§b°vh±çû˜éRsœ©­]ÓœÊE*·¡¥6åXËÄ†—^‰m\\¾O×ëÓ‹Ñ†ÊÊíp‚Õæº}ßÁ†ÆšìÛøXÌë]‰äi¦¬×•ÀÎ—Û*LîïüÇbVëJ$gØ?È/ó2^¡vßîÀéa?Y’”˜ÊŒ;‹ùz1ßÌfÑò¸µø‚*_\n<3c ?üı²ÜÌ=;ÁE~˜À·Ñús-Q°Y}nìøKvÌñà=FO‚³ZOçO\'bÃËc“Õ  Ë¾y|Á­pjs^C(Eö „ò2}µş°H%ibrK4ş!ZVP&^¾LøX$«€Ò9ñX¸Å8µn°pqám©lÂ·\'c¨›Ñ¬|=Ò\'˜ë¨\n‚n3ŞÄ•Â¢q°ĞÆ¡éÛA…í³Ô{yÆÑëã‹•”\'xêÉ}Ôştù¶Y•eJ3rXcyÁÊİgdg²Ş¶”@•;-MØ€¢qûêf0XºGŸ©ÎËD\'úÂæ4ƒ|r:£vt!‹³p“,(êÊ¹Ø…nï<TÅ·½Œ‰ªç°AJI--ğÔÃÒA1$Ù\n’	?ì\\ä£­ÙqšÕ:íé i/™=H–WD)EZ¨\nHM_·ÎÊ«Ú»’¢I+Ô(Jw°*}\0>p‰*æ£şğ²{Ûö4òá¸œµ–œ×0Vì–¢NğÚÃº<òùRLØîêLÚƒğ¦m©(ü£@9ÛC·ò(å +Lª=PB©\Z¡GÉîbdå1ÙqÑ´0éƒiÊ4w(.9Mèûëœ‡–IÌQE$üĞsfK‘ªä4A›„ÔEÎ‚¯\'ÈY(ÔçnD‰…—çrF”lÜ«O_Ş¡5Eıi´Z/v]QåÛ—gGÁHÿ*ËJÎË®ÒÙ¬\ZbC­˜ÔúÒÃy©Óœï£;ØË`\0:„ˆk\rSD/‡m’Øy/@íêIJÏhÿ˜²éò1,_Œ	K´ÚşQOi\n\Z}wÃo¨İé´Ç¨Ó¿¼A?¡«áYë‘°;G,c_Xzo*„vÉdâ6=^S.™¨0Ú‹\'Ñs¿R;ØNû&¸\ríSª®€+=ciw¯nP?÷&w¶¤a.½`íÛ®äßŒÛhÔß\rÁí`œô1 \05U»‚©]µ£5‘*şáBªŠJoÂşoJo\Z1”ş¼ib§ô…ĞÔ¦ô&jJïº•“cJO´¿Sz?SzÈ[*&%£fwŠ­],	åGùbLØ»Q0F×°\"‹Ü¸¶éq¶xûà¨®>½€*8ÕiŸî××§,Ä»èz×¤\\&´¡\\¶‚T±O/À[å°*=MĞ&cuÑÓ„=)³ÕˆBÍ1Ù‰âÚœ(&ìmô=²¼±‰sÍáT0eİ¥ÄÏF[0~û!iÕ3 ‚\rE8aÌ#Ô§5àwg@ÔÖºUÍi^ØˆR5§MĞ¦–uå´sòA?^¡µ\"…‹Rš6Tğ“Cÿ7t¿h-W‹y4ÿ€ï×Ç—(~­„‚óNR3RùÂmYTaåŞš¨ÎŸ£9ºZÌfı©íÅLEÁ5mzkKıŠı‰Î­íPÅYÅmZA+ffaù‰Ä„Lsµ9ÿ(LâËaT¡Q·w‹z“±¥È^Iäoi“1)1¾g®E‹^èôU0ì‚Î¬_ß[Îãs•Ûw@é)¼\'µ•~¸vZnÀÆ”dŞÑï÷ˆ\0Á§£{\"”§GD_ŒhË¢o/ãóXæğ*„òìL=erÚ´>4Âw¸‡Ñåtş†ÿY¢`ù=z]£Íü	=¼.×«Ç—ésúç_«u´\\Æ¯¬^áj„F‹Õzõcº|›®V\nŞÖ¯w½¿§.á_¼›Q\nendstream\nendobj\n18 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤+¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0²\nendstream\nendobj\n13 0 obj<</Type/Page/Contents 18 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf3 17 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n19 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1504/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœ­YÍRã8¾ç)tÜ­\"B¿–|t 0„dC€Ú½e3d\'?³¶Cmñ ûû{Ø–8rÅòŒ9@pì¯[İ_ê–;u(&ğƒHåÏæ\"±2Y@qÈáÃËbïşnõ*\nÜ­,Àk¬Ø±ÛÏo‹ï_^	º\\u~ëtN{\'…1Iø#ŞI.q ¾Q˜½}ãİx×›tÎ¯\nwi~š¼v(aXJ4ù8í\nË¼m€eşì¢óËíğ~2Œşx¼şMş0ó”ëOöÂ3]k³`c×62BQPÚ€€ĞZ‚r¬enCWŒŞ|še§£-;”ÕÛáv¸ïZ4C\Zk²oã}µlw%’L«X¾+Ì×Û@&÷3ÿ¾Z´ºÉøEL]šjÿäpÚ‘\n‡ù’¤ÄTnkgµÌVËõb\'Ç­™AıR¸à[3úó¿oÉzùõ82d‚‹õaßÅÙG\'(Z§k7¾‡ã’s<šô<8i6]~9^›í~\0%ÈÊ\n_¿¼áîdêr^ÃP‹¬ „ò2=Íâ¿B‰ÀÂCš˜Üš\rÿ\'\r”‰×g€‰‹|À!]V!ŠuA»ŒSg‚…‡ˆµ)e¾ÿ8v€úiİnDŸAô‰ÊõTA7Œ·±R84Îº‘Ã8œ…nPáQö[j‰Õ¶xÆñìåÍ‰Jê‰*¹:˜&ßÖió‚‚y”Ã\Zë7¬Ò}Fv† !=RáÓ¦”eiÃò¢;\"Ô0›@\'ƒ®û÷ıqtwƒúã‡›‹Oıñãı5ŠM0Y/	R°Í>o,S=Y/ÂRŠB+ gRt°~áß	Œoü7¬w·Œ?¥;Ø»øs¼Lßã$vÆîm‰rê’Ò¸»ÇQt¢\0(lù*ğF§!ß¡†w˜Òà8Ä¨@ç„n<Ã^0¦bŒˆ€H\n©”*,=ôW’BŞ+ğÃës’ÿh\Zü‡¶%Lµğ¤óiËËØ°½øcú6æ Á4N³dåLP÷]`lƒ‹20ª!¨À.Ğ†BPmO¼³ßéúÎ«Ì¢²6’OÃ«ş]F÷·“ãà6`]ŠB€¨z¡ŸÊ\Z…\03ìÎîë«‚VŒ__MxøªaÜczô–£<qøq`nÙRyÃ„d…ÃIŒ0ÀBùóÂF½ì»Ê9ôé–ÍmÁhp4ˆú“ñ†Èˆ¶+\"f\0i ×¶:OdšRvë˜észtt¥*ÙğO—¥*¹vƒÓZ¤‹­jlr¤ 	‚Ü–\0UxFOv\"MèÃ÷Vºa™Æ²¶ä¯\r;Âøw/¢ñ%Šzcè~Ì4ÒZs`›\nÌw¡fT3Ûš66¼Õ8ãä·ò 8`©ÀsÕR7\\A=ƒ¶8èKª1m vP„Õ\"óKAÌaî(úıîæªz°1¢ëAïS;Ä7|Ö™¢şØ›å;tc‹x™æSÇYcc>Úv|d.ínÚ“ÙğÊÚ”6èiM}SÈBLı\'Ê­™ú>s	ÌB¿3ñ¥š1‘QO&zQ”Ë°a³8‹]ÑaĞ5  {Õ/	è8k,ˆ6<“m1ĞFm‘Ş©ƒüg(µFwpªsA$¦\"s\Z\'èâ-™¥ÙÌ),\rYH¸ßR„×Ñr¹¶?[Î§_Ûâ¡lñıT8$¤öĞqj^‚´ÄD·E&ú¦¯™†ÖÔ+¸RÅÎL\Z*hÙë¥p{şY¾2wˆœ>{»´êˆ.Ş;,èrzr›ŠùƒÊÍsZ+qhÀ-ıô=dë-ƒÁ-Œ¶”ĞÑÒ#¢ÏG´{òİ¦õ–\\Ã¬”òb†R[ë½è9ºFEçzÓå7ôú_‚¢äs<ËĞzù=Ï’,}y›¾ÿş“fq’˜DÎàjŒF«4K¿C~§i\Z£è[6‹—_ã4Ï‹‡—ğ?:WŸ°\nendstream\nendobj\n20 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤™(¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0Æ³\nendstream\nendobj\n14 0 obj<</Type/Page/Contents 20 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf4 19 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n7 0 obj[/PDF/Text/ImageB]\nendobj\n5 0 obj <</Type/XObject/Filter/FlateDecode/Length 80/Height 30/BitsPerComponent 1/Subtype/Image/Name/IMmpdf1/ImageMask true/Width 2232/Decode[1 0]>>stream\nxœíÖ1\00ÃÀĞ/ƒÂ-İdßé)hÑŒôëhCg‘ê]mè,R=zBÑY¤zô „¢³HõèAEg‘êÑƒŠÎ\"Õ£!Eª÷\0hœFq\nendstream\nendobj\n6 0 obj <</Type/XObject/Filter/FlateDecode/Length 700/Height 78/BitsPerComponent 1/Subtype/Image/Name/IMmpdf0/ImageMask true/Width 360>>stream\nxœÍ×;Õ0`G)B…[\n„Y‚\r·2K ¼vÇvØ@¨(Ù‚—`D‘\"›ÿ?vîc\"d‰HÉÍã‹slŸÑÔzûşËG¶oÿş:§CØÃn6Ã°†—ax†§aXÃ~vÃ°†äèA| G?ÃrÔş	ç«ä±ê’£_jıqÅ”bˆ\n­±^ -\nqÆm\\Ö÷hÇ×\Z]ôuSÄ~•òÀ\0V‚ÙM”]Çy\"†cCÀ¸®ÛçMÇXõÄ.â YÎ/ìyéxÕ‚ÍÚ[ÆuÍ{Ì	{;®cSq0·x#:Fçùß%S¸FÇ9ã#êe\'Ş¦Öû˜\r]ğJìd\'f8gŒqæk´´“`#8.·˜×u½ÂÑb8qIŒ~uìs0ˆõójqŠ¨ˆ¹2^cp®0:°púD¿cçg–úUrã…Œ`•Ù\'%lˆè©òÀ§©Mh”Iñéù˜£ğÛ†.dŸIÁPV†@iÂ|#H\r|§ZŠ*—%7$+Ë«ZæJ—Èìñ\rûÂ†€uÃ¾a€Ó,Ø5ÌÑ\'~¹Übt/ø¬³\"æ8÷ÄE&&Å%®mR<uçâeAµš²Íib®\\ptIúÃ4vqI-7d+sš‹ôØN\r\'#§Xç[Œ&Ü\rG`Ç”üY°ábÀ¤Ì;æ\\iœ\"ùÎÉ_ë\'Ä¼pY™+ñ’™K‚]Ãh1-}Á®vi8¸ĞqŒ–#Úêw,$ qÔ÷R ‹Óê½ƒö‘V7ï¥€cwÁ…ÏT¿U$Á±—/ö8YÓğ´ñY‘Z×1ŸIadOw¼l\\?áÄã³mRVÌ91zœ­ëogY’o˜ŠÄõ¿‚ïoÜ’m³ş7Oşû÷ö®¯à×GğÇaøĞÿVàŸ‘\nendstream\nendobj\n8 0 obj<</Type/Font/BaseFont/Helvetica-Bold/Subtype/Type1/Name/Fmpdf1/Encoding/WinAnsiEncoding>>\nendobj\n9 0 obj<</Type/Font/BaseFont/Helvetica/Subtype/Type1/Name/Fmpdf0/Encoding/WinAnsiEncoding>>\nendobj\n21 0 obj<</Type/Catalog/AcroForm<</SigFlags 3/Fields[2 0 R]>>/Pages 11 0 R>>\nendobj\n22 0 obj<</CreationDate(D:20080923065445+02\'00\')/Producer(iText 1.4.8 \\(by lowagie.com\\))/ModDate(D:20080923065445+02\'00\')>>\nendobj\nxref\n0 23\n0000000000 65535 f \n0000000117 00000 n \n0000000015 00000 n \n0000010019 00000 n \n0000008020 00000 n \n0000016736 00000 n \n0000016989 00000 n \n0000016703 00000 n \n0000017850 00000 n \n0000017954 00000 n \n0000009802 00000 n \n0000009947 00000 n \n0000012262 00000 n \n0000014463 00000 n \n0000016534 00000 n \n0000010200 00000 n \n0000012117 00000 n \n0000012431 00000 n \n0000014318 00000 n \n0000014632 00000 n \n0000016389 00000 n \n0000018053 00000 n \n0000018137 00000 n \ntrailer\n<</Root 21 0 R/Size 23/Info 22 0 R>>\nstartxref\n18269\n%%EOF\n','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-02-17 21:22:03','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `attachments`(`id`,`category_key`,`description`,`owner_id`,`intern`,`mime_type`,`file_name`,`file_size`,`file_content`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('aa5fe149-d31e-4809-802c-15d8dc3419a2','certificat','Test fÃ¼r Mach','7522bc7f-42cf-415c-a050-da12518a4cd3',0,'application/pdf','20080908.pdf',18806,'%PDF-1.4\n%âãÏÓ\n2 0 obj<</P 3 0 R/Type/Annot/F 132/T(Signature1)/V 1 0 R/Rect[0 0 0 0]/Subtype/Widget/FT/Sig>>\nendobj\n1 0 obj<</Filter/Adobe.PPKMS/Type/Sig/Contents <30820ebb06092a864886f70d010702a0820eac30820ea8020101310b300906052b0e03021a0500302306092a864886f70d010701a0160414a3939baa417b8f344e2be299336cd946bf5fcdeda0820d1d308204b33082039ba003020102020302f2f9300d06092a864886f70d010105050030819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033301e170d3037303232303135343535385a170d3132303232303135343535385a305b310b3009060355040613024154310e300c060355040a0c054241574147310b3009060355040b0c0249543118301606035504030c0f424157414720502e532e4b2e204147311530130603550405130c33343038393431313432383930819f300d06092a864886f70d010101050003818d00308189028181009f18e18acafeced47e87948327150690350f940b496d3d832b80568e1578be143898e395bd10d5863bab7400f0b3f7c41052e298169f96ae84f3931805bae5fb150b191a80cee84538a05cce009c4457f3b059e34f5d9668a7317ced1a8db2ea88079ce7685599fd678ebe8a41154bdb18ec239098f4ee063944d4142ccb585b0203010001a38201bd308201b930130603551d23040c300a80084191691cbfadd898305506082b0601050507010104493047304506082b060105050730028639687474703a2f2f7777772e612d74727573742e61742f63657274732f612d7369676e2d636f72706f726174652d6c696768742d30332e63727430580603551d200451304f304d06072a2800110107013042304006082b060105050702011634687474703a2f2f7777772e612d74727573742e61742f646f63732f63702f612d7369676e2d636f72706f726174652d6c6967687430819e0603551d1f048196308193308190a0818da0818a8681876c6461703a2f2f6c6461702e612d74727573742e61742f6f753d612d7369676e2d636f72706f726174652d6c696768742d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f7269747930110603551d0e040a0408487cf8373c3f8bb6300e0603551d0f0101ff0404030204b030220603551d11041b3019811769742d736963686572686569744062617761672e636f6d30090603551d1304023000300d06092a864886f70d01010505000382010100776e67497156b2bcb608d9de5dcf85efbaa18859128f1934e0ee4ecab2f94ccee454e8610e6be5b6c37eb49e4c8026363d107ce5b3e62bea1b4927cd9ed152d430a77976215fabbe75b27a8f452c3f1e8943bc28339dfc241c2a3fc66872919067c1420a7e010933947e324117d118bfa2cb732d845c80b3aecd37ee481323fe9166bdad16d619298ee502c5533254e8bdb1f90ab97c9a4c343deb368f3def13c4ba4d66fc4861f5af516cd6d94e1b5ebddf5e8eceb331a7fb2c9bfb0d210c0d8639f81ffd9d2cbf50e50ba200828415e8dabad043cbfd95355915ab7f3542ce80c6d90b5d1b72d1d03c9370e7c9f90909b76f870ebdf248b918da2e705259d73082048f30820377a003020102020301aaed300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035313131333233303030305a170d3135313131333233303030305a30819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100b7e7da22b5b1e490481d12b44f78176471844ad832ddc5f7279fe488ba6484633595b1c4537213ca4b36fb7f56ba1073db079599b0625a5035fd29f4eab0934ca3eedb0122a4de0b0b571359a98dadd86fb2f07bd111861e8c3388ce302470142bb7e9efb8152519854942312ec809f5f1fdaf1b463754dbd91462a73caf00572714324d14c36d516cadef5f9b5b6cf810fe471990de1cce2e551dea4cbdfea1030c0ba098fe28bf73c66f23c8f2bcf2a0812808cc342a592ac121c9a9f0d428d86d5987b38e50e56e8506da83712e88ab8982e93b543c6b424eea07eff24bba1129d3be68869f3bbf77d297378b2630699140cff54f13bfb9767ae0f4b755150203010001a381e33081e0300f0603551d130101ff040530030101ff30110603551d0e040a04084191691cbfadd89830130603551d23040c300a8008446a95675579114f300e0603551d0f0101ff0404030201063081940603551d1f04818c308189308186a08183a08180867e6c6461703a2f2f6c6461702e612d74727573742e61742f6f753d412d54727573742d6e5175616c2d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f72697479300d06092a864886f70d010105050003820101000d3448690b4fc283d2ebf4e9c8184ec38c001e4fd13388242d3efa5113d8dac5078a1c6acbef2a10494df9fe6523ad66c823f72054c295f95c1d5475402a0c834648e7f11c41323c0e0779890917b958e1df00a3f3b29ad93bbb7752be8bd549e0147fa61fea98f291ecbc7f3ed013c3fbd6a38936adbb79a354308b167b08fb7e045f7109a002e2e2f46c1d0cf4cde2f56d9e3e6a97e96201de33244fd1500b42a284f3dfc3fd7b29d74d8b8ac530c1224f22af253b53a839c80305964eab0bd46ee8c4c32d9e761a296310ea04223a2473098ede7dc022ff7630517e942d6e73c6a6a936cd6fa846d21ac40497578597aec70591d800f7681c6527645d0a69308203cf308202b7a0030201020203016c1e300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035303831373232303030305a170d3135303831373232303030305a30818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100ad3d616e03f3903bc0410b8480cdec2aa39d6bbb6ec24284f75114e1a0a82d51a351f2de23f03444ff94ebcc05239540b90778a525f60abd4586e8d9bdc0048e854461ef7fa7c9fac125cc852c633f0560734905e0607895104bdcf91159ce717f409b8aaa24df0b42e2db56bc4ad2a50c9bb7433edd83d3261002cfea23c4494ee5d3e9b488ab0cae6292d46587d96ad7f4859fe4332225a5e5c833bac3c741dc5fc66acc000e6d32a8b687360062779b1e1f34cb903c78887405eb79f5937165ca9dc76b182d3d5c4ee7d5f83f317d8f87ec0a222f23e9febb7dc9e0f4eceb7cc4b0c32d62b59a71d6b16ae8ecd9edd572ecbe5701ce05559fded1608810b30203010001a3363034300f0603551d130101ff040530030101ff30110603551d0e040a0408446a95675579114f300e0603551d0f0101ff040403020106300d06092a864886f70d0101050500038201010055d454d159485cb39385aabf632fe480ce34a334623ef6d8ee67883104036f0bd407fb4e750fd32ed3c017c7c628ec060d11240e0ea55dbf8cb2139671dcd4ce0e0d0a68326cb9413119abb1077b4d98d35cb0d1f0a742a0b5c48eaffef13ff4ef4f460076eb02fbf99dd24096c7883ab89f1179f38065a8bd1fd37881a0514c37b4a65d2570d166c968f92e111468f1549808ac26920fde899ed4fab3792bd2a379d4ec8bac875368424c5151741e1b272ee3f51f29744dedaff7e1929981e8be3ac71750f6b7c6fc9bb08a6bd68803918f06773a8502dd98d543783fc63015ac9b6bcb57b789518b3ae8c9840cdbb150200a1a4aba6a1abdec1bc8c5849acd3182014e3082014a0201013081a730819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033020302f2f9300906052b0e03021a0500300d06092a864886f70d01010105000481806452bd278af0c176438b49ad48756ef60aa7d0badffe78407ee18e1b9dbab2036362b5314ac3dfe6b2a29164063ae49d6346fd126c4f8784c363231d9987565076313d509b3321bf733910119405ebb8903601f87e721a425ebc2075284692f6706b7aecadf8f6a2f4f0c4cb9d17aaf87ce597e78e5b7aef4807be6de1d9fa3800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000>/M(D:20080923065445+02\'00\')/Name(BAWAG P.S.K. AG)/SubFilter/adbe.pkcs7.sha1/ByteRange [0 164 7844 10962 ]                                                             >>\nendobj\n4 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1530/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛRÛH}÷Wèq·Êæªí›IHŠbI8qÕ¾	<­m™Õ…İâC÷òyØdK¶Ç\ZK„|NO_NwËƒ¿aø\npç¥¾È\"$Í+\r	Šür·Ú¹Ô}CîV!âà’ôĞí\'ç—«ÇÙï×ƒ/ƒÁqë8FÜ Pª~Æ:Á\nÕŞ$¢o¯­#µu§“ÁÉk.©n\n&óÁ	L›B™Y×†HTï]\r~¹øüiòyüõæ¯\r&˜y—;›8ø¢QÕKÇhXó¶é¢c$$7àÒËÁ	CJTÒ^1§Ë¸(Fµxíça…{<Ü÷,\nE‘‡B\nïr<­Ó·=‰`6Ó~î$ù~¥­ÀNäŸÖ«7=‰`E{,ØÔ¥9¡òó¨!QT%€ˆˆ¦vÖi±NËÕJg‡ÙÌ…°ÿ(Œ³†¦…>ış•éıadˆã/¨6ğïºx.tŒËü¹tã{.è!ÃÇKƒ^9\'/âtvÄ7¬ß7M?€¤›\n/ïĞh»ŒW½È\\(!ƒÓóBÿã‚9âÒDE]hmø©Î^ L¬?”GˆW§€R›*ÀXÒ(ğˆ2â0÷q:¼öõÚê§A¤iD›AÔ‘ÊõTNêŒoc¥phœ\',L#û~FnPîQöMjsdS<×:¹{p¢âşÄK¥ØE½Œ³E™;aÃşöDÃÈ„ÜfœÁ=p(” öÈ^…arÙ}_fwùİCšEÇËÙÚ­ 4ôÈaeµ»C2ÿá²<DRöƒ†Ø6è,vûØ#Ë˜İeN;±‡Š pòKgE¼t\0/}Ã‡¼z«‹ìû½Câ\0XôË2gÜöõğ£«ÅzJäÀ¾gû†?ä*:€ıJøÁ0»Ebˆ±Ó¿²¿¶…,¸m€u!k*÷«dn\'äj¼(J½\\ºCĞÔş&ÔÊdù,Iq9Ï3ëìÉEà•ÉLâÚÕm‚ã™ì×<£zêZşÚæÉëºkƒVSƒ;1Bx‹‡¹Ä*„…Ö!ùÆŒ·¸û·ãõK§÷¦OwÈ(¢ümútv(\Zº?tqŸUm[BTZu­FG,ã{ıÆbŒÔ…ÕærúĞé•¨MäÛ6ò°$ü_‘o“1$¨+{¥OÛØF¾\r;ŒèÈ×U0ıÕ6ô$´|†æ“.;Oğ\\FQ»»™¸<\"{­uT›êc™ÂrâökX˜Ö·ºôê8·Y ™éëüMœÛ°C)<ÃL0L!ı>ÚÄ9bvB­+<¸şq·˜g¦ÎefX‡ßn@\\à6ß·-Îê5µmÈü<W_- f½êŸ7\'Wá†ğf=×™=g\nG.‚Û$Máàa0]W˜é•Íÿ¸z¨Àø%óbé\\fLØ¤V†ÛãÿÌt–?fÚPƒ8ÕÙBWög	Ì)zÕÎ°¡Q+üçï¼ck¦·~Ğİª—·Y>_İ\\Œ\'Ó©wT9H§‡˜(»¯´©ÎOÇŸ^­ÏÉÌÈ£vO40nÙ|IÊhè|(Ñœ.Üù¨2ê%‡Ö»C®ñtìx¦M0A¢2”¼ió-Ø+tƒ.ĞĞ\rì±ùGÍ×±WWrâH•¸ıõBˆ:\0}ıî’8-Æ>í•42ÔÁàÀ9¯\n¼¿Ò,Ò|xš¸ZÜ¶\0Â:E`@Âı<›ub3yØB(EaæÁÚCÄ.ì±§-¦mO\Zsq}¡Â]¯óÛØm±_$MŠŠ]h\"%é‰ÚØ»ğ&ˆ!Ì•P˜Î¥Gı)û9BôC¢ouv›•iO‚´42¬zĞ¼?\"]ÖZ!Y-aÕ£¨o¡È\\ÔTÖ„¥ Dº•ğ–j›wï1¿¼¼ÀCh¯¾aJu…ÕÉùî?`šŸÊ°úz7ßèm`å18ÓE0ÿ‘ãìV\'LF³`šdÌñÜşù/Ì±v‚Jàª®`vÎ¡tâ<‡½cQ€ÜC+_.í›öğ`‹e\nendstream\nendobj\n10 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤*¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0K°\nendstream\nendobj\n11 0 obj<</Count 4/Type/Pages/Kids[3 0 R 12 0 R 13 0 R 14 0 R]>>\nendobj\n3 0 obj<</Type/Page/Contents 10 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf1 4 0 R>>>>/Annots[2 0 R]/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n15 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1664/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµZÍRã8¾ç)tÜ­\"B¿–|4a¦26	Pµ·˜Ÿ’YÇÌNñ ûû{Ø–ÉD±¼qr	1ò×­îşúGvïÏÅ>ˆ8_«‹<ÆÊ|³ˆâ˜Ã7/µõ}÷Vë‹\rğ\Z+¶iùá—ÑË÷Û;‚N½ßz½íÚ	‚…AaLcÿí$—8Òş£0Û¸|¥]iw4í~*Õ¥Å\"4½ëQÂ°”hú¶]’yiÚËâŞ—Ş/Ãó³éyr9ùıòôW4ıÀÌ]l0õÈ‹cÌt£8Î¢•\\[\\¼MEQ%BeÊ±–…U^12gy¾}3Ú’CY³NpôA\rİ‹Æq CcMê2~,æİîDò2Ò)\"t\'àùfB…Éºç,^:İ‰äÇâ‹^šêpçğ\0îH…ãbKRb*×ÜYÌóÅüõå%Í6K3¢æ­pÁ×b,ôë²×ùıfdğ-øaMó·<ÍPòº|{õã(.Ù&Å“gƒ^g™Ïæ·[lÃ›m³®@AV1üõæ÷§3Ÿò\Z*@#²ÊMÈ`ôeşô¤J©‰ÉÑløë4k‘™x³˜ˆ±(v1¤+¢X2pŸqO–‹€$.ÔŠÊ6üàrì\rËAt]ˆ!é-Ì\rÌ\n‚®\"ŞÆ6™Â“ãa¡ùh‡ƒØ*h¿m!±Z“gœ>Ş<xQIsà¦JÖQG³ìéuÙyQy”Ã›V¥>#ï‚Lşõ8BÂ¨é-mØ£ômöğÄD£YºÌ³…GU°‰fÍ%Y‘Æ–1:>$Åüë‡å²^\n¶êlxá‰\n©S6§r)E™aĞÆû>k9ñ=M…zšMİœ¶+/RúûùüÓ`ŒN’³át38ö„8+Îjèíä5\nf2ªÎ<~ÕUA\'\"TWÃ‘\0]5ôŠL×@‡Ã—#D”À\"£¢78¼AEgô¶a÷Eo[†EoOIlMo^²èmƒÒ½C½ÈÀ‹Q°iÌ-/^ ˆÇ»·`!©#Á£YÈ„®ÁvÀB¥X\rXXØ×°öÌU\',¤ZXşÛ’›á,t`ÇéÍÃÜ4¿ó#Óˆ1É}DŒ±hpÖDtÄVDôq¦%xŞĞwDÄ`G.mAD%m~+\"M‰Š¢XEègù}‘åè>]d÷hù×âÛâvÖM`Âô´ˆŞ78°_vkb‚µ5Lä%¿‰í€Œ§Q]Sp1äl4J“éø%S¨Ä{±š›G¼ˆ¯ÂÜ–d,m\r‡>’\Zµ!©\ruR‘t|\0IyTÓÔíÔKmÔîÊ¥Õ²3–JeåÁÙüiñ2ËÍ9Ìól~‹vóª)t¤øãœS,šUg$Â”Š\Zjç>`ÈpÍ¶®l\"ô;nRòå=¸»é½^ƒr¦önft˜Œ§SàSá1©u{œL&Éñàl:N¾ÌÁørœ ãä\n~Oèòlxv~}ÖM*çÖ}`G¾»§Nß‘auú¾™»mƒaÃSŸ›Û¦.îò²«ÔêE\"°n®>•™5iN.’NæxSTÜŸ±Zò*cÌÓ‰·N&lƒ¼\n#Dá¸b÷gÁ¶ô¦ÖÈ¶Õ´vô¦ûä½eG­‰\rO¥o‚mËoõ ºâ·ãÆ-ü†fœ·(ÃÄša?ÆW_&°é±pÛƒ:½3~Û »ëZñ;H×¶ü&|3¿aÏ´›ƒºXÓ›‡ÓÛF5÷Ao[†ÅnÏ‘O[v;véjî°A»;U¹×…±Äqø(­­“ärz>Jv¬Ú0 GZ¸À~NÃøĞTÃ0ÂÀå&\"b¨ì¬Qa¢ ‚÷}m];Â¨h„±Q÷E[†EßQcËn×±KG|Q{ÓM·jÓ ÷VÖŒì!š\\ÃDTA°Æê€fhÏ8°~¬Z½ibVˆ‚¶5¹}êª£Ë×Y éµ\Z\'W\'îæ‚bá rõ»ètˆ’Œú‚.”ãA°ÒPÇÖÚ1\Z\r¡–RB/®ˆˆ”¾ úğ‚öl¥ßm¥0kJyù®”SE*—]\'§èOğ#sÈ‚îşÍP’}Ksô:¿E×Y¾¼y˜İ•?ÿ^æi–™Çîp5E‹e¾ü>ËfËeŠ’§ü1ß§Ëôù¹¼éãş„uI_\nendstream\nendobj\n16 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤)¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0t±\nendstream\nendobj\n12 0 obj<</Type/Page/Contents 16 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf2 15 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n17 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1634/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛrâF}ç+æ)•TÁxî\Z=Ê6‹)Ìe±°«öµåØ±\rWÊß—oÈä3ò–„Ä4\n\nU[`­tº§§ÏéîQã÷Å>ˆ¾¶¹½ø›)Š}?g{÷·ŠQàn­°8¯±Çİ~ÑíÏ~<=t½h|m4N{\'1\nc\Zÿ¿x\'¹ÄJü‡ÙÑÛ·ŞÑ­w—aãâKê.MnBásƒ†¥DáçiWXæih–É³³ÆÏ½á “»o“Î/(ü\rÀâ§b°vh±çû˜éRsœ©­]ÓœÊE*·¡¥6åXËÄ†—^‰m\\¾O×ëÓ‹Ñ†ÊÊíp‚Õæº}ßÁ†ÆšìÛøXÌë]‰äi¦¬×•ÀÎ—Û*LîïüÇbVëJ$gØ?È/ó2^¡vßîÀéa?Y’”˜ÊŒ;‹ùz1ßÌfÑò¸µø‚*_\n<3c ?üı²ÜÌ=;ÁE~˜À·Ñús-Q°Y}nìøKvÌñà=FO‚³ZOçO\'bÃËc“Õ  Ë¾y|Á­pjs^C(Eö „ò2}µş°H%ibrK4ş!ZVP&^¾LøX$«€Ò9ñX¸Å8µn°pqám©lÂ·\'c¨›Ñ¬|=Ò\'˜ë¨\n‚n3ŞÄ•Â¢q°ĞÆ¡éÛA…í³Ô{yÆÑëã‹•”\'xêÉ}Ôştù¶Y•eJ3rXcyÁÊİgdg²Ş¶”@•;-MØ€¢qûêf0XºGŸ©ÎËD\'úÂæ4ƒ|r:£vt!‹³p“,(êÊ¹Ø…nï<TÅ·½Œ‰ªç°AJI--ğÔÃÒA1$Ù\n’	?ì\\ä£­ÙqšÕ:íé i/™=H–WD)EZ¨\nHM_·ÎÊ«Ú»’¢I+Ô(Jw°*}\0>p‰*æ£şğ²{Ûö4òá¸œµ–œ×0Vì–¢NğÚÃº<òùRLØîêLÚƒğ¦m©(ü£@9ÛC·ò(å +Lª=PB©\Z¡GÉîbdå1ÙqÑ´0éƒiÊ4w(.9Mèûëœ‡–IÌQE$üĞsfK‘ªä4A›„ÔEÎ‚¯\'ÈY(ÔçnD‰…—çrF”lÜ«O_Ş¡5Eıi´Z/v]QåÛ—gGÁHÿ*ËJÎË®ÒÙ¬\ZbC­˜ÔúÒÃy©Óœï£;ØË`\0:„ˆk\rSD/‡m’Øy/@íêIJÏhÿ˜²éò1,_Œ	K´ÚşQOi\n\Z}wÃo¨İé´Ç¨Ó¿¼A?¡«áYë‘°;G,c_Xzo*„vÉdâ6=^S.™¨0Ú‹\'Ñs¿R;ØNû&¸\ríSª®€+=ciw¯nP?÷&w¶¤a.½`íÛ®äßŒÛhÔß\rÁí`œô1 \05U»‚©]µ£5‘*şáBªŠJoÂşoJo\Z1”ş¼ib§ô…ĞÔ¦ô&jJïº•“cJO´¿Sz?SzÈ[*&%£fwŠ­],	åGùbLØ»Q0F×°\"‹Ü¸¶éq¶xûà¨®>½€*8ÕiŸî××§,Ä»èz×¤\\&´¡\\¶‚T±O/À[å°*=MĞ&cuÑÓ„=)³ÕˆBÍ1Ù‰âÚœ(&ìmô=²¼±‰sÍáT0eİ¥ÄÏF[0~û!iÕ3 ‚\rE8aÌ#Ô§5àwg@ÔÖºUÍi^ØˆR5§MĞ¦–uå´sòA?^¡µ\"…‹Rš6Tğ“Cÿ7t¿h-W‹y4ÿ€ï×Ç—(~­„‚óNR3RùÂmYTaåŞš¨ÎŸ£9ºZÌfı©íÅLEÁ5mzkKıŠı‰Î­íPÅYÅmZA+ffaù‰Ä„Lsµ9ÿ(LâËaT¡Q·w‹z“±¥È^Iäoi“1)1¾g®E‹^èôU0ì‚Î¬_ß[Îãs•Ûw@é)¼\'µ•~¸vZnÀÆ”dŞÑï÷ˆ\0Á§£{\"”§GD_ŒhË¢o/ãóXæğ*„òìL=erÚ´>4Âw¸‡Ñåtş†ÿY¢`ù=z]£Íü	=¼.×«Ç—ésúç_«u´\\Æ¯¬^áj„F‹Õzõcº|›®V\nŞÖ¯w½¿§.á_¼›Q\nendstream\nendobj\n18 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤+¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0²\nendstream\nendobj\n13 0 obj<</Type/Page/Contents 18 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf3 17 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n19 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1504/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœ­YÍRã8¾ç)tÜ­\"B¿–|t 0„dC€Ú½e3d\'?³¶Cmñ ûû{Ø–8rÅòŒ9@pì¯[İ_ê–;u(&ğƒHåÏæ\"±2Y@qÈáÃËbïşnõ*\nÜ­,Àk¬Ø±ÛÏo‹ï_^	º\\u~ëtN{\'…1Iø#ŞI.q ¾Q˜½}ãİx×›tÎ¯\nwi~š¼v(aXJ4ù8í\nË¼m€eşì¢óËíğ~2Œşx¼şMş0ó”ëOöÂ3]k³`c×62BQPÚ€€ĞZ‚r¬enCWŒŞ|še§£-;”ÕÛáv¸ïZ4C\Zk²oã}µlw%’L«X¾+Ì×Û@&÷3ÿ¾Z´ºÉøEL]šjÿäpÚ‘\n‡ù’¤ÄTnkgµÌVËõb\'Ç­™AıR¸à[3úó¿oÉzùõ82d‚‹õaßÅÙG\'(Z§k7¾‡ã’s<šô<8i6]~9^›í~\0%ÈÊ\n_¿¼áîdêr^ÃP‹¬ „ò2=Íâ¿B‰ÀÂCš˜Üš\rÿ\'\r”‰×g€‰‹|À!]V!ŠuA»ŒSg‚…‡ˆµ)e¾ÿ8v€úiİnDŸAô‰ÊõTA7Œ·±R84Îº‘Ã8œ…nPáQö[j‰Õ¶xÆñìåÍ‰Jê‰*¹:˜&ßÖió‚‚y”Ã\Zë7¬Ò}Fv† !=RáÓ¦”eiÃò¢;\"Ô0›@\'ƒ®û÷ıqtwƒúã‡›‹Oıñãı5ŠM0Y/	R°Í>o,S=Y/ÂRŠB+ gRt°~áß	Œoü7¬w·Œ?¥;Ø»øs¼Lßã$vÆîm‰rê’Ò¸»ÇQt¢\0(lù*ğF§!ß¡†w˜Òà8Ä¨@ç„n<Ã^0¦bŒˆ€H\n©”*,=ôW’BŞ+ğÃës’ÿh\Zü‡¶%Lµğ¤óiËËØ°½øcú6æ Á4N³dåLP÷]`lƒ‹20ª!¨À.Ğ†BPmO¼³ßéúÎ«Ì¢²6’OÃ«ş]F÷·“ãà6`]ŠB€¨z¡ŸÊ\Z…\03ìÎîë«‚VŒ__MxøªaÜczô–£<qøq`nÙRyÃ„d…ÃIŒ0ÀBùóÂF½ì»Ê9ôé–ÍmÁhp4ˆú“ñ†Èˆ¶+\"f\0i ×¶:OdšRvë˜észtt¥*ÙğO—¥*¹vƒÓZ¤‹­jlr¤ 	‚Ü–\0UxFOv\"MèÃ÷Vºa™Æ²¶ä¯\r;Âøw/¢ñ%Šzcè~Ì4ÒZs`›\nÌw¡fT3Ûš66¼Õ8ãä·ò 8`©ÀsÕR7\\A=ƒ¶8èKª1m vP„Õ\"óKAÌaî(úıîæªz°1¢ëAïS;Ä7|Ö™¢şØ›å;tc‹x™æSÇYcc>Úv|d.ínÚ“ÙğÊÚ”6èiM}SÈBLı\'Ê­™ú>s	ÌB¿3ñ¥š1‘QO&zQ”Ë°a³8‹]ÑaĞ5  {Õ/	è8k,ˆ6<“m1ĞFm‘Ş©ƒüg(µFwpªsA$¦\"s\Z\'èâ-™¥ÙÌ),\rYH¸ßR„×Ñr¹¶?[Î§_Ûâ¡lñıT8$¤öĞqj^‚´ÄD·E&ú¦¯™†ÖÔ+¸RÅÎL\Z*hÙë¥p{şY¾2wˆœ>{»´êˆ.Ş;,èrzr›ŠùƒÊÍsZ+qhÀ-ıô=dë-ƒÁ-Œ¶”ĞÑÒ#¢ÏG´{òİ¦õ–\\Ã¬”òb†R[ë½è9ºFEçzÓå7ôú_‚¢äs<ËĞzù=Ï’,}y›¾ÿş“fq’˜DÎàjŒF«4K¿C~§i\Z£è[6‹—_ã4Ï‹‡—ğ?:WŸ°\nendstream\nendobj\n20 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤™(¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0Æ³\nendstream\nendobj\n14 0 obj<</Type/Page/Contents 20 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf4 19 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n7 0 obj[/PDF/Text/ImageB]\nendobj\n5 0 obj <</Type/XObject/Filter/FlateDecode/Length 80/Height 30/BitsPerComponent 1/Subtype/Image/Name/IMmpdf1/ImageMask true/Width 2232/Decode[1 0]>>stream\nxœíÖ1\00ÃÀĞ/ƒÂ-İdßé)hÑŒôëhCg‘ê]mè,R=zBÑY¤zô „¢³HõèAEg‘êÑƒŠÎ\"Õ£!Eª÷\0hœFq\nendstream\nendobj\n6 0 obj <</Type/XObject/Filter/FlateDecode/Length 700/Height 78/BitsPerComponent 1/Subtype/Image/Name/IMmpdf0/ImageMask true/Width 360>>stream\nxœÍ×;Õ0`G)B…[\n„Y‚\r·2K ¼vÇvØ@¨(Ù‚—`D‘\"›ÿ?vîc\"d‰HÉÍã‹slŸÑÔzûşËG¶oÿş:§CØÃn6Ã°†—ax†§aXÃ~vÃ°†äèA| G?ÃrÔş	ç«ä±ê’£_jıqÅ”bˆ\n­±^ -\nqÆm\\Ö÷hÇ×\Z]ôuSÄ~•òÀ\0V‚ÙM”]Çy\"†cCÀ¸®ÛçMÇXõÄ.â YÎ/ìyéxÕ‚ÍÚ[ÆuÍ{Ì	{;®cSq0·x#:Fçùß%S¸FÇ9ã#êe\'Ş¦Öû˜\r]ğJìd\'f8gŒqæk´´“`#8.·˜×u½ÂÑb8qIŒ~uìs0ˆõójqŠ¨ˆ¹2^cp®0:°púD¿cçg–úUrã…Œ`•Ù\'%lˆè©òÀ§©Mh”Iñéù˜£ğÛ†.dŸIÁPV†@iÂ|#H\r|§ZŠ*—%7$+Ë«ZæJ—Èìñ\rûÂ†€uÃ¾a€Ó,Ø5ÌÑ\'~¹Übt/ø¬³\"æ8÷ÄE&&Å%®mR<uçâeAµš²Íib®\\ptIúÃ4vqI-7d+sš‹ôØN\r\'#§Xç[Œ&Ü\rG`Ç”üY°ábÀ¤Ì;æ\\iœ\"ùÎÉ_ë\'Ä¼pY™+ñ’™K‚]Ãh1-}Á®vi8¸ĞqŒ–#Úêw,$ qÔ÷R ‹Óê½ƒö‘V7ï¥€cwÁ…ÏT¿U$Á±—/ö8YÓğ´ñY‘Z×1ŸIadOw¼l\\?áÄã³mRVÌ91zœ­ëogY’o˜ŠÄõ¿‚ïoÜ’m³ş7Oşû÷ö®¯à×GğÇaøĞÿVàŸ‘\nendstream\nendobj\n8 0 obj<</Type/Font/BaseFont/Helvetica-Bold/Subtype/Type1/Name/Fmpdf1/Encoding/WinAnsiEncoding>>\nendobj\n9 0 obj<</Type/Font/BaseFont/Helvetica/Subtype/Type1/Name/Fmpdf0/Encoding/WinAnsiEncoding>>\nendobj\n21 0 obj<</Type/Catalog/AcroForm<</SigFlags 3/Fields[2 0 R]>>/Pages 11 0 R>>\nendobj\n22 0 obj<</CreationDate(D:20080923065445+02\'00\')/Producer(iText 1.4.8 \\(by lowagie.com\\))/ModDate(D:20080923065445+02\'00\')>>\nendobj\nxref\n0 23\n0000000000 65535 f \n0000000117 00000 n \n0000000015 00000 n \n0000010019 00000 n \n0000008020 00000 n \n0000016736 00000 n \n0000016989 00000 n \n0000016703 00000 n \n0000017850 00000 n \n0000017954 00000 n \n0000009802 00000 n \n0000009947 00000 n \n0000012262 00000 n \n0000014463 00000 n \n0000016534 00000 n \n0000010200 00000 n \n0000012117 00000 n \n0000012431 00000 n \n0000014318 00000 n \n0000014632 00000 n \n0000016389 00000 n \n0000018053 00000 n \n0000018137 00000 n \ntrailer\n<</Root 21 0 R/Size 23/Info 22 0 R>>\nstartxref\n18269\n%%EOF\n','2009-06-12 23:03:28','e96bcbd2-676d-102c-ace2-9cc3fca64c89','2009-06-12 23:03:28','e96bcbd2-676d-102c-ace2-9cc3fca64c89',1,0);
insert  into `attachments`(`id`,`category_key`,`description`,`owner_id`,`intern`,`mime_type`,`file_name`,`file_size`,`file_content`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('18119e4f-07fe-4c15-bdce-927dfa69eed6','certificat','Test zum LÃ¶schen','0b0b7658-2ddb-11de-86ae-00301bb60f17',0,'application/octet-stream','grid.csv',0,'','2009-06-19 16:36:28','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-19 16:36:28','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `attachments`(`id`,`category_key`,`description`,`owner_id`,`intern`,`mime_type`,`file_name`,`file_size`,`file_content`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('0c05017b-0f9f-4bc3-baeb-c1f8eb734120','certificat','Test','0b0b7658-2ddb-11de-86ae-00301bb60f17',0,'application/pdf','20080908.pdf',18806,'%PDF-1.4\n%âãÏÓ\n2 0 obj<</P 3 0 R/Type/Annot/F 132/T(Signature1)/V 1 0 R/Rect[0 0 0 0]/Subtype/Widget/FT/Sig>>\nendobj\n1 0 obj<</Filter/Adobe.PPKMS/Type/Sig/Contents <30820ebb06092a864886f70d010702a0820eac30820ea8020101310b300906052b0e03021a0500302306092a864886f70d010701a0160414a3939baa417b8f344e2be299336cd946bf5fcdeda0820d1d308204b33082039ba003020102020302f2f9300d06092a864886f70d010105050030819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033301e170d3037303232303135343535385a170d3132303232303135343535385a305b310b3009060355040613024154310e300c060355040a0c054241574147310b3009060355040b0c0249543118301606035504030c0f424157414720502e532e4b2e204147311530130603550405130c33343038393431313432383930819f300d06092a864886f70d010101050003818d00308189028181009f18e18acafeced47e87948327150690350f940b496d3d832b80568e1578be143898e395bd10d5863bab7400f0b3f7c41052e298169f96ae84f3931805bae5fb150b191a80cee84538a05cce009c4457f3b059e34f5d9668a7317ced1a8db2ea88079ce7685599fd678ebe8a41154bdb18ec239098f4ee063944d4142ccb585b0203010001a38201bd308201b930130603551d23040c300a80084191691cbfadd898305506082b0601050507010104493047304506082b060105050730028639687474703a2f2f7777772e612d74727573742e61742f63657274732f612d7369676e2d636f72706f726174652d6c696768742d30332e63727430580603551d200451304f304d06072a2800110107013042304006082b060105050702011634687474703a2f2f7777772e612d74727573742e61742f646f63732f63702f612d7369676e2d636f72706f726174652d6c6967687430819e0603551d1f048196308193308190a0818da0818a8681876c6461703a2f2f6c6461702e612d74727573742e61742f6f753d612d7369676e2d636f72706f726174652d6c696768742d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f7269747930110603551d0e040a0408487cf8373c3f8bb6300e0603551d0f0101ff0404030204b030220603551d11041b3019811769742d736963686572686569744062617761672e636f6d30090603551d1304023000300d06092a864886f70d01010505000382010100776e67497156b2bcb608d9de5dcf85efbaa18859128f1934e0ee4ecab2f94ccee454e8610e6be5b6c37eb49e4c8026363d107ce5b3e62bea1b4927cd9ed152d430a77976215fabbe75b27a8f452c3f1e8943bc28339dfc241c2a3fc66872919067c1420a7e010933947e324117d118bfa2cb732d845c80b3aecd37ee481323fe9166bdad16d619298ee502c5533254e8bdb1f90ab97c9a4c343deb368f3def13c4ba4d66fc4861f5af516cd6d94e1b5ebddf5e8eceb331a7fb2c9bfb0d210c0d8639f81ffd9d2cbf50e50ba200828415e8dabad043cbfd95355915ab7f3542ce80c6d90b5d1b72d1d03c9370e7c9f90909b76f870ebdf248b918da2e705259d73082048f30820377a003020102020301aaed300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035313131333233303030305a170d3135313131333233303030305a30819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100b7e7da22b5b1e490481d12b44f78176471844ad832ddc5f7279fe488ba6484633595b1c4537213ca4b36fb7f56ba1073db079599b0625a5035fd29f4eab0934ca3eedb0122a4de0b0b571359a98dadd86fb2f07bd111861e8c3388ce302470142bb7e9efb8152519854942312ec809f5f1fdaf1b463754dbd91462a73caf00572714324d14c36d516cadef5f9b5b6cf810fe471990de1cce2e551dea4cbdfea1030c0ba098fe28bf73c66f23c8f2bcf2a0812808cc342a592ac121c9a9f0d428d86d5987b38e50e56e8506da83712e88ab8982e93b543c6b424eea07eff24bba1129d3be68869f3bbf77d297378b2630699140cff54f13bfb9767ae0f4b755150203010001a381e33081e0300f0603551d130101ff040530030101ff30110603551d0e040a04084191691cbfadd89830130603551d23040c300a8008446a95675579114f300e0603551d0f0101ff0404030201063081940603551d1f04818c308189308186a08183a08180867e6c6461703a2f2f6c6461702e612d74727573742e61742f6f753d412d54727573742d6e5175616c2d30332c6f3d412d54727573742c633d41543f63657274696669636174657265766f636174696f6e6c6973743f626173653f6f626a656374636c6173733d65696443657274696669636174696f6e417574686f72697479300d06092a864886f70d010105050003820101000d3448690b4fc283d2ebf4e9c8184ec38c001e4fd13388242d3efa5113d8dac5078a1c6acbef2a10494df9fe6523ad66c823f72054c295f95c1d5475402a0c834648e7f11c41323c0e0779890917b958e1df00a3f3b29ad93bbb7752be8bd549e0147fa61fea98f291ecbc7f3ed013c3fbd6a38936adbb79a354308b167b08fb7e045f7109a002e2e2f46c1d0cf4cde2f56d9e3e6a97e96201de33244fd1500b42a284f3dfc3fd7b29d74d8b8ac530c1224f22af253b53a839c80305964eab0bd46ee8c4c32d9e761a296310ea04223a2473098ede7dc022ff7630517e942d6e73c6a6a936cd6fa846d21ac40497578597aec70591d800f7681c6527645d0a69308203cf308202b7a0030201020203016c1e300d06092a864886f70d010105050030818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d3033301e170d3035303831373232303030305a170d3135303831373232303030305a30818d310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831193017060355040b0c10412d54727573742d6e5175616c2d30333119301706035504030c10412d54727573742d6e5175616c2d303330820122300d06092a864886f70d01010105000382010f003082010a0282010100ad3d616e03f3903bc0410b8480cdec2aa39d6bbb6ec24284f75114e1a0a82d51a351f2de23f03444ff94ebcc05239540b90778a525f60abd4586e8d9bdc0048e854461ef7fa7c9fac125cc852c633f0560734905e0607895104bdcf91159ce717f409b8aaa24df0b42e2db56bc4ad2a50c9bb7433edd83d3261002cfea23c4494ee5d3e9b488ab0cae6292d46587d96ad7f4859fe4332225a5e5c833bac3c741dc5fc66acc000e6d32a8b687360062779b1e1f34cb903c78887405eb79f5937165ca9dc76b182d3d5c4ee7d5f83f317d8f87ec0a222f23e9febb7dc9e0f4eceb7cc4b0c32d62b59a71d6b16ae8ecd9edd572ecbe5701ce05559fded1608810b30203010001a3363034300f0603551d130101ff040530030101ff30110603551d0e040a0408446a95675579114f300e0603551d0f0101ff040403020106300d06092a864886f70d0101050500038201010055d454d159485cb39385aabf632fe480ce34a334623ef6d8ee67883104036f0bd407fb4e750fd32ed3c017c7c628ec060d11240e0ea55dbf8cb2139671dcd4ce0e0d0a68326cb9413119abb1077b4d98d35cb0d1f0a742a0b5c48eaffef13ff4ef4f460076eb02fbf99dd24096c7883ab89f1179f38065a8bd1fd37881a0514c37b4a65d2570d166c968f92e111468f1549808ac26920fde899ed4fab3792bd2a379d4ec8bac875368424c5151741e1b272ee3f51f29744dedaff7e1929981e8be3ac71750f6b7c6fc9bb08a6bd68803918f06773a8502dd98d543783fc63015ac9b6bcb57b789518b3ae8c9840cdbb150200a1a4aba6a1abdec1bc8c5849acd3182014e3082014a0201013081a730819f310b300906035504061302415431483046060355040a0c3f412d5472757374204765732e20662e20536963686572686569747373797374656d6520696d20656c656b74722e20446174656e7665726b65687220476d624831223020060355040b0c19612d7369676e2d636f72706f726174652d6c696768742d30333122302006035504030c19612d7369676e2d636f72706f726174652d6c696768742d3033020302f2f9300906052b0e03021a0500300d06092a864886f70d01010105000481806452bd278af0c176438b49ad48756ef60aa7d0badffe78407ee18e1b9dbab2036362b5314ac3dfe6b2a29164063ae49d6346fd126c4f8784c363231d9987565076313d509b3321bf733910119405ebb8903601f87e721a425ebc2075284692f6706b7aecadf8f6a2f4f0c4cb9d17aaf87ce597e78e5b7aef4807be6de1d9fa3800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000>/M(D:20080923065445+02\'00\')/Name(BAWAG P.S.K. AG)/SubFilter/adbe.pkcs7.sha1/ByteRange [0 164 7844 10962 ]                                                             >>\nendobj\n4 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1530/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛRÛH}÷Wèq·Êæªí›IHŠbI8qÕ¾	<­m™Õ…İâC÷òyØdK¶Ç\ZK„|NO_NwËƒ¿aø\npç¥¾È\"$Í+\r	Šür·Ú¹Ô}CîV!âà’ôĞí\'ç—«ÇÙï×ƒ/ƒÁqë8FÜ Pª~Æ:Á\nÕŞ$¢o¯­#µu§“ÁÉk.©n\n&óÁ	L›B™Y×†HTï]\r~¹øüiòyüõæ¯\r&˜y—;›8ø¢QÕKÇhXó¶é¢c$$7àÒËÁ	CJTÒ^1§Ë¸(Fµxíça…{<Ü÷,\nE‘‡B\nïr<­Ó·=‰`6Ó~î$ù~¥­ÀNäŸÖ«7=‰`E{,ØÔ¥9¡òó¨!QT%€ˆˆ¦vÖi±NËÕJg‡ÙÌ…°ÿ(Œ³†¦…>ış•éıadˆã/¨6ğïºx.tŒËü¹tã{.è!ÃÇKƒ^9\'/âtvÄ7¬ß7M?€¤›\n/ïĞh»ŒW½È\\(!ƒÓóBÿã‚9âÒDE]hmø©Î^ L¬?”GˆW§€R›*ÀXÒ(ğˆ2â0÷q:¼öõÚê§A¤iD›AÔ‘ÊõTNêŒoc¥phœ\',L#û~FnPîQöMjsdS<×:¹{p¢âşÄK¥ØE½Œ³E™;aÃşöDÃÈ„ÜfœÁ=p(” öÈ^…arÙ}_fwùİCšEÇËÙÚ­ 4ôÈaeµ»C2ÿá²<DRöƒ†Ø6è,vûØ#Ë˜İeN;±‡Š pòKgE¼t\0/}Ã‡¼z«‹ìû½Câ\0XôË2gÜöõğ£«ÅzJäÀ¾gû†?ä*:€ıJøÁ0»Ebˆ±Ó¿²¿¶…,¸m€u!k*÷«dn\'äj¼(J½\\ºCĞÔş&ÔÊdù,Iq9Ï3ëìÉEà•ÉLâÚÕm‚ã™ì×<£zêZşÚæÉëºkƒVSƒ;1Bx‹‡¹Ä*„…Ö!ùÆŒ·¸û·ãõK§÷¦OwÈ(¢ümútv(\Zº?tqŸUm[BTZu­FG,ã{ıÆbŒÔ…ÕærúĞé•¨MäÛ6ò°$ü_‘o“1$¨+{¥OÛØF¾\r;ŒèÈ×U0ıÕ6ô$´|†æ“.;Oğ\\FQ»»™¸<\"{­uT›êc™ÂrâökX˜Ö·ºôê8·Y ™éëüMœÛ°C)<ÃL0L!ı>ÚÄ9bvB­+<¸şq·˜g¦ÎefX‡ßn@\\à6ß·-Îê5µmÈü<W_- f½êŸ7\'Wá†ğf=×™=g\nG.‚Û$Máàa0]W˜é•Íÿ¸z¨Àø%óbé\\fLØ¤V†ÛãÿÌt–?fÚPƒ8ÕÙBWög	Ì)zÕÎ°¡Q+üçï¼ck¦·~Ğİª—·Y>_İ\\Œ\'Ó©wT9H§‡˜(»¯´©ÎOÇŸ^­ÏÉÌÈ£vO40nÙ|IÊhè|(Ñœ.Üù¨2ê%‡Ö»C®ñtìx¦M0A¢2”¼ió-Ø+tƒ.ĞĞ\rì±ùGÍ×±WWrâH•¸ıõBˆ:\0}ıî’8-Æ>í•42ÔÁàÀ9¯\n¼¿Ò,Ò|xš¸ZÜ¶\0Â:E`@Âı<›ub3yØB(EaæÁÚCÄ.ì±§-¦mO\Zsq}¡Â]¯óÛØm±_$MŠŠ]h\"%é‰ÚØ»ğ&ˆ!Ì•P˜Î¥Gı)û9BôC¢ouv›•iO‚´42¬zĞ¼?\"]ÖZ!Y-aÕ£¨o¡È\\ÔTÖ„¥ Dº•ğ–j›wï1¿¼¼ÀCh¯¾aJu…ÕÉùî?`šŸÊ°úz7ßèm`å18ÓE0ÿ‘ãìV\'LF³`šdÌñÜşù/Ì±v‚Jàª®`vÎ¡tâ<‡½cQ€ÜC+_.í›öğ`‹e\nendstream\nendobj\n10 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤*¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0K°\nendstream\nendobj\n11 0 obj<</Count 4/Type/Pages/Kids[3 0 R 12 0 R 13 0 R 14 0 R]>>\nendobj\n3 0 obj<</Type/Page/Contents 10 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf1 4 0 R>>>>/Annots[2 0 R]/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n15 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1664/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµZÍRã8¾ç)tÜ­\"B¿–|4a¦26	Pµ·˜Ÿ’YÇÌNñ ûû{Ø–ÉD±¼qr	1ò×­îşúGvïÏÅ>ˆ8_«‹<ÆÊ|³ˆâ˜Ã7/µõ}÷Vë‹\rğ\Z+¶iùá—ÑË÷Û;‚N½ßz½íÚ	‚…AaLcÿí$—8Òş£0Û¸|¥]iw4í~*Õ¥Å\"4½ëQÂ°”hú¶]’yiÚËâŞ—Ş/Ãó³éyr9ùıòôW4ıÀÌ]l0õÈ‹cÌt£8Î¢•\\[\\¼MEQ%BeÊ±–…U^12gy¾}3Ú’CY³NpôA\rİ‹Æq CcMê2~,æİîDò2Ò)\"t\'àùfB…Éºç,^:İ‰äÇâ‹^šêpçğ\0îH…ãbKRb*×ÜYÌóÅüõå%Í6K3¢æ­pÁ×b,ôë²×ùıfdğ-øaMó·<ÍPòº|{õã(.Ù&Å“gƒ^g™Ïæ·[lÃ›m³®@AV1üõæ÷§3Ÿò\Z*@#²ÊMÈ`ôeşô¤J©‰ÉÑløë4k‘™x³˜ˆ±(v1¤+¢X2pŸqO–‹€$.ÔŠÊ6üàrì\rËAt]ˆ!é-Ì\rÌ\n‚®\"ŞÆ6™Â“ãa¡ùh‡ƒØ*h¿m!±Z“gœ>Ş<xQIsà¦JÖQG³ìéuÙyQy”Ã›V¥>#ï‚Lşõ8BÂ¨é-mØ£ômöğÄD£YºÌ³…GU°‰fÍ%Y‘Æ–1:>$Åüë‡å²^\n¶êlxá‰\n©S6§r)E™aĞÆû>k9ñ=M…zšMİœ¶+/RúûùüÓ`ŒN’³át38ö„8+Îjèíä5\nf2ªÎ<~ÕUA\'\"TWÃ‘\0]5ôŠL×@‡Ã—#D”À\"£¢78¼AEgô¶a÷Eo[†EoOIlMo^²èmƒÒ½C½ÈÀ‹Q°iÌ-/^ ˆÇ»·`!©#Á£YÈ„®ÁvÀB¥X\rXXØ×°öÌU\',¤ZXşÛ’›á,t`ÇéÍÃÜ4¿ó#Óˆ1É}DŒ±hpÖDtÄVDôq¦%xŞĞwDÄ`G.mAD%m~+\"M‰Š¢XEègù}‘åè>]d÷hù×âÛâvÖM`Âô´ˆŞ78°_vkb‚µ5Lä%¿‰í€Œ§Q]Sp1äl4J“éø%S¨Ä{±š›G¼ˆ¯ÂÜ–d,m\r‡>’\Zµ!©\ruR‘t|\0IyTÓÔíÔKmÔîÊ¥Õ²3–JeåÁÙüiñ2ËÍ9Ìól~‹vóª)t¤øãœS,šUg$Â”Š\Zjç>`ÈpÍ¶®l\"ô;nRòå=¸»é½^ƒr¦önft˜Œ§SàSá1©u{œL&Éñàl:N¾ÌÁørœ ãä\n~Oèòlxv~}ÖM*çÖ}`G¾»§Nß‘auú¾™»mƒaÃSŸ›Û¦.îò²«ÔêE\"°n®>•™5iN.’NæxSTÜŸ±Zò*cÌÓ‰·N&lƒ¼\n#Dá¸b÷gÁ¶ô¦ÖÈ¶Õ´vô¦ûä½eG­‰\rO¥o‚mËoõ ºâ·ãÆ-ü†fœ·(ÃÄša?ÆW_&°é±pÛƒ:½3~Û »ëZñ;H×¶ü&|3¿aÏ´›ƒºXÓ›‡ÓÛF5÷Ao[†ÅnÏ‘O[v;véjî°A»;U¹×…±Äqø(­­“ärz>Jv¬Ú0 GZ¸À~NÃøĞTÃ0ÂÀå&\"b¨ì¬Qa¢ ‚÷}m];Â¨h„±Q÷E[†EßQcËn×±KG|Q{ÓM·jÓ ÷VÖŒì!š\\ÃDTA°Æê€fhÏ8°~¬Z½ibVˆ‚¶5¹}êª£Ë×Y éµ\Z\'W\'îæ‚bá rõ»ètˆ’Œú‚.”ãA°ÒPÇÖÚ1\Z\r¡–RB/®ˆˆ”¾ úğ‚öl¥ßm¥0kJyù®”SE*—]\'§èOğ#sÈ‚îşÍP’}Ksô:¿E×Y¾¼y˜İ•?ÿ^æi–™Çîp5E‹e¾ü>ËfËeŠ’§ü1ß§Ëôù¹¼éãş„uI_\nendstream\nendobj\n16 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤)¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0t±\nendstream\nendobj\n12 0 obj<</Type/Page/Contents 16 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf2 15 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n17 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1634/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœµYÛrâF}ç+æ)•TÁxî\Z=Ê6‹)Ìe±°«öµåØ±\rWÊß—oÈä3ò–„Ä4\n\nU[`­tº§§ÏéîQã÷Å>ˆ¾¶¹½ø›)Š}?g{÷·ŠQàn­°8¯±Çİ~ÑíÏ~<=t½h|m4N{\'1\nc\Zÿ¿x\'¹ÄJü‡ÙÑÛ·ŞÑ­w—aãâKê.MnBásƒ†¥DáçiWXæih–É³³ÆÏ½á “»o“Î/(ü\rÀâ§b°vh±çû˜éRsœ©­]ÓœÊE*·¡¥6åXËÄ†—^‰m\\¾O×ëÓ‹Ñ†ÊÊíp‚Õæº}ßÁ†ÆšìÛøXÌë]‰äi¦¬×•ÀÎ—Û*LîïüÇbVëJ$gØ?È/ó2^¡vßîÀéa?Y’”˜ÊŒ;‹ùz1ßÌfÑò¸µø‚*_\n<3c ?üı²ÜÌ=;ÁE~˜À·Ñús-Q°Y}nìøKvÌñà=FO‚³ZOçO\'bÃËc“Õ  Ë¾y|Á­pjs^C(Eö „ò2}µş°H%ibrK4ş!ZVP&^¾LøX$«€Ò9ñX¸Å8µn°pqám©lÂ·\'c¨›Ñ¬|=Ò\'˜ë¨\n‚n3ŞÄ•Â¢q°ĞÆ¡éÛA…í³Ô{yÆÑëã‹•”\'xêÉ}Ôştù¶Y•eJ3rXcyÁÊİgdg²Ş¶”@•;-MØ€¢qûêf0XºGŸ©ÎËD\'úÂæ4ƒ|r:£vt!‹³p“,(êÊ¹Ø…nï<TÅ·½Œ‰ªç°AJI--ğÔÃÒA1$Ù\n’	?ì\\ä£­ÙqšÕ:íé i/™=H–WD)EZ¨\nHM_·ÎÊ«Ú»’¢I+Ô(Jw°*}\0>p‰*æ£şğ²{Ûö4òá¸œµ–œ×0Vì–¢NğÚÃº<òùRLØîêLÚƒğ¦m©(ü£@9ÛC·ò(å +Lª=PB©\Z¡GÉîbdå1ÙqÑ´0éƒiÊ4w(.9Mèûëœ‡–IÌQE$üĞsfK‘ªä4A›„ÔEÎ‚¯\'ÈY(ÔçnD‰…—çrF”lÜ«O_Ş¡5Eıi´Z/v]QåÛ—gGÁHÿ*ËJÎË®ÒÙ¬\ZbC­˜ÔúÒÃy©Óœï£;ØË`\0:„ˆk\rSD/‡m’Øy/@íêIJÏhÿ˜²éò1,_Œ	K´ÚşQOi\n\Z}wÃo¨İé´Ç¨Ó¿¼A?¡«áYë‘°;G,c_Xzo*„vÉdâ6=^S.™¨0Ú‹\'Ñs¿R;ØNû&¸\ríSª®€+=ciw¯nP?÷&w¶¤a.½`íÛ®äßŒÛhÔß\rÁí`œô1 \05U»‚©]µ£5‘*şáBªŠJoÂşoJo\Z1”ş¼ib§ô…ĞÔ¦ô&jJïº•“cJO´¿Sz?SzÈ[*&%£fwŠ­],	åGùbLØ»Q0F×°\"‹Ü¸¶éq¶xûà¨®>½€*8ÕiŸî××§,Ä»èz×¤\\&´¡\\¶‚T±O/À[å°*=MĞ&cuÑÓ„=)³ÕˆBÍ1Ù‰âÚœ(&ìmô=²¼±‰sÍáT0eİ¥ÄÏF[0~û!iÕ3 ‚\rE8aÌ#Ô§5àwg@ÔÖºUÍi^ØˆR5§MĞ¦–uå´sòA?^¡µ\"…‹Rš6Tğ“Cÿ7t¿h-W‹y4ÿ€ï×Ç—(~­„‚óNR3RùÂmYTaåŞš¨ÎŸ£9ºZÌfı©íÅLEÁ5mzkKıŠı‰Î­íPÅYÅmZA+ffaù‰Ä„Lsµ9ÿ(LâËaT¡Q·w‹z“±¥È^Iäoi“1)1¾g®E‹^èôU0ì‚Î¬_ß[Îãs•Ûw@é)¼\'µ•~¸vZnÀÆ”dŞÑï÷ˆ\0Á§£{\"”§GD_ŒhË¢o/ãóXæğ*„òìL=erÚ´>4Âw¸‡Ñåtş†ÿY¢`ù=z]£Íü	=¼.×«Ç—ésúç_«u´\\Æ¯¬^áj„F‹Õzõcº|›®V\nŞÖ¯w½¿§.á_¼›Q\nendstream\nendobj\n18 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤+¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0²\nendstream\nendobj\n13 0 obj<</Type/Page/Contents 18 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf3 17 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n19 0 obj <</Matrix[1 0 0 1 0 0]/Filter[/FlateDecode]/Type/XObject/FormType 1/Length 1504/Resources<</XObject<</IMmpdf1 5 0 R/IMmpdf0 6 0 R>>/ProcSet 7 0 R/Font<</Fmpdf1 8 0 R/Fmpdf0 9 0 R>>>>/Subtype/Form/BBox[0 0 595.3 293.4]>>stream\nxœ­YÍRã8¾ç)tÜ­\"B¿–|t 0„dC€Ú½e3d\'?³¶Cmñ ûû{Ø–8rÅòŒ9@pì¯[İ_ê–;u(&ğƒHåÏæ\"±2Y@qÈáÃËbïşnõ*\nÜ­,Àk¬Ø±ÛÏo‹ï_^	º\\u~ëtN{\'…1Iø#ŞI.q ¾Q˜½}ãİx×›tÎ¯\nwi~š¼v(aXJ4ù8í\nË¼m€eşì¢óËíğ~2Œşx¼şMş0ó”ëOöÂ3]k³`c×62BQPÚ€€ĞZ‚r¬enCWŒŞ|še§£-;”ÕÛáv¸ïZ4C\Zk²oã}µlw%’L«X¾+Ì×Û@&÷3ÿ¾Z´ºÉøEL]šjÿäpÚ‘\n‡ù’¤ÄTnkgµÌVËõb\'Ç­™AıR¸à[3úó¿oÉzùõ82d‚‹õaßÅÙG\'(Z§k7¾‡ã’s<šô<8i6]~9^›í~\0%ÈÊ\n_¿¼áîdêr^ÃP‹¬ „ò2=Íâ¿B‰ÀÂCš˜Üš\rÿ\'\r”‰×g€‰‹|À!]V!ŠuA»ŒSg‚…‡ˆµ)e¾ÿ8v€úiİnDŸAô‰ÊõTA7Œ·±R84Îº‘Ã8œ…nPáQö[j‰Õ¶xÆñìåÍ‰Jê‰*¹:˜&ßÖió‚‚y”Ã\Zë7¬Ò}Fv† !=RáÓ¦”eiÃò¢;\"Ô0›@\'ƒ®û÷ıqtwƒúã‡›‹Oıñãı5ŠM0Y/	R°Í>o,S=Y/ÂRŠB+ gRt°~áß	Œoü7¬w·Œ?¥;Ø»øs¼Lßã$vÆîm‰rê’Ò¸»ÇQt¢\0(lù*ğF§!ß¡†w˜Òà8Ä¨@ç„n<Ã^0¦bŒˆ€H\n©”*,=ôW’BŞ+ğÃës’ÿh\Zü‡¶%Lµğ¤óiËËØ°½øcú6æ Á4N³dåLP÷]`lƒ‹20ª!¨À.Ğ†BPmO¼³ßéúÎ«Ì¢²6’OÃ«ş]F÷·“ãà6`]ŠB€¨z¡ŸÊ\Z…\03ìÎîë«‚VŒ__MxøªaÜczô–£<qøq`nÙRyÃ„d…ÃIŒ0ÀBùóÂF½ì»Ê9ôé–ÍmÁhp4ˆú“ñ†Èˆ¶+\"f\0i ×¶:OdšRvë˜észtt¥*ÙğO—¥*¹vƒÓZ¤‹­jlr¤ 	‚Ü–\0UxFOv\"MèÃ÷Vºa™Æ²¶ä¯\r;Âøw/¢ñ%Šzcè~Ì4ÒZs`›\nÌw¡fT3Ûš66¼Õ8ãä·ò 8`©ÀsÕR7\\A=ƒ¶8èKª1m vP„Õ\"óKAÌaî(úıîæªz°1¢ëAïS;Ä7|Ö™¢şØ›å;tc‹x™æSÇYcc>Úv|d.ínÚ“ÙğÊÚ”6èiM}SÈBLı\'Ê­™ú>s	ÌB¿3ñ¥š1‘QO&zQ”Ë°a³8‹]ÑaĞ5  {Õ/	è8k,ˆ6<“m1ĞFm‘Ş©ƒüg(µFwpªsA$¦\"s\Z\'èâ-™¥ÙÌ),\rYH¸ßR„×Ñr¹¶?[Î§_Ûâ¡lñıT8$¤öĞqj^‚´ÄD·E&ú¦¯™†ÖÔ+¸RÅÎL\Z*hÙë¥p{şY¾2wˆœ>{»´êˆ.Ş;,èrzr›ŠùƒÊÍsZ+qhÀ-ıô=dë-ƒÁ-Œ¶”ĞÑÒ#¢ÏG´{òİ¦õ–\\Ã¬”òb†R[ë½è9ºFEçzÓå7ôú_‚¢äs<ËĞzù=Ï’,}y›¾ÿş“fq’˜DÎàjŒF«4K¿C~§i\Z£è[6‹—_ã4Ï‹‡—ğ?:WŸ°\nendstream\nendobj\n20 0 obj <</Filter/FlateDecode/Length 78>>stream\nxœ3P0TĞ5T0P0µ4’É¹\\…\n ®442Ö36U045Ğ³\0Ê(èG¤™(¸ä+r*”s¡Èuë+Y\Zë™(¥rs\0Æ³\nendstream\nendobj\n14 0 obj<</Type/Page/Contents 20 0 R/Parent 11 0 R/Resources<</ProcSet[/PDF/Text/ImageB/ImageC/ImageI]/XObject<</Xf4 19 0 R>>>>/MediaBox[0 0 595 842]/Rotate 90>>\nendobj\n7 0 obj[/PDF/Text/ImageB]\nendobj\n5 0 obj <</Type/XObject/Filter/FlateDecode/Length 80/Height 30/BitsPerComponent 1/Subtype/Image/Name/IMmpdf1/ImageMask true/Width 2232/Decode[1 0]>>stream\nxœíÖ1\00ÃÀĞ/ƒÂ-İdßé)hÑŒôëhCg‘ê]mè,R=zBÑY¤zô „¢³HõèAEg‘êÑƒŠÎ\"Õ£!Eª÷\0hœFq\nendstream\nendobj\n6 0 obj <</Type/XObject/Filter/FlateDecode/Length 700/Height 78/BitsPerComponent 1/Subtype/Image/Name/IMmpdf0/ImageMask true/Width 360>>stream\nxœÍ×;Õ0`G)B…[\n„Y‚\r·2K ¼vÇvØ@¨(Ù‚—`D‘\"›ÿ?vîc\"d‰HÉÍã‹slŸÑÔzûşËG¶oÿş:§CØÃn6Ã°†—ax†§aXÃ~vÃ°†äèA| G?ÃrÔş	ç«ä±ê’£_jıqÅ”bˆ\n­±^ -\nqÆm\\Ö÷hÇ×\Z]ôuSÄ~•òÀ\0V‚ÙM”]Çy\"†cCÀ¸®ÛçMÇXõÄ.â YÎ/ìyéxÕ‚ÍÚ[ÆuÍ{Ì	{;®cSq0·x#:Fçùß%S¸FÇ9ã#êe\'Ş¦Öû˜\r]ğJìd\'f8gŒqæk´´“`#8.·˜×u½ÂÑb8qIŒ~uìs0ˆõójqŠ¨ˆ¹2^cp®0:°púD¿cçg–úUrã…Œ`•Ù\'%lˆè©òÀ§©Mh”Iñéù˜£ğÛ†.dŸIÁPV†@iÂ|#H\r|§ZŠ*—%7$+Ë«ZæJ—Èìñ\rûÂ†€uÃ¾a€Ó,Ø5ÌÑ\'~¹Übt/ø¬³\"æ8÷ÄE&&Å%®mR<uçâeAµš²Íib®\\ptIúÃ4vqI-7d+sš‹ôØN\r\'#§Xç[Œ&Ü\rG`Ç”üY°ábÀ¤Ì;æ\\iœ\"ùÎÉ_ë\'Ä¼pY™+ñ’™K‚]Ãh1-}Á®vi8¸ĞqŒ–#Úêw,$ qÔ÷R ‹Óê½ƒö‘V7ï¥€cwÁ…ÏT¿U$Á±—/ö8YÓğ´ñY‘Z×1ŸIadOw¼l\\?áÄã³mRVÌ91zœ­ëogY’o˜ŠÄõ¿‚ïoÜ’m³ş7Oşû÷ö®¯à×GğÇaøĞÿVàŸ‘\nendstream\nendobj\n8 0 obj<</Type/Font/BaseFont/Helvetica-Bold/Subtype/Type1/Name/Fmpdf1/Encoding/WinAnsiEncoding>>\nendobj\n9 0 obj<</Type/Font/BaseFont/Helvetica/Subtype/Type1/Name/Fmpdf0/Encoding/WinAnsiEncoding>>\nendobj\n21 0 obj<</Type/Catalog/AcroForm<</SigFlags 3/Fields[2 0 R]>>/Pages 11 0 R>>\nendobj\n22 0 obj<</CreationDate(D:20080923065445+02\'00\')/Producer(iText 1.4.8 \\(by lowagie.com\\))/ModDate(D:20080923065445+02\'00\')>>\nendobj\nxref\n0 23\n0000000000 65535 f \n0000000117 00000 n \n0000000015 00000 n \n0000010019 00000 n \n0000008020 00000 n \n0000016736 00000 n \n0000016989 00000 n \n0000016703 00000 n \n0000017850 00000 n \n0000017954 00000 n \n0000009802 00000 n \n0000009947 00000 n \n0000012262 00000 n \n0000014463 00000 n \n0000016534 00000 n \n0000010200 00000 n \n0000012117 00000 n \n0000012431 00000 n \n0000014318 00000 n \n0000014632 00000 n \n0000016389 00000 n \n0000018053 00000 n \n0000018137 00000 n \ntrailer\n<</Root 21 0 R/Size 23/Info 22 0 R>>\nstartxref\n18269\n%%EOF\n','2009-06-03 11:40:04','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-02-17 21:21:57','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `attachments`(`id`,`category_key`,`description`,`owner_id`,`intern`,`mime_type`,`file_name`,`file_size`,`file_content`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('92ba9435-0fd9-4011-a9e0-75c94ded2e74','certificat','Test','0b0b7658-2ddb-11de-86ae-00301bb60f17',0,'image/png','Button.PNG',4578,'‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\Z\0\0\0;\0\0\0+VÒÊ\0\0\0sRGB\0®Îé\0\0\0	pHYs\0\0Ã\0\0ÃÇo¨d\0\0\0tIMEÙ0-ÄÂß_\0\0tIDATxÚíYl\\×y€¿sî2+9ÜÄ7q‘¬…²VËŠ,oŠ-9‰[;µc7i\n´H‘¢@ƒ¼µE_úĞ·¢E‹¢H‘\"éC“6¨;‰\'ñ\Zk±[b´K\\ÄU\\†CÎz—sOHÊ²#Sô7Ï÷Æ¹3s‡?îwÿÿüçÜ{EE€ƒÁğQÑZÒÂ`ø¤VEQ†¡RJkm2•Á°ÂŒ$„°mÛ¶íkì…\raçr9­µeY&RÃmQJY–µfÍšl6»`}cÃìììèèhE¶m›H·%Çq\\×mnn^xÅ¾9sEK˜H·%Š¢…±Ò\rŞÕIJiÛ¶ÉNÃÊ±m[JyŞÓ ¸¹¡5 ¥Œ\\7²,Àt*wrƒaA€(’A ÂP,äŸßêÏ½//-§Ó­SJŞ$˜ÁpG\"äÑú#ç ÓB÷Ü²´mkÛÖR:ssuçÎ%&&„Ö‘©\rwrzÒ2U,Vèé)vu©XLPJ†¡Xh+|ğLÒ²b¡-K¹®ŠÅ’££­/¿œ}ã\rEa\"abn¸c³“Öv©ä54~éK…0™tJ%±²Luk´e¡u,—K\\¿.Ã0ŒÇkêÎåó€;?o‚n¸³qJ¥†3gÊ­­a*e—Ë~&SÉfÃdRDÑ2jİZ\'åºB©šşşu/¼\Z		Ë÷&Ê†ÕCÃ;ï$GGe¢õÌîİ×|Ò¯¯—¾/ÂğCè$@Û6Rºssõ}}n¡ğ¦‡éìîì¡ÓÒAÏåâ¹ÜÂ‹A&cU«‘e-¿Ï¾E[\"ÇÑ–&‘ëšø\Z‘”¬`-ëûuÒBh!œBÁ.—c³³ZÊß–Í`¸“[·ÔÉuõGĞ)ŠÅPªáôé5ÇÕŸ=ëÜTé†£“‘ã TæâÅuÏ?oW«f¼d0¬yËq˜‚—ÃG×éF+\"L&Mt†QìİTõéeGf†ÿœõ=4·ÑİD&bìm†ÏSmÄŞL6Kg\rÑC\'›d*&^¿;:~‰×Ñı({á©lğyıyñ¹v’Ègîã‰.ü¼ğ÷¼>É£“ÑéÓ«p¸Ô×ÓVK}a¡Á’HĞšPQÍS¾N.Ïx™jø	íÔ!½–LÍ2’ê4¹rEráÊV.[.©,Ù\rlê º×Ğ*Izí½lN¡6r*E-8æÀ6:}zUS#\r°çİÆ=m‡bD’J…©3ş‚c§x¡ŸÑOhªÀMÑñ ;ph+ÛL¼ÊÑÿáØEÌQP+ú¡<¼2% ¢â@ *T‹TSx%<Eærj£Ó§ší´lgïƒl\\X_fÎ£õõ$fF*PåíÉOL\'-IµÑº“İ÷²f§™}‰+Ö‡;öuDh\"ŸPÖD\n@¢4‘ÑÉèô©¢ÑŠ(Di€à}ÇùéoÃ¾ùÊçéXCÛ~6Í³ñ4#ãLƒ÷±÷éU)U(•©(°¨–)((¬ø\Zµİ°M !0¶ÑéÓ³I£CT€B‰©s\\z…Ÿçˆf6Áşı´×\"ÚYÓFmœØP¥H¥p,,½ÔîT ğ=\n‚ì	—¸uÓDèS™\'“¥­†&ËG\'PqÒ´çÙ2Í@ª¯%À‘XzQ{\"tH©@1$TDúvÿ—Â‚ZA©Ç\"®ñKx%\"’8.q¡Ê!ÊA¸¸1!–š¸\Z4BTJT”\0—xŒtÛºé”@VªxŠ@V1B€&ò	Ê\0%pá¸* ŠLF\\ƒ…fÄİ‡¸ïI64Ğ¢HtˆE\rÓ÷şóUóĞNË.êåšjÛÁÜ¿‹ŞØ¼‰@ÃNö“­#<{—ßäß~È4ì|šıÑ“d\"&PbšÊ¯ç»¯Q,R]6ïh…_%!Øş8û°y-k+\\ú	\'Ì\\3ÎAº6³7KbšK/18ÈÌd/½kÙQOM\ZbHö Š(3ÚÏ+?åø%&°‰»ùÊ=´­!’\r>*DYÌÍ0ük®¼Åo&¹T¹ğF§;:MEàb×’^KÛ<5­$,P0J~œÑÊ¢KµMì=ÄÓ_c+4k,Q‚!v¼Â©K–HmaãçùÂ~şxëÒi^\0ä&èËP)²¥›ÎÆÅ]\'²tf3p\nüè^œıOğìÓÜH\"y¸Bsœ7Ïs6B.­[¹%QDèKÓz?›ş”Ckè‚_Oâ¿ÈX=ñ‡Ùû(_í¢nŒWÇ8^epò_èå‰–÷ü`\0Ü[ç¿ÄTrû~¯?FÊYLƒˆÅ÷ª\noÃCäç¸RYåÃ6{U»¤|¨¥a7w×ñõ<Ÿ“lÚÊÆjœ\\çÏ0:Ğ¸‡‡òÈ½ôLâå_0â0Ûºørİ{xl7ñ\Z’ØøÛ×ÀCçùÁ[LÎ‘ˆQ)01„›fG’µll¥YPäÚiF/3|‹s´?Æ¶\r<º…®*—_áù£Œ6àmàánvr÷gxêİóX5„ŠP-Wï!°]b	š,	l‰íâ&H‚•$îàZØVœšz\0ÕÏÉŸ“O ÷ÑÒÍÖ\rt­eGÙû©}’v’rPWxıÆBt/k7ò`±µ	\\‰%Ì”ÿjÖI p7°a=ë±¸èjn†kÃŒ‘ì{†CÏ²o\rÙ}Ÿïü+G\\ŠŸåÀèídûzŞF[\Zµ‡ô&²³ĞÇ«ÏñOÿÅõü»»K7s¶Á,ÏÔÓœ$ßÇñoñâQ^š!±‹ÇÿŠƒØ—\"ÖÇ©oóp²uá/²½‡µ›ùı]tLrº– \"ˆ–+_uDP¡<O!MVS¬â¯*æ³ˆyŠÕ¿ŠUÂóÑqÎü’ÿı;†\Z°¿Á¶CÔôÒ’¤¡‰-]ì¾öGÙéÀ8Ç~Ä·ş™3<ÍÎÏ‘9È½O%$ŒĞF§Õ<p@4Áø0C×™(a·é âw³¥ÂÁ\"S)vm¦½‡X%zvsğ«tº¶rÏ.Ú!$[OaŠÑ€|™JzØñ _V\\›Æ±)N0ú6Fxä=*\n@U)çÈÍ0*¤©ÎVÒà6Ğû \'ØÜˆÚÆ­4Å¡µuŒ—‰Û¨ß°J/[Şhv,4ıó3Ì†8%Š!»D––fzÖ9fÆ97ÎY¨›£§J Ìœ±ÑiI\'+UfNğÎÏùŞ¯ùÕ8õ÷sßñÈ.}¶õÔ†Œq\Zp¡MÍìx†€’h‡„ƒ\r”(yäË\\¼L!Cóz:×³s=ÿğ¡&’¿Å‘áÂ\0“ibÖâ,]Ü4©v‰º$µ..TÀíâá?gïŸ¡,p©±\0ÂóUÊ>a„\\ê |‚Y\Z°c$R¤j±cÄ%V„?EP‡´‰ƒ[†O>\rnŠ´ƒcVt\Z–|BJPx9f¸tšqÍD?}‚\Z)ÒëèiÁ&C-ŒÒ«äÇ\Z¥T!OãEäG¸~œÑ.LSÄ_G®ƒL²†uİlj ½µ‹dŒw\ZğäRY!x!¡Fƒ”˜aä:UŸ\0|M%`æ\"ã\'˜äzÈJ,¹ÜL­ØÍBO[>¾‡\\¶!)bñ¾h(\"å ]¤ÀöA¢‚ÈÌŞWğ„DÚX64dhÏÒT‡|B…’/a•.LpâûüÇw¹p|ŠêBŸ=DU*Zœ¶ùŸ~ÑÎÁÇùÆ×Øgí&<›kÍ\\_J,Zú!@©J®À<8!a?¿ü6ßÁ…_ÄŸ\'swí¤ÃÂ‘Ë¥\ZÖ\r4*$€ÀÃR‹ÒŠ\\& B!ga’R‘\ZÖ®¡-d2BxjqÜ°ªuZ5IKB‚Ì:Úw³/ š¢çötÑic{Lrn”s#L&Y¦«—•Âu‰ÅˆÅÑK }üG’®ÅN#Cd„Œ‹kcYèjü’‰Å\n\"CÃ:ºö²×FQYÃõ3œŠÓ±‘æ:IÌ%âÅœ«JÌÆ¶‘6¶DºØÖbá\'-,\\×&Â\Za[’¶-Ü}?ÙÜ.6ÔQÒFJ¬…ÚØl)i!plâ¢ÌÄ$ş4™&\ZšèÜÎgÑâö²1K]­‰@HÌSöVkv’;FÜAú!îßH÷3ü‰GMÍÍÔøp–şÃ<wœ×.Px“ömÔ}“†\'Øúüí~¼*mc9®1ø\"3S¨$÷ÑjÑZEÚ¨­t×PãÜ«¼ú&¿ÚBEp¨ÖR÷¶sßù+¼~šÿ!WPó7<¶‡şš=_¤\ZRµ.B0{–¡—WŒ7A‹pÇÂIO;Mm¯Â©>Îa[»Úéı2ÙTÈêãÄ8®MÌÂq±$\0©8.8;N,E\Z¨!“Æœ ü™]lkaÓÓüå6f=¼\Zu¬K\"¤…0†]:Eş4¹A.ÖS¿aá;¤ZÉH´À›bî*³Ç8z”Ã—ÊÌNÒ½—ìv4Ñ±‰” ÔHËe6†<ERtÜBKw…˜&Œ‚¹OpôG¯r>„F.´3œ¤±‘bšÚ6\Z|Îö1~Š~‹hÜÂÖ\ZZ·â”ÆŠ!S>NáÃ3L\\æ|\Z;b`Š˜+\\cäºğ¯0á1W¯ p²ûï§¡ºˆG0G©HÑaâ<ıÓL”ÉM!è¯§=Ã¥Q¦\"ò>ö9-9f-qù“)ZJlİM{=2ËZ—x’˜ğÀ÷ğK„¾ÑiUêÎ0{˜“W)üŒÔ ,´Ä±°Zú³T&™`|ni,qêÇÌÓœ¢Nã´^»{yò—)‰Æ±NÆ¢6ÄÖDšTÄÌ0ƒÃŒB4‡yi„ë?!‘$„¸3Oÿ\0“U€£ßcêM.µzñÇH 2ÅÜUr%®ŸC¿Æh0ö6Ãšêö3ú\Z\'kˆ&\Zfôuô›ül˜ñ“H¢$\"‚\0­Q’ÒSçÉç(V—Ÿ¹ä.2P¥¤‘?áÚÛ\\ª#Yeú\"×G§ĞÏs¯pº‘¸D:îfÏÙ±‡í6nœpü5Êæ¦W«S\'U¤x‘âE®~˜O\rŸføô²ï8{»¯(Ãeú.Ó÷Û0päö¿äÂÍÌÀ›ÀÑ›_,ÂÎ\\áÌò_4\rç¸ü3\rœ„“¼ó¾wN1ĞÏ @)€î	Ä.j·Ğ™!•cz’‘Yò˜i\\sqûÊZ«ù‘VÎÖnbw7÷´²¨ú4­gËÃ´§°¯2t„w®p\nFÌuVF§•°š]’.énZáÑøÚÒ±w7y3¿Á;/q¢Ÿsæ(1:n*‰ğ\'˜9ÅÉ©Ô\'ñT„\n)Œ1q‚«g¹0mâdt2¬D§ò¯2fó¼ï^8¸°$?\\¸\nÓp+o¡†–ç½çªÕkT@BÙÄâv¥ñ­ÇŞQ$•2Ñ1>–N•[Z¦wíòêê\"Ë&5¥ØÓZzRÎìŞ]nmÍ>Üşâ‹±ÙY&ƒá#f\'¡u©­múŞ{ç6m2O4†?>;İø°ÖD‘ô}«T2¡4¬jUDÑmWÍß¢³§APJKé55¹Å¢rİÈ6-uÃªU*Z¿¾>²mEbÙ9ı[K\"ƒ\0­‹CO=åæóÚ²Ş÷\\ƒaµ”yaˆ¥ ¦FzŞòUßë¥ööR{»	¨Á°Xòù>Ë.ô}W§(ŠÂ0Œ¢ˆ›A¨\\W[–Ğæ\ZfÃ*eññ¶ah-¥¦2A „ˆnÊWïê$„Rr©®³”²Ìr\r––AKÉMÓ°RJñŞæÄ¢N–eÕ××k­µÖ–e™è·E)eYV&“¹!•ĞZß¨ô”RZkaî¡a0¬(ci!„mÛ¶m/X³Xù…†©´Ö`0|şšã~Ş;óÚ\r\0\0\0\0IEND®B`‚','2009-08-24 23:28:10','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `attachments`(`id`,`category_key`,`description`,`owner_id`,`intern`,`mime_type`,`file_name`,`file_size`,`file_content`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('11d5aa5f-3f00-438d-bf00-09fe4a9c3968','certificat','Attest SJO','0b0b7658-2ddb-11de-86ae-00301bb60f17',0,'application/msword','Test.xls',28672,'ĞÏà¡±\Zá\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0>\0\0şÿ	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÿÿÿ\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿıÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\n\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\Z\0\0\0\0\0\0\0\0\0\0\0\0şÿÿÿ&\0\0\02\0\0\0!\0\0\0\"\0\0\0#\0\0\0$\0\0\0%\0\0\0\'\0\0\01\0\0\0(\0\0\0)\0\0\0*\0\0\0+\0\0\0,\0\0\0-\0\0\0.\0\0\0/\0\0\00\0\0\03\0\0\05\0\0\0şÿÿÿ4\0\0\06\0\0\0şÿÿÿşÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿR\0o\0o\0t\0 \0E\0n\0t\0r\0y\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0 \0\0\0\0\0À\0\0\0\0\0\0F\0\0\0\0¹9ñ@Ÿ=Êà,zà¢=Ê \0\0\0À%\0\0\0\0\0\0W\0o\0r\0k\0b\0o\0o\0k\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0m7\0\0\0\0\0\0_\0V\0B\0A\0_\0P\0R\0O\0J\0E\0C\0T\0_\0C\0U\0R\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\"\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\ZLÙ¢=ÊàUÙ¢=Ê\0\0\0\0\0\0\0\0\0\0\0\0V\0B\0A\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ğcNÙ¢=ÊğÔPÙ¢=Ê\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0ªÍÉ\0\0\0\0á\0\0°Á\0\0\0\0â\0\0\0\\\0p\0\0\0reich                                                                                                        B\0\0°a\0\0\0À\0\0=\0\0Ó\0\0\0º\0\0\0DieseArbeitsmappeœ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0¯\0\0\0¼\0\0\0=\0\0à _v/8\0\0\0\0\0\0X@\0\0\0\0\0\0\0\0\"\0\0\0\0\0\0\0·\0\0\0Ú\0\0\0\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\0h\08\0¼\0\0\0\0âC\0a\0m\0b\0r\0i\0a\01\0\Z\0,\08\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0\08\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0Ü\0\08\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0<\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0>\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0?\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\04\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\04\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0	\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\n\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0¼\0\0\0\0âA\0r\0i\0a\0l\01\0\Z\0È\0\0\0	\0\0\0\0\0âA\0r\0i\0a\0l\03\0\0\0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0;\0\\\0-\0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0=\0\0\0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0;\0[\0R\0e\0d\0]\0\\\0-\0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0?\0\0\0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0;\0\\\0-\0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0I\0\0\"\0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0;\0[\0R\0e\0d\0]\0\\\0-\0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0q\0*\06\0_\0-\0*\0 \0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0_\0-\0;\0\\\0-\0*\0 \0#\0,\0#\0#\00\0\\\0 \0\"\0¬ \"\0_\0-\0;\0_\0-\0*\0 \0\"\0-\0\"\0\\\0 \0\"\0¬ \"\0_\0-\0;\0_\0-\0@\0_\0-\0k\0)\03\0_\0-\0*\0 \0#\0,\0#\0#\00\0\\\0 \0_\0¬ _\0-\0;\0\\\0-\0*\0 \0#\0,\0#\0#\00\0\\\0 \0_\0¬ _\0-\0;\0_\0-\0*\0 \0\"\0-\0\"\0\\\0 \0_\0¬ _\0-\0;\0_\0-\0@\0_\0-\0\0,\0>\0_\0-\0*\0 \0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0_\0-\0;\0\\\0-\0*\0 \0#\0,\0#\0#\00\0.\00\00\0\\\0 \0\"\0¬ \"\0_\0-\0;\0_\0-\0*\0 \0\"\0-\0\"\0?\0?\0\\\0 \0\"\0¬ \"\0_\0-\0;\0_\0-\0@\0_\0-\0{\0+\0;\0_\0-\0*\0 \0#\0,\0#\0#\00\0.\00\00\0\\\0 \0_\0¬ _\0-\0;\0\\\0-\0*\0 \0#\0,\0#\0#\00\0.\00\00\0\\\0 \0_\0¬ _\0-\0;\0_\0-\0*\0 \0\"\0-\0\"\0?\0?\0\\\0 \0_\0¬ _\0-\0;\0_\0-\0@\0_\0-\0à\0\0\0\0\0\0õÿ \0\0\0\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0\0 \0\0\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0Ÿ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0­ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0ª à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0® à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0› à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¯ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¬ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0 à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0‹ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0® à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¬ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0³ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0 à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0 à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0‹ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¤ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0± à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0´ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¾ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0Š à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¹ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0¤ à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0± à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0µ à\0\0\0\0\0õÿ \0\0”¿¿\0– à\0\0\0\0\0õÿ \0\0”——\0– à\0\0\0+\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0)\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\r\0\0\0õÿ \0\0”——\0¯ à\0\0\0\0\0õÿ \0\0Ô\0a\0\0>\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\n\0\0\0õÿ \0\0´\0\0\0\0\0\0\0ª à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0« à\0\0\0\0\0õÿ \0\0œ\0š à\0\0\0	\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0´\0\0\0\0\0\0\0­ à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0Ô\0P\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0Ô\0P\0\0\0\0\0À à\0\0	\0\0\0õÿ \0\0Ô\0 \0\0\0\0\0À à\0\0	\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0Ô\0`\0\0\0\Z\0\0À à\0\0\0,\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0*\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0”ff¿¿\0· à\0\0\0\01\0\0 \0\0\0\0\0\0\0\0\0À |\0|\0\0\0\0\0\0\0\0\0\0\0\0?\0//}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\n\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ef	\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\Z\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÌL	\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0 \0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0!\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023	\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\"\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0#\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0$\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0%\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0&\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\0\'\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0	\0\0\0\0,\0#\0#\00}Œ\0}\0\0\0\0\0\0\0\0\0\0\0\0(\0\0\0\0\r\0\0\0\0\0???ÿ\0_\0¬ _\0-\0\0\0\0\0òòòÿ\0,\0#\0#\00\0\0\0\0\0???ÿ\0¬ _\0-\0;\0\0\0\0\0???ÿ\0\"\0?\0?\0\\	\0\0\0\0\0???ÿ\0_\0-\0@\0_\n\0\0\0\0\0???ÿ\0\0\0\0\0\0\0\0}Œ\0}\0\0\0\0\0\0\0\0\0\0\0\0)\0\0\0\0\r\0\0\0\0\0ú}\0ÿ\0_\0¬ _\0-\0\0\0\0\0òòòÿ\0,\0#\0#\00\0\0\0\0\0ÿ\0¬ _\0-\0;\0\0\0\0\0ÿ\0\"\0?\0?\0\\	\0\0\0\0\0ÿ\0_\0-\0@\0_\n\0\0\0\0\0ÿ\0\0\0\0\0\0\0\0}(\0}\0\0\0\0\0\0\0\0\0\0\0\0*\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0+\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}Œ\0}\0\0\0\0\0\0\0\0\0\0\0\0,\0\0\0\0\r\0\0\0\0\0??vÿ\0_\0¬ _\0-\0\0\0\0\0ÿÌ™ÿ\0,\0#\0#\00\0\0\0\0\0ÿ\0¬ _\0-\0;\0\0\0\0\0ÿ\0\"\0?\0?\0\\	\0\0\0\0\0ÿ\0_\0-\0@\0_\n\0\0\0\0\0ÿ\0\0\0\0\0\0\0\0}P\0}\0\0\0\0\0\0\0\0\0\0\0\0-\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00\0\0\0\0\0\0\0\0\0¬ _\0-\0;}(\0}\0\0\0\0\0\0\0\0\0\0\0\0.\0\0\0\0\r\0\0\0\0\0ÿ\0_\0¬ _\0-}<\0}\0\0\0\0\0\0\0\0\0\0\0\0/\0\0\0\0\r\0\0\0\0\0\0a\0ÿ\0_\0¬ _\0-\0\0\0\0\0ÆïÎÿ\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\00\0\0\0\0\r\0\0\0\0\0œe\0ÿ\0_\0¬ _\0-\0\0\0\0\0ÿëœÿ\0,\0#\0#\00}Œ\0}\0\0\0\0\0\0\0\0\0\0\0\01\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0ÿÿÌÿ\0,\0#\0#\00\0\0\0\0\0²²²ÿ\0¬ _\0-\0;\0\0\0\0\0²²²ÿ\0\"\0?\0?\0\\	\0\0\0\0\0²²²ÿ\0_\0-\0@\0_\n\0\0\0\0\0²²²ÿ\0\0\0\0\0\0\0\0}(\0}\0\0\0\0\0\0\0\0\0\0\0\02\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}<\0}\0\0\0\0\0\0\0\0\0\0\0\03\0\0\0\0\r\0\0\0\0\0œ\0ÿ\0_\0¬ _\0-\0\0\0\0\0ÿÇÎÿ\0,\0#\0#\00}-\0}\0\0\0\0\0\0\0\0\0\0\0\04\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0}<\0}\0\0\0\0\0\0\0\0\0\0\0\05\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\06\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0ÿ?\0\0\0\0,\0#\0#\00}<\0}\0\0\0\0\0\0\0\0\0\0\0\07\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\023\0\0\0\0,\0#\0#\00}(\0}\0\0\0\0\0\0\0\0\0\0\0\08\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}<\0}\0\0\0\0\0\0\0\0\0\0\0\09\0\0\0\0\r\0\0\0\0\0ú}\0ÿ\0_\0¬ _\0-\0\0\0\0\0ÿ€ÿ\0,\0#\0#\00}(\0}\0\0\0\0\0\0\0\0\0\0\0\0:\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0;\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-}(\0}\0\0\0\0\0\0\0\0\0\0\0\0<\0\0\0\0\r\0\0\0\0\0ÿ\0\0ÿ\0_\0¬ _\0-}Œ\0}\0\0\0\0\0\0\0\0\0\0\0\0=\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0_\0¬ _\0-\0\0\0\0\0¥¥¥ÿ\0,\0#\0#\00\0\0\0\0\0???ÿ\0¬ _\0-\0;\0\0\0\0\0???ÿ\0\"\0?\0?\0\\	\0\0\0\0\0???ÿ\0_\0-\0@\0_\n\0\0\0\0\0???ÿ\0\0\0\0\0\0\0\0}(\0}\0\0\0\0\0\0\0\0\0\0\0\0>\0\0\0\0\r\0\0\0\0\0\0\0\0\0_\0¬ _\0-“\0\0\r\0\020% - Akzent1’H\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\01\0\0\0\0\0\0efÛåñÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\020% - Akzent2’H\0’\0\0\0\0\0\0\0\0\0\0\"ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\02\0\0\0\0\0\0efòİÜÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\020% - Akzent3’H\0’\0\0\0\0\0\0\0\0\0\0&ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\03\0\0\0\0\0\0efêñİÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\020% - Akzent4’H\0’\0\0\0\0\0\0\0\0\0\0*ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\04\0\0\0\0\0\0efåàìÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\020% - Akzent5’H\0’\0\0\0\0\0\0\0\0\0\0.ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\05\0\0\0\0\0\0efÛîóÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\020% - Akzent6’H\0’\0\0\0\0\0\0\0\0\0\02ÿ\r\02\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\06\0\0\0\0\0\0	efıéÙÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\040% - Akzent1’H\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\01\0\0\0\0\0\0ÌL¸Ìäÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\040% - Akzent2’H\0’\0\0\0\0\0\0\0\0\0\0#ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\02\0\0\0\0\0\0ÌLæ¹¸ÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\040% - Akzent3’H\0’\0\0\0\0\0\0\0\0\0\0\'ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\03\0\0\0\0\0\0ÌL×ä¼ÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\040% - Akzent4’H\0’\0\0\0\0\0\0\0\0\0\0+ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\04\0\0\0\0\0\0ÌLÌÀÚÿ\0\0\0\0\0\0\0ÿ“\0\Z\0\r\0\040% - Akzent5’H\0’\0\0\0\0\0\0\0\0\0\0/ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\05\0\0\0\0\0\0ÌL¶İèÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\040% - Akzent6’H\0’\0\0\0\0\0\0\0\0\0\03ÿ\r\04\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\06\0\0\0\0\0\0	ÌLüÕ´ÿ\0\0\0\0\0\0\0ÿ“\0\0\r\0\060% - Akzent1’H\0’\0\0\0\0\0\0\0\0\0\0 ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\01\0\0\0\0\0\023•³×ÿ\0\0\0\0\0ÿÿÿÿ“\0\0\r\0\060% - Akzent2’H\0’\0\0\0\0\0\0\0\0\0\0$ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\02\0\0\0\0\0\023Ù—•ÿ\0\0\0\0\0ÿÿÿÿ“\0\0\r\0\060% - Akzent3’H\0’\0\0\0\0\0\0\0\0\0\0(ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\03\0\0\0\0\0\023ÂÖšÿ\0\0\0\0\0ÿÿÿÿ“\0\0\r\0\060% - Akzent4’H\0’\0\0\0\0\0\0\0\0\0\0,ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\04\0\0\0\0\0\023²¡Çÿ\0\0\0\0\0ÿÿÿÿ“\0 \0\r\0\060% - Akzent5’H\0’\0\0\0\0\0\0\0\0\0\00ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\05\0\0\0\0\0\023“Íİÿ\0\0\0\0\0ÿÿÿÿ“\0!\0\r\0\060% - Akzent6’H\0’\0\0\0\0\0\0\0\0\0\04ÿ\r\06\00\0%\0 \0-\0 \0A\0k\0z\0e\0n\0t\06\0\0\0\0\0\0	23úÀÿ\0\0\0\0\0ÿÿÿÿ“\0\"\0\0\0Akzent1’<\0’\0\0\0\0\0\0\0\0\0\0ÿ\0A\0k\0z\0e\0n\0t\01\0\0\0\0\0\0\0\0O½ÿ\0\0\0\0\0ÿÿÿÿ“\0#\0\0\0Akzent2’<\0’\0\0\0\0\0\0\0\0\0\0!ÿ\0A\0k\0z\0e\0n\0t\02\0\0\0\0\0\0\0\0ÀPMÿ\0\0\0\0\0ÿÿÿÿ“\0$\0\0\0Akzent3’<\0’\0\0\0\0\0\0\0\0\0\0%ÿ\0A\0k\0z\0e\0n\0t\03\0\0\0\0\0\0\0\0›»Yÿ\0\0\0\0\0ÿÿÿÿ“\0%\0\0\0Akzent4’<\0’\0\0\0\0\0\0\0\0\0\0)ÿ\0A\0k\0z\0e\0n\0t\04\0\0\0\0\0\0\0\0€d¢ÿ\0\0\0\0\0ÿÿÿÿ“\0&\0\0\0Akzent5’<\0’\0\0\0\0\0\0\0\0\0\0-ÿ\0A\0k\0z\0e\0n\0t\05\0\0\0\0\0\0\0\0K¬Æÿ\0\0\0\0\0ÿÿÿÿ“\0\'\0\0\0Akzent6’<\0’\0\0\0\0\0\0\0\0\0\01ÿ\0A\0k\0z\0e\0n\0t\06\0\0\0\0\0\0	\0\0÷–Fÿ\0\0\0\0\0ÿÿÿÿ“\0(\0\0\0Ausgabe’t\0’\0\0\0\0\0\0\0\0\0\0ÿ\0A\0u\0s\0g\0a\0b\0e\0\0\0\0\0\0ÿ\0\0òòòÿ\0\0ÿ\0\0???ÿ\0\0ÿ\0\0???ÿ\0\0\0ÿ\0\0???ÿ\0\0\0ÿ\0\0???ÿ\0	\0\0ÿ\0\0???ÿ\0“\0)\0\n\0\0Berechnung’z\0’\0\0\0\0\0\0\0\0\0\0ÿ\n\0B\0e\0r\0e\0c\0h\0n\0u\0n\0g\0\0\0\0\0\0ÿ\0\0òòòÿ\0\0ÿ\0\0ú}\0ÿ\0\0ÿ\0\0ÿ\0\0\0ÿ\0\0ÿ\0\0\0ÿ\0\0ÿ\0	\0\0ÿ\0\0ÿ\0“\0*€ÿ’$\0’\0\0\0\0\0\0\0\0\0\0ÿ\0D\0e\0z\0i\0m\0a\0l\0\0\0\0\0“\0+€ÿ’,\0’\0\0\0\0\0\0\0\0\0\0ÿ\0D\0e\0z\0i\0m\0a\0l\0 \0[\00\0]\0\0\0\0\0“\0,\0\0\0Eingabe’t\0’\0\0\0\0\0\0\0\0\0\0ÿ\0E\0i\0n\0g\0a\0b\0e\0\0\0\0\0\0ÿ\0\0ÿÌ™ÿ\0\0ÿ\0\0??vÿ\0\0ÿ\0\0ÿ\0\0\0ÿ\0\0ÿ\0\0\0ÿ\0\0ÿ\0	\0\0ÿ\0\0ÿ\0“\r\0-\0\0\0Ergebnis’N\0’\0\0\0\0\0\0\0\0\0\0ÿ\0E\0r\0g\0e\0b\0n\0i\0s\0\0\0\0\0\0\0\0\0\0\0ÿ\0\0\0\0O½ÿ\0\0\0\0\0O½ÿ\0“\0.\0\0\0Erklärender Text’B\0’\0\0\0\0\0\0\0\0\0\05ÿ\0E\0r\0k\0l\0ä\0r\0e\0n\0d\0e\0r\0 \0T\0e\0x\0t\0\0\0\0\0\0ÿ\0\0ÿ“\0/\0\0\0Gut’4\0’\0\0\0\0\0\0\0\0\0\0\Zÿ\0G\0u\0t\0\0\0\0\0\0ÿ\0\0ÆïÎÿ\0\0ÿ\0\0\0a\0ÿ“\00\0\0\0Neutral’<\0’\0\0\0\0\0\0\0\0\0\0ÿ\0N\0e\0u\0t\0r\0a\0l\0\0\0\0\0\0ÿ\0\0ÿëœÿ\0\0ÿ\0\0œe\0ÿ“\n\01\0\0\0Notiz’d\0’\0\0\0\0\0\0\0\0\0\0\nÿ\0N\0o\0t\0i\0z\0\0\0\0\0\0ÿ\0\0ÿÿÌÿ\0\0ÿ\0\0²²²ÿ\0\0\0ÿ\0\0²²²ÿ\0\0\0ÿ\0\0²²²ÿ\0	\0\0ÿ\0\0²²²ÿ\0“\02€ÿ’$\0’\0\0\0\0\0\0\0\0\0\0ÿ\0P\0r\0o\0z\0e\0n\0t\0\0\0\0\0“\r\03\0\0\0Schlecht’>\0’\0\0\0\0\0\0\0\0\0\0ÿ\0S\0c\0h\0l\0e\0c\0h\0t\0\0\0\0\0\0ÿ\0\0ÿÇÎÿ\0\0ÿ\0\0œ\0ÿ“\0\0€\0ÿ’2\0’\0\0\0\0\0\0\0\0\0\0\0ÿ\0S\0t\0a\0n\0d\0a\0r\0d\0\0\0\0\0\0\0\0\0\0\0ÿ“\04\0\0\0Überschrift’=\0’\0\0\0\0\0\0\0\0\0\0ÿ\0Ü\0b\0e\0r\0s\0c\0h\0r\0i\0f\0t\0\0\0\0\0\0\0\0I}ÿ%\0\0“\05\0\r\0\0Überschrift 1’J\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\0Ü\0b\0e\0r\0s\0c\0h\0r\0i\0f\0t\0 \01\0\0\0\0\0\0\0\0I}ÿ\0\0\0\0O½ÿ\0“\06\0\r\0\0Überschrift 2’J\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\0Ü\0b\0e\0r\0s\0c\0h\0r\0i\0f\0t\0 \02\0\0\0\0\0\0\0\0I}ÿ\0\0ÿ?¨ÀŞÿ\0“\07\0\r\0\0Überschrift 3’J\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\0Ü\0b\0e\0r\0s\0c\0h\0r\0i\0f\0t\0 \03\0\0\0\0\0\0\0\0I}ÿ\0\023•³×ÿ\0“\08\0\r\0\0Überschrift 4’<\0’\0\0\0\0\0\0\0\0\0\0ÿ\r\0Ü\0b\0e\0r\0s\0c\0h\0r\0i\0f\0t\0 \04\0\0\0\0\0\0\0\0I}ÿ“\09\0\0\0Verknüpfte Zelle’P\0’\0\0\0\0\0\0\0\0\0\0ÿ\0V\0e\0r\0k\0n\0ü\0p\0f\0t\0e\0 \0Z\0e\0l\0l\0e\0\0\0\0\0\0ÿ\0\0ú}\0ÿ\0\0ÿ\0\0ÿ€ÿ\0“\0:€ÿ’$\0’\0\0\0\0\0\0\0\0\0\0ÿ\0W\0ä\0h\0r\0u\0n\0g\0\0\0\0\0“\0;€ÿ’,\0’\0\0\0\0\0\0\0\0\0\0ÿ\0W\0ä\0h\0r\0u\0n\0g\0 \0[\00\0]\0\0\0\0\0“\0<\0\0\0Warnender Text’>\0’\0\0\0\0\0\0\0\0\0\0ÿ\0W\0a\0r\0n\0e\0n\0d\0e\0r\0 \0T\0e\0x\0t\0\0\0\0\0\0ÿ\0\0ÿ\0\0ÿ“\0=\0\0\0Zelle überprüfen’†\0’\0\0\0\0\0\0\0\0\0\0ÿ\0Z\0e\0l\0l\0e\0 \0ü\0b\0e\0r\0p\0r\0ü\0f\0e\0n\0\0\0\0\0\0ÿ\0\0¥¥¥ÿ\0\0\0\0\0ÿÿÿÿ\0\0ÿ\0\0???ÿ\0\0\0ÿ\0\0???ÿ\0\0\0ÿ\0\0???ÿ\0	\0\0ÿ\0\0???ÿ\0X\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0T\0a\0b\0l\0e\0S\0t\0y\0l\0e\0M\0e\0d\0i\0u\0m\09\0P\0i\0v\0o\0t\0S\0t\0y\0l\0e\0L\0i\0g\0h\0t\01\06\0`\0\0\0…\0\0^.\0\0\0\0\0Tabelle1š\0š\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0£\0£\0\0\0\0\0\0\0\0\0\0\0\0\0\0Œ\0\01\01\0®\0\0\0\0\0\0\0\0\0\0\0Á\0Á\0\0ë\0ü\0\0\0\0\0\0\0\0\0\0Testÿ\0\n\0\0Ï-\0\0\0\0\0c\0c\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0–\0–\0\0\0\0\0\0\0\0\0\0Bå\0›\0›\0\0\0\0\0\0\0\0\0\0\0\0\0Œ\0Œ\0\0\0\0\0\0\0\0\0\0\0\0\0\0—\0—\0\0\0\0\0\0\0\0\0\04cş·¢Á\rå½=_MA»Âã\n\0\0\0	\0\0\0ªÍÉ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0f6\0\0ô6\0\0\r\0\0\0\0\0d\0\0\0\0\0\0\0\0\0\0ü©ñÒMbP?_\0\0\0*\0\0\0\0+\0\0\0\0‚\0\0\0€\0\0\0\0\0\0\0\0\0\0%\0\0\0ÿ\0\0\0Á\0\0\0\0\0\0ƒ\0\0\0\0„\0\0\0\0&\0\0ffffffæ?\'\0\0ffffffæ?(\0\0[4®Éd2é?)\0\0[4®Éd2é?M\0î\0\0\\\0\\\0p\0r\0i\0n\0t\0s\0r\0v\0\\\0s\0f\02\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Ü\0Sÿ€\0	\0š4d\0\0\0X\0\0X\0\0A\04\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0PRIVâ0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\'\'\'\0\0\'\0\0\0\0\0\0\0\0ğÄ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0L\0\0P4\0(ˆ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ïL„!\0\0\0\0\0\0\0\0ÿ\0\0\0ÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ğ\0\0SMTJ\0\0\0\0\0àR\0I\0C\0O\0H\0 \0A\0f\0i\0c\0i\0o\0 \0M\0P\0 \0C\02\05\00\00\0 \0P\0S\0\0\0Resolution\0600dpi\0RIPaperPolicy\0PromptUser\0PageSize\0A4\0PageRegion\0\0InputSlot\0*UseFormTrayTable\0LeadingEdge\0\0Duplex\0None\0RICollate\0False\0RIPrintMode\00rhit\0Rimagesm\0Off\0ColorModel\0Gray\0RPSBitsPerPixel\02BitsPerPixel\0RPSRGBcorrect\0DetailBright\0RPSColorRendDict\0Auto\0RPSDitherType\0Auto\0RPSBlackMode\0gray\0RPSBlackOverPrint\0False\0RPSColorSep\0None\0Rcmyksimulation\0Off\0MediaType\0Auto\0OutputBin\0Default\0StapleLocation\0None\0RIPunch\0None\0RIOrientOvr\0Off\0RIWatermark\0Off\0RIWMText\0Confidential\0RIwmFont\0HelveticaB\0RIwmSize\036\0RIwmAngle\045Deg\0RIwmTextStyle\0Gray\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0L\0\0\0EBDAEBDA\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0¡\0\"\0	\0d\0\0\0\0\0XX333333Ó?333333Ó?\0œ&\0œ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0<3\0\0\0\0\0\0\0\0U\0\0\n\0}\0\0\0\0\0\0m>\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\0\0\0\0\0\0\0\0\0\0\0\0ÿ\0\0\0\0\0\0\0\0\0\0\0\0ÿ\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0>\0\0\0\0\0~\n\0\0\0\0>\0\0À^@~\n\0\0\0\0>\0\0@m@×\0\n\0f\0\0\0(\0\0\0>\0¶\0\0\0\0@\0\0\0\0\0\0\0\0\0\0\0‹\0‹\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0º\0\0\0Tabelle1g\0g\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿD\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0M\0o\0d\0u\0l\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\n\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0â\0\0\0\0\0\0_\0_\0S\0R\0P\0_\02\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0T\0\0\0\0\0\0\0_\0_\0S\0R\0P\0_\03\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\Z\0\0\0k\0\0\0\0\0\0\0D\0i\0e\0s\0e\0A\0r\0b\0e\0i\0t\0s\0m\0a\0p\0p\0e\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$\0	\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ß\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\n\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÿÿÿ\0\0\0şÿÿÿ\0\0\0şÿÿÿ\0\0\0\0\0\0\0\0\0 \0\0\0!\0\0\0\"\0\0\0#\0\0\0$\0\0\0%\0\0\0&\0\0\0\'\0\0\0(\0\0\0)\0\0\0*\0\0\0+\0\0\0şÿÿÿ-\0\0\0.\0\0\0/\0\0\00\0\0\01\0\0\02\0\0\03\0\0\04\0\0\05\0\0\06\0\0\07\0\0\08\0\0\09\0\0\0:\0\0\0;\0\0\0şÿÿÿ=\0\0\0>\0\0\0?\0\0\0@\0\0\0A\0\0\0B\0\0\0C\0\0\0D\0\0\0E\0\0\0F\0\0\0G\0\0\0H\0\0\0I\0\0\0J\0\0\0K\0\0\0L\0\0\0M\0\0\0N\0\0\0O\0\0\0P\0\0\0Q\0\0\0R\0\0\0S\0\0\0T\0\0\0U\0\0\0V\0\0\0W\0\0\0X\0\0\0Y\0\0\0Z\0\0\0[\0\0\0\\\0\0\0]\0\0\0^\0\0\0_\0\0\0`\0\0\0a\0\0\0b\0\0\0c\0\0\0d\0\0\0şÿÿÿf\0\0\0g\0\0\0h\0\0\0i\0\0\0j\0\0\0k\0\0\0l\0\0\0m\0\0\0şÿÿÿo\0\0\0p\0\0\0q\0\0\0r\0\0\0s\0\0\0t\0\0\0u\0\0\0v\0\0\0w\0\0\0x\0\0\0y\0\0\0z\0\0\0{\0\0\0|\0\0\0}\0\0\0~\0\0\0\0\0\0şÿÿÿ\0ğ\0\0\0,\0\0Ô\0\0\0¸\0\0ÿÿÿÿ]\0\0µ\0\0\0\0\0\0\0\0\0áÛÀ—\0\0ÿÿ\0\0\0\0\0\0\0¶\0ÿÿ\0\0\0\0ÿÿÿÿ\0\0\0\0ÿÿ\0ÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0ÿÿÿÿx\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿ\0\0\0\0ME\0\0ÿÿÿÿÿÿ\0\0\0\0ÿÿ\0\0\0\0ÿÿ\0\0\0\0ß\0ÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿ\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ(\0\0\0\0<ÿÿ\0\0\0\0\0\0<\0\0\Z\0\0\0\0<\0\0\0\0\06\nÿÿÿÿ\0\0ÿÿ\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿè\0\0\0ÿÿÿÿ\0\0\0`\0\0\0\0ÿÿÿÿ@\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿ\0\0	\0	\0\0\0„\0\0\0\0\0\0\0 \n14 ¤\0ÿÿÿÿÿÿÿÿ`\0\0\0\0\0\0\0\\\0O\0\0\0\0%\0\0\0 „\Zÿÿÿÿÿÿÿÿ€\0\0\0\0\0\0\0L\0\0\0\0\0%\0\0\0 „ÿÿÿÿÿÿÿÿ \0\0\0\0\0\0\0\0\0\0\0\0\0%\0\0\0ÿÿÿÿ\0\0\0\0ƒÿÿÿÿ\0ÿÿØ\0\0\0\0\0ÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0%\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ°\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0¨\0\0\0àïGL\0$\0*\0\\\0R\0f\0f\0f\0f\0*\00\0@\04\0c\04\07\0f\06\00\01\0\0\0\0ß\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÊ\0	\0\"\0\0\0\0\0\0\0\0\0€\0\0\0\0\0\0\0€\0\0\0\0\0\0\0€\0\0\0\0\0\0\0*\0\0\0 \0\0\0\0\0\0\0ˆ\0\0\0\0\0\0\0P\0\0\0\0\n\0\0\0p\0\0\0\0\0\0\0€\0\0\0ÿÿÿÿ \0\0\0–\0\0\0\0\0\0]\0òH\0\0\0]\0òh\0\0\0]\0òˆ\0\0\0í\0¶\0\0c:\\temp\\test2.xls\0 \0\0!\0%\0\0.\0\Z\0ÿÿÿÿ¶\0\0@\0¶\0\0A:A\0 \0%\0\"\0(\0$x\0\0\0· \0\0(\0&Ñ§ô\0`o\0ÿÿ`\0\0\0í\0 \0\Z%\0 \0\0.\0ÿÿÿÿ`\0\0\0ÿÿÿÿ\0\0#±\0Attribut\0e VB_Nam\0e = \"Mod\0ul1\"\r\nSu\0b Makro1°()\r\n¬L.\0È\0ProcDataInvoke_FuncŠ \\n14\0ˆ   Di\0m excel @As New.\0Applicat ion\r\n&woÀrkbook)Á\nsheet­S\0;=`WL\0s.Open(\"€c:\\temp\0\0st2.xls\"O\0Ö\0<ƒ-= $.{ \ns€€.\0Columns(\0\"A:A\").N\0umberForhmat~@ƒ|‚z.@Visibl­T\0rue\r\nEnd €­\r\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0rU€\0\0\0€\0\0\0€\0\0\0€\0\0\0\0\0~|\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0k\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0rU€\0\0\0\0\0\0\0€\0\0\0€\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0$\0\0\0\0\0\0\0\0\0\0`\0\0ıÿÿÿÿÿÿÿa\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ğ\0\0\0Ä\0\0Ô\0\0\0\0\0\0ÿÿÿÿË\0\0\0\0\0\0\0\0\0\0\0áÛ>/\0\0ÿÿ#\0\0\0ˆ\0\0\0¶\0ÿÿ\0\0\0\0ÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0ÿÿÿÿx\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿ\0\0\0\0ME\0\0ÿÿÿÿÿÿ\0\0\0\0ÿÿ\0\0\0\0ÿÿ\0\0\0\0ß\0ÿÿ\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ(\0\0\0\0SLÿÿÿÿ\0\0\0Sÿÿÿÿ\0\0\0S”ÿÿÿÿ\0\0\0\0<ÿÿÿÿ\0\0ÿÿ\0\0\0\0\0N\00\0{\00\00\00\02\00\08\01\09\0-\00\00\00\00\0-\00\00\00\00\0-\0C\00\00\00\0-\00\00\00\00\00\00\00\00\00\00\04\06\0}\0\0\0\0\0ÿÿÿÿ8\0\0\0€şÿÿÿÿÿ\0ÿÿ(\0\0\0ÿÿ\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0%\0\0\0ÿÿÿÿH\0\0\0\0\0ÿÿ\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0ß\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÊ\0\0\0ÿÿÿÿ\0\0\0ÿÿÿÿx\0\0\0ÿÿÿÿ\0\0¶°\0Attribut\0e VB_Nam\0e = \"Die\0seArbeit\0smappe\"\r\"\n\n Bas 0{\000020819ê-\00C\0$ 0046}\r|GlobalÚSpa‚cIFalsed\0CreatablPredecla\0Id\0¶Tru\rBExpose\0Template`Deriv’BuÀstomizD2\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ğ\0\0\0Ä\0\0Ô\0\0\0\0\0\0ÿÿÿÿË\0\0\0\0\0\0\0\0\0\0\0áÛ‡§\0\0ÿÿ#\0\0\0ˆ\0\0\0¶\0ÿÿ\0\0\0\0ÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0ÿÿÿÿx\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿ\0\0\0\0ME\0\0ÿÿÿÿÿÿ\0\0\0\0ÿÿ\0\0\0\0ÿÿ\0\0\0\0ß\0ÿÿ\0\0\0\0\0ÿÿÿÿÿÿT\0a\0b\0e\0l\0l\0e\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0,\0\0\0Ó\0\0\0\0\0\0_\0V\0B\0A\0_\0P\0R\0O\0J\0E\0C\0T\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\Z\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0<\0\0\0 \n\0\0\0\0\0\0d\0i\0r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0e\0\0\0<\0\0\0\0\0\0_\0_\0S\0R\0P\0_\00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0n\0\0\0N\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ(\0\0\0\0SLÿÿÿÿ\0\0\0Sÿÿÿÿ\0\0\0S”ÿÿÿÿ\0\0\0\0<ÿÿÿÿ\0\0ÿÿ\0\0\0\0\0N\00\0{\00\00\00\02\00\08\02\00\0-\00\00\00\00\0-\00\00\00\00\0-\0C\00\00\00\0-\00\00\00\00\00\00\00\00\00\00\04\06\0}\0\0\0\0\0ÿÿÿÿ8\0\0\0€şÿÿÿÿÿ\0ÿÿ(\0\0\0ÿÿ\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0%\0\0\0ÿÿÿÿH\0\0\0\0\0ÿÿ\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0ß\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÊ\0\0\0ÿÿÿÿ\0\0\0ÿÿÿÿx\0\0\0ÿÿÿÿ\0\0ª°\0Attribut\0e VB_Nam\0e = \"Tab\0elle1\"\r\n\nøBas|0{0\00020820-;\0 C\0$0046}\r|Glob„alÈSpac’ FalsedCr@eatablP€redecla\0DId\0­Tru\rBE xposeTe\0mplateDeriv$’Bust0omizD2\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Ìaˆ\0\0\0ÿ\0\0	\0\0ä\0\0\0\0\0\0\0\0\0\0\0\0ú\0*\0\\\0G\0{\00\00\00\02\00\04\0E\0F\0-\00\00\00\00\0-\00\00\00\00\0-\0C\00\00\00\0-\00\00\00\00\00\00\00\00\00\00\04\06\0}\0#\04\0.\00\0#\09\0#\0C\0:\0\\\0P\0R\0O\0G\0R\0A\0~\01\0\\\0C\0O\0M\0M\0O\0N\0~\01\0\\\0M\0I\0C\0R\0O\0S\0~\01\0\\\0V\0B\0A\0\\\0V\0B\0A\06\0\\\0V\0B\0E\06\0.\0D\0L\0L\0#\0V\0i\0s\0u\0a\0l\0 \0B\0a\0s\0i\0c\0 \0F\0o\0r\0 \0A\0p\0p\0l\0i\0c\0a\0t\0i\0o\0n\0s\0\0\0\0\0\0\0\0\0\0\0\0\0*\0\\\0G\0{\00\00\00\02\00\08\01\03\0-\00\00\00\00\0-\00\00\00\00\0-\0C\00\00\00\0-\00\00\00\00\00\00\00\00\00\00\04\06\0}\0#\01\0.\06\0#\00\0#\0C\0:\0\\\0P\0r\0o\0g\0r\0a\0m\0 \0F\0i\0l\0e\0s\0\\\0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \0O\0f\0f\0i\0c\0e\0\\\0O\0f\0f\0i\0c\0e\01\02\0\\\0E\0X\0C\0E\0L\0.\0E\0X\0E\0#\0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \0E\0x\0c\0e\0l\0 \01\02\0.\00\0 \0O\0b\0j\0e\0c\0t\0 \0L\0i\0b\0r\0a\0r\0y\0\0\0\0\0\0\0\0\0\0\0\0\0¸\0*\0\\\0G\0{\00\00\00\02\00\04\03\00\0-\00\00\00\00\0-\00\00\00\00\0-\0C\00\00\00\0-\00\00\00\00\00\00\00\00\00\00\04\06\0}\0#\02\0.\00\0#\00\0#\0C\0:\0\\\0W\0I\0N\0N\0T\0\\\0s\0y\0s\0t\0e\0m\03\02\0\\\0s\0t\0d\0o\0l\0e\02\0.\0t\0l\0b\0#\0O\0L\0E\0 \0A\0u\0t\0o\0m\0a\0t\0i\0o\0n\0\0\0\0\0\0\0\0\0\0\0\0\0(*\0\\\0G\0{\02\0D\0F\08\0D\00\04\0C\0-\05\0B\0F\0A\0-\01\00\01\0B\0-\0B\0D\0E\05\0-\00\00\0A\0A\00\00\04\04\0D\0E\05\02\0}\0#\02\0.\04\0#\00\0#\0C\0:\0\\\0P\0r\0o\0g\0r\0a\0m\0 \0F\0i\0l\0e\0s\0\\\0C\0o\0m\0m\0o\0n\0 \0F\0i\0l\0e\0s\0\\\0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \0S\0h\0a\0r\0e\0d\0\\\0O\0F\0F\0I\0C\0E\01\02\0\\\0M\0S\0O\0.\0D\0L\0L\0#\0M\0i\0c\0r\0o\0s\0o\0f\0t\0 \0O\0f\0f\0i\0c\0e\0 \01\02\0.\00\0 \0O\0b\0j\0e\0c\0t\0 \0L\0i\0b\0r\0a\0r\0y\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\n\0ÿÿÿÿÿÿ\0\0\0\0ÿÿ\0\0àïGL\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0ÿÿ\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0áÛ\0\0M\0o\0d\0u\0l\01\0\00\0@\04\0c\04\07\0f\06\00\01\0ÿÿ\0M\0o\0d\0u\0l\01\0ÿÿÀ—\0\0\0\0\0\0\0\0\0\0»\0\0ÿÿ\"\0D\0i\0e\0s\0e\0A\0r\0b\0e\0i\0t\0s\0m\0a\0p\0p\0e\0\00\0>\04\0c\04\07\0f\06\00\01\0ÿÿ)\"\0D\0i\0e\0s\0e\0A\0r\0b\0e\0i\0t\0s\0m\0a\0p\0p\0e\0ÿÿ>/\0\0\0\0\0\0\0\0\0%\0\0ÿÿ\0T\0a\0b\0e\0l\0l\0e\01\0\00\0?\04\0c\04\07\0f\06\00\01\0ÿÿ+\0T\0a\0b\0e\0l\0l\0e\01\0ÿÿ‡§\0\0\0\0\0\00\0\0\0%\0\0ÿÿÿÿÿÿP\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿˆ&µMI£}SÏY¿ÿÿÿÿ\0\0\0×m:ÃÈìaA¯®u÷z/·Ûÿÿÿÿ\0\0\0y”è ùÅ©L‘àŠéš“\"ÿÿÿÿ\0\0\0ÿÿÿÿ0\0\0\0€\0\0\0\0\0\0ÿ\0\'\0\0excel€+\0VBA÷â\0Win16Á~\0Win32\0Mac³²\0VBA6­#\0\nVBAProject¾¿\0\0stdole“`\0\0Officeu\0Modul1Í\0	\0_EvaluateÙ\0Makro1C|\0\0Application¥*\0workbookk\0	worksheetÁş\0	\0WorkbooksBì\0\n\0Worksheets¼ø\0\0Columnsp9\0\0NumberFormatÕR\0\0Visible¶Ó\0DieseArbeitsmappe¯\0Tabelle1RŠ\0ÿÿ`\0\0\0\0\0ÿÿ\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿ)\0\0+\0	\0ÿÿÿÿ\0ÿÿ\0ÿÿ\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\06\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\08²€\0\0\0\0\00*	\0pH\0‚\0dä\0\n\0\0VBAProjeˆct\04\0\0@j\n=\nr	àïGL”\0J<\n\0r€stdole>\0s\0t\0d\0o\0€l\0e\0\r\0f\0%\\\0*\\G{00€020430-\0C\0\n004\06}#2.0#0\0#C:\\WINN\0T\\system32\\c2.tl\0b#OLE Au\0tomation\0^\0ƒDOffic„DO€>f\0i\0c5‚D€”€D2D\0F8D04C-5\0BFA-101B -BDE5€DAA5€B4€2ˆD€—gr\0am Files€\\Common\0Microsof\0t Shared\0\\OFFICE1\02\\MSO.DLLL#‡ƒM 1€p ObÀ LibrXary\0K\0‚ÓD\0‚áÛ‚­M@odul1G‚­M€«d\0u€­1\0\ZUˆ2N‚HB1µÂn»À„B,Â(À—!B+B\0ÀDieseA\0rbeitsmaÀppeG\0\"\0Re\0s€RA\0r\0\nbÀi\0zs\0m\0ha\0p@\0e\0$’2C¤O,%\0\0M,>R/#\0\0E,€&T@abelleÀHwA\0$\'lB ÁIÉ2f\0Ñã‡§ÉÁ\0\0\0\0\0\0\0“K*ˆ\0\0\0\0ÿÿ\0\0\0\0\0\0ÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0rU\0\0\0€\0\0\0€\0\0\0€\0\0\0\0\0~\0\0~\0\0~\0\0~\0\0~\0\0~o\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0É\0\0\0\0\0\0\0G*OÀK´³E·ğv\0	\0\0\0\0ä\0\0\0\0\0\0\0ÿÿÿÿ\0ˆ \0	\0\0\0\0\0\0™\0\0\0\0\0\0ÿÿÿÿp\0\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0ÿÿ\0\0é\0\0\0\0\0\0\0\n\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\n\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\01\0\0\0\0\0\0\0ñ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0)\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\n\0\0\0VBAProject\0\0\0\0\0Modul1\0\0\0\0\0DieseArbeitsmappe\0\0\0\0\0Tabelle1\0\0ï\0\0\0\0\0À\0\0\0\0\0\0F\0\0/\0\0\0C:\\PROGRA~1\\COMMON~1\\MICROS~1\\VBA\\VBA6\\VBE6.DLL\0\0\0\0\0VBA\n\0\0\nQ\0\0\0\0\0\0ÿÿÿÿ\0\0\0	\0\0\0y\0\0\0\0\0\0á\0\0\0\0\0\00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0À\0\0\0\0\0\0F\0\04\0\0\0C:\\Program Files\\Microsoft Office\\Office12\\EXCEL.EXE\0\0\0\0\0Excel\n\0\0\nI\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0q\0\0\0\0\0\0é\0\0\0\0\0\0@\0\0\0\0\0\0\0\0\0\0\0\0\0\0\00\0\0\0\0\0À\0\0\0\0\0\0F\0\0\0\0\0C:\\WINNT\\system32\\stdole2.tlb\0\0\0\0\0stdole\0\0\n\0\0\nY\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0É\0\0\0\0\0\0P\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0LĞø-ú[½å\0ª\0DŞR\0\0?\0\0\0C:\\Program Files\\Common Files\\Microsoft Shared\\OFFICE12\\MSO.DLL\0\0\0\0\0Office\n\0\0\na\0\0\0\0\0\0ÿÿÿÿ\0\0\0\0\0\0‰\0\0\0\0\0\0\0\0\0\0\0\0`\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Makro1\0\0\r\0\0\0\0\0\0\0\0\0\0\0I\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0_\0_\0S\0R\0P\0_\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0€\0\0\0V\0\0\0\0\0\0\0P\0R\0O\0J\0E\0C\0T\0w\0m\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0‚\0\0\0h\0\0\0\0\0\0\0P\0R\0O\0J\0E\0C\0T\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0„\0\0\0ß\0\0\0\0\0\0\0S\0u\0m\0m\0a\0r\0y\0I\0n\0f\0o\0r\0m\0a\0t\0i\0o\0n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0(\0ÿÿÿÿ\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Œ\0\0\0Ğ\0\0\0\0\0\0\0\0\0\0şÿÿÿƒ\0\0\0şÿÿÿ…\0\0\0†\0\0\0‡\0\0\0ˆ\0\0\0‰\0\0\0Š\0\0\0‹\0\0\0şÿÿÿ\0\0\0\0\0\0\0\0\0şÿÿÿ‘\0\0\0’\0\0\0“\0\0\0”\0\0\0şÿÿÿ–\0\0\0şÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿrU€\0\0\0\0\0\0\0€\0\0\0€\0\0\0\0\0\0\0\n\0\0\0	\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0 \n14q\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Modul1\0M\0o\0d\0u\0l\01\0\0\0DieseArbeitsmappe\0D\0i\0e\0s\0e\0A\0r\0b\0e\0i\0t\0s\0m\0a\0p\0p\0e\0\0\0Tabelle1\0T\0a\0b\0e\0l\0l\0e\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ID=\"{FDB9242B-AC6C-495B-BDDA-380C088A74A8}\"\r\nModule=Modul1\r\nDocument=DieseArbeitsmappe/&H00000000\r\nDocument=Tabelle1/&H00000000\r\nName=\"VBAProject\"\r\nHelpContextID=\"0\"\r\nVersionCompatible32=\"393222000\"\r\nCMG=\"595B437147714771477147\"\r\nDPB=\"DBD9C1714272427242\"\r\nGC=\"5D5F47FB4B7C4C7C4C83\"\r\n\r\n[Host Extender Info]\r\n&H00000001={3832D640-CF90-11CF-8E43-00A0C911005A};VBE;&H00000000\r\n\r\n[Workspace]\r\nModul1=44, 44, 1218, 675, \r\nDieseArbeitsmappe=0, 0, 0, 0, C\r\nTabelle1=22, 22, 1196, 653, \r\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0à…ŸòùOh«‘\0+\'³Ù0\0\0\0 \0\0\0\0\0\0\0\0\0@\0\0\0\0\0\0H\0\0\0\0\0\0X\0\0\0\0\0\0h\0\0\0\0\0\0€\0\0\0\r\0\0\0Œ\0\0\0\0\0\0˜\0\0\0\0\0\0ä\0\0\0\0\0\0\0\0reich\0\0\0\0\0\0\0\0\0reich\0\0\0\0\0\0\0\0\0Microsoft Excel\0@\0\0\0\0xÄŸ=Ê@\0\0\0€~Ù¢=Ê\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0D\0o\0c\0u\0m\0e\0n\0t\0S\0u\0m\0m\0a\0r\0y\0I\0n\0f\0o\0r\0m\0a\0t\0i\0o\0n\0\0\0\0\0\0\0\0\0\0\08\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0C\0o\0m\0p\0O\0b\0j\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0•\0\0\0s\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÕÍÕœ.“—\0+,ù®0\0\0\0Ü\0\0\0	\0\0\0\0\0\0P\0\0\0\0\0\0X\0\0\0\0\0\0x\0\0\0\0\0\0€\0\0\0\0\0\0ˆ\0\0\0\0\0\0\0\0\0\0\0\0˜\0\0\0\r\0\0\0 \0\0\0\0\0\0µ\0\0\0\0\0\0ä\0\0\0\0\0\0\0\0Fritz EGGER GmbH & Co.\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0Tabelle1\0\0\0\0\0\0\0\0\0\0\0\0Arbeitsblätter\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÿ\n\0\0ÿÿÿÿ \0\0\0\0\0À\0\0\0\0\0\0F\'\0\0\0Microsoft Office Excel 2003-Arbeitsbl.\0\0\0\0Biff8\0\0\0\0Excel.Sheet.8\0ô9²q\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','2009-11-16 13:22:19','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `attachments`(`id`,`category_key`,`description`,`owner_id`,`intern`,`mime_type`,`file_name`,`file_size`,`file_content`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('44107a64-106d-4048-b3b3-3689442a86b4','results',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17',1,'application/msword','performance_chart.xls',6144,'ĞÏà¡±\Zá\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0;\0\0şÿ	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0şÿÿÿ\0\0\0\0\n\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿR\0o\0o\0t\0 \0E\0n\0t\0r\0y\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0À\0\0\0\0\0\0W\0o\0r\0k\0b\0o\0o\0k\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0¡\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0ÓÌA\0\0\0\0\0\0á\0\0°Á\0\0\0\0â\0\0\0\\\0p\0\0\0reich                                                                                                        B\0\0°a\0\0\0=\0\0œ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0¯\0\0\0¼\0\0\0=\0\0h\\:¾#8\0\0\0\0\0\0X@\0\0\0\0\0\0\0\0\"\0\0\0\0\0\0\0·\0\0\0Ú\0\0\0\01\0\0È\0\0\0ÿ\0\0\0\0\0\0\0Arial1\0\0È\0\0\0ÿ\0\0\0\0\0\0\0Arial1\0\0È\0\0\0ÿ\0\0\0\0\0\0\0Arial1\0\0È\0\0\0ÿ\0\0\0\0\0\0\0Arial1\0\0h\0\0ÿ¼\0\0\0\0\0\0\0Arial1\0\0ğ\0\0\0ÿ¼\0\0\0\0\0\0\0Arial1\0\0ğ\0\0\0	\0¼\0\0\0\0\0\0\0Arial1\0\0È\0\0\0	\0\0\0\0\0\0\0\0Arial1\0\0Ü\0\0\0	\0\0\0\0\0\0\0\0Arial\0\0\0\0\"$\"#,##0_);\\(\"$\"#,##0\\)!\0\0\0\0\"$\"#,##0_);[Red]\\(\"$\"#,##0\\)\"\0\0\0\0\"$\"#,##0.00_);\\(\"$\"#,##0.00\\)\'\0\0\"\0\0\"$\"#,##0.00_);[Red]\\(\"$\"#,##0.00\\)7\0*\02\0\0_(\"$\"* #,##0_);_(\"$\"* \\(#,##0\\);_(\"$\"* \"-\"_);_(@_).\0)\0)\0\0_(* #,##0_);_(* \\(#,##0\\);_(* \"-\"_);_(@_)?\0,\0:\0\0_(\"$\"* #,##0.00_);_(\"$\"* \\(#,##0.00\\);_(\"$\"* \"-\"??_);_(@_)6\0+\01\0\0_(* #,##0.00_);_(* \\(#,##0.00\\);_(* \"-\"??_);_(@_)à\0\0\0\0\0\0õÿ \0\0\0\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0õÿ \0\0ô\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0\0 \0\0\0\0\0\0\0\0\0\0\0À à\0\0\0+\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0)\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0,\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0*\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0	\0õÿ \0\0ø\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0\0\0\0\0\0\0À à\0\0\0\0\0\0\0\08\0 à\0\0	\0\0\0\0)Z\08\0 à\0\0\0\0\0\0\0\Z\0\00\0\0À à\0\0\0\0\0\0\0\0\00\0\0À à\0\0\0\0\0\0\Z\0\08\0\0À à\0\0\0\0\0\0\Z\0\08\0 à\0\0\0\0\0\0\Z\0\08\0\n à\0\0\0\0\0\0\0 \0\0 ‰\0 “\0€ÿ“\0€ÿ“\0€ÿ“\0€ÿ“\0\0€\0ÿ“\0€ÿ`\0\0\0…\0\0x	\0\0\0\0\0Performance ChartŒ\0\0\0\0®\0\0\0\0\0\0\0\0\0\0\0ü\0”1\0\0\0,\0\0\0#\0\0Leistungsentwicklung Daniela Bucher\0\0Final Position\0\0Date\0\0Competition\0\0Category\0\0Fastest swimsplit\0\0Swim cutoff\0\0Swimsplit/Position\0\0Behind fastest swimmer\0\0Wetsuit\0\0Fastest runsplit\n\0\0Run cutoff\0\0Runsplit/Position\0\0Behind fastest runner\0\0Comment\0\01\n\0\018.11.2009\r\0\0Sylvesterlauf\0\0Elite A\0\000:25:00\0\0Reich\0\010%\0\000:00\0\00.0%\0\000:30:00\0\0Bucher\0\015%\0\000:40:00\0\010:00\0\033.33%	\0\0Kommentar\0\02\n\0\010.07.2010\0\0Weltcup Kitzbühel\0\0Pro W\0\000:34:00\0\0Allen\0\000:35:00\0\000:45:00\0\011:00\0\032.35%\0\000:36:00\0\000:00:00p\0\0DNF = Did Not Finish, CSR = Championship Race, WC = World Cup,\nEC = European Cup, W-CHAMPS = World Championshipsÿ\02\0\0²\0\0\0\0\0@\0\0š\0\0\0½\0\0\0\0\0\0f\0\0R\0\0¬\0\0¬\0\0\0\0\n\0\0\0	\0\0\0»\rÌÁ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0È\0\0\r\0\0\0\0\0d\0\0\0\0\0\0\0\0\0\0ü©ñÒMbP?_\0\0\0*\0\0\0\0+\0\0\0\0‚\0\0\0€\0\0\0\0\0\0\0\0\0\0%\0\0\0ÿ\0\0\0Á\0\0\0\0\0\0ƒ\0\0\0„\0\0\0\0¡\0\"\0\0d\0\0\0\0\0\0,,\0\0\0\0\0\0à?\0\0\0\0\0\0à?\0U\0\0\0}\0\0\0\0\0\0“\0\0\0}\0\0\0\0Î\n\0\0\0}\0\0\0\0ÿ\0\0\0}\0\0\0\0¢\0\0\0}\0\0\0\0·\0\0\0}\0\0\0\0¶\0\0\0}\0\0\0\0f\0\0\0}\0\0	\0\0·\0\0\0}\0\0\0\0¶\0\0\0}\0\0\r\0\r\04\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0„\0\0\0\0@\0\0\0\0\0\0`	\0\0\0\0@\0\0\0\0\0\0\0\0\0\0@\0\0\0\0\0\0\0\0\0\0@\0\0\0\0\0\0\0\0\0\0@\0\0\0\0\0\0\0\0\0\0@\0\0\0\0\0\0X\0\0\0\0@\0ı\0\n\0\0\0\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0	\0\0\0ı\0\n\0\0	\0\0\n\0\0\0ı\0\n\0\0\n\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\r\0\0\0ı\0\n\0\0\r\0\0\0\0\0ı\0\n\0\0\0\0\Z\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0\0\0\0\0ı\0\n\0\0	\0\0\0\0\0ı\0\n\0\0\n\0\0\Z\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0ı\0\n\0\0\r\0\0\0\0\0¾\0\0\0\0\0\Z\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0¾\0\n\0\0\0\0\0\0ı\0\n\0\0\0\0\0\0\0\0\0\0\0ı\0\n\0\0	\0\0\0\0\0¾\0\n\0\0\n\0\0\0\0ı\0\n\0\0\0\0\0\0\0\0\0\r\0\0ı\0\n\0\0\0\0\Z\0\0\0\0ı\0\n\0\0\0\0 \0\0\0ı\0\n\0\0\0\0!\0\0\0ı\0\n\0\0\0\0\"\0\0\0ı\0\n\0\0\0\0#\0\0\0ı\0\n\0\0\0\0%\0\0\0ı\0\n\0\0\0\0&\0\0\0ı\0\n\0\0\0\0\'\0\0\0\0\0\0\0ı\0\n\0\0	\0\0)\0\0\0ı\0\n\0\0\n\0\0\0\0\0ı\0\n\0\0\0\0*\0\0\0\0\0\0\0ı\0\n\0\0\r\0\0\0\0\0¾\0\0\0\0\0\Z\0\0\0\0\0ı\0\n\0\0\0\0$\0\0\0¾\0\n\0\0\0\0\0\0ı\0\n\0\0\0\0(\0\0\0\0\0\0\0ı\0\n\0\0	\0\0\0\0\0¾\0\n\0\0\n\0\0\0\0ı\0\n\0\0\0\0\0\0\0\0\0\r\0\0ı\0\n\0\0\0\0\0+\0\0\0×\0\0Ü\0\0x\0\0Ä\0À\0z\0¼\0z\0>\0¶\0\0\0\0@\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0å\0’\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\n\0\n\0\0\0\r\0\r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\n\0\n\0\0\0\r\0\r\0\0\0\0\0\r\0\n\0\0\0ÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\n\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\Z\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0 \0\0\0!\0\0\0\"\0\0\0#\0\0\0$\0\0\0%\0\0\0&\0\0\0\'\0\0\0(\0\0\0)\0\0\0*\0\0\0+\0\0\0,\0\0\0-\0\0\0.\0\0\0/\0\0\00\0\0\01\0\0\02\0\0\03\0\0\04\0\0\05\0\0\06\0\0\07\0\0\08\0\0\09\0\0\0:\0\0\0;\0\0\0<\0\0\0=\0\0\0>\0\0\0şÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿşÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0şÿÿÿşÿÿÿşÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿÿ',NULL,NULL,'2010-04-07 21:47:29',NULL,0,0);

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
  `modified_at` datetime DEFAULT '1900-01-01 00:00:00' COMMENT 'Datensatz geÃ¤ndert am',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Datensatz geÃ¤ndert von',
  `test` tinyint(1) DEFAULT '0',
  `deleted` tinyint(1) DEFAULT '0' COMMENT 'Datensatz gelÃ¶scht',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `competitions` */

insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('x96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-08-31','Podersdorf','tria',NULL,'Moosbach 28\n6392 St. Jakob','at',NULL,NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('63df5d72-3d22-411f-affa-3c974318e790','2009-11-12','Ironman KÃ¤rnten','tria',NULL,'Klagenfurt','at',NULL,NULL,'2009-11-11 22:29:53','e96bcbd2-676d-102c-ace2-9cc3fca64c87','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('528e52ef-15ad-469c-bde6-6bd593c74984','2009-11-11','Test','tria',NULL,'Test','de',NULL,NULL,'2009-11-11 22:45:16','e96bcbd2-676d-102c-ace2-9cc3fca64c87','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('0d1edae8-a48a-4aa2-a9db-fdebd1acc15e','2009-11-13','Test 2','tria',NULL,NULL,'al',NULL,NULL,'2009-11-11 22:51:25','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-06 22:14:57','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('ba76369e-5c2b-4814-a1ca-6229a2a2fa1a','2009-11-21','Test 3','tria',NULL,NULL,NULL,NULL,NULL,'2009-11-11 22:51:46','e96bcbd2-676d-102c-ace2-9cc3fca64c87','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('30c5931c-cabb-42cf-9e55-5bfe663cefa0','2010-07-11','Weltcup KitzbÃ¼hel','tria',NULL,'6300 KitzbÃ¼hel','at',NULL,NULL,'2009-11-28 12:40:38','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-02-19 14:31:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('bb6116f8-fd51-4d05-8bfd-e0d86e228278','2009-11-29','Ironman South Africa','tria',NULL,NULL,'at',NULL,NULL,'2009-11-28 17:16:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 17:16:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('5337cdba-b2ac-42f6-bcd9-bd7825db4284','2009-11-27','Buachlauf','tria',NULL,NULL,'at',NULL,NULL,'2009-11-28 23:14:14','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 23:14:14','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('2e122f41-2121-4d16-94d6-f5dc328d84dc','2009-11-18','Sylvesterlauf','tria',NULL,'Test22','at','ITU','44107a64-106d-4048-b3b3-3689442a86b4','2009-11-28 23:26:32','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:47:29','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('9956d670-cb98-4d78-930a-88722f68d6a2','2010-04-24','Linz Marathon','tria',NULL,'Testwassssssssss','at',NULL,NULL,'2010-01-31 15:55:45','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-01-31 16:58:11','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('a510cfbd-0f4e-4033-95f2-32f669cc964a','2009-09-13','CSR','tria',NULL,'Gold Coast ','at',NULL,NULL,'2010-02-17 22:15:05','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-19 19:31:53','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,1);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('ffd573fd-355c-4307-b0b9-338901210fff','2009-09-13','CSR Gold Coast','tria',NULL,'Gold Coast','at',NULL,NULL,'2010-02-19 19:41:28','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 17:08:07','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,1);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('3724b912-dfff-481b-92fe-f4687aac0f1b','2009-07-12','CSR KitzbÃ¼hel','tria',NULL,NULL,'at',NULL,NULL,'2010-02-19 22:13:06','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 17:08:12','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,1);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('a2f4545a-8998-4c4e-8f09-706ca409d0aa','2009-03-29','WC-M Mooloolaba','tria',NULL,NULL,'au',NULL,NULL,'2010-02-20 17:16:48','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 17:22:23','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('e2debec2-9127-4980-b1f3-0a939eb5b40b','2009-04-26','WC Ishigaki','tria',NULL,NULL,'at',NULL,NULL,'2010-02-20 17:20:14','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 17:27:44','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,1);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('033849c9-c2ae-4b53-bb68-9b9e7fe24213','2009-04-26','WC-F Ishigaki','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 17:31:08','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 17:31:08','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('5c0a7ffd-085c-42fa-93fc-ad1c21f903b8','2009-04-26','WC-M Ishigaki','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 17:34:18','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 17:34:18','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('bceaed7b-fb33-49fa-a3f2-4b507b78e28b','2009-06-27','WC-F Hy-Vee','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 17:37:55','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 17:37:55','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('7d85149d-1b4b-4098-bae6-46d8e146beb3','2009-06-27','WC-M Hy-Vee','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 17:42:56','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 17:42:56','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('cc602ead-3fa1-4355-a4e9-ad86414374b9','2009-08-09','WC-F Tiszaujvaros','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 17:46:08','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 17:46:08','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('ff9d8567-2d53-42fd-a311-a4f20efffb1d','2009-08-09','WC-M Tiszaujvaros','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 17:50:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 17:50:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('5fed2847-689c-489e-a7bf-e14c66bd3612','2009-11-08','WC-F Huatulco','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 17:59:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 17:59:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('9f55f3f0-fc1a-4d85-9746-f4b2ddaa66c9','2009-11-08','WC-M Huatulco','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 18:02:37','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:02:37','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('70a4cbf9-e670-498b-8abe-d327aa0591cf','2009-03-29','WC-F Mooloolaba','tria',NULL,NULL,'au',NULL,NULL,'2010-02-20 18:04:27','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:04:27','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('a8424bbe-162f-44f9-999f-f1862452443a','2009-05-02','CSR-F Tongyeong','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 18:07:46','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:07:46','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('94f019bf-d9e9-471a-9d88-b19f1cd4c7e6','2009-05-02','CSR-M Tongyeong','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 18:11:17','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:11:17','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('6ba80501-1754-4d63-9dc7-4545c38b8008','2009-05-31','CSR-F Madrid','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 18:13:57','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:13:57','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('de8f6cfb-1625-4a80-ae53-5c3fd20bc75e','2009-05-31','CSR-M Madrid','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 18:35:36','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:35:36','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('043424a4-e0f9-4736-b804-37a53d7b642a','2009-06-21','CSR-F Washington','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 18:37:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:37:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('83d7ea1c-4ed8-4967-8ff9-33d19aab76eb','2009-06-21','CSR-M Washington','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 18:40:07','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:40:07','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('944a9071-c02c-4574-b1c8-a799706e9d1b','2009-07-12','CSR-F KitzbÃ¼hel','tria',NULL,NULL,'at',NULL,NULL,'2010-02-20 18:42:27','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:50:23','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('7034b557-4719-42bd-96a9-aadb846b68af','2009-07-11','CSR-M KitzbÃ¼hel','tria',NULL,NULL,'at',NULL,NULL,'2010-02-20 18:45:19','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:50:41','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('6f23e23d-1bd7-4300-9dfd-72cbed333c8e','2009-07-25','CSR-F Hamburg','tria',NULL,NULL,'de',NULL,NULL,'2010-02-20 18:50:03','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:50:03','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('01ea6bc5-2263-4298-b38d-e403faa9c0e3','2009-07-26','CSR-M Hamburg','tria',NULL,NULL,'de',NULL,NULL,'2010-02-20 18:53:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:53:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('351a928e-588e-4b68-86dc-7e5dcd0c0749','2009-08-15','CSR-F London','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 18:56:05','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:56:05','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('59db8e01-ada7-4a17-ba6a-e9c0d41323cf','2009-08-15','CSR-M London','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 18:58:39','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 18:58:39','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('ad9212bd-2f5d-453a-9c67-4549c2ca2164','2009-08-22','CSR-F Yokohama','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 19:02:47','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 19:02:47','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('bfdeef28-dd69-4e27-b8f9-ef03c5ea94c0','2009-08-22','CSR-F Yokohama','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 19:06:46','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 19:07:48','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,1);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('395c99de-7126-4e42-a414-01cd1e7c8b84','2009-08-22','CSR-M Yokohama','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-20 19:10:28','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 19:10:28','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('494c57f6-9809-4257-a453-d11af4fc16bb','2009-09-13','CSR-F Gold Coast','tria',NULL,NULL,'au',NULL,NULL,'2010-02-20 19:11:20','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 19:14:55','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('d92b9db6-4e55-4f24-958d-679ef6663829','2009-09-12','CSR-M Gold Coast','tria',NULL,NULL,'au',NULL,NULL,'2010-02-20 19:13:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 19:13:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('6daa7f39-dc3f-47db-95aa-bcd20b3fbdb4','2008-10-26','WC-F Huatulco','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:16:55','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:16:55','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('d2cea231-5258-4069-9e06-100e318c1a05','2008-10-26','WC-M Huatulco','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:19:26','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:19:26','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('b8d808a9-d368-4c47-8354-a68e82412f0e','2008-09-27','WC-F Lorient','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:21:16','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:21:16','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('b0ea9052-13b4-4c14-9be5-5d18fac05839','2008-09-28','WC-M Lorient','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:23:35','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:23:35','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('9fd7883c-2ee7-418a-9b82-71391ebf11d9','2008-07-20','WC-F KitzbÃ¼hel','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:27:03','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:27:03','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('92568d45-fd98-4eba-b374-6a25cb0247b7','2008-07-20','WC-M KitzbÃ¼hel','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:29:23','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:29:23','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('93da38ef-560d-4d12-a88a-f15739391707','2008-07-13','WC-F Tiszaujvaros','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:31:28','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:31:28','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('62e751c2-8b33-44b4-ac8b-2447b7443e95','2008-07-13','Wc-M Tiszaujvaros','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:34:45','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:34:45','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('c5c4f7bc-d46e-4e2f-99ae-83a507085f6a','2008-07-05','WC-F Hamburg','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:36:44','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:36:44','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('efe7065a-9bf1-40aa-a773-96b0016ca05d','2008-07-05','WC-M Hamburg','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:39:38','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:39:38','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('992f4fc9-6259-4544-9736-995a88c438d9','2008-06-22','WC-F HyVee','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:41:37','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:41:37','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('f8228ce1-e67c-4705-b63c-698c000c79a5','2008-06-22','WC-M Hy Vee','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:46:16','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:46:16','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('ce061097-9be0-4f4d-aeaa-b425de009633','2008-05-25','WC-F Madrid','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:48:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:48:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('3d6f7d71-4d36-4d97-88b3-e007073d4e8b','2008-05-25','WC-M Madrid','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:54:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:54:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('77c6f123-2c7b-49b9-b125-95e989ac1724','2008-05-04','WC-F Richards Bay','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:56:45','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:56:45','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('ca2954d1-c4e9-4f1c-b02a-fb818db8a1fe','2008-05-04','WC-M Richards Bay','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 00:58:39','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 00:58:39','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('fb287cb7-90b4-430d-ae60-098e683fb0b7','2008-04-26','Wc-F Tongyeong','tria',NULL,NULL,NULL,NULL,NULL,'2010-02-21 01:01:03','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-21 01:01:03','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('75e602a8-5d08-44dc-83d6-25491dcd9e02','2008-08-18','OG-M Beijing','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-09 11:02:56','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:02:56','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('5416b236-59d4-42f4-88cb-244cd65f63c0','2008-08-18','OG-F Beijing','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-09 11:06:37','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:06:37','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('0f52179e-a794-444d-8052-b6a6a1f44acc','2008-03-30','WC-M Mooloolaba','tria',NULL,NULL,'au',NULL,NULL,'2010-03-09 11:47:47','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:47:47','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('0b5a86dc-d0ec-47f6-86a4-fc2a3131c6c9','2008-03-30','WC-F Mooloolaba','tria',NULL,NULL,'au',NULL,NULL,'2010-03-09 11:50:21','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:50:21','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('9f27f03c-3ad0-41d4-9027-998ee4ef3042','2009-09-09','WORLDS-M-U23','tria',NULL,NULL,'au',NULL,NULL,'2010-03-11 11:13:31','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:13:31','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('7c693d99-a918-41fe-864e-311ed696a08b','2008-04-26','WC-M Tongyeong','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 11:17:43','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:18:42','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,1);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('c0aa097f-070c-4d67-af64-845437b2c4bc','2008-04-26','WC-M Tongyeong','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 11:19:40','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:19:40','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('b99f514d-267f-4973-a120-96986e2c2c7a','2009-07-18','EC-M Athlone','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 11:26:12','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:26:12','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('cb65cbfc-b068-4d9a-9b53-dad5f9d71c8d','2009-06-20','E-CHAMPS-U23 Tarzo Revine','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 11:33:22','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:33:22','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('ea7ad4ae-6512-4ab3-9300-a8b26059082b','2009-05-17','EC-M Pontevedra','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 11:37:59','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:37:59','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('ff038737-84a2-44c3-8b5e-614a29b2d81a','2009-04-05','EC-M Quarteira','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 11:40:48','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:40:48','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('43418740-34e7-4dcb-bc55-14cd6de7c5a0','2008-07-12','EC-M Athlone','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 11:44:44','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:44:44','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('9d899102-f963-46b6-928c-5c53dc2d89e2','2008-06-05','W-CHAMPS-JUN Vancouver','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 11:47:51','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:47:51','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('abfb08fa-0540-4e72-ab55-f095597ec481','2008-05-10','E-CHAMPS-JUN Lisbon','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 11:50:30','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:50:30','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('2a14545b-420d-400d-ab15-63d40bf6c227','2007-10-24','EC-M Alanya','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 11:54:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:54:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('0b35ed8e-2cef-48bd-86ae-f62a0b235472','2007-08-30','W-CHAMPS-M-JUN Hamburg','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 12:04:45','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 12:22:22','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('18033f3f-5242-4e47-ab3a-448a2d30f1df','2007-06-29','E-CHAMPS-M-JUN Copenhagen','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 12:26:11','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 12:26:11','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('0e9a11ba-0a77-48c7-9abb-f3342ae88193','2006-09-02','W-CHAMPS-M-JUN Lausanne','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 12:37:05','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 12:37:05','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('2f81e8e6-4b87-433e-800e-af16702827cb','2006-06-23','E-CHAMPS-M-JUN Autun','tria',NULL,NULL,NULL,NULL,NULL,'2010-03-11 12:40:04','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 12:40:04','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('e287b9cf-8b66-45e2-accc-2803d8b4d9ba','2010-03-13','Pillerseer X-Terra','terra','sprint','St. Ulrich','at',NULL,NULL,'2010-03-27 18:34:22','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-27 18:34:22','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `competitions`(`id`,`date`,`description`,`type`,`subtype`,`address`,`country_key`,`results_template`,`results_id`,`created_at`,`created_by`,`modified_at`,`modified_by`,`test`,`deleted`) values ('6e9c5042-405f-4d30-83d5-34f86c7ff66d','2010-03-31','Xterra 2','terra','short',NULL,'at',NULL,NULL,'2010-03-27 19:06:28','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-27 19:06:28','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

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

insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('x96bcbd2-676d-102c-ace2-9cc3fca64c87','0b0b7658-2ddb-11de-86ae-00301bb60f17','');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('63df5d72-3d22-411f-affa-3c974318e790','0b0b7658-2ddb-11de-86ae-00301bb60f17','');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('ba76369e-5c2b-4814-a1ca-6229a2a2fa1a','0b0b7658-2ddb-11de-86ae-00301bb60f17','');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('0d1edae8-a48a-4aa2-a9db-fdebd1acc15e','0b0b7658-2ddb-11de-86ae-00301bb60f17','[]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('30c5931c-cabb-42cf-9e55-5bfe663cefa0','0b0b7658-2ddb-11de-86ae-00301bb60f17','[{\"category\":\"Elite A\",\"limits\":[\"00:35:00\",\"3%\"],\"run\":[\"Allen\",\"00:28:30\"],\"swim\":[\"Haskin\",\"00:31:00\"],\"swimsuit\":true},{\"category\":\"Elite B\",\"limits\":[\"00:36:00\",\"00:35:00\"],\"run\":[\"Test\",\"00:32:00\"],\"swim\":[\"Test\",\"00:32:20\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('bb6116f8-fd51-4d05-8bfd-e0d86e228278','0b0b7658-2ddb-11de-86ae-00301bb60f17','');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('5337cdba-b2ac-42f6-bcd9-bd7825db4284','0b0b7658-2ddb-11de-86ae-00301bb60f17','');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('2e122f41-2121-4d16-94d6-f5dc328d84dc','0b0b7658-2ddb-11de-86ae-00301bb60f17','[{\"bike\":[\"Nicole Hackett\",\"01:23:36\"],\"category\":\"Elite A\",\"limits\":[\"1%\",\"00:37:00\",\"00:40:00\"],\"run\":[\"Rina Hill\",\"00:36:41\"],\"swim\":[\"Barbara Lindquist\",\"00:18:41\"],\"swimsuit\":null,\"tolerances\":[30,30,60]},{\"bike\":[\"Nicole Hackett\",\"01:23:36\"],\"category\":\"Elite B\",\"limits\":[null,null,null],\"run\":[null,null],\"swim\":[\"Barbara Lindquist\",\"00:18:41\"],\"swimsuit\":null,\"tolerances\":[null,null,null]},{\"bike\":[null,null],\"category\":null,\"limits\":[null,null,null],\"run\":[null,null],\"swim\":[null,null],\"swimsuit\":null,\"tolerances\":[50,50,50]}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('9956d670-cb98-4d78-930a-88722f68d6a2','0b0b7658-2ddb-11de-86ae-00301bb60f17','');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('a510cfbd-0f4e-4033-95f2-32f669cc964a','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\" Elite F\",\"limits\":[\"00:21:30\",\"3%\"],\"run\":[\"Dittmer\",\"00:34:18\"],\"swim\":[\"Haskins\",\"00:21:30\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('ffd573fd-355c-4307-b0b9-338901210fff','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:21:51\",\"3%\"],\"run\":[\"Dittmer\",\"00:34:18\"],\"swim\":[\"Haskins\",\"00:21:30\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('3724b912-dfff-481b-92fe-f4687aac0f1b','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:18:06\",\"3%\"],\"run\":[\"Moffatt\",\"00:34:42\"],\"swim\":[\"Peirsol\",\"00:16:36\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('a2f4545a-8998-4c4e-8f09-706ca409d0aa','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:19:52\",\"3%\"],\"run\":[\" Gemmel (NZL)\",\"00:31:43\"],\"swim\":[\"Amberger\",\"00:19:15\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('e2debec2-9127-4980-b1f3-0a939eb5b40b','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:18:18\",\"3%\"],\"run\":[\"Ide\",\"00:34:40\"],\"swim\":[\"Nakashima\",\"00:18:05\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('033849c9-c2ae-4b53-bb68-9b9e7fe24213','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:18:18\",\"3%\"],\"run\":[\"Ide (JPN)\",\"00:34:40\"],\"swim\":[\"Nakashima\",\"00:18:05\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('5c0a7ffd-085c-42fa-93fc-ad1c21f903b8','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:16:47\",\"3%\"],\"run\":[\"Rank (GER)\",\"00:30:53\"],\"swim\":[\"Vasiliev\",\"00:16:41\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('bceaed7b-fb33-49fa-a3f2-4b507b78e28b','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:19:48\",\"3%\"],\"run\":[\"Moffatt (AUS)\",\"00:35:38\"],\"swim\":[\"McLarty\",\"00:18:45\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('7d85149d-1b4b-4098-bae6-46d8e146beb3','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:19:36\",\"3%\"],\"run\":[\"Shoemaker (USA)\",\"00:31:32\"],\"swim\":[\"Atkinson\",\"00:19:14\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('cc602ead-3fa1-4355-a4e9-ad86414374b9','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:19:57\",\"3%\"],\"run\":[\"Sheedy-Ryan (AUS)\",\"00:34:24\"],\"swim\":[\"Raw\",\"00:19:25\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('ff9d8567-2d53-42fd-a311-a4f20efffb1d','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:18:10\",\"3%\"],\"run\":[\"Rouault (FRA)\",\"00:30:19\"],\"swim\":[\"Varga\",\"00:17:28\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('5fed2847-689c-489e-a7bf-e14c66bd3612','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:19:51\",\"3%\"],\"run\":[\"Robisch (GER)\",\"00:35:28\"],\"swim\":[\"McLarty\",\"00:18:44\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('9f55f3f0-fc1a-4d85-9746-f4b2ddaa66c9','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:18:05\",\"3%\"],\"run\":[\"Shoemaker (USA)\",\"00:32:21\"],\"swim\":[\"McLarty\",\"00:17:02\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('70a4cbf9-e670-498b-8abe-d327aa0591cf','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[null,null],\"run\":[null,null],\"swim\":[null,null],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('a8424bbe-162f-44f9-999f-f1862452443a','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:19:57\",\"3%\"],\"run\":[\"Snowsill (AUS)\",\"00:33:45\"],\"swim\":[\"McLarty\",\"00:19:12\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('94f019bf-d9e9-471a-9d88-b19f1cd4c7e6','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:18:43\",\"3%\"],\"run\":[\"Docherty (NZL)\",\"00:30:20\"],\"swim\":[\"Lee\",\"00:18:05\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('6ba80501-1754-4d63-9dc7-4545c38b8008','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:19:39\",\"3%\"],\"run\":[\"Warriner (NZL)\",\"00:35:03\"],\"swim\":[\"Haskins\",\"00:19:35\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('de8f6cfb-1625-4a80-ae53-5c3fd20bc75e','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:18:03\",\"3%\"],\"run\":[\"Brownlee (GBR)\",\"00:30:31\"],\"swim\":[\"Vasiliev\",\"00:17:46\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('043424a4-e0f9-4736-b804-37a53d7b642a','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:20:11\",\"3%\"],\"run\":[\"Moffatt (AUS)\",\"00:34:24\"],\"swim\":[\"Haskins\",\"00:20:05\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('83d7ea1c-4ed8-4967-8ff9-33d19aab76eb','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:20:08\",\"3%\"],\"run\":[\"Frodeno (GER)\",\"00:30:26\"],\"swim\":[\"Petzold\",\"00:20:04\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('944a9071-c02c-4574-b1c8-a799706e9d1b','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:18:06\",\"3%\"],\"run\":[\"Moffatt (AUS)\",\"00:34:42\"],\"swim\":[\"Peirsol\",\"00:16:36\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('7034b557-4719-42bd-96a9-aadb846b68af','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:16:40\",\"3%\"],\"run\":[\"Brownlee (GBR)\",\"00:30:36\"],\"swim\":[\"Varga\",\"00:15:58\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('6f23e23d-1bd7-4300-9dfd-72cbed333c8e','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:18:27\",\"3%\"],\"run\":[\"Moffatt (AUS)\",\"00:34:28\"],\"swim\":[\"Peirsol\",\"00:16:41\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('01ea6bc5-2263-4298-b38d-e403faa9c0e3','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:17:21\",\"3%\"],\"run\":[\"Shoemaker (USA)\",\"00:29:37\"],\"swim\":[\"Varga\",\"00:16:09\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('351a928e-588e-4b68-86dc-7e5dcd0c0749','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:20:00\",\"3%\"],\"run\":[\"Spirig\",\"00:32:59\"],\"swim\":[\"Luxford\",\"00:19:30\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('59db8e01-ada7-4a17-ba6a-e9c0d41323cf','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:19:17\",\"3%\"],\"run\":[\"Brownlee (GBR)\",\"00:28:43\"],\"swim\":[\"Vasiliev\",\"00:18:01\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('ad9212bd-2f5d-453a-9c67-4549c2ca2164','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:17:54\",\"3%\"],\"run\":[\"Norden (SWE)\",\"00:35:07\"],\"swim\":[\"Nakashima\",\"00:17:35\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('bfdeef28-dd69-4e27-b8f9-ef03c5ea94c0','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:17:54\",\"3%\"],\"run\":[\"Norden (SWE)\",\"00:35:07\"],\"swim\":[\"Nakashima\",\"00:17:35\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('395c99de-7126-4e42-a414-01cd1e7c8b84','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:17:20\",\"3%\"],\"run\":[\"Gomez (ESP)\",\"00:30:36\"],\"swim\":[\"Amberger\",\"00:16:27\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('494c57f6-9809-4257-a453-d11af4fc16bb','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[null,null],\"run\":[null,null],\"swim\":[null,null],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('d92b9db6-4e55-4f24-958d-679ef6663829','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:17:46\",\"3%\"],\"run\":[\"Brownlee (GBR)\",\"00:29:04\"],\"swim\":[\"Petzold\",\"00:16:59\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('6daa7f39-dc3f-47db-95aa-bcd20b3fbdb4','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:19:50\",\"3%\"],\"run\":[\"Whitcomb\",\"00:35:56\"],\"swim\":[\"Groff\",\"00:19:46\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('d2cea231-5258-4069-9e06-100e318c1a05','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[null,null],\"run\":[null,null],\"swim\":[null,null],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('b8d808a9-d368-4c47-8354-a68e82412f0e','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:18:20\",\"3%\"],\"run\":[\"Norden (SWE)\",\"00:33:27\"],\"swim\":[\"Groff\",\"00:18:16\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('b0ea9052-13b4-4c14-9be5-5d18fac05839','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:17:49\",\"3%\"],\"run\":[\"Rana (ESP)\",\"00:29:38\"],\"swim\":[\"Aurelien\",\"00:17:04\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('9fd7883c-2ee7-418a-9b82-71391ebf11d9','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:20:34\",\"3%\"],\"run\":[\"Spirig\",\"00:34:10\"],\"swim\":[\"Di Marco\",\"00:20:11\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('92568d45-fd98-4eba-b374-6a25cb0247b7','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:18:57\",\"3%\"],\"run\":[\"Rana (ESP)\",\"00:31:02\"],\"swim\":[\"Vasiliev\",\"00:18:15\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('93da38ef-560d-4d12-a88a-f15739391707','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:20:27\",\"3%\"],\"run\":[\"Whitcombe /GBR)\",\"00:35:29\"],\"swim\":[\"Groff\",\"00:19:58\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('62e751c2-8b33-44b4-ac8b-2447b7443e95','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:19:22\",\"3%\"],\"run\":[\"Gomez (ESP)\",\"00:30:33\"],\"swim\":[\"Varga\",\"00:18:12\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('c5c4f7bc-d46e-4e2f-99ae-83a507085f6a','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:20:02\",\"3%\"],\"run\":[\"Lisk\",\"00:34:26\"],\"swim\":[\"Murua\",\"00:19:49\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('efe7065a-9bf1-40aa-a773-96b0016ca05d','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:18:29\",\"3%\"],\"run\":[\"Unger (GER)\",\"00:30:34\"],\"swim\":[\"Vasiliev\",\"00:17:40\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('992f4fc9-6259-4544-9736-995a88c438d9','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:20:51\",\"3%\"],\"run\":[\"Snowsill (AUS)\",\"00:34:22\"],\"swim\":[\"McLarty\",\"00:19:40\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('f8228ce1-e67c-4705-b63c-698c000c79a5','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:19:23\",\"3%\"],\"run\":[\"Hennig (DEN)\",\"00:31:32\"],\"swim\":[\"Kemper\",\"00:19:06\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('ce061097-9be0-4f4d-aeaa-b425de009633','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:19:53\",\"3%\"],\"run\":[\"Fernandes (POR)\",\"00:34:44\"],\"swim\":[\"Jenkins\",\"00:19:19\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('3d6f7d71-4d36-4d97-88b3-e007073d4e8b','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:18:20\",\"3%\"],\"run\":[\"Gomez (ESP)\",\"00:31:01\"],\"swim\":[\"Poulat\",\"00:17:53\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('77c6f123-2c7b-49b9-b125-95e989ac1724','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:19:10\",\"3%\"],\"run\":[\"Abram (AUS)\",\"00:35:01\"],\"swim\":[\"Groff\",\"00:18:21\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('ca2954d1-c4e9-4f1c-b02a-fb818db8a1fe','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:18:34\",\"3%\"],\"run\":[\"Unger (GER)\",\"00:30:51\"],\"swim\":[\"Albert\",\"00:18:08\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('fb287cb7-90b4-430d-ae60-098e683fb0b7','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:20:02\",\"3%\"],\"run\":[\"Warriner (NZL)\",\"00:34:10\"],\"swim\":[\"Janj\",\"00:19:19\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('75e602a8-5d08-44dc-83d6-25491dcd9e02','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite M\",\"limits\":[\"00:18:29\",\"3%\"],\"run\":[\"Frodeno\",\"00:30:45\"],\"swim\":[\"S. Reed\",\"00:18:00\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('5416b236-59d4-42f4-88cb-244cd65f63c0','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"category\":\"Elite F\",\"limits\":[\"00:20:00\",\"00:03:00\"],\"run\":[\"Snowsill\",\"00:33:16\"],\"swim\":[\"Bennett\",\"00:19:48\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('0f52179e-a794-444d-8052-b6a6a1f44acc','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Elite M\",\"limits\":[\"00:19:37\",\"3%\"],\"run\":[\"Gomez\",\"00:30:29\"],\"swim\":[\"Jenkins\",\"00:18:46\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('0b5a86dc-d0ec-47f6-86a4-fc2a3131c6c9','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Elite F\",\"limits\":[\"00:20:45\",\"3%\"],\"run\":[\"Snowsill\",\"00:33:19\"],\"swim\":[\"Bennett\",\"00:20:26\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('9f27f03c-3ad0-41d4-9027-998ee4ef3042','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"U23\",\"limits\":[\"00:17:28\",\"3%\",null],\"run\":[\"Zipf\",\"00:30:31\"],\"swim\":[\"McCartney\",\"00:17:12\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('c0aa097f-070c-4d67-af64-845437b2c4bc','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Elite M\",\"limits\":[\"00:18:34\",\"3%\",null],\"run\":[\"Don\",\"00:30:26\"],\"swim\":[\"Vasiliev\",\"00:18:08\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('b99f514d-267f-4973-a120-96986e2c2c7a','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Elite M\",\"limits\":[\"00:17:52\",\"3%\",null],\"run\":[\"Fidalgo ESP\",\"00:29:58\"],\"swim\":[\"Rafphael\",\"00:17:19\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('cb65cbfc-b068-4d9a-9b53-dad5f9d71c8d','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"U23\",\"limits\":[\"00:17:47\",\"3%\",null],\"run\":[\"Brukhankov\",\"00:32:02\"],\"swim\":[\"Varga\",\"00:17:37\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('ea7ad4ae-6512-4ab3-9300-a8b26059082b','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Elite M\",\"limits\":[\"00:18:00\",\"3%\",null],\"run\":[\"Polyansky\",\"00:29:31\"],\"swim\":[\"Rivera\",\"00:16:49\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('ff038737-84a2-44c3-8b5e-614a29b2d81a','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Elite M\",\"limits\":[\"00:17:56\",\"3%\",null],\"run\":[\"Polyansky\",\"00:30:49\"],\"swim\":[\"Vasiliev\",\"00:17:45\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('43418740-34e7-4dcb-bc55-14cd6de7c5a0','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Elite M\",\"limits\":[\"00:18:01\",\"3%\",null],\"run\":[\"Luis\",\"00:32:06\"],\"swim\":[\"Stannard\",\"00:17:15\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('9d899102-f963-46b6-928c-5c53dc2d89e2','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Junior\",\"limits\":[\"00:13:28\",\"3%\",null],\"run\":[\"Gonzalez MEX\",\"00:15:04\"],\"swim\":[\"Adams\",\"00:13:24\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('abfb08fa-0540-4e72-ab55-f095597ec481','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Junior\",\"limits\":[\"00:08:46\",\"3%\",null],\"run\":[\"LÃ¶schke GER\",\"00:15:37\"],\"swim\":[\"Vasiliev\",\"00:08:43\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('2a14545b-420d-400d-ab15-63d40bf6c227','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Elite M\",\"limits\":[\"00:18:41\",\"3%\",null],\"run\":[\"Malyshev RUS\",\"00:31:44\"],\"swim\":[\"Luis\",\"00:17:47\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('0b35ed8e-2cef-48bd-86ae-f62a0b235472','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Junior\",\"limits\":[\"00:08:58\",\"3%\",null],\"run\":[\"Brownlee A GBR\",\"00:14:59\"],\"swim\":[\"Amberger\",\"00:08:50\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('18033f3f-5242-4e47-ab3a-448a2d30f1df','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Junior\",\"limits\":[\"00:08:22\",\"3%\",null],\"run\":[\"Brownlee A GBR\",\"00:15:35\"],\"swim\":[\"Molinari\",\"00:08:14\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('0e9a11ba-0a77-48c7-9abb-f3342ae88193','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Junior\",\"limits\":[\"00:09:07\",\"3%\",null],\"run\":[\"Duplinsky USA\",\"00:15:20\"],\"swim\":[\"Luis\",\"00:08:46\"],\"swimsuit\":true}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('2f81e8e6-4b87-433e-800e-af16702827cb','aa37127b-0622-483d-89c8-fe378fe63a0d','[{\"bike\":[null,null],\"category\":\"Junior\",\"limits\":[\"00:09:19\",\"3%\",null],\"run\":[\"Brownlee A GBR\",\"00:16:00\"],\"swim\":[\"Luis\",\"00:09:13\"],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('e287b9cf-8b66-45e2-accc-2803d8b4d9ba','0b0b7658-2ddb-11de-86ae-00301bb60f17','[{\"bike\":[null,null],\"category\":\"Elite A\",\"limits\":[null,null,null],\"run\":[null,null],\"swim\":[null,null],\"swimsuit\":null}]');
insert  into `competitions_scouts`(`competition_id`,`scout_id`,`limits`) values ('6e9c5042-405f-4d30-83d5-34f86c7ff66d','0b0b7658-2ddb-11de-86ae-00301bb60f17','[]');

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

/*Data for the table `day_infos` */

insert  into `day_infos`(`date`,`person_id`,`resting_hr`,`weight_morning`,`weight_evening`,`sleeping_quality`,`temperature`,`location`,`altitude`,`dinners_lunch`,`dinners_evening`,`fluids_intake`,`sleeping_hours`,`tiredness`,`feeling`,`training_intensity`,`training_valuation`,`restday`,`travelday`,`camp`,`illness`,`illness_text`,`massage`,`massage_text`,`therapy`,`therapy_text`,`comment`,`comment_coach`,`ck_morning`,`ck_morning_time`,`ck_lunch`,`ck_lunch_time`,`ck_evening`,`ck_evening_time`,`urea_morning`,`urea_morning_time`,`urea_lunch`,`urea_lunch_time`,`urea_evening`,`urea_evening_time`,`glucose_morning`,`glucose_morning_time`,`glucose_lunch`,`glucose_lunch_time`,`glucose_evening`,`glucose_evening_time`,`hb`,`hb_time`,`hkt`,`hkt_time`,`weather`) values ('2010-04-04','0b0b7658-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `day_infos`(`date`,`person_id`,`resting_hr`,`weight_morning`,`weight_evening`,`sleeping_quality`,`temperature`,`location`,`altitude`,`dinners_lunch`,`dinners_evening`,`fluids_intake`,`sleeping_hours`,`tiredness`,`feeling`,`training_intensity`,`training_valuation`,`restday`,`travelday`,`camp`,`illness`,`illness_text`,`massage`,`massage_text`,`therapy`,`therapy_text`,`comment`,`comment_coach`,`ck_morning`,`ck_morning_time`,`ck_lunch`,`ck_lunch_time`,`ck_evening`,`ck_evening_time`,`urea_morning`,`urea_morning_time`,`urea_lunch`,`urea_lunch_time`,`urea_evening`,`urea_evening_time`,`glucose_morning`,`glucose_morning_time`,`glucose_lunch`,`glucose_lunch_time`,`glucose_evening`,`glucose_evening_time`,`hb`,`hb_time`,`hkt`,`hkt_time`,`weather`) values ('2010-04-05','10f52302-2ddb-11de-86ae-00301bb60f17',80,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `day_infos`(`date`,`person_id`,`resting_hr`,`weight_morning`,`weight_evening`,`sleeping_quality`,`temperature`,`location`,`altitude`,`dinners_lunch`,`dinners_evening`,`fluids_intake`,`sleeping_hours`,`tiredness`,`feeling`,`training_intensity`,`training_valuation`,`restday`,`travelday`,`camp`,`illness`,`illness_text`,`massage`,`massage_text`,`therapy`,`therapy_text`,`comment`,`comment_coach`,`ck_morning`,`ck_morning_time`,`ck_lunch`,`ck_lunch_time`,`ck_evening`,`ck_evening_time`,`urea_morning`,`urea_morning_time`,`urea_lunch`,`urea_lunch_time`,`urea_evening`,`urea_evening_time`,`glucose_morning`,`glucose_morning_time`,`glucose_lunch`,`glucose_lunch_time`,`glucose_evening`,`glucose_evening_time`,`hb`,`hb_time`,`hkt`,`hkt_time`,`weather`) values ('2010-04-06','10f52302-2ddb-11de-86ae-00301bb60f17',90,NULL,NULL,0,'25.5',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'fewclouds');
insert  into `day_infos`(`date`,`person_id`,`resting_hr`,`weight_morning`,`weight_evening`,`sleeping_quality`,`temperature`,`location`,`altitude`,`dinners_lunch`,`dinners_evening`,`fluids_intake`,`sleeping_hours`,`tiredness`,`feeling`,`training_intensity`,`training_valuation`,`restday`,`travelday`,`camp`,`illness`,`illness_text`,`massage`,`massage_text`,`therapy`,`therapy_text`,`comment`,`comment_coach`,`ck_morning`,`ck_morning_time`,`ck_lunch`,`ck_lunch_time`,`ck_evening`,`ck_evening_time`,`urea_morning`,`urea_morning_time`,`urea_lunch`,`urea_lunch_time`,`urea_evening`,`urea_evening_time`,`glucose_morning`,`glucose_morning_time`,`glucose_lunch`,`glucose_lunch_time`,`glucose_evening`,`glucose_evening_time`,`hb`,`hb_time`,`hkt`,`hkt_time`,`weather`) values ('2010-04-07','10f52302-2ddb-11de-86ae-00301bb60f17',90,NULL,'85.0',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

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

insert  into `doctors`(`id`,`name`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`longitude`,`latitude`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('c94e3cff-495d-11de-921e-1178275b5596','Medizinalrat Dr. Helmut Schwitzer','Kirchweg','2','6391','Fieberbrunn','Tirol','at',NULL,NULL,'office@drschwitzer.at','http://www.drschwitzer.at','05354 / 56535',NULL,'05354 / 56535 - 75','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-05-27 23:05:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `doctors`(`id`,`name`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`longitude`,`latitude`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('d0782a5c-495d-11de-921e-1178275b5596','Dr. Michael Plattner','Dorf','39','6373','Jochberg','Tirol','at','12.4180161953','47.3754969916','michael.plattner@gmail.com',NULL,'05355 / 20071',NULL,NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-12-10 23:22:13','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `doctors`(`id`,`name`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`longitude`,`latitude`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e941cc54-cceb-4a38-984c-f55b20c41e6a','Dr. Hannes Lechner',NULL,NULL,'6391','Fieberbrunn',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-09-10 18:58:28','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-05-27 23:05:40','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `doctors`(`id`,`name`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`longitude`,`latitude`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('2650b3dc-a509-49d1-94e6-56ce7d63e727','Dr. Mittermayer Paul',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-11-27 23:28:44','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

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

insert  into `entities_have_labels`(`entity`,`label`,`person_id`) values ('0c05017b-0f9f-4bc3-baeb-c1f8eb734120','d89401a3-d6cc-41dd-b7df-32823172d893','0b0b7658-2ddb-11de-86ae-00301bb60f17');
insert  into `entities_have_labels`(`entity`,`label`,`person_id`) values ('42473dc1-e4f2-4408-910f-10b4e64a04c1','e22d73e4-83c3-4a21-8f5d-9d76cefb6761','0b0b7658-2ddb-11de-86ae-00301bb60f17');
insert  into `entities_have_labels`(`entity`,`label`,`person_id`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa','55620350-6d49-11de-a69b-604b59d93787','0b0b7658-2ddb-11de-86ae-00301bb60f17');
insert  into `entities_have_labels`(`entity`,`label`,`person_id`) values ('b8a954e4-4bca-11de-ab35-74df036e1e4f','3418c962-818c-43ee-ad7a-5964fdd2eb6c','10f52302-2ddb-11de-86ae-00301bb60f17');
insert  into `entities_have_labels`(`entity`,`label`,`person_id`) values ('bb6116f8-fd51-4d05-8bfd-e0d86e228278','d89401a3-d6cc-41dd-b7df-32823172d893','0b0b7658-2ddb-11de-86ae-00301bb60f17');
insert  into `entities_have_labels`(`entity`,`label`,`person_id`) values ('x96bcbd2-676d-102c-ace2-9cc3fca64c87','e22d73e4-83c3-4a21-8f5d-9d76cefb6761','0b0b7658-2ddb-11de-86ae-00301bb60f17');

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

/*Data for the table `import_templates` */

insert  into `import_templates`(`entity`,`person_id`,`description`,`starting_row`,`mapping`) values ('RESULT','0b0b7658-2ddb-11de-86ae-00301bb60f17','Test',1,'[1, 2, 3, 4, 5, 6]');
insert  into `import_templates`(`entity`,`person_id`,`description`,`starting_row`,`mapping`) values ('RESULT','0b0b7658-2ddb-11de-86ae-00301bb60f17','ITU',2,'[1, 11, 4, 3, 6, 8, 10]');

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
insert  into `k_categories`(`key`) values ('results');

/*Table structure for table `k_compsubtypes` */

DROP TABLE IF EXISTS `k_compsubtypes`;

CREATE TABLE `k_compsubtypes` (
  `key` varchar(10) NOT NULL,
  `key_parent` varchar(10) NOT NULL,
  `ordinal` int(11) DEFAULT NULL,
  PRIMARY KEY (`key`,`key_parent`),
  KEY `fk_compsubtypes_comptypes` (`key_parent`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Keys for type of competition';

/*Data for the table `k_compsubtypes` */

insert  into `k_compsubtypes`(`key`,`key_parent`,`ordinal`) values ('sprint','tria',2);
insert  into `k_compsubtypes`(`key`,`key_parent`,`ordinal`) values ('long','tria',3);
insert  into `k_compsubtypes`(`key`,`key_parent`,`ordinal`) values ('olympic','tria',1);
insert  into `k_compsubtypes`(`key`,`key_parent`,`ordinal`) values ('im','tria',5);
insert  into `k_compsubtypes`(`key`,`key_parent`,`ordinal`) values ('him','tria',4);
insert  into `k_compsubtypes`(`key`,`key_parent`,`ordinal`) values ('short','terra',1);
insert  into `k_compsubtypes`(`key`,`key_parent`,`ordinal`) values ('sprint','terra',2);
insert  into `k_compsubtypes`(`key`,`key_parent`,`ordinal`) values ('middle','terra',3);
insert  into `k_compsubtypes`(`key`,`key_parent`,`ordinal`) values ('long','terra',4);

/*Table structure for table `k_comptypes` */

DROP TABLE IF EXISTS `k_comptypes`;

CREATE TABLE `k_comptypes` (
  `key` varchar(10) NOT NULL,
  `ordinal` int(11) DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Keys for type of competition';

/*Data for the table `k_comptypes` */

insert  into `k_comptypes`(`key`,`ordinal`) values ('tria',1);
insert  into `k_comptypes`(`key`,`ordinal`) values ('terra',2);

/*Table structure for table `k_countries` */

DROP TABLE IF EXISTS `k_countries`;

CREATE TABLE `k_countries` (
  `key` varchar(2) NOT NULL,
  `currency_key` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_countries` */

insert  into `k_countries`(`key`,`currency_key`) values ('ad','');
insert  into `k_countries`(`key`,`currency_key`) values ('de','eur');
insert  into `k_countries`(`key`,`currency_key`) values ('at','eur');
insert  into `k_countries`(`key`,`currency_key`) values ('ae','');
insert  into `k_countries`(`key`,`currency_key`) values ('af','');
insert  into `k_countries`(`key`,`currency_key`) values ('ag','');
insert  into `k_countries`(`key`,`currency_key`) values ('ai','');
insert  into `k_countries`(`key`,`currency_key`) values ('al','');
insert  into `k_countries`(`key`,`currency_key`) values ('am','');
insert  into `k_countries`(`key`,`currency_key`) values ('an','');
insert  into `k_countries`(`key`,`currency_key`) values ('ao','');
insert  into `k_countries`(`key`,`currency_key`) values ('aq','');
insert  into `k_countries`(`key`,`currency_key`) values ('ar','');
insert  into `k_countries`(`key`,`currency_key`) values ('as','');
insert  into `k_countries`(`key`,`currency_key`) values ('au','');
insert  into `k_countries`(`key`,`currency_key`) values ('aw','');
insert  into `k_countries`(`key`,`currency_key`) values ('az','');
insert  into `k_countries`(`key`,`currency_key`) values ('ba','');
insert  into `k_countries`(`key`,`currency_key`) values ('bb','');
insert  into `k_countries`(`key`,`currency_key`) values ('bd','');
insert  into `k_countries`(`key`,`currency_key`) values ('be','');
insert  into `k_countries`(`key`,`currency_key`) values ('bf','');
insert  into `k_countries`(`key`,`currency_key`) values ('bg','');
insert  into `k_countries`(`key`,`currency_key`) values ('bh','');
insert  into `k_countries`(`key`,`currency_key`) values ('bi','');
insert  into `k_countries`(`key`,`currency_key`) values ('bj','');
insert  into `k_countries`(`key`,`currency_key`) values ('bm','');
insert  into `k_countries`(`key`,`currency_key`) values ('bn','');
insert  into `k_countries`(`key`,`currency_key`) values ('bo','');
insert  into `k_countries`(`key`,`currency_key`) values ('br','');
insert  into `k_countries`(`key`,`currency_key`) values ('bs','');
insert  into `k_countries`(`key`,`currency_key`) values ('bt','');
insert  into `k_countries`(`key`,`currency_key`) values ('bv','');
insert  into `k_countries`(`key`,`currency_key`) values ('bw','');
insert  into `k_countries`(`key`,`currency_key`) values ('by','');
insert  into `k_countries`(`key`,`currency_key`) values ('bz','');
insert  into `k_countries`(`key`,`currency_key`) values ('ca','');
insert  into `k_countries`(`key`,`currency_key`) values ('cc','');
insert  into `k_countries`(`key`,`currency_key`) values ('cf','');
insert  into `k_countries`(`key`,`currency_key`) values ('cg','');
insert  into `k_countries`(`key`,`currency_key`) values ('ch','');
insert  into `k_countries`(`key`,`currency_key`) values ('ci','');
insert  into `k_countries`(`key`,`currency_key`) values ('ck','');
insert  into `k_countries`(`key`,`currency_key`) values ('cl','');
insert  into `k_countries`(`key`,`currency_key`) values ('cm','');
insert  into `k_countries`(`key`,`currency_key`) values ('cn','');
insert  into `k_countries`(`key`,`currency_key`) values ('co','');
insert  into `k_countries`(`key`,`currency_key`) values ('cr','');
insert  into `k_countries`(`key`,`currency_key`) values ('cs','');
insert  into `k_countries`(`key`,`currency_key`) values ('cu','');
insert  into `k_countries`(`key`,`currency_key`) values ('cv','');
insert  into `k_countries`(`key`,`currency_key`) values ('cx','');
insert  into `k_countries`(`key`,`currency_key`) values ('cy','');
insert  into `k_countries`(`key`,`currency_key`) values ('cz','');
insert  into `k_countries`(`key`,`currency_key`) values ('dj','');
insert  into `k_countries`(`key`,`currency_key`) values ('dk','');
insert  into `k_countries`(`key`,`currency_key`) values ('dm','');
insert  into `k_countries`(`key`,`currency_key`) values ('do','');
insert  into `k_countries`(`key`,`currency_key`) values ('dz','');
insert  into `k_countries`(`key`,`currency_key`) values ('ec','');
insert  into `k_countries`(`key`,`currency_key`) values ('ee','');
insert  into `k_countries`(`key`,`currency_key`) values ('eg','');
insert  into `k_countries`(`key`,`currency_key`) values ('er','');
insert  into `k_countries`(`key`,`currency_key`) values ('es','');
insert  into `k_countries`(`key`,`currency_key`) values ('et','');
insert  into `k_countries`(`key`,`currency_key`) values ('fi','');
insert  into `k_countries`(`key`,`currency_key`) values ('fj','');
insert  into `k_countries`(`key`,`currency_key`) values ('fk','');
insert  into `k_countries`(`key`,`currency_key`) values ('fm','');
insert  into `k_countries`(`key`,`currency_key`) values ('fo','');
insert  into `k_countries`(`key`,`currency_key`) values ('fr','');
insert  into `k_countries`(`key`,`currency_key`) values ('ga','');
insert  into `k_countries`(`key`,`currency_key`) values ('gb','');
insert  into `k_countries`(`key`,`currency_key`) values ('gd','');
insert  into `k_countries`(`key`,`currency_key`) values ('ge','');
insert  into `k_countries`(`key`,`currency_key`) values ('gf','');
insert  into `k_countries`(`key`,`currency_key`) values ('gh','');
insert  into `k_countries`(`key`,`currency_key`) values ('gi','');
insert  into `k_countries`(`key`,`currency_key`) values ('gl','');
insert  into `k_countries`(`key`,`currency_key`) values ('gm','');
insert  into `k_countries`(`key`,`currency_key`) values ('gn','');
insert  into `k_countries`(`key`,`currency_key`) values ('gp','');
insert  into `k_countries`(`key`,`currency_key`) values ('gq','');
insert  into `k_countries`(`key`,`currency_key`) values ('gr','');
insert  into `k_countries`(`key`,`currency_key`) values ('gt','');
insert  into `k_countries`(`key`,`currency_key`) values ('gu','');
insert  into `k_countries`(`key`,`currency_key`) values ('gw','');
insert  into `k_countries`(`key`,`currency_key`) values ('gy','');
insert  into `k_countries`(`key`,`currency_key`) values ('hk','');
insert  into `k_countries`(`key`,`currency_key`) values ('hm','');
insert  into `k_countries`(`key`,`currency_key`) values ('hn','');
insert  into `k_countries`(`key`,`currency_key`) values ('hr','');
insert  into `k_countries`(`key`,`currency_key`) values ('ht','');
insert  into `k_countries`(`key`,`currency_key`) values ('hu','');
insert  into `k_countries`(`key`,`currency_key`) values ('id','');
insert  into `k_countries`(`key`,`currency_key`) values ('ie','');
insert  into `k_countries`(`key`,`currency_key`) values ('il','');
insert  into `k_countries`(`key`,`currency_key`) values ('in','');
insert  into `k_countries`(`key`,`currency_key`) values ('io','');
insert  into `k_countries`(`key`,`currency_key`) values ('iq','');
insert  into `k_countries`(`key`,`currency_key`) values ('ir','');
insert  into `k_countries`(`key`,`currency_key`) values ('is','');
insert  into `k_countries`(`key`,`currency_key`) values ('it','');
insert  into `k_countries`(`key`,`currency_key`) values ('jm','');
insert  into `k_countries`(`key`,`currency_key`) values ('jo','');
insert  into `k_countries`(`key`,`currency_key`) values ('jp','');
insert  into `k_countries`(`key`,`currency_key`) values ('ke','');
insert  into `k_countries`(`key`,`currency_key`) values ('kg','');
insert  into `k_countries`(`key`,`currency_key`) values ('kh','');
insert  into `k_countries`(`key`,`currency_key`) values ('ki','');
insert  into `k_countries`(`key`,`currency_key`) values ('km','');
insert  into `k_countries`(`key`,`currency_key`) values ('kn','');
insert  into `k_countries`(`key`,`currency_key`) values ('kp','');
insert  into `k_countries`(`key`,`currency_key`) values ('kr','');
insert  into `k_countries`(`key`,`currency_key`) values ('kw','');
insert  into `k_countries`(`key`,`currency_key`) values ('ky','');
insert  into `k_countries`(`key`,`currency_key`) values ('kz','');
insert  into `k_countries`(`key`,`currency_key`) values ('la','');
insert  into `k_countries`(`key`,`currency_key`) values ('lb','');
insert  into `k_countries`(`key`,`currency_key`) values ('lc','');
insert  into `k_countries`(`key`,`currency_key`) values ('li','');
insert  into `k_countries`(`key`,`currency_key`) values ('lk','');
insert  into `k_countries`(`key`,`currency_key`) values ('lr','');
insert  into `k_countries`(`key`,`currency_key`) values ('ls','');
insert  into `k_countries`(`key`,`currency_key`) values ('lt','');
insert  into `k_countries`(`key`,`currency_key`) values ('lu','');
insert  into `k_countries`(`key`,`currency_key`) values ('lv','');
insert  into `k_countries`(`key`,`currency_key`) values ('ly','');
insert  into `k_countries`(`key`,`currency_key`) values ('ma','');
insert  into `k_countries`(`key`,`currency_key`) values ('mc','');
insert  into `k_countries`(`key`,`currency_key`) values ('md','');
insert  into `k_countries`(`key`,`currency_key`) values ('me','');
insert  into `k_countries`(`key`,`currency_key`) values ('mg','');
insert  into `k_countries`(`key`,`currency_key`) values ('mh','');
insert  into `k_countries`(`key`,`currency_key`) values ('mk','');
insert  into `k_countries`(`key`,`currency_key`) values ('ml','');
insert  into `k_countries`(`key`,`currency_key`) values ('mm','');
insert  into `k_countries`(`key`,`currency_key`) values ('mn','');
insert  into `k_countries`(`key`,`currency_key`) values ('mo','');
insert  into `k_countries`(`key`,`currency_key`) values ('mp','');
insert  into `k_countries`(`key`,`currency_key`) values ('mq','');
insert  into `k_countries`(`key`,`currency_key`) values ('mr','');
insert  into `k_countries`(`key`,`currency_key`) values ('ms','');
insert  into `k_countries`(`key`,`currency_key`) values ('mt','');
insert  into `k_countries`(`key`,`currency_key`) values ('mu','');
insert  into `k_countries`(`key`,`currency_key`) values ('mv','');
insert  into `k_countries`(`key`,`currency_key`) values ('mw','');
insert  into `k_countries`(`key`,`currency_key`) values ('mx','');
insert  into `k_countries`(`key`,`currency_key`) values ('my','');
insert  into `k_countries`(`key`,`currency_key`) values ('mz','');
insert  into `k_countries`(`key`,`currency_key`) values ('na','');
insert  into `k_countries`(`key`,`currency_key`) values ('nc','');
insert  into `k_countries`(`key`,`currency_key`) values ('ne','');
insert  into `k_countries`(`key`,`currency_key`) values ('nf','');
insert  into `k_countries`(`key`,`currency_key`) values ('ng','');
insert  into `k_countries`(`key`,`currency_key`) values ('ni','');
insert  into `k_countries`(`key`,`currency_key`) values ('nl','');
insert  into `k_countries`(`key`,`currency_key`) values ('no','');
insert  into `k_countries`(`key`,`currency_key`) values ('np','');
insert  into `k_countries`(`key`,`currency_key`) values ('nr','');
insert  into `k_countries`(`key`,`currency_key`) values ('nu','');
insert  into `k_countries`(`key`,`currency_key`) values ('nz','');
insert  into `k_countries`(`key`,`currency_key`) values ('om','');
insert  into `k_countries`(`key`,`currency_key`) values ('pa','');
insert  into `k_countries`(`key`,`currency_key`) values ('pe','');
insert  into `k_countries`(`key`,`currency_key`) values ('pf','');
insert  into `k_countries`(`key`,`currency_key`) values ('pg','');
insert  into `k_countries`(`key`,`currency_key`) values ('ph','');
insert  into `k_countries`(`key`,`currency_key`) values ('pk','');
insert  into `k_countries`(`key`,`currency_key`) values ('pl','');
insert  into `k_countries`(`key`,`currency_key`) values ('pm','');
insert  into `k_countries`(`key`,`currency_key`) values ('pn','');
insert  into `k_countries`(`key`,`currency_key`) values ('pr','');
insert  into `k_countries`(`key`,`currency_key`) values ('ps','');
insert  into `k_countries`(`key`,`currency_key`) values ('pt','');
insert  into `k_countries`(`key`,`currency_key`) values ('pw','');
insert  into `k_countries`(`key`,`currency_key`) values ('py','');
insert  into `k_countries`(`key`,`currency_key`) values ('qa','');
insert  into `k_countries`(`key`,`currency_key`) values ('re','');
insert  into `k_countries`(`key`,`currency_key`) values ('ro','');
insert  into `k_countries`(`key`,`currency_key`) values ('rs','');
insert  into `k_countries`(`key`,`currency_key`) values ('ru','');
insert  into `k_countries`(`key`,`currency_key`) values ('rw','');
insert  into `k_countries`(`key`,`currency_key`) values ('sa','');
insert  into `k_countries`(`key`,`currency_key`) values ('sb','');
insert  into `k_countries`(`key`,`currency_key`) values ('sc','');
insert  into `k_countries`(`key`,`currency_key`) values ('sd','');
insert  into `k_countries`(`key`,`currency_key`) values ('se','');
insert  into `k_countries`(`key`,`currency_key`) values ('sg','');
insert  into `k_countries`(`key`,`currency_key`) values ('sh','');
insert  into `k_countries`(`key`,`currency_key`) values ('si','');
insert  into `k_countries`(`key`,`currency_key`) values ('sj','');
insert  into `k_countries`(`key`,`currency_key`) values ('sk','');
insert  into `k_countries`(`key`,`currency_key`) values ('sl','');
insert  into `k_countries`(`key`,`currency_key`) values ('sm','');
insert  into `k_countries`(`key`,`currency_key`) values ('sn','');
insert  into `k_countries`(`key`,`currency_key`) values ('so','');
insert  into `k_countries`(`key`,`currency_key`) values ('sr','');
insert  into `k_countries`(`key`,`currency_key`) values ('st','');
insert  into `k_countries`(`key`,`currency_key`) values ('sv','');
insert  into `k_countries`(`key`,`currency_key`) values ('sy','');
insert  into `k_countries`(`key`,`currency_key`) values ('sz','');
insert  into `k_countries`(`key`,`currency_key`) values ('tc','');
insert  into `k_countries`(`key`,`currency_key`) values ('td','');
insert  into `k_countries`(`key`,`currency_key`) values ('tg','');
insert  into `k_countries`(`key`,`currency_key`) values ('th','');
insert  into `k_countries`(`key`,`currency_key`) values ('tj','');
insert  into `k_countries`(`key`,`currency_key`) values ('tk','');
insert  into `k_countries`(`key`,`currency_key`) values ('tm','');
insert  into `k_countries`(`key`,`currency_key`) values ('tn','');
insert  into `k_countries`(`key`,`currency_key`) values ('to','');
insert  into `k_countries`(`key`,`currency_key`) values ('tp','');
insert  into `k_countries`(`key`,`currency_key`) values ('tr','');
insert  into `k_countries`(`key`,`currency_key`) values ('tt','');
insert  into `k_countries`(`key`,`currency_key`) values ('tv','');
insert  into `k_countries`(`key`,`currency_key`) values ('tw','');
insert  into `k_countries`(`key`,`currency_key`) values ('tz','');
insert  into `k_countries`(`key`,`currency_key`) values ('ua','');
insert  into `k_countries`(`key`,`currency_key`) values ('ug','');
insert  into `k_countries`(`key`,`currency_key`) values ('um','');
insert  into `k_countries`(`key`,`currency_key`) values ('us','');
insert  into `k_countries`(`key`,`currency_key`) values ('uy','');
insert  into `k_countries`(`key`,`currency_key`) values ('uz','');
insert  into `k_countries`(`key`,`currency_key`) values ('va','');
insert  into `k_countries`(`key`,`currency_key`) values ('vc','');
insert  into `k_countries`(`key`,`currency_key`) values ('ve','');
insert  into `k_countries`(`key`,`currency_key`) values ('vg','');
insert  into `k_countries`(`key`,`currency_key`) values ('vi','');
insert  into `k_countries`(`key`,`currency_key`) values ('vn','');
insert  into `k_countries`(`key`,`currency_key`) values ('vu','');
insert  into `k_countries`(`key`,`currency_key`) values ('wf','');
insert  into `k_countries`(`key`,`currency_key`) values ('ws','');
insert  into `k_countries`(`key`,`currency_key`) values ('xk','');
insert  into `k_countries`(`key`,`currency_key`) values ('xs','');
insert  into `k_countries`(`key`,`currency_key`) values ('ye','');
insert  into `k_countries`(`key`,`currency_key`) values ('yt','');
insert  into `k_countries`(`key`,`currency_key`) values ('za','');
insert  into `k_countries`(`key`,`currency_key`) values ('zm','');
insert  into `k_countries`(`key`,`currency_key`) values ('zr','');
insert  into `k_countries`(`key`,`currency_key`) values ('zw','');

/*Table structure for table `k_currencies` */

DROP TABLE IF EXISTS `k_currencies`;

CREATE TABLE `k_currencies` (
  `key` varchar(3) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_currencies` */

insert  into `k_currencies`(`key`) values ('eur');
insert  into `k_currencies`(`key`) values ('usd');

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

insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('masterdata',' ',' ',0,0,0);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('users_all','ENTITYLIST','USER',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('persons_all','ENTITYLIST','PERSON',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('person_own','ENTITYDETAIL','PERSON',1,0,0);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('coaches_own','ENTITYLIST','MYCOACHES',0,0,0);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('athletes_own','ENTITYLIST','MYATHLETES',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('relations','','',0,0,0);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('relation_coach','RELATIONLIST','COACH',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('relation_doctor','RELATIONLIST','DOCTOR',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('doctors_all','ENTITYLIST','DOCTOR',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('doctors_own','ENTITYLIST','MYDOCTORS',0,0,0);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('attachments_all','ENTITYLIST','ATTACHMENT',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('attachments_own','ENTITYLIST','MYATTACHMENTS',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('relation_attachment','RELATIONLIST','ATTACHMENT',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('tests_all','ENTITYLIST','TEST',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('tests_own','ENTITYLIST','MYTESTS',0,0,0);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('tests_coach','ENTITYLIST','COACHTESTS',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('zones_coach','ZONESDEFINITION',' ',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('zones_athlete','ZONESDETAIL',' ',0,0,0);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('competitions_all','ENTITYLIST','COMPETITION',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('competitions_own','ENTITYLIST','SCOUTCOMPETITIONS',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('scouted_own','ENTITYLIST','MYSCOUTEDATHLETES',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('results_scout','ENTITYLIST','SCOUTRESULTS',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('relation_scout','RELATIONLIST','SCOUT',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('results_all','ENTITYLIST','RESULT',1,1,1);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('imports','','',0,0,0);
insert  into `k_functionnodes`(`key`,`page`,`entity`,`edit`,`create`,`delete`) values ('results_import','RESULTSIMPORT','',0,0,0);

/*Table structure for table `k_languages` */

DROP TABLE IF EXISTS `k_languages`;

CREATE TABLE `k_languages` (
  `key` varchar(2) NOT NULL,
  `logon` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Language relevant for logon',
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='\n';

/*Data for the table `k_languages` */

insert  into `k_languages`(`key`,`logon`) values ('de',1);
insert  into `k_languages`(`key`,`logon`) values ('en',1);
insert  into `k_languages`(`key`,`logon`) values ('fr',0);

/*Table structure for table `k_reltyps` */

DROP TABLE IF EXISTS `k_reltyps`;

CREATE TABLE `k_reltyps` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_reltyps` */

insert  into `k_reltyps`(`key`) values ('attachment');
insert  into `k_reltyps`(`key`) values ('coach');
insert  into `k_reltyps`(`key`) values ('comp');
insert  into `k_reltyps`(`key`) values ('doctor');
insert  into `k_reltyps`(`key`) values ('scout');

/*Table structure for table `k_roles` */

DROP TABLE IF EXISTS `k_roles`;

CREATE TABLE `k_roles` (
  `key` varchar(36) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Roles for authorization';

/*Data for the table `k_roles` */

insert  into `k_roles`(`key`) values ('admin');
insert  into `k_roles`(`key`) values ('athlete');
insert  into `k_roles`(`key`) values ('coach');
insert  into `k_roles`(`key`) values ('scouter');

/*Table structure for table `k_salutation` */

DROP TABLE IF EXISTS `k_salutation`;

CREATE TABLE `k_salutation` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_salutation` */

insert  into `k_salutation`(`key`) values ('mr');
insert  into `k_salutation`(`key`) values ('mrs');

/*Table structure for table `k_scheduletypes` */

DROP TABLE IF EXISTS `k_scheduletypes`;

CREATE TABLE `k_scheduletypes` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `k_scheduletypes` */

insert  into `k_scheduletypes`(`key`) values ('bike');
insert  into `k_scheduletypes`(`key`) values ('private');
insert  into `k_scheduletypes`(`key`) values ('run');
insert  into `k_scheduletypes`(`key`) values ('swim');

/*Table structure for table `k_sex` */

DROP TABLE IF EXISTS `k_sex`;

CREATE TABLE `k_sex` (
  `key` varchar(1) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_sex` */

insert  into `k_sex`(`key`) values ('m');
insert  into `k_sex`(`key`) values ('w');

/*Table structure for table `k_testtypes` */

DROP TABLE IF EXISTS `k_testtypes`;

CREATE TABLE `k_testtypes` (
  `key` varchar(10) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `k_testtypes` */

insert  into `k_testtypes`(`key`) values ('ergo');
insert  into `k_testtypes`(`key`) values ('swim');
insert  into `k_testtypes`(`key`) values ('treadmill');

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

insert  into `labels`(`id`,`person_id`,`description`,`color`) values ('55620350-6d49-11de-a69b-604b59d93787','0b0b7658-2ddb-11de-86ae-00301bb60f17','Bestellung','#ff0000');
insert  into `labels`(`id`,`person_id`,`description`,`color`) values ('e22d73e4-83c3-4a21-8f5d-9d76cefb6761','0b0b7658-2ddb-11de-86ae-00301bb60f17','Super Test','#cc99ff');
insert  into `labels`(`id`,`person_id`,`description`,`color`) values ('3418c962-818c-43ee-ad7a-5964fdd2eb6c','10f52302-2ddb-11de-86ae-00301bb60f17','World','#FFFFFF');
insert  into `labels`(`id`,`person_id`,`description`,`color`) values ('d89401a3-d6cc-41dd-b7df-32823172d893','0b0b7658-2ddb-11de-86ae-00301bb60f17','Klasse A','#009933');

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
insert  into `list_variants`(`list`,`entity`,`user_id`,`columns_sequence`,`columns_width`) values ('entitylist','SCOUTCOMPETITIONS','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'100;100;100;242;100');
insert  into `list_variants`(`list`,`entity`,`user_id`,`columns_sequence`,`columns_width`) values ('entitylist','COMPETITION','e96bcbd2-676d-102c-ace2-9cc3fca64c87','0;1;2;4;3;5','100;100;100;291;100');
insert  into `list_variants`(`list`,`entity`,`user_id`,`columns_sequence`,`columns_width`) values ('entitylist','MYRESULTS','e96bcbd2-676d-102c-ace2-9cc3fca64c87','1;0;2;3;4;5;7;6;8;9;10;11;12;13;14;15;16;17;21;19;18;20;22','100;193;100;100;100;100;100;100;100;100;100;100;100;100;100;100;100;100');
insert  into `list_variants`(`list`,`entity`,`user_id`,`columns_sequence`,`columns_width`) values ('entitylist','MYRESULTS','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','0;1;2;3;5;6;9;7;8;10;11;12;14;15;16;17;18;19;21;22','76;100;100;129;100;33;58');
insert  into `list_variants`(`list`,`entity`,`user_id`,`columns_sequence`,`columns_width`) values ('list','COMPETITIONLIMITS','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',NULL,NULL);
insert  into `list_variants`(`list`,`entity`,`user_id`,`columns_sequence`,`columns_width`) values ('entitylist','SCOUTCOMPETITIONS','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',NULL,'100;169;100;100;100;100');
insert  into `list_variants`(`list`,`entity`,`user_id`,`columns_sequence`,`columns_width`) values ('list','COMPETITIONLIMITS','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,NULL);

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

insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('0b0b7658-2ddb-11de-86ae-00301bb60f17','mr','Markus','Reich','m','Moosbach','28/2','6393','St. Ulrich','Tirol','at','reich.markus@gmail.com','http://www.meex-rich.com','','0664/3453852',NULL,'1978-10-24 00:00:00',NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-02-08 22:40:19','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('10f52302-2ddb-11de-86ae-00301bb60f17','mrs','Daniela','Bucher','w','Moosbach','28/21','6392','St. Jakob','Tirol','at','dany.bucher@gmail.com','www.dany.at','05354/88462','0664/2844263','05354/88462-10','1983-05-16 00:00:00',NULL,'1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-27 23:48:50','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('7522bc7f-42cf-415c-a050-da12518a4cd3','mr','Thomas','Mach','m','Dorf','2','6392','St. Jakob','Tirol','de','thomas.mach@egger.com',NULL,NULL,NULL,NULL,'1969-09-15 00:00:00','ÿØÿà\0JFIF\0\0H\0H\0\0ÿÛ\0C\0ÿÀ\0\0`\0`\0ÿÄ\0\0\0\0\0\0\0\0\0\0\0\0	\n\0ÿÄ\0H\0	\0\0\0!1\0AQq	\"a\n$%24‘#35¡Áğ&6BCD±ÑERber‚’ÂáÿÚ\0\0\0?\0»yt³~É/!öç†lòc—–Hã´ÊPwÃ‡æ<±^cøÿ\0wQåş£ı8ãQ&’Ai\"H/ÕË‚ßñ><ó·«!¾¼ôçèíÑır¢ß©Iµ¾^¨›mÓø·V6ÊRGX3\0`1¶1}NùC¹–itÎÂF’™#(µ*TÊk• íâD3cså“€ÀÆê?”#Ò\Z‚éïJt¨şSTEJ}M§ôAñ!d.¹øq‡cÅàô6öÏô{é\'52Ö¿jhôºò¨î”ÕJB²ÉV¯.ÎªHùŸæÂ	<â|qrƒ²™+µŒù3şíQNIräqçëğà¶¢Eæá¿qvgõlİÎÜTÀ\0,@¾[×sùm·s)^d™QnáŸñb7vÏŸæ8yû¤ŞÓ²Ë>íÉÿ\0œyòlğ?»Ñùïğwû^¿>OÇheubn¶²q’ÿ\0É~3¯í•ö«¦ô_¡¬Ğ½ ­£¬Õ…\nqãU4Õ‹EËu\0å¹;îxÆÛ©Ú©¯WtkWjúíÎ¶º¢ó>­F¾²²º(_­Öò ‚b÷N	Ë„jˆ¬I«­J¨]è*\"§ˆOOz…Ç¿8\0Ä’0aÃÁMJLÙSªdö$H/Ûˆˆ\0à€Å„åú¤ƒèüLUTA®”¬—]P£N^\nŠ¶@pa‹ªı IÙ~5ÓìOö§)ß\niõÙT×UÎ=×Kîª©ñC{mYŠqˆ\"	Ky‚Û^­±1ˆ\"ŠY‚8µ-£1‰ƒrÛ$ç|~Ï—/³¢ y’O[˜;\0Ãà>%ù—÷gQ9!ÿ\0æ?ş\\<n}?ˆã¿e:NÒ§ğÉß;Gæ1Ëˆ·Òç\\ú5è6¥ë\"ÜÑ\"’Ä´ë×5«¯)‡ÂS\0Á#í·[fÇŸn–èF¢tâÔ»Ë\\uifºe-×q(ª)ÖÂL1Ö¨G_ÕY\"#Õ„Œ…ıã,D\"â4·¡¦Œiä4s’ìÔÙêBœıd¤{ípq¸-7#ÏZ‰Ğ—Goi¥)Ø©¸á¾YHŞj›ÈÁ|5tëw³Æ\rJ¯Nê”d-SÓŠ ‹RUĞ¶BJ«7À‚9cbx¥û†‚½R±%N–©9F‚|TÕT•\"€Âwˆc\'İ9$Awı:¼–¬+½ê@¯ŸF®¥H£M>EGTõ¡#¬e$×¼M(ÄíŒ0 é_Ğ¤½7KŠúi«°ÔÉ¿9;À/JwÇÎÔ¶IV-@œ$¥®¶?kŒc‰ƒ¡*0HırÃ?¦@×\r¹<~şŸùêpëÓÒ4Ççû¹åœùÿ\0øy\r[Qûğ_øÿ\0§ÑùGıR\'DË?JÓ\'N…g[õvÓµÏw?|¡JXBïÌ±\0±5­¡v’n¡Û)D„D— Ó.–ªdí±ò@sÄµ ^¦”ój©²ïMô`	…òqéêNy…ŠrüÈ`Ÿ8§¼È%³Œ\\r~©p<\0pÙ]µt*éRF\"ÈrŞ@yc°q€p^Ÿúett¶¯ËuríIL“Gx¢ÑM¯ ›NÆ¶Z|A\ZZ±Ø¬OAú±bfŠÁT`‡†9q?Æ¡?æãd¿&—X‚­³®ú!UTH ªCÔtZ|ò%%UÜ¾~©æìñªå4dm–oÛ¿?\rùğ[İ~‘ÖÃoÏ_>nûc«Ë‡‹²é&Íö|¼¼¹ùeˆÎ8ÍgÊµÅÁC¦D„g_T©©§SI@“m?&cõP.I0\"´‘uF–Ê—IT»`^2Qkˆ­øhä$à¶Ab	/’NĞÌû-v×½i$ª#[$\'š’K³\0ç,0Åğ6sîõº¼5÷ŸH¥;qwÀ-{MQ.D U,­-\Z,ŸúXø–1$ˆÉŒëÎìLùÏİmºº9Ô¿vE¸Ók{®NêÈìß Å³#*ÇŠwêÔºº	ÒdÎ$wzÀæ\'lÃÉ˜ä3 h\'¤ƒ/NµzéE¦ı[PË¸ªÅ2|°K\0H˜&;\0€Ãn.äçŞ%¦Íe¹S:e$µí+½çÊ\"(á•\n¥Ã„·mx 9‹¬1ãv“%ûû·wÜÜ6[Ï<:òÿ\0\\=aÿ\08x;‡õ_—ÿ\0<Q/¶’ÃT\\²´ìO¦Ÿ8ÚZğ…‰¦À¤AUM»RU\0û§	Mœ¶îq‹¯¢½£¨s$©jµùy×K§Y‰L›¢ZT‚8H‰1*\\Ø#–’&„8\"Ç	B~4²é©³d^_6+ÔêÑM=5EM7qU&;ÄuH|gsv•.‘N™\Z–rY£XIZñ9\nE92º…Y¶ñd•wn\\’ì²ÅöŸDı	´nz‹æÔN_A½ÔWÔ.5åš{å¡¡T«WW™5‚[\"‹ÚÌ/ æ&\0á[«µh´Ôr{¬²+‹\n†pK¿\'%À19Á\'â¼ú=ÚšÇ¬’•î$e:¹È)”\nTôÉõ\0ÑU×Hª€¬ğ»\"³‰½$Ù>Ñ]Ôû].Jlë³çm-ÇOMş/ú4®U<Y(l±o­$€ÌÂ€]€k\" \"X;ˆ@>¢\0îÿ\0áÃ¥IúÙ_ÏöO\nÏºOåËâ><B^ŸVi»ú.êí--)ŸZ“iÅtHÜÿ\0U‡‹aˆÈD…Tû¹ËxñšÛrÚHğ2©_M\"a>ÄçöŸÉ\'ç†\rjÈ–„²™6¡fuÍâCöuP4&¿Äœy¶CG»ÖòsÃ©£¾0”¼§*r”ôdÅ\n_£§-\\*ëÃìàù›˜‰bŸSl/Ûj2ª©|6àIú*Šu1úÀ¶	Î\'Ë3hw©ÔRÂug÷ÌAbí]}‹uK¶är&ú:¦V/Ï¿êi+d¦)HD¡¥§P¨¦ï¦3¹ÃJ9\"fØ·EìôÒ:…ÍF¹µÉ{¾V£[ezØ°””‡~ñZõB÷UÍ	g%ÉA-\0NÏ‹‡­ıPÿ\0¸p‡6WŞ¬üğwÚ~ŒùùrwçûÏ§çÂP-º[¶Ã¼­ËµùÉj/ œø¢j²IpNï—ˆà±ã%VêUÃaÜ6u]WrS‘L •QUş6½)ÜC#’waÄdÑ:{>®‘JUÑ¨V~œÜjWBŠ%ÇmÜµÕ¿UõUG#úÀÙ.³³0÷Ì»b\ZÂÒÔ„‹Æàï•99Á¹{_ìŸ÷¨\\`ä*çbåò8{lQSæ¢’õÇKÜÔ…Mu\'è*5iìQË‡0a!ƒâ=ë\r-2u&vÓ§§\nî…ÁıIß™áÕöté½«:Ãáw$š»}ºûZNb<T¥øBRJa,	eU‰Ã/cNèé6õ„´¤ú$ÚJ\nj\njtôÚaCCGA–ğ´±ŸC€aÎì82P›ÚÆ<Ï§2<¾#;pãµş?åÇh¦vdD66 Pä¢§ö\\yå›ÈoL3»g8]<ômO£Ö·)^è4½†k=Ez¢mM;\n4«¬¬«m\0sÕeë_fñŒósˆ•\n-MdÚS wêˆj¾ì+›İÀ%‰bùßlóü£\\·«’èh»¿e>G*tãC¸waóÈm\r™i:î¡”›áræÄoÕ9¥räü\0y9`IâêµİÛtÉ·-Ù]´éõ=ÔT¿Üö$ã ß?-Ù\"“IK©ºİQÙ†I³,$\n}ˆu[‘Y_æS<„AÜä‹×›TDD‘lóŸºó;ñÖtl®ÙË~òIÏ™|qÆ9ñÇ—ğşüùp¿“V\'Mˆ8“ˆyïøùä6ùåÀÉÓX‰Ï\"\'9ÛÅßÈñõ.ß¿çøg?~Ù.’2©n½è£KK&H»u‰mj¤uki>j)JÖLJÏÅ•¼‰H »7Åjjâœ¯:ØÔIS“”¤¥R)½ş…V€\rÎwÁÆC`m—aW^mšêx~g ,¬ÖŠo£\nZnãB<İöw99s³ğßÍQ¾—%ÕÍT¯ğÙ3€ïÉ£Ğr`H€Ë+Å…2µuRWÓ\'°§5%À Ë8pØÆÄ}Öİ:xX½:SI¶u&¥jM›¬ê\nb’Š-/~®·®Ô›™Y\"ÕRVJÀ(ãÅ•>t8aêí\ZÈ¤gKí²î]Ááƒìíû€ã´é½”1Ä\\Ã½ú·?/Ç€ü,iÄ3&“*9ÓÎî6#PÆàgXğƒ¾u¯HôÂG{ÔMPÓ»\nIûÍMéyÛHØo®WŠòçÈˆ3ªŞ×gÆ“¢Ö©¨t‹³oŠÉ©íİ1ñ;İnª¿\'ÃY øÈ¸n7;oŒhO…ş–ı&Ûº%]€š‚‹AgXhäÖÖ¤Úi*k\nä*ª·í‹ƒÅÕ\rÒy72ì£±:Vif¬¦Ûèúœ$Ù×ºuE-2P\rX8ÂR«:6\"ê¹ê¶ç¬	\"ßô·OlEäÚª*³¼S©M¨ïÔ5g /x˜ùétÙé\n]•*ŠM;ıæ	Ãœ˜¢Ø;8Á`	\'1£XúSè¥œ™>’¯Qm™ÓèZ˜§Ûª~7\\=ìE\nR>Í€ólqLš¥¬–î¡k\r¿¨6å\"ŸcmT øqZz÷áJ~.Z&İ€,Ü\0xôèŸÒƒL:UéJ6§é…ÑB±&}5-ÔŠ*™jÈ»<7ë[jêJİ!\\;s7ôHRf)¦k<=Sùw\'àÁ¶øñõÊˆ¾Ì$ü}À?2şñçO¬^Ó~›šİ2lÛÛ¤6¢š)À‰n\\Ÿ1ĞèÇÕLéV—ÍÒÌ“€Øqİ‹‹ÕjÊ¢šhv©Q¨ïd³õA\0ÀrfEu\\rev¸jV$ÄÀ4A€-³C“ÕÈİ°	}m|È‹	§ ¹g$³îsÁÃ‚9Í41b?öÿ\0LxUÛ·î¤Yó\'SY—åámJ´WZš\'öTÂJÌ Ä=÷\"\"bZ q…P»¯™Ÿ[İ×J”ãQô€¡pªW\Zé~ì&#8$¸ÂpAëSKk´$Eâ<ñ—?ƒ¾ß‰…%@·‡é ’¸\0€ì9óg Xñ(º>tµÖî‹·©Ô\rÔ%û\näş£==«h®\ZÖdË¥%`„5„WÏô>©#\rñh—Ê@Ôz¨¨5ÏClëúîÕ7(©ÙuÛ¥+‹‰›Ö¶üÆü]ƒû]z\rk¸‘KO«ÔzkpOÚİÖ\ncdWïâ¾õ°}n\\·«ÿÙ','1900-01-01 00:00:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c89',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4','mr','Wolfgang','Baumgartner',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-11-16 13:19:11','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('aa37127b-0622-483d-89c8-fe378fe63a0d','mr','Marcel','Diechtler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-11-19 22:50:37','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('5b7265da-114c-467a-86fd-a8f2e5618f53','mr','Mario','Seibl',NULL,NULL,NULL,'6392',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1978-11-12 00:00:00',NULL,'2009-11-27 23:49:49','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-27 23:58:45','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('9f17b17c-594c-414e-bc6b-bd59e50c3cc0','mrs','Sabrina','von der Weide',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-11-28 18:06:24','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-02-20 12:30:35','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('51d76ea4-e375-4d5d-85b7-bcab63c5b1df',NULL,NULL,'XYZ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2010-02-08 22:00:23',NULL,'2010-02-08 22:00:24',NULL,NULL,NULL);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('4fe98bcf-7316-4bfa-a62e-e4fae72f8093','mrs','Daniela','Ryf',NULL,'xxx','12','6380','Schiz','Tirol','at','test@test.at',NULL,NULL,NULL,NULL,'1987-01-21 00:00:00',NULL,'2010-02-17 22:18:19','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 12:26:57','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',1,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('4f155310-7113-452c-93fc-15e2aa3447ae','mrs','Uschi','Glas',NULL,NULL,NULL,NULL,NULL,NULL,'at',NULL,NULL,NULL,NULL,NULL,'1978-02-19 00:00:00',NULL,'2010-02-19 14:25:32','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-02-19 14:25:32','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('c3e08b7a-562e-4988-9e64-19e7e71b7831','mr','Hans','Wurst',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1964-02-17 00:00:00',NULL,'2010-02-19 14:28:53','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-02-19 14:28:53','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('4c82c4b2-14b2-4437-b2eb-5370280508ab','mrs','Lisa','Norden',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1978-10-10 00:00:00',NULL,'2010-02-19 20:29:00','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-20 12:30:48','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',1,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','mr','Courtney','Atkinson',NULL,NULL,NULL,NULL,'Mackay','Queensland','au',NULL,'http://courtneyatkinson.com.au',NULL,NULL,NULL,'1979-01-01 00:00:00',NULL,'2010-02-23 00:29:27','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-23 00:29:27','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('93ff8621-c5ee-4c6a-ba46-dd23094f2704','mrs','Daniela','Ryf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1987-01-01 00:00:00',NULL,'2010-03-01 18:25:58','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-01 18:25:58','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('0b343d32-0928-47ad-960c-9c191688acd4','mr','Daniel','Unger',NULL,NULL,NULL,NULL,'Bad Saulgau','Baden-WÃ¼rttemberg','de','info@daniel-unger.de','http://www.daniel-unger.de',NULL,'+4915150438249',NULL,'1978-03-22 00:00:00',NULL,'2010-03-09 10:36:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 10:36:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('a4fe80da-4f25-4573-ab9a-30de032bf079','mr','Sven','Riederer',NULL,NULL,NULL,NULL,'Wallisellen',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1981-03-27 00:00:00',NULL,'2010-03-09 11:58:51','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:58:51','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('295173e2-b79b-45a8-b0aa-0a2c6ad509df','mr','Alexander','Brukhankov',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1987-01-01 00:00:00',NULL,'2010-03-09 12:01:39','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 12:01:39','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `persons`(`id`,`salutation_key`,`name_first`,`name_last`,`sex_key`,`street`,`housenumber`,`postcode`,`city`,`state`,`country_key`,`email`,`homepage`,`telephone`,`mobile`,`fax`,`birthdate`,`picture`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('7c3c4644-3345-4d03-928f-0996b7f9fc7f','mr','Vincent','Luis',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1989-01-01 00:00:00',NULL,'2010-03-11 11:07:44','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 11:07:44','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);

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

insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('0b0b7658-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,NULL,NULL,42,NULL);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('10f52302-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,NULL,200,NULL,NULL);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('7522bc7f-42cf-415c-a050-da12518a4cd3',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('9f17b17c-594c-414e-bc6b-bd59e50c3cc0',NULL,NULL,NULL,NULL,220,NULL,NULL);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('c3e08b7a-562e-4988-9e64-19e7e71b7831',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('4c82c4b2-14b2-4437-b2eb-5370280508ab',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','176.00',NULL,'67.00',NULL,NULL,NULL,NULL);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('93ff8621-c5ee-4c6a-ba46-dd23094f2704',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('0b343d32-0928-47ad-960c-9c191688acd4','186.00',NULL,'74.50',NULL,200,40,82);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('a4fe80da-4f25-4573-ab9a-30de032bf079','183.00',NULL,'69.00',NULL,NULL,NULL,NULL);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('295173e2-b79b-45a8-b0aa-0a2c6ad509df',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `persons_athlete`(`id`,`height`,`height_unit`,`weight`,`weight_unit`,`max_hr`,`resting_hr`,`vo2_max`) values ('7c3c4644-3345-4d03-928f-0996b7f9fc7f',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

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

insert  into `persons_have_doctors`(`id`,`person`,`doctor`,`reltyp_key`,`standard`) values ('f12d0178-496f-11de-921e-1178275b5596','10f52302-2ddb-11de-86ae-00301bb60f17','c94e3cff-495d-11de-921e-1178275b5596','doctor',0);
insert  into `persons_have_doctors`(`id`,`person`,`doctor`,`reltyp_key`,`standard`) values ('f73f73b4-496f-11de-921e-1178275b5596','0b0b7658-2ddb-11de-86ae-00301bb60f17','c94e3cff-495d-11de-921e-1178275b5596','doctor',0);
insert  into `persons_have_doctors`(`id`,`person`,`doctor`,`reltyp_key`,`standard`) values ('8e505f79-8d0e-4617-bdd9-8cba09a2ab5f','10f52302-2ddb-11de-86ae-00301bb60f17','d0782a5c-495d-11de-921e-1178275b5596','doctor',1);

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

insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('e3572a08-8c2d-102c-a1cd-29e813a50118','10f52302-2ddb-11de-86ae-00301bb60f17','0b0b7658-2ddb-11de-86ae-00301bb60f17','coach',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('1b390fb4-f4ee-4840-94a6-37bc0195e852','0b0b7658-2ddb-11de-86ae-00301bb60f17','aa37127b-0622-483d-89c8-fe378fe63a0d','coach',1);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('541a619b-f363-45e7-9999-51cdfb3ee784','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','coach',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('287f5224-708b-4788-8b70-27c4b2268021','10f52302-2ddb-11de-86ae-00301bb60f17','0b0b7658-2ddb-11de-86ae-00301bb60f17','scout',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('c3ee4964-02f0-4860-9fe0-78462929f733','514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4','0b0b7658-2ddb-11de-86ae-00301bb60f17','coach',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('7bcecdfe-f6cb-45dc-be1c-90e347932ef7','0b0b7658-2ddb-11de-86ae-00301bb60f17','aa37127b-0622-483d-89c8-fe378fe63a0d','scout',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('c6531a89-e347-46d1-ac26-3edae63f5433','0b343d32-0928-47ad-960c-9c191688acd4','aa37127b-0622-483d-89c8-fe378fe63a0d','scout',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('59d8438b-7210-4e38-8dff-edb2aed74b89','4f155310-7113-452c-93fc-15e2aa3447ae','0b0b7658-2ddb-11de-86ae-00301bb60f17','scout',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('a7e370f0-9da7-4d9d-bca7-0ec0d18a7a47','c3e08b7a-562e-4988-9e64-19e7e71b7831','0b0b7658-2ddb-11de-86ae-00301bb60f17','scout',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('e74fbf94-57c4-48fd-83e3-6a1ec09b4774','93ff8621-c5ee-4c6a-ba46-dd23094f2704','aa37127b-0622-483d-89c8-fe378fe63a0d','scout',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('8e4a4f3a-fadf-447c-a088-8faf391f9b41','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','aa37127b-0622-483d-89c8-fe378fe63a0d','scout',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('da9ec950-83a4-4b4a-a779-31dec3815f0a','a4fe80da-4f25-4573-ab9a-30de032bf079','aa37127b-0622-483d-89c8-fe378fe63a0d','scout',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('0cc1a4bd-5518-4511-95a7-afba07949a66','295173e2-b79b-45a8-b0aa-0a2c6ad509df','aa37127b-0622-483d-89c8-fe378fe63a0d','scout',0);
insert  into `persons_have_relations`(`id`,`partner1`,`partner2`,`reltyp_key`,`standard`) values ('428888e4-8ab2-4a94-af8a-a684841056b3','7c3c4644-3345-4d03-928f-0996b7f9fc7f','aa37127b-0622-483d-89c8-fe378fe63a0d','scout',0);

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

/*Data for the table `results` */

insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('42473dc1-e4f2-4408-910f-10b4e64a04c1','x96bcbd2-676d-102c-ace2-9cc3fca64c87','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','11','02:06:33','TEst','2009-11-10 12:18:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-19 18:35:58','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('7fbe2262-8b66-465b-8070-8a59bb3c2d50','63df5d72-3d22-411f-affa-3c974318e790','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,'2009-11-11 22:33:23','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-10 12:18:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('89c8328f-7a18-4b35-9c5f-8275ccf4f5f4','0d1edae8-a48a-4aa2-a9db-fdebd1acc15e','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,'2009-11-11 23:00:54','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-10 12:18:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e0729fb3-ba68-46ca-badc-21a24187953c','ba76369e-5c2b-4814-a1ca-6229a2a2fa1a','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','5','01:01:30','FÃ¼nfter Platz','2009-11-15 22:45:25','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-10 12:18:48','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('14fcb1c9-b175-4026-a3ce-cbb84f431560','30c5931c-cabb-42cf-9e55-5bfe663cefa0','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','2','01:02:50',NULL,'2009-11-28 12:42:24','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 12:43:33','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('1d72ad99-b7cd-41fd-917c-99de4c408be8','bb6116f8-fd51-4d05-8bfd-e0d86e228278','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','3','01:00:00','Gutes Rennen','2009-11-28 17:19:20','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 17:21:19','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('43d43177-77a8-4a5a-ab27-8469b42be1a5','5337cdba-b2ac-42f6-bcd9-bd7825db4284','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,'2009-11-28 23:14:40','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-28 23:14:40','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('8d261ab5-113c-4050-bf0d-cc29a3e6dd8d','2e122f41-2121-4d16-94d6-f5dc328d84dc','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','11','02:06:33','Test','2009-11-28 23:26:50','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-20 10:58:41','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('f659f59a-2ba2-4fc0-ada2-d3d6ffced394','9956d670-cb98-4d78-930a-88722f68d6a2','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','1','03:25:20','wwassssssssdsssss','2010-01-31 15:55:57','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-01-31 16:59:32','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('8c95eae2-08e0-4616-9c1d-68976ee6f8d1','a510cfbd-0f4e-4033-95f2-32f669cc964a','aa37127b-0622-483d-89c8-fe378fe63a0d','4fe98bcf-7316-4bfa-a62e-e4fae72f8093','6','02:00:21',NULL,'2010-02-17 22:29:16','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-17 22:29:16','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e48df0e1-2cbe-42b6-aaa4-d3f170f2fcba','30c5931c-cabb-42cf-9e55-5bfe663cefa0','0b0b7658-2ddb-11de-86ae-00301bb60f17','4f155310-7113-452c-93fc-15e2aa3447ae','20','02:08:36',NULL,'2010-02-19 14:26:36','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-19 18:35:58','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('b0d6797a-f221-4da0-bbb9-a943a0041a57','30c5931c-cabb-42cf-9e55-5bfe663cefa0','0b0b7658-2ddb-11de-86ae-00301bb60f17','c3e08b7a-562e-4988-9e64-19e7e71b7831','18','02:07:27',NULL,'2010-02-19 14:30:16','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-19 18:35:58','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('0a2da374-945f-42b5-99fd-41b03f33ca8a','ffd573fd-355c-4307-b0b9-338901210fff','aa37127b-0622-483d-89c8-fe378fe63a0d','0b0b7658-2ddb-11de-86ae-00301bb60f17','6','02:00:21',NULL,'2010-02-19 20:01:04','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-19 20:01:04','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('c4c3a8cc-863f-4f29-b16c-9d5abd60d60d','ffd573fd-355c-4307-b0b9-338901210fff','aa37127b-0622-483d-89c8-fe378fe63a0d','4c82c4b2-14b2-4437-b2eb-5370280508ab','2','02:02:11',NULL,'2010-02-19 20:31:04','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-19 20:33:48','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('ab08a29c-c13e-4286-9988-273c714e1e1f','3724b912-dfff-481b-92fe-f4687aac0f1b','aa37127b-0622-483d-89c8-fe378fe63a0d','4c82c4b2-14b2-4437-b2eb-5370280508ab','6',NULL,'was injured before','2010-02-19 22:13:48','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-19 22:15:04','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('ea1b3f0f-f6a4-4027-875e-5fc11ed10a98','3724b912-dfff-481b-92fe-f4687aac0f1b','aa37127b-0622-483d-89c8-fe378fe63a0d','4fe98bcf-7316-4bfa-a62e-e4fae72f8093',NULL,NULL,NULL,'2010-02-19 22:17:31','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-02-19 22:17:31','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('caff2285-840b-4e89-b1d7-7ac9f134382e','d92b9db6-4e55-4f24-958d-679ef6663829','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','5','01:45:27',NULL,'2010-02-23 00:30:20','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:26:31','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',1,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('04d71be1-1348-4631-950f-7fa147bf2ee4','494c57f6-9809-4257-a453-d11af4fc16bb','aa37127b-0622-483d-89c8-fe378fe63a0d','93ff8621-c5ee-4c6a-ba46-dd23094f2704','6','02:00:21',NULL,'2010-03-01 18:26:48','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-01 18:28:58','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('c02d939b-df49-4137-86b6-091d4dc6542b','a2f4545a-8998-4c4e-8f09-706ca409d0aa','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef',NULL,NULL,NULL,'2010-03-08 13:29:15','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:26:28','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',1,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('6dae5557-f50d-472d-80c8-cda38c16688f','5c0a7ffd-085c-42fa-93fc-ad1c21f903b8','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef',NULL,NULL,NULL,'2010-03-08 13:30:10','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:26:24','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',1,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('1f901487-8c93-43dd-a76c-bba61d7d402d','395c99de-7126-4e42-a414-01cd1e7c8b84','aa37127b-0622-483d-89c8-fe378fe63a0d','0b343d32-0928-47ad-960c-9c191688acd4','DNF',NULL,'Aufgabe im Radrennen?','2010-03-09 10:37:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 10:46:25','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e33bbfab-7179-482b-a967-34a8d2f22ed8','59db8e01-ada7-4a17-ba6a-e9c0d41323cf','aa37127b-0622-483d-89c8-fe378fe63a0d','0b343d32-0928-47ad-960c-9c191688acd4','16','01:43:20',NULL,'2010-03-09 10:43:58','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 10:45:54','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('46e279f1-d3fe-4552-830a-098a4f4402a4','01ea6bc5-2263-4298-b38d-e403faa9c0e3','aa37127b-0622-483d-89c8-fe378fe63a0d','0b343d32-0928-47ad-960c-9c191688acd4','51','01:52:02','Reifendefekt im Radrennen!','2010-03-09 10:47:12','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 10:50:20','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e9748c6f-23b2-4db0-b73a-1bdee8a6f02d','7034b557-4719-42bd-96a9-aadb846b68af','aa37127b-0622-483d-89c8-fe378fe63a0d','0b343d32-0928-47ad-960c-9c191688acd4','5','01:43:51',NULL,'2010-03-09 10:50:53','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 10:55:07','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('c6faf85d-fa6a-4f8d-b901-86352f7ee9aa','83d7ea1c-4ed8-4967-8ff9-33d19aab76eb','aa37127b-0622-483d-89c8-fe378fe63a0d','0b343d32-0928-47ad-960c-9c191688acd4','15','01:51:21','Difficult swimming conditions!','2010-03-09 10:55:34','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 10:57:26','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('b215627d-50bc-4d5c-9374-47a4c85cbda0','75e602a8-5d08-44dc-83d6-25491dcd9e02','aa37127b-0622-483d-89c8-fe378fe63a0d','0b343d32-0928-47ad-960c-9c191688acd4','6','01:49:43',NULL,'2010-03-09 11:07:39','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:10:27','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('5753f836-2ac9-45dd-b4fc-87df5440b673','efe7065a-9bf1-40aa-a773-96b0016ca05d','aa37127b-0622-483d-89c8-fe378fe63a0d','0b343d32-0928-47ad-960c-9c191688acd4','1','01:46:51',NULL,'2010-03-09 11:11:21','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:12:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('8b070252-d842-42a7-be66-54029f497107','ca2954d1-c4e9-4f1c-b02a-fb818db8a1fe','aa37127b-0622-483d-89c8-fe378fe63a0d','0b343d32-0928-47ad-960c-9c191688acd4','1','01:52:49',NULL,'2010-03-09 11:13:01','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:14:00','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('32a7de9c-90c1-4398-a013-e352f00aaeec','d92b9db6-4e55-4f24-958d-679ef6663829','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','5','01:45:27',NULL,'2010-03-09 11:28:24','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:29:30','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e9f06411-2695-4eaf-b3a5-6e85d1590a5e','395c99de-7126-4e42-a414-01cd1e7c8b84','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','8','01:46:07',NULL,'2010-03-09 11:29:50','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-10 00:39:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('8af7b8e5-3bda-4e51-ad2d-893ad27f16eb','59db8e01-ada7-4a17-ba6a-e9c0d41323cf','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','7','01:42:24',NULL,'2010-03-09 11:31:42','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:33:26','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('f7c1d426-b2e5-4e42-a9e0-89ce57de9692','7034b557-4719-42bd-96a9-aadb846b68af','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','12','01:44:32',NULL,'2010-03-09 11:33:47','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:34:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('09aa3689-27b5-4cb1-8806-d75fca6821e0','7d85149d-1b4b-4098-bae6-46d8e146beb3','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','41','01:56:17',NULL,'2010-03-09 11:35:01','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:36:02','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('ec7897f3-46ae-44cc-af8d-60d5ead43eec','de8f6cfb-1625-4a80-ae53-5c3fd20bc75e','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','2','01:52:14',NULL,'2010-03-09 11:36:18','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:37:01','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('bb2c2660-ca67-4fb1-8151-e60124ee370b','94f019bf-d9e9-471a-9d88-b19f1cd4c7e6','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','10','01:51:06',NULL,'2010-03-09 11:37:12','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:38:05','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('d0e80a87-594b-428b-9c3d-ada4b8e7acb0','5c0a7ffd-085c-42fa-93fc-ad1c21f903b8','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','1','01:48:24',NULL,'2010-03-09 11:38:17','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:39:45','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('db423699-0b1f-4242-b993-0577a1cbeaf4','a2f4545a-8998-4c4e-8f09-706ca409d0aa','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','1','01:52:05',NULL,'2010-03-09 11:40:05','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:41:01','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('a8853530-2e39-42e7-bbb6-a8ab4f720041','75e602a8-5d08-44dc-83d6-25491dcd9e02','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','11','01:50:10',NULL,'2010-03-09 11:41:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:42:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('41370b83-045a-43c9-b6c3-a0dbc9470102','0f52179e-a794-444d-8052-b6a6a1f44acc','aa37127b-0622-483d-89c8-fe378fe63a0d','2e522940-b3d5-4fd6-b18c-0e6ba0ac3aef','24','01:51:55',NULL,'2010-03-09 11:51:25','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-09 11:52:39','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('94312c30-f331-4e83-b5e1-c29e1f3f49d7','9f27f03c-3ad0-41d4-9027-998ee4ef3042','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','17','01:48:46',NULL,'2010-03-11 13:07:05','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 13:10:40','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('8b7cc610-aae2-434c-8932-8246c16525f4','ff9d8567-2d53-42fd-a311-a4f20efffb1d','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','26','01:51:45',NULL,'2010-03-11 13:13:52','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 13:18:04','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('13995500-fb05-46d2-a2f2-a1f7d6ed6056','b99f514d-267f-4973-a120-96986e2c2c7a','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','7','01:47:47',NULL,'2010-03-11 13:19:51','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 13:22:10','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('138b88f4-797c-48f8-8b77-56423c3c1875','cb65cbfc-b068-4d9a-9b53-dad5f9d71c8d','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','7','01:52:30',NULL,'2010-03-11 13:27:41','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 13:31:29','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('8a482230-4ae7-418c-8a8e-1c65298de48f','ea7ad4ae-6512-4ab3-9300-a8b26059082b','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','6','01:46:52',NULL,'2010-03-11 13:32:59','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 13:36:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('963090e9-08ca-4c7f-b6fb-d714e71f1b29','ff038737-84a2-44c3-8b5e-614a29b2d81a','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','33','01:51:51',NULL,'2010-03-11 13:37:03','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 13:39:39','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('88789fb2-7743-446c-b39e-bbc07f65f874','b0ea9052-13b4-4c14-9be5-5d18fac05839','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','23','01:53:46',NULL,'2010-03-11 13:40:16','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 13:43:20','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('433b2242-46fe-4a85-80cb-dc572c763e8c','43418740-34e7-4dcb-bc55-14cd6de7c5a0','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','1','01:48:51',NULL,'2010-03-11 13:44:42','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 13:46:43','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('4574e8c0-71c5-401a-8327-aea7addc7d1f','9d899102-f963-46b6-928c-5c53dc2d89e2','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','1','00:01:00','Biked with 2 others ahead of the pack','2010-03-11 13:48:27','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 14:27:33','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('64faa2dd-ce4c-46ce-b498-6fa32bf2f64c','abfb08fa-0540-4e72-ab55-f095597ec481','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','1','00:59:04','Biked with one other ahead of the group','2010-03-11 13:52:43','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 13:56:47','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('bf57d5ba-69a4-4060-9dfd-765df8820f54','2a14545b-420d-400d-ab15-63d40bf6c227','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','27','01:50:47',NULL,'2010-03-11 13:58:25','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 14:01:32','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('80e319a5-e268-49e7-b9e4-0c4715ae6d0f','0b35ed8e-2cef-48bd-86ae-f62a0b235472','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','3','00:54:14',NULL,'2010-03-11 14:01:59','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 14:03:52','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('a2df395d-ac21-437b-96c3-4d36b7990130','18033f3f-5242-4e47-ab3a-448a2d30f1df','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','6','00:57:11',NULL,'2010-03-11 14:04:49','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 14:07:36','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('aa728072-f474-4c6e-8060-69ce36aa1d66','0e9a11ba-0a77-48c7-9abb-f3342ae88193','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','14','00:59:57',NULL,'2010-03-11 14:16:51','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 14:20:57','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('f9b7f890-eaf1-4c7b-80f7-d29eef8f3461','2f81e8e6-4b87-433e-800e-af16702827cb','aa37127b-0622-483d-89c8-fe378fe63a0d','7c3c4644-3345-4d03-928f-0996b7f9fc7f','5','01:00:42',NULL,'2010-03-11 14:21:24','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','2010-03-11 14:23:08','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('b93feba5-4d18-4c4f-ad10-7ff10fc4f207','2e122f41-2121-4d16-94d6-f5dc328d84dc','0b0b7658-2ddb-11de-86ae-00301bb60f17','c3e08b7a-562e-4988-9e64-19e7e71b7831','18','02:07:27',NULL,NULL,NULL,'2010-03-20 11:17:41',NULL,0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('d91627ce-d770-4c12-b41f-a007a1b34cbf','2e122f41-2121-4d16-94d6-f5dc328d84dc','0b0b7658-2ddb-11de-86ae-00301bb60f17','4f155310-7113-452c-93fc-15e2aa3447ae','20','02:08:36',NULL,NULL,NULL,'2010-03-20 11:17:41',NULL,0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('efacd666-d94d-4bf9-ac86-4776f0150671','e287b9cf-8b66-45e2-accc-2803d8b4d9ba','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17','1','01:23:00',NULL,'2010-03-27 18:34:45','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-27 18:35:09','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('45fd1c2d-1ff7-4a70-8b27-dfb672da3752','e287b9cf-8b66-45e2-accc-2803d8b4d9ba','0b0b7658-2ddb-11de-86ae-00301bb60f17','4f155310-7113-452c-93fc-15e2aa3447ae',NULL,NULL,NULL,'2010-03-27 18:59:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-27 18:59:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `results`(`id`,`competition_id`,`scout_id`,`athlete_id`,`final_position`,`time`,`comment`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('63cfa973-55b8-49b9-a02a-3e0c3a2f45e6','6e9c5042-405f-4d30-83d5-34f86c7ff66d','0b0b7658-2ddb-11de-86ae-00301bb60f17','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,NULL,NULL,'2010-03-27 19:06:44','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-27 19:06:44','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

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

/*Data for the table `results_tria` */

insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('42473dc1-e4f2-4408-910f-10b4e64a04c1','W20','00:35:00','00:35:00',NULL,'20','3',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('7fbe2262-8b66-465b-8070-8a59bb3c2d50','W20','00:30:00','00:30:00',NULL,NULL,NULL,NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('89c8328f-7a18-4b35-9c5f-8275ccf4f5f4','W40','01:00:00','00:45:00',NULL,NULL,NULL,NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('e0729fb3-ba68-46ca-badc-21a24187953c','W20','00:30:00','00:29:00',NULL,NULL,NULL,NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('14fcb1c9-b175-4026-a3ce-cbb84f431560','Elite A','00:35:15','00:32:50',NULL,'2','5',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('1d72ad99-b7cd-41fd-917c-99de4c408be8','Pro W','00:32:00','00:25:00',NULL,'5','2',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('43d43177-77a8-4a5a-ab27-8469b42be1a5','W20','01:00:00','01:00:00',NULL,NULL,NULL,NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('8d261ab5-113c-4050-bf0d-cc29a3e6dd8d','Elite A','00:22:50','00:37:31','01:26:20','15','12','9');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('f659f59a-2ba2-4fc0-ada2-d3d6ffced394','Pro W','00:30:00','00:29:30',NULL,NULL,NULL,NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('8c95eae2-08e0-4616-9c1d-68976ee6f8d1','Elite F','00:22:05','00:35:05',NULL,'14','2',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('e48df0e1-2cbe-42b6-aaa4-d3f170f2fcba','Pro W','01:00:00','00:45:00',NULL,'5','4',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('b0d6797a-f221-4da0-bbb9-a943a0041a57','Pro W','01:00:00','00:32:00',NULL,NULL,NULL,NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('0a2da374-945f-42b5-99fd-41b03f33ca8a','Elite F','00:22:05','00:35:05',NULL,'','',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('c4c3a8cc-863f-4f29-b16c-9d5abd60d60d','Elite F','00:20:11','00:35:23',NULL,'5','7',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('ab08a29c-c13e-4286-9988-273c714e1e1f','Elite F','00:18:35','00:35:16',NULL,'','',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('caff2285-840b-4e89-b1d7-7ac9f134382e','Elite M','00:17:11','00:29:47',NULL,'28','6',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('04d71be1-1348-4631-950f-7fa147bf2ee4','Elite F','00:22:05','00:35:05',NULL,'14','7',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('1f901487-8c93-43dd-a76c-bba61d7d402d','Elite M','00:17:15','00:59:59',NULL,NULL,NULL,NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('e33bbfab-7179-482b-a967-34a8d2f22ed8','Elite M','00:18:29','00:30:19',NULL,'14','18',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('46e279f1-d3fe-4552-830a-098a4f4402a4','Elite M','16:42','33:16',NULL,'20','38',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('e9748c6f-23b2-4db0-b73a-1bdee8a6f02d','Elite M','00:59:59','00:59:59',NULL,NULL,NULL,NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('c6faf85d-fa6a-4f8d-b901-86352f7ee9aa','Elite M','00:20:55','00:31:14',NULL,'34','11',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('b215627d-50bc-4d5c-9374-47a4c85cbda0','Elite M','00:18:25','00:31:35',NULL,'36','6',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('5753f836-2ac9-45dd-b4fc-87df5440b673','Elite M','00:17:59','00:30:34',NULL,'8','1',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('8b070252-d842-42a7-be66-54029f497107','Elite M','00:18:28','00:30:51',NULL,'28','1',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('32a7de9c-90c1-4398-a013-e352f00aaeec','Elite M','00:17:11','00:29:47',NULL,'28','6',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('e9f06411-2695-4eaf-b3a5-6e85d1590a5e','Elite M','00:11:00','00:32:37',NULL,'1638','10',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('8af7b8e5-3bda-4e51-ad2d-893ad27f16eb','Elite M','00:18:56','00:29:25',NULL,'31','7',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('f7c1d426-b2e5-4e42-a9e0-89ce57de9692','Elite M','00:16:14','00:31:51',NULL,'4','11',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('09aa3689-27b5-4cb1-8806-d75fca6821e0','Elite M','00:19:14','00:38:09',NULL,'1','46',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('ec7897f3-46ae-44cc-af8d-60d5ead43eec','Elite M','00:17:52','00:31:18',NULL,'5','5',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('bb2c2660-ca67-4fb1-8151-e60124ee370b','Elite M','00:18:09','00:31:04',NULL,'4','9',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('d0e80a87-594b-428b-9c3d-ada4b8e7acb0','Elite M','00:16:47','00:31:24',NULL,'6','5',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('db423699-0b1f-4242-b993-0577a1cbeaf4','Elite M','00:19:19','00:32:52',NULL,'2','4',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('a8853530-2e39-42e7-bbb6-a8ab4f720041','Elite M','00:18:05','00:31:59',NULL,'6','11',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('41370b83-045a-43c9-b6c3-a0dbc9470102','Elite M','00:19:15','00:32:31',NULL,'30','24',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('94312c30-f331-4e83-b5e1-c29e1f3f49d7','U23','00:17:18','00:33:40',NULL,'4','27',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('8b7cc610-aae2-434c-8932-8246c16525f4','Elite M','00:17:39','00:33:23',NULL,'6','41',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('13995500-fb05-46d2-a2f2-a1f7d6ed6056','Elite M','00:17:21','00:30:44',NULL,'2','7',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('138b88f4-797c-48f8-8b77-56423c3c1875','U23','00:17:41','00:34:56',NULL,'4','26',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('8a482230-4ae7-418c-8a8e-1c65298de48f','Elite M','00:17:54','00:31:04',NULL,'4','16',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('963090e9-08ca-4c7f-b6fb-d714e71f1b29','Elite M','00:17:46','00:34:09',NULL,'7','34',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('88789fb2-7743-446c-b39e-bbc07f65f874','Elite M','00:17:11','00:32:11',NULL,'7','23',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('433b2242-46fe-4a85-80cb-dc572c763e8c','Elite M','00:17:17','00:32:06',NULL,'3','1',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('4574e8c0-71c5-401a-8327-aea7addc7d1f','Junior','00:13:28','00:15:52',NULL,'4','10',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('64faa2dd-ce4c-46ce-b498-6fa32bf2f64c','Junior','00:08:45','00:16:36',NULL,'2','11',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('bf57d5ba-69a4-4060-9dfd-765df8820f54','Elite M','00:17:47','00:35:24',NULL,'1','30',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('80e319a5-e268-49e7-b9e4-0c4715ae6d0f','Junior','00:08:54','00:15:55',NULL,'3','11',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('a2df395d-ac21-437b-96c3-4d36b7990130','Junior','00:08:15','00:16:19',NULL,'2','16',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('aa728072-f474-4c6e-8060-69ce36aa1d66','Junior','00:08:46','00:16:45',NULL,'14','21',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('f9b7f890-eaf1-4c7b-80f7-d29eef8f3461','Junior','00:09:13','00:17:26',NULL,'5','12',NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('7ca1a3c7-bd3e-45b6-ad6e-f11c8d9019ca','Elite A','00:19:54','00:39:59','01:26:18','8','20','7');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('a5adbf72-3793-40f9-bfc8-42af2716dd66','Elite A','00:20:42','00:40:55','01:26:22','22','23','12');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('cceeabf8-dcb6-4fe8-8902-8805e8fecd6b','Elite A','00:19:54','00:39:59','01:26:18','8','20','7');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('376466e0-0628-4a2b-a942-8c600f2f5199','Elite A','00:20:42','00:40:55','01:26:22','22','23','12');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('65233d23-fed7-4ec0-be6c-bed64bef6326','Elite A','00:19:54','00:39:59','01:26:18','8','20','7');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('7d086b8b-743a-4891-9202-bf0a946cd5c8','Elite A','00:20:42','00:40:55','01:26:22','22','23','12');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('ca66c944-cc18-4256-aee8-3c2fd05c4fb2','Elite A','00:19:54','00:39:59','01:26:18','8','20','7');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('75a8ef24-e4e4-41fd-9446-b569e663a318','Elite A','00:20:42','00:40:55','01:26:22','22','23','12');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('41ab19a1-c8df-447e-a6f8-e95be856509d','Elite A','00:19:54','00:39:59','01:26:18','8','20','7');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('e8a74099-83be-47d6-8b4b-f4da6379a402','Elite A','00:20:42','00:40:55','01:26:22','22','23','12');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('b93feba5-4d18-4c4f-ad10-7ff10fc4f207','Elite B','00:19:54','00:39:59','01:26:18','8','20','7');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('d91627ce-d770-4c12-b41f-a007a1b34cbf','Elite B','00:20:42','00:40:55','01:26:22','22','23','12');
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('efacd666-d94d-4bf9-ac86-4776f0150671','Elite A',NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `results_tria`(`id`,`category`,`swim_split`,`run_split`,`bike_split`,`swim_position`,`run_position`,`bike_position`) values ('63cfa973-55b8-49b9-a02a-3e0c3a2f45e6','Elite A',NULL,NULL,NULL,NULL,NULL,NULL);

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

insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','masterdata',1,0,1);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','users_all',2,1,1);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','persons_all',3,1,2);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('athlete','person_own',1,0,1);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('athlete','coaches_own',2,0,2);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('coach','person_own',1,0,1);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('coach','athletes_own',2,0,2);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','relations',4,0,2);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','relation_coach',5,4,1);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','doctors_all',6,1,3);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','relation_doctor',7,4,2);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('athlete','doctors_own',3,0,3);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('coach','doctors_own',3,0,3);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','attachments_all',7,1,4);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('athlete','attachments_own',4,0,4);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('coach','attachments_own',4,0,4);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','relation_attachment',8,4,3);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','tests_all',9,1,5);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('athlete','tests_own',5,0,5);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('coach','tests_coach',5,0,5);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('coach','zones_coach',6,0,6);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('athlete','zones_athlete',6,0,6);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','competitions_all',10,1,6);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('scouter','competitions_all',1,0,1);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('scouter','competitions_own',2,0,2);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('scouter','scouted_own',3,0,3);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('scouter','results_scout',4,0,4);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','relation_scout',11,4,4);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('admin','results_all',12,1,7);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('scouter','imports',5,0,5);
insert  into `roles_have_functionnodes`(`role_key`,`functionnode_key`,`node`,`parent`,`order`) values ('scouter','results_import',6,5,1);

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

/*Data for the table `schedules` */

insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('32de7bc9-913e-41f0-82f9-c7a626f0e7b2','10f52302-2ddb-11de-86ae-00301bb60f17','run','Test','2010-03-16 06:45:00',60,'#ffff33',0,NULL,NULL,'[]','2010-03-21 21:21:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-03 19:53:27','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('92113b6c-ad0b-40a3-8fdf-6ff3cd797327','10f52302-2ddb-11de-86ae-00301bb60f17','run','Test','2010-03-22 09:00:00',165,'#99ff66',0,NULL,NULL,'[{\"duration\":\"00:35:00\",\"durationAthlete\":\"00:30:00\",\"hrHigh\":50,\"hrLow\":0,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"},{\"duration\":\"00:50:00\",\"durationAthlete\":\"\",\"hrHigh\":70,\"hrLow\":51,\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"}]','2010-03-22 23:40:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-24 22:13:49','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('1fbc5c76-4f81-4e12-9db6-0c47938652ff','10f52302-2ddb-11de-86ae-00301bb60f17','run','Test','2010-03-30 05:45:00',45,'#FFFFFF',0,NULL,1,'[{\"duration\":\"00:30:00\",\"durationAthlete\":\"00:45:00\",\"hrAvgAthlete\":105,\"hrHigh\":50,\"hrLow\":0,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"}]','2010-03-29 20:53:24','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-03 19:34:44','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('37279a7a-676a-4d95-a1e1-c5e63c106bd7','10f52302-2ddb-11de-86ae-00301bb60f17','run',NULL,'2010-03-23 09:00:00',30,'#6666ff',0,NULL,1,'[{\"duration\":\"00:30:00\",\"hrHigh\":50,\"hrLow\":0,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"}]','2010-03-22 23:44:10','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:22:41','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('a92331fd-5344-4f3e-9db9-72c5517ac004','10f52302-2ddb-11de-86ae-00301bb60f17','bike','Test','2010-03-30 07:15:00',130,'#FFFFFF',0,NULL,1,'[{\"duration\":\"01:00:00\",\"durationAthlete\":\"00:30:00\",\"hrHigh\":70,\"hrLow\":51,\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"},{\"duration\":\"00:30:00\",\"durationAthlete\":\"00:50:00\",\"hrAvgAthlete\":100,\"hrHigh\":90,\"hrLow\":71,\"lactateHigh\":4.0,\"lactateLow\":3.5,\"zone\":\"396456df-eaa5-4e6a-a83c-9b9ac7659eb9\"},{\"duration\":\"00:50:00\",\"hrHigh\":100,\"hrLow\":91,\"lactateHigh\":6.0,\"lactateLow\":4.0,\"zone\":\"1e678437-441a-470e-b058-cca95cfea404\"}]','2010-03-29 21:35:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-03 19:34:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('13de830c-7257-45f0-84fb-cad47e7a5e35','0b0b7658-2ddb-11de-86ae-00301bb60f17','run','','2010-03-29 10:30:00',30,'#ffff00',1,'Meine Laufeinheit',NULL,'[]','2010-04-03 21:24:37','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:48:42','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('965ffff2-6f65-4312-96be-97186ad0a48c','0b0b7658-2ddb-11de-86ae-00301bb60f17','run',NULL,'2010-03-29 10:15:00',60,'#FFFFFF',0,'Test Template',NULL,'[]','2010-04-03 21:24:37','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-03 23:07:45','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('d665d323-198f-41ca-8995-8f19ff4d4745','0b0b7658-2ddb-11de-86ae-00301bb60f17','run','Test','2010-03-30 11:00:00',60,'#ffffff',0,'Test Template',NULL,'[]','2010-04-03 21:24:37','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-03 23:28:08','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('966277f1-839c-434f-9dc0-ca7db4b65058','0b0b7658-2ddb-11de-86ae-00301bb60f17','swim',NULL,NULL,NULL,'#cc00cc',1,'Test 2',NULL,'[]','2010-04-03 23:27:55','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-06 20:28:21','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('af51299a-8905-49d6-a05f-40aa8ec5da87','0b0b7658-2ddb-11de-86ae-00301bb60f17','run','Test','2010-04-04 21:13:05',165,'#99ff66',0,NULL,NULL,'[{\"duration\":\"00:35:00\",\"durationAthlete\":\"00:30:00\",\"hrHigh\":50,\"hrLow\":0,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"},{\"duration\":\"00:50:00\",\"durationAthlete\":\"\",\"hrHigh\":70,\"hrLow\":51,\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"}]','2010-03-22 23:40:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-04 21:39:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('b094ea6f-d445-428e-be81-d6cae151429e','0b0b7658-2ddb-11de-86ae-00301bb60f17','run','Test','2010-04-03 16:30:00',165,'#99ff66',0,NULL,NULL,'[{\"duration\":\"00:35:00\",\"durationAthlete\":\"00:30:00\",\"hrHigh\":50,\"hrLow\":0,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"},{\"duration\":\"00:50:00\",\"durationAthlete\":\"\",\"hrHigh\":70,\"hrLow\":51,\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"}]','2010-03-22 23:40:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-04 21:18:44','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('6b189483-1ee9-4837-a2d9-2a01c0d1a914','514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4','run','Test Kopie','2010-04-04 21:29:16',165,'#99ff66',0,NULL,NULL,'[{\"duration\":\"00:35:00\",\"durationAthlete\":\"00:30:00\",\"hrHigh\":50,\"hrLow\":0,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"},{\"duration\":\"00:50:00\",\"durationAthlete\":\"\",\"hrHigh\":70,\"hrLow\":51,\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"}]','2010-03-22 23:40:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-04 21:29:38','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('4513fe8b-c30f-4eaa-a20d-6e9d03b4d52a','0b0b7658-2ddb-11de-86ae-00301bb60f17','run','Test 2','2010-04-04 21:00:00',60,'#99ff66',0,NULL,NULL,'[{\"duration\":\"00:35:00\",\"durationAthlete\":\"00:30:00\",\"hrHigh\":50,\"hrLow\":0,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"},{\"duration\":\"00:50:00\",\"durationAthlete\":\"\",\"hrHigh\":70,\"hrLow\":51,\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"}]','2010-03-22 23:40:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-04 21:39:05','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('2dbfb2c1-5c0f-4c25-95b2-63141589921b','0b0b7658-2ddb-11de-86ae-00301bb60f17','run','Test 3','2010-04-03 21:00:00',165,'#99ff66',0,NULL,NULL,'[{\"duration\":\"00:35:00\",\"durationAthlete\":\"00:30:00\",\"hrHigh\":50,\"hrLow\":0,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"},{\"duration\":\"00:50:00\",\"durationAthlete\":\"\",\"hrHigh\":70,\"hrLow\":51,\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"}]','2010-03-22 23:40:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-04 21:33:00','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('b60dcf8e-fde7-49ef-a0b1-4966d7d28148','0b0b7658-2ddb-11de-86ae-00301bb60f17','run','Test','2010-04-04 21:00:00',60,'#FFFFFF',0,NULL,NULL,'[]','2010-04-04 21:33:50','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-04 21:39:14','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('53bc4da0-73c5-4edb-b0aa-c6ac54ba2f82','10f52302-2ddb-11de-86ae-00301bb60f17','run','Test','2010-04-12 23:00:00',165,'#99ff66',0,NULL,NULL,'[{\"duration\":\"00:35:00\",\"durationAthlete\":\"00:30:00\",\"hrHigh\":50,\"hrLow\":0,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"},{\"duration\":\"00:50:00\",\"durationAthlete\":\"\",\"hrHigh\":70,\"hrLow\":51,\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"}]','2010-03-22 23:40:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-24 22:13:49','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('c784f5df-0ba6-437b-85f3-b144b1d15403','10f52302-2ddb-11de-86ae-00301bb60f17','run','Test','2010-04-12 20:00:00',165,'#99ff66',0,NULL,1,'[{\"duration\":\"00:35:00\",\"durationAthlete\":\"00:30:00\",\"hrHigh\":50,\"hrLow\":0,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"},{\"duration\":\"00:50:00\",\"durationAthlete\":\"\",\"hrHigh\":70,\"hrLow\":51,\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"}]','2010-03-22 23:40:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:37:19','e96bcbd2-676d-102c-ace2-9cc3fca64c88',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e407b03f-e9d3-4a4d-9986-974ed7e3de79','10f52302-2ddb-11de-86ae-00301bb60f17','run','','2010-04-07 09:15:00',60,'#ffff00',0,'Meine Laufeinheit',NULL,'[]','2010-04-03 21:24:37','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:48:42','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('2431eab3-df65-40f4-9a6d-7b13cd4db589','10f52302-2ddb-11de-86ae-00301bb60f17','run','Test','2010-04-05 09:15:00',285,'#9933ff',0,'Test Template',0,'[{\"duration\":\"00:30:00\",\"durationAthlete\":\"\",\"hrHigh\":128,\"hrLow\":123,\"lactateHigh\":4.0,\"lactateLow\":3.5,\"zone\":\"396456df-eaa5-4e6a-a83c-9b9ac7659eb9\"}]','2010-04-03 21:24:37','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:22:20','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('579b4bd8-a91d-46fa-b8db-b78a7cd1d9c8','10f52302-2ddb-11de-86ae-00301bb60f17','run','Test','2010-04-06 17:30:00',60,'#9933ff',0,'Test Template',NULL,'[{\"duration\":\"00:30:00\",\"hrHigh\":128,\"hrLow\":123,\"lactateHigh\":4.0,\"lactateLow\":3.5,\"zone\":\"396456df-eaa5-4e6a-a83c-9b9ac7659eb9\"}]','2010-04-03 21:24:37','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:22:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('c2450452-43ba-4e56-8332-4f37421f9f5c','10f52302-2ddb-11de-86ae-00301bb60f17','run','Test','2010-04-05 09:30:00',85,'#66ff33',0,NULL,1,'[{\"duration\":\"00:30:00\",\"durationAthlete\":\"00:25:00\",\"hrAvgAthlete\":115,\"hrHigh\":110,\"hrLow\":97,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"},{\"duration\":\"00:50:00\",\"durationAthlete\":\"01:00:00\",\"hrHigh\":123,\"hrLow\":110,\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"}]','2010-04-07 21:34:18','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:50:53','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('9c6e70e2-ab81-4146-a4a2-6d87e810e7f8','0b0b7658-2ddb-11de-86ae-00301bb60f17','run','Test fÃ¼r Reich','2010-04-07 21:34:47',80,'#66ff33',0,NULL,NULL,'[{\"duration\":\"00:30:00\",\"hrHigh\":110,\"hrLow\":97,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"},{\"duration\":\"00:50:00\",\"hrHigh\":123,\"hrLow\":110,\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"}]','2010-04-07 21:34:18','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:34:59','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('d364b8da-c0c4-480d-870c-469cd020453b','10f52302-2ddb-11de-86ae-00301bb60f17','run','Das ist eine leichte Einheit',NULL,50,'#99ccff',1,'Leichte Einheit',NULL,'[{\"duration\":\"00:15:00\",\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"},{\"duration\":\"00:35:00\",\"lactateHigh\":3.5,\"lactateLow\":2.0,\"zone\":\"41ac38c4-4609-4e18-98a0-ec3b20f001de\"}]','2010-04-07 21:39:18','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:39:18','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('a8bee096-dddb-435b-915c-dfc5417bfc83','0b0b7658-2ddb-11de-86ae-00301bb60f17','swim',NULL,'2010-04-08 09:15:00',60,'#cc00cc',0,'Test 2',NULL,'[]','2010-04-03 23:27:55','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-06 20:28:21','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('5fdbd495-869e-4c81-8d7e-45f5ce7f7bdd','0b0b7658-2ddb-11de-86ae-00301bb60f17','run','Test','2010-04-05 12:45:00',60,'#9933ff',0,'Test Template',NULL,'[{\"duration\":\"00:30:00\",\"lactateHigh\":4.0,\"lactateLow\":3.5,\"zone\":\"396456df-eaa5-4e6a-a83c-9b9ac7659eb9\"}]','2010-04-03 21:24:37','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-06 20:28:42','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('b37f9ebd-caaf-46e8-a9b7-3389ff740715','0b0b7658-2ddb-11de-86ae-00301bb60f17','run',NULL,NULL,NULL,'#ccccff',1,'TEST',NULL,'[]','2010-04-07 21:40:19','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:40:31','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('487c064e-a509-4e8a-8afd-acd9a170c8f9','0b0b7658-2ddb-11de-86ae-00301bb60f17','run',NULL,'2010-04-07 12:45:00',60,'#ccccff',0,'TEST',NULL,'[]','2010-04-07 21:40:19','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:40:31','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('4a2adaaa-33cf-4f6e-9fd3-135bb76e2c15','10f52302-2ddb-11de-86ae-00301bb60f17','run',NULL,'2010-04-06 09:15:00',45,'#ccccff',0,'TEST',0,'[{\"duration\":\"00:45:00\",\"durationAthlete\":\"\",\"hrHigh\":110,\"hrLow\":97,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"}]','2010-04-07 21:40:19','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:42:40','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('2413e712-183c-42ea-a404-d5113dbcfa88','10f52302-2ddb-11de-86ae-00301bb60f17','bike','tEST','2010-04-06 12:00:00',45,'#FFFFFF',0,NULL,1,'[{\"duration\":\"00:30:00\",\"durationAthlete\":\"00:45:00\",\"hrAvgAthlete\":135,\"hrHigh\":128,\"hrLow\":123,\"lactateHigh\":4.0,\"lactateLow\":3.5,\"zone\":\"396456df-eaa5-4e6a-a83c-9b9ac7659eb9\"}]','2010-04-07 21:42:54','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:43:20','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('977c1cd6-4d76-4cb2-837c-a215e5189f12','10f52302-2ddb-11de-86ae-00301bb60f17','swim',NULL,'2010-04-08 08:30:00',60,'#cc0000',0,NULL,NULL,'[]','2010-04-07 21:49:24','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:49:24','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `schedules`(`id`,`person_id`,`type`,`description`,`start`,`duration`,`color`,`template`,`template_name`,`done`,`details`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('a9ceb70b-dba0-40a4-99e1-5deb1cfe3105','514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4','run',NULL,'2010-04-07 21:52:42',45,'#ccccff',0,'TEST',0,'[{\"duration\":\"00:45:00\",\"durationAthlete\":\"\",\"hrHigh\":110,\"hrLow\":97,\"lactateHigh\":2.0,\"lactateLow\":1.0,\"zone\":\"e154a52c-1e5e-43b1-bc6c-5ec89d0ea915\"}]','2010-04-07 21:40:19','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-04-07 21:52:49','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

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

insert  into `t_categories`(`key`,`language_key`,`description`,`description_long`) values ('certificat','de','Attest','Attest');
insert  into `t_categories`(`key`,`language_key`,`description`,`description_long`) values ('certificat','en','Certificate','Certificate');
insert  into `t_categories`(`key`,`language_key`,`description`,`description_long`) values ('results','de','Ergebnisliste','Ergebnisliste');
insert  into `t_categories`(`key`,`language_key`,`description`,`description_long`) values ('results','en','Result list','Result list');

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

/*Data for the table `t_compsubtypes` */

insert  into `t_compsubtypes`(`key`,`key_parent`,`language_key`,`description`,`description_long`) values ('sprint','tria','de','Sprintdistanz','Sprintdistanz');
insert  into `t_compsubtypes`(`key`,`key_parent`,`language_key`,`description`,`description_long`) values ('long','tria','de','Langdistanz','Langdistanz');
insert  into `t_compsubtypes`(`key`,`key_parent`,`language_key`,`description`,`description_long`) values ('olympic','tria','de','Olymp. Distanz','Olyimpische Distanz');
insert  into `t_compsubtypes`(`key`,`key_parent`,`language_key`,`description`,`description_long`) values ('im','tria','de','Ironman','Ironman');
insert  into `t_compsubtypes`(`key`,`key_parent`,`language_key`,`description`,`description_long`) values ('him','tria','de','70.3','70.3');
insert  into `t_compsubtypes`(`key`,`key_parent`,`language_key`,`description`,`description_long`) values ('sprint','terra','de','Sprintdistanz','Sprintdistanz');
insert  into `t_compsubtypes`(`key`,`key_parent`,`language_key`,`description`,`description_long`) values ('short','terra','de','Kurzdistanz','Kurzdistanz');
insert  into `t_compsubtypes`(`key`,`key_parent`,`language_key`,`description`,`description_long`) values ('middle','terra','de','Mitteldistanz','Mitteldistanz');
insert  into `t_compsubtypes`(`key`,`key_parent`,`language_key`,`description`,`description_long`) values ('long','terra','de','Langdistanz','Langdistanz');

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

insert  into `t_comptypes`(`key`,`language_key`,`description`,`description_long`) values ('tria','de','Triathlon','Triathlon');
insert  into `t_comptypes`(`key`,`language_key`,`description`,`description_long`) values ('tria','en','Triatlon','Triathlon');
insert  into `t_comptypes`(`key`,`language_key`,`description`,`description_long`) values ('terra','de','X-Terra','X-Terra Triathlon');
insert  into `t_comptypes`(`key`,`language_key`,`description`,`description_long`) values ('terra','en','X-Terra','X-Terra Triathlon');

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

insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('at','de','Ã–sterreich','Ã–sterreich');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('de','de','Deutschland','Deutschland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('at','en','Austria','Austria');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('de','en','Germany','Germany');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ad','de','Andorra','Andorra');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ae','de','Ver.Arab.Emir.','Ver.Arab.Emir.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('af','de','Afghanistan','Afghanistan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ag','de','Antigua/Barbuda','Antigua/Barbuda');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ai','de','Anguilla','Anguilla');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('al','de','Albanien','Albanien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('am','de','Armenien','Armenien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('an','de','Niederl.Antill.','Niederl.Antill.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ao','de','Angola','Angola');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('aq','de','Antarctica','Antarctica');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ar','de','Argentinien','Argentinien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('as','de','Samoa,American','Samoa,American');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('au','de','Australien','Australien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('aw','de','Aruba','Aruba');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('az','de','Aserbaidschan','Aserbaidschan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ba','de','Bosnien-Herz.','Bosnien-Herz.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bb','de','Barbados','Barbados');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bd','de','Bangladesh','Bangladesh');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('be','de','Belgien','Belgien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bf','de','Burkina-Faso','Burkina-Faso');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bg','de','Bulgarien','Bulgarien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bh','de','Bahrain','Bahrain');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bi','de','Burundi','Burundi');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bj','de','Benin','Benin');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bm','de','Bermuda','Bermuda');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bn','de','Brunei Darussal','Brunei Darussal');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bo','de','Bolivien','Bolivien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('br','de','Brasilien','Brasilien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bs','de','Bahamas','Bahamas');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bt','de','Bhutan','Bhutan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bv','de','Bouvet Inseln','Bouvet Inseln');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bw','de','Botsuana','Botsuana');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('by','de','Weissrussland','Weissrussland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bz','de','Belize','Belize');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ca','de','Canada','Canada');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cc','de','Kokosinseln','Kokosinseln');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cf','de','Zentralaf. Rep.','Zentralaf. Rep.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cg','de','Kongo','Kongo');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ch','de','Schweiz','Schweiz');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ci','de','ElfenbeinkÃ¼ste','ElfenbeinkÃ¼ste');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ck','de','Cookinseln','Cookinseln');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cl','de','Chile','Chile');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cm','de','Kamerun','Kamerun');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cn','de','China','China');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('co','de','Kolumbien','Kolumbien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cr','de','Costa Rica','Costa Rica');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cs','de','Serbia/Monteneg','Serbia/Monteneg');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cu','de','Kuba','Kuba');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cv','de','Kap Verde','Kap Verde');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cx','de','Weihnachtsinsel','Weihnachtsinsel');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cy','de','Zypern','Zypern');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cz','de','Tschech. Rep.','Tschech. Rep.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('dj','de','Dschibuti','Dschibuti');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('dk','de','DÃ¤nemark','DÃ¤nemark');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('dm','de','Dominica','Dominica');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('do','de','Dominik. Rep.','Dominik. Rep.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('dz','de','Algerien','Algerien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ec','de','Ecuador','Ecuador');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ee','de','Estland','Estland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('eg','de','Ã„gypten','Ã„gypten');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('er','de','Eritrea','Eritrea');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('es','de','Spanien','Spanien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('et','de','Ã„thiopien','Ã„thiopien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fi','de','Finnland','Finnland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fj','de','Fidschi','Fidschi');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fk','de','Falklandinseln','Falklandinseln');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fm','de','Micronesien','Micronesien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fo','de','FÃ¤rÃ¶er','FÃ¤rÃ¶er');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fr','de','Frankreich','Frankreich');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ga','de','Gabun','Gabun');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gb','de','United Kingdom','United Kingdom');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gd','de','Grenada','Grenada');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ge','de','Georgien','Georgien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gf','de','FranzÃ¶s.Guayana','FranzÃ¶s.Guayana');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gh','de','Ghana','Ghana');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gi','de','Gibraltar','Gibraltar');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gl','de','GrÃ¶nland','GrÃ¶nland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gm','de','Gambia','Gambia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gn','de','Guinea','Guinea');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gp','de','Guadeloupe','Guadeloupe');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gq','de','Ã„quatorialguine','Ã„quatorialguine');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gr','de','Griechenland','Griechenland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gt','de','Guatemala','Guatemala');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gu','de','Guam','Guam');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gw','de','Guinea-Bissau','Guinea-Bissau');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gy','de','Guyana','Guyana');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('hk','de','Hongkong','Hongkong');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('hm','de','Heard/McDon.Ins','Heard/McDon.Ins');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('hn','de','Honduras','Honduras');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('hr','de','Kroatien','Kroatien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ht','de','Haiti','Haiti');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('hu','de','Ungarn','Ungarn');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('id','de','Indonesien','Indonesien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ie','de','Irland','Irland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('il','de','Israel','Israel');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('in','de','Indien','Indien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('io','de','Brit.Geb.Ind.Oz','Brit.Geb.Ind.Oz');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('iq','de','Irak','Irak');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ir','de','Iran','Iran');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('is','de','Island','Island');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('it','de','Italien','Italien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('jm','de','Jamaika','Jamaika');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('jo','de','Jordanien','Jordanien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('jp','de','Japan','Japan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ke','de','Kenia','Kenia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kg','de','Kirgistan','Kirgistan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kh','de','Kambodscha','Kambodscha');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ki','de','Kiribati','Kiribati');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('km','de','Komoren','Komoren');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kn','de','St.Chr.,Nevis','St.Chr.,Nevis');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kp','de','Nordkorea','Nordkorea');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kr','de','SÃ¼dkorea','SÃ¼dkorea');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kw','de','Kuwait','Kuwait');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ky','de','Kaimaninseln','Kaimaninseln');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kz','de','Kasachstan','Kasachstan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('la','de','Laos','Laos');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lb','de','Libanon','Libanon');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lc','de','St. Lucia','St. Lucia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('li','de','Liechtenstein','Liechtenstein');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lk','de','Sri Lanka','Sri Lanka');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lr','de','Liberia','Liberia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ls','de','Lesotho','Lesotho');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lt','de','Litauen','Litauen');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lu','de','Luxemburg','Luxemburg');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lv','de','Lettland','Lettland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ly','de','Libyen','Libyen');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ma','de','Marokko','Marokko');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mc','de','Monaco','Monaco');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('md','de','Moldau','Moldau');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('me','de','Montenegro','Montenegro');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mg','de','Madagaskar','Madagaskar');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mh','de','Marshall-Insel','Marshall-Insel');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mk','de','Mazedonien','Mazedonien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ml','de','Mali','Mali');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mm','de','Myanmar','Myanmar');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mn','de','Mongolei','Mongolei');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mo','de','Macau','Macau');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mp','de','N.Mariana Insel','N.Mariana Insel');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mq','de','Martinique','Martinique');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mr','de','Mauretanien','Mauretanien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ms','de','Montserrat','Montserrat');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mt','de','Malta','Malta');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mu','de','Mauritius','Mauritius');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mv','de','Malediven','Malediven');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mw','de','Malawi','Malawi');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mx','de','Mexiko','Mexiko');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('my','de','Malaysia','Malaysia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mz','de','Mosambik','Mosambik');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('na','de','Namibia','Namibia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nc','de','Neukaledonien','Neukaledonien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ne','de','Niger','Niger');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nf','de','Norfolkinseln','Norfolkinseln');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ng','de','Nigeria','Nigeria');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ni','de','Nicaragua','Nicaragua');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nl','de','Niederlande','Niederlande');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('no','de','Norwegen','Norwegen');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('np','de','Nepal','Nepal');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nr','de','Nauru','Nauru');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nu','de','Niue-Inseln','Niue-Inseln');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nz','de','Neuseeland','Neuseeland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('om','de','Oman','Oman');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pa','de','Panama','Panama');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pe','de','Peru','Peru');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pf','de','FranzPolynesien','FranzPolynesien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pg','de','Papua-Neuguinea','Papua-Neuguinea');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ph','de','Philippinen','Philippinen');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pk','de','Pakistan','Pakistan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pl','de','Polen','Polen');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pm','de','StPier.,Miquel.','StPier.,Miquel.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pn','de','Pitcairn Inseln','Pitcairn Inseln');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pr','de','Puerto Rico','Puerto Rico');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ps','de','PalÃ¤stina','PalÃ¤stina');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pt','de','Portugal','Portugal');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pw','de','Palau','Palau');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('py','de','Paraguay','Paraguay');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('qa','de','Qatar','Qatar');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('re','de','Reunion','Reunion');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ro','de','RumÃ¤nien','RumÃ¤nien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('rs','de','Serbien','Serbien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ru','de','Russische Foed.','Russische Foed.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('rw','de','Ruanda','Ruanda');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sa','de','Saudi-Arabien','Saudi-Arabien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sb','de','Salomonen','Salomonen');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sc','de','Seschellen','Seschellen');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sd','de','Sudan','Sudan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('se','de','Schweden','Schweden');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sg','de','Singapur','Singapur');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sh','de','St. Helena','St. Helena');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('si','de','Slowenien','Slowenien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sj','de','Svalbard','Svalbard');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sk','de','Slowakei','Slowakei');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sl','de','Sierra Leone','Sierra Leone');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sm','de','San Marino','San Marino');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sn','de','Senegal','Senegal');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('so','de','Somalia','Somalia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sr','de','Suriname','Suriname');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('st','de','Staatenlos','Staatenlos');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sv','de','El Salvador','El Salvador');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sy','de','Syrien','Syrien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sz','de','Swasiland','Swasiland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tc','de','Turks-,Caicosin','Turks-,Caicosin');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('td','de','Tschad','Tschad');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tg','de','Togo','Togo');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('th','de','Thailand','Thailand');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tj','de','Tadschikistan','Tadschikistan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tk','de','Tokelau-Inseln','Tokelau-Inseln');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tm','de','Turkmenistan','Turkmenistan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tn','de','Tunesien','Tunesien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('to','de','Tonga','Tonga');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tp','de','Ost Timor','Ost Timor');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tr','de','TÃ¼rkei','TÃ¼rkei');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tt','de','Trinidad,Tobago','Trinidad,Tobago');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tv','de','Tuvalu','Tuvalu');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tw','de','Taiwan','Taiwan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tz','de','Tansania','Tansania');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ua','de','Ukraine','Ukraine');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ug','de','Uganda','Uganda');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('um','de','Minor Outl.Ins.','Minor Outl.Ins.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('us','de','USA','USA');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('uy','de','Uruguay','Uruguay');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('uz','de','Usbekistan','Usbekistan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('va','de','Vatikanstadt','Vatikanstadt');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('vc','de','St. Vincent','St. Vincent');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ve','de','Venezuela','Venezuela');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('vg','de','Brit.Jungferni.','Brit.Jungferni.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('vi','de','Amer.Jungferni.','Amer.Jungferni.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('vn','de','Vietnam','Vietnam');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('vu','de','Vanuatu','Vanuatu');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('wf','de','Wallis,Futuna','Wallis,Futuna');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ws','de','Westsamoa','Westsamoa');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('xk','de','Kosovo','Kosovo');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('xs','de','Serbien','Serbien');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ye','de','Jemen','Jemen');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('yt','de','Mayotte','Mayotte');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('za','de','SÃ¼dafrika','SÃ¼dafrika');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('zm','de','Sambia','Sambia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('zr','de','Zaire','Zaire');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('zw','de','Simbabwe','Simbabwe');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ad','en','Andorra','Andorra');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ae','en','Utd.Arab.Emir.','Utd.Arab.Emir.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('af','en','Afghanistan','Afghanistan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ag','en','Antigua/Barbuda','Antigua/Barbuda');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ai','en','Anguilla','Anguilla');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('al','en','Albania','Albania');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('am','en','Armenia','Armenia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('an','en','Dutch Antilles','Dutch Antilles');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ao','en','Angola','Angola');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('aq','en','Antarctica','Antarctica');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ar','en','Argentina','Argentina');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('as','en','Samoa,American','Samoa,American');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('au','en','Australia','Australia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('aw','en','Aruba','Aruba');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('az','en','Azerbaijan','Azerbaijan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ba','en','Bosnia-Herz.','Bosnia-Herz.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bb','en','Barbados','Barbados');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bd','en','Bangladesh','Bangladesh');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('be','en','Belgium','Belgium');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bf','en','Burkina-Faso','Burkina-Faso');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bg','en','Bulgaria','Bulgaria');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bh','en','Bahrain','Bahrain');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bi','en','Burundi','Burundi');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bj','en','Benin','Benin');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bm','en','Bermuda','Bermuda');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bn','en','Brunei Dar-es-S','Brunei Dar-es-S');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bo','en','Bolivia','Bolivia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('br','en','Brazil','Brazil');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bs','en','Bahamas','Bahamas');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bt','en','Bhutan','Bhutan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bv','en','Bouvet Island','Bouvet Island');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bw','en','Botswana','Botswana');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('by','en','BELARUS','BELARUS');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('bz','en','Belize','Belize');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ca','en','Canada','Canada');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cc','en','Cocos Islands','Cocos Islands');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cf','en','Central Afr.Rep','Central Afr.Rep');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cg','en','Congo','Congo');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ch','en','Switzerland','Switzerland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ci','en','Ivory Coast','Ivory Coast');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ck','en','Cook Islands','Cook Islands');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cl','en','Chile','Chile');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cm','en','Cameroon','Cameroon');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cn','en','China','China');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('co','en','Columbia','Columbia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cr','en','Costa Rica','Costa Rica');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cs','en','Serbia/Monteneg','Serbia/Monteneg');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cu','en','Cuba','Cuba');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cv','en','Cape Verde','Cape Verde');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cx','en','Christmas Islnd','Christmas Islnd');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cy','en','Cyprus','Cyprus');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('cz','en','Czech Republic','Czech Republic');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('dj','en','Djibouti','Djibouti');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('dk','en','Denmark','Denmark');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('dm','en','Dominica','Dominica');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('do','en','Dominican Rep.','Dominican Rep.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('dz','en','Algeria','Algeria');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ec','en','Ecuador','Ecuador');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ee','en','Estonia','Estonia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('eg','en','Egypt','Egypt');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('er','en','Eritrea','Eritrea');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('es','en','Spain','Spain');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('et','en','Ethiopia','Ethiopia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fi','en','Finland','Finland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fj','en','Fiji','Fiji');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fk','en','Falkland Islnds','Falkland Islnds');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fm','en','Micronesia','Micronesia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fo','en','Faeroe','Faeroe');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('fr','en','France','France');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ga','en','Gabon','Gabon');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gb','en','Great Britain','Great Britain');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gd','en','Grenada','Grenada');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ge','en','Georgia','Georgia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gf','en','French Guinea','French Guinea');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gh','en','Ghana','Ghana');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gi','en','Gibraltar','Gibraltar');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gl','en','Greenland','Greenland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gm','en','Gambia','Gambia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gn','en','Guinea','Guinea');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gp','en','Guadeloupe','Guadeloupe');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gq','en','Equatorial Guin','Equatorial Guin');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gr','en','Greece','Greece');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gt','en','Guatemala','Guatemala');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gu','en','Guam','Guam');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gw','en','Guinea-Bissau','Guinea-Bissau');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('gy','en','Guyana','Guyana');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('hk','en','Hong Kong','Hong Kong');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('hm','en','Heard/McDon.Isl','Heard/McDon.Isl');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('hn','en','Honduras','Honduras');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('hr','en','Croatia','Croatia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ht','en','Haiti','Haiti');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('hu','en','Hungary','Hungary');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('id','en','Indonesia','Indonesia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ie','en','Ireland','Ireland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('il','en','Israel','Israel');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('in','en','India','India');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('io','en','Brit.Ind.Oc.Ter','Brit.Ind.Oc.Ter');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('iq','en','Iraq','Iraq');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ir','en','Iran','Iran');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('is','en','Iceland','Iceland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('it','en','Italy','Italy');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('jm','en','Jamaica','Jamaica');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('jo','en','Jordan','Jordan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('jp','en','Japan','Japan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ke','en','Kenya','Kenya');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kg','en','Kyrgyzstan','Kyrgyzstan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kh','en','Cambodia','Cambodia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ki','en','Kiribati','Kiribati');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('km','en','Comorin','Comorin');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kn','en','St.Chr.,Nevis','St.Chr.,Nevis');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kp','en','North Korea','North Korea');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kr','en','South Korea','South Korea');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kw','en','Kuwait','Kuwait');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ky','en','Cayman Islands','Cayman Islands');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('kz','en','Kazakhstan','Kazakhstan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('la','en','Laos','Laos');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lb','en','Lebanon','Lebanon');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lc','en','St. Lucia','St. Lucia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('li','en','Liechtenstein','Liechtenstein');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lk','en','Sri Lanka','Sri Lanka');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lr','en','Liberia','Liberia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ls','en','Lesotho','Lesotho');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lt','en','Lithuania','Lithuania');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lu','en','Luxembourg','Luxembourg');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('lv','en','Latvia','Latvia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ly','en','Libya','Libya');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ma','en','Morocco','Morocco');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mc','en','Monaco','Monaco');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('md','en','Moldavia','Moldavia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('me','en','Montenegro','Montenegro');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mg','en','Madagascar','Madagascar');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mh','en','Marshall Islnds','Marshall Islnds');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mk','en','Macedonia','Macedonia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ml','en','Mali','Mali');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mm','en','Myanmar','Myanmar');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mn','en','Mongolia','Mongolia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mo','en','Macau','Macau');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mp','en','N.Mariana Islnd','N.Mariana Islnd');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mq','en','Martinique','Martinique');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mr','en','Mauretania','Mauretania');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ms','en','Montserrat','Montserrat');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mt','en','Malta','Malta');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mu','en','Mauritius','Mauritius');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mv','en','Maldives','Maldives');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mw','en','Malawi','Malawi');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mx','en','Mexico','Mexico');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('my','en','Malaysia','Malaysia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('mz','en','Mozambique','Mozambique');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('na','en','Namibia','Namibia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nc','en','New Caledonia','New Caledonia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ne','en','Niger','Niger');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nf','en','Norfolk Island','Norfolk Island');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ng','en','Nigeria','Nigeria');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ni','en','Nicaragua','Nicaragua');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nl','en','Netherlands','Netherlands');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('no','en','Norway','Norway');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('np','en','Nepal','Nepal');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nr','en','Nauru','Nauru');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nu','en','Niue Islands','Niue Islands');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('nz','en','New Zealand','New Zealand');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('om','en','Oman','Oman');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pa','en','Panama','Panama');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pe','en','Peru','Peru');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pf','en','Frenc.Polynesia','Frenc.Polynesia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pg','en','Papua Nw Guinea','Papua Nw Guinea');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ph','en','Philippines','Philippines');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pk','en','Pakistan','Pakistan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pl','en','Poland','Poland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pm','en','St.Pier,Miquel.','St.Pier,Miquel.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pn','en','Pitcairn Islnds','Pitcairn Islnds');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pr','en','Puerto Rico','Puerto Rico');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ps','en','Palestine','Palestine');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pt','en','Portugal','Portugal');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('pw','en','Palau','Palau');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('py','en','Paraguay','Paraguay');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('qa','en','Qatar','Qatar');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('re','en','Reunion','Reunion');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ro','en','Rumania','Rumania');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('rs','en','Serbia','Serbia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ru','en','Russian Fed.','Russian Fed.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('rw','en','Rwanda','Rwanda');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sa','en','Saudi Arabia','Saudi Arabia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sb','en','Solomon Islands','Solomon Islands');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sc','en','Seychelles','Seychelles');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sd','en','Sudan','Sudan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('se','en','Sweden','Sweden');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sg','en','Singapore','Singapore');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sh','en','St. Helena','St. Helena');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('si','en','Slovenia','Slovenia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sj','en','Svalbard','Svalbard');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sk','en','Slovakia','Slovakia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sl','en','Sierra Leone','Sierra Leone');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sm','en','San Marino','San Marino');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sn','en','Senegal','Senegal');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('so','en','Somalia','Somalia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sr','en','Suriname','Suriname');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('st','en','stateless','stateless');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sv','en','El Salvador','El Salvador');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sy','en','Syria','Syria');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('sz','en','Swaziland','Swaziland');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tc','en','Turksh Caicosin','Turksh Caicosin');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('td','en','Chad','Chad');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tg','en','Togo','Togo');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('th','en','Thailand','Thailand');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tj','en','Tadzhikistan','Tadzhikistan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tk','en','Tokelau Islands','Tokelau Islands');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tm','en','Turkmenistan','Turkmenistan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tn','en','Tunisia','Tunisia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('to','en','Tonga','Tonga');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tp','en','East Timor','East Timor');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tr','en','Turkey','Turkey');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tt','en','Trinidad,Tobago','Trinidad,Tobago');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tv','en','Tuvalu','Tuvalu');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tw','en','Taiwan','Taiwan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('tz','en','Tanzania','Tanzania');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ua','en','Ukraine','Ukraine');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ug','en','Uganda','Uganda');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('um','en','Minor Outl.Isl.','Minor Outl.Isl.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('us','en','USA','USA');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('uy','en','Uruguay','Uruguay');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('uz','en','Uzbekistan','Uzbekistan');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('va','en','Vatican City','Vatican City');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('vc','en','St. Vincent','St. Vincent');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ve','en','Venezuela','Venezuela');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('vg','en','Brit.Virgin Is.','Brit.Virgin Is.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('vi','en','Amer.Virgin Is.','Amer.Virgin Is.');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('vn','en','Vietnam','Vietnam');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('vu','en','Vanuatu','Vanuatu');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('wf','en','Wallis,Futuna','Wallis,Futuna');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ws','en','Western Samoa','Western Samoa');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('xk','en','Kosovo','Kosovo');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('xs','en','Serbia','Serbia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('ye','en','Yemen','Yemen');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('yt','en','Mayotte','Mayotte');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('za','en','South Africa','South Africa');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('zm','en','Zambia','Zambia');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('zr','en','Zaire','Zaire');
insert  into `t_countries`(`key`,`language_key`,`description`,`description_long`) values ('zw','en','Zimbabwe','Zimbabwe');

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

insert  into `t_currencies`(`key`,`language_key`,`description`,`description_long`) values ('eur','de','Euro','Euro');
insert  into `t_currencies`(`key`,`language_key`,`description`,`description_long`) values ('usd','de','Dollar','Dollar');

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

insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('persons_all','de','Personen','Personen');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('persons_all','en','Persons','Persons');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('users_all','de','Benutzer','Benutzer');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('users_all','en','Users','Users');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('masterdata','de','Stammdaten','Stammdaten');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('masterdata','en','Masterdata','Masterdata');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('person_own','de','Eigene Person','Eigene Person');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('person_own','en','My person','My person');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('coaches_own','de','Meine Trainer','Meine Trainer');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('coaches_own','en','My Coaches','My Coaches');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('athletes_own','de','Meine Athleten','Meine Athleten');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('athletes_own','en','My Athletes','My Athletes');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('relations','de','Beziehungen','Beziehungen');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('relations','en','Relationships','Relationships');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('relation_coach','de','Trainer','Trainer');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('relation_coach','en','Coaches','Coaches');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('relation_doctor','de','Ã„rzte','Ã„rzte');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('relation_doctor','en','Doctors','Doctors');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('doctors_all','de','Ã„rzte','Ã„rzte');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('doctors_all','en','Doctors','Doctors');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('doctors_own','de','Meine Ã„rzte','Meine Ã„rzte');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('doctors_own','en','My Doctors','My Doctors');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('attachments_all','de','AnhÃ¤nge','AnhÃ¤nge');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('attachments_all','en','Attachments','Attachments');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('attachments_own','de','Meine AnhÃ¤nge','Meine AnhÃ¤nge');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('attachments_own','en','My Attachments','My Attachments');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('relation_attachment','de','AnhÃ¤nge','AnhÃ¤nge');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('relation_attachment','en','Attachments','Attachments');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('tests_all','de','Tests','Tests');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('tests_own','de','Meine Tests','Meine Tests');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('tests_coach','de','Meine Tests','Meine Tests');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('zones_coach','de','Trainingsbereiche','Trainingsbereiche');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('zones_coach','en','Excercise Zones','Exercise Zones');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('zones_athlete','de','Trainingsbereiche','Trainingsbereiche');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('zones_athlete','en','My Zones','My Zones');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('competitions_all','de','WettkÃ¤mpfe','WettkÃ¤mpfe');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('competitions_all','en','Competitions','Competitions');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('competitions_own','de','Meine WettkÃ¤mpfe','Meine WettkÃ¤mpfe');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('competitions_own','en','My Competitions','My Competitions');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('scouted_own','de','Meine Athleten','Meine Athleten');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('scouted_own','en','My Athletes','My Athletes');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('results_scout','de','Ergebnisse','Ergebnisse');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('results_scout','en','Results','Results');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('relation_scout','de','Scouter','Scouter');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('relation_scout','en','Scout','Scout');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('results_all','de','Ergebnisse','Ergebnisse');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('results_all','en','Results','Results');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('adminpanel','de','Admin Konsole','Admin Konsole');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('imports','de','Daten Import','Daten Import');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('imports','en','Data import','Data import');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('results_import','de','Ergebnisse','Ergebnisse');
insert  into `t_functionnodes`(`key`,`language_key`,`description`,`description_long`) values ('results_import','en','Results','Results');

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

insert  into `t_languages`(`key`,`language_key`,`description`,`description_long`) values ('de','de','Deutsch','Deutsch');
insert  into `t_languages`(`key`,`language_key`,`description`,`description_long`) values ('de','en','German','German');
insert  into `t_languages`(`key`,`language_key`,`description`,`description_long`) values ('en','de','Englisch','Englisch');
insert  into `t_languages`(`key`,`language_key`,`description`,`description_long`) values ('en','en','English','English');

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

insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('coach','de','Trainer','Trainer','hat den Trainer','ist Trainer von');
insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('coach','en','Coach','Coach','has the coach','is the coach of');
insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('doctor','de','Arzt','Arzt','hat den Arzt','ist Arzt von');
insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('doctor','en','Doctor','Doctor','has the doctor','is the doctor of');
insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('attachment','de','Anhang','Anhang','hat den Anhang','der Anhang gehÃ¶rt');
insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('attachment','en','Attachment','Attachment','has the attachment','the att. belongs to');
insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('scout','de','Scouter','Scouter','hat den Scouter','ist Scouter von');
insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('scout','en','Scouter','Scouter','has the scouter','is the scouter of');
insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('comp','de','Wettkampf','Wettkampf','hat den Wettkampf','definiert Wettkampf');
insert  into `t_reltyps`(`key`,`language_key`,`description`,`description_long`,`relation_description`,`relation_description_inverse`) values ('comp','en','Competition','Competition','has the competition','defines competition');

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

insert  into `t_roles`(`key`,`language_key`,`description`,`description_long`) values ('admin','de','Administrator','Administrator');
insert  into `t_roles`(`key`,`language_key`,`description`,`description_long`) values ('admin','en','Admin','Admin');
insert  into `t_roles`(`key`,`language_key`,`description`,`description_long`) values ('coach','de','Trainer','Trainer');
insert  into `t_roles`(`key`,`language_key`,`description`,`description_long`) values ('coach','en','Coach','Coach');
insert  into `t_roles`(`key`,`language_key`,`description`,`description_long`) values ('athlete','de','Athlet','Athlet');
insert  into `t_roles`(`key`,`language_key`,`description`,`description_long`) values ('athlete','en','Athlete','Athlete');
insert  into `t_roles`(`key`,`language_key`,`description`,`description_long`) values ('scouter','de','Scouter','Scouter');
insert  into `t_roles`(`key`,`language_key`,`description`,`description_long`) values ('scouter','en','Scoutmaster','Scoutmaster');

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

insert  into `t_salutation`(`key`,`language_key`,`description`,`description_long`) values ('mr','de','Herr','Herr');
insert  into `t_salutation`(`key`,`language_key`,`description`,`description_long`) values ('mr','en','Mr.','Mr.');
insert  into `t_salutation`(`key`,`language_key`,`description`,`description_long`) values ('mrs','de','Frau','Frau');
insert  into `t_salutation`(`key`,`language_key`,`description`,`description_long`) values ('mrs','en','Mrs.','Mrs.');

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

/*Data for the table `t_scheduletypes` */

insert  into `t_scheduletypes`(`key`,`language_key`,`description`,`description_long`) values ('run','de','Laufeinheit','Laufeinheit');
insert  into `t_scheduletypes`(`key`,`language_key`,`description`,`description_long`) values ('run','en','Run','Run');
insert  into `t_scheduletypes`(`key`,`language_key`,`description`,`description_long`) values ('swim','de','Schwimmeinheit','Schwimmeinheit');
insert  into `t_scheduletypes`(`key`,`language_key`,`description`,`description_long`) values ('swim','en','Swim','Swim');
insert  into `t_scheduletypes`(`key`,`language_key`,`description`,`description_long`) values ('bike','de','Radeinheit','Radeinheit');
insert  into `t_scheduletypes`(`key`,`language_key`,`description`,`description_long`) values ('bike','en','Bike','Bike');
insert  into `t_scheduletypes`(`key`,`language_key`,`description`,`description_long`) values ('private','de','Privat','Privat');
insert  into `t_scheduletypes`(`key`,`language_key`,`description`,`description_long`) values ('private','en','Private','Private');

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

insert  into `t_sex`(`key`,`language_key`,`description`,`description_long`) values ('m','de','mÃ¤nnlich','mÃ¤nnlich');
insert  into `t_sex`(`key`,`language_key`,`description`,`description_long`) values ('w','de','weiblich','weiblich');
insert  into `t_sex`(`key`,`language_key`,`description`,`description_long`) values ('m','en','male','male');
insert  into `t_sex`(`key`,`language_key`,`description`,`description_long`) values ('w','en','female','female');

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

insert  into `t_testtypes`(`key`,`language_key`,`description`,`description_long`) values ('treadmill','de','Laufband','Laufband');
insert  into `t_testtypes`(`key`,`language_key`,`description`,`description_long`) values ('ergo','de','Ergometer','Ergometer');
insert  into `t_testtypes`(`key`,`language_key`,`description`,`description_long`) values ('swim','de','Schwimmen','Schwimmen');

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

insert  into `tests`(`id`,`person_id`,`doctor_id`,`coach_id`,`type`,`date`,`description`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('a5119e53-025e-4648-892c-adf2612eea04','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-01 00:00:00','Das ist ein Test','2009-11-01 18:34:13','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-01 18:34:33','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);
insert  into `tests`(`id`,`person_id`,`doctor_id`,`coach_id`,`type`,`date`,`description`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('cfbc410c-cdd8-4f76-9d5a-65ccd301b738','7522bc7f-42cf-415c-a050-da12518a4cd3','d0782a5c-495d-11de-921e-1178275b5596','0b0b7658-2ddb-11de-86ae-00301bb60f17','ergo','2009-11-01 00:00:00','Test\nTest2','2009-11-01 18:44:24','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-01 18:46:05','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `tests`(`id`,`person_id`,`doctor_id`,`coach_id`,`type`,`date`,`description`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','swim','2009-11-01 00:00:00','Test Schwimmen','2009-11-01 19:06:15','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-01 19:07:46','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `tests`(`id`,`person_id`,`doctor_id`,`coach_id`,`type`,`date`,`description`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('492f1a63-b6d7-40a0-8948-4b1e9d2f879c','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-12 00:00:00','Test','2009-11-27 23:30:18','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `tests`(`id`,`person_id`,`doctor_id`,`coach_id`,`type`,`date`,`description`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('1cac8765-c4cc-495d-a1b9-bd90a359bea7','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-26 00:00:00',NULL,'2009-11-27 23:25:05','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `tests`(`id`,`person_id`,`doctor_id`,`coach_id`,`type`,`date`,`description`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('66d6e0ad-dd1a-4854-a4a7-f10a3fb5c3b8','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','ergo','2009-11-13 00:00:00','Test','2009-11-27 23:35:08','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `tests`(`id`,`person_id`,`doctor_id`,`coach_id`,`type`,`date`,`description`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('55dd2633-7422-4dd2-bc85-70227f931e0c','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-04 00:00:00',NULL,'2009-11-27 23:39:46','e96bcbd2-676d-102c-ace2-9cc3fca64c87',NULL,'e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `tests`(`id`,`person_id`,`doctor_id`,`coach_id`,`type`,`date`,`description`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('75f5b154-63ab-49b5-9a5d-1719a3fd3c86','10f52302-2ddb-11de-86ae-00301bb60f17','d0782a5c-495d-11de-921e-1178275b5596','0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-20 00:00:00','TEST','2009-11-27 23:50:33','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-30 19:31:20','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `tests`(`id`,`person_id`,`doctor_id`,`coach_id`,`type`,`date`,`description`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('71ecbf87-f779-40a3-9dc3-bb9383f14bfe','10f52302-2ddb-11de-86ae-00301bb60f17',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-20 00:00:00','Test','2009-11-27 23:50:33','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-29 00:40:39','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `tests`(`id`,`person_id`,`doctor_id`,`coach_id`,`type`,`date`,`description`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('4178104d-71ba-41a9-a653-bd89e797d1be','9f17b17c-594c-414e-bc6b-bd59e50c3cc0',NULL,'0b0b7658-2ddb-11de-86ae-00301bb60f17','treadmill','2009-11-29 00:00:00','Test','2009-11-28 18:09:04','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-11-29 00:37:19','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);

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

insert  into `tests_analysis`(`id`,`created_at`,`created_by`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa','2009-11-02 20:49:08','e96bcbd2-676d-102c-ace2-9cc3fca64c87');
insert  into `tests_analysis`(`id`,`created_at`,`created_by`) values ('cfbc410c-cdd8-4f76-9d5a-65ccd301b738','2009-11-02 21:51:17','e96bcbd2-676d-102c-ace2-9cc3fca64c87');
insert  into `tests_analysis`(`id`,`created_at`,`created_by`) values ('75f5b154-63ab-49b5-9a5d-1719a3fd3c86','2009-11-27 23:51:40','e96bcbd2-676d-102c-ace2-9cc3fca64c87');
insert  into `tests_analysis`(`id`,`created_at`,`created_by`) values ('4178104d-71ba-41a9-a653-bd89e797d1be','2009-11-29 00:36:36','e96bcbd2-676d-102c-ace2-9cc3fca64c87');

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

insert  into `tests_ergo`(`id`,`power_init`,`power_step`,`cadence_low`,`cadence_high`,`step_time`) values ('cfbc410c-cdd8-4f76-9d5a-65ccd301b738',80,20,80,90,'01:00');
insert  into `tests_ergo`(`id`,`power_init`,`power_step`,`cadence_low`,`cadence_high`,`step_time`) values ('66d6e0ad-dd1a-4854-a4a7-f10a3fb5c3b8',400,20,80,90,'01:30');

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

insert  into `tests_protocol`(`id`,`description`,`model`,`model_lactate`,`model_spiro`,`count_steps`,`time_last_step`,`performance_max`,`lactate`,`hr`,`o2_absorption`,`co2_emission`,`rq`,`created_at`,`created_by`) values ('cfbc410c-cdd8-4f76-9d5a-65ccd301b738','Alles Roger','Daum','A1','A2',5,'01:00',NULL,'{\"list\":[1.0,2.0,3.0,4.0,5.0]}','{\"list\":[100,120,145,175,200]}','{\"list\":[2,1,null,2,8]}','','','2009-11-01 18:44:43','e96bcbd2-676d-102c-ace2-9cc3fca64c87');
insert  into `tests_protocol`(`id`,`description`,`model`,`model_lactate`,`model_spiro`,`count_steps`,`time_last_step`,`performance_max`,`lactate`,`hr`,`o2_absorption`,`co2_emission`,`rq`,`created_at`,`created_by`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa','Test',NULL,'A1',NULL,4,'00:00','03:30','','','','','','2009-11-01 19:06:51','e96bcbd2-676d-102c-ace2-9cc3fca64c87');
insert  into `tests_protocol`(`id`,`description`,`model`,`model_lactate`,`model_spiro`,`count_steps`,`time_last_step`,`performance_max`,`lactate`,`hr`,`o2_absorption`,`co2_emission`,`rq`,`created_at`,`created_by`) values ('6630713b-3a20-4d20-b738-de5c456b98d7',NULL,'Daum','Lactate','keine Spiro',0,'00:00',NULL,'','','','','','2009-11-27 23:19:30','e96bcbd2-676d-102c-ace2-9cc3fca64c87');
insert  into `tests_protocol`(`id`,`description`,`model`,`model_lactate`,`model_spiro`,`count_steps`,`time_last_step`,`performance_max`,`lactate`,`hr`,`o2_absorption`,`co2_emission`,`rq`,`created_at`,`created_by`) values ('75f5b154-63ab-49b5-9a5d-1719a3fd3c86','Test','Daum',NULL,NULL,5,'00:00',NULL,'{\"list\":[1.0,2.0,4.5,5.0,8.0]}','{\"list\":[100,110,120,140,180]}','{\"list\":[null,2,null,null,null]}','{\"list\":[1,null,3,null,null]}','{\"list\":[null,1.1,null,7.7,10.5]}','2009-11-27 23:50:53','e96bcbd2-676d-102c-ace2-9cc3fca64c87');
insert  into `tests_protocol`(`id`,`description`,`model`,`model_lactate`,`model_spiro`,`count_steps`,`time_last_step`,`performance_max`,`lactate`,`hr`,`o2_absorption`,`co2_emission`,`rq`,`created_at`,`created_by`) values ('4178104d-71ba-41a9-a653-bd89e797d1be',NULL,NULL,NULL,NULL,6,'00:00',NULL,'','','','','','2009-11-28 18:09:07','e96bcbd2-676d-102c-ace2-9cc3fca64c87');
insert  into `tests_protocol`(`id`,`description`,`model`,`model_lactate`,`model_spiro`,`count_steps`,`time_last_step`,`performance_max`,`lactate`,`hr`,`o2_absorption`,`co2_emission`,`rq`,`created_at`,`created_by`) values ('71ecbf87-f779-40a3-9dc3-bb9383f14bfe','Test',NULL,NULL,NULL,0,'00:00',NULL,'','','','','','2009-11-29 00:40:44','e96bcbd2-676d-102c-ace2-9cc3fca64c87');

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

insert  into `tests_swim_protocol`(`id`,`step`,`attempt`,`intensity`,`time`,`valid`,`lactate`,`hr`,`splits`,`comment`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa',1,3,75,'06:00',0,'4','90','[{00:00;0},{00:00;0},{00:00;0},{00:00;0}]',NULL);
insert  into `tests_swim_protocol`(`id`,`step`,`attempt`,`intensity`,`time`,`valid`,`lactate`,`hr`,`splits`,`comment`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa',1,1,75,'05:20',1,'1@2 1,1@5',NULL,'[{01:30;20},{00:45;20},{00:00;0},{00:00;0}]','Super Intervall');
insert  into `tests_swim_protocol`(`id`,`step`,`attempt`,`intensity`,`time`,`valid`,`lactate`,`hr`,`splits`,`comment`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa',1,2,75,'05:00',0,'2','100','[{00:00;0},{00:00;0},{00:00;0},{00:00;0}]',NULL);
insert  into `tests_swim_protocol`(`id`,`step`,`attempt`,`intensity`,`time`,`valid`,`lactate`,`hr`,`splits`,`comment`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa',2,1,70,'04:35',1,'2@5','100','[{00:00;0},{00:00;0},{00:00;0},{00:00;0}]',NULL);
insert  into `tests_swim_protocol`(`id`,`step`,`attempt`,`intensity`,`time`,`valid`,`lactate`,`hr`,`splits`,`comment`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa',3,1,80,'04:10',1,'2,8',NULL,'[{00:00;0},{00:00;0},{00:00;0},{00:00;0}]',NULL);
insert  into `tests_swim_protocol`(`id`,`step`,`attempt`,`intensity`,`time`,`valid`,`lactate`,`hr`,`splits`,`comment`) values ('52ec1e3f-ec3e-4676-960e-f8a547b734aa',4,1,90,'03:50',1,'4,5',NULL,'[{00:00;0},{00:00;0},{00:00;0},{00:00;0}]',NULL);

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

insert  into `tests_treadmill`(`id`,`speed_variable`,`incline_variable`,`speed_init`,`speed_step`,`incline_init`,`incline_step`,`step_time`) values ('a5119e53-025e-4648-892c-adf2612eea04',0,0,NULL,NULL,NULL,NULL,NULL);
insert  into `tests_treadmill`(`id`,`speed_variable`,`incline_variable`,`speed_init`,`speed_step`,`incline_init`,`incline_step`,`step_time`) values ('1cac8765-c4cc-495d-a1b9-bd90a359bea7',1,0,'10.0','2.0',0,NULL,'01:30');
insert  into `tests_treadmill`(`id`,`speed_variable`,`incline_variable`,`speed_init`,`speed_step`,`incline_init`,`incline_step`,`step_time`) values ('492f1a63-b6d7-40a0-8948-4b1e9d2f879c',1,0,'10.0','2.0',0,NULL,'01:30');
insert  into `tests_treadmill`(`id`,`speed_variable`,`incline_variable`,`speed_init`,`speed_step`,`incline_init`,`incline_step`,`step_time`) values ('55dd2633-7422-4dd2-bc85-70227f931e0c',1,0,'10.0','2.0',0,NULL,'01:30');
insert  into `tests_treadmill`(`id`,`speed_variable`,`incline_variable`,`speed_init`,`speed_step`,`incline_init`,`incline_step`,`step_time`) values ('75f5b154-63ab-49b5-9a5d-1719a3fd3c86',1,0,'10.0','2.0',0,NULL,'01:30');
insert  into `tests_treadmill`(`id`,`speed_variable`,`incline_variable`,`speed_init`,`speed_step`,`incline_init`,`incline_step`,`step_time`) values ('71ecbf87-f779-40a3-9dc3-bb9383f14bfe',1,0,'10.0','2.0',0,NULL,'01:30');
insert  into `tests_treadmill`(`id`,`speed_variable`,`incline_variable`,`speed_init`,`speed_step`,`incline_init`,`incline_step`,`step_time`) values ('4178104d-71ba-41a9-a653-bd89e797d1be',1,0,'10.0','2.0',0,NULL,'01:00');

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

/*Data for the table `user_defaults` */

insert  into `user_defaults`(`id`,`user_id`,`field`,`dependency`,`value`) values ('1791fcec-9b1c-4e9c-855c-a62a33f200f8','e96bcbd2-676d-102c-ace2-9cc3fca64c87','comptype',NULL,'tria');
insert  into `user_defaults`(`id`,`user_id`,`field`,`dependency`,`value`) values ('85d74d08-eb84-407c-8666-4729c6ecb7d9','e96bcbd2-676d-102c-ace2-9cc3fca64c87','compsubtype',NULL,'im');
insert  into `user_defaults`(`id`,`user_id`,`field`,`dependency`,`value`) values ('ba5f132b-3c78-40bb-a5bc-9cf8858596d5','e96bcbd2-676d-102c-ace2-9cc3fca64c87','tolerance',NULL,'50');

/*Table structure for table `user_preferences` */

DROP TABLE IF EXISTS `user_preferences`;

CREATE TABLE `user_preferences` (
  `id` varchar(36) NOT NULL,
  `sbvisibleamount` int(11) DEFAULT NULL,
  `competition_categories` text,
  PRIMARY KEY (`id`),
  KEY `fk_user_preferences_users` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `user_preferences` */

insert  into `user_preferences`(`id`,`sbvisibleamount`,`competition_categories`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c87',30,'Elite A;Elite B');
insert  into `user_preferences`(`id`,`sbvisibleamount`,`competition_categories`) values ('f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',35,'Elite F;Elite M;U23;Junior');

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

insert  into `users`(`id`,`user_name`,`user_hash`,`language_key`,`currency_key`,`locked`,`initial`,`active`,`person_id`,`email`,`last_login`,`last_login_ip`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c87','reich','mINWXHB/fsKFpyWud2lhmFQSIMY=','de','eur',0,0,1,'0b0b7658-2ddb-11de-86ae-00301bb60f17','reich.markus@gmail.com','2010-04-07 21:31:57','169.254.1.10','1900-01-01 00:00:00','','2010-04-07 21:46:49','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `users`(`id`,`user_name`,`user_hash`,`language_key`,`currency_key`,`locked`,`initial`,`active`,`person_id`,`email`,`last_login`,`last_login_ip`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c88','bucher','qUqP5cyxm6YcTAhz05Hph5gvu9M=','en','usd',0,0,1,'10f52302-2ddb-11de-86ae-00301bb60f17','dany.bucher@gmail.com','2010-04-07 21:35:55','169.254.1.10','1900-01-01 00:00:00','','2010-04-07 21:35:55','e96bcbd2-676d-102c-ace2-9cc3fca64c88',0,0);
insert  into `users`(`id`,`user_name`,`user_hash`,`language_key`,`currency_key`,`locked`,`initial`,`active`,`person_id`,`email`,`last_login`,`last_login_ip`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c89','mach','test','de','eur',0,0,1,'7522bc7f-42cf-415c-a050-da12518a4cd3','mach.thomas@gmail.com','2009-11-28 17:36:47','169.254.1.10','1900-01-01 00:00:00','','2009-11-28 17:36:47','e96bcbd2-676d-102c-ace2-9cc3fca64c87',0,0);
insert  into `users`(`id`,`user_name`,`user_hash`,`language_key`,`currency_key`,`locked`,`initial`,`active`,`person_id`,`email`,`last_login`,`last_login_ip`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','diechtler','RFkrTuo2ZjyGuZS7DqmdFTCcHH0=','de',NULL,0,0,1,'aa37127b-0622-483d-89c8-fe378fe63a0d','marcel.diechtler@gmail.com','2010-03-16 21:23:09','169.254.1.10','2009-11-19 23:00:43','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2010-03-16 21:23:10','f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a',0,0);
insert  into `users`(`id`,`user_name`,`user_hash`,`language_key`,`currency_key`,`locked`,`initial`,`active`,`person_id`,`email`,`last_login`,`last_login_ip`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`,`test`) values ('c841dff0-94ea-4415-b145-9fe4f6fec5fa','baumgartner',NULL,'de',NULL,0,0,0,'514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4','wolfgang@gmx.de',NULL,NULL,'2009-11-16 13:19:46','e96bcbd2-676d-102c-ace2-9cc3fca64c87','2009-06-12 23:25:01','e96bcbd2-676d-102c-ace2-9cc3fca64c87',1,0);

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

insert  into `users_have_roles`(`user_id`,`role_key`) values ('1c203729-ac8a-424a-960a-85ed6dae03d7','admin');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('1c203729-ac8a-424a-960a-85ed6dae03d7','athlete');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('1c203729-ac8a-424a-960a-85ed6dae03d7','coach');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('c841dff0-94ea-4415-b145-9fe4f6fec5fa','athlete');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c87','admin');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c87','athlete');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c87','coach');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c87','scouter');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c88','coach');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('e96bcbd2-676d-102c-ace2-9cc3fca64c89','athlete');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','admin');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','coach');
insert  into `users_have_roles`(`user_id`,`role_key`) values ('f6352d84-ae4b-4a5e-ab7b-e9a0df84e86a','scouter');

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

insert  into `zones`(`id`,`athlete_id`,`zones_definition_id`,`hr_low`,`hr_high`,`speed_low`,`speed_high`,`auto_hr`,`auto_speed`,`test_id_hr`,`test_id_speed`) values ('85c32e06-0361-4a57-97ad-1a81e03a2752','10f52302-2ddb-11de-86ae-00301bb60f17','e154a52c-1e5e-43b1-bc6c-5ec89d0ea915',97,110,'1.39','1.39',1,1,NULL,'52ec1e3f-ec3e-4676-960e-f8a547b734aa');
insert  into `zones`(`id`,`athlete_id`,`zones_definition_id`,`hr_low`,`hr_high`,`speed_low`,`speed_high`,`auto_hr`,`auto_speed`,`test_id_hr`,`test_id_speed`) values ('85c32e06-0361-4a57-97ad-1a81e03a2753','10f52302-2ddb-11de-86ae-00301bb60f17','41ac38c4-4609-4e18-98a0-ec3b20f001de',110,123,'1.20','1.20',1,1,NULL,'52ec1e3f-ec3e-4676-960e-f8a547b734aa');
insert  into `zones`(`id`,`athlete_id`,`zones_definition_id`,`hr_low`,`hr_high`,`speed_low`,`speed_high`,`auto_hr`,`auto_speed`,`test_id_hr`,`test_id_speed`) values ('85c32e06-0361-4a57-97ad-1a81e03a2754','10f52302-2ddb-11de-86ae-00301bb60f17','396456df-eaa5-4e6a-a83c-9b9ac7659eb9',123,128,'1.16','1.16',1,1,NULL,'52ec1e3f-ec3e-4676-960e-f8a547b734aa');
insert  into `zones`(`id`,`athlete_id`,`zones_definition_id`,`hr_low`,`hr_high`,`speed_low`,`speed_high`,`auto_hr`,`auto_speed`,`test_id_hr`,`test_id_speed`) values ('a3a2e797-c542-49c5-9d6d-8ffb7ff2abc6','514f02d2-2a7d-4bec-a6c4-9e9e97ffefe4','e154a52c-1e5e-43b1-bc6c-5ec89d0ea915',80,100,NULL,NULL,0,NULL,NULL,NULL);
insert  into `zones`(`id`,`athlete_id`,`zones_definition_id`,`hr_low`,`hr_high`,`speed_low`,`speed_high`,`auto_hr`,`auto_speed`,`test_id_hr`,`test_id_speed`) values ('0dc35223-ea4f-4760-851a-d1cbd6d5bf4d','10f52302-2ddb-11de-86ae-00301bb60f17','1e678437-441a-470e-b058-cca95cfea404',128,148,'1.03','1.03',1,1,NULL,'52ec1e3f-ec3e-4676-960e-f8a547b734aa');
insert  into `zones`(`id`,`athlete_id`,`zones_definition_id`,`hr_low`,`hr_high`,`speed_low`,`speed_high`,`auto_hr`,`auto_speed`,`test_id_hr`,`test_id_speed`) values ('41913cc4-595b-4ff9-9f0d-a14fcc95de63','9f17b17c-594c-414e-bc6b-bd59e50c3cc0','e154a52c-1e5e-43b1-bc6c-5ec89d0ea915',107,153,NULL,NULL,1,NULL,NULL,'4178104d-71ba-41a9-a653-bd89e797d1be');
insert  into `zones`(`id`,`athlete_id`,`zones_definition_id`,`hr_low`,`hr_high`,`speed_low`,`speed_high`,`auto_hr`,`auto_speed`,`test_id_hr`,`test_id_speed`) values ('9b926319-1948-495d-bb44-53dff5f61a9b','9f17b17c-594c-414e-bc6b-bd59e50c3cc0','41ac38c4-4609-4e18-98a0-ec3b20f001de',153,190,NULL,NULL,1,NULL,NULL,'4178104d-71ba-41a9-a653-bd89e797d1be');
insert  into `zones`(`id`,`athlete_id`,`zones_definition_id`,`hr_low`,`hr_high`,`speed_low`,`speed_high`,`auto_hr`,`auto_speed`,`test_id_hr`,`test_id_speed`) values ('cb192319-6f7a-437a-a236-826016d5785f','9f17b17c-594c-414e-bc6b-bd59e50c3cc0','396456df-eaa5-4e6a-a83c-9b9ac7659eb9',190,199,NULL,NULL,1,NULL,NULL,'4178104d-71ba-41a9-a653-bd89e797d1be');
insert  into `zones`(`id`,`athlete_id`,`zones_definition_id`,`hr_low`,`hr_high`,`speed_low`,`speed_high`,`auto_hr`,`auto_speed`,`test_id_hr`,`test_id_speed`) values ('38338c2a-927b-4e02-a710-d07609d57a27','9f17b17c-594c-414e-bc6b-bd59e50c3cc0','1e678437-441a-470e-b058-cca95cfea404',199,226,NULL,NULL,1,NULL,NULL,'4178104d-71ba-41a9-a653-bd89e797d1be');

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

insert  into `zones_definition`(`id`,`coach_id`,`sequence`,`shortcut`,`description`,`color`,`lactate_low`,`lactate_high`,`hr_low`,`hr_high`) values ('e154a52c-1e5e-43b1-bc6c-5ec89d0ea915','0b0b7658-2ddb-11de-86ae-00301bb60f17',0,'GA 1','Grundlagen Ausdauer 1','#00cc66','1.00','2.00',0,50);
insert  into `zones_definition`(`id`,`coach_id`,`sequence`,`shortcut`,`description`,`color`,`lactate_low`,`lactate_high`,`hr_low`,`hr_high`) values ('41ac38c4-4609-4e18-98a0-ec3b20f001de','0b0b7658-2ddb-11de-86ae-00301bb60f17',1,'GA 2','Grundlagen Ausdauer 2','#0000ff','2.00','3.50',51,70);
insert  into `zones_definition`(`id`,`coach_id`,`sequence`,`shortcut`,`description`,`color`,`lactate_low`,`lactate_high`,`hr_low`,`hr_high`) values ('396456df-eaa5-4e6a-a83c-9b9ac7659eb9','0b0b7658-2ddb-11de-86ae-00301bb60f17',2,'EXT','Extensiver Bereich','#990099','3.50','4.00',71,90);
insert  into `zones_definition`(`id`,`coach_id`,`sequence`,`shortcut`,`description`,`color`,`lactate_low`,`lactate_high`,`hr_low`,`hr_high`) values ('1e678437-441a-470e-b058-cca95cfea404','0b0b7658-2ddb-11de-86ae-00301bb60f17',3,'INT','Intensiver Bereich','#cc0000','4.00','6.00',91,100);
insert  into `zones_definition`(`id`,`coach_id`,`sequence`,`shortcut`,`description`,`color`,`lactate_low`,`lactate_high`,`hr_low`,`hr_high`) values ('1679d3da-1705-43f2-8bc5-c4144a7a24b9','123456ABCDEFG',1,NULL,'Test',NULL,'10.00','12.10',NULL,NULL);

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
