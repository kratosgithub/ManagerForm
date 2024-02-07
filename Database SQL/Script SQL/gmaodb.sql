-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 01 fév. 2024 à 12:51
-- Version du serveur : 8.0.27
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gmaodb`
--

-- --------------------------------------------------------

--
-- Structure de la table `action_form`
--

DROP TABLE IF EXISTS `action_form`;
CREATE TABLE IF NOT EXISTS `action_form` (
  `idAction` int NOT NULL AUTO_INCREMENT,
  `jour` date NOT NULL,
  `harrivee` time NOT NULL,
  `hfin` time NOT NULL,
  `nomCustomer` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `nomHdre` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `statut` enum('Sous garantie','Hors garantie','Contrat de maintenance','Hors contrat de maintenance') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT 'Hors contrat de maintenance',
  PRIMARY KEY (`idAction`),
  KEY `fk_clients_nom_customer` (`nomCustomer`),
  KEY `fk_hardware_nom_hdre` (`nomHdre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Déchargement des données de la table `action_form`
--

INSERT INTO `action_form` (`idAction`, `jour`, `harrivee`, `hfin`, `nomCustomer`, `nomHdre`, `statut`) VALUES
(1, '2024-01-12', '12:30:00', '17:30:00', 'HOPITAL DISTRICT DEIDO', 'HUMASTAR', 'Sous garantie'),
(2, '2024-01-24', '14:30:00', '16:30:00', 'CHU', 'AUTOMATE ELISA', 'Sous garantie');

-- --------------------------------------------------------

--
-- Structure de la table `agents`
--

DROP TABLE IF EXISTS `agents`;
CREATE TABLE IF NOT EXISTS `agents` (
  `idAgent` int NOT NULL AUTO_INCREMENT,
  `nomAgent` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `telAgent` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`idAgent`),
  KEY `uk_agents` (`nomAgent`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Déchargement des données de la table `agents`
--

INSERT INTO `agents` (`idAgent`, `nomAgent`, `telAgent`) VALUES
(1, 'KOUANGA', '691461450'),
(2, 'DJOMALEU', '690219460'),
(3, 'MOUTONG', '694563717');

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `idClient` int NOT NULL AUTO_INCREMENT,
  `nomClient` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `service` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `ville` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `telClient` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`idClient`),
  KEY `uk_clients` (`nomClient`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`idClient`, `nomClient`, `service`, `ville`, `telClient`) VALUES
(1, 'HOPITAL DISTRICT DEIDO', 'RADIOLOGIE', 'DOUALA', '233291460'),
(2, 'HÔPITAL RÉGIONAL DE MAROUA', 'LABORATOIRE', 'MAROUA', '233458975'),
(3, 'CHU', 'RADIOLOGIE', 'YAOUNDÉ', '233879657'),
(4, 'HOPITAL CNPS', 'RADIOLOGIE', 'YAOUNDE', '23345678');

-- --------------------------------------------------------

--
-- Structure de la table `hardware`
--

DROP TABLE IF EXISTS `hardware`;
CREATE TABLE IF NOT EXISTS `hardware` (
  `idHardware` int NOT NULL AUTO_INCREMENT,
  `nomHardware` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `marque` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `model` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `noSerie` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `poids` int NOT NULL,
  `longueur` int NOT NULL,
  `largeur` int NOT NULL,
  `hauteur` int NOT NULL,
  `puissanceMin` int NOT NULL,
  `puissanceMax` int NOT NULL,
  `fqceMin` int NOT NULL,
  `fqceMax` int NOT NULL,
  PRIMARY KEY (`idHardware`),
  KEY `uk_hardware` (`nomHardware`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Déchargement des données de la table `hardware`
--

INSERT INTO `hardware` (`idHardware`, `nomHardware`, `marque`, `model`, `noSerie`, `poids`, `longueur`, `largeur`, `hauteur`, `puissanceMin`, `puissanceMax`, `fqceMin`, `fqceMax`) VALUES
(1, 'AUTOMATE ELISA', 'HUMAN', 'ELYSIS UNO', '2950-6250', 70, 120, 80, 93, 100, 240, 50, 60),
(2, 'AUTOMATE ELECTROPHORESE', 'CELL START PROJET', 'MINPHOR', '58450-0207', 18, 36, 23, 12, 150, 220, 30, 50),
(3, 'HUMASTAR', 'HUMAN', 'HUMANSTAR 200', 'REF-16895', 51, 69, 76, 52, 220, 240, 50, 60);

-- --------------------------------------------------------

--
-- Structure de la table `history_record`
--

DROP TABLE IF EXISTS `history_record`;
CREATE TABLE IF NOT EXISTS `history_record` (
  `idRecord` int NOT NULL AUTO_INCREMENT,
  `idHardware` int NOT NULL,
  `jour` date NOT NULL,
  `motif` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `idAction` int NOT NULL,
  `nomAgt` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`idRecord`),
  KEY `fk_action_form_id_action` (`idAction`),
  KEY `fk_agents_nom_agt` (`nomAgt`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Déchargement des données de la table `history_record`
--

INSERT INTO `history_record` (`idRecord`, `idHardware`, `jour`, `motif`, `idAction`, `nomAgt`) VALUES
(1, 3, '2024-01-30', 'maintenance sous demande du client', 1, 'DJOMALEU');

-- --------------------------------------------------------

--
-- Structure de la table `install_form_typeb`
--

DROP TABLE IF EXISTS `install_form_typeb`;
CREATE TABLE IF NOT EXISTS `install_form_typeb` (
  `idFormB` int NOT NULL AUTO_INCREMENT,
  `jour` date NOT NULL,
  `hdebut` time NOT NULL,
  `hfin` time NOT NULL,
  `nomCtmer` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `nomHrdw` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`idFormB`),
  KEY `fk_clients_nom_ctmer` (`nomCtmer`),
  KEY `fk_hardware_nom_hrdw` (`nomHrdw`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

-- --------------------------------------------------------

--
-- Structure de la table `install_form_typec`
--

DROP TABLE IF EXISTS `install_form_typec`;
CREATE TABLE IF NOT EXISTS `install_form_typec` (
  `idFormC` int NOT NULL AUTO_INCREMENT,
  `jour` date NOT NULL,
  `hdebut` time NOT NULL,
  `hfin` time NOT NULL,
  `nomCusto` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `nomEqpmt` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`idFormC`),
  KEY `fk_clients_nom_custo` (`nomCusto`),
  KEY `fk_hardware_nom_eqpmt` (`nomEqpmt`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Déchargement des données de la table `install_form_typec`
--

INSERT INTO `install_form_typec` (`idFormC`, `jour`, `hdebut`, `hfin`, `nomCusto`, `nomEqpmt`) VALUES
(1, '2023-11-30', '16:30:00', '20:00:00', 'HOPITAL DISTRICT DEIDO', 'HUMASTAR');

-- --------------------------------------------------------

--
-- Structure de la table `pre_install_form`
--

DROP TABLE IF EXISTS `pre_install_form`;
CREATE TABLE IF NOT EXISTS `pre_install_form` (
  `idPreForm` int NOT NULL AUTO_INCREMENT,
  `jour` date NOT NULL,
  `hdebut` time NOT NULL,
  `hfin` time NOT NULL,
  `nomClient` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `nomHardware` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`idPreForm`),
  KEY `fk_clients_nom_client` (`nomClient`),
  KEY `fk_hardware_nom_hardware` (`nomHardware`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Déchargement des données de la table `pre_install_form`
--

INSERT INTO `pre_install_form` (`idPreForm`, `jour`, `hdebut`, `hfin`, `nomClient`, `nomHardware`) VALUES
(1, '2023-10-25', '14:30:00', '16:30:00', 'HOPITAL CNPS', 'AUTOMATE ELISA'),
(2, '2023-11-10', '07:30:00', '16:30:00', 'CHU', 'AUTOMATE ELECTROPHORESE'),
(3, '2023-11-30', '10:30:00', '16:30:00', 'HOPITAL DISTRICT DEIDO', 'HUMASTAR');

-- --------------------------------------------------------

--
-- Structure de la table `receipt_form`
--

DROP TABLE IF EXISTS `receipt_form`;
CREATE TABLE IF NOT EXISTS `receipt_form` (
  `idReceiptForm` int NOT NULL AUTO_INCREMENT,
  `jour` date NOT NULL,
  `harrivee` time NOT NULL,
  `destination` enum('Stock','Client') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT 'Stock',
  `expediteur` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `pays` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `accessoires` enum('Oui','Non') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT 'Non',
  `jourReceipt` date NOT NULL,
  `hreceipt` time NOT NULL,
  `nomReceipt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `nomClt` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `nomHard` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`idReceiptForm`),
  KEY `fk_clients_nom_clt` (`nomClt`),
  KEY `fk_hardware_nom_hard` (`nomHard`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Déchargement des données de la table `receipt_form`
--

INSERT INTO `receipt_form` (`idReceiptForm`, `jour`, `harrivee`, `destination`, `expediteur`, `pays`, `accessoires`, `jourReceipt`, `hreceipt`, `nomReceipt`, `nomClt`, `nomHard`) VALUES
(1, '2023-02-20', '11:45:00', 'Client', 'BOLLORE', 'ITALIE', 'Non', '2023-02-22', '14:46:00', 'DJOMALEU', 'CHU', 'AUTOMATE ELECTROPHORESE'),
(2, '2023-02-21', '10:20:00', 'Stock', 'INTEK', 'FRANCE', 'Non', '2023-02-23', '11:31:00', 'KOUANGA', 'HÔPITAL RÉGIONAL DE MAROUA', 'AUTOMATE ELISA'),
(3, '2023-11-28', '14:45:00', 'Client', 'ALWAYS', 'FRANCE', 'Non', '2023-11-28', '14:34:00', 'MOUTONG', 'HOPITAL DISTRICT DEIDO', 'HUMASTAR');

-- --------------------------------------------------------

--
-- Structure de la table `removal_form`
--

DROP TABLE IF EXISTS `removal_form`;
CREATE TABLE IF NOT EXISTS `removal_form` (
  `idRmForm` int NOT NULL AUTO_INCREMENT,
  `jour` date NOT NULL,
  `hrm` time NOT NULL,
  `nomClnt` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `nomHware` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `nomAgent` varchar(32) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `accessoires` enum('Oui','Non') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT 'Non',
  PRIMARY KEY (`idRmForm`),
  KEY `fk_clients_nom_clnt` (`nomClnt`),
  KEY `fk_hardware_nom_hware` (`nomHware`),
  KEY `fk_agents_nom_agent` (`nomAgent`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Déchargement des données de la table `removal_form`
--

INSERT INTO `removal_form` (`idRmForm`, `jour`, `hrm`, `nomClnt`, `nomHware`, `nomAgent`, `accessoires`) VALUES
(1, '2024-01-26', '14:00:00', 'HOPITAL DISTRICT DEIDO', 'HUMASTAR', 'DJOMALEU', 'Oui');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id_users` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `prenom` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `login` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `tel` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id_users`,`login`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id_users`, `nom`, `prenom`, `login`, `password`, `tel`) VALUES
(1, 'DOMAIN', 'ADMIN', 'ADM_domain', 'rRDrNT70KlTQwzzcvMnGJvN9l9+XdJVUZ/kWVZ880ys=', '690129640'),
(2, 'DJOMALEU', 'VERLIN', 'Djomal88', 'yxhXH2yknB4DnZingaLYP4N/QGYqRl1gKyQLG29mJE8=', '690129640');

-- --------------------------------------------------------

--
-- Structure de la table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE IF NOT EXISTS `users_roles` (
  `login` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `roles` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  UNIQUE KEY `uk_users_roles` (`login`,`roles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `users_roles`
--

INSERT INTO `users_roles` (`login`, `roles`) VALUES
('ADM_domain', 'admins'),
('Djomal88', 'default_group');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
