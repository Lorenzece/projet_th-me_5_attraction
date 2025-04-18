-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 18 avr. 2025 à 09:12
-- Version du serveur : 5.7.40
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
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(150) DEFAULT NULL,
  `description` text,
  `prix_base` varchar(255) DEFAULT NULL,
  `limite_age` varchar(255) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `attractions`
--

INSERT INTO `attractions` (`id`, `nom`, `description`, `prix_base`, `limite_age`, `image_url`) VALUES
(1, 'Space Blaster', 'Montagnes russes à propulsion verticale qui vous propulsent à 120 km/h en 2 secondes !', '10 euro par personne', 'A partir de 14 ans', 'C:\\Users\\cleme\\Downloads\\parc-asterix.jpg'),
(2, 'Aqua Vortex', 'Un toboggan géant en spirale avec effets lumineux et jets d’eau', '7 euro par personne', 'A partir de 10 ans', 'C:\\Users\\cleme\\Downloads\\dbobyk_210813_1309_a-mod-1024x683.jpg'),
(3, 'La Maison Hantée', 'Parcours horrifique dans un manoir rempli d’acteurs et de frayeurs garanties.', '8 euro par personne', 'A partir de 16 ans', 'C:\\Users\\cleme\\Downloads\\2e03dbe5921887536af057060302d8ae_XL.jpg'),
(4, 'Cyber Galaxy', 'Expérience VR interactive où vous pilotez un vaisseau spatial dans une galaxie alien', '12 euro par personne', 'A partir de 10 ans', 'C:\\Users\\cleme\\Downloads\\Disney_SpaceMountain_KV3_HR-e1493202193421-1.jpg'),
(5, 'Train Magique', 'Un petit train féerique à travers un monde enchanté, idéal pour les plus jeunes', '4 euro par personne', 'A partir de 4 ans', 'C:\\Users\\cleme\\Downloads\\93c6f918c257c8cf10e5807644bea263.jpg'),
(6, 'Le Carrousel Royal', 'Un magnifique manège à chevaux de style vintage avec musique et lumières', '3 euro par personne', 'A partir de 2 ans', 'C:\\Users\\cleme\\Downloads\\R.jfif'),
(7, 'Tornado Tower', 'Une tour de chute libre de 60 mètres avec rotation à 360° avant la descente', '9 euro par personne', 'A partir de 14 ans', 'C:\\Users\\cleme\\Downloads\\thumb_0fd07bf3214ef83622713887458dfe93_1280_960_1.jpg'),
(8, 'Le Royaume du Sorcier', 'Parcours interactif avec baguette magique, énigmes et effets spéciaux dans un univers de magie', '10 euro par personne', 'A partir de 7 ans', 'C:\\Users\\cleme\\Downloads\\Diagon_Alley_Dragon-1536x1024.jpg'),
(9, 'Karting géant', 'Piste de karting avec karts électriques pour les grands', '11 euro par personne', 'A partir de 12 ans', 'C:\\Users\\cleme\\Downloads\\des-essais-sur-la-piste-ont-permis-de-juger-de-ses-performances-photo-dr-1654611143.jpg'),
(10, 'Volcan Fury', 'Montagnes russes dans un décor de volcan en éruption, avec jets de vapeur et effets sonores', '10 euro par personne', 'A partir de 12 ans', 'C:\\Users\\cleme\\Downloads\\roller-coaster-amusement-park-90s-retro-background_962764-97600.jpg');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
