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
	numArticles  int,
	import real,

	CONSTRAINT positive_num_articles CHECK(numArticles >= 0),
	CONSTRAINT positive_import CHECK(import >= 0),

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
	venuda int,


	CONSTRAINT positive_fila CHECK(fila >= 0),	
	CONSTRAINT positive_seient CHECK(seient >= 0),	
	CONSTRAINT positive_preu CHECK(preu >= 0),
	CONSTRAINT positive_IVA CHECK(IVA >= 0),
	CONSTRAINT positive_importTotal CHECK(importTotal >= 0),
	CONSTRAINT venuda_bool CHECK(venuda == 0 or venuda == 1),

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
	stock int,
	preu real,
	IVA real,

	CONSTRAINT positive_stock CHECK(stock >= 0),
	CONSTRAINT positive_preu CHECK(preu >= 0),
	CONSTRAINT positive_IVA CHECK(IVA >= 0),

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

	CONSTRAINT positive_unitatsComprades CHECK(unitatsComprades >= 1),
	CONSTRAINT positive_preuTotal CHECK(preuTotal >= 0),
	CONSTRAINT positive_impostos CHECK(impostos >= 0),

	PRIMARY KEY (nom, idArticle),
	FOREIGN KEY (nom) REFERENCES Usuari (nom),
	FOREIGN KEY (idArticle) REFERENCES Article (idArticle)
);
