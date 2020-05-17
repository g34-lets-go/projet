SET search_path TO projet;


-- Supprimer toutes les données
--DELETE FROM compte;
--DELETE FROM benevole;
--DELETE FROM participant;
--DELETE FROM poste;
--DELETE FROM etre_affecte;


-- Compte

--INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES 
 -- (1, 'geek', 'geek', 'geek@3il.fr' ),
 -- (2, 'chef', 'chef', 'chef@3il.fr' ),
 -- (3, 'job', 'job', 'job@3il.fr' );

--ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;


-- Benevole

--INSERT INTO benevole (matricule_b, nom, prenom, adresse, email, poste_souhaite, permis_conduire, date_naissance, id_type_benevole) VALUES 
--  ( 1, 'MOUAFO', 'Paul Denilson', '184 avenue albert thomas', 'mouafo@gmail.com', TRUE, '2020-06-07', 1),
--  ( 2, 'MOUAPO', 'Mark', '154 avenue albert thomas', 'mouapo@gmail.com', FALSE, '2020-06-06', 2);
--  ( 3, 'MOUABO', 'Jeremy', '14 avenue albert', 'mouabo@gmail.com', TRUE, '2020-05-06', 1);

--ALTER TABLE benevole ALTER COLUMN matricule_b RESTART WITH 4;


-- Poste
  
--INSERT INTO poste (id_poste, nom_poste, description_poste, horaires_poste, personnel_poste, localisation_poste, Equipement_necessaire, id_course) VALUES 
--  (1, 'Signaleur', 'Signaler les participants', '08:00', 37, 'Partout', 'Rien', 1),
--  (2, 'Ravitaillement', 'Ravitailler les points', '08:00', 6, 'Au club', 'Rien', 1),
--  (3, 'Sécurité sur l''eau', 'Assurer la sécurité des participants sur l''eau', '08:00', 6, 'Sur l''eau', 'Gilet de sauvetage', 1),

--ALTER TABLE categorie ALTER COLUMN idcategorie RESTART WITH 4;

---- Etre_affecte
--
--INSERT INTO etre_affecte (matricule_b, id_poste) VALUES 
--  ( 1, 1 ),
--  ( 2, 2 ),
--  ( 3, 3 );


-- Participant

--INSERT INTO participant (matricule_p, nom, prenom, date_naiss, telephone, sexe, attestation_medical_pieds, attestation_medical_velo, attestation_medical_canoe, frais_paye, repas_supplementaire, id_equipe, id_equipe_etre_coequipier, id_velo ) VALUES 
  --( 1, 'MOUAFO', 'Paul Denilson', '2020-02-03', '05 55 99 11 11'),
  --( 2, 'MALO', 'Paul', '2020-03-02', '05 55 99 11 11' );

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

