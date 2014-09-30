CREATE TABLE Familia (
	nom text,
	idFamilia int AUTOINCREMENT,
	PRIMARY KEY (idFamilia ASC)
	
);

CREATE TABLE Subfamilia (
	nom text,
	idSubfamilia int AUTOINCREMENT,

	PRIMARY KEY (idSubfamilia ASC),
	FOREIGN KEY (idSubfamilia) REFERENCES Familia(idFamilia)
);

CREATE TABLE Marca (
	nom text,
	PRIMARY KEY (nom ASC)
);

CREATE TABLE Campanya (
	nom text,
	data_fi text,
	data_inici text,
	numArticles int ,
	import real,

	CONSTRAINT num_articles_positive CHECK(numArticles >= 0)
	CONSTRAINT positive_import CHECK(import > 0),
	PRIMARY KEY (nom, data_fi, data_inici) 
);

CREATE TABLE Entrada (
	fila int,
	seient int,
	preu real,
	IVA real,
	importTotal real,

	PRIMARY KEY (fila, seient),
	FOREIGN KEY (fila,seient) REFERENCES EntradaCampanya (fila,seient)
);

CREATE TABLE EntradaCampanya (
	fila int,
	seient int,
	nom text,
	data_fi text,
	data_inici text,

	PRIMARY KEY (fila, seient, nom, data_fi, data_inici),
	FOREIGN KEY (fila, seient) REFERENCES Entrada (fila,seient),
	FOREIGN KEY (nom, data_fi, data_inici) REFERENCES Campanya (nom, data_fi, data_inici) 
);

CREATE TABLE Article (
	idArticle int,
	nom text,
	talla text,
	color text,
	preu real,
	IVA real,

	PRIMARY KEY (idArticle ASC),
	FOREIGN KEY (idArticle) REFERENCES Subfamilia(idSubfamilia),
	FOREIGN KEY (idArticle) REFERENCES Marca(nom),
	FOREIGN KEY (idArticle) REFERENCES Campanya(nom)
);

CREATE TABLE ArticleCampanya (
	idArticle int,
	nom text,
	data_fi text,
	data_inici text,

	PRIMARY KEY (idArticle, nom, data_fi, data_inici),
	FOREIGN KEY (idArticle) REFERENCES Article(idArticle),
	FOREIGN KEY (nom, data_fi, data_inici) REFERENCES Campanya (nom, data_fi, data_inici)
);

CREATE TABLE Usuari (
	nom text,
	contrasenya text,
	direccio text,
	
	PRIMARY KEY (nom)
);

CREATE TABLE UsuariArticle (
	nom text,
	idArticle int,
	unitatsComprades int,
	preuTotal real,
	impostos real,

	PRIMARY KEY (nom, idArticle),
	FOREIGN KEY (nom) REFERENCES Usuari (nom),
	FOREIGN KEY (idArticle) REFERENCES Article (idArticle)
	
);
