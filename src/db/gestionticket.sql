-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  mer. 07 juin 2023 à 11:29
-- Version du serveur :  5.7.28
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestionticket`
--

-- --------------------------------------------------------

--
-- Structure de la table `autremateriel`
--

DROP TABLE IF EXISTS `autremateriel`;
CREATE TABLE IF NOT EXISTS `autremateriel` (
  `IdMateriel` varchar(6) NOT NULL,
  `Designation` varchar(20) NOT NULL,
  `Archiver` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdMateriel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `autremateriel`
--

INSERT INTO `autremateriel` (`IdMateriel`, `Designation`, `Archiver`) VALUES
('AU001', 'Clavier Lenovo', 0),
('AU002', 'Souris hp ', 0),
('AU003', 'Routeur DIR-816', 0),
('AU004', 'Cable RJ45 1m', 0);

-- --------------------------------------------------------

--
-- Structure de la table `ecran`
--

DROP TABLE IF EXISTS `ecran`;
CREATE TABLE IF NOT EXISTS `ecran` (
  `IdMateriel` varchar(6) NOT NULL,
  `Marque` varchar(10) NOT NULL,
  `Dimension` varchar(10) NOT NULL,
  `Archiver` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdMateriel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ecran`
--

INSERT INTO `ecran` (`IdMateriel`, `Marque`, `Dimension`, `Archiver`) VALUES
('E001', 'HP', '17\"', 0),
('E002', 'HYUNDAI', '20\"', 0),
('E003', 'DELL', '24\"', 0),
('E004', 'Lenovo', '15\"', 0);

-- --------------------------------------------------------

--
-- Structure de la table `fichedemande`
--

DROP TABLE IF EXISTS `fichedemande`;
CREATE TABLE IF NOT EXISTS `fichedemande` (
  `NumeroFiche` int(9) NOT NULL AUTO_INCREMENT,
  `DateDemande` date NOT NULL,
  `HeureDemande` time NOT NULL,
  `NumPorte` int(3) NOT NULL,
  `MatriculePersonel` int(5) UNSIGNED NOT NULL,
  PRIMARY KEY (`NumeroFiche`),
  KEY `fk` (`MatriculePersonel`),
  KEY `fk2` (`NumPorte`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `fichedemande`
--

INSERT INTO `fichedemande` (`NumeroFiche`, `DateDemande`, `HeureDemande`, `NumPorte`, `MatriculePersonel`) VALUES
(1, '2023-05-16', '12:14:22', 9, 4),
(2, '2023-05-06', '21:56:50', 6, 4),
(3, '2023-05-09', '04:00:00', 9, 4),
(4, '2023-05-06', '22:00:55', 8, 4),
(5, '2023-05-06', '22:07:46', 4, 13),
(6, '2023-05-09', '11:52:01', 8, 4),
(7, '2023-05-09', '15:21:06', 8, 1),
(8, '2023-06-06', '11:51:48', 10, 13);

-- --------------------------------------------------------

--
-- Structure de la table `materiel`
--

DROP TABLE IF EXISTS `materiel`;
CREATE TABLE IF NOT EXISTS `materiel` (
  `IdMateriel` varchar(6) NOT NULL,
  `Type` varchar(15) NOT NULL DEFAULT '???',
  PRIMARY KEY (`IdMateriel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `materiel`
--

INSERT INTO `materiel` (`IdMateriel`, `Type`) VALUES
('AU001', 'Autre'),
('AU002', 'Autre'),
('AU003', 'Autre'),
('AU004', 'Autre'),
('E001', 'Ecran'),
('E002', 'Ecran'),
('E003', 'Ecran'),
('E004', 'Ecran'),
('UC001', 'Unité Centrale'),
('UC002', 'Unité Centrale'),
('UC003', 'Unité Centrale'),
('UC004', 'Unité Centrale'),
('UC005', 'Unité Centrale');

-- --------------------------------------------------------

--
-- Structure de la table `personel`
--

DROP TABLE IF EXISTS `personel`;
CREATE TABLE IF NOT EXISTS `personel` (
  `MatriculePersonel` int(5) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NomPersonel` varchar(30) NOT NULL,
  `PrenomPersonel` varchar(30) NOT NULL,
  `Telephone` varchar(13) NOT NULL,
  PRIMARY KEY (`MatriculePersonel`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `personel`
--

INSERT INTO `personel` (`MatriculePersonel`, `NomPersonel`, `PrenomPersonel`, `Telephone`) VALUES
(1, 'RANAIVOMANANA', 'Riantsoa', '034 00 000 10'),
(4, 'HASIMBOLA ', 'Rivoniaina', '033 00 000 01'),
(13, 'ANDRIAMAROMANANA', 'André', '033 00 000 00');

-- --------------------------------------------------------

--
-- Structure de la table `porte`
--

DROP TABLE IF EXISTS `porte`;
CREATE TABLE IF NOT EXISTS `porte` (
  `NumPorte` int(3) NOT NULL,
  `Poste` varchar(30) NOT NULL DEFAULT '....',
  `CodeService` char(4) NOT NULL,
  `Archiver` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`NumPorte`),
  KEY `fk1` (`CodeService`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `porte`
--

INSERT INTO `porte` (`NumPorte`, `Poste`, `CodeService`, `Archiver`) VALUES
(1, '....', 'COM', 1),
(2, '....', 'COM', 1),
(3, '....', 'COM', 1),
(4, '....', 'COM', 1),
(5, '....', 'DRI', 1),
(6, '....', 'DRI', 1),
(7, 'tsy aiko', 'PHA', 0),
(8, 'Tsy aiko', 'PHA', 0),
(9, 'Directeur', 'DRI', 0),
(10, 'Service reparation', 'DRI', 0),
(11, 'Tsy aiko', 'DRI', 0);

-- --------------------------------------------------------

--
-- Structure de la table `recuperer`
--

DROP TABLE IF EXISTS `recuperer`;
CREATE TABLE IF NOT EXISTS `recuperer` (
  `Observation` varchar(50) DEFAULT 'Non ',
  `Solution` varchar(50) DEFAULT NULL,
  `DateRecuperation` date DEFAULT NULL,
  `HeureRecuperation` time DEFAULT NULL,
  `Reparer` tinyint(1) DEFAULT NULL,
  `IdMateriel` varchar(6) NOT NULL,
  `MatriculeReparateur` int(2) DEFAULT NULL,
  `NumeroFiche` int(9) NOT NULL,
  PRIMARY KEY (`IdMateriel`,`NumeroFiche`),
  KEY `fk3` (`MatriculeReparateur`),
  KEY `NumeroFiche` (`NumeroFiche`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `recuperer`
--

INSERT INTO `recuperer` (`Observation`, `Solution`, `DateRecuperation`, `HeureRecuperation`, `Reparer`, `IdMateriel`, `MatriculeReparateur`, `NumeroFiche`) VALUES
('Non', NULL, NULL, NULL, NULL, 'AU002', NULL, 1),
('Non', NULL, NULL, NULL, NULL, 'E002', NULL, 5),
('Non', NULL, NULL, NULL, NULL, 'E002', NULL, 8),
('Non', NULL, NULL, NULL, NULL, 'UC002', NULL, 7),
('Non', NULL, NULL, NULL, NULL, 'UC003', NULL, 6);

-- --------------------------------------------------------

--
-- Structure de la table `reparateur`
--

DROP TABLE IF EXISTS `reparateur`;
CREATE TABLE IF NOT EXISTS `reparateur` (
  `MatriculeReparateur` int(2) NOT NULL AUTO_INCREMENT,
  `NomReparateur` varchar(30) NOT NULL,
  `PrenomReparateur` varchar(30) NOT NULL,
  PRIMARY KEY (`MatriculeReparateur`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reparateur`
--

INSERT INTO `reparateur` (`MatriculeReparateur`, `NomReparateur`, `PrenomReparateur`) VALUES
(1, 'HASIMBOLA', 'Rivoniaina'),
(2, 'RANAIVOMANANA', 'Riantsoa Ckael'),
(3, 'RANDRIAMIFIDY', 'RIvo'),
(4, 'IDEALISOA', 'DImby GIno'),
(6, 'RASOANJANAHARY', 'Liantsoa Daniela'),
(7, 'LAZASOA', 'Jenny Kael'),
(8, 'RAKOTO', 'Maurice'),
(9, 'ANDRIAMAROMANANA', 'André'),
(10, 'BAKOLINIAINA', 'Grifin');

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `CodeService` char(4) NOT NULL,
  `Service` varchar(20) NOT NULL,
  PRIMARY KEY (`CodeService`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`CodeService`, `Service`) VALUES
('COM', 'Communication'),
('DRI', 'Informatique'),
('PHA', 'Pharmacie');

-- --------------------------------------------------------

--
-- Structure de la table `unitecentrale`
--

DROP TABLE IF EXISTS `unitecentrale`;
CREATE TABLE IF NOT EXISTS `unitecentrale` (
  `IdMateriel` varchar(6) NOT NULL,
  `Ram` varchar(9) NOT NULL,
  `Processeur` varchar(10) NOT NULL,
  `DisqueDur` varchar(10) NOT NULL,
  `Archiver` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdMateriel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `unitecentrale`
--

INSERT INTO `unitecentrale` (`IdMateriel`, `Ram`, `Processeur`, `DisqueDur`, `Archiver`) VALUES
('UC001', '4Gb  ', 'Core I3', '100 Gb', 0),
('UC002', '4Gb', 'Core I5', '500Gb', 0),
('UC003', '512Gb', 'Core 2 Duo', '150Gb', 0),
('UC004', '2Gb', 'Core I3', '500Gb', 0),
('UC005', 'Tsy aiko', 'Tsy aiko', 'Tsy aiko', 1);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `viewfiche`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `viewfiche`;
CREATE TABLE IF NOT EXISTS `viewfiche` (
`NumPorte` int(3)
,`MatriculePersonel` int(5) unsigned
,`NumeroFiche` int(9)
,`DateDemande` date
,`HeureDemande` time
,`NomPersonel` varchar(30)
,`PrenomPersonel` varchar(30)
,`Telephone` varchar(13)
,`CodeService` char(4)
,`Poste` varchar(30)
,`Archiver` tinyint(1)
,`Service` varchar(20)
);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `viewporte`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `viewporte`;
CREATE TABLE IF NOT EXISTS `viewporte` (
`CodeService` char(4)
,`NumPorte` int(3)
,`Poste` varchar(30)
,`Archiver` tinyint(1)
,`Service` varchar(20)
);

-- --------------------------------------------------------

--
-- Structure de la vue `viewfiche`
--
DROP TABLE IF EXISTS `viewfiche`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewfiche`  AS  select `fichedemande`.`NumPorte` AS `NumPorte`,`fichedemande`.`MatriculePersonel` AS `MatriculePersonel`,`fichedemande`.`NumeroFiche` AS `NumeroFiche`,`fichedemande`.`DateDemande` AS `DateDemande`,`fichedemande`.`HeureDemande` AS `HeureDemande`,`personel`.`NomPersonel` AS `NomPersonel`,`personel`.`PrenomPersonel` AS `PrenomPersonel`,`personel`.`Telephone` AS `Telephone`,`viewporte`.`CodeService` AS `CodeService`,`viewporte`.`Poste` AS `Poste`,`viewporte`.`Archiver` AS `Archiver`,`viewporte`.`Service` AS `Service` from ((`fichedemande` join `personel` on((`fichedemande`.`MatriculePersonel` = `personel`.`MatriculePersonel`))) join `viewporte` on((`fichedemande`.`NumPorte` = `viewporte`.`NumPorte`))) ;

-- --------------------------------------------------------

--
-- Structure de la vue `viewporte`
--
DROP TABLE IF EXISTS `viewporte`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewporte`  AS  select `porte`.`CodeService` AS `CodeService`,`porte`.`NumPorte` AS `NumPorte`,`porte`.`Poste` AS `Poste`,`porte`.`Archiver` AS `Archiver`,`service`.`Service` AS `Service` from (`porte` join `service` on((`porte`.`CodeService` = `service`.`CodeService`))) ;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `autremateriel`
--
ALTER TABLE `autremateriel`
  ADD CONSTRAINT `autremateriel_ibfk_1` FOREIGN KEY (`IdMateriel`) REFERENCES `materiel` (`IdMateriel`);

--
-- Contraintes pour la table `ecran`
--
ALTER TABLE `ecran`
  ADD CONSTRAINT `ecran_ibfk_1` FOREIGN KEY (`IdMateriel`) REFERENCES `materiel` (`IdMateriel`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `fichedemande`
--
ALTER TABLE `fichedemande`
  ADD CONSTRAINT `fk` FOREIGN KEY (`MatriculePersonel`) REFERENCES `personel` (`MatriculePersonel`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`NumPorte`) REFERENCES `porte` (`NumPorte`);

--
-- Contraintes pour la table `porte`
--
ALTER TABLE `porte`
  ADD CONSTRAINT `porte_ibfk_1` FOREIGN KEY (`CodeService`) REFERENCES `service` (`CodeService`);

--
-- Contraintes pour la table `recuperer`
--
ALTER TABLE `recuperer`
  ADD CONSTRAINT `fk3` FOREIGN KEY (`MatriculeReparateur`) REFERENCES `reparateur` (`MatriculeReparateur`),
  ADD CONSTRAINT `recuperer_ibfk_1` FOREIGN KEY (`IdMateriel`) REFERENCES `materiel` (`IdMateriel`),
  ADD CONSTRAINT `recuperer_ibfk_2` FOREIGN KEY (`NumeroFiche`) REFERENCES `fichedemande` (`NumeroFiche`);

--
-- Contraintes pour la table `unitecentrale`
--
ALTER TABLE `unitecentrale`
  ADD CONSTRAINT `unitecentrale_ibfk_1` FOREIGN KEY (`IdMateriel`) REFERENCES `materiel` (`IdMateriel`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
