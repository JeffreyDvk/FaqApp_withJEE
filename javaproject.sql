-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 27 mai 2021 à 09:31
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `javaproject`
--

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `categorie` varchar(30) NOT NULL,
  `question` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  `inFaq` tinyint(1) NOT NULL,
  `asker` varchar(100) NOT NULL,
  `response` text NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `question`
--

INSERT INTO `question` (`id`, `categorie`, `question`, `status`, `inFaq`, `asker`, `response`) VALUES
(7, 'Commande de nourriture', 'Combien de chaises puis-je Livrer ?', 1, 1, 'no@gmail.com', '20'),
(18, 'Service traiteur', 'Livrer vous des fourchettes ?', 1, 0, 'admin@gmail.com', 'xhf,h,'),
(23, 'Command de nourriture', 'Que vendez vous ?', 0, 0, 'no@gmail.com', 'Aucune'),
(24, 'Service traiteur', 'Quand es ce que vous me rendez mes chaises ?', 1, 1, 'no@gmail.com', 'bnc'),
(26, 'Service traiteur', 'bof', 1, 0, 'no@gmail.com', 'wep'),
(27, 'Non sÃ©lectionner', 'scv', 0, 0, 'no@gmail.com', ''),
(28, 'Devenir partenaire', 'wnwgc', 1, 0, 'no@gmail.com', 'xcb'),
(29, 'Service traiteur', 'vnngn', 0, 0, 'no@gmail.com', '');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`email`, `password`, `status`) VALUES
('admin@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055', 0),
('jeffrey.dvk.fr@gmail.com', '2bfb5448e632e067180cac5f49fefcbe', 0),
('no@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `constraint1` (`asker`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
