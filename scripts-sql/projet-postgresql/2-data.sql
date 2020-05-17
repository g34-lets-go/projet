SET search_path TO projet;


-- Supprimer toutes les données
--DELETE FROM compte;
--DELETE FROM velo;
--DELETE FROM type_benevole;
--DELETE FROM benevole;
--DELETE FROM participant;
--DELETE FROM course;
--DELETE FROM type_course;
--DELETE FROM poste;
--DELETE FROM equipe;
--DELETE FROM dossard;
--DELETE FROM etre_affecte;


-- Compte

--INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES 
 -- (1, 'geek', 'geek', 'geek@3il.fr' ),
 -- (2, 'chef', 'chef', 'chef@3il.fr' ),
 -- (3, 'job', 'job', 'job@3il.fr' );

--ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;


-- Velo

--INSERT INTO velo (id_velo, type_velo, ) VALUES 
--  ( 1, 'VTT' ),
--  ( 2, 'VTT electrique' );

--ALTER TABLE velo ALTER COLUMN id_velo RESTART WITH 3;

-- type_benevole
  
--INSERT INTO type_benevole (id_type_benevole  , nom_type_benevole ) VALUES 
 -- (1, 'Capitain' ),
  --(2, 'Equipier' ),
 -- (3, 'Capitain' ),
 -- (4, 'Equipier' );

--ALTER TABLE type_benevole ALTER COLUMN id_type_benevole RESTART WITH 5;


-- Benevole

--INSERT INTO benevole (matricule_b, nom, prenom, adresse, email, poste_souhaite, permis_conduire, date_naissance, id_type_benevole) VALUES 
--  ( 2020, 'MOUAFO', 'Paul Denilson', '184 avenue albert thomas', 'mouafo@gmail.com', true, 06/06/2020, 1),
--  ( 2021, 'MOUAFO', 'Paul Denilson', '184 avenue albert thomas', 'mouafo@gmail.com', true, 06/06/2020, 2);

--ALTER TABLE benevole ALTER COLUMN matricule_b RESTART WITH 3;


-- Participant

--INSERT INTO participant (matricule_p, nom, prenom, date_naiss, telephone, sexe, attestation_medical_pieds, attestation_medical_velo, attestation_medical_canoe, frais_paye, repas_supplementaire, id_equipe, id_equipe_etre_coequipier, id_velo ) VALUES 
  --( 1, 'MOUAFO', 'Paul Denilson' 02/02/2020, '05 55 99 11 11'),
  --( 2, 'Fax', '05 55 99 11 11' );

--ALTER TABLE participant ALTER COLUMN participant RESTART WITH 3;


-- Memo
--
--INSERT INTO memo (idmemo, titre, description, flagurgent, statut, effectif, budget, echeance, idcategorie ) VALUES 
--  ( 1, 'Mémo n°1', 'Texte du mémo n°1', TRUE,  2,   2,   1234.56,   {d  '2020-02-25' }, 1 ),
--  ( 2, 'Mémo n°2', 'Texte du mémo n°2', FALSE, 1,   4,   5000.00,   {d  '2020-05-18' }, 2 ),
--  ( 3, 'Mémo n°3', NULL, TRUE, 0, NULL, NULL, NULL, NULL );
--
--ALTER TABLE memo ALTER COLUMN idmemo RESTART WITH 4;
--
--
---- Concerner
--
--INSERT INTO concerner (idmemo, idPersonne) VALUES 
--  ( 1, 1 ),
--  ( 1, 2 ),
--  ( 1, 3 ),
--  ( 2, 1 ),
--  ( 2, 2 );
--
--
---- Service
--
--INSERT INTO service ( idservice, nom, anneecreation, flagsiege ) VALUES 
--  ( 1, 'Direction', 2007, TRUE ),
--  ( 2, 'Comptabilité', NULL, TRUE ),
--  ( 3, 'Agence Limoges', 2008, FALSE ),
--  ( 4, 'Agence Brive', 2014, FALSE );
--
--
--ALTER TABLE service ALTER COLUMN idservice RESTART WITH 5;

