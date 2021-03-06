SET search_path TO projet;


-- Supprimer toutes les données
DELETE FROM compte;
DELETE FROM velo;
DELETE FROM dossard;
DELETE FROM benevole;
DELETE FROM participant;
DELETE FROM poste;
DELETE FROM course;
DELETE FROM equipe;
DELETE FROM categorie;

-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES 
  (1, 'geek', 'geek', 'geek@3il.fr' ),
  (2, 'chef', 'chef', 'chef@3il.fr' ),
  (3, 'job', 'job', 'job@3il.fr' );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;

-- Vélo

INSERT INTO velo (id_velo, typevelo) VALUES 
  ( 1, 'VTT' ),
  ( 2, 'VTT electrique' );

ALTER TABLE velo ALTER COLUMN id_velo RESTART WITH 3;

-- Dossard

INSERT INTO dossard ( numero_dossard ) VALUES 
  ( 1 ),
  ( 2 ),
  ( 3 ),
  ( 4 ),
  ( 5 ),
  ( 6 ),
  ( 7 ),
  ( 8 ),
  ( 9 ),
  ( 10 ),
  ( 11 ),
  ( 12 );

ALTER TABLE dossard ALTER COLUMN numero_dossard RESTART WITH 13;


-- Participant

INSERT INTO participant (matricule_p, nom, prenom, date_naiss, telephone, email, adresse, attestations_ok, frais_paye, repas_supplementaire, id_velo,valider ) VALUES 
 	( 1, 'MOUAFO', 'Paul Denilson', '2020-02-03', 753756979, 'mouafo@gmail.com', '13 rue pasteur', TRUE, 0, 2, 1, TRUE),
  	( 2, 'MALO', 'Paul', '2020-03-02', 753756979, 'malo@gmail.com', '15 rue patrick', TRUE, 1, 2, 2, TRUE ),
	( 3, 'MOUFO', 'Denilson', '2020-05-03', 753756979, 'moufo@gmail.com', '3 avenue paul', FALSE, 1, 0, 1, TRUE ),
	( 4, 'DJIHOUA', 'Edy Wise', '2020-03-05', 753756979, 'edywise@gmail.com', '16 rue fraternite', TRUE, 0, 0, 2, TRUE ),
	( 5, 'MAFO', 'Pule', '2020-03-05', 753756979, 'miafo@gmail.com', '1 rue descartes', TRUE, 1, 2, 2, TRUE ),
	( 6, 'TCHAMENI', 'Rose Pierrette', '2020-06-12', 691544196, 'rosetchameni508@gmail.com', '6 rue bonamoussadi', FALSE, 1, 2, 1, TRUE ),
	( 7, 'TCHATCHOU', 'Wilfried', '2020-07-07', 619351043, 'sinkamwilfried@gmail.com', '7 rue mendong', TRUE, 0, 3, 2, TRUE ),
	( 8, 'ELOUNDOU', 'Kevin', '2020-03-05', 753456979, 'kevin@gmail.com', '8 rue degaulle', FALSE, 0, 3, 1, FALSE ),
	( 9, 'KAMGA', 'Arien', '2020-03-05', 757756979, 'arien@gmail.com', '6 rue duchien', TRUE, 1, 0, 2, TRUE ),
	( 10, 'DUPITEN', 'Coul', '2020-03-05', 763756979, 'coul@gmail.com', '3 rue patate', FALSE, 0, 0, 1, FALSE ),
	( 11, 'AMOUR', 'Divine', '2020-02-14', 743756979, 'amour@gmail.com', '2 rue amour', FALSE, 0, 2, 2, FALSE ),
	( 12, 'SIME', 'Kevin', '2020-10-05', 753756479, 'donne@gmail.com', '25 rue puce', TRUE, 1, 2, 1, TRUE ),
	( 13, 'NANDA', 'Vianney', '2020-03-02', 753756979, 'pars@gmail.com', '1 rue perdre', FALSE, 0, 5, 1, FALSE ),
	( 14, 'NDJIKI', 'Christian', '2020-03-03', 793056979, 'kiffnobeat@gmail.com', '16 rue lavine', TRUE, 1, 5, 1, TRUE ),
	( 15, 'KENGO', 'Benito', '2020-11-05', 053758979, 'zuzu@gmail.com', '20 rue aloes', FALSE, 0, 1, 1, TRUE ),
	( 16, 'NJOYA', 'Lauren', '2020-03-05', 053756979, 'pring@gmail.com', '2 rue cerra', FALSE, 1, 1, 1, FALSE ),
	( 17, 'MASSADO', 'Brandy', '2020-07-05', 757756979, 'shullfke@gmail.com', '16 rue anatole', TRUE, 1, 2, 1, TRUE ),
	( 18, 'AKANA', 'Judicäel', '2020-03-04', 753786979, 'rague@gmail.com', '5 rue peureux', TRUE, 1, 2, 1, FALSE ),
	( 19, 'TATCHI', 'Raymond', '2020-03-05', 653756679, 'ruine@gmail.com', '13 rue gift', TRUE, 1, 0, 1, FALSE ),
	( 20, 'RAYMOND', 'Redington', '2020-08-06', 756756989, 'poule@gmail.com', '1 rue simbock', FALSE, 0, 0, 2, FALSE ),
	( 21, 'KOUAM', 'Arnold', '2020-03-05', 753556979, 'aero@gmail.com', '9 rue puceau', FALSE, 1, 2, 1, TRUE ),
	( 22, 'CROCKMOU', 'Arold', '2020-04-01', 755055079, 'dine@gmail.com', '16 rue kong', TRUE, 0, 2, 2, FALSE ),
	( 23, 'JUPITER', 'Marc', '2020-03-06', 705755970, 'pouabe@gmail.com', '27 rue douala', TRUE, 1, 1, 2, TRUE ),
	( 24, 'SIMSOMS', 'Luc', '2020-05-04', 756753979, 'fille@gmail.com', '45 rue lauren', FALSE, 0, 1, 2, TRUE );
  	
ALTER TABLE participant ALTER COLUMN matricule_p RESTART WITH 25;

-- Course

INSERT INTO course (id_course, nom_course, heure_depart, lieu_depart, lieu_arrivee) VALUES 
  ( 1, 'Mini Bol d''air', '09:00', 'Village du Dognon (Ste Marie de Vaux)', 'Club de Canoë Kayak de St Victurnien'),
  ( 2, 'Bol d''air', '09:00', 'Club de Canoë Kayak', 'Club de Canoë Kayak de St Victurnien'),
  ( 3, 'Les 2 bol d''air', '09:00', 'CLub de Canoë Kayak', 'Club de Canoë Kayak de St Victurnien');	
  
     
--Equipe

INSERT INTO equipe(id_equipe, nom_equipe, id_capitaine, id_equipier, id_course, numero_dossard) VALUES
	(1,'Barca',	1, 2, 1, 1),
	(2,'3IL',	3, 4, 1, 2),
	(3,'Teams',	5, 6, 1, 3),
	(4,'Real', 7, 8, 2, 4),
	(5,'Douaba', 9, 10, 2, 5),
	(6,'Fraternite', 11, 12, 2, 6),
	(7,'Can', 13, 14, 1, 7),
	(8,'Cer', 15, 16, 1, 8),
	(9,'Scorpion', 17, 18, 1, 9),
	(10,'Douala', 19, 20, 1, 10),
	(11,'Les PORC', 21, 22, 1, 11),
	(12,'Les Matchos', 23, 24, 1, 12);
ALTER TABLE equipe ALTER COLUMN id_equipe RESTART WITH 13;



-- Poste
  
INSERT INTO poste (id_poste, nom_poste, description_poste, horaires_poste, personnel_poste, localisation_poste, Equipement_necessaire, id_course, nb_personnel) VALUES 
	  (1, 'Signaleur', 'Signaler les participants', '08:30', 37, 'Partout', 'Rien', 3, 0),
	  (2, 'Ravitaillement', 'Ravitailler les points', '09:00', 6, 'Au club', 'Rien', 3, 0),
	  (3, 'Sécurité sur l''eau', 'Assurer la sécurité des participants sur l''eau', '09:00', 6, 'Sur l''eau', 'Gilet de sauvetage', 3, 0),
	  (4, 'Parking voiture', 'Veiller sur les voitures', '07:00', 1, 'Parking voiture', 'Aucun', 3, 0),
	  (5, 'Parking vélo', 'Veiller sur les vélos', '07:00', 1, 'Parking vélo', 'Aucun', 3, 0),
	  (6, 'Remise des dossards', 'Remettre les dossards aux participants', '07:00', 4, 'Départ course', 'Dossards', 3, 0),
	  (7, 'Chronométrage', 'Chronomètre les 3 courses de chacun des bols', '09:30', 2, 'Départ et arrivée des différentes courses', '1 Chrono pour chaque course = 6 chronos', 3, 0),
	  (8, 'Moto (fermeture)', 'Clôturer les différentees courses des bols', '09:00', 2, 'Derrière les courses', 'Moto', 3, 0),
	  (9, 'Buvette', 'Fournir à boire aux participants', '07:00', 5, 'Point sur les parcours', 'Verre, bouteille d''eau et sucre', 3, 0),
	  (10, 'Repas', 'Charger des diférents des bols', '11:00', 3, 'Club', 'Courvets de cuisine', 3, 0),
	  (11, 'Récuperer dossards et puces', 'Récupérer les puces et dossars aux participants', '12:00', 1, 'Club', 'Aucun', 3, 0),
 	  (12, 'Photographe', 'Photographier les différentees courses des bols', '07:00', 2, 'Partout', 'Appareil photo', 3, 0);

  
ALTER TABLE poste ALTER COLUMN id_poste RESTART WITH 13;


-- Benevole

INSERT INTO benevole (matricule_b, nom, prenom, adresse, email, id_poste, permis_conduire, date_naissance, membre_ok, valider) VALUES 
  ( 1, 'MOUAFO', 'Paul Denilson', '184 avenue albert thomas', 'mouafo@gmail.com', 1, TRUE, '2020-06-07', TRUE, TRUE),
  ( 2, 'MOUAPO', 'Mark', '154 avenue albert thomas', 'mouapo@gmail.com', 2, FALSE, '2020-06-06', TRUE, TRUE),
  ( 3, 'MOUABO', 'Jeremy', '14 avenue albert', 'mouabo@gmail.com', 3, TRUE, '2020-05-06', FALSE, TRUE),
  ( 4, 'MOUANO', 'Jeremy', '14 ave albert', 'mouab@gmail.com', 1, TRUE, '2020-05-10', FALSE, FALSE),
  ( 5, 'JEAN', 'Paul', '23 Rue du Binks', 'vitae@Duissitamet.edu', 4, TRUE, '2020-06-07', FALSE, TRUE),
  ( 6, 'JEAN', 'Taka', '23 Rue de la joie', 'mollis.Integer@netuset.net', 6, TRUE, '2020-06-09', TRUE, TRUE),
  ( 7, 'JULE', 'Paule', '15 Rue de Blanche', 'sit.amet.metus@nullaIntincidunt.com', 10, TRUE, '2020-06-13', TRUE, FALSE),
  ( 8, 'GEREMY', 'Paul', '17 Avenue Wagan', 'in.sodales@placeratvelitQuisque.com', 2, TRUE, '2020-06-12', FALSE, FALSE),
  ( 9, 'JULIEN', 'Paul', '20 Rue du Capitaine', 'sed@afelisullamcorper.ca', 8, TRUE, '2020-06-11', FALSE, TRUE),
  ( 10, 'BERNARD', 'Antony', '50 Rue du Cash', 'et.malesuada.fames@Nuncmauris.net', 10, TRUE, '2020-06-15', TRUE, FALSE),
  ( 11, 'JUNIOR', 'Nana', '1 Allee Hélène-Repère', 'elementum.lorem.ut@hendreritDonecporttitor.com', 3, TRUE, '2020-06-07', FALSE, TRUE),
  ( 12, 'MESSI', 'Lionnel', '37 Rue de Solignac', 'Integer.vitae@Donecconsectetuer.net', 1, TRUE, '2020-06-16', FALSE, TRUE),
  ( 13, 'BARTHELEMI', 'Diaz', '23 Rue de la Fierté', 'adipiscing.enim@Pellentesqueut.net', 10, TRUE, '2020-06-07', TRUE, FALSE),
  ( 14, 'BERTRAND', 'Paul', '4 Rue du Jeu', 'lectus@Nunclectuspede.edu', 1, TRUE, '2020-06-20', TRUE, TRUE),
  ( 15, 'FRANCIS', 'Pierre', '5 Avenue des salopards', 'vitae@Duissitamet.edu', 9, TRUE, '2020-06-07', FALSE, TRUE),
  ( 16, 'BELLA', 'Georgette', '16 Rue du Boss', 'mollis.Integer@netuset.net', 6, TRUE, '2020-06-09', TRUE, TRUE),
  ( 17, 'ABIBA', 'Paulle', '23 Rue la Vaillante', 'sit.amet.metus@nullaIntincidunt.com', 7, TRUE, '2020-06-13', TRUE, FALSE),
  ( 18, 'ABENA', 'Macia', '13 Rue du Limousin', 'in.sodales@placeratvelitQuisque.com', 2, TRUE, '2020-06-12', FALSE, FALSE),
  ( 19, 'SERGIO', 'Paul', '23 Rue des Frères', 'sed@afelisullamcorper.ca', 8, TRUE, '2020-08-11', FALSE, TRUE),
  ( 20, 'DENIS', 'Claire', '28 Impasse de Roumanet', 'et.malesuada.fames@Nuncmauris.net', 12, TRUE, '2020-9-15', TRUE, FALSE),
  ( 21, 'PATSON', 'Paul', '2 Rue des Bénédictins', 'elementum.lorem.ut@hendreritDonecporttitor.com', 3, TRUE, '2020-06-07', FALSE, TRUE),
  ( 22, 'ANTOINETTE', 'Pauline', '14 Rue du Charbon', 'Integer.vitae@Donecconsectetuer.net', 1, TRUE, '2020-06-16', FALSE, TRUE),
  ( 23, 'PROSPER', 'Rio', '23 Rue du Bled', 'vitae@Duissitamet.edu', 5, TRUE, '2020-11-07', FALSE, TRUE),
  ( 24, 'BERLIN', 'Naerobi', '4 Rue des Marchands', 'vitae@Duissitamet.edu', 12, TRUE, '2020-10-07', FALSE, TRUE),
  ( 25, 'MOSCOU', 'Tokyo', '8 Boulevard de la Libération', 'adipiscing.enim@Pellentesqueut.net', 1, TRUE, '2020-06-07', TRUE, FALSE);
  
  
 ALTER TABLE benevole ALTER COLUMN matricule_b RESTART WITH 26;
 
 -- Categorie
 
 INSERT INTO categorie( id, libelle, id_equipe) VALUES
     ( 1, 'HOMME', 1) ,
     ( 2, 'FEMME', 2) ,
     ( 3, 'MIXTE', 3) ,
     ( 4, 'VAE'  , 4);
     
 ALTER TABLE categorie ALTER COLUMN id RESTART WITH 5;

