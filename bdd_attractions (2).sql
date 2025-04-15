-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 15 avr. 2025 à 20:23
-- Version du serveur : 8.0.41
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdd_attractions`
--

-- --------------------------------------------------------

--
-- Structure de la table `attractions`
--

DROP TABLE IF EXISTS `attractions`;
CREATE TABLE IF NOT EXISTS `attractions` (
  `attractions_ID` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(150) DEFAULT NULL,
  `description` text,
  `prix_base` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`attractions_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `attractions`
--

INSERT INTO `attractions` (`attractions_ID`, `nom`, `description`, `prix_base`) VALUES
(1, 'Montagnes Russes', 'Chaque montée est une promesse. Chaque descente, une explosion !', '30.00'),
(2, 'Château Hanté', 'Faîtes attention, un fantôme vous attend peut-être au tournant.', '18.50'),
(3, 'Grande Roue', 'Prenez de la hauteur et admirez le parc à 360°.', '10.00');

-- --------------------------------------------------------

--
-- Structure de la table `historique_attractions`
--

DROP TABLE IF EXISTS `historique_attractions`;
CREATE TABLE IF NOT EXISTS `historique_attractions` (
  `Historique_attractions_ID` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `Utilisateur_ID` bigint UNSIGNED DEFAULT NULL,
  `attractions_ID` bigint UNSIGNED DEFAULT NULL,
  `date_visite` date DEFAULT NULL,
  PRIMARY KEY (`Historique_attractions_ID`),
  KEY `Utilisateur_ID` (`Utilisateur_ID`),
  KEY `attractions_ID` (`attractions_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `historique_attractions`
--

INSERT INTO `historique_attractions` (`Historique_attractions_ID`, `Utilisateur_ID`, `attractions_ID`, `date_visite`) VALUES
(1, 2, 1, '2024-03-20');

-- --------------------------------------------------------

--
-- Structure de la table `reductions`
--

DROP TABLE IF EXISTS `reductions`;
CREATE TABLE IF NOT EXISTS `reductions` (
  `Réduction_ID` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `min_age` int DEFAULT NULL,
  `max_age` int DEFAULT NULL,
  `min_visites` int DEFAULT NULL,
  `pourcentage_reduction` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`Réduction_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `reductions`
--

INSERT INTO `reductions` (`Réduction_ID`, `min_age`, `max_age`, `min_visites`, `pourcentage_reduction`) VALUES
(1, 18, 25, 5, '10.00'),
(2, 60, 120, 3, '15.00'),
(3, 5, 12, 0, '20.00');

-- --------------------------------------------------------

--
-- Structure de la table `réservation`
--

DROP TABLE IF EXISTS `réservation`;
CREATE TABLE IF NOT EXISTS `réservation` (
  `Réservation_ID` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `Utilisateur_ID` bigint UNSIGNED DEFAULT NULL,
  `attractions_ID` bigint UNSIGNED DEFAULT NULL,
  `Réduction_ID` bigint UNSIGNED DEFAULT NULL,
  `date_reservation` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `prix_paye` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`Réservation_ID`),
  KEY `Utilisateur_ID` (`Utilisateur_ID`),
  KEY `attractions_ID` (`attractions_ID`),
  KEY `Réduction_ID` (`Réduction_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `réservation`
--

INSERT INTO `réservation` (`Réservation_ID`, `Utilisateur_ID`, `attractions_ID`, `Réduction_ID`, `date_reservation`, `prix_paye`) VALUES
(1, 2, 1, 1, '2025-04-15 20:19:22', '27.00'),
(2, 3, 2, NULL, '2025-04-15 20:19:22', '18.50'),
(3, 2, 1, 1, '2025-04-15 20:20:31', '27.00'),
(4, 3, 2, NULL, '2025-04-15 20:20:31', '18.50');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `prenom`, `email`, `mot_de_passe`, `date_naissance`, `role`) VALUES
(1, 'Admin', 'Principal', 'admin@parc.com', 'adminpass', '2004-03-20', 0),
(2, 'Martin', 'Claire', 'claire.martin@email.com', 'mdpClaire2024', '1985-09-21', 1),
(3, 'Durand', 'Luc', 'invite_luc@inconnu.com', NULL, '2000-01-01', 2);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `historique_attractions`
--
ALTER TABLE `historique_attractions`
  ADD CONSTRAINT `historique_attractions_ibfk_1` FOREIGN KEY (`Utilisateur_ID`) REFERENCES `utilisateurs` (`id`),
  ADD CONSTRAINT `historique_attractions_ibfk_2` FOREIGN KEY (`attractions_ID`) REFERENCES `attractions` (`attractions_ID`);

--
-- Contraintes pour la table `réservation`
--
ALTER TABLE `réservation`
  ADD CONSTRAINT `réservation_ibfk_1` FOREIGN KEY (`Utilisateur_ID`) REFERENCES `utilisateurs` (`id`),
  ADD CONSTRAINT `réservation_ibfk_2` FOREIGN KEY (`attractions_ID`) REFERENCES `attractions` (`attractions_ID`),
  ADD CONSTRAINT `réservation_ibfk_3` FOREIGN KEY (`Réduction_ID`) REFERENCES `reductions` (`Réduction_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
