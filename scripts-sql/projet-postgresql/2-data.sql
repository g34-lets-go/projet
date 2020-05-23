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

-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES 
  (1, 'geek', 'geek', 'geek@3il.fr' ),
  (2, 'chef', 'chef', 'chef@3il.fr' ),
  (3, 'job', 'job', 'job@3il.fr' );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;

-- Vélo

INSERT INTO velo (id_velo, typevelo) VALUES 
  ( 1, 'VTT' ),
  ( 2, 'VTT electrique' ),
  ( 3, 'VTT' ),
  ( 4, 'VTT electrique' );

ALTER TABLE velo ALTER COLUMN id_velo RESTART WITH 5;

-- Dossard

INSERT INTO dossard ( numero_dossard ) VALUES 
  ( 1 ),
  ( 2 ),
  ( 3 ),
  ( 4 );

ALTER TABLE dossard ALTER COLUMN numero_dossard RESTART WITH 5;


-- Participant

INSERT INTO participant (matricule_p, nom, prenom, date_naiss, telephone, email, adresse, attestations_ok, frais_paye, repas_supplementaire, id_velo ) VALUES 
 	( 1, 'MOUAFO', 'Paul Denilson', '2020-02-03', '05 55 99 11 11', 'mouafo@gmail.com', '13 rue pasteur', TRUE, TRUE, 2, 1 ),
  	( 2, 'MALO', 'Paul', '2020-03-02', '05 55 99 11 11', 'malo@gmail.com', '15 rue patrick', TRUE, TRUE, 1, 2 ),
	( 3, 'MOUFO', 'Denilson', '2020-05-03', '05 55 99 11 11', 'moufo@gmail.com', '3 avenue paul', FALSE, TRUE, 0, 3 ),
	( 4, 'MAFO', 'Paulo', '2020-03-05', '05 55 99 11 11', 'mafo@gmail.com', '16 rue pere', TRUE, TRUE, 2, 4 );
  	
ALTER TABLE participant ALTER COLUMN matricule_p RESTART WITH 5;

-- Course

INSERT INTO course (id_course, nom_course, heure_depart, lieu_depart, lieu_arrivee) VALUES 
  ( 1, 'Mini Bol d''air', '09:00', 'Village du Dognon (Ste Marie de Vaux)', 'Club de Canoë Kayak de St Victurnien'),
  ( 2, 'Bol d''air', '09:00', 'Club de Canoë Kayak', 'Club de Canoë Kayak de St Victurnien'),
  ( 3, 'Les 2 bol d''air', '09:00', 'CLub de Canoë Kayak', 'Club de Canoë Kayak de St Victurnien');	
  
     
--Equipe

INSERT INTO equipe(id_equipe, nom_equipe, id_capitaine, id_equipier, id_course, numero_dossard) VALUES
	(1,'Barca',	1, 2, 1, 1),
	(2,'Real', 3, 4, 2, 3);

ALTER TABLE equipe ALTER COLUMN id_equipe RESTART WITH 3;


-- Poste
  
INSERT INTO poste (id_poste, nom_poste, description_poste, horaires_poste, personnel_poste, localisation_poste, Equipement_necessaire, id_course, nb_personnel) VALUES 
	  (1, 'Signaleur', 'Signaler les participants', '08:30', 37, 'Partout', 'Rien', 3, 0),
	  (2, 'Ravitaillement', 'Ravitailler les points', '09:00', 6, 'Au club', 'Rien', 3, 0),
	  (3, 'Sécurité sur l''eau', 'Assurer la sécurité des participants sur l''eau', '09:00', 6, 'Sur l''eau', 'Gilet de sauvetage', 3, 0),
	  (4, 'Parking voiture', 'Vueiller sur les voitures', '07:00', 1, 'Parking voiture', 'Aucun', 3, 0),
	  (5, 'Parking vélo', 'Vueiller sur les vélos', '07:00', 1, 'Parking vélo', 'Aucun', 3, 0),
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
  ( 4, 'MOUANO', 'Jeremy', '14 ave albert', 'mouab@gmail.com', 1, TRUE, '2020-05-10', FALSE, FALSE);

 ALTER TABLE benevole ALTER COLUMN matricule_b RESTART WITH 5;


