
--
-- Base de données : GMAO DB
-- 

--
-- Structure de la table : Users
--

-- Suppression de la table Users si elle existe.
drop table if exists `users`;

-- Creation de la table Users
create table if not exists `users` (
	`id_users` int auto_increment not null,
    `nom` varchar(32)  collate utf8mb4_unicode_520_ci not null,
    `prenom` varchar(32) collate utf8mb4_unicode_520_ci not null,
    `login` varchar(32) collate utf8mb4_unicode_520_ci not null,
    `password` varchar(64) collate utf8mb4_unicode_520_ci not null,
    `tel` varchar(16) collate utf8mb4_unicode_520_ci not null,
    
    primary key (`id_users`, `login`)
) Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci row_format=dynamic;

--
-- Déchargement des données de la table `users`
--

insert into  `users` (`id_users`, `nom`, `prenom`, `login`, `password`, `tel`) values
(1, 'DOMAIN', 'ADMIN', 'ADM_domain', 'rRDrNT70KlTQwzzcvMnGJvN9l9+XdJVUZ/kWVZ880ys=', '690129640'),
(2, 'DJOMALEU', 'VERLIN', 'Djomal88', 'yxhXH2yknB4DnZingaLYP4N/QGYqRl1gKyQLG29mJE8=', '690129640');

-- 
-- Structure de la table : Users_roles
--

-- Suppression de la table si elle existe.
drop table if exists `users_roles`;

-- Creation de la table : Users_roles
create table if not exists `users_roles` (
	`login` varchar(32) collate utf8mb4_unicode_520_ci not null,
    `roles` varchar(32) collate utf8mb4_unicode_520_ci not null,
    
    unique key `uk_users_roles` (`login`, `roles`)
) Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci row_format=dynamic;

--
-- Déchargement des données de la table `users_roles`
--

insert into `users_roles` (`login`, `roles`) values
('ADM_domain', 'admins'),
('Djomal88', 'default_group');

--
-- Structure de la table : Customers ou Clients
--

-- Suppression de la table si elle existe.
drop table if exists `clients`;

-- Creation de la table : Customers
create table if not exists `clients` (
	`idClient` int auto_increment not null,
    `nomClient` varchar(32) collate utf8mb4_unicode_520_ci not null,
    `service` varchar(32) collate utf8mb4_unicode_520_ci not null,
    `ville` varchar(20) collate utf8mb4_unicode_520_ci not null,
    `telClient` varchar(16) collate utf8mb4_unicode_520_ci not null,
    
    primary key (`idClient`),
    key `uk_clients` (`nomClient`)
) Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci;

--
-- Chargement des données de la table `clients`
--

insert into  `clients` (`idClient`, `nomClient`, `service`, `ville`, `telClient`) values
(1, 'HOPITAL DISTRICT DEIDO', 'RADIOLOGIE', 'DOUALA', '233291460'),
(2, 'HÔPITAL RÉGIONAL DE MAROUA', 'LABORATOIRE', 'MAROUA', '233458975'),
(3, 'CHU', 'RADIOLOGIE', 'YAOUNDÉ', '233879657'),
(4, 'HOPITAL CNPS', 'RADIOLOGIE', 'YAOUNDE', '23345678');

--
-- Structure de la table : Agents ou Techniciens
--

-- Suppression de la table :  Agents ou Techniciens
drop table if exists `agents`;

-- Creation de la table : Agents
create table if not exists `agents` (
	`idAgent` int auto_increment not null,
    `nomAgent` varchar(32) collate utf8mb4_unicode_520_ci not null,
     `telAgent` varchar(16) collate utf8mb4_unicode_520_ci not null,
     
     primary key (`idAgent`),
     key `uk_agents` (`nomAgent`)
) Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci;

--
-- Chargement des données de la table `agents`
--

insert into `agents` (`idAgent`, `nomAgent`, `telAgent`) values
(1, 'KOUANGA', '691461450'),
(2, 'DJOMALEU', 690219460),
(3, 'MOUTONG', '694563717');

--
-- Structure de la table : Hardware ou Equipements
--

-- Suppression de la table : Hardware
drop table if exists `hardware`;

-- Creation de la table : Hardware
create table if not exists `hardware` (
	`idHardware` int auto_increment not null,
    `nomHardware` varchar(32) collate utf8mb4_unicode_520_ci not null,
     `marque` varchar(32) collate utf8mb4_unicode_520_ci not null,
	`model` varchar(32) collate utf8mb4_unicode_520_ci not null,
	`noSerie` varchar(32) collate utf8mb4_unicode_520_ci not null,
    `poids` int collate utf8mb4_unicode_520_ci not null,
    `longueur` int collate utf8mb4_unicode_520_ci not null,
    `largeur` int collate utf8mb4_unicode_520_ci not null,
    `hauteur` int collate utf8mb4_unicode_520_ci not null,
    `puissanceMin` int collate utf8mb4_unicode_520_ci not null,
    `puissanceMax` int collate utf8mb4_unicode_520_ci not null,
    `fqceMin` int collate utf8mb4_unicode_520_ci not null,
    `fqceMax` int collate utf8mb4_unicode_520_ci not null,
    
    primary key (`idHardware`),
    key `uk_hardware` (`nomHardware`)
) Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci;

--
-- Déchargement des données de la table : `hardware`
--

insert into `hardware` (`idHardware`, `nomHardware`, `marque`, `model`, `noSerie`, `poids`, `longueur`, `largeur`, `hauteur`, `puissanceMin`, `puissanceMax`, `fqceMin`, `fqceMax`) values
(1, 'AUTOMATE ELISA', 'HUMAN', 'ELYSIS UNO', '2950-6250', 70, 120, 80, 93, 100, 240, 50, 60),
(2, 'AUTOMATE ELECTROPHORESE', 'CELL START PROJET', 'MINPHOR', '58450-0207', 18, 36, 23, 12, 150, 220, 30, 50),
(3, 'HUMASTAR', 'HUMAN', 'HUMANSTAR 200', 'REF-16895', 51, 69, 76, 52, 220, 240, 50, 60);

--
-- Structure de la table : Pre_Install_Form
--

-- Suppression de la table si elle existe.
drop table if exists `pre_install_form`;

-- Creation de la table : pre_install_form
create table if not exists `pre_install_form` (
	`idPreForm` int auto_increment not null,
   `jour` date not null,
    `hdebut` time not null, 
    -- `mdebut` int not null,
    `hfin` time not null,
    -- `mfin` int not null,
    `nomClient` varchar(32) not null,
    `nomHardware` varchar(32) not null,
    
    primary key (`idPreForm`),
    
    -- foreign key 
    key `fk_clients_nom_client` (`nomClient`),
    key `fk_hardware_nom_hardware` (`nomHardware`)
) Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci;

--
-- Chargement des données de la table `pre_install_form`
--

insert into `pre_install_form` (`idPreForm`, `jour`, `hdebut`, `hfin`, `nomClient`, `nomHardware`) values
(1, '2023-10-25', '14:30:00', '16:30:00', 'HOPITAL CNPS', 'AUTOMATE ELISA'),
(2, '2023-11-10', '07:30:00', '16:30:00', 'CHU', 'AUTOMATE ELECTROPHORESE'),
(3, '2023-11-30', '10:30:00', '16:30:00', 'HOPITAL DISTRICT DEIDO', 'HUMASTAR');

--
-- Structure de la table : Receipt_form
--

-- Suppression de la table si elle existe
drop table if exists `receipt_form`;

-- Creation de la table
create table if not exists `receipt_form` (
	`idReceiptForm` int auto_increment not null,
    `jour` date not null,
    `harrivee` time not null,
    -- `marrivee` int not null,
    `destination` enum('Stock', 'Client') collate utf8mb4_unicode_520_ci default 'Stock',
    `expediteur` varchar(32) collate utf8mb4_unicode_520_ci not null,
    `pays` varchar(32) collate utf8mb4_unicode_520_ci not null,
    `accessoires` enum('Oui', 'Non') collate utf8mb4_unicode_520_ci default 'Non',
    `jourReceipt` date not null,
    `hreceipt` time not null,
    -- `mreceipt` int not null,
    `nomReceipt` varchar(32) collate utf8mb4_unicode_520_ci not null,
    `nomClt` varchar(32) not null,
    `nomHard` varchar(32) not null,
    
    primary key(`idReceiptForm`),
    
    -- foreign key 
    key `fk_clients_nom_clt`(`nomClt`),
    key `fk_hardware_nom_hard`(`nomHard`)
)  Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci;

--
-- Déchargement des données de la table `receipt_form`
--

insert into `receipt_form` (`idReceiptForm`, `jour`, `harrivee`, `destination`, `expediteur`, `pays`, `accessoires`, `jourReceipt`, `hreceipt`, `nomReceipt`, `nomClt`, `nomHard`) values
(1, '2023-02-20 01:00:00', '11:45:00', 'Client', 'BOLLORE', 'ITALIE', 'Non', '2023-02-22', '14:46:00', 'DJOMALEU', 'CHU', 'AUTOMATE ELECTROPHORESE'),
(2, '2023-02-21 01:00:00', '10:20:00', 'Stock', 'INTEK', 'FRANCE', 'Non', '2023-02-23', '11:31:00', 'KOUANGA', 'HÔPITAL RÉGIONAL DE MAROUA', 'AUTOMATE ELISA'),
(3, '2023-11-28', '14:45:00', 'Client', 'ALWAYS', 'FRANCE', 'Non', '2023-11-28', '14:34:00', 'MOUTONG', 'HOPITAL DISTRICT DEIDO', 'HUMASTAR');

-- --------------------------------------------------------

--
-- Structure de la table : Removal_form
--

-- Suppression de la table :
drop table if exists `removal_form`;

-- Creation de la table
create table if not exists `removal_form` (
	`idRmForm` int auto_increment not null,
     `jour` date not null,
     `hrm` time not null,
     -- `mrm` int not null,
     `nomClnt` varchar(32) not null,
     `nomHware` varchar(32) not null,
     `nomAgent` varchar(32) not null,
     `accessoires` enum('Oui', 'Non') collate utf8mb4_unicode_520_ci default 'Non',
     
     primary key (`idRmForm`),
     
     -- foreign key
     key `fk_clients_nom_clnt` (`nomClnt`),
     key `fk_hardware_nom_hware` (`nomHware`),
     key `fk_agents_nom_agent` (`nomAgent`)
) Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci;

--
-- Structure de la table : action_form
--

-- Suppression de la table si elle existe
drop table if exists `action_form`;

-- Creation de la table
create table if not exists `action_form` (
	`idAction` int auto_increment not null,
    `jour` date not null,
    `harrivee` time not null,
    -- `marrivee` int not null,
    `hfin` time not null,
    -- `mfin` int not null,
    `nomCustomer` varchar(32) not null,
    `nomHdre` varchar(32) not null,
    `statut` enum('Sous garantie', 'Hors garantie', 'Contrat de maintenance', 'Hors contrat de maintenance') collate utf8mb4_unicode_520_ci default 'Hors contrat de maintenance',
    
    primary key (`idAction`),
    
    -- foreign key
    key `fk_clients_nom_customer` (`nomCustomer`),
    key `fk_hardware_nom_hdre` (`nomHdre`)
) Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci;

--
-- Structure de la table : history_record
--

-- Suppression de la table
drop table if exists `history_record`;

-- Creation de la table
create table if not exists `history_record` (
	`idRecord` int auto_increment not null,
    `idHardware` int not null,
    `jour` date not null,
    `motif` varchar(255) collate utf8mb4_unicode_520_ci not null,
    `idAction` int not null,
    `nomAgt` varchar(32) not null,
    
    primary key (`idRecord`),
    
    -- foreign key
    key `fk_action_form_id_action` (`idAction`),
    key `fk_agents_nom_agt` (`nomAgt`)
) Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci;

-- 
-- Structure de la table : install_form_tpyeC ou WP21A
--

-- Suppression de la table
drop table if exists `install_form_typeC`;

-- Creation de la table
create table if not exists `install_form_typeC` (
	`idFormC` int auto_increment not null,
    `jour` date not null,
    `hdebut` time not null,
    -- `mdebut` int not null,
    `hfin` time not null,
    -- `mfin` int not null,
    `nomCusto` varchar(32) not null,
    `nomEqpmt` varchar(32) not null,
    
    primary key (`idFormC`),
    
    -- foreign key
    key `fk_clients_nom_custo` (`nomCusto`),
    key `fk_hardware_nom_eqpmt` (`nomEqpmt`)
) Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci;

--
-- Chargement des données de la table `install_form_typec`
--

insert into `install_form_typec` (`idFormC`, `jour`, `hdebut`, `hfin`, `nomCusto`, `nomEqpmt`) values
(1, '2023-11-30', '16:30:00', '20:00:00', 'HOPITAL DISTRICT DEIDO', 'HUMASTAR');

--
-- Structure de la tabel : install_form_typeB ou KT60
--

-- Suppression de la table 
drop table if exists `install_form_typeB`;

-- Creation de la table
create table if not exists `install_form_typeB` (
	`idFormB` int auto_increment not null,
    `jour` date not null,
    `hdebut` time not null,
    -- `mdebut` int not null,
    `hfin` time not null,
    -- `mfin` int not null,
    `nomCtmer` varchar(32) not null,
    `nomHrdw` varchar(32) not null,
    
    primary key (`idFormB`),
    
    -- foreign key
    key `fk_clients_nom_ctmer` (`nomCtmer`),
    key `fk_hardware_nom_hrdw` (`nomHrdw`)
) Engine=InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_520_ci;

--
-- Contraintes sur les tables
--

-- Contraintes pour la table : pre_installation_form
-- alter table `pre_install_form`
	-- add constraint `fk_clients_nom_client` foreign key (`nomClient`) references `clients` (`nomClient`),
    -- add constraint `fk_hardware_nom_hardware` foreign key (`nomHardware`) references `hardware` (`nomHardware`);

-- Contrainte pour la table : receipt_form
-- alter table `receipt_form`
	-- add constraint `fk_clients_nom_clt` foreign key (`nomClt`) references `clients` (`nomClient`),
	-- add constraint `fk_hardware_nom_hard` foreign key (`nomHard`) references `hardware` (`nomHardware`);

-- Contraintes pour la table : removal_form
-- alter table `removal_form`
	-- add constraint `fk_clients_nom_clnt` foreign key (`nomClnt`) references `clients` (`nomClient`),
    -- add constraint `fk_hardware_nom_hware` foreign key (`nomHware`) references `hardware` (`nomHardware`),
    -- add constraint `fk_agents_nom_agent` foreign key (`nomAgent`) references `agents` (`nomAgent`);

-- Contraintes pour la table : action_form
-- alter table `action_form`
	-- add constraint `fk_clients_nom_customer` foreign key (`nomCustomer`) references `clients` (`nomClient`),
    -- add constraint `fk_hardware_nom_hdre` foreign key (`nomHdre`) references `hardware` (`nomHardware`);

-- Contraintes pour la table : history_record
-- alter table `history_record`
	-- add constraint `fk_action_form_id_action` foreign key (`idAction`) references `action_form` (`idAction`),
    -- add constraint `fk_agents_nom_agt` foreign key (`nomAgt`) references `agents` (`nomAgent`);

-- Contraintes pour la table : install_form_typeC
-- alter table `install_form_typeC`
	-- add constraint `fk_clients_nom_custo` foreign key (`nomCusto`) references `clients` (`nomClient`),
    -- add constraint `fk_hardware_nom_eqpmt` foreign key (`nomEqpmt`) references `hardware` (`nomHardware`);

-- Contraintes pour la table : install_form_typeC
-- alter table `install_form_typeB`
	-- add constraint `fk_clients_nom_ctmer` foreign key (`nomCtmer`) references `clients` (`nomClient`),
    -- add constraint `fk_hardware_nom_hrdw` foreign key (`nomHrdw`) references `hardware` (`nomHardware`);
    
-- Fin du script --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 

