CREATE TABLE Familia (
	nom text,
	idFamilia INTEGER,

	PRIMARY KEY (idFamilia)
);

CREATE TABLE Subfamilia (
	nom text,
	idSubfamilia INTEGER,
	familia int,

	PRIMARY KEY (idSubfamilia, familia),
	FOREIGN KEY (familia) REFERENCES Familia(idFamilia)
);

CREATE TABLE Marca (
	nom text,
	PRIMARY KEY (nom)
);

CREATE TABLE Campanya (
	nom text,
	data_fi text,
	data_inici text,
	numArticles int CHECK (numArticles >= 0),
	import real,

	--CONSTRAINT num_articles_positive CHECK(numArticles >= 0)
	CONSTRAINT positive_import CHECK(import > 0),
	PRIMARY KEY (nom, data_fi, data_inici) 
);

CREATE TABLE Entrada (
	fila int,
	seient int,
	campanya text,
	data_fi text,
	data_inici text,
	preu real,
	IVA real,
	importTotal real,

	PRIMARY KEY (fila, seient, campanya, data_fi, data_inici),
	FOREIGN KEY (campanya, data_fi, data_inici) REFERENCES Campanya(nom, data_fi, data_inici) 
);

CREATE TABLE Producte (
	idProducte int,
	nom text,
	color text,
	marca text,
	subfamilia int,

	PRIMARY KEY (idProducte),
	FOREIGN KEY (marca) REFERENCES Marca(nom),
	FOREIGN KEY (subfamilia) REFERENCES Subfamilia(idSubfamilia)
);

CREATE TABLE Article (
	idArticle int,
	producte int,
	campanya text,
	talla text,
	color text,
	preu real,
	IVA real,

	PRIMARY KEY (idArticle, producte),
	FOREIGN KEY (producte) REFERENCES Producte(idProducte),
	FOREIGN KEY (campanya) REFERENCES Campanya(nom)
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
