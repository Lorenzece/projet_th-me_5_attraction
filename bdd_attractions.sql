-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 02 avr. 2025 à 14:27
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
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(150) DEFAULT NULL,
  `description` text,
  `prix_base` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `attractions`
--

INSERT INTO `attractions` (`id`, `nom`, `description`, `prix_base`) VALUES
(1, 'Montagnes Russes', 'Chaque montée est une promesse. Chaque descente, une explosion !', '30.00'),
(2, 'Château Hanté', 'faîtes attention un fantôme vous attend peut-être au tournant ', '18.50'),
(3, 'Grande Roue', 'Prenez de la hauteur et admirez le parc à 360°.', '10.00');

-- --------------------------------------------------------

--
-- Structure de la table `historique_attractions`
--

DROP TABLE IF EXISTS `historique_attractions`;
CREATE TABLE IF NOT EXISTS `historique_attractions` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `utilisateur_id` int DEFAULT NULL,
  `attraction_id` int DEFAULT NULL,
  `date_visite` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `historique_attractions`
--

INSERT INTO `historique_attractions` (`id`, `utilisateur_id`, `attraction_id`, `date_visite`) VALUES
(1, 2, 1, '2024-03-20');

-- --------------------------------------------------------

--
-- Structure de la table `reductions`
--

DROP TABLE IF EXISTS `reductions`;
CREATE TABLE IF NOT EXISTS `reductions` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `min_age` int DEFAULT NULL,
  `max_age` int DEFAULT NULL,
  `min_visites` int DEFAULT NULL,
  `pourcentage_reduction` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `reductions`
--

INSERT INTO `reductions` (`id`, `min_age`, `max_age`, `min_visites`, `pourcentage_reduction`) VALUES
(1, 18, 25, 5, '10.00'),
(2, 60, 120, 3, '15.00'),
(3, 5, 12, 0, '20.00');

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `utilisateur_id` int DEFAULT NULL,
  `attraction_id` int DEFAULT NULL,
  `date_reservation` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `prix_paye` decimal(6,2) DEFAULT NULL,
  `reduction_appliquee` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `reservations`
--

INSERT INTO `reservations` (`id`, `utilisateur_id`, `attraction_id`, `date_reservation`, `prix_paye`, `reduction_appliquee`) VALUES
(1, 2, 1, '2025-04-02 14:06:00', '27.00', '10.00'),
(2, 3, 2, '2025-04-02 14:06:00', '18.50', '0.00');

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
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `email` (`email`)
) ;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `prenom`, `email`, `mot_de_passe`, `date_naissance`, `role`) VALUES
(4, 'Admin', 'Principal', 'admin@parc.com', 'adminpass', '2004-03-20', 0),
(5, 'Martin', 'Claire', 'claire.martin@email.com', 'mdpClaire2024', '1985-09-21', 1),
(6, 'Durand', 'Luc', 'invite_luc@inconnu.com', NULL, '2000-01-01', 2);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
