SET search_path TO projet;


-- Schéma

DROP SCHEMA IF EXISTS projet CASCADE;
CREATE SCHEMA projet AUTHORIZATION projet;
GRANT ALL PRIVILEGES ON SCHEMA projet TO projet;


-- Tables

CREATE TABLE compte (
	idcompte		INT				GENERATED BY DEFAULT AS IDENTITY,
	pseudo			VARCHAR(25)		NOT NULL,
	motdepasse		VARCHAR(25) 	NOT NULL,
	email			VARCHAR(100)	NOT NULL,
	PRIMARY KEY (idcompte),
	UNIQUE (pseudo)
);


CREATE TABLE velo (
	id_velo			INT				GENERATED BY DEFAULT AS IDENTITY,
	typevelo		VARCHAR(50)		NOT NULL,
	PRIMARY KEY (id_velo)
);


CREATE TABLE dossard(
    numero_dossard 	INT 	 GENERATED BY DEFAULT AS IDENTITY,
	PRIMARY KEY (numero_dossard)
);
	

CREATE TABLE participant (
	matricule_p					INT				GENERATED BY DEFAULT AS IDENTITY,
	nom                  		VARCHAR (50) 	NOT NULL ,
    prenom                    	VARCHAR (50) 	NOT NULL ,
    date_naiss                	DATE	 		NOT NULL ,
    telephone                 	INT			 	NOT NULL ,
    email                      	VARCHAR (50) 	NOT NULL ,
    adresse                    	VARCHAR (50) 	NOT NULL ,
    attestations_ok			 	BOOLEAN  ,
    frais_paye                	INT  ,
    repas_supplementaire      	INT ,
    id_velo                   	INT ,
    valider						BOOLEAN,
	PRIMARY KEY (matricule_p),
	FOREIGN KEY (id_velo) REFERENCES velo (id_velo)
);


CREATE TABLE course (
	id_course    		INT				GENERATED BY DEFAULT AS IDENTITY,
	nom_course          VARCHAR (50),
	heure_depart 		TIME 			NOT NULL ,
    lieu_depart  		VARCHAR (50) 	NOT NULL ,
    lieu_arrivee 		VARCHAR (50) 	NOT NULL ,
   PRIMARY KEY (id_course)
);


CREATE TABLE equipe (	
	id_equipe 		INT				GENERATED BY DEFAULT AS IDENTITY, 
	nom_equipe		VARCHAR(50)		NOT NULL ,
	id_capitaine	INT				NOT NULL ,
	id_equipier		INT				NOT NULL ,
	id_course		INT				NOT NULL ,
    numero_dossard 	INT 			NOT NULL ,
	PRIMARY KEY (id_equipe),
	FOREIGN KEY (numero_dossard) REFERENCES dossard(numero_dossard),
	FOREIGN KEY (id_capitaine) REFERENCES participant(matricule_p),
	FOREIGN KEY (id_equipier) REFERENCES participant(matricule_p),
	FOREIGN KEY (id_course) REFERENCES course(id_course)
);


CREATE TABLE poste (
	id_poste				INT				GENERATED BY DEFAULT AS IDENTITY,
	nom_poste            	VARCHAR (50) 	NOT NULL ,
    description_poste     	VARCHAR (50) 	NOT NULL ,
    horaires_poste        	TIME 			NOT NULL ,
    personnel_poste      	INT 			NOT NULL ,
    localisation_poste    	TEXT 			NOT NULL ,
    Equipement_necessaire 	TEXT 			NOT NULL ,
    id_course             	INT 			NOT NULL ,
	nb_personnel	      	INT 			NOT NULL ,
    PRIMARY KEY (id_poste),
	FOREIGN KEY (id_course) REFERENCES course(id_course)
);


CREATE TABLE benevole (
	matricule_b			INT				GENERATED BY DEFAULT AS IDENTITY,
	nom              	VARCHAR (50) 	NOT NULL ,
    prenom          	VARCHAR (50) 	NOT NULL ,
    adresse          	VARCHAR (50) 	NOT NULL ,
    email            	VARCHAR (50) 	NOT NULL ,
    id_poste		  	INT 			NOT NULL ,
    permis_conduire  	BOOLEAN ,
    date_naissance   	DATE			NOT NULL ,
    membre_ok			BOOLEAN			NOT NULL ,
    valider				BOOLEAN ,
	PRIMARY KEY (matricule_b),
	FOREIGN KEY (id_poste) REFERENCES poste(id_poste)
	
);

CREATE TABLE posteSouhaiter (
	id				INT				GENERATED BY DEFAULT AS IDENTITY,
	poste		VARCHAR(100)	NOT NULL,
	nom			VARCHAR(25)		NOT NULL,
	Prenom		VARCHAR(25) 	NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE categorie (
<<<<<<< HEAD
    id            INT                GENERATED BY DEFAULT AS IDENTITY,
    libelle        VARCHAR(20)    NOT NULL,
    id_equipe    INT                NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_equipe) REFERENCES equipe(id_equipe)   
=======
	id			INT				GENERATED BY DEFAULT AS IDENTITY,
	libelle		VARCHAR(20)	NOT NULL,
	id_equipe	INT				NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (id_equipe) REFERENCES equipe(id_equipe)	
>>>>>>> branch 'master' of https://github.com/g34-lets-go/projet.git
);

-- Vues

--CREATE VIEW v_compte_avec_roles AS 
--	SELECT c.*, ARRAY_AGG( r.role ) AS roles
--	FROM compte c 
--	LEFT JOIN ROLE r ON c.idcompte = r.idcompte
--	GROUP BY c.idcompte;

